package com.api.service.dao;

import org.apache.ibatis.annotations.Mapper;

import com.api.vo.AirVO;

/**
 * 평균 대기오염도 DAO
 */
@Mapper
public interface AirMapper {

	public void insertData(AirVO airVO);
}
