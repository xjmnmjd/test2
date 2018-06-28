<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
<link href="${basePath}/css/page.css" type="text/css" rel="stylesheet"/>
<title>委托年检收费标准</title>
</head>

<body>
<div class="top">
  <p><img src="${basePath}/img/ico_qiz.png"/>当前位置：系统管理>外牌委托收费<span id="clocks">2017-08-01 11：29 星期二</span></p>
</div>
<div class="down">
   <div class="check"><a   class="fenp popbox-link" id="popbox_add_wt" style="cursor:pointer">添加</a>
    </div>
        <table width="100%" border="0">
        <tr>
          <th scope="col">委托省份</th>
          <th scope="col">委托区域</th>
          <th scope="col">车牌</th>
          <th scope="col">免检收费</th>
          <th scope="col">提交资料</th>
          <th scope="col">非免检收费</th>
          <th scope="col">非免检提交资料</th>
          <th scope="col">备注</th>
           <th scope="col">插入时间</th>
           <th scope="col">天数</th>
          <th scope="col">操作</th>
        </tr> 
          
        <#if wtlist?exists && wtlist?size gt 0>
               <#list wtlist as wt>
                  <tr>
                  <td style="display:none"><input class="s_wtid" value="${wt.wtid}"/></td>
                  <td>${wt.wt_province?default('')}</td>
                  <td>${wt.wt_area?default('')}</td>
                  <td>${wt.wt_car_number?default('')}</td>
                  <td>${wt.wt_mj_fee?default('')}</td>
                  <td>${wt.wt_mj_zl?default('')}</td>
                  <td>${wt.wt_fmj_fee?default('')}</td>
                  <td>${wt.wt_fmj_zl?default('')}</td>
                  <td>${wt.wt_remark?default('')}</td> 
                  <td>${wt.wt_insert_time?default('')}</td>
                  <td>${wt.wt_number_day?default('')}</td>                            
                  <td><a style='cursor:pointer;width:100%;text-align:center' onclick="update_wt(${wt.wtid})">修改</a> 
                  <a style='cursor:pointer;width:100%;text-align:center' onclick="delete_wt(${wt.wtid})">删除</a></td>
                  </tr>
                   </#list>
                <#else>           
				<tr>
			<td class="text-center danger" colspan="12">没有找到委托收费</td>
				</tr>
			</#if> 
      </table>
           <input style="display:none" value="${pageIndex}" class="page_i"/>
     <ul class="page"  id="page">
          <li><a href="${basePath}/wt/wtList.shtml?pageIndex=1">首页</a></li>
          <li>
              <#if pageIndex-1=0>
              <a href="${basePath}/wt/wtList.shtml?pageIndex=1">上一页</a>
              <#else>
              <a href="${basePath}/wt/wtList.shtml?pageIndex=${pageIndex-1}">上一页</a>
              </#if>
          </li>
              
            <#if total<5 && total gt 0>             
              <#list 1..total as i>    
          <li id="${i}"><a href="${basePath}/wt/wtList.shtml?pageIndex=${i}">${i}</a></li>
              </#list>
               <#elseif total=0>
              <li id="1"><a href="${basePath}/wt/wtList.shtml?pageIndex=1">1</a></li>           
              <#else>
                     <#if pageIndex<6>
                         <#list 1..5 as i>    
                         <li id="${i}"><a href="${basePath}/wt/wtList.shtml?pageIndex=${i}">${i}</a></li>
                         </#list>
                     <#else>
                        <#list pageIndex-4..pageIndex as i>    
                         <li id="${i}"><a href="${basePath}/wt/wtList.shtml?pageIndex=${i}">${i}</a></li>
                        </#list>
                     </#if>           
        
              </#if> 
          <li>                    
              <#if pageIndex gte total>
         <a href="${basePath}/wt/wtList.shtml?pageIndex=${pageIndex}">下一页</a>
              <#else>
        <a href="${basePath}/wt/wtList.shtml?pageIndex=${pageIndex+1}">下一页</a>
             </#if>
         </li>
        <#if total=0>
          <li><a href="${basePath}/wt/wtList.shtml?pageIndex=${total+1}">末页</a></li>
          <#else>
          <li><a href="${basePath}/wt/wtList.shtml?pageIndex=${total}">末页(${total})</a></li>          
          </#if>    
          
          </ul> 
</div>
<!--添加修改委托弹出层开始-->
<div id="screen"></div>
<div class="popbox" style="height:90%;overflow-x:hidden;overflow-y:auto;position:absolute;top:5%">
	<div class="mainlist">
        <h2>添加委托信息<a class="close-btn" style='cursor:pointer'>&times;</a></h2>
        <form class="submit_form">
		 <ul> 
		    <li style="display:none"><input class="a_wtid"/></li> 
			<li><span>委托省份</span><input type="text" name="wt_province" class="a_wt_province"/></li> 
			<li><span>委托区域</span><input type="text" name="wt_area" class="a_wt_area"/></li> 
			<li><span>车牌</span><input type="text" name="wt_car_number" class="a_wt_car_number"/></li> 
			<li><span>免检收费</span><input type="text" name="wt_mj_fee" class="a_wt_mj_fee"/></li> 
			<li><span>提交资料</span><input type="text" name="wt_mj_zl" class="a_wt_mj_zl"/></li> 
			<li><span>非免检收费</span><input type="text" name="wt_fmj_fee" class="a_wt_fmj_fee"/></li> 			
			<li><span>非免检提交资料</span><input type="text" name="wt_fmj_zl" class="a_wt_fmj_zl"/></li> 
			<li><span>天数</span><input type="text" name="wt_number_day" class="a_wt_number_day"/></li> 		         				         					         
			<li><span>备注</span><input type="text" name="wt_remark" class="a_wt_remark"/></li> 		         				         
		</ul>
        <div class="btn"><div><a class="close-btn" style='cursor:pointer'>关闭</a>
        <a class="ok_btn" style='cursor:pointer;margin-left:15px'>确定</a></div></div>
        </form>
	</div>
</div>
<!--弹出层结束-->

<!--删除弹出层开始-->
<div id="screen"></div>
<div class="popbox1" style="width:500px">
	<div class="mainlist">
        <h2>是否删除<a class="close-btn1"  style='cursor:pointer'>&times;</a></h2>
        <input class="d_wtid" style="display:none"/>
        <div class="btn" style="height:84px"><div><a class="close-btn1" style='cursor:pointer'>关闭</a>
        <a class="del_wt_btn" style='cursor:pointer;margin-left:15px'>确定</a></div></div>
	</div>
</div>
<!--弹出层结束-->
<script type="text/javascript" src="${basePath}/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${basePath}/js/page.js"></script>
<script type="text/javascript" src="${basePath}/js/common/layer/layer.js"></script>
<script type="text/javascript" src="${basePath}/js/layer.js"></script>
<script type="text/javascript">
$(document).ready(function(){
   var page_i=$('.page_i').val();
   $('#'+page_i).addClass("curru");
 }); 
function update_wt(wtid){
    var h = $(document).height();
    $('#screen').css({ 'height': h });	
	$('#screen').show();
	$('.popbox').center();
	$('.popbox').fadeIn();
    $('.a_wtid').val(wtid);
    var data={wtid:wtid};
	 //单个信息查询		
     $.ajax({ 
	   url:"${basePath}/wt/selectOneWt.shtml",
	   data:data,
	   type:"post",			        		        
	   success:function(result){
	      $('.a_wtid').val(result.wt.wtid);		        			       
		  $('.a_wt_province').val(result.wt.wt_province);
	      $('.a_wt_area').val(result.wt.wt_area);
	      $('.a_wt_car_number').val(result.wt.wt_car_number);
		  $('.a_wt_mj_fee').val(result.wt.wt_mj_fee);
		  $('.a_wt_mj_zl').val(result.wt.wt_mj_zl);
		  $('.a_wt_fmj_fee').val(result.wt.wt_fmj_fee);
		  $('.a_wt_fmj_zl').val(result.wt.wt_fmj_zl);
		  $('.a_wt_remark').val(result.wt.wt_remark); 
		  $('.a_wt_number_day').val(result.wt.wt_number_day);
	  }
   }); 
  return false; 
}
//删除弹框
  function delete_wt(wtid){
  var h = $(document).height();
		$('#screen').css({ 'height': h });	
		$('#screen').show();
		$('.popbox1').center();
		$('.popbox1').fadeIn();
		$('.d_wtid').val(wtid);
  return false;
 }
 $(document).ready(function(){
      $('#popbox_add_wt').click(function(){   
        var h = $(document).height();
		$('#screen').css({ 'height': h });	
		$('#screen').show();
		$('.popbox').center();
		$('.popbox').fadeIn();
		$('.a_wtid').val("");
		$('.a_wt_province').val("");
		$('.a_wt_area').val("");					
	    $('.a_wt_car_number').val("");
		$('.a_wt_mj_fee').val("");
		$('.a_wt_mj_zl').val("");
		$('.a_wt_fmj_fee').val("");
		$('.a_wt_fmj_zl').val("");
		$('.a_wt_remark').val(""); 
		$('.a_wt_number_day').val("");	
		return false;
        });
       //添加修改委托    
       $('.ok_btn').click(function(){
            var wtid=$('.a_wtid').val();           
            if(wtid==""||wtid==undefined){   
                $.ajax({
			      url:"${basePath}/wt/addWt.shtml",
			      data:$('.submit_form').serialize(),			 
			      type:"post",
			      async : false,			        		        
			       success:function(result){
			            layer.msg("添加成功");
			             $('.popbox').fadeOut(function(){ $('#screen').hide(); });
			             setTimeout(function(){window.location.href="${basePath}/wt/wtList.shtml?pageIndex="+$('.page_i').val();},2000);				          
				  }
		       });        
		    }else{		 
		        $.ajax({
			         url:"${basePath}/wt/updateWt.shtml",
			         data:$('.submit_form').serialize()+"&wtid="+wtid,			 
			         type:"post",
			         async : false,			        		        
			         success:function(result){
			             layer.msg("修改成功");
			              $('.popbox').fadeOut(function(){ $('#screen').hide(); });
			              setTimeout(function(){window.location.href="${basePath}/wt/wtList.shtml?pageIndex="+$('.page_i').val();},2000);				          
					  }
		        });		         
		    }	           
         });
         
         //删除
         $('.del_wt_btn').click(function(){
            var wtid=$('.d_wtid').val();
            var data={wtid:wtid};
            $.ajax({
			url:"${basePath}/wt/deleteWt.shtml",
		    data:data,			 
		    type:"post",			        		        
		     success:function(result){
		        layer.msg("删除成功");
		        $('.popbox1').fadeOut(function(){ $('#screen').hide(); });
			    setTimeout(function(){window.location.href="${basePath}/wt/wtList.shtml?pageIndex="+$('.page_i').val();},2000);				          
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
