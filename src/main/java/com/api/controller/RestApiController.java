package com.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.service.ApiService;
import com.api.vo.AcdntVO;
import com.api.vo.AirVO;
import com.api.vo.XmlConvert;

@RestController
public class RestApiController {

	@Autowired
	ApiService apiService;
	
	
	
	@GetMapping("/acdnt")
	public List<AcdntVO> acdntData(){
		
		return apiService.selectAcdnt();
		
	}
	
	@GetMapping("/air")
	public List<AirVO> airData(){
		List<AirVO> air = apiService.selectAir();
		List<XmlConvert> list = new ArrayList<>();
		list.add(air.get(0));
		
		XmlConvert conv = new XmlConvert();
		conv.set
		
		return ;
		
	}
	
	
	
}
