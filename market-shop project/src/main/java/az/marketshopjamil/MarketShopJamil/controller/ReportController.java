package az.marketshopjamil.MarketShopJamil.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import az.marketshopjamil.MarketShopJamil.response.ResponseReport;
import az.marketshopjamil.MarketShopJamil.service.ReportService;

@RestController
@RequestMapping(path = "/report")
@CrossOrigin(origins = "*")
public class ReportController {

	@Autowired
	private ReportService reportService;

	@GetMapping(path = "/total-report")
	@PreAuthorize(value = "hasAuthority('Admin')")
	public MappingJacksonValue getReport() {
		ResponseReport report = reportService.getReport();

		return filter(report, "report", "totalTurnover", "consumption", "profit");
	}

	// bu servis ancaq mysql-de calisir!
	@GetMapping(path = "/by-date")
	@PreAuthorize(value = "hasAuthority('Admin')")
	public MappingJacksonValue getReportByDate(@RequestParam String from, @RequestParam String to) {
		ResponseReport report = reportService.getReportByDate(from, to);

		return filter(report, "report", "totalTurnover", "consumption", "profit");
	}

	public MappingJacksonValue filter(Object data, String dto, String... fields) {
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(fields);
		FilterProvider provider = new SimpleFilterProvider().addFilter(dto, filter);
		MappingJacksonValue value = new MappingJacksonValue(data);
		value.setFilters(provider);
		return value;
	}
}
