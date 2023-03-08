package com.api;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.api.util.ApiUtil;

@SpringBootApplication
public class OpenapiApplication {

	public static void main(String[] args) {
		// SpringApplication.run(OpenapiApplication.class, args);

		String url = "https://openapi.its.go.kr:9443/eventInfo?apiKey=c93967afe8df48fdbe73ce95b0b2a993&type=all&eventType=all&minX=126.764470&maxX=127.183797&minY=37.428387&maxY=37.701407&getType=json";
		ApiUtil apiUtil = new ApiUtil();
		apiUtil.apiGet(url);
	}

}
