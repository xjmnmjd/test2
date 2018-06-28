var list;
function jcz_detail_init() {
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
	FastClick.attach(document.body);
	$.ajax({
		type : "POST",
		url : "/shjw/open/getAllStation.shtml",
		async : false,
		cache : false,
		dataType : "json",
		success : function(data) {
			if (data.result == 1) {
				list = data.stationList;
				var area = new Array();
				var flag = false;
				var content = "", jcz_content = "";
				$("#area").html("");
				$("#jcz_content").html("");
				for (var i = 0; i < list.length; i++) {
					flag = false;
					for (var j = 0; j < area.length; j++) {
						if (list[i].station_area == area[j]) {
							flag = true;
							break;
						}
					}
					if (!flag) {
						area.push(list[i].station_area);
						content += "<option>" + list[i].station_area
								+ "</option>";
					}
					if (i == 0) {
						jcz_content += "<option jczid=" + list[i].stationid
								+ ">" + list[i].station_no + "</option>";
					} else if (list[i].station_area == list[0].station_area) {
						jcz_content += "<option jczid=" + list[i].stationid
								+ ">" + list[i].station_no + "</option>";
					}
				}
				$("#area").append(content);
				$("#jcz_content").append(jcz_content);
				$("#area").attr("onchange", "area_change()");
				jcz_init();
			}
		}
	});
}
function area_change() {
	var area = $("#area").val();
	var jcz_content = "";
	$("#jcz_content").html("");
	for (var i = 0; i < list.length; i++) {
		if (list[i].station_area == area) {
			jcz_content += "<option jczid=" + list[i].stationid + ">"
					+ list[i].station_no + "</option>";
		}
	}
	$("#jcz_content").append(jcz_content);
	jcz_init();
}
function jcz_init() {
	var jczid = $("#jcz_content :selected").attr("jczid");
	for (var k = 0; k < list.length; k++) {
		if (list[k].stationid == jczid) {
			$("#jcz_name").text(list[k].station_no);
			$("#jcz_address").text(list[k].station_address);
			$("#jcz_phone").text("电话：" + list[k].station_phone);
		}
	}

	$
			.ajax({
				type : "GET",
				url : "/shjw/open/getStationFeeById.shtml",
				async : false,
				cache : false,
				data : {
					stationId : jczid
				},
				dataType : "json",
				success : function(data) {
					if (data.result == 1) {
						$("#hb_content")
								.html(
										"<tr><th scope='col'>环保收费性质</th><th scope='col'>1-6年</th><th scope='col'>7-10年</th><th scope='col'>11-13年</th><th scope='col'>13年以上</th></tr>");
						for (var i = 0; i < data.station_hb_list.length; i++) {
							$("#hb_content").append(
									"<tr><td>"
											+ data.station_hb_list[i].hb_name
											+ "</td><td>"
											+ data.station_hb_list[i].hb_time_a
											+ "</td><td>"
											+ data.station_hb_list[i].hb_time_b
											+ "</td><td>"
											+ data.station_hb_list[i].hb_time_c
											+ "</td><td>"
											+ data.station_hb_list[i].hb_time_d
											+ "</td></tr>");
						}
						$("#nj_content")
								.html(
										"<tr><th scope='col'>车型</th><th scope='col'>年检费</th><th scope='col'>尾气费</th></tr>");
						for (var i = 0; i < data.station_nj_list.length; i++) {
							$("#nj_content").append(
									"<tr><td>"
											+ data.station_nj_list[i].nj_name
											+ "</td><td>"
											+ data.station_nj_list[i].nj_fee_a
											+ "</td><td>"
											+ data.station_nj_list[i].nj_fee_b
											+ "</td></tr>");
						}
					}
				}
			});
}
