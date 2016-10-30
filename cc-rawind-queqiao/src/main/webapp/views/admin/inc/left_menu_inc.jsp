<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!-- Sidebar user panel -->
<div class="user-panel">
	<div class="pull-left image">
		<img src="${SITE_DOMAIN}/static/img/touxiang.jpeg"
			class="img-circle" alt="User Image" />
	</div>
	<div class="pull-left info">
		<p>欢迎, ${user.name}</p>
		<a href="#"><i class="fa fa-circle text-success"></i> 在线</a>
	</div>
</div>

<!-- sidebar menu: : style can be found in sidebar.less -->
<ul class="sidebar-menu">
	<li <c:if test="${menu == 1}"> class="active" </c:if> >
		<a href="/admin/user/list"> <i class="fa fa-dashboard"></i> <span>用户列表</span></a>
	</li>

	<li  <c:if test="${menu == 2}"> class="active" </c:if>  >
		<a href="/admin/node/list"> <i class="fa fa-sitemap"></i><span>节点列表</span></a>
	</li>

	<li <c:if test="${menu == 3}"> class="active" </c:if>  >
		<a href="/admin/user/my_info"> <i class="fa fa-user"></i> <span>我的信息</span></a>
	</li>

	<li <c:if test="${menu == 4}"> class="active" </c:if>  >
		<a href="/admin/user/profile_update"> <i class="fa  fa-pencil"></i> <span>修改资料</span></a>
	</li>

	<li <c:if test="${menu == 5}"> class="active" </c:if>  >
		<a href="/admin/order/list"> <i class="fa fa-users"></i> <span>订单列表</span></a>
	</li>

</ul>
			
