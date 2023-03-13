package com.api.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.api.parser.impl.AcdntParser;
import com.api.parser.impl.AirParser;
import com.api.parser.util.ApiUtil;

@Component
@PropertySource(value = "classpath:application.properties", encoding = "UTF-8")
public class Main implements ApplicationRunner {

	@Autowired
	AcdntParser acdntParser; 
	
	@Autowired
	AirParser airParser;

	
	@Value("${com.api.acdnt.url}")
	private String acdntUrl;
	
	@Value("${com.api.acdnt.type}")
	private String acdntType;

	@Value("${com.api.air.url}")
	private String airUrl;
	
	@Value("${com.api.air.type}")
	private String airType;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		scheduledRun();
		
	}
	
	
	private void scheduledRun() throws Exception {
		ApiUtil apiUtil = new ApiUtil();
		
		String url = acdntUrl;
		String data = apiUtil.apiGet(url, acdntType);

		System.out.println("data : " + data);

		String url2 = airUrl;
		String data2 = apiUtil.apiGet(url2, airType);

		System.out.println("data2 : " + data2);

		
		acdntParser.parser(data);
		airParser.parser(data2);
	}
}
