<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
<link href="${basePath}/css/page.css" type="text/css" rel="stylesheet"/>
<title>车辆性质</title>
</head>

<body>
<div class="top">
  <p><img src="${basePath}/img/ico_qiz.png"/>当前位置：系统管理>车辆性质<span id="clocks">2017-08-01 11：29 星期二</span></p>
  <a class="curr">车辆性质</a>
</div>
<div class="down">
     <div class="check"><a   class="fenp popbox-link" id="add_vn" style="cursor:pointer">添加</a></div>
        <table width="100%" border="0">
        <tr>     
          <th scope="col">车辆性质</th>
          <th scope="col">插入时间</th>
           <th scope="col">排序</th>
          <th scope="col" colspan='2'>操作</th>
        </tr>
          <#if naturelist?exists && naturelist?size gt 0>
               <#list naturelist as vn>
                  <tr>                           
                    <td>${vn.vehiclenature_name?default('未设置')}</td>
                     <td>${vn.insert_time?default('未设置')}</td>
                      <td>${vn.sort?default('未设置')}</td>
                    <td><a onclick="update(${vn.vnid})" style='cursor:pointer;width:100%;text-align:center'>修改</a></td>
                    <td><a onclick="delete_vn(${vn.vnid})" style='cursor:pointer;width:100%;text-align:center'>删除</a></td>
                  </tr>
                </#list>
                <#else>
				<tr>
			<td class="text-center danger" colspan="6">没有找到车辆类型</td>
				</tr>
			</#if> 
    
      </table>
        <td style="display:none"><input class="svnid"/></td>
</div>
<!--添加弹出层开始-->
<div id="screen"></div>
<div class="popbox">
	<div class="mainlist">
        <h2>添加车辆性质<a class="close-btn"  style='cursor:pointer'>&times;</a></h2>
		 <ul>
		    <li style="display:none"><input id="pvnid"/></li> 
			<li><span>车辆性质</span><input class="pvehiclenature"/></li>  
			<li><span>排序</span><input class="sort"/></li>         
		</ul>
        <div class="btn"><div><a class="close-btn" style='cursor:pointer'>关闭</a>
        <a class="ok-btn" style='cursor:pointer;margin-left:15px'>确定</a></div></div>
	</div>
</div>
<!--弹出层结束-->

<!--删除弹出层开始-->
<div id="screen"></div>
<div class="popbox1" style="width:500px">
	<div class="mainlist">
        <h2>是否删除<a class="close-btn1"  style='cursor:pointer'>&times;</a></h2>
        <div class="btn" style="height:84px"><div><a class="close-btn1" style='cursor:pointer'>关闭</a>
        <a class="del-ok-btn" style='cursor:pointer;margin-left:15px'>确定</a></div></div>
	</div>
</div>
<!--弹出层结束-->
<script type="text/javascript" src="${basePath}/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${basePath}/js/page.js"></script>
<script type="text/javascript" src="${basePath}/js/common/layer/layer.js"></script>

<script>
     function delete_vn(vnid){
        var h = $(document).height();
		$('#screen').css({ 'height': h });	
		$('#screen').show();
		$('.popbox1').center();
		$('.popbox1').fadeIn();
		$('.svnid').val(vnid);
		return false;
     }
     function update(vnid){
        var h = $(document).height();
		$('#screen').css({ 'height': h });	
		$('#screen').show();
		$('.popbox').center();
		$('.popbox').fadeIn();          
         var data={vnid:vnid};
         $.ajax({ 
			url:"${basePath}/nature/selectonenature.shtml",
			data:data,
			type:"post",			        		        
			success:function(result){		        	
			     $('#pvnid').val(result.message.vnid);
			     $('.pvehiclenature').val(result.message.vehiclenature_name);
			      $('.sort').val(result.message.sort);
		     }
		 }); 
}



$(function(){
    $(document).ready(function(){  
	$('.close-btn').click(function(){	     
		$('.popbox').fadeOut(function(){ $('#screen').hide(); });
		return false;
	});
	
	$('.close-btn1').click(function(){	   
		$('.popbox1').fadeOut(function(){ $('#screen').hide(); });
		return false;
	});
	
	$('#add_vn').click(function(){
	    $('#pvnid').val("");
	    $('.pvehiclenature').val("");
	    $('.sort').val("");
		var h = $(document).height();
		$('#screen').css({ 'height': h });	
		$('#screen').show();
		$('.popbox').center();
		$('.popbox').fadeIn();
		return false;
	});
	
	//删除
	$('.del-ok-btn').click(function(){      
	        var vnid=$('.svnid').val();
	         var data = {vnid:vnid};	                
			        $.ajax({
			        	url:"${basePath}/nature/deletenature.shtml",
			        	data:data,
			        	type:"post",			        		        
			        	success:function(result){			        									       				     				    
				       $('.popbox').fadeOut(function(){ $('#screen').hide(); });			    
				       
				       // window.location.reload(true);
			        	}
			        });    
	});
	
	//添加
  $('.ok-btn').click(function(){    
       var vnid=$('#pvnid').val();
       var vehiclenature=$('.pvehiclenature').val();
       var sort=$('.sort').val();     
        if(vnid==""||vnid==null){    
          var data={vehiclenature:vehiclenature,sort:sort};
                $.ajax({
			        	url:"${basePath}/nature/addNature.shtml",
			        	data:data,
			        	type:"post",			        		        
			        	success:function(result){		        			      
				    $('.popbox').fadeOut(function(){ $('#screen').hide(); });
				        window.location.reload(true);
			        	}
			        });  
			        }else{	        
			        var data1={vnid:vnid,vehiclenature:vehiclenature,sort:sort};
			         $.ajax({
			        	url:"${basePath}/nature/updatenature.shtml",
			        	data:data1,
			        	type:"post",			        		        
			        	success:function(result){
			        				      			        			       				     				    
				       $('.popbox').fadeOut(function(){ $('#screen').hide(); });			        
				           window.location.reload(true);
			        	}
			        });
			        
			        			       			     	        			        
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
	}else{
		obj.stop();
		obj.css({'position': 'absolute'});
		obj.animate({ 'top': top_position }, 200, 'linear');
	}
}
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
