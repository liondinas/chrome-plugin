package com.rawind.queqiao.web.service.dao;

import java.util.Date;
import java.util.List;

import com.rawind.queqiao.web.model.QueqiaoOrder;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.ReturnGeneratedKeys;
import net.paoding.rose.jade.annotation.SQL;
import net.paoding.rose.jade.annotation.SQLParam;



@DAO
public interface QueqiaoOrderDAO {

	
	/**
	 * 
CREATE TABLE `queqiao_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `user_id` bigint(20) NOT NULL DEFAULT '0'  COMMENT '用户id',
  `user_name` varchar(35) NOT NULL DEFAULT ''  COMMENT '用户名称',
  `amount` int(8) NOT NULL DEFAULT '0'  COMMENT '金额-单位分', 
  `type` int(8) NOT NULL DEFAULT '0'  COMMENT '类型', 
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `pay_time` datetime DEFAULT NULL COMMENT '过期时间',
  `status` tinyint(2) NOT NULL DEFAULT '0'  COMMENT '0:初始化，11-付款， 12-生效',
  `memo` varchar(64) DEFAULT NULL  COMMENT '订单备注',
  `queqiao_trade_no` varchar(64) DEFAULT NULL  COMMENT '鹊桥订单号',
  `trade_no` varchar(64) DEFAULT NULL  COMMENT '支付宝订单号',
  `version` int(8) NOT NULL DEFAULT '0'  COMMENT '乐观锁', 
  `fee` int(8) NOT NULL DEFAULT '0'  COMMENT '手续费', 
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户订单表'

	 * 
	 */
	
	
	public static final String TABLE = " queqiao_order ";
	
	
	public static final String FIELDS_NO_ID = "user_id, user_name, amount, type, create_time, pay_time, status, memo, queqiao_trade_no, trade_no, version";
	
	public static final String FIELDS_ALL = "id, " + FIELDS_NO_ID;
	
	@SQL("insert into " + TABLE + "(" + FIELDS_NO_ID + ") values (:order.userId, :order.userName, :order.amount, :order.type, :order.createTime, :order.payTime, "
			+ ":order.status, :order.memo, :order.queqiaoTradeNo, :order.tradeNo, :order.version)")
	@ReturnGeneratedKeys
	public long insert(@SQLParam("order")QueqiaoOrder order);
	
	
	@SQL("select count(1) from " + TABLE + " where user_id =:userId")
	public int countByUser(@SQLParam("userId")long userId);
	
	@SQL("select " + FIELDS_ALL + " from " + TABLE + " where user_id =:userId limit :offset,:limit")
	public List<QueqiaoOrder> listByUser(@SQLParam("userId")long userId, @SQLParam("offset")int offset, @SQLParam("limit")int limit);
	
	
	@SQL("update " + TABLE + " set pay_time =:payTime, status=:status, queqiao_trade_no=:queqiaoTradeNo, trade_no =:tradeNo  where id =:orderId and version=:version")
	public int updateByStatus(@SQLParam("orderId")long orderId, @SQLParam("payTime")Date payTime, 
			@SQLParam("status")int status, @SQLParam("version")int version,
			@SQLParam("queqiaoTradeNo")String queqiaoTradeNo, @SQLParam("tradeNo")String tradeNo);
		
	
	@SQL("select " + FIELDS_ALL + " from " + TABLE + " where id =:orderId")
	public QueqiaoOrder getById(@SQLParam("orderId")long orderId);
	
	
	@SQL("select " + FIELDS_ALL + " from " + TABLE + " where queqiao_trade_no =:tradeNo")
	public QueqiaoOrder getByTradeNo(@SQLParam("tradeNo")String tradeNo);
	
	
	@SQL("select count(1) from " + TABLE )
	public int countAll();
	
	
	@SQL("select " + FIELDS_ALL + " from " + TABLE + " order by id desc limit :offset,:limit")
	public List<QueqiaoOrder> listAll(@SQLParam("offset")int offset, @SQLParam("limit")int limit);
	
	@SQL("update " + TABLE + " set amount =:amount where id =:orderId and version=:version and status="+QueqiaoOrder.STATUS_CREATED)
	public int updateOrderPrice(@SQLParam("orderId")long orderId, @SQLParam("amount")int amount, @SQLParam("version")int version);
}
