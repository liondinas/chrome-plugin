package com.rawind.queqiao.web.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chewen.tools.commons.util.BCrypt;
import com.chewen.tools.commons.util.CwDateTimeUtil;
import com.rawind.queqiao.web.model.LoginResult;
import com.rawind.queqiao.web.model.QueqiaoUser;
import com.rawind.queqiao.web.model.QueqiaoUserExtr;
import com.rawind.queqiao.web.service.QueqiaoUserService;
import com.rawind.queqiao.web.service.dao.QueqiaoUserDAO;
import com.rawind.queqiao.web.service.dao.QueqiaoUserExtrDAO;


@Component
public class QueqiaoUserServiceImpl implements QueqiaoUserService {
	
	private static final Log logger = LogFactory.getLog(QueqiaoUserServiceImpl.class);
	
	@Autowired
	private QueqiaoUserDAO queqiaoUserDAO;
	
	@Autowired
	private QueqiaoUserExtrDAO queqiaoUserExtrDAO;
	
	public LoginResult login(String email, String passWord) {
		LoginResult result = new LoginResult();
		if(StringUtils.isBlank(email) || StringUtils.isBlank(passWord)){
			result.setResultCode(LoginResult.CODE_NOFILL);
			return result;
		}
		
		QueqiaoUser user = queqiaoUserDAO.getByEmail(email);
		if (user != null) {
			if (BCrypt.checkpw(passWord, user.getPassWord())) {
				result.setResultCode(LoginResult.CODE_SUCCESS);
				result.setQueqiaoUser(user);
				return result;
			} else {
				result.setResultCode(LoginResult.CODE_FAIL);
				return result;
			}
		} else {
			result.setResultCode(LoginResult.CODE_FAIL);
			return result;
		}
	}

	public QueqiaoUser getQueqiaoUserById(long id) {
		return queqiaoUserDAO.getById(id);
	}

	public long save(QueqiaoUser entity) {
		long userId= queqiaoUserDAO.addUser(entity);
		
		try{			
			QueqiaoUserExtr userExtr = queqiaoUserExtrDAO.getByUserId(userId);
			if(userExtr == null){
				userExtr = new QueqiaoUserExtr();
				Date expiredTime = CwDateTimeUtil.cleanMinutes(new Date());
				expiredTime = CwDateTimeUtil.lastHourAndMinutes(expiredTime);
				userExtr.setExpiredTime(expiredTime);
				userExtr.setPasswd("123456a");
				userExtr.setUserId(userId);
				userExtr.setUserName(entity.getName());
				userExtr.setStatus(QueqiaoUserExtr.STATUS_NORMAL);			
				queqiaoUserExtrDAO.insert(userExtr);
			}else{
				logger.warn("userExtr for uesrId=" + userId + " has been created");
			}
			
			
		}catch(Exception e){
			logger.error("hand userExtr error for UserId=" + userId, e.getCause());
		}
		
		return userId;				
		
	}

	public void update(QueqiaoUser entity) {
		queqiaoUserDAO.update(entity);
	}

	public int countByStatus(int status) {
		return queqiaoUserDAO.countByStatus(status);
	}

	public List<QueqiaoUser> findByStatus(int status, int offset, int limit) {
		return queqiaoUserDAO.findByStatus(status, offset, limit);
	}

}
