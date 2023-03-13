package com.api.service.impl;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.api.service.ApiService;
import com.api.service.ParserIntf;
import com.api.vo.AirVO;

@Component
public class AirParser implements ParserIntf {

	@Autowired
	ApiService apiService;

	static public String XmlTag1 = "row";

	@Override
	public String[] element() {
		System.out.println("api tag: row");

		String[] title = new String[] { "MSRDT", "MSRSTE_NM", "NO2", "O3", "CO", "SO2", "PM10", "PM25" };

		return title;
	}

	@Override
	public void serviceExcute(String[] value) {
		AirVO airVO = new AirVO();

		airVO.setMSRDT(value[0]);
		airVO.setMSRSTE_NM(value[1]);
		airVO.setNO2(Float.parseFloat(value[2]));
		airVO.setO3(Float.parseFloat(value[3]));
		airVO.setCO(Float.parseFloat(value[4]));
		airVO.setSO2(Float.parseFloat(value[5]));
		airVO.setPM10(Integer.parseInt(value[6]));
		airVO.setPM25(Integer.parseInt(value[7]));

		apiService.insertAir(airVO);
	}

	@Override
	public void parser(String data) throws Exception {
		// xml parsing start------------------
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document document = db.parse(new InputSource(new StringReader(data)));

		document.getDocumentElement().normalize();
		System.out.println("Root Element :" + document.getDocumentElement().getNodeName());

		String[] arrElementName = element();

		NodeList nList = document.getElementsByTagName(XmlTag1);
		System.out.println("----------------------------");

		// xml data(tag)세트 갯수 불러오기
		int nListLen = nList.getLength();
		System.out.println("::::: data세트 갯수: " + nListLen);

		// xml elements title 불러오기
		System.out.println("::::: elements 갯수: " + arrElementName.length + "\n");
		String[] value = new String[arrElementName.length];

		// xml tag별로 로딩
		for (int temp = 0; temp < nListLen; temp++) {

			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;

				// xml elements title 별로 파싱
				for (int eleNo = 0; eleNo < arrElementName.length; eleNo++) {

					value[eleNo] = eElement.getElementsByTagName(arrElementName[eleNo]).item(0).getTextContent();

				}

				// setter함수에 저장 후 서비스 실행
				serviceExcute(value);

			}
		}
	}
}
