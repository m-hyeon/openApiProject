package com.api.service;

import java.util.List;

import com.api.vo.AcdntVO;
import com.api.vo.AirVO;

public interface ApiService {
	
	public List<AcdntVO> selectAcdnt();
	
	public List<AirVO> selectAir();

	public void insertAcdnt(AcdntVO acdnt);

	public void insertAir(AirVO air);
}