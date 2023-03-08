package com.api.vo;

import org.springframework.stereotype.Component;

@Component
public class AirVO {
	String MSRDT;
	String MSRSTE_NM;
	float NO2;
	float O3;
	float CO;
	float SO2;
	int PM10;
	int PM25;
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
	public float getNO2() {
		return NO2;
	}
	public void setNO2(float nO2) {
		NO2 = nO2;
	}
	public float getO3() {
		return O3;
	}
	public void setO3(float o3) {
		O3 = o3;
	}
	public float getCO() {
		return CO;
	}
	public void setCO(float cO) {
		CO = cO;
	}
	public float getSO2() {
		return SO2;
	}
	public void setSO2(float sO2) {
		SO2 = sO2;
	}
	public int getPM10() {
		return PM10;
	}
	public void setPM10(int pM10) {
		PM10 = pM10;
	}
	public int getPM25() {
		return PM25;
	}
	public void setPM25(int pM25) {
		PM25 = pM25;
	}

	

}
