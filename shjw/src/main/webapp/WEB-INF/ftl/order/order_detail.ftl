<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>订单详情</title>
<link rel="stylesheet" type="text/css" href="${basePath}/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
 </style>
</head>

<body>   
  <div class="top">
          <a class="curr">订单详情</a>
          <a href="${basePath}/pay/fukuan.shtml?orderid=${order.orderid}">付款信息</a>
          <input value="${order.orderid}" style="display:none" class="all_order"/>
          <input value="${order.orderno}" style="display:none" class="order_no"/>
  </div>
  <div class="down">
         <#if order?exists>
          <div class="information">
               <h3>订单信息</h3>            
               <div class="list-group row">            
                   <div class="list-lef col-lg-5 col-md-5 col-sm-5">
                       <p>订单编号：<span>${order.orderno?default('未设置')}</span></p>
                   </div> 
                   <div class="list-rig col-lg-5 col-md-5 col-sm-5">
                       <p>提交时间：<span>${order.submittime?default('未设置')}</span></p>
                   </div>                
                   <div class="list-lef col-lg-5 col-md-5 col-sm-5">
                       <p>检测站名称：${order.station?default('未设置')}</span></p>
                   </div>
                   <#if station?exists>                
                   <div class="list-rig col-lg-5 col-md-5 col-sm-5">
                       <p>检测站地址:<span>${station.station_province?default('')}${station.station_area?default('')}${station.station_address?default('')}</span></p>
                   </div>
                  </#if>
                 <#if order.is_collect_car==1>  
                   <div class="list-lef col-lg-5 col-md-5 col-sm-5">
                       <p>上门接收车时间：<span>${order.collect_car_time?default('未设置')}</span></p>
                   </div>
                   <div class="list-rig col-lg-5 col-md-5 col-sm-5">
                       <p>上门接收车地址：<span>${order.collect_car_address?default('未设置')}</span></p>
                   </div>                   
                  </#if>
                   <#if order.is_collect_materials==1>  
                   <div class="list-lef col-lg-5 col-md-5 col-sm-5">
                       <p>上门接收材料时间：<span>${order.collect_materials_time?default('未设置')}</span></p>
                   </div>
                   <div class="list-rig col-lg-5 col-md-5 col-sm-5">
                       <p>上门接收材料地址：<span>${order.collect_materials_address?default('未设置')}</span></p>
                   </div> 
                   </#if>
                                    
              </div>
              
        </div>
        <form id="detail_form">
        <div class="information">
               <h3>客户基本信息</h3>
               <div class="list-group row">
                          <div class="lef col-lg-5 col-md-5 col-sm-5">
                          <p>
                            <img style="height:100%;width:400px" src="${order.identificationpath?default('${basePath}/img/mr.png')}"/> 
                         
                          </p></div>
                   <div class="rig col-lg-5 col-md-5 col-sm-5">
                      <#if user_qx=1>
                          <div class="list"><em>车主姓名</em><p><input type="text"  name="owner" value="${order.owner?default('未设置')}"/></p></div>
                          <div class="list"><em>车牌号码</em><p><input type="text" name="licenseplate" value="${order.licenseplate?default('未设置')}"/></p></div>
                          <div class="list"><em>身份证号</em><p><input type="text" name="identificationno" value="${order.identificationno?default('未设置')}"/></p></div>
                          <div class="list"><em>手机号码</em><p class="tel"><input name="phonenumber" type="text" value="${order.phonenumber?default('未设置')}"/></p><a class="send">发送信息</a></div>
                       <#else>
                          <div class="list"><em>车主姓名</em><p>${order.owner?default('未设置')}</p></div>
                          <div class="list"><em>车牌号码</em><p>${order.licenseplate?default('未设置')}</p></div>
                          <div class="list"><em>身份证号</em><p>${order.identificationno?default('未设置')}</p></div>
                          <div class="list"><em>手机号码</em><p class="tel"><input name="phonenumber" type="text" value="${order.phonenumber?default('未设置')}"/></p><a class="send">发送信息</a></div>
                       </#if>
                   </div>
              </div>
      </div>
      <div class="information">
               <h3>车辆信息</h3>
               <div class="list-group row">
                   <div class="lef col-lg-5 col-md-5 col-sm-5">
                   <p>
                  <img style="height:100%;width:400px" src="${order.carpath?default('${basePath}/img/mr.png')}"/>                              
                   </p></div>
                   <div class="rig col-lg-5 col-md-5 col-sm-5">
                   <#if user_qx=1>
                       <div class="list"><em class="w">车辆性质</em><p class="tel" style="width:280px"><input name="vehicle" type="text" value="${order.vehicle?default('未设置')}"/></p></div>
                       <div class="list"><em class="w">车辆类型</em><p class="tel" style="width:280px"><input name="cartype" type="text" value="${order.cartype?default('未设置')}"/></p></div>
                       <div class="list"><em class="w">注册日期</em><p class="tel" style="width:280px"><input name="rigisterdate" type="text" value="${order.rigisterdate?default('未设置')}"/></p></div>
                       <div class="list"><em class="w">强险到期时间</em><p class="tel" style="width:280px"><input name="insurancedate" type="text" value="${order.insurancedate?default('未设置')}"/></p></div>
                        <div class="list"><em class="w">车型</em><p class="tel" style="width:280px"><input name="models" type="text" value="${order.models?default('未设置')}"/></p></div>
                       <div class="list"><em class="w">车架号</em><p class="tel" style="width:280px"><input name="vin_no" type="text" value="${order.vin_no?default('未设置')}"/></p></div>
                       <div class="list"><em class="w">发动机号</em><p class="tel" style="width:280px"><input name="engine_number" type="text" value="${order.engine_number?default('未设置')}"/></p></div>
                       <div class="list"><em class="w">备注(材料)</em><p class="tel" style="width:280px"><input name="engine_number" type="text" value="${order.orderRemark?default('未设置')}"/></p</div>
                   <#else>
                       <div class="list"><em class="w">车辆性质</em><p class="tel" style="width:280px">${order.vehicle?default('未设置')}</p></div>
                       <div class="list"><em class="w">车辆类型</em><p class="tel" style="width:280px">${order.cartype?default('未设置')}</p></div>
                       <div class="list"><em class="w">注册日期</em><p class="tel" style="width:280px">${order.rigisterdate?default('未设置')}</p></div>
                       <div class="list"><em class="w">强险到期时间</em><p class="tel" style="width:280px">${order.insurancedate?default('未设置')}</p></div>
                       <div class="list"><em class="w">车型</em><p class="tel" style="width:280px">${order.models?default('未设置')}</p></div>
                       <div class="list"><em class="w">车架号</em><p class="tel" style="width:280px">${order.vin_no?default('未设置')}</p></div>
                       <div class="list"><em class="w">发动机号</em><p class="tel" style="width:280px">${order.engine_number?default('未设置')}</p></div>
                       <div class="list"><em class="w">备注(材料)</em><p class="tel" style="width:280px">${order.orderRemark?default('未设置')}</p</div>
                   </#if>  
                   </div>
             </div>
      </div>
      <#if order.suditstatus==4>
      <#if order.is_collect_materials==1>
       <div class="information">
          <h3>取件通知
          <a style="float:right; margin-right:10px;cursor:pointer" class="fs_message">发送短信</a>
          </h3>
          <div class="list-group row">        
               <div class="list-lef col-lg-5 col-md-5 col-sm-5">
                     <p>上门收材料时间:<span>${order.collect_materials_time?default('未设置')}</span></p>
               </div>
               <div class="list-rig col-lg-5 col-md-5 col-sm-5">
                    <p>上门收材料地址:<span>${order.collect_materials_address?default('未设置')}</span></p>
               </div>
         </div>
       </div>
      </#if>
      </#if>
      <#if order.suditstatus==10||order.suditstatus==11>
       <div class="information">
          <h3>寄件通知
          <a style="float:right; margin-right:10px;cursor:pointer" class="jj_message">发送短信</a>
          </h3>
          <div class="list-group row">        
               <div class="list-lef col-lg-5 col-md-5 col-sm-5">
                     <p>寄件地址:<span>${order.send_material_address?default('')}</span></p>
               </div>
         </div>
       </div>
      </#if>
      <#else>
       <div class="information">
          <h3>未找到订单</h3>
       </div>
       </#if>
        <div class="information">
                <h3>违章图片上传                
               <a style="float:right; margin-right:10px;cursor:pointer" class="open_wz">添加</a></h3>
                <div id="wz_img">
                 <#if wzimgList?exists && wzimgList?size gt 0>
                      <#list wzimgList as wz>
                        <img src="${wz.img_path}"/>                                       
                      </#list>
                        <#else>				 
			              <span class="text-center danger" style="margin-left:420px">暂时无违章图片</span>				            
			      </#if> 
			      </div>                                                                 
      </div>
 </div>
 </form> 
         <#if user_qx=1>
         <a class="save" style='cursor:pointer'>保存</a>
        </#if>
  <!--弹出层开始-->
<div id="screen"></div>
<div class="popbox1" style="width:600px;height:480px;overflow-y:hidden">
	<div class="mainlist">
        <h2>上传图片<a class="close-btn1">&times;</a></h2>
                <form method="post"  action="${basePath}/order/upload.shtml" enctype="multipart/form-data" onsubmit="return check()">      
                 <div id="open_img" style="margin-left:5px;"> 
                 <a style="position:relative;width:450px;;height:300px;margin-left: 70px">                       
                 <img src="${basePath}/img/add_wz.png" id="open_wzpng" style="max-width:450px;max-height:300px;"/>
                 <input style="width:450px;height:300px;display:inline-block;position:absolute;top:10px;opacity:0" type="file" class="uploadfile" id="myFile" name="uploadfile" onchange="c()"/>                                                                                  
                 <input value="${order.orderno?default('')}" style="display:none" name="order_no"/>
                 <a style="width:100%;text-align:center;margin:20px">
                 <input type="submit" value="上传" style="margin-left:10px;width:100px;height:40px;background:#01b386;color:#fff;">
                 <input type="button" value="取消" style="width:100px;height:40px;background:#ddd;" class="close-btn1"></a>
                 </div>      
           </form>                                                                                                                                                            v               
	</div>
</div>

<!--取件发送短信弹出层开始-->
<div id="screen"></div>
<div class="popbox2">
	<div class="mainlist">
        <h2>取件通知<a class="close-btn2" style='cursor:pointer'>&times;</a></h2>
		 <ul>
		    <li style="display:none"><span>订单号：</span><input value="${order.orderno}" class="order_no"/></li>
		    <li style="display:none><span>取件时间：</span><input value="${order.collect_materials_time}" class="collect_materials_time"/></li>
			<li><span>快递员：</span><input class="courier_name"/></li> 
			<li><span>快递员电话：</span><input class="courier_phone"/></li>             
		</ul>
        <div class="btn"><div><a class="close-btn2" style='cursor:pointer'>关闭</a>
        <a class="email_ok_btn" style='cursor:pointer;margin-left:15px'>确定</a></div></div>
	</div>
</div>
<!--弹出层结束-->  
<!--寄件发送短信弹出层开始-->
<div id="screen"></div>
<div class="popbox">
	<div class="mainlist">
        <h2>寄件通知<a class="close-btn" style='cursor:pointer'>&times;</a></h2>
		 <ul>
		    <li style="display:none"><span>订单号：</span><input value="${order.orderno}" class="j_order_no"/></li>
			<li><span>快递单号：</span><input class="j_courier_no"/></li>             
			             
		</ul>
        <div class="btn"><div><a class="close-btn" style='cursor:pointer'>关闭</a>
        <a class="jj_ok_btn" style='cursor:pointer;margin-left:15px'>确定</a></div></div>
	</div>
</div>
<!--弹出层结束--> 

<!--图片放大弹框-->
<div id="screen"></div>
<div class="popbox3" style="width:100%;height:100%">
	<div class="mainlist">
        <h2 style="height:30px;line-height:30px;"><a class="close-btn3" style='cursor:pointer'>&times;</a></h2>
           <img src="${basePath}/img/mr.png"  style="margin-top:0px" class="pop_img"/>
	</div>
</div>
<!--弹出层结束-->      
 <script src="${basePath}/js/main.js"></script>
 <script type="text/javascript" src="${basePath}/js/jquery-1.8.3.min.js"></script>
 <script type="text/javascript" src="${basePath}/js/common/layer/layer.js"></script>
 
 <script type="text/javascript">
  $(function(){ 
   var imgs=document.getElementsByTagName('img');
    for(var i in imgs){
        imgs[i].onclick=function(){
          var h = $(document).height();
		  $('#screen').css({ 'height': h });	
		  $('#screen').show();
		  $('.popbox3').center();
		  $('.popbox3').fadeIn(); 
          var t=$(this).prop("src");
          $(".pop_img").attr("src",t);

		  return false; 
        }
    }    
  });
</script>
<script type="text/javascript">
$(function(){

//图片弹出
    $('.open_wz').click(function(){
        var h = $(document).height();
		$('#screen').css({ 'height': h });	
		$('#screen').show();
		$('.popbox1').center();
		$('.popbox1').fadeIn();
		return false;  
    });
    
    $('.fs_message').click(function(){
        var h = $(document).height();
		$('#screen').css({ 'height': h });	
		$('#screen').show();
		$('.popbox2').center();
		$('.popbox2').fadeIn();
		return false;  
    });
    $('.jj_message').click(function(){
        var h = $(document).height();
		$('#screen').css({ 'height': h });	
		$('#screen').show();
		$('.popbox').center();
		$('.popbox').fadeIn();
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
		$('.close-btn3').click(function(){
		$('.popbox3').fadeOut(function(){ $('#screen').hide(); });
		return false;
	});
	$('.close-btn').click(function(){
		$('.popbox').fadeOut(function(){ $('#screen').hide(); });
		return false;
	});
	$('.email_ok_btn').click(function(){
	  var order_no=$('.order_no').val();
	  var courier_name=$('.courier_name').val();
      var courier_phone=$('.courier_phone').val();
	  var collect_materials_time=$('.collect_materials_time').val();
	  var data={order_no:order_no,courier_name:courier_name,courier_phone:courier_phone,collect_materials_time:collect_materials_time};
	          $.ajax({
		           url:"${basePath}/order/fs_email.shtml",
			      data:data,			 
			      type:"post",			        		        
			        success:function(result){
			           layer.msg("发送成功"); 
			            $('.popbox2').fadeOut(function(){ $('#screen').hide(); });
			        } 
	          });	     
	});
		$('.jj_ok_btn').click(function(){
	      var order_no=$('.j_order_no').val();
          var courier_no=$('.j_courier_no').val();
	      var data={order_no:order_no,courier_no:courier_no};
	             $.ajax({
		            url:"${basePath}/order/jj_email.shtml",
			        data:data,			 
			        type:"post",			        		        
			        success:function(result){
			       	    layer.msg("发送成功");  		           	   			       
			            $('.popbox').fadeOut(function(){ $('#screen').hide(); });
			        }
	            });
	   });
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

<script type="text/javascript">
$(function(){
	var Width=$(window).width();
	var Height=$(window).height();
	$('.all,.header').css({'width':Width});
	$('.all').css({'height':Height});
	$('.left').css({'height':Height-80});
	$('.right').css({'width':Width-160,'height':Height-140});
	$('.row').css({'width':'100%'});
	$('.list-group').css({'width':Width-300,'margin':'20px auto'});
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


<script type="text/javascript">
    function check(){
        var file=document.getElementById("myFile").value;
        if(file==""){
            alert("请选择需要上传的文件！");
            return false;
        }else{
         
        }
    }
    jQuery(document).ready(function() {
         $('.save').click(function(){
              var orderid=$('.all_order').val();
               $.ajax({
		          url:"${basePath}/order/update_Order.shtml",
		          data:$('#detail_form').serialize()+"&orderid="+orderid,				 
		          type:"post",			        		        
		          success:function(result){
		              layer.msg("修改成功");  		           	   
				   location.href="${basePath}/order/order_detail.shtml?orderid="+orderid;    
		          }
	          }); 
         });

    });
</script>
<script type="text/javascript">
function c () {
var r= new FileReader();
f=document.getElementById('myFile').files[0];
r.readAsDataURL(f);
r.onload=function  (e) {
document.getElementById('open_wzpng').src=this.result;
};
}
</script>
</body>
</html>
