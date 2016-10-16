package com.rawind.queqiao.web.controllers;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.rawind.queqiao.alipay.util.AlipayNotify;
import com.rawind.queqiao.web.annotation.IgnoreLogin;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;
import net.paoding.rose.web.annotation.rest.Post;



@IgnoreLogin()
@Path("")
public class IndexController {
	
	
	
	@Get("")
	public String showIndex(Invocation inv) {				
		return "index";
	}
	
	
}
