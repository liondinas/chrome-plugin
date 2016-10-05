package com.rawind.queqiao.web.model;

import java.io.Serializable;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;



@Entity(noClassnameStored = true)
public class QueqiaoProxy implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6623998263114477903L;

	
	public static final int TYPE_HTTP = 1;
	
	public static final int TYPE_SOCKT = 2;
	
	public static final int TYPE_SOCKTV5 = 3;
	
	public static final int STATUS_NORMAL = 0;
	
	public static final int STATUS_DEL = -1;
	
	
	@Id
	private long id;
	
	private int type;
	
	private String url;
	
	private String password;
		
	private int status;
	

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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}	
	
	public boolean isDel(){
		return status == STATUS_DEL;
	}
}
