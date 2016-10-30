<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<a href="${SITE_DOMAIN}/admin/user/list" class="logo"> <!-- Add the class icon to your logo image or logo icon to add the margining -->
			小橙子-后台管理平台
</a>
<!-- Header Navbar: style can be found in header.less -->
<nav class="navbar navbar-static-top" role="navigation">
	<!-- Sidebar toggle button-->
	<a href="#" class="navbar-btn sidebar-toggle" data-toggle="offcanvas"
		role="button"> <span class="sr-only">Toggle navigation</span> <span
		class="icon-bar"></span> <span class="icon-bar"></span> <span
		class="icon-bar"></span>
	</a>
	<div class="navbar-right">
		<ul class="nav navbar-nav">
			<!-- User Account: style can be found in dropdown.less -->
			<li class="dropdown user user-menu"><a href="#"
				class="dropdown-toggle" data-toggle="dropdown"> <i
					class="glyphicon glyphicon-user"></i> <span>${user.name} <i
						class="caret"></i></span>
			</a>
				<ul class="dropdown-menu">
					<!-- User image -->
					<li class="user-header bg-light-blue"><img
						src="${SITE_DOMAIN}/static/img/touxiang.jpeg" class="img-circle"
						alt="User Image" />
						<p>liondinas</p></li>

					<!-- Menu Footer-->
					<li class="user-footer">
						<div class="pull-left">
							<a href="${SITE_DOMAIN}/admin/user/my_info"
								class="btn btn-default btn-flat">我的信息</a>
						</div>
						<div class="pull-right">
							<a href="${SITE_DOMAIN}/admin/logout"
								class="btn btn-default btn-flat">退出</a>
						</div>
					</li>
				</ul></li>
		</ul>
	</div>
</nav>
			
