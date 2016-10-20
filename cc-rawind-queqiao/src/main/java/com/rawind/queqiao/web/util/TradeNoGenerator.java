package com.rawind.queqiao.web.util;


import com.chewen.tools.commons.util.CwRandomUtil;
import com.chewen.tools.commons.util.CwStringUtil;

public class TradeNoGenerator {
	private static final int RANDOM_STRING_LENGTH = 5;


	public static String generate(int type) {
		StringBuilder orderNo = new StringBuilder();
		orderNo.append("ALI");
		orderNo.append(CwRandomUtil.randomNumber(RANDOM_STRING_LENGTH));
		orderNo.append(System.currentTimeMillis());
		return orderNo.toString();
	}
	
}
