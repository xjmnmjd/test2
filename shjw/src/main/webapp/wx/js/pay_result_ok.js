function pay_result_ok_init() {
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
	var orderno = GetQueryString("orderno");
	if (orderno != null && orderno != "" && orderno != "undefined") {
		$.ajax({
			type : "POST",
			url : "/shjw/open/findByOrderNo.shtml",
			data : {
				"orderno" : orderno,
				"openId" : $("#openId").val()
			},
			async : false,
			cache : false,
			dataType : "json",
			success : function(data) {
				if (data.result == 1) {
					$("#order_no").text(orderno);
					$("#pay_time").text(data.entity.time_end);
					var fee = parseInt(data.entity.cash_fee);
					$("#pay_fee").text("￥" + (fee / 100));
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
function detail() {
	window.location.href = window.location.protocol + "//"
			+ window.location.host + "/shjw/wx/order.html";
}
function goBack() {
	history.back(-1);
}