package com.rawind.queqiao.web.model;

public enum OrderTypeEnum {
	
	FIRST_ONE_MONTH(1001, 20, 30, "一个月(带宽:20M)", 10*100),
	FIRST_THREE_MONTH(1003, 20, 90, "三个月(带宽:20M)", 30*100),
	FIRST_SIX_MONTH(1006, 20, 180, "六个月(带宽:20M)", 50*100),
	FIRST_12_MONTH(1012, 20, 365, "12个月(带宽:20M)", 100*100),
	NOLIMIT_ONE_MONTH(1001, 20, 30, "一个月(带宽:无限制)", 20*100),
	NOLIMIT_THREE_MONTH(1003, 20, 90, "三个月(带宽:无限制)", 60*100),
	NOLIMIT_SIX_MONTH(1006, 20, 180, "六个月(带宽:无限制)", 100*100),
	NOLIMIT_12_MONTH(1012, 20, 365, "12个月(带宽:无限制)", 200*100);;
	
	
	private OrderTypeEnum(int code, int width, int day, String memo, int amount){
		this.code = code;
		this.width = width;
		this.days = day;
		this.memo = memo;
		this.amount = amount;
	}
	
	
	private int code;
	
	private int width;
	
	private int days;
	
	private String memo;
	
	private int amount;

	public int getAmount() {
		return amount;
	}

	public int getCode() {
		return code;
	}

	public int getWidth() {
		return width;
	}

	public int getDays() {
		return days;
	}

	public String getMemo() {
		return memo;
	}
	
	public String getAmountString(){
		return "￥:" + amount/100;
	}
	
	public static OrderTypeEnum getByCode(int code){
		for(OrderTypeEnum item : OrderTypeEnum.values()){
			if(item.getCode() == code){
				return item;
			}
		}
		
		return null;
	}
	
	
	
	
	
}
