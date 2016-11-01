package com.rawind.queqiao.web.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chewen.tools.commons.util.CwDateTimeUtil;
import com.rawind.queqiao.web.model.QueqiaoUser;
import com.rawind.queqiao.web.model.QueqiaoUserExtr;
import com.rawind.queqiao.web.service.QueqiaoUserExtrService;
import com.rawind.queqiao.web.service.dao.QueqiaoUserDAO;
import com.rawind.queqiao.web.service.dao.QueqiaoUserExtrDAO;
import com.rawind.queqiao.web.util.CollectionUtil;




@Component
public class QueqiaoUserExtrServiceImpl implements QueqiaoUserExtrService {
	
	private static final Log logger = LogFactory.getLog(QueqiaoUserExtrServiceImpl.class);
	
	@Autowired
	private QueqiaoUserExtrDAO queqiaoUserExtrDAO;
	
	@Autowired
	private QueqiaoUserDAO queqiaoUserDAO;
	
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
	public void updateExpiredTime(long userId, int addDay) {
		QueqiaoUserExtr userExtr =  getByUserId(userId);
		if(userExtr == null){
			QueqiaoUser user = queqiaoUserDAO.getById(userId);
			if(user==null){
				logger.warn("no user find for userId="+userId);				
				return;
			}
			
			userExtr = new QueqiaoUserExtr();


			Date expiredTime = CwDateTimeUtil.changeCurrentDay(addDay);
			expiredTime = CwDateTimeUtil.lastHourAndMinutes(expiredTime);
			userExtr.setExpiredTime(expiredTime);
			userExtr.setPasswd("123456a");
			userExtr.setUserId(user.getId());
			userExtr.setUserName(user.getEmail());
			userExtr.setStatus(QueqiaoUserExtr.STATUS_NORMAL);
			
			queqiaoUserExtrDAO.insert(userExtr);
			
		}else{
			Date old = userExtr.getExpiredTime();
			if(old==null){
				old = new Date();
			}else{
				Date current = new Date();
				if(old.before(current)){
					logger.warn("userId=" + userId + ", SKIP old=" + CwDateTimeUtil.formatDate(old));
					old = current;
				}
			}
			
			Date expiredTime = CwDateTimeUtil.changeDay(old, addDay);
			expiredTime = CwDateTimeUtil.lastHourAndMinutes(expiredTime);
			userExtr.setExpiredTime(expiredTime);
			queqiaoUserExtrDAO.updateExpiredTime(userId, expiredTime);
			logger.info("update userId= "+ userId +" from " + CwDateTimeUtil.formatDate(old) + " to " + CwDateTimeUtil.formatDate(expiredTime));
		}
		
	}

	@Override
	public int queryCountByStatus(int status) {
		return queqiaoUserExtrDAO.queryCountByStatus(status);
	}

	@Override
	public QueqiaoUserExtr queryListByStatus(int status, int offset, int limit) {
		return queqiaoUserExtrDAO.queryListByStatus(status, offset, limit);
	}

	@Override
	public void updateUserInfo(long userId, String userName, String userPwd) {
		queqiaoUserExtrDAO.updateUserInfo(userId, userName, userPwd);		
	}

	@Override
	public List<QueqiaoUserExtr> getByUserIds(List<Long> userIds) {
		if(CollectionUtil.isEmpty(userIds)){
			return Collections.emptyList();
		}
		
		Date cur = new Date();
		cur = CwDateTimeUtil.cleanMinutes(cur);
		
		
		List<QueqiaoUserExtr> retval = queqiaoUserExtrDAO.getByUserIds(userIds);
		
		if(CollectionUtil.isNotEmpty(retval)){
			for(QueqiaoUserExtr userExtr : retval){
				if(userExtr.getExpiredTime()!=null){
					if(userExtr.getExpiredTime().compareTo(cur)<0){
						userExtr.setStatus(QueqiaoUserExtr.STATUS_EXPIRED);
					}else{
						userExtr.setStatus(QueqiaoUserExtr.STATUS_NORMAL);
					}
					
				}else{
					userExtr.setStatus(QueqiaoUserExtr.STATUS_EXPIRED);
				}
			}
		}
		
		
		
		return retval;
	}

	@Override
	public Map<Long, QueqiaoUserExtr> getMapByUserIds(List<Long> userIds) {
		List<QueqiaoUserExtr> userList = getByUserIds(userIds);
		Map<Long, QueqiaoUserExtr> retval = new HashMap<Long, QueqiaoUserExtr>();
		if(CollectionUtil.isNotEmpty(userList)){
			for(QueqiaoUserExtr ue : userList){
				retval.put(ue.getUserId(), ue);
			}
		}
		
		
		return retval;
	}

}
