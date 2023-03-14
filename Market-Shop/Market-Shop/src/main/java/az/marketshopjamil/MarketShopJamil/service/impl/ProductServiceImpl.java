package az.marketshopjamil.MarketShopJamil.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import az.marketshopjamil.MarketShopJamil.exception.AlreadyDefinedException;
import az.marketshopjamil.MarketShopJamil.exception.NotFoundException;
import az.marketshopjamil.MarketShopJamil.model.IncomingProduct;
import az.marketshopjamil.MarketShopJamil.model.Product;
import az.marketshopjamil.MarketShopJamil.model.SearchModel;
import az.marketshopjamil.MarketShopJamil.repository.IncomingProductRepository;
import az.marketshopjamil.MarketShopJamil.repository.ProductRepository;
import az.marketshopjamil.MarketShopJamil.repository.ReportRepository;
import az.marketshopjamil.MarketShopJamil.request.RequestProduct;
import az.marketshopjamil.MarketShopJamil.response.ResponseProduct;
import az.marketshopjamil.MarketShopJamil.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private IncomingProductRepository incomingProductRepository;

	@Autowired
	private ReportRepository reportRepository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public List<ResponseProduct> findAllProduct() {
		List<ResponseProduct> products = productRepository.findAll().stream()
				.map(product -> convertRespProduct(product)).collect(Collectors.toList());
		if (products.isEmpty()) {
			throw new NotFoundException("Məhsul tapılmadı!");
		}
		return products;
	}

	@Override
	public List<ResponseProduct> findAllIncomingProduct() {
		List<ResponseProduct> products = incomingProductRepository.findAll().stream()
				.map(product -> convertRespIncProduct(product)).collect(Collectors.toList());
		if (products.isEmpty()) {
			throw new NotFoundException("Məhsul tapılmadı!");
		}
		return products;
	}

	@Override
	public ResponseProduct getProductByBarcode(String barcode) {
		Product product = productRepository.findByBarcode(barcode);
		if (product == null) {
			throw new NotFoundException("Məhsul tapılmadı! " + barcode);
		}
		ResponseProduct responseProduct = convertRespProduct(product);

		return responseProduct;
	}

	@Override
	public void saveProduct(RequestProduct requestProduct) {
		Product findedProduct = productRepository.findByBarcode(requestProduct.getBarcode());
		if (findedProduct != null) {
			throw new AlreadyDefinedException("Bu barkodda məhsul artıq mövcuddur!-" + requestProduct.getBarcode());
		}
		Product product = new Product();
		mapper.map(requestProduct, product);
		product.setRegisterDate(LocalDate.now().toString());
		product.setUpdateDate("-");
		double percent = ((requestProduct.getPrice().doubleValue() - requestProduct.getCost().doubleValue())
				/ requestProduct.getCost().doubleValue()) * 100;
		product.setPercent(Math.round(percent * 10.0) / 10.0);
		productRepository.save(product);

		Double consumption = requestProduct.getCost().doubleValue() * requestProduct.getQuantity();
		reportRepository.uptadeConsumption(consumption);

	}

	@Override
	public void saveIncomingProduct(RequestProduct requestProduct) {
		Product existingProduct = productRepository.findByName(requestProduct.getName());
		if (existingProduct == null) {
			throw new NotFoundException("Məhsul tapılmadı! " + requestProduct.getName());
		}
		existingProduct.setQuantity(existingProduct.getQuantity() + requestProduct.getQuantity());
		existingProduct.setUpdateDate(LocalDate.now().toString());
		productRepository.save(existingProduct);

		Double consumption = existingProduct.getCost().doubleValue() * requestProduct.getQuantity();
		reportRepository.uptadeConsumption(consumption);

		IncomingProduct incomingProduct = new IncomingProduct();
		mapper.map(existingProduct, incomingProduct);
		incomingProduct.setUpdateDate(LocalDate.now().toString());
		incomingProduct.setQuantity(requestProduct.getQuantity());
		incomingProductRepository.save(incomingProduct);
	}

	@Override
	public void deleteById(Integer id) {
		Optional<Product> product = productRepository.findById(id);
		if (product.isEmpty()) {
			throw new NotFoundException("Məhsul tapılmadı! " + id);
		}
		productRepository.deleteById(id);
	}

	@Override
	public void editProduct(Integer id, RequestProduct requestProduct) {
		Optional<Product> product = productRepository.findById(id);
		if (product.isEmpty()) {
			throw new NotFoundException("Məhsul tapılmadı! " + id);
		}
		Product editProduct = product.get();
		editProduct.setUpdateDate(LocalDate.now().toString());
		double percent = ((requestProduct.getPrice().doubleValue() - requestProduct.getCost().doubleValue())
				/ requestProduct.getCost().doubleValue()) * 100;
		editProduct.setPercent(percent);
		mapper.map(requestProduct, editProduct);
		productRepository.save(editProduct);
	}

	@Override
	public List<ResponseProduct> findSearch(SearchModel search) {
		List<ResponseProduct> searchProducts = productRepository
				.searchProducts(search.getSearch()).stream()
				.map(product -> convertRespProduct(product)).collect(Collectors.toList());
		if (searchProducts.isEmpty()) {
			throw new NotFoundException("Axtardığınız parametrlərə uyğun heç bir nəticə tapılmadı!");
		}
		return searchProducts;
	}

	private ResponseProduct convertRespProduct(Product product) {
		ResponseProduct responseProduct = new ResponseProduct();
		mapper.map(product, responseProduct);
		return responseProduct;
	}

	private ResponseProduct convertRespIncProduct(IncomingProduct incomingProduct) {
		ResponseProduct responseProduct = new ResponseProduct();
		mapper.map(incomingProduct, responseProduct);
		return responseProduct;
	}

}
