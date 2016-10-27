<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
					节点列表 <small>Node List</small>
				</h1>
			</section>
			<!-- Main content -->
			<section class="content">
				<!-- START PROGRESS BARS -->
				<div class="row">
					<div class="col-md-6">
						<div class="box box-solid">
							<div class="box-header">
								<i class="fa fa-th-list"></i>
								<h3 class="box-title">节点</h3>
							</div>
							<!-- /.box-header -->
							<div class="box-body">
								<div class="callout callout-info">
									<h4>加密方式</h4>
									<p>
										无特殊说明加密方式均为
										<code>rc4-md5</code>
									</p>
								</div>
								<p>
									默认节点:
									<code>1.2.3.4</code>
									默认节点
								</p>
							</div>
							<!-- /.box-body -->
						</div>
						<!-- /.box -->
					</div>
					<!-- /.col (left) -->

					<div class="col-md-6">
						<div class="box box-solid">
							<div class="box-header">
								<i class="fa fa-code"></i>
								<h3 class="box-title">测试节点</h3>
							</div>
							<!-- /.box-header -->
							<div class="box-body">
								<div class="callout callout-warning">
									<h4>注意!</h4>
									<p>测试节点可能随时撤销，有问题请反馈.</p>
								</div>
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
		 $(window).on('load', function () {

		        $('.selectpicker').selectpicker({
		            'selectedText': 'cat'
		        });

		        // $('.selectpicker').selectpicker('hide');
		    });
		</script>
		<div id="analytics-code" style="display: none">统计代码</div>
</body>
</html>

