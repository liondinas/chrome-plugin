package com.rawind.queqiao.web.model;

import java.io.Serializable;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity(noClassnameStored = true)
public class DailySinatureKey implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4858513468354694939L;
	@Id
	private long id;
	private String createTime;
	private String sinatureKey;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getSinatureKey() {
		return sinatureKey;
	}
	public void setSinatureKey(String sinatureKey) {
		this.sinatureKey = sinatureKey;
	}
	
}
