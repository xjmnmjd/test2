<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
<link href="${basePath}/css/page.css" type="text/css" rel="stylesheet"/>
<title>信息管理</title>
</head>

<body>
<div class="top">
            <p><img src="${basePath}/img/ico_qiz.png"/>当前位置：短信管理>短信记录<span id="clocks">2017-08-01 11：29 星期二</span></p>
            <a class="curr">短信记录</a>
        </div>
<div class="down">
  <div class="search">
    <div><input value="${search?default('')}" placeholder="输入姓名或者手机号查询" class="search_v"/><a style='cursor:pointer' class="p_search">查询</a></div><a class="jq-search" a style='cursor:pointer' class="p_search">精确查询<i class="ico-jt ico-jt-1"></i></a>
  </div>
          <#if status=0>
             <div class="cx-result">
          <#else>
             <div class="cx-result" style="display:block">
          </#if>
  <form action="${basePath}/mess/messList.shtml?status=1" method="post">
      <div class="choose">
          <input placeholder="姓名" name="message_owner" value="${message.message_owner?default('')}"/>
          <input placeholder="手机号码" name="message_phone" value="${message.message_phone?default('')}"/>
          <input class="demo-input" id="test1" placeholder="选择发送开始时间" name="start_time" value="${start_time?default('')}"/>
          <input class="demo-input" id="test2" placeholder="选择发送结束时间" name="end_time" value="${end_time?default('')}"/>
          <input placeholder="车牌号码" name="message_plate" value="${message.message_plate?default('')}"/>
      </div>
      <a style="left:65%;top:0"><input value="查询" type="submit"/></a>
      </form>
  </div>
  <div class="check"><a class="daoc" style="cursor:pointer">导出EXCEL</a></div>
  <table width="100%" border="0">
        <tr>
          <th scope="col">发送时间</th>
          <th scope="col">手机号码</th>
          <th scope="col">车主</th>
          <th scope="col">车牌</th>
          <th scope="col" style="width:35%; padding:0 6px;">短信内容</th>
          <th scope="col">操作者</th>
          <th scope="col">发送状态</th>
          <th scope="col">操作</th>
        </tr>
           <#if messlist?exists && messlist?size gt 0>
             <#list messlist as mess>
                 <tr>
                    <td>${mess.message_time?default('未设置')}</td>
                    <td>${mess.message_phone?default('未设置')}</td>
                    <td>${mess.message_owner?default('未设置')}</td>
                    <td>${mess.message_plate?default('')}</td>
                    <td>${mess.message_content?default('')}</td>
                    <#if mess.message_status==0>
                    <td>未发送</td>
                    <#else>
                    <td>已发送</td>
                    </#if>
                    <td>${mess.message_operator?default('')}</td>
                    <td ><a style='cursor:pointer;width:100%;text-align:center' onclick="delete_mess(${mess.messid})">删除</a></td>                 
                  </tr>
            </#list>
             <#else>
				 <tr>
			        <td class="text-center danger" colspan="8">未找到短信</td>
				</tr>
			</#if> 
            </table>
         </table>
         <input style="display:none" value="${pageIndex}" class="page_i"/>
    <ul class="page"  id="page">
          <li><a href="${basePath}/mess/messList.shtml?pageIndex=1">首页</a><li>
          <li>
              <#if pageIndex-1=0>
              <a href="${basePath}/mess/messList.shtml?pageIndex=1">上一页</a>
              <#else>
              <a href="${basePath}/mess/messList.shtml?pageIndex=${pageIndex-1}">上一页</a>
              </#if>
          </li>              
               
               <#if total<5 && total gt 0>            
              <#list 1..total as i>    
          <li id="${i}"><a href="${basePath}/mess/messList.shtml?pageIndex=${i}">${i}</a></li>
              </#list>
             <#elseif total=0>
            <li id="1"><a href="${basePath}/mess/messList.shtml?pageIndex=1">1</a></li>
              <#else>
                     <#if pageIndex<6>
                         <#list 1..5 as i>    
                         <li id="${i}"><a href="${basePath}/mess/messList.shtml?pageIndex=${i}">${i}</a></li>
                         </#list>
                     <#else>
                        <#list pageIndex-4..pageIndex as i>    
                         <li id="${i}"><a href="${basePath}/mess/messList.shtml?pageIndex=${i}">${i}</a></li>
                        </#list>
                     </#if>           
        
              </#if> 
          <li>                    
              <#if pageIndex gte total>
         <a href="${basePath}/mess/messList.shtml?pageIndex=${pageIndex}">下一页</a>
              <#else>
        <a href="${basePath}/mess/messList.shtml?pageIndex=${pageIndex+1}">下一页</a>
             </#if>
         </li>
         <#if total=0>
          <li><a href="${basePath}/mess/messList.shtml?pageIndex=${total+1}">末页</a></li>
          <#else>
          <li><a href="${basePath}/mess/messList.shtml?pageIndex=${total}">末页</a></li>          
          </#if>    
       </ul>  
</div>
<!--弹出层开始-->
<div id="screen"></div>
<div class="popbox">
	<div class="mainlist">
        <h2>重新发送<a class="close-btn">&times;</a></h2>
		<ul>
            <li><span>手机号码</span><input style="background-color:#f0f0f0"/></li>
            <li><span>内容</span><textarea></textarea></li>
		</ul>
        <div class="btn"><div><a class="close-btn" style="cursor:pointer">关闭</a>
        <a style="cursor:pointer;margin-left:15px">确定</a></div></div>
	</div>
</div>
<!--弹出层结束-->
<!--删除弹出层开始-->
<div id="screen"></div>
<div class="popbox1" style="width:500px">
	<div class="mainlist">
	   <input class="de_messid" style="display:none"/>
        <h2>是否删除<a class="close-btn1" style="cursor:pointer">&times;</a></h2>
        <div class="btn" style="height:84px"><div><a class="close-btn1" style="cursor:pointer">关闭</a>
        <a class="del_btn" style="cursor:pointer;margin-left:15px">确定</a></div></div>
	</div>
</div>
<!--弹出层结束-->

<script type="text/javascript" src="${basePath}/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${basePath}/js/page.js"></script>
<script type="text/javascript">
   $(document).ready(function(){
      var page_i=$('.page_i').val();
   $('#'+page_i).addClass("curru");
   //导出
   $('.daoc').click(function(){
    location.href="${basePath}/mess/mess_daoc.shtml"    
   });
     $('.p_search').click(function(){
       var me_search=$('.search_v').val();
        location.href="${basePath}/mess/messList.shtml?me_search="+encodeURI(encodeURI(me_search));              
      });
   });
   $('.del_btn').click(function(){
      var messid=$('.de_messid').val();
      var data={messid:messid};
      $.ajax({
	    url:"${basePath}/mess/delete_Mess.shtml",
	    data:data,
	    type:"post",			        		        
	    success:function(result){	      			      			        			       				     				    
		  $('.popbox1').fadeOut(function(){ $('#screen').hide(); });			        
		  window.location.reload(true);
	    }			     
	   }); 
   });
   function delete_mess(messid){
     var h = $(document).height();
		$('#screen').css({ 'height': h });	
		$('#screen').show();
		$('.popbox1').center();
		$('.popbox1').fadeIn();
		$('.de_messid').val(messid);
		return false;
   }
   
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
<script type="text/javascript" src="${basePath}/js/layer.js"></script>
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
