package az.marketshopjamil.MarketShopJamil.service;

import java.util.List;

import az.marketshopjamil.MarketShopJamil.request.RequestUser;
import az.marketshopjamil.MarketShopJamil.response.ResponseUser;

public interface UserService {

	public List<ResponseUser> findAllUser();

	public void saveUser(RequestUser requestUser);
}
