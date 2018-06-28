var list;
function chooze_jcz_init() {
	CompareVersion();
	var wxopenid = getcookie("wxopenid");
	var access_code = GetQueryString("code");
	var uuid=GetQueryString("uuid");
	if(uuid==null || uuid==""||uuid=="undefined"){
		alert("请求参数错误");
		return false;
	}
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
	$
			.ajax({
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
								jcz_content += "<li onclick='pay(this)' jczid="
										+ list[i].stationid
										+ "><img src='img/ico_car_j.png' /><p class='text m-left'>"
										+ list[i].station_no
										+ "<span>"
										+ list[i].station_address
										+ "</span></p> <i class='ico ico-more'></i></li>";
							} else if (list[i].station_area == list[0].station_area) {
								jcz_content += "<li onclick='pay(this)' jczid="
										+ list[i].stationid
										+ "><img src='img/ico_car_j.png' /><p class='text m-left'>"
										+ list[i].station_no
										+ "<span>"
										+ list[i].station_address
										+ "</span></p> <i class='ico ico-more'></i></li>";
							}
						}
						$("#area").append(content);
						$("#jcz_content").append(jcz_content);
						$("#area").attr("onchange", "area_change()");
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
			jcz_content += "<li onclick='pay(this)' jczid=" + list[i].stationid
					+ "><img src='img/ico_car_j.png' /><p class='text m-left'>"
					+ list[i].station_no + "<span>" + list[i].station_address
					+ "</span></p> <i class='ico ico-more'></i></li>";
		}
	}
	$("#jcz_content").append(jcz_content);
}
function pay(t) {
	window.location.href = window.location.protocol + "//"
			+ window.location.host + "/shjw/wx/pay.html?uuid="
			+ GetQueryString("uuid") + "&jid=" + $(t).attr("jczid");
}