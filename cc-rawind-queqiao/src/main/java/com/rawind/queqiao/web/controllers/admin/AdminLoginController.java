package com.rawind.queqiao.web.controllers.admin;

import java.util.Date;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.DefValue;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;
import net.paoding.rose.web.annotation.rest.Post;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.chewen.tools.commons.util.AjaxOutput;
import com.chewen.tools.commons.util.CookieUtils;
import com.chewen.tools.commons.util.CwIPUtil;
import com.rawind.queqiao.web.Constants;
import com.rawind.queqiao.web.annotation.IgnoreLogin;
import com.rawind.queqiao.web.annotation.JsonResponse;
import com.rawind.queqiao.web.controllers.user.LoginController;
import com.rawind.queqiao.web.model.LoginResult;
import com.rawind.queqiao.web.model.QueqiaoUser;
import com.rawind.queqiao.web.service.PassportService;
import com.rawind.queqiao.web.service.QueqiaoUserExtrService;
import com.rawind.queqiao.web.service.QueqiaoUserService;
import com.rawind.queqiao.web.util.UserAgentUtils;



@IgnoreLogin()
@Path("")
public class AdminLoginController {
	private static final Log logger = LogFactory.getLog(LoginController.class);
	
	@Autowired
	private QueqiaoUserService queqiaoUserService;
	
	@Autowired
	private PassportService passportService;
	
	@Autowired
	private QueqiaoUserExtrService queqiaoUserExtrService;
	
	
	@Get("")
	public String goIndex(Invocation inv) {
		logger.info("admin login ");
		return "r:/admin/login";
	}
	
	@Get("login")
	public String goLogin(Invocation inv) {
		logger.info("admin login ");
		return "admin_login";
	}
	
	@JsonResponse
	@Post("doLogin")
	public String doLogin(Invocation inv, @Param("email") String email, @Param("password") String password,
			@Param("autologin") @DefValue("1") int autologin) {
		
		if(password!=null){
			/*password = password.replace("d", "a");
			password = password.replace("c", "s");
			password = password.replace("f", "e");*/
		}
		LoginResult result = queqiaoUserService.login(email, password);
		if(result.getResultCode()== LoginResult.CODE_SUCCESS){
			QueqiaoUser user = result.getQueqiaoUser();
			if(user!=null){			
				if(!user.isAdmin()){
					return AjaxOutput.failure("登陆错误(-1)");
				}
				
				String passport = passportService.genPsssport(user.getId(), UserAgentUtils.getInstance().getUserAgent(inv.getRequest()));
				
				int maxAge = -1;
				if(autologin == 1){
					maxAge = 60*60*24*30;
				}
				CookieUtils.getInstance().saveCookie(inv.getResponse(), Constants.userCookie, passport, "/", maxAge, false);
				
				user = queqiaoUserService.getQueqiaoUserById(user.getId());
				user.setLastLoginTime(new Date());
				user.setPassport(passport);
				user.setLastLoginIp(CwIPUtil.getRealIP(inv.getRequest()));
				queqiaoUserService.update(user);
				
				return AjaxOutput.success();
								
			}				
			
		}else if(result.getResultCode() == LoginResult.CODE_NOFILL){
			return AjaxOutput.failure("请输入正确的用户名和密码");
		}else if(result.getResultCode() == LoginResult.CODE_FAIL){
			return AjaxOutput.failure("用户名和密码不正确");
		}
		
		return AjaxOutput.failure("2");
	}
	
	
	@Get("logout")
	public String gologout(Invocation inv){				
		CookieUtils.getInstance().deleteCookie(inv.getResponse(), Constants.userCookie, "/");			
		return "r:/admin/login";
	}
	
}
