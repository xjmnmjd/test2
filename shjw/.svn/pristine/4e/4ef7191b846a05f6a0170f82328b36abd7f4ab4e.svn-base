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
            <p><img src="${basePath}/img/ico_qiz.png"/>当前位置：订单管理>当月新进订单<span id="clocks">2017-08-01 11：29 星期二</span></p>
                <a style="width:120px"   href="${basePath}/order/sp_order.shtml"><#if sp_count gt 0>超权限审批<span class="ts">(${sp_count})</span>
            <#else>超权限审批</#if></a>
            <a class="curr" style="width:120px">当月新进订单</a> 
            <a href="${basePath}/order/account_list.shtml" style="width:120px">已结算订单</a> 
    </div>
        <div class="down">
            <div class="search">
              <div><input value="${m_search?default('')}" placeholder="输入车主姓名或车牌号进行查询" class="search_value"/><a style="cursor:pointer" class="search_js">查询</a></div><a class="jq-search" style="cursor:pointer">精确查询<i class="ico-jt ico-jt-1"></i></a>
                 <a class="shuaxin" style="color:#000000;cursor:pointer;float:right">刷新</a>          
               <a class="shuaxin" style="cursor:pointer;float:right;margin-right: 2px">
            <img src="${basePath}/img/shuaxin.png" style=" width:20px;height:20px;"/></a>
            </div>
          <#if status="0">
             <div class="cx-result">
          <#else>
             <div class="cx-result" style="display:block">
          </#if>            
          <form  action="${basePath}/order/month_list.shtml?status=1" method="post">
               <div class="choose">                           
                  <input placeholder="车主姓名" name="owner" class="owner" value="${xorder.owner?default('')}"/>
                  <input placeholder="车牌号码" name="licenseplate" class="licenseplate" value="${xorder.licenseplate?default('')}"/>
                  <input placeholder="手机号码" name="phonenumber" class="phonenumber" value="${xorder.phonenumber?default('')}"/>
  </div>  
               <a style="left:40%;top:0px"><input style="cursor:pointer" value="查询" type="submit" class="jq_search_ok"/></a>
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
                  <#if month_list?exists &&month_list?size gt 0>
               <#list month_list as order>
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
                       <#else>
                       <td>未知状态</td>
                       </#if>
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
                    <#elseif order.suditstatus=12>
                    <td>未付款已接受</td>
                    <#elseif order.suditstatus=13>
                    <td>超权限审批中</td>
                    <#elseif order.suditstatus=14>
                    <td>潜在客户</td>
                    <#else>
                    <td>未设置</td>                  
                    </#if>
                    <td>${order.ordersource?default('')}</td>
                    <td>
                    <a style='cursor:pointer;width:100%;text-align:center' href="${basePath}/order/order_detail.shtml?orderid=${order.orderid}">详情</a></td>
                  </tr>
                  </#list>
                <#else>
				 <tr>
			        <td class="text-center danger" colspan="15">未找到当月订单 </td>
				</tr>
			    </#if> 
               
                </table>
                 <input style="display:none" value="${pageIndex}" class="page_i"/>
    <#if s_status==0>
     <ul class="page"  id="page">
     
       <li><a href="${basePath}/order/month_list.shtml?pageIndex=1">首页</a></li>
       <li>
       <#if pageIndex-1=0>
            <a href="${basePath}/order/month_list.shtml?pageIndex=1">上一页</a>
       <#else>
            <a href="${basePath}/order/month_list.shtml?pageIndex=${pageIndex-1}">上一页</a>
       </#if> 
       </li>    
      
            <#if total<5 && total gt 0>          
              <#list 1..total as i>    
          <li id="${i}"><a href="${basePath}/order/month_list.shtml?pageIndex=${i}">${i}</a></li>
              </#list>
             <#elseif total=0>
            <li id="1"><a href="${basePath}/order/month_list.shtml?pageIndex=1">1</a></li>
              <#else>
                     <#if pageIndex<5>
                         <#list 1..4 as i>    
                         <li id="${i}"><a href="${basePath}/order/month_list.shtml?pageIndex=${i}">${i}</a></li>
                         </#list>
                     <#else>
                        <#list pageIndex-3..pageIndex as i>    
                         <li id="${i}"><a href="${basePath}/order/month_list.shtml?pageIndex=${i}">${i}</a></li>
                        </#list>
                     </#if>           
        
              </#if> 
             <li>  
               <#if pageIndex gte total>
               <a href="${basePath}/order/month_list.shtml?pageIndex=${pageIndex}">下一页</a>
              <#else>
              <a href="${basePath}/order/month_list.shtml?pageIndex=${pageIndex+1}">下一页</a>
             </#if>
             </li>
         <#if total=0>
          <li><a href="${basePath}/order/month_list.shtml?pageIndex=${total+1}">末页</a></li>
          <#else>
          <li><a href="${basePath}/order/month_list.shtml?pageIndex=${total}">末页</a></li>          
          </#if>             
          </ul>
         <#else>
           <p style="font-size:16px;color:#FF8040;text-align:center">成功为您找到${month_list?size}款符合的订单</p>
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
       var m_search=$('.search_value').val();   
       location.href="${basePath}/order/month_list.shtml?m_search="+encodeURI(encodeURI(m_search));           
   });
      $('.shuaxin').click(function(){
      location.href="${basePath}/order/month_list.shtml";
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
                setTimeout(function(){window.location.href="${basePath}/order/order_njs.shtml";},2000);
	                   
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
