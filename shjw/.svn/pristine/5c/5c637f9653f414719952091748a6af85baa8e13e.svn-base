<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>权限管理</title>
<link rel="stylesheet" type="text/css" href="${basePath}/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
</head>

<body>
<div id="menuinfo" style="display: none;"></div>
<div class="top">
    <p><img src="${basePath}/img/ico_qiz.png"/>当前位置：系统管理> 权限管理<span id="clocks">2017-08-01 11：29 星期二</span></p>
    <a  class="curr">权限管理</a><a href="${basePath}/menu/menulist.shtml">菜单管理</a>
</div>
<div class="down">
    <div class="row">
        <div class="col-lg-4 col-md-5 col-sm-6">
            <div class="check"><span style="float:left; font-size:18px">角色管理</span><a class="fenp"  id="fenp" style="float:right;cursor:pointer">添加角色</a></div>
            <table width="100%" border="0">
                  <tr>
                    <th scope="col">角色名称</th>
                    <th scope="col" colspan='2'>操作</th>
                  </tr>                         
        <#if lmrole?exists && lmrole?size gt 0>
         <#list lmrole as role>
        <tr>
          <td>${role.role_name?default('空')}</td>      
          <td><a style='cursor:pointer;width:100%;text-align:center' onclick="bj_menu(${role.roleid},'${role.role_name}')">编辑权限</a></td>
          <td><a style='cursor:pointer;width:100%;text-align:center' onclick="delete_r(${role.roleid})">删除角色</a>
          </td>
        </tr> 
           </#list>
           <#else>
		<tr>
			<td class="text-center danger" colspan="3">没有找到角色</td>
		</tr>
			</#if>         
                </table>
        </div>
        <div class="col-lg-4 col-md-5 col-sm-6" style="margin-left:50px" id="role_menu">
            <div class="check"><span style="float:left; font-size:18px">权限配置</span></div>
            <input style="display:none" type="text" class="role_id"/>
            <p id="role_name" style="display:none"><span style="margin-left:100px">角色</span><label class="role_name" style="margin-left:50px"></label></p> 
            <div class="qx">         
                  <#macro typeChilden items>  
                  <#assign countSize = items?size>  
                    <div id="qx_div"style="margin-left:20px">
                           <#list items as item1>
                                <h4 class="second"><i class="ico ico-add ico-add-01"></i><label><input type="checkbox" name="menu_check" value="${item1.m_menu.menuid}"/>${item1.m_menu.menu_name}</label></h4>             
                              <div class="third">  <#if item1.children?? && item1.children?size gt 0>                                                
                                <#list item1.children as item2>                                     
                                     <p><label><input type="checkbox" name="menu_check" value="${item2.m_menu.menuid}"/>${item2.m_menu.menu_name}</label></p>                                                                                                  
                                 </#list>                                                                                                                                                                                                                                                                        
                               </#if></div>
                                                                           
                        </#list>
                     </div>              
                </#macro>
                  <br>            
                 <#list types as item>       
                       <h4 class="first"><i class="ico ico-add ico-add-01"></i><label><input type="checkbox" name="menu_check" value="${item.m_menu.menuid}" onclick="return  false;"/>${item.m_menu.menu_name!''}</label></h4>                        
                    <#if item.children?? && item.children?size gt 0>  
                       <@typeChilden item.children></@typeChilden>  
                   </#if>              
              </#list>
               
               </div>
          <div class="check" id="check_div" style="display:none"><a class="fenp" style="float:right;cursor:pointer" id="menu_save">保存</a></div>            
               
         </div>
    
    </div>
</div>
<!--添加弹出层开始-->
<div id="screen"></div>
<div class="popbox">
	<div class="mainlist">
        <h2>添加角色<a class="close-btn"  style='cursor:pointer'>&times;</a></h2>
		 <ul>
		    <li style="display:none"><input class="roleid"/></li> 
			<li><span>角色名称</span><input class="t_role_name"/></li>          
		</ul>
        <div class="btn"><div><a class="close-btn">关闭</a><a class="ok_btn">确定</a></div></div>
	</div>
</div>
<!--弹出层结束-->

<!--删除弹出层开始-->
<div id="screen"></div>
<div class="popbox1">
	<div class="mainlist">
	    <input class="del_roleid"  style="display:none"/>
        <h2>是否删除<a class="close-btn1" style='cursor:pointer'>&times;</a></h2>
        <div class="btn"><div><a class="close-btn1" style='cursor:pointer'>关闭</a>
        <a class="del_ok_btn" style='cursor:pointer'>确定</a></div></div>
	</div>
</div>
<!--弹出层结束-->


<!--添加菜单弹出层开始-->
<div id="screen"></div>
<div class="popbox2">
	<div class="mainlist">
        <h2>添加菜单<a class="close-btn2" style='cursor:pointer'>&times;</a></h2>
		 <ul>		 			 
			<li><span>菜单名</span><input type="text" class="menu_name"/></li> 
		    <li><span>上一级菜单</span><select id="jb" class="parentid"></select></li>  
		</ul>      
		 <div class="btn"><div><a class="close-btn2" style='cursor:pointer'>关闭</a>
		 <a class="menu_ok_btn" style='cursor:pointer'>确定</a></div></div>     
	</div>
</div>
<!--弹出层结束-->


<!--添加菜单弹出层开始-->
<div id="screen"></div>
<div class="popbox3" style="height:90%;overflow-x:hidden;overflow-y:auto;position:absolute;top:5%">
	<div class="mainlist">
         <h2>编辑权限<a class="close-btn3" style='cursor:pointer'>&times;</a></h2>
	     <div class="qx1"></div>
		 <div class="btn"><div><a class="close-btn3" style='cursor:pointer'>关闭</a>
		 <a class="bj_ok_btn" style='cursor:pointer'>确定</a></div></div>     
	</div>
</div>
<!--弹出层结束-->

<script type="text/javascript" src="${basePath}/js/jquery-1.8.3.min.js"></script>
 <script type="text/javascript" src="${basePath}/js/common/layer/layer.js"></script>
<script type="text/javascript">
//删除角色
   function delete_r(roleid){
        var h = $(document).height();
		$('#screen').css({ 'height': h });	
		$('#screen').show();
		$('.popbox1').center();
		$('.popbox1').fadeIn();
		$('.del_roleid').val(roleid);
		return false;
   }
   //编辑权限
   function bj_menu(roleid,name){
      $('#role_name').css({'display':'block'});
      $('#check_div').css({'display':'block'});
      $('.qx div').css({'display':'block'});
      $('.qx div p').css({'display':'block'});
      $('.first').find('i').removeClass('ico-add-01').addClass('ico-add-02');
      $('.second').find('i').removeClass('ico-add-01').addClass('ico-add-02');
      $('.role_name').html(name);
      $('.role_id').val(roleid);
      $(':checkbox').attr("checked",false);
      var data={roleid:roleid};
      var array=document.getElementsByName("menu_check");
      $.ajax({
		   url:"${basePath}/role_menu/list_roleMenu.shtml",
		   data:data,			 
		   type:"post",			        		        
		      success:function(result){		           
		          var list=result.list_rm;
		          if(list.length>0){
		              for(var i=0;i<list.length;i++){
		                 for(var j=0;j<array.length;j++){                
		                     if(list[i].menuid==array[j].value){
		                        $(":checkbox[value="+array[j].value+"]").prop("checked",true);
		                      }	                 
		                  }
		               }
		          }  
		      	$('.first').find("input[type='checkbox']").prop("checked","checked");	         
		          		    	     
		      }
	   });
	
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
	$('.close-btn3').click(function(){
		$('.popbox3').fadeOut(function(){ $('#screen').hide(); });
		return false;
	});
//添加角色弹出	
     $('#fenp').click(function(){
        var h = $(document).height();
		$('#screen').css({ 'height': h });	
		$('#screen').show();
		$('.popbox').center();
		$('.popbox').fadeIn();
		return false;
  });


//添加确定
     $('.ok_btn').click(function(){
         var role_name=$('.t_role_name').val();
         var data={role_name:role_name};
             $.ajax({
		        url:"${basePath}/mrole/addmRole.shtml",
		        data:data,			 
		        type:"post",			        		        
		            success:function(result){		           
		             $('.popbox').fadeOut(function(){ $('#screen').hide(); });	              
	       	           window.location.reload(true);		    	     
		            }
	           });
      });
      //删除确定
     $('.del_ok_btn').click(function(){
         var roleid=$('.del_roleid').val();
         var data={roleid:roleid};
             $.ajax({
		        url:"${basePath}/mrole/deletemRole.shtml",
		        data:data,			 
		        type:"post",			        		        
		            success:function(result){		           
		             $('.popbox1').fadeOut(function(){ $('#screen').hide(); });	              
	       	           window.location.reload(true);		    	     
		            }
	           });
      });
  //权限编辑保存    
   	$('#menu_save').click(function(){
  		
   	   var roleid=$('.role_id').val();
   	     obj = document.getElementsByName("menu_check");
         var checkArray="";
         for(k in obj){
             if(obj[k].checked)
             checkArray+=obj[k].value+"&";
         }  
         var data={roleid:roleid,checkArray:checkArray};
   	           $.ajax({
		           url:"${basePath}/role_menu/addRole_menu.shtml",
		           data:data,			 
		           type:"post",			        		        
		              success:function(result){
		               layer.msg("保存成功");		           
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
	var Width=$(window).width();
	var Height=$(window).height();
	$('.all,.header').css({'width':Width});
	$('.all').css({'height':Height});
	$('.left').css({'height':Height-80});
	$('.right').css({'width':Width-160,'height':Height-80});
	$('.row').css({'width':'100%'});
	$('.qx').css({'height':Height-160});
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
  	$('.close-btn2').click(function(){
		$('.popbox2').fadeOut(function(){ $('#screen').hide(); });
		return false;
	});
     $('#addMenu').click(function(){
        var h = $(document).height();
		$('#screen').css({ 'height': h });	
		$('#screen').show();
		$('.popbox2').center();
		$('.popbox2').fadeIn();
		   
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
 //添加菜单确定
 $('.menu_ok_btn').click(function(){
    var menu_name=$('.menu_name').val();
    var parentid=$('.parentid').val();
    var data={menu_name:menu_name,parentid:parentid};
             $.ajax({
		        url:"${basePath}/menu/addMenu.shtml",
		        data:data,			 
		        type:"post",			        		        
		            success:function(result){		           
		             $('.popbox2').fadeOut(function(){ $('#screen').hide(); });	              
	       	           window.location.reload(true);		    	     
		            }
	         });
 });
  });
</script>

<script>

$(function(){
	$('.second').click(function(){
               if($(this).find('i').hasClass('ico-add-02')){
			     $(this).find('i').removeClass('ico-add-02').addClass('ico-add-01');
				 $(this).next().find("p").css({'display':'none'});
			   }else{
			     $(this).find('i').removeClass('ico-add-01').addClass('ico-add-02');
				 $(this).next().find('p').css({'display':'block'});
			   }
	});
	$('.first').click(function(){
		if($(this).find('i').hasClass('ico ico-add ico-add-01')){
			$(this).find('i').removeClass('ico ico-add ico-add-01').addClass('ico ico-add ico-add-02');
			$('.qx div').css({'display':'block'});		
		}else{		
			$(this).find('i').removeClass('ico ico-add ico-add-02').addClass('ico ico-add ico-add-01');
			$('.second').find('i').removeClass('ico-add-02').addClass('ico-add-01');
			$('.qx div').css({'display':'none'});
			$('.qx div p').css({'display':'none'});		
		};
	});
});
</script>
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
