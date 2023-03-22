package com.api.parser.impl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.api.parser.ParserIntf;
import com.api.service.ApiService;
import com.api.vo.AcdntVO;

@Component
@Transactional
public class AcdntParser implements ParserIntf {

	@Autowired
	ApiService apiService;
	
	private String jsonTag1 = "items";
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	
	
	
	
	/**
	 * oepnApi를 통해 받아온 data의 태그 값을 String 배열로 저장
	 */
	@Override
	public String[] element() {
		String[] title = new String[] { "type", "eventType", "eventDetailType", "startDate", "coordX", "coordY",
				"linkId", "roadName", "roadNo", "roadDrcType", "lanesBlockType", "lanesBlocked", "message", "endDate" };

		return title;
	}

	
	
	
	/**
	 * oepnApi를 통해 받아온 data의 파싱된 값을 vo에 Set 하고, insertAcdnt 메서드 실행하여 db에 파싱된 값을 저장
	 * 
	 */
	@Override
	public void serviceExcute(String[] value) {
		AcdntVO acdntVO = new AcdntVO();

		acdntVO.setType(value[0]);
		acdntVO.setEventType(value[1]);
		acdntVO.setEventDetailType(value[2]);
		acdntVO.setStartDate(value[3]);
		if ((!"".equals(value[4]))) {
			acdntVO.setCoordX(Float.parseFloat(value[4]));
		}
		if ((!"".equals(value[5]))) {
			acdntVO.setCoordY(Float.parseFloat(value[5]));
		}
		acdntVO.setLinkId(value[6]);
		acdntVO.setRoadName(value[7]);
		if (!"".equals(value[8])) {
			acdntVO.setRoadNo(Integer.parseInt(value[8]));
		}
		acdntVO.setRoadDrcType(value[9]);
		acdntVO.setLanesBlockType(value[10]);
		acdntVO.setLanesBlocked(value[11]);
		acdntVO.setMessage(value[12]);
		acdntVO.setEndDate(value[13]);

		apiService.insertAcdnt(acdntVO);
	}

	
	
	/**
	 * openApi를 통해 받아온 Json data를 각각 해당하는 type에 맞게 파싱 후 serviceExcute 실행
	 * 
	 */
	@Override
	public void parser(String data) {
		try {
			String[] arrElementName = element();

			// 가장 큰 JSONObject를 가져옵니다.
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(data);

			// Json의 배열을 가져옴.
			// obj를 우선 JSONObject에 담음
			JSONObject jsonMain = (JSONObject) obj;
			JSONObject jsonBody = (JSONObject) jsonMain.get("body");

			// jsonObject에서 jsonArray를 get함
			JSONArray tagArr = (JSONArray) jsonBody.get(jsonTag1);

			// data세트 갯수
			int tagSize = tagArr.size();
			logger.info("acdnt data 세트 갯수: {}", tagSize);

			// json elements title 불러오기
			logger.info("acdnt elements 갯수: {}", arrElementName.length + "\n");
			String[] value = new String[arrElementName.length];

			// jsonArray에서 하나씩 elementArray하나씩 load
			if (tagArr.size() > 0) {
				for (int tagNo = 0; tagNo < tagSize; tagNo++) {

					JSONObject allElements = (JSONObject) tagArr.get(tagNo);
					int allSize = allElements.size();

					// elementArray에서 하나씩 JSONObject로 cast해서 사용
					for (int eleNo = 0; eleNo < allSize; eleNo++) {
						value[eleNo] = (String) allElements.get(arrElementName[eleNo]);
					}

					serviceExcute(value);
				}

			}
		} catch (Exception e) {
			// 현재 날짜로 date format ex) 20230314
			Date nowDate = new Date();
			SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd_HHmm");
			String nowDateFormat = formatDate.format(nowDate);

			logger.error("acdnt parsing error!! ./log/acdntError_{}.log로 저장", nowDateFormat);
			logger.error("{}", e);

			BufferedWriter bw;
			try {
				
				
				//파싱 실패시  .log 파일로 log 폴더에 저장
				bw = new BufferedWriter(new FileWriter("./log/acdntError_" + nowDateFormat + ".log", true));
				bw.write(data.toString());
				bw.close();
			} catch (IOException e1) {
				logger.error("acdntError 저장 실패!!");
				logger.error("error msg: {}", e.toString());
			}
		}
	}
}
