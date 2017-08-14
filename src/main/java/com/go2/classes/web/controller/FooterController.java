package com.go2.classes.web.controller;

import java.text.ParseException;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.go2.classes.business.service.ArticleService;
import com.go2.classes.common.BaseController;

@Controller
@RequestMapping("/")
public class FooterController extends BaseController {

    @Resource
    ArticleService articleService;
    
    @RequestMapping(value = "/about")
    public String openAbout(Model model, HttpSession session) {
	return "about";
    }

    @RequestMapping(value = "/partner")
    public String openPartner(Model model, HttpSession session) {
	return "partner";
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

    @RequestMapping(value = "/articles")
    public String openWhatsNew(Model model, HttpSession session) {
	model.addAttribute("articles", articleService.getAllPublished());
	return "articles";
    }

    @RequestMapping(value = "/article")
    public String openClassesByCenter(Model model, @RequestParam(name = "articleId") Long articleId) throws ParseException {
	model.addAttribute("article", articleService.findById(articleId));
	return "article";
    }


}
