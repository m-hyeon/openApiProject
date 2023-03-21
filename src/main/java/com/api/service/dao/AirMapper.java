package com.api.service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.api.vo.AirVO;

@Mapper
public interface AirMapper {

	public List<AirVO> selectData();
	
	public void insertData(AirVO airVO);
}
