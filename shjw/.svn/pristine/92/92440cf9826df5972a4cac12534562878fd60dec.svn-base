package com.sojson.common.wxpay.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.SortedMap;
import java.util.TreeMap;

import net.sf.json.JSONObject;

import com.alibaba.fastjson.JSONArray;
import com.sojson.common.payutil.WxpayUtil;
import com.sojson.common.utils.BaseUtils;
import com.sojson.common.wxpay.WXPayUtil;
import com.sojson.common.xj.HttpUtil;
import com.sojson.common.xj.XmltoJsonUtil;

public class test {

	public static void main(String[] args) throws Exception {
		
		System.out.println((int) Math.abs(Double.parseDouble("580.00")*100));
		SortedMap<String, String> signParams1 = new TreeMap<String, String>();
		signParams1.put("appid", "wx4d0ece8ecad51949");
		signParams1.put("body", "aa");
		signParams1.put("device_info", "WEB");
		signParams1.put("mch_id", "1488647402");
		signParams1.put("nonce_str", "18a0bbd89e204d409a8aca4b140129ad");
		String sign1 = WXPayUtil.generateSignature(signParams1,
				"39f42ee356d742e79c3fc8f69bd87295");
		System.out.println("sign1==" + sign1);
		String sign2 = WxpayUtil.createSign(signParams1);
		System.out.println("sign2==" + sign2);
		// HostnameVerifier hnv = new HostnameVerifier() {
		// public boolean verify(String hostname, SSLSession session) {
		// // Always return true，接受任意域名服务器
		// return true;
		// }
		// };
		// HttpsURLConnection.setDefaultHostnameVerifier(hnv);
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("appid", BaseUtils.AppID); // 支付类型
		packageParams.put("mch_id", BaseUtils.MchID); // 微信支付分配的商户号
		packageParams.put("device_info", "WEB");// 自定义参数，可以为终端设备号(门店号或收银设备ID)，PC网页或公众号内支付可以传"WEB"
		packageParams.put("body", "上海锦卫"); // 商品描述
		String nonce_str = WXPayUtil.generateNonceStr();
		System.out.println(nonce_str);
		packageParams.put("nonce_str", nonce_str); // 随机字符串，长度要求在32位以内
		packageParams.put("attach", "支付测试"); // 商品描述
												// 附加数据，在查询API和支付通知中原样返回，可作为自定义参数使用。
		packageParams.put("detail", "商品描述"); // 商品描述
		packageParams.put("notify_url",
				"http://wxpay.wxutil.com/pub_v2/pay/notify.v2.php"); // 异步接收微信支付结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数
		// packageParams.put("openid", WXPayUtil.generateNonceStr()); //
		// 此参数为微信用户在商户对应appid下的唯一标识
		packageParams.put("out_trade_no", "SHJW201708W251639e21232");// 商户系统内部订单号，要求32个字符内
		// APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP request.getRemoteAddr()
		packageParams.put("spbill_create_ip", "123.12.12.123");
		packageParams.put("total_fee", "1");// 订单总金额，单位为分
		packageParams.put("trade_type", "JSAPI");// JSAPI--公众号支付、NATIVE--原生扫码支付、APP--app支付
		packageParams.put("fee_type", "CNY"); // 银行币种
		packageParams.put("input_charset", "UTF-8"); // 字符集
		/*
		 * SortedMap<String, String> signParams = new TreeMap<String, String>();
		 * signParams.put("appid", BaseUtils.AppID); signParams.put("body",
		 * "aa"); signParams.put("device_info", "WEB"); signParams.put("mch_id",
		 * BaseUtils.MchID); signParams.put("nonce_str", nonce_str); String sign
		 * = WXPayUtil.generateSignature(signParams, BaseUtils.APISecret);
		 * System.out.println(sign);
		 */
		String UTF8 = "UTF-8";
		packageParams.put("openid", "oVl0f0n5deqtzrF-FkKKMCML9dvY");
		packageParams.put("sign_type", "MD5");
		String sign = WXPayUtil.generateSignature(packageParams,
				BaseUtils.APISecret);
		System.out.println(sign);
		packageParams.put("sign", sign);
		String reqBody = WXPayUtil.mapToXml(packageParams);
		// String reqBody = WxpayUtil.buildXML(packageParams);
		System.out.println(reqBody);
		JSONObject obj = new JSONObject();
		String content = HttpUtil.sendPost(BaseUtils.UNIFORMORDER, reqBody);
		System.out.println("content1==" + content);
		com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSONObject
				.parseObject(XmltoJsonUtil.xml2JSON(content));
		com.alibaba.fastjson.JSONObject result_xml = jsonObject
				.getJSONObject("xml");
		JSONArray result_code = result_xml.getJSONArray("result_code");
		String code = (String) result_code.get(0);
		if (code.equalsIgnoreCase("FAIL")) {
			obj.put("result", 0);
			obj.put("msg", result_xml.getJSONArray("return_msg").get(0) + "");
		} else if (code.equalsIgnoreCase("SUCCESS")) {
			JSONArray prepay_id = result_xml.getJSONArray("prepay_id");
			String prepayId = (String) prepay_id.get(0);
			obj.put("result", 1);
			obj.put("prepayId", prepayId);
			obj.put("msg", "预支付成功");
		}

		// packageParams.put("total_fee", String.valueOf(rstotal));
		// String reqBody
		// ="<xml><body>测试商家-商品类目</body><trade_type>NATIVE</trade_type><mch_id>11473623</mch_id><sign_type>HMAC-SHA256</sign_type><nonce_str>b1089cb0231011e7b7e1484520356fdc</nonce_str><detail /><fee_type>CNY</fee_type><device_info>WEB</device_info><out_trade_no>20161909105959000000111108</out_trade_no><total_fee>1</total_fee><appid>wxab8acb865bb1637e</appid><notify_url>http://test.letiantian.com/wxpay/notify</notify_url><sign>78F24E555374B988277D18633BF2D4CA23A6EAF06FEE0CF1E50EA4EADEEC41A3</sign><spbill_create_ip>123.12.12.123</spbill_create_ip></xml>";
		URL httpUrl = new URL("https://api.mch.weixin.qq.com/pay/unifiedorder");
		HttpURLConnection httpURLConnection = (HttpURLConnection) httpUrl
				.openConnection();
		httpURLConnection.setRequestProperty("Host", "api.mch.weixin.qq.com");
		httpURLConnection.setDoOutput(true);
		httpURLConnection.setRequestMethod("POST");
		httpURLConnection.setConnectTimeout(10 * 1000);
		httpURLConnection.setReadTimeout(10 * 1000);
		httpURLConnection.connect();
		OutputStream outputStream = httpURLConnection.getOutputStream();
		outputStream.write(reqBody.getBytes(UTF8));

		// 获取内容
		InputStream inputStream = httpURLConnection.getInputStream();
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(inputStream, UTF8));
		final StringBuffer stringBuffer = new StringBuffer();
		String line = null;
		while ((line = bufferedReader.readLine()) != null) {
			stringBuffer.append(line);
		}
		String resp = stringBuffer.toString();
		if (stringBuffer != null) {
			try {
				bufferedReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (inputStream != null) {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (outputStream != null) {
			try {
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println(resp);
	}
}
