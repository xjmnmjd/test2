var nj_fee = 0, hb_nj_fee = 0, wt_fee = 0, totalorder = 0, station_id = 0, wq_fee = 0, xj_clxz, xj_clzl,xj_isInsure=0;
function pay_init() {
	CompareVersion();
	var wxopenid = getcookie("wxopenid");
	var access_code = GetQueryString("code");
	if (wxopenid == "" || wxopenid == null || wxopenid == "null") {
		var fromurl = location.href;
		if (access_code == null) {
			var url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+getAppId()+"&redirect_uri="
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
	var jcz_id = GetQueryString("jid");
	var uuid = GetQueryString("uuid");
	if (jcz_id != null && jcz_id != "" && jcz_id != "undefined" && uuid != null
			&& uuid != "" && uuid != "undefined") {
		$.ajax({
			type : "get",
			url : "/shjw/open/getStationById.shtml",
			async : false,
			data : {
				"stationId" : jcz_id,
				"uuid" : uuid,
				"openId" : $("#openId").val()
			},
			cache : false,
			dataType : "json",
			success : function(result) {
				if (result != null && result.result == 1) {
					$("#jcz_no").text(result.station.station_no);
					$("#jcz_address").text(result.station.station_address);
					$("#jcz_phone").text("电话：" + result.station.station_phone);
					station_id = result.station.stationid;
					var carNo = result.order.licenseplate;
					$("#car_no").text(carNo);
					var rigisterdate = result.order.rigisterdate;
					xj_clxz = result.order.vehicle;
					xj_clzl = result.order.cartype;
					xj_isInsure=result.order.injury_accident_last_year;
					var date = new Date();
					var curDate = date.getFullYear() + "-"
							+ (date.getMonth() + 1) + "-" + date.getDate();
					var years = DateDiff(curDate, rigisterdate);
					if (years < 7) {
						hb_nj_fee = returnFloat(result.hb_time_a);
					} else if (years < 11) {
						hb_nj_fee = returnFloat(result.hb_time_b);
					} else if (years < 14) {
						hb_nj_fee = returnFloat(result.hb_time_c);
					} else {
						hb_nj_fee = returnFloat(result.hb_time_d);
					}
					$("#pay_hb_fee").text("￥" + hb_nj_fee);
					totalorder = parseFloat(hb_nj_fee);
					if (carNo.indexOf("沪") == 0) {
						$("#foreign_car").text("【沪牌】");
					} else {
						$("#foreign_car").text("【外牌】");
					}
					var xj_flag = isMJ(rigisterdate);
					if (xj_flag) {
						nj_fee = returnFloat(result.nj_fee);
						wq_fee = returnFloat(result.wq_fee);
					} else {
						nj_fee = returnFloat(0);
						wq_fee = returnFloat(0);
					}
					$("#pay_nj_fee").text("￥" + nj_fee);
					$("#pay_wq_fee").text("￥" + wq_fee);
					totalorder += parseFloat(nj_fee);
					totalorder += parseFloat(wq_fee);
					$("#type_nature").text(
							result.order.vehicle + "/" + result.order.cartype
									+ "/" + years + "年之内");
					if (result.wt != null && result.wt != "undefined"
							&& result.wt != "") {
						if (years < 7) {
							$("#pay_wt_fee").text(
									"￥" + returnFloat(result.wt.wt_mj_fee));
							wt_fee = returnFloat(result.wt.wt_mj_fee);
							$("#nj_zl").text(result.wt.wt_mj_zl);
							totalorder += parseFloat(result.wt.wt_mj_fee);
						} else {
							$("#pay_wt_fee").text(
									"￥" + returnFloat(result.wt.wt_fmj_fee));
							wt_fee = returnFloat(result.wt.wt_fmj_fee);
							$("#nj_zl").text(result.wt.wt_fmj_zl);
							totalorder += parseFloat(result.wt.wt_fmj_fee);
						}
						$("#nj_bz").text(result.wt.wt_remark);
					} else {
						$("#pay_wt_fee").text("￥0.00");
					}
					if (result.order.is_collect_car == 1) {
						totalorder += 200;
						$("#pay_jsc_fee").text("￥200.00");
						$("#jsc").css("display", "block");
					}
					$("#pay_sum").text("￥" + returnFloat(totalorder));
					$("#pay_sum_all").text("￥" + returnFloat(totalorder));
				} else {
					alert("服务异常，请重试");
					history.back(-1);
				}
			}
		});
	} else {
		alert("非法访问，即将关闭");
		history.back(-1);
	}
}
// 判断是否需要免检
function isMJ(registerDate) {
	var flag = false;
	var date = new Date();
	var curDate = date.getFullYear() + "-" + (date.getMonth() + 1) + "-"
			+ date.getDate();
	var date1 = curDate.split("-");
	var date2 = registerDate.split("-");
	var m = 0;
	if (parseInt(date1) - parseInt(date2) >= 0) {
		m = parseInt(date1[0]) - parseInt(date2[0]) + 1;
		if (parseInt(date1[1]) < parseInt(date2[1])) {
			m = m - 1;
		} 
	} else {
		// 异常暂定返回true
		flag = true;
	}
	if (m < 7) {
		if (xj_clxz.indexOf("非营运") >= 0) {
			if (xj_clzl.indexOf("5") >= 0
					&& (xj_clzl.indexOf("汽油") >= 0 || xj_clzl.indexOf("柴油") >= 0)) {
				if (xj_isInsure == 1) {
					flag = true;
				} else {
					flag = false;
				}
			} else {
				flag = true;
			}
		} else {
			flag = true;
		}
	} else {
		flag = true;
	}
	return flag;
}
function returnFloat(val) {
	var value = Math.round(parseFloat(val) * 100) / 100;
	var xsd = value.toString().split(".");
	if (xsd.length == 1) {
		value = value.toString() + ".00";
		return value;
	}
	if (xsd.length > 1) {
		if (xsd[1].length < 2) {
			value = value.toString() + "0";
		}
		return value;
	}
}
function order_pay() {
	$.ajax({
		type : "POST",
		url : "/shjw/open/updatePay.shtml",
		async : false,
		cache : false,
		data : {
			nj_fee : nj_fee,
			hb_nj_fee : hb_nj_fee,
			wt_fee : wt_fee,
			wq_fee : wq_fee,
			totalorder : totalorder,
			uuid : GetQueryString("uuid"),
			station_id : station_id,
			station : $("#jcz_no").text()
		},
		dataType : "json",
		success : function(data) {
			if (data.result == 1) {
				window.location.href = window.location.protocol + "//"
						+ window.location.host
						+ "/shjw/wx/order_pay.html?uuid="
						+ GetQueryString("uuid");
			} else {
				alert(data.msg);
			}
		}
	});

}
function car_manager() {
	window.location.href = window.location.protocol + "//"
			+ window.location.host + "/shjw/wx/car_manger.html";
}
