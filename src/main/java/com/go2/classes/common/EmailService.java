package com.go2.classes.common;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;

@Component
public class EmailService {

    @Value("${sendgridapikey}")
    private String sendgridApiKey = "";

    @Value("${sendgridsendermailid}")
    private String sendgridSenderMailId = "";

	public void sendEmail(String subject, String messageBody, String toStr) {
		Email from = new Email(sendgridSenderMailId);
		Email to = new Email(toStr);
		Content content = new Content("text/plain", messageBody);
		Mail mail = new Mail(from, subject, to, content);

		SendGrid sg = new SendGrid(sendgridApiKey);
		Request request = new Request();
		try {
			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(mail.build());
			sg.api(request);
		} catch (IOException ex) {
		}

	}

}
