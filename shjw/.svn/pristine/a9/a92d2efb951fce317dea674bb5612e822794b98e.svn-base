function weixin_init() {
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
					} else {
						location.href = fromurl;
					}
				}
			});
		}
	} 
}