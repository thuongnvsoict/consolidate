package vn.com.vhc.consolidate.filters;

import vn.com.vhc.consolidate.models.AccountCredentials;
import vn.com.vhc.consolidate.services.TokenAuthenticationService;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;

/**
 * Created by nhs3108 on 29/03/2017.
 */
public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {
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
    public JWTLoginFilter(String url, AuthenticationManager authManager) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        AccountCredentials credentials;
		credentials = new AccountCredentials(request.getParameter("username"), request.getParameter("password"));
		return getAuthenticationManager().authenticate(
		        new UsernamePasswordAuthenticationToken(
		                credentials.getUsername(),
		                credentials.getPassword(),
		                Collections.emptyList()
		        )
		);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        TokenAuthenticationService.addAuthentication(response, authResult.getName());
    }
}
