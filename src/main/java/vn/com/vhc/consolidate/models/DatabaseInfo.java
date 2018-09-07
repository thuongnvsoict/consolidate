package vn.com.vhc.consolidate.models;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
public class DatabaseInfo {
	
	private String address;
	private String port;
	private String sid;
	private String userdb;
	private String password;
	private String maxMonth;
	
	public String getMaxMonth() {
		return maxMonth;
	}

	public void setMaxMonth(String maxMonth) {
		this.maxMonth = maxMonth;
	}

	public DatabaseInfo(String address, String port, String sid, String userdb, String password, String maxMonth) {
		super();
		this.address = address;
		this.port = port;
		this.sid = sid;
		this.userdb = userdb;
		this.password = password;
		this.maxMonth = maxMonth;
	}

	public DatabaseInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getUserdb() {
		return userdb;
	}

	public void setUserdb(String userdb) {
		this.userdb = userdb;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
}