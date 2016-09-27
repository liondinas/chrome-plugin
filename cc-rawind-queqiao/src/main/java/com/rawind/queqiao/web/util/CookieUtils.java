package com.rawind.queqiao.web.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CookieUtils {

	private static Log logger = LogFactory.getLog(CookieUtils.class);
	private CookieUtils(){
		cookieNames.put("pintour_passport", "pintour_passport");
	}
    private static CookieUtils cu = null ;
    private Map<String,String> cookieNames = new HashMap<String,String>();
    
    public static CookieUtils getInstance(){
		if (cu == null) {
			synchronized (CookieUtils.class) {
				if (cu == null) {
					cu = new CookieUtils();
				}
			}
		}
		return cu;
	}
    
    public void saveCookie(HttpServletResponse resp, String name,String value, String path, int maxAge, boolean secure) {
		Cookie cookie = new Cookie(name, value);
		cookie.setPath(path);
		cookie.setMaxAge(maxAge);
		cookie.setSecure(secure);

		resp.addCookie(cookie);
	}

	public String getCookieValue(HttpServletRequest req, String name) {
		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(name)) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}

	public void deleteCookie(HttpServletResponse resp, String name, String path) {
		Cookie cookie = new Cookie(name, null);
		cookie.setPath(path);
		cookie.setMaxAge(0);

		resp.addCookie(cookie);
	}
	
}
