<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>订单管理:潜在客户</title>
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
<link href="${basePath}/css/page.css" type="text/css" rel="stylesheet"/>
</head>

<body>
    <div class="top">
       <p><img src="${basePath}/img/ico_qiz.png"/>当前位置：订单管理>潜在客户 <span id="clocks"></span></p>     
     <a class="curr">潜在客户</a>
   </div>
<div class="down">
         <div class="search">
               <div><input  value="${qz_search?default('')}" placeholder="输入车主姓名或车牌号进行查询" class="search_value"/><a style="cursor:pointer" class="search_qz">查询</a></div><a class="jq-search" style="cursor:pointer">精确查询<i class="ico-jt ico-jt-1"></i></a>
                           <a class="shuaxin" style="color:#000000;cursor:pointer;float:right;margin-top:10px">刷新</a>          
 <a class="shuaxin" style="cursor:pointer;float:right;margin-top:10px;margin-right: 2px">
            <img src="${basePath}/img/shuaxin.png" style=" width:20px;height:20px;"/></a>
               </div>
       
          <#if status="0">
             <div class="cx-result">
          <#else>
             <div class="cx-result" style="display:block">
          </#if> 
             
          <form  action="${basePath}/order/qz_order.shtml?status=1" method="post">
               <div class="choose">                           
                  <input placeholder="车主姓名" name="owner" class="owner" value="${xorder.owner?default('')}"/>
                  <input placeholder="车牌号码" name="licenseplate" class="licenseplate" value="${xorder.licenseplate?default('')}"/>
                  <input placeholder="手机号码" name="phonenumber" class="phonenumber" value="${xorder.phonenumber?default('')}"/>
                  <input class="demo-input" value="${tj_start?default('')}" id="test1" name="tj_start" type="text" placeholder="选择提交开始时间" style="margin-right:0" /><img src="${basePath}/img/line.png"/>
                  <input class="demo-input" value="${tj_end?default('')}" name="tj_end" placeholder="选择提交结束时间" type="text" id="test2"/>                                  
               </div>           
               <a style="left:66%;top:0px"><input style="cursor:pointer" value="查询" type="submit" class="jq_search_ok"/></a>
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
            <th scope="col">潜在客户来源</th>              
          </tr>
               <#if qz_list?exists && qz_list?size gt 0>
               <#list qz_list as order>
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
                    <td>未付款</td>
                    <td>潜在客户</td>
                    <td>${order.ordersource?default('')}</td>
                      <#if order.qz_status=1>                  
                        <td>退回订单</td>
                      <#elseif order.qz_status=2>
                        <td>回访订单</td>
                      <#else>
                        <td>未知订单</td>
                      </#if>
                 </tr>
                 </#list>
            <#else>
				 <tr>
			<td class="text-center danger" colspan="15">未找到潜在客户</td>
				</tr>
			</#if> 
        </table>
         <input style="display:none" value="${pageIndex}" class="page_i"/>
     <#if s_status==0>  
    <ul class="page"  id="page">
       <li><a href="${basePath}/order/qz_order.shtml?pageIndex=1">首页</a></li>
       <li>
       <#if pageIndex-1=0>
            <a href="${basePath}/order/qz_order.shtml?pageIndex=1">上一页</a>
       <#else>
            <a href="${basePath}/order/qz_order.shtml?pageIndex=${pageIndex-1}">上一页</a>
       </#if> 
       </li>    
      
              <#if total<5 && total gt 0>             
              <#list 1..total as i>    
          <li id="${i}"><a href="${basePath}/order/qz_order.shtml?pageIndex=${i}">${i}</a></li>
              </#list>
            <#elseif total=0>
            <li id="1"><a href="${basePath}/order/qz_order.shtml?pageIndex=1">1</a></li>
              <#else>
                     <#if pageIndex<5>
                         <#list 1..4 as i>    
                         <li id="${i}"><a href="${basePath}/order/qz_order.shtml?pageIndex=${i}">${i}</a></li>
                         </#list>
                     <#else>
                        <#list pageIndex-3..pageIndex as i>    
                         <li id="${i}"><a href="${basePath}/order/qz_order.shtml?pageIndex=${i}">${i}</a></li>
                        </#list>
                     </#if>           
        
              </#if> 
             <li>  
               <#if pageIndex gte total>
               <a href="${basePath}/order/qz_order.shtml?pageIndex=${pageIndex}">下一页</a>
              <#else>
              <a href="${basePath}/order/qz_order.shtml?pageIndex=${pageIndex+1}">下一页</a>
             </#if>
             </li>
         <#if total=0>
          <li><a href="${basePath}/order/qz_order.shtml?pageIndex=${total+1}">末页</a></li>
          <#else>
          <li><a href="${basePath}/order/qz_order.shtml?pageIndex=${total}">末页</a></li>          
          </#if>      
       </ul>
                                     <#else>
           <p style="font-size:16px;color:#FF8040;text-align:center">成功为您找到${qz_list?size}款符合的订单</p>
       </#if> 
   </div>
       
         

<script type="text/javascript" src="${basePath}/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${basePath}/js/page.js"></script>
<script type="text/javascript" src="${basePath}/js/common/layer/layer.js"></script>
<script type="text/javascript">
$(document).ready(function(){
   var page_i=$('.page_i').val();
   $('#'+page_i).addClass("curru");
 });
$(function(){
    $('.search_qz').click(function(){
       var qz_search=$('.search_value').val();   
       location.href="${basePath}/order/qz_order.shtml?qz_search="+encodeURI(encodeURI(qz_search));           
   });
      $('.shuaxin').click(function(){
      location.href="${basePath}/order/qz_order.shtml";
      //window.location.reload(true);	             
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

</script>
</body>
</html>
