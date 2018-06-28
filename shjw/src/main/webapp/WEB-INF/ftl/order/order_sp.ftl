<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>订单管理-超权限审批</title>
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
<link href="${basePath}/css/page.css" type="text/css" rel="stylesheet"/>
</head>
<style>
.ts{font-size:4px;color:red}
</style>
<body>
        <div class="top">
            <p><img src="${basePath}/img/ico_qiz.png"/>当前位置：订单管理>超权限审批<span id="clocks">2017-08-01 11：29 星期二</span></p>                  
           <a class="curr" style="width:120px"><#if count gt 0>超权限审批<span class="ts">(${count})</span>
            <#else>超权限审批</#if></a>
            <a href="${basePath}/order/month_list.shtml" style="width:120px">当月新进订单</a>
            <a href="${basePath}/order/account_list.shtml" style="width:120px">已结算订单</a>      
                  
 
       </div>
        <div class="down">
         <div class="search">
               <div><input value="${sp_search?default('')}" placeholder="输入车主姓名或车牌号进行查询" class="search_value"/><a style="cursor:pointer" class="search_sp">查询</a></div>
               <a class="jq-search" style="cursor:pointer">精确查询<i class="ico-jt ico-jt-1"></i></a>
                 <a class="shuaxin" style="color:#000000;cursor:pointer;float:right">刷新</a>          
                 <a class="shuaxin" style="cursor:pointer;float:right;margin-right:2px">
            <img src="${basePath}/img/shuaxin.png" style=" width:20px;height:20px;"/></a>
            </div>
          <#if status="0">
             <div class="cx-result">
          <#else>
             <div class="cx-result" style="display:block">
          </#if>            
          <form  action="${basePath}/order/sp_order.shtml?status=1" method="post">
               <div class="choose">                           
                  <input placeholder="车主姓名" name="owner" class="owner" value="${xorder.owner?default('')}"/>
                  <input placeholder="车牌号码" name="licenseplate" class="licenseplate" value="${xorder.licenseplate?default('')}"/>
                  <input placeholder="手机号码" name="phonenumber" class="phonenumber" value="${xorder.phonenumber?default('')}"/>
                  <input class="demo-input" value="${tj_start?default('')}" id="test1" name="tj_start" type="text" placeholder="选择提交开始时间" style="margin-right:0" /><img src="${basePath}/img/line.png"/>
                  <input class="demo-input" value="${tj_end?default('')}" name="tj_end" placeholder="选择提交结束时间" type="text" id="test2"/>                 
                  <input class="demo-input" value="${yy_start?default('')}" name="yy_start" type="text" placeholder="选择预约开始时间" style="margin-right:0" id="test3"/><img src="${basePath}/img/line.png"/>
                  <input class="demo-input" value="${yy_end?default('')}" name="yy_end" placeholder="选择预约结束时间" type="text" id="test4"/>
               </div> 
               <a style="left:94%;top:0px"><input style="cursor:pointer" value="查询" type="submit" class="jq_search_ok"/></a>
            </form> 
            </div> 
                 
            <table width="100%" border="0">
                  <tr>        
                    <th scope="col">提交时间</th>
                    <th scope="col">车主</th>
                    <th scope="col">车牌</th>
                    <th scope="col">类型</th>
                    <th scope="col">是否缴费</th>
                    <th scope="col">业务类型</th>
                    <th scope="col">预约时间</th>
                    <th scope="col">预约监测站</th>
                    <th scope="col">客服员</th>
                    <th scope="col">订单状态</th>
                    <th scope="col">审核状态</th>
                    <th scope="col">订单来源</th>
                    <th scope="col" colspan='2'>操作</th>
                  </tr>
            <#if splist?exists && splist?size gt 0>
          <#list splist as order>
                 <tr>
                    <td>${order.submittime?default('')}</td>
                    <td>${order.owner?default('')}</td>
                    <td>${order.licenseplate?default('')}</td>
                    <td>${order.cartype?default('')}</td>
                     <#if order.ispayment=0>
                    <td>未缴费</td>
                    <#else>
                    <td>已经缴费</td>
                    </#if>
                    <td>${order.businesstype?default('')}</td>
                    <td>${order.appointmenttime?default('')}</td>
                    <td>${order.station?default('')}</td>
                    <td>${order.customer?default('')}</td>
                    <#if order.orderstatus=1>
                    <td>未付款</td>
                    <#elseif order.orderstatus=2> 
                    <td>已付款</td>
                    </#if> 
                    <#if order.suditstatus=13>                                                   
                     <td>未付款审批中</td>
                     <#else>
                     <td>缴费审批中</td>
                     </#if>                         
                    <td>${order.ordersource?default('')}</td>
                    <td><a style='cursor:pointer;width:100%;text-align:center' href="${basePath}/order/order_detail.shtml?orderid=${order.orderid}">详情</a></td>
                    <td><a style='cursor:pointer;width:100%;text-align:center' onclick='sp_btn(${order.orderid})'>审批</a>                  
                    </td>
                  
                  </tr>
            </#list>
             <#else>
				 <tr>
			        <td class="text-center danger" colspan="15">未找到审批订单 </td>
				</tr>
			    </#if>
                </table>
                 <input style="display:none" value="${pageIndex}" class="page_i"/>
           <#if s_status==0>    
 <ul class="page"  id="page">
       <li><a href="${basePath}/order/sp_order.shtml?pageIndex=1">首页</a></li>
       <li>
       <#if pageIndex-1=0>
            <a href="${basePath}/order/sp_order.shtml?pageIndex=1">上一页</a>
       <#else>
            <a href="${basePath}/order/sp_order.shtml?pageIndex=${pageIndex-1}">上一页</a>
       </#if> 
       </li>    
      
               <#if total<5 && total gt 0>            
              <#list 1..total as i>    
          <li id="${i}"><a href="${basePath}/order/sp_order.shtml?pageIndex=${i}">${i}</a></li>
              </#list>
           <#elseif total=0>
            <li id="1"><a href="${basePath}/order/sp_order.shtml?pageIndex=1">1</a></li>
              <#else>
                     <#if pageIndex<6>
                         <#list 1..5 as i>    
                         <li id="${i}"><a href="${basePath}/order/sp_order.shtml?pageIndex=${i}">${i}</a></li>
                         </#list>
                     <#else>
                        <#list pageIndex-4..pageIndex as i>    
                         <li id="${i}"><a href="${basePath}/order/sp_order.shtml?pageIndex=${i}">${i}</a></li>
                        </#list>
                     </#if>           
        
              </#if> 
             <li>  
               <#if pageIndex gte total>
               <a href="${basePath}/order/sp_order.shtml?pageIndex=${pageIndex}">下一页</a>
              <#else>
             <a href="${basePath}/order/sp_order.shtml?pageIndex=${pageIndex+1}">下一页</a>
             </#if>
             </li>
         <#if total=0>
          <li><a href="${basePath}/order/sp_order.shtml?pageIndex=${total+1}">末页</a></li>
          <#else>
          <li><a href="${basePath}/order/sp_order.shtml?pageIndex=${total}">末页</a></li>          
          </#if>     
        </ul> 
                                             <#else>
           <p style="font-size:16px;color:#FF8040;text-align:center">成功为您找到${splist?size}款符合的订单</p>
        </#if>
        </div>
<!--回访弹出层开始-->
<div id="screen"></div>
<div class="popbox">
	<div class="mainlist">
         <input class="sp_orderid" style="display:none"/>
         <p class="cc">审批结果</p>
        <div class="btn"><div><a class="close-btn" style='cursor:pointer'>关闭</a>
        <a class="sp_ok_btn" style='cursor:pointer;margin-left:15px'>通过</a></div></div>
	</div> 
</div>
<!--弹出层结束-->



<script type="text/javascript" src="${basePath}/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${basePath}/js/page.js"></script>
<script type="text/javascript" src="${basePath}/js/common/layer/layer.js"></script>
 <script  src="${basePath}/js/common/jquery/jquery1.8.3.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
   var page_i=$('.page_i').val();
   $('#'+page_i).addClass("curru");
   
    $('.search_sp').click(function(){
       var sp_search=$('.search_value').val();   
       location.href="${basePath}/order/sp_order.shtml?sp_search="+encodeURI(encodeURI(sp_search));           
   });
      $('.shuaxin').click(function(){
      location.href="${basePath}/order/sp_order.shtml";
      //window.location.reload(true);	             
   });

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
<script>
$(function(){
	$('.jq-search').click(function(){
		if($('.ico-jt').hasClass('ico-jt-1')){
			$('.ico-jt').removeClass('ico-jt-1').addClass('ico-jt-2');
			$('.cx-result').css({'display':'block'});
		}else{
			$('.ico-jt').removeClass('ico-jt-2').addClass('ico-jt-1');
			$('.cx-result').css({'display':'none'});
		};
	});
});
</script>

<script type="text/javascript">
//回访弹出
   function sp_btn(orderid){
        var h = $(document).height();
        $('.sp_orderid').val(orderid);
		$('#screen').css({ 'height': h });	
		$('#screen').show();
		$('.popbox').center();
		$('.popbox').fadeIn();
		return false;
  }
$(document).ready(function(){
	$('.close-btn').click(function(){		   
		$('.popbox').fadeOut(function(){ $('#screen').hide(); });
		return false;
	});		
    //审批通过
    $('.sp_ok_btn').click(function(){
           var orderid=$('.sp_orderid').val();  
           var data={orderid:orderid};
           $.ajax({
		       url:"${basePath}/order/checkOrder.shtml",
		       data:data,			 
		       type:"post",			        		        
		       success:function(result){
	       	      $('.popbox').fadeOut(function(){ $('#screen').hide(); });
	       	      layer.msg("审批成功");       	     
	              setTimeout(function(){window.location.href="${basePath}/order/sp_order.shtml?pageIndex="+$('.page_i').val();},2000);	
		       }
	       });
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
<script>
$(function(){
	$('.choose-kf').click(function(){
		if($('.ico-radio').hasClass('ico-radio-1')){
			$(this).siblings().find('.ico-radio').removeClass('ico-radio-2').addClass('ico-radio-1');
			$(this).find('.ico-radio').removeClass('ico-radio-1').addClass('ico-radio-2');
		}else{
			$(this).find('.ico-radio').removeClass('ico-radio-2').addClass('ico-radio-1');
		};
	});
})
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
		document.getElementById("clocks").innerHTML=year+"-"+(month+1)+"-"+date+" "+hour+":"+minute+":"+second+day;
	}
	setInterval("gettime()",1000);
</script>
<script src="${basePath}/js/laydate/laydate.js"></script>
<script>
lay('#version').html('-v'+ laydate.v);

//执行一个laydate实例
laydate.render({
  elem: '#test1' //指定元素
});
laydate.render({
  elem: '#test2' //指定元素
});
laydate.render({
  elem: '#test3' //指定元素
});
laydate.render({
  elem: '#test4' //指定元素
});
</script>
</body>
</html>
