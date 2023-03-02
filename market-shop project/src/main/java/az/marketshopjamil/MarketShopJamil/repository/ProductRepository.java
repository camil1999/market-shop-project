package az.marketshopjamil.MarketShopJamil.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import az.marketshopjamil.MarketShopJamil.model.Product;

@Transactional
public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Query(value = "select * from products where name like %?1% or barcode like %?2 or price like %?3 or cost like %?4 or description like %?5 or quantity like %?6", nativeQuery = true)
	@Modifying
	List<Product> searchProducts(String name, String barcode, Double price, Double cost, String description,
			Double quantity);

	public Product findByBarcode(String barcode);
	public Product findByName(String name);
	
	
}
