package com.api.parser.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApiUtil {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public String apiGet(String url, String type) {

		StringBuffer response = new StringBuffer();

		try {
			URL apiUrl = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) apiUrl.openConnection();

			// Request 형식 설정
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Type", "application/" + type);

			logger.info("content-type : {}", conn.getRequestProperty("Content-Type"));

			// 응답 데이터 받아오기
			int responseCode = conn.getResponseCode();
			BufferedReader br;

			logger.info("url responseCode : {}", responseCode);

			if (responseCode == 200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			} else {
				br = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "UTF-8"));
			}

			String line;
			logger.info("data download");
			while ((line = br.readLine()) != null) {
				response.append(line);
			}

			br.close();
			conn.disconnect();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return response.toString();
	}
}
