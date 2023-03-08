package com.api.service;

public interface ParserIntf {

	public String[] element();

	public void serviceExcute(String[] value);

	public void parser(String data) throws Exception;
}
