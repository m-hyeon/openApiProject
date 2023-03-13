package com.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.service.ApiService;
import com.api.service.dao.AcdntMapper;
import com.api.service.dao.AirMapper;
import com.api.vo.AcdntVO;
import com.api.vo.AirVO;

@Service
public class ApiServiceImpl implements ApiService{

	@Autowired
	AirMapper airMapper;
	
	@Autowired
	AcdntMapper acdntMapper;

	@Override
	public void insertAcdnt(AcdntVO acdnt) {

		acdntMapper.insertData(acdnt);
	}
	
	// 서울 오염도 정보 insert
	public void insertAir(AirVO air) {

		airMapper.insertData(air);

	};
	
}
