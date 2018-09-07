package vn.com.vhc.consolidate.controllers;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.com.vhc.consolidate.models.Report;
import vn.com.vhc.consolidate.services.ReportService;

@RestController
public class ReportController {

	ReportService reportService = new ReportService();

	@RequestMapping(value = "/report", method = RequestMethod.GET)
	public List<Report> getReportInfo(@RequestParam(value = "month", required = true, defaultValue = "") String month,
			@RequestParam(value = "year", required = true, defaultValue = "") String year)
			throws NoSuchAlgorithmException, SQLException {
		return reportService.getReportInfo(month, year);
	}
}
