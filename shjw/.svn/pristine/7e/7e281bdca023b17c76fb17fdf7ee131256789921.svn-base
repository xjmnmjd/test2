var order_num = 0, orderno, edit_type = "add";
function order_fp_init() {
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
	$("#bill_type").change(function() {
		if ($(this).val() == "company") {
			$(".company").css("display", "block");
		} else {
			$(".company").css("display", "none");
		}
	})
	var orderno = GetQueryString("orderno");
	if (orderno != null && orderno != "" && orderno != "undefined") {
		$.ajax({
			type : "POST",
			url : "/shjw/open/getBillByOrderNo.shtml",
			data : {
				"orderno" : orderno
			},
			async : false,
			cache : false,
			dataType : "json",
			success : function(data) {
				if (data.result == 1) {
					$("#bank_no").val(data.entity.bank_no);
					$("#bill_head").val(data.entity.bill_head);
					$("#bill_type").val(data.entity.bill_type);
					$("#company_address").val(data.entity.company_address);
					$("#company_phone").val(data.entity.company_phone);
					$("#contact_number").val(data.entity.contact_number);
					$("#mailing_address").val(data.entity.mailing_address);
					$("#opening_bank").val(data.entity.opening_bank);
					$("#taxpayer_identification_number").val(
							data.entity.taxpayer_identification_number);
					edit_type = "update";
					if (data.entity.bill_type == "company") {
						$(".company").css("display", "block");
					} else {
						$(".company").css("display", "none");
					}
				}
			}
		});
	} else {
		alert("请求参数异常，请重试");
		history.back(-1);
	}
}
function add_update_order_fp() {
	var bank_no = $("#bank_no").val();
	var bill_head = $("#bill_head").val();
	var bill_type = $("#bill_type").val();
	var company_address = $("#company_address").val();
	var company_phone = $("#company_phone").val();
	var contact_number = $("#contact_number").val();
	var mailing_address = $("#mailing_address").val();
	var opening_bank = $("#opening_bank").val();
	var taxpayer_identification_number = $("#taxpayer_identification_number")
			.val();
	var orderno = GetQueryString("orderno");
	var data = {
		bank_no : bank_no,
		bill_head : bill_head,
		bill_type : bill_type,
		company_address : company_address,
		company_phone : company_phone,
		contact_number : contact_number,
		mailing_address : mailing_address,
		opening_bank : opening_bank,
		taxpayer_identification_number : taxpayer_identification_number,
		orderno : orderno,
		edit_type : edit_type
	};
	if (orderno != null && orderno != "" && orderno != "undefined") {
		$.ajax({
			type : "POST",
			url : "/shjw/open/addOrUpdateBill.shtml",
			data : data,
			async : false,
			cache : false,
			dataType : "json",
			success : function(data) {
				if (data.result == 1) {
					alert(data.msg);
					history.back(-1);
				} else {
					alert(data.msg);
				}
			}
		});
	} else {
		alert("请求参数异常，请重试");
		history.back(-1);
	}
}