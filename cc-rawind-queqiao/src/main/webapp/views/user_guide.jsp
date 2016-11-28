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
    
    
    <style type="text/css">
    	em{
    		font-size:14px;
    	}
    	
    	.jumbotron img{
    		width:550px;
    	}
    </style>
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
        <p><em>用户名为翻墙用到的用户名，最少6位，只能使用字母和数组；邮箱地址为登录后台使用，忘记密码会发送找回密码链接到该油箱；邀请码默认是不用填写的，如果有邀请码，请输入。</em></p>
        <p><img alt="" src="http://www.yufengchen.com/img/xcz/xcz_101.png"></img></p>
        <p><em>点击“用户中心”进入用户中心管理平台，在用户中心首页点击续期，然后选择符合自己需求的套餐并完成支付。</em></p>
        <p><img alt="" src="http://www.yufengchen.com/img/xcz/xcz_102.png"></img></p>
        <p><img alt="" src="http://www.yufengchen.com/img/xcz/xcz_103.png"></img></p>        
        <p><em>在“用户中心”可以进入订单列表，查看自己未支付的订单。</em></p>
        <p><img alt="" src="http://www.yufengchen.com/img/xcz/xcz_104.png"></img></p>        
    </div>
	
	<div class="jumbotron" style="text-align: left;">
       <h4>下载浏览器插件</h4>
       <p><em>在首页导航中找到下载中心，可以直接<a href="http://www.yufengchen.com/list.php" target="_blank">点击</a>进入下载页面,选择最新版本的插件下载。如果使用的是Chrome浏览器，会提示无法安装插件。</em><p>
       <p><img alt="" src="http://www.yufengchen.com/img/xcz/xcz_dl_01.png"></img></p>
       <p><em>下载完成以后，首先打开Chrome浏览器，点击设置进入到浏览器插件的设置界面</em></p>
       <p><img alt="" src="http://www.yufengchen.com/img/xcz/xcz_use_00.jpg"></img></p>
       <p><em>进入设置界面后，选择左侧的“扩展程序”</em></p>
       <p><img alt="" src="http://www.yufengchen.com/img/xcz/xcz_use_01.jpg" ></img></p>
       <p><em>打开插件程序所在的文件夹， 注意将文件夹缩放到浏览器的前面， 然后拖拽插件到浏览器的扩展程序处， 完成插件的安装。</em></p>
       <p><img alt="" src="http://www.yufengchen.com/img/xcz/xcz_use_02.jpg"></img></p>
       <p><em>安装过程中会出现如下提示，选择“添加扩展程序”，然后会提示小橙子插件已经添加到Chrome浏览器。</em></p>
       <p><img alt="" src="http://www.yufengchen.com/img/xcz/xcz_use_03.jpg"></img></p>
       <p><img alt="" src="http://www.yufengchen.com/img/xcz/xcz_use_04.jpg"></img></p>
       <p><em>右键点击“小橙子”图标，显示的是添加翻墙的域名。只有添加过的域名才能通过翻墙网络，才能够正常访问。如果一些页面的域名已经添加过， 但还是出现页面错乱的话，请不要着急，多刷新几次就好了。</em></p>
       <p><img alt="" src="http://www.yufengchen.com/img/xcz/xcz_use_10.png"></img></p>
       <p><em> 如果出现输入用户名和密码的话，请输入注册时的用户名，密码默认是123456a， 建议登录管理平台密码管理里面修改默认密码。如果不太清除这些应该填写什么的话，可以在管理平台密码管理中进行设置。</em></p>
       <p><img alt="" src="http://www.yufengchen.com/img/xcz/xcz_use_09.png"></img></p>
       <p><em>左键点击“小橙子”图标，选择"选项"进入管理模式。在管理模式中可以管理需要翻墙的域名地址， 还可以过滤掉一些不需要的广告域名。插件初始话的时候已经初始了一些常见的翻墙域名。</em></p>
       <p><img alt="" src="http://www.yufengchen.com/img/xcz/xcz_use_06.jpg"></img></p>
       <p><img alt="" src="http://www.yufengchen.com/img/xcz/xcz_use_07.jpg"></img></p>
       <p><em>接下来就可以翻墙啦 ^_^</em></p>
       <p><img alt="" src="http://www.yufengchen.com/img/xcz/xcz_use_08.jpg"></img></p>
       <p><br/><br/></p>
       
       <p><em><a href="#">返回顶部</a></em></p>
    </div>
	
    
    <div class="footer">
	    <p>&copy; ${SITE_NAME}  Power by <a href="https://www.yufengchen.com" target="_blank">yufengchen.com</a>&nbsp;<a href="${pageContext.request.contextPath}/user/guestbook">讨论</a></p>
	</div>
	<div id="analytics-code" style="display: none">统计代码</div>

	</div> <!-- /container -->

</body>
</html>
