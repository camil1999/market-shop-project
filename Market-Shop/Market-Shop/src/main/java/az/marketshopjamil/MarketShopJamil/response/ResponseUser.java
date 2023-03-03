package az.marketshopjamil.MarketShopJamil.response;

import com.fasterxml.jackson.annotation.JsonFilter;

import lombok.Data;

@Data
@JsonFilter(value = "user")
public class ResponseUser {
	private String username;

	private String password;

	private Boolean enabled;

	private String type;
}
