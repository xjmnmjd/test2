package com.sojson.common.controller;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UrlPathHelper;

import com.alibaba.fastjson.JSONArray;
import com.sojson.common.model.Bill;
import com.sojson.common.model.DrivingLicense;
import com.sojson.common.model.Gj;
import com.sojson.common.model.Infor;
import com.sojson.common.model.Order;
import com.sojson.common.model.Station;
import com.sojson.common.model.Station_hb;
import com.sojson.common.model.Station_nj;
import com.sojson.common.model.VehicleNature;
import com.sojson.common.model.VehicleType;
import com.sojson.common.model.WZ_img;
import com.sojson.common.model.WeixinOauth2Token;
import com.sojson.common.payutil.WxpayUtil;
import com.sojson.common.utils.AliyunSmsUtil;
import com.sojson.common.utils.BaseUtils;
import com.sojson.common.utils.CommonUtil;
import com.sojson.common.utils.CookieUtil;
import com.sojson.common.utils.LoggerUtils;
import com.sojson.common.utils.StringUtils;
import com.sojson.common.utils.VerifyCodeUtils;
import com.sojson.common.utils.WeixinUtil;
import com.sojson.common.utils.ocr.APPCode;
import com.sojson.common.utils.vcode.Captcha;
import com.sojson.common.utils.vcode.GifCaptcha;
import com.sojson.common.utils.vcode.SpecCaptcha;
import com.sojson.common.wxpay.WXPayUtil;
import com.sojson.common.xj.ResultObject;
import com.sojson.common.xj.XmltoJsonUtil;
import com.sojson.core.shiro.token.manager.TokenManager;
import com.sojson.order.service.GjService;
import com.sojson.permission.service.RoleService;
import com.sojson.system.service.HbStationService;
import com.sojson.system.service.NjStationService;
import com.sojson.system.service.StationService;
import com.sojson.system.service.VehicleNatureService;
import com.sojson.system.service.VehicleTypeService;
import com.sojson.wx.service.BillService;
import com.sojson.wx.service.DrivingLicenseService;
import com.sojson.wx.service.InforService;
import com.sojson.wx.service.OrderService;
import com.sojson.wx.service.WZ_imgService;

/**
 * 
 * @author xj
 * @version 1.0,2017年8月7日
 * 
 */
@Controller
@Scope(value = "prototype")
@RequestMapping("open")
public class CommonController extends BaseController {
	@Resource
	private RoleService roleService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private VehicleNatureService vehicleNatureService;
	@Autowired
	private VehicleTypeService vehicleTypeService;
	@Autowired
	private StationService stationService;
	@Autowired
	private InforService inforService;
	@Autowired
	private BillService billService;
	private static Random random = new Random();
	private LoggerUtils logger = new LoggerUtils();
	public static SimpleDateFormat formatter = new SimpleDateFormat(
			"yyyyMMddHHmmss");
	private static SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
	private static SimpleDateFormat xj_formatter = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	@Resource
	private HbStationService hbStationService;
	@Resource
	private NjStationService njStationService;
	@Autowired
	private WZ_imgService wz_imgService;
	@Autowired
	private GjService gjService;
	/**
	 * 2017-12-20
	 * 
	 * @author xj
	 */
	@Autowired
	private DrivingLicenseService drivingLicenseService;

	@RequestMapping("refreshDB")
	@ResponseBody
	public Map<String, Object> refreshDB() {
		roleService.initData();
		resultMap.put("status", 200);
		return resultMap;
	}

	/**
	 * 404错误
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("404")
	public ModelAndView _404(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("common/404");
		return view;
	}

	/**
	 * 500错误
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("500")
	public ModelAndView _500(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("common/500");

		Throwable t = (Throwable) request
				.getAttribute("javax.servlet.error.exception");
		String defaultMessage = "未知";
		if (null == t) {
			view.addObject("line", defaultMessage);
			view.addObject("clazz", defaultMessage);
			view.addObject("methodName", defaultMessage);
			return view;
		}
		String message = t.getMessage();// 错误信息
		StackTraceElement[] stack = t.getStackTrace();
		view.addObject("message", message);
		if (null != stack && stack.length != 0) {
			StackTraceElement element = stack[0];
			int line = element.getLineNumber();// 错误行号
			String clazz = element.getClassName();// 错误java类
			String fileName = element.getFileName();

			String methodName = element.getMethodName();// 错误方法
			view.addObject("line", line);
			view.addObject("clazz", clazz);
			view.addObject("methodName", methodName);
			LoggerUtils.fmtError(getClass(),
					"line:%s,clazz:%s,fileName:%s,methodName:%s()", line,
					clazz, fileName, methodName);
		}
		return view;
	}

	/**
	 * 获取验证码
	 * 
	 * @param response
	 */
	@RequestMapping(value = "getVCode", method = RequestMethod.GET)
	public void getVCode(HttpServletResponse response,
			HttpServletRequest request) {
		try {
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.setContentType("image/jpg");

			// 生成随机字串
			String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
			// 存入Shiro会话session
			TokenManager.setVal2Session(VerifyCodeUtils.V_CODE,
					verifyCode.toLowerCase());
			// 生成图片
			int w = 146, h = 43;
			VerifyCodeUtils.outputImage(w, h, response.getOutputStream(),
					verifyCode);
		} catch (Exception e) {
			LoggerUtils.fmtError(getClass(), e, "获取验证码异常：%s", e.getMessage());
		}
	}

	/**
	 * 获取验证码（Gif版本）
	 * 
	 * @param response
	 */
	@RequestMapping(value = "getGifCode", method = RequestMethod.GET)
	public void getGifCode(HttpServletResponse response,
			HttpServletRequest request) {
		try {
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.setContentType("image/gif");
			/**
			 * gif格式动画验证码 宽，高，位数。
			 */
			Captcha captcha = new GifCaptcha(146, 43, 4);
			// 输出
			ServletOutputStream out = response.getOutputStream();
			captcha.out(out);
			out.flush();
			// 存入Shiro会话session
			// System.out.println("jps"+captcha.text().toLowerCase());
			TokenManager.setVal2Session(VerifyCodeUtils.V_CODE, captcha.text()
					.toLowerCase());
		} catch (Exception e) {
			LoggerUtils.fmtError(getClass(), e, "获取验证码异常：%s", e.getMessage());
		}
	}

	/**
	 * 获取验证码（jpg版本）
	 * 
	 * @param response
	 */
	@RequestMapping(value = "getJPGCode", method = RequestMethod.GET)
	public void getJPGCode(HttpServletResponse response,
			HttpServletRequest request) {
		try {
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.setContentType("image/jpg");
			/**
			 * jgp格式验证码 宽，高，位数。
			 */
			Captcha captcha = new SpecCaptcha(146, 43, 4);
			// 输出
			ServletOutputStream out = response.getOutputStream();
			captcha.out(out);
			out.flush();
			// captcha.out(response.getOutputStream());
			TokenManager.setVal2Session(VerifyCodeUtils.V_CODE, captcha.text()
					.toLowerCase());
			/*
			 * HttpSession session = request.getSession(true); // 存入Session
			 * session.setAttribute("_code", captcha.text().toLowerCase());
			 */
		} catch (Exception e) {
			LoggerUtils.fmtError(getClass(), e, "获取验证码异常：%s", e.getMessage());
		}
	}

	/**
	 * 跳转到其他网站
	 * 
	 * @param url
	 * @return
	 */
	@RequestMapping(value = "www/open/goto", method = RequestMethod.GET)
	public ModelAndView _goto(String url) {

		return new ModelAndView("www/go_to", "url", url);
	}

	/**
	 * 踢出页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "kickedOut", method = RequestMethod.GET)
	public ModelAndView kickedOut(HttpServletRequest request, UrlPathHelper pp) {
		// 如果来源是null，那么就重定向到首页。这个时候，如果首页是要登录，那就会跳转到登录页
		if (StringUtils.isBlank(request.getHeader("Referer"))) {
			return redirect("/");
		}
		return new ModelAndView("common/kicked_out");
	}

	/**
	 * 没有权限提示页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "unauthorized", method = RequestMethod.GET)
	public ModelAndView unauthorized() {
		return new ModelAndView("common/unauthorized");
	}

	@RequestMapping(value = "shiro", method = RequestMethod.GET)
	public ModelAndView shiro() {
		return new ModelAndView("shiro");
	}

	/*
	 * 保存信息 xj 2017-8-4
	 */
	@RequestMapping(value = "saveInfo", method = RequestMethod.POST)
	public void saveInfo(HttpServletRequest request,
			HttpServletResponse response) {
		PrintWriter writer = null;
		JSONObject obj = new JSONObject();
		try {
			String state = request.getParameter("state");
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			writer = response.getWriter();
			if (state.equals("2")) {
				String uuid = request.getParameter("uuid");
				Order order = new Order();
				order.setOwner(request.getParameter("car_owner"));
				order.setLicenseplate(request.getParameter("car_no"));
				order.setCartype(request.getParameter("cllx"));
				order.setVehicle(request.getParameter("clxz"));
				order.setRigisterdate(request.getParameter("register_date"));
				order.setCarpath(request.getParameter("xsz_img"));
				order.setIdentificationno(request.getParameter("card_no"));
				order.setInsurancedate(request.getParameter("jq_date"));
				order.setIdentificationpath(request.getParameter("sfz_img"));
				order.setOpenid(request.getParameter("openId"));
				order.setUpdate_time(xj_formatter.format(new Date()));
				order.setIs_del(0);
				order.setUuid(uuid);
				orderService.updateOrder(order);
				obj.put("result", 1);
				obj.put("msg", "提交成功");
				obj.put("yzm", uuid);
				try {
					DrivingLicense drivingLicense = APPCode
							.getEntityByOCR(request.getParameter("xsz_img"));
					if (drivingLicense != null) {
						drivingLicense.setImg_path(request
								.getParameter("xsz_img"));
						drivingLicense
								.setOpenId(request.getParameter("openId"));
						drivingLicense.setInsert_time(xj_formatter
								.format(new Date()));
						drivingLicenseService.insert(drivingLicense);
					}
				} catch (Exception e) {
				}
			} else {
				String yzm = request.getParameter("yzm_code");
				if (yzm != null && yzm != "") {
					String sms_code = CookieUtil.findCookieByName(request,
							"sms_code");
					if (sms_code != null && sms_code != "") {
						if (yzm.equals(sms_code)) {
							CookieUtil.clearCookie(request, response,
									"sms_code");
							Order order = new Order();
							order.setOwner(request.getParameter("car_owner"));
							order.setLicenseplate(request
									.getParameter("car_no"));
							order.setCartype(request.getParameter("cllx"));
							order.setVehicle(request.getParameter("clxz"));
							order.setRigisterdate(request
									.getParameter("register_date"));
							order.setCarpath(request.getParameter("xsz_img"));
							order.setIdentificationno(request
									.getParameter("card_no"));
							order.setInsurancedate(request
									.getParameter("jq_date"));
							order.setIdentificationpath(request
									.getParameter("sfz_img"));
							order.setOpenid(request.getParameter("openId"));
							order.setPhonenumber(request
									.getParameter("phone_no"));
							order.setSubmittime(xj_formatter.format(new Date()));
							order.setOrdersource(request.getParameter("orderSource"));
							String uuid = WXPayUtil.generateUUID();
							order.setUuid(uuid);
							orderService.insertOrder(order);
							obj.put("result", 1);
							obj.put("msg", "提交成功");
							obj.put("yzm", uuid);
							try {
								DrivingLicense drivingLicense = APPCode
										.getEntityByOCR(request
												.getParameter("xsz_img"));
								if (drivingLicense != null) {
									drivingLicense.setImg_path(request
											.getParameter("xsz_img"));
									drivingLicense.setOpenId(request
											.getParameter("openId"));
									drivingLicense.setInsert_time(xj_formatter
											.format(new Date()));
									drivingLicenseService
											.insert(drivingLicense);
								}
							} catch (Exception e) {
							}
						} else {
							obj.put("result", 0);
							obj.put("msg", "验证码不正确");
						}
					} else {
						obj.put("result", 0);
						obj.put("msg", "验证码已失效，请重新发送");
					}
				} else {
					obj.put("result", 0);
					obj.put("msg", "验证码不能为空");
				}
			}
		} catch (Exception e) {
			obj.put("result", 0);
			obj.put("msg", "服务器异常");
			logger.error(CommonController.class, "saveInfo", e);
		} finally {
			if (writer != null) {
				writer.print(obj);
				writer.close();
			}
		}

	}

	/*
	 * 保存上线检车信息
	 */
	@RequestMapping(value = "updateOrderSXJC", method = RequestMethod.POST)
	public void updateOrderSXJC(HttpServletRequest request,
			HttpServletResponse response) {
		PrintWriter writer = null;
		JSONObject obj = new JSONObject();
		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			writer = response.getWriter();
			String uuid = request.getParameter("uuid");
			if (uuid != null && uuid.trim().length() > 0) {
				Order order = new Order();
				order.setInjury_accident_last_year(Integer.parseInt(request
						.getParameter("injury_accident_last_year")));
				order.setUuid(uuid);
				order.setIs_collect_materials(Integer.parseInt(request
						.getParameter("is_collect_materials")));
				order.setCollect_materials_time(request
						.getParameter("collect_materials_time"));
				order.setCollect_materials_address(request
						.getParameter("collect_materials_address"));
				order.setSend_material_address(request
						.getParameter("send_material_address"));
				order.setIs_collect_car(Integer.parseInt(request
						.getParameter("is_collect_car")));
				order.setCollect_car_time(request
						.getParameter("collect_car_time"));
				order.setCollect_car_address(request
						.getParameter("collect_car_address"));
				order.setUpdate_time(xj_formatter.format(new Date()));
				order.setNjdq_date(request.getParameter("njdq_date"));
				orderService.updateOrderSXJC(order);
				obj.put("result", 1);
			} else {
				obj.put("result", 0);
				obj.put("msg", "参数异常");
			}
		} catch (Exception e) {
			obj.put("result", 0);
			obj.put("msg", "服务异常");
		} finally {
			if (writer != null) {
				writer.print(obj);
				writer.close();
			}
		}
	}

	/*
	 * 根据uuid获取order
	 */
	@RequestMapping(value = "findByUUID", method = RequestMethod.POST)
	public void findByUUID(HttpServletRequest request,
			HttpServletResponse response) {
		PrintWriter writer = null;
		JSONObject obj = new JSONObject();
		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			writer = response.getWriter();
			String uuid = request.getParameter("uuid");
			String openId = request.getParameter("openId");
			if (uuid != null && uuid.trim().length() > 0 && openId != null
					&& openId.trim().length() > 0) {
				Order order = orderService.findByUUID(uuid, openId);
				if (order != null && order.getOrderid() > 0) {
					obj.put("result", 1);
					obj.put("entity", order);
				} else {
					obj.put("result", 0);
					obj.put("msg", "该订单不存在");
				}
			} else {
				obj.put("result", 0);
				obj.put("msg", "请求参数异常");
			}
		} catch (Exception e) {
			obj.put("result", 0);
			obj.put("msg", "服务异常");
		} finally {
			if (writer != null) {
				writer.print(obj);
				writer.close();
			}
		}
	}

	/*
	 * 根据orderno获取order
	 */
	@RequestMapping(value = "findByOrderNo", method = RequestMethod.POST)
	public void findByOrderNo(HttpServletRequest request,
			HttpServletResponse response) {
		PrintWriter writer = null;
		JSONObject obj = new JSONObject();
		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			writer = response.getWriter();
			String orderno = request.getParameter("orderno");
			String openId = request.getParameter("openId");
			if (orderno != null && orderno.trim().length() > 0) {
				Order order = orderService.findPayOkByOrderNo(orderno, openId);
				if (order != null && order.getOrderid() > 0) {
					obj.put("result", 1);
					obj.put("entity", order);
				} else {
					obj.put("result", 0);
					obj.put("msg", "该订单不存在");
				}
			} else {
				obj.put("result", 0);
				obj.put("msg", "参数异常");
			}
		} catch (Exception e) {
			obj.put("result", 0);
			obj.put("msg", "服务异常");
		} finally {
			if (writer != null) {
				writer.print(obj);
				writer.close();
			}
		}
	}

	/*
	 * 根据openid获取order列表
	 */
	@RequestMapping(value = "findOrderByOpenId", method = RequestMethod.POST)
	public void findOrderByOpenId(HttpServletRequest request,
			HttpServletResponse response) {
		PrintWriter writer = null;
		JSONObject obj = new JSONObject();
		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			writer = response.getWriter();
			String openid = request.getParameter("openid");
			String status = request.getParameter("status");
			if (openid != null && openid.trim().length() > 0 && status != null
					&& status.trim().length() > 0) {
				List<Order> list = orderService.findByOpenId(openid,
						Integer.parseInt(status));
				obj.put("result", 1);
				obj.put("list", list);
			} else {
				obj.put("result", 0);
				obj.put("msg", "参数异常");
			}
		} catch (Exception e) {
			obj.put("result", 0);
			obj.put("msg", "服务异常");
		} finally {
			if (writer != null) {
				writer.print(obj);
				writer.close();
			}
		}
	}

	/*
	 * 根据uuid删除order
	 */
	@RequestMapping(value = "delOrderByOpenId", method = RequestMethod.GET)
	public void delOrderByOpenId(HttpServletRequest request,
			HttpServletResponse response) {
		PrintWriter writer = null;
		JSONObject obj = new JSONObject();
		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			writer = response.getWriter();
			String uuid = request.getParameter("uuid");
			if (uuid != null && uuid.trim().length() > 0) {
				orderService.delOrderByUUID(uuid);
				obj.put("result", 1);
				obj.put("msg", "操作成功");
			} else {
				obj.put("result", 0);
				obj.put("msg", "参数异常");
			}
		} catch (Exception e) {
			obj.put("result", 0);
			obj.put("msg", "服务异常");
		} finally {
			if (writer != null) {
				writer.print(obj);
				writer.close();
			}
		}
	}

	/*
	 * 根据OrderNo删除order
	 */
	@RequestMapping(value = "delOrderByOrderNo", method = RequestMethod.GET)
	public void delOrderByOrderNo(HttpServletRequest request,
			HttpServletResponse response) {
		PrintWriter writer = null;
		JSONObject obj = new JSONObject();
		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			writer = response.getWriter();
			String orderno = request.getParameter("orderno");
			if (orderno != null && orderno.trim().length() > 0) {
				orderService.delOrderByOrderNo(orderno);
				obj.put("result", 1);
				obj.put("msg", "操作成功");
			} else {
				obj.put("result", 0);
				obj.put("msg", "参数异常");
			}
		} catch (Exception e) {
			obj.put("result", 0);
			obj.put("msg", "服务异常");
		} finally {
			if (writer != null) {
				writer.print(obj);
				writer.close();
			}
		}
	}

	/**
	 * 发送验证码
	 * 
	 * @return
	 */
	@RequestMapping(value = "sendCode", method = RequestMethod.POST)
	public void sendCode(HttpServletRequest request,
			HttpServletResponse response) {
		PrintWriter writer = null;
		JSONObject obj = new JSONObject();
		String phoneNo = request.getParameter("phoneNo");
		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			if (phoneNo != null && phoneNo.trim() != "") {
				/*
				 * List<XJ> list = (List<XJ>) VCache.getList("phoneNo"); boolean
				 * flag = false; if (list != null && list.size() > 0) { for (XJ
				 * per : list) { if (BaseUtils.accessTokenExpireTimeCompare(
				 * per.init_time, BaseUtils.getDateSecond())) {
				 * list.remove(per); } else { if (per.phone_no.equals(phoneNo))
				 * { flag = true; } } } } else { list = new ArrayList<XJ>(); }
				 * if (flag) { obj.put("result", 0); obj.put("msg",
				 * "该手机号短信已发送"); logger.error(CommonController.class,
				 * "该手机号短信已发送，请过一分钟再发送"); } else {
				 */
				String code = "";
				for (int i = 0; i < 4; i++) {
					code += random.nextInt(10);
				}
				CookieUtil.addCookie(response, "sms_code", code, 30 * 60);
				/*
				 * XJ xj = new XJ(); xj.phone_no = phoneNo; xj.init_time =
				 * BaseUtils.getDateSecond(); list.add(xj);
				 */
				AliyunSmsUtil.sendSms(phoneNo,
						AliyunSmsUtil.SMS_YZM_TemplateCode, code);
				writer = response.getWriter();
				obj.put("result", 1);
				// }
				// VCache.setList("phoneNo", list);
			} else {
				obj.put("result", 0);
				obj.put("msg", "手机号不能为空");
				logger.error(CommonController.class, "手机号不能为空");
			}
		} catch (Exception e) {
			obj.put("result", 0);
			obj.put("msg", "短信发送失败");
			logger.error(CommonController.class, "sendCode", e);
		} finally {
			if (writer != null) {
				writer.print(obj);
				writer.close();
			}
		}

	}

	/*
	 * 上传照片
	 */
	@RequestMapping(value = "upload", method = RequestMethod.POST)
	public void uploadBase64(HttpServletRequest request,
			HttpServletResponse response) {
		PrintWriter writer = null;
		JSONObject obj = new JSONObject();
		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			String date = format.format(new Date());
			String path = request.getSession().getServletContext()
					.getRealPath("/photo/" + date + "/");
			String saveFileName = CommonUtil.saveBase64File(
					request.getParameter("base64"), path);
			writer = response.getWriter();
			obj.put("result", 1);
			obj.put("path",
					request.getScheme() + "://" + request.getServerName() + ":"
							+ request.getServerPort() + "/shjw/photo/" + date
							+ "/" + saveFileName);
		} catch (Exception e) {
			obj.put("result", 0);
		} finally {
			if (writer != null) {
				writer.print(obj);
				writer.close();
			}
		}
	}

	/*
	 * 获取车辆性质和车辆实体 author xj 2017-8-22 14:15
	 */
	@RequestMapping(value = "getCarTypeNature", method = RequestMethod.POST)
	public void getCarTypeNature(HttpServletRequest request,
			HttpServletResponse response) {
		PrintWriter writer = null;
		JSONObject obj = new JSONObject();
		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			writer = response.getWriter();
			List<VehicleType> vehicleTypeList = vehicleTypeService
					.selectAllType();
			List<VehicleNature> vehicleNatureList = vehicleNatureService
					.listnature();
			obj.put("result", 1);
			obj.put("vehicleTypeList", vehicleTypeList);
			obj.put("vehicleNatureList", vehicleNatureList);
		} catch (Exception e) {
			obj.put("result", 0);
		} finally {
			if (writer != null) {
				writer.print(obj);
				writer.close();
			}
		}
	}

	/*
	 * 获取全部检测站 author xj 2017-8-22 16:52
	 */
	@RequestMapping(value = "getAllStation", method = RequestMethod.POST)
	public void getAllStation(HttpServletRequest request,
			HttpServletResponse response) {
		PrintWriter writer = null;
		JSONObject obj = new JSONObject();
		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			writer = response.getWriter();
			obj.put("result", 1);
			obj.put("stationList", stationService.getAll());
		} catch (Exception e) {
			obj.put("result", 0);
		} finally {
			if (writer != null) {
				writer.print(obj);
				writer.close();
			}
		}
	}

	/*
	 * 提交预约 author xj 2017-8-23 14:30
	 */
	@RequestMapping(value = "updatePay", method = RequestMethod.POST)
	public void updatePay(HttpServletRequest request,
			HttpServletResponse response) {
		PrintWriter writer = null;
		JSONObject obj = new JSONObject();
		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			String nj_fee = request.getParameter("nj_fee");
			String wq_fee = request.getParameter("wq_fee");
			String hb_nj_fee = request.getParameter("hb_nj_fee");
			String wt_fee = request.getParameter("wt_fee");
			String totalorder = request.getParameter("totalorder");
			String uuid = request.getParameter("uuid");
			String station_id = request.getParameter("station_id");
			String station = request.getParameter("station");
			writer = response.getWriter();
			if (uuid != null && uuid.trim().length() > 0) {
				Order order = new Order();
				order.setNj_fee(Double.parseDouble(nj_fee));
				order.setWq_fee(Double.parseDouble(wq_fee));
				order.setHb_nj_fee(Double.parseDouble(hb_nj_fee));
				order.setWt_fee(Double.parseDouble(wt_fee));
				order.setTotalorder(Double.parseDouble(totalorder));
				order.setUuid(uuid);
				order.setStation_id(Integer.parseInt(station_id));
				order.setStation(station);
				order.setUpdate_time(xj_formatter.format(new Date()));
				order.setOrderno("JW" + formatter.format(new Date())
						+ (random.nextInt(999) + 1000));
				order.setOrderstatus(1);
				orderService.updatePay(order);
				order = orderService.findPhoneByOrderNo(order.getOrderno());
				AliyunSmsUtil.sendSms(order.getPhonenumber(),
						AliyunSmsUtil.ORDER_TemplateCode, "");
				obj.put("result", 1);
			} else {
				obj.put("result", 1);
				obj.put("msg", "请求参数异常");
			}
		} catch (Exception e) {
			obj.put("result", 0);
			obj.put("msg", "服务器异常");
		} finally {
			if (writer != null) {
				writer.print(obj);
				writer.close();
			}
		}
	}

	/*
	 * 根据订单号获取检测站 author xj 2017-9-14 16:52
	 */
	@RequestMapping(value = "getStationByOrderNo", method = RequestMethod.GET)
	public void getStationByOrderNo(HttpServletRequest request,
			HttpServletResponse response) {
		PrintWriter writer = null;
		JSONObject obj = new JSONObject();
		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			String orderno = request.getParameter("orderno");
			String openId = request.getParameter("openId");
			writer = response.getWriter();
			if (orderno != null && orderno.length() > 0) {
				Order order = orderService.findByOrderNo(orderno, openId);
				if (order != null && order.getOrderid() > 0) {
					int stationId = order.getStation_id();
					Station station = stationService
							.selectOneStation(stationId);
					if (station == null) {
						station = new Station();
					}
					obj.put("station", station);
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("nj_name", order.getCartype());
					map.put("station_id", stationId);
					Map<String, Object> nj_map = orderService.getStationNJ(map);
					if (nj_map != null) {
						obj.put("nj_fee", nj_map.get("nj_fee_a").toString());
						obj.put("wq_fee", nj_map.get("nj_fee_b").toString());
					}
					map.put("hb_name", order.getVehicle());
					Map<String, Object> hb_map = orderService.getStationHB(map);
					if (hb_map != null) {
						obj.put("hb_time_a", hb_map.get("hb_time_a").toString());
						obj.put("hb_time_b", hb_map.get("hb_time_b").toString());
						obj.put("hb_time_c", hb_map.get("hb_time_c").toString());
						obj.put("hb_time_d", hb_map.get("hb_time_d").toString());
					}
					String wt_car_number = order.getLicenseplate().substring(0,
							2);
					Map<String, Object> wt_map = stationService
							.getStationWT(wt_car_number);
					List<WZ_img> wz_img_list = wz_imgService
							.getWZ_imgByOrderNo(orderno);
					obj.put("order", order);
					obj.put("wt", wt_map);
					obj.put("wz_img_list", wz_img_list);
					obj.put("result", 1);
				} else {
					obj.put("result", 0);
					obj.put("msg", "请求参数异常");
				}
			} else {
				obj.put("result", 0);
				obj.put("msg", "请求参数异常");
			}
		} catch (Exception e) {
			obj.put("result", 0);
			obj.put("msg", "服务器异常");
		} finally {
			if (writer != null) {
				writer.print(obj);
				writer.close();
			}
		}
	}

	/*
	 * 根据主键获取检测站 author xj 2017-8-22 16:52
	 */
	@RequestMapping(value = "getStationById", method = RequestMethod.GET)
	public void getStationById(HttpServletRequest request,
			HttpServletResponse response) {
		PrintWriter writer = null;
		JSONObject obj = new JSONObject();
		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			String uuid = request.getParameter("uuid");
			String stationId = request.getParameter("stationId");
			String openId = request.getParameter("openId");
			writer = response.getWriter();
			if (uuid != null && uuid.length() > 0 && stationId != null
					&& stationId.length() > 0 && openId != null
					&& openId.length() > 0) {
				Order order = orderService.findByUUID(uuid, openId);
				if (order != null && order.getOrderid() > 0) {
					Station station = stationService.selectOneStation(Integer
							.parseInt(stationId));
					if (station == null) {
						station = new Station();
					}
					obj.put("station", station);
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("nj_name", order.getCartype());
					map.put("station_id", stationId);
					Map<String, Object> nj_map = orderService.getStationNJ(map);
					if (nj_map != null) {
						obj.put("nj_fee", nj_map.get("nj_fee_a").toString());
						obj.put("wq_fee", nj_map.get("nj_fee_b").toString());
					}
					map.put("hb_name", order.getVehicle());
					Map<String, Object> hb_map = orderService.getStationHB(map);
					if (hb_map != null) {
						obj.put("hb_time_a", hb_map.get("hb_time_a").toString());
						obj.put("hb_time_b", hb_map.get("hb_time_b").toString());
						obj.put("hb_time_c", hb_map.get("hb_time_c").toString());
						obj.put("hb_time_d", hb_map.get("hb_time_d").toString());
					}
					String wt_car_number = order.getLicenseplate().substring(0,
							2);
					Map<String, Object> wt_map = stationService
							.getStationWT(wt_car_number);
					obj.put("order", order);
					obj.put("wt", wt_map);
					obj.put("result", 1);
				} else {
					obj.put("result", 0);
					obj.put("msg", "请求参数异常");
				}
			} else {
				obj.put("result", 0);
				obj.put("msg", "请求参数异常");
			}
		} catch (Exception e) {
			obj.put("result", 0);
			obj.put("msg", "服务器异常");
		} finally {
			if (writer != null) {
				writer.print(obj);
				writer.close();
			}
		}
	}

	@RequestMapping(value = "getStationFeeById", method = RequestMethod.GET)
	public void getStationFeeById(HttpServletRequest request,
			HttpServletResponse response) {
		PrintWriter writer = null;
		JSONObject obj = new JSONObject();
		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			String stationId = request.getParameter("stationId");
			writer = response.getWriter();
			if (stationId != null && stationId.length() > 0) {
				List<Station_hb> hbList = new ArrayList<Station_hb>();
				List<Station_nj> njList = new ArrayList<Station_nj>();
				try {
					int sid = Integer.parseInt(stationId);
					hbList = hbStationService.hbList(sid);
					njList = njStationService.njlist(sid);
				} catch (Exception e) {
				}
				obj.put("station_hb_list", hbList);
				obj.put("station_nj_list", njList);
				obj.put("result", 1);
			} else {
				obj.put("result", 0);
				obj.put("msg", "请求参数异常");
			}
		} catch (Exception e) {
			obj.put("result", 0);
			obj.put("msg", "服务器异常");
		} finally {
			if (writer != null) {
				writer.print(obj);
				writer.close();
			}
		}
	}

	@RequestMapping(value = "createWXMenu", method = RequestMethod.GET)
	public void createWXMenu(HttpServletRequest request,
			HttpServletResponse response) {
		PrintWriter writer = null;
		JSONObject obj = new JSONObject();
		try {
			String accessToken = BaseUtils.getAccessToken();
			BaseUtils.createWXMenu(accessToken, BaseUtils.createWXMenuData());
			writer = response.getWriter();
			obj.put("result", 1);
			obj.put("msg", "操作成功");
		} catch (Exception e) {
			obj.put("result", 0);
			obj.put("msg", "服务器异常");
		} finally {
			if (writer != null) {
				writer.print(obj);
				writer.close();
			}
		}
	}

	/*
	 * 修改订单是否预约定金 是否开发票等信息 author xj 2017-8-25 14:30
	 */
	@RequestMapping(value = "updateOrderPay", method = RequestMethod.POST)
	public void updateOrderPay(HttpServletRequest request,
			HttpServletResponse response) {
		PrintWriter writer = null;
		JSONObject obj = new JSONObject();
		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			String is_bill = request.getParameter("is_bill");
			String is_deposit_payment = request
					.getParameter("is_deposit_payment");
			String payment_amount = request.getParameter("payment_amount");
			String uuid = request.getParameter("uuid");
			String openId = request.getParameter("openId");
			writer = response.getWriter();
			if (uuid != null && uuid.trim().length() > 0 && openId != null
					&& openId.trim().length() > 0) {
				Order order = new Order();
				order.setIs_bill(Integer.parseInt(is_bill));
				order.setIs_deposit_payment(Integer
						.parseInt(is_deposit_payment));
				// order.setPayment_amount(Double.parseDouble(payment_amount));
				order.setUuid(uuid);
				order.setUpdate_time(xj_formatter.format(new Date()));
				orderService.updateOrderPay(order);
				String order_no = request.getParameter("order_no");
				SortedMap<String, String> parame = new TreeMap<String, String>();
				parame.put("appid", BaseUtils.AppID);
				parame.put("mch_id", BaseUtils.MchID);// 商家账号。
				parame.put("device_info", "WEB");
				parame.put("body", "上海锦鹄验车收费");// 商品描述
				String randomStr = CommonUtil.getRandomString(18).toUpperCase();
				parame.put("nonce_str", randomStr);// 随机字符串
				parame.put("attach", "支付测试"); // 商品描述
				parame.put("detail", "商品描述"); // 商品描述
				parame.put("notify_url", BaseUtils.NOTIFYURL);// 回调地址
				parame.put("out_trade_no", order_no);// 商户订单编号
				String ip = CommonUtil.getIpAddr(request);
				if (StringUtils.isEmpty(ip)) {
					parame.put("spbill_create_ip", "127.0.0.1");// 消费IP地址
				} else {
					parame.put("spbill_create_ip", ip);// 消费IP地址
				}
				// 支付金额
				parame.put(
						"total_fee",
						(int) Math.abs(Double.parseDouble(payment_amount) * 100)
								+ "");
				// 临时用1分钱进行测试
				// parame.put("total_fee", "1");
				parame.put("trade_type", BaseUtils.TRADETYPE);// 交易类型
				parame.put("fee_type", "CNY"); // 银行币种
				parame.put("input_charset", "UTF-8"); // 字符集
				parame.put("openid", openId);
				parame.put("sign_type", "MD5");
				String sign = WXPayUtil.generateSignature(parame,
						BaseUtils.APISecret);
				parame.put("sign", sign);// 数字签证
				String reqBody = WxpayUtil.buildXML(parame);
				String UTF8 = "UTF-8";
				URL httpUrl = new URL(
						"https://api.mch.weixin.qq.com/pay/unifiedorder");
				HttpURLConnection httpURLConnection = (HttpURLConnection) httpUrl
						.openConnection();
				httpURLConnection.setRequestProperty("Host",
						"api.mch.weixin.qq.com");
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
				com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSONObject
						.parseObject(XmltoJsonUtil.xml2JSON(resp));
				com.alibaba.fastjson.JSONObject result_xml = jsonObject
						.getJSONObject("xml");
				JSONArray result_code = result_xml.getJSONArray("result_code");
				if (result_code != null && result_code.size() > 0) {
					String code = (String) result_code.get(0);
					if (code.equalsIgnoreCase("FAIL")) {
						obj.put("result", 0);
						obj.put("msg", result_xml.getJSONArray("err_code_des")
								.get(0) + "");
					} else if (code.equalsIgnoreCase("SUCCESS")) {
						JSONArray prepay_id = result_xml
								.getJSONArray("prepay_id");
						String prepayId = (String) prepay_id.get(0);
						obj.put("result", 1);
						int time = (int) (System.currentTimeMillis() / 1000);
						String pay_noncestr = CommonUtil.getRandomString(18)
								.toUpperCase();
						obj.put("msg", "预支付成功");
						obj.put("appid", BaseUtils.AppID);
						obj.put("noncestr", pay_noncestr);
						obj.put("timestamp", String.valueOf(time));
						obj.put("packageNo", "prepay_id=" + prepayId);
						SortedMap<String, String> payMap = new TreeMap<String, String>();
						payMap.put("appId", BaseUtils.AppID);
						payMap.put("timeStamp", String.valueOf(time));
						payMap.put("nonceStr", pay_noncestr);
						payMap.put("package", "prepay_id=" + prepayId);
						payMap.put("signType", "MD5");
						String paySign = WXPayUtil.generateSignature(payMap,
								BaseUtils.APISecret);
						obj.put("paySign", paySign);
						obj.put("signType", "MD5");
					}
				} else {
					obj.put("result", 0);
					obj.put("msg", result_xml.getJSONArray("return_msg").get(0)
							+ "");
				}
			}
		} catch (Exception e) {
			obj.put("result", 0);
			obj.put("msg", e.getMessage());
		} finally {
			if (writer != null) {
				writer.print(obj);
				writer.close();
			}
		}

	}

	/**
	 * 微信订单回调接口
	 * 
	 * @return
	 */
	@RequestMapping(value = "/notify", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public void notify(HttpServletRequest request, HttpServletResponse response) {
		ResultObject result = new ResultObject();// 返回数据结果集合
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			response.setHeader("Access-Control-Allow-Origin", "*");
			InputStream in = request.getInputStream();
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = in.read(buffer)) != -1) {
				out.write(buffer, 0, len);
			}
			out.close();
			in.close();
			String content = new String(out.toByteArray(), "utf-8");// xml数据
			com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSONObject
					.parseObject(XmltoJsonUtil.xml2JSON(content));
			com.alibaba.fastjson.JSONObject result_xml = jsonObject
					.getJSONObject("xml");
			System.out.println(result_xml);
			JSONArray result_code = result_xml.getJSONArray("result_code");
			String code = (String) result_code.get(0);
			if (code.equalsIgnoreCase("FAIL")) {
				result.setMsg("微信统一订单下单失败");
				result.setResultCode("-1");
				response.getWriter().write(CommonUtil.setXml("SUCCESS", "OK"));
			} else if (code.equalsIgnoreCase("SUCCESS")) {
				result.setMsg("微信统一订单下单成功");
				result.setResultCode("1");
				JSONArray out_trade_no = result_xml
						.getJSONArray("out_trade_no");// 订单编号
				Order order = new Order();
				order.setOrderstatus(2);
				order.setOrderno((String) out_trade_no.get(0));
				try {
					order.setTime_end(result_xml.getJSONArray("time_end")
							.getString(0));
					String cash_fee = result_xml.getJSONArray("cash_fee")
							.getString(0);
					order.setCash_fee(cash_fee);
					order.setResult_code(result_xml.getJSONArray("result_code")
							.getString(0));
					order.setTransaction_id(result_xml.getJSONArray(
							"transaction_id").getString(0));
					order.setErr_code_des(result_xml.getJSONArray(
							"err_code_des").getString(0));
					order.setPayment_amount(Double.parseDouble(cash_fee) / 100);
				} catch (Exception e) {
				}
				orderService.updateOrderState(order);
				response.getWriter().write(CommonUtil.setXml("SUCCESS", "OK"));
			}
		} catch (Exception e) {
			result.setMsg(e.getMessage());
			result.setResultCode("-1");
			return;
		}

	}

	/*
	 * 根据code获取openid
	 */
	@RequestMapping(value = "getOpenId", method = RequestMethod.GET)
	public String getOpenId(HttpServletRequest request,
			HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = null;
		JSONObject obj = new JSONObject();
		try {
			request.setCharacterEncoding("UTF-8");
			writer = response.getWriter();
			// 用户同意授权后，能获取到code
			String code = request.getParameter("code");
			// 用户同意授权
			if (!"authdeny".equals(code)) {
				// 获取网页授权access_token,应用的appid和appsecret
				WeixinOauth2Token weixinOauth2Token = BaseUtils
						.getOauth2AccessToken(BaseUtils.AppID,
								BaseUtils.AppSecret, code);
				if (weixinOauth2Token != null) {
					obj.put("result", 1);
					obj.put("openId", weixinOauth2Token.getOpenId());
				} else {
					obj.put("result", 0);
					logger.error(CommonController.class,
							"CommonController--getOpenId--weixinOauth2Token为空");
				}
			} else {
				obj.put("result", 0);
				logger.error(CommonController.class,
						"CommonController--getOpenId--授权失败");
			}
			writer.print(obj);
		} catch (Exception e) {
			logger.error(CommonController.class, "getOpenId", e);
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
		return null;
	}

	/*
	 * 根据orderNo获取发票
	 */
	@RequestMapping(value = "getBillByOrderNo", method = RequestMethod.POST)
	public void getBillByOrderNo(HttpServletRequest request,
			HttpServletResponse response) {
		PrintWriter writer = null;
		JSONObject obj = new JSONObject();
		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			writer = response.getWriter();
			String orderno = request.getParameter("orderno");
			if (orderno != null && orderno.trim().length() > 0) {
				Bill bill = billService.getBillByOrderNo(orderno);
				if (bill == null) {
					bill = new Bill();
				}
				obj.put("result", 1);
				obj.put("entity", bill);
			} else {
				obj.put("result", 0);
				obj.put("msg", "参数异常");
			}
		} catch (Exception e) {
			obj.put("result", 0);
			obj.put("msg", "服务异常");
		} finally {
			writer.print(obj);
			if (writer != null) {
				writer.close();
			}
		}
	}

	/*
	 * 根据orderNo添加发票、修改发票
	 */
	@RequestMapping(value = "addOrUpdateBill", method = RequestMethod.POST)
	public void addOrUpdateBill(HttpServletRequest request,
			HttpServletResponse response) {
		PrintWriter writer = null;
		JSONObject obj = new JSONObject();
		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			writer = response.getWriter();
			String orderno = request.getParameter("orderno");
			String edit_type = request.getParameter("edit_type");
			System.out.println("ss" + orderno);
			if (orderno != null && orderno.trim().length() > 0
					&& edit_type != null && edit_type.trim().length() > 0) {
				Bill bill = new Bill();
				bill.setBank_no(request.getParameter("bank_no"));
				bill.setBill_head(request.getParameter("bill_head"));
				bill.setBill_type(request.getParameter("bill_type"));
				bill.setCompany_address(request.getParameter("company_address"));
				bill.setCompany_phone(request.getParameter("company_phone"));
				bill.setContact_number(request.getParameter("contact_number"));
				bill.setMailing_address(request.getParameter("mailing_address"));
				bill.setOpening_bank(request.getParameter("opening_bank"));
				bill.setOrder_no(orderno);
				bill.setTaxpayer_identification_number(request
						.getParameter("taxpayer_identification_number"));
				if (edit_type.equals("add")) {
					bill.setInsert_time(xj_formatter.format(new Date()));
					billService.insert(bill);
					obj.put("msg", "保存成功");
					obj.put("result", 1);
				} else if (edit_type.equals("update")) {
					bill.setUpdate_time(xj_formatter.format(new Date()));
					billService.update(bill);
					obj.put("msg", "修改成功");
					obj.put("result", 1);
				} else {
					obj.put("result", 0);
					obj.put("msg", "参数异常");
				}
			} else {
				obj.put("result", 0);
				obj.put("msg", "参数异常");
			}
		} catch (Exception e) {
			obj.put("result", 0);
			obj.put("msg", "服务异常");
		} finally {
			writer.print(obj);
			if (writer != null) {
				writer.close();
			}
		}
	}

	/*
	 * 获取签名
	 */
	@RequestMapping(value = "getSign", method = RequestMethod.GET)
	public String getSign(HttpServletRequest request,
			HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = null;
		JSONObject obj = new JSONObject();
		try {
			request.setCharacterEncoding("UTF-8");
			writer = response.getWriter();
			String url = request.getParameter("link");
			Map<String, String> sign = WeixinUtil.getSign(url);
			obj.put("result", 1);
			obj.put("sign", sign);
			obj.put("appId", BaseUtils.AppID);
		} catch (Exception e) {
			obj.put("result", 0);
		} finally {
			writer.print(obj);
			if (writer != null) {
				writer.close();
			}
		}
		return null;
	}

	/*
	 * 保存客户留言信息
	 */
	@RequestMapping(value = "saveContactInfor", method = RequestMethod.POST)
	public void saveContactInfor(HttpServletRequest request,
			HttpServletResponse response) {
		PrintWriter writer = null;
		JSONObject obj = new JSONObject();
		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			writer = response.getWriter();
			String openId = request.getParameter("openId");
			if (openId != null && openId.trim().length() > 0) {
				Infor infor = new Infor();
				infor.setContact_name(request.getParameter("contact_name"));
				infor.setContact_phone(request.getParameter("contact_phone"));
				infor.setInfor(request.getParameter("contact_infor"));
				infor.setInsert_time(xj_formatter.format(new Date()));
				infor.setOpenid(openId);
				infor.setState(0);
				inforService.insert(infor);
				obj.put("result", 1);
			} else {
				obj.put("result", 0);
				obj.put("msg", "参数异常");
			}
		} catch (Exception e) {
			obj.put("result", 0);
			obj.put("msg", "服务异常");
		} finally {
			if (writer != null) {
				writer.print(obj);
				writer.close();
			}
		}
	}

	@RequestMapping(value = "/getPhoto", method = RequestMethod.POST)
	@ResponseBody
	public Object getPhoto(String media_id, HttpServletRequest request,
			HttpServletResponse response) throws NoSuchAlgorithmException {
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/media/get?access_token="
				+ WeixinUtil.getAccessToken() + "&media_id=" + media_id;
		InputStream inputStream = null;
		String saveFileName = null;
		PrintWriter writer = null;
		JSONObject obj = new JSONObject();
		try {
			writer = response.getWriter();
			URL url = new URL(requestUrl);
			HttpURLConnection httpUrlConn = (HttpURLConnection) url
					.openConnection();
			httpUrlConn.setDoOutput(false);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			httpUrlConn.setRequestMethod("POST");
			httpUrlConn.connect();
			// 将返回的输入流转换成字符串
			inputStream = httpUrlConn.getInputStream();
			byte[] data = new byte[1024];
			int len = 0;
			FileOutputStream fileOutputStream = null;
			saveFileName = System.currentTimeMillis()
					+ RandomStringUtils.random(6, true, true) + ".jpg";
			Date date = new Date();
			String datePath = format.format(date);
			String upload = "D:\\shjw\\photo\\" + datePath + "\\";
			String path = upload + saveFileName;
			File dir = new File(upload);
			if (!dir.exists()) {
				FileUtils.forceMkdir(dir);
			}
			try {
				fileOutputStream = new FileOutputStream(path);
				while ((len = inputStream.read(data)) != -1) {
					fileOutputStream.write(data, 0, len);
				}
				fileOutputStream.flush();
			} catch (IOException e) {
			} finally {
				if (inputStream != null) {
					try {
						inputStream.close();
					} catch (IOException e) {
					}
				}
				if (fileOutputStream != null) {
					try {
						fileOutputStream.close();
					} catch (IOException e) {
					}
				}
			}
			obj.put("result", 1);
			obj.put("path", "/shjw/showImage?imgId=" + datePath + "_"
					+ saveFileName);
		} catch (Exception e) {
			obj.put("result", 0);
		} finally {
			writer.print(obj);
			if (writer != null) {
				writer.close();
			}
		}
		return null;
	}

	/**
	 * @author xj 2017-12-21 获取订单状态轨迹
	 */
	@RequestMapping(value = "findOrderSateByOrderNo", method = RequestMethod.POST)
	public void findOrderSateByOrderNo(HttpServletRequest request,
			HttpServletResponse response) {
		PrintWriter writer = null;
		JSONObject obj = new JSONObject();
		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			writer = response.getWriter();
			String orderno = request.getParameter("orderno");
			String openId = request.getParameter("openId");
			String state = request.getParameter("state");
			if (orderno != null && orderno.trim().length() > 0
					&& openId != null && openId.trim().length() > 0
					&& state != null && state.trim().length() > 0) {
				List<Gj> list = new ArrayList<Gj>();
				if (state.equals("2")) {
					list = gjService.findByOrderNo(orderno);
				}
				Order order = orderService.findByOrderNo(orderno, openId);
				if (order != null && order.getOrderid() > 0) {
					obj.put("result", 1);
					obj.put("order", order);
					if (list == null) {
						list = new ArrayList<Gj>();
					}
					obj.put("list", list);
				} else {
					obj.put("result", 0);
					obj.put("msg", "该订单不存在");
				}
			} else {
				obj.put("result", 0);
				obj.put("msg", "参数异常");
			}
		} catch (Exception e) {
			obj.put("result", 0);
			obj.put("msg", "服务异常");
		} finally {
			if (writer != null) {
				writer.print(obj);
				writer.close();
			}
		}
	}
}
