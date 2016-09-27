package com.rawind.queqiao.mongo.sequence;

import java.io.Serializable;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity(noClassnameStored = true)
public class Sequence implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1975456383739503486L;
	@Id
	private String id;
    private long currentIdValue;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public long getCurrentIdValue() {
		return currentIdValue;
	}
	public void setCurrentIdValue(long currentIdValue) {
		this.currentIdValue = currentIdValue;
	}
    
}
