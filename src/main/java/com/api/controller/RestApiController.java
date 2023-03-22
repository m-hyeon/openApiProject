package com.api.controller;

import java.util.List;

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
	
	
	
	/**
	 * json으로 its사고정보를 뿌려주는 method
	 */	
	@GetMapping("/acdnt")
	public List<AcdntVO> acdntData() {

		return apiService.selectAcdnt();

	}

	
	
	
	/**
	 * //xml로 서울시 시간 평균 대기오염도 정보 뿌려주는 method
	 */	
	@RequestMapping(value = "/air", method = RequestMethod.GET)
	@ResponseBody
	public AirVO sendxml() {

		List<AirVO> air = apiService.selectAir();
		AirVO airVO = new AirVO();
		airVO.setAirVO(air);
		
		
		return airVO;
	}

}
