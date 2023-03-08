package com.api.service.impl;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.api.service.ApiService;
import com.api.service.ParserIntf;
import com.api.vo.AcdntVO;

@Component
public class AcdntParser implements ParserIntf {

	@Autowired
	AcdntVO acdntVO;

	@Autowired
	ApiService apiService;

	static public String jsonTag1 = "items";

	@Override
	public String[] element() {
		System.out.println("api tag: items");

		String[] title = new String[] { "type", "eventType", "eventDetailType", "startDate", "coordX", "coordY",
				"linkId", "roadName", "roadNo", "roadDrcType", "lanesBlockType", "lanesBlocked", "message", "endDate" };

		return title;
	}

	@Override
	public void serviceExcute(String[] value) {
		acdntVO.setType(value[0]);
		acdntVO.setEventType(value[1]);
		acdntVO.setEventDetailType(value[2]);
		acdntVO.setStartDate(value[3]);
		acdntVO.setCoordX(Float.parseFloat(value[4]));
		acdntVO.setCoordY(Float.parseFloat(value[5]));
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

	@Override
	public void parser(String data) throws Exception {
		String[] arrElementName = element();

		// 가장 큰 JSONObject를 가져옵니다.
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(data);

		// 배열을 가져옵니다.
		// obj를 우선 JSONObject에 담음
		JSONObject jsonMain = (JSONObject) obj;
		JSONObject jsonBody = (JSONObject) jsonMain.get("body");

		// jsonObject에서 jsonArray를 get함
		JSONArray tagArr = (JSONArray) jsonBody.get(jsonTag1);

		// data세트 갯수
		int tagSize = tagArr.size();
		System.out.println("::::: data세트 갯수: " + tagSize);

		// json elements title 불러오기
		System.out.println("::::: elements 갯수: " + arrElementName.length + "\n");
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
	}
}
