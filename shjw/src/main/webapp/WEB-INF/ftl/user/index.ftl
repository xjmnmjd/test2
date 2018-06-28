<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>锦卫年检管理系统</title>
<link rel="stylesheet" type="text/css" rel="stylesheet" href="${basePath}/css/style.css" />
<link href="${basePath}/css/page.css" type="text/css" rel="stylesheet"/>


<script type="text/javascript" src="${basePath}/js/jquery-1.8.3.min.js"></script>
<script  src="${basePath}/js/common/layer/layer.js"></script>
<script baseUrl="${basePath}" src="${basePath}/js/user.login.js"></script>
</head>

<body>
<div class="all" style="height:100%">
    <div class="header">
      <input value="${basePath}" id="basevalue" style="display:none" />
        <div class="header-lef">
        <img src="${basePath}/img/logo_pic.png"/></div>
        <div class="header-rig">
            <a href="#"><img src="${basePath}/img/1.png"/>${token.nickname}</a>
            <a href="#" class="update_pwd"><img src="${basePath}/img/2.png"/>修改密码</a>
            <a href="javascript:void(0);" onclick="logout();"><img src="${basePath}/img/3.png"/>注销</a>
        </div>
    </div>
    <div class="left">
        <a class="go-back" style="display:none"><img src="${basePath}/img/go_back.png"/></a>
    <#macro typeChilden items>  
    <#assign countSize = items?size>                      
             <#list items as item>
                 <dl>
                    <#if item.m_menu.menu_name=="首页">
                    <dt id="${item.m_menu.menu_pid?default('')}" class="cur" style="cursor:pointer"><i class="${item.m_menu.menu_iclass?default('')}" style="cursor:pointer"></i>${item.m_menu.menu_name?default('')}</dt>
                    <#else>
                    <dt id="${item.m_menu.menu_pid?default('')}" style="cursor:pointer"><i class="${item.m_menu.menu_iclass?default('')}" style="cursor:pointer"></i>${item.m_menu.menu_name?default('')}</dt>
                    </#if>
                      <#if item.children?? && item.children?size gt 0> 
                         <#list item.children as item1>                                      
                            <dd id="${item1.m_menu.menu_pid?default('')}" style="cursor:pointer">${item1.m_menu.menu_name?default('')}</dd>                     
                         </#list>                                                                                                                                                                                                                                                                        
                     </#if>   
                 </dl>                                                                                 
             </#list>                           
    </#macro> 
                 <#if types??>            
                 <#list types as item>       
                    <#if item.children?? && item.children?size gt 0>  
                       <@typeChilden item.children></@typeChilden>  
                   </#if>              
                 </#list>
                 </#if> 
 </div>
    <div class="right" style="padding:0">
        <iframe id="ifra" src="${basePath}/user/sy.shtml?id=${token.id}" width="100%" height="100%" style="border:none;"></iframe>
    </div>
     <input value="${token.id}" class="id">
</div>
<!--<p class="bootom-txt">2017 ©  技术支持  浙江联保网络技术有限公司  0571-85819399</p>-->
<script type="text/javascript" src="${basePath}/js/jquery-1.8.3.min.js"></script>

<!--弹出层开始-->
<div id="screen"></div>
<div class="popbox">
	<div class="mainlist">
        <h2>修改密码<a class="close-btn" style="cursor:pointer">&times;</a></h2>
       <form id="formId" enctype="multipart/form-data" action="${basePath}/user/updatePswd.shtml" method="post">     
		<ul>
			<li><span>原密码</span><input type="password" class="form-control" autocomplete="off" id="pswd" maxlength="20" name="pswd"  placeholder="请输入原密码"/></li>
            <li><span>新密码</span><input type="password" class="form-control" autocomplete="off" id="newPswd" maxlength="20" name="newPswd" placeholder="请输入新密码"/></li>
            <li><span>确认密码</span><input type="password" class="form-control" autocomplete="off" id="reNewPswd" maxlength="20" name="reNewPswd"placeholder="请再次输入新密码"/></li>
		</ul>		
        <div class="btn"><div><a class="close-btn" style="cursor:pointer">关闭</a>
        <a style="margin-left:15px"> <button type="submit" class="btn_success" style="color:white;cursor:pointer">确定</button></a></div></div>
        </form>
	</div>
</div>
<!--弹出层结束-->
<script src="${basePath}/js/common/jquery/jquery.form-2.82.js?${_v}"></script>
<script  src="${basePath}/js/common/layer/layer.js"></script>
	<script>
			$(function(){
				var load;
				$("#formId").ajaxForm({
			    	success:function (result){
			    		layer.close(load);
			    		if(result && result.status != 200){
			    			return layer.msg(result.message,function(){}),!1;
			    		}else{
				    		layer.msg('操作成功！');
				    		$("form :password").val('');
			    		}
			    		$('.popbox').fadeOut(function(){ $('#screen').hide(); });
			    	},
			    	beforeSubmit:function(){
			    		//判断参数
			    		if($.trim($("#pswd").val()) == ''){
				    		layer.msg('请输入原密码',function(){});
				    		$("#pswd").parent().removeClass('has-success').addClass('has-error');
				    		return !1;
			    		}else{
			    			$("#pswd").parent().removeClass('has-error').addClass('has-success');
			    		}
			    		if($.trim($("#newPswd").val()) == ''){
				    		layer.msg('请输入新密码',function(){});
				    		$("#newPswd").parent().removeClass('has-success').addClass('has-error');
				    		return !1;
			    		}else{
			    			$("#newPswd").parent().removeClass('has-error').addClass('has-success');
			    		}
			    		if($.trim($("#reNewPswd").val()) == ''){
				    		layer.msg('请再次输入新密码',function(){});
				    		$("#reNewPswd").parent().removeClass('has-success').addClass('has-error');
				    		return !1;
			    		}else{
			    			$("#reNewPswd").parent().removeClass('has-error').addClass('has-success');
			    		}
			    		if($("#reNewPswd").val() != $("#newPswd").val()){
			    			return layer.msg('2次新密码输入不一致。',function(){}),!1;
			    		}
			    		load = layer.load('正在提交！！！');
			    	},
			    	dataType:"json",
			    	clearForm:false
				});
			
		});
		</script>


<script type="text/javascript">
  $(function (){ 
    $('.update_pwd').click(function(){    
          var h = $(document).height();
		$('#screen').css({ 'height': h });	
		$('#screen').show();
		$('.popbox').center();
		$('.popbox').fadeIn();
		return false;  
    });
    $('.close-btn').click(function(){
		$('.popbox').fadeOut(function(){ $('#screen').hide(); });
		return false;
	});
	
	
	$('.header-lef').click(function(){
	  $('.header').css("background-color","#1a7d65");
	  $('.left').css("background-color","#1a7d65");
	  $('dt').css("background-color","#1a7d65");
	  $('dl').css("background-color","#1a7d65");
	  $('dd').css("background-color","#1a7d65");
	  	  $('current').css("background-color","#1a7d65");
	  
	});
  
  });
  
 
</script>


<script>
$(function(){
	var Width=$(window).width();
	var Height=$(window).height();
	$('.all,.header').css({'width':Width});
	$('.all').css({'height':Height});
	$('.left').css({'height':Height-80});
	$('.right').css({'width':Width-160,'height':Height-140});
    $('.row').css({'width':'100%'});
	$('.list-group').css({'width':Width-300,'margin':'20px auto'});
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
});
</script>

<script type="text/javascript">
$(function () {   
	   $("#nature").click(function () {
		  $("iframe").attr("src", $("#basevalue").val()+"/nature/naturelist.shtml");
		  		  $(this).css({'display':'block'});
		  $(this).siblings('dd').css({'display':'block'});
		  $('.left dl dd').removeClass('current'); 
		  $(this).addClass('current');      
	   });
	     $("#ctype").click(function () {
		  $("iframe").attr("src", $("#basevalue").val()+"/type/vtypelist.shtml");
		  		  $(this).css({'display':'block'});
		  $(this).siblings('dd').css({'display':'block'});
		  $('.left dl dd').removeClass('current'); 
		  $(this).addClass('current');          
	   });
	    $("#index").click(function () {
		  $("iframe").attr("src", $("#basevalue").val()+"/user/sy.shtml?id="+$('.id').val());
		  $(this).addClass('cur');
		  $(this).find('i').css({'background-position':'center -64px'});
	   });
	    $("#station").click(function () {
		  $("iframe").attr("src", $("#basevalue").val()+"/station/stationlist.shtml");
		  $(this).css({'display':'block'});
		  $(this).siblings('dd').css({'display':'block'});
		  $('.left dl dd').removeClass('current'); 		  
		  $(this).addClass('current');
	   });
	    $("#wt").click(function () {
		  $("iframe").attr("src", $("#basevalue").val()+"/wt/wtList.shtml");
		  $(this).css({'display':'block'});
		  $(this).siblings('dd').css({'display':'block'});
		  $('.left dl dd').removeClass('current'); 		  
		  $(this).addClass('current');
	   });
	   $("#order-pay").click(function (){
		  $("iframe").attr("src", $("#basevalue").val()+"/order/orderList.shtml");	
		  $('#index i').css({'background-position':'center 0'});		
		  $("#index").removeClass('cur');
		  $(this).css({'display':'block'});
		  $(this).siblings('dd').css({'display':'block'});
		  $('.left dl dd').removeClass('current');  	
		  $(this).addClass('current');			
	   });
	   $("#order-nopay").click(function (){
		  $("iframe").attr("src", $("#basevalue").val()+"/order/nopayAll.shtml");
		  $('#index i').css({'background-position':'center 0'});		
		  $("#index").removeClass('cur');
		  $(this).css({'display':'block'});
		  $(this).siblings('dd').css({'display':'block'});
		  $('.left dl dd').removeClass('current');  	
		  $(this).addClass('current');
	   });
	   $("#order-lr").click(function (){
		  $("iframe").attr("src", $("#basevalue").val()+"/order/order_lr.shtml");
		  $(this).css({'display':'block'});
		  $(this).siblings('dd').css({'display':'block'});
		  $('.left dl dd').removeClass('current'); 		  
		  $(this).addClass('current');
	   });
	    $("#qx").click(function (){
		  $("iframe").attr("src", $("#basevalue").val()+"/mrole/qx.shtml");
		  $(this).css({'display':'block'});
		  $(this).siblings('dd').css({'display':'block'});
		  $('.left dl dd').removeClass('current'); 		  
		  $(this).addClass('current');
		  
	   });
	   $("#user").click(function (){
		  $("iframe").attr("src", $("#basevalue").val()+"/user/userlist.shtml");
		  $(this).css({'display':'block'});
		  $(this).siblings('dd').css({'display':'block'});
		  $('.left dl dd').removeClass('current'); 		  
		  $(this).addClass('current');
	   });
	   $("#fin").click(function (){
		  $("iframe").attr("src", $("#basevalue").val()+"/order/fin.shtml");
		  $(this).css({'display':'block'});
		  $(this).siblings('dd').css({'display':'block'});
		  $('.left dl dd').removeClass('current'); 
		  $(this).addClass('current')
	   });
	    $("#car").click(function (){
		  $("iframe").attr("src", $("#basevalue").val()+"/car/carList.shtml");
		  $(this).css({'display':'block'});
		  $(this).siblings('dd').css({'display':'block'});
		  $('.left dl dd').removeClass('current'); 		  
	      $(this).addClass('current');
	   });
	    $("#message").click(function (){
		  $("iframe").attr("src", $("#basevalue").val()+"/mess/messList.shtml");
		  $(this).css({'display':'block'});
		  $(this).siblings('dd').css({'display':'block'});
		  $('.left dl dd').removeClass('current'); 		  
		  $(this).addClass('current');
	   });
	   $("#order_sp").click(function (){
		  $("iframe").attr("src", $("#basevalue").val()+"/order/sp_order.shtml");
		  $(this).css({'display':'block'});
		  $(this).siblings('dd').css({'display':'block'});
		  $('.left dl dd').removeClass('current'); 		  
		  $(this).addClass('current');
	   });
	   $("#order_qz").click(function (){
		  $("iframe").attr("src", $("#basevalue").val()+"/order/qz_order.shtml");
		  $(this).css({'display':'block'});
		  $(this).siblings('dd').css({'display':'block'});
		  $('.left dl dd').removeClass('current'); 
		  $(this).addClass('current');
	   });
	   $("#pb").click(function (){
		  $("iframe").attr("src", $("#basevalue").val()+"/pb/pblist.shtml");
		  $('#index i').css({'background-position':'center 0'});		
		  $("#index").removeClass('cur');
	      $(this).css({'display':'block'});
		  $(this).siblings('dd').css({'display':'block'});
		  $('.left dl dd').removeClass('current');  	
		  $(this).addClass('current');
	   });
	   $("#lyan").click(function (){
		  $("iframe").attr("src", $("#basevalue").val()+"/info/inforlist.shtml");
		  $(this).css({'display':'block'});
		  $(this).siblings('dd').css({'display':'block'});
		  $('.left dl dd').removeClass('current'); 
		  $(this).addClass('current');
	   });
	   $("#record").click(function (){
		  $("iframe").attr("src", $("#basevalue").val()+"/cr/listRecord.shtml");
		  $(this).css({'display':'block'});
		  $(this).siblings('dd').css({'display':'block'});
		  $('.left dl dd').removeClass('current'); 
		  $(this).addClass('current');
	   });
	   $("#auto").click(function (){
		  $("iframe").attr("src", $("#basevalue").val()+"/auto/autoList.shtml");
		  $(this).css({'display':'block'});
		  $(this).siblings('dd').css({'display':'block'});
		  $('.left dl dd').removeClass('current'); 
		  $(this).addClass('current');
	   });
	   $("#hf").click(function (){
		  $("iframe").attr("src", $("#basevalue").val()+"/order/order_hf.shtml");
		  $(this).css({'display':'block'});
		  $(this).siblings('dd').css({'display':'block'});
		  $('.left dl dd').removeClass('current'); 
		  $(this).addClass('current');
	   });
	   $("#order_gj").click(function (){
		  $("iframe").attr("src", $("#basevalue").val()+"/gj/gjList.shtml");
		  $(this).css({'display':'block'});
		  $(this).siblings('dd').css({'display':'block'});
		  $('.left dl dd').removeClass('current'); 
		  $(this).addClass('current');
	   });	   
})
</script>
</body>
</html>
