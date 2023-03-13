package com.api.service.dao;

import org.apache.ibatis.annotations.Mapper;

import com.api.vo.AirVO;

@Mapper
public interface AirMapper {

	public void insertData(AirVO airVO);
}
