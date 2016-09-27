package com.rawind.queqiao.web.service;

import java.util.List;

import com.rawind.queqiao.web.model.LoginResult;
import com.rawind.queqiao.web.model.QueqiaoUser;



public interface QueqiaoUserService {

	/**
	 * 登录判断
	 * @param dealerKey
	 * @param userName
	 * @param passWord
	 * @return
	 */
	public LoginResult login(String email, String passWord);
	
	
	/**
	 * 根据ID获得admin
	 * @param id
	 * @return
	 */
	public QueqiaoUser getQueqiaoUserById(long id);
	/**
	 * 新建admin
	 * @param admin
	 * @return
	 */
	public long save(QueqiaoUser entity);
	
	
	/**
	 * 更新user
	 * @param entity
	 */
	public void update(QueqiaoUser entity);
	
	/**
	 * 
	 * @param status 0-不区分， 1-正常， 2-过期
	 * @return
	 */
	public int countByStatus(int status);
	public List<QueqiaoUser> findByStatus(int status, int offset, int limit);
	
	
}
