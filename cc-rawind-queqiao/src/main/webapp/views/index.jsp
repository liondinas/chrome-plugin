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
    <title>小橙子-跨越万里长城</title>

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

    <div class="jumbotron">
        <h2>${SITE_NAME}</h2>
        <p class="lead"> 每个月不限流量，香港美国高速节点</p>
        <p><a class="btn btn-lg btn-success" href="${pageContext.request.contextPath}/user/register" role="button">立即注册</a></p>
    </div>

    <div class="row marketing">
        <div class="col-lg-6">
            <!-- <a href="https://play.google.com/store/apps/details?id=com.github.shadowsocks" target="_blank"><h4>Android</h4></a>
            <p>Android客户端</p>

            <h4><a href="http://sourceforge.net/projects/shadowsocksgui/files/dist/" target="_blank">Shadowsocks C#</a></h4>
            <p> Windows用户推荐此客户端.</p> -->

 			<h4><a href="http://www.yufengchen.com/queqiao_V1.01.crx" target="_blank">Chrome浏览器插件</a></h4>
            <p>无须安装客户端，简单易用，只需浏览器插件即可</p>

        </div>

        <div class="col-lg-6">        
        	<a href="https://itunes.apple.com/us/app/shadowsocks/id665729974?mt=8" target="_blank"><h4>安装使用说明</h4></a>
            <p>高速访问国外网站，不影响本地网站的访问速度</p>
            
            
            <!-- <a href="https://itunes.apple.com/us/app/shadowsocks/id665729974?mt=8" target="_blank"><h4>iOS</h4></a>
            <p>iOS客户端</p>

            <h4><a href="https://github.com/ohdarling/GoAgentX/releases" target="_blank">GoAgentX</a></h4>
            <p> Mac用户推荐此客户端.</p> -->
        </div>
    </div>
    <div class="footer">
	    <p>&copy; ${SITE_NAME}  Power by <a href="https://www.yufengchen.com" target="_blank">yufengchen.com</a>&nbsp;<a href="${pageContext.request.contextPath}/user/guestbook">讨论</a></p>
	</div>
	<div id="analytics-code" style="display: none">统计代码</div>

	</div> <!-- /container -->

</body>
</html>
