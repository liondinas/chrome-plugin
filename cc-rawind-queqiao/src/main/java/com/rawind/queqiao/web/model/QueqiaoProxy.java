package com.rawind.queqiao.web.model;

import java.io.Serializable;

import org.mongodb.morphia.annotations.Entity;



@Entity(noClassnameStored = true)
public class QueqiaoProxy implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6623998263114477903L;

	private long id;
	
	private int type;
	
	private String url;
	
	private String password;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
	
}
