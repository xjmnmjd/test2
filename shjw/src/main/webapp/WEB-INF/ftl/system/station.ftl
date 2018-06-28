<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
<link href="${basePath}/css/page.css" type="text/css" rel="stylesheet"/>
<title>监测站</title>
</head>

<body>
<div class="top">
  <p><img src="${basePath}/img/ico_qiz.png"/>当前位置：系统管理><span style="float:none" id="head_s">监测站</span><span id="clocks">2017-08-01 11：29 星期二</span></p>
</div>
<div class="down" id="station">
   <div class="check"><a   class="fenp popbox-link" id="add_station" style="cursor:pointer">添加</a></div>
        <table width="100%" border="0">
        <tr>         
          <th scope="col">编号</th>
          <th scope="col">名称</th>
          <th scope="col">地址</th>
          <th scope="col">电话</th>
          <th scope="col">区域</th>
          <th scope="col">省份</th>
          <th scope="col">添加时间</th>
          <th scope="col" colspan='2'>操作</th>
        </tr>
         
      <#if stationlist?exists && stationlist?size gt 0>
               <#list stationlist as sl>
                  <tr>
                  <td style="display:none"><input class="stationid" value="${sl.stationid}"/></td>
                  <td>${sl.station_no?default('未设置')}</td>
                  <td>${sl.station_name?default('未设置')}</td>
                  <td>${sl.station_address?default('未设置')}</td>
                  <td>${sl.station_phone?default('未设置')}</td>
                  <td>${sl.station_area?default('未设置')}</td>
                  <td>${sl.station_province?default('未设置')}</td>
                  <td> ${sl.insert_time?default('未设置')}</td> 
                  <td><a style='cursor:pointer;width:100%;text-align:center' onclick="njchange(${sl.stationid})">年检收费标准</a>
                  <a style='cursor:pointer;width:100%;text-align:center' onclick="hb_change(${sl.stationid})">环保收费标准</a></td>                
                  <td><a class="update-link" onclick="update_link(${sl.stationid})" style="cursor:pointer">修改</a> <a  style="cursor:pointer" onclick="delete_st(${sl.stationid})">删除</a></td>
                  </tr>
                </#list>
                <#else>
				<tr>
			<td class="text-center danger" colspan="6">没有找到检测站</td>
				</tr>
			</#if> 
      </table>
           <input style="display:none" value="${pageIndex}" class="page_i"/>
    <ul class="page"  id="page">
          <li><a href="${basePath}/station/stationlist.shtml?pageIndex=1">首页</a></li>
          <li>
              <#if pageIndex-1=0>
              <a href="${basePath}/station/stationlist.shtml?pageIndex=1">上一页</a>
              <#else>
              <a href="${basePath}/station/stationlist.shtml?pageIndex=${pageIndex-1}">上一页</a>
              </#if>
          </li>
              
              <#if total<5 && total gt 0>            
              <#list 1..total as i>    
          <li id="${i}"><a href="${basePath}/station/stationlist.shtml?pageIndex=${i}">${i}</a></li>
              </#list>
              <#elseif total=0>
              <li id="1"><a href="${basePath}/station/stationlist.shtml?pageIndex=1">1</a></li>
              <#else>
                     <#if pageIndex<6>
                         <#list 1..5 as i>    
                         <li id="${i}"><a href="${basePath}/station/stationlist.shtml?pageIndex=${i}">${i}</a></li>
                         </#list>
                     <#else>
                        <#list pageIndex-4..pageIndex as i>    
                         <li id="${i}"><a href="${basePath}/station/stationlist.shtml?pageIndex=${i}">${i}</a></li>
                        </#list>
                     </#if>           
        
              </#if>
          <li>                    
              <#if pageIndex gte total>
         <a href="${basePath}/station/stationlist.shtml?pageIndex=${pageIndex}">下一页</a>
              <#else>
        <a href="${basePath}/station/stationlist.shtml?pageIndex=${pageIndex+1}">下一页</a>
             </#if>
         </li>
        <#if total=0>
          <li><a href="${basePath}/station/stationlist.shtml?pageIndex=${total+1}">末页</a></li>
          <#else>
          <li><a href="${basePath}/station/stationlist.shtml?pageIndex=${total}">末页</a></li>          
          </#if>   
           </ul>  
</div>


<div class="down" id="nj" style="display:none">
 <div class="check">
 <a   class="fenp popbox-link" id="addnj" style="cursor:pointer">添加</a>
 <a   class="fenp popbox-link" id="fh_nj" style="cursor:pointer;background-color:#aed6bc;color:#333">返回</a>
 </div>
   <table width="100%" border="0" id="table">    
    </table>
</div>

<div class="down" id="hb" style="display:none">
 <div class="check">
 <a  class="fenp popbox-link" id="add_hb" style="cursor:pointer">添加</a>
 <a  class="fenp popbox-link" id="fh_hb" style="cursor:pointer;background-color:#aed6bc;color:#333">返回</a>
 </div>
   <table width="100%" border="0" id="table_hb">    
    </table>
</div>

<!--添加修改年检弹出层开始-->
<div id="screen"></div>
<div class="popbox2">
	<div class="mainlist">
        <h2>年检信息<a class="close-btn3"  style='cursor:pointer'>&times;</a></h2>
        <form class="addnjform">
		 <ul>
		    <li style="display:none"><input class="a_njid"/></li> 
			<li><span>车辆类型</span><select name="nj_name" id="njselect" style="margin-left:35px;width:199px"></select></li> 
			<li><span>年检收费</span><input type="text" name="nj_fee_a" class="a_nj_fee_a"/></li> 
			<li><span>尾气费</span><input type="text" name="nj_fee_b" class="a_nj_fee_b"/></li> 
			<li style="display:none"><span>年检收费c</span><input type="text" name="nj_fee_c" class="a_nj_fee_c"/></li>
		    <li style="display:none"><input name="station_id" class="nj_stationid"/></li>  
		</ul>
		  </form>
        <div class="btn"><div><a class="close-btn3" style="cursor:pointer">关闭</a>
        <a class="nj_ok_btn" style="cursor:pointer;margin-left:15px">确定</a></div></div>
      
	</div>
</div>
<!--弹出层结束-->

<!--添加修改环保弹出层开始-->
<div id="screen"></div>
<div class="popbox4">
	<div class="mainlist">
        <h2>环保信息<a class="close-btn4"  style='cursor:pointer'>&times;</a></h2>
        <form class="add_hb_form">
		 <ul>
		    <li style="display:none"><input class="a_hbid"/></li> 
			<li><span>车辆性质</span><select name="hb_name" id="hb_select" style="margin-left:55px;width:199px"></select></li> 
			<li><span>1-6年</span><input type="text" name="hb_time_a" class="a_hb_time_a"/></li> 
			<li><span>7-10年</span><input type="text" name="hb_time_b" class="a_hb_time_b"/></li> 
			<li><span>11-13年</span><input type="text" name="hb_time_c" class="a_hb_time_c"/></li>
			<li><span>13年以上</span><input type="text" name="hb_time_d" class="a_hb_time_d"/></li>		
		 <li style="display:none"><input name="stationid" class="hb_stationid"/></li>  
		</ul>
		  </form>
        <div class="btn"><div><a class="close-btn4" style="cursor:pointer">关闭</a>
        <a class="hb_ok_btn" style="cursor:pointer;margin-left:15px">确定</a></div></div>
      
	</div>
</div>
<!--弹出层结束-->

<!--添加station弹出层开始-->
<div id="screen"></div>
<div class="popbox">
	<div class="mainlist">
        <h2>添加检测站信息<a class="close-btn"  style='cursor:pointer'>&times;</a></h2>
        <form class="addform">
		 <ul> 
		    <li style="display:none"><input id="a_stationid"/></li> 
			<li><span>编号</span><input type="text" name="station_no" class="a_station_no"/></li> 
			<li><span>名称</span><input type="text" name="station_name" class="a_station_name"/></li> 
			<li><span>地址</span><input type="text" name="station_address" class="a_station_address"/></li> 
			<li><span>电话</span><input type="text" name="station_phone" class="a_station_phone"/></li> 
			<li><span>区域</span><input type="text" name="station_area" class="a_station_area"/></li> 
			<li><span>省份</span><input type="text" name="station_province" class="a_station_province"/></li> 		         
		</ul>
        <div class="btn"><div><a class="close-btn" style="cursor:pointer">关闭</a>
        <a class="ok_btn" style="cursor:pointer;margin-left:15px">确定</a></div></div>
        </form>
	</div>
</div>
<!--弹出层结束-->


<!--删除监测站弹出层开始-->
<div id="screen"></div>
<div class="popbox1" style="width:500px">
	<div class="mainlist">
        <h2>是否删除<a class="close-btn1"  style='cursor:pointer'>&times;</a></h2>
        <input class="del_station" style="display:none"/>
        <div class="btn" style="height:84px"><div><a class="close-btn1" style="cursor:pointer">关闭</a>
        <a class="del-ok-btn" style="cursor:pointer;margin-left:15px">确定</a></div></div>
	</div>
</div>
<!--弹出层结束-->

<!--删除年检弹出层开始-->
<div id="screen"></div>
<div class="popbox3" style="width:500px">
	<div class="mainlist">
        <h2>是否删除<a class="close-btn2"  style='cursor:pointer'>&times;</a></h2>
        <input class="delnjid" style="display:none"/>
        <div class="btn" style="height:84px"><div><a class="close-btn2" style="cursor:pointer">关闭</a><a class="del-ok-nj" style="cursor:pointer">确定</a></div></div>
	</div>
</div>
<!--弹出层结束-->

<!--删除环保弹出层开始-->
<div id="screen"></div>
<div class="popbox5" style="width:500px">
	<div class="mainlist">
        <h2>是否删除<a class="close-btn5">&times;</a></h2>
        <input class="delhbid" style="display:none"/>
        <input class="del_hb_stationid" style="display:none"/>
        <div class="btn" style="height:84px"><div><a class="close-btn5" style="cursor:pointer">关闭</a>
        <a class="del-ok-hb" style="cursor:pointer;margin-left:15px">确定</a></div></div>
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
//修改监测站弹出
 function update_link(stationid){
   var h = $(document).height();		
   var data={stationid:stationid};	  	    
   $('#screen').css({ 'height': h }).show();
   $('.popbox').center();
   $('.popbox').fadeIn();		
	  $.ajax({ 
	    url:"${basePath}/station/selectOneStation.shtml",
	    data:data,
	    type:"post",			        		        
	    success:function(result){
	    $('#a_stationid').val(stationid);		        			       
	    $('.a_station_no').val(result.message.station_no);
	    $('.a_station_name').val(result.message.station_name);
	    $('.a_station_address').val(result.message.station_address);
	    $('.a_station_phone').val(result.message.station_phone);
	    $('.a_station_area').val(result.message.station_area);
	    $('.a_station_province').val(result.message.station_province);
        }
    }); 
return false;
}
    //删除检测站弹出
   function delete_st(stationid){
        var h = $(document).height();
		$('#screen').css({ 'height': h });	
		$('#screen').show();
		$('.popbox1').center();
		$('.popbox1').fadeIn();
		$('.del_station').val(stationid);
		return false;
   }
$(document).ready(function(){
//显示检测站
 $('.station_nj').click(function(){	     
    var stationid=$('.stationid').val();
    var data={stationid:stationid};
    $.ajax({
	   url:"${basePath}/nj/njlist.shtml",
	   data:data,
	   type:"post",			        		        
	   success:function(result){			        									       				     				    		
	   }
    }); 	
  });		  	
//删除检测站
    $('.del-ok-btn').click(function(){     	      
    	var stationid=$('.del_station').val();
	    var data={stationid:stationid};	                
	    $.ajax({
	    	url:"${basePath}/station/deleteStation.shtml",
		  	data:data,
			type:"post",			        		        
			success:function(result){
			  layer.msg("删除成功");			        									       				     				    
	          $('.popbox').fadeOut(function(){ $('#screen').hide(); });			    
			  setTimeout(function(){window.location.href="${basePath}/station/stationlist.shtml?pageIndex="+$('.page_i').val();},2000);				          
			}
         });			             			             			             
   });		
//添加 修改监测站
   $('.ok_btn').click(function(){ 		       
	   var stationid=$('#a_stationid').val(); 
	  if(stationid==""||stationid==undefined){    
	      $.ajax({
	    	url:"${basePath}/station/addStation.shtml",
			data:$('.addform').serialize(),			 
		    type:"post",			        		        
			success:function(result){
		       layer.msg("添加成功");
			   $('.popbox').fadeOut(function(){ $('#screen').hide(); });
		       setTimeout(function(){window.location.href="${basePath}/station/stationlist.shtml?pageIndex="+$('.page_i').val();},2000);
		    }
		  });  
	  }else{
		  $.ajax({
		    url:"${basePath}/station/updateStation.shtml",
			    data:$('.addform').serialize()+"&stationid="+stationid,			 
			    type:"post",			        		        
				success:function(result){
					layer.msg("修改成功");		        			      
				    $('.popbox').fadeOut(function(){ $('#screen').hide(); });
					 setTimeout(function(){window.location.href="${basePath}/station/stationlist.shtml?pageIndex="+$('.page_i').val();},2000);					
			     }
		    }); 		  			   		        
       } 
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
<!--年检收费JQ-->
<script type="text/javascript">
  $(document).ready(function(){
     $('#fh_nj').click(function(){
          $('#station').css('display','block');
          $('#nj').css('display','none');
           $('#head_s').html("检测站");
      });

  });
  function njchange(stationid){
        $('#station').css('display','none');
        $('#nj').css('display','block');
        $('.nj_stationid').val(stationid);
        $('#head_s').html("检测站>年检收费标准");
            var data={stationid:stationid};
            $('#table').find("tr").remove();            
             $.ajax({
					url:"${basePath}/nj/njlist.shtml",
					data:data,			 
					type:"post",			        		        
					success:function(result){
					var njlist=result.njlist;
					if(njlist.length>0)	{			   
				    for(var i=0; i<njlist.length; i++){
				     var htmlstr="";
					if(i==0){
	 htmlstr+="<tr><th scope='col'>年检收费车辆</th><th scope='col'>年检收费</th><th scope='col'>尾气费</th><th scope='col'>插入时间</th><th scope='col' colspan='2'>操作</th></tr>";			        			 	 					 
						 }	
     htmlstr+="<tr><td style='display:none'><input class='njid' value=\""+njlist[i].njid+"\"";
	 htmlstr+="/></td><td>"+njlist[i].nj_name+"</td><td>"+njlist[i].nj_fee_a+"</td><td>"+njlist[i].nj_fee_b+"</td><td>"+njlist[i].nj_insert_time+"</td><td class='njupdate'><a  onclick='njupdate("+njlist[i].njid+")' style='cursor:pointer'>修改</a></td><td><a style='cursor:pointer' onclick='nj_delete("+njlist[i].njid+")'>删除</a></td></tr>";	 				 	 	 
			        $('#table').append(htmlstr);
			          } 
			         }else{
			     var htmlstr="";
				 htmlstr+="<tr><th scope='col'>年检收费车辆</th><th scope='col'>年检收费</th><th scope='col'>尾气费</th><th scope='col'>插入时间</th><th scope='col' colspan='2'>操作</th></tr>";			        			 	 					 
			          $('#table').append(htmlstr);
			         }	    			    			     				      
			     }
			 });          
        }
$(document).ready(function(){
    $('#addnj').click(function(){
    
    
        var h = $(document).height();
		$('#screen').css({ 'height': h });	
		$('#screen').show();
		$('.popbox2').center();
		$('.popbox2').fadeIn();
		$('.a_nj_fee_a').val("");
		$('.a_nj_fee_b').val("");
		$('.a_nj_fee_c').val("");
               $.ajax({
		       url:"${basePath}/type/typelist.shtml",			  
		       type:"post",			        		        
		       success:function(result){
		       $("#njselect").find("option").remove(); 	            
		             var typelist=result.vtlist;
		       	     for(var i=0; i<typelist.length; i++){  
		             var option="<option value=\""+typelist[i].vehicletype+"\""; 
		             option += ">"+typelist[i].vehicletype+"</option>";    
                      $("#njselect").append(option);         		  		       		               }	        									       				     				    		
		                }
		            });	
     });
    //添加 修改年检信息
    $('.nj_ok_btn').click(function(){       
                 var njid=$('.a_njid').val();
                 var stationid=$('.nj_stationid').val(); 
                 var nj_fee_a=$('.a_nj_fee_a').val(); 
                 var nj_fee_b=$('.a_nj_fee_b').val();
                 var nj_stationid=$('.nj_stationid').val(); 
                 var njselect=$('#njselect').val();             
                  var data={nj_fee_a:nj_fee_a,nj_fee_b:nj_fee_b,nj_stationid:nj_stationid,njselect:njselect};        
                  if(njid==""||njid==undefined){
                    $.ajax({
					url:"${basePath}/nj/addNj.shtml",
					data:data,			 
					type:"post",
					async : false,			        		        
					success:function(result){
					 //layer.msg("添加成功");
				    $('.popbox2').fadeOut(function(){ $('#screen').hide(); });
			        njchange(stationid);
					      }
				    });			    
				    }else{
				      $.ajax({
					  url:"${basePath}/nj/updateNj.shtml",
					  data:$('.addnjform').serialize()+"&njid="+njid,			 
					  type:"post",	
					   async : false,		        		        
					  success:function(result){
					   //layer.msg("修改成功");
				      $('.popbox2').fadeOut(function(){ $('#screen').hide(); });
			           njchange(stationid);
					          }
				           });	
				    }  
            });	       
        $('.njupdate').click(function(){         
            var njid=$('.njid').val();          
        });	
        //删除年检信息
        $('.del-ok-nj').click(function(){    
            var njid=$('.delnjid').val();
            var data={njid:njid};
            $.ajax({
			url:"${basePath}/nj/deleteNj.shtml",
		    data:data,			 
		    type:"post",			        		        
		    success:function(result){
		     //layer.msg("修改成功");
		     $('.popbox3').fadeOut(function(){ $('#screen').hide(); });
		      window.location.reload(true);
					  }
		     });	
        });
        	  
});
    //修改年检弹出框
   function njupdate(njid){
      var data={njid:njid};
      var h = $(document).height();
		$('#screen').css({ 'height': h });	
		$('#screen').show();
		$('.popbox2').center();
		$('.popbox2').fadeIn();	
		    //下拉框
		      $.ajax({    
		       url:"${basePath}/type/typelist.shtml",			  
		       type:"post",	
		        async : false,			        		        
		       success:function(result){
		    	 $("#njselect").find("option").remove();        
		         var typelist=result.vtlist;
		       	 for(var i=0; i<typelist.length; i++){  
		             var option="<option value=\""+typelist[i].vehicletype+"\""; 
		             option += ">"+typelist[i].vehicletype+"</option>";    
                      $("#njselect").append(option);         		  		       		               }	        									       				     				    		
		                }
		            });	
		      //单个信息查询		
		      $.ajax({ 
			        	url:"${basePath}/nj/selectOneNj.shtml",
			        	data:data,
			        	type:"post",			        		        
			        	success:function(result){
			        	$('.a_njid').val(result.nj.njid);		        			       
			        	
			        	$('.a_nj_fee_a').val(result.nj.nj_fee_a);
			        	$('.a_nj_fee_b').val(result.nj.nj_fee_b);
			        	$('.a_nj_fee_c').val(result.nj.nj_fee_c);
			        	$("#njselect option[value='"+result.nj.nj_name+"']").attr("selected","selected");     
			           }
			        });  	
		return false;
   }
 //删除弹框
function nj_delete(njid){
	   var h = $(document).height();
		$('#screen').css({ 'height': h });	
		$('#screen').show();
		$('.popbox3').center();
		$('.popbox3').fadeIn();
		$('.delnjid').val(njid);
		return false;
       
}
</script>

<!--环保收费jQ-->

<script type="text/javascript">

 $(document).ready(function(){
     $('#fh_hb').click(function(){
          $('#station').css('display','block');
          $('#hb').css('display','none');
           $('#head_s').html("检测站");
      });

  });
//显示环保收费信息
 function hb_change(stationid){
        $('#station').css('display','none');
        $('#hb').css('display','block');
        $('.hb_stationid').val(stationid);
         $('#head_s').html("检测站>环保收费标准");
         $('.del_hb_stationid').val(stationid); 
             var data={stationid:stationid}; 
              $('#table_hb').find("tr").remove(); 
              $.ajax({
					url:"${basePath}/hb/hbList.shtml",
					data:data,			 
					type:"post",			        		        
					success:function(result){
					var hbList=result.hbList;
					if(hbList.length>0){
			 for(var i=0; i<hbList.length; i++){
			        var htmlstr="";
			        if(i==0){
	 htmlstr+="<tr><th scope='col'>环保收费性质</th><th scope='col'>1-6年</th><th scope='col'>7-10年</th><th scope='col'>11-13年</th><th scope='col'>13年以上</th><th scope='col' colspan='2'>操作</th></tr>"			 	 		        
			        }
	 htmlstr+="<tr><td style='display:none'><input class='hbid' value=\""+hbList[i].hbid+"\"";
	 htmlstr+="/></td><td>"+hbList[i].hb_name+"</td><td>"+hbList[i].hb_time_a+"</td><td>"+hbList[i].hb_time_b+"</td><td>"+hbList[i].hb_time_c+"</td><td>"+hbList[i].hb_time_d+"</td><td class='hbupdate'><a  onclick='hb_update("+hbList[i].hbid+")' style='cursor:pointer'>修改</a></td><td><a style='cursor:pointer' onclick='hb_delete("+hbList[i].hbid+")'>删除</a></td></tr>";	 				 	 	 
			        $('#table_hb').append(htmlstr);
			         }          
			         }else{
			         var htmlstr="";
	htmlstr+="<tr><th scope='col'>环保收费性质</th><th scope='col'>1-6年</th><th scope='col'>7-10年</th><th scope='col'>11-13年</th><th scope='col'>13年以上</th><th scope='col' colspan='2'>操作</th></tr>"			 	 		        
			          $('#table_hb').append(htmlstr);
			         }	    			    			     				      
			     }
			 }); 
        
        }
       
 //修改环保弹出框
   function hb_update(hbid){
        var data={hbid:hbid};
        var h = $(document).height();
		$('#screen').css({ 'height': h });	
		$('#screen').show();
		$('.popbox4').center();
		$('.popbox4').fadeIn();	
//下拉框	    
		       $.ajax({    
		       url:"${basePath}/nature/nlist.shtml",			  
		       type:"post",
		        async : false,				        		        
		            success:function(result){
		             $("#hb_select").find("option").remove(); 	            
		             var vnlist=result.nlist;
		       	     for(var i=0; i<vnlist.length; i++){  
		             var option="<option value=\""+vnlist[i].vehiclenature_name+"\""; 
		             option += ">"+vnlist[i].vehiclenature_name+"</option>";    
                      $("#hb_select").append(option);         		  		       		               }	        									       				     				    		
		                }
		            });
		            		                       	
//单个信息查询		
		      $.ajax({ 
			        	url:"${basePath}/hb/selectOneHb.shtml",
			        	data:data,
			        	type:"post",			        		        
			        	success:function(result){			     	
			        	$('.a_hbid').val(result.hb.hbid);		        			       
			        	$("#hb_select option[value='"+result.hb.hb_name+"']").attr("selected","selected");     
			        	
			        	$('.a_hb_time_a').val(result.hb.hb_time_a);
			        	$('.a_hb_time_b').val(result.hb.hb_time_b);
			        	$('.a_hb_time_c').val(result.hb.hb_time_c);
			        	$('.a_hb_time_d').val(result.hb.hb_time_d);
			           }
			        });  	
		      return false;
             }
//删除弹框环保
           function hb_delete(hbid){
                 $('.delhbid').val(hbid);
                 var h = $(document).height();
		         $('#screen').css({ 'height': h });	
		         $('#screen').show();
		         $('.popbox5').center();
		         $('.popbox5').fadeIn();
		         return false;
            }
$(document).ready(function(){

    $('#add_hb').click(function(){
//下拉框
		       $.ajax({    
		        url:"${basePath}/nature/nlist.shtml",			  
		        type:"post",			        		        
		        success:function(result){
		              $("#hb_select").find("option").remove(); 	            
		         var vnlist=result.nlist;
		       	 for(var i=0; i<vnlist.length; i++){
		       	    var option="";  
		             option+="<option value=\""+vnlist[i].vehiclenature_name+"\""; 
		             option += ">"+vnlist[i].vehiclenature_name+"</option>";    
                      $("#hb_select").append(option);         		  		       		               }	        									       				     				    		
		                }
		            });	
      });
    //添加环保收费
     $('.hb_ok_btn').click(function(){
                   var hbid=$('.a_hbid').val();
                   var stationid=$('.hb_stationid').val();             
                   if(hbid==""||hbid==undefined){
                    $.ajax({
			        url:"${basePath}/hb/addHb.shtml",
					data:$('.add_hb_form').serialize(),			 
					type:"post",			        		        
					success:function(result){
				    $('.popbox4').fadeOut(function(){ $('#screen').hide(); });
			           hb_change(stationid);
					      }
				    });	
				    }else{				  
				    $.ajax({
			        url:"${basePath}/hb/updateHb.shtml",
					data:$('.add_hb_form').serialize()+"&hbid="+hbid,			 
					type:"post",			        		        
					success:function(result){
				    $('.popbox4').fadeOut(function(){ $('#screen').hide(); });
			           hb_change(stationid);
					      }
				      });				   
				   } 				    		
          });
    
    //删除环保信息
    $('.del-ok-hb').click(function(){
           var hbid=$('.delhbid').val();
           var stationid=$('.del_hb_stationid').val();
            var data={hbid:hbid};
            $.ajax({
			url:"${basePath}/hb/deleteHb.shtml",
		    data:data,			 
		    type:"post",			        		        
		    success:function(result){
		    $('.popbox5').fadeOut(function(){ $('#screen').hide(); });
		       hb_change(stationid);	    
			      }
		     });
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
