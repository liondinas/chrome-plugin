package com.rawind.queqiao.web.service.dao;

import java.util.Date;
import java.util.List;

import org.mongodb.morphia.AdvancedDatastore;
import org.mongodb.morphia.query.Query;
import org.springframework.stereotype.Component;

import com.rawind.queqiao.mongo.MongoDataStoreFactory;
import com.rawind.queqiao.mongo.sequence.SequenceGenerater;
import com.rawind.queqiao.web.model.QueqiaoProxy;



@Component
public class QueqiaoProxyDAO {

	private static String DBNAME = "queqiao";
	private static String SEQUENCE = "QueqiaoProxy";
	private AdvancedDatastore  dataStrore = (AdvancedDatastore) MongoDataStoreFactory.getDatastore(DBNAME);
	
	
	
	public long addProxy(QueqiaoProxy entity){
		long id = SequenceGenerater.getInstance().getSequence(SEQUENCE);
		entity.setId(id);
		dataStrore.insert(entity);
		return entity.getId();
	}
	
	public QueqiaoProxy getById(long id){
		return dataStrore.get(QueqiaoProxy.class, id);
	}	
	
	
	public int countByType(int type){
		Query<QueqiaoProxy> query = dataStrore.createQuery(QueqiaoProxy.class);
		if(type>0){
			query.filter("type", type);
		}
		
		return Long.valueOf(query.countAll()).intValue();
	}
	
	
	public List<QueqiaoProxy> getByType(int type, int offset, int limit){
		Query<QueqiaoProxy> query = dataStrore.createQuery(QueqiaoProxy.class);
		if(type>0){
			query.filter("type", type);
		}
		
		query.offset(offset);
		query.limit(limit);
		return query.asList();
		
	}
	
	
	public void update(QueqiaoProxy entity){
		Query<QueqiaoProxy> query = dataStrore.createQuery(QueqiaoProxy.class);
		query.field("id").equal(entity.getId());	
		dataStrore.updateFirst(query, entity, false);		
	}
	
	
	
	
	
}
