package az.marketshopjamil.MarketShopJamil.response;

import com.fasterxml.jackson.annotation.JsonFilter;

import lombok.Data;

@Data
@JsonFilter(value = "cashier")
public class ResponseCashier{
	private String name;
	private String surname;
	private String email;
	private String phone;
}
