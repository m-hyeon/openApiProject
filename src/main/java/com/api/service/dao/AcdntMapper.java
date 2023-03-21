package com.api.service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.api.vo.AcdntVO;

@Mapper
public interface AcdntMapper {
	
	public List<AcdntVO> selectData();

	public void insertData(AcdntVO acdnt);
}
