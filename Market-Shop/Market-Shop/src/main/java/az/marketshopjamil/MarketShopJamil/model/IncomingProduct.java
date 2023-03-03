package az.marketshopjamil.MarketShopJamil.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFilter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "incoming_products")
@JsonFilter(value = "incoming")
public class IncomingProduct {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String barcode;
	private Double price;
	private Double cost;
	private String description;
	private String registerDate;
	private String updateDate;
	private Integer quantity;
	private Double percent;
}
