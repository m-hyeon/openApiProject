package com.api.controller;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.service.ApiService;
import com.api.vo.AcdntVO;
import com.api.vo.AirVO;


@RestController
public class RestApiController {

	@Autowired
	ApiService apiService;

	@GetMapping("/acdnt")
	public List<AcdntVO> acdntData() {

		return apiService.selectAcdnt();

	}

	
	// XML 데이터 만들어 주기
	@RequestMapping(value = "/air", method = RequestMethod.GET)
	@ResponseBody
	public AirVO sendxml() {

		List<AirVO> air = apiService.selectAir();
		AirVO airVO = new AirVO();
		airVO.setAirVO(air);
		
		
		return airVO;
	}

}
