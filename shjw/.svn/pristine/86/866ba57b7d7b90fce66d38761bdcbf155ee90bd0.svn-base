var rigisterdate;
var xj_clxz, xj_clzl;
var xj_isInsure = 0;
var njdq_date = '';// 年检到期时间
function sxjc_init() {
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
	var uuid = GetQueryString("uuid");
	if (uuid != null && uuid != "" && uuid != "undefined") {
		var calendar = new LCalendar();
		var date = new Date();
		calendar.init({
			'trigger' : '#receive_material_date',
			'type' : 'date',
			'minDate' : date.getFullYear() + '-' + (date.getMonth() + 1) + '-'
					+ date.getDate(),
			'maxDate' : (date.getFullYear() + 3) + '-' + 12 + '-' + 31
		});
		calendar = new LCalendar();
		calendar.init({
			'trigger' : '#take_car_date',
			'type' : 'date',
			'minDate' : date.getFullYear() + '-' + (date.getMonth() + 1) + '-'
					+ date.getDate(),
			'maxDate' : (date.getFullYear() + 3) + '-' + 12 + '-' + 31
		});
		$("#slideOne").change(function() {
			if ($(this).attr("checked")) {
				xj_isInsure = 1;
				$("div.slideOne").css("background", "#00bc8d");
			} else {
				xj_isInsure = 0;
				$("div.slideOne").css("background", "#ddd");
			}
			isMJ(rigisterdate);
		});
		$("#slideTwo").change(function() {
			if ($(this).attr("checked")) {
				$("div.slideTwo").css("background", "#00bc8d");
				$(".slideTwoChild").css("display", "block");
			} else {
				$("div.slideTwo").css("background", "#ddd");
				$(".slideTwoChild").css("display", "none");
			}
		});
		$("#slideThree").change(function() {
			if ($(this).attr("checked")) {
				$("div.slideThree").css("background", "#00bc8d");
				$(".slideThreeChild").css("display", "block");
			} else {
				$("div.slideThree").css("background", "#ddd");
				$(".slideThreeChild").css("display", "none");
			}
		});
		$('.close-btn').click(function() {
			$('.popbox').fadeOut(function() {
				$('#screen').hide();
			});
			return false;
		});
		$('.popbox-link').click(function() {
			var h = $(document).height();
			$('#screen').css({
				'height' : h
			});
			$('#screen').show();
			$('.popbox').center();
			$('.popbox').fadeIn();
			return false;
		});
		getOrder();
	} else {
		alert("请求参数异常，请重试");
		history.back(-1);
	}
}
function getOrder() {
	$
			.ajax({
				type : "POST",
				url : "/shjw/open/findByUUID.shtml",
				data : {
					"uuid" : GetQueryString("uuid"),
					"openId" : $("#openId").val()
				},
				async : false,
				cache : false,
				dataType : "json",
				success : function(data) {
					if (data.result == 1) {
						var carNo = data.entity.licenseplate;
						var content = "<p class='text'>";
						rigisterdate = data.entity.rigisterdate;
						var date = new Date();
						var curDate = date.getFullYear() + "-"
								+ (date.getMonth() + 1) + "-" + date.getDate();
						var years = DateDiff(curDate, rigisterdate);
						xj_clxz = data.entity.vehicle;
						xj_clzl = data.entity.cartype;
						if (carNo.indexOf("沪") == 0) {
							content += carNo
									+ "<em>【沪牌】</em><span>"
									+ data.entity.vehicle
									+ "/"
									+ data.entity.cartype
									+ "/"
									+ years
									+ "年以内</span></p> <i class='ico ico-more'></i>";
						} else {
							content += carNo
									+ "<em>【外牌】</em><span>"
									+ data.entity.vehicle
									+ "/"
									+ data.entity.cartype
									+ "/"
									+ years
									+ "年以内</span></p> <i class='ico ico-more'></i>";
						}
						$("#orderTitle").html(content);
						$("#uuid").val(data.entity.uuid);
						$("#no_checked_date").text(getNJTime(rigisterdate));
						var injury_accident_last_year = data.entity.injury_accident_last_year;
						if (injury_accident_last_year != null
								&& injury_accident_last_year != 0) {
							$("div.slideOne").css("background", "#00bc8d");
							xj_isInsure = injury_accident_last_year;
						}
						var is_collect_materials = data.entity.is_collect_materials;
						if (is_collect_materials != null
								&& is_collect_materials != 0) {
							$("div.slideTwo").css("background", "#00bc8d");
							$(".slideTwoChild").css("display", "block");
							$("#receive_material_date").val(
									data.entity.collect_materials_time);
							$("#receive_material_address").val(
									data.entity.collect_materials_address);
							$("#send_material_address").val(
									data.entity.send_material_address);
						}
						var is_collect_car = data.entity.is_collect_car;
						if (is_collect_car != null && is_collect_car != 0) {
							$("div.slideThree").css("background", "#00bc8d");
							$(".slideThreeChild").css("display", "block");
							$("#take_car_date").val(
									data.entity.collect_car_time);
							$("#take_car_address").val(
									data.entity.collect_car_address);
						}
						isMJ(rigisterdate);
					} else {
						alert(data.msg);
						history.back(-1);
					}
				}
			});
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
		} else if (parseInt(date1[1]) == parseInt(date2[1])) {
			if (parseInt(date1[2]) < parseInt(date2[2])) {
				m = m - 1;
			}
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
	njdq_date = getNJTime(rigisterdate);
	if (flag) {
		$("#alert_text")
				.html(
						"<i class='ico ico-tip'></i>亲，您的爱车需要上线年检哦!年检到期时间为：<span id='checked_date'></span>");
		$("#checked_date").text(njdq_date);
	} else {
		$("#alert_text")
				.html(
						"<i class='ico ico-tip'></i>您的爱车暂不需要上线检车！可别忘了办理年检手续哦，年检到期时间为<span id='no_checked_date'></span>");
		$("#no_checked_date").text(njdq_date);
	}
}
function getNJTime(registerDate) {
	var date = new Date();
	var curDate = date.getFullYear() + "-" + (date.getMonth() + 1) + "-"
			+ date.getDate();
	var date1 = curDate.split("-");
	var date2 = registerDate.split("-");
	var m = 0;
	if (parseInt(date1) - parseInt(date2) >= 0) {
		m = parseInt(date1[0]) - parseInt(date2[0]);
		if (parseInt(date2[1]) < parseInt(date1[1])) {
			m = m + 1;
		}
		if (m < 6) {
			if (m != 2 && m != 4) {
				m = m + 1;
			}
		}
	} else {
		return "日期非法";
	}
	var yyyy, mm, day;
	yyyy = parseInt(date2[0]) + m;
	mm = parseInt(date2[1]);
	day = calcDays(yyyy, mm - 1);
	return yyyy + "年" + mm + "月" + day + "日";
}
function getNoNJTime(registerDate) {
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
		} else if (parseInt(date1[1]) == parseInt(date2[1])) {
			if (parseInt(date1[2]) < parseInt(date2[2])) {
				m = m - 1;
			}
		}
	} else {
		return "日期非法";
	}
	var yyyy, mm, day;
	if (m < 6) {
		if (m % 2 == 0) {
			yyyy = parseInt(date2[0]) + m;
		} else {
			yyyy = parseInt(date2[0]) + m + 1;
		}
	} else {
		yyyy = parseInt(date2[0]) + m;
	}
	mm = parseInt(date2[1]);
	day = calcDays(yyyy, mm - 1);
	return yyyy + "年" + mm + "月" + day + "日";
}
// 求月份最大天数
function calcDays(year, month) {
	if (month == 1) {
		if ((year % 4 == 0 && year % 100 != 0)
				|| (year % 400 == 0 && year % 4000 != 0)) {
			return 29;
		} else {
			return 28;
		}
	} else {
		if (month == 3 || month == 5 || month == 8 || month == 10) {
			return 30;
		} else {
			return 31;
		}
	}
}
function submit() {
	var injury_accident_last_year = 0;
	if ($("#slideOne").attr("checked")) {
		injury_accident_last_year = 1;
	}
	var is_collect_materials = 0;
	var collect_materials_time = "", collect_materials_address = "", send_material_address = "";
	if ($("#slideTwo").attr("checked")) {
		is_collect_materials = 1;
		collect_materials_time = $("#receive_material_date").val();
		collect_materials_address = $("#receive_material_address").val();
		send_material_address = $("#send_material_address").val();
		if (collect_materials_time == null
				|| trim(collect_materials_time).length == 0
				|| collect_materials_time == "undefined"
				|| collect_materials_address == null
				|| trim(collect_materials_address).length == 0
				|| collect_materials_address == "undefined"
				|| send_material_address == null
				|| trim(send_material_address).length == 0
				|| send_material_address == "undefined") {
			alert("请把信息填写完整");
			return false;
		}
	}
	var is_collect_car = 0;
	var collect_car_time = "", collect_car_address = "";
	if ($("#slideThree").attr("checked")) {
		is_collect_car = 1;
		collect_car_time = $("#take_car_date").val();
		collect_car_address = $("#take_car_address").val();
		if (collect_car_time == null || trim(collect_car_time).length == 0
				|| collect_car_time == "undefined"
				|| collect_car_address == null
				|| trim(collect_car_address).length == 0
				|| collect_car_address == "undefined") {
			alert("请把信息填写完整");
			return false;
		}
	}
	if ($("#agree_consent").attr("checked")) {
		$.ajax({
			type : "POST",
			url : "/shjw/open/updateOrderSXJC.shtml",
			data : {
				"uuid" : GetQueryString("uuid"),
				"injury_accident_last_year" : injury_accident_last_year,
				"is_collect_materials" : is_collect_materials,
				"collect_materials_time" : collect_materials_time,
				"collect_materials_address" : collect_materials_address,
				"send_material_address" : send_material_address,
				"is_collect_car" : is_collect_car,
				"collect_car_time" : collect_car_time,
				"collect_car_address" : collect_car_address,
				"njdq_date" : njdq_date
			},
			async : false,
			cache : false,
			dataType : "json",
			success : function(data) {
				if (data.result == 1) {
					window.location.href = window.location.protocol + "//"
							+ window.location.host
							+ "/shjw/wx/choose_jcz.html?uuid="
							+ GetQueryString("uuid");
				} else {
					alert(data.msg);
				}
			}
		});
	} else {
		alert("请阅读并同意《年检须知》");
		return false;
	}
}
function goBack() {
	window.location.href = window.location.protocol + "//"
			+ window.location.host + "/shjw/wx/car_infro_in.html?uuid="
			+ GetQueryString("uuid");
}
function trim(str) {
	return str.replace(/(^\s*)|(\s*$)/g, "");
}
function carManager() {
	window.location.href = window.location.protocol + "//"
			+ window.location.host + "/shjw/wx/car_manger.html";
}
jQuery.fn.center = function(loaded) {
	var obj = this;
	body_width = parseInt($(window).width());
	body_height = parseInt($(window).height());
	block_width = parseInt(obj.width());
	block_height = parseInt(obj.height());
	left_position = parseInt((body_width / 2) - (block_width / 2)
			+ $(window).scrollLeft());
	if (body_width < block_width) {
		left_position = 0 + $(window).scrollLeft();
	}
	top_position = parseInt((body_height / 2) - (block_height / 2)
			+ $(window).scrollTop());
	if (body_height < block_height) {
		top_position = 0 + $(window).scrollTop();
	}
	if (!loaded) {
		obj.css({
			'position' : 'absolute'
		});
		obj.css({
			'top' : top_position,
			'left' : left_position
		});
		$(window).bind('resize', function() {
			obj.center(!loaded);
		});
		$(window).bind('scroll', function() {
			obj.center(!loaded);
		});
	} else {
		obj.stop();
		obj.css({
			'position' : 'absolute'
		});
		obj.animate({
			'top' : top_position
		}, 200, 'linear');
	}
};
$(document).click(function() {
	if ($(".popbox,#screen").css({
		'display' : 'block'
	})) {
		$(".popbox,#screen").css({
			'display' : 'none'
		});
	}
});