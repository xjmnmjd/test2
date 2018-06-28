/*
 * create xj 2017-8-15
 */
var list, xj_uuid;
var orderstatus;
function order_detail_init() {
	CompareVersion();
	var wxopenid = getcookie("wxopenid");
	var access_code = GetQueryString("code");
	if (wxopenid == "" || wxopenid == null || wxopenid == "null") {
		var fromurl = location.href;
		if (access_code == null) {
			var url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="
					+ getAppId()
					+ "&redirect_uri="
					+ encodeURIComponent(fromurl)
					+ "&response_type=code&scope=snsapi_base&state=STATE%23wechat_redirect&connect_redirect=1#wechat_redirect";
			location.href = url;
		} else {
			$.ajax({
				type : "get",
				url : "/shjw/open/getOpenId.shtml",
				async : false,
				cache : false,
				data : {
					code : access_code
				},
				dataType : "json",
				success : function(result) {
					if (result != null && result.result == 1) {
						addcookie("wxopenid", result.openId, 360000);
						$("#openId").val(result.openId);
						init();
					} else {
						location.href = fromurl;
					}
				}
			});
		}
	} else {
		$("#openId").val(wxopenid);
		init();
	}
}
function init() {
	FastClick.attach(document.body);
	var orderno = GetQueryString("orderno");
	if (orderno != null && orderno != "" && orderno != "undefined") {
		$
				.ajax({
					type : "get",
					url : "/shjw/open/getStationByOrderNo.shtml",
					async : false,
					data : {
						"openId" : $("#openId").val(),
						"orderno" : orderno
					},
					cache : false,
					dataType : "json",
					success : function(result) {
						if (result != null && result.result == 1) {
							orderstatus=result.order.orderstatus;
							if (result.order.orderstatus == 2) {
								// $("#pay_status").text("已支付");
								var suditstatus = result.order.suditstatus;
								if (suditstatus == 0) {
									$("#pay_status").text("待分配");
								} else if (suditstatus == 1) {
									$("#pay_status").text("待接收");
								} else if (suditstatus == 2) {
									$("#pay_status").text("违章查询中");
								} else if (suditstatus == 3) {
									$("#pay_status").text("违章处理中");
								} else if (suditstatus == 4) {
									$("#pay_status").text("资料收集中");
								} else if (suditstatus == 5) {
									$("#pay_status").text("委托办理中");
								} else if (suditstatus == 6) {
									$("#pay_status").text("资料已寄等待中");
								} else if (suditstatus == 7) {
									$("#pay_status").text("缴费确认中");
								} else if (suditstatus == 8) {
									$("#pay_status").text("预约年检中");
								} else if (suditstatus == 9) {
									$("#pay_status").text("待年检");
								} else if (suditstatus == 10) {
									$("#pay_status").text("年检通过");
								} else if (suditstatus == 11) {
									$("#pay_status").text("年检完成");
								} else if (suditstatus == 12) {
									$("#pay_status").text("未付款已接受");
								} else if (suditstatus == 13) {
									$("#pay_status").text("超权限审批中");
								} else if (suditstatus == 14) {
									$("#pay_status").text("潜在客户");
								}
								$("#bottom_menu").css("display", "none");
							} else {
								$("#pay_status").text("未支付");
								$("#lj_pay").attr("uuid", result.order.uuid);
								$("#lj_pay").attr("station_id",
										result.order.station_id);
							}
							var rigisterdate = result.order.rigisterdate;
							var date = new Date();
							var curDate = date.getFullYear() + "-"
									+ (date.getMonth() + 1) + "-"
									+ date.getDate();
							var years = DateDiff(curDate, rigisterdate);
							var content = result.order.vehicle + "/"
									+ result.order.cartype + "/" + years
									+ "年以内";
							$("#carNo").text(result.order.licenseplate);
							$("#car_infor").text(content);
							$("#order_no").text(result.order.orderno);
							$("#nj_company").text(result.order.station);
							$("#yy_time").text(result.order.appointmenttime);
							$("#qj_address").text(
									result.order.collect_materials_address);
							$("#nj_fee").text("￥" + result.order.nj_fee);
							$("#hb_nj_fee").text("￥" + result.order.hb_nj_fee);
							$("#wt_fee").text("￥" + result.order.wt_fee);
							$("#wq_fee").text("￥" + result.order.wq_fee);
							xj_uuid = result.order.uuid;
							$("#totalorder")
									.text("￥" + result.order.totalorder);
							var rigisterdate = result.order.rigisterdate;
							var date = new Date();
							var curDate = date.getFullYear() + "-"
									+ (date.getMonth() + 1) + "-"
									+ date.getDate();
							var years = DateDiff(curDate, rigisterdate);
							if (result.wt != null && result.wt != "undefined"
									&& result.wt != "") {
								if (years < 7) {
									$("#nj_zl").text(result.wt.wt_mj_zl);
								} else {
									$("#nj_zl").text(result.wt.wt_fmj_zl);
								}
								$("#nj_bz").text(result.wt.wt_remark);
							}
							var wz_img_detail = "";
							if (result.wz_img_list != null
									&& result.wz_img_list.length > 0) {
								for (var n = 0; n < result.wz_img_list.length; n++) {
									wz_img_detail += "<div><img src='"
											+ result.wz_img_list[n].img_path
											+ "' /></div>";
								}
							} else {
								wz_img_detail += "<div style='text-align:center;padding:1rem;'>暂无违章相关信息</div>";
							}
							$("#wz_detail").append(wz_img_detail);
						} else {
							alert(result.msg);
							history.back(-1);
						}
					}
				});
	} else {
		alert("请求参数异常");
		history.back(-1);
	}
	$("#more_state").click(
			function() {
				window.location.href = window.location.protocol + "//"
						+ window.location.host
						+ "/shjw/wx/order_state_detail.html?orderno="
						+ GetQueryString("orderno") + "&state=" + orderstatus;
			});
}
function pay(t) {
	window.location.href = window.location.protocol + "//"
			+ window.location.host + "/shjw/wx/pay.html?uuid=" + xj_uuid
			+ "&jid=" + $(t).attr("station_id");
}
function del() {
	$.ajax({
		type : "get",
		url : "/shjw/open/delOrderByOrderNo.shtml",
		async : true,
		cache : false,
		data : {
			orderno : $("#order_no").text()
		},
		dataType : "json",
		success : function(result) {
			if (result != null && result.result == 1) {
				window.location.href = window.location.protocol + "//"
						+ window.location.host + "/shjw/wx/order.html";
			} else {
				alert(result.msg);
			}
		}
	});
}