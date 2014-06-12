package com.stock.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.stock.model.CompanyModel;

@Controller
public class CompanyController {
	@RequestMapping(value = "/getCompany", method = RequestMethod.POST)
	public ModelAndView addContact(@ModelAttribute("company") CompanyModel cm,
			BindingResult result, ModelMap model) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("company-success");
		mav.addObject("selectedCompany", cm);
		return mav;
	}

	@RequestMapping("/company")
	public ModelAndView showContacts() {

		return new ModelAndView("company", "command", new CompanyModel());
	}

	@ModelAttribute("symbolsMap")
	public List<String> populateSymbol() {
		List<String> symbolList = new ArrayList<String>();
		symbolList.add("MSFT");
		symbolList.add("GOOG");
		return symbolList;
	}

}
