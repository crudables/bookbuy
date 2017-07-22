package com.ables.bookbuy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {

//	@RequestMapping("/error")
	public String error(Model model){
		String errorMessage = "There was an error executing your demand, Pls try again later or contact the administrator";
		model.addAttribute("error", errorMessage);
		return "error";
	}
}
