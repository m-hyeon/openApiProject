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

@RestController
public class RestApiController {

	@Autowired
	ApiService apiService;
	
	
	
	@GetMapping("/acdnt")
	public List<AcdntVO> acdntData(){
		
		return apiService.selectAcdnt();
		
	}
	
	@GetMapping(path ="/air", produces = MediaType.APPLICATION_XML_VALUE)
	public List<AirVO> airData(){
		//https://velog.io/@garam0410/Java-OPEN-API-%ED%8C%8C%EC%8B%B1%ED%95%98%EA%B8%B0-XML
		
		return ;
		
	}
	
	
	
}
