package com.sojson.order.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.aliyuncs.exceptions.ClientException;
import com.sojson.common.controller.BaseController;
import com.sojson.common.model.Bill;
import com.sojson.common.model.ChangeRecord;
import com.sojson.common.model.Chargeback;
import com.sojson.common.model.Dy;
import com.sojson.common.model.Gj;
import com.sojson.common.model.Hf;
import com.sojson.common.model.JW_Email;
import com.sojson.common.model.Message;
import com.sojson.common.model.Order;
import com.sojson.common.model.OrderXx;
import com.sojson.common.model.Pay;
import com.sojson.common.model.Station;
import com.sojson.common.model.UUser;
import com.sojson.common.model.Up_date;
import com.sojson.common.model.VehicleNature;
import com.sojson.common.model.VehicleType;
import com.sojson.common.model.WZ_img;
import com.sojson.common.utils.AliyunSmsUtil;
import com.sojson.core.shiro.token.manager.TokenManager;
import com.sojson.message.service.MessService;
import com.sojson.order.service.ChargebackService;
import com.sojson.order.service.DyService;
import com.sojson.order.service.EmailService;
import com.sojson.order.service.GjService;
import com.sojson.order.service.HfService;
import com.sojson.order.service.IChangeRecord;
import com.sojson.order.service.IOrderService;
import com.sojson.order.service.KdService;
import com.sojson.order.service.MyWz_imgService;
import com.sojson.order.service.PayService;
import com.sojson.order.service.Up_dateService;
import com.sojson.order.util.ExcelOperate;
import com.sojson.order.util.ExcelOperate_xx;
import com.sojson.system.service.StationService;
import com.sojson.system.service.VehicleNatureService;
import com.sojson.system.service.VehicleTypeService;
import com.sojson.user.service.UUserService;
import com.sojson.wx.service.BillService;

@Controller
@Scope(value = "prototype")
@RequestMapping("order")
public class IOrderController extends BaseController {
	@Resource
	IOrderService iOrderService;
	@Resource
	StationService stationService;
	@Resource
	VehicleTypeService vehicleTypeService;
	@Resource
	VehicleNatureService vehicleNatureService;
	@Autowired
	HfService hfService;
	@Autowired
	UUserService userService;
	@Resource
	KdService kdService;
	@Resource
	BillService billService;
	@Resource
	PayService payService;
	@Resource
	DyService dyService;
	@Resource
	GjService gjService;
	@Resource
	MyWz_imgService myWz_imgService;
	@Resource
	Up_dateService upService;
	@Resource
	EmailService emailService;
	@Resource
	MessService messService;
	@Resource
	IChangeRecord ichangeRecord;
	@Resource
	ChargebackService chargebackService;
	String insert_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
			.format(new Date());
	String order_time = new SimpleDateFormat("yyyyMMddHHmmss")
			.format(new Date());
	private static SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String insert_time1 = new SimpleDateFormat("yyyy年MM月dd日")
			.format(new Date());

	/**
	 * 全部订单列表
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "orderList")
	public ModelAndView list(ModelMap map, String status, String pageIndex,
			String search, Order order, String tj_start, String tj_end,
			String yy_start, String yy_end, String s_status)
			throws UnsupportedEncodingException {
		if (pageIndex == null || pageIndex.equals("")) {
			pageIndex = "1";
		}
		int apageindex = Integer.parseInt(pageIndex);

		Long id = TokenManager.getUserId();
		UUser user = userService.selectByPrimaryKey(id);
		long userid = 0;
		String station ="";
/*		if (user.getPosition().equals("客服员")) {
			userid = user.getId();
		}*/
     	if (user.getPosition().equals("验车员")) {
			station = user.getStation();
			userid = user.getId();
		}

		String search_v = "";
		int page = (apageindex - 1) * 10;
		String x_tj_start = tj_start;
		String x_tj_end = tj_end;

		if (tj_start != null && !tj_start.equals("")) {
			x_tj_start += " 00:00:00";
		}
		if (tj_end != null && !tj_end.equals("")) {
			x_tj_end += " 00:00:00";
		}
		if (search != null) {
			search_v = URLDecoder.decode(search, "UTF-8");
		}
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.clear();
		map1.put("search_v", search_v);
		map1.put("tj_start", x_tj_start);
		map1.put("tj_end", x_tj_end);
		map1.put("yy_start", yy_start);
		map1.put("yy_end", yy_end);

		int counts = 0;
		Map<String, Object> mapx = new HashMap<String, Object>();
		if ("".equals(s_status) || s_status == null) {
			s_status = "0";
		}
		if ((tj_start == null || "".equals(tj_start))
				&& (tj_end == null || "".equals(tj_end))
				&& (yy_start == null || "".equals(yy_start))
				&& (yy_end == null || "".equals(yy_end))
				&& (search == null || "".equals(search))
				&& (order.getOwner() == null || "".equals(order.getOwner()))
				&& (order.getLicenseplate() == null || "".equals(order.getLicenseplate()))
				&& (order.getPhonenumber() == null || "".equals(order.getPhonenumber()))
				&& (order.getSuditstatus() == 12 || order.getSuditstatus() == 0)) {
			map1.put("page", page);
			order.setSuditstatus(12);
			map1.put("order", order);
			map1.put("userid", userid);
			map1.put("station", station);
			mapx.put("station", station);
			mapx.put("userid", userid);

			/*
			 * List<Map<String, Object>> list1 =
			 * iOrderService.orderListPage(map1); counts =
			 * iOrderService.getCountOrder(); xlist=list1;
			 */

			counts = iOrderService.getCountOrder(mapx);
		} else {
			map1.put("page", -1);
			s_status = "1";
			if (order.getSuditstatus() == 0) {
				order.setSuditstatus(12);
			}
			if (order.getSuditstatus() == 13) {
				order.setSuditstatus(0);
			}
			map1.put("order", order);
		}

		if ("".equals(status) || status == null) {
			status = "0";
		}

		List<Map<String, Object>> list1 = iOrderService.orderListPage(map1);
		// int counts=list.size();
		int total = (counts % 10 == 0) ? (counts / 10) : (counts / 10 + 1);
		// 未处理订单提示
		OrderXx ox = new OrderXx();
		int fp_count = iOrderService.nfpCountOrder(mapx);
		int js_counts = iOrderService.njsCountOrder(mapx);
		int wz_counts = iOrderService.wzCountOrder(mapx);
		int zl_counts = iOrderService.zlCountOrder(mapx);
		int wt_counts = iOrderService.wtCountOrder(mapx);
		int jf_counts = iOrderService.jfCountOrder(mapx);
		int yy_counts = iOrderService.yyCountOrder(mapx);
		int nj_counts = iOrderService.njCountOrder(mapx);
		int hf_counts = iOrderService.hfCountOrder(mapx);
		ox.setFp_count(fp_count);
		ox.setJs_counts(js_counts);
		ox.setWz_counts(wz_counts);
		ox.setZl_counts(zl_counts);
		ox.setWt_counts(wt_counts);
		ox.setJf_counts(jf_counts);
		ox.setYy_counts(yy_counts);
		ox.setNj_counts(nj_counts);
		ox.setHf_counts(hf_counts);
		map.put("ox", ox);

		map.put("status", status);
		map.put("orderList", list1);
		map.put("pageIndex", apageindex);
		map.put("total", total);
		map.put("user", user);
		if (order.getSuditstatus() == 0) {
			order.setSuditstatus(13);
		}
		map.put("xorder", order);
		map.put("tj_start", tj_start);
		map.put("tj_end", tj_end);
		map.put("yy_start", yy_start);
		map.put("yy_end", yy_end);
		map.put("s_status", Integer.parseInt(s_status));
		map.put("search", search_v);
		return new ModelAndView("order/order_pay");
		// return new ModelAndView("common/401");
	}

	/**
	 * 待分配订单查询
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "order_nfp")
	public ModelAndView order_nfp(ModelMap map, String status,
			String pageIndex, String fp_search, Order order, String tj_start,
			String tj_end, String yy_start, String yy_end, String s_status)
			throws UnsupportedEncodingException {
		if (pageIndex == null || pageIndex.equals("")) {
			pageIndex = "1";
		}
		int apageindex = Integer.parseInt(pageIndex);

		Long id = TokenManager.getUserId();
		UUser user = userService.selectByPrimaryKey(id);
		long userid = 0;
		if (user.getPosition().equals("客服员")) {
			userid = user.getId();
		}

		String search_v = "";
		int page = (apageindex - 1) * 10;
		String x_tj_start = tj_start;
		String x_tj_end = tj_end;

		if (tj_start != null && !tj_start.equals("")) {
			x_tj_start += " 00:00:00";
		}
		if (tj_end != null && !tj_end.equals("")) {
			x_tj_end += " 00:00:00";
		}
		if (fp_search != null) {
			search_v = URLDecoder.decode(fp_search, "UTF-8");
		}
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("search_v", search_v);
		map1.put("order", order);
		map1.put("tj_start", x_tj_start);
		map1.put("tj_end", x_tj_end);
		map1.put("yy_start", yy_start);
		map1.put("yy_end", yy_end);

		Map<String, Object> mapx = new HashMap<String, Object>();
		mapx.put("userid", userid);

		if ((tj_start == null || "".equals(tj_start))
				&& (tj_end == null || "".equals(tj_end))
				&& (yy_start == null || "".equals(yy_start))
				&& (yy_end == null || "".equals(yy_end))
				&& (fp_search == null || "".equals(fp_search))
				&& (order.getOwner() == null || "".equals(order.getOwner()))
				&& (order.getLicenseplate() == null || "".equals(order.getLicenseplate()))
				&& (order.getPhonenumber() == null || "".equals(order.getPhonenumber()))) {
			map1.put("page", page);
		} else {
			map1.put("page", -1);
			s_status = "1";
		}
		if ("".equals(s_status) || s_status == null) {
			s_status = "0";
		}
		List<Map<String, Object>> list = iOrderService.order_nfp_Page(map1);

		if ("".equals(status) || status == null) {
			status = "0";
		}

		int counts = iOrderService.nfpCountOrder(mapx);
		// int counts=list.size();
		int total = (counts % 10 == 0) ? (counts / 10) : (counts / 10 + 1);

		// 未处理订单提示
		OrderXx ox = new OrderXx();
		int fp_count = iOrderService.nfpCountOrder(mapx);
		int js_counts = iOrderService.njsCountOrder(mapx);
		int wz_counts = iOrderService.wzCountOrder(mapx);
		int zl_counts = iOrderService.zlCountOrder(mapx);
		int wt_counts = iOrderService.wtCountOrder(mapx);
		int jf_counts = iOrderService.jfCountOrder(mapx);
		int yy_counts = iOrderService.yyCountOrder(mapx);
		int nj_counts = iOrderService.njCountOrder(mapx);
		int hf_counts = iOrderService.hfCountOrder(mapx);
		ox.setFp_count(fp_count);
		ox.setJs_counts(js_counts);
		ox.setWz_counts(wz_counts);
		ox.setZl_counts(zl_counts);
		ox.setWt_counts(wt_counts);
		ox.setJf_counts(jf_counts);
		ox.setYy_counts(yy_counts);
		ox.setNj_counts(nj_counts);
		ox.setHf_counts(hf_counts);

		map.put("ox", ox);
		map.put("status", status);
		map.put("nfplist", list);
		map.put("pageIndex", apageindex);
		map.put("total", total);
		map.put("user", user);
		map.put("counts", counts);

		map.put("xorder", order);
		map.put("tj_start", tj_start);
		map.put("tj_end", tj_end);
		map.put("yy_start", yy_start);
		map.put("yy_end", yy_end);
		map.put("fp_search", search_v);
		map.put("s_status", Integer.parseInt(s_status));
		return new ModelAndView("order/order_fp");
	}

	/**
	 * 待接受订单列表
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "order_njs")
	public ModelAndView order_njs(ModelMap map, String status,
			String pageIndex, String js_search, Order order, String tj_start,
			String tj_end, String yy_start, String yy_end, String s_status)
			throws UnsupportedEncodingException {
		if (pageIndex == null || pageIndex.equals("")) {
			pageIndex = "1";
		}
		int apageindex = Integer.parseInt(pageIndex);

		Long id = TokenManager.getUserId();
		UUser user = userService.selectByPrimaryKey(id);
		long userid = 0;
		if (user.getPosition().equals("客服员")) {
			userid = user.getId();
		}

		String search_v = "";
		int page = (apageindex - 1) * 10;
		String x_tj_start = tj_start;
		String x_tj_end = tj_end;

		if (tj_start != null && !tj_start.equals("")) {
			x_tj_start += " 00:00:00";
		}
		if (tj_end != null && !tj_end.equals("")) {
			x_tj_end += " 00:00:00";
		}
		if (js_search != null) {
			search_v = URLDecoder.decode(js_search, "UTF-8");
		}
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("search_v", search_v);
		map1.put("order", order);
		map1.put("tj_start", x_tj_start);
		map1.put("tj_end", x_tj_end);
		map1.put("yy_start", yy_start);
		map1.put("yy_end", yy_end);
		map1.put("userid", userid);
		Map<String, Object> mapx = new HashMap<String, Object>();
		mapx.put("userid", userid);
		if ((tj_start == null || "".equals(tj_start))
				&& (tj_end == null || "".equals(tj_end))
				&& (yy_start == null || "".equals(yy_start))
				&& (yy_end == null || "".equals(yy_end))
				&& (js_search == null || "".equals(js_search))
				&& (order.getOwner() == null || "".equals(order.getOwner()))
				&& (order.getLicenseplate() == null || "".equals(order.getLicenseplate()))
				&& (order.getPhonenumber() == null || "".equals(order.getPhonenumber()))) {
			map1.put("page", page);
		} else {
			s_status = "1";
			map1.put("page", -1);
		}
		if ("".equals(s_status) || s_status == null) {
			s_status = "0";
		}
		List<Map<String, Object>> list = iOrderService.order_njs_Page(map1);
		if ("".equals(status) || status == null) {
			status = "0";
		}

		int counts = iOrderService.njsCountOrder(mapx);
		// int counts=list.size();
		int total = (counts % 10 == 0) ? (counts / 10) : (counts / 10 + 1);

		// 未处理订单提示
		OrderXx ox = new OrderXx();
		int fp_count = iOrderService.nfpCountOrder(mapx);
		int js_counts = iOrderService.njsCountOrder(mapx);
		int wz_counts = iOrderService.wzCountOrder(mapx);
		int zl_counts = iOrderService.zlCountOrder(mapx);
		int wt_counts = iOrderService.wtCountOrder(mapx);
		int jf_counts = iOrderService.jfCountOrder(mapx);
		int yy_counts = iOrderService.yyCountOrder(mapx);
		int nj_counts = iOrderService.njCountOrder(mapx);
		int hf_counts = iOrderService.hfCountOrder(mapx);
		ox.setFp_count(fp_count);
		ox.setJs_counts(js_counts);
		ox.setWz_counts(wz_counts);
		ox.setZl_counts(zl_counts);
		ox.setWt_counts(wt_counts);
		ox.setJf_counts(jf_counts);
		ox.setYy_counts(yy_counts);
		ox.setNj_counts(nj_counts);
		ox.setHf_counts(hf_counts);
		map.put("ox", ox);
		map.put("status", status);

		map.put("njslist", list);
		map.put("pageIndex", apageindex);
		map.put("total", total);
		map.put("user", user);

		map.put("xorder", order);
		map.put("tj_start", tj_start);
		map.put("tj_end", tj_end);
		map.put("yy_start", yy_start);
		map.put("yy_end", yy_end);

		map.put("js_search", search_v);
		map.put("s_status", Integer.parseInt(s_status));
		return new ModelAndView("order/order_js");
	}

	/**
	 * 违章查询订单
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "order_wzcx")
	public ModelAndView order_wzcx(ModelMap map, String status,
			String pageIndex, String wz_search, Order order, String tj_start,
			String tj_end, String yy_start, String yy_end, String s_status)
			throws UnsupportedEncodingException {

		wz_cl_Order();
		if (pageIndex == null || pageIndex.equals("")) {
			pageIndex = "1";
		}
		int apageindex = Integer.parseInt(pageIndex);

		Long id = TokenManager.getUserId();
		UUser user = userService.selectByPrimaryKey(id);
		long userid = 0;
/*		if (user.getPosition().equals("客服员")) {
			userid = user.getId();
		}*/

		String search_v = "";
		int page = (apageindex - 1) * 10;
		String x_tj_start = tj_start;
		String x_tj_end = tj_end;

		if (tj_start != null && !tj_start.equals("")) {
			x_tj_start += " 00:00:00";
		}
		if (tj_end != null && !tj_end.equals("")) {
			x_tj_end += " 00:00:00";
		}
		if (wz_search != null) {
			search_v = URLDecoder.decode(wz_search, "UTF-8");
		}
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("search_v", search_v);
		map1.put("order", order);
		map1.put("tj_start", x_tj_start);
		map1.put("tj_end", x_tj_end);
		map1.put("yy_start", yy_start);
		map1.put("yy_end", yy_end);
		map1.put("userid", userid);
		Map<String, Object> mapx = new HashMap<String, Object>();
		mapx.put("userid", userid);

		if ((tj_start == null || "".equals(tj_start))
				&& (tj_end == null || "".equals(tj_end))
				&& (yy_start == null || "".equals(yy_start))
				&& (yy_end == null || "".equals(yy_end))
				&& (wz_search == null || "".equals(wz_search))
				&& (order.getOwner() == null || "".equals(order.getOwner()))
				&& (order.getLicenseplate() == null || "".equals(order.getLicenseplate()))
				&& (order.getPhonenumber() == null || "".equals(order.getPhonenumber()))) {
			map1.put("page", page);
		} else {
			s_status = "1";
			map1.put("page", -1);
		}
		if ("".equals(s_status) || s_status == null) {
			s_status = "0";
		}
		List<Map<String, Object>> list = iOrderService.order_wzcx_Page(map1);

		if ("".equals(status) || status == null) {
			status = "0";
		}
		int counts = iOrderService.wzCountOrder(mapx);
		// int counts=list.size();
		int total = (counts % 10 == 0) ? (counts / 10) : (counts / 10 + 1);
		// 未处理订单提示
		OrderXx ox = new OrderXx();
		int fp_count = iOrderService.nfpCountOrder(mapx);
		int js_counts = iOrderService.njsCountOrder(mapx);
		int wz_counts = iOrderService.wzCountOrder(mapx);
		int zl_counts = iOrderService.zlCountOrder(mapx);
		int wt_counts = iOrderService.wtCountOrder(mapx);
		int jf_counts = iOrderService.jfCountOrder(mapx);
		int yy_counts = iOrderService.yyCountOrder(mapx);
		int nj_counts = iOrderService.njCountOrder(mapx);
		int hf_counts = iOrderService.hfCountOrder(mapx);
		ox.setFp_count(fp_count);
		ox.setJs_counts(js_counts);
		ox.setWz_counts(wz_counts);
		ox.setZl_counts(zl_counts);
		ox.setWt_counts(wt_counts);
		ox.setJf_counts(jf_counts);
		ox.setYy_counts(yy_counts);
		ox.setNj_counts(nj_counts);
		ox.setHf_counts(hf_counts);

		map.put("ox", ox);
		map.put("status", status);
		map.put("wzslist", list);
		map.put("pageIndex", apageindex);
		map.put("total", total);
		map.put("user", user);

		map.put("xorder", order);
		map.put("tj_start", tj_start);
		map.put("tj_end", tj_end);
		map.put("yy_start", yy_start);
		map.put("yy_end", yy_end);

		map.put("wz_search", search_v);
		map.put("s_status", Integer.parseInt(s_status));
		return new ModelAndView("order/order_wz");
	}

	/**
	 * 资料收集订单
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "order_zlsj")
	public ModelAndView order_zlsj(ModelMap map, String status,
			String pageIndex, String zl_search, Order order, String tj_start,
			String tj_end, String yy_start, String yy_end, String s_status)
			throws UnsupportedEncodingException {
		if (pageIndex == null || pageIndex.equals("")) {
			pageIndex = "1";
		}
		int apageindex = Integer.parseInt(pageIndex);

		Long id = TokenManager.getUserId();
		UUser user = userService.selectByPrimaryKey(id);
		long userid = 0;
/*		if (user.getPosition().equals("客服员")) {
			userid = user.getId();
		}*/

		String search_v = "";
		int page = (apageindex - 1) * 10;
		String x_tj_start = tj_start;
		String x_tj_end = tj_end;

		if (tj_start != null && !tj_start.equals("")) {
			x_tj_start += " 00:00:00";
		}
		if (tj_end != null && !tj_end.equals("")) {
			x_tj_end += " 00:00:00";
		}
		if (zl_search != null) {
			search_v = URLDecoder.decode(zl_search, "UTF-8");
		}
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("search_v", search_v);
		map1.put("order", order);
		map1.put("tj_start", x_tj_start);
		map1.put("tj_end", x_tj_end);
		map1.put("yy_start", yy_start);
		map1.put("yy_end", yy_end);
		map1.put("userid", userid);
		Map<String, Object> mapx = new HashMap<String, Object>();
		mapx.put("userid", userid);

		if ((tj_start == null || "".equals(tj_start))
				&& (tj_end == null || "".equals(tj_end))
				&& (yy_start == null || "".equals(yy_start))
				&& (yy_end == null || "".equals(yy_end))
				&& (zl_search == null || "".equals(zl_search))
				&& (order.getOwner() == null || "".equals(order.getOwner()))
				&& (order.getLicenseplate() == null || "".equals(order.getLicenseplate()))
				&& (order.getPhonenumber() == null || "".equals(order.getPhonenumber()))) {
			map1.put("page", page);
		} else {
			s_status = "1";
			map1.put("page", -1);
		}
		if ("".equals(s_status) || s_status == null) {
			s_status = "0";
		}
		List<Map<String, Object>> list = iOrderService.order_zlsj_Page(map1);
		if ("".equals(status) || status == null) {
			status = "0";
		}

		int counts = iOrderService.zlCountOrder(mapx);
		// int counts=list.size();
		int total = (counts % 10 == 0) ? (counts / 10) : (counts / 10 + 1);

		// 未处理订单提示
		OrderXx ox = new OrderXx();
		int fp_count = iOrderService.nfpCountOrder(mapx);
		int js_counts = iOrderService.njsCountOrder(mapx);
		int wz_counts = iOrderService.wzCountOrder(mapx);
		int zl_counts = iOrderService.zlCountOrder(mapx);
		int wt_counts = iOrderService.wtCountOrder(mapx);
		int jf_counts = iOrderService.jfCountOrder(mapx);
		int yy_counts = iOrderService.yyCountOrder(mapx);
		int nj_counts = iOrderService.njCountOrder(mapx);
		int hf_counts = iOrderService.hfCountOrder(mapx);
		ox.setFp_count(fp_count);
		ox.setJs_counts(js_counts);
		ox.setWz_counts(wz_counts);
		ox.setZl_counts(zl_counts);
		ox.setWt_counts(wt_counts);
		ox.setJf_counts(jf_counts);
		ox.setYy_counts(yy_counts);
		ox.setNj_counts(nj_counts);
		ox.setHf_counts(hf_counts);
		map.put("ox", ox);
		map.put("status", status);
		map.put("zllist", list);
		map.put("pageIndex", apageindex);
		map.put("total", total);
		map.put("user", user);

		map.put("xorder", order);
		map.put("tj_start", tj_start);
		map.put("tj_end", tj_end);
		map.put("yy_start", yy_start);
		map.put("yy_end", yy_end);
		map.put("zl_search", search_v);
		map.put("s_status", Integer.parseInt(s_status));
		return new ModelAndView("order/order_zlsj");
	}

	/**
	 * 委托办理订单
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "order_wtbl")
	public ModelAndView order_wtbl(ModelMap map, String status,
			String pageIndex, String wt_search, Order order, String tj_start,
			String tj_end, String yy_start, String yy_end, String s_status)
			throws UnsupportedEncodingException {
		if (pageIndex == null || pageIndex.equals("")) {
			pageIndex = "1";
		}
		int apageindex = Integer.parseInt(pageIndex);

		Long id = TokenManager.getUserId();
		UUser user = userService.selectByPrimaryKey(id);
		long userid = 0;
/*		if (user.getPosition().equals("客服员")) {
			userid = user.getId();
		}*/

		String search_v = "";
		int page = (apageindex - 1) * 10;
		String x_tj_start = tj_start;
		String x_tj_end = tj_end;

		if (tj_start != null && !tj_start.equals("")) {
			x_tj_start += " 00:00:00";
		}
		if (tj_end != null && !tj_end.equals("")) {
			x_tj_end += " 00:00:00";
		}
		if (wt_search != null) {
			search_v = URLDecoder.decode(wt_search, "UTF-8");
		}
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("search_v", search_v);
		map1.put("order", order);
		map1.put("tj_start", x_tj_start);
		map1.put("tj_end", x_tj_end);
		map1.put("yy_start", yy_start);
		map1.put("yy_end", yy_end);
		map1.put("userid", userid);
		Map<String, Object> mapx = new HashMap<String, Object>();
		mapx.put("userid", userid);

		if ((tj_start == null || "".equals(tj_start))
				&& (tj_end == null || "".equals(tj_end))
				&& (yy_start == null || "".equals(yy_start))
				&& (yy_end == null || "".equals(yy_end))
				&& (wt_search == null || "".equals(wt_search))
				&& (order.getOwner() == null || "".equals(order.getOwner()))
				&& (order.getLicenseplate() == null || "".equals(order.getLicenseplate()))
				&& (order.getPhonenumber() == null || "".equals(order.getPhonenumber()))) {
			map1.put("page", page);
		} else {
			s_status = "1";
			map1.put("page", -1);
		}
		if ("".equals(s_status) || s_status == null) {
			s_status = "0";
		}
		List<Map<String, Object>> list = iOrderService.order_wtbl_Page(map1);
		if ("".equals(status) || status == null) {
			status = "0";
		}

		int counts = iOrderService.wtCountOrder(mapx);
		// int counts=list.size();
		int total = (counts % 10 == 0) ? (counts / 10) : (counts / 10 + 1);
		// 未处理订单提示
		OrderXx ox = new OrderXx();
		int fp_count = iOrderService.nfpCountOrder(mapx);
		int js_counts = iOrderService.njsCountOrder(mapx);
		int wz_counts = iOrderService.wzCountOrder(mapx);
		int zl_counts = iOrderService.zlCountOrder(mapx);
		int wt_counts = iOrderService.wtCountOrder(mapx);
		int jf_counts = iOrderService.jfCountOrder(mapx);
		int yy_counts = iOrderService.yyCountOrder(mapx);
		int nj_counts = iOrderService.njCountOrder(mapx);
		int hf_counts = iOrderService.hfCountOrder(mapx);
		ox.setFp_count(fp_count);
		ox.setJs_counts(js_counts);
		ox.setWz_counts(wz_counts);
		ox.setZl_counts(zl_counts);
		ox.setWt_counts(wt_counts);
		ox.setJf_counts(jf_counts);
		ox.setYy_counts(yy_counts);
		ox.setNj_counts(nj_counts);
		ox.setHf_counts(hf_counts);
		map.put("ox", ox);
		map.put("status", status);
		map.put("wtlist", list);
		map.put("pageIndex", apageindex);
		map.put("total", total);
		map.put("user", user);

		map.put("xorder", order);
		map.put("tj_start", tj_start);
		map.put("tj_end", tj_end);
		map.put("yy_start", yy_start);
		map.put("yy_end", yy_end);
		map.put("wt_search", search_v);
		map.put("s_status", Integer.parseInt(s_status));
		return new ModelAndView("order/order_wt");
	}

	/**
	 * 缴费确认订单
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "order_jfqr")
	public ModelAndView order_jfqr(ModelMap map, String status,
			String pageIndex, String jf_search, Order order, String tj_start,
			String tj_end, String yy_start, String yy_end, String s_status)
			throws UnsupportedEncodingException {
		if (pageIndex == null || pageIndex.equals("")) {
			pageIndex = "1";
		}
		int apageindex = Integer.parseInt(pageIndex);

		Long id = TokenManager.getUserId();
		UUser user = userService.selectByPrimaryKey(id);
		long userid = 0;
/*		if (user.getPosition().equals("客服员")) {
			userid = user.getId();
		}*/

		String search_v = "";
		int page = (apageindex - 1) * 10;
		String x_tj_start = tj_start;
		String x_tj_end = tj_end;

		if (tj_start != null && !tj_start.equals("")) {
			x_tj_start += " 00:00:00";
		}
		if (tj_end != null && !tj_end.equals("")) {
			x_tj_end += " 00:00:00";
		}
		if (jf_search != null) {
			search_v = URLDecoder.decode(jf_search, "UTF-8");
		}
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("search_v", search_v);
		map1.put("order", order);
		map1.put("tj_start", x_tj_start);
		map1.put("tj_end", x_tj_end);
		map1.put("yy_start", yy_start);
		map1.put("yy_end", yy_end);
		map1.put("userid", userid);
		Map<String, Object> mapx = new HashMap<String, Object>();
		mapx.put("userid", userid);
		if ((tj_start == null || "".equals(tj_start))
				&& (tj_end == null || "".equals(tj_end))
				&& (yy_start == null || "".equals(yy_start))
				&& (yy_end == null || "".equals(yy_end))
				&& (jf_search == null || "".equals(jf_search))
				&& (order.getOwner() == null || "".equals(order.getOwner()))
				&& (order.getLicenseplate() == null || "".equals(order.getLicenseplate()))
				&& (order.getPhonenumber() == null || "".equals(order.getPhonenumber()))) {
			map1.put("page", page);
		} else {
			s_status = "1";
			map1.put("page", -1);
		}
		if ("".equals(s_status) || s_status == null) {
			s_status = "0";
		}
		List<Map<String, Object>> list = iOrderService.order_jfqr_Page(map1);
		if ("".equals(status) || status == null) {
			status = "0";
		}
		// int counts=list.size();
		int counts = iOrderService.jfCountOrder(mapx);
		int total = (counts % 10 == 0) ? (counts / 10) : (counts / 10 + 1);

		// 未处理订单提示
		OrderXx ox = new OrderXx();
		int fp_count = iOrderService.nfpCountOrder(mapx);
		int js_counts = iOrderService.njsCountOrder(mapx);
		int wz_counts = iOrderService.wzCountOrder(mapx);
		int zl_counts = iOrderService.zlCountOrder(mapx);
		int wt_counts = iOrderService.wtCountOrder(mapx);
		int jf_counts = iOrderService.jfCountOrder(mapx);
		int yy_counts = iOrderService.yyCountOrder(mapx);
		int nj_counts = iOrderService.njCountOrder(mapx);
		int hf_counts = iOrderService.hfCountOrder(mapx);
		ox.setFp_count(fp_count);
		ox.setJs_counts(js_counts);
		ox.setWz_counts(wz_counts);
		ox.setZl_counts(zl_counts);
		ox.setWt_counts(wt_counts);
		ox.setJf_counts(jf_counts);
		ox.setYy_counts(yy_counts);
		ox.setNj_counts(nj_counts);
		ox.setHf_counts(hf_counts);
		map.put("ox", ox);
		map.put("status", status);
		map.put("jflist", list);
		map.put("pageIndex", apageindex);
		map.put("total", total);
		map.put("user", user);

		map.put("xorder", order);
		map.put("tj_start", tj_start);
		map.put("tj_end", tj_end);
		map.put("yy_start", yy_start);
		map.put("yy_end", yy_end);
		map.put("jf_search", search_v);
		map.put("s_status", Integer.parseInt(s_status));
		return new ModelAndView("order/order_jf");
	}
	
	/**
	 * 删除订单
	 * 
	 * @return
	 */
	@RequestMapping(value = "deleteOrder")
	@ResponseBody
	public Map<String, Object> deleteOrder(String orderid,String changeCause) {
		
		Map<String, Object> mapx = new HashMap<String, Object>();
		mapx.put("orderid", Integer.parseInt(orderid));
		Order order = iOrderService.order_detail(mapx);
		// 记录分配轨迹
		Long id = TokenManager.getUserId();
		UUser userx = userService.selectByPrimaryKey(id);
        int cid=id.intValue();
		ChangeRecord cr=new ChangeRecord();
        cr.setChangeCause(changeCause);
        cr.setChangeType(1);
        cr.setUserid(cid);
        cr.setOperator(userx.getNickname());
        cr.setOrderno(order.getOrderno());
        cr.setUpdate_time(insert_time);
        cr.setLicenseplate(order.getLicenseplate());
		ichangeRecord.addChangeRecord(cr);
		
		payService.deleteByOrderid(order.getOrderid());
		iOrderService.deleteOrder(Integer.parseInt(orderid));

		return resultMap;
	}

	/**
	 * 订单录入页面返回
	 * 
	 * @return
	 */
	@RequestMapping(value = "order_lr")
	public ModelAndView order_lr(ModelMap map) {
		List<VehicleType> vtlist = vehicleTypeService.selectAllType();
		List<VehicleNature> vnlist = vehicleNatureService.listnature();
		List<Station> slist = stationService.getAll();
		map.put("vtlist", vtlist);
		map.put("vnlist", vnlist);
		map.put("slist", slist);
		return new ModelAndView("order/order_l");
	}

	/**
	 * 添加订单
	 * 
	 * @return
	 */
	@RequestMapping(value = "addOrder")
	@ResponseBody
	public Map<String, Object> addOrder(Order order,
			String is_collect_materials, String injury_accident_last_year,
			String is_collect_car, String remark,String orderRemark) {
		order.setIs_collect_materials(Integer.parseInt(is_collect_materials));
		order.setInjury_accident_last_year(Integer
				.parseInt(injury_accident_last_year));
		order.setIs_collect_car(Integer.parseInt(is_collect_car));
		order.setSuditstatus(0);
		order.setOrderstatus(1);
		order.setOrderRemark(orderRemark);
		order.setCopetotal(order.getTotalorder());
		order.setSubmittime(insert_time);
		Random random = new Random();
		order.setOrderno("JW" + order_time + (random.nextInt(999) + 1000));
		order.setNjdq_date(get_end(order.getRigisterdate()));
		order.setPayment_amount(order.getOfflinepayment());
		
		//查找检测站
	     Station station=stationService.selectOneStation(order.getStation_id());		
	     order.setStation(station.getStation_no());
	     //插入订单
		iOrderService.addOrder(order);

		Map<String, Object> mapx = new HashMap<String, Object>();
		mapx.put("orderno", order.getOrderno());
		Order order2 = iOrderService.order_detail(mapx);
		Long id = TokenManager.getUserId();
		UUser user = userService.selectByPrimaryKey(id);
		int userid = new Long(id).intValue();

		// 添加线下支付
		Pay pay = new Pay();
		pay.setPay_money(order.getOfflinepayment());
		pay.setPay_time(insert_time);
		pay.setPay_type(1);
		pay.setRemark(remark);
		pay.setOrderno(order.getOrderno());
		pay.setCustomer(user.getNickname());
		pay.setUserid(userid);
		pay.setOrderid(order2.getOrderid());
		pay.setPay_kind(4);
		payService.add_pay(pay);
		// 添加订单总额
		Pay pay2 = new Pay();
		pay2.setPay_money(order.getTotalorder());
		pay2.setPay_time(insert_time);
		pay2.setPay_type(3);
		pay2.setRemark(remark);
		pay2.setOrderno(order.getOrderno());
		pay2.setCustomer(user.getNickname());
		pay2.setUserid(userid);
		pay2.setOrderid(order2.getOrderid());
		pay2.setPay_kind(4);
		payService.add_pay(pay2);
		return resultMap;
	}

	/**
	 * 订单详情
	 * 
	 * @return
	 */
	@RequestMapping(value = "order_detail")
	public ModelAndView order_detail(ModelMap map, String orderid,
			String user_qx) {

		Long id = TokenManager.getUserId();
		UUser user = userService.selectByPrimaryKey(id);
		if (user.getPosition().equals("系统管理员")) {
			user_qx = "1";
		}
		if ("".equals(user_qx) || user_qx == null) {
			user_qx = "0";
		}
		Map<String, Object> mapx = new HashMap<String, Object>();
		mapx.put("orderid", Integer.parseInt(orderid));
		Order order = iOrderService.order_detail(mapx);

		Station station = stationService
				.selectOneStation(order.getStation_id());
		List<WZ_img> wzimgList = myWz_imgService
				.find_wz_img(order.getOrderno());
		map.put("order", order);
		map.put("station", station);
		map.put("wzimgList", wzimgList);
		map.put("user_qx", Integer.parseInt(user_qx));
		return new ModelAndView("order/order_detail");
	}

	/*
	 * 上传照片
	 */
	@RequestMapping(value = "upload", method = RequestMethod.POST)
	public ModelAndView upload(ModelMap map,
			@RequestParam("uploadfile") CommonsMultipartFile file,
			HttpServletRequest request, HttpServletResponse response,
			String order_no, String user_qx) {
		// MultipartFile是对当前上传的文件的封装，当要同时上传多个文件时，可以给定多个MultipartFile参数(数组)
		Long id = TokenManager.getUserId();
		UUser user = userService.selectByPrimaryKey(id);
		if (user.getPosition().equals("系统管理员")) {
			user_qx = "1";
		}
		if ("".equals(user_qx) || user_qx == null) {
			user_qx = "0";
		}
		PrintWriter writer = null;
		JSONObject obj = new JSONObject();
		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			if (!file.isEmpty()) {
				DiskFileItem fileItem = (DiskFileItem) file.getFileItem();
				InputStream inputStream = fileItem.getInputStream();
				byte[] data = new byte[1024];
				int len = 0;
				FileOutputStream fileOutputStream = null;
				String saveFileName = System.currentTimeMillis()
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
				WZ_img wz_img = new WZ_img();
				wz_img.setImg_path(request.getScheme() + "://"
						+ request.getServerName() + ":"
						+ request.getServerPort() + "/shjw/showImage?imgId="
						+ datePath + "_" + saveFileName);
				wz_img.setInsert_time(insert_time);
				wz_img.setOrder_no(order_no);
				wz_img.setOperator(user.getEmail());
				myWz_imgService.insert_img(wz_img);
			}
		} catch (Exception e) {
			obj.put("result", 0);
		} finally {
			if (writer != null) {
				writer.print(obj);
				writer.close();
			}
		}
		Map<String, Object> mapx = new HashMap<String, Object>();
		mapx.put("orderno", order_no);
		Order order = iOrderService.order_detail(mapx);
		Station station = stationService
				.selectOneStation(order.getStation_id());
		List<WZ_img> wzimgList = myWz_imgService
				.find_wz_img(order.getOrderno());
		map.put("order", order);
		map.put("station", station);
		map.put("wzimgList", wzimgList);
		map.put("user_qx", Integer.parseInt(user_qx));
		return new ModelAndView("order/order_detail");
	}

	/**
	 * 发送短信
	 * 
	 * @return
	 * @throws ClientException
	 */
	@RequestMapping(value = "fs_email")
	@ResponseBody
	public Map<String, Object> fs_email(String order_no, String courier_name,
			String courier_phone, String collect_materials_time)
			throws ClientException {
		Map<String, Object> mapx = new HashMap<String, Object>();
		mapx.put("orderno", order_no);
		Order order = iOrderService.order_detail(mapx);

		// 操作者名字
		Long id = TokenManager.getUserId();
		UUser user = userService.selectByPrimaryKey(id);
		// 寄件短信发送
		AliyunSmsUtil.Arrangement_Express_Delivery_Sms(order.getPhonenumber(),
				order.getAppointmenttime(), courier_name, courier_phone);

		// 记录寄件信息
		JW_Email jw_Email = new JW_Email();
		jw_Email.setOrder_no(order_no);
		jw_Email.setCourier_name(courier_name);
		jw_Email.setCourier_phone(courier_phone);
		jw_Email.setEmail_type(1);
		jw_Email.setInsert_time(insert_time);
		jw_Email.setOperator(user.getNickname());
		emailService.add_email(jw_Email);
		// 短信记录
		Message message = new Message();
		message.setMessage_owner(order.getOwner());
		message.setMessage_time(insert_time);
		message.setMessage_phone(order.getPhonenumber());
		message.setMessage_plate(order.getLicenseplate());
		message.setMessage_operator(user.getNickname());
		message.setMessage_status(1);
		message.setMessage_content("您好！您的订单收到，已安排顺丰快递上门取件 预计上门时间："
				+ order.getAppointmenttime() + ": 快递员姓名：" + courier_name
				+ " 快递员电话：" + courier_phone
				+ "如需查询订单状态可关注微信公众号'车检易 '查询，或致电021-60501999");
		messService.add_message(message);
		return resultMap;
	}

	/**
	 * 寄件短信
	 * 
	 * @return
	 * @throws ClientException
	 */
	@RequestMapping(value = "jj_email")
	@ResponseBody
	public Map<String, Object> jj_email(String order_no, String courier_no)
			throws ClientException {
		Map<String, Object> mapx = new HashMap<String, Object>();
		mapx.put("orderno", order_no);
		Order order = iOrderService.order_detail(mapx);

		// 操作者名字
		Long id = TokenManager.getUserId();
		UUser user = userService.selectByPrimaryKey(id);
		// 寄件短信发送
		AliyunSmsUtil.Send_Materials_To_Customers_Sms(order.getPhonenumber(),
				courier_no);

		// 记录寄件信息
		JW_Email jw_Email = new JW_Email();
		jw_Email.setOrder_no(order_no);
		jw_Email.setEmail_type(2);
		jw_Email.setInsert_time(insert_time);
		jw_Email.setOperator(user.getNickname());
		jw_Email.setCourierNumber(courier_no);
		jw_Email.setAppointtime(order.getAppointmenttime());
		emailService.add_email(jw_Email);
		// 短信记录
		Message message = new Message();
		message.setMessage_owner(order.getOwner());
		message.setMessage_time(insert_time);
		message.setMessage_phone(order.getPhonenumber());
		message.setMessage_plate(order.getLicenseplate());
		message.setMessage_operator(user.getNickname());
		message.setMessage_status(1);
		message.setMessage_content("您好！您的爱车年检已办好，顺丰快递帮您寄回， 快递单号：" + courier_no
				+ "快递查询电话：95338 如有疑问请致电：021-60501999");
		messService.add_message(message);
		return resultMap;
	}

	/**
	 * 订单详情2
	 * 
	 * @return
	 */
	@RequestMapping(value = "order_detail2")
	@ResponseBody
	public Map<String, Object> order_detail2(String orderid) {
		Map<String, Object> mapx = new HashMap<String, Object>();
		mapx.put("orderid", Integer.parseInt(orderid));
		Order order = iOrderService.order_detail(mapx);

		resultMap.put("order", order);
		return resultMap;
	}

	/**
	 * 修改订单信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "update_Order")
	@ResponseBody
	public Map<String, Object> update_Order(Order order, String orderid) {
		order.setOrderid(Integer.parseInt(orderid));
		iOrderService.update_Order(order);
		return resultMap;
	}

	// 分配订单
	/**
	 * 分配订单
	 * 
	 * @return
	 */
	@RequestMapping(value = "order_fp")
	@ResponseBody
	public Map<String, Object> order_fp(String[] orderidlist, String c_kfy) {
		// 操作者名字
		Long id = TokenManager.getUserId();
		UUser userx = userService.selectByPrimaryKey(id);
		String[] array = c_kfy.split("&");
		int len = array.length;
		// Random random = new Random();// 创建随机对象
		// int arrIdx = random.nextInt(len);//
		// 随机数组索引，nextInt(len-1)表示随机整数[0,(len-1)]之间的值
		int order_length = orderidlist.length;
		int s1 = 0;
		int s2 = 0;
		if (len <= order_length) {
			s1 = order_length / len;
			s2 = order_length % len;

			for (int j = 0; j < len; j++) {
				for (int i = j * s1; i < (j + 1) * s1; i++) {
					UUser user = userService.selectByPrimaryKey(Long
							.parseLong(array[j]));
					iOrderService.update_kfy(user.getId(), user.getNickname(),
							Integer.parseInt(orderidlist[i]));
					// 记录分配轨迹
					Map<String, Object> mapx = new HashMap<String, Object>();
					mapx.put("orderid", Integer.parseInt(orderidlist[i]));
					Order order = iOrderService.order_detail(mapx);
					Gj gj = new Gj(order.getOrderno(), insert_time, 0, 1,
							userx.getNickname());
					gjService.add_GJ(gj);
				}
			}
			if (s2 > 0) {
				for (int x = 0; x < len; x++) {
					for (int y = (order_length - s2) + x; y < (order_length
							- s2 + x + 1)
							&& y < order_length; y++) {
						UUser user = userService.selectByPrimaryKey(Long
								.parseLong(array[x]));
						iOrderService.update_kfy(user.getId(),
								user.getNickname(),
								Integer.parseInt(orderidlist[y]));
						// 记录分配轨迹
						Map<String, Object> mapx = new HashMap<String, Object>();
						mapx.put("orderid", Integer.parseInt(orderidlist[y]));
						Order order = iOrderService.order_detail(mapx);
						Gj gj = new Gj(order.getOrderno(), insert_time, 0, 1,
								userx.getNickname());
						gjService.add_GJ(gj);
						if ((x + 1) == s2) {
							break;
						}
					}
				}
			}

		} else {
			s1 = len / order_length;
			s2 = len % order_length;
			for (int j = 0; j < len; j++) {
				for (int i = j; i < (j + 1) && i < order_length; i++) {
					if (j == order_length) {
						break;
					}
					UUser user = userService.selectByPrimaryKey(Long
							.parseLong(array[j]));
					iOrderService.update_kfy(user.getId(), user.getNickname(),
							Integer.parseInt(orderidlist[i]));

					// 记录分配轨迹
					Map<String, Object> mapx = new HashMap<String, Object>();
					mapx.put("orderid", Integer.parseInt(orderidlist[i]));
					Order order = iOrderService.order_detail(mapx);
					Gj gj = new Gj(order.getOrderno(), insert_time, 0, 1,
							userx.getNickname());
					gjService.add_GJ(gj);

				}
			}
		}
		return resultMap;
	}

	// 接受订单
	/**
	 * 接受订单
	 * 
	 * @return
	 */
	@RequestMapping(value = "order_js")
	@ResponseBody
	public Map<String, Object> order_js(String orderid) {
		iOrderService.update_js(Integer.parseInt(orderid));
		// 记录分配轨迹
		Long id = TokenManager.getUserId();
		UUser userx = userService.selectByPrimaryKey(id);
		Map<String, Object> mapx = new HashMap<String, Object>();
		mapx.put("orderid", Integer.parseInt(orderid));
		Order order = iOrderService.order_detail(mapx);
		Gj gj = new Gj(order.getOrderno(), insert_time, 1, 2,
				userx.getNickname());
		gjService.add_GJ(gj);
		return resultMap;
	}

	// 违章查询
	/**
	 * 违章修改状态(有违章)
	 * 
	 * @return
	 */
	@RequestMapping(value = "order_ywz")
	@ResponseBody
	public Map<String, Object> order_ywz(String orderid, String status) {
		if ("".equals(status) || status == null) {
			status = "0";
		}
		// 记录分配轨迹
		Long id = TokenManager.getUserId();
		UUser userx = userService.selectByPrimaryKey(id);
		Map<String, Object> mapx = new HashMap<String, Object>();
		mapx.put("orderid", Integer.parseInt(orderid));
		Order order = iOrderService.order_detail(mapx);
		if (status.equals("1")) {
			Gj gj = new Gj(order.getOrderno(), insert_time, 5, 3,
					userx.getNickname());
			gjService.add_GJ(gj);
		} else if (status.equals("2")) {
			Gj gj = new Gj(order.getOrderno(), insert_time, 9, 3,
					userx.getNickname());
			gjService.add_GJ(gj);
		} else {
			Gj gj = new Gj(order.getOrderno(), insert_time, 2, 3,
					userx.getNickname());
			gjService.add_GJ(gj);

		}
		iOrderService.update_ywz(Integer.parseInt(orderid));

		return resultMap;
	}

	/**
	 * 无违章状态
	 * 
	 * @return
	 */
	@RequestMapping(value = "order_wwz")
	@ResponseBody
	public Map<String, Object> order_wwz(String orderid) {
		Map<String, Object> mapx = new HashMap<String, Object>();
		mapx.put("orderid", Integer.parseInt(orderid));
		Order order = iOrderService.order_detail(mapx);
		Long id = TokenManager.getUserId();
		UUser userx = userService.selectByPrimaryKey(id);

		String licenseplate = order.getLicenseplate();
		int suditstatus = 4;
		if (order.getAppointmenttime() != null
				&& !"".equals(order.getAppointmenttime())) {
			suditstatus = 9;
			Gj gj = new Gj(order.getOrderno(), insert_time,
					order.getSuditstatus(), 9, userx.getNickname());
			gjService.add_GJ(gj);

			resultMap.put("message", "继续完成年检");
		} else {
			boolean status = licenseplate.contains("沪");
			if (status) {
				suditstatus = 7;

				Gj gj = new Gj(order.getOrderno(), insert_time,
						order.getSuditstatus(), 7, userx.getNickname());
				gjService.add_GJ(gj);

				resultMap.put("message", "是沪牌进去缴费");
			} else {
				suditstatus = 4;
				Gj gj = new Gj(order.getOrderno(), insert_time,
						order.getSuditstatus(), 4, userx.getNickname());
				gjService.add_GJ(gj);

				resultMap.put("message", "非沪牌收集资料");
			}

		}
		order.setSuditstatus(suditstatus);
		iOrderService.update_wwz(order);
		return resultMap;
	}

	/**
	 * 警示取消
	 * 
	 * @return
	 */
	@RequestMapping(value = "update_wzstatus")
	@ResponseBody
	public Map<String, Object> update_wzstatus(String orderid) {
		iOrderService.update_wzstatus(Integer.parseInt(orderid));
		return resultMap;
	}

	// 资料收集
	/**
	 * 资料已收集
	 * 
	 * @return
	 */
	@RequestMapping(value = "order_ysj")
	@ResponseBody
	public Map<String, Object> order_ysj(String orderid) {
		// 记录分配轨迹
		Long id = TokenManager.getUserId();
		UUser userx = userService.selectByPrimaryKey(id);
		Map<String, Object> mapx = new HashMap<String, Object>();
		mapx.put("orderid", Integer.parseInt(orderid));
		Order order = iOrderService.order_detail(mapx);
		Gj gj = new Gj(order.getOrderno(), insert_time, 4, 5,
				userx.getNickname());
		gjService.add_GJ(gj);

		iOrderService.update_ysj(Integer.parseInt(orderid));

		return resultMap;
	}

	/**
	 * 违章退回
	 * 
	 * @return
	 */
	@RequestMapping(value = "order_wz_th")
	@ResponseBody
	public Map<String, Object> order_wz_th(String orderid) {
		// 记录分配轨迹
		Long id = TokenManager.getUserId();
		UUser userx = userService.selectByPrimaryKey(id);
		Map<String, Object> mapx = new HashMap<String, Object>();
		mapx.put("orderid", Integer.parseInt(orderid));
		Order order = iOrderService.order_detail(mapx);
		Gj gj = new Gj(order.getOrderno(), insert_time, 4, 3,
				userx.getNickname());
		gjService.add_GJ(gj);

		iOrderService.update_ywz(Integer.parseInt(orderid));
		return resultMap;
	}

	// 委托办理
	/**
	 * 资料已寄等待中
	 * 
	 * @return
	 */
	@RequestMapping(value = "order_zlyj")
	@ResponseBody
	public Map<String, Object> order_zlyj(String orderid) {
		// 记录分配轨迹
		Long id = TokenManager.getUserId();
		UUser userx = userService.selectByPrimaryKey(id);
		Map<String, Object> mapx = new HashMap<String, Object>();
		mapx.put("orderid", Integer.parseInt(orderid));
		Order order = iOrderService.order_detail(mapx);
		Gj gj = new Gj(order.getOrderno(), insert_time, 5, 6,
				userx.getNickname());
		gjService.add_GJ(gj);

		iOrderService.update_zlyj(Integer.parseInt(orderid));
		return resultMap;
	}

	/**
	 * 资料收到
	 * 
	 * @return
	 */
	@RequestMapping(value = "order_zlsd")
	@ResponseBody
	public Map<String, Object> order_zlsd(String orderid) {

		// 记录分配轨迹
		Long id = TokenManager.getUserId();
		UUser userx = userService.selectByPrimaryKey(id);
		Map<String, Object> mapx = new HashMap<String, Object>();
		mapx.put("orderid", Integer.parseInt(orderid));
		Order order = iOrderService.order_detail(mapx);
		Gj gj = new Gj(order.getOrderno(), insert_time, order.getSuditstatus(),
				8, userx.getNickname());
		gjService.add_GJ(gj);

		int order_id = Integer.parseInt(orderid);
		iOrderService.update_zlsd(order_id);
		return resultMap;
	}

	/**
	 * 资料不足退回
	 * 
	 * @return
	 */
	@RequestMapping(value = "order_zlbz")
	@ResponseBody
	public Map<String, Object> order_zlbz(String orderid) {
		Map<String, Object> mapx = new HashMap<String, Object>();
		mapx.put("orderid", Integer.parseInt(orderid));
		Order order = iOrderService.order_detail(mapx);

		// 记录分配轨迹
		Long id = TokenManager.getUserId();
		UUser userx = userService.selectByPrimaryKey(id);
		Gj gj = new Gj(order.getOrderno(), insert_time, order.getSuditstatus(),
				4, userx.getNickname());
		gjService.add_GJ(gj);

		int suditstatus = 4;
		order.setSuditstatus(suditstatus);
		iOrderService.update_wwz(order);
		return resultMap;
	}

	/**
	 * 预约年检订单
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "order_yynj")
	public ModelAndView order_yynj(ModelMap map, String status,
			String pageIndex, String yynj_search, Order order, String tj_start,
			String tj_end, String yy_start, String yy_end, String s_status)
			throws UnsupportedEncodingException {
		if (pageIndex == null || pageIndex.equals("")) {
			pageIndex = "1";
		}
		int apageindex = Integer.parseInt(pageIndex);
		Long id = TokenManager.getUserId();
		UUser user = userService.selectByPrimaryKey(id);
		long userid = 0;
/*		if (user.getPosition().equals("客服员")) {
			userid = user.getId();
		}*/

		String search_v = "";
		int page = (apageindex - 1) * 10;
		String x_tj_start = tj_start;
		String x_tj_end = tj_end;

		if (tj_start != null && !tj_start.equals("")) {
			x_tj_start += " 00:00:00";
		}
		if (tj_end != null && !tj_end.equals("")) {
			x_tj_end += " 00:00:00";
		}
		if (yynj_search != null) {
			search_v = URLDecoder.decode(yynj_search, "UTF-8");
		}
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("search_v", search_v);
		map1.put("order", order);
		map1.put("tj_start", x_tj_start);
		map1.put("tj_end", x_tj_end);
		map1.put("yy_start", yy_start);
		map1.put("yy_end", yy_end);
		map1.put("userid", userid);
		Map<String, Object> mapx = new HashMap<String, Object>();
		mapx.put("userid", userid);
		if ((tj_start == null || "".equals(tj_start))
				&& (tj_end == null || "".equals(tj_end))
				&& (yy_start == null || "".equals(yy_start))
				&& (yy_end == null || "".equals(yy_end))
				&& (yynj_search == null || "".equals(yynj_search))
				&& (order.getOwner() == null || "".equals(order.getOwner()))
				&& (order.getLicenseplate() == null || "".equals(order
						.getLicenseplate()))
				&& (order.getPhonenumber() == null || "".equals(order
						.getPhonenumber()))) {
			map1.put("page", page);
		} else {
			s_status = "1";
			map1.put("page", -1);
		}
		if ("".equals(s_status) || s_status == null) {
			s_status = "0";
		}
		List<Map<String, Object>> list = iOrderService.order_yynj_Page(map1);
		if ("".equals(status) || status == null) {
			status = "0";
		}
		int counts = iOrderService.yyCountOrder(mapx);
		// int counts=list.size();
		int total = (counts % 10 == 0) ? (counts / 10) : (counts / 10 + 1);
		// 未处理订单提示
		OrderXx ox = new OrderXx();
		int fp_count = iOrderService.nfpCountOrder(mapx);
		int js_counts = iOrderService.njsCountOrder(mapx);
		int wz_counts = iOrderService.wzCountOrder(mapx);
		int zl_counts = iOrderService.zlCountOrder(mapx);
		int wt_counts = iOrderService.wtCountOrder(mapx);
		int jf_counts = iOrderService.jfCountOrder(mapx);
		int yy_counts = iOrderService.yyCountOrder(mapx);
		int nj_counts = iOrderService.njCountOrder(mapx);
		int hf_counts = iOrderService.hfCountOrder(mapx);
		ox.setFp_count(fp_count);
		ox.setJs_counts(js_counts);
		ox.setWz_counts(wz_counts);
		ox.setZl_counts(zl_counts);
		ox.setWt_counts(wt_counts);
		ox.setJf_counts(jf_counts);
		ox.setYy_counts(yy_counts);
		ox.setNj_counts(nj_counts);
		ox.setHf_counts(hf_counts);
		map.put("ox", ox);
		map.put("status", status);
		map.put("yylist", list);
		map.put("pageIndex", apageindex);
		map.put("total", total);
		map.put("user", user);

		map.put("xorder", order);
		map.put("tj_start", tj_start);
		map.put("tj_end", tj_end);
		map.put("yy_start", yy_start);
		map.put("yy_end", yy_end);
		map.put("yynj_search", search_v);
		map.put("s_status", Integer.parseInt(s_status));
		return new ModelAndView("order/order_njyy");
	}

	/**
	 * 不分页查询检测站
	 * 
	 * @return
	 */
	@RequestMapping(value = "listStation")
	@ResponseBody
	public Map<String, Object> listStation() {
		List<Station> listStation = stationService.listStation();
		resultMap.put("listStation", listStation);
		return resultMap;
	}

	/**
	 * 预约年检时间
	 * 
	 * @return
	 * @throws ClientException
	 */
	@RequestMapping(value = "order_yyok")
	@ResponseBody
	public Map<String, Object> order_yyok(String orderid, String yy_time,
			String yy_station) {

		// 记录分配轨迹
		Long id = TokenManager.getUserId();
		UUser userx = userService.selectByPrimaryKey(id);
		Map<String, Object> mapx = new HashMap<String, Object>();
		mapx.put("orderid", Integer.parseInt(orderid));
		Order order = iOrderService.order_detail(mapx);
		Gj gj = new Gj(order.getOrderno(), insert_time, 8, 7,
				userx.getNickname());
		gjService.add_GJ(gj);

		int y_order = Integer.parseInt(orderid);
		iOrderService.update_yynj(yy_time, yy_station, y_order);

		return resultMap;
	}

	/**
	 * 年检订单
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "order_nj")
	public ModelAndView order_njlist(ModelMap map, String status,
			String pageIndex, String nj_search, Order order, String tj_start,
			String tj_end, String yy_start, String yy_end, String s_status)
			throws UnsupportedEncodingException {
		if (pageIndex == null || pageIndex.equals("")) {
			pageIndex = "1";
		}
		int apageindex = Integer.parseInt(pageIndex);

		Long id = TokenManager.getUserId();
		UUser user = userService.selectByPrimaryKey(id);
		long userid = 0;
		String station = "";
/*		if (user.getPosition().equals("验车员")) {
			station = user.getStation();
		}*/

		String search_v = "";
		int page = (apageindex - 1) * 10;
		String x_tj_start = tj_start;
		String x_tj_end = tj_end;

		if (tj_start != null && !tj_start.equals("")) {
			x_tj_start += " 00:00:00";
		}
		if (tj_end != null && !tj_end.equals("")) {
			x_tj_end += " 00:00:00";
		}
		if (nj_search != null) {
			search_v = URLDecoder.decode(nj_search, "UTF-8");
		}
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("search_v", search_v);
		map1.put("order", order);
		map1.put("tj_start", x_tj_start);
		map1.put("tj_end", x_tj_end);
		map1.put("yy_start", yy_start);
		map1.put("yy_end", yy_end);
		map1.put("station", station);
		Map<String, Object> mapx = new HashMap<String, Object>();
		mapx.put("userid", userid);
		mapx.put("station", station);

		if ((tj_start == null || "".equals(tj_start))
				&& (tj_end == null || "".equals(tj_end))
				&& (yy_start == null || "".equals(yy_start))
				&& (yy_end == null || "".equals(yy_end))
				&& (nj_search == null || "".equals(nj_search))
				&& (order.getOwner() == null || "".equals(order.getOwner()))
				&& (order.getLicenseplate() == null || "".equals(order
						.getLicenseplate()))
				&& (order.getPhonenumber() == null || "".equals(order
						.getPhonenumber()))) {
			map1.put("page", page);
		} else {
			s_status = "1";
			map1.put("page", -1);
		}
		if ("".equals(s_status) || s_status == null) {
			s_status = "0";
		}
		List<Map<String, Object>> list = iOrderService.order_nj_Page(map1);
		if ("".equals(status) || status == null) {
			status = "0";
		}
		int counts = iOrderService.njCountOrder(mapx);

		// int counts=list.size();
		int total = (counts % 10 == 0) ? (counts / 10) : (counts / 10 + 1);
		// 未处理订单提示
		OrderXx ox = new OrderXx();
		int fp_count = iOrderService.nfpCountOrder(mapx);
		int js_counts = iOrderService.njsCountOrder(mapx);
		int wz_counts = iOrderService.wzCountOrder(mapx);
		int zl_counts = iOrderService.zlCountOrder(mapx);
		int wt_counts = iOrderService.wtCountOrder(mapx);
		int jf_counts = iOrderService.jfCountOrder(mapx);
		int yy_counts = iOrderService.yyCountOrder(mapx);
		int nj_counts = iOrderService.njCountOrder(mapx);
		int hf_counts = iOrderService.hfCountOrder(mapx);
		ox.setFp_count(fp_count);
		ox.setJs_counts(js_counts);
		ox.setWz_counts(wz_counts);
		ox.setZl_counts(zl_counts);
		ox.setWt_counts(wt_counts);
		ox.setJf_counts(jf_counts);
		ox.setYy_counts(yy_counts);
		ox.setNj_counts(nj_counts);
		ox.setHf_counts(hf_counts);
		map.put("ox", ox);
		map.put("status", status);
		map.put("njlist", list);
		map.put("pageIndex", apageindex);
		map.put("total", total);
		map.put("user", user);

		map.put("xorder", order);
		map.put("tj_start", tj_start);
		map.put("tj_end", tj_end);
		map.put("yy_start", yy_start);
		map.put("yy_end", yy_end);
		map.put("nj_search", search_v);
		map.put("s_status", Integer.parseInt(s_status));
		return new ModelAndView("order/order_nj");
	}

	/**
	 * 年检通过
	 * 
	 * @return
	 */
	@RequestMapping(value = "order_nj_tg")
	@ResponseBody
	public Map<String, Object> order_nj_tg(String orderid) {

		// 记录分配轨迹
		Long id = TokenManager.getUserId();
		UUser userx = userService.selectByPrimaryKey(id);
		Map<String, Object> mapx = new HashMap<String, Object>();
		mapx.put("orderid", Integer.parseInt(orderid));
		Order order = iOrderService.order_detail(mapx);
		Gj gj = new Gj(order.getOrderno(), insert_time, 9, 10,
				userx.getNickname());
		gjService.add_GJ(gj);

		iOrderService.update_nj_tg(Integer.parseInt(orderid));
		return resultMap;
	}

	/**
	 * 年检重新预约
	 * 
	 * @return
	 */
	@RequestMapping(value = "order_nj_cxyy")
	@ResponseBody
	public Map<String, Object> order_nj_cxyy(String orderid, String nj_time) {
		iOrderService.update_nj_tg(Integer.parseInt(orderid), nj_time);
		return resultMap;
	}

	/**
	 * 回访年检订单
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "order_hf")
	public ModelAndView order_hf(ModelMap map, String status, String pageIndex,
			String hf_search, Order order, String tj_start, String tj_end,
			String yy_start, String yy_end, String s_status)
			throws UnsupportedEncodingException {
		if (pageIndex == null || pageIndex.equals("")) {
			pageIndex = "1";
		}
		int apageindex = Integer.parseInt(pageIndex);

		Long id = TokenManager.getUserId();
		UUser user = userService.selectByPrimaryKey(id);
		long userid = 0;
/*		if (user.getPosition().equals("客服员")) {
			userid = user.getId();
		}*/

		String search_v = "";
		int page = (apageindex - 1) * 10;
		String x_tj_start = tj_start;
		String x_tj_end = tj_end;

		if (tj_start != null && !tj_start.equals("")) {
			x_tj_start += " 00:00:00";
		}
		if (tj_end != null && !tj_end.equals("")) {
			x_tj_end += " 00:00:00";
		}
		if (hf_search != null) {
			search_v = URLDecoder.decode(hf_search, "UTF-8");
		}
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("search_v", search_v);
		map1.put("order", order);
		map1.put("tj_start", x_tj_start);
		map1.put("tj_end", x_tj_end);
		map1.put("yy_start", yy_start);
		map1.put("yy_end", yy_end);
		map1.put("userid", userid);
		Map<String, Object> mapx = new HashMap<String, Object>();
		mapx.put("userid", userid);
		if ((tj_start == null || "".equals(tj_start))
				&& (tj_end == null || "".equals(tj_end))
				&& (yy_start == null || "".equals(yy_start))
				&& (yy_end == null || "".equals(yy_end))
				&& (hf_search == null || "".equals(hf_search))
				&& (order.getOwner() == null || "".equals(order.getOwner()))
				&& (order.getLicenseplate() == null || "".equals(order
						.getLicenseplate()))
				&& (order.getPhonenumber() == null || "".equals(order
						.getPhonenumber()))) {
			map1.put("page", page);
		} else {
			s_status = "1";
			map1.put("page", -1);
		}
		if ("".equals(s_status) || s_status == null) {
			s_status = "0";
		}
		List<Map<String, Object>> list = iOrderService.order_hf_Page(map1);
		if ("".equals(status) || status == null) {
			status = "0";
		}
		int counts = iOrderService.hfCountOrder(mapx);
		// int counts=list.size();
		int total = (counts % 10 == 0) ? (counts / 10) : (counts / 10 + 1);
		// 未处理订单提示
		OrderXx ox = new OrderXx();
		int fp_count = iOrderService.nfpCountOrder(mapx);
		int js_counts = iOrderService.njsCountOrder(mapx);
		int wz_counts = iOrderService.wzCountOrder(mapx);
		int zl_counts = iOrderService.zlCountOrder(mapx);
		int wt_counts = iOrderService.wtCountOrder(mapx);
		int jf_counts = iOrderService.jfCountOrder(mapx);
		int yy_counts = iOrderService.yyCountOrder(mapx);
		int nj_counts = iOrderService.njCountOrder(mapx);
		int hf_counts = iOrderService.hfCountOrder(mapx);
		ox.setFp_count(fp_count);
		ox.setJs_counts(js_counts);
		ox.setWz_counts(wz_counts);
		ox.setZl_counts(zl_counts);
		ox.setWt_counts(wt_counts);
		ox.setJf_counts(jf_counts);
		ox.setYy_counts(yy_counts);
		ox.setNj_counts(nj_counts);
		ox.setHf_counts(hf_counts);

		map.put("ox", ox);
		map.put("status", status);
		map.put("hflist", list);
		map.put("pageIndex", apageindex);
		map.put("total", total);
		map.put("user", user);

		map.put("xorder", order);
		map.put("tj_start", tj_start);
		map.put("tj_end", tj_end);
		map.put("yy_start", yy_start);
		map.put("yy_end", yy_end);
		map.put("hf_search", search_v);
		map.put("s_status", Integer.parseInt(s_status));
		return new ModelAndView("order/order_hf");
	}

	/**
	 * 回访
	 * 
	 * @return
	 */
	@RequestMapping(value = "hfContent")
	@ResponseBody
	public Map<String, Object> hfContent(String orderid, String content) {
		iOrderService.update_hfContent(Integer.parseInt(orderid));
		Hf hf = new Hf();
		hf.setHf_time(insert_time);
		hf.setHf_content(content);
		hf.setOrderid(Integer.parseInt(orderid));
		hfService.add_hf(hf);
		return resultMap;
	}

	/**
	 * 回访记录
	 * 
	 * @return
	 */
	@RequestMapping(value = "hf_record")
	@ResponseBody
	public Map<String, Object> hf_record(String orderid) {
		List<Hf> hflist = hfService.find_hf(Integer.parseInt(orderid));
		resultMap.put("hflist", hflist);
		return resultMap;
	}

	/**
	 * 财务管理
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "fin")
	public ModelAndView fin(ModelMap map, String pageIndex, String status,
			String search, Order order, String pay_start, String pay_end,
			String s_status) throws UnsupportedEncodingException {

		// 获取当天/当月 总收入
		//List<Order> daylist = iOrderService.find_day_list();
		//List<Order> monthlist = iOrderService.find_month_list();
		//当天线下支付
		List<Pay> day_xxlist=payService.find_day_xxlist();
		//当天支出
		List<Pay> day_zflist=payService.find_day_zflist();
        //当月线下支付
		List<Pay> month_xxlist=payService.find_month_xxlist();
        //当月支出
		List<Pay> month_zflist=payService.find_month_zflist();

		double day_all_xx = 0;// 当天线下支付
		double day_all_zf = 0;// 当天支出
		double day_all_gross = 0;// 当天总利润
		double month_all_xx = 0;// 当月线下支付
		double month_all_zf = 0;// 当月支出
		double month_all_gross = 0;// 当月总利润

		for (Pay pay : day_xxlist) {
			day_all_xx +=pay.getPay_money();
		}
		for (Pay pay : day_zflist) {
			day_all_zf +=pay.getPay_money();

		}
		day_all_gross = day_all_xx - day_all_zf;//当日总利润
		for (Pay pay : month_xxlist) {
			month_all_xx +=pay.getPay_money();
		}
		for (Pay pay : month_zflist) {
			month_all_zf +=pay.getPay_money();
		}
		month_all_gross = month_all_xx - month_all_zf;//当月总利润
		
		
		if ("".equals(status) || status == null) {
			status = "0";
		}

		if (pageIndex == null || pageIndex.equals("")) {
			pageIndex = "1";
		}
		String search_v = "";
		int apageindex = Integer.parseInt(pageIndex);

		if (search != null) {
			search_v = URLDecoder.decode(search, "UTF-8");
		}
/*		String x_pay_start = pay_start;
		String x_pay_end = pay_end;
		if (pay_start != null && !("".equals(pay_start))) {
			x_pay_start += " 00:00:00";
		}
		if (pay_end != null && !("".equals(pay_end))) {
			x_pay_end += " 00:00:00";
		}
*/
		int page = (apageindex - 1) * 10;
		int counts = 0;
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.clear();
		map1.put("search_v", search_v);
		map1.put("order", order);
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.clear();
		map2.put("pay_start", pay_start);
		map2.put("pay_end", pay_end);
		// map1.put("pay_start", pay_start);
		// map1.put("pay_end", pay_end);

		List<Order> xlist = new ArrayList<Order>();
		xlist.clear();
		double allxpay = 0;// 总线下支付
		double allpay = 0;// 总支出支付
		if ((pay_start == null || "".equals(pay_start))
		 && (pay_end == null || "".equals(pay_end))
		 && (search == null || "".equals(search))
		 && (order.getOwner() == null || "".equals(order.getOwner()))
		 && (order.getLicenseplate() == null || "".equals(order.getLicenseplate()))
		 && (order.getPhonenumber() == null || "".equals(order.getPhonenumber()))){
			map1.put("page", page);
			List<Order> list1 = iOrderService.fin_list(map1);
			counts = iOrderService.finCount_order();
			xlist = list1;

		} else {
			map1.put("page", -1);
			List<Pay> payList = payService.find_pay_time(map2);
			for(Pay pay:payList){
				if (pay.getPay_type() == 1) {
					allxpay+=pay.getPay_money();
				}
				if (pay.getPay_type() == 2) {
					allpay+=pay.getPay_money();
				}
			}
			List<Order> list = iOrderService.fin_list(map1);

			if (payList.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					double xxpay = 0;// 线下支付
					double zfpay = 0;// 支付
					double xjpay = 0;// 实际支付
					Order orderx = list.get(i);
					for (Pay pay : payList) {
						if (orderx.getOrderid() == pay.getOrderid()) {
							if (pay.getPay_type() == 1) {
								xxpay += pay.getPay_money();
							}
							if (pay.getPay_type() == 2) {
								zfpay += pay.getPay_money();
							}
							if (orderx.getCash_fee() != null) {
								double cash_fee = Double.valueOf(
										orderx.getCash_fee()).doubleValue();
								cash_fee = cash_fee / 100;
								xjpay = xxpay + cash_fee;
							}

							orderx.setPay(zfpay);
							orderx.setOfflinepayment(xxpay);
							orderx.setPayment_amount(xjpay);

						}
					}
					if (xxpay != 0 || zfpay != 0 || xjpay != 0) {
						xlist.add(orderx);
					}
				}
			}
			s_status = "1";
		}
		//查询利润
		 double lr=0;
          lr=allxpay-allpay;
		// int counts=xlist.size();
		// System.out.println("count==="+counts);
		int total = (counts % 10 == 0) ? (counts / 10) : (counts / 10 + 1);
		if ("".equals(s_status) || s_status == null) {
			s_status = "0";
		}

		map.put("status", status);
		map.put("finlist", xlist);
		map.put("pageIndex", apageindex);
		map.put("total", total);
		map.put("day_all_money", day_all_gross);
		map.put("month_all_money", month_all_gross);
		map.put("pay_start", pay_start);
		map.put("pay_end", pay_end);
		map.put("h_order", order);
		map.put("search", search_v);
		map.put("lr",  lr);
		map.put("s_status", Integer.parseInt(s_status));
		
		return new ModelAndView("fin/fin");
	}

	/**
	 * 导出财务
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "fin_to")
	@ResponseBody
	public Map<String, Object> fin_to(HttpServletResponse response,
			String search_v, String owner, String licenseplate,
			String phonenumber, String pay_start, String pay_end)
			throws UnsupportedEncodingException {
		Order order = new Order();
		order.setOwner(owner);
		order.setLicenseplate(licenseplate);
		order.setPhonenumber(phonenumber);

		if (search_v != null) {
			search_v = URLDecoder.decode(search_v, "UTF-8");
		}
		if (owner != null) {
			owner = URLDecoder.decode(owner, "UTF-8");
		}
		if (licenseplate != null) {
			licenseplate = URLDecoder.decode(licenseplate, "UTF-8");
		}
		if (phonenumber != null) {
			phonenumber = URLDecoder.decode(phonenumber, "UTF-8");
		}
		if (pay_start != null) {
			pay_start = URLDecoder.decode(pay_start, "UTF-8");
		}
		if (pay_end != null) {
			pay_end = URLDecoder.decode(pay_end, "UTF-8");
		}
/*		String x_pay_start = pay_start;
		String x_pay_end = pay_end;
		if (pay_start != null && !("".equals(pay_start))) {
			x_pay_start += " 00:00:00";
		}
		if (pay_end != null && !("".equals(pay_end))) {
			x_pay_end += " 00:00:00";
		}*/
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.clear();
		map1.put("search_v", search_v);
		map1.put("order", order);
		map1.put("page", -1);
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.clear();
		map2.put("pay_start", pay_start);
		map2.put("pay_end", pay_end);
		// System.out.println("pay_start=="+pay_start);
		List<Order> xlist = new ArrayList<Order>();
		xlist.clear();
		// System.out.println("order=="+order.toString());
		if ((pay_start == null || "".equals(pay_start))
				&& (pay_end == null || "".equals(pay_end))
				&& (search_v == null || "".equals(search_v))
				&& (order.getOwner() == null || "".equals(order.getOwner()))
				&& (order.getLicenseplate() == null || "".equals(order
						.getLicenseplate()))
				&& (order.getPhonenumber() == null || "".equals(order
						.getPhonenumber()))) {
			List<Order> list1 = iOrderService.fin_list(map1);
			xlist = list1;

		} else {
			List<Pay> payList = payService.find_pay_time(map2);
			List<Order> list = iOrderService.fin_list(map1);
			if (payList.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					double xxpay = 0;// 线下支付
					double zfpay = 0;// 支付
					double xjpay = 0;// 实际支付
					Order orderx = list.get(i);
					for (Pay pay : payList) {
						if (orderx.getOrderid() == pay.getOrderid()) {
							if (pay.getPay_type() == 1) {
								xxpay += pay.getPay_money();
							}
							if (pay.getPay_type() == 2) {
								zfpay += pay.getPay_money();
							}
							xjpay = xxpay + orderx.getOnlinepayment();
							orderx.setPay(zfpay);
							orderx.setOfflinepayment(xxpay);
							orderx.setPayment_amount(xjpay);

						}
					}
					if (xxpay != 0 || zfpay != 0 || xjpay != 0) {
						xlist.add(orderx);
					}
				}
			}
		}
		// List<Order> fin_list = iOrderService.findAll_fin();
		ExcelOperate.createExcel(xlist, response);
		return resultMap;
	}

	/**
	 * 导出财务详细报表
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "fin_to_bb")
	@ResponseBody
	public Map<String, Object> fin_to_bb(HttpServletResponse response,
			String search_v, String owner, String licenseplate,
			String phonenumber, String pay_start, String pay_end)
			throws UnsupportedEncodingException {

		if (search_v != null) {
			search_v = URLDecoder.decode(search_v, "UTF-8");
		}
		if (owner != null) {
			owner = URLDecoder.decode(owner, "UTF-8");
		}
		if (licenseplate != null) {
			licenseplate = URLDecoder.decode(licenseplate, "UTF-8");
		}
		if (phonenumber != null) {
			phonenumber = URLDecoder.decode(phonenumber, "UTF-8");
		}
		if (pay_start != null) {
			pay_start = URLDecoder.decode(pay_start, "UTF-8");
		}
		if (pay_end != null) {
			pay_end = URLDecoder.decode(pay_end, "UTF-8");
		}
		Order order = new Order();
		order.setOwner(owner);
		order.setLicenseplate(licenseplate);
		order.setPhonenumber(phonenumber);
/*		String x_pay_start = pay_start;
		String x_pay_end = pay_end;
		if (pay_start != null && !("".equals(pay_start))) {
			x_pay_start += " 00:00:00";
		}
		if (pay_end != null && !("".equals(pay_end))) {
			x_pay_end += " 00:00:00";
		}*/
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.clear();
		map1.put("search_v", search_v);
		map1.put("order", order);
		map1.put("page", -1);
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.clear();
		map2.put("pay_start", pay_start);
		map2.put("pay_end", pay_end);
		List<Pay> payList = payService.find_pay_time(map2);
		List<Order> list1 = iOrderService.fin_list(map1);
		//这里之前有订单删除导致有些支付失效在这里新建new_payList做个删选
		List<Pay> new_payList=new ArrayList<Pay>();
		for (int i = 0; i < payList.size(); i++) {
			Pay pay = payList.get(i);
			for (int j = 0; j < list1.size(); j++) {
				Order order1 = list1.get(j);
				if (pay.getOrderid() == order1.getOrderid()) {
					pay.setOrder(order1);
					new_payList.add(pay);
				}
			}
		}
		map1.put("pay_start", pay_start);
		map1.put("pay_end", pay_end);
		List<Order> list2 = iOrderService.fin_list(map1);
		for (int j = 0; j < list2.size(); j++) {
			Order order1 = list2.get(j);
			int y = 0;
			for (int i = 0; i < payList.size(); i++) {
				Pay pay = payList.get(i);
				if (pay.getOrderid() == order1.getOrderid()) {
					if (order1.getCash_fee() != null&& !"".equals(order1.getCash_fee())) {
						if (y == 0) {
							double cash_fee = Double.valueOf(order1.getCash_fee()).doubleValue();
							cash_fee = cash_fee / 100;
							Pay payx = new Pay();
							payx.setCustomer(order1.getCustomer());
							payx.setPay_money(cash_fee);
							payx.setPay_type(4);
							payx.setPay_kind(5);
							payx.setPay_time(order1.getSubmittime());
							payx.setRemark("微信支付");
							payx.setOrder(order1);
							new_payList.add(payx);
							y++;
							j++;
						}
					}

				}

			}
		}
		if (payList.size() > 0) {
			ExcelOperate_xx.createExcel(new_payList, response);
		}
		return resultMap;
	}

	/**
	 * 比较时间大小
	 * 
	 * @return
	 */
	public int bj(String date1, String date2) {
		int i = 0;
		Date date11 = null;
		Date date22 = null;
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			date11 = dateFormat.parse(date1);
			if(date2!=null&&!"".equals(date2)){
				date22 = dateFormat.parse(date2);
			}
			
			if (date11.before(date22)) {
				i = 1;
			}
			if (date11.after(date22)) {
				i = 2;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}

	/**
	 * 计算当前日期月份最后一天
	 * 
	 * @return
	 */
	@Test
	public String get_end(String date) {
		String date1 = null;
		// String date2="2017-11-16";
		try {
			SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat df2 = new SimpleDateFormat("yyyy年MM月dd日");
			Calendar ca = Calendar.getInstance();
			Date theDate = df1.parse(date);
			ca.setTime(theDate);
			ca.set(Calendar.DAY_OF_MONTH,
					ca.getActualMaximum(Calendar.DAY_OF_MONTH));
			Date theDate2 = ca.getTime();
			date1 = df2.format(theDate2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date1;
	}

	/**
	 * 未付款全部
	 * 
	 * @return
	 */
	@RequestMapping(value = "nopayAll")
	public ModelAndView nopayAll(ModelMap map, String status, String allsearch,
			String pageIndex, Order order, String tj_start, String tj_end,
			String yy_start, String yy_end, String s_status)
			throws UnsupportedEncodingException {
		if (pageIndex == null || pageIndex.equals("")) {
			pageIndex = "1";
		}
		String search_v = "";
		int apageindex = Integer.parseInt(pageIndex);

		Long id = TokenManager.getUserId();
		UUser user = userService.selectByPrimaryKey(id);
		long userid = 0;
/*		if (user.getPosition().equals("客服员")) {
			userid = user.getId();
		}*/
		if (user.getPosition().equals("验车员")) {
			userid = user.getId();
		}
		if (allsearch != null) {
			search_v = URLDecoder.decode(allsearch, "UTF-8");
		}

		String x_tj_start = tj_start;
		String x_tj_end = tj_end;

		if (tj_start != null && !tj_start.equals("")) {
			x_tj_start += " 00:00:00";
		}
		if (tj_end != null && !tj_end.equals("")) {
			x_tj_end += " 00:00:00";
		}
		int counts = 0;
		int page = (apageindex - 1) * 10;
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.clear();
		map1.put("order", order);
		map1.put("search_v", search_v);
		map1.put("tj_start", x_tj_start);
		map1.put("tj_end", x_tj_end);
		map1.put("yy_start", yy_start);
		map1.put("yy_end", yy_end);
		Map<String, Object> mapx = new HashMap<String, Object>();
		if ((tj_start == null || "".equals(tj_start))
				&& (tj_end == null || "".equals(tj_end))
				&& (yy_start == null || "".equals(yy_start))
				&& (yy_end == null || "".equals(yy_end))
				&& (allsearch == null || "".equals(allsearch))
				&& (order.getOwner() == null || "".equals(order.getOwner()))
				&& (order.getLicenseplate() == null || "".equals(order.getLicenseplate()))
				&& (order.getPhonenumber() == null || "".equals(order.getPhonenumber()))) {
			map1.put("page", page);
			map1.put("userid", userid);
			mapx.put("userid", userid);
			counts = iOrderService.no_pay_count(mapx);
		} else {
			s_status = "1";
			map1.put("page", -1);
		}
		if ("".equals(s_status) || s_status == null) {
			s_status = "0";
		}
		List<Order> list = iOrderService.nopay_All(map1);
		if ("".equals(status) || status == null) {
			status = "0";
		}

		// int counts=list.size();
		int total = (counts % 10 == 0) ? (counts / 10) : (counts / 10 + 1);
		// 通知数量
		int nopay_counts = iOrderService.nopayCount_fp();
		int nopayjs_counts = iOrderService.nopayCount_njs(mapx);
		map.put("nopay_counts", nopay_counts);
		map.put("nopayjs_counts", nopayjs_counts);

		map.put("status", status);
		map.put("nopayAllList", list);
		map.put("pageIndex", apageindex);
		map.put("total", total);
		map.put("user", user);

		map.put("xorder", order);
		map.put("tj_start", tj_start);
		map.put("tj_end", tj_end);
		map.put("yy_start", yy_start);
		map.put("yy_end", yy_end);
		map.put("allsearch", search_v);
		map.put("s_status", Integer.parseInt(s_status));
		return new ModelAndView("order/nopay_All");
	}

	/**
	 * 未付款带分配订单
	 * 
	 * @return
	 */

	/**
	 * 未付款待分配订单
	 * 
	 * @return
	 */
	@RequestMapping(value = "nopay_fp")
	public ModelAndView nopay_fp(ModelMap map, String status, String fp_search,
			String pageIndex, Order order, String tj_start, String tj_end,
			String yy_start, String yy_end, String s_status)
			throws UnsupportedEncodingException {
		if (pageIndex == null || pageIndex.equals("")) {
			pageIndex = "1";
		}
		String search_v = "";
		int apageindex = Integer.parseInt(pageIndex);
		Long id = TokenManager.getUserId();
		UUser user = userService.selectByPrimaryKey(id);
		long userid = 0;
/*		if (user.getPosition().equals("客服员")) {
			userid = user.getId();
		}*/
		if (fp_search != null) {
			search_v = URLDecoder.decode(fp_search, "UTF-8");
		}

		String x_tj_start = tj_start;
		String x_tj_end = tj_end;

		if (tj_start != null && !tj_start.equals("")) {
			x_tj_start += " 00:00:00";
		}
		if (tj_end != null && !tj_end.equals("")) {
			x_tj_end += " 00:00:00";
		}
		int page = (apageindex - 1) * 10;
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("order", order);
		map1.put("search_v", search_v);
		map1.put("tj_start", x_tj_start);
		map1.put("tj_end", x_tj_end);
		map1.put("yy_start", yy_start);
		map1.put("yy_end", yy_end);

		if ((tj_start == null || "".equals(tj_start))
				&& (tj_end == null || "".equals(tj_end))
				&& (yy_start == null || "".equals(yy_start))
				&& (yy_end == null || "".equals(yy_end))
				&& (fp_search == null || "".equals(fp_search))
				&& (order.getOwner() == null || "".equals(order.getOwner()))
				&& (order.getLicenseplate() == null || "".equals(order
						.getLicenseplate()))
				&& (order.getPhonenumber() == null || "".equals(order
						.getPhonenumber()))) {
			map1.put("page", page);
		} else {
			s_status = "1";
			map1.put("page", -1);
		}
		if ("".equals(s_status) || s_status == null) {
			s_status = "0";
		}
		List<Order> list = iOrderService.nopay_fp(map1);
		if ("".equals(status) || status == null) {
			status = "0";
		}

		int counts = iOrderService.nopayCount_fp();
		// int counts=list.size();
		int total = (counts % 10 == 0) ? (counts / 10) : (counts / 10 + 1);
		Map<String, Object> mapx = new HashMap<String, Object>();
		mapx.put("userid", userid);
		// 通知数量
		int nopay_counts = iOrderService.nopayCount_fp();
		int nopayjs_counts = iOrderService.nopayCount_njs(mapx);
		map.put("nopay_counts", nopay_counts);
		map.put("nopayjs_counts", nopayjs_counts);
		map.put("status", status);
		map.put("nopayList", list);
		map.put("pageIndex", apageindex);
		map.put("total", total);

		map.put("xorder", order);
		map.put("tj_start", tj_start);
		map.put("tj_end", tj_end);
		map.put("yy_start", yy_start);
		map.put("yy_end", yy_end);
		map.put("fp_search", search_v);
		map.put("s_status", Integer.parseInt(s_status));
		return new ModelAndView("order/nopay_fporder");
	}

	/**
	 * 未付款待接受订单
	 * 
	 * @return
	 */
	@RequestMapping(value = "nopaylist_js")
	public ModelAndView nopaylist_js(ModelMap map, String status,
			String pageIndex, Order order, String js_search, String tj_start,
			String tj_end, String yy_start, String yy_end, String s_status)
			throws UnsupportedEncodingException {
		if (pageIndex == null || pageIndex.equals("")) {
			pageIndex = "1";
		}
		String search_v = "";
		int apageindex = Integer.parseInt(pageIndex);

		Long id = TokenManager.getUserId();
		UUser user = userService.selectByPrimaryKey(id);
		long userid = 0;
/*		if (user.getPosition().equals("客服员")) {
			userid = user.getId();
		}*/
		if (js_search != null) {
			search_v = URLDecoder.decode(js_search, "UTF-8");
		}
		String x_tj_start = tj_start;
		String x_tj_end = tj_end;

		if (tj_start != null && !tj_start.equals("")) {
			x_tj_start += " 00:00:00";
		}
		if (tj_end != null && !tj_end.equals("")) {
			x_tj_end += " 00:00:00";
		}

		int page = (apageindex - 1) * 10;
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("order", order);
		map1.put("search_v", search_v);
		map1.put("tj_start", x_tj_start);
		map1.put("tj_end", x_tj_end);
		map1.put("yy_start", yy_start);
		map1.put("yy_end", yy_end);
		map1.put("userid", userid);
		Map<String, Object> mapx = new HashMap<String, Object>();
		mapx.put("userid", userid);

		if ((tj_start == null || "".equals(tj_start))
				&& (tj_end == null || "".equals(tj_end))
				&& (yy_start == null || "".equals(yy_start))
				&& (yy_end == null || "".equals(yy_end))
				&& (js_search == null || "".equals(js_search))
				&& (order.getOwner() == null || "".equals(order.getOwner()))
				&& (order.getLicenseplate() == null || "".equals(order.getLicenseplate()))
				&& (order.getPhonenumber() == null || "".equals(order.getPhonenumber()))) {
			        map1.put("page", page);
		} else {
			   s_status = "1";
			   map1.put("page", -1);
		}
		if ("".equals(s_status) || s_status == null) {
			s_status = "0";
		}
		List<Order> list = iOrderService.nopay_js(map1);
		if ("".equals(status) || status == null) {
			status = "0";
		}

		int counts = iOrderService.nopayCount_js(mapx);
		// int counts=list.size();
		int total = (counts % 10 == 0) ? (counts / 10) : (counts / 10 + 1);

		// 通知数量
		int nopay_counts = iOrderService.nopayCount_fp();
		int nopayjs_counts = iOrderService.nopayCount_njs(mapx);
		map.put("nopay_counts", nopay_counts);
		map.put("nopayjs_counts", nopayjs_counts);
		map.put("status", status);
		map.put("nopay_jsList", list);
		map.put("pageIndex", apageindex);
		map.put("total", total);
		map.put("user", user);

		map.put("xorder", order);
		map.put("tj_start", tj_start);
		map.put("tj_end", tj_end);
		map.put("yy_start", yy_start);
		map.put("yy_end", yy_end);
		map.put("js_search", search_v);
		map.put("s_status", Integer.parseInt(s_status));
		return new ModelAndView("order/nopayorder_js");
	}

	/**
	 * 未付款接受订单
	 * 
	 * @return
	 */
	@RequestMapping(value = "nopay_js")
	@ResponseBody
	public Map<String, Object> nopay_js(String orderid) {
		// 记录分配轨迹
		Long id = TokenManager.getUserId();
		UUser userx = userService.selectByPrimaryKey(id);
		Map<String, Object> mapx = new HashMap<String, Object>();
		mapx.put("orderid", Integer.parseInt(orderid));
		Order order = iOrderService.order_detail(mapx);
		Gj gj = new Gj(order.getOrderno(), insert_time, 1, 12,
				userx.getNickname());
		gjService.add_GJ(gj);

		iOrderService.update_nopayjs(Integer.parseInt(orderid));
		return resultMap;
	}

	/**
	 * 未付款客服员拒绝订单
	 * 
	 * @return
	 */
	@RequestMapping(value = "nopay_jj")
	@ResponseBody
	public Map<String, Object> nopay_jj(String orderid) {
		// 记录分配轨迹
		Long id = TokenManager.getUserId();
		UUser userx = userService.selectByPrimaryKey(id);
		Map<String, Object> mapx = new HashMap<String, Object>();
		mapx.put("orderid", Integer.parseInt(orderid));
		Order order = iOrderService.order_detail(mapx);
		Gj gj = new Gj(order.getOrderno(), insert_time, 1, 0,
				userx.getNickname());
		gjService.add_GJ(gj);

		iOrderService.update_nopayjj(Integer.parseInt(orderid));
		return resultMap;
	}

	/**
	 * 未付款同意付款订单转化
	 * 
	 * @return
	 */
	@RequestMapping(value = "order_tyfk")
	@ResponseBody
	public Map<String, Object> order_tyfk(String orderid) {
		// 记录分配轨迹
		Long id = TokenManager.getUserId();
		UUser userx = userService.selectByPrimaryKey(id);
		Map<String, Object> mapx = new HashMap<String, Object>();
		mapx.put("orderid", Integer.parseInt(orderid));
		Order order = iOrderService.order_detail(mapx);
		Gj gj = new Gj(order.getOrderno(), insert_time, 12, 2,
				userx.getNickname());
		gjService.add_GJ(gj);

		iOrderService.update_order_tyfk(Integer.parseInt(orderid), insert_time);
		return resultMap;
	}

	/**
	 * 未付款申请超权限审批订单转化
	 * 
	 * @return
	 */
	@RequestMapping(value = "order_sqlc")
	@ResponseBody
	public Map<String, Object> order_sqlc(String orderid,String status) {
		// 记录分配轨迹
		Long id = TokenManager.getUserId();
		UUser userx = userService.selectByPrimaryKey(id);
		Map<String, Object> mapx = new HashMap<String, Object>();
		mapx.put("orderid", Integer.parseInt(orderid));
		Order order = iOrderService.order_detail(mapx);
		Gj gj = new Gj(order.getOrderno(), insert_time, 12, 13,
				userx.getNickname());
		gjService.add_GJ(gj);
		/*status=13表示从未付款进入 15表示从缴费进入
		 * 
		 * */
		
        int suditstatus=Integer.parseInt(status);
        order.setSuditstatus(suditstatus);
		iOrderService.update_order_sqlc(order);
		return resultMap;
	}

	/**
	 * 订单转化潜在客户14
	 * 
	 * @return
	 */
	@RequestMapping(value = "order_ddsx")
	@ResponseBody
	public Map<String, Object> order_ddsx(String orderid) {

		// 记录分配轨迹
		Long id = TokenManager.getUserId();
		UUser userx = userService.selectByPrimaryKey(id);
		Map<String, Object> mapx = new HashMap<String, Object>();
		mapx.put("orderid", Integer.parseInt(orderid));
		Order order = iOrderService.order_detail(mapx);
		Gj gj = new Gj(order.getOrderno(), insert_time, 12, 14,
				userx.getNickname());
		gjService.add_GJ(gj);
		iOrderService.update_order_ddsx(Integer.parseInt(orderid));
		return resultMap;
	}

	/**
	 * 当月新进订单
	 * 
	 * @return
	 */
	@RequestMapping(value = "month_list")
	public ModelAndView month_list(ModelMap map, String status,
			String pageIndex, Order order, String m_search, String tj_start,
			String tj_end, String s_status) throws UnsupportedEncodingException {
		if (pageIndex == null || pageIndex.equals("")) {
			pageIndex = "1";
		}
		String search_v = "";
		int apageindex = Integer.parseInt(pageIndex);

		if (m_search != null) {
			search_v = URLDecoder.decode(m_search, "UTF-8");
		}
		int page = (apageindex - 1) * 10;
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("order", order);
		map1.put("search_v", search_v);

		if ((m_search == null || "".equals(m_search))
				&& (order.getOwner() == null || "".equals(order.getOwner()))
				&& (order.getLicenseplate() == null || "".equals(order
						.getLicenseplate()))
				&& (order.getPhonenumber() == null || "".equals(order
						.getPhonenumber()))) {
			map1.put("page", page);
		} else {
			s_status = "1";
			map1.put("page", -1);
		}
		if ("".equals(s_status) || s_status == null) {
			s_status = "0";
		}
		List<Order> list = iOrderService.month_list(map1);
		if ("".equals(status) || status == null) {
			status = "0";
		}
		int counts = iOrderService.count_month_list();
		int sp_count = iOrderService.spCount_order();

		int total = (counts % 10 == 0) ? (counts / 10) : (counts / 10 + 1);
		map.put("status", status);
		map.put("month_list", list);
		map.put("pageIndex", apageindex);
		map.put("total", total);
		map.put("xorder", order);
		map.put("count", counts);
		map.put("sp_count", sp_count);
		map.put("m_search", search_v);
		map.put("s_status", Integer.parseInt(s_status));
		return new ModelAndView("order/month_order");
	}

	/**
	 * 已结算订单
	 * 
	 * @return
	 */
	@RequestMapping(value = "account_list")
	public ModelAndView account_list(ModelMap map, String status,
			String pageIndex, Order order, String m_search, String tj_start,
			String tj_end, String s_status) throws UnsupportedEncodingException {
		if (pageIndex == null || pageIndex.equals("")) {
			pageIndex = "1";
		}
		String search_v = "";
		int apageindex = Integer.parseInt(pageIndex);

		if (m_search != null) {
			search_v = URLDecoder.decode(m_search, "UTF-8");
		}
		int page = (apageindex - 1) * 10;
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("order", order);
		map1.put("search_v", search_v);

		if ((m_search == null || "".equals(m_search))
				&& (order.getOwner() == null || "".equals(order.getOwner()))
				&& (order.getLicenseplate() == null || "".equals(order
						.getLicenseplate()))
				&& (order.getPhonenumber() == null || "".equals(order
						.getPhonenumber()))) {
			map1.put("page", page);
		} else {
			s_status = "1";
			map1.put("page", -1);
		}
		if ("".equals(s_status) || s_status == null) {
			s_status = "0";
		}
		List<Order> list = iOrderService.account_list(map1);
		if ("".equals(status) || status == null) {
			status = "0";
		}
		int counts = iOrderService.count_a_list();
		int sp_count = iOrderService.spCount_order();

		int total = (counts % 10 == 0) ? (counts / 10) : (counts / 10 + 1);
		map.put("status", status);
		map.put("acount_list", list);
		map.put("pageIndex", apageindex);
		map.put("total", total);
		map.put("xorder", order);
		map.put("count", counts);
		map.put("sp_count", sp_count);
		map.put("a_search", search_v);
		map.put("s_status", Integer.parseInt(s_status));
		return new ModelAndView("order/account_order");
	}

	/**
	 * 超权限审批订单
	 * 
	 * @return
	 */
	@RequestMapping(value = "sp_order")
	public ModelAndView sp_order(ModelMap map, String status, String pageIndex,
			Order order, String sp_search, String tj_start, String tj_end,
			String yy_start, String yy_end, String s_status)
			throws UnsupportedEncodingException {
		if (pageIndex == null || pageIndex.equals("")) {
			pageIndex = "1";
		}
		String search_v = "";
		int apageindex = Integer.parseInt(pageIndex);

		if (sp_search != null) {
			search_v = URLDecoder.decode(sp_search, "UTF-8");
		}
		String x_tj_start = tj_start;
		String x_tj_end = tj_end;

		if (tj_start != null && !tj_start.equals("")) {
			x_tj_start += " 00:00:00";
		}
		if (tj_end != null && !tj_end.equals("")) {
			x_tj_end += " 00:00:00";
		}

		int page = (apageindex - 1) * 10;
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("order", order);
		map1.put("search_v", search_v);
		map1.put("tj_start", x_tj_start);
		map1.put("tj_end", x_tj_end);
		map1.put("yy_start", yy_start);
		map1.put("yy_end", yy_end);

		if ((tj_start == null || "".equals(tj_start))
				&& (tj_end == null || "".equals(tj_end))
				&& (yy_start == null || "".equals(yy_start))
				&& (yy_end == null || "".equals(yy_end))
				&& (sp_search == null || "".equals(sp_search))
				&& (order.getOwner() == null || "".equals(order.getOwner()))
				&& (order.getLicenseplate() == null || "".equals(order
						.getLicenseplate()))
				&& (order.getPhonenumber() == null || "".equals(order
						.getPhonenumber()))) {
			map1.put("page", page);
		} else {
			s_status = "1";
			map1.put("page", -1);
		}
		if ("".equals(s_status) || s_status == null) {
			s_status = "0";
		}
		List<Order> list = iOrderService.sp_list(map1);
		if ("".equals(status) || status == null) {
			status = "0";
		}
		int counts = iOrderService.spCount_order();
		int total = (counts % 10 == 0) ? (counts / 10) : (counts / 10 + 1);
		map.put("status", status);
		map.put("splist", list);
		map.put("pageIndex", apageindex);
		map.put("total", total);
		map.put("xorder", order);
		map.put("tj_start", tj_start);
		map.put("tj_end", tj_end);
		map.put("yy_start", yy_start);
		map.put("yy_end", yy_end);
		map.put("count", counts);
		map.put("sp_search", search_v);
		map.put("s_status", Integer.parseInt(s_status));
		return new ModelAndView("order/order_sp");
	}

	/**
	 * 审批订单转化
	 * 
	 * @return
	 */
	@RequestMapping(value = "checkOrder")
	@ResponseBody
	public Map<String, Object> checkOrder(String orderid) {
		Map<String, Object> mapx = new HashMap<String, Object>();
		mapx.put("orderid", Integer.parseInt(orderid));
		Order order = iOrderService.order_detail(mapx);
		if(order.getSuditstatus()==13){
			order.setSuditstatus(2);
		}
		if(order.getSuditstatus()==15){
			order.setSuditstatus(9);
		}
		iOrderService.update_check(order);		
		return resultMap;
	}

	/**
	 * 潜在客户订单
	 * 
	 * @return
	 */
	@RequestMapping(value = "qz_order")
	public ModelAndView qz_order(ModelMap map, String status, String pageIndex,
			Order order, String qz_search, String tj_start, String tj_end,
			String s_status) throws UnsupportedEncodingException {
		if (pageIndex == null || pageIndex.equals("")) {
			pageIndex = "1";
		}
		String search_v = "";
		int apageindex = Integer.parseInt(pageIndex);

		if (qz_search != null) {
			search_v = URLDecoder.decode(qz_search, "UTF-8");
		}
		String x_tj_start = tj_start;
		String x_tj_end = tj_end;

		if (tj_start != null && !tj_start.equals("")) {
			x_tj_start += " 00:00:00";
		}
		if (tj_end != null && !tj_end.equals("")) {
			x_tj_end += " 00:00:00";
		}

		int page = (apageindex - 1) * 10;
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("order", order);
		map1.put("search_v", search_v);
		map1.put("tj_start", x_tj_start);
		map1.put("tj_end", x_tj_end);

		if ((tj_start == null || "".equals(tj_start))
				&& (tj_end == null || "".equals(tj_end))
				&& (qz_search == null || "".equals(qz_search))
				&& (order.getOwner() == null || "".equals(order.getOwner()))
				&& (order.getLicenseplate() == null || "".equals(order
						.getLicenseplate()))
				&& (order.getPhonenumber() == null || "".equals(order
						.getPhonenumber()))) {
			map1.put("page", page);
		} else {
			s_status = "1";
			map1.put("page", -1);
		}
		if ("".equals(s_status) || s_status == null) {
			s_status = "0";
		}
		List<Order> list = iOrderService.qz_list(map1);
		if ("".equals(status) || status == null) {
			status = "0";
		}
		int counts = iOrderService.qzCount_order();
		// int counts=list.size();
		int total = (counts % 10 == 0) ? (counts / 10) : (counts / 10 + 1);
		map.put("qz_list", list);
		map.put("pageIndex", apageindex);
		map.put("total", total);
		map.put("status", status);

		map.put("xorder", order);
		map.put("tj_start", tj_start);
		map.put("tj_end", tj_end);
		map.put("qz_search", search_v);
		map.put("s_status", Integer.parseInt(s_status));
		return new ModelAndView("order/order_qz");
	}

	/**
	 * 查询发票
	 * 
	 * @return
	 */
	@RequestMapping(value = "find_bill")
	@ResponseBody
	public Map<String, Object> find_bill(String orderno) {
		Bill bill = null;
		if (orderno != null && !orderno.equals("")) {
			bill = billService.getBillByOrderNo(orderno);
		}
		resultMap.put("bill", bill);
		return resultMap;
	}

	/**
	 * 打印编号
	 * 
	 * @return
	 */
	@RequestMapping(value = "find_dy")
	@ResponseBody
	public Map<String, Object> find_dy(String orderid) {
		Dy dy = dyService.find_dy(Integer.parseInt(orderid));
		if (dy == null) {
			Dy dy1 = new Dy();
			dy1.setOrderid(Integer.parseInt(orderid));
			dy1.setInsert_time(insert_time);
			dyService.add_dy(dy1);
		} else {
			dyService.delete_dy(dy);
			Dy dy2 = new Dy();
			dy2.setOrderid(Integer.parseInt(orderid));
			dy2.setInsert_time(insert_time);
			dyService.add_dy(dy2);
		}
		Dy dy2 = dyService.find_dy(Integer.parseInt(orderid));
		int num = dy2.getId();
		String str = String.valueOf(num);
		/*
		 * System.out.println("=="+str.length()); for(char c :
		 * str.toCharArray()){ System.out.println("--"+c); }
		 */
		int dyLength = str.length();
		String message = "";
		if (dyLength == 1) {
			message = message + "0000" + num;
		}
		if (dyLength == 2) {
			message = message + "000" + num;
		}
		if (dyLength == 3) {
			message = message + "00" + num;
		}
		if (dyLength == 4) {
			message = message + "0" + num;
		}
		if (dyLength == 5) {
			message = message + num;
		}
		resultMap.put("message", message);
		return resultMap;
	}

	public void wz_cl_Order() {
		List<Order> listO = iOrderService.wzcx_totime();
		// System.out.println("xc=="+formatter.format(new Date()));
		if (listO.size() > 0) {
			for (Order order : listO) {
				if (order.getNjdq_date() != null
						&& !"".equals(order.getNjdq_date())) {
					if (get_day(order.getNjdq_date()) <= 2) {
						iOrderService.update_wz_status(2, order.getOrderid());
					} else if (get_day(order.getNjdq_date()) > 2) {
						Up_date up_date = upService.findUp_date(order
								.getOrderno());
						if (up_date == null) {
							Up_date up_date1 = new Up_date(insert_time1,
									order.getOrderno());
							upService.addUp_date(up_date1);
							iOrderService.update_wz_status(1,
									order.getOrderid());
						} else {
							// 如果时间不为空要判断当前时间与上次时间是否大于3天
							if (back_day(up_date.getUpdate_time()) == 2) {
								Up_date up_date1 = new Up_date(insert_time1,
										order.getOrderno());
								upService.update_up_date(up_date1);
								iOrderService.update_wz_status(1,
										order.getOrderid());
							}
						}
					}
				}

			}

		}
	}

	/**
	 * 返回年检到期时间与当前时间差
	 * 
	 * @return
	 */
	public static int get_day(String todate) {
		// String todate="2017年12月12日";
		int days = 0;
		SimpleDateFormat simpleFormat1 = new SimpleDateFormat("yyyy年MM月dd日");
		try {
			Date strD = simpleFormat1.parse(todate);
			long from = new Date().getTime();
			long to = strD.getTime();
			days = (int) ((to - from) / (1000 * 60 * 60 * 24));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return days;
	}

	/**
	 * 返回上次警示时间与当前时间差
	 * 
	 * @return
	 */
	public static int back_day(String todate) {
		// String todate="2017年12月12日";
		int days = 0;
		SimpleDateFormat simpleFormat1 = new SimpleDateFormat("yyyy年MM月dd日");
		try {
			Date strD = simpleFormat1.parse(todate);
			long to = new Date().getTime();
			long from = strD.getTime();
			days = (int) ((to - from) / (1000 * 60 * 60 * 24));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return days;
	}
	
	/**
	 * 退单
	 * 
	 * @return
	 */
	@RequestMapping(value = "chargeback")
	@ResponseBody
	public Map<String, Object> chargeback(String orderid,String context) {	
		Long id = TokenManager.getUserId();
		UUser userx = userService.selectByPrimaryKey(id);
		Chargeback cb=new Chargeback();
		cb.setOperator(userx.getNickname());
		cb.setTd_reason(context);
		cb.setUpdate_time(insert_time);
		cb.setOrderid(Integer.parseInt(orderid));
		cb.setUserid(new Long(userx.getId()).intValue());
		//添加退单
		chargebackService.add_chargeback(cb); 
		//修改订单状态
		iOrderService.update_chargeback(Integer.parseInt(orderid));
		resultMap.put("messsage", "1");
		return resultMap;
	}

}
