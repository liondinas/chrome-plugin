<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<title>${SITE_NAME}-用户中心</title>
<meta charset="UTF-8">
<meta
	content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no'
	name='viewport'>
<link href="${SITE_DOMAIN}/static/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<link href="${SITE_DOMAIN}/static/css/font-awesome.min.css"
	rel="stylesheet" type="text/css" />
<!-- Ionicons -->
<link href="${SITE_DOMAIN}/static/css/ionicons.min.css" rel="stylesheet"
	type="text/css" />
<!-- Morris chart -->
<link href="${SITE_DOMAIN}/static/css/morris/morris.css"
	rel="stylesheet" type="text/css" />

<!-- Theme style -->
<link href="${SITE_DOMAIN}/static/css/AdminLTE.css" rel="stylesheet"
	type="text/css" />

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
</head>
<body class="skin-blue">
	<!-- header logo: style can be found in header.less -->
	<header class="header">
		<a href="${SITE_DOMAIN}/user" class="logo"> <!-- Add the class icon to your logo image or logo icon to add the margining -->
			Xiaochengzi SS
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
	</header>
	<div class="wrapper row-offcanvas row-offcanvas-left">
		<!-- Left side column. contains the logo and sidebar -->
		<aside class="left-side sidebar-offcanvas">
			<!-- sidebar: style can be found in sidebar.less -->
			<section class="sidebar">
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
					<li><a href="/admin/user/list"> <i class="fa fa-dashboard"></i> <span>用户列表</span>
					</a></li>

					<li  class="active"><a href="/admin/node/list"> <i class="fa fa-sitemap"></i>
							<span>节点列表</span>
					</a></li>

					<li><a href="/admin/user/my_info"> <i class="fa fa-user"></i> <span>我的信息</span>
					</a></li>

					<li><a href="/user/profile_update"> <i
							class="fa  fa-pencil"></i> <span>修改资料</span>
					</a></li>

					<li><a href="/user/invite_list"> <i
							class="fa fa-users"></i> <span>查看邀请</span>
					</a></li>

				</ul>
			</section>
			<!-- /.sidebar -->
		</aside>
		<!-- Right side column. Contains the navbar and content of the page -->
		<aside class="right-side">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>
					用户 <small>User</small>
				</h1>
			</section>
			<!-- Main content -->
			<section class="content">
				<div class="row">
					<!-- left column -->
					<div class="col-md-6">
						<!-- general form elements -->
						<div class="box box-primary">
							<div class="box-header">
								<h3 class="box-title">用户修改</h3>								
							</div>

							<form  name="editForm" id="editForm" method="post"
								action="/admin/user/editpost" accept-charset="utf-8">
								<div class="box-body">
									<div class="form-group">
										<label>用户名：${user.name}</label>
									</div>
									<div class="form-group">
										<label>Email：${user.email}</label>
									</div>
									<div class="form-group">
										<label>注册时间：<fmt:formatDate value="${user.createTime}" pattern="yyyy-MM-dd HH:mm"/></label>
									</div>
									<div class="form-group">
										<label>过期时间：<fmt:formatDate value="${user.expiredTime}" pattern="yyyy-MM-dd HH:mm"/></label>
									</div>
									<div class="form-group">
										<label>上次登陆时间：<fmt:formatDate value="${user.lastLoginTime}" pattern="yyyy-MM-dd HH:mm"/></label>
									</div>
									<div class="form-group">
										<label>上次登陆IP：${user.lastLoginIp}</label>
									</div>									
									<div class="form-group">
										<select name="proxyId" id="proxyId">
											<c:forEach items="${proxyList}" var="proxy">											
											<option value="${proxy.id}">${proxy.url}</option>
											</c:forEach>
										</select>																				
									</div>					
									<input type="hidden" name="userId" value="${user.id}" />
								</div>
								<!-- /.box-body -->
								<div class="box-footer">
									<button type="submit" name="action" value="add"
										class="btn btn-primary">提交</button>
								</div>
							</form>
						</div>
					</div>
					<!-- /.box -->
				</div>
				<!-- /.row -->
			</section>
			<!-- /.content -->
		</aside>
		<!-- /.right-side -->

		<script src="${SITE_DOMAIN}/static/js/jquery-2.1.1.js"></script>
		<script src="${SITE_DOMAIN}/static/js/bootstrap.min.js"
			type="text/javascript"></script>
		<script src="${SITE_DOMAIN}/static/js/jquery-ui.min.js"
			type="text/javascript"></script>
		<!-- Morris.js charts -->
		<script src="${SITE_DOMAIN}/static/js/raphael-min.js"></script>
		<script src="${SITE_DOMAIN}/static/js/plugins/morris/morris.min.js"
			type="text/javascript"></script>
		<!-- Sparkline -->
		<script
			src="${SITE_DOMAIN}/static/js/plugins/sparkline/jquery.sparkline.min.js"
			type="text/javascript"></script>
		<!-- jvectormap -->
		<script
			src="${SITE_DOMAIN}/static/js/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"
			type="text/javascript"></script>
		<script
			src="${SITE_DOMAIN}/static/js/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"
			type="text/javascript"></script>
		<!-- jQuery Knob Chart -->
		<script
			src="${SITE_DOMAIN}/static/js/plugins/jqueryKnob/jquery.knob.js"
			type="text/javascript"></script>
		<!-- Moment JS -->
		<script src="${SITE_DOMAIN}/static/js/plugins/moment/moment.min.js"></script>
		<!-- daterangepicker -->
		<script
			src="${SITE_DOMAIN}/static/js/plugins/daterangepicker/daterangepicker.js"
			type="text/javascript"></script>
		<!-- datepicker -->
		<script
			src="${SITE_DOMAIN}/static/js/plugins/datepicker/bootstrap-datepicker.js"
			type="text/javascript"></script>
		<!-- Bootstrap WYSIHTML5 -->
		<script
			src="${SITE_DOMAIN}/static/js/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"
			type="text/javascript"></script>
		<!-- iCheck -->
		<script src="${SITE_DOMAIN}/static/js/plugins/iCheck/icheck.min.js"
			type="text/javascript"></script>


 	<!-- jQuery 2.0.2 -->
    <script src="${SITE_DOMAIN}/static/js/jquery-2.0.3.min.js"></script>
    <script src="${SITE_DOMAIN}/static/js/jquery.validate.min.js"></script>
    <script src="${SITE_DOMAIN}/static/js/jquery.form.min.js"></script>
    <!-- Bootstrap -->
    <script src="${SITE_DOMAIN}/static/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="${SITE_DOMAIN}/static/js/md5.js" type="text/javascript"></script>
		<script type="text/javascript">						
			$(document).ready(function() {
	        	console.log('admin login');
	            var options = {
	                target:        '#editForm',   // target element(s) to be updated with server response
	                success:       showResponse,  // post-submit callback
	                dataType:  'json'        // 'xml', 'script', or 'json' (expected server response type)
	            };

	            $('#editForm').submit(function() {
	            	
	                if ($(this).valid()) {	                   	                   
	                    $(this).ajaxSubmit(options);
	                    return false;
	                }
	            });

	            jQuery.validator.addMethod("onlyAlphaNumber", function(value, element) {
	                return /^[a-zA-Z0-9]+$/.test(value);
	            }, "Alpha and Number Only!");

	            $('#editForm').validate( {
	                    rules:{
	                    	
	                    }
	                }
	            )
	        });

	        // post-submit callback
	        function showResponse(data) {
	            if (data.code == "0") {
	                window.location.href = "/admin/user/list";
	            } else {
	                alert(data.msg);
	            }
	        }
			
		</script>
		<div id="analytics-code" style="display: none">统计代码</div>
</body>
</html>

