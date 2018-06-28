<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>订单轨迹</title>
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
<link href="${basePath}/css/page.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<div class="top">
   <p><img src="${basePath}/img/ico_qiz.png"/>当前位置：订单管理>订单轨迹<span id="clocks">2017-08-01 11：29 星期二</span></p>
</div>
<div class="down">
           <div class="cx-result" style="display:block">
           <form  action="${basePath}/gj/gjList.shtml?s_status=1" method="post">          
              <div class="choose">
                  <input value="${orderno?default('')}" placeholder="请输入订单号" name="orderno" class="orderno" />    
                  <input value="${licenseplate?default('')}" placeholder="请输入操车牌号" name="licenseplate" class="licenseplate" />                        
                  <input value="${operator?default('')}" placeholder="请输入操作者" name="operator" class="operator" />
                  <input value="${start_time?default('')}" class="demo-input" name="start_time" id="test1"  type="text" placeholder="请选择开始时间" style="margin-right:0" /><img src="${basePath}/img/line.png"/>
                  <input value="${end_time?default('')}" class="demo-input"  name="end_time" placeholder="请选择结束时间" type="text" id="test2"/>                                  
              </div>  
               <a style="left:68%;top:0px"><input style="cursor:pointer" value="查询" type="submit"/></a>

           </div>
           </form>
  <table width="100%" border="0">
      <tr>   
         <th scope="col">订单号</th>       
         <th scope="col">车牌号</th>
         <th scope="col">变更前状态</th>
         <th scope="col">变更前状态</th>
         <th scope="col">操作者</th>
         <th scope="col">操作时间</th>                                               
      </tr>
   <#if gjList?exists && gjList?size gt 0>
   <#list gjList as gj>
      <tr>  
        <td>${gj.orderno?default('')}</td>            
        <td>${gj.licenseplate?default('')}</td>
        <#if gj.status_1=0>
        <td>待分配</td>
        <#elseif gj.status_1=1>
        <td>待接受</td>
        <#elseif gj.status_1=2>
        <td>违章查询中</td>
        <#elseif gj.status_1=3>
        <td>违章处理中</td>
        <#elseif gj.status_1=4>
        <td>资料收集中</td>
        <#elseif gj.status_1=5>
        <td>委托办理中</td>
        <#elseif gj.status_1=6>
        <td>资料已寄等待中</td>
        <#elseif gj.status_1=7>
        <td>缴费确认中</td>
        <#elseif gj.status_1=8>
        <td>预约年检中</td>
        <#elseif gj.status_1=9>
        <td>待年检</td>
        <#elseif gj.status_1=10>
        <td>年检通过</td>
        <#elseif gj.status_1=11>
        <td>年检完成</td>
        <#elseif gj.status_1=13>
        <td>未付款审批中</td>
        <#elseif gj.status_1=14>
        <td>潜在客户</td>
        <#elseif gj.status_1=15>
        <td>缴费审批中</td>
        <#else>
        <td>未知状态</td>                  
        </#if>
        
        <#if gj.status_2=0>
        <td>待分配</td>
        <#elseif gj.status_2=1>
        <td>待接受</td>
        <#elseif gj.status_2=2>
        <td>违章查询中</td>
        <#elseif gj.status_2=3>
        <td>违章处理中</td>
        <#elseif gj.status_2=4>
        <td>资料收集中</td>
        <#elseif gj.status_2=5>
        <td>委托办理中</td>
        <#elseif gj.status_2=6>
        <td>资料已寄等待中</td>
        <#elseif gj.status_2=7>
        <td>缴费确认中</td>
        <#elseif gj.status_2=8>
        <td>预约年检中</td>
        <#elseif gj.status_2=9>
        <td>待年检</td>
        <#elseif gj.status_2=10>
        <td>年检通过</td>
        <#elseif gj.status_2=11>
        <td>年检完成</td>
        <#elseif gj.status_2=13>
        <td>未付款审批中</td>
        <#elseif gj.status_2=14>
        <td>潜在客户</td>
        <#elseif gj.status_2=15>
        <td>缴费审批中</td>
        <#else>
        <td>未知状态</td>                  
        </#if>
        <td>${gj.operator?default('')}</td>
        <td>${gj.update_time?default('')}</td>
      </tr>
    </#list>
    <#else>
	<tr>
         <td class="text-center danger" colspan="6">未找到运行轨迹</td>
	</tr>
</#if>                               
    </table> 
    
    <input style="display:none" value="${pageIndex}" class="page_i"/>
   <#if s_status==0>
   <ul class="page"  id="page">  
     <li><a href="${basePath}/gj/gjList.shtml?pageIndex=1">首页</a></li>
     <li>
     <#if pageIndex-1=0>
        <a href="${basePath}/gj/gjList.shtml?pageIndex=1">上一页</a>
     <#else>
        <a href="${basePath}/gj/gjList.shtml?pageIndex=${pageIndex-1}">上一页</a>
     </#if> 
     </li>         
     <#if total<5 && total gt 0>          
           <#list 1..total as i>    
     <li id="${i}"><a href="${basePath}/gj/gjList.shtml?pageIndex=${i}">${i}</a></li>
      </#list>
     <#elseif total=0>
     <li id="1"><a href="${basePath}/gj/gjList.shtml?pageIndex=1">1</a></li>
      <#else>
     <#if pageIndex<5>
     <#list 1..4 as i>    
       <li id="${i}"><a href="${basePath}/gj/gjList.shtml?pageIndex=${i}">${i}</a></li>
       </#list>
       <#else>
       <#list pageIndex-3..pageIndex as i>    
        <li id="${i}"><a href="${basePath}/gj/gjList.shtml?pageIndex=${i}">${i}</a></li>
       </#list>
       </#if>                   
    </#if> 
      <li>  
     <#if pageIndex gte total>
     <a href="${basePath}/gj/gjList.shtml?pageIndex=${pageIndex}">下一页</a>
     <#else>
     <a href="${basePath}/gj/gjList.shtml?pageIndex=${pageIndex+1}">下一页</a>
     </#if>
      </li>
      <#if total=0>
      <li><a href="${basePath}/gj/gjList.shtml?pageIndex=${total+1}">末页</a></li>
      <#else>
       <li><a href="${basePath}/gj/gjList.shtml?pageIndex=${total}">末页</a></li>          
      </#if>             
      </ul>
      <#else>
           <p style="font-size:16px;color:#FF8040;text-align:center">成功为您找到${gjList?size}款符合的记录</p>
     </#if>                 
</div>
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
</body>
</html>
