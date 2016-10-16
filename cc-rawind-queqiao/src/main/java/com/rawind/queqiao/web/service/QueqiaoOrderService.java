package com.rawind.queqiao.web.service;


import java.util.List;


import com.rawind.queqiao.web.model.QueqiaoOrder;

public interface QueqiaoOrderService {

	
	long insert(QueqiaoOrder order);
	
	
	int countByUser(long userId);
	
	List<QueqiaoOrder> listByUser(long userId, int offset, int limit);
	
	
	boolean finishOrder(String tradeNo, String aliTradeNo, int amout);
	
	
	QueqiaoOrder getById(long orderId);
	
	
	QueqiaoOrder getByTradeNO(String tradeNo);
	
}
