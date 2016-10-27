<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<title>${SITE_NAME}- 用户中心</title>
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
				<jsp:include  page="/views/user/inc/left_menu_inc.jsp"/>
			</section>
			<!-- /.sidebar -->
		</aside>
		<!-- Right side column. Contains the navbar and content of the page -->
		<aside class="right-side">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>
					用户中心 <small>User Panel</small>
				</h1>
			</section>
			<!-- Main content -->
			<section class="content">
				<!-- START PROGRESS BARS -->
				<div class="row">
					<div class="col-md-6">
						<div class="box box-solid">
							<div class="box-header">
								<h3 class="box-title">公告&FAQ</h3>
							</div>
							<!-- /.box-header -->
							<div class="box-body">
								<div class="callout callout-warning">
									<h4>注意!</h4>
									<p>
										测试中，测试期间随时删号，不保证可用。<br />所有节点均不支持外发邮件。
									</p>
								</div>
							</div>
							<!-- /.box-body -->
						</div>
						<!-- /.box -->
					</div>
					<!-- /.col (right) -->
					<div class="col-md-6">
						<div class="box box-solid">
							<div class="box-header">
								<h3 class="box-title">注意</h3>
							</div>
							<!-- /.box-header -->
							<div class="box-body">
								<div class="callout callout-danger">
									<h4>未激活</h4>
									<p>您的账号还没有激活，暂时不能使用！请查收邮件。</p>
									<p>
										<a class="btn btn-success" id="check_in_button"
											onclick="do_resend_mail()">重发激活邮件</a>
									</p>
								</div>
							</div>
							<!-- /.box-body -->
						</div>
						<!-- /.box -->
					</div>
					<!-- /.col (right) -->
					<div class="col-md-6">
						<div class="box box-solid">
							<div class="box-header">
								<h3 class="box-title">流量使用情况</h3>
							</div>
							<!-- /.box-header -->
							<div class="box-body">
								<p>已用流量: 0 bytes</p>
								<div class="progress progress-striped">
									<div class="progress-bar progress-bar-primary"
										role="progressbar" aria-valuenow="40" aria-valuemin="0"
										aria-valuemax="100" style="width: 0%">
										<span class="sr-only">Transfer</span>
									</div>
								</div>
								<p>可用流量: 5.05 GB</p>
								<p>剩余流量: 5.05 GB</p>
							</div>
							<!-- /.box-body -->
						</div>
						<!-- /.box -->
					</div>
					<!-- /.col (left) -->

					<div class="col-md-6">
						<div class="box box-solid">
							<div class="box-header">
								<h3 class="box-title">签到获取流量</h3>
							</div>
							<!-- /.box-header -->
							<div class="box-body">
								<p>24小时内可以签到一次，剩余流量小于2G可以一次获得2G流量。</p>
								<p>
									<a class="btn btn-success" id="check_in_button" href="#"
										onclick="do_check_in()">签到</a>
								</p>
								<p>
									上次签到时间
									<code>2016-08-25 18:11:20</code>
								</p>
								<p id="check_in_result"></p>
							</div>
							<!-- /.box-body -->
						</div>
						<!-- /.box -->
					</div>
					<!-- /.col (right) -->


					<div class="col-md-6">
						<div class="box box-solid">
							<div class="box-header">
								<h3 class="box-title">连接信息</h3>
							</div>
							<!-- /.box-header -->
							<div class="box-body">
								<p>
									端口:
									<code>50006</code>
								</p>
								<p>密码: 320943</p>
								<p>
									套餐: <span class="label label-info"> A </span>
								</p>
								<p>
									最后使用时间:
									<code>1970-01-01 08:00:00</code>
								</p>
							</div>
							<!-- /.box-body -->
						</div>
						<!-- /.box -->
					</div>
					<!-- /.col (right) -->
				</div>
				<!-- /.row -->
				<!-- END PROGRESS BARS -->
			</section>
			<!-- /.content -->
		</aside>
		<!-- /.right-side -->
		<script type="text/javascript">
			function do_check_in() {
				var xmlhttp;
				if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
					xmlhttp = new XMLHttpRequest();
				} else {// code for IE6, IE5
					xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
				}
				xmlhttp.onreadystatechange = function() {
					if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
						var str1 = "<code>";
						var str2 = "</code>";
						document.getElementById("check_in_result").innerHTML = str1
								.concat(xmlhttp.responseText, str2);
						alert(xmlhttp.responseText);
						document.getElementById("check_in_button").href = "";
						document.getElementById("check_in_button").innerHTML = "不能签到";
						setTimeout(function() {
							window.location.reload();
						}, 3000);
					}
				}
				xmlhttp.open("GET", "${SITE_DOMAIN}/user/check_in.html", true);
				xmlhttp.send();
			}
			function do_resend_mail() {
				var xmlhttp;
				if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
					xmlhttp = new XMLHttpRequest();
				} else {// code for IE6, IE5
					xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
				}
				xmlhttp.onreadystatechange = function() {
					if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
						alert(xmlhttp.responseText);
					}
				}
				xmlhttp.open("GET", "${SITE_DOMAIN}/user/resend_mail.html",
						true);
				xmlhttp.send();
			}
		</script>
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

		<script type="text/javascript">
			$(window).on('load', function() {

				$('.selectpicker').selectpicker({
					'selectedText' : 'cat'
				});

				// $('.selectpicker').selectpicker('hide');
			});
		</script>
		<div id="analytics-code" style="display: none">统计代码</div>
</body>
</html>