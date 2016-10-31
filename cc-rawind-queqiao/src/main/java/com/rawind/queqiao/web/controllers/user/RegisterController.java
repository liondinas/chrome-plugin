package com.rawind.queqiao.web.controllers.user;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.velocity.tools.config.Data;
import org.springframework.beans.factory.annotation.Autowired;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;
import net.paoding.rose.web.annotation.rest.Post;

import com.chewen.tools.commons.util.AjaxOutput;
import com.chewen.tools.commons.util.BCrypt;
import com.chewen.tools.commons.util.CookieUtils;
import com.chewen.tools.commons.util.CwDateTimeUtil;
import com.rawind.queqiao.web.Constants;
import com.rawind.queqiao.web.annotation.IgnoreLogin;
import com.rawind.queqiao.web.annotation.JsonResponse;
import com.rawind.queqiao.web.model.QueqiaoUser;
import com.rawind.queqiao.web.service.QueqiaoUserService;
import com.rawind.queqiao.web.util.UserAgentUtils;


@IgnoreLogin()
@Path("")
public class RegisterController {
	
	
	private final String DATE_FORMATE = "MMyyyydd";
	
	@Autowired
	private QueqiaoUserService queqiaoUserService;
	
	@Get("register")
	public String goRegister(Invocation inv, @Param("from") String from, @Param("inviteCode") String inviteCode) {		
		
		// cookie token
		int maxAge = -1;		
		CookieUtils.getInstance().saveCookie(inv.getResponse(), Constants.RegisterCookie, getCookieVerify(), "/", maxAge, false);
		
		// ua token		
		inv.addModel("verify", getUAVeiryfy(inv.getRequest()));
				
		
		inv.addModel("from", from);
		
		if(StringUtils.isBlank(inviteCode)){
			inviteCode = "8816000001";
		}
		
		inv.addModel("inviteCode", inviteCode);
		
		return "register";
	}
	
	@JsonResponse
	@Post("doRegister")
	public String doRegister(Invocation inv, @Param("username") String username, @Param("email") String email, @Param("password") String password, 
			@Param("souce") String souce, @Param("from") String from, @Param("verify") String verify) {	
		
		String cookieToken = CookieUtils.getInstance().getCookieValue(inv.getRequest(), Constants.RegisterCookie);
		if(!getCookieVerify().equals(cookieToken)){
			return AjaxOutput.failure("please contact the admin");
		}
		
		if(getUAVeiryfy(inv.getRequest()).equals(verify)){
			return AjaxOutput.failure("please contact the cyf");
		}	
		
		if(StringUtils.isBlank(username)){
			return AjaxOutput.failure("请填写用户名");
		}
		
		if(StringUtils.isBlank(email)){
			return AjaxOutput.failure("请填写邮箱地址");
		}
		
		if(email.indexOf("@")<=2){
			return AjaxOutput.failure("请填写正确的邮箱地址");
		}
		
		
		if(StringUtils.isBlank(password)){
			return AjaxOutput.failure("请填写密码");
		}
		
		if(password.length()<6){
			return AjaxOutput.failure("密码不少于6位");
		}
		
		QueqiaoUser user = new QueqiaoUser();
		user.setCreateTime(new Date());
		user.setEmail(email);
		user.setExpiredTime(new Date());
		user.setFrom(from);
		//user.setLastLoginTime();
		user.setName(username);
		String passwordBcr = BCrypt.hashpw(password, BCrypt.gensalt(Constants.LOG_ROUNDS));
		user.setPassWord(passwordBcr);
		//user.setPassport(passport);
		//user.setPhoneNum(0);
		user.setProxyId(0);
		user.setProxyStr("");
		user.setType(0);
		
		long id = queqiaoUserService.save(user);
		
		return AjaxOutput.success(""+id);
	}
		
	
	
	private String getCookieVerify(){
		Date cur = new Date();
		String dayStr = CwDateTimeUtil.formatDate(cur, DATE_FORMATE);		
		return "cc_"+dayStr.hashCode();
	}
	
	private String getUAVeiryfy(HttpServletRequest req){
		String ua = UserAgentUtils.getInstance().getUserAgent(req);
		return "dd_" + ua.hashCode();
	}
	
	
}
