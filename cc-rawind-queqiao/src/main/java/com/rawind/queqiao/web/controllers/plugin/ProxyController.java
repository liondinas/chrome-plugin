package com.rawind.queqiao.web.controllers.plugin;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.chewen.tools.commons.util.AjaxOutput;
import com.rawind.queqiao.web.annotation.JsonResponse;
import com.rawind.queqiao.web.model.QueqiaoProxy;
import com.rawind.queqiao.web.model.QueqiaoUser;
import com.rawind.queqiao.web.service.HostHolderService;
import com.rawind.queqiao.web.service.QueqiaoProxyService;
import com.rawind.queqiao.web.service.QueqiaoUserService;
import com.rawind.queqiao.web.util.QqBase64;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;
import net.sf.json.JSONObject;


@Path("")
public class ProxyController {

	
	@Autowired
	private QueqiaoUserService queqiaoUserService;
	
	@Autowired
	private QueqiaoProxyService queqiaoProxyService;
	
	@Autowired
	private HostHolderService hostHolderService;
	
	
	@JsonResponse
	@Get("getProxy")
	public String getProxy(Invocation inv, @Param("uerId") long userId) {
		
		if(hostHolderService.isUserLogin()){
			JSONObject data = new JSONObject();
			QueqiaoUser user = hostHolderService.getQueqiaoUser();
			if(user.isExpired()){
				data.put("type", "1");
				data.put("url", "");
				return AjaxOutput.success("data", data);
			}
			String proxy = user.getProxyStr();
			if(StringUtils.isBlank(proxy)){
				long proxyId = user.getProxyId();
				QueqiaoProxy px = queqiaoProxyService.getById(proxyId);
				if(px!=null){
					proxy = px.getUrl();
				}			
			}
			
			
			//"Proxy-Authorization"= "Basic Base64.encode(user:password)" 
			String headerKey = "Proxy-Authorization"; 
			String headerValue = "Basic " + QqBase64.encodeStr(user.getName()+":"+user.getPassWord()); 
			//conn.setRequestProperty(headerKey, headerValue); 
			
			// proxy url="SOCKS5 127.0.0.1:1080";
			// proxy url="PROXY 101.200.121.195:3118";
			data.put("type", "0");
			data.put("url", proxy);
			data.put("userName", user.getName());
			return AjaxOutput.success("data", data);
			
		}
		
		
		return AjaxOutput.failure("Please login");
	}
}
