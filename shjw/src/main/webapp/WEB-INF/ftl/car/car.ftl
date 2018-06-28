<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
<link href="${basePath}/css/page.css" type="text/css" rel="stylesheet"/>
<script src=" http://files.cnblogs.com/files/tengfei8/ajaxfileupload.js"></script>
<title>车辆查询</title>
</head>
<style>
.lay-table tr th{font-size:8px;color:#888;line-height:20px}
</style>
<body>
<div class="top">
            <p><img src="${basePath}/img/ico_qiz.png"/>当前位置：车辆管理>车辆查询<span id="clocks">2017-08-01 11：29 星期二</span></p>
            <a class="curr">车辆查询</a>
        </div>
<div class="down">
    <div class="cx-result" style="display:block">
      <form  action="${basePath}/car/carList.shtml" method="post">
        <div class="choose">
            <input placeholder="姓名" name="car_owner"/>
            <input placeholder="手机号码" name="car_phone"/>
            <input placeholder="车牌号码" name="car_plate_number"/>
        </div>
        <a style="left:40%;margin-top:-30px" ><input value="查询" type="submit" style='cursor:pointer'/></a>
        </form>
    </div>
     <div class="check">
        <a class="fenp" style='cursor:pointer'>添加用户</a>
        <a class="daoc" style='cursor:pointer'>导出EXCEL</a>
        <a class="daor" style='cursor:pointer'>导入EXCEL</a>
     </div>
    <table width="100%" border="0">
          <tr>                 
            <th scope="col">车主</th>
            <th scope="col">手机号码</th>
            <th scope="col">车牌号</th>
            <th scope="col">车辆类型</th>
            <th scope="col">使用性质</th>
            <th scope="col">注册日期</th>
            <th scope="col">车架号</th>
            <th scope="col">发动机号</th>
            <th scope="col">品牌型号</th>
            <th scope="col">监测站</th>
            <th scope="col" colspan='2'>操作</th>
          </tr>
   <#if carlist?exists && carlist?size gt 0>
   <#list carlist as car>
       <tr>
            <td>${car.car_owner?default('未设置')}</td>
            <td>${car.car_phone?default('未设置')}</td>
            <td>${car.car_plate_number?default('未设置')}</td>
            <td>${car.car_type?default('未设置')}</td>                
            <td>${car.car_property?default('未设置')}</td>
            <td>${car.car_regdate?default('未设置')}</td>
            <td>${car.car_vin_no?default('未设置')}</td>
            <td>${car.car_engine_No?default('未设置')}</td>
            <td>${car.car_brand_model?default('未设置')}</td>
            <td>${car.car_station?default('未设置')}</td>        
            <td><a style='cursor:pointer;width:100%;text-align:center' onclick="car_bj_open(${car.carid})">编辑</a></td>
            <td><a style='cursor:pointer;width:100%;text-align:center' onclick="car_delete_open(${car.carid})">删除</a></td>
       </tr>
    </#list>
         <#else>
		    <tr>
			  <td class="text-center danger" colspan="12">未找到车辆</td>
		    </tr>
    </#if>
 </table>
 
   <input style="display:none" value="${pageIndex}" class="page_i"/>
   <ul class="page"  id="page">
       <li><a href="${basePath}/car/carList.shtml?pageIndex=1">首页</a></li>
       <li>
       <#if pageIndex-1=0>
            <a href="${basePath}/car/carList.shtml?pageIndex=1">上一页</a>
       <#else>
            <a href="${basePath}/car/carList.shtml?pageIndex=${pageIndex-1}">上一页</a>
       </#if> 
       </li>                
              <#if total<5 && total gt 0>            
              <#list 1..total as i>    
                   <li id="${i}"><a href="${basePath}/car/carList.shtml?pageIndex=${i}">${i}</a></li>
              </#list>
              <#elseif total=0>
              <li id="1"><a href="${basePath}/car/carList.shtml?pageIndex=1">1</a></li>
              <#else>
                     <#if pageIndex<5>
                         <#list 1..4 as i>    
                         <li id="${i}"><a href="${basePath}/car/carList.shtml?pageIndex=${i}">${i}</a></li>
                         </#list>
                     <#else>
                        <#list pageIndex-3..pageIndex as i>    
                         <li id="${i}"><a href="${basePath}/car/carList.shtml?pageIndex=${i}">${i}</a></li>
                        </#list>
                     </#if>           
        
              </#if> 
             <li>  
                <#if pageIndex gte total>
                   <a href="${basePath}/car/carList.shtml?pageIndex=${pageIndex}">下一页</a>          
                <#else>
                    <a href="${basePath}/car/carList.shtml?pageIndex=${pageIndex+1}">下一页</a>
                </#if>
            </li>
            <#if total=0>
          <li><a href="${basePath}/car/carList.shtml?pageIndex=${total+1}">末页</a></li>
          <#else>
          <li><a href="${basePath}/car/carList.shtml?pageIndex=${total}">末页</a></li>          
     </#if>
   </ul> 
</div>

<!--添加车辆弹出层开始-->
<div id="screen"></div>
<div class="popbox" style="height:90%;overflow-x:hidden;overflow-y:auto;position:absolute;top:5%">
	<div class="mainlist">
        <h2>车辆信息<a class="close-btn">&times;</a></h2>
        <form class="car_form">
		 <ul> 
		    <li style="display:none"><input class="car_id"/></li> 
			<li><span>车主</span><input type="text" name="car_owner" class="car_owner"/></li> 
			<li><span>手机号码</span><input type="text" name="car_phone" class="car_phone"/></li> 
			<li><span>车牌号</span><input type="text" name="car_plate_number" class="car_plate_number"/></li> 
			<li><span>车辆类型</span><input type="text" name="car_type" class="car_type"/></li> 
			<li><span>使用性质</span><input type="text" name="car_property" class="car_property"/></li> 
			<li><span>注册日期</span><input type="text" name="car_regdate" class="car_regdate"/></li>
			<li><span>车架号</span><input type="text" name="car_vin_no" class="car_vin_no"/></li> 		         
			<li><span>发动机号</span><input type="text" name="car_engine_No" class="car_engine_No"/></li> 		         
			<li><span>品牌型号</span><input type="text" name="car_brand_model" class="car_brand_model"/></li> 		         
			<li><span>检测站</span><input type="text" name="car_station" class="car_station"/></li> 		         		 		         
		</ul>
        <div class="btn"><div><a class="close-btn" style='cursor:pointer'>关闭</a>
        <a class="car_ok_btn" style='cursor:pointer;margin-left:15px'>确定</a></div></div>
        </form>
	</div>
</div>
<!--弹出层结束-->

<!--删除弹出层开始-->
<div id="screen"></div>
<div class="popbox1" style="width:500px">
	<div class="mainlist">
        <h2>是否删除<a class="close-btn1">&times;</a></h2>
        <input style="display:none" class="delete_car_id"/>
        <div class="btn" style="height:84px"><div><a class="close-btn1" style='cursor:pointer'>关闭</a>
        <a class="del_ok_btn" style='cursor:pointer;margin-left:15px'>确定</a></div></div>
	</div>
</div>
<!--弹出层结束-->
<!--添加车辆弹出层开始-->
<div id="screen"></div>
<div class="popbox2" style="height:90%;overflow-x:hidden;overflow-y:auto;position:absolute;top:5%">
	<div class="mainlist">
        <h2>车辆信息导入<a class="close-btn2">&times;</a></h2>
        <form method="post" id="car_form" action="${basePath}/car/onload.shtml" enctype="multipart/form-data" onsubmit="return check()">      
        <a style="background:#00bc8d; width:200px; margin:10px auto; text-align:center; font-size:14px; color:#fff; line-height:33px; border-radius:3px" class="mb_load">下载模板</a>
      
       <a style="background: #efad4d;width:200px;height: 33px;margin: 2px auto; text-align: center;font-size: 14px;
        padding-top: 6px; color: #fff;">
       <input style="width:205px;height: 30px;display: inline-block;position:absolute;opacity: 0;right: 197px;" type="file" class="uploadfile" id="myFile" name="uploadfile" accept=".xls"
        />选择文件</a>
        <div style="font-size:12px; padding:10px; line-height:22px">
            <h3>注意事项：</h3>
            <p style="background-color:#0CF; width:270px">需要导入的数据必须满足下图中的格式:</p>
            <table style="width:100%; margin-top:10px; margin-bottom:10px" border="0" class="lay-table">
              <tr>
                <th scope="col">车主<em style="color:#F00">*</em></th>
                <th scope="col">手机号</th>
                <th scope="col">车牌号</th>
                <th scope="col">车辆类型</th>
                <th scope="col">使用性质</th>
                <th scope="col">注册日期</th>
                <th scope="col">车架号</th>
                <th scope="col">发动机号</th>
                <th scope="col">品牌型号</th>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>      
            </table>
            <p>车辆类型</p>
            <p>	7座以下的客车：1  8座-17座中型客车：2</p >
			<p>17座以上大型客车： 3  校车：4</p >
			<p>小型普通货车（蓝牌）：5  大型普通货车（黄牌）：6</p >
			<p>重型货车：7 </p >
			<p>车辆使用性质：</p >
			<p>非营运：1  营运：2</p >
			<p> 轻柴（3.5吨以下）： 3  重柴（3.5吨以上）：4</p >
			<p style="color:#F00">若模板格式不正确，则相应的数据无法导入！</p>
        </div>
        <div class="btn"><div><a class="close-btn2">关闭</a>
        <input type="submit" value="确定"/></div></div>
       </form>
	</div>
</div>
<!--弹出层结束-->

<script type="text/javascript" src="${basePath}/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${basePath}/js/page.js"></script>

<script type="text/javascript">
  window.onload = function(){ 
 
  }
$(document).ready(function(){
   var page_i=$('.page_i').val();
   $('#'+page_i).addClass("curru");
   //查询
   $('.car_search').click(function(){
        var search=$('.search_value').val();
        alert(search); 
        location.href="${basePath}/car/carList.shtml?search="+encodeURI(encodeURI(search));           
   });
});
$(document).ready(function(){
 	$('.close-btn').click(function(){
		$('.popbox').fadeOut(function(){ $('#screen').hide(); });
		return false;
	});
	$('.close-btn1').click(function(){
		$('.popbox1').fadeOut(function(){ $('#screen').hide(); });
		return false;
	});
    $('.close-btn2').click(function(){
		$('.popbox2').fadeOut(function(){ $('#screen').hide(); });
		return false;
	});
	$('.daoc').click(function(){
	   location.href="${basePath}/car/daoc_car.shtml"    	
	});
     $('.mb_load').click(function(){
	   location.href="${basePath}/car/mb_load.shtml"    	
	});
    $('.daor').click(function(){
           var h = $(document).height();
		   $('#screen').css({ 'height': h });	
		   $('#screen').show();
		   $('.popbox2').center();
		   $('.popbox2').fadeIn();	
		   return false;
    });

 //添加弹出
      $('.fenp').click(function(){
           var h = $(document).height();
		   $('#screen').css({ 'height': h });	
		   $('#screen').show();
		   $('.popbox').center();
		   $('.popbox').fadeIn();
		   
		   $('.car_id').val("");
		   $('.car_owner').val("");
		   $('.car_phone').val("");
		   $('.car_plate_number').val("");
		   $('.car_type').val("");
		   $('.car_property').val("");
		   $('.car_regdate').val("");
		   $('.car_vin_no').val("");
		   $('.car_engine_No').val("");
		   $('.car_brand_model').val("");
		   $('.car_station').val("");	   		   
		   return false;
      });
//添加修改确定    
      $('.car_ok_btn').click(function(){
       var carid=$('.car_id').val();
       if(carid==""||carid=="undefined"){
           $.ajax({
			  url:"${basePath}/car/addCar.shtml",
			  data:$('.car_form').serialize(),			 
			  type:"post",			        		        
			      success:function(result){
			           $('.popbox').fadeOut(function(){ $('#screen').hide(); });
			           window.location.reload(true);
			      }
		   });
		 }else{
		      $.ajax({
			     url:"${basePath}/car/updateCar.shtml",
			     data:$('.car_form').serialize()+"&carid="+carid,			 
			     type:"post",			        		        
			        success:function(result){
			             $('.popbox').fadeOut(function(){ $('#screen').hide(); });
			             window.location.reload(true);
			         }
		       });
		 
		 }
      });
    //删除确定
      $('.del_ok_btn').click(function(){
         var carid=$('.delete_car_id').val();
         var data={carid:carid};
            $.ajax({
			     url:"${basePath}/car/deleteCar.shtml",
			     data:data,			 
			     type:"post",			        		        
			        success:function(result){
			             $('.popbox1').fadeOut(function(){ $('#screen').hide(); });
			              window.location.reload(true);
			         }
		    });
      });
 });
 //编辑弹出
 function car_bj_open(carid){
        var h = $(document).height();
		   $('#screen').css({ 'height': h });	
		   $('#screen').show();
		   $('.popbox').center();
		   $('.popbox').fadeIn();
		   var data={carid:carid};
		     $.ajax({
			    url:"${basePath}/car/findCar.shtml",
			    data:data,			 
			    type:"post",			        		        
			      success:function(result){
			      var car=result.car;
			         $('.car_id').val(car.carid);
		             $('.car_owner').val(car.car_owner);
		             $('.car_phone').val(car.car_phone);
		             $('.car_plate_number').val(car.car_plate_number);
		             $('.car_type').val(car.car_type);
		             $('.car_property').val(car.car_property);
		             $('.car_regdate').val(car.car_regdate);
		             $('.car_vin_no').val(car.car_vin_no);
		             $('.car_engine_No').val(car.car_engine_No);
		             $('.car_brand_model').val(car.car_brand_model);
		             $('.car_station').val(car.car_station);
			      }
		   });
		   
		   return false;
		   
 } 
 //删除弹出
  function car_delete_open(carid){
      var h = $(document).height();
	    $('#screen').css({ 'height': h });	
		$('#screen').show();
		$('.popbox1').center();
		$('.popbox1').fadeIn();
		$('.delete_car_id').val(carid);
	    return false;
		
  }
//文件上传前的验证
    function check(){
        var file=document.getElementById("myFile").value;
        if(file==""){
            alert("请选择需要上传的文件！");
            return false;
        }else{
         
        }
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
	}else{
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
<script>
  //文件上传
  function ajaxFileUpload() {
  alert("===");
      $.ajaxFileUpload
     ({
         url: '/ApproveListHandler.aspx?Type=UpLoadAttachment', //用于文件上传的服务器端请求地址
        secureuri: false, //是否需要安全协议，一般设置为false
        fileElementId: 'txtAttachment', //文件上传域的ID
        dataType: 'json', //返回值类型 一般设置为json
        success: function (data, status)  //服务器成功响应处理函数
        {
            if (typeof (data.error) != 'undefined') {
                if (data.error != '') {
                     $("#attTable").append("<tr class='attContent'><td class='att_title' colspan='2' >" + "附件添加失败" + "</td></tr>");
                } else { 
                    var displayName = data.oldFileName;
                    var fullname = data.fileName;
                    allAttFullPath = allAttFullPath + fullname + "*";
                    $("#hdAttContent").val(allAttFullPath);
                    $("#attTable").append("<tr class='attContent'><td class='att_title'>" + displayName + "</td><td onclick='delTr(this);' class='delTd'><input type='hidden' value='" + fullname + "' /><img src='/images/selected/list_delete.png'></td></tr>");
                     }            }
       },         error: function (data, status, e)//服务器响应失败处理函数
                {
            $("#attTable").append("<tr class='attContent'><td class='att_title' colspan='2' >" + "附件添加失败,服务器响应失败,请联系管理员" + "</td></tr>");
        }
    });
    return false;
}
</script>
</body>
</html>
