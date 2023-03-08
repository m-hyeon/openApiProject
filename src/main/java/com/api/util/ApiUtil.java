package com.api.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiUtil {

	public String apiGet(String url) {

		StringBuffer response = new StringBuffer();

		try {
			URL apiUrl = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) apiUrl.openConnection();

			// Request 형식 설정
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Type", "application/json");

			// 응답 데이터 받아오기
			int responseCode = conn.getResponseCode();
			BufferedReader br;
			if (responseCode == 200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			} else {
				br = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "UTF-8"));
			}

			String line;
			while ((line = br.readLine()) != null) {
				response.append(line);
			}

			br.close();
			conn.disconnect();

			//TODO 나중에 지울거임
			// 전송받은 데이터 파일로 저장
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response.toString();
	}
}
