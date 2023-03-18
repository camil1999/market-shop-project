package az.marketshopjamil.MarketShopJamil.service;

import java.util.List;

import az.marketshopjamil.MarketShopJamil.request.RequestSale;
import az.marketshopjamil.MarketShopJamil.response.ResponseSale;

public interface SaleService {

	public List<ResponseSale> getAllSale();

	public List<ResponseSale> getAllSaleByDate(String from, String to);

	public void createSale(List<RequestSale> requestSale);

}
