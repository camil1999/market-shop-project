package az.marketshopjamil.MarketShopJamil.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import az.marketshopjamil.MarketShopJamil.exception.MyValidationException;
import az.marketshopjamil.MarketShopJamil.request.RequestUser;
import az.marketshopjamil.MarketShopJamil.response.ResponseUser;
import az.marketshopjamil.MarketShopJamil.service.UserService;

@RestController
@RequestMapping(path = "/users")
@CrossOrigin(origins = "*")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping
	@PreAuthorize(value = "hasAuthority('Admin')")
	public void saveUser(@Valid @RequestBody RequestUser requestUser,BindingResult result) {
		if (result.hasErrors()) {
			throw new MyValidationException(result);
		}
		userService.saveUser(requestUser);
	}

	@GetMapping
	@PreAuthorize(value = "hasAuthority('Admin')")
	public MappingJacksonValue findAllUser() {
		List<ResponseUser> users = userService.findAllUser();
		return filter(users, "user", "username", "password", "enabled", "type");
	}

	public MappingJacksonValue filter(Object data, String dto, String... fields) {
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(fields);
		FilterProvider provider = new SimpleFilterProvider().addFilter(dto, filter);
		MappingJacksonValue value = new MappingJacksonValue(data);
		value.setFilters(provider);
		return value;
	}

	@GetMapping(path = "/login")
	public void login() {

	}
}
