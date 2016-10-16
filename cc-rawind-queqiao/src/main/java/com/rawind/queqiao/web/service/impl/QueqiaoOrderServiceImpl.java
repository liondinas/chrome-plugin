package com.rawind.queqiao.web.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rawind.queqiao.web.model.OrderTypeEnum;
import com.rawind.queqiao.web.model.QueqiaoOrder;
import com.rawind.queqiao.web.service.QueqiaoOrderService;
import com.rawind.queqiao.web.service.dao.QueqiaoOrderDAO;
import com.rawind.queqiao.web.service.dao.QueqiaoUserDAO;
import com.rawind.queqiao.web.service.dao.QueqiaoUserExtrDAO;
import com.rawind.queqiao.web.util.TradeNoGenerator;


@Component
public class QueqiaoOrderServiceImpl implements QueqiaoOrderService {

	
	private static final Log logger = LogFactory.getLog(QueqiaoOrderServiceImpl.class);
	
	@Autowired
	private QueqiaoUserExtrDAO queqiaoUserExtrDAO;
	
	@Autowired
	private QueqiaoUserDAO queqiaoUserDAO;
		
	@Autowired
	private QueqiaoOrderDAO queqiaoOrderDAO;
	
	@Autowired
	private QueqiaoUserExtrServiceImpl queqiaoUserExtrServiceImpl; 
	
	@Override
	public long insert(QueqiaoOrder order) {
		return queqiaoOrderDAO.insert(order);
	}

	@Override
	public int countByUser(long userId) {
		return queqiaoOrderDAO.countByUser(userId);
	}

	@Override
	public List<QueqiaoOrder> listByUser(long userId, int offset, int limit) {
		return queqiaoOrderDAO.listByUser(userId, offset, limit);
	}

	@Override
	public boolean finishOrder(String queqiaoTradeNo, String tradeNo, int amout) {
		logger.info("begin to hand orderId="+queqiaoTradeNo);
		long orderId = TradeNoGenerator.getOrderIdFromTradeNo(queqiaoTradeNo);
		boolean retval = false;
		try{
			QueqiaoOrder order = queqiaoOrderDAO.getById(orderId);
			if(order==null){
				logger.info("orderId="+orderId + " is null ");
			}else{				
				if(order.getStatus() == QueqiaoOrder.STATUS_CREATED){
					
					
					OrderTypeEnum type = OrderTypeEnum.getByCode(order.getType());
					if(type ==null){
						logger.info("orderId="+orderId + " type="+order.getType() + " type is null ");
					}else{
						
						int result = queqiaoOrderDAO.updateByStatus(orderId, new Date(), QueqiaoOrder.STATUS_PAYED, order.getVersion(), queqiaoTradeNo, tradeNo);
											
						if(result ==1){
							queqiaoUserExtrServiceImpl.updateExpiredTime(order.getUserId(), type.getDays());
							retval = true;
						}else{
							logger.info("orderId="+orderId + " type="+order.getType() + " queqiaoOrderDAO updateStatus result= " + result);
						}
						
					}													
					
					
				}else{
					logger.info("orderId="+orderId + " has been handled ");
				}
				
				
			}
			
			
		}catch(Exception e){
			
		}finally{
			logger.info("hand orderId="+orderId + " result="+retval);
		}
		
		return retval;
		
		
	}

	@Override
	public QueqiaoOrder getById(long orderId) {
		return queqiaoOrderDAO.getById(orderId);
	}

	@Override
	public QueqiaoOrder getByTradeNO(String tradeNo) {
		long orderId = TradeNoGenerator.getOrderIdFromTradeNo(tradeNo);
		if(orderId>0){
			return queqiaoOrderDAO.getById(orderId);
		}
		return null;
	}

}
