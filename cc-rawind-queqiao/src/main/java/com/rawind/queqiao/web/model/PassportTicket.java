package com.rawind.queqiao.web.model;

import java.io.Serializable;

public class PassportTicket implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -543479038093033464L;

	private final long userId;

	private final String userAgent;

	private final long lifeTime;

	private final long expiredTime;

	private String signature;

	public PassportTicket(long userId, String userAgent, long lifeTime,long expiredTime) {
		super();
		this.userId = userId;
		this.userAgent = userAgent;
		this.lifeTime = lifeTime;
		this.expiredTime = expiredTime;
	}

	public long getUserId() {
		return userId;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public long getLifeTime() {
		return lifeTime;
	}

	public long getExpiredTime() {
		return expiredTime;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getContent() {
		return "" + userId + userAgent + lifeTime + expiredTime;
	}
}
