package com.stock.web;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.stock.service.CompanyParser;
import com.stock.service.StockManager;

@Controller
@RequestMapping("/welcome")
public class WelcomeController {

	@Autowired
	private StockManager manager;

	@Autowired
	private CompanyParser comParser;

	@RequestMapping(method = RequestMethod.GET)
	public String welcome(Model model) {
		Date today = new Date();
		model.addAttribute("today", today);

		return "welcome";
	}
}