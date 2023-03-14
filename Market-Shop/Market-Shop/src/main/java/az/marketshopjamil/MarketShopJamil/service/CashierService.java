package az.marketshopjamil.MarketShopJamil.service;

import java.util.List;

import az.marketshopjamil.MarketShopJamil.request.RequestCashier;
import az.marketshopjamil.MarketShopJamil.response.ResponseCashier;

public interface CashierService {

	public List<ResponseCashier> findAllCashier();

	public void editCashier(Integer id, RequestCashier requestCashier);

	public void deleteById(Integer id);

}
