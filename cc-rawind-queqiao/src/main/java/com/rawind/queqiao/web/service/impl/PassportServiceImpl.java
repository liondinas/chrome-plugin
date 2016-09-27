package com.rawind.queqiao.web.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chewen.tools.commons.util.BCrypt;
import com.rawind.queqiao.web.Constants;
import com.rawind.queqiao.web.model.DailySinatureKey;
import com.rawind.queqiao.web.model.PassportTicket;
import com.rawind.queqiao.web.model.PassportVerifyResult;
import com.rawind.queqiao.web.model.QueqiaoUser;
import com.rawind.queqiao.web.service.PassportService;
import com.rawind.queqiao.web.service.dao.DailySinatureKeyDAO;
import com.rawind.queqiao.web.service.dao.QueqiaoUserDAO;

@Component
public class PassportServiceImpl implements PassportService {
	private static Log logger = LogFactory.getLog(PassportServiceImpl.class);
	@Autowired
	private DailySinatureKeyDAO dailySinatureKeyDAO;
	@Autowired
	private QueqiaoUserDAO queqiaoUserDAO;
	
	private Map<String, String> keysMap = new ConcurrentHashMap<String, String>();
	private final static Object locked1 = new Object();
	private final static Object locked2 = new Object();
	private final long PASSPROT_LIFE_TIME = 52*604800L;
	private final long PASSPROT_LIFE_TIME_OBD_CAR = 52*604800L;
	
	public String genPsssport(long userId, String userAgent) {
		long currentTime = System.currentTimeMillis() / 1000;
		long expiredTime = currentTime + PASSPROT_LIFE_TIME;
		PassportTicket ticket = new PassportTicket(userId, userAgent,PASSPROT_LIFE_TIME, expiredTime);

		String signatureKey = getOrCreateKey(1000 * currentTime);
		ticket.setSignature(BCrypt.hashpw(ticket.getContent()+signatureKey,BCrypt.gensalt(Constants.LOG_ROUNDS)));

		return "" + ticket.getUserId() + ":" + ticket.getLifeTime() + ":" + ticket.getExpiredTime() + ":" + ticket.getSignature();
	}

	
	public PassportVerifyResult verifyPassport(String passport, String userAgent) {
		long currentTime = System.currentTimeMillis() / 1000;

		if (passport == null || passport.equals("")) {
			return notExistResult();
		}

		PassportTicket ticket = getTicket(passport, userAgent);
		
		QueqiaoUser user = queqiaoUserDAO.getById(ticket.getUserId());
		if(user == null){
			return ExpiredResult(ticket.getUserId());
		}
		
		if(ticket.getExpiredTime() < currentTime){
			return ExpiredResult(ticket.getUserId());
		}
		
		if(!user.getPassport().equalsIgnoreCase(passport)){
			return ExpiredResult(ticket.getUserId());
		}
		
		String signatureKey = getKey(1000 * (ticket.getExpiredTime() - ticket.getLifeTime()));
		if (BCrypt.checkpw(ticket.getContent()+signatureKey, ticket.getSignature())) {
			return okResult(ticket.getUserId());
		} else {
			return invalidResult(ticket.getUserId());
		}
	}

	
	public String obtainAdminId(String passport) {
		if(passport==null||passport.trim().length()==0){
			return null;
		}
		String[] datas = passport.split(":");
		if (datas == null || datas.length != 4) {
			return null;
		}
		return datas[0];
	}
	
	private String getOrCreateKey(long time) {
		String key = getKey(time);
        if(key==null){
        	synchronized (locked1) {
        		key = getKey(time);
				if (key == null) {
					key = genUUID();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
					Date d = new Date(time);
			        String mapKey = sdf.format(d);
					DailySinatureKey dsk = new DailySinatureKey();
					dsk.setCreateTime(mapKey);
					dsk.setSinatureKey(key);
					try {
						//dailySinatureKeyDAO.add(dsk);
					} catch (Exception e) {
						key = getKey(time);
						logger.error("getOrCreateKey", e);
					}
					keysMap.put(mapKey, key);
				}
        	}
        }
        return key;
	}
	
	private String getKey(long time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		Date d = new Date(time);
        String mapKey = sdf.format(d);
        String key = keysMap.get(mapKey);
        if(key==null){
        	synchronized (locked2) {
        		key = keysMap.get(mapKey);
        		if(key==null){
        			List<DailySinatureKey> list = dailySinatureKeyDAO.getSignatureKey(mapKey);
        			if(list!=null&&list.size()>0){
        				DailySinatureKey dsk = list.get(0);
        				key =  dsk.getSinatureKey();
        				keysMap.put(mapKey,key);
        			}
        		}
        	}
        }
        return key;
	}
	
	private String genUUID(){
    	return UUID.randomUUID().toString().replaceAll("-", "");
    }
	
	private PassportVerifyResult notExistResult() {
		PassportVerifyResult result = new PassportVerifyResult();
		result.setValidationCode(PassportVerifyResult.NOT_EXIST);
		return result;
	}

	private PassportVerifyResult ExpiredResult(long l) {
		PassportVerifyResult result = new PassportVerifyResult();
		result.setUserId(l);
		result.setValidationCode(PassportVerifyResult.EXPIRED);
		return result;
	}

	private PassportVerifyResult okResult(long userId) {
		PassportVerifyResult result = new PassportVerifyResult();
		result.setUserId(userId);
		result.setValidationCode(PassportVerifyResult.OK);
		return result;
	}

	private PassportVerifyResult invalidResult(long l) {
		PassportVerifyResult result = new PassportVerifyResult();
		result.setUserId(l);
		result.setValidationCode(PassportVerifyResult.INVALID);
		return result;
	}

	private PassportTicket getTicket(String passport, String userAgent) {
		String[] datas = passport.split(":");
		if (datas == null || datas.length != 4) {
			return new PassportTicket(0, null, 0, 0);
		}
		int userId = Integer.parseInt(datas[0]);
		long lifeTime = Long.parseLong(datas[1]);
		long expiredTime = Long.parseLong(datas[2]);
		String signature = datas[3];

		PassportTicket ticket = new PassportTicket(userId, userAgent, lifeTime,expiredTime);
		ticket.setSignature(signature);
		return ticket;
	}

	

}
