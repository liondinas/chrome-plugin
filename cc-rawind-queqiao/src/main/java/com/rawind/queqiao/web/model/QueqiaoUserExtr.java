package com.rawind.queqiao.web.model;

import java.io.Serializable;

import net.paoding.rose.jade.annotation.DAO;



public class QueqiaoUserExtr implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4170851400371170810L;

	public static final int STATUS_NORMAL = 0;
	
	public static final int STATUS_EXPIRED = 1;
	
	
	private long id;
	
	private long userId;
	
	private String userName;
	
	private String passwd;
	
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
	
	
	
	
	
	
}
