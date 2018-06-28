package com.sojson.common.utils;

import java.awt.Menu;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sojson.common.model.WxParams;

/**
 * 公众平台通用接口工具类
 * 
 */
public class WeixinUtil {
	private static Logger log = LoggerFactory.getLogger(WeixinUtil.class);

	/**
	 * 发起https请求并获取结果
	 * 
	 * @param requestUrl
	 *            请求地址
	 * @param requestMethod
	 *            请求方式（GET、POST）
	 * @param outputStr
	 *            提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public static JSONObject httpRequest(String requestUrl,
			String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url
					.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);

			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();

			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			jsonObject = JSONObject.fromObject(buffer.toString());
		} catch (ConnectException ce) {
			log.error("Weixin server connection timed out.");
		} catch (Exception e) {
			log.error("https request error:{}", e);
		}
		return jsonObject;
	}

	/**
	 * 获取ticket
	 * 
	 * @param token
	 *            凭证
	 * @return
	 */
	public synchronized static String getTicket(String token) {

		Object accessTicketTime = CacheManager.cacheMap
				.get("access_ticket_get_time");
		if (accessTicketTime != null) {
			int lastAccessTicketTime = Integer.parseInt(accessTicketTime
					.toString());
			if (!BaseUtils.accessTokenExpireTimeCompare(lastAccessTicketTime,
					BaseUtils.getDateSecond())) {
				return CacheManager.cacheMap.get("access_ticket").toString();
			}
		}
		String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="
				+ token + "&type=jsapi";
		JSONObject jsonObject = httpRequest(url, "GET", null);
		try {
			if (null != jsonObject) {
				String access_ticket = jsonObject.getString("ticket");
				CacheManager.cacheMap.put("access_ticket", access_ticket);
				CacheManager.cacheMap.put("access_ticket_get_time",
						BaseUtils.getDateSecond());
				WxParams.ticket = access_ticket;
				WxParams.ticketExpires = jsonObject.getString("expires_in");
				WxParams.ticketTime = CacheManager.cacheMap.get(
						"access_ticket_get_time").toString();
				return access_ticket;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("获取token失败 errcode:{} errmsg:{}",
					jsonObject.getInt("errcode"),
					jsonObject.getString("errmsg"));
		}
		return null;
	}

	/**
	 * 获取access_token
	 * 
	 * @param appid
	 *            凭证
	 * @param appsecret
	 *            密钥
	 * @return
	 */
	public synchronized static String getAccessToken() {
		Object accessTokenTime = CacheManager.cacheMap
				.get("access_token_get_time");
		if (accessTokenTime != null) {
			int lastAccessTokenTime = Integer.parseInt(accessTokenTime
					.toString());
			if (!BaseUtils.accessTokenExpireTimeCompare(lastAccessTokenTime,
					BaseUtils.getDateSecond())) {
				return CacheManager.cacheMap.get("access_token").toString();
			}
		}
		String accessToken = null;
		String requestUrl = BaseUtils.getAccessToken.replace("APPID",
				BaseUtils.AppID).replace("APPSECRET", BaseUtils.AppSecret);
		JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
		// 如果请求成功
		if (null != jsonObject) {
			try {
				accessToken = jsonObject.getString("access_token");
				CacheManager.cacheMap.put("access_token", accessToken);
				CacheManager.cacheMap.put("access_token_get_time",
						BaseUtils.getDateSecond());
				WxParams.token = accessToken;
				WxParams.tokenExpires = jsonObject.getString("expires_in");
				WxParams.tokenTime = CacheManager.cacheMap.get(
						"access_token_get_time").toString();
				return accessToken;
			} catch (JSONException e) {
				// 获取token失败
				log.error("获取token失败 errcode:{} errmsg:{}",
						jsonObject.getInt("errcode"),
						jsonObject.getString("errmsg"));
			}
		}
		return null;
	}

	public static void main(String[] args) {
		System.out.println(WeixinUtil.getAccessToken());
	}

	/*
	 * public static AccessToken getAccessToken(String appid, String appsecret)
	 * { AccessToken accessToken = null;
	 * 
	 * String requestUrl = access_token_url.replace("APPID", appid).replace(
	 * "APPSECRET", appsecret); JSONObject jsonObject = httpRequest(requestUrl,
	 * "GET", null); // 如果请求成功 if (null != jsonObject) { try { accessToken = new
	 * AccessToken();
	 * accessToken.setToken(jsonObject.getString("access_token"));
	 * accessToken.setExpiresIn(jsonObject.getInt("expires_in")); } catch
	 * (JSONException e) { accessToken = null; // 获取token失败 log.error("获取token失败
	 * errcode:{} errmsg:{}", jsonObject.getInt("errcode"),
	 * jsonObject.getString("errmsg")); } } return accessToken; }
	 */

	// 菜单创建（POST） 限100（次/天）
	public static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

	/**
	 * 创建菜单
	 * 
	 * @param menu
	 *            菜单实例
	 * @param accessToken
	 *            有效的access_token
	 * @return 0表示成功，其他值表示失败
	 */
	public static int createMenu(Menu menu, String accessToken) {
		int result = 0;

		// 拼装创建菜单的url
		String url = menu_create_url.replace("ACCESS_TOKEN", accessToken);
		// 将菜单对象转换成json字符串
		String jsonMenu = JSONObject.fromObject(menu).toString();
		// 调用接口创建菜单
		JSONObject jsonObject = httpRequest(url, "POST", jsonMenu);

		if (null != jsonObject) {
			if (0 != jsonObject.getInt("errcode")) {
				result = jsonObject.getInt("errcode");
				log.error("创建菜单失败 errcode:{} errmsg:{}",
						jsonObject.getInt("errcode"),
						jsonObject.getString("errmsg"));
			}
		}

		return result;
	}
	
	/**
	 * 获取js sdk 认证信息
	 * 
	 * @author
	 * @date 创建时间 2016年7月28日 上午11:25:01
	 * @param url
	 * @return
	 */
	public static Map<String, String> getSign(String url) {
		// 处理token失效的问题
		try {
			long tokenTimeLong = Long.parseLong(WxParams.tokenTime);
			long tokenExpiresLong = Long.parseLong(WxParams.tokenExpires);
			// 时间差
			long differ = (System.currentTimeMillis() - tokenTimeLong) / 1000;
			if (WxParams.token == null || differ > (tokenExpiresLong - 1800)) {
				String access_token = WeixinUtil.getAccessToken();
				if (access_token != null) {
					WxParams.token = access_token;
					WxParams.tokenTime = System.currentTimeMillis() + "";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			String access_token = WeixinUtil.getAccessToken();
			if (access_token != null) {
				WxParams.token = access_token;
				WxParams.tokenTime = System.currentTimeMillis() + "";
			}
		}

		// 处理ticket失效的问题
		try {
			long ticketTimeLong = Long.parseLong(WxParams.ticketTime);
			long ticketExpiresLong = Long.parseLong(WxParams.ticketExpires);

			// 时间差
			long differ = (System.currentTimeMillis() - ticketTimeLong) / 1000;
			if (WxParams.ticket == null || differ > (ticketExpiresLong - 1800)) {
				String ticket = WeixinUtil.getTicket(WxParams.token);
				if (ticket != null) {
					WxParams.ticket = ticket;
					WxParams.ticketTime = System.currentTimeMillis() + "";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			String ticket = WeixinUtil.getTicket(WxParams.token);
			if (ticket != null) {
				WxParams.ticket = ticket;
				WxParams.ticketTime = System.currentTimeMillis() + "";
			}
		}
		Map<String, String> ret = SignUtil.sign(WxParams.ticket, url);
		return ret;
	}
}
