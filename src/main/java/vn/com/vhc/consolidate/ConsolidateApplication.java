package vn.com.vhc.consolidate;

import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import vn.com.vhc.consolidate.models.DatabaseInfo;
import vn.com.vhc.consolidate.services.MasterService;

@SpringBootApplication
public class ConsolidateApplication implements CommandLineRunner{
	
	public static int maxMonth;
	
	@Autowired
	private DatabaseInfo ds;
	
	public static void main(String[] args) {
		SpringApplication.run(ConsolidateApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		ds = new DatabaseInfo(ds.getAddress(), ds.getPort(), ds.getSid(), ds.getUserdb(), ds.getPassword(), ds.getMaxMonth());
		System.out.println("Address = " + ds.getAddress());
		maxMonth = Integer.parseInt(ds.getMaxMonth());		
		System.out.println("MAX MONTH:" + maxMonth);
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Where is your Oracle JDBC Driver?");
		}
		MasterService.connection = null;
		try {
			MasterService.connection = DriverManager.getConnection(
					"jdbc:oracle:thin:@" + ds.getAddress() + ":" + ds.getPort() + ":" + ds.getSid() + "",
					ds.getUserdb(), ds.getPassword());
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Connection Failed! Check output console");
		}
	}
}
