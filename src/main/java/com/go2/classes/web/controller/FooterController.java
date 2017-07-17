package com.go2.classes.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.go2.classes.common.BaseController;

@Controller
@RequestMapping("/")
public class FooterController extends BaseController {

    @RequestMapping(value = "/about")
    public String openAbout(Model model, HttpSession session) {
	return "about";
    }

    @RequestMapping(value = "/partner")
    public String openPartner(Model model, HttpSession session) {
	return "about";
    }

    @RequestMapping(value = "/faq")
    public String openFaq(Model model, HttpSession session) {
	return "faq";
    }

    @RequestMapping(value = "/tnc")
    public String openTnc(Model model, HttpSession session) {
	return "tnc";
    }

    @RequestMapping(value = "/getstarted")
    public String openGetStarted(Model model, HttpSession session) {
	return "getstarted";
    }

    @RequestMapping(value = "/whatsnew")
    public String openWhatsNew(Model model, HttpSession session) {
	return "whats-new";
    }

}
