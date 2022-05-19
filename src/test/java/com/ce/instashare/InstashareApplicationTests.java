package com.ce.instashare;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(classes=InstashareApplication.class,webEnvironment=WebEnvironment.RANDOM_PORT)
class InstashareApplicationTests {
	
	@LocalServerPort
    private String randomServerPort;
	
	private static String hostUrl = "http://localhost:";

	@Test
	void checkEmail() throws URISyntaxException{
		RestTemplate restTemplate = new RestTemplate();
		String baseUrl = hostUrl + randomServerPort; 
	    String url = baseUrl + "/user/checkemail";
	    URI uri = new URI(url);
	    
	    String drone = "{"
	    		+ "    \"email\":\"rmora900121@gmail.com\"\r\n"
	    		+ "}";
	    try {
	    	MultiValueMap<String,String> headers = new LinkedMultiValueMap<String, String>();
	    	headers.set("Content-Type","application/json");
	    	HttpEntity<String> entity = new HttpEntity<String>(drone, headers);
	    	ResponseEntity<String> response = restTemplate.postForEntity(uri, entity, String.class);
	    	String body = response.getBody();
	    	String expected = "{\"errorCode\":1,\"errorDescription\":\"ERROR Email Already Exist:: rmora900121@gmail.com\"}";
	    	assertEquals(expected,body);
	    }
	    catch (Exception e) {
	    	//test failed	    	
	    	assertEquals("", e.toString());
		}	    
	}

}
