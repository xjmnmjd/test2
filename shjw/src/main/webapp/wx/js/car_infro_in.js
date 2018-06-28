/*
 * create xj 2017-8-4
 */

function car_info_in_init() {
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
	var link = location.href;
	$.ajax({
		type : "get",
		url : "/shjw/open/getSign.shtml",
		async : false,
		cache : false,
		data : {
			link : link
		},
		dataType : "json",
		success : function(result) {
			if (result != null && result.result == 1) {
				wx.config({
					debug : false,
					appId : result.appId,
					timestamp : result.sign.timestamp,
					nonceStr : result.sign.nonceStr,
					signature : result.sign.signature,
					jsApiList : [ "checkJsApi", "onMenuShareTimeline",
							"onMenuShareAppMessage", "onMenuShareQQ",
							"onMenuShareWeibo", "onMenuShareQZone",
							"hideMenuItems", "showMenuItems",
							"hideAllNonBaseMenuItem", "showAllNonBaseMenuItem",
							"translateVoice", "startRecord", "stopRecord",
							"onVoiceRecordEnd", "playVoice", "onVoicePlayEnd",
							"pauseVoice", "stopVoice", "uploadVoice",
							"downloadVoice", "chooseImage", "previewImage",
							"uploadImage", "downloadImage", "getNetworkType",
							"openLocation", "getLocation", "hideOptionMenu",
							"showOptionMenu", "closeWindow", "scanQRCode",
							"chooseWXPay", "openProductSpecificView",
							"addCard", "chooseCard", "openCard" ]
				});
			} else {
				alert("\u9875\u9762\u52a0\u8f7d\u5931\u8d25");
			}
		},
		error : function(data) {
			alert("\u8fde\u63a5\u5931\u8d25\uff01");
		}
	});
}

function init() {
	// fastclick,使移动端点击没有延迟
	FastClick.attach(document.body);
	// upload_ops.init();
	var calendar = new LCalendar();
	var date = new Date();
	calendar.init({
		'trigger' : '#register_date',
		'type' : 'date',
		'minDate' : (date.getFullYear() - 20) + '-' + 1 + '-' + 1,
		'maxDate' : (date.getFullYear()) + '-' + (date.getMonth() + 1) + '-'
				+ (date.getDate() - 1)
	});
	
	calendar = new LCalendar();
	calendar.init({
		'trigger' : '#jq_date',
		'type' : 'date',
		'minDate' : (date.getFullYear()) + '-' + (date.getMonth() + 1) + '-'
				+ date.getDate(),
		'maxDate' : (date.getFullYear() + 3) + '-' + 12 + '-' + 31
	});
	date_month=date.getMonth() + 1;
	date_month = date_month > 9 ? date_month : '0' + date_month;
	date_day=date.getDate() - 1;
	date_day = date_day > 9 ? date_day : '0' + date_day;
	$("#register_date").val((date.getFullYear()) + '-' + date_month + '-'
			+ date_day);
	$("#jq_date").val((date.getFullYear()) + '-' + date_month + '-'
			+ date_day);
	$.ajax({
		type : "POST",
		url : "/shjw/open/getCarTypeNature.shtml",
		async : false,
		cache : false,
		dataType : "json",
		success : function(data) {
			if (data.result == 1) {
				var content = "";
				$("#cllx").html("");
				for (var i = 0; i < data.vehicleTypeList.length; i++) {
					content += "<option>" + data.vehicleTypeList[i].vehicletype
							+ "</option>";
				}
				$("#cllx").append(content);
				content = "";
				$("#clxz").html("");
				for (var i = 0; i < data.vehicleNatureList.length; i++) {
					content += "<option>"
							+ data.vehicleNatureList[i].vehiclenature_name
							+ "</option>";
				}
				$("#clxz").append(content);
			}
		}
	});
	var uuid = GetQueryString("uuid");
	if (uuid != null && uuid != "" && uuid != "undefined") {
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
					$("#car_no").val(data.entity.licenseplate);
					$("#car_owner").val(data.entity.owner);
					$("#clxz").val(data.entity.vehicle);
					$("#cllx").val(data.entity.cartype);
					$("#register_date").val(data.entity.rigisterdate);
					$("#jq_date").val(data.entity.insurancedate);
					$("#card_no").val(data.entity.identificationno);
					var xsz_img = data.entity.carpath;
					if (xsz_img != null && xsz_img != ""
							&& xsz_img != "undefined") {
						$("#xsz_img").attr("src", xsz_img);
						$("#xsz_img").css({
							"text-align" : "center",
							"height" : "7rem",
							"width" : "auto",
							"max-width" : $("#xsz_img_content").width()
						});
						$("#xsz_img").attr("state", 2);
					}
					var sfz_img = data.entity.identificationpath;
					if (sfz_img != null && sfz_img != ""
							&& sfz_img != "undefined") {
						$("#sfz_img").css({
							"text-align" : "center",
							"height" : "7rem",
							"width" : "auto",
							"max-width" : $("#sfz_img_content").width()
						});
						$("#sfz_img").attr("src", sfz_img);
						$("#sfz_img").attr("state", 2);
					}
					$("#state").val(2);
					$("#phone_yzm").css("display", "none");
				} else {
					alert(data.msg);
					history.back(-1);
				}
			}
		});
	}
}
var x = 60;
var upload_ops = {
	init : function() {
		this.eventBind();
	},
	eventBind : function() {
		var that = this;
		$("#upload1").change(function() {
			var reader = new FileReader();
			reader.onload = function(e) {
				that.compress(this.result, 1);
			};
			reader.readAsDataURL(this.files[0]);
		});
		$("#upload2").change(function() {
			var reader = new FileReader();
			reader.onload = function(e) {
				that.compress(this.result, 2);
			};
			reader.readAsDataURL(this.files[0]);
		});
	},
	compress : function(res, reg) {
		var that = this;
		var img = new Image(), maxH = 300;
		img.onload = function() {
			var cvs = document.createElement('canvas'), ctx = cvs
					.getContext('2d');

			if (img.height > maxH) {
				img.width *= maxH / img.height;
				img.height = maxH;
			}
			cvs.width = img.width;
			cvs.height = img.height;
			ctx.clearRect(0, 0, cvs.width, cvs.height);
			ctx.drawImage(img, 0, 0, img.width, img.height);
			var dataUrl = cvs.toDataURL('image/jpeg', 1);
			that.upload(dataUrl, reg);
		};
		img.src = res;
	},
	upload : function(image_data, reg) {
		/* 这里写上次方法,图片流是base64_encode的 */
		var xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4) {
				if ((xhr.status >= 200 && xhr.status < 300)
						|| xhr.status == 304) {
					var data = xhr.responseText;
					data = eval('(' + data + ')');
					if (reg == 1) {
						$("#xsz_img").attr("src", data.path);
						$("#xsz_img").attr("state", 2);
						$("#xsz_img").css({
							"height" : "auto",
							"width" : "auto",
							"max-width" : $("#xsz_img_content").width(),
							"max-height" : $("#xsz_img_content").height(),
							"margin-top" : "0rem",
							"text-align" : "center"
						});
						$("#xsz_title").css("display", "none");
					} else if (reg == 2) {
						$("#sfz_img").attr("src", data.path);
						$("#sfz_img").attr("state", 2);
						$("#sfz_img").css({
							"height" : "auto",
							"width" : "auto",
							"max-width" : $("#sfz_img_content").width(),
							"max-height" : $("#sfz_img_content").height(),
							"margin-top" : "0rem",
							"text-align" : "center"
						});
						$("#sfz_title").css("display", "none");
					}
				} else {
				}
			}
		};
		xhr.open("post", "/shjw/open/upload.shtml", true);
		xhr.setRequestHeader("Content-Type",
				"application/x-www-form-urlencoded ");
		xhr.send("base64=" + encodeURIComponent(image_data));
	}
};

function sendCode() {
	x = 60;
	if (checkSubmitMobil($("#phone_no").val())) {
		setInterval(go, 1000);
		var xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4) {
				if ((xhr.status >= 200 && xhr.status < 300)
						|| xhr.status == 304) {
					alert("验证码已发送，请注意查收！");
				} else {
					var data = xhr.responseText;
					data = eval('(' + data + ')');
					alert(data.msg);
				}
			}
		};
		xhr.open("post", "/shjw/open/sendCode.shtml", true);
		xhr.setRequestHeader("Content-Type",
				"application/x-www-form-urlencoded ");
		xhr.send("phoneNo=" + $("#phone_no").val());
	}
}
function go() {
	x--;
	if (x > 0) {
		$("#sendcode").text(x + "秒");
		$("#sendcode").removeAttr("onclick");
	} else {
		$("#sendcode").text("重新获取");
		$("#sendcode").attr("onclick", "sendCode()");
	}
}

function safeStr(str) {
	return str.replace(/</g, '&lt;').replace(/>/g, '&gt;').replace(/"/g,
			"&quot;").replace(/'/g, "&#039;");
}
var re = /^[\u4e00-\u9fa5]{1}[A-Z]{1}[A-Z_0-9]{5}$/;
function saveInfo() {
	var car_no = $("#car_no").val();
	if (!isOk(car_no)) {
		alert("车牌号不能为空");
		return false;
	}
	if (car_no.search(re) == -1) {
		alert("车牌号格式不正确");
		return false;
	}
	var car_owner = $("#car_owner").val();
	var clxz = $("#clxz").val();
	var cllx = $("#cllx").val();
	var register_date = $("#register_date").val();
	var jq_date = $("#jq_date").val();
	var card_no = $("#card_no").val();
	var xsz_img_state = $("#xsz_img").attr("state");
	var xsz_img;
	if (xsz_img_state == 1) {
		alert("请上传行驶证照片");
		return;
	} else {
		xsz_img = $("#xsz_img").attr("src");
	}
	var sfz_img_state = $("#sfz_img").attr("state");
	var sfz_img;
	if (sfz_img_state == 1) {
		alert("请上传身份证或公司营业执照");
		return;
	} else {
		sfz_img = $("#sfz_img").attr("src");
	}
	var phone_no = $("#phone_no").val();
	var yzm_code = $("#yzm_code").val();
	if (isOk(car_owner) && isOk(clxz) && isOk(cllx) && isOk(register_date)
			&& isOk(jq_date)) {
		var state = $("#state").val();
		if (state == 1) {
			if (!isOk(yzm_code)) {
				alert("验证码不能为空");
				return false;
			}
			if (!checkSubmitMobil(phone_no)) {
				return false;
			}
		}
		var msg = {
			"car_no" : car_no,
			"car_owner" : car_owner,
			"clxz" : clxz,
			"cllx" : cllx,
			"register_date" : register_date,
			"jq_date" : jq_date,
			"card_no" : card_no,
			"xsz_img" : xsz_img,
			"sfz_img" : sfz_img,
			"phone_no" : phone_no,
			"yzm_code" : yzm_code,
			"openId" : $("#openId").val(),
			"state" : state,
			"uuid" : GetQueryString("uuid"),
			"orderSource":getcookie("shjw_order_source")
		};
		$.ajax({
			type : "POST",
			url : "/shjw/open/saveInfo.shtml",
			data : msg,
			async : false,
			cache : false,
			dataType : "json",
			success : function(data) {
				if (data.result == 1) {
					window.location.href = window.location.protocol + "//"
							+ window.location.host + "/shjw/wx/sxjc.html?uuid="
							+ data.yzm;
				} else {
					alert(data.msg);
					return;
				}
			}
		});
	} else {
		alert("请把信息录入完整");
	}
}

function photo(type) {
	var images = {
		localId : [],
		serverId : []
	};
	wx.chooseImage({
		count : 1,
		sizeType : [ "original", "compressed" ],
		sourceType : [ "album", "camera" ],
		success : function(res) {
			var localIds = res.localIds;
			images.localId = res.localIds;
			var i = 0, length = images.localId.length;
			function upload() {
				wx.uploadImage({
					localId : images.localId[i].toString(),
					success : function(res) {
						i++;
						images.serverId.push(res.serverId);
						var indata = {
							"media_id" : res.serverId
						};
						$.post("/shjw/open/getPhoto.shtml", indata, function(
								data) {
							if (data.result == 0) {
								alert("\u63d0\u4ea4\u5931\u8d25");
							} else {
								if (type == 1) {
									$("#xsz_img").attr("src", data.path);
									$("#xsz_img").attr("state", 2);
									$("#xsz_img").css(
											{
												"height" : "7rem",
												"width" : "auto",
												"max-width" : $(
														"#xsz_img_content")
														.width(),
												"margin-top" : "0rem",
												"text-align" : "center"
											});
								} else if (type == 2) {
									$("#sfz_img").attr("src", data.path);
									$("#sfz_img").attr("state", 2);
									$("#sfz_img").css(
											{
												"height" : "7rem",
												"width" : "auto",
												"max-width" : $(
														"#sfz_img_content")
														.width(),
												"margin-top" : "0rem",
												"text-align" : "center"
											});
								}
							}
						}, "json");
						if (i < length) {
							upload();
						}
					},
					fail : function(res) {
						alert(JSON.stringify(res));
					}
				});
			}
			upload();
		}
	});
}
