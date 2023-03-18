package az.marketshopjamil.MarketShopJamil.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import az.marketshopjamil.MarketShopJamil.exception.NotFoundException;
import az.marketshopjamil.MarketShopJamil.model.Product;
import az.marketshopjamil.MarketShopJamil.model.Sale;
import az.marketshopjamil.MarketShopJamil.model.User;
import az.marketshopjamil.MarketShopJamil.repository.ProductRepository;
import az.marketshopjamil.MarketShopJamil.repository.ReportRepository;
import az.marketshopjamil.MarketShopJamil.repository.SaleRepository;
import az.marketshopjamil.MarketShopJamil.repository.UserRepository;
import az.marketshopjamil.MarketShopJamil.request.RequestSale;
import az.marketshopjamil.MarketShopJamil.response.ResponseSale;
import az.marketshopjamil.MarketShopJamil.service.SaleService;

@Service
public class SaleServiceImpl implements SaleService {
	@Autowired
	private SaleRepository saleRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ReportRepository reportRepository;

	@Autowired
	private ModelMapper mapper;

	private String getUsername() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}

	@Override
	public List<ResponseSale> getAllSale() {
		List<ResponseSale> sales = saleRepository.findAll().stream().map(sale -> convertRespSale(sale))
				.collect(Collectors.toList());
		if (sales.isEmpty()) {
			throw new NotFoundException("Satış tapılmadı!");
		}
		return sales;
	}

	@Override
	public List<ResponseSale> getAllSaleByDate(String from, String to) {
		List<ResponseSale> findAllByDate = saleRepository.findAllByDate(from, to).stream()
				.map(sale -> convertRespSale(sale)).collect(Collectors.toList());
		if (findAllByDate.isEmpty()) {
			throw new NotFoundException("Seçdiyiniz tarixlərə uyğun satış tapılmadı! " + from + " - " + to);
		}
		return findAllByDate;
	}

	@Override
	public void createSale(List<RequestSale> requestSales) {
		User user = userRepository.findByUsername(getUsername());
		 List<String> barcodes = new ArrayList<>();

		    for (RequestSale requestSale : requestSales) {
		        barcodes.add(requestSale.getBarcode());
		    }

		    List<Product> products = productRepository.findByBarcodeIn(barcodes);
		    if (products.size() != barcodes.size()) {
		        throw new NotFoundException("Bir neçə məhsul tapılmadı!");
		    }
		for (RequestSale requestSale : requestSales) {
			Sale sale = new Sale();
			Product product = productRepository.findByBarcode(requestSale.getBarcode());
			if (product == null) {
				throw new NotFoundException("Məhsul tapılmadı! " + requestSale.getBarcode());
			}
			Integer remnant = product.getQuantity().intValue() - requestSale.getQuantity().intValue();
			product.setQuantity(remnant);
			productRepository.save(product);
			sale.setPrice(product.getPrice());
			sale.setCost(product.getCost());
			sale.setProductName(product.getName());
			sale.setQuantity(requestSale.getQuantity());
			sale.setSoldDate(LocalDate.now().toString());
			Double totalPrice = product.getPrice() * sale.getQuantity();
			sale.setTotalPrice(totalPrice);
			Double profit = sale.getTotalPrice().doubleValue()
					- (sale.getQuantity().byteValue() * sale.getCost().byteValue());
			sale.setProfit(profit);
			sale.setCashierName(user.getUsername());
			saleRepository.save(sale);
			reportRepository.updateProfit(profit);
			reportRepository.updateTotalTurnover(totalPrice);
		}
		
		

	}

	private ResponseSale convertRespSale(Sale sale) {
		ResponseSale responseSale = new ResponseSale();
		mapper.map(sale, responseSale);
		return responseSale;
	}
}
