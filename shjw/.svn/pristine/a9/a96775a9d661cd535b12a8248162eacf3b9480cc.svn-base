/*
 * create xj 2017-8-15
 */
var list;
function car_manger_init() {
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
						getOrder();
					} else {
						location.href = fromurl;
					}
				}
			});
		}
	} else {
		$("#openId").val(wxopenid);
		getOrder();
	}
}
function getOrder() {
	$
			.ajax({
				type : "post",
				url : "/shjw/open/findOrderByOpenId.shtml",
				async : false,
				cache : false,
				data : {
					openid : $("#openId").val(),
					status : 0
				},
				dataType : "json",
				success : function(result) {
					if (result != null && result.result == 1) {
						if (result.list != null && result.list.length > 0) {
							list = result.list;
							$("#carContent").html("");
							var content = "";
							for (var i = 0; i < list.length; i++) {
								content += "<li><a onclick='detail(this)' uuid='"
										+ list[i].uuid
										+ "'><img src='img/ico_car.png' /><p>"
										+ list[i].licenseplate
										+ "<span>"
										+ list[i].owner
										+ "</span></p></a> <i class='ico ico-delete' uuid='"
										+ list[i].uuid + "'></i></li>";
							}
							$("#carContent").append(content);
						} else {
							window.location.href = window.location.protocol
									+ "//" + window.location.host
									+ "/shjw/wx/car_infro_in.html"
						}
					} else {
						alert("获取失败，请刷新");
					}
				}
			});
	$(".ico-delete")
			.click(
					function() {
						var uuid = $(this).attr("uuid");
						if (uuid != null && uuid != "" && uuid != "undefined") {
							$
									.ajax({
										type : "GET",
										url : "/shjw/open/delOrderByOpenId.shtml",
										async : false,
										cache : false,
										data : {
											uuid : uuid
										},
										dataType : "json",
										success : function(result) {
											if (result != null
													&& result.result == 1) {
												$("#carContent").html("");
												var content = "";
												alert("删除成功");
												for (var i = 0; i < list.length; i++) {
													if (list[i].uuid == uuid) {
														list.splice(i, 1);
														i--;
													} else {
														content += "<li><a onclick='detail(this)' uuid='"
																+ list[i].uuid
																+ "'><img src='img/ico_car.png' /><p>"
																+ list[i].licenseplate
																+ "<span>"
																+ list[i].owner
																+ "</span></p></a> <i class='ico ico-delete' uuid='"
																+ list[i].uuid
																+ "'></i></li>";
													}
												}
												$("#carContent")
														.append(content);
											} else {
												alert("删除失败");
											}
										}
									});
						} else {
							alert("删除失败");
						}
					})
}
function detail(t) {
	window.location.href = window.location.protocol + "//"
			+ window.location.host + "/shjw/wx/car_infro_in.html?uuid="
			+ $(t).attr("uuid");
}
function addCar() {
	window.location.href = window.location.protocol + "//"
			+ window.location.host + "/shjw/wx/car_infro_in.html";
}