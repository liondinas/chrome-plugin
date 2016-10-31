<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<title>${SITE_NAME} - 用户中心 - 密码修改</title>
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
		<jsp:include  page="/views/user/inc/left_header_inc.jsp"/>
	</header>
	<div class="wrapper row-offcanvas row-offcanvas-left">
		<!-- Left side column. contains the logo and sidebar -->
		<aside class="left-side sidebar-offcanvas">
			<!-- sidebar: style can be found in sidebar.less -->
			<section class="sidebar">
				<c:set var="menu" scope="session" value="4"/>		
				<jsp:include  page="/views/user/inc/left_menu_inc.jsp"/>
			</section>
			<!-- /.sidebar -->
		</aside>
		<!-- Right side column. Contains the navbar and content of the page -->
		<aside class="right-side">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>
					密码管理 <small>PWD Manager</small>
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
								<h3 class="box-title">资料修改</h3>
							</div>
							<!-- /.box-header -->
							<!-- form start -->
							<form
								action="/user/update_pwd"
								role="form" name="editForm" id="editForm" method="post"
								accept-charset="utf-8">
								<input type="hidden" name="userId" value="${user.id}" />
								<div class="box-body">
									<div class="form-group">
										<input type="password" class="form-control"
											placeholder="当前密码(必填)" id="nowpassword" name="nowpassword"
											required>
									</div>

									<div class="form-group">
										<input type="password" class="form-control"
											placeholder="新密码(不修改请留空)" id="password" name="password">
									</div>

									<div class="form-group">
										<input type="password" placeholder="确认密码" class="form-control"
											id="repassword" name="repassword">
									</div>

									<div class="form-group">
										<input type="text" placeholder="邮箱(不修改请留空)"
											class="form-control" id="email" name="email">
									</div>
								</div>
								<!-- /.box-body -->
								<div class="box-footer">
									<button type="submit" name="action" value="add"
										class="btn btn-primary">修改</button>
								</div>
							</form>
						</div>
						<!-- /.box -->
					</div>

					<div class="col-md-6">
						<div class="box box-solid">
							<div class="box-header">
								<i class="fa fa-align-left"></i>
								<h3 class="box-title">翻墙帐号修改</h3>
							</div>
							<!-- /.box-header -->
							<div class="box-body">
								<form role="form" id="ssPass" name="ssPass" method="post"
									action="/user/update_userextr">
									
									<input type="hidden" name="userId" value="${user.id}" />
									<div class="form-group">
										<input type="text" class="form-control"
											placeholder="帐号" id="userName" name="userName"
											required value="${userExtr.userName}">
									</div>
																		
									<div class="form-group">
										<input type="password" placeholder="输入新密码" class="form-control"
											id="userPwd" name="userPwd" required>
									</div>

									<div class="box-footer">
										<button type="submit" name="action" value="edit"
											class="btn btn-primary">修改</button>
									</div>
								</form>
							</div>
							<!-- /.box-body -->
						</div>
						<!-- /.box -->
					</div>
					<!-- /.col (right) -->
				</div>
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
		<!-- AdminLTE App -->
		<script src="${SITE_DOMAIN}/static/js/AdminLTE/app.js"
			type="text/javascript"></script>

		<!-- Select js -->
		<script src="${SITE_DOMAIN}/static/js/bootstrap-select.js"></script>
		<script src="${SITE_DOMAIN}/static/js/bootstrap-switch.js"></script>
		
		    
    	<script src="${SITE_DOMAIN}/static/js/jquery.validate.min.js"></script>
    	<script src="${SITE_DOMAIN}/static/js/jquery.form.min.js"></script>

		<script type="text/javascript">
			$(window).on('load', function() {

				$('.selectpicker').selectpicker({
					'selectedText' : 'cat'
				});

				// $('.selectpicker').selectpicker('hide');
			});
			$(document).ready(function() {
				var options = {
					target : '#updateResult', // target element(s) to be updated with server response
					success : showResponse, // post-submit callback
					dataType : 'json' // 'xml', 'script', or 'json' (expected server response type)
				};

				$('#editForm').submit(function() {
					if ($(this).valid()) {
						$(this).ajaxSubmit(options);
						return false;
					}
				});

				$('#editForm').validate({
					rules : {
						nowpassword : {
							required : true,
							minlength : 6
						},
						password : {
							required : false,
							minlength : 8
						},
						repassword : {
							required : false,
							minlength : 8,
							equalTo : '#password'
						},
						email : {
							required : false,
							email : true
						}
					}
				});

				$('#ssPass').submit(function() {
					$(this).ajaxSubmit(options);
					return false;
				});
			});

			// post-submit callback
			function showResponse(data) {
				if (data.code == "0") {
					alert('Update Success!');
					window.location.reload();
				} else {
					alert(data.msg);
				}
			}
		</script>
		<div id="analytics-code" style="display: none">统计代码</div>
</body>
</html>
