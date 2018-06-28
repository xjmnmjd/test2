<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>订单管理</title>
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
<link href="${basePath}/css/page.css" type="text/css" rel="stylesheet"/>
</head>
<body>
     <div class="top">
         <p><img src="${basePath}/img/ico_qiz.png"/>当前位置：系统管理>车辆类型<span id="clocks">2017-08-01 11：29 星期二</span></p>
     </div>
     <div class="down">
              <div class="check"><a   class="fenp popbox-link" id="add_vt" style="cursor:pointer">添加</a>
              </div>
              <table width="100%" border="0">
                  <tr>          
                     <th scope="col">车辆类型</th>
                      <th scope="col">修改时间</th>
                     <th scope="col">排序</th>                         
                     <th scope="col" colspan='2'>操作</th>
                  </tr>
               <#if vtlist?exists && vtlist?size gt 0>
               <#list vtlist as vt>
                  <tr>              
                    <td>${vt.vehicletype?default('未设置')}</td>
                     <td>${vt.insert_time?default('未设置')}</td>
                    <td>${vt.sort?default('未设置')}</td>
                    <td><a onclick="update(${vt.vtid})" style='cursor:pointer;width:100%;text-align:center'>修改</a></td>
                    <td><a  onclick="delete_vt(${vt.vtid})" style='cursor:pointer;width:100%;text-align:center'>删除</a></td>
                  </tr>
                </#list>
                <#else>
				<tr>
			<td class="text-center danger" colspan="6">没有找到车辆类型</td>
				</tr>
			</#if>                               
                </table>
                  <td style="display:none"><input class="vtid"/></td>
</div>

<!--添加弹出层开始-->
<div id="screen"></div>
<div class="popbox">
	<div class="mainlist">
        <h2>添加车辆类型<a class="close-btn" style='cursor:pointer'>&times;</a></h2>
		 <ul>
		    <li style="display:none"><input class="pvtid"/></li> 
			<li><span>车辆类型</span><input class="pvehicletype"/></li>
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
    function delete_vt(vtid){
    	var h = $(document).height();
		$('#screen').css({ 'height': h });	
		$('#screen').show();
		$('.popbox1').center();
		$('.popbox1').fadeIn();
		$('.vtid').val(vtid);
		return false;
    }
    //修改弹出
  function update(vtid){
        var h = $(document).height();
		$('#screen').css({ 'height': h });	
		$('#screen').show();
		$('.popbox').center();
		$('.popbox').fadeIn();       
            var data={vtid:vtid};
            $.ajax({ 
			   url:"${basePath}/type/selectonetype.shtml",
			   data:data,
			   type:"post",			        		        
			   success:function(result){			       
			       $('.pvtid').val(result.message.vtid);
			       $('.pvehicletype').val(result.message.vehicletype);
			       $('.sort').val(result.message.sort);
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
	
	$('#add_vt').click(function(){
	    $('.pvtid').val("");
	    $('.pvehicletype').val("");
	    $('.sort').val("");
		var h = $(document).height();
		$('#screen').css({ 'height': h });	
		$('#screen').show();
		$('.popbox').center();
		$('.popbox').fadeIn();
		return false;
	});
					
	//添加修改
	$('.ok-btn').click(function(){	         
	     var vtid=$('.pvtid').val();	        
	     if(vtid==""||vtid==0){
	         var vehicletype=$('.pvehicletype').val();
	         var sort=$('.sort').val();
	         var data = {vehicletype:vehicletype,sort:sort};                 
			 $.ajax({
			    url:"${basePath}/type/addtype.shtml",
			    data:data,
			    type:"post",			        		        
			  	success:function(result){
				     if(result.message==1){				       				     				    
				         $('.popbox').fadeOut(function(){ $('#screen').hide(); });
				     }else{
				          layer.msg("添加失败");
				          $('.popbox').fadeOut(function(){ $('#screen').hide(); });
				     }
				          window.location.reload(true);
			        },
			         error:function(e){
			        		console.log(e,e.message);
			        		layer.msg("错误");
			         }
			  }); 
		}else{				       
			 var vehicletype=$('.pvehicletype').val();
			  var sort=$('.sort').val();			       	        
			 var data1={vtid:vtid,vehicletype:vehicletype,sort:sort};
			   $.ajax({
			      url:"${basePath}/type/updatetype.shtml",
			      data:data1,
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
	        var vtid=$('.vtid').val();
	        var data = {vtid:vtid};	                
			        $.ajax({
			        	url:"${basePath}/type/deletetype.shtml",
			        	data:data,
			        	type:"post",			        		        
			        	success:function(result){			     
				        if(result.message==1){				       				     				    
				       $('.popbox').fadeOut(function(){ $('#screen').hide(); });
				        }else{
				         layer.msg("删除失败");
				       $('.popbox').fadeOut(function(){ $('#screen').hide(); });
				        }
				        window.location.reload(true);
			        	},
			        	error:function(e){
			        		console.log(e,e.message);
			        		layer.msg("错误");
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
