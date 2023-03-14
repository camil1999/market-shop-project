package az.marketshopjamil.MarketShopJamil.service;

import java.util.List;

import az.marketshopjamil.MarketShopJamil.model.SearchModel;
import az.marketshopjamil.MarketShopJamil.request.RequestProduct;
import az.marketshopjamil.MarketShopJamil.response.ResponseProduct;

public interface ProductService {

	public List<ResponseProduct> findAllProduct();

	public List<ResponseProduct> findAllIncomingProduct();

	public ResponseProduct getProductByBarcode(String barcode);

	public void saveProduct(RequestProduct requestProduct);

	public void saveIncomingProduct(RequestProduct requestProduct);

	public void deleteById(Integer id);

	public void editProduct(Integer id, RequestProduct requestProduct);

	public List<ResponseProduct> findSearch(SearchModel search);

}
