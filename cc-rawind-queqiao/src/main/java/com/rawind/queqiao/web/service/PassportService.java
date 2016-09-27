package com.rawind.queqiao.web.service;

import com.rawind.queqiao.web.model.PassportVerifyResult;



public interface PassportService {
	
	
	/**
	 * 生成passport
	 * @param userId
	 * @param userAgent
	 * @return
	 */
	public String genPsssport(long userId, String userAgent);
	/**
	 * 检验passport合法性
	 * @param passport
	 * @param userAgent
	 * @return
	 */
	public PassportVerifyResult verifyPassport(String passport,String userAgent);
	
	/**
	 * 截取passport中的userId
	 * @param passport
	 * @return
	 */
	public String obtainAdminId(String passport);

	
}
