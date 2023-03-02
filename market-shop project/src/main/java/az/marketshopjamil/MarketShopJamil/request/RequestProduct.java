package az.marketshopjamil.MarketShopJamil.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class RequestProduct {
	@NotEmpty(message = "Məhsul adını boş qoymaq olmaz")
	private String name;
	@NotEmpty(message = "Barkodu boş qoymaq olmaz")
	private String barcode;
	@NotNull(message = "Qiymət daxil edilməyib!")
	private Double price;
	@NotNull(message = "Məhsulun maya dəyəri daxil edilməyib!")
	private Double cost;
	private String description;
	@NotNull(message = "Məhsulun miqdarı daxil edilməyib!")
	private Integer quantity;

}
