package com.atguigu.ssm.crud.domain;

import java.util.HashMap;
import java.util.Map;

public class Msg {
	//状态码200成功 500失败
	private  Integer code;
	//提示信息
	private String msg;
	
	//装载数据信息
	private Map<String,Object> extand =new HashMap<>();

	
	public static Msg success( ) {
		Msg msg =new Msg();
		msg.setCode(200);
		msg.setMsg("成功");
		return msg;
	}
	
	public static Msg fail( ) {
		Msg msg =new Msg();
		msg.setCode(500);
		msg.setMsg("失败");
		return msg;
	}
	
	public Msg addDate(String key,Object value ) {
		this.getExtand().put(key, value);
		
		return this;
		
	}
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Map<String, Object> getExtand() {
		return extand;
	}

	public void setExtand(Map<String, Object> extand) {
		this.extand = extand;
	}
	
	

}
