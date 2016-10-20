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
									<a href="${SITE_DOMAIN}/user/my_info"
										class="btn btn-default btn-flat">我的信息</a>
								</div>
								<div class="pull-right">
									<a href="${SITE_DOMAIN}/user/logout"
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
					<div class="col-md-10">
						<div class="box box-solid">
							<div class="box-header">
								<i class="fa fa-th-list"></i>
								<h3 class="box-title">节点</h3>
							</div>
							<!-- /.box-header -->
							<table class="table table-striped">
									<thead>
										<tr>
											<th>订单ID</th>
											<th>订单详情</th>
											<th>订单金额</th>
											<th>下单时间</th>
											<th>下单人</th>											
											<th>订单状态</th>
											<th>支付</th>
										</tr>
									</thead>

									<tbody>
										<c:forEach items="${orderList}" var="order">
											<tr >				            					
				            					<td>${order.id}</td>
				            					<td>${order.memo}</td>
				            					<td><fmt:formatNumber value="${ order.amount / 100.00 }" pattern="#0.00"/></td>
				            					<td><fmt:formatDate value="${order.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
				            					<td>${order.userName}</td>				  
				            					<td><c:choose><c:when test="${order.status == 1}">已支付</c:when><c:otherwise>未支付</c:otherwise></c:choose></td>
				            					<td><button type="submit" class="btn btn-info btn-sm alipayBtn" id="alipayBtn" tradeNo="${order.queqiaoTradeNo}" subject="${order.memo}" fee="${order.amount/100}" orderBody="${order.memo}" >支付</button></td>						            							            			
				          					</tr>																				
										</c:forEach>
									</tbody>
								</table>
							<!-- /.box-body -->
						</div>
						<!-- /.box -->
					</div>
					<!-- /.col (left) -->
					
				</div>
				<!-- /.row -->
				<!-- END PROGRESS BARS -->
			</section>
			<!-- /.content -->
		</aside>
		<!-- /.right-side -->
		
		
		<form action="http://www.yufengchen.com/alipay/alipayapi.php" id="alipayform" method="POST" target="_blank">
				<input type="hidden" name="WIDout_trade_no" value="${orderQueqiao.queqiaoTradeNo}" id="WIDout_trade_no" />
				<input type="hidden" name="WIDsubject" value="${orderType.memo}" id="WIDsubject" />
				<input type="hidden" name="WIDtotal_fee" value="${orderQueqiao.amount/100}" id="WIDtotal_fee" />
				<input type="hidden" name="WIDbody" value="${orderQueqiao.memo}" id="WIDbody" />
		</form>
		
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

		        $('.alipayBtn').bind('click', function(e) {
		        	var item = $(e.target);
		        	$('#WIDout_trade_no').val(item.attr("tradeNo"));
		        	$('#WIDsubject').val(item.attr("subject"));
		        	$('#WIDtotal_fee').val(item.attr("fee"));
		        	$('#WIDbody').val(item.attr("orderBody"));
		        	console.log(item.attr("subject"));
		        	$('#alipayform').submit();
					
			     });
		    });
		</script>
		<div id="analytics-code" style="display: none">统计代码</div>
</body>
</html>

