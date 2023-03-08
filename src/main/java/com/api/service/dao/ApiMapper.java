package com.api.service.dao;

import org.apache.ibatis.annotations.Mapper;

import com.api.vo.AcdntVO;
import com.api.vo.AirVO;

@Mapper
public interface ApiMapper {

	public void insertAir(AirVO airVO);
	public void insertAcdnt(AcdntVO acdnt);
}
