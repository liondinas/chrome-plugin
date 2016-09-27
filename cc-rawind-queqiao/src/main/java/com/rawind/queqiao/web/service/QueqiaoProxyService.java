package com.rawind.queqiao.web.service;

import java.util.List;

import com.rawind.queqiao.web.model.QueqiaoProxy;

public interface QueqiaoProxyService {

	/**
	 * 增加代理
	 * @param entity
	 * @return
	 */
	long addProxy(QueqiaoProxy entity);
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	QueqiaoProxy getById(long id);
	
	
	/**
	 * 根据类型查询
	 * @param type
	 * @return
	 */
	int countByType(int type);
	List<QueqiaoProxy> getByType(int type, int offset, int limit);
	
	/**
	 * 更新
	 * @param entity
	 */
	void update(QueqiaoProxy entity);
	
	
	
}
