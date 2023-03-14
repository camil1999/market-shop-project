package az.marketshopjamil.MarketShopJamil.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import az.marketshopjamil.MarketShopJamil.request.RequestSale;
import az.marketshopjamil.MarketShopJamil.response.ResponseSale;
import az.marketshopjamil.MarketShopJamil.service.SaleService;

@RestController
@RequestMapping(path = "/sale")
@CrossOrigin(origins = "*")
public class SaleRestController {

	@Autowired
	private SaleService saleService;

	@GetMapping
	@PreAuthorize(value = "hasAuthority('Admin')")
	public MappingJacksonValue getAllSale() {
		List<ResponseSale> findAll = saleService.getAllSale();
		return filter(findAll, "sale", "id", "productName", "price", "cost", "quantity", "soldDate", "cashierName",
				"totalPrice", "profit");
	}

	@GetMapping(path = "/date")
	@PreAuthorize(value = "hasAuthority('Admin')")
	public MappingJacksonValue getAllSaleByDate(@RequestParam String from, @RequestParam String to) {
		List<ResponseSale> findAllByDate = saleService.getAllSaleByDate(from, to);
		return filter(findAllByDate, "sale", "id", "productName", "price", "quantity", "soldDate", "cashierName",
				"totalPrice", "profit");
	}

	@PostMapping
	public void createSale(@RequestBody RequestSale requestSale) {
		saleService.createSale(requestSale);

	}

	public MappingJacksonValue filter(Object data, String dto, String... fields) {
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(fields);
		FilterProvider provider = new SimpleFilterProvider().addFilter(dto, filter);
		MappingJacksonValue value = new MappingJacksonValue(data);
		value.setFilters(provider);
		return value;
	}
}
