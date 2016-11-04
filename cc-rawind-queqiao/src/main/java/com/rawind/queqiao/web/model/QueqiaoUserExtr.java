package com.rawind.queqiao.web.model;

import java.io.Serializable;
import java.util.Date;

import com.chewen.tools.commons.util.CwDateTimeUtil;

import net.paoding.rose.jade.annotation.DAO;



public class QueqiaoUserExtr implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4170851400371170810L;

	public static final int STATUS_ALL = 0;
	
	public static final int STATUS_NORMAL = 1;
	
	public static final int STATUS_EXPIRED = 2;
	
	
	private long id;
	
	private long userId;
	
	private String userName;
	
	private String passwd;
	
	private Date expiredTime;
	
	private int status = STATUS_NORMAL;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getExpiredTime() {
		return expiredTime;
	}

	public void setExpiredTime(Date expiredTime) {
		this.expiredTime = expiredTime;
	}
	
	
	public int checkStatusByExpiredTime(){
		if(expiredTime == null){
			return STATUS_EXPIRED;
		}
		
		Date cur = CwDateTimeUtil.cleanMinutes(new Date());
		if(expiredTime.compareTo(cur)<0){
			return STATUS_EXPIRED;
		}
		return STATUS_NORMAL;
		
	}
	
	
	
	
}
