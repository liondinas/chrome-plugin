<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<!-- sidebar menu: : style can be found in sidebar.less -->
<ul class="sidebar-menu">
	<li>
		<a href="/user"> <i class="fa fa-dashboard"></i> <span>用户中心</span></a>
	</li>

	<li>
		<a href="/user/order_list"> <i class="fa fa-sitemap"></i><span>节点列表</span></a>
	</li>

	<li class="active">
		<a href="/user/my_info"> <i class="fa fa-user"></i> <span>我的信息</span></a>
	</li>

	<li>
		<a href="/user/profile_update"> <i class="fa  fa-pencil"></i> <span>修改资料</span></a>
	</li>

	<li>
		<a href="/user/invite_list"> <i class="fa fa-users"></i><span>查看邀请</span></a>
	</li>
</ul>
			
