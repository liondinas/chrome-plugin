package com.rawind.queqiao.web.controllers.admin;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.chewen.tools.commons.util.AjaxOutput;
import com.rawind.queqiao.web.controllers.user.LoginController;
import com.rawind.queqiao.web.model.QueqiaoProxy;
import com.rawind.queqiao.web.model.QueqiaoUser;
import com.rawind.queqiao.web.service.HostHolderService;
import com.rawind.queqiao.web.service.PassportService;
import com.rawind.queqiao.web.service.QueqiaoProxyService;
import com.rawind.queqiao.web.service.QueqiaoUserExtrService;
import com.rawind.queqiao.web.service.QueqiaoUserService;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;
import net.paoding.rose.web.annotation.rest.Post;



@Path("/node")
public class NodeMgrController {

	private static final Log logger = LogFactory.getLog(LoginController.class);
	
	@Autowired
	private QueqiaoUserService queqiaoUserService;
	
	@Autowired
	private PassportService passportService;
	
	@Autowired
	private QueqiaoUserExtrService queqiaoUserExtrService;
	
	@Autowired
	private HostHolderService hostHolderService;
		
	@Autowired
	private QueqiaoProxyService queqiaoProxyService;
	
	@Get("list")
	public String showNodeList(Invocation inv) {
		
		
		
		inv.addModel("user", hostHolderService.getQueqiaoAdmin());
		
		
		int totalCount = queqiaoProxyService.countByType(0);
		List<QueqiaoProxy> proxyList = queqiaoProxyService.getByType(0, 0, totalCount);
		
		inv.addModel("proxyList", proxyList);
		
		
		return "admin_node_list";
	}
	
	
	@Get("add")
	public String showAddNode(Invocation inv) {
		
		
		
		inv.addModel("user", hostHolderService.getQueqiaoAdmin());
		
					
		inv.addModel("proxyId", 0);
		return "admin_node_add";
	}
	
	
	@Get("edit")
	public String showEditNode(Invocation inv, @Param("proxyId") long proxyId) {
		
		
		
		inv.addModel("user", hostHolderService.getQueqiaoAdmin());
		QueqiaoProxy proxy = queqiaoProxyService.getById(proxyId);
		if(proxy==null){
			return "@节点信息错误";
		}
					
		inv.addModel("proxy", proxy);
		inv.addModel("proxyId", proxyId);
		return "admin_node_add";
	}
	
	
	@Post("addPost")
	public String postAddNode(Invocation inv, @Param("proxyType") int proxyType, @Param("proxyUrl") String proxyUrl,
			@Param("proxyId") long proxyId) {
		
		
		if(proxyType == 0){
			return AjaxOutput.failure("请选择类型");
		}
		
		if(StringUtils.isBlank(proxyUrl)){
			return AjaxOutput.failure("请填写节点类型");
		}
		
		if(proxyId>0){
			QueqiaoProxy proxy = queqiaoProxyService.getById(proxyId);
			if(proxy==null){
				return AjaxOutput.failure("节点信息错误");
			}
			
			proxy.setType(proxyType);
			proxy.setUrl(proxyUrl);
			queqiaoProxyService.update(proxy);
			
		}else{
			QueqiaoProxy proxy = new QueqiaoProxy();
			proxy.setStatus(QueqiaoProxy.STATUS_NORMAL);
			proxy.setUrl(proxyUrl);
			proxy.setType(proxyType);

			queqiaoProxyService.addProxy(proxy);
		}
		
		
		return AjaxOutput.success("ok");
	}
	
}
