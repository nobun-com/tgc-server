package com.go2.classes.rest.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.HashMap;
import java.util.StringTokenizer;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.go2.classes.business.service.UserCartService;

@Controller
public class PaymentController {

    @Resource
    private UserCartService userCartService; // Injected by Spring

    private static String gv_APIEndpoint = "";

    @Value("${serverurl}")
    private String serverUrl = "";

    private String returnURL = "/paymentcomplete";
    private String cancelURL = "/cancel";

    @RequestMapping(value = "/bookCart")
    public void bookCart(Model model, HttpServletResponse response, HttpSession session, HttpServletRequest request) throws IOException {

	String PAYPAL_URL;

	boolean bSandbox = true;
	if (bSandbox == true) {
	    gv_APIEndpoint = "https://api-3t.sandbox.paypal.com/nvp";
	    PAYPAL_URL = "https://www.sandbox.paypal.com/webscr?cmd=_express-checkout&token=";
	} else {
	    gv_APIEndpoint = "https://api-3t.paypal.com/nvp";
	    PAYPAL_URL = "https://www.paypal.com/cgi-bin/webscr?cmd=_express-checkout&token=";
	}

	String token = callShortcutExpressCheckout(request);
	response.sendRedirect(PAYPAL_URL + token);
    }

    @RequestMapping(value = "/paymentcomplete", method = RequestMethod.GET)
    public String completePayment(Model model, HttpServletRequest request) {
	HashMap<String, String> nvp = confirmPayment(request);
	String strAck = nvp.get("ACK");
	model.addAttribute("ack", strAck);
	if (strAck.equalsIgnoreCase("Success")) {
	    Long userId = (Long) request.getSession().getAttribute("userId");
	    String transactionId = nvp.get("PAYMENTINFO_0_TRANSACTIONID");
	    userCartService.bookAllCarts(userId, transactionId);
	    model.addAttribute("message", "Payment ammount : " + nvp.get("PAYMENTINFO_0_AMT"));
	} else {
	    model.addAttribute("message", nvp.get("L_SHORTMESSAGE0"));
	}
	return ("payment-completed");
    }

    @RequestMapping(value = "/refund")
    public String refundPayment(Model model, @RequestParam(name = "bookingId") Long bookingId, HttpServletRequest request) throws ParseException {
	Long userId = (Long) request.getSession().getAttribute("userId");
	String tranctionID = userCartService.getTransactionId(userId, bookingId);

	String nvpstr = "&TRANSACTIONID=" + tranctionID + "&REFUNDTYPE==" + "Full"; // transaction_ID

	HashMap<String, String> nvp = httpcall("RefundTransaction", nvpstr, request);

	String strAck = nvp.get("ACK");
	model.addAttribute("ack", strAck);
	if (strAck.equalsIgnoreCase("Success")) {
	    userCartService.cancelBooking(userId, bookingId);
	    model.addAttribute("message", "Refunded ammount : " + nvp.get("GROSSREFUNDAMT"));
	} else {
	    model.addAttribute("message", nvp.get("L_LONGMESSAGE0"));
	}
	return ("payment-completed");
    }

    public String callShortcutExpressCheckout(HttpServletRequest request) {
	Long userId = (Long) request.getSession().getAttribute("userId");
	Double ammount = userCartService.getToatlFees(userId);
	String currencyCodeType = "USD";
	String paymentType = "Order";

	String nvpstr = "&PAYMENTREQUEST_0_AMT=" + ammount + "&PAYMENTREQUEST_0_PAYMENTACTION=" + paymentType + "&ReturnUrl=" + URLEncoder.encode(serverUrl + returnURL) + "&CANCELURL=" + URLEncoder.encode(serverUrl + cancelURL) + "&PAYMENTREQUEST_0_CURRENCYCODE=" + currencyCodeType;

	HashMap<String, String> nvp = httpcall("SetExpressCheckout", nvpstr, request);
	String strAck = nvp.get("ACK").toString();
	if (strAck.equalsIgnoreCase("Success")) {
	    return (String) nvp.get("TOKEN");
	}
	return "";
    }

    public HashMap<String, String> httpcall(String methodName, String nvpStr, HttpServletRequest request) {

	String gv_APIUserName = "nobun.paypal-facilitator_api1.gmail.com";
	String gv_APIPassword = "MD4FN9RF97BAEUXH";
	String gv_APISignature = "An5ns1Kso7MWUdW4ErQKJJJ4qi4-ACKNhncRaNp0W7rpHUesxigkpH0H";
	String gv_Version = "94";
	String gv_BNCode = "PP-ECWizard";

	String agent = request.getHeader("User-Agent");
	String respText = "";

	String encodedData = "METHOD=" + methodName + "&VERSION=" + gv_Version + "&PWD=" + gv_APIPassword + "&USER=" + gv_APIUserName + "&SIGNATURE=" + gv_APISignature + nvpStr + "&BUTTONSOURCE=" + gv_BNCode;
	try {
	    URL postURL = new URL(gv_APIEndpoint);
	    HttpURLConnection conn = (HttpURLConnection) postURL.openConnection();
	    conn.setDoInput(true);
	    conn.setDoOutput(true);
	    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	    conn.setRequestProperty("User-Agent", agent);
	    conn.setRequestProperty("Content-Length", String.valueOf(encodedData.length()));
	    conn.setRequestMethod("POST");
	    DataOutputStream output = new DataOutputStream(conn.getOutputStream());
	    output.writeBytes(encodedData);
	    output.flush();
	    output.close();
	    int rc = conn.getResponseCode();
	    if (rc != -1) {
		BufferedReader is = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String _line = null;
		while (((_line = is.readLine()) != null)) {
		    respText = respText + _line;
		}
	    }
	    return deformatNVP(respText);
	} catch (Exception e) {
	    e.printStackTrace();
	    return null;
	}
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public HashMap<String, String> deformatNVP(String pPayload) {
	HashMap nvp = new HashMap();
	StringTokenizer stTok = new StringTokenizer(pPayload, "&");
	while (stTok.hasMoreTokens()) {
	    StringTokenizer stInternalTokenizer = new StringTokenizer(stTok.nextToken(), "=");
	    if (stInternalTokenizer.countTokens() == 2) {
		@SuppressWarnings("deprecation")
		String key = URLDecoder.decode(stInternalTokenizer.nextToken());
		@SuppressWarnings("deprecation")
		String value = URLDecoder.decode(stInternalTokenizer.nextToken());
		nvp.put(key.toUpperCase(), value);
	    }
	}
	return nvp;
    }

    public HashMap<String, String> confirmPayment(HttpServletRequest request) {

	String token = request.getParameter("token");
	String payerID = request.getParameter("PayerID");
	Long userId = (Long) request.getSession().getAttribute("userId");
	Double ammount = userCartService.getToatlFees(userId);
	String serverName = request.getLocalAddr();

	String currencyCodeType = "USD";
	String paymentType = "Sale";

	String nvpstr = "&TOKEN=" + token + "&PAYERID=" + payerID + "&PAYMENTREQUEST_0_PAYMENTACTION=" + paymentType + "&PAYMENTREQUEST_0_AMT=" + ammount;

	nvpstr = nvpstr + "&PAYMENTREQUEST_0_CURRENCYCODE=" + currencyCodeType + "&IPADDRESS=" + serverName;

	return httpcall("DoExpressCheckoutPayment", nvpstr, request);
    }

    @RequestMapping(value = "/cancel", method = RequestMethod.GET)
    public String aboutus(Model model, HttpServletRequest request) {
	model.addAttribute("ack", "Canceled");
	return ("payment-completed");
    }
}