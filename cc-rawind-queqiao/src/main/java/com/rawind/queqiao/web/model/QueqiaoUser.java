package com.rawind.queqiao.web.model;

import java.io.Serializable;
import java.util.Date;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;


@Entity(noClassnameStored = true)
public class QueqiaoUser implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -370840220654080672L;
	@Id
	private long id;
	private String name;
	private String email;
	private long phoneNum;
	private String passWord;
	private String passport;
	private int type;
	private Date expiredTime;
	private Date createTime;
	private Date lastLoginTime;
	private String lastLoginIp;
	private long proxyId;
	private String proxyStr;
	private String from;
	private Date upTime;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(long phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getPassport() {
		return passport;
	}
	public void setPassport(String passport) {
		this.passport = passport;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Date getExpiredTime() {
		return expiredTime;
	}
	public void setExpiredTime(Date expiredTime) {
		this.expiredTime = expiredTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public long getProxyId() {
		return proxyId;
	}
	public void setProxyId(long proxyId) {
		this.proxyId = proxyId;
	}
	public String getProxyStr() {
		return proxyStr;
	}
	public void setProxyStr(String proxyStr) {
		this.proxyStr = proxyStr;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getLastLoginIp() {
		return lastLoginIp;
	}
	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}
	public Date getUpTime() {
		return upTime;
	}
	public void setUpTime(Date upTime) {
		this.upTime = upTime;
	}	
	
	public boolean isExpired(){
		if(expiredTime == null){
			return true;
		}
		
		return expiredTime.compareTo(new Date())>0;
	}
	
	
}
