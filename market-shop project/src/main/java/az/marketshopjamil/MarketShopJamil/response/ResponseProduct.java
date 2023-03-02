package az.marketshopjamil.MarketShopJamil.response;

import com.fasterxml.jackson.annotation.JsonFilter;

import lombok.Data;

@Data
@JsonFilter(value = "product")
public class ResponseProduct {
	private String name;
	private String barcode;
	private Double price;
	private Double cost;
	private String description;
	private Integer quantity;
	
}
