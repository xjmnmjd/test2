<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>首页</title>
<link  href="${basePath}/css/bootstrap.min.css"  rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
</head>

<body>
<div class="all">
    <div class="header">
        <div class="header-lef"><img src="${basePath}/img/logo.png"/><img src="${basePath}/img/logo_pic.png"/></div>
        <div class="header-rig">
            <a href="#"><img src="${basePath}/img/1.png"/>admin</a>
            <a href="#"><img src="${basePath}/img/2.png"/>修改密码</a>
            <a href="#"><img src="${basePath}/img/3.png"/>注销</a>
        </div>
    </div>
    <div class="left">
        <a class="go-back"><img src="${basePath}/img/go_back.png"/></a>
        <dl>
          <dt class="cur"><i class="ico-index-1"></i>首页</dt>
        </dl>
        <dl>
            <dt><i class="ico-order"></i>订单管理</dt>
            <dd ><a href="${basePath}/user/allorder.shtml">已付款订单</a></dd>
            <dd>未付款订单</dd>
            <dd>潜在客户</dd>
            <dd>订单录入</dd>
        </dl>
        <dl>
            <dt><i class="ico-system"></i>系统管理</dt>
            <dd><a href="${basePath}/type/vtypelist.shtml">车辆类型</a></dd>
            <dd>车辆性质</dd>
            <dd>用户管理</dd>
            <dd>权限管理</dd>
        </dl>
        <dl>
            <dt><i class="ico-car"></i>车辆管理</dt>
            <dd>已付款订单</dd>
            <dd>未付款订单</dd>
            <dd>潜在客户</dd>
            <dd>订单录入</dd>
        </dl> 
        <dl>
            <dt><i class="ico-message"></i>短信管理</dt>
            <dd>已付款订单</dd>
            <dd>未付款订单</dd>
            <dd>潜在客户</dd>
            <dd>订单录入</dd>
        </dl>
        <dl>
          <dt><i class="ico-fin"></i>财务管理</dt>
        </dl>
    </div>
    <div class="right">
    
    <div class="up">
        <ul class="row">
            <li class="col-lg-4 col-md-4 col-sm-4">
                <div style="overflow:hidden; width:100%; background-color:#f5f5f5; padding:10px">
                    <div><img src="${basePath}/img/ico_01.png"/>账户信息</div>
                    <div class="rig">
                        <h2>2017年8月9日<span>星期三</span></h2>
                        <p class="time">上次登录时间：2017-08-08 12:12:12</p>
                    </div>
                </div>
                <p><span>用户名</span>，欢迎使用锦卫年检系统！</p>
                <span>姓名：007</span>
                <span>职位：业务员</span>
                <span>手机号码：17712349876</span>
                <span>类型：锦卫</span>
                <a class="change popbox-link">修改密码</a>
            </li>
            <li class="col-lg-4 col-md-4 col-sm-4">
                <div style="overflow:hidden; width:100%; background-color:#f5f5f5; height:100%;padding:10px">
                    <div><img src="${basePath}/img/ico_02.png"/>已付款订单</div>
                    <h3>20</h3>
                    <a class="go">去处理&gt;</a>
                </div>
                
            </li>
            <li class="col-lg-4 col-md-4 col-sm-4">
                <div style="overflow:hidden; width:100%; background-color:#f5f5f5; height:100%;padding:10px">
                    <div><img src="${basePath}/img/ico_11.png"/>未付款订单</div>
                    <h3>20</h3>
                    <a class="go">去处理&gt;</a>
                </div>
                
            </li>
            <li class="col-lg-4 col-md-4 col-sm-4">
                <div style="overflow:hidden; width:100%; background-color:#f5f5f5; height:100%;padding:10px">
                    <div><img src="${basePath}/img/ico_03.png"/>待处理订单</div>
                    <h3>20</h3>
                    <a class="go">去处理&gt;</a>
                </div>
                
            </li>
            <li class="col-lg-4 col-md-4 col-sm-4">
                <div style="overflow:hidden; width:100%; background-color:#f5f5f5; height:100%;padding:10px">
                    <div><img src="${basePath}/img/ico_12.png"/>待处理订单</div>
                    <div class="rig">
                       <p style="margin:20px 0; border-bottom:none">今日排班</p>
                       <a>客服员1</a>
                       <a>客服员1</a>
                       <a>客服员1</a>
                       <a>客服员1</a>
                    </div>
                    <a class="go">查看更多&gt;</a>
                </div>
                
            </li>
        </ul>
    </div>
    </div>
</div>
<p class="bootom-txt">2017 ©  技术支持  浙江联保网络科技有限公司  0571-85819399</p>
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

<script type="text/javascript" src="${basePath}/js/jquery-1.8.3.min.js"></script>
<script>
$(function(){
	var Width=$(window).width();
	var Height=$(window).height();
	$('.all,.header').css({'width':Width});
	$('.all').css({'height':Height});
	$('.left').css({'height':Height-80});
	$('.right').css({'width':Width-160,'height':Height-80});
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
$(document).ready(function(){
	$('.close-btn').click(function(){
		$('.popbox').fadeOut(function(){ $('#screen').hide(); });
		return false;
	});
	
	$('.popbox-link').click(function(){
		var h = $(document).height();
		$('#screen').css({ 'height': h });	
		$('#screen').show();
		$('.popbox').center();
		$('.popbox').fadeIn();
		return false;
	});
	
});

jQuery.fn.center = function(loaded) {
	var obj = this;
	body_width = parseInt($(window).width());
	body_height = parseInt($(window).height());
	block_width = parseInt(obj.width());
	block_height = parseInt(obj.height());
	
	left_position = parseInt((body_width/2) - (block_width/2)  + $(window).scrollLeft());
	if (body_width<block_width) { left_position = 0 + $(window).scrollLeft(); };
	
	top_position = parseInt((body_height/2) - (block_height/2) + $(window).scrollTop());
	if (body_height<block_height) { top_position = 0 + $(window).scrollTop(); };
	
	if(!loaded) {
		
		obj.css({'position': 'absolute'});
		obj.css({ 'top': top_position, 'left': left_position });
		$(window).bind('resize', function() { 
			obj.center(!loaded);
		});
		$(window).bind('scroll', function() { 
			obj.center(!loaded);
		});
		
	} else {
		obj.stop();
		obj.css({'position': 'absolute'});
		obj.animate({ 'top': top_position }, 200, 'linear');
	}
}

</script>

</body>
</html>
