package com.rawind.queqiao.web.controllers.admin;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.chewen.tools.commons.util.AjaxOutput;
import com.rawind.queqiao.web.model.QueqiaoOrder;
import com.rawind.queqiao.web.model.QueqiaoUser;
import com.rawind.queqiao.web.service.HostHolderService;
import com.rawind.queqiao.web.service.PassportService;
import com.rawind.queqiao.web.service.QueqiaoOrderService;
import com.rawind.queqiao.web.service.QueqiaoProxyService;
import com.rawind.queqiao.web.service.QueqiaoUserExtrService;
import com.rawind.queqiao.web.service.QueqiaoUserService;
import com.rawind.queqiao.web.util.PageInfo;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.DefValue;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;
import net.paoding.rose.web.annotation.rest.Post;



@Path("/order")
public class OrderMgrController {

	
	private static final Log logger = LogFactory.getLog(OrderMgrController.class);
	
	@Autowired
	private QueqiaoUserService queqiaoUserService;
	
	@Autowired
	private PassportService passportService;
	
	@Autowired
	private QueqiaoUserExtrService queqiaoUserExtrService;
	
	@Autowired
	private HostHolderService hostHolderService;
	
	@Autowired
	private QueqiaoProxyService queqiaoProxyService;
	
	@Autowired
	private QueqiaoOrderService queqiaoOrderService;
	
	
	@Get("list")
	public String showOrderList(Invocation inv, @Param("pageNo") int pageNo, @Param("limit") @DefValue("20") int limit,
			@Param("userId") long userId, 
			@Param("orderId") long orderId) {
		
		if(userId>0){
			int count = queqiaoOrderService.countByUser(userId);
			PageInfo<QueqiaoOrder> pageInfo = new PageInfo<QueqiaoOrder>(count, pageNo, limit);
			List<QueqiaoOrder>	orderList = queqiaoOrderService.listByUser(userId, pageInfo.getOffset(), pageInfo.getLimit());
			pageInfo.setDataList(orderList);
			inv.addModel("pageInfo", pageInfo);
			
		}else if(orderId>0){
			int count = 0;
			List<QueqiaoOrder>	orderList = new ArrayList<>();
			QueqiaoOrder qr = queqiaoOrderService.getById(orderId);
			if(qr!=null){
				orderList.add(qr);
				count = 1;
			}
			PageInfo<QueqiaoOrder> pageInfo = new PageInfo<QueqiaoOrder>(count, pageNo, limit);
			pageInfo.setDataList(orderList);			
			inv.addModel("pageInfo", pageInfo);
			
		}else{
			int count = queqiaoOrderService.countAll();
			PageInfo<QueqiaoOrder> pageInfo = new PageInfo<QueqiaoOrder>(count, pageNo, limit);
			List<QueqiaoOrder>	orderList = queqiaoOrderService.listAll(pageInfo.getOffset(), pageInfo.getLimit());
			pageInfo.setDataList(orderList);
			inv.addModel("pageInfo", pageInfo);
		}
		
		inv.addModel("url", "list");
		return "admin_order_list";
	}
	
	
	
	@Get("detail")
	public String showOrderDetail(Invocation inv, 
			@Param("orderId") long orderId) {
	
		QueqiaoOrder qr = queqiaoOrderService.getById(orderId);
		if(qr==null){
			return "@订单不存在";
		}
		
		
		inv.addModel("order", qr);
		
		return "admin_order_edit";
	}
	
	
	@Post("updateprice")
	public String updateOrderPrice(Invocation inv, 
			@Param("orderId") long orderId, @Param("amount") int amount) {
		QueqiaoUser admin = hostHolderService.getQueqiaoAdmin();
		if(admin==null){
			return AjaxOutput.failure("请重新登陆");
		}
				
		if(!admin.isAdmin()){
			return AjaxOutput.failure("非法用户");
		}
		
		QueqiaoOrder qr = queqiaoOrderService.getById(orderId);
		if(qr==null){
			return AjaxOutput.failure("订单不存在");
		}
		
		qr.setAmount(amount);
		
		try{
			int result = queqiaoOrderService.updateOrderPrice(orderId, amount, qr.getVersion());
			logger.info("update orderPrice error orderId=" + orderId + ", amount=" + amount + ", result=" + result);
			if(result == 1){
				return AjaxOutput.success();
			}
		}catch(Exception e){
			logger.error("update orderPrice error orderId=" + orderId + ", amount=" + amount, e.getCause());
		}
		
		
		return AjaxOutput.failure("未知错误");
	}
	
	
	
}
