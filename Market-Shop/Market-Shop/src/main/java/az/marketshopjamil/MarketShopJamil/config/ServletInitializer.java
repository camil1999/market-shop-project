package az.marketshopjamil.MarketShopJamil.config;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import az.marketshopjamil.MarketShopJamil.MarketShopJamilApplication;

public class ServletInitializer extends SpringBootServletInitializer {
	@Override
	 protected SpringApplicationBuilder configure (SpringApplicationBuilder application) {
	 return application.sources(MarketShopJamilApplication.class);
	 }
}
