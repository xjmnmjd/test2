package com.sojson.common.xj;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.sojson.common.utils.BaseUtils;
import com.sojson.common.wxpay.WXPayUtil;

public class WeiChartUtil {
	/**
	 * 返回状态码
	 */
	public static final String ReturnCode = "return_code";

	/**
	 * 返回信息
	 */
	public static final String ReturnMsg = "return_msg";

	/**
	 * 业务结果
	 */
	public static final String ResultCode = "result_code";

	/**
	 * 预支付交易会话标识
	 */
	public static final String PrepayId = "prepay_id";

	/**
	 * 得到微信预付单的返回ID
	 * 
	 * @param orderId
	 *            商户自己的订单号
	 * @param totalFee
	 *            总金额 （分）
	 * @return
	 * @throws Exception
	 */
	public static Map<String, String> getPreyId(String orderId, String totalFee)
			throws Exception {
		Map<String, String> reqMap = new HashMap<String, String>();
		reqMap.put("appid", WeiChartConfig.AppId);
		reqMap.put("mch_id", WeiChartConfig.MchId);
		reqMap.put("nonce_str", getRandomString());
		reqMap.put("body", WeiChartConfig.body);
		// reqMap.put("detail", WeiChartConfig.subject); //非必填
		// reqMap.put("attach", "附加数据"); //非必填
		reqMap.put("out_trade_no", orderId); // 商户系统内部的订单号,
		reqMap.put("total_fee", totalFee); // 订单总金额，单位为分
		reqMap.put("spbill_create_ip", getHostIp()); // 用户端实际ip
		// reqMap.put("time_start", "172.16.40.18"); //交易起始时间 非必填
		// reqMap.put("time_expire", "172.16.40.18"); //交易结束时间 非必填
		// reqMap.put("goods_tag", "172.16.40.18"); //商品标记 非必填
		reqMap.put("notify_url", WeiChartConfig.notify_url); // 通知地址
		reqMap.put("trade_type", "APP"); // 交易类型
		// reqMap.put("limit_pay", "no_credit"); //指定支付方式,no_credit 指定不能使用信用卡支
		// 非必填
		reqMap.put("sign",
				WXPayUtil.generateSignature(reqMap, BaseUtils.AppSecret));

		String reqStr = WXPayUtil.mapToXml(reqMap);
		String retStr = HttpClientUtil.postHttplient(WeiChartConfig.PrepayUrl,
				reqStr);
		return WXPayUtil.xmlToMap(retStr);
	}

	/**
	 * 关闭订单
	 * 
	 * @param orderId
	 *            商户自己的订单号
	 * @return
	 * @throws Exception
	 */
	public static Map<String, String> closeOrder(String orderId)
			throws Exception {
		Map<String, String> reqMap = new HashMap<String, String>();
		reqMap.put("appid", WeiChartConfig.AppId);
		reqMap.put("mch_id", WeiChartConfig.MchId);
		reqMap.put("nonce_str", getRandomString());
		reqMap.put("out_trade_no", orderId); // 商户系统内部的订单号,
		reqMap.put("sign",
				WXPayUtil.generateSignature(reqMap, BaseUtils.AppSecret));

		String reqStr = WXPayUtil.mapToXml(reqMap);
		String retStr = HttpClientUtil.postHttplient(
				WeiChartConfig.CloseOrderUrl, reqStr);
		return WXPayUtil.xmlToMap(retStr);
	}

	/**
	 * 查询订单
	 * 
	 * @param orderId
	 *            商户自己的订单号
	 * @return
	 * @throws Exception
	 */
	public static String getOrder(String orderId) throws Exception {
		Map<String, String> reqMap = new HashMap<String, String>();
		reqMap.put("appid", WeiChartConfig.AppId);
		reqMap.put("mch_id", WeiChartConfig.MchId);
		reqMap.put("nonce_str", getRandomString());
		reqMap.put("out_trade_no", orderId); // 商户系统内部的订单号,
		reqMap.put("sign",
				WXPayUtil.generateSignature(reqMap, BaseUtils.AppSecret));

		String reqStr = WXPayUtil.mapToXml(reqMap);
		String retStr = HttpClientUtil.postHttplient(WeiChartConfig.OrderUrl,
				reqStr);
		return retStr;
	}

	/**
	 * 退款
	 * 
	 * @param orderId
	 *            商户订单号
	 * @param refundId
	 *            退款单号
	 * @param totralFee
	 *            总金额（分）
	 * @param refundFee
	 *            退款金额（分）
	 * @param opUserId
	 *            操作员ID
	 * @return
	 * @throws Exception
	 */
	public static Map<String, String> refundWei(String orderId,
			String refundId, String totralFee, String refundFee, String opUserId)
			throws Exception {
		Map<String, String> reqMap = new HashMap<String, String>();
		reqMap.put("appid", WeiChartConfig.AppId);
		reqMap.put("mch_id", WeiChartConfig.MchId);
		reqMap.put("nonce_str", getRandomString());
		reqMap.put("out_trade_no", orderId); // 商户系统内部的订单号,
		reqMap.put("out_refund_no", refundId); // 商户退款单号
		reqMap.put("total_fee", totralFee); // 总金额
		reqMap.put("refund_fee", refundFee); // 退款金额
		reqMap.put("op_user_id", opUserId); // 操作员
		reqMap.put("sign",
				WXPayUtil.generateSignature(reqMap, BaseUtils.AppSecret));

		String reqStr = WXPayUtil.mapToXml(reqMap);
		String retStr = "";
		try {
			retStr = HttpClientUtil.postHttplientNeedSSL(
					WeiChartConfig.RefundUrl, reqStr,
					WeiChartConfig.refund_file_path, WeiChartConfig.MchId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return WXPayUtil.xmlToMap(retStr);
	}

	/**
	 * 退款查询
	 * 
	 * @param refundId
	 *            退款单号
	 * @return
	 * @throws Exception
	 */
	public static Map<String, String> getRefundWeiInfo(String refundId)
			throws Exception {
		Map<String, String> reqMap = new HashMap<String, String>();
		reqMap.put("appid", WeiChartConfig.AppId);
		reqMap.put("mch_id", WeiChartConfig.MchId);
		reqMap.put("nonce_str", getRandomString());
		reqMap.put("out_refund_no", refundId); // 商户退款单号
		reqMap.put("sign",
				WXPayUtil.generateSignature(reqMap, BaseUtils.AppSecret));

		String reqStr = WXPayUtil.mapToXml(reqMap);
		String retStr = HttpClientUtil.postHttplient(
				WeiChartConfig.RefundQueryUrl, reqStr);
		return WXPayUtil.xmlToMap(retStr);
	}

	/**
	 * 得到10 位的时间戳 如果在JAVA上转换为时间要在后面补上三个0
	 * 
	 * @return
	 */
	public static String getTenTimes() {
		String t = new Date().getTime() + "";
		t = t.substring(0, t.length() - 3);
		return t;
	}

	/**
	 * 得到随机字符串
	 * 
	 * @param length
	 * @return
	 */
	public static String getRandomString() {
		int length = 32;
		String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < length; ++i) {
			int number = random.nextInt(62);// [0,62)
			sb.append(str.charAt(number));
		}
		return sb.toString();
	}

	/**
	 * 得到本地机器的IP
	 * 
	 * @return
	 */
	private static String getHostIp() {
		String ip = "";
		try {
			ip = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return ip;
	}

	/**
	 * 将金额转换成分
	 * 
	 * @param fee
	 *            元格式的
	 * @return 分
	 */
	public static String changeToFen(Double fee) {
		String priceStr = "";
		if (fee != null) {
			int p = (int) (fee * 100); // 价格变为分
			priceStr = Integer.toString(p);
		}
		return priceStr;
	}

}
