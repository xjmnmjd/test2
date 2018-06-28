<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>操作记录</title>
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
<link href="${basePath}/css/page.css" type="text/css" rel="stylesheet"/>
</head>
<body>
     <div class="top">
         <p><img src="${basePath}/img/ico_qiz.png"/>当前位置：系统管理>操作记录<span id="clocks">2017-08-01 11：29 星期二</span></p>
     </div>
  <div class="down">
           <div class="cx-result" style="display:block">
           <form  action="${basePath}/cr/listRecord.shtml?status=1" method="post">          
              <div class="choose">                           
                  <input value="${operator?default('')}" placeholder="请输入操作者" name="operator" class="operator" />
                  <input value="${start_time?default('')}" class="demo-input" name="start_time" id="test1"  type="text" placeholder="请选择开始时间" style="margin-right:0" /><img src="${basePath}/img/line.png"/>
                  <input value="${end_time?default('')}" class="demo-input"  name="end_time" placeholder="请选择结束时间" type="text" id="test2"/>                                  
              </div>  
               <a style="left:42%;top:0px"><input style="cursor:pointer" value="查询" type="submit"/></a>

           </div>
           </form>
       <table width="100%" border="0">
                  <tr>          
                     <th scope="col">操作类型</th>
                      <th scope="col">操作理由</th>
                     <th scope="col">操作时间</th>
                      <th scope="col">操作者</th>
                      <th scope="col">车牌号</th>
                  </tr>
               <#if listRecord?exists && listRecord?size gt 0>
               <#list listRecord as cr>
                  <tr>   
                    <#if cr.changeType==1>
                       <td>删除订单</td>
                    <#elseif cr.changeType==2>
                       <td>删除应付总额 </td>
                    <#elseif cr.changeType==3>
                       <td>删除线下支付</td>
                     <#elseif cr.changeType==4>
                       <td>删除支出</td>
                    <#elseif cr.changeType==5>
                       <td>修改线下支付</td>
                    <#elseif cr.changeType==6>
                       <td>修改支出</td>
                    <#elseif cr.changeType==7>
                       <td>修改应付总额</td>
                     <#else>
                       <td>无操作</td>                                                                                                                                        
                    </#if>           
                    <td>${cr.changeCause?default('')}</td>
                    <td>${cr.update_time?default('')}</td>
                    <td>${cr.operator?default('')}</td>
                    <td>${cr.licenseplate?default('')}</td>
                  </tr>
                </#list>
                <#else>
				<tr>
			<td class="text-center danger" colspan="5">暂时没有留言</td>
				</tr>
			</#if>                               
       </table>
                    <input style="display:none" value="${pageIndex}" class="page_i"/>
    <#if s_status==0>
     <ul class="page"  id="page">
       <li><a href="${basePath}/cr/listRecord.shtml?pageIndex=1">首页</a></li>
       <li>
       <#if pageIndex-1=0>
            <a href="${basePath}/cr/listRecord.shtml?pageIndex=1">上一页</a>
       <#else>
            <a href="${basePath}/cr/listRecord.shtml?pageIndex=${pageIndex-1}">上一页</a>
       </#if> 
       </li>    
               
               <#if total<5 && total gt 0>            
              <#list 1..total as i>    
          <li id="${i}"><a href="${basePath}/cr/listRecord.shtml?pageIndex=${i}">${i}</a></li>
              </#list>
            <#elseif total=0>
            <li id="1"><a href="${basePath}/cr/listRecord.shtml?pageIndex=1">1</a></li>
              <#else>
                     <#if pageIndex<5>
                         <#list 1..4 as i>    
                         <li id="${i}"><a href="${basePath}/cr/listRecord.shtml?pageIndex=${i}">${i}</a></li>
                         </#list>
                     <#else>
                        <#list pageIndex-3..pageIndex as i>    
                         <li id="${i}"><a href="${basePath}/cr/listRecord.shtml?pageIndex=${i}">${i}</a></li>
                        </#list>
                     </#if>           
        
              </#if> 
             <li>  
               <#if pageIndex gte total>
               <a href="${basePath}/cr/listRecord.shtml?pageIndex=${pageIndex}">下一页</a>
              <#else>
              <a href="${basePath}/cr/listRecord.shtml?pageIndex=${pageIndex+1}">下一页</a>
             </#if>
             </li>
         <#if total=0>
          <li><a href="${basePath}/cr/listRecord.shtml?pageIndex=${total+1}">末页</a></li>
          <#else>
          <li><a href="${basePath}/cr/listRecord.shtml?pageIndex=${total}">末页</a></li>          
          </#if>             
          </ul>
          <#else>
           <p style="font-size:16px;color:#FF8040;text-align:center">成功为您找到${listRecord?size}款符合的订单</p>
          </#if>   
</div>

<!--弹出层开始-->
<div id="screen"></div>
<div class="popbox">
	<div class="mainlist">
        <h2>处理情况<a class="close-btn" style='cursor:pointer'>&times;</a></h2>
         <input class="ly_id" style="display:none"/>
        <a class="choose-kf wz" style="margin-top:40px" ><i class="ico-radio ico-radio-1" id="ycl"></i>已处理</a>
        <a class="choose-kf wz" style="margin-bottom:40px" ><i class="ico-radio ico-radio-1" id="wcl"></i>未处理</a>
        <div class="btn"><div><a class="close-btn" style='cursor:pointer'>关闭</a>
        <a class="ly_cx_ok" style='cursor:pointer;margin-left:15px'>确定</a></div></div>
	</div>
</div>
<!--弹出层结束-->

<!--删除弹出层开始-->
<div id="screen"></div>
<div class="popbox1" style="width:500px">
	<div class="mainlist">
        <h2>是否删除<a class="close-btn1"  style='cursor:pointer'>&times;</a></h2>
        <input class="dly_id" style="display:none"/>
        <div class="btn" style="height:84px"><div><a class="close-btn1" style='cursor:pointer'>关闭</a>
        <a class="del-ok-btn" style='cursor:pointer;margin-left:15px'>确定</a></div></div>
	</div>
</div>
<!--弹出层结束-->
<script type="text/javascript" src="${basePath}/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${basePath}/js/page.js"></script>


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
</script >
<script type="text/javascript">


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

$(function(){
    var page_i=$('.page_i').val();
    $('#'+page_i).addClass("curru");
    
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
</body>
</html>
