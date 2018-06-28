<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>留言管理</title>
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
<link href="${basePath}/css/page.css" type="text/css" rel="stylesheet"/>
</head>
<body>
     <div class="top">
         <p><img src="${basePath}/img/ico_qiz.png"/>当前位置：系统管理>渠道商管理<span id="clocks">2017-08-01 11：29 星期二</span></p>
     </div>
  <div class="down">
                <div class="check"><a  class="fenp popbox-link" id="add_auto" style="cursor:pointer">添加</a></div>
              <table width="100%" border="0">
                  <tr>          
                      <th scope="col">车商代码</th>
                      <th scope="col">车商名称</th>
                     <th scope="col">修改时间</th> 
                     <th scope="col">操作者</th>                                              
                     <th scope="col" colspan='2'>操作</th>
                  </tr>
               <#if autolist?exists && autolist?size gt 0>
               <#list autolist as auto>
                  <tr>              
                    <td>${auto.auto_code?default('')}</td>
                    <td>${auto.auto_name?default('')}</td>
                    <td>${auto.update_time?default('')}</td>
                    <td>${auto.operator?default('')}</td>
                    <td><a  onclick="open_check(${auto.id?default('')})" style='cursor:pointer;width:100%;text-align:center'>修改</a></td>
                    <td><a  onclick="open_del(${auto.id?default('')})" style='cursor:pointer;width:100%;text-align:center'>删除</a></td>
                  </tr>
                </#list>
                <#else>
				<tr>
			<td class="text-center danger" colspan="6">暂时没有渠道商信息</td>
				</tr>
			</#if>                               
                </table>
         <input style="display:none" value="${pageIndex}" class="page_i"/>
     <ul class="page"  id="page">
       <li><a href="${basePath}/order/auto/autoList.shtml?pageIndex=1">首页</a></li>
       <li>
       <#if pageIndex-1=0>
            <a href="${basePath}/auto/autoList.shtml?pageIndex=1">上一页</a>
       <#else>
            <a href="${basePath}/order/auto/autoList.shtml?pageIndex=${pageIndex-1}">上一页</a>
       </#if> 
       </li>    
               
               <#if total<5 && total gt 0>            
              <#list 1..total as i>    
          <li id="${i}"><a href="${basePath}/auto/autoList.shtml?pageIndex=${i}">${i}</a></li>
              </#list>
            <#elseif total=0>
            <li id="1"><a href="${basePath}/auto/autoList.shtml?pageIndex=1">1</a></li>
              <#else>
                     <#if pageIndex<5>
                         <#list 1..4 as i>    
                         <li id="${i}"><a href="${basePath}/auto/autoList.shtml?pageIndex=${i}">${i}</a></li>
                         </#list>
                     <#else>
                        <#list pageIndex-3..pageIndex as i>    
                         <li id="${i}"><a href="${basePath}/auto/autoList.shtml?pageIndex=${i}">${i}</a></li>
                        </#list>
                     </#if>           
        
              </#if> 
             <li>  
               <#if pageIndex gte total>
               <a href="${basePath}/auto/autoList.shtml?pageIndex=${pageIndex}">下一页</a>
              <#else>
              <a href="${basePath}/auto/autoList.shtml?pageIndex=${pageIndex+1}">下一页</a>
             </#if>
             </li>
         <#if total=0>
          <li><a href="${basePath}/auto/autoList.shtml?pageIndex=${total+1}">末页</a></li>
          <#else>
          <li><a href="${basePath}/auto/autoList.shtml?pageIndex=${total}">末页</a></li>          
          </#if>             
          </ul>
    
</div>

<!--添加弹出层开始-->
<div id="screen"></div>
<div class="popbox">
	<div class="mainlist">
        <h2>添加渠道商信息<a class="close-btn" style='cursor:pointer'>&times;</a></h2>
		 <ul>
		    <li style="display:none"><input class="t_auto_id"/></li> 
			<li><span>车商代码</span><input class="auto_code"/></li>
			<li><span>车商名称</span><input class="auto_name"/></li>            
		</ul>
        <div class="btn"><div><a class="close-btn" style='cursor:pointer'>关闭</a>
        <a class="auto_ok_tn" style='cursor:pointer;margin-left:15px'>确定</a></div></div>
	</div>
</div>
<!--弹出层结束-->

<!--删除弹出层开始-->
<div id="screen"></div>
<div class="popbox1" style="width:500px">
	<div class="mainlist">
        <h2>是否删除<a class="close-btn1"  style='cursor:pointer'>&times;</a></h2>
        <input class="auto_id" style="display:none"/>
        <div class="btn" style="height:84px"><div><a class="close-btn1" style='cursor:pointer'>关闭</a>
        <a class="del-ok-btn" style='cursor:pointer;margin-left:15px'>确定</a></div></div>
	</div>
</div>
<!--弹出层结束-->
<script type="text/javascript" src="${basePath}/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${basePath}/js/page.js"></script>
<script type="text/javascript" src="${basePath}/js/common/layer/layer.js"></script>
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
    function open_del(id){
    	var h = $(document).height();
		$('#screen').css({ 'height': h });	
		$('#screen').show();
		$('.popbox1').center();
		$('.popbox1').fadeIn();
		$('.auto_id').val(id);
		return false;
    }
    function open_check(id){
    	var h = $(document).height();
		$('#screen').css({ 'height': h });	
		$('#screen').show();
		$('.popbox').center();
		$('.popbox').fadeIn();
		$('.t_auto_id').val(id);
		var data={id:id};
		  $.ajax({
			 url:"${basePath}/auto/findOneAuto.shtml",
			 data:data,
			 type:"post",			        		        
			 success:function(result){			 
	       	         var auto=result.auto;
	       	        $('.auto_code').val(auto.auto_code); 
	       	        $('.auto_name').val(auto.auto_name);       				     
			     }
	  });    
	return false;
    }
$(document).ready(function(){
   var page_i=$('.page_i').val();
   $('#'+page_i).addClass("curru");
	$('.close-btn').click(function(){
		$('.popbox').fadeOut(function(){ $('#screen').hide(); });
		return false;
	});	
	$('.close-btn1').click(function(){
		$('.popbox1').fadeOut(function(){ $('#screen').hide(); });
		return false;
	});	
	
	$('#add_auto').click(function(){
	    var h = $(document).height();
		$('#screen').css({ 'height': h });	
		$('#screen').show();
		$('.popbox').center();
		$('.popbox').fadeIn();
		$('.ly_id').val();
		return false; 
	});				
	 //添加修改
	 $('.auto_ok_tn').click(function(){		          
         var id=$('.t_auto_id').val();
         var auto_code=$('.auto_code').val();
         var auto_name=$('.auto_name').val();
         var data={auto_code:auto_code,auto_name:auto_name};
          if(id==""||id==undefined){
              $.ajax({
			    url:"${basePath}/auto/addAuto.shtml",
			    data:data,
			    type:"post",			        		        
			  	success:function(result){
				     if(result.message==1){				       				     				    
				         $('.popbox').fadeOut(function(){ $('#screen').hide(); });
				            layer.msg("添加成功");
				     }else{
				          layer.msg("添加失败");
				          $('.popbox').fadeOut(function(){ $('#screen').hide(); });
				     }
                      setTimeout(function(){window.location.href="${basePath}/auto/autoList.shtml?pageIndex="+$('.page_i').val();},2000);	       	               				     
			     },
			     error:function(e){
			        		console.log(e,e.message);
			        		layer.msg("错误");
			     }
			  })
          }else{
           var data={id:id,auto_code:auto_code,auto_name:auto_name};
             $.ajax({
			    url:"${basePath}/auto/updateAuto.shtml",
			    data:data,
			    type:"post",			        		        
			  	success:function(result){
				    if(result.message==1){				       				     				    
				         $('.popbox').fadeOut(function(){ $('#screen').hide(); });
				            layer.msg("修改成功");
				     }else{
				          layer.msg("修改失败");
				          $('.popbox').fadeOut(function(){ $('#screen').hide(); });
				     }
                      setTimeout(function(){window.location.href="${basePath}/auto/autoList.shtml?pageIndex="+$('.page_i').val();},2000);	       	               				     
			     },
			     error:function(e){
			        		console.log(e,e.message);
			        		layer.msg("错误");
			     }
			  });
          }
	 });            	 
		
	//删除
	$('.del-ok-btn').click(function(){
	        var id=$('.auto_id').val();
	        var data = {id:id};	                
			        $.ajax({
			        	url:"${basePath}/auto/deleteAuto.shtml",
			        	data:data,
			        	type:"post",			        		        
			        	success:function(result){
                         layer.msg("删除成功");		     				  
				         $('.popbox1').fadeOut(function(){ $('#screen').hide(); });				   
                      setTimeout(function(){window.location.href="${basePath}/auto/autoList.shtml?pageIndex="+$('.page_i').val();},2000);	       	               				     
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
</body>
</html>
