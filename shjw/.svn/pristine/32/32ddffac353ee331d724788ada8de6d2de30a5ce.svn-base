package com.sojson.common.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sojson.common.model.WeixinOauth2Token;
import com.sojson.common.utils.BaseUtils;
import com.sojson.common.utils.CommonUtil;

/**
 * 授权后的回调请求处理
 * 
 * @author Administrator
 * 
 */
public class OAuthServlet extends HttpServlet {
	private static final long serialVersionUID = -1847238807216447030L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		// 用户同意授权后，能获取到code
		String code = request.getParameter("code");
		String state = request.getParameter("state");
		String result = CommonUtil
				.urlEncodeUTF8("http://115.197.168.249/shjw/OAuthServlet.action");
		/*
		 * // 用户同意授权 if (!"authdeny".equals(code)) { //
		 * 获取网页授权access_token,应用的appid和appsecret WeixinOauth2Token
		 * weixinOauth2Token = AdvancedUtil
		 * .getOauth2AccessToken(Constants.AppID, Constants.AppSecret, code); //
		 * 网页授权接口访问凭证 String accessToken = weixinOauth2Token.getAccessToken();
		 * // 用户标识 String openId = weixinOauth2Token.getOpenId();
		 * System.out.println("openId: " + openId);
		 * System.out.println("accessToken: " + accessToken); // 获取用户信息
		 * SNSUserInfo snsUserInfo = AdvancedUtil.getSNSUserInfo(accessToken,
		 * openId); System.out.println("Nickname: " +
		 * snsUserInfo.getNickname()); // 设置要传递的参数
		 * request.setAttribute("snsUserInfo", snsUserInfo); }
		 */
		// 用户同意授权
		if (!"authdeny".equals(code)) {
			// 获取网页授权access_token,应用的appid和appsecret
			WeixinOauth2Token weixinOauth2Token = BaseUtils
					.getOauth2AccessToken(BaseUtils.AppID, BaseUtils.AppSecret,
							code);
			request.setAttribute("openId", weixinOauth2Token.getOpenId());
			request.setAttribute("prev",
					"ttps://open.weixin.qq.com/connect/oauth2/authorize?appid="
							+ BaseUtils.AppID + "&redirect_uri=" + result
							+ "&response_type=code&scope=snsapi_base&state=");
			request.setAttribute("next", "#wechat_redirect");
		}
		request.getRequestDispatcher("/WEB-INF/view/" + state + ".jsp")
				.forward(request, response);
	}
}
