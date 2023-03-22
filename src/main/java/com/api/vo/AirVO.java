package com.api.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * DB에 저장된 데이터를 Xml Api로 제공하기 위한 세팅. (jdom 라이브러리.)
 * 참고 사이트 [https://codevang.tistory.com/262]
 * 
 * @XmlAccessorType(XmlAccessType.NONE) -  Jaxb 어노테이션을 설정한 필드만 직렬화
 * @XmlRootElement(name ="") - root 계층 설정 *명시하지 않으면 클래스명이 디폴트로 적용
 * @XmlAttribute : 루트 태그의 값 
 * @XmlElement : 하위 태그의 값
 * 
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "body")
public class AirVO {
	
	@XmlElement(name = "items")
	private List<AirVO> airVO;
	
	public List<AirVO> getAirVO() {
		return airVO;
	}

	public void setAirVO(List<AirVO> airVO) {
		this.airVO = airVO;
	}

	@XmlElement
	private String MSRDT;

	@XmlElement
	private String MSRSTE_NM;

	@XmlElement
	private Float NO2;

	@XmlElement
	private Float O3;

	@XmlElement
	private Float CO;

	@XmlElement
	private Float SO2;

	@XmlElement
	private Integer PM10;

	@XmlElement
	private Integer PM25;

	public String getMSRDT() {
		return MSRDT;
	}

	public void setMSRDT(String mSRDT) {
		MSRDT = mSRDT;
	}

	public String getMSRSTE_NM() {
		return MSRSTE_NM;
	}

	public void setMSRSTE_NM(String mSRSTE_NM) {
		MSRSTE_NM = mSRSTE_NM;
	}

	public Float getNO2() {
		return NO2;
	}

	public void setNO2(Float nO2) {
		NO2 = nO2;
	}

	public Float getO3() {
		return O3;
	}

	public void setO3(Float o3) {
		O3 = o3;
	}

	public Float getCO() {
		return CO;
	}

	public void setCO(Float cO) {
		CO = cO;
	}

	public Float getSO2() {
		return SO2;
	}

	public void setSO2(Float sO2) {
		SO2 = sO2;
	}

	public Integer getPM10() {
		return PM10;
	}

	public void setPM10(Integer pM10) {
		PM10 = pM10;
	}

	public Integer getPM25() {
		return PM25;
	}

	public void setPM25(Integer pM25) {
		PM25 = pM25;
	}

	@Override
	public String toString() {
		return "AirVO [MSRDT=" + MSRDT + ", MSRSTE_NM=" + MSRSTE_NM + ", NO2=" + NO2 + ", O3=" + O3 + ", CO=" + CO
				+ ", SO2=" + SO2 + ", PM10=" + PM10 + ", PM25=" + PM25 + "]";
	}
}
