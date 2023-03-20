package com.api.service;

import com.api.vo.AcdntVO;
import com.api.vo.AirVO;

/**
 *  각 데이터들을 dao로 전달하기 위한 인터페이스
 */
public interface ApiService {

	/**
	 *  vo에 담긴 사고 정보 데이터들을 insert한다.
	 */
	public void insertAcdnt(AcdntVO acdnt);

	/**
	 *  vo에 담긴 평균 대기오염도 데이터들을 insert한다.
	 */
	public void insertAir(AirVO air);
}