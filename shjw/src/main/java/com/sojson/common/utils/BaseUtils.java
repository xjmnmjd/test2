package com.sojson.common.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.sojson.common.model.WXMenu;
import com.sojson.common.model.WeixinOauth2Token;
import com.sojson.common.model.WxParams;

public class BaseUtils {
	public static final String AppID = "wx4d0ece8ecad51949";
	public static final String AppSecret = "679a39a867e044869054d9ed75f79ea9";
	public static final String MchID = "1488647402";
	public static final String APISecret = "39f42ee356d742e79c3fc8f69bd87295";

	// 微信统一下单接口路径
	public static final String UNIFORMORDER = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	// 微信回调地址
	public static final String NOTIFYURL = "http://app.shhucuo.com/shjw/open/notify.shtml";
	// 微信交易类型
	public static final String TRADETYPE = "JSAPI";
	// 创建菜单
	public static final String createMenuUrl = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=%s";
	// 发送模板消息
	public static final String sendTemplateMessage = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
	// 获取模板id
	public static final String getTemplateMessageId = "https://api.weixin.qq.com/cgi-bin/template/api_add_template?access_token=ACCESS_TOKEN";
	// 手机号码归属地查询
	public static final String SENDPATH = "http://webservice.webxml.com.cn/WebServices/MobileCodeWS.asmx/getMobileCodeInfo";
	// 获取access_token的接口地址（GET） 限200（次/天）
	public final static String getAccessToken = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

	// 笑话
	public static final String SENDPATH2 = "http://api100.duapp.com/joke/?appkey=trialuser";

	// 智能聊天
	public static final String SENDPATH3 = "http://www.wendacloud.com/openapi/api?key=bbab79b3f2feaf07e40553265f112aae&info=";

	// 天气查询
	public static final String SENDPATH4 = "http://api.map.baidu.com/telematics/v3/weather?location=LOCATION&output=json&ak=81218080E79C9685b35e757566d8cbe5";

	// 热门影片
	public static final String SENDPATH5 = "http://api.map.baidu.com/telematics/v3/movie?qt=hot_movie&location=LOCATION&output=json&ak=81218080E79C9685b35e757566d8cbe5";

	// 景点详情
	public static final String SENDPATH6 = "http://api.map.baidu.com/telematics/v3/travel_attractions?id=ID&output=json&ak=81218080E79C9685b35e757566d8cbe5";

	// 百度音乐
	public static final String SENDPATH7 = "http://box.zhangmen.baidu.com/x?op=12&count=1&title={TITLE}$${AUTHOR}$$$$";

	// 百度翻译
	public static final String SENDPATH8 = "http://openapi.baidu.com/public/2.0/bmt/translate?client_id=MZ0xVxAicygxHWx6YUnkcCLd&q={keyWord}&from=auto&to=auto";

	// 人脸检测
	public static final String SENDPATH9 = "http://apicn.faceplusplus.com/v2/detection/detect?url=URL&api_secret=18PekbemsZn-x954KT-bb18HKjRkSw9e&api_key=fbe9455f3e3721cd89c97f393a7fc0a7";

	// 周边检索
	public static final String SENDPATH10 = "http://api.map.baidu.com/telematics/v3/local?location=LOCATION&keyWord=KEYWORD&output=xml&ak=81218080E79C9685b35e757566d8cbe5";;
	private static LoggerUtils logger = new LoggerUtils();

	public static int getDateSecond() {
		return (int) (new Date().getTime() / 1000);
	}

	public static boolean accessTokenExpireTimeCompare(int lastTime,
			int currTime) {
		return (currTime - lastTime) >= 7200;
	}

	public static final int freeTime = 7 * 24 * 60 * 60;

	/**
	 * String 转换 int 转换成数字
	 * 
	 * @param tmp
	 * @return
	 */
	public static int convertNumeric(String tmp) {
		Pattern pattern = Pattern.compile("[0-9]+");
		if (pattern.matcher(tmp).matches()) {
			return Integer.parseInt(tmp);
		} else {
			return 0;
		}
	}

	/**
	 * 获取毫秒数
	 */
	public static long getDateMillSecond() {
		return new Date().getTime();
	}

	/**
	 * 获取7天后的秒数
	 */
	public static int getWeekAfterSecond() {
		return (int) (new Date().getTime() / 1000) + freeTime;
	}

	/**
	 * 获取7天前的秒数
	 */
	public static int getWeekBeforeSecond() {
		return (int) (new Date().getTime() / 1000) - freeTime;
	}

	/**
	 * 字符转化
	 */
	public static String filterNullStr(String str) {
		return str == null ? "" : str;
	}

	/**
	 * 日期转化为描述（格式：yyyy/MM/dd）
	 */
	public static int strDateToInt(String date) {
		if (date == null) {
			return (int) (new Date().getTime() / 1000);
		}
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		int second = 0;
		try {
			second = (int) (format.parse(date).getTime() / 1000);
		} catch (ParseException e) {
			e.printStackTrace();
			return (int) (new Date().getTime() / 1000);
		}
		return second;
	}

	/**
	 * 获取大图片路径
	 */
	public static String getDatePath() {
		return "/" + getYear() + "/" + getMonth() + "/" + getDay() + "/";
	}

	/**
	 * 获取当前时间 yyyy-MM-dd HH:mm:ss
	 */
	public static String getDateYearSecond() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(new Date());
	}

	/**
	 * 获取当前 日期 yyyy-MM-dd
	 */
	public static String getDateYearDay() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(new Date());
	}

	/**
	 * 获取当前 时间 秒 HH:mm:ss
	 */
	public static String getHourMinuteSecond() {
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		return format.format(new Date());
	}

	/**
	 * 获取年
	 */
	public static String getYear() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		return format.format(new Date());
	}

	/**
	 * 获取月
	 */
	public static String getMonth() {
		SimpleDateFormat format = new SimpleDateFormat("MM");
		return format.format(new Date());
	}

	/**
	 * 获取日
	 */
	public static String getDay() {
		SimpleDateFormat format = new SimpleDateFormat("dd");
		return format.format(new Date());
	}

	/**
	 * 获取基础路径
	 */
	public static String getBasePath() {
		return "upload" + BaseUtils.getDatePath();
	}

	/**
	 * 判断目录是否存在，否在创建
	 */
	public static void isDir(String path) {
		File dirPath = new File(path);
		if (!dirPath.exists()) {
			dirPath.mkdirs();
		}
	}

	/**
	 * 获取一天时间最大秒数
	 */
	public static int getMaxTimeByDay() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String str = format.format(new Date());
		try {
			return (int) (format.parse(str).getTime() / 1000) + 3600 * 24;
		} catch (ParseException e) {
			System.out.println("时间解析错误：getMaxTimeByDay");
		}
		return 0;
	}

	/**
	 * 获取一天时间最小秒数
	 */
	public static int getMinTimeByDay() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String str = format.format(new Date());
		try {
			return (int) (format.parse(str).getTime() / 1000);
		} catch (ParseException e) {
			System.out.println("时间解析错误：getMinTimeByDay");
		}
		return 0;
	}

	/**
	 * 获取前30天的最小秒数
	 */
	public static int getPrevMonthMinTimeByDay() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String str = format.format(new Date());
		try {
			return (int) (format.parse(str).getTime() / 1000) - 30 * 24 * 3600;
		} catch (ParseException e) {
			System.out.println("时间解析错误：getMinTimeByDay");
		}
		return 0;
	}

	public static boolean isEmpty(Integer input) {
		if (input == null) {
			return true;
		}
		return false;
	}

	public static boolean isEmpty(String input) {
		if (input == null || input.trim().equals("")) {
			return true;
		}
		return false;
	}

	/**
	 * 获取文件名
	 */
	public static String genFileName() {
		Date date = new Date();
		return date.getTime() + ".jpg";
	}

	/**
	 * yyyy-MM-dd HH:mm:ss转换为秒
	 */
	public static int strDateToSecond(String str) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return (int) (format.parse(str).getTime() / 1000);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public static int strDateToSecond2(String str) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return (int) (format.parse(str).getTime() / 1000);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return -1;
	}

	/**
	 * 2016年04月19日
	 */
	public static String intDateToStr(int second) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
		try {
			long time = second * 1000l;
			return format.format(new Date(time));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String intDateToStr3(int second) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHH");
		try {
			long time = second * 1000l;
			return format.format(new Date(time));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String intDateToStr4(int second) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		try {
			long time = second * 1000l;
			return format.format(new Date(time));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * yyyy-MM-dd HH:mm:ss
	 */
	public static String intDateToStr2(int second) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			long time = second * 1000l;
			return format.format(new Date(time));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * yyyy-MM-dd
	 */
	public static String intDateToYYYYMMDD(int second) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			long time = second * 1000l;
			return format.format(new Date(time));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 生成随机6位验证码
	 */
	public static String genVerifyCode() {
		Random random = new Random();
		int tmp = random.nextInt(99999);
		return String.valueOf(tmp + 100000);
	}

	/**
	 * 获取服务器前缀路径
	 */
	public static String getServerSuffixPath(HttpServletRequest request) {
		String path = request.getContextPath();
		return request.getScheme() + "://" + request.getServerName() + ":"
				+ request.getServerPort() + path + "/";
	}

	/**
	 * 获取域名
	 */
	public static String getDoMain(HttpServletRequest request) {
		return request.getScheme() + "://" + request.getServerName() + ":"
				+ request.getServerPort();
	}

	public static String getFullURL(HttpServletRequest request) {
		StringBuffer url = request.getRequestURL();
		if (request.getQueryString() != null) {
			url.append("?");
			url.append(request.getQueryString());
		}
		return url.toString();
	}

	/**
	 * 检查目录是否存在
	 */
	public static void checkDirIsExist(String path) {
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
	}

	/**
	 * 将str中的html符号转义,将转义“'，&，<，"，>”五个字符
	 */
	public static String htmlToStr(String html) {
		html = html.replaceAll("\'", "&#39;");
		html = html.replaceAll("&", "&amp;");
		html = html.replaceAll("<", "&lt;");
		html = html.replaceAll("\"", "&quot;");
		html = html.replaceAll(">", "&gt;");
		return html;
	}

	/**
	 * 将str中的html符号转义,将转义“'，&，<，"，>”五个字符
	 */
	public static String strToHtml(String html) {
		html = html.replaceAll("&#39;", "\'");
		html = html.replaceAll("&amp;", "&");
		html = html.replaceAll("&lt;", "<");
		html = html.replaceAll("&quot;", "\"");
		html = html.replaceAll("&gt;", ">");
		return html;
	}

	/**
	 * 数字到boolean类型
	 */
	public static boolean intToBoolean(int num) {
		if (num == 0) {
			return false;
		}
		return true;
	}

	/**
	 * 生成订单号
	 */
	public static synchronized String genOrderNumber() {
		long time = new Date().getTime();
		try {
			Thread.sleep(10);
		} catch (Exception e) {
		}
		int s = (int) ((Math.random() * 9 + 1) * 100000);
		return "E" + time + String.valueOf(s);
	}

	/**
	 * 生成汽修的订单号
	 */
	public static synchronized String genCarOrderNumber() {
		long time = new Date().getTime();
		try {
			Thread.sleep(10);
		} catch (Exception e) {
		}
		int s = (int) ((Math.random() * 9 + 1) * 100000);
		return "Q" + time + String.valueOf(s);
	}

	/**
	 * 判断是否登录（临时）
	 */
	public static int isLogin(HttpServletRequest request) {
		String mId = request.getParameter("mId");
		if (mId == null || mId.equals("")) {
			return 1;
		}
		return 2;
	}

	/**
	 * 生成session_id
	 */
	public static String getSessionID() {
		String uuid = UUID.randomUUID().toString();
		uuid = uuid.replaceAll("-", "");
		return uuid;
	}

	/**
	 * 生成报案号
	 */
	public static synchronized String genCaseNumber() {
		long time = new Date().getTime();
		try {
			Thread.sleep(10);
		} catch (Exception e) {
		}
		return "CP" + time;
	}

	/**
	 * 获取uuid
	 */
	public static String genUUID() {
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		return uuid;
	}

	/**
	 * 将 yyyy-MM-dd HH:mm:ss转换为秒
	 */
	public static int yyyyMMddHHmmssToSecond(String time) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date date = format.parse(time);
			return (int) (date.getTime() / 1000);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return -1;
	}

	/**
	 * 将 yyyy-MM-dd 转换为秒
	 */
	public static int yyyyMMddToSecond(String time) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = format.parse(time);
			return (int) (date.getTime() / 1000);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return -1;
	}

	/**
	 * 获取保单结束时间
	 */
	public static int getPolicyEndTime(int startTime, String safeDate) {
		int date = 0;
		int endTime = 0;
		if (safeDate.contains("天")) {
			safeDate = safeDate.replaceAll("天", "");
			if (safeDate.contains("-")) {
				date = Integer.parseInt(safeDate.split("-")[1]);
			} else {
				date = Integer.parseInt(safeDate);
			}
			endTime = startTime + date * 24 * 3600;
		} else if (safeDate.contains("年")) {
			safeDate = safeDate.replaceAll("年", "");
			if (safeDate.contains("-")) {
				date = Integer.parseInt(safeDate.split("-")[1]);
			} else {
				date = Integer.parseInt(safeDate);
			}
			int monthNum = date * 12;
			long c = startTime * 1000l;
			Date now = new Date(c);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(now);
			calendar.add(Calendar.MONTH, monthNum);
			endTime = (int) (calendar.getTime().getTime() / 1000);
		} else if (safeDate.contains("月")) {
			safeDate = safeDate.replaceAll("月", "");
			if (safeDate.contains("-")) {
				date = Integer.parseInt(safeDate.split("-")[1]);
			} else {
				date = Integer.parseInt(safeDate);
			}
			int monthNum = date;
			long c = startTime * 1000l;
			Date now = new Date(c);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(now);
			calendar.add(Calendar.MONTH, monthNum);
			endTime = (int) (calendar.getTime().getTime() / 1000);
		}
		return endTime;
	}

	public static String getPolicyEndTimeName(int endTime) {
		long c = (endTime - 1) * 1000l;
		Date date = new Date(c);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(date) + " 二十四时";
	}

	/**
	 * 获取nonce_str
	 */
	public static String getNonceStr() {
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		return uuid.substring(0, 15);
	}

	/**
	 * 设置预支付的订单号
	 */
	public synchronized static String getPrePayOrderNo() {
		try {
			long time = new Date().getTime();
			Thread.sleep(10);
			return String.valueOf(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取主域名
	 * 
	 * @param request
	 * @return
	 */
	public static String getImportant(HttpServletRequest request) {
		return "http://www.zgbxjj.com/";
	}

	/**
	 * 根据生日获取年龄
	 */
	public static int getAgeByBirth(String birth) {
		Date dbDate = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			dbDate = (Date) dateFormat.parse(birth);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		int age = 0;
		Calendar born = Calendar.getInstance();
		Calendar now = Calendar.getInstance();
		if (dbDate != null) {
			now.setTime(new Date());
			born.setTime(dbDate);
			if (born.after(now)) {

			}
			age = now.get(Calendar.YEAR) - born.get(Calendar.YEAR);
			if (now.get(Calendar.DAY_OF_YEAR) < born.get(Calendar.DAY_OF_YEAR)) {
				age -= 1;
			}
		}
		return age;
	}

	/**
	 * 获取验证码
	 * 
	 * @return
	 */
	public static String getCaptcha() {
		Random random = new Random();
		int num = random.nextInt(899999);
		return String.valueOf(num + 100000);
	}

	/**
	 * 获取天根据保险期限
	 */
	public static int getDaysBySafeDate(String safeDate) {
		safeDate = safeDate.replaceAll("天", "");
		if (safeDate.contains("-")) {
			return Integer.parseInt(safeDate.split("-")[1]);
		}
		return Integer.parseInt(safeDate);
	}

	/**
	 * 获取月根据保险期限
	 */
	public static int getMonthsBySafeDate(String safeDate) {
		safeDate = safeDate.replaceAll("年", "");
		if (safeDate.contains("-")) {
			return Integer.parseInt(safeDate.split("-")[1]) * 12;
		}
		return Integer.parseInt(safeDate) * 12;
	}

	/**
	 * 将时间转换为时间戳
	 * 
	 * @param s
	 * @return
	 * @throws ParseException
	 */
	public static String dateToStamp(String s) throws ParseException {
		String res;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = simpleDateFormat.parse(s);
		long ts = date.getTime(); // 毫秒
		res = String.valueOf(ts / 1000); // 秒
		return res;
	}

	/**
	 * 获取第二天凌晨的Date
	 */
	public static Date getNextDayDate() throws ParseException {
		long time = new Date().getTime() + 3600 * 1000 * 24l;
		Date tmpDate = new Date(time);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String tmpStr = simpleDateFormat.format(tmpDate) + " 00:00:00";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.parse(tmpStr);
	}

	public static int getNowDayMinSecond() {
		long time = new Date().getTime();
		Date tmpDate = new Date(time);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String tmpStr = simpleDateFormat.format(tmpDate) + " 00:00:00";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return (int) (format.parse(tmpStr).getTime() / 1000);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 根据身份证获取出生日期
	 * 
	 * @param iIdNo
	 * @return
	 */
	public static String getBirthByIDCard(String iIdNo) {
		String tmpStr = "";
		if (iIdNo.length() == 15) {
			tmpStr = iIdNo.substring(6, 12);
			tmpStr = "19" + tmpStr;
			tmpStr = tmpStr.substring(0, 4) + "-" + tmpStr.substring(4, 6)
					+ "-" + tmpStr.substring(6);

			return tmpStr;
		} else {
			tmpStr = iIdNo.substring(6, 14);
			tmpStr = tmpStr.substring(0, 4) + "-" + tmpStr.substring(4, 6)
					+ "-" + tmpStr.substring(6);

			return tmpStr;
		}
	}

	public static String policyStauts(String stauts) {
		String stautsEnd = "";
		if (stauts.equals("1")) {
			stautsEnd = "待支付";
		} else if (stauts.equals("2")) {
			stautsEnd = "即将生效";
		} else if (stauts.equals("3")) {
			stautsEnd = "已生效";
		} else if (stauts.equals("4")) {
			stautsEnd = "已关闭";
		} else if (stauts.equals("5")) {
			stautsEnd = "即将到期";
		} else if (stauts.equals("6")) {
			stautsEnd = "已到期";
		}
		return stautsEnd;
	}

	public static String insereStatus(String stauts) {
		String stautsEnd = "";
		if (stauts.equals("1")) {
			stautsEnd = "已投保";
		} else if (stauts.equals("2")) {
			stautsEnd = "已支付,投保失败";
		} else if (stauts.equals("3")) {
			stautsEnd = "已退保";
		} else if (stauts.equals("4")) {
			stautsEnd = "已退款";
		} else {
			stautsEnd = "已支付，投保状态未知";
		}
		return stautsEnd;
	}

	/**
	 * yyyyMMdd
	 * 
	 * @return
	 */
	public static String getStringToday() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * yyyyMMddHHmmssSSS
	 */
	public static String getStringDate() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * 关系转换 数字----------->文本
	 */
	public static String getContent(String stauts) {
		String stautsEnd = "";
		if (stauts.equals("0")) {
			stautsEnd = "本人";
		} else if (stauts.equals("1")) {
			stautsEnd = "配偶";
		} else if (stauts.equals("2")) {
			stautsEnd = "父母";
		} else if (stauts.equals("3")) {
			stautsEnd = "子女";
		} else if (stauts.equals("4")) {
			stautsEnd = "兄弟姐妹";
		} else if (stauts.equals("5")) {
			stautsEnd = "祖父母、外祖父母";
		} else if (stauts.equals("6")) {
			stautsEnd = "雇佣";
		} else if (stauts.equals("7")) {
			stautsEnd = "其他";
		}
		return stautsEnd;
	}

	/**
	 * 老年险 投保人证件类型 关系转换 数字----------->文本
	 */
	public static String getOldCertificateType(String stauts) {
		String stautsEnd = "";
		if (stauts.equals("01")) {
			stautsEnd = "身份证";
		} else if (stauts.equals("02")) {
			stautsEnd = "户口簿";
		} else if (stauts.equals("03")) {
			stautsEnd = "护照";
		} else if (stauts.equals("04")) {
			stautsEnd = "军人证件";
		} else if (stauts.equals("05")) {
			stautsEnd = "驾驶执照";
		} else if (stauts.equals("06")) {
			stautsEnd = "返乡证";
		} else if (stauts.equals("07")) {
			stautsEnd = "港澳身份证";
		} else if (stauts.equals("08")) {
			stautsEnd = "工号";
		} else if (stauts.equals("09")) {
			stautsEnd = "赴台通行证";
		} else if (stauts.equals("10")) {
			stautsEnd = "港澳通行证";
		} else if (stauts.equals("15")) {
			stautsEnd = "士兵证";
		} else if (stauts.equals("25")) {
			stautsEnd = "港澳居民来往内地通行证";
		} else if (stauts.equals("26")) {
			stautsEnd = "台湾居民来往内地通行证";
		} else if (stauts.equals("31")) {
			stautsEnd = "组织机构代码证";
		} else if (stauts.equals("37")) {
			stautsEnd = "统一社会信用代码";
		} else if (stauts.equals("99")) {
			stautsEnd = "其他";
		}
		return stautsEnd;
	}

	/**
	 * 关系转换 文本-----------> 数字
	 */
	public static String getStauts(String content) {
		String stautsEnd = "";
		if (content.equals("本人")) {
			stautsEnd = "0";
		} else if (content.equals("配偶")) {
			stautsEnd = "1";
		} else if (content.equals("父母")) {
			stautsEnd = "2";
		} else if (content.equals("子女")) {
			stautsEnd = "3";
		} else if (content.equals("兄弟姐妹")) {
			stautsEnd = "4";
		} else if (content.equals("祖父母、外祖父母")) {
			stautsEnd = "5";
		} else if (content.equals("雇佣")) {
			stautsEnd = "6";
		} else if (content.equals("其他")) {
			stautsEnd = "7";
		}
		return stautsEnd;
	}

	/**
	 * 获取网页授权凭证
	 * 
	 * @param appId
	 *            公众账号的唯一标识
	 * @param appSecret
	 *            公众账号的密钥
	 * @param code
	 * @return WeixinAouth2Token
	 */
	public static WeixinOauth2Token getOauth2AccessToken(String appId,
			String appSecret, String code) {
		WeixinOauth2Token wat = null;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
		requestUrl = requestUrl.replace("APPID", appId);
		requestUrl = requestUrl.replace("SECRET", appSecret);
		requestUrl = requestUrl.replace("CODE", code);
		// 获取网页授权凭证
		JSONObject jsonObject = httpsRequest(requestUrl, "GET", null);
		if (null != jsonObject) {
			try {
				wat = new WeixinOauth2Token();
				wat.setAccessToken(jsonObject.getString("access_token"));
				wat.setExpiresIn(jsonObject.getInt("expires_in"));
				wat.setRefreshToken(jsonObject.getString("refresh_token"));
				wat.setOpenId(jsonObject.getString("openid"));
				wat.setScope(jsonObject.getString("scope"));
			} catch (Exception e) {
				wat = null;
				logger.error(BaseUtils.class,
						"getOauth2AccessToken-获取网页授权凭证失败", e);
			}
		}
		return wat;

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
		JSONObject jsonObject = httpsRequest(requestUrl, "GET", null);
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
				logger.error(BaseUtils.class, "getAccessToken-获取token失败"
						+ jsonObject.getInt("errcode"), e);
			}
		}
		return null;
	}

	/**
	 * 发送https请求
	 * 
	 * @param requestUrl
	 *            请求地址
	 * @param requestMethod
	 *            请求方式（GET、POST）
	 * @param outputStr
	 *            提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public static JSONObject httpsRequest(String requestUrl,
			String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			URL url = new URL(requestUrl);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setSSLSocketFactory(ssf);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			conn.setRequestMethod(requestMethod);
			// 当outputStr不为null时向输出流写数据
			if (null != outputStr) {
				OutputStream outputStream = conn.getOutputStream();
				// 注意编码格式
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}
			// 从输入流读取返回内容
			InputStream inputStream = conn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);
			String str = null;
			StringBuffer buffer = new StringBuffer();
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			// 释放资源
			bufferedReader.close();
			inputStreamReader.close();
			inputStream.close();
			inputStream = null;
			conn.disconnect();
			jsonObject = JSONObject.fromObject(buffer.toString());
		} catch (ConnectException ce) {
			logger.error(BaseUtils.class, "httpsRequest-连接超时", ce);
		} catch (Exception e) {
			logger.error(BaseUtils.class, "httpsRequest-https请求异常", e);
		}
		return jsonObject;
	}

	/**
	 * 组建微信菜单数据
	 */
	public static String createWXMenuData() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<WXMenu> wxMenuList = new ArrayList<WXMenu>();

		WXMenu first = new WXMenu();
		first.setName("车辆年检");
		List<WXMenu> oneChild = new ArrayList<WXMenu>();

		WXMenu oneChildFive = new WXMenu();
		oneChildFive.setType("view");
		oneChildFive.setName("年检服务");
		oneChildFive.setUrl("http://app.shhucuo.com/shjw/wx/car_infro_in.html");
		oneChild.add(oneChildFive);

		WXMenu oneChildFour = new WXMenu();
		oneChildFour.setType("view");
		oneChildFour.setName("检测站");
		oneChildFour
				.setUrl("http://app.shhucuo.com/shjw/wx/jcz_detail.html");
		oneChild.add(oneChildFour);
		first.setSub_button(oneChild);
		WXMenu sencond = new WXMenu();
		sencond.setName("我的订单");
		sencond.setUrl("http://app.shhucuo.com/shjw/wx/order.html");
		sencond.setType("view");
		WXMenu third = new WXMenu();
		third.setName("车主服务");
		List<WXMenu> thirdChild = new ArrayList<WXMenu>();

		WXMenu thirdChildFive = new WXMenu();
		thirdChildFive.setType("view");
		thirdChildFive.setName("车辆管理");
		thirdChildFive.setUrl("http://app.shhucuo.com/shjw/wx/car_manger.html");
		thirdChild.add(thirdChildFive);
		WXMenu thirdTwoFive = new WXMenu();
		thirdTwoFive.setType("view");
		thirdTwoFive.setName("车主服务");
		thirdTwoFive.setUrl("http://app.shhucuo.com/shjw/wx/car_service.html");
		thirdChild.add(thirdTwoFive);
		third.setSub_button(thirdChild);
		wxMenuList.add(first);
		wxMenuList.add(third);
		wxMenuList.add(sencond);
		map.put("button", wxMenuList);
		return JSON.toJSONString(map);
	}

	/**
	 * 自定义菜单创建
	 * 
	 * @param accessToken
	 *            接口访问凭证
	 * @param jsonMsg
	 *            json格式的客服消息（包括touser、msgtype和消息内容）
	 * @return true | false
	 */
	public static boolean createWXMenu(String accessToken, String jsonMsg) {
		boolean result = false;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		// 发送客服消息
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "POST",
				jsonMsg);

		if (null != jsonObject) {
			int errorCode = jsonObject.getInt("errcode");
			String errorMsg = jsonObject.getString("errmsg");
			if (0 == errorCode) {
				result = true;
			}
		}
		return result;
	}

	public static void main(String[] args) {
		// 获取接口访问凭证
		String accessToken = getAccessToken();
		createWXMenu(accessToken, createWXMenuData());
	}

}
