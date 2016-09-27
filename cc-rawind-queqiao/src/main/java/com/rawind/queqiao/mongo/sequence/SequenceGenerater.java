package com.rawind.queqiao.mongo.sequence;


public class SequenceGenerater {
	private static final Object _lock = new Object();
	private static SequenceGenerater generate = null ;
	private static SequenceDao sequenceDao;
	private SequenceGenerater(){
		sequenceDao = new SequenceDao();
	}
	public static SequenceGenerater getInstance(){
		if(generate == null){
			synchronized(_lock){
				if(generate == null){
					SequenceGenerater g = new SequenceGenerater() ;
					generate = g ;
				}
			}
		}
		return generate ;
	}
	
	public long getSequence(String sequenceName){
		return sequenceDao.getSequenceID(sequenceName);
	}
	
	public long getSequenceOnly(String sequenceName){
		return sequenceDao.getSequenceIDOnly(sequenceName);
	}
	
	public static void main(String[] args) {
		SequenceGenerater sg = SequenceGenerater.getInstance();
		System.out.println(sg.getSequence("test"));
	}
}
