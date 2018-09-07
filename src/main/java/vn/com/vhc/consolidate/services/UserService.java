package vn.com.vhc.consolidate.services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Service;

import vn.com.vhc.consolidate.models.User;

@Service
public class UserService extends MasterService{
	
	public User getUserInfo(String username) throws SQLException, NoSuchAlgorithmException{
		ResultSet data = null;
		String sql = "select * from sys_users where upper(username) = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, username.toUpperCase());
		data = ps.executeQuery();
		
		User user = null;
		if(data.next()){
			user = new User();
			user.setID(data.getString("ID"));
			user.setUSERNAME(data.getString("username"));
			user.setPASSWORD(data.getString("password"));
			user.setFULLNAME(data.getString("fullname"));
			user.setSEX(data.getString("sex"));
			user.setPHONE(data.getString("phone"));
			user.setEMAIL(data.getString("email"));
			user.setSTATUS(data.getString("status"));
			user.setIS_ENABLE(data.getString("is_enable"));
		}
		
		data.close();
		ps.close();
		
		if (user == null) {
			return null;
		}else {
			return user;
		}
		
		
	}
	/*
	public String generateMD5(String initString) throws NoSuchAlgorithmException {	
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(initString.getBytes());
		byte byteData[] = md.digest();
		// convert the byte to hex format method 1
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		}		
		return sb.toString();
	}
	*/
}