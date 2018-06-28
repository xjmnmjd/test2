<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>订单管理-回访</title>
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
<link href="${basePath}/css/page.css" type="text/css" rel="stylesheet"/>
</head>
<style>
.ts{font-size:4px;color:red}
</style>
<body>
<div class="top">
    <p><img src="${basePath}/img/ico_qiz.png"/>当前位置：订单管理>回访<span id="clocks">2017-08-01 11：29 星期二</span></p>             
</div>
<div class="down">
    <div class="search">
         <div><input value="${hf_search?default('')}" placeholder="输入车主姓名或车牌号进行查询" class="search_value"/><a style="cursor:pointer" class="search_hf">查询</a></div><a class="jq-search" style="cursor:pointer">精确查询<i class="ico-jt ico-jt-1"></i></a>
			<a class="shuaxin" style="color:#000000;cursor:pointer;float:right">刷新</a>          
			<a class="shuaxin" style="cursor:pointer;float:right;margin-right:2px">
			<img src="${basePath}/img/shuaxin.png" style=" width:20px;height:20px;"/></a>
		</div>
		<#if status="0">
		<div class="cx-result">
		<#else>
        <div class="cx-result" style="display:block">
        </#if>           
              <form  action="${basePath}/order/order_hf.shtml?status=1" method="post">
                 <div class="choose">                           
                    <input placeholder="车主姓名" name="owner" class="owner" value="${xorder.owner?default('')}"/>
                    <input placeholder="车牌号码" name="licenseplate" class="licenseplate" value="${xorder.licenseplate?default('')}"/>
                    <input placeholder="手机号码" name="phonenumber" class="phonenumber" value="${xorder.phonenumber?default('')}"/>
                    <input placeholder="客服员" name="customer" class="customer" value="${xorder.customer?default('')}"/>
                    <input placeholder="检测站" name="station" class="station" value="${xorder.station?default('')}"/>
                  </div> 
                   <div class="choose"> 
                    <input class="demo-input" value="${tj_start?default('')}" id="test1" name="tj_start" type="text" placeholder="选择提交开始时间" style="margin-right:0" /><img src="${basePath}/img/line.png"/>
                    <input class="demo-input" value="${tj_end?default('')}" name="tj_end" placeholder="选择提交结束时间" type="text" id="test2"/>                 
                    <input class="demo-input" value="${yy_start?default('')}" name="yy_start" type="text" placeholder="选择预约开始时间" style="margin-right:0" id="test3"/><img src="${basePath}/img/line.png"/>
                    <input class="demo-input" value="${yy_end?default('')}" name="yy_end" placeholder="选择预约结束时间" type="text" id="test4"/>
                  
                   </div> 
                     <a style="left:56%;top:60px"><input style="cursor:pointer" value="查询" type="submit" class="jq_search_ok"/></a>
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
            <#if hflist?exists && hflist?size gt 0>
          <#list hflist as order>
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
                       <#if order.suditstatus=10>                                           
                     <td>年检通过</td> 
                       <#else>
                    <td>年检完成</td>
                       </#if>            
                    <td>${order.ordersource?default('')}</td>
                        <#if order.suditstatus=10>  
                    <td><a style='cursor:pointer;width:100%;text-align:center' onclick='hf_btn(${order.orderid})'>回访</a>
                    <a style='cursor:pointer;width:100%;text-align:center' href="${basePath}/order/order_detail.shtml?orderid=${order.orderid}">详情</a></td>
                        <#else>
                    <td><a style='cursor:pointer;width:100%;text-align:center' onclick='hf_btn(${order.orderid})'>回访</a>
                    <a style='cursor:pointer;width:100%; text-align:center' onclick='hfjl_btn(${order.orderid})'>回访记录</a>
                    <a style='cursor:pointer;width:100%;text-align:center' href="${basePath}/order/order_detail.shtml?orderid=${order.orderid}">详情</a></td>
                        </#if>
                  </tr>
            </#list>
             <#else>
				 <tr>
			        <td class="text-center danger" colspan="15">未找到回访订单 </td>
				</tr>
			    </#if>
                </table>
                 <input style="display:none" value="${pageIndex}" class="page_i"/>
                  <#if s_status==0> 
     <ul class="page"  id="page">
       <li><a href="${basePath}/order/order_hf.shtml?pageIndex=1">首页</a></li>
       <li>
       <#if pageIndex-1=0>
            <a href="${basePath}/order/order_hf.shtml?pageIndex=1">上一页</a>
       <#else>
            <a href="${basePath}/order/order_hf.shtml?pageIndex=${pageIndex-1}">上一页</a>
       </#if> 
       </li>    
             
              <#if total<5 && total gt 0>           
              <#list 1..total as i>    
          <li id="${i}"><a href="${basePath}/order/order_hf.shtml?pageIndex=${i}">${i}</a></li>
              </#list>
            <#elseif total=0>
            <li id="1"><a href="${basePath}/order/order_hf.shtml?pageIndex=1">1</a></li>
              <#else>
                     <#if pageIndex<5>
                         <#list 1..4 as i>    
                         <li id="${i}"><a href="${basePath}/order/order_hf.shtml?pageIndex=${i}">${i}</a></li>
                         </#list>
                     <#else>
                        <#list pageIndex-3..pageIndex as i>    
                         <li id="${i}"><a href="${basePath}/order/order_hf.shtml?pageIndex=${i}">${i}</a></li>
                        </#list>
                     </#if>           
        
              </#if> 
             <li>  
               <#if pageIndex gte total>
               <a href="${basePath}/order/order_hf.shtml?pageIndex=${pageIndex}">下一页</a>
              <#else>
              <a href="${basePath}/order/order_hf.shtml?pageIndex=${pageIndex+1}">下一页</a>
             </#if>
             </li>
         <#if total=0>
          <li><a href="${basePath}/order/order_hf.shtml?pageIndex=${total+1}">末页</a></li>
          <#else>
          <li><a href="${basePath}/order/order_hf.shtml?pageIndex=${total}">末页</a></li>          
          </#if>  
          
           </ul>
                                          <#else>
           <p style="font-size:16px;color:#FF8040;text-align:center">成功为您找到${hflist?size}款符合的订单</p>  
           </#if>
        </div>
<!--回访弹出层开始-->
<div id="screen"></div>
<div class="popbox">
	<div class="mainlist">
        <h2>回访<a class="close-btn" style='cursor:pointer'>&times;</a></h2>
         <input class="hf_orderid" style="display:none"/>
		 <input class="hf_page" style="display:none"/>
        <p class="cc" style="font-size:12px">填写回访内容</p>
        <textarea class="text" id="hf_text" style="width:400px;margin-right:80px;height:150px"></textarea>
        <div class="btn"><div><a class="close-btn" style='cursor:pointer'>关闭</a>
        <a class="hf_ok_btn" style='cursor:pointer;margin-left:15px'>确定</a></div></div>
	</div>
</div>
<!--弹出层结束-->
<!--回访记录弹出层开始-->
<div id="screen"></div>
<div class="popbox1" style="height:90%;overflow-x:hidden;overflow-y:auto;position:absolute;top:5%">
	<div class="mainlist">
        <h2>回访记录<a class="close-btn1" style='cursor:pointer'>&times;</a></h2>
        <table width="100%" border="0" id="hf_table">
          <tr>
            <th scope="col">时间</th>
            <th scope="col">内容</th>
          </tr>
          <tr>
            <td></td>
            <td></td>
          </tr>     
        </table>
	</div>
</div>
<!--弹出层结束-->
<script type="text/javascript" src="${basePath}/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${basePath}/js/page.js"></script>
<script type="text/javascript" src="${basePath}/js/common/layer/layer.js"></script>
<script type="text/javascript">
$(document).ready(function(){
   var page_i=$('.page_i').val();
   $('#'+page_i).addClass("curru");
   
    $('.search_hf').click(function(){
       var hf_search=$('.search_value').val();   
       location.href="${basePath}/order/order_hf.shtml?hf_search="+encodeURI(encodeURI(hf_search));           
   });
      $('.shuaxin').click(function(){
      location.href="${basePath}/order/order_hf.shtml";
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
function hf_btn(orderid){
        var h = $(document).height();
        $('.hf_orderid').val(orderid);
		$('#screen').css({ 'height': h });	
		$('#screen').show();
		$('.popbox').center();
		$('.popbox').fadeIn();
		return false;
}
//回访记录
function hfjl_btn(orderid){
        var h = $(document).height();
		$('#screen').css({ 'height': h });	
		$('#screen').show();
		$('.popbox1').center();
		$('.popbox1').fadeIn();	
		var data={orderid:orderid};
		    $.ajax({
		     url:"${basePath}/order/hf_record.shtml",
		     data:data,				 
		     type:"post",			        		        
		     success:function(result){	     
		        $('#hf_table').empty();
		        var hflist=result.hflist;
		        var htmlstr="";
		        if(hflist.length>0){
		            for(var i=0;i<hflist.length;i++){
		               if(i==0){
		                  htmlstr+="<tr><th scope='col'>时间</th>"+
                          "<th scope='col'>内容</th></tr>";
		                }
		                   htmlstr+="<tr><td>"+hflist[i].hf_time+"</td>"+
                                     "<td>"+hflist[i].hf_content+"</td></tr>";		    
		            }		            
		        }else{
		            htmlstr+="<tr><th scope='col'>时间</th>"+
                          "<th scope='col'>内容</th></tr>";
		        }
		           $('#hf_table').append(htmlstr);
		     }
	     });
			
     return false;
}
$(document).ready(function(){
	$('.close-btn').click(function(){
		$('.popbox').fadeOut(function(){ $('#screen').hide(); });
		return false;
	});	
	$('.close-btn1').click(function(){
		$('.popbox1').fadeOut(function(){ $('#screen').hide(); });
		return false;
	});	
	//回访确定
    $('.hf_ok_btn').click(function(){
       var orderid=$('.hf_orderid').val();
       var content=$('#hf_text').val();
          if(content==""||content==undefined){ 
           alert("回访内容不能为空");   
          return false;
       }
        var data={orderid:orderid,content:content};
           $.ajax({
		       url:"${basePath}/order/hfContent.shtml",
		       data:data,			 
		       type:"post",			        		        
		       success:function(result){
		          layer.msg("回访完成");
	       	      $('.popbox').fadeOut(function(){ $('#screen').hide(); });
	       	      setTimeout(function(){window.location.href="${basePath}/order/order_hf.shtml?pageIndex="+$('.page_i').val();},1000);
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
