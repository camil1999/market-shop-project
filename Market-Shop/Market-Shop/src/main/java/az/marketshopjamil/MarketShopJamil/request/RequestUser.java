package az.marketshopjamil.MarketShopJamil.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class RequestUser {
	private String type;
	@NotEmpty(message = "İstifadəçi adını boş qoymaq olmaz")
	@Size(max = 12, message = "İstifadəçi adı maksimum 10 simvoldan ibarət olmalıdır!")
	@Size(min = 4, message = "İstifadəçi adı minimum 4 simvoldan ibarət olmalıdır!")
	private String username;
	@NotEmpty(message = "Şifrəni boş qoymaq olmaz")
	private String password;
	@NotEmpty(message = "Adı boş qoymaq olmaz")
	@Size(max = 12, message = "Ad maksimum 12 simvoldan ibarət olmalıdır!")
	@Size(min = 4, message = "Ad minimum 4 simvoldan ibarət olmalıdır!")
	private String name;
	@Size(max = 12, message = "Soyad maksimum 12 simvoldan ibarət olmalıdır!")
	@Size(min = 4, message = "Soyad minimum 4 simvoldan ibarət olmalıdır!")
	@NotEmpty(message = "Soyad hissəsini boş qoymaq olaz!")
	private String surname;
	@Pattern(regexp = "[a-z0-9]+@[a-z]+\\.[a-z]{2,4}", message = "E-mail düzgün yazılmayıb!")
	private String email;
	@NotEmpty(message = "Telefon hissəsini boş qoymaq olmaz!")
	@Pattern(regexp = "[0-9]{3}-[0-9]{3}-[0-9]{2}-[0-9]{2}", message = "Telefon nömrəsini düzgün daxil edin!")
	private String phone;
}
