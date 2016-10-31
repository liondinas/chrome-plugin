<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
	<link rel="shortcut icon"href="favicon.ico"> 
    <title>小橙子-用户使用说明</title>

    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/css/jumbotron-narrow.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

<div class="container">
    <div class="header">
	    <ul class="nav nav-pills pull-right" role="tablist">
	        <li role="presentation"><a href="${pageContext.request.contextPath}">主页</a></li>
	        <li role="presentation"><a href="${pageContext.request.contextPath}/user/register" target="_blank">注册</a></li>
	        <li role="presentation"><a href="http://www.yufengchen.com/list.php" target="_blank">下载中心</a></li>
	        <li role="presentation"><a href="${pageContext.request.contextPath}/user/login" target="_blank">登录</a></li>
	        <li role="presentation"><a href="${pageContext.request.contextPath}/user" target="_blank">用户中心</a></li>	        
	    </ul>
	    <h3 class="text-muted">${SITE_NAME}</h3>
	</div>

    <div class="jumbotron" style="text-align: left;">
        <h4>注册小橙子</h4>
        <p style="font-size:15px;"><em>用户名为翻墙用到的用户名，最少6位，只能使用字母和数组；邮箱地址为登录后台使用，忘记密码会发送找回密码链接到该油箱；邀请码默认是不用填写的，如果有邀请码，请输入。</em></p>
        <p><img alt="" src="/img/guide/xcz_101.png" style="width:550px;"></img></p>
        <p style="font-size:15px;"><em>点击“用户中心”进入用户中心管理平台，在用户中心首页点击续期，然后选择符合自己需求的套餐并完成支付。</em></p>
        <p><img alt="" src="/img/guide/xcz_102.png" style="width:550px;"></img></p>
        <p><img alt="" src="/img/guide/xcz_103.png" style="width:550px;"></img></p>        
        <p style="font-size:15px;"><em>在“用户中心”可以进入订单列表，查看自己未支付的订单。</em></p>
        <p><img alt="" src="/img/guide/xcz_104.png" style="width:550px;"></img></p>        
    </div>
	
	<div class="jumbotron">
       <h4>登录管理后台</h4>
       <em>用户名为翻墙用到的用户名，最少6位，只能使用字母和数组；油箱地址为登录后台使用，忘记密码会发送找回密码链接到该油箱；邀请码默认是不用填写的，如果有邀请码，请输入。</em>
    </div>
	
    
    <div class="footer">
	    <p>&copy; ${SITE_NAME}  Power by <a href="https://www.yufengchen.com" target="_blank">yufengchen.com</a>&nbsp;<a href="${pageContext.request.contextPath}/user/guestbook">讨论</a></p>
	</div>
	<div id="analytics-code" style="display: none">统计代码</div>

	</div> <!-- /container -->

</body>
</html>
