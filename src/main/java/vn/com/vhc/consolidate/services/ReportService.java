package vn.com.vhc.consolidate.services;

import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import vn.com.vhc.consolidate.ConsolidateApplication;
import vn.com.vhc.consolidate.models.Report;


@Service
public class ReportService extends MasterService {

	@SuppressWarnings("deprecation")
	public List<Report> getReportInfo(String month, String year) throws SQLException, NoSuchAlgorithmException {

		// Lay ngay thang hien tai
		Date date = java.util.Calendar.getInstance().getTime();
		int currentMonth = date.getMonth() + 1;
		int currentYear = date.getYear() + 1900;
		int maxMonth = ConsolidateApplication.maxMonth;
		System.out.println("xxxxx: " + maxMonth);
		int searchMonth = Integer.parseInt(month);
		int searchYear = Integer.parseInt(year);

		// Lay ten bang truy van
		String table_name = "";
		if (searchMonth < 10)
			table_name = "REPORT_0" + searchMonth + searchYear;
		else
			table_name = "REPORT_" + searchMonth + searchYear;

		// Check dieu kien duoc lay du lieu
		if (ckeckMonth(maxMonth, currentMonth, currentYear, searchMonth, searchYear)) {
			System.out.println("Correct");
			// Get Data
			ResultSet data = null;
			String sql = null;
			sql = "select * from " + table_name;

			PreparedStatement ps = connection.prepareStatement(sql);

			data = ps.executeQuery();
			if (data == null)
				System.out.println("Xau");

			List<Report> reports = new LinkedList<Report>();

			while (data.next()) {
				Report report = new Report();

				report.setPsll3gTtDatacard(data.getInt("PSLL_3G_TT_DATACARD"));
				report.setPsll3gTtMaydt(data.getInt("PSLL_3G_TT_MAYDT"));
				report.setTtTong3G(data.getInt("PSLL_3G_TT_DATACARD") + data.getInt("PSLL_3G_TT_MAYDT"));
				report.setPsll3gTsDatacard(data.getInt("PSLL_3G_TS_DATACARD"));
				report.setPsll3gTsMaydt(data.getInt("PSLL_3G_TS_MAYDT"));
				report.setTsTong3G(report.getPsll3gTsDatacard() + report.getPsll3gTsMaydt());
				report.setTong3G(report.getTtTong3G() + report.getTsTong3G());
				report.setPsll2gTt(data.getInt("PSLL_2G_TT"));
				report.setPsll2gTs(data.getInt("PSLL_2G_TS"));
				report.setTong2G(report.getPsll2gTt() + report.getPsll2gTs());

				
				report.setPsllTong2G3G(report.getTong2G() + report.getTong3G());;

				report.setTbkh2g3gTt(data.getInt("TBKH_2G_3G_TT"));
				report.setTbkh2g3gTs(data.getInt("TBKH_2G_3G_TS"));
				report.setTong2G3G(report.getTbkh2g3gTs() + report.getTbkh2g3gTt());

				report.setTbhd2gT1(data.getInt("TBHD_2G_T1"));
				report.setTbhd3gT1(data.getInt("TBHD_3G_T1"));
				report.setTong2G3Gtuan1(report.getTbhd2gT1() + report.getTbhd3gT1());
				report.setTbhd2gT2(data.getInt("TBHD_2G_T2"));
				report.setTbhd3gT2(data.getInt("TBHD_2G_T2"));
				report.setTong2G3Gtuan2(report.getTbhd2gT2() + report.getTbhd3gT2());
				report.setTbhd2gT3(data.getInt("TBHD_2G_T3"));
				report.setTbhd3gT3(data.getInt("TBHD_3G_T3"));
				report.setTong2G3Gtuan3(report.getTbhd2gT3() + report.getTbhd3gT3());
				report.setTbhd2gT4(data.getInt("TBHD_2G_T4"));
				report.setTbhd3gT4(data.getInt("TBHD_3G_T4"));
				report.setTong2G3Gtuan4(report.getTbhd2gT4() + report.getTbhd3gT4());

				reports.add(report);
			}

			data.close();
			ps.close();

			return reports;
		}
		return null;
	}

	// check ngay thang voi max < 12
	boolean ckeckMonth(int max, int currentMonth, int currentYear, int searchMonth, int searchYear) {
		int minMonth, minYear;

		// Lay ngay thang min de check tu min->current
		if (currentMonth - max <= 0) {
			minMonth = 12 - max - currentMonth;
			minYear = currentYear - 1;
		} else {
			minMonth = currentMonth - max;
			minYear = currentYear;
		}

		// Truong hop SearchYear = MinYear
		if (minYear == searchYear) {
			if (searchMonth >= minMonth)
				return true;
			return false;
		}
		// Truong hop searchYear trung voi nam hien tai
		if (searchYear == currentYear) {
			if (searchMonth <= currentMonth && searchMonth >= minMonth)
				return true;
			return false;
		}
		return false;
	}
}
