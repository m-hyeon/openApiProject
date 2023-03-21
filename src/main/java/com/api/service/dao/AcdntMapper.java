package com.api.service.dao;

import org.apache.ibatis.annotations.Mapper;

import com.api.vo.AcdntVO;

/**
 * 사고 정보 DAO
 */
@Mapper
public interface AcdntMapper {

	/**
	 * vo에 담긴 데이터들을 insert한다.
	 */
	public void insertData(AcdntVO acdnt);
}
