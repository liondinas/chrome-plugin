package com.rawind.queqiao.mongo.counter;


/**
 * mongo 计数工具类
 * @author yufeng.che@gmail.com
 *
 */
public class MgCounterUtil {

	private static MgCounterDAO mgCounterDAO;
	
	static {
		mgCounterDAO = new MgCounterDAO();
	}
	
	
	
	/**
	 * 设置 key 的数值 <p>
	 * 没有时会新建key
	 * @param key
	 * @param val
	 */
	public static void setCounter(String key, long val){
		mgCounterDAO.updateMgCounter(key, val, true);
	}
	
	
	/**
	 * 得到 key 的数值
	 * @param key
	 * @return
	 */
	public static long getCounter(String key){
		return mgCounterDAO.getMgCounterValue(key, -1);
	}
	
	
	/**
	 * 更改key的数值（没有key时按照新建处理）
	 * @param key
	 * @param step 可以是正数， 也可以是负数
	 */
	public static void incCounter(String key, long step){
		long oldVal = mgCounterDAO.getMgCounterValue(key, 0);
		mgCounterDAO.updateMgCounter(key, oldVal + step , true);
	}
}
