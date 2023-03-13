package com.api.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.api.service.impl.AcdntParser;
import com.api.service.impl.AirParser;
import com.api.util.ApiUtil;

@Component
@PropertySource(value = "classpath:application.properties", encoding = "UTF-8")
public class Main implements ApplicationRunner {

	@Autowired
	AcdntParser acdntParser; 
	
	@Autowired
	AirParser airParser;


	@Override
	public void run(ApplicationArguments args) throws Exception {
		ApiUtil apiUtil = new ApiUtil();
		
		String url = "https://openapi.its.go.kr:9443/eventInfo?apiKey=c93967afe8df48fdbe73ce95b0b2a993&type=all&eventType=all&minX=126.764470&maxX=127.183797&minY=37.428387&maxY=37.701407&getType=json";
		String data = apiUtil.apiGet(url, "json");

		System.out.println("data : " + data);

		String url2 = "http://openAPI.seoul.go.kr:8088/6a4b587743736978373451775a5261/xml/TimeAverageAirQuality/1/5/20130615/";
		String data2 = apiUtil.apiGet(url2, "xml");

		System.out.println("data2 : " + data2);

		
		acdntParser.parser(data);
		airParser.parser(data2);

		
		
	}

}
