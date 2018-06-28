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
         <p><img src="${basePath}/img/ico_qiz.png"/>当前位置：系统管理>留言板<span id="clocks">2017-08-01 11：29 星期二</span></p>
     </div>
  <div class="down">
              <table width="100%" border="0">
                  <tr>          
                     <th scope="col">联系姓名</th>
                      <th scope="col">联系电话</th>
                     <th scope="col">留言内容</th>
                      <th scope="col">留言时间</th>
                      <th scope="col">状态</th>
                     <th scope="col">修改时间</th> 
                     <th scope="col">操作者</th>                                              
                     <th scope="col" colspan='2'>操作</th>
                  </tr>
               <#if inforlist?exists && inforlist?size gt 0>
               <#list inforlist as il>
                  <tr>              
                    <td>${il.contact_name?default('')}</td>
                    <td>${il.contact_phone?default('')}</td>
                    <td>${il.infor?default('')}</td>
                    <td>${il.insert_time?default('')}</td>
                    <#if il.state==0>
                    <td>未处理</td>
                     <#else>
                     <td>已处理</td>
                     </#if>
                    <td>${il.update_time?default('')}</td>
                     <td>${il.operator?default('')}</td>
                    <td><a  onclick="open_check(${il.id?default('')})" style='cursor:pointer;width:100%;text-align:center'>处理</a></td>
                    <td><a  onclick="open_del(${il.id?default('')})" style='cursor:pointer;width:100%;text-align:center'>删除</a></td>
                  </tr>
                </#list>
                <#else>
				<tr>
			<td class="text-center danger" colspan="8">暂时没有留言</td>
				</tr>
			</#if>                               
                </table>
        
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
    function open_del(id){
    	var h = $(document).height();
		$('#screen').css({ 'height': h });	
		$('#screen').show();
		$('.popbox1').center();
		$('.popbox1').fadeIn();
		$('.dly_id').val(id);
		return false;
    }
    //处理留言弹出
  function open_check(id){
        var h = $(document).height();
		$('#screen').css({ 'height': h });	
		$('#screen').show();
		$('.popbox').center();
		$('.popbox').fadeIn();
		$('.ly_id').val(id);
		return false;       
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
	

					
	//添加修改
	$('.ly_cx_ok').click(function(){	         
	     var id=$('.ly_id').val();	        
	     var state=1;	        	         
	    if($('#ycl').hasClass('ico-radio ico-radio-2')){
	        var data={id:id,state:state};
	         $.ajax({
		       url:"${basePath}/info/update_ly.shtml",
		          data:data,			 
		          type:"post",			        		        
		          success:function(result){
		               $('.popbox').fadeOut(function(){ $('#screen').hide(); });	          
                       window.location.reload(true);
		          }
	          });
	   }
	   if($('#wcl').hasClass('ico-radio ico-radio-2')){
	        state=0;
	        var data={id:id,state:state};
	        $.ajax({
		       url:"${basePath}/info/update_ly.shtml",
		       data:data,			 
		       type:"post",			        		        
		          success:function(result){
		            $('.popbox').fadeOut(function(){ $('#screen').hide(); });		          
	                window.location.reload(true);
		          }
	        });  
	   }             

	 
  });
		
	//删除
	$('.del-ok-btn').click(function(){
	        var id=$('.dly_id').val();
	        var data = {id:id};	                
			        $.ajax({
			        	url:"${basePath}/info/deleteInfor.shtml",
			        	data:data,
			        	type:"post",			        		        
			        	success:function(result){			     				  
				         $('.popbox1').fadeOut(function(){ $('#screen').hide(); });				   
				         window.location.reload(true);
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
<script>
$(function(){

});
</script>
<script type="text/javascript">
function tt(dd){
	//alert(dd);
}
var GG = {
	"kk":function(mm){
	   // alert(mm);
	}
}

$("#page").initPage(71,1,GG.kk);
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
