package com.rawind.queqiao.web.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chewen.tools.commons.util.BCrypt;
import com.rawind.queqiao.web.model.LoginResult;
import com.rawind.queqiao.web.model.QueqiaoUser;
import com.rawind.queqiao.web.service.QueqiaoUserService;
import com.rawind.queqiao.web.service.dao.QueqiaoUserDAO;


@Component
public class QueqiaoUserServiceImpl implements QueqiaoUserService {
	
	
	@Autowired
	private QueqiaoUserDAO queqiaoUserDAO;
	
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
		return queqiaoUserDAO.addUser(entity);
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
