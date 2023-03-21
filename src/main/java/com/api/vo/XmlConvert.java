package com.api.vo;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "xml")
public class XmlConvert {

	@JacksonXmlProperty(isAttribute = true) private String MSRDT; 
	@JacksonXmlProperty private String MSRSTE_NM; 
}
