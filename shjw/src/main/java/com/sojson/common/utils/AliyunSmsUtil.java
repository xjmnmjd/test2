package com.sojson.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

public class AliyunSmsUtil {
	// 产品名称:云通信短信API产品,开发者无需替换
	static final String product = "Dysmsapi";
	// 产品域名,开发者无需替换
	static final String domain = "dysmsapi.aliyuncs.com";

	// TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
	static final String accessKeyId = "LTAInpjdgCD6tBdP";
	static final String accessKeySecret = "qFNNnILmAjA9mIS6urzpdwcLOn6Z35";
	// 短信验证码模板
	public static final String SMS_YZM_TemplateCode = "SMS_115760104";
	// 预约年检模板
	public static final String YYNJ_TemplateCode = "SMS_115755097";
	// 订单生成发送短信
	public static final String ORDER_TemplateCode = "SMS_115750107";
	// 给客户寄送材料
	public static final String Send_Materials_To_Customers_TemplateCode = "SMS_117520797";
	// 安排快递到客户哪里去取材料
	public static final String Arrangement_Express_Delivery_TemplateCode = "SMS_117526130";

	/*
	 * 短信模板备注 
	 * SMS_117526130：您好！您的订单收到，已安排顺丰快递上门取件 预计上门时间：${courierTime}
	 * 快递员姓名：${courierName} 快递员电话：${courierPhone}
	 * 如需查询订单状态可关注微信公众号"车检易 "查询，或致电021-60501999
	 * SMS_117520797:您好！您的爱车年检已办好，顺丰快递帮您寄回， 快递单号：${courierNumber} 快递查询电话：95338 如有疑问请致电：021-60501999
	 * SMS_115755097:您好！您的爱车可以验车了，需开通验车绿色通道，请提前一天确认验车时间和检测站。如未预约，需按实际验车情况排队等候，预约电话021-60501999
	 * SMS_115750107:您好！您的验车订单已收到，我们会尽快帮您查询违章，并安排就近检测站验车，稍后我们客服会联系您，或者致电021-60501999
	 * SMS_115760104:您的验证码为：${code}，该验证码30分钟内有效，请勿泄露与他人。
	 */

	/*
	 * 安排快递到客户哪里去取材料发送短信 预计上门时间： ${courierTime} 快递员姓名：${courierName}
	 * 快递员电话：${courierPhone}
	 */
	public static SendSmsResponse Arrangement_Express_Delivery_Sms(
			String phoneNo, String courierTime, String courierName,
			String courierPhone) throws ClientException {

		// 可自助调整超时时间
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");

		// 初始化acsClient,暂不支持region化
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou",
				accessKeyId, accessKeySecret);
		DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product,
				domain);
		IAcsClient acsClient = new DefaultAcsClient(profile);

		// 组装请求对象-具体描述见控制台-文档部分内容
		SendSmsRequest request = new SendSmsRequest();
		// 必填:待发送手机号
		request.setPhoneNumbers(phoneNo);
		// 必填:短信签名-可在短信控制台中找到
		request.setSignName("车检易");
		// 必填:短信模板-可在短信控制台中找到
		request.setTemplateCode(Arrangement_Express_Delivery_TemplateCode);
		request.setTemplateParam("{\"courierTime\":\"" + courierTime
				+ "\",\"courierName\":\"" + courierName
				+ "\",\"courierPhone\":\"" + courierPhone + "\"}");

		// 选填-上行短信扩展码(无特殊需求用户请忽略此字段)
		// request.setSmsUpExtendCode("90997");

		// 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
		request.setOutId("shjw109165057");

		// hint 此处可能会抛出异常，注意catch
		SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
		return sendSmsResponse;
	}

	/*
	 * 给客户寄送材料发送短信 courierNumber:快递单号
	 */
	public static SendSmsResponse Send_Materials_To_Customers_Sms(
			String phoneNo, String courierNumber) throws ClientException {

		// 可自助调整超时时间
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");

		// 初始化acsClient,暂不支持region化
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou",
				accessKeyId, accessKeySecret);
		DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product,
				domain);
		IAcsClient acsClient = new DefaultAcsClient(profile);

		// 组装请求对象-具体描述见控制台-文档部分内容
		SendSmsRequest request = new SendSmsRequest();
		// 必填:待发送手机号
		request.setPhoneNumbers(phoneNo);
		// 必填:短信签名-可在短信控制台中找到
		request.setSignName("车检易");
		// 必填:短信模板-可在短信控制台中找到
		request.setTemplateCode(Send_Materials_To_Customers_TemplateCode);
		request.setTemplateParam("{\"courierNumber\":\"" + courierNumber
				+ "\"}");

		// 选填-上行短信扩展码(无特殊需求用户请忽略此字段)
		// request.setSmsUpExtendCode("90997");

		// 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
		request.setOutId("shjw109165057");

		// hint 此处可能会抛出异常，注意catch
		SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
		return sendSmsResponse;
	}

	public static SendSmsResponse sendSms(String phoneNo, String templateCode,
			String msg) throws ClientException {

		// 可自助调整超时时间
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");

		// 初始化acsClient,暂不支持region化
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou",
				accessKeyId, accessKeySecret);
		DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product,
				domain);
		IAcsClient acsClient = new DefaultAcsClient(profile);

		// 组装请求对象-具体描述见控制台-文档部分内容
		SendSmsRequest request = new SendSmsRequest();
		// 必填:待发送手机号
		request.setPhoneNumbers(phoneNo);
		// 必填:短信签名-可在短信控制台中找到
		request.setSignName("车检易");
		// 必填:短信模板-可在短信控制台中找到
		request.setTemplateCode(templateCode);
		// 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
		request.setTemplateParam("{\"code\":\"" + msg + "\"}");

		// 选填-上行短信扩展码(无特殊需求用户请忽略此字段)
		// request.setSmsUpExtendCode("90997");

		// 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
		request.setOutId("shjw109165057");

		// hint 此处可能会抛出异常，注意catch
		SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);

		return sendSmsResponse;
	}

	public static QuerySendDetailsResponse querySendDetailss(String bizId)
			throws ClientException {

		// 可自助调整超时时间
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");

		// 初始化acsClient,暂不支持region化
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou",
				accessKeyId, accessKeySecret);
		DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product,
				domain);
		IAcsClient acsClient = new DefaultAcsClient(profile);

		// 组装请求对象
		QuerySendDetailsRequest request = new QuerySendDetailsRequest();
		// 必填-号码
		request.setPhoneNumber("13958016162");
		// 可选-流水号
		request.setBizId(bizId);
		// 必填-发送日期 支持30天内记录查询，格式yyyyMMdd
		SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd");
		request.setSendDate(ft.format(new Date()));
		// 必填-页大小
		request.setPageSize(10L);
		// 必填-当前页码从1开始计数
		request.setCurrentPage(1L);

		// hint 此处可能会抛出异常，注意catch
		QuerySendDetailsResponse querySendDetailsResponse = acsClient
				.getAcsResponse(request);

		return querySendDetailsResponse;
	}

	public static void main(String[] args) throws ClientException,
			InterruptedException {
		Arrangement_Express_Delivery_Sms("13958016162", "2017", "徐君", "139");
		/*
		 * // 发短信 SendSmsResponse response = sendSms("13958016162",
		 * YYNJ_TemplateCode, "");
		 * System.out.println("短信接口返回的数据----------------");
		 * System.out.println("Code=" + response.getCode());
		 * System.out.println("Message=" + response.getMessage());
		 * System.out.println("RequestId=" + response.getRequestId());
		 * System.out.println("BizId=" + response.getBizId());
		 * 
		 * Thread.sleep(3000L);
		 * 
		 * // 查明细 if (response.getCode() != null &&
		 * response.getCode().equals("OK")) { QuerySendDetailsResponse
		 * querySendDetailsResponse = querySendDetailss(response .getBizId());
		 * System.out.println("短信明细查询接口返回数据----------------");
		 * System.out.println("Code=" + querySendDetailsResponse.getCode());
		 * System.out.println("Message=" +
		 * querySendDetailsResponse.getMessage()); int i = 0; for
		 * (QuerySendDetailsResponse.SmsSendDetailDTO smsSendDetailDTO :
		 * querySendDetailsResponse .getSmsSendDetailDTOs()) {
		 * System.out.println("SmsSendDetailDTO[" + i + "]:");
		 * System.out.println("Content=" + smsSendDetailDTO.getContent());
		 * System.out.println("ErrCode=" + smsSendDetailDTO.getErrCode());
		 * System.out.println("OutId=" + smsSendDetailDTO.getOutId());
		 * System.out .println("PhoneNum=" + smsSendDetailDTO.getPhoneNum());
		 * System.out.println("ReceiveDate=" +
		 * smsSendDetailDTO.getReceiveDate()); System.out .println("SendDate=" +
		 * smsSendDetailDTO.getSendDate()); System.out.println("SendStatus=" +
		 * smsSendDetailDTO.getSendStatus()); System.out.println("Template=" +
		 * smsSendDetailDTO.getTemplateCode()); }
		 * System.out.println("TotalCount=" +
		 * querySendDetailsResponse.getTotalCount());
		 * System.out.println("RequestId=" +
		 * querySendDetailsResponse.getRequestId()); }
		 */

	}
}
