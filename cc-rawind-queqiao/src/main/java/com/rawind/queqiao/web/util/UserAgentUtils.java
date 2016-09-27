package com.rawind.queqiao.web.util;

import javax.servlet.http.HttpServletRequest;


public class UserAgentUtils {
	private UserAgentUtils(){
	}
    private static UserAgentUtils uau = null ;
    
    public static UserAgentUtils getInstance(){
		if (uau == null) {
			synchronized (UserAgentUtils.class) {
				if (uau == null) {
					uau = new UserAgentUtils();
				}
			}
		}
		return uau;
	}
    
    /**
	 * 获取UA
	 * @param request
	 * @return
	 */
	public String getUserAgent(HttpServletRequest request){
		String userAgent = request.getHeader("User-Agent");
		if(userAgent==null){
			userAgent = "";
		}
		return userAgent;
	} 
}
