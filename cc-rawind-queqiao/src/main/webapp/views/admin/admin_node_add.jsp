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
		<jsp:include  page="/views/admin/inc/left_header_inc.jsp"/>
	</header>
	<div class="wrapper row-offcanvas row-offcanvas-left">
		<!-- Left side column. contains the logo and sidebar -->
		<aside class="left-side sidebar-offcanvas">
			<!-- sidebar: style can be found in sidebar.less -->
			<section class="sidebar">
				<c:set var="menu" scope="session" value="2"/>
				<jsp:include  page="/views/admin/inc/left_menu_inc.jsp"/>
			</section>
			<!-- /.sidebar -->
		</aside>
		<!-- Right side column. Contains the navbar and content of the page -->
		<aside class="right-side">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>
					节点 <small>Node</small>
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
								<h3 class="box-title">新增节点</h3>								
							</div>

							<form  name="editForm" id="editForm" method="post"
								action="/admin/node/addPost" accept-charset="utf-8">
								<div class="box-body">
									<div class="form-group">
										<select name="proxyType" id="proxyType">
											<option value="1" <c:if test="${proxy.type == 1}">selected</c:if> >HTTP</option>
											<option value="2" <c:if test="${proxy.type == 2}">selected</c:if> >SOCKT</option>
											<option value="3" <c:if test="${proxy.type == 3}">selected</c:if> >SOCKTV5</option>
										</select>
									</div>

									<div class="form-group">
										<input type="text" class="form-control"
											placeholder="节点地址" id="proxyUrl" name="proxyUrl" value="${proxy.url}" required>
									</div>
									
									<input type="hidden" name="proxyId" value="${proxyId}" />
									
									<div class="form-group">
										<label>地址例子： SOCKS5 127.0.0.1:1080, PROXY 101.200.121.195:3118</label>
									</div>
									
								</div>
								<!-- /.box-body -->
								<div class="box-footer">
									<button type="button" name="action" value="add"
										class="btn btn-info" onclick="javascript:window.location.href='/admin/node/list';">返回</button>	
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
	                    	proxyUrl: {
	                            required: true
	                        }
	                    }
	                }
	            )
	        });

	        // post-submit callback
	        function showResponse(data) {
	            if (data.code == "0") {
	                window.location.href = "/admin/node/list";
	            } else {
	                alert(data.msg);
	            }
	        }
			
		</script>
		<div id="analytics-code" style="display: none">统计代码</div>
</body>
</html>

