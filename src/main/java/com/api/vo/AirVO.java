package com.api.vo;

import org.springframework.stereotype.Component;

@Component
public class AirVO {
	private String MSRDT;
	private String MSRSTE_NM;
	private Float NO2;
	private Float O3;
	private Float CO;
	private Float SO2;
	private Integer PM10;
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
