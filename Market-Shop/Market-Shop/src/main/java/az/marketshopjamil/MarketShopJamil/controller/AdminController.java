package az.marketshopjamil.MarketShopJamil.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import az.marketshopjamil.MarketShopJamil.config.MySession;

@Controller
public class AdminController {

	@Autowired
	private MySession mySession;

	@GetMapping(path = "/admin")
	public String showAdminPage(Model model) {

		return "admin";
	}
}
