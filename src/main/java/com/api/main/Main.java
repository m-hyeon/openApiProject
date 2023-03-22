package com.api.main;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
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

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void run(ApplicationArguments args) {
		logger.info("\n \n \n \n");
		acdntRun();
		logger.info("\n \n \n \n");
		airRun();
		logger.info("\n \n \n \n");
	}

	/**
	 * @Scheduled 규칙 =>  Method는 void 타입으로 // Method는 매개변수 사용 불가
	 * 
	 *  apiUtil클래스의 apiGet메서드에 국토교통부_사고정보 API url을 전달하고 Json data를 받아와  AcdntParser에 전달. 
	 *  AcdntParser의 parser 메서드는 Json data를 전달받아 DB에 저장한다. 
	 *  
	 *  
	 */	
	@Scheduled(cron = "${api.acdnt.cron}")
	private void acdntRun() {
		ApiUtil apiUtil = new ApiUtil();

		String url = acdntUrl;
		String data = apiUtil.apiGet(url, acdntType);

		acdntParser.parser(data);
	}

	
	
	/**
	 * @Scheduled 규칙 =>  Method는 void 타입으로 // Method는 매개변수 사용 불가
	 * 
	 *  apiUtil클래스의 apiGet메서드에 서울시 시간 평균 대기오염도 정보 API url을 전달하고 Xml data를 받아와  AirParser에 전달. 
	 *  AirParser의 parser 메서드는 Xml data를 전달받아 DB에 저장한다. 
	 *  
	 *  
	 */	
	@Scheduled(cron = "${api.air.cron}")
	private void airRun() {
		ApiUtil apiUtil = new ApiUtil();

		// 현재 날짜로 date format ex) 20230314
		Date nowDate = new Date();
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyyMMdd");
		String nowDateFormat = formatDate.format(nowDate);

		String url = airUrl + nowDateFormat;
		String data = apiUtil.apiGet(url, airType);

		airParser.parser(data);
	}
}
