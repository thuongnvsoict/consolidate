package vn.com.vhc.consolidate.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import vn.com.vhc.consolidate.models.DatabaseInfo;

import javax.sql.DataSource;

/**
 * Created by nhs3108 on 07/11/2017.
 */
@Configuration
public class DataSourceConfig {
	
	@Autowired
	private DatabaseInfo ds;
	
    @Bean
    public DataSource dataSource() {
    	return DataSourceBuilder.create()
    			.url("jdbc:oracle:thin:@//"+ds.getAddress()+":"+ds.getPort()+"/"+ds.getSid())
        .driverClassName("oracle.jdbc.OracleDriver")
        .username(ds.getUserdb())
        .password(ds.getPassword()).build();
    }
}
