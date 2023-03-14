package az.marketshopjamil.MarketShopJamil.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import az.marketshopjamil.MarketShopJamil.exception.MyValidationException;
import az.marketshopjamil.MarketShopJamil.model.SearchModel;
import az.marketshopjamil.MarketShopJamil.request.RequestProduct;
import az.marketshopjamil.MarketShopJamil.response.ResponseProduct;
import az.marketshopjamil.MarketShopJamil.service.ProductService;

@RestController
@RequestMapping(path = "/product")
@CrossOrigin(origins = "*")
public class ProductRestController {

	@Autowired
	private ProductService productService;

	@GetMapping
	@PreAuthorize(value = "hasAuthority('Admin')")
	public MappingJacksonValue findAllProduct() {
		List<ResponseProduct> products = productService.findAllProduct();
		return filter(products, "product", "name", "barcode", "price", "cost", "description", "registerDate",
				"updateDate", "quantity", "percent");
	}

	@GetMapping(path = "/barcode")
	public MappingJacksonValue getProductByBarcode(@RequestParam String barcode) {
		ResponseProduct product = productService.getProductByBarcode(barcode);
		return filter(product, "product", "name", "barcode", "price", "cost", "description", "registerDate",
				"updateDate", "quantity", "percent");
	}

	@PostMapping
	@PreAuthorize(value = "hasAuthority('Admin')")
	public void saveProduct(@Valid @RequestBody RequestProduct requestProduct, BindingResult result) {
		if (result.hasErrors()) {
			throw new MyValidationException(result);
		}
		productService.saveProduct(requestProduct);

	}

	@GetMapping(path = "/incoming")
	@PreAuthorize(value = "hasAuthority('Admin')")
	public MappingJacksonValue findAllIncomingProduct(@RequestBody RequestProduct requestProduct) {
		List<ResponseProduct> products = productService.findAllIncomingProduct();
		return filter(products, "product", "name", "barcode", "price", "cost", "description", "registerDate",
				"updateDate", "quantity", "percent");

	}

	@PostMapping(path = "/incoming")
	@PreAuthorize(value = "hasAuthority('Admin')")
	public void saveIncomingProduct(@RequestBody RequestProduct requestProduct) {

		productService.saveIncomingProduct(requestProduct);

	}

	@DeleteMapping
	@PreAuthorize(value = "hasAuthority('Admin')")
	public void deleteById(@PathVariable Integer id) {
		productService.deleteById(id);
	}

	@PutMapping(path = "/{id}")
	@PreAuthorize(value = "hasAuthority('Admin')")
	public void editProduct(@PathVariable Integer id, @RequestBody RequestProduct requestProduct) {
		productService.editProduct(id, requestProduct);
	}

	@PostMapping(path = "/search")
	@PreAuthorize(value = "hasAuthority('Admin')")
	public MappingJacksonValue findSearch(@RequestBody SearchModel search) {			
		List<ResponseProduct> searchProducts = productService.findSearch(search);

		return filter(searchProducts, "product", "id", "name", "barcode", "price", "cost", "description",
				"registerDate", "updateDate", "quantity", "percent");
	}

	public MappingJacksonValue filter(Object data, String dto, String... fields) {
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(fields);
		FilterProvider provider = new SimpleFilterProvider().addFilter(dto, filter);
		MappingJacksonValue value = new MappingJacksonValue(data);
		value.setFilters(provider);
		return value;
	}
}
