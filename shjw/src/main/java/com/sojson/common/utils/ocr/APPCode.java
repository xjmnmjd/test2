package com.sojson.common.utils.ocr;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.sojson.common.model.DrivingLicense;
import com.sojson.common.utils.ocr.util.HttpUtils;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.codec.binary.Base64.encodeBase64;

/**
 * 使用APPCODE进行云市场ocr服务接口调用
 */

public class APPCode {
	private final static String host = "https://dm-53.data.aliyun.com";
	private final static String path = "/rest/160601/ocr/ocr_vehicle.json";
	private final static String appcode = "6c6a44af894d45c999cb63800680331b";

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

	public static DrivingLicense getEntityByOCR(String imgFile) {
		// 如果文档的输入中含有inputs字段，设置为True， 否则设置为False
		Boolean is_old_format = true;
		// 请根据线上文档修改configure字段
		JSONObject configObj = new JSONObject();
		configObj.put("side", "face");
		String config_str = configObj.toString();
		String method = "POST";
		Map<String, String> headers = new HashMap<String, String>();
		// 最后在header中的格式(中间是英文空格)为Authorization:APPCODE
		// 83359fd73fe94948385f570e3c139105
		headers.put("Authorization", "APPCODE " + appcode);
		Map<String, String> querys = new HashMap<String, String>();
		// 对图像进行base64编码
		String imgBase64 = "";
		try {
			String static_server_path = "D:\\shjw\\photo\\";
			String imgId = imgFile.split("\\?imgId=")[1];
			String[] imgids = imgId.split("_");
			if (imgids.length > 1) {
				File file = new File(static_server_path + imgids[0] + "\\"
						+ imgids[1]);
				byte[] content = new byte[(int) file.length()];
				FileInputStream finputstream = new FileInputStream(file);
				finputstream.read(content);
				finputstream.close();
				imgBase64 = new String(encodeBase64(content));
			} else {
				return null;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		// 拼装请求body的json字符串
		JSONObject requestObj = new JSONObject();
		try {
			if (is_old_format) {
				JSONObject obj = new JSONObject();
				obj.put("image", getParam(50, imgBase64));
				if (config_str.length() > 0) {
					obj.put("configure", getParam(50, config_str));
				}
				JSONArray inputArray = new JSONArray();
				inputArray.add(obj);
				requestObj.put("inputs", inputArray);
			} else {
				requestObj.put("image", imgBase64);
				if (config_str.length() > 0) {
					requestObj.put("configure", config_str);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		String bodys = requestObj.toString();
		try {
			HttpResponse response = HttpUtils.doPost(host, path, method,
					headers, querys, bodys);
			int stat = response.getStatusLine().getStatusCode();
			if (stat != 200) {
				System.out.println("Http code: " + stat);
				System.out.println("http header error msg: "
						+ response.getFirstHeader("X-Ca-Error-Message"));
				System.out.println("Http body error msg:"
						+ EntityUtils.toString(response.getEntity()));
				return null;
			}
			String res = EntityUtils.toString(response.getEntity());
			JSONObject res_obj = JSON.parseObject(res);
			if (is_old_format) {
				JSONArray outputArray = res_obj.getJSONArray("outputs");
				String output = outputArray.getJSONObject(0)
						.getJSONObject("outputValue").getString("dataValue");
				JSONObject out = JSON.parseObject(output);
				System.out.print(out.toString());
				if (out.getBoolean("success")) {
					DrivingLicense drivingLicense = new DrivingLicense();
					drivingLicense.setConfig_str(out.getString("config_str"));
					drivingLicense.setOwner(out.getString("owner"));
					drivingLicense.setPlate_num(out.getString("plate_num"));
					drivingLicense.setModel(out.getString("model"));
					drivingLicense.setVin(out.getString("vin"));
					drivingLicense.setUse_character(out
							.getString("use_character"));
					drivingLicense.setIssue_date(out.getString("issue_date"));
					drivingLicense.setVehicle_type(out
							.getString("vehicle_type"));
					drivingLicense.setEngine_num(out.getString("engine_num"));
					drivingLicense.setRegister_date(out
							.getString("register_date"));
					drivingLicense.setRequest_id(out.getString("request_id"));
					return drivingLicense;
				} else {
					System.out.println("predict error");
				}
			} else {
				System.out.println(res_obj.toJSONString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static void main(String[]args){
		getEntityByOCR("/shjw/showImage?imgId=20171026_22.jpg");
	}
}
