<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>订单管理-未支付-待分配</title>
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
<link href="${basePath}/css/page.css" type="text/css" rel="stylesheet"/>
</head>
<style>
.ts{font-size:4px;color:red}
</style>
<body>
<div class="top">        
            <p><img src="${basePath}/img/ico_qiz.png"/>当前位置：订单管理>未付款订单>待分配<span id="clocks">2017-08-01 11：29 星期二</span></p>        
             <a href="${basePath}/order/nopayAll.shtml">全部</a>
             <a href="${basePath}/order/nopay_fp.shtml" class="curr">
                             <#if nopay_counts gt 0>  
                                                                               待分配<span class="ts">(${nopay_counts})</span>
                              <#else>待分配</#if></a>
             <a href="${basePath}/order/nopaylist_js.shtml">
                              <#if nopayjs_counts gt 0>  
                                                                                待接受<span class="ts">(${nopayjs_counts})</span>
                              <#else>待接受</#if></a>              
</div>
<div class="down">
          <div class="search">
              <div><input value="${fp_search?default('')}" placeholder="输入车主姓名或车牌号进行查询" class="search_value" />
              <a style="cursor:pointer" class="search_pf">查询</a></div>
              <a class="jq-search" style="cursor:pointer">精确查询<i class="ico-jt ico-jt-1"></i></a>
              <a class="shuaxin" style="color:#000000;cursor:pointer;float:right;margin-top:10px">刷新</a>          
               <a class="shuaxin" style="cursor:pointer;float:right;margin-top:10px;margin-right: 2px">
            <img src="${basePath}/img/shuaxin.png" style=" width:20px;height:20px;"/></a>
            </div>
          <#if status="0">
             <div class="cx-result">
          <#else>
             <div class="cx-result" style="display:block">
          </#if>                
           <form  action="${basePath}/order/nopay_fp.shtml?status=1" method="post">
               <div class="choose">                           
                  <input placeholder="车主姓名" name="owner" class="owner" value="${xorder.owner?default('')}"/>
                  <input placeholder="车牌号码" name="licenseplate" class="licenseplate" value="${xorder.licenseplate?default('')}"/>
                  <input placeholder="手机号码" name="phonenumber" class="phonenumber" value="${xorder.phonenumber?default('')}"/>
                   <input class="demo-input"  value="${tj_start?default('')}" id="test1" name="tj_start" type="text" placeholder="选择提交开始时间" style="margin-right:0" /><img src="${basePath}/img/line.png"/>
                   <input class="demo-input"  value="${tj_end?default('')}" name="tj_end" placeholder="选择提交结束时间" type="text" id="test2"/>                 
                   <input class="demo-input"  value="${yy_start?default('')}" name="yy_start" type="text" placeholder="选择预约开始时间" style="margin-right:0" id="test3"/><img src="${basePath}/img/line.png"/>
                   <input class="demo-input"  value="${yy_end?default('')}" name="yy_end" placeholder="选择预约结束时间" type="text" id="test4"/>
               </div>  
               <a style="left:94%;top:0px"><input style="cursor:pointer" value="查询" type="submit" class="jq_search_ok"/></a>
              </form>
            </div>
            <div class="check"><a class="fenp  popbox-link" onclick="fenp()">分配</a>
              <a><input type="checkbox" class="b"></a>
             <a style="color:#000000;cursor:pointer" class="quanx">全选</a>            
                 
            </div>
            <table width="100%" border="0">
                  <tr>
                    <th scope="col">#</th>
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
                  <#if nopayList?exists && nopayList?size gt 0>
               <#list nopayList as order>
                 <tr>
                    <td><input type="checkbox" value="${order.orderid}" name='test' class="a"/></td>
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
                    <td></td>             
                    <td>未付款</td>
                    <td>待分配</td>
                    <td>${order.ordersource?default('')}</td>
                    <td><a style='cursor:pointer;width:100%;text-align:center' href="${basePath}/order/order_detail.shtml?orderid=${order.orderid}">详情</a></td>
                  </tr>
                  </#list>
                <#else>
				 <tr>
			        <td class="text-center danger" colspan="15">未找到待分配订单</td>
				</tr>
			    </#if> 
                </table>
             </table>
              <input style="display:none" value="${pageIndex}" class="page_i"/>
        <#if s_status==0>     
     <ul class="page"  id="page">
       <li><a href="${basePath}/order/nopay_fp.shtml?pageIndex=1">首页</a></li>
       <li>
       <#if pageIndex-1=0>
            <a href="${basePath}/order/nopay_fp.shtml?pageIndex=1">上一页</a>
       <#else>
            <a href="${basePath}/order/nopay_fp.shtml?pageIndex=${pageIndex-1}">上一页</a>
       </#if> 
       </li>    
             
              <#if total<5 && total gt 0>           
              <#list 1..total as i>    
          <li id="${i}"><a href="${basePath}/order/nopay_fp.shtml?pageIndex=${i}">${i}</a></li>
              </#list>
             <#elseif total=0>
            <li id="1"><a href="${basePath}/order/nopay_fp.shtml?pageIndex=1">1</a></li> 
              <#else>
                     <#if pageIndex<5>
                         <#list 1..4 as i>    
                         <li id="${i}"><a href="${basePath}/order/nopay_fp.shtml?pageIndex=${i}">${i}</a></li>
                         </#list>
                     <#else>
                        <#list pageIndex-3..pageIndex as i>    
                         <li id="${i}"><a href="${basePath}/order/nopay_fp.shtml?pageIndex=${i}">${i}</a></li>
                        </#list>
                     </#if>           
        
              </#if> 
             <li>  
               <#if pageIndex gte total>
               <a href="${basePath}/order/nopay_fp.shtml?pageIndex=${pageIndex}">下一页</a>
              <#else>
              <a href="${basePath}/order/nopay_fp.shtml?pageIndex=${pageIndex+1}">下一页</a>
             </#if>
             </li>
         <#if total=0>
          <li><a href="${basePath}/order/nopay_fp.shtml?pageIndex=${total+1}">末页</a></li>
          <#else>
          <li><a href="${basePath}/order/nopay_fp.shtml?pageIndex=${total}">末页</a></li>          
          </#if>  
           </ul>
                     <#else>
           <p style="font-size:16px;color:#FF8040;text-align:center">成功为您找到${nopayList?size}款符合的订单</p> 
           </#if>        
</div>
<!--分配弹出层开始-->
<div id="screen"></div>
<div class="popbox">
	<div class="mainlist">
        <h2>订单分配<a class="close-btn" style='cursor:pointer'>&times;</a></h2>
        <input class="orderidlist" style="display:none"/>
         <input class="fp_page"/>
        <p class="cc">选择客服员</p>
        <div class="kf" id="kfy">         
        </div>
        <div class="btn"><div><a class="close-btn" style='cursor:pointer'>关闭</a>
        <a class="fp_ok_btn" style='cursor:pointer;margin-left:20px'>确定</a></div></div>
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

   $('.search_pf').click(function(){
       var fp_search=$('.search_value').val();   
        location.href="${basePath}/order/nopay_fp.shtml?fp_search="+encodeURI(encodeURI(fp_search));           
   });
      $('.shuaxin').click(function(){
       location.href="${basePath}/order/nopay_fp.shtml";
   // window.location.reload(true);	             
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
     function fenp(){    
       var h = $(document).height();
		$('#screen').css({ 'height': h });	
		$('#screen').show();
		$('.popbox').center();
		$('.popbox').fadeIn();
        obj = document.getElementsByName("test");
        orderidlist = [];
         for(k in obj){
             if(obj[k].checked)
             orderidlist.push(obj[k].value);
         }
         $('.orderidlist').val(orderidlist);
         //查询客服员
           $.ajax({
		     url:"${basePath}/user/user_kfy.shtml",		   			 
		     type:"post",			        		        
		     success:function(result){
	             var ulist=result.ulist;
	               $('#kfy').empty();
	              if(ulist.length>0){
	                var str="";
	              for(var i=0;i<ulist.length;i++){       
	              str+="<a class='choose-kf'><input type='checkbox' name='c_kfy' value='"+ulist[i].id+"'>"+ulist[i].nickname+"</a>";
	                }
	              }
	              $('#kfy').append(str);
		      }
	     });         
        return false;  
   }
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
	
	//分配确定
	$('.fp_ok_btn').click(function(){
	     var orderidlist=$('.orderidlist').val();
	     obj = document.getElementsByName("c_kfy");
         var c_kfy="";
         for(k in obj){
             if(obj[k].checked)
             c_kfy+=obj[k].value+"&";
         }      
         var data={orderidlist:orderidlist,c_kfy:c_kfy};
           $.ajax({
		      url:"${basePath}/order/order_fp.shtml",
		      data:data,			 
		      type:"post",			        		        
		      success:function(result){  
		       layer.msg("分配成功");     	        
	       	   $('.popbox').fadeOut(function(){ $('#screen').hide(); });	       	        
	       	   setTimeout(function(){window.location.href="${basePath}/order/nopay_fp.shtml?pageIndex="+$('.page_i').val();},2000);
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
		if($('.ico-check').hasClass('ico-check-1')){
			$(this).find('.ico-check').removeClass('ico-check-1').addClass('ico-check-2');
		}else{
			$(this).find('.ico-check').removeClass('ico-check-2').addClass('ico-check-1');
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
<script type="text/javascript">
	$(".b").click(function(){//判断当前点击的复选框处于什么状态$(this).is(":checked") 返回的是布尔类型
         if($(this).is(":checked")){
             $(".a").prop("checked",true);
             $('.quanx').html("全不选");
         }else{
              $(".a").prop("checked",false);
              $('.quanx').html("全选");
         }
    });
    $(".quanx").click(function(){
         if($(this).html()=="全选"){
             $(".a").prop("checked",true);
             $(".b").prop("checked",true);
             $('.quanx').html("全不选");
         }else{
              $(".a").prop("checked",false);
              $(".b").prop("checked",false);
              $('.quanx').html("全选");
         }
    });
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
