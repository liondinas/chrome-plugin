package com.rawind.queqiao.web.controllers.user;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.rawind.queqiao.web.model.OrderTypeEnum;
import com.rawind.queqiao.web.model.QueqiaoOrder;
import com.rawind.queqiao.web.model.QueqiaoUser;
import com.rawind.queqiao.web.service.HostHolderService;
import com.rawind.queqiao.web.service.QueqiaoOrderService;
import com.rawind.queqiao.web.service.QueqiaoUserService;
import com.rawind.queqiao.web.util.TradeNoGenerator;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;



@Path("")
public class UserPayController {
	
	
	
	@Autowired
	private QueqiaoUserService queqiaoUserService;
	
	
	@Autowired
	private HostHolderService hostHolderService;
	
	@Autowired
	private QueqiaoOrderService queqiaoOrderService;
	
	
	
	
	@Get("user_pay")
	public String goPay(Invocation inv, @Param("type") int type){
		
		if(!hostHolderService.isUserLogin()){
			return "@请登录";
		}
		
		OrderTypeEnum orderType = OrderTypeEnum.getByCode(type);
		if(orderType == null){
			return "@参数错误";
		}
		
		QueqiaoUser user = hostHolderService.getQueqiaoUser();
				
		inv.addModel("user", user);
		
		
		QueqiaoOrder order = new QueqiaoOrder();
		order.setAmount(orderType.getAmount());
		order.setCreateTime(new Date());
		order.setFee(0);
		order.setMemo(orderType.getMemo());
		//order.setQueqiaoTradeNo("");
		order.setStatus(QueqiaoOrder.STATUS_CREATED);
		order.setType(orderType.getCode());
		order.setUserId(user.getId());
		order.setUserName(user.getName());
		order.setAmount(orderType.getAmount());
		order.setVersion(0);
		order.setQueqiaoTradeNo(TradeNoGenerator.generate(orderType.getCode()));
		long orderId = queqiaoOrderService.insert(order);
		
		
		
		inv.addModel("orderQueqiao", order);
		inv.addModel("orderType", orderType);
		
		return "user_pay";
	}
	
	
	
	
}
