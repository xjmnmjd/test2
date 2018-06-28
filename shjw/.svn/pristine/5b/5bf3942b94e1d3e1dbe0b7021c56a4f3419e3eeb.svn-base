package com.sojson.common.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import sun.misc.BASE64Encoder;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressWarnings("restriction")
public class SmsUtil {
	private static String x_id = "mnmjd";
	private static String x_pwd = "xdf@123";

	public static String SendSms(String mobile, String content)
			throws UnsupportedEncodingException {
		HttpURLConnection httpconn = null;
		String result = "Error";
		StringBuilder sb = new StringBuilder();
		sb.append("http://service.winic.org:8009/sys_port/gateway/index.asp?");
		// 以下是参数
		sb.append("id=").append(URLEncoder.encode(x_id, "gb2312"));
		sb.append("&pwd=").append(x_pwd);
		sb.append("&to=").append(mobile);
		sb.append("&content=").append(URLEncoder.encode(content, "gb2312"));
		sb.append("&time=").append("");
		try {
			URL url = new URL(sb.toString());
			httpconn = (HttpURLConnection) url.openConnection();
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					httpconn.getInputStream()));
			result = rd.readLine();
			rd.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (httpconn != null) {
				httpconn.disconnect();
				httpconn = null;
			}
		}
		return result;
	}

	public static void main1(String[] args) {
		String host = "https://dm-53.data.aliyun.com";
		String path = "/rest/160601/ocr/ocr_vehicle.json";
		String method = "POST";
		String appcode = "6c6a44af894d45c999cb63800680331b";
		Map<String, String> headers = new HashMap<String, String>();
		// 最后在header中的格式(中间是英文空格)为Authorization:APPCODE
		// 83359fd73fe94948385f570e3c139105
		headers.put("Authorization", "APPCODE " + appcode);
		// 根据API的要求，定义相对应的Content-Type
		headers.put("Content-Type", "application/json; charset=UTF-8");
		Map<String, String> querys = new HashMap<String, String>();

		String upload = "D:\\shjw\\photo\\20171026\\timg.jpg";
		String code = CommonUtil.GetImageStr(upload);
		String bodys = "{\"inputs\":[{\"image\":{\"dataType\":50,\"dataValue\":"
				+ code + "}}]}";

		try {
			HttpResponse response = HttpUtils.doPost(host, path, method,
					headers, querys, bodys);
			System.out.println(response.toString());
			// 获取response的body
			System.out.println(EntityUtils.toString(response.getEntity()));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*
	 * 获取参数的json对象
	 */
	public static JSONObject getParam(int type, JSONObject dataValue) {
		JSONObject obj = new JSONObject();
		try {
			obj.put("dataType", type);
			obj.put("dataValue", dataValue);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return obj;
	}

	/*
	 * 获取参数的json对象
	 */
	public static JSONObject getParam(int type, String dataValue) {
		JSONObject obj = new JSONObject();
		try {
			obj.put("dataType", type);
			obj.put("dataValue", dataValue);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return obj;
	}

	String host = "https://dm-53.data.aliyun.com";
	String path = "/rest/160601/ocr/ocr_vehicle.json";
	String method = "POST";
	String appcode = "6c6a44af894d45c999cb63800680331b";

	public static void main(String[] args) throws Exception {
		String img_file = "D:\\shjw\\photo\\20171026\\timg.jpg";
		String service_url = "http://dm-53.data.aliyun.com/rest/160601/ocr/ocr_vehicle.json";
		String ak_id = "LTAInpjdgCD6tBdP";
		String ak_secret = "qFNNnILmAjA9mIS6urzpdwcLOn6Z35";
		// 对图像进行base64编码
		String img_base64 = "";
		try {
			File file = new File(img_file);
			byte[] content = new byte[(int) file.length()];
			FileInputStream finputstream = new FileInputStream(file);
			finputstream.read(content);
			finputstream.close();
			img_base64 = (new BASE64Encoder()).encode(content);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		// 拼装请求body的json字符串
		JSONObject request_obj = new JSONObject();
		try {
			JSONObject obj = new JSONObject();
			JSONArray input_array = new JSONArray();
			obj.put("image", getParam(50, img_base64));
			input_array.put(obj);
			request_obj.put("inputs", input_array);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		String body = request_obj.toString();
		// Sender代码参考
		// https://help.aliyun.com/document_detail/shujia/OCR/ocr-api/sender.html
		String result = Sender.sendPost(service_url, body, ak_id, ak_secret);
		System.out.println(result);
		// 解析请求结果
		try {
			JSONObject result_obj = new JSONObject(result);
			JSONArray output_array = result_obj.getJSONArray("outputs");
			String output = output_array.getJSONObject(0)
					.getJSONObject("outputValue").getString("dataValue"); // 取出结果json字符串
			JSONObject out = new JSONObject(output);
			if (out.getBoolean("success")) {
				String owner = out.getString("owner"); // 获取所有人
				String plate_num = out.getString("plate_num"); // 获取车牌号码
				String model_type = out.getString("model"); // 获取品牌型号
				String vin = out.getString("vin"); // 获取车辆识别代码
				String engine_num = out.getString("engine_num"); // 获取发动机号码
				String register_date = out.getString("register_date"); // 获取注册日期
				String request_id = out.getString("request_id"); // 获取request_id
				System.out
						.printf(" owner : %s \n plate_num : %s\n model_type : %s\n vin : %s\n engine_num : %s\n register_date : %s\n request_id : %s\n",
								owner, plate_num, model_type, vin, engine_num,
								register_date, request_id);
			} else {
				System.out.println("predict error");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

}
