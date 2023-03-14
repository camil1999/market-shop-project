package az.marketshopjamil.MarketShopJamil.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import az.marketshopjamil.MarketShopJamil.request.RequestCashier;
import az.marketshopjamil.MarketShopJamil.response.ResponseCashier;
import az.marketshopjamil.MarketShopJamil.service.CashierService;

@RestController
@RequestMapping(path = "/cashier")
@CrossOrigin(origins = "*")
public class CashierRestController {

	@Autowired
	private CashierService cashierService;

	@GetMapping
	@PreAuthorize(value = "hasAuthority('Admin')")
	public MappingJacksonValue findAllCashier() {
		List<ResponseCashier> cashiers = cashierService.findAllCashier();
		return filter(cashiers, "cashier", "id", "name", "surname", "email", "phone");

	}

	@PutMapping(path = "/{id}")
	public void editCashier(@PathVariable Integer id, @RequestBody RequestCashier requestCashier) {
		cashierService.editCashier(id, requestCashier);
	}

	@DeleteMapping(path = "/{id}")
	@PreAuthorize(value = "hasAuthority('Admin')")
	public void deleteById(@PathVariable Integer id) {
		cashierService.deleteById(id);
	}

	public MappingJacksonValue filter(Object data, String dto, String... fields) {
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(fields);
		FilterProvider provider = new SimpleFilterProvider().addFilter(dto, filter);
		MappingJacksonValue value = new MappingJacksonValue(data);
		value.setFilters(provider);
		return value;
	}

}
