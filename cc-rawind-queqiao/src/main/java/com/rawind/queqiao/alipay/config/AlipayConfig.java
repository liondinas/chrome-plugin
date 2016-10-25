package com.rawind.queqiao.alipay.config;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.4
 *修改日期：2016-03-08
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 合作身份者ID，签约账号，以2088开头由16位纯数字组成的字符串，查看地址：https://b.alipay.com/order/pidAndKey.htm
	public static String partner = "2088421778870972";
	
	// 收款支付宝账号，以2088开头由16位纯数字组成的字符串，一般情况下收款账号就是签约账号
	public static String seller_id = partner;

	//商户的私钥,需要PKCS8格式，RSA公私钥生成：https://doc.open.alipay.com/doc2/detail.htm?spm=a219a.7629140.0.0.nBDxfy&treeId=58&articleId=103242&docType=1
	public static String private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCx5avIRamFDfg7fVlKLRgT6Adptd4QHGHebIPrJDFHN9+wHFDVyayNf5O+B4eSHlg6aAc6NIU8EtV37NC85bL6OQgtrPxpxjmVoFnyXXQqDU2GL3lo2F+MjhfEHq17MjOaQ9Dv1tDdHSfb2Hy/AhiTOU8X6csjodFYDmEVRGXxMe+58rKqWLfeNoouIH6+g065nyHVyy+2xYbYE6pNEpa9wFJgJ0wWeyP+Ndtgzq5LmxfgluU78nUx967qbgiwvNEWm3AjDV90fN5i/7kF10PEvd56N2iDyZ+BJ4UdfsdWW1aOxQX16aXk3Uk22Q//LDDS+W3V8kRHshcosTr5BAfxAgMBAAECggEAH3wllhF2Oj1JtMWvmug2PbewMC5m+fT8AVC1XFWQfylNZq7sQ5oo9vYgYN6WSO8j8FpfBRcsiYBP1aTNINGKhqy3XNYj6liR8oBpyvJtT9AA64HVfA/ngUnYvPYtAAPXjDL6dKhRWQEC/+7lzSyCyvO2ouXW9Rx7n5kT556uaCyBrDazLoQfhYQmPf2v3o2VE2Dge/rJDPBQyfgf2dFAui3eYFYWHIxj85tF2w5ZmmrOdhnpufAoytGh5GJDRA1noYNQD+OeF2o1tQPVeaS2gM5vGDWVzqIYk6jQNYTh3hYFaN3HvqVuY5BcAUut3mpLmc8ZP9cBi9nm67fV38oLXQKBgQDj5y0CxJ6XLjrPK5giYoWGuK5Mv6WlAS24jgrJHR6fM9EO31p5xaNKAYeR9b1XSjcV5Yzr7K2Yf+mvXz7PRB79gQ2Yr8TGx3aUO4052gsQgWCiCfSDIhkLG0rLuGf1OuYn11rJsQuU4GStnbVAkt/0gxknCQW+1LlhM05Nu3DQ/wKBgQDH1EPKk4dGjAPboKZhV5EzZsE02uyvxb2kK/Q5q7O6jCaPnvuaFlgjcF8QcjEsb5TaboFnNCsiJyznYC/fQgFDQ70VJ/DSK3ZAovdJTsD2gz0aZBt6ImorQfdjgoHxtqP2N6/KEJ8vkEuuWA9TKXatcep+fJSPc0ruBuTd7n83DwKBgAMqHJfjPwh+Xdaulkgz8gKy4cizCIvH/miHoGK2gsxXHdoFA+6Y7fGDH+zI6r4hi65EJ7bG7IonjIkg23BpU7zVY+jw19yivGTGhul8u5aZFUxqsvh3Kr3avSkjAF6JWNTIyrvuN2jzzLIUdIXXBEES6PB/0ZKaxZAx9C5kIeblAoGAID3BSlgPRXSl0rPS5HXKea6kkbCY66tN88UbvIpVj025kwaltbDRO7aPwAmMoYzHSY721f32z6Xeet5sGut9ZKrzQQyWsHJOvBMPLwbD34JBQ5bPUB9pXd1g2u9do/BB+CH3/PQ9muD/NPwETPitq0JsAUh8d8C0YD9AggZPBEUCgYEA0ehtHrTaKPOtHREjF0uKS2DNaNCyUilHED3mYgTiW5txck2sGkV/tMnjbsXmjMDAP+x4ikzf5B7eATAzg9369CW3aNO/DKUlrBG4Sp3H+/1YKLCnk2NBQVdrK35f1vLrhsR9tO/ttYO0Iuiep90EzukM87FqlzVZFMWVRvm+DaI=";
	
	// 支付宝的公钥,查看地址：https://b.alipay.com/order/pidAndKey.htm
	public static String alipay_public_key  = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAseWryEWphQ34O31ZSi0YE+gHabXeEBxh3myD6yQxRzffsBxQ1cmsjX+TvgeHkh5YOmgHOjSFPBLVd+zQvOWy+jkILaz8acY5laBZ8l10Kg1Nhi95aNhfjI4XxB6tezIzmkPQ79bQ3R0n29h8vwIYkzlPF+nLI6HRWA5hFURl8THvufKyqli33jaKLiB+voNOuZ8h1csvtsWG2BOqTRKWvcBSYCdMFnsj/jXbYM6uS5sX4JblO/J1Mfeu6m4IsLzRFptwIw1fdHzeYv+5BddDxL3eejdog8mfgSeFHX7HVltWjsUF9eml5N1JNtkP/yww0vlt1fJER7IXKLE6+QQH8QIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://proxy.xiaochengzi.vip/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://proxy.xiaochengzi.vip/return_url.jsp";

	// 签名方式
	//public static String sign_type = "RSA";
	public static String sign_type = "MD5";
	
	// 调试用，创建TXT日志文件夹路径，见AlipayCore.java类中的logResult(String sWord)打印方法。
	public static String log_path = "/data/logs/";
		
	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String input_charset = "utf-8";
		
	// 支付类型 ，无需修改
	public static String payment_type = "1";
		
	// 调用的接口名，无需修改
	public static String service = "create_direct_pay_by_user";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	
//↓↓↓↓↓↓↓↓↓↓ 请在这里配置防钓鱼信息，如果没开通防钓鱼功能，为空即可 ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	
	// 防钓鱼时间戳  若要使用请调用类文件submit中的query_timestamp函数
	public static String anti_phishing_key = "";
	
	// 客户端的IP地址 非局域网的外网IP地址，如：221.0.0.1
	public static String exter_invoke_ip = "";
		
//↑↑↑↑↑↑↑↑↑↑请在这里配置防钓鱼信息，如果没开通防钓鱼功能，为空即可 ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	
}

