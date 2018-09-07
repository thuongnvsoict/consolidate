package vn.com.vhc.consolidate.services;

import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import vn.com.vhc.consolidate.models.Subscriber;


@Service
public class SubscriberService extends MasterService {

	@SuppressWarnings("deprecation")
	public List<Subscriber> getSubscriber(String phoneNumber, String GPKD, String CMT_HC, String type)
			throws SQLException, NoSuchAlgorithmException {
		ResultSet data = null;
		String sql = null;
		Statement ps = null;

		Date date = java.util.Calendar.getInstance().getTime();
		int month = date.getMonth() + 1;
		int year = date.getYear() + 1900;

		String tableName = "";
		if (month < 10) {
			if (type.equals("prepaid"))
				tableName = "MC_SUBSCRIBER_0" + month + year;
			else if(type.equals("postpaid"))
				tableName = "SUBSCRIBER_0" + month + year;
		} else {
			if (type.equals("prepaid"))
				tableName = "MC_SUBSCRIBER_" + month + year;
			else if(type.equals("postpaid"))
				tableName = "SUBSCRIBER_" + month + year;
		}
//		
//		sql = "select * from " + tableName + " where MSISDN like ? and " + " SO_GIAYTO_TOCHUC like ? and "
//				+ " SOGIAYTO_TB like ?";
//
//		ps = connection.prepareStatement(sql);
//
//		ps.setString(1, "%" + phoneNumber + "%");
//		ps.setString(2, "%" + GPKD + "%");
//		ps.setString(3, "%" + CMT_HC + "%");
			
		sql = "select * from " + tableName + " where 1=1";
		if (!phoneNumber.equals("")) {
			sql += " and MSISDN = "+ phoneNumber;
		}
		if (!GPKD.equals("")) {
			sql += " and SO_GIAYTO_TOCHUC = "+ GPKD;
		}
		if (!CMT_HC.equals("")) {
			sql += " and SOGIAYTO_TB = "+ CMT_HC;
		}
		
		ps = connection.createStatement();
		
		data = ps.executeQuery(sql);

		List<Subscriber> subscribers = new LinkedList<Subscriber>();
		while (data.next()) {
			System.out.println("yyyyyyyy");
			Subscriber subscriber = new Subscriber();
			
			subscriber.setID(data.getString("iD"));
			subscriber.setMSISDN(data.getString("mSISDN"));
			subscriber.setLOAI_KHACHHANG(data.getString("lOAI_KHACHHANG"));
			subscriber.setDOITUONG_SD(data.getString("dOITUONG_SD"));
			subscriber.setHOVATEN_TB(data.getString("hOVATEN_TB"));
			subscriber.setNGAYSINH_TB(data.getString("nGAYSINH_TB"));
			subscriber.setQUOCTICH_TB(data.getString("qUOCTICH_TB"));
			subscriber.setLOAI_GIAYTO_TB(data.getString("lOAI_GIAYTO_TB"));
			subscriber.setSOGIAYTO_TB(data.getString("sOGIAYTO_TB"));
			subscriber.setNGAYCAP_TB(data.getString("nGAYCAP_TB"));
			subscriber.setNOICAP_TB(data.getString("nOICAP_TB"));
			subscriber.setHOKHAUTT_TB(data.getString("hOKHAUTT_TB"));
			subscriber.setTEN_TOCHUC(data.getString("tEN_TOCHUC"));
			subscriber.setDIACHI_TOCHUC(data.getString("dIACHI_TOCHUC"));
			subscriber.setSO_GIAYTO_TOCHUC(data.getString("sO_GIAYTO_TOCHUC"));
			subscriber.setHOVATEN_DK(data.getString("hOVATEN_DK"));
			subscriber.setNGAYSINH_DK(data.getString("nGAYSINH_DK"));
			subscriber.setQUOCTICH_DK(data.getString("qUOCTICH_DK"));
			subscriber.setLOAI_GIAYTO_DK(data.getString("lOAI_GIAYTO_DK"));
			subscriber.setSO_GIAYTO_DK(data.getString("sO_GIAYTO_DK"));
			subscriber.setNGAYCAP_DK(data.getString("nGAYCAP_DK"));
			subscriber.setNOICAP_DK(data.getString("nOICAP_DK"));
			subscriber.setHOKHAUTT_DK(data.getString("hOKHAUTT_DK"));
			subscriber.setTEN_NVGD(data.getString("tEN_NVGD"));
			subscriber.setTHOIGIAN_HD(data.getString("tHOIGIAN_HD"));
			subscriber.setTHOIGIAN_CAPNHAT(data.getString("tHOIGIAN_CAPNHAT"));
			subscriber.setDIACHI_DIEMGD(data.getString("dIACHI_DIEMGD"));
			subscriber.setNOIDK_TINH(data.getString("nOIDK_TINH"));
			subscriber.setDIENTHOAI_DIEMGD(data.getString("dIENTHOAI_DIEMGD"));
			subscriber.setNGAY_SUDUNG(data.getString("nGAY_SUDUNG"));
			subscriber.setTRANGTHAI_TB(data.getString("tRANGTHAI_TB"));

			subscribers.add(subscriber);
		}

		data.close();
		ps.close();

		return subscribers;
	}
}
