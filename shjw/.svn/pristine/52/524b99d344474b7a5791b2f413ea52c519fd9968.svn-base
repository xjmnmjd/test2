<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>订单管理</title>
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
<link href="${basePath}/css/page.css" type="text/css" rel="stylesheet"/>
<style>
.ts{font-size:4px;color:red}
</style>	
</head>

<body>
    <div class="top">
     <p><img src="${basePath}/img/ico_qiz.png"/>当前位置：订单管理>已付款订单>全部<span id="clocks"></span></p>
      <a class="curr" href="${basePath}/order/orderList.shtml">全部</a> 
      <#if user.position="验车员">
          <a href="${basePath}/order/order_nj.shtml">
                   <#if ox.nj_counts gt 0>  
                                   年检<span class="ts">(${ox.nj_counts})</span>
              <#else>年检</#if></a> 
     <#else>
           <#if user.position="系统管理员"||user.position="主管">                 
             <a href="${basePath}/order/order_nfp.shtml">
               <#if ox.fp_count gt 0>  
                                     待分配<span class="ts">(${ox.fp_count})</span>
               <#else>待分配</#if>
            </a>
          </#if>
       <a href="${basePath}/order/order_njs.shtml">
              <#if ox.js_counts gt 0>  
                                  待接受<span class="ts">(${ox.js_counts})</span>
             <#else>待接受</#if></a>
       <a href="${basePath}/order/order_wzcx.shtml">
              <#if ox.wz_counts gt 0>  
                                 违章查询<span class="ts">(${ox.wz_counts})</span>
             <#else>违章查询 </#if></a>
       <a href="${basePath}/order/order_zlsj.shtml">
              <#if ox.zl_counts gt 0>  
                                 资料收集<span class="ts">(${ox.zl_counts})</span>
             <#else>资料收集 </#if></a>
       <#if user.position="客服员">
          <#else>
           <a href="${basePath}/order/order_wtbl.shtml">
              <#if ox.wt_counts gt 0>  
                                    委托办理<span class="ts">(${ox.wt_counts})</span>
              <#else>委托办理</#if></a>     
      </#if>
       <a href="${basePath}/order/order_yynj.shtml">
                  <#if ox.yy_counts gt 0>  
                                   预约年检<span class="ts">(${ox.yy_counts})</span>
              <#else>预约年检</#if></a> 
       <a href="${basePath}/order/order_jfqr.shtml">
               <#if ox.jf_counts gt 0>  
                                   缴费确认<span class="ts">(${ox.jf_counts})</span>
              <#else>缴费确认</#if></a>                   
       <a href="${basePath}/order/order_nj.shtml">
                   <#if ox.nj_counts gt 0>  
                                   年检<span class="ts">(${ox.nj_counts})</span>
              <#else>年检</#if></a>                  
    </#if>

   </div>
<div class="down">
      <div class="search">
           <input  class="status"/>          
          <div class="x_search"><input value="${search?default('')}" placeholder="输入车主姓名或车牌号进行查询" class="search_value"/><a style="cursor:pointer" class="search_all">查询</a>
          </div>
           <a class="jq-search" style="cursor:pointer;"><span class="search_word">精确查询</span><i class="ico-jt ico-jt-1"></i></a>     
                  <a class="shuaxin" style="color:#000000;cursor:pointer;float:right">刷新</a>          
             <a class="shuaxin" style="cursor:pointer;float:right;margin-right:2px">
            <img src="${basePath}/img/shuaxin.png" style=" width:20px;height:20px;"/></a>
      </div>
      
          <#if status="0">
             <div class="cx-result">
          <#else>
             <div class="cx-result" style="display:block">
          </#if>
          <form  action="${basePath}/order/orderList.shtml?status=1" method="post">
               <div class="choose">               
                  <select name="suditstatus" class="suditstatus">
                        <option value="12">选择审核状态</option>
                        <option value="13">待分配</option>
                        <option value="1">待接受</option>
                        <option value="2">违章查询</option>
                        <option value="3">违章处理</option>
                        <option value="4">资料收集</option>
                        <option value="5">委托办理中</option>
                        <option value="6">资料已寄等待中</option>
                        <option value="7">缴费确认中</option>
                        <option value="8">预约年检中</option>
                        <option value="9">待年检</option>
                        <option value="10">年检通过</option>
                        <option value="11">年检完成</option>
                  </select>
                  <input placeholder="车主姓名" name="owner" class="owner" value="${xorder.owner?default('')}"/>
                  <input placeholder="车牌号码" name="licenseplate" class="licenseplate" value="${xorder.licenseplate?default('')}"/>
                  <input placeholder="手机号码" name="phonenumber" class="phonenumber" value="${xorder.phonenumber?default('')}"/>
               </div>
               <div class="choose">
                   <input class="demo-input" value="${tj_start?default('')}" id="test1" name="tj_start" type="text" placeholder="选择提交开始时间" style="margin-right:0" /><img src="${basePath}/img/line.png"/>
                   <input class="demo-input" value="${tj_end?default('')}" name="tj_end" placeholder="选择提交结束时间" type="text" id="test2"/>                 
                   <input class="demo-input" value="${yy_start?default('')}" name="yy_start" type="text" placeholder="选择预约开始时间" style="margin-right:0" id="test3"/><img src="${basePath}/img/line.png"/>
                   <input class="demo-input" value="${yy_end?default('')}" name="yy_end" placeholder="选择预约结束时间" type="text" id="test4"/>
               </div>  
               <a style="left:55%;top:60px"><input style="cursor:pointer" value="查询" type="submit" class="jq_search_ok"/></a>
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
            <th scope="col">操作</th>
          </tr>
               <#if orderList?exists && orderList?size gt 0>
               <#list orderList as order>
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
                    <td>已付款</td>
                    <#if order.suditstatus=0>
                    <td>待分配</td>
                    <#elseif order.suditstatus=1>
                    <td>待接受</td>
                    <#elseif order.suditstatus=2>
                    <td>违章查询中</td>
                    <#elseif order.suditstatus=3>
                    <td>违章处理中</td>
                    <#elseif order.suditstatus=4>
                    <td>资料收集中</td>
                    <#elseif order.suditstatus=5>
                    <td>委托办理中</td>
                    <#elseif order.suditstatus=6>
                    <td>资料已寄等待中</td>
                    <#elseif order.suditstatus=7>
                    <td>缴费确认中</td>
                    <#elseif order.suditstatus=8>
                    <td>预约年检中</td>
                    <#elseif order.suditstatus=9>
                    <td>待年检</td>
                    <#elseif order.suditstatus=10>
                    <td>年检通过</td>
                    <#elseif order.suditstatus=11>
                    <td>年检完成</td>
                    <#elseif order.suditstatus=13>
                    <td>未付款审批中</td>
                    <#elseif order.suditstatus=15>
                    <td>缴费审批中</td>
                    <#else>
                    <td>未设置</td>                  
                    </#if>
                    <td>${order.ordersource?default('')}</td>
                    <td><a style='cursor:pointer;width:100%;text-align:center' href="${basePath}/order/order_detail.shtml?orderid=${order.orderid}">详情</a>
                    <#if user.position="系统管理员"||user.position="主管"> 
                    <a style='cursor:pointer;width:100%;text-align:center' onclick="deleteOrder(${order.orderid})">删除</a></td>
                    </#if>
                 </tr>
                 </#list>
            <#else>
				 <tr>
			<td class="text-center danger" colspan="15">未找到订单</td>
				</tr>
			</#if> 
        </table>
         <input style="display:none" value="${pageIndex}" class="page_i"/>
         <input style="display:none" value="${order.suditstatus}" class="order_suditstatus"/>
      <#if s_status=0>
       <ul class="page"  id="page">
       <li><a href="${basePath}/order/orderList.shtml?pageIndex=1">首页</a></li>
       <li>
       <#if pageIndex-1=0>
            <a href="${basePath}/order/orderList.shtml?pageIndex=1">上一页</a>
       <#else>
            <a href="${basePath}/order/orderList.shtml?pageIndex=${pageIndex-1}">上一页</a>
       </#if> 
       </li>    
       
              <#if total<5 && total gt 0>           
              <#list 1..total as i>    
          <li id="${i}"><a href="${basePath}/order/orderList.shtml?pageIndex=${i}">${i}</a></li>
              </#list>
            <#elseif total=0>
            <li id="1"><a href="${basePath}/order/orderList.shtml?pageIndex=1">1</a></li>
              <#else>
                     <#if pageIndex<5>
                         <#list 1..4 as i>    
                         <li id="${i}"><a href="${basePath}/order/orderList.shtml?pageIndex=${i}">${i}</a></li>
                         </#list>
                     <#else>
                        <#list pageIndex-3..pageIndex as i>    
                         <li id="${i}"><a href="${basePath}/order/orderList.shtml?pageIndex=${i}">${i}</a></li>
                        </#list>
                     </#if>           
        
              </#if> 
             <li>  
               <#if pageIndex gte total>
               <a href="${basePath}/order/orderList.shtml?pageIndex=${pageIndex}">下一页</a>
              <#else>
              <a href="${basePath}/order/orderList.shtml?pageIndex=${pageIndex+1}">下一页</a>
             </#if>
             </li>
         <#if total=0>
          <li><a href="${basePath}/order/orderList.shtml?pageIndex=${total+1}">末页</a></li>
          <#else>
          <li><a href="${basePath}/order/orderList.shtml?pageIndex=${total}">末页</a></li>          
          </#if>    
      </ul>
                              <#else>
           <p style="font-size:16px;color:#FF8040;text-align:center">成功为您找到${orderList?size}款符合的订单</p> 
      </#if>
   </div>
       
         
<!--删除弹出层开始-->
<div id="screen"></div>
<div class="popbox1" style="width:500px">
	<div class="mainlist">
        <h2>是否删除<a class="close-btn1"  style='cursor:pointer'>&times;</a></h2>
        <input class="deleteOrderid" style="display:none"/>
         <p style="font-size:18px; color:#666;float:left;margin-left:20px">请填写删除理由</p>
         <textarea class="text" id="change_text" style="width:400px;margin-right:60px;height:120px"></textarea>
        <div class="btn" style="height:84px"><div><a class="close-btn1" style='cursor:pointer'>关闭</a>
        <a class="del-ok-btn" style='cursor:pointer;margin-left:15px'>确定</a></div></div>
	</div>
</div>
<!--弹出层结束-->
<script type="text/javascript" src="${basePath}/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${basePath}/js/page.js"></script>
<script type="text/javascript" src="${basePath}/js/common/layer/layer.js"></script>
<script type="text/javascript">
  function deleteOrder(orderid){
		var h = $(document).height();
		$('#screen').css({ 'height': h });	
		$('#screen').show();
		$('.popbox1').center();
		$('.popbox1').fadeIn();
		$('.deleteOrderid').val(orderid);
		return false;
  }

$(document).ready(function(){
   var page_i=$('.page_i').val();
   $('#'+page_i).addClass("curru");
   var suditstatus=$('.order_suditstatus').val();
   $(".suditstatus option[value='"+suditstatus+"']").attr("selected","selected");  
      
   	$('.close-btn1').click(function(){
		$('.popbox1').fadeOut(function(){ $('#screen').hide(); });
		return false;
	});
	
    $('.search_all').click(function(){
       var search=$('.search_value').val(); 
        location.href="${basePath}/order/orderList.shtml?search="+encodeURI(encodeURI(search));           
    });
   
   $('.shuaxin').click(function(){
   location.href="${basePath}/order/orderList.shtml";
    //window.location.reload(true);	             
   });
   //删除确定
   $('.del-ok-btn').click(function(){
     var orderid=$('.deleteOrderid').val();
     var changeCause=$('#change_text').val();
     if(changeCause==''||changeCause==undefined){
        alert("请输入删除理由");
        return false;
     }
     var data={orderid:orderid,changeCause:changeCause};
   	      $.ajax({
		    url:"${basePath}/order/deleteOrder.shtml",
			data:data,
			type:"post",			        		        
			success:function(result){			     				  			       				     				    
			  $('.popbox1').fadeOut(function(){ $('#screen').hide(); });
		      layer.msg("删除成功");
		      setTimeout(function(){window.location.href="${basePath}/order/orderList.shtml?pageIndex="+$('.page_i').val();},2000);		      
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
