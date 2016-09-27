package com.rawind.queqiao.web.model;

import java.io.Serializable;

public class PassportVerifyResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3718504668592054587L;

	public static final int NOT_EXIST = 1;
	
	public static final int EXPIRED = 2;
	
    public static final int OK = 3;

    public static final int INVALID = 4;
    
    private int validationCode;
    private long userId;
    
	public int getValidationCode() {
		return validationCode;
	}
	public void setValidationCode(int validationCode) {
		this.validationCode = validationCode;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	public boolean isOK(){
		return OK == validationCode;
	}
}
