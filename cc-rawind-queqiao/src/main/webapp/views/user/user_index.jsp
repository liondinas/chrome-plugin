<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<title>${SITE_NAME}- 用户中心</title>
<meta charset="UTF-8">
<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no'
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
				<c:set var="menu" scope="session" value="1"/>
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
										${broadcast}
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
									<h4>账号还有 ${step} 天到期</h4>
									<p>您的账号的到期时间为:<code> <fmt:formatDate value="${expiredDate}" pattern="yyyy-MM-dd HH:mm"/> </code>。为了不影响您的正常使用，请及时续期！ </p>
									<p>
										<a class="btn btn-success" id="check_in_button"
											onclick="window.location.href='/user/order_create';">续期</a>
									</p>
								</div>
							</div>
							<!-- /.box-body -->
						</div>
						<!-- /.box -->
					</div>					

					<div class="col-md-6">
						<div class="box box-solid">
							<div class="box-header">
								<h3 class="box-title">分享好友获取流量</h3>
							</div>
							<!-- /.box-header -->
							<div class="box-body">
								<p>分享好友，成功注册并使用的免费获取一个月的使用。</p>
								<p>
									<!-- <a class="btn btn-success" id="check_in_button" href="#"
										onclick="do_check_in()">好友</a> -->
									<a href="javascript:void((function(s,d,e,r,l,p,t,z,c) {var%20f='http://v.t.sina.com.cn/share/share.php?appkey=962772401',u=z||d.location,p=['&url=',e(u),'& title=',e(t||d.title),'&source=',e(r),'&sourceUrl=',e(l),'& content=',c||'gb2312','&pic=',e(p||'')].join('');function%20a() {if(!window.open([f,p].join(''),'mb', ['toolbar=0,status=0,resizable=1,width=600,height=500,left=',(s.width- 600)/2,',top=',(s.height-600)/2].join('')))u.href=[f,p].join('');}; if(/Firefox/.test(navigator.userAgent))setTimeout(a,0);else%20a();}) (screen,document,encodeURIComponent,'','','http://proxy.xiaochengzi.vip/orange512.png','小橙子-跨越万里长城。浏览器插件模式，无需安装客户端，高速浏览国外网站，不影响本地网站的访问。','http://proxy.xiaochengzi.vip?inviteCode=${user.id}','utf-8'));" 
										alt="分享到新浪微博" title="分享到新浪微博" class="btn btn-info" id="check_in_button">分享新浪微博</a>    
								</p>
								<p>
									上次登陆时间
									<code><fmt:formatDate value="${user.lastLoginTime}" pattern="yyyy-MM-dd HH:mm"/> </code>
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
								<h3 class="box-title">代理信息</h3>
							</div>
							<!-- /.box-header -->
							<div class="box-body">
								<p>
									端口:
									<code>50006</code>
								</p>
								<p>地址: ${proxy}</p>
								<p>
									套餐: <span class="label label-info"> A </span>
								</p>
								<p>
									最后使用时间:
									<code><fmt:formatDate value="${cur}" pattern="yyyy-MM-dd HH:mm"/></code>
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