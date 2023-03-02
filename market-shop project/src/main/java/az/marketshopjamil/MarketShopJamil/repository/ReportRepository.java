package az.marketshopjamil.MarketShopJamil.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import az.marketshopjamil.MarketShopJamil.model.Report;

@Transactional
public interface ReportRepository extends JpaRepository<Report, Integer> {
	@Query(value = "UPDATE reports SET consumption = consumption + :consumption", nativeQuery = true)
	@Modifying
	public void uptadeConsumption(Double consumption);

	@Query(value = "UPDATE reports SET profit = profit + :profit", nativeQuery = true)
	@Modifying
	public void updateProfit(Double profit);

	@Query(value = "UPDATE reports SET total_turnover = total_turnover + :total_turnover", nativeQuery = true)
	@Modifying
	public void updateTotalTurnover(Double total_turnover);
	
	@Query(value = "select id as id, sum(total_price) as total_turnover ,sum(profit) as profit,sum((cost*quantity)) as consumption from sales where sold_date between ?1 and ?2", nativeQuery = true)
	//@Modifying
	public Report getReportByDate(String from, String to);
	

}