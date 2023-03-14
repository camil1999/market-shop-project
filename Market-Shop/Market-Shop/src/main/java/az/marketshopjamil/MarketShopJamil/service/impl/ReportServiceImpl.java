package az.marketshopjamil.MarketShopJamil.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import az.marketshopjamil.MarketShopJamil.exception.NotFoundException;
import az.marketshopjamil.MarketShopJamil.model.Report;
import az.marketshopjamil.MarketShopJamil.repository.ReportRepository;
import az.marketshopjamil.MarketShopJamil.response.ResponseReport;
import az.marketshopjamil.MarketShopJamil.service.ReportService;

@Service
public class ReportServiceImpl implements ReportService {
	@Autowired
	private ReportRepository reportRepository;
	@Autowired
	private ModelMapper mapper;

	@Override
	public ResponseReport getReport() {
		Report report = reportRepository.findById(1).get();
		ResponseReport responsetReport = convertRespReport(report);
		return responsetReport;
	}

	@Override
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
