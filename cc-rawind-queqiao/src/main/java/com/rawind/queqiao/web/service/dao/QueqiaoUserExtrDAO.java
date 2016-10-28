package com.rawind.queqiao.web.service.dao;

import java.util.Date;

import com.rawind.queqiao.web.model.QueqiaoUserExtr;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.ReturnGeneratedKeys;
import net.paoding.rose.jade.annotation.SQL;
import net.paoding.rose.jade.annotation.SQLParam;



@DAO
public interface QueqiaoUserExtrDAO {

	
	
	/**
	 * 
	 * 
	 * 
	 
CREATE TABLE `queqiao_user_extr` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `user_id` bigint(20) NOT NULL DEFAULT '0'  COMMENT '用户id',
  `user_name` varchar(35) NOT NULL DEFAULT ''  COMMENT '用户名称',
  `passwd` varchar(35) NOT NULL DEFAULT ''  COMMENT '用户密码',
  `expired_time` datetime DEFAULT NULL COMMENT '过期时间',
  `status` tinyint(1) NOT NULL DEFAULT '0'  COMMENT '1:正常，0-过期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户代理表'


	 * 
	 * 
	 * 
	 */
	
	public static final String TABLE = "queqiao_user_extr";
	
	
	public static final String FIELDS_NO_ID = "user_id, user_name, passwd, expired_time, status";
	
	public static final String FIELDS_ALL = "id, user_id, user_name, passwd, expired_time, status";
	
	
	@SQL("insert into " + TABLE + "(" + FIELDS_NO_ID + ") values (:user.userId, :user.userName, :user.passwd, :user.expiredTime, :user.status)")
	@ReturnGeneratedKeys
	public long insert(@SQLParam("user")QueqiaoUserExtr user);
	
	
	
	@SQL("select " + FIELDS_ALL + " from " + TABLE + " where id = :id")
	public QueqiaoUserExtr getById(@SQLParam("id")long id);
	
	
	@SQL("select " + FIELDS_ALL + " from " + TABLE + " where user_id = :userId")
	public QueqiaoUserExtr getByUserId(@SQLParam("userId")long userId);
	
	
	@SQL(" update " + TABLE + " set expired_time=:expiredTime  where user_id = :userId")
	public void updateExpiredTime(@SQLParam("userId")long userId, @SQLParam("expiredTime")Date expiredTime);
	
	
	
	@SQL(" update " + TABLE + " set user_name=:userName,  passwd=:userPwd where user_id = :userId")
	public void updateUserInfo(@SQLParam("userId")long userId, @SQLParam("userName")String userName, @SQLParam("userPwd")String userPwd);
	
	
	@SQL("select count(1) from " + TABLE + " where 1=1 "		
			+ "#if(:status ==1) { and expired_time >= now()} "
			+ "#if(:status ==2) { and expired_time < now()} ")
	public int queryCountByStatus(@SQLParam("status")int status);
	
	
	@SQL("select  " + FIELDS_ALL + " from " + TABLE + " where 1=1 "		
			+ "#if(:status ==1) { and expired_time >= now()} "
			+ "#if(:status ==2) { and expired_time < now()} "+" limit :offset,:limit")
	public QueqiaoUserExtr queryListByStatus(@SQLParam("status")int status, @SQLParam("offset")int offset, @SQLParam("offset")int limit);
}
