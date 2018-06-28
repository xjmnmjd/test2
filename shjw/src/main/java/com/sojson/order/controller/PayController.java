package com.sojson.order.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.aliyuncs.exceptions.ClientException;
import com.sojson.common.controller.BaseController;
import com.sojson.common.model.Bill;
import com.sojson.common.model.ChangeRecord;
import com.sojson.common.model.Gj;
import com.sojson.common.model.Order;
import com.sojson.common.model.Pay;
import com.sojson.common.model.UUser;
import com.sojson.common.utils.AliyunSmsUtil;
import com.sojson.core.shiro.token.manager.TokenManager;
import com.sojson.order.service.GjService;
import com.sojson.order.service.IChangeRecord;
import com.sojson.order.service.IOrderService;
import com.sojson.order.service.PayService;
import com.sojson.user.service.UUserService;
import com.sojson.wx.service.BillService;

@Controller
@Scope(value = "prototype")
@RequestMapping("pay")
public class PayController extends BaseController {
	@Resource
	PayService payService;
	@Resource
	BillService billService;
	@Resource
	IOrderService iOrderService;
	@Resource
	GjService gjService;
	@Resource
	IChangeRecord ichangeRecord;
	@Autowired
	UUserService userService;
	String insert_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

	/**
	 * 添加应付总额
	 * 
	 * @return
	 */
	@RequestMapping(value = "add_yf")
	@ResponseBody
	public Map<String, Object> add_yf(Pay pay) {
		pay.setPay_time(insert_time);
		pay.setPay_type(3);
		payService.add_pay(pay);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderid", pay.getOrderid());
		Order order = iOrderService.order_detail(map);
		//应付总额
		order.setCopetotal(order.getCopetotal() + pay.getPay_money());
		iOrderService.update_Dpay(order);
		return resultMap;
	}
	/**
	 * 添加线下支付
	 * 
	 * @return
	 */
	@RequestMapping(value = "add_pay")
	@ResponseBody
	public Map<String, Object> add_pay(Pay pay) {
		pay.setPay_time(insert_time);
		pay.setPay_type(1);
		payService.add_pay(pay);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderid", pay.getOrderid());
		Order order = iOrderService.order_detail(map);
		// 线下
		order.setOfflinepayment(order.getOfflinepayment() + pay.getPay_money());
		// 实际支付
		order.setPayment_amount(order.getPayment_amount() + pay.getPay_money());
		//应付总额
		//order.setCopetotal(order.getCopetotal() + pay.getPay_money());
		iOrderService.update_Dpay(order);
		return resultMap;
	}

	/**
	 * 修改
	 * 
	 * @return
	 */
	@RequestMapping(value = "update_pay")
	@ResponseBody
	public Map<String, Object> update_pay(Pay pay, String id, String pay_xx2,
			String zf_money2,String pay_yf,String changeCause) {
		double pay_xx = 0;
		double zf_money = 0;
		double yf_money = 0;
		pay.setId(Integer.parseInt(id));
		pay.setPay_time(insert_time);
		payService.update_pay(pay);
	     Map<String, Object> mapx = new HashMap<String, Object>();
	     mapx.put("orderid", pay.getOrderid());
	     Order order = iOrderService.order_detail(mapx);
	     
	        Long fid = TokenManager.getUserId();
			UUser userx = userService.selectByPrimaryKey(fid);
	        int cid=fid.intValue();
			ChangeRecord cr=new ChangeRecord();
	        cr.setChangeCause(changeCause);
	        cr.setUserid(cid);
	        cr.setOperator(userx.getNickname());
	        cr.setOrderno(order.getOrderno());
	        cr.setUpdate_time(insert_time);
	        cr.setLicenseplate(order.getLicenseplate());	        
		// 修改线下支付
		if (pay_xx2 != null) {
	        cr.setChangeType(5);
			pay_xx = Double.parseDouble(pay_xx2);
			order.setOfflinepayment(order.getOfflinepayment() - pay_xx
					+ pay.getPay_money());
			order.setPayment_amount(order.getPayment_amount() - pay_xx
					+ pay.getPay_money());
        
		}
		// 修改支出
		if (zf_money2 != null) {
	        cr.setChangeType(6);
			zf_money = Double.parseDouble(zf_money2);
			order.setPay(order.getPay() - zf_money + pay.getPay_money());
			// 应付总额
		}
		// 修改应付总额
		if (pay_yf != null) {
	        cr.setChangeType(7);
			yf_money = Double.parseDouble(pay_yf);
			order.setCopetotal(order.getCopetotal() - yf_money + pay.getPay_money());
			// 应付总额
		}
		ichangeRecord.addChangeRecord(cr);
		iOrderService.update_Dpay(order);
		return resultMap;
	}
	
	
	
	/**
	 * 删除
	 * 
	 * @return
	 */
	@RequestMapping(value = "deletePay")
	@ResponseBody
	public Map<String, Object> deletePay(String payid,String status,String changeCause) {
		Pay pay = payService.findone_pay(Integer.parseInt(payid));
		payService.deletePay(Integer.parseInt(payid));
		int s_status=Integer.parseInt(status);
		     Map<String, Object> mapx = new HashMap<String, Object>();
	         mapx.put("orderid", pay.getOrderid());
			 Order order = iOrderService.order_detail(mapx);
				Long id = TokenManager.getUserId();
				UUser userx = userService.selectByPrimaryKey(id);
		        int cid=id.intValue();
				ChangeRecord cr=new ChangeRecord();
		        cr.setChangeCause(changeCause);
		        cr.setUserid(cid);
		        cr.setOperator(userx.getNickname());
		        cr.setOrderno(order.getOrderno());
		        cr.setUpdate_time(insert_time);
		        cr.setLicenseplate(order.getLicenseplate());
		if(s_status==1){
	        cr.setChangeType(2);
			//修改应付总额
			order.setCopetotal(order.getCopetotal()-pay.getPay_money());		
		}else if(s_status==2){
	        cr.setChangeType(3);

			//修改线下支付
			order.setOfflinepayment(order.getOfflinepayment()-pay.getPay_money());
			order.setPayment_amount(order.getPayment_amount()-pay.getPay_money());
		}else if(s_status==3){
	        cr.setChangeType(4);
			//修改支出
			order.setPay(order.getPay()-pay.getPay_money());
		}
		ichangeRecord.addChangeRecord(cr);

		iOrderService.update_Dpay(order);
		return resultMap;
	}

	/**
	 * 支出
	 * 
	 * @return
	 */
	@RequestMapping(value = "add_zf")
	@ResponseBody
	public Map<String, Object> add_zf(Pay pay) {
		pay.setPay_time(insert_time);
		pay.setPay_type(2);
		payService.add_pay(pay);
		   Map<String, Object> mapx = new HashMap<String, Object>();
		     mapx.put("orderid", pay.getOrderid());
		Order order = iOrderService.order_detail(mapx);
		// 支出
		order.setPay(order.getPay() + pay.getPay_money());
		// 应付总额
		// order.setCopetotal(order.getCopetotal()+pay.getPay_money());
		iOrderService.update_Dpay(order);
		return resultMap;
	}

	/**
	 * 单个查询
	 * 
	 * @return
	 */
	@RequestMapping(value = "findone_pay")
	@ResponseBody
	public Map<String, Object> findone_pay(String id) {
		Pay pay = payService.findone_pay(Integer.parseInt(id));
		resultMap.put("pay", pay);
		return resultMap;
	}

	/**
	 * 付款页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "fukuan")
	public ModelAndView fukuan(ModelMap map, String orderid, String status,
			String user_qx) {

		Long id = TokenManager.getUserId();
		UUser user = userService.selectByPrimaryKey(id);
		if (user.getPosition().equals("系统管理员")) {
			user_qx = "1";
		}
		if ("".equals(user_qx) || user_qx == null) {
			user_qx = "0";
		}
		if ("".equals(status) || status == null) {
			status = "0";
		}
		// 订单
	     Map<String, Object> mapx = new HashMap<String, Object>();
	     mapx.put("orderid", Integer.parseInt(orderid));
		Order order = iOrderService.order_detail(mapx);
		// 应付总额
		List<Pay> yf_list = payService.getOrder_yf(order.getOrderid());
		// 线下支付
		List<Pay> xx_list = payService.getOrder_xx(order.getOrderid());
		// 支出
		List<Pay> zf_list = payService.getOrder_zf(order.getOrderid());
		// 查询发票
		String order_no = order.getOrderno();
		Bill bill = billService.getBillByOrderNo(order_no);
		map.put("xx_list", xx_list);
		map.put("zf_list", zf_list);
		map.put("yf_list", yf_list);
	
/*		if(order.getCash_fee()!=null){
			double cash_fee=Double.valueOf(order.getCash_fee()).doubleValue();
			cash_fee=cash_fee/100;
			order.setCash_fee(Double.toString(cash_fee));
		}*/
		map.put("order", order);
		map.put("bill", bill);
		map.put("status", Integer.parseInt(status));
		map.put("user_qx", Integer.parseInt(user_qx));
		return new ModelAndView("order/fukuan");
	}

	/**
	 * 缴费确认
	 * 
	 * @return
	 * @throws ClientException 
	 */
	@RequestMapping(value = "order_jfqr_nj")
	@ResponseBody
	public Map<String, Object> order_jfqr_nj(String orderid) throws ClientException {	
	     Map<String, Object> map = new HashMap<String, Object>();
	     map.put("orderid", Integer.parseInt(orderid));
		Order order_detail=iOrderService.order_detail(map);
		/*Order order=new Order();
		double offlinepayment=0;//线下支付
		double pay=0;//支出
		double totalorder=order_detail.getTotalorder();//订单总额
		double onlinepayment=order_detail.getOnlinepayment();//线上支付
		double copetotal=order_detail.getCopetotal();//应付总额
		double payment_amount=order_detail.getPayment_amount();//实际支付
		  //线下支付
		  List<Pay> xx_list=payService.getOrder_xx(Integer.parseInt(orderid));
		  for(Pay pay_xx:xx_list){
			  offlinepayment+=pay_xx.getPay_money();
		  }
		  //支出
		  List<Pay> zf_list=payService.getOrder_zf(Integer.parseInt(orderid));
		  for(Pay pay_zf:zf_list){
			  pay+=pay_zf.getPay_money();
		  }
		  计算实际支付=实际支付(线上支付)+线下支付
		  payment_amount=payment_amount+offlinepayment;
		  计算应付总额=订单总额+支出
		  copetotal=totalorder+pay;
		  
		  order.setOrderid(Integer.parseInt(orderid));
		  order.setOfflinepayment(offlinepayment);
		  order.setPay(pay);
		  order.setCopetotal(copetotal);
		  order.setPayment_amount(payment_amount);*/
		  //添加缴费时间
		  double pay=order_detail.getPay();//支出
		  double payment_amount=order_detail.getPayment_amount();//实际支付
		  //线下支付
		  List<Pay> xx_list=payService.getOrder_xx(Integer.parseInt(orderid));
		  //支出
		  List<Pay> zf_list=payService.getOrder_zf(Integer.parseInt(orderid));
		  double xx_wt_money=0;//线下支付委托服务费
		  double xx_sx_money=0;//线下支付上线检测费费
		  double xx_wz_money=0;//线下支付违章费
		  double xx_yk_money=0;//线下支付余款支付费用
		  double xx_other_money=0;//线下支付其他费用
		  for(Pay pay1:xx_list){
			  if(pay1.getPay_kind()==1){
				  xx_wt_money+=pay1.getPay_money(); 
			  }
			  if(pay1.getPay_kind()==2){
				  xx_sx_money+=pay1.getPay_money(); 
			  }
			  if(pay1.getPay_kind()==3){
				  xx_wz_money+=pay1.getPay_money(); 
			  }
			  if(pay1.getPay_kind()==4){
				  xx_yk_money+=pay1.getPay_money(); 
			  }
			  if(pay1.getPay_kind()==5){
				  xx_other_money+=pay1.getPay_money(); 
			  }
		  }
		  
		  
		  double zf_wt_money=0;//支出委托服务费
		  double zf_sx_money=0;//支出上线检测费费
		  double zf_wz_money=0;//支出违章费
		  double zf_yk_money=0;//支出余款支付费用
		  double zf_other_money=0;//支出其他费用
		  
		  
		  for(Pay pay1:zf_list){
			  if(pay1.getPay_kind()==1){
				  zf_wt_money+=pay1.getPay_money(); 
			  }
			  if(pay1.getPay_kind()==2){
				  zf_sx_money+=pay1.getPay_money(); 
			  }
			  if(pay1.getPay_kind()==3){
				  zf_wz_money+=pay1.getPay_money(); 
			  }
			  if(pay1.getPay_kind()==4){
				  zf_yk_money+=pay1.getPay_money(); 
			  }
			  if(pay1.getPay_kind()==5){
				  zf_other_money+=pay1.getPay_money(); 
			  }
		  }
		  String xy="";
		  if(xx_wt_money<zf_wt_money){
			 xy+="1";			  
		  }
		  if(xx_sx_money<zf_sx_money){
			 xy+="2";			  
		  }
		  if(xx_wz_money<zf_wz_money){
			 xy+="3";			  
		  }
		  if(xx_yk_money<zf_yk_money){
			 xy+="4";			  
		  }
		  if(xx_other_money<zf_other_money){
			 xy+="5";			  
		  }
		  int suditstatus=9;//到年检
		  //默认是进入预约年检，如果支出大于实际支付则要进入差权限审批
		    //记录分配轨迹
		  //2018 6/10修改不进入超权限审批
			Long id = TokenManager.getUserId();
			UUser userx = userService.selectByPrimaryKey(id);
		/*   if(!xy.equals("")||pay>payment_amount){*/
			  if(pay>payment_amount){
			     suditstatus=9;
				 Gj gj=new Gj(order_detail.getOrderno(), insert_time, 7, 15, userx.getNickname());
			    gjService.add_GJ(gj);
			   }else{				   
			    Gj gj=new Gj(order_detail.getOrderno(), insert_time, 7, 9, userx.getNickname());
				 gjService.add_GJ(gj);   
			   }
           order_detail.setSuditstatus(suditstatus);
		   order_detail.setFk_time(insert_time);
		   iOrderService.update_jf(order_detail);
		   //判断超权限原因
		   String message="缴费成功";
		   if(xy.equals("1")){			   
			   message="委托服务费超支";
		   }
		   if(xy.equals("2")){			   
			   message="上线检测费超支";
		   }
		   if(xy.equals("3")){			   
			   message="违章费超支";
		   }
		   if(xy.equals("4")){			   
			   message="余款超支";
		   }
		   if(xy.equals("5")){			   
			   message="其他超支";
		   }
		   
		   if(xy.equals("12")){			   
			   message="委托服务费,上线检测费超支";
		   }
		   if(xy.equals("13")){			   
			   message="委托服务费,违章费超支";
		   }
		   if(xy.equals("14")){			   
			   message="委托服务费,余款超支";
		   }
		   if(xy.equals("15")){			   
			   message="委托服务费,其他超支";
		   }
		   
		   if(xy.equals("23")){			   
			   message="上线检测费,违章费超支";
		   }
		   if(xy.equals("24")){			   
			   message="上线检测费,余款超支";
		   }
		   if(xy.equals("25")){			   
			   message="上线检测费,其他费用超支";
		   }
		   
		   if(xy.equals("34")){			   
			   message="违章费,余款超支";
		   }
		   if(xy.equals("35")){			   
			   message="违章,其他费用超支";
		   }
		   
		   if(xy.equals("45")){			   
			   message="余款费用,其他费用超支";
		   }
		   
		   if(xy.equals("123")){			   
			   message="委托服务费,上线检测费,违章费用超支";
		   }
		   if(xy.equals("124")){			   
			   message="委托服务费,上线检测费,余款费用超支";
		   }
		   if(xy.equals("125")){			   
			   message="委托服务费,上线检测费,违其他费用超支";
		   }
		   if(xy.equals("134")){			   
			   message="委托服务费,违章费,违余款费用超支";
		   }
		   if(xy.equals("135")){			   
			   message="委托服务费,违章费,其他费用超支";
		   }
		   if(xy.equals("145")){			   
			   message="委托服务费,余款,其他费用超支";
		   }
		   if(xy.equals("234")){			   
			   message="上线检测费,违章费,余款费用超支";
		   }
		   if(xy.equals("235")){			   
			   message="上线检测费,违章费,其他费用超支";
		   }
		   if(xy.equals("345")){			   
			   message="违章费,余款费用,其他费用超支";
		   }
		   if(xy.equals("1234")){			   
			   message="委托服务费,上线检测费,违章费,余款费用超支";
		   }
		   if(xy.equals("2345")){			   
			   message="上线检测费,违章费,余款费用,其他费用超支";
		   }
		   if(xy.equals("2345")){			   
			   message="委托服务费，上线检测费,违章费,余款费用,其他费用超支";
		   }
	/*	   if(pay>payment_amount){   	   
        	  message="支出总额超支:"+message;
           }
		   if(!xy.equals("")){
			   message+="订单进入超权限审批";  
		   }*/
		   message="缴费成功";
		   if(pay>payment_amount){   	   
	        	  message="支出总额超支:进入超权限审批";
	           }
		   if("缴费成功".equals(message)){		
		        AliyunSmsUtil.sendSms(order_detail.getPhonenumber(), AliyunSmsUtil.YYNJ_TemplateCode, "");
		   }
		   resultMap.put("message",message);

		return resultMap;
	}
	
	

}
