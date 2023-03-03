package az.marketshopjamil.MarketShopJamil.response;

import com.fasterxml.jackson.annotation.JsonFilter;

import lombok.Data;

@Data
@JsonFilter(value = "admin")
public class ResponseAdmin {
	private String name;
	private String surname;
	private String email;
	private String phone;
}
