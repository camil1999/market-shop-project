package az.marketshopjamil.MarketShopJamil.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import az.marketshopjamil.MarketShopJamil.config.MySession;
import az.marketshopjamil.MarketShopJamil.model.Product;
import az.marketshopjamil.MarketShopJamil.repository.ProductRepository;

@RequestMapping(path = "/products")
@Controller
public class ProductController {
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private MySession mySession;

	@GetMapping
	public String showProducts(Model model) {
		List<Product> products = productRepository.findAll();
		model.addAttribute("products", products);

		return "products";
	}

	@GetMapping(path = "/new-product")
	public String openNewProductPage(Model model) {
		Product product = new Product();
		model.addAttribute("product", product);
		model.addAttribute("header", "Yeni məhsul");

		return "new-product";
	}

	@PostMapping(path = "/save-product-process")
	public String saveProduct(@Valid @ModelAttribute(name = "book") Product product, Model model) {

		productRepository.save(product);
		List<Product> products = productRepository.findAll();
		model.addAttribute("products", products);

		return "redirect:/products";
	}

	@GetMapping(path = "/delete/{id}")
	public String deleteProduct(@PathVariable(name = "id") Integer id, Model model) {
		boolean productExists = productRepository.findById(id).isPresent();
		if (productExists) {
			productRepository.deleteById(id);
		} else {

		}
		List<Product> products = productRepository.findAll();
		model.addAttribute("products", products);

		return "redirect:/products";
	}

	@GetMapping(path = "/edit/{id}")
	public String editProduct(@PathVariable(name = "id") Integer id, Model model) {
		Optional<Product> productOptional = productRepository.findById(id);
		boolean productExists = productOptional.isPresent();
		Product product = new Product();
		if (productExists) {
			product = productOptional.get();
		} else {

		}

		model.addAttribute("product", product);
		model.addAttribute("header", "Məhsul redaktəsi");

		return "new-product";
	}

}
