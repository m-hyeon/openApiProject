package com.api.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiUtil {

	public String apiGet(String url, String type) {

		StringBuffer response = new StringBuffer();

		try {
			URL apiUrl = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) apiUrl.openConnection();

			// Request 형식 설정
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Type", "application/"+ type);

			System.out.println("conn : " + conn.getRequestProperty("Content-Type"));

			// 응답 데이터 받아오기
			int responseCode = conn.getResponseCode();
			BufferedReader br;
			System.out.println("url responseCode : " + responseCode);
			if (responseCode == 200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			} else {
				br = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "UTF-8"));
			}

			String line;
			System.out.println("data 읽어오기 시작");
			while ((line = br.readLine()) != null) {
				response.append(line);
			}
			System.out.println("data 읽어오기 완료");

			br.close();
			conn.disconnect();

			//TODO 추후 데이터 파싱하는 부분과 연결하기 위해 파일을 생성함. (나중에 지울거임)
			// 전송받은 데이터 파일로 저장
			BufferedWriter bw = new BufferedWriter(new FileWriter("D:\\dataFile.txt", true));
			bw.write(response.toString());
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response.toString();
	}
}
