var order_num = 0, orderno;
function order_pay_init() {
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
	if ($("#slideOne").attr("checked")) {
		$("div.slideOne").css("background", "#00bc8d");
		$("#deposit").text("￥" + (order_num / 2).toFixed(2));
	} else {
		$("div.slideOne").css("background", "#ddd");
		$("#deposit").text("￥" + order_num);
	}
	if ($("#slideTwo").attr("checked")) {
		$("div.slideTwo").css("background", "#00bc8d");
		$("#fpxx").css("display", "block");
	} else {
		$("div.slideTwo").css("background", "#ddd");
		$("#fpxx").css("display", "none");
	}
	var uuid = GetQueryString("uuid");
	if (uuid != null && uuid != "" && uuid != "undefined") {
		$("#slideOne").change(function() {
			if ($(this).attr("checked")) {
				$("div.slideOne").css("background", "#00bc8d");
				$("#deposit").text("￥" + (order_num / 2).toFixed(2));
			} else {
				$("div.slideOne").css("background", "#ddd");
				$("#deposit").text("￥" + order_num);
			}
		});
		$("#slideTwo").change(function() {
			if ($(this).attr("checked")) {
				$("div.slideTwo").css("background", "#00bc8d");
				$("#fpxx").css("display", "block");
			} else {
				$("div.slideTwo").css("background", "#ddd");
				$("#fpxx").css("display", "none");
			}
		});
		$.ajax({
			type : "POST",
			url : "/shjw/open/findByUUID.shtml",
			data : {
				"uuid" : uuid,
				"openId" : $("#openId").val()
			},
			async : false,
			cache : false,
			dataType : "json",
			success : function(data) {
				if (data.result == 1) {
					orderno = data.entity.orderno;
					$("#order_no").text(orderno);
					order_num = returnFloat(data.entity.totalorder);
					$("#order_num").text("￥" + order_num);
					$("#deposit").text("￥" + (order_num / 2).toFixed(2));
				} else {
					alert(data.msg);
					history.back(-1);
				}
			}
		});
	} else {
		alert("请求参数异常，请重试");
		history.back(-1);
	}
}
function onBridgeReady() {
	WeixinJSBridge.invoke('getBrandWCPayRequest', {
		"appId" : appId, // 公众号名称，由商户传入
		"timeStamp" : timeStamp, // 时间戳，自1970年以来的秒数
		"nonceStr" : nonceStr, // 随机串
		"package" : packageNo,
		"signType" : signType, // 微信签名方式：
		"paySign" : paySign
	// 微信签名
	}, function(res) {
		if (res.err_msg == "get_brand_wcpay_request:ok") {
			weixinPayValidate(outTradeNo);
		} // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回 ok，但并不保证它绝对可靠。
		else {
			$api.rmStorage('orderData');
			history.back(-1);
		}
	});
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
	var is_bill = 0;
	var is_deposit_payment = 0;
	var payment_amount = order_num;
	if ($("#slideOne").attr("checked")) {
		is_deposit_payment = 1;
		payment_amount = 100.00;
	}
	if ($("#slideTwo").attr("checked")) {
		is_bill = 1;
	}
	$
			.ajax({
				type : "POST",
				url : "/shjw/open/updateOrderPay.shtml",
				data : {
					"uuid" : GetQueryString("uuid"),
					"is_bill" : is_bill,
					"is_deposit_payment" : is_deposit_payment,
					"payment_amount" : payment_amount,
					"order_no" : $("#order_no").text(),
					"openId" : $("#openId").val()
				},
				async : false,
				cache : false,
				dataType : "json",
				success : function(data) {
					if (data.result == 1) {
						outTradeNo = $("#order_no").text();
						WeixinJSBridge
								.invoke(
										'getBrandWCPayRequest',
										{
											"appId" : data.appid, // 公众号名称，由商户传入
											"timeStamp" : data.timestamp, // 时间戳，自1970年以来的秒数
											"nonceStr" : data.noncestr, // 随机串
											"package" : data.packageNo,
											"signType" : data.signType, // 微信签名方式：
											"paySign" : data.paySign
										// 微信签名
										},
										function(res) {
											WeixinJSBridge.log(res.err_msg);
											if (res.err_msg == "get_brand_wcpay_request:ok") {
												window.location.href = window.location.protocol
														+ "//"
														+ window.location.host
														+ "/shjw/wx/pay_result_ok.html?orderno="
														+ outTradeNo;
											} else if (res.err_msg == "get_brand_wcpay_request:cancel") {
												$.toast("用户取消", "text");
											} else {
												$.toast("支付失败", "forbidden",
														function() {
															window.location
																	.reload();// 刷新页面
														});
											}
										});
					} else {
						alert(data.msg);
					}
				}
			});
}
function order_fp() {
	window.location.href = window.location.protocol + "//"
			+ window.location.host + "/shjw/wx/order_fp.html?orderno="
			+ orderno;
}

/**
 * 验证支付是否成功
 */
function weixinPayValidate(outTradeNo) {

	var data = {
		outTradeNo : outTradeNo
	}
	extApi.ajax('wap/weixin_validate.action', data, function(data) {

		if (JSON.parse(data).flag) {
			newOrder();
		} else {
			// $api.rmStorage('orderData');
			history.back(-1);
		}
	});
}

/**
 * 下单
 */
function newOrder() {
	var orderData = $api.getStorage("orderData");
	var key = $api.getStorage("orderDataKey");
	var url;
	if (key == "shopData") {
		url = 'shop/order_new.action';
	} else {
		url = 'shop/order_pre_new.action';
	}
	extApi.ajax(url, orderData, newOrerCallback);

}

/**
 * 下单回调函数
 */
function newOrerCallback(data) {
	// 异常
	if (data.flag == false) {
		extApi.toast('下单失败');
		return;
	}
	extApi.toast('订单已经生成');
	var key = $api.getStorage("orderDataKey");

	if (key == "shopData") {
		// 如果用户选择了积分兑换，下单成功后将用户的积分减去
		minUserScore();
	} else {
		ordernewSuccess();
	}
}

/**
 * 如果用户选择了积分兑换，下单成功后将用户的积分清零
 */
function minUserScore() {
	var user = $api.getStorage('user');
	var score = 0;

	if ($("input[type=checkbox]").prop('checked')) {
		score = user.store;
	}
	var data = {
		buyerId : user.id,
		score : score
	};

	extApi.ajax('shop/buyer_score_edit.action', data, function(data) {
		user.store = data.store;
		$api.setStorage('user', user);
		ordernewSuccess();
	});
}

function ordernewSuccess() {

	var key = $api.getStorage("orderDataKey");
	$api.rmStorage(key);
	$api.rmStorage('orderData');
	$api.rmStorage('orderDataKey');
	var count = $api.getStorage("orderDataCount");
	// 购物车减去数量监听
	shopcart.minCountTocart(count);
	// 清理缓存
	window.history.go(-2);
}
