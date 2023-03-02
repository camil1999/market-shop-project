package az.marketshopjamil.MarketShopJamil.response;

import com.fasterxml.jackson.annotation.JsonFilter;

import lombok.Data;

@Data
@JsonFilter(value = "sale")
public class ResponseSale {
	private String productName;
	private Double price;
	private Double cost;
	private Integer quantity;
	private String soldDate;
	private String cashierName;
	private Double totalPrice;
	private Double profit;
}
