package az.marketshopjamil.MarketShopJamil.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import az.marketshopjamil.MarketShopJamil.model.Product;

@Transactional
public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Query(value = "select * from products where name like %?1% or barcode like %?1% or price like %?1% or cost like %?1% or description like %?1% or quantity like %?1%", nativeQuery = true)
	@Modifying
	List<Product> searchProducts(String search);

	public Product findByBarcode(String barcode);

	public Product findByName(String name);
	 List<Product> findByBarcodeIn(List<String> barcodes);
}
