package az.marketshopjamil.MarketShopJamil.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import az.marketshopjamil.MarketShopJamil.model.Report;
import az.marketshopjamil.MarketShopJamil.model.Sale;
@Transactional
public interface SaleRepository extends JpaRepository<Sale, Integer> {
	
	
	@Query(value = "select * from sales where sold_date >=?1 and sold_date<=?2", nativeQuery = true)
	List<Sale> findAllByDate(String from, String to);
	
	
	
//	 @Query(value = "SELECT SUM(total_price) AS totalSales, SUM(profit) AS totalProfit FROM sales WHERE sold_date BETWEEN ?1 AND ?2", nativeQuery = true)
//	    ReportDTO getSalesReport(LocalDate startDate, LocalDate endDate);
}