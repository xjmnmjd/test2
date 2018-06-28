<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>首页</title>
<link  href="${basePath}/css/bootstrap.min.css"  rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
<script type="text/javascript" src="${basePath}/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${basePath}/js/layer.js"></script>
</head>
<body style="width:100%" >
<input value="${basePath}" id="basevalue" style="display:none" />
<div class="right">
  <div class="up">
    <ul class="row">
        <li class="col-lg-4 col-md-4 col-sm-4">
            <div style="overflow:hidden; width:100%; background-color:#f5f5f5; padding:10px">
                <div><img src="${basePath}/img/ico_01.png"/>账户信息</div>
                <div class="rig">
                    <h2 id="clocks">2017年8月9日<span>星期三</span></h2>
                    <p class="time">上次登录时间：${user.lastLoginTime?string('yyyy-MM-dd HH:mm')}</p>
                   
                </div>
            </div>
            <#if user?exists>
            <p><span>用户名:</span>${token.email?default('空')}</p>
            <span style="font-size:14px">姓名:${user.nickname?default('空')}</span>
            <span style="font-size:14px">职位:${user.position?default('空')}</span>
            <span style="font-size:14px">手机号码:${user.phone?default('空')}</span>
            <span style="font-size:14px">类型:${user.u_type?default('空')}</span>
             </#if>
        </li>
        <li class="col-lg-4 col-md-4 col-sm-4">
            <div style="overflow:hidden; width:100%; background-color:#f5f5f5; height:100%;padding:10px">
                <div><img src="${basePath}/img/ico_02.png"/>已付款订单</div>
                <h3>${count?default("0")}</h3>
                <a class="go" id="go_pay" style="cursor:pointer">去处理&gt;</a>
            </div>
            
        </li>
        <li class="col-lg-4 col-md-4 col-sm-4">
            <div style="overflow:hidden; width:100%; background-color:#f5f5f5; height:100%;padding:10px">
                <div><img src="${basePath}/img/ico_11.png"/>未付款订单</div>
                <h3>${nopay_count?default("0")}</h3>
                <a class="go" id="go_nopay" style="cursor:pointer">去处理&gt;</a>
            </div>
            
        </li>
        <li class="col-lg-4 col-md-4 col-sm-4">
            <div style="overflow:hidden; width:100%; background-color:#f5f5f5; height:100%;padding:10px">
                <div><img src="${basePath}/img/ico_12.png"/>排班管理</div>
                <div class="rig">
                   <p style="margin:20px 0; border-bottom:none">今日排班</p>
                     <#if kfyList?exists && kfyList?size gt 0>
                     <#list kfyList as kfy>
                         <a>${kfy.nickname}</a>
                   </#list>
                    </#if>
                </div>
                <a class="go" id="go_kfy" style="cursor:pointer;float:right">查看更多&gt;</a>
            </div>
            
        </li>
    </ul>
</div>
</div>
<!--弹出层开始-->
<div id="screen"></div>
<div class="popbox">
	<div class="mainlist">
        <h2>修改密码<a class="close-btn">&times;</a></h2>
		<ul>
			<li><span>原密码</span><input/></li>
            <li><span>新密码</span><input/></li>
            <li><span>确认密码</span><input/></li>
		</ul>
        <div class="btn"><div><a class="close-btn">关闭</a><a>确定</a></div></div>
	</div>
</div>
<!--弹出层结束-->
<script>
  $(function(){
     $('#go_pay').click(function(){
         $("#order-pay", window.parent.document).click();

	     
     });
     $('#go_nopay').click(function(){
        $("#order-nopay", window.parent.document).click();
     });
     $('#go_kfy').click(function(){
        $("#pb", window.parent.document).click();
     });
  });
</script>
<script>
$(function(){
	var Width=$('body').width();
	var Height=$(window).height();
	$('.all,.header').css({'width':Width});
	$('.all').css({'height':Height});
	$('.left').css({'height':Height-80});
	$('.right').css({'width':Width,'height':Height-80,'margin-left':'-160px'});
	$('.left dl dt').click(function(){
		if($(this).siblings('dd')){
			$('.left dl dt').removeClass('cur');
			$('.left dl dt i').css({'background-position':'center 0'});
			$('.left dl dd').css({'display':'none'});
			$(this).siblings('dd').css({'display':'block'});
			$(this).siblings('dd').click(function(){
				$('.left dl dd').removeClass('current');
				$(this).addClass('current');
				
			});
		}else{
			$(this).addClass('cur');
			$(this).css({'background-position':'center -64px'});
		};
	});
});
</script>
<script type="text/javascript">
	function gettime(){
		var today=new Date();
		var year=today.getFullYear();
		var month=today.getMonth();
		var date=today.getDate();
		var hour=today.getHours();
		var minute=today.getMinutes();
		var second=today.getSeconds();
		var day=today.getDay();
		switch(day){
			case 0:
				day="星期日";
				break;
			case 1:
				day="星期一";
				break;
			case 2:
				day="星期二";
				break;
			case 3:
				day="星期三";
				break;
			case 4:
				day="星期四";
				break;
			case 5:
				day="星期五";
				break;
			case 6:
				day="星期六";
				break;
		}
		document.getElementById("clocks").innerHTML=year+"年"+(month+1)+"月"+date+"日"+day;
	}
	setInterval("gettime()",1000);
</script>

</body>
</html>
