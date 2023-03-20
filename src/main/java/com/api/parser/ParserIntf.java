package com.api.parser;

/**
 *  openApi를 통해 받아온 data를 파싱하기 위한 인터페이스
 */
public interface ParserIntf {

	/**
	 *  oepnApi를 통해 받아온 data의 태그 값을 String 배열로 저장
	 */
	public String[] element();

	/**
	 *  oepnApi를 통해 받아온 data의 파싱된 값을 vo에 Set
	 */
	public void serviceExcute(String[] value);

	/**
	 *  openApi를 통해 받아온 data를 각각 해당하는 type에 맞게 파싱
	 *  type에는 xml과 json이 있다.
	 */
	public void parser(String data) throws Exception;
}
