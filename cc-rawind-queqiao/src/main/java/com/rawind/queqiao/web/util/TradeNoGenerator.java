package com.rawind.queqiao.web.util;


import com.chewen.tools.commons.util.CwRandomUtil;
import com.chewen.tools.commons.util.CwStringUtil;

public class TradeNoGenerator {
	private static final int RANDOM_STRING_LENGTH = 5;

	public static String generateTradeNo(long orderId) {
		StringBuilder orderNo = new StringBuilder();
		orderNo.append("ali-");
		orderNo.append(CwRandomUtil.randomNumber(RANDOM_STRING_LENGTH)+"-");
		orderNo.append(orderId);
		return orderNo.toString();
	}
	
	
	public static long getOrderIdFromTradeNo(String queqiaoTradeNo){
		if(queqiaoTradeNo == null){
			return 0l;
		}		
		String[] arry = queqiaoTradeNo.split("-");
		if(arry.length>=3){		
			return CwStringUtil.conver2Long(arry[2], 0l);
		}
		return 0l;
	}
	
}
