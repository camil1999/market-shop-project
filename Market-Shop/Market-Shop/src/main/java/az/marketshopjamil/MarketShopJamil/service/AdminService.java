package az.marketshopjamil.MarketShopJamil.service;

import java.util.List;

import az.marketshopjamil.MarketShopJamil.request.RequestAdmin;
import az.marketshopjamil.MarketShopJamil.response.ResponseAdmin;

public interface AdminService {

	public List<ResponseAdmin> findAllAdmin();

	public void editAdmin(Integer id, RequestAdmin requestAdmin);

	public void deleteById(Integer id);

}
