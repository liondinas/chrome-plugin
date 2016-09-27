package com.rawind.queqiao.web.service.dao;

import java.util.Date;
import java.util.List;

import org.mongodb.morphia.AdvancedDatastore;
import org.mongodb.morphia.query.Query;
import org.springframework.stereotype.Component;

import com.rawind.queqiao.mongo.MongoDataStoreFactory;
import com.rawind.queqiao.mongo.sequence.SequenceGenerater;
import com.chewen.tools.commons.util.CwDateTimeUtil;
import com.rawind.queqiao.web.model.QueqiaoUser;



@Component
public class QueqiaoUserDAO {

	
	private static String DBNAME = "queqiao";
	private static String SEQUENCE = "QueqiaoUser";
	private AdvancedDatastore  dataStrore = (AdvancedDatastore) MongoDataStoreFactory.getDatastore(DBNAME);
	
	
	public long addUser(QueqiaoUser entity){
		long id = SequenceGenerater.getInstance().getSequence(SEQUENCE);
		entity.setId(id);
		dataStrore.insert(entity);
		return entity.getId();
	}
	
	public QueqiaoUser getById(long id){
		return dataStrore.get(QueqiaoUser.class, id);
	}		
	
	
	public QueqiaoUser getByName(String userName){
		Query<QueqiaoUser> query = dataStrore.createQuery(QueqiaoUser.class);
		query.field("name").equal(userName);
		return query.get();		
	}
	
	
	public QueqiaoUser getByEmail(String email){
		Query<QueqiaoUser> query = dataStrore.createQuery(QueqiaoUser.class);
		query.field("email").equal(email);
		return query.get();		
	}
	
	
	public void update(QueqiaoUser entity){
		Query<QueqiaoUser> query = dataStrore.createQuery(QueqiaoUser.class);
		query.field("id").equal(entity.getId());
		entity.setUpTime(new Date());
		dataStrore.updateFirst(query, entity, false);
	}
	
	
	public int countByStatus(int status){
		Query<QueqiaoUser> query = dataStrore.createQuery(QueqiaoUser.class);
		if(status == 1){// 正常的
			Date cur = new Date();
			cur = CwDateTimeUtil.cleanHourAndMinutes(cur);
			query.field("expiredTime").greaterThanOrEq(cur);
		}else if(status ==2){ //过期的
			Date cur = new Date();
			cur = CwDateTimeUtil.cleanHourAndMinutes(cur);
			query.field("expiredTime").lessThan(cur);
		}
				
		return Long.valueOf(query.countAll()).intValue();		
	}
	
	public List<QueqiaoUser> findByStatus(int status, int offset, int limit){
		Query<QueqiaoUser> query = dataStrore.createQuery(QueqiaoUser.class);
		if(status == 1){// 正常的
			Date cur = new Date();
			cur = CwDateTimeUtil.cleanHourAndMinutes(cur);
			query.field("expiredTime").greaterThanOrEq(cur);
		}else if(status ==2){ //过期的
			Date cur = new Date();
			cur = CwDateTimeUtil.cleanHourAndMinutes(cur);
			query.field("expiredTime").lessThan(cur);
		}
				
		return query.asList();		
	}
	
}
