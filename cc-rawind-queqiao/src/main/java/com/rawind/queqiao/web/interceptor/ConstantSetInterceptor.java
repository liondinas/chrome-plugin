package com.rawind.queqiao.web.interceptor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chewen.tools.commons.util.CookieUtils;
import com.chewen.tools.commons.util.CwStringUtil;
import com.rawind.queqiao.web.Constants;
import com.rawind.queqiao.web.service.PassportService;

import net.paoding.rose.web.ControllerInterceptorAdapter;
import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Interceptor;



/**
 * 主要设置一些常量类以及当前用户id
 * @author yufeng.che@gmail.com
 *
 */


@Interceptor(oncePerRequest = true)
@Component
public class ConstantSetInterceptor extends ControllerInterceptorAdapter {
	
	
	private static Log logger = LogFactory.getLog(ConstantSetInterceptor.class);
	
	@Autowired
	private PassportService passportService;
	
	
	@Override
	public Object before(Invocation inv) throws Exception {
		try{
			inv.addModel("SITE_DOMAIN", Constants.SITE_URL);
			inv.addModel("SITE_NAME", Constants.SITE_NAME);
			String passport = CookieUtils.getInstance().getCookieValue(inv.getRequest(), Constants.userCookie);
			long adminId = CwStringUtil.conver2Int(passportService.obtainAdminId(passport), 0);
			inv.addModel("_adminId", adminId);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			logger.error("ConstantSetInterceptor set error for :" + inv.getResourceId());
		}
		
		return true;
	}
}
