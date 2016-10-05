package com.rawind.queqiao.web.interceptor;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;










import com.chewen.tools.commons.util.AjaxOutput;
import com.chewen.tools.commons.util.CookieUtils;
import com.chewen.tools.commons.util.CwStringUtil;
import com.chewen.tools.commons.util.UserAgentUtils;
import com.rawind.queqiao.web.Constants;
import com.rawind.queqiao.web.annotation.IgnoreLogin;
import com.rawind.queqiao.web.annotation.JsonResponse;
import com.rawind.queqiao.web.model.PassportVerifyResult;
import com.rawind.queqiao.web.model.QueqiaoUser;
import com.rawind.queqiao.web.service.HostHolderService;
import com.rawind.queqiao.web.service.PassportService;
import com.rawind.queqiao.web.service.QueqiaoUserService;

import net.paoding.rose.web.ControllerInterceptorAdapter;
import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.advancedinterceptor.Ordered;

@Component
public class LoginInterceptor extends ControllerInterceptorAdapter implements Ordered{
	private static Log logger = LogFactory.getLog(LoginInterceptor.class);
	@Autowired
	PassportService passportService;

	@Autowired
	HostHolderService hostHolderService;
	
	@Autowired
	QueqiaoUserService queqiaoUserService;
	
	@Override
	public int getPriority() {
		return 90;
	}
	
	@Override
	public Object before(Invocation inv) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("-----------LoginInterceptor");
		} 
		if(inv.getMethod().isAnnotationPresent(IgnoreLogin.class)){
			return true;
		}
		if(inv.getAnnotation(IgnoreLogin.class)!=null){
			return true;
		}
		
		String uri = inv.getRequestPath().getUri();
		logger.info("current uri =" + uri);
		
		
		int uriStatus = 0;
		if(uri.startsWith("/user")){
			uriStatus = 0;
		}else if(uri.startsWith("/admin")){
			uriStatus = 1;
		}
		
		
		String passport = CookieUtils.getInstance().getCookieValue(inv.getRequest(), Constants.userCookie);
		String userAgent = UserAgentUtils.getInstance().getUserAgent(inv.getRequest());
		PassportVerifyResult pvr = passportService.verifyPassport(passport, userAgent);
		if (pvr == null || !pvr.isOK()) {
			if(inv.getMethod().isAnnotationPresent(JsonResponse.class)){
				return AjaxOutput.needLogin();
			}
			return buildLogin(uriStatus);
		}
		long userId = CwStringUtil.conver2Int(passportService.obtainAdminId(passport), 0);
		
		QueqiaoUser user = queqiaoUserService.getQueqiaoUserById(userId);		
		if(user==null){
			return buildLogin(uriStatus);
		}
		
		if(user.isAdmin()){
			if(user.isAdmin()){
				hostHolderService.setQueqiaoAdmin(user);
			}else{
				return buildLogin(uriStatus);
			}
			
		}else{
			if(user.isAdmin()){
				return buildLogin(uriStatus);
			}else{
				hostHolderService.setQueqiaoUser(user);
			}
			
		}
			
		return true;
	}
	
	
	
	
	private String buildLogin(int uriStatus){
		if(uriStatus == 0){
			return "r:" + "/user/login";
		}else if(uriStatus == 1){
			return "r:" + "/admin/login";
		}
		
		return "r:/";
	}
	
}
