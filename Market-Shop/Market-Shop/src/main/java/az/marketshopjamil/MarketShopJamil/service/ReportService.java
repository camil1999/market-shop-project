package az.marketshopjamil.MarketShopJamil.service;

import az.marketshopjamil.MarketShopJamil.response.ResponseReport;

public interface ReportService {

	public ResponseReport getReport();

	public ResponseReport getReportByDate(String from, String to);

}
