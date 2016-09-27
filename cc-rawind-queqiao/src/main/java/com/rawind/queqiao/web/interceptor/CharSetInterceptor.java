package com.rawind.queqiao.web.interceptor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;



import net.paoding.rose.web.ControllerInterceptorAdapter;
import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.advancedinterceptor.Ordered;
import net.paoding.rose.web.annotation.Interceptor;


/**
 * 主要设置返回的字符集
 * @author yufeng.che@gmail.com
 *
 */


@Interceptor(oncePerRequest = true)
@Component
public class CharSetInterceptor extends ControllerInterceptorAdapter implements Ordered{

	
	private static Log logger = LogFactory.getLog(CharSetInterceptor.class);
	
	
    @Override
    public int getPriority() {
        return 99;
    }
	
	@Override
	public Object before(Invocation inv) throws Exception {
		try{
			inv.getResponse().setCharacterEncoding("utf-8");
			inv.addModel("staticVersion", 1);
			if(logger.isDebugEnabled()){
				logger.debug("conver chaset for :" + inv.getResourceId());
			}
			return true;
		}catch(Exception e){
			e.printStackTrace();
			logger.error("conver chaset error for :" + inv.getResourceId());
		}
		
		return true;
	}
	
}
