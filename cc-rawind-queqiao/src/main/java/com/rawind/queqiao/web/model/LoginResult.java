package com.rawind.queqiao.web.model;

import java.io.Serializable;



public class LoginResult implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2545097658027371567L;
	
	public static final int CODE_SUCCESS = 1;
	
	public static final int CODE_FAIL = 0;
	
	public static final int CODE_NOFILL = -1;
	
	
	private int resultCode = CODE_FAIL;
    private QueqiaoUser user;
	public int getResultCode() {
		return resultCode;
	}
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
	public QueqiaoUser getQueqiaoUser() {
		return user;
	}
	public void setQueqiaoUser(QueqiaoUser user) {
		this.user = user;
	}
}
