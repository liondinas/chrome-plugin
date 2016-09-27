package com.rawind.queqiao.mongo.counter;

import org.mongodb.morphia.AdvancedDatastore;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import com.rawind.queqiao.mongo.MongoDataStoreFactory;



class MgCounterDAO {
	
	
	private static final String dbname = "sequence";
	
	public void createMgCounter(String key, long val) {
		AdvancedDatastore ds = (AdvancedDatastore) MongoDataStoreFactory.getDatastore(dbname);
		MgCounter mgCOunter = new MgCounter();
		mgCOunter.setId(key);
		mgCOunter.setVal(val);
		ds.insert(mgCOunter);
    }

    public MgCounter getMgCounter(String key) {
    	Datastore ds = MongoDataStoreFactory.getDatastore(dbname);
    	return ds.find(MgCounter.class, "_id", key).get();
    }
    
    
    
    public long getMgCounterValue(String key, long defaultVal) {
    	MgCounter counter = getMgCounter(key);
    	if(counter==null){
    		return defaultVal;
    	}
        return counter.getVal();
    }
    
    
    
    public void updateMgCounter(String key, long val, boolean no2Create){
    	Datastore ds = MongoDataStoreFactory.getDatastore(dbname);
    	MgCounter counter= ds.find(MgCounter.class, "_id", key).get();
    	
        if(counter == null && no2Create){
        	createMgCounter(key,val);        
        }else{
        	 Query<MgCounter> q = ds.createQuery(MgCounter.class).field("_id").equal(key);
             UpdateOperations<MgCounter> ops = ds.createUpdateOperations(MgCounter.class).set("val", val);
             ds.findAndModify(q, ops,true);
        }
    }
	
}
