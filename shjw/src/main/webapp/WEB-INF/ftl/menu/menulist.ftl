<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
<link href="${basePath}/css/page.css" type="text/css" rel="stylesheet"/>
 <script  type="text/javascript" src="${basePath}/js/common/jquery/jquery1.8.3.min.js"></script>
<script type="text/javascript" src="${basePath}/js/page.js"></script>
<script type="text/javascript" src="${basePath}/js/common/layer/layer.js"></script>
<title>菜单管理</title>
</head>
<body>
        <div class="top">
              <p><img src="${basePath}/img/ico_qiz.png"/>当前位置：系统管理>菜单管理<span id="clocks">2017-08-01 11：29 星期二</span></p>
               <a href="${basePath}/mrole/qx.shtml">权限管理</a> <a class="curr">菜单管理</a>
        </div>
<div class="down">     

       <div class="check"><a class="fenp popbox-link" id="addMenu" style="cursor:pointer">添加菜单</a>
      </div>
  <div>
       <table width="100%" border="0">
         <tr>
          <th scope="col">菜单编号</th>
          <th scope="col">菜单名</th>
          <th scope="col">父级菜单</th>
          <th scope="col">插入时间</th> 
          <th scope="col">url</th> 
          <th scope="col">选择器id</th> 
          <th scope="col">排序</th>
          <th scope="col">图标</th>      
          <th scope="col" colspan='2'>操作</th>
        </tr>
     <#if menulist?exists && menulist?size gt 0>
     <#list menulist as menu>
        <tr>
          <td>${menu.menuid?default('空')}</td>
          <td>${menu.menu_name?default('空')}</td>
          <td>${menu.parentid?default('空')}</td>
          <td>${menu.insert_time?default('空')}</td>
          <td>${menu.menu_url?default('空')}</td>
          <td>${menu.menu_pid?default('空')}</td>
          <td>${menu.sort?default('空')}</td>
          <td>${menu.menu_iclass?default('空')}</td>                    
          <td><a onclick="update_menu(${menu.menuid})" style='cursor:pointer;width:100%;text-align:center'>编辑</a></td>  
          <td><a onclick="delete_menu(${menu.menuid})" style='cursor:pointer;width:100%;text-align:center'>删除</a></td>                                
        </tr> 
           </#list>
           <#else>
		<tr>
			<td class="text-center danger" colspan="10">没有找到菜单</td>
		</tr>
			</#if>
      </table>
       <input style="display:none" value="${pageIndex}" class="page_i"/>
  <ul class="page"  id="page">
       <li><a href="${basePath}/menu/menulist.shtml?pageIndex=1">首页</a></li>
       <li>
       <#if pageIndex-1=0>
            <a href="${basePath}/menu/menulist.shtml?pageIndex=1">上一页</a>
       <#else>
            <a href="${basePath}/menu/menulist.shtml?pageIndex=${pageIndex-1}">上一页</a>
       </#if> 
       </li>    
               
               <#if total<5>            
              <#list 1..total as i>    
          <li id="${i}"><a href="${basePath}/menu/menulist.shtml?pageIndex=${i}">${i}</a></li>
              </#list>
              <#else>
                     <#if pageIndex<6>
                         <#list 1..5 as i>    
                         <li id="${i}"><a href="${basePath}/menu/menulist.shtml?pageIndex=${i}">${i}</a></li>
                         </#list>
                     <#else>
                        <#list pageIndex-4..pageIndex as i>    
                         <li id="${i}"><a href="${basePath}/menu/menulist.shtml?pageIndex=${i}">${i}</a></li>
                        </#list>
                     </#if>           
        
              </#if> 
             <li>  
               <#if pageIndex lt total>
               <a href="${basePath}/menu/menulist.shtml?pageIndex=${pageIndex+1}">下一页</a>
              <#else>
              <a href="${basePath}/menu/menulist.shtml?pageIndex=${pageIndex}">下一页</a>
             </#if>
             </li>
            <#if total=0>
            <li><a href="${basePath}/menu/menulist.shtml?pageIndex=${total+1}">末页</a></li>
            <#else>
            <li><a href="${basePath}/menu/menulist.shtml?pageIndex=${total}">末页</a></li>
            </#if>
   </ul> 
    
 </div>
<!--添加菜单弹出层开始-->
<div id="screen"></div>
<div class="popbox">
	<div class="mainlist">
        <h2>添加菜单<a class="close-btn" style='cursor:pointer'>&times;</a></h2>
        <form id="menu_form">
		 <ul>
		    <li style="display:none"><span>id</span><input  type="text" class="menu_id"/></li> 		 			 
			<li><span>菜单名</span><input type="text"   name="menu_name" class="menu_name"/></li> 
		    <li><span>上一级菜单</span><select id="jb"  style="margin-left:15px;width:199px" name="parentid" class="parentid"></select></li>
		    <li><span>url</span><input type="text"    name="menu_url" class="menu_url"/></li>
		    <li><span>选择器id</span><input type="text" name="menu_pid"  class="menu_pid"/></li>  
			<li><span>排序</span><input type="text"    name="sort" class="sort"/></li>
			<li><span>图标</span><input type="text"    name="menu_iclass" class="menu_iclass"/></li>   
		</ul>
		</form>      
		 <div class="btn"><div><a class="close-btn" style='cursor:pointer'>关闭</a>
		 <a class="menu_ok_btn" style='cursor:pointer;margin-left:15px'>确定</a></div></div>     
	</div>
</div>
<!--弹出层结束-->

<!--删除弹出层开始-->
<div id="screen"></div>
<div class="popbox1" style="width:500px">
	<div class="mainlist">
        <h2>是否删除<a class="close-btn1" style='cursor:pointer'>&times;</a></h2>
        <input style="display:none" class="p_menu_id"/>
        <div class="btn" style="height:84px"><div><a class="close-btn1" style='cursor:pointer'>关闭</a>
        <a class="del_ok_btn" style='cursor:pointer;margin-left:15px'>确定</a></div></div>
	</div>
</div>
<!--弹出层结束-->
<script type="text/javascript">
$(document).ready(function(){
   var page_i=$('.page_i').val();
   $('#'+page_i).addClass("curru");
 });
</script>
<script type="text/javascript">
//删除弹出
      function delete_menu(menuid){
    	var h = $(document).height();
		$('#screen').css({ 'height': h });	
		$('#screen').show();
		$('.popbox1').center();
		$('.popbox1').fadeIn();
		$('.p_menu_id').val(menuid);
		return false;
    }
//修改弹出
    function update_menu(menuid){
        var h = $(document).height();
		$('#screen').css({ 'height': h });	
		$('#screen').show();
		$('.popbox').center();
		$('.popbox').fadeIn();
		
		 $.ajax({
		          url:"${basePath}/menu/listMenu.shtml",		   		 
		          type:"post",
		          async : false,				        		        
		            success:function(result){
                       $('#jb').empty();	           
		         	   var list=result.menulist;
		         	   var option="";
		         	  if(list.length>0){
		         	       for(var i=0;i<list.length;i++){  	      	         	    
		         	       option+="<option value='"+list[i].menuid+"'>"+list[i].menu_name+"</option>";
		         	       }
		         	       $('#jb').append(option); 
		         	   }else{
		         	       $('#jb').append("<option value='0'>主菜单</option>"); 
		         	   }
		         	  	     	     
		          }
	           }); 
//查询菜单
		var data={menuid:menuid};
		       $.ajax({
		            url:"${basePath}/menu/findOneMenu.shtml",
		            data:data,			 
		            type:"post",			        		        
		              success:function(result){
		       		    var menu=result.menu;
		       		    $('.menu_id').val(menu.menuid);
		       		    $('.menu_name').val(menu.menu_name);	
		       		    $('.menu_url').val(menu.menu_url);	
		       		    $('.menu_pid').val(menu.menu_pid);	
		       		    $('.sort').val(menu.sort);	
		       		    $('.menu_iclass').val(menu.menu_iclass);
		       		  	$("#jb option[value='"+menu.parentid+"']").attr("selected","selected");     
		              }
	           });
		
		return false;  
    }
  

$(document).ready(function(){

   var page_i=$('.page_i').val();
   $('#'+page_i).addClass("curru");
 });
   
$(function(){
	$('.close-btn').click(function(){
		$('.popbox').fadeOut(function(){ $('#screen').hide(); });
		return false;
	});
	$('.close-btn1').click(function(){
		$('.popbox1').fadeOut(function(){ $('#screen').hide(); });
		return false;
	});
//添加弹出框
     $('#addMenu').click(function(){
        var h = $(document).height();
		$('#screen').css({ 'height': h });	
		$('#screen').show();
		$('.popbox').center();
		$('.popbox').fadeIn();
           $('.menu_id').val("");       
		   $('.menu_name').val("");
		   $('.parentid').val("");
		   $('.menu_url').val("");
		   $('.menu_pid').val("");
		   $('.sort').val("");
		   $('.menu_iclass').val("");
		       $.ajax({
		          url:"${basePath}/menu/listMenu.shtml",		   		 
		          type:"post",			        		        
		            success:function(result){
                       $('#jb').empty();	           
		         	   var list=result.menulist;
		         	   var option="";
		         	  if(list.length>0){
		         	       for(var i=0;i<list.length;i++){  	      	         	    
		         	       option+="<option value='"+list[i].menuid+"'>"+list[i].menu_name+"</option>";
		         	       }
		         	       $('#jb').append(option); 
		         	   }else{
		         	       $('#jb').append("<option value='0'>主菜单</option>"); 
		         	   }
		         	  	     	     
		          }
	           });       	          
          return false;   
     });
     
     
     $('.menu_ok_btn').click(function(){
             var menuid=$('.menu_id').val();                         
             if(menuid==""||menuid=="undefined"){
               $.ajax({
		            url:"${basePath}/menu/addMenu.shtml",
		            data:$('#menu_form').serialize(),			 
		            type:"post",			        		        
		              success:function(result){
		                 layer.msg("添加成功");
	       	             $('.popbox').fadeOut(function(){ $('#screen').hide(); });
	       	             window.location.reload(true);		    	     
		              }
	           });
	          }else{          	          
	            $.ajax({
		            url:"${basePath}/menu/updateMenu.shtml",
		            data:$('#menu_form').serialize()+"&menuid="+menuid,			 
		            type:"post",			        		        
		              success:function(result){
		                 layer.msg("修改成功成功");
	       	             $('.popbox').fadeOut(function(){ $('#screen').hide(); });
	       	             window.location.reload(true);		    	     
		              }
	           });
	          }
     });
     
     $('.del_ok_btn').click(function(){
        var menuid=$('.p_menu_id').val();
        var data={menuid:menuid};
        	   $.ajax({
		            url:"${basePath}/menu/deleteMenu.shtml",
		            data:data,			 
		            type:"post",			        		        
		              success:function(result){
		                 layer.msg("删除成功");
	       	             $('.popbox1').fadeOut(function(){ $('#screen').hide(); });
	       	             window.location.reload(true);		    	     
		              }
	           });
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
