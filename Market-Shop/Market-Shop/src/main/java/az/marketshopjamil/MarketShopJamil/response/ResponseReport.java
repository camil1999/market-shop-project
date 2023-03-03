package az.marketshopjamil.MarketShopJamil.response;

import com.fasterxml.jackson.annotation.JsonFilter;

import lombok.Data;

@Data
@JsonFilter(value = "report")
public class ResponseReport {

	private Double totalTurnover;
	private Double consumption;
	private Double profit;

}
