/*
 * create xj 2017-8-15
 */
var list;
function order_init() {
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
	$
			.ajax({
				type : "post",
				url : "/shjw/open/findOrderByOpenId.shtml",
				async : false,
				cache : false,
				data : {
					openid : $("#openId").val(),
					status : 2
				},
				dataType : "json",
				success : function(result) {
					if (result != null && result.result == 1) {
						if (result.list != null && result.list.length > 0) {
							list = result.list;
							$("#order_content").html("");
							var content = "";
							for (var i = 0; i < list.length; i++) {
								content += "<ul class='clearfix' ><li orderno='"
										+ list[i].orderno
										+ "' station_id='"
										+ list[i].station_id
										+ "'><img src='img/ico_j.png' /><p class='text m-left'>"
										+ list[i].station
										+ "</p><p class='c-ff1e3c pay-no'>";
								if (list[i].orderstatus == 2) {
									content += "已支付";
								} else {
									content += "待支付";
								}
								content += "</p><p class='pay-no moreState' orderno='"
										+ list[i].orderno
										+ "' orderstatus='"
										+ list[i].orderstatus
										+ "' >进度查询</p></li><li onclick='detail(this)' orderno='"
										+ list[i].orderno
										+ "' station_id='"
										+ list[i].station_id
										+ "'><p class='text m-left'>"
										+ list[i].licenseplate
										+ "<em>"
										+ list[i].owner + "</em><span>";
								var rigisterdate = list[i].rigisterdate;
								var date = new Date();
								var curDate = date.getFullYear() + "-"
										+ (date.getMonth() + 1) + "-"
										+ date.getDate();
								var years = DateDiff(curDate, rigisterdate);
								content += list[i].vehicle + "/"
										+ list[i].cartype + "/" + years + "年之内";
								content += "</span></p><p class='pay-no'>￥"
										+ list[i].payment_amount
										+ "</p></li><li><p class='text m-left'>预约时间：";
								content += list[i].appointmenttime + "</p>";
								if (list[i].orderstatus == 2) {
									content += "</li></ul>";
								} else {
									content += "<a onclick='pay(this)' uuid='"
											+ list[i].uuid + "' station_id='"
											+ list[i].station_id
											+ "'>立即支付</a></li></ul>";
								}
							}
							$("#order_content").append(content);
							$(".moreState").css({
								"margin-right" : "30px",
								"text-shadow" : "1px 1px 1px orange",
								"color" : "green",
								"cursor" : "pointer"
							});
							$(".moreState")
									.click(
											function() {
												window.location.href = window.location.protocol
														+ "//"
														+ window.location.host
														+ "/shjw/wx/order_state_detail.html?orderno="
														+ $(this).attr("orderno")
														+ "&state="
														+ $(this).attr("orderstatus");
											})
						} else {
							window.location.href = window.location.protocol
									+ "//" + window.location.host
									+ "/shjw/wx/car_infro_in.html";
						}
					} else {
						alert("获取失败，请刷新");
					}
				}
			});
}

function detail(t) {
	window.location.href = window.location.protocol + "//"
			+ window.location.host + "/shjw/wx/order_detail.html?orderno="
			+ $(t).attr("orderno") + "&station_id=" + $(t).attr("station_id");
}
function pay(t) {
	window.location.href = window.location.protocol + "//"
			+ window.location.host + "/shjw/wx/pay.html?uuid="
			+ $(t).attr("uuid") + "&jid=" + $(t).attr("station_id");
}