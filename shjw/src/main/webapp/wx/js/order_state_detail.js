/*
 * create xj 2017-12-21
 */
var xj_clxz, xj_clzl;
var xj_isInsure = 0;
function order_state_detail_init() {
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
	var state = GetQueryString("state");
	if (orderno != null && orderno != "" && orderno != "undefined"
			&& state != null && state != "" && state != "undefined") {
		$
				.ajax({
					type : "POST",
					url : "/shjw/open/findOrderSateByOrderNo.shtml",
					data : {
						"orderno" : orderno,
						"openId" : $("#openId").val(),
						"state" : state
					},
					async : false,
					cache : false,
					dataType : "json",
					success : function(result) {
						if (result.result == 1) {
							xj_clxz = result.order.vehicle;
							xj_clzl = result.order.cartype;
							xj_isInsure = result.order.injury_accident_last_year;
							var flag = isMJ(result.order.rigisterdate);
							var isSH = false;
							var carNo = result.order.licenseplate;
							if (carNo.indexOf("沪") == 0) {
								isSH = true;
							} else {
								isSH = false;
							}
							var content = "";
							var list = result.list;
							var xj_flag = false;
							var statu_time;
							if (!flag) {
								var array = new Array("未支付", "已支付", "违章处理中",
										"资料收集", "缴费确认", "年检办理中（约3-5个工作日）", "年检完成");
								for (var i = 0; i < array.length; i++) {
									if (state == 2) {
										if (i == 0) {

										} else if (i == 1) {
											content += "<div class='div_1'><div class='state_yuan_xj state_img'><img src='img/yuan_1.png' /></div>"
													+ "<div class='state_title'>"
													+ array[i]
													+ "</div></div>"
													+ "<div class='div_2'><div class='state_yuan_xj state_img'><img src='img/sx_1.png' /></div><div class='state_title'>"
													+ result.order.time_end
													+ "</div></div>";
										} else if (i == 2) {
											xj_flag = false;
											for (var k = 0; k < list.length; k++) {
												if (list[k].status_2 == 3) {
													xj_flag = true;
													statu_time = list[k].update_time;
												}
											}
											if (xj_flag) {
												content += "<div class='div_1'><div class='state_yuan_xj state_img'><img src='img/yuan_1.png' /></div>"
														+ "<div class='state_title'>"
														+ array[i]
														+ "</div></div>"
														+ "<div class='div_2'><div class='state_yuan_xj state_img'><img src='img/sx_1.png' /></div><div class='state_title'>"
														+ statu_time
														+ "</div></div>";
											} else {
												content += "<div class='div_1'><div class='state_yuan_xj state_img'><img src='img/yuan_2.png' /></div>"
														+ "<div class='state_title'>"
														+ array[i]
														+ "</div></div>"
														+ "<div class='div_2'><div class='state_yuan_xj state_img'><img src='img/sx_1.png' /></div><div class='state_title'>"
														+ "</div></div>";
											}
										} else if (i == 3) {
											xj_flag = false;
											for (var k = 0; k < list.length; k++) {
												if (list[k].status_2 == 4) {
													xj_flag = true;
													statu_time = list[k].update_time;
												}
											}
											if (xj_flag) {
												content += "<div class='div_1'><div class='state_yuan_xj state_img'><img src='img/yuan_1.png' /></div>"
														+ "<div class='state_title'>"
														+ array[i]
														+ "</div></div>"
														+ "<div class='div_2'><div class='state_yuan_xj state_img'><img src='img/sx_1.png' /></div><div class='state_title'>"
														+ statu_time
														+ "</div></div>";
											} else {
												content += "<div class='div_1'><div class='state_yuan_xj state_img'><img src='img/yuan_2.png' /></div>"
														+ "<div class='state_title'>"
														+ array[i]
														+ "</div></div>"
														+ "<div class='div_2'><div class='state_yuan_xj state_img'><img src='img/sx_1.png' /></div><div class='state_title'>"
														+ "</div></div>";
											}
										} else if (i == 4) {
											xj_flag = false;
											for (var k = 0; k < list.length; k++) {
												if (list[k].status_2 == 7) {
													xj_flag = true;
													statu_time = list[k].update_time;
												}
											}
											if (xj_flag) {
												content += "<div class='div_1'><div class='state_yuan_xj state_img'><img src='img/yuan_1.png' /></div>"
														+ "<div class='state_title'>"
														+ array[i]
														+ "</div></div>"
														+ "<div class='div_2'><div class='state_yuan_xj state_img'><img src='img/sx_1.png' /></div><div class='state_title'>"
														+ statu_time
														+ "</div></div>";
											} else {
												content += "<div class='div_1'><div class='state_yuan_xj state_img'><img src='img/yuan_2.png' /></div>"
														+ "<div class='state_title'>"
														+ array[i]
														+ "</div></div>"
														+ "<div class='div_2'><div class='state_yuan_xj state_img'><img src='img/sx_1.png' /></div><div class='state_title'>"
														+ "</div></div>";
											}
										} else if (i == 5) {
											xj_flag = false;
											for (var k = 0; k < list.length; k++) {
												if (list[k].status_2 == 8
														|| list[k].status_2 == 9) {
													xj_flag = true;
													statu_time = list[k].update_time;
												}
											}
											if (xj_flag) {
												content += "<div class='div_1'><div class='state_yuan_xj state_img'><img src='img/yuan_1.png' /></div>"
														+ "<div class='state_title'>"
														+ array[i]
														+ "</div></div>"
														+ "<div class='div_2'><div class='state_yuan_xj state_img'><img src='img/sx_1.png' /></div><div class='state_title'>"
														+ statu_time
														+ "</div></div>";
											} else {
												content += "<div class='div_1'><div class='state_yuan_xj state_img'><img src='img/yuan_2.png' /></div>"
														+ "<div class='state_title'>"
														+ array[i]
														+ "</div></div>"
														+ "<div class='div_2'><div class='state_yuan_xj state_img'><img src='img/sx_1.png' /></div><div class='state_title'>"
														+ "</div></div>";
											}
										} else if (i == 6) {
											xj_flag = false;
											for (var k = 0; k < list.length; k++) {
												if (list[k].status_2 == 10) {
													xj_flag = true;
													statu_time = list[k].update_time;
												}
											}
											if (xj_flag) {
												content += "<div class='div_1'><div class='state_yuan_xj state_img'><img src='img/yuan_1.png' /></div>"
														+ "<div class='state_title'>"
														+ array[i]
														+ "&nbsp&nbsp&nbsp"
														+ statu_time
														+ "</div></div>";
											} else {
												content += "<div class='div_1'><div class='state_yuan_xj state_img'><img src='img/yuan_2.png' /></div>"
														+ "<div class='state_title'>"
														+ array[i]
														+ "</div></div>";
											}
										}
									} else {
										if (i == 0) {
											content += "<div class='div_1'><div class='state_yuan_xj state_img'><img src='img/yuan_1.png' /></div>"
													+ "<div class='state_title'>"
													+ array[i]
													+ "</div></div>"
													+ "<div class='div_2'><div class='state_yuan_xj state_img'><img src='img/sx_1.png' /></div><div class='state_title'>"
													+ result.order.update_time
													+ "</div></div>";
										} else if (i == 6) {
											content += "<div class='div_1'><div class='state_yuan_xj state_img'><img src='img/yuan_2.png' /></div>"
													+ "<div class='state_title'>"
													+ array[i] + "</div></div>";
										} else {
											content += "<div class='div_1'><div class='state_yuan_xj state_img'><img src='img/yuan_2.png' /></div>"
													+ "<div class='state_title'>"
													+ array[i]
													+ "</div></div>"
													+ "<div class='div_2'><div class='state_yuan_xj state_img'><img src='img/sx_1.png' /></div><div class='state_title'>"
													+ "</div></div>";
										}
									}
								}
							} else {
								if (isSH) {
									var array = new Array("未支付", "已支付",
											"违章处理中", "资料收集", "缴费确认", "预约年检",
											"年检完成");
									for (var i = 0; i < array.length; i++) {
										if (state == 2) {
											if (i == 0) {

											} else if (i == 1) {
												content += "<div class='div_1'><div class='state_yuan_xj state_img'><img src='img/yuan_1.png' /></div>"
														+ "<div class='state_title'>"
														+ array[i]
														+ "</div></div>"
														+ "<div class='div_2'><div class='state_yuan_xj state_img'><img src='img/sx_1.png' /></div><div class='state_title'>"
														+ result.order.time_end
														+ "</div></div>";
											} else if (i == 2) {
												xj_flag = false;
												for (var k = 0; k < list.length; k++) {
													if (list[k].status_2 == 3) {
														xj_flag = true;
														statu_time = list[k].update_time;
													}
												}
												if (xj_flag) {
													content += "<div class='div_1'><div class='state_yuan_xj state_img'><img src='img/yuan_1.png' /></div>"
															+ "<div class='state_title'>"
															+ array[i]
															+ "</div></div>"
															+ "<div class='div_2'><div class='state_yuan_xj state_img'><img src='img/sx_1.png' /></div><div class='state_title'>"
															+ statu_time
															+ "</div></div>";
												} else {
													content += "<div class='div_1'><div class='state_yuan_xj state_img'><img src='img/yuan_2.png' /></div>"
															+ "<div class='state_title'>"
															+ array[i]
															+ "</div></div>"
															+ "<div class='div_2'><div class='state_yuan_xj state_img'><img src='img/sx_1.png' /></div><div class='state_title'>"
															+ "</div></div>";
												}
											} else if (i == 3) {
												xj_flag = false;
												for (var k = 0; k < list.length; k++) {
													if (list[k].status_2 == 4) {
														xj_flag = true;
														statu_time = list[k].update_time;
													}
												}
												if (xj_flag) {
													content += "<div class='div_1'><div class='state_yuan_xj state_img'><img src='img/yuan_1.png' /></div>"
															+ "<div class='state_title'>"
															+ array[i]
															+ "</div></div>"
															+ "<div class='div_2'><div class='state_yuan_xj state_img'><img src='img/sx_1.png' /></div><div class='state_title'>"
															+ statu_time
															+ "</div></div>";
												} else {
													content += "<div class='div_1'><div class='state_yuan_xj state_img'><img src='img/yuan_2.png' /></div>"
															+ "<div class='state_title'>"
															+ array[i]
															+ "</div></div>"
															+ "<div class='div_2'><div class='state_yuan_xj state_img'><img src='img/sx_1.png' /></div><div class='state_title'>"
															+ "</div></div>";
												}
											} else if (i == 4) {
												xj_flag = false;
												for (var k = 0; k < list.length; k++) {
													if (list[k].status_2 == 7) {
														xj_flag = true;
														statu_time = list[k].update_time;
													}
												}
												if (xj_flag) {
													content += "<div class='div_1'><div class='state_yuan_xj state_img'><img src='img/yuan_1.png' /></div>"
															+ "<div class='state_title'>"
															+ array[i]
															+ "</div></div>"
															+ "<div class='div_2'><div class='state_yuan_xj state_img'><img src='img/sx_1.png' /></div><div class='state_title'>"
															+ statu_time
															+ "</div></div>";
												} else {
													content += "<div class='div_1'><div class='state_yuan_xj state_img'><img src='img/yuan_2.png' /></div>"
															+ "<div class='state_title'>"
															+ array[i]
															+ "</div></div>"
															+ "<div class='div_2'><div class='state_yuan_xj state_img'><img src='img/sx_1.png' /></div><div class='state_title'>"
															+ "</div></div>";
												}
											} else if (i == 5) {
												xj_flag = false;
												for (var k = 0; k < list.length; k++) {
													if (list[k].status_2 == 8) {
														xj_flag = true;
														statu_time = list[k].update_time;
													}
												}
												if (xj_flag) {
													content += "<div class='div_1'><div class='state_yuan_xj state_img'><img src='img/yuan_1.png' /></div>"
															+ "<div class='state_title'>"
															+ array[i]
															+ "</div></div>"
															+ "<div class='div_2'><div class='state_yuan_xj state_img'><img src='img/sx_1.png' /></div><div class='state_title'>"
															+ statu_time
															+ "</div></div>";
												} else {
													content += "<div class='div_1'><div class='state_yuan_xj state_img'><img src='img/yuan_2.png' /></div>"
															+ "<div class='state_title'>"
															+ array[i]
															+ "</div></div>"
															+ "<div class='div_2'><div class='state_yuan_xj state_img'><img src='img/sx_1.png' /></div><div class='state_title'>"
															+ "</div></div>";
												}
											} else if (i == 6) {
												xj_flag = false;
												for (var k = 0; k < list.length; k++) {
													if (list[k].status_2 == 10) {
														xj_flag = true;
														statu_time = list[k].update_time;
													}
												}
												if (xj_flag) {
													content += "<div class='div_1'><div class='state_yuan_xj state_img'><img src='img/yuan_1.png' /></div>"
															+ "<div class='state_title'>"
															+ array[i]
															+ "&nbsp&nbsp&nbsp"
															+ statu_time
															+ "</div></div>";
												} else {
													content += "<div class='div_1'><div class='state_yuan_xj state_img'><img src='img/yuan_2.png' /></div>"
															+ "<div class='state_title'>"
															+ array[i]
															+ "</div></div>";
												}
											}
										} else {
											if (i == 0) {
												content += "<div class='div_1'><div class='state_yuan_xj state_img'><img src='img/yuan_1.png' /></div>"
														+ "<div class='state_title'>"
														+ array[i]
														+ "</div></div>"
														+ "<div class='div_2'><div class='state_yuan_xj state_img'><img src='img/sx_1.png' /></div><div class='state_title'>"
														+ result.order.update_time
														+ "</div></div>";
											} else if (i == 6) {
												content += "<div class='div_1'><div class='state_yuan_xj state_img'><img src='img/yuan_2.png' /></div>"
														+ "<div class='state_title'>"
														+ array[i]
														+ "</div></div>";
											} else {
												content += "<div class='div_1'><div class='state_yuan_xj state_img'><img src='img/yuan_2.png' /></div>"
														+ "<div class='state_title'>"
														+ array[i]
														+ "</div></div>"
														+ "<div class='div_2'><div class='state_yuan_xj state_img'><img src='img/sx_1.png' /></div><div class='state_title'>"
														+ "</div></div>";
											}
										}
									}
								} else {
									var array = new Array("未支付", "已支付",
											"违章处理中", "资料收集", "委托办理中", "委托已办好",
											"缴费确认", "预约年检", "年检完成");
									for (var i = 0; i < array.length; i++) {
										if (state == 2) {
											if (i == 0) {

											} else if (i == 1) {
												content += "<div class='div_1'><div class='state_yuan_xj state_img'><img src='img/yuan_1.png' /></div>"
														+ "<div class='state_title'>"
														+ array[i]
														+ "</div></div>"
														+ "<div class='div_2'><div class='state_yuan_xj state_img'><img src='img/sx_1.png' /></div><div class='state_title'>"
														+ result.order.time_end
														+ "</div></div>";
											} else if (i == 2) {
												xj_flag = false;
												for (var k = 0; k < list.length; k++) {
													if (list[k].status_2 == 3) {
														xj_flag = true;
														statu_time = list[k].update_time;
													}
												}
												if (xj_flag) {
													content += "<div class='div_1'><div class='state_yuan_xj state_img'><img src='img/yuan_1.png' /></div>"
															+ "<div class='state_title'>"
															+ array[i]
															+ "</div></div>"
															+ "<div class='div_2'><div class='state_yuan_xj state_img'><img src='img/sx_1.png' /></div><div class='state_title'>"
															+ statu_time
															+ "</div></div>";
												} else {
													content += "<div class='div_1'><div class='state_yuan_xj state_img'><img src='img/yuan_2.png' /></div>"
															+ "<div class='state_title'>"
															+ array[i]
															+ "</div></div>"
															+ "<div class='div_2'><div class='state_yuan_xj state_img'><img src='img/sx_1.png' /></div><div class='state_title'>"
															+ "</div></div>";
												}
											} else if (i == 3) {
												xj_flag = false;
												for (var k = 0; k < list.length; k++) {
													if (list[k].status_2 == 4) {
														xj_flag = true;
														statu_time = list[k].update_time;
													}
												}
												if (xj_flag) {
													content += "<div class='div_1'><div class='state_yuan_xj state_img'><img src='img/yuan_1.png' /></div>"
															+ "<div class='state_title'>"
															+ array[i]
															+ "</div></div>"
															+ "<div class='div_2'><div class='state_yuan_xj state_img'><img src='img/sx_1.png' /></div><div class='state_title'>"
															+ statu_time
															+ "</div></div>";
												} else {
													content += "<div class='div_1'><div class='state_yuan_xj state_img'><img src='img/yuan_2.png' /></div>"
															+ "<div class='state_title'>"
															+ array[i]
															+ "</div></div>"
															+ "<div class='div_2'><div class='state_yuan_xj state_img'><img src='img/sx_1.png' /></div><div class='state_title'>"
															+ "</div></div>";
												}
											} else if (i == 4) {
												xj_flag = false;
												for (var k = 0; k < list.length; k++) {
													if (list[k].status_2 == 5) {
														xj_flag = true;
														statu_time = list[k].update_time;
													}
												}
												if (xj_flag) {
													content += "<div class='div_1'><div class='state_yuan_xj state_img'><img src='img/yuan_1.png' /></div>"
															+ "<div class='state_title'>"
															+ array[i]
															+ "</div></div>"
															+ "<div class='div_2'><div class='state_yuan_xj state_img'><img src='img/sx_1.png' /></div><div class='state_title'>"
															+ statu_time
															+ "</div></div>";
												} else {
													content += "<div class='div_1'><div class='state_yuan_xj state_img'><img src='img/yuan_2.png' /></div>"
															+ "<div class='state_title'>"
															+ array[i]
															+ "</div></div>"
															+ "<div class='div_2'><div class='state_yuan_xj state_img'><img src='img/sx_1.png' /></div><div class='state_title'>"
															+ "</div></div>";
												}
											} else if (i == 5) {
												xj_flag = false;
												for (var k = 0; k < list.length; k++) {
													if (list[k].status_2 == 7) {
														xj_flag = true;
														statu_time = list[k].update_time;
													}
												}
												if (xj_flag) {
													content += "<div class='div_1'><div class='state_yuan_xj state_img'><img src='img/yuan_1.png' /></div>"
															+ "<div class='state_title'>"
															+ array[i]
															+ "</div></div>"
															+ "<div class='div_2'><div class='state_yuan_xj state_img'><img src='img/sx_1.png' /></div><div class='state_title'>"
															+ statu_time
															+ "</div></div>";
												} else {
													content += "<div class='div_1'><div class='state_yuan_xj state_img'><img src='img/yuan_2.png' /></div>"
															+ "<div class='state_title'>"
															+ array[i]
															+ "</div></div>"
															+ "<div class='div_2'><div class='state_yuan_xj state_img'><img src='img/sx_1.png' /></div><div class='state_title'>"
															+ "</div></div>";
												}
											} else if (i == 6) {
												xj_flag = false;
												for (var k = 0; k < list.length; k++) {
													if (list[k].status_2 == 7) {
														xj_flag = true;
														statu_time = list[k].update_time;
													}
												}
												if (xj_flag) {
													content += "<div class='div_1'><div class='state_yuan_xj state_img'><img src='img/yuan_1.png' /></div>"
															+ "<div class='state_title'>"
															+ array[i]
															+ "</div></div>"
															+ "<div class='div_2'><div class='state_yuan_xj state_img'><img src='img/sx_1.png' /></div><div class='state_title'>"
															+ statu_time
															+ "</div></div>";
												} else {
													content += "<div class='div_1'><div class='state_yuan_xj state_img'><img src='img/yuan_2.png' /></div>"
															+ "<div class='state_title'>"
															+ array[i]
															+ "</div></div>"
															+ "<div class='div_2'><div class='state_yuan_xj state_img'><img src='img/sx_1.png' /></div><div class='state_title'>"
															+ "</div></div>";
												}
											} else if (i == 7) {
												xj_flag = false;
												for (var k = 0; k < list.length; k++) {
													if (list[k].status_2 == 8) {
														xj_flag = true;
														statu_time = list[k].update_time;
													}
												}
												if (xj_flag) {
													content += "<div class='div_1'><div class='state_yuan_xj state_img'><img src='img/yuan_1.png' /></div>"
															+ "<div class='state_title'>"
															+ array[i]
															+ "</div></div>"
															+ "<div class='div_2'><div class='state_yuan_xj state_img'><img src='img/sx_1.png' /></div><div class='state_title'>"
															+ statu_time
															+ "</div></div>";
												} else {
													content += "<div class='div_1'><div class='state_yuan_xj state_img'><img src='img/yuan_2.png' /></div>"
															+ "<div class='state_title'>"
															+ array[i]
															+ "</div></div>"
															+ "<div class='div_2'><div class='state_yuan_xj state_img'><img src='img/sx_1.png' /></div><div class='state_title'>"
															+ "</div></div>";
												}
											} else if (i == 8) {
												xj_flag = false;
												for (var k = 0; k < list.length; k++) {
													if (list[k].status_2 == 10) {
														xj_flag = true;
														statu_time = list[k].update_time;
													}
												}
												if (xj_flag) {
													content += "<div class='div_1'><div class='state_yuan_xj state_img'><img src='img/yuan_1.png' /></div>"
															+ "<div class='state_title'>"
															+ array[i]
															+ "&nbsp&nbsp&nbsp"
															+ statu_time
															+ "</div></div>";
												} else {
													content += "<div class='div_1'><div class='state_yuan_xj state_img'><img src='img/yuan_2.png' /></div>"
															+ "<div class='state_title'>"
															+ array[i]
															+ "</div></div>";
												}
											}
										} else {
											if (i == 0) {
												content += "<div class='div_1'><div class='state_yuan_xj state_img'><img src='img/yuan_1.png' /></div>"
														+ "<div class='state_title'>"
														+ array[i]
														+ "</div></div>"
														+ "<div class='div_2'><div class='state_yuan_xj state_img'><img src='img/sx_1.png' /></div><div class='state_title'>"
														+ result.order.update_time
														+ "</div></div>";
											} else if (i == 8) {
												content += "<div class='div_1'><div class='state_yuan_xj state_img'><img src='img/yuan_2.png' /></div>"
														+ "<div class='state_title'>"
														+ array[i]
														+ "</div></div>";
											} else {
												content += "<div class='div_1'><div class='state_yuan_xj state_img'><img src='img/yuan_2.png' /></div>"
														+ "<div class='state_title'>"
														+ array[i]
														+ "</div></div>"
														+ "<div class='div_2'><div class='state_yuan_xj state_img'><img src='img/sx_1.png' /></div><div class='state_title'>"
														+ "</div></div>";
											}
										}
									}
								}
							}
							$("#state_section").append(content);
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
function changeTime(str){
	if(str.indexOf("-")>=0){
		return str;
	}
	return str.substring(0,4)+"-"+str.substring(4,6)+"-"+str.substring(6,8)+" "+str.substring(8,10)+":"+str.substring(10,12)+":"+str.substring(12,14);
}