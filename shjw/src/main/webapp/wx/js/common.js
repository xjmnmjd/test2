!function() {
	function a() {
		if (document.documentElement.clientWidth < 600) {
			document.documentElement.style.fontSize = document.documentElement.clientWidth
					/ 32 + "px";
		} else {
			document.documentElement.style.fontSize = "16.875px";
		}
	}
	var b = null;
	window.addEventListener("resize", function() {
		clearTimeout(b), b = setTimeout(a, 300);
	}, !1), a();
}(window);
function GetQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	var r = window.location.search.substr(1).match(reg);
	if (r != null) {
		return unescape(r[2]);
	}
	return null;
}
function addcookie(name, value, expireHours) {
	var cookieString = name + "=" + escape(value) + "; path=/";
	if (expireHours > 0) {
		var date = new Date();
		date.setTime(date.getTime + expireHours * 3600 * 1000);
		cookieString = cookieString + "; expire=" + date.toGMTString();
	}
	document.cookie = cookieString;
}
function getcookie(name) {
	var strcookie = document.cookie;
	var arrcookie = strcookie.split("; ");
	for (var i = 0; i < arrcookie.length; i++) {
		var arr = arrcookie[i].split("=");
		if (arr[0] == name) {
			return decodeURIComponent(arr[1]);
		}
	}
	return "";
}
function delCookie(name) {
	var exp = new Date();
	exp.setTime(exp.getTime() - 1);
	var cval = getcookie(name);
	if (cval != null) {
		document.cookie = name + "=" + cval + "; path=/;expires="
				+ exp.toGMTString();
	}
}
function DateDiff(sDate1, sDate2) {
	var date1 = sDate1.split("-");
	var date2 = sDate2.split("-");
	var m = 0;
	if (parseInt(date1) - parseInt(date2) < 0) {
		return "";
	}
	m = parseInt(date1[0]) - parseInt(date2[0]) + 1;
	if (parseInt(date1[1]) > parseInt(date2[1])) {
		return m + 1;
	} else {
		return m;
	}
}
/*
 * 验证手机号
 */
function checkSubmitMobil(phone_no) {
	if (phone_no == "") {
		alert("\u624b\u673a\u53f7\u7801\u4e0d\u80fd\u4e3a\u7a7a\uff01");
		return false;
	}
	if (!phone_no.match(/^1[0-9]{10}$/)) {
		alert("\u624b\u673a\u53f7\u7801\u683c\u5f0f\u4e0d\u6b63\u786e\uff01");
		return false;
	}
	return true;
}
/*
 * 添加留言信息
 */
function addInfor() {
	var contact_name = $("#contact_name").val();
	var contact_phone = $("#contact_phone").val();
	var contact_infor = $("#contact_infor").val();
	if (!isOk(contact_name)) {
		alert("请填写您的姓名，方便我们联系你");
		return false;
	}
	if (!checkSubmitMobil(contact_phone)) {
		return false;
	}
	if (!isOk(contact_infor)) {
		alert("写点儿吧");
		return false;
	}
	var msg = {
		"contact_name" : contact_name,
		"contact_phone" : contact_phone,
		"contact_infor" : contact_infor,
		"openId" : $("#openId").val()
	};
	$.ajax({
		type : "POST",
		url : "/shjw/open/saveContactInfor.shtml",
		data : msg,
		async : false,
		cache : false,
		dataType : "json",
		success : function(data) {
			if (data.result == 1) {
				alert("提交成功，我们会尽快处理，谢谢您的支持！");
				$("#infor_div").css("display", "none");
				$("#infor_mask").css("display", "none");
				document.body.style.overflow = "visible";
				$("#contact_name").val("");
				$("#contact_phone").val("");
				$("#contact_infor").val("");
			} else {
				alert(data.msg);
				return;
			}
		}
	});
}
/*
 * 在线留言点击事件
 */
function zxly() {
	$("#head_tel_div").css("display", "none");
	$("#infor_div").css("display", "block");
	$("#infor_mask").css("display", "block");
	document.body.style.overflow = "hidden";
	$("#infor_div").css("top",
			(document.body.clientHeight - $("#infor_div").height()) / 2);
}
/*
 * 验证是否为空
 */
function isOk(val) {
	if (val != null && val != undefined && val != "" && val != "null"
			&& val != "请选择") {
		return true;
	}
	return false;
}
// 版本号进行控制
function getVersion() {
	return "1.1";
}
// 获取appid
function getAppId() {
	return "wx4d0ece8ecad51949";
}
// 版本比较
function CompareVersion() {
	var xj_version = getcookie("shjw_version");
	if (xj_version == "" || xj_version != getVersion()) {
		delCookie("wxopenid");
		addcookie("shjw_version", getVersion(), 60000);
	}
	var shjw_order_source = GetQueryString("shjw_order_source");
	if (shjw_order_source != null && shjw_order_source != "NULL"
			&& shjw_order_source != "") {
		addcookie("shjw_order_source", shjw_order_source, 60000);
	}
	addcookie("wxopenid", "oVl0f0hLd9lgcQfJdvaYU1c5LQ4M", 60000);
}
