package az.marketshopjamil.MarketShopJamil.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import az.marketshopjamil.MarketShopJamil.model.User;
import az.marketshopjamil.MarketShopJamil.repository.UserDAO;



@Controller
public class UserController {
	private boolean createUser = false;
	
	@Autowired
	private UserDAO userDAO;

	@GetMapping(path = "/show-login")
	public String showLoginPage(Model model,HttpServletRequest request) {
		if (createUser) {
			model.addAttribute("userCreated", "");
			createUser=false;
		}
		request.getSession().invalidate();
		return "my-custom-login";
	}

	@GetMapping(path = "/create-account")
	public String showSignUpPage(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "create-account";
	}

	@PostMapping(path = "/create-account-process")
	public String saveBook(@Valid @ModelAttribute(name = "user") User user, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "create-account";
		}
		boolean userExists=userDAO.createUser(user);
		if(userExists) {
			model.addAttribute("userExists","");
			return "create-account";
		}
		createUser = true;
		return "redirect:/show-login";
	}
}
