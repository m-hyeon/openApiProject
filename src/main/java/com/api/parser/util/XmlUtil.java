package com.api.parser.util;

import java.util.ArrayList;
import java.util.List;

import com.api.vo.XmlConvert;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "xmllist")
public class XmlUtil {

	@JacksonXmlProperty(localName = "xml")
	@JacksonXmlElementWrapper(useWrapping = false)
	List<XmlConvert> xml =new ArrayList<>();
}
