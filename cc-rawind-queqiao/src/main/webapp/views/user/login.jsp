<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="bg-black">
<head>
    <meta charset="utf-8">
    <title>${SITE_NAME} - 用戶登陆</title>
    <link rel="icon" href="${SITE_DOMAIN}/favicon.ico">
	<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <!-- bootstrap 3.0.2 -->
    <link href="${SITE_DOMAIN}/static/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <!-- font Awesome -->
    <link href="${SITE_DOMAIN}/static/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
    <!-- Theme style -->
    <link href="${SITE_DOMAIN}/static/css/AdminLTE.css" rel="stylesheet" type="text/css" />

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->

    <!-- jQuery 2.0.2 -->
    <script src="${SITE_DOMAIN}/static/js/jquery-2.0.3.min.js"></script>
    <script src="${SITE_DOMAIN}/static/js/jquery.validate.min.js"></script>
    <script src="${SITE_DOMAIN}/static/js/jquery.form.min.js"></script>
    <!-- Bootstrap -->
    <script src="${SITE_DOMAIN}/static/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="${SITE_DOMAIN}/static/js/md5.js" type="text/javascript"></script>
    <script type="text/javascript">
    $(document).ready(function() {
        	console.log('fffff');
            var options = {
                target:        '#loginResult',   // target element(s) to be updated with server response
                success:       showResponse,  // post-submit callback
                dataType:  'json'        // 'xml', 'script', or 'json' (expected server response type)
            };

            $('#loginForm').submit(function() {
            	
                if ($(this).valid()) {
                    document.getElementById('password').value = md7(document.getElementById('pass').value);
                    //document.getElementById('pass').value = '';
                    $(this).ajaxSubmit(options);
                    return false;
                }
            });

            jQuery.validator.addMethod("onlyAlphaNumber", function(value, element) {
                return /^[a-zA-Z0-9]+$/.test(value);
            }, "Alpha and Number Only!");

            $('#loginForm').validate( {
                    rules:{
                        username: {
                            required: true,
                            onlyAlphaNumber: true
                        },
                        password: "required"
                    }
                }
            )
        });

        // post-submit callback
        function showResponse(data) {
            if (data.code == "0") {
                window.location.href = "/user";
            } else {
                alert(data.msg);
            }
        }
    </script>
</head>
<body class="bg-black">

<div class="form-box" id="login-box">
    <div class="header">${SITE_NAME} - 用户登录</div>
    <form action="/user/doLogin" role="form" name="reg" id="loginForm"  method="post" accept-charset="utf-8">
    <div class="body bg-gray">
        <div class="form-group">
            <div class="input-group">
                <span class="input-group-addon"><i class="fa fa-user fa-fw"></i></span>
                <input type="email"  id="email" name="email" class="form-control" placeholder="Email" required autofocus>
            </div>
        </div>
        <div class="form-group">
            <div class="input-group">
                <span class="input-group-addon"><i class="fa fa-key fa-fw"></i></span>
                <input type="password"  id="pass" name="pass" class="form-control" placeholder="Password" required>
            </div>
        </div>
        <div class="form-group">
            <div class="input-group" style="display: none">
                <input type="hidden"  id="password" name="password" class="form-control">
            </div>
        </div>
        <div class="form-group">
            <div class="input-group">
                <input type="checkbox" name="remember_me" value="week"/> 保存Cookie7天
            </div>
        </div>
        <div class="input-group" id="loginResult"></div>
    </div>
    <div class="footer">
        <button type="submit" class="btn bg-olive btn-block"  name="login" >登录</button>
    </div>
    </form>
</div>
</body>
</html>