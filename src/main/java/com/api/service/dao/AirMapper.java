package com.api.service.dao;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;

import com.api.vo.AcdntVO;
import com.api.vo.AirVO;

@Mapper
public interface AirMapper {

	public void insertData(AirVO airVO);
}
