<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
<link href="${basePath}/css/page.css" type="text/css" rel="stylesheet"/>
<title>用户管理</title>
</head>
<body>
        <div class="top">
              <p><img src="${basePath}/img/ico_qiz.png"/>当前位置：系统管理>用户管理<span id="clocks">2017-08-01 11：29 星期二</span></p>
              <a class="curr">用户管理</a>
        </div>
<div class="down">
         <div class="search">
              <div><input value="${search_v?default('')}" placeholder="输入姓名或者手机号查询" class="search_value"/><a style='cursor:pointer' class="p_search">查询</a></div>               
           <a class="shuaxin" style="color:#000000;cursor:pointer;float:right">刷新</a>           
             <a class="shuaxin" style="cursor:pointer;float:right;margin-right:2px">
            <img src="${basePath}/img/shuaxin.png" style=" width:20px;height:20px;"/></a>
         </div>
     
       <div class="check"><a class="fenp popbox-link" id="addUser" style="cursor:pointer">添加用户</a>
      </div>

       <table width="100%" border="0">
         <tr>
          <th scope="col">用户名</th>
          <th scope="col">姓名</th>
      
          <th scope="col">手机号码</th>
          <th scope="col">职位</th>
          <th scope="col">使用类型</th>
          <th scope="col">入职时间</th>
          <th scope="col">离职时间</th>
          <th scope="col">状态</th>
          <th scope="col">工作状态</th>
          <th scope="col">创建时间</th>    
          <th scope="col">操作</th>
        </tr>
     <#if page?exists && page?size gt 0>
     <#list page as ul>
        <tr>
          <td>${ul.email?default('空')}</td>
          <td>${ul.nickname?default('空')}</td>
         
          <td>${ul.phone?default('空')}</td>
          <td>${ul.position?default('空')}</td>
          <td>${ul.u_type?default('空')}</td>
          <td>${ul.hiredate?default('空')}</td>
          <td>${ul.leavedate?default('空')}</td>
            <#if ul.status==1>
               <td>账号正常</td>
            <#else>
               <td>禁用</td>
            </#if>
            <#if ul.work_status==0>
               <td>正常</td>
            <#else>
                <td>已离职</td>
           </#if>
           <td>${ul.createTime?string('yyyy-MM-dd HH:mm')}</td>
          <td><a onclick="update_user(${ul.id})" style='cursor:pointer;width:100%;text-align:center'>编辑</a>
              <#if ul.work_status==0>
              <a onclick="user_leave(${ul.id})" style='cursor:pointer;width:100%;text-align:center'>离职</a> 
              <#else>
                 <#if ul.position == "系统管理员"> 
                 <#else>          
                 <a onclick="delete_User(${ul.id})" style='cursor:pointer;width:100%;text-align:center'>删除</a> 
                  </#if>      
              </#if>
              </td>
        </tr> 
           </#list>
           <#else>
		<tr>
			<td class="text-center danger" colspan="9">没有找到用户</td>
		</tr>
			</#if>
      </table>
      <input style="display:none" value="${pageIndex}" class="page_i"/>
        <#if s_status==0>
    <ul class="page"  id="page">
          <li><a href="${basePath}/user/userlist.shtml?pageIndex=1">首页</a></li>
          <li>
              <#if pageIndex-1=0>
              <a href="${basePath}/user/userlist.shtml?pageIndex=1">上一页</a>
              <#else>
              <a href="${basePath}/user/userlist.shtml?pageIndex=${pageIndex-1}">上一页</a>
              </#if>
         </li>            
               
             <#if total<5 && total gt 0>           
              <#list 1..total as i>    
          <li id="${i}"><a href="${basePath}/user/userlist.shtml?pageIndex=${i}">${i}</a></li>
              </#list>
             <#elseif total=0>
             <li id="1"><a href="${basePath}/user/userlist.shtml?pageIndex=1">1</a></li>
              <#else>
                     <#if pageIndex<5>
                         <#list 1..4 as i>    
                         <li id="${i}"><a href="${basePath}/user/userlist.shtml?pageIndex=${i}">${i}</a></li>
                         </#list>
                     <#else>
                        <#list pageIndex-3..pageIndex as i>    
                         <li id="${i}"><a href="${basePath}/user/userlist.shtml?pageIndex=${i}">${i}</a></li>
                        </#list>
                     </#if>           
        
              </#if> 
         <li>                    
              <#if pageIndex gte total>
         <a href="${basePath}/user/userlist.shtml?pageIndex=${pageIndex}">下一页</a>
              <#else>
        <a href="${basePath}/user/userlist.shtml?pageIndex=${pageIndex+1}">下一页</a>
             </#if>
         </li>
         <#if total=0>
          <li><a href="${basePath}/user/userlist.shtml?pageIndex=${total+1}">末页</a></li>
          <#else>
          <li><a href="${basePath}/user/userlist.shtml?pageIndex=${total}">末页</a></li>          
          </#if>  
          
           </ul>
           <#elseif page?size==0>
           <p style="font-size:16px;color:#FF8040;text-align:center">没有符合的用户</p>
           <#else>
           <p style="font-size:16px;color:#FF8040;text-align:center">成功为您找到${page?size}款符合的用户</p>
           </#if>
 </div>
<!--弹出层开始-->
<div id="screen"></div>
<div class="popbox" style="height:90%;overflow-x:hidden;overflow-y:auto;position:absolute;top:5%">
	<div class="mainlist">
        <h2>添加用户<a class="close-btn">&times;</a></h2>
        <form id="ap_user">
		<ul>
		    <li style="display:none"><input class="id"/></li>
		
		    <li><span>用户名</span><input name="email" class="email" onblur="upperCase()"/></li>
			<li><span>姓名</span><input name="nickname" class="nickname"/></li>
		    <li><span>手机号码</span><input name="phone" class="phone"/></li>
            <li><span>性别</span><div class="sex"><div class="choose-kf"><i class="ico-radio ico-radio-1" style="margin-top:8px;margin-left:15px" id="man"></i>男</div><div class="choose-kf"><i class="ico-radio ico-radio-1" style="margin-top:8px" id="woman"></i>女</div></div></li>            
            <li><span>入职日期</span><input name="hiredate" type="text" class="demo-input" id="hiredate" placeholder="选择入职时间"/></li>
            <li><span>职位</span><select name="roleid" style="margin-left:14px;width:200px;" id="role_select" class="role_select" onchange="change()"></select></li>
            <li><span>使用类型</span><input name="u_type" class="u_type"/></li>
            <li style="display:none" id="li_station"><span>检测站</span>
            <select  id="station" name="station" class="station" style="margin-left:14px;width:200px;">
               </select></li>
		</ul>
		</form>
        <div class="btn"><div><a class="close-btn" style='cursor:pointer'>关闭</a>
        <a class="user_ok_btn" style='cursor:pointer;margin-left:15px'>确定</a></div></div>
	</div>
</div>
<!--弹出层结束-->
<!--删除弹出层开始-->
<div id="screen"></div>
<div class="popbox1" style="width:500px">
	<div class="mainlist">
	    <input class="de_id" style="display:none"/>
        <h2>是否删除<a class="close-btn1">&times;</a></h2>
        <div class="btn" style="height:84px"><div><a class="close-btn1" style='cursor:pointer'>关闭</a>
        <a class="del_btn" style='cursor:pointer;margin-left:15px'>确定</a></div></div>
	</div>
</div>
<!--弹出层结束-->
 <script type="text/javascript" src="${basePath}/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${basePath}/js/page.js"></script>
<script type="text/javascript" src="${basePath}/js/common/layer/layer.js"></script>
<script type="text/javascript">
$(function(){
 $('.shuaxin').click(function(){
     location.href="${basePath}/user/userlist.shtml";
    //window.location.reload(true);	             
   });

});
  function change(){
     var role=$('.role_select').find("option:selected").text();
      if(role=="验车员"){ 
           $('#li_station').css({"display":"block"});
      }else{
           $('#li_station').css({"display":"none"}); 
      }  
  }
function upperCase(){
  var myReg = /^[a-zA-Z0-9_]{0,}$/; 
    if (!myReg.test($('.email').val())) { 
    $('.email').val("");
    alert("用户名不能含有中文或特殊字符");
    return;  
   }  
}
$(document).ready(function(){
   var page_i=$('.page_i').val();
   $('#'+page_i).addClass("curru");
     
   $('.p_search').click(function(){
    var search=$('.search_value').val();
    location.href="${basePath}/user/userlist.shtml?search="+encodeURI(encodeURI(search));              
   });
   	$('.close-btn1').click(function(){
		$('.popbox1').fadeOut(function(){ $('#screen').hide(); });
		return false;
	});
	$('.del_btn').click(function(){
	  var id=$('.de_id').val();
	  var data={id:id};
	     $.ajax({
		     url:"${basePath}/user/delete_User.shtml",
		     data:data,			 
		     type:"post",			        		        
		         success:function(result){
		            layer.msg("已删除");
	                setTimeout(function(){window.location.href="${basePath}/user/userlist.shtml?pageIndex="+$('.page_i').val();},1000);
		          }
	    });
	});
});
  function delete_User(id){
        var h = $(document).height();
		$('#screen').css({ 'height': h });	
		$('#screen').show();
		$('.popbox1').center();
		$('.popbox1').fadeIn();
		$('.de_id').val(id);
		return false;
   }
//编辑弹框
function update_user(id){
       var data={id:id};
        var h = $(document).height();
		$('#screen').css({ 'height': h });	
		$('#screen').show();
		$('.popbox').center();
		$('.popbox').fadeIn();
//检测站	
		    $.ajax({
		         url:"${basePath}/order/listStation.shtml",			 
		         type:"post",
		         async : false,			        		        
		         success:function(result){	
		             $('#station').empty();     
		             var listStation=result.listStation;
		             var option="<option value=''>请选择检测站</option>";
		             for(var i=0;i<listStation.length;i++){		    
		              option+="<option value='"+listStation[i].station_no+"'>"+listStation[i].station_no+"</option>";
		             }
		              $('#station').append(option);
		         }
	       });
	       
	       	     
	      $.ajax({
		          url:"${basePath}/mrole/rolelist.shtml",			 
		          type:"post",
		          async : false,			        		        
		          success:function(result){	     
		             var rolelist=result.rolelist;
		             $('.role_select').empty();
		             var option="";
		             for(var i=0;i<rolelist.length;i++){		    
		             option+="<option value='"+rolelist[i].roleid+"'>"+rolelist[i].role_name+"</option>";
		             }
		              $('.role_select').append(option);
		          }
	          });  
//单个查询				
		   $.ajax({
		          url:"${basePath}/user/findOne_user.shtml",
		          data:data,			 
		          type:"post",
		        			        		        
		          success:function(result){	     
		              var user=result.uuser;		          
		              $('.id').val(user.id);
		              $('.email').val(user.email);
		              $('.nickname').val(user.nickname);
		              $('.phone').val(user.phone);
		              $('.u_type').val(user.u_type);
		              $('#hiredate').val(user.hiredate);		        
		              $("#role_select option[value='"+user.roleid+"']").attr("selected","selected");
		              if(user.sex==0){
		              $('#man').addClass('ico-radio ico-radio-2');
		              }else{
		                $('#woman').addClass('ico-radio ico-radio-2');
		              }
		         }		     
	     }); 
  	      	
		return false;
}
//离职
 function user_leave(id){
       var data={id:id};
            $.ajax({
		       url:"${basePath}/user/user_leave.shtml",
		       data:data,			 
		       type:"post",			        		        
		            success:function(result){
		                layer.msg("已经离职");
	       	            setTimeout(function(){window.location.href="${basePath}/user/userlist.shtml?pageIndex="+$('.page_i').val();},2000);		    	     
		            }
	        });
   }
$(function(){
	$('.close-btn').click(function(){
		$('.popbox').fadeOut(function(){ $('#screen').hide(); });
		return false;
	});
//添加弹出框
     $('#addUser').click(function(){
        var h = $(document).height();
		$('#screen').css({ 'height': h });	
		$('#screen').show();
		$('.popbox').center();
		$('.popbox').fadeIn();
           $('.id').val("");
           $('.email').val("");       
		   $('.nickname').val("");
		   $('.phone').val("");
		   $('#hiredate').val("");
		   $('.u_type').val("");
              $.ajax({
		          url:"${basePath}/order/listStation.shtml",			 
		          type:"post",
		          async : false,			        		        
		          success:function(result){
		             $('#station').empty();	     
		             var listStation=result.listStation;
		              var option="<option value=''>请选择检测站</option>";
		             for(var i=0;i<listStation.length;i++){		    
		             option+="<option value='"+listStation[i].station_no+"'>"+listStation[i].station_no+"</option>";
		             }
		             $('#station').append(option);
		          }
	          });
	          $.ajax({
		          url:"${basePath}/mrole/rolelist.shtml",			 
		          type:"post",			        		        
		          success:function(result){	     
		             var rolelist=result.rolelist;
		             $('.role_select').empty();
		             var option="";
		             for(var i=0;i<rolelist.length;i++){		    
		             option+="<option value='"+rolelist[i].roleid+"'>"+rolelist[i].role_name+"</option>";
		             }
		              $('.role_select').append(option);
		          }
	          });     
    return false;   
     });
     
     
     $('.user_ok_btn').click(function(){
           var id=$('.id').val();            
           var sex=0;
           if($('#man').hasClass('ico-radio ico-radio-2')){ 
              sex=0;
           }
            if($('#woman').hasClass('ico-radio ico-radio-2')){ 
              sex=1;
           }
           if(id==""||id=="undefined"){
               $.ajax({
		            url:"${basePath}/user/addUser.shtml",
		            data:$('#ap_user').serialize()+"&sex="+sex,			 
		            type:"post",			        		        
		            success:function(result){
		               layer.msg("添加成功");
	       	           $('.popbox').fadeOut(function(){ $('#screen').hide(); });
	       	           setTimeout(function(){window.location.href="${basePath}/user/userlist.shtml?pageIndex="+$('.page_i').val();},2000);	    	     
		            }
	           });
	      
	     }else{		    
	          $.ajax({
		            url:"${basePath}/user/updateUser.shtml",
		            data:$('#ap_user').serialize()+"&sex="+sex+"&id="+id,			 
		            type:"post",			        		        
		            success:function(result){
		                 layer.msg("修改成功");
	       	             $('.popbox').fadeOut(function(){ $('#screen').hide(); });
	       	             setTimeout(function(){window.location.href="${basePath}/user/userlist.shtml?pageIndex="+$('.page_i').val();},2000);	    	     
		            }
	           });
	      
	     }
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

<script src="${basePath}/js/laydate/laydate.js"></script>
<script>
lay('#version').html('-v'+ laydate.v);

//执行一个laydate实例
laydate.render({
  elem: '#hiredate' //指定元素
});


</script>
</body>

</html>
