package com.rawind.queqiao.web.service.dao;

import java.util.List;

import org.mongodb.morphia.AdvancedDatastore;
import org.mongodb.morphia.query.Query;
import org.springframework.stereotype.Component;

import com.mongodb.WriteConcern;
import com.rawind.queqiao.mongo.MongoDataStoreFactory;
import com.rawind.queqiao.mongo.sequence.SequenceGenerater;
import com.rawind.queqiao.web.model.DailySinatureKey;

@Component
public class DailySinatureKeyDAO {
	private static String DBNAME = "queqiao";
	private static String SEQUENCE = "DailySinatureKey";
	private AdvancedDatastore  dataStrore = (AdvancedDatastore) MongoDataStoreFactory.getDatastore(DBNAME);
	
	public long add(DailySinatureKey key) throws Exception{	
		try{
			long id = SequenceGenerater.getInstance().getSequence(SEQUENCE);
			key.setId(id);
			dataStrore.insert(key, WriteConcern.SAFE);			
			return key.getId();
		}catch(Exception e){
			throw e;
		}
	}
	
	public List<DailySinatureKey> getSignatureKey(String date){ 
		Query<DailySinatureKey> query = dataStrore.createQuery(DailySinatureKey.class);
		query.field("createTime").equal(date);
		return query.asList();
	}
}
