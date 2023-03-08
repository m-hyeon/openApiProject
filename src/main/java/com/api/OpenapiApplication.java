package com.api;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.api.service.impl.AcdntParser;
import com.api.service.impl.AirParser;
import com.api.util.ApiUtil;

@SpringBootApplication
public class OpenapiApplication {

	public static void main(String[] args) throws Exception {
		// SpringApplication.run(OpenapiApplication.class, args);

		String url = "https://openapi.its.go.kr:9443/eventInfo?apiKey=c93967afe8df48fdbe73ce95b0b2a993&type=all&eventType=all&minX=126.764470&maxX=127.183797&minY=37.428387&maxY=37.701407&getType=json";
		ApiUtil apiUtil = new ApiUtil();
		String data = apiUtil.apiGet(url, "json");

		System.out.println("data : " + data);

		String url2 = "http://openAPI.seoul.go.kr:8088/6a4b587743736978373451775a5261/xml/TimeAverageAirQuality/1/5/20130615/종로구";
		String data2 = apiUtil.apiGet(url2, "xml");

		System.out.println("data2 : " + data2);

//		AirParser airParser = new AirParser();
//		airParser.parser(data2);

		AcdntParser acdntParser = new AcdntParser();
		acdntParser.parser(data);
	}

}
