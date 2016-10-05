package com.rawind.queqiao.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rawind.queqiao.web.model.QueqiaoUserExtr;
import com.rawind.queqiao.web.service.QueqiaoUserExtrService;
import com.rawind.queqiao.web.service.dao.QueqiaoUserExtrDAO;




@Component
public class QueqiaoUserExtrServiceImpl implements QueqiaoUserExtrService {
	
	
	@Autowired
	private QueqiaoUserExtrDAO queqiaoUserExtrDAO;
	
	
	
	@Override
	public long insert(QueqiaoUserExtr user) {
		return queqiaoUserExtrDAO.insert(user);
	}

	@Override
	public QueqiaoUserExtr getById(long id) {
		return queqiaoUserExtrDAO.getById(id);
	}

	@Override
	public QueqiaoUserExtr getByUserId(long userId) {
		return queqiaoUserExtrDAO.getByUserId(userId);
	}

	@Override
	public void updateStatus(long userId, int status) {
		queqiaoUserExtrDAO.updateStatus(userId, status);
	}

	@Override
	public int queryCountByStatus(int status) {
		return queqiaoUserExtrDAO.queryCountByStatus(status);
	}

	@Override
	public QueqiaoUserExtr queryListByStatus(int status, int offset, int limit) {
		return queqiaoUserExtrDAO.queryListByStatus(status, offset, limit);
	}

}
