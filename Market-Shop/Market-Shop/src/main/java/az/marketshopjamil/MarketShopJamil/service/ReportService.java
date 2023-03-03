package az.marketshopjamil.MarketShopJamil.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import az.marketshopjamil.MarketShopJamil.exception.NotFoundException;
import az.marketshopjamil.MarketShopJamil.model.Report;
import az.marketshopjamil.MarketShopJamil.repository.ReportRepository;
import az.marketshopjamil.MarketShopJamil.response.ResponseReport;

@Service
public class ReportService {
	@Autowired
	private ReportRepository reportRepository;
	@Autowired
	private ModelMapper mapper;

	public ResponseReport getReport() {
		Report report = reportRepository.findById(1).get();
		ResponseReport responsetReport = convertRespReport(report);
		return responsetReport;
	}

	public ResponseReport getReportByDate(String from, String to) {
		Report report = reportRepository.getReportByDate(from, to);
		if (report == null) {
			throw new NotFoundException(" Seçdiyiniz tarixlərə uyğun hesabat tapılmadı");
		}
		ResponseReport responseReport = convertRespReport(report);
		return responseReport;

	}

	private ResponseReport convertRespReport(Report report) {
		ResponseReport responseReport = new ResponseReport();
		mapper.map(report, responseReport);
		return responseReport;
	}

}
