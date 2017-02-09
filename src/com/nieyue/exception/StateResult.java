package com.nieyue.exception;

import org.springframework.stereotype.Component;

/**
 * 返回状态DTO
 * @author yy
 *
 */
@Component
public class StateResult {
	private Integer code;
	private String msg;
	
	public StateResult() {
		super();
	}

	public StateResult(Integer code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
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

	@Override
	public String toString() {
		return "StateResult [code=" + code + ", msg=" + msg + "]";
	}
	
	/**
	 * 成功
	 * 
	 */
	public static StateResult getSuccess(){
		StateResult sr=new StateResult();
		sr.setCode(200);
		sr.setMsg("success");
		return sr;
	}
	/**
	 * 普通失败
	 * 
	 */
	public static StateResult getFail(){
		StateResult sr=new StateResult();
		sr.setCode(40000);
		sr.setMsg("fail");
		return sr;
	}
	/**
	 * 常规获取返回状态
	 * @param bl 
	 */
	public static StateResult getSR(boolean bl){
		StateResult sr = StateResult.getSuccess();
		if(!bl){
		sr = StateResult.getFail();
		}
		return sr;
	}
	/**
	 * 自定义获取返回状态
	 * @param bl 
	 */
	public static StateResult getSlefSR(Integer code,String msg){
		StateResult sr = new StateResult();
		sr.setCode(code);
		sr.setMsg(msg);
		return sr;
	}
	/**
	 * 自定义获取返回状态
	 * @param bl 
	 */
	public static StateResult getSlefSR(String code,String msg){
		StateResult sr = new StateResult();
		sr.setCode(Integer.valueOf(code));
		sr.setMsg(msg);
		return sr;
	}
	
}
