package com.rawind.queqiao.mongo.counter;

import java.io.Serializable;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;


/**
 * 计数类
 * @author yufeng.che@gmail.com
 *
 */

@Entity(value="mg_counter", noClassnameStored = true)
public class MgCounter implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	
    private long val;
    
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getVal() {
		return val;
	}

	public void setVal(long val) {
		this.val = val;
	}

	
    
    
    
	
}
