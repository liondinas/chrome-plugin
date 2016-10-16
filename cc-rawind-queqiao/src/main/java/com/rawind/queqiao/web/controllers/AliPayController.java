package com.rawind.queqiao.web.controllers;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;
import net.paoding.rose.web.annotation.rest.Post;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import com.rawind.queqiao.alipay.util.AlipayNotify;
import com.rawind.queqiao.web.annotation.IgnoreLogin;
import com.rawind.queqiao.web.service.QueqiaoOrderService;



@Path("/alipay")
public class AliPayController {
	private static Logger logger = Logger.getLogger(AliPayController.class);

	
	@Autowired
	private QueqiaoOrderService queqiaoOrderService;
	
	
	@SuppressWarnings("rawtypes")
	@Get("notice")
	@Post("notice")
	@IgnoreLogin
	public String notice(Invocation inv) {
		logger.info("notice-------");
		Map<String, String> params = new HashMap<String, String>();
		Map requestParams = inv.getRequest().getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			// valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
			params.put(name, valueStr);
		}

		try {
			// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
			// 车问订单号
			String cw_payment_no = new String(inv.getRequest().getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
			logger.info("cw_payment_no-------" + cw_payment_no);

			// 支付宝交易号
			String ali_trade_no = new String(inv.getRequest().getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
			logger.info("ali_trade_no-------" + ali_trade_no);

			// 交易状态
			String trade_status = new String(inv.getRequest().getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");
			logger.info("trade_status-------" + trade_status);

			// 交易金额
			String total_fee = new String(inv.getRequest().getParameter("total_fee").getBytes("ISO-8859-1"), "UTF-8");
			logger.info("total_fee-------" + total_fee);

			// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
			if (AlipayNotify.verify(params)) {// 验证成功
				//////////////////////////////////////////////////////////////////////////////////////////
				// 请在这里加上商户的业务逻辑程序代码
				logger.info("AlipayNotify verify success");
				// ——请根据您的业务逻辑来编写程序（以下代码仅作参考）——

				if (trade_status.equals("TRADE_FINISHED")) {
					// 判断该笔订单是否在商户网站中已经做过处理
					// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					// 如果有做过处理，不执行商户的业务程序

					// 注意：
					// 该种交易状态只在两种情况下出现
					// 1、开通了普通即时到账，买家付款成功后。
					// 2、开通了高级即时到账，从该笔交易成功时间算起，过了签约时的可退款时限（如：三个月以内可退款、一年以内可退款等）后。

					dealTrade(cw_payment_no, ali_trade_no, total_fee);
				} else if (trade_status.equals("TRADE_SUCCESS")) {
					// 判断该笔订单是否在商户网站中已经做过处理
					// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					// 如果有做过处理，不执行商户的业务程序

					// 注意：
					// 该种交易状态只在一种情况下出现——开通了高级即时到账，买家付款成功后。
					dealTrade(cw_payment_no, ali_trade_no, total_fee);
				}
				// ——请根据您的业务逻辑来编写程序（以上代码仅作参考）——

				return "@success"; // 请不要修改或删除

				//////////////////////////////////////////////////////////////////////////////////////////
			} else {// 验证失败
				logger.info("AlipayNotify verify fail");
				return "@fail";
			}
		} catch (Exception e) {
			logger.error("notice : ", e);
			return "@fail";
		}

	}

	private void dealTrade(String cw_payment_no, String ali_trade_no, String total_fee) {
		logger.info("cw_payment_no:" + cw_payment_no + ";ali_trade_no:" + ali_trade_no + ";total_fee:" + total_fee);
		BigDecimal decimalAmount = new BigDecimal(total_fee).setScale(2, BigDecimal.ROUND_HALF_UP);
		if (decimalAmount.compareTo(BigDecimal.ZERO) <= 0) {
			logger.warn("cw_payment_no:" + cw_payment_no + ";ali_trade_no:" + ali_trade_no + ";total_fee:" + total_fee + "---error total_fee");
			return;
		}
		int amount = decimalAmount.multiply(BigDecimal.valueOf(100)).intValue();
		if (amount <= 0) {
			logger.warn("cw_payment_no:" + cw_payment_no + ";ali_trade_no:" + ali_trade_no + ";total_fee:" + total_fee + "---error total_fee");
			return;
		}
		if (cw_payment_no != null) {
			queqiaoOrderService.finishOrder(cw_payment_no, ali_trade_no, amount);
		}else{
			logger.warn("cw_payment_no:" + cw_payment_no + ";ali_trade_no:" + ali_trade_no + ";total_fee:" + total_fee + "--- bussiness trade no is empty");
		}
	}
}
