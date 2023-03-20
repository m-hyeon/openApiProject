package com.api.parser.impl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.api.parser.ParserIntf;
import com.api.service.ApiService;
import com.api.vo.AirVO;

@Component
public class AirParser implements ParserIntf {

	@Autowired
	ApiService apiService;

	private String XmlTag1 = "row";
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public String[] element() {
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
	public void parser(String data) {

		try {
			// xml parsing start------------------
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(new InputSource(new StringReader(data)));

			document.getDocumentElement().normalize();

			String[] arrElementName = element();

			NodeList nList = document.getElementsByTagName(XmlTag1);

			// xml data(tag)세트 갯수 불러오기
			int nListLen = nList.getLength();
			logger.info("air data 세트 갯수: {}", nListLen);

			// xml elements title 불러오기
			logger.info("air elements 갯수: {}", arrElementName.length + "\n");
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
		} catch (Exception e) {
			// 현재 날짜로 date format ex) 20230314
			Date nowDate = new Date();
			SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
			String nowDateFormat = formatDate.format(nowDate);

			logger.error("air parsing error!! ./log/airError_{}.log로 저장", nowDateFormat);
			logger.error("error msg: {}", e.toString());

			BufferedWriter bw;
			try {
				bw = new BufferedWriter(new FileWriter("./log/airError_" + nowDateFormat + ".log", true));
				bw.write(data.toString());
				bw.close();
			} catch (IOException e1) {
				logger.error("airError 저장 실패!!");
				logger.error("error msg: {}", e.toString());
			}
		}
	}
}
