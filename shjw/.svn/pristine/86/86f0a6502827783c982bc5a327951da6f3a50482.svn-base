<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
<link href="${basePath}/css/page.css" type="text/css" rel="stylesheet"/>
<script type="text/javascript" src="${basePath}/js/jedate.js"></script>
<title>财务管理</title>
</head>

<body>
<div class="top">
  <p><img src="${basePath}/img/ico_qiz.png"/>当前位置：财务管理<span id="clocks">2017-08-01 11：29 星期二</span></p>
  <a class="curr">财务管理</a>
</div>
<div class="down">
  <div class="search">
        <div><input value="${search?default('')}" placeholder="输入姓名或者车牌号码查询" class="search_v"/><a style='cursor:pointer' class="p_search">查询</a></div><a class="jq-search" style='cursor:pointer;'>精确查询<i class="ico-jt ico-jt-1"></i></a>
      
                  <a class="shuaxin" style="color:#000000;cursor:pointer;float:right">刷新</a>          
 <a class="shuaxin" style="cursor:pointer;float:right;margin-right: 2px">
            <img src="${basePath}/img/shuaxin.png" style=" width:20px;height:20px;"/></a>
       </div>
          <#if status="0">
             <div class="cx-result">
          <#else>
             <div class="cx-result" style="display:block">
          </#if> 
       <form action="${basePath}/order/fin.shtml?status=1" method="post">        
        <div class="choose">
          <input placeholder="姓名" class="owner" name="owner" value="${h_order.owner?default('')}"/>
          <input placeholder="车牌号码" class="licenseplate" name="licenseplate" value="${h_order.licenseplate?default('')}"/>
          <input placeholder="手机号码" class="phonenumber" name="phonenumber" value="${h_order.phonenumber?default('')}"/>
          <input style="width:14%" type="text" placeholder="付款开始时间" name="pay_start"  class="pay_start" id="dateinfo1" value="${pay_start?default('')}"/>
          <input style="width:14%" type="text" placeholder="付款结束时间" name="pay_end" class="pay_end" id="dateinfo2"   value="${pay_end?default('')}"/>          
        </div>   
          <a style="left:69%;margin-top:-30px"><input value="查询" type="submit"/></a>
        </form>
 </div>
  <div class="check">
  <a class="daoc" style="cursor:pointer">导出EXCEL</a>
  <a class="daor" style="cursor:pointer" id="dao_bb">导出详细报表</a>
  <p>当日总利润：
  <span><#if day_all_money??>
        ${day_all_money}
        <#else>
        0.00
        </#if>
  </span>元
  <a>下载报表</a>|&nbsp;&nbsp;当月总利润：<span>
<#if month_all_money??>
        ${month_all_money}
        <#else>
        0.00
        </#if>
</span>元<a>下载报表</a>
<#if lr!=0>
<span style="color:red;margin-left:10px">${pay_start}到${pay_end}时间内总利润为：${lr}元</span>
</#if>
</p>
</div>
  <table width="100%" border="0">
        <tr>
          <th scope="col">车主</th>
          <th scope="col">车牌</th>
          <th scope="col">初始订单总额(元)</th>
          <th scope="col">订单总额(元)</th>
          <th scope="col">实际支付</th>
          <th scope="col">线上支付</th>
          <th scope="col">线下支付</th>
          <th scope="col">支出</th>
          <th scope="col">缴费时间</th>
          <th scope="col">开具发票</th>
          <th scope="col" colspan='2'>操作</th>
        </tr>
           <#if finlist?exists && finlist?size gt 0>
             <#list finlist as fin>
                 <tr>
                    <td>${fin.owner?default('')}</td>
                    <td>${fin.licenseplate?default('')}</td>
                    <td>${fin.totalorder?default('0.00')}</td>
                    <td>${fin.copetotal?default('0.00')}</td>
                    <td>${fin.payment_amount?default('0.00')}</td>
                    <td>${fin.cash_fee?default('0.00')?number/100}</td>
                  
                    <td>${fin.offlinepayment?default('0.00')}</td>
                    <td>${fin.pay?default('0.00')}</td>
                    <td>${fin.fk_time?default('')}</td>
                        <#if fin.is_bill==0>
                          <td>否</td>
                       <#elseif fin.is_bill==1>
                          <td><a style='cursor:pointer;width:100%;text-align:center' onclick="look_bill('${fin.orderno?default('')}')">查看</a></td>    
                       <#else>
                         <td>空</td> 
                       </#if> 
                  <td><a style='cursor:pointer;width:100%;text-align:center' href="${basePath}/order/order_detail.shtml?orderid=${fin.orderid}">详情</a>               
                  </td> 
                         <td>                         
                            <#if fin.is_kd==0>                                              
                              <a style='cursor:pointer;width:100%;text-align:center' onclick="kp(${fin.orderid})">添加运单号</a》                       
                            <#else>
                              <a style='cursor:pointer;width:100%;text-align:center' onclick="chakan(${fin.orderid})">查看运单号</a》                       
                            </#if>
                          </td>
                  </tr>
            </#list>
             <#else>
				 <tr>
			        <td class="text-center danger" colspan="12">未找到缴费订单</td>
				</tr>
			</#if> 
            </table>
         </table>
         <input style="display:none" value="${pageIndex}" class="page_i"/>
     <#if s_status==0>  
 <ul class="page"  id="page">
          <li><a href="${basePath}/order/fin.shtml?pageIndex=1">首页</a><li>
          <li>
              <#if pageIndex-1=0>
              <a href="${basePath}/order/fin.shtml?pageIndex=1">上一页</a>
              <#else>
              <a href="${basePath}/order/fin.shtml?pageIndex=${pageIndex-1}">上一页</a>
              </#if>
          </li>              
               
               <#if total<5 && total gt 0>            
              <#list 1..total as i>    
          <li id="${i}"><a href="${basePath}/order/fin.shtml?pageIndex=${i}">${i}</a></li>
              </#list>
             <#elseif total=0>
            <li id="1"><a href="${basePath}/order/fin.shtml?pageIndex=1">1</a></li>
              <#else>
                     <#if pageIndex<6>
                         <#list 1..5 as i>    
                         <li id="${i}"><a href="${basePath}/order/fin.shtml?pageIndex=${i}">${i}</a></li>
                         </#list>
                     <#else>
                        <#list pageIndex-4..pageIndex as i>    
                         <li id="${i}"><a href="${basePath}/order/fin.shtml?pageIndex=${i}">${i}</a></li>
                        </#list>
                     </#if>           
        
              </#if> 
          <li>                    
              <#if pageIndex gte total>
         <a href="${basePath}/order/fin.shtml?pageIndex=${pageIndex}">下一页</a>
              <#else>
        <a href="${basePath}/order/fin.shtml?pageIndex=${pageIndex+1}">下一页</a>
             </#if>
         </li>
         <#if total=0>
          <li><a href="${basePath}/order/fin.shtml?pageIndex=${total+1}">末页</a></li>
          <#else>
          <li><a href="${basePath}/order/fin.shtml?pageIndex=${total}">末页</a></li>          
          </#if>     </ul>
          
                                                  <#else>
           <p style="font-size:16px;color:#FF8040;text-align:center">成功为您找到${finlist?size}款符合的订单</p> 
          </#if> 
</div>

<!--弹出层开始-->
<div id="screen"></div>
<div class="popbox">
	<div class="mainlist">
        <h2>快递信息<a class="close-btn" style='cursor:pointer'>&times;</a></h2>     
		 <ul>
		    <li style="display:none"><input class="kd_id"/></li>
			<li><span>运单号:</span><input type="text"  class="kd_no"/></li> 
			<li><span>快递公司:</span><input type="text" class="kd_company"/></li>
			<li style="display:none"><input class="order_id"/></li> 
		</ul>
        <div class="btn"><div><a class="close-btn" style='cursor:pointer'>关闭</a>
        <a class="kd_ok_btn" style='cursor:pointer;margin-left:15px'>确定</a></div></div>
      
	</div>
</div>
<!--弹出层结束-->

<!--发票弹出层开始-->
<div id="screen"></div>
<div class="popbox2">
	<div class="mainlist">
        <h2>发票记录<a class="close-btn2" style='cursor:pointer'>&times;</a></h2>
        <ul>
           <li><span>发票类型:</span><em class="bill_type" style="background-color:#E8E8E8;padding: 2px;margin-left:20px;">空</em></li>
           <li><span>发票抬头:</span><em class="bill_head" style="background-color:#E8E8E8;padding: 2px;margin-left:20px;">空</em></li>
           <li><span>纳税人识别号:</span><em class="taxpayer_identification_number"  style="background-color:#E8E8E8;padding: 2px;margin-left:20px;">空</em></li>
           <li><span>寄送地址:</span><em class="mailing_address" style="background-color:#E8E8E8;padding: 2px;margin-left:20px;">空</em></li>
           <li><span>联系电话:</span><em class="contact_number" style="background-color:#E8E8E8;padding: 2px;margin-left:20px;">空</em></li>
           </ul>               
	</div>
</div>
<!--弹出层结束-->

<script type="text/javascript" src="${basePath}/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${basePath}/js/page.js"></script>
<script type="text/javascript">
//添加运单弹出
   function kp(orderid){
      	var h = $(document).height();
		$('#screen').css({ 'height': h });	
		$('#screen').show();
		$('.popbox').center();
		$('.popbox').fadeIn();
		$('.order_id').val(orderid);
		$('.kd_id').val("");
		$('.kd_no').val("");
		$('.kd_company').val("");  
	 return false;
   }
   
   //发票弹出
   function look_bill(orderno){
      	var h = $(document).height();
		$('#screen').css({ 'height': h });	
		$('#screen').show();
		$('.popbox2').center();
		$('.popbox2').fadeIn();	
		var data={orderno:orderno};
		    $.ajax({
	           url:"${basePath}/order/find_bill.shtml",
		       data:data,
		       type:"post",			        		        
			      success:function(result){
			         if(result.bill!=null){
			          var bill=result.bill;
			   		$('.bill_type').html(bill.bill_type);
			   		$('.bill_head').html(bill.bill_head);
			   		$('.taxpayer_identification_number').html(bill.taxpayer_identification_number);
			   		$('.mailing_address').html(bill.mailing_address);
			   		$('.contact_number').html(bill.contact_number);
			   		}		        									       				     				    		
			      }      
	         });
		 
	 return false;
   }
   //查看弹出
   function chakan(orderid){
      	var h = $(document).height();
		$('#screen').css({ 'height': h });	
		$('#screen').show();
		$('.popbox').center();
		$('.popbox').fadeIn();
		$('.order_id').val(orderid);
		var data={orderid:orderid}; 
		      $.ajax({
	           url:"${basePath}/kd/find_kd.shtml",
		       data:data,
		       type:"post",			        		        
			      success:function(result){
			   		$('.kd_id').val(result.kd.kdid);
			   		$('.kd_no').val(result.kd.kd_no);
			   		$('.kd_company').val(result.kd.kd_company);	        									       				     				    		
			      }      
	         }); 
	 return false;
   }
  

//分页样式js
$(document).ready(function(){
   var page_i=$('.page_i').val();
   $('#'+page_i).addClass("curru");
   
   $('.shuaxin').click(function(){
    location.href="${basePath}/order/fin.shtml";
    //window.location.reload(true);	             
   });
//导出总报表
  $('.daoc').click(function(){
   var owner=$('.owner').val();
   var licenseplate=$('.licenseplate').val();
   var phonenumber=$('.phonenumber').val();
   var test1=$('.pay_start').val();
   var test2=$('.pay_end').val();
   var search_v=$('.search_v').val();
    location.href="${basePath}/order/fin_to.shtml?search_v="+encodeURI(encodeURI(search_v))+
    "&owner="+encodeURI(encodeURI(owner))+
    "&licenseplate="+encodeURI(encodeURI(licenseplate))+
    "&phonenumber="+encodeURI(encodeURI(phonenumber))+
    "&pay_start="+encodeURI(encodeURI(test1))+
    "&pay_end="+encodeURI(encodeURI(test2));    
   });
  //导出总报表
  $('#dao_bb').click(function(){
   var owner=$('.owner').val();
   var licenseplate=$('.licenseplate').val();
   var phonenumber=$('.phonenumber').val();
   var test1=$('.pay_start').val();
   var test2=$('.pay_end').val();
   var search_v=$('.search_v').val();
    location.href="${basePath}/order/fin_to_bb.shtml?search_v="+encodeURI(encodeURI(search_v))+
    "&owner="+encodeURI(encodeURI(owner))+
    "&licenseplate="+encodeURI(encodeURI(licenseplate))+
    "&phonenumber="+encodeURI(encodeURI(phonenumber))+
    "&pay_start="+encodeURI(encodeURI(test1))+
    "&pay_end="+encodeURI(encodeURI(test2));    
   });
   $('.p_search').click(function(){
    var search=$('.search_v').val();
    location.href="${basePath}/order/fin.shtml?search="+encodeURI(encodeURI(search));           
    
   });
  
  	$('.close-btn').click(function(){
		$('.popbox').fadeOut(function(){ $('#screen').hide(); });
		return false;
	});
	 $('.close-btn2').click(function(){
		$('.popbox2').fadeOut(function(){ $('#screen').hide(); });
		return false;
	});
    $('.kd_ok_btn').click(function(){
         var kdid=$('.kd_id').val();
         var orderid=$('.order_id').val();
         var kd_no=$('.kd_no').val();
         var kd_company=$('.kd_company').val();
         var data={orderid:orderid,kd_no:kd_no,kd_company:kd_company};
         if(kdid==""||kdid=="undefined"){
               $.ajax({
	              url:"${basePath}/kd/addkd_no.shtml",
		          data:data,
		          type:"post",			        		        
			        success:function(result){
			            $('.popbox').fadeOut(function(){ $('#screen').hide(); });
	                   setTimeout(function(){window.location.href="${basePath}/order/fin.shtml?pageIndex="+$('.page_i').val();},2000);	
			         }       
	            }); 
	       }else{
	          var data1={kdid:kdid,orderid:orderid,kd_no:kd_no,kd_company:kd_company};
	              $.ajax({
	                 url:"${basePath}/kd/update_kd.shtml",
		             data:data1,
		             type:"post",			        		        
			         success:function(result){
			         $('.popbox').fadeOut(function(){ $('#screen').hide(); });
	                   setTimeout(function(){window.location.href="${basePath}/order/fin.shtml?pageIndex="+$('.page_i').val();},2000);	
			          }       
	              }); 
	       }	      
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

</script>
<script type="text/javascript">
    jeDate({
        dateCell:"#dateinfo1",
        format:"YYYY-MM-DD hh:mm:ss",
        isinitVal:false,
        isTime:true, //isClear:false,
        festival:true, //是否显示节日       
        okfun:function(val){      
        }
    });
     jeDate({
        dateCell:"#dateinfo2",    
        format:"YYYY-MM-DD hh:mm:ss",
        isinitVal:false,
        isTime:true, //isClear:false,
        festival:true, //是否显示节日
        okfun:function(val){
        
        }
    });
    function getNowFormatDate() {
    var date = new Date();
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
            + " " + date.getHours() + seperator2 + date.getMinutes()
            + seperator2 + date.getSeconds();
    return currentdate;
};
</script>
</body>
</html>
