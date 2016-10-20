package com.rawind.queqiao.web.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.rawind.queqiao.web.util.TradeNoGenerator;

public class QueqiaoOrder  implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7148780088348254793L;
	
	
	
	public static final int STATUS_CREATED = 0;
	
	public static final int STATUS_PAYED = 1;
	

	private long id;
	
	private long userId;
	
	private String userName;
	
	private int amount;
	
	private int type;
	
	private int status;
	
	private Date createTime;
	
	private Date payTime;
	
	private String memo;
	
	/**
	 * 商户订单号
	 */
	private String queqiaoTradeNo;
	
	/**
	 * 支付宝交易号
	 */
	private String tradeNo;
	
	private int version;
	
	/**
	 * 手续费
	 */
	private int fee;
	
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

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getQueqiaoTradeNo() {
		return queqiaoTradeNo;
	}

	public void setQueqiaoTradeNo(String queqiaoTradeNo) {
		this.queqiaoTradeNo = queqiaoTradeNo;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getFee() {
		return fee;
	}

	public void setFee(int fee) {
		this.fee = fee;
	}
}
