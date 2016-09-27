package com.rawind.queqiao.mongo;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;



import com.chewen.tools.commons.config.BaseConfig;
import com.chewen.tools.commons.util.CwJSONUtil;
import com.chewen.tools.commons.util.CwStringUtil;
import com.mongodb.Mongo;
import com.mongodb.MongoOptions;
import com.mongodb.ServerAddress;
import com.rawind.queqiao.mongo.exception.MongoDataStoreException;

public class MongoDataStoreFactory {
	private static Map<String,Datastore> dataStoreMap = new HashMap<String,Datastore>();
	private static Log logger = LogFactory.getLog(MongoDataStoreFactory.class);
	private static Mongo mongo;
	private static Morphia morphia;
	private MongoDataStoreFactory(){
		
	}
	static {
		 init();
	}
	
	private static void init(){
		//String mongoConfigStr = ZookeeperManager.getInstance().getDataAsString(BaseConfig.MONGO_DB_PATH_PREFIX).trim();
				
		//String mongoConfigStr = "10.3.63.12:27017;obd 10.3.63.12:27017;sequence 10.3.63.12:27017;question";
		String mongoConfigStr = "127.0.0.1:27017;queqiao 127.0.0.1:27017;sequence";
		
		if (mongoConfigStr.equals("")) {
			throw new IllegalArgumentException("No hosts in list:  ``" + mongoConfigStr + "''");
		}

		for (String hoststuff : mongoConfigStr.split(" ")) {
			int finalColon = hoststuff.lastIndexOf(':');
			if (finalColon < 2) {
				throw new IllegalArgumentException("Invalid server ``" + hoststuff + "'' in list:  " + mongoConfigStr);
			}
			String[] hosts = hoststuff.split(";");
			List<ServerAddress> serverAddressList = new ArrayList<ServerAddress>();
			String dataStore = "";
			for (String string : hosts) {
				String[] configStr = string.split(":");
				if(configStr.length>1){
					String host = configStr[0];
					String port = configStr[1];
					try {
						serverAddressList.add(new ServerAddress(host ,CwStringUtil.conver2Int(port, 27017)));
					} catch (UnknownHostException e) {
						logger.error("MongoDataStoreFactory", e);
					}
				}
				else{
					dataStore = configStr[0];
				}
			}
			try {
				MongoOptions options = getOptionsFronZooKeeper(BaseConfig.MONGO_DB_CONFIG_PATH_PREFIX);
				mongo = new Mongo(serverAddressList ,options);
			} catch (Exception e) {
				logger.error("MongoDataStoreFactory", e);
			}
			morphia = new Morphia();
			Datastore ds = morphia.createDatastore(mongo, dataStore);
        	dataStoreMap.put(dataStore, ds);
		}
	}
	
	private static MongoOptions getOptionsFronZooKeeper(String path){
		MongoOptions options = new MongoOptions();
		//String json = ZookeeperManager.getInstance().getDataAsString(path);
		String json = "	{\"connectionsPerHost\":50,\"threadsAllowedToBlockForConnectionMultiplier\":50,\"maxWaitTime\":12000,\"connectTimeout\":6000,\"socketTimeout\":200000,\"socketKeepAlive\":true,\"autoConnectRetry\":true,\"maxAutoConnectRetryTime\":12000,\"slaveOk\":false,\"safe\":false,\"w\":0,\"wtimeout\":0,\"fsync\":false,\"j\":false,\"cursorFinalizerEnabled\":true,\"alwaysUseMBeans\":false}";
		if(json == null){
			//ZooKeeper没有配置
			logger.error("not found Database options");
		}
		else{
			options = CwJSONUtil.jstr2Obj(json, MongoOptions.class);
		}
		return options;
	}
	
	
	public static Datastore getDatastore(String name) throws MongoDataStoreException {
		Datastore ds = dataStoreMap.get(name);
		if(ds == null){
			throw new MongoDataStoreException("Please make sure 'datastore name'="+name+" is Correct");
		}
		return ds;
	}
}
