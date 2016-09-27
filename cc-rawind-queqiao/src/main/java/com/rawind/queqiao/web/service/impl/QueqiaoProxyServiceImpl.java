package com.rawind.queqiao.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rawind.queqiao.web.model.QueqiaoProxy;
import com.rawind.queqiao.web.service.QueqiaoProxyService;
import com.rawind.queqiao.web.service.dao.QueqiaoProxyDAO;


@Component
public class QueqiaoProxyServiceImpl implements QueqiaoProxyService{
	
	@Autowired
	private QueqiaoProxyDAO queqiaoProxyDAO;

	@Override
	public long addProxy(QueqiaoProxy entity) {
		return queqiaoProxyDAO.addProxy(entity);
	}

	@Override
	public QueqiaoProxy getById(long id) {
		return queqiaoProxyDAO.getById(id);
	}

	@Override
	public int countByType(int type) {
		return queqiaoProxyDAO.countByType(type);
	}

	@Override
	public List<QueqiaoProxy> getByType(int type, int offset, int limit) {
		return queqiaoProxyDAO.getByType(type, offset, limit);
	}

	@Override
	public void update(QueqiaoProxy entity) {
		queqiaoProxyDAO.update(entity);
	}

}
