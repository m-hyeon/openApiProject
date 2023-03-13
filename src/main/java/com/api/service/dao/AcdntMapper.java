package com.api.service.dao;

import org.apache.ibatis.annotations.Mapper;

import com.api.vo.AcdntVO;

@Mapper
public interface AcdntMapper {

	public void insertData(AcdntVO acdnt);
}
