package com.rawind.queqiao.mongo.sequence;

import org.mongodb.morphia.AdvancedDatastore;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import com.rawind.queqiao.mongo.MongoDataStoreFactory;



public class SequenceDao {
	private static final String dbname = "sequence";
	private void createSequence(String sequenceName, long StartValue) {
		AdvancedDatastore ds = (AdvancedDatastore) MongoDataStoreFactory.getDatastore(dbname);
		Sequence sequence = new Sequence();
		sequence.setId(sequenceName);
		sequence.setCurrentIdValue(StartValue);
		ds.insert(sequence);
    }

    public long getSequenceID(String id) {
    	Datastore ds = MongoDataStoreFactory.getDatastore(dbname);
    	Sequence object = ds.find(Sequence.class, "_id", id).get();
        if(object==null){
        	createSequence(id,1l);
        }
        Query<Sequence> q = ds.createQuery(Sequence.class).field("_id").equal(id);
        UpdateOperations<Sequence> ops = ds.createUpdateOperations(Sequence.class).inc("currentIdValue", 1);
        return ds.findAndModify(q, ops,true).getCurrentIdValue();
    }
    public long getSequenceIDOnly(String id) {
    	Datastore ds = MongoDataStoreFactory.getDatastore(dbname);
    	Sequence object = ds.find(Sequence.class, "_id", id).get();
        if(object==null){
        	return 0L;
        }
        return object.getCurrentIdValue();
    }
    
    public static void main(String[] args) {
    	SequenceDao dao  = new SequenceDao();
    	dao.createSequence("test", 3);
    }
}
