<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>订单管理-待接受</title>
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
<link href="${basePath}/css/page.css" type="text/css" rel="stylesheet"/>
</head>
<style>
.ts{font-size:4px;color:red}
</style>
<body>
        <div class="top">
            <p><img src="${basePath}/img/ico_qiz.png"/>当前位置：订单管理>已付款订单>待接受<span id="clocks">2017-08-01 11：29 星期二</span></p>
       <a href="${basePath}/order/orderList.shtml">全部</a>       
    <#if user.position="系统管理员"||user.position="主管">
          <a href="${basePath}/order/order_nfp.shtml">
               <#if ox.fp_count gt 0>  
                                     待分配<span class="ts">(${ox.fp_count})</span>
               <#else>待分配</#if></a>
    </#if>
       <a href="${basePath}/order/order_njs.shtml" class="curr">
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
    </div>
        <div class="down">
            <div class="search">
              <div><input value="${js_search?default('')}" placeholder="输入车主姓名或车牌号进行查询" class="search_value"/><a style="cursor:pointer" class="search_js">查询</a></div><a class="jq-search" style="cursor:pointer">精确查询<i class="ico-jt ico-jt-1"></i></a>
                 <a class="shuaxin" style="color:#000000;cursor:pointer;float:right">刷新</a>          
               <a class="shuaxin" style="cursor:pointer;float:right;margin-right: 2px">
            <img src="${basePath}/img/shuaxin.png" style=" width:20px;height:20px;"/></a>
            </div>
          <#if status="0">
             <div class="cx-result">
          <#else>
             <div class="cx-result" style="display:block">
          </#if>            
          <form  action="${basePath}/order/order_njs.shtml?status=1" method="post">
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
                    <th scope="col">操作</th>
                  </tr>
                  <#if njslist?exists && njslist?size gt 0>
               <#list njslist as order>
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
                    <td>待接受</td>
                    <td>${order.ordersource?default('')}</td>
                    <td>
                    <#if user.position="客服员">
                    <a style='cursor:pointer;width:100%;text-align:center' onclick='order_js(${order.orderid})'>接受</a>
                    </#if>
                    <a style='cursor:pointer;width:100%;text-align:center' href="${basePath}/order/order_detail.shtml?orderid=${order.orderid}">详情</a></td>
                  </tr>
                  </#list>
                <#else>
				 <tr>
			        <td class="text-center danger" colspan="15">未找到待接受订单 </td>
				</tr>
			    </#if> 
               
                </table>
                 <input style="display:none" value="${pageIndex}" class="page_i"/>
    <#if s_status==0>
     <ul class="page"  id="page">
     
       <li><a href="${basePath}/order/order_njs.shtml?pageIndex=1">首页</a></li>
       <li>
       <#if pageIndex-1=0>
            <a href="${basePath}/order/order_njs.shtml?pageIndex=1">上一页</a>
       <#else>
            <a href="${basePath}/order/order_njs.shtml?pageIndex=${pageIndex-1}">上一页</a>
       </#if> 
       </li>    
      
            <#if total<5 && total gt 0>          
              <#list 1..total as i>    
          <li id="${i}"><a href="${basePath}/order/order_njs.shtml?pageIndex=${i}">${i}</a></li>
              </#list>
             <#elseif total=0>
            <li id="1"><a href="${basePath}/order/order_njs.shtml?pageIndex=1">1</a></li>
              <#else>
                     <#if pageIndex<5>
                         <#list 1..4 as i>    
                         <li id="${i}"><a href="${basePath}/order/order_njs.shtml?pageIndex=${i}">${i}</a></li>
                         </#list>
                     <#else>
                        <#list pageIndex-3..pageIndex as i>    
                         <li id="${i}"><a href="${basePath}/order/order_njs.shtml?pageIndex=${i}">${i}</a></li>
                        </#list>
                     </#if>           
        
              </#if> 
             <li>  
               <#if pageIndex gte total>
               <a href="${basePath}/order/order_njs.shtml?pageIndex=${pageIndex}">下一页</a>
              <#else>
              <a href="${basePath}/order/order_njs.shtml?pageIndex=${pageIndex+1}">下一页</a>
             </#if>
             </li>
         <#if total=0>
          <li><a href="${basePath}/order/order_njs.shtml?pageIndex=${total+1}">末页</a></li>
          <#else>
          <li><a href="${basePath}/order/order_njs.shtml?pageIndex=${total}">末页</a></li>          
          </#if>             
          </ul>
         <#else>
           <p style="font-size:16px;color:#FF8040;text-align:center">成功为您找到${njslist?size}款符合的订单</p>
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
    $('.search_js').click(function(){
       var js_search=$('.search_value').val();   
       location.href="${basePath}/order/order_njs.shtml?js_search="+encodeURI(encodeURI(js_search));           
   });
      $('.shuaxin').click(function(){
      location.href="${basePath}/order/order_njs.shtml";
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

     //接受
   function order_js(orderid){
   var data={orderid:orderid};
       $.ajax({
		     url:"${basePath}/order/order_js.shtml",
		     data:data,			 
		     type:"post",			        		        
		     success:function(result){
	            layer.msg("接受订单进入违章查询"); 
                setTimeout(function(){window.location.href="${basePath}/order/order_njs.shtml?pageIndex="+$('.page_i').val();},2000);
	                   
	            // window.location.reload(true);
		      }
	     });
    }
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
