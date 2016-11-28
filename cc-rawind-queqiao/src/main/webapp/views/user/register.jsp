<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<!DOCTYPE html>
<html lang="en" class="bg-black">
<head>
<meta charset="UTF-8">
<title>${SITE_NAME}-注册</title>
<link rel="icon" href="${SITE_DOMAIN}/favicon.ico">
<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>

<!-- bootstrap 3.0.2 -->
<link
	href="${STATIC_DOMAIN}/static/css/bootstrap.min.css"
	rel="stylesheet">
<!-- font Awesome -->
<link
	href="${STATIC_DOMAIN}/static/css/font-awesome.min.css"
	rel="stylesheet">
<!-- Theme style -->
<link href="${STATIC_DOMAIN}/static/css/AdminLTE.css"
	rel="stylesheet">

<!-- jQuery 2.0.2 -->
<script
	src="${STATIC_DOMAIN}/static/js/jquery-2.0.3.min.js"></script>
<script
	src="${STATIC_DOMAIN}/static/js/jquery.validate.min.js"></script>
<script
	src="${STATIC_DOMAIN}/static/js/jquery.form.min.js"></script>
<!-- Bootstrap -->
<script
	src="${STATIC_DOMAIN}/static/js/bootstrap.min.js"
	type="text/javascript"></script>

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->

<script type="text/javascript">
        $(document).ready(function() {
            var options = {
                target:        '#registerResult',   // target element(s) to be updated with server response
                success:       showResponse,  // post-submit callback
                dataType:  'json'        // 'xml', 'script', or 'json' (expected server response type)
            };

            $('#registerForm').submit(function() {
                if ($(this).valid()) {
                    $(this).ajaxSubmit(options);
                    return false;
                }
            });

            jQuery.validator.addMethod("onlyAlphaNumber", function(value, element) {
                return /^[a-zA-Z0-9]+$/.test(value);
            }, "只能是字母或者数字!");

            $('#registerForm').validate( {
                    rules:{
                        username: {
                            required: true,
                            minlength: 6,
                            onlyAlphaNumber: true
                        },
                        password: {
                            required: true,
                            minlength: 8
                        },
                        repassword: {
                            required: true,
                            minlength: 8,
                            equalTo: '#password'
                        },
                        email: {
                            required: true,
                            email: true
                        }<c:if test="${contacts == null}">,
                        code: {
                            required: true,
                            onlyAlphaNumber: true
                        }
                        </c:if>
                    }
                }
            )
        });

        // post-submit callback
        function showResponse(data) {
            if (data.code == "0") {
                window.location.href = "/user/login";
            } else {
                alert(data.msg);
            }
        }
    </script>
</head>
<body class="bg-black">

	<div class="form-box" id="login-box">
		<div class="header">${SITE_NAME}-注册</div>
		<form action="/user/doRegister" role="form" name="reg" id="registerForm"  method="post" accept-charset="utf-8">
			<div class="body bg-gray">
				<div class="form-group">
					<div class="input-group">
						<span class="input-group-addon"><i class="fa fa-user fa-fw"></i></span>
						<input type="text" id="username" name="username"
							class="form-control" placeholder="用户名" required autofocus>
					</div>
				</div>

				<div class="form-group">
					<div class="input-group">
						<span class="input-group-addon"><i class="fa fa-key fa-fw"></i></span>
						<input type="password" class="form-control" id="password"
							name="password" placeholder="密码" required>
					</div>
				</div>

				<div class="form-group">
					<div class="input-group">
						<span class="input-group-addon"><i class="fa fa-key fa-fw"></i></span>
						<input type="password" class="form-control" id="repassword"
							name="repassword" placeholder="重复密码" required>
					</div>
				</div>

				<div class="form-group">
					<div class="input-group">
						<span class="input-group-addon"><i
							class="fa fa-envelope-o fa-fw"></i></span> <input type="email"
							id="email" name="email" class="form-control" placeholder="邮箱"
							required>
					</div>
				</div>

				<?php if( $invite_only ) { ?>
				<div class="form-group">
					<div class="input-group">
						<span class="input-group-addon"><i
							class="fa fa-money fa-fw"></i></span> <input type="text"
							class="form-control" id="code" name="code" placeholder="邀请码"
							value="${inviteCode}" required>
					</div>
				</div>
				<?php } ?>
			</div>
			<div class="footer">

				<button type="submit" class="btn bg-olive btn-block">注册</button>

				<a href="/user/login"
					class="text-center">已经注册？请登录</a>								
			</div>
		</form>

		<div class="margin text-center">
			<span><a href="/" class="text-center">Home - 返回首页</a></span>
			<!-- <button class="btn bg-light-blue btn-circle">
				<i class="fa fa-facebook"></i>
			</button>
			<button class="btn bg-aqua btn-circle">
				<i class="fa fa-twitter"></i>
			</button>
			<button class="btn bg-red btn-circle">
				<i class="fa fa-google-plus"></i>
			</button> -->

		</div>
	</div>
</body>
</html>