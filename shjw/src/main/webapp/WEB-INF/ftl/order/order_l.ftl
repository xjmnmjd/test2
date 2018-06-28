<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>订单录入</title>
<link rel="stylesheet" type="text/css" href="${basePath}/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
</head>

<body>
        <div class="top">
            <p><img src="${basePath}/img/ico_qiz.png"/>当前位置：订单管理>订单录入</p>
            <a class="curr">订单录入</a>
        </div>
<div class="down">
<form id="addorder">
     <div class="information">
              <h3>客户基本信息</h3>
           <div class="list-group row">
                <div class="list-lef col-lg-5 col-md-5 col-sm-5">
                     <div class="row">
                       <span class="col-lg-4 col-md-4 col-sm-4"><em>*</em>车主姓名</span>
                       <div class="col-lg-7 col-md-7 col-sm-7"><input placeholder="请输入车主姓名" name="owner" class="owner"/></div>
                      </div>
                 </div>
                 <div class="list-rig col-lg-5 col-md-5 col-sm-5">
                      <div class="row">
                          <span class="col-lg-4 col-md-4 col-sm-4"><em>*</em>车牌号码</span>
                          <div class="col-lg-7 col-md-7 col-sm-7"><input placeholder="请输入车牌号" name="licenseplate" class="licenseplate"/></div>
                      </div>
                 </div>
                 <div class="list-lef col-lg-5 col-md-5 col-sm-5">
                      <div class="row">
                           <span class="col-lg-4 col-md-4 col-sm-4"><em>*</em>身份证号</span>
                            <div class="col-lg-7 col-md-7 col-sm-7"><input placeholder="请输入身份证号" name="identificationno" class="identificationno"/></div>
                      </div>
                 </div>
                 <div class="list-rig col-lg-5 col-md-5 col-sm-5">
                     <div class="row">
                            <span class="col-lg-4 col-md-4 col-sm-4"><em>*</em>手机号码</span>
                           <div class="col-lg-7 col-md-7 col-sm-7"><input placeholder="请输入手机号码" name="phonenumber" class="phonenumber"/></div>
                     </div>
                 </div>
          </div>
    </div>
    <div class="information">
     <h3>车辆信息</h3>
      <div class="list-group row">
         <div class="list-lef col-lg-5 col-md-5 col-sm-5">
             <div class="row">
                 <span class="col-lg-4 col-md-4 col-sm-4"><em>*</em>车辆性质</span>
                 <div class="col-lg-7 col-md-7 col-sm-7">
                 <select name="vehicle" id="vehicle">
                 <option value="0">请选择车辆性质</option>
                 <#if vnlist?exists && vnlist?size gt 0>
                 <#list vnlist as vn>
                 <option value="${vn.vehiclenature_name}">${vn.vehiclenature_name}</option>
                 </#list>
                 </#if>
                 </select>
                 </div>
             </div>
         </div>
         <div class="list-rig col-lg-5 col-md-5 col-sm-5">
             <div class="row">
                 <span class="col-lg-4 col-md-4 col-sm-4"><em>*</em>车辆类型</span>
                 <div class="col-lg-7 col-md-7 col-sm-7">
                   <select name="cartype" id="cartype"> 
                  <option value="0">请选择车辆类型</option>                    
                 <#if vtlist?exists && vtlist?size gt 0>
               <#list vtlist as vt>
                 <option value="${vt.vehicletype}">${vt.vehicletype}</option>
                 </#list>
                 </#if>
                 </select>
              
                 </div>
             </div>
         </div>
         <div class="list-lef col-lg-5 col-md-5 col-sm-5">
             <div class="row">
                 <span class="col-lg-4 col-md-4 col-sm-4"><em>*</em>注册日期</span>
                 <div class="col-lg-7 col-md-7 col-sm-7"><input type="text" placeholder="请输入注册日期" name="rigisterdate" class="demo-input" id="rigisterdate"/></div>
             </div>
         </div>
         <div class="list-rig col-lg-5 col-md-5 col-sm-5">
             <div class="row">
                 <span class="col-lg-4 col-md-4 col-sm-4"><em>*</em>强险到期时间</span>
                 <div class="col-lg-7 col-md-7 col-sm-7"><input type="text" placeholder="请输入强险到期时间" name="insurancedate" class="demo-input" id="insurancedate"/></div>
             </div>
         </div>
                 <div class="list-lef col-lg-5 col-md-5 col-sm-5">
             <div class="row">
                 <span class="col-lg-4 col-md-4 col-sm-4"><em>*</em>车&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp型</span>
                 <div class="col-lg-7 col-md-7 col-sm-7"><input type="text" placeholder="请输入车型"  name="models" class="models" id="models"/></div>
             </div>
         </div>
         <div class="list-rig col-lg-5 col-md-5 col-sm-5">
             <div class="row">
                 <span class="col-lg-4 col-md-4 col-sm-4"><em>*</em>车&nbsp&nbsp架&nbsp&nbsp号</span>
                 <div class="col-lg-7 col-md-7 col-sm-7"><input type="text" placeholder="请输入车架号"  name="vin_no" class="vin_no" id="vin_no"/></div>
             </div>
         </div>
                  <div class="list-lef col-lg-5 col-md-5 col-sm-5">
             <div class="row">
                 <span class="col-lg-4 col-md-4 col-sm-4"><em>*</em>发动机号</span>
                 <div class="col-lg-7 col-md-7 col-sm-7"><input type="text" placeholder="请输入发动机号"  name="engine_number" class="engine_number" id="engine_number"/></div>
             </div>
         </div>

    </div>
 </div>
 <div class="information">
     <h3>检车信息</h3>
     <div class="list-group row">
         <div class="list-all">
               <span><em>*</em>选择检测站</span>
                <select id="p_select"  onchange="changeProvince()"> 
                <option value="0">请选择省份</option>
               <#if slist?exists && slist?size gt 0>
                <#list slist as s>                                     
                   <option value="${s.station_province}">${s.station_province}</option>                  
               </#list>
               </#if>
             </select>
                <select id="area" onchange="changeArea()">
                 <option value="0">请选择区域</option></select>
                <select id="address" name="station_id">
                 <option value="0">请选择地址</option></select>
          
         </div>
         <div class="list-all">
             <span style="margin-left:90px"><em>*</em>上年是否发生人伤事故？</span>
             <div class="sex sf"><div class="choose-kf" ><i class="ico-radio ico-radio-1" style="margin-top:8px" id="isjt"></i>是</div><div class="choose-kf" style="margin:0"><i class="ico-radio ico-radio-1" style="margin-top:8px" id="noisjt"></i>否</div></div>
         </div>
         <div class="list-all">
             <span style="margin-left:90px"><em>*</em>是否需要上门收材料？</span>
             <div class="sex sf"><div class="choose-kf" ><i class="ico-radio ico-radio-1" style="margin-top:8px" id="issm"></i>是</div><div class="choose-kf" style="margin:0"><i class="ico-radio ico-radio-1" style="margin-top:8px" id="noissm"></i>否</div></div>
         </div>
        <div id="scl" style="display:none">
              <div class="list-lef col-lg-5 col-md-5 col-sm-5">
                 <div class="row">
                       <span class="col-lg-3 col-md-3 col-sm-3">选择时间：</span>
                       <div class="col-lg-7 col-md-7 col-sm-7"><input type="text" placeholder="请输入上门收材料时间" name="collect_materials_time" class="demo-input" id="collect_materials_time"/></div>
                 </div>
              </div>
              <div class="list-rig col-lg-5 col-md-5 col-sm-5">
                  <div class="row">
                        <span class="col-lg-3 col-md-3 col-sm-3">地址：</span>
                        <div class="col-lg-7 col-md-7 col-sm-7"><input  placeholder="请输入上门收材料地址" name="collect_materials_address" class="collect_materials_address"/></div>
                  </div>
              </div>
              <div class="list-lef col-lg-5 col-md-5 col-sm-5">
                 <div class="row">
                       <span class="col-lg-3 col-md-3 col-sm-3">寄件地址：</span>
                       <div class="col-lg-7 col-md-7 col-sm-7"><input type="text" placeholder="请输入寄件地址" name="send_material_address" class="send_material_address" id="send_material_address"/></div>
                 </div>
              </div>
        </div>
         <div class="list-all">
                <span style="margin-left:90px"><em>*</em>是否需要上门接送车？</span>
                <div class="sex sf"><div class="choose-kf" ><i class="ico-radio ico-radio-1" style="margin-top:8px" id="isjs"></i>是</div><div class="choose-kf" style="margin:0"><i class="ico-radio ico-radio-1" style="margin-top:8px" id="nojs"></i>否</div></div>
         </div>
             <div id="s_car" style="display:none">
                <div class="list-lef col-lg-5 col-md-5 col-sm-5">
                       <div class="row">
                          <span class="col-lg-3 col-md-3 col-sm-3" >选择时间</span>
                           <div class="col-lg-7 col-md-7 col-sm-7"><input type="text" placeholder="请输入上门送车时间" name="collect_car_time" class="demo-input" id="collect_car_time"/></div>
                       </div>
                </div>
                <div class="list-rig col-lg-5 col-md-5 col-sm-5">
                        <div class="row">
                            <span class="col-lg-3 col-md-3 col-sm-3" style="text-align:left;">地址</span>
                            <div class="col-lg-7 col-md-7 col-sm-7"><input placeholder="请输入上门送车地址" name="collect_car_address" class="collect_car_address"/></div>
                       </div>
                 </div>      
              </div> 
      <div class="list-all">
             <span><em>*</em>已&nbsp&nbsp付&nbsp&nbsp款</span>
              <div class="col-lg-7 col-md-7 col-sm-7"><input placeholder="请输入已付款" class="offlinepayment" name="offlinepayment" style="width:50%;border:1px solid #ddd;line-height:30px;"/></div>
         </div>
       <div class="list-all">
             <span><em>*</em>订单总额：</span>
              <div class="col-lg-7 col-md-7 col-sm-7"><input placeholder="请输入订单总额" name="totalorder" class="totalorder" style="width:50%;border:1px solid #ddd;line-height:30px;"/></div>
       </div>
       <div class="list-all">
             <span><em>*</em>支付方式：</span>
              <div class="col-lg-7 col-md-7 col-sm-7"><input placeholder="请输入支付方式" id="remark" class="remark" style="width:50%;border:1px solid #ddd;line-height:30px;"/></div>
       </div>
       <div class="list-all">
            <span><em>*</em>备注(材料)：</span>
              <div class="col-lg-7 col-md-7 col-sm-7"><textarea placeholder="请输入备注"  id="orderRemark" style="width:50%;border:1px solid #ddd;line-height:20px;float:left;height:200px;"></textarea>
       </div>
        
     </div>   
 </div>
 </form>
</div>
<a class="save" style="cursor:pointer">保存</a>
<script type="text/javascript" src="${basePath}/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${basePath}/js/common/layer/layer.js"></script>
<script type="text/javascript">
   window.onload = function(){
       $('#p_select option').each(function() {
                text = $(this).text();
                if($("#p_select option:contains("+text+")").length > 1){
                   $("#p_select option:contains("+text+"):gt(0)").remove();
                }
        });
        $('#area option').each(function() {
            text = $(this).text();
            if($("#area option:contains("+text+")").length > 1){
             $("#area option:contains("+text+"):gt(0)").remove();
            }
        }); 
  }
  
  function changeProvince(){
     var province=$('#p_select').val();
     var data={province:province};
           $.ajax({
		       url:"${basePath}/station/find_city.shtml",
		       data:data,			 
		       type:"post",			        		        
		          success:function(result){
		             var cityList=result.newList;
		             $('#area').empty();
		             if(cityList.length>0){
		                    $('#area').append("<option value='0'>请选择区域</option>");
		                    for(var i=0;i<cityList.length;i++){	              
		                       $('#area').append("<option value='"+cityList[i]+"'>"+cityList[i]+"</option>");
		                    }
		             }else{
		                $('#address').empty();
		               $('#area').append("<option value='0'>请选择区域</option>");
		               $('#address').append("<option value='0'>请选择地址</option>");     		               
		             }
		        }		            		            
	       }); 	      	   
  }   
  function changeArea(){ 
     var area=$('#area').val();
     var data={area:area};
           $.ajax({
		       url:"${basePath}/station/find_address.shtml",
		       data:data,			 
		       type:"post",			        		        
		          success:function(result){
		             var addressList=result.addressList;
		             $('#address').empty();
		             if(addressList.length>0){
		          	       $('#address').append("<option value='0'>请选择地址</option>");     
		                   for(var i=0;i<addressList.length;i++){
		                       $('#address').append("<option value='"+addressList[i].stationid+"'>"+addressList[i].station_address+"</option>");
		                   }
		              }else{
		                $('#address').append("<option value='0'>请选择地址</option>");     
		              
		              }
		        }
	       });  
  }
</script>
<script type="text/javascript">
$(document).ready(function(){
    $('.save').click(function(){
      var is_collect_materials=0;
      var is_collect_car=0;
      var injury_accident_last_year=0;
       if($('#isjt').hasClass('ico-radio ico-radio-2')){
         injury_accident_last_year=1;
       }
        if($('#noisjt').hasClass('ico-radio ico-radio-2')){
         injury_accident_last_year=0;
       }
        if($('#issm').hasClass('ico-radio ico-radio-2')){
         is_collect_materials=1;
       }
        if($('#noissm').hasClass('ico-radio ico-radio-2')){
         is_collect_materials=0;
       }
         if($('#isjs').hasClass('ico-radio ico-radio-2')){
         is_collect_car=1;
       }
          if($('#nojs').hasClass('ico-radio ico-radio-2')){
         is_collect_car=0;
       }
       //判断
       if($('.owner').val()==""||$('.owner').val()==null){
            alert("请填写车主信息");
            return false;
       }
       if($('.licenseplate').val()==""||$('.licenseplate').val()==null){
            alert("请填写车牌信息");
            return false;
       }
         var re = /^[\u4e00-\u9fa5]{1}[A-Z]{1}[A-Z_0-9]{5}$/;
         if($('.licenseplate').val().search(re)==-1){
            alert("车牌号格式不正确");
            return false;
        }
       if($('.identificationno').val()==""||$('.identificationno').val()==null){
            alert("请填写身份证信息");
            return false;
       }
         // 身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X  
            var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;  
             if(reg.test($('.identificationno').val()) === false)  
              {  
                 alert("身份证输入不合法");  
                  return  false;  
              }  
       if($('.phonenumber').val()==""||$('.phonenumber').val()==null){
            alert("请填写手机号");
            return false;
       }
       var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(17[0-9]{1})|(18[0-9]{1}))+\d{8})$/; 
               if(!myreg.test($('.phonenumber').val())) 
                  { 
                      alert('请输入有效的手机号码！'); 
                       return false; 
                  } 
       if($('#rigisterdate').val()==""||$('#rigisterdate').val()==null){
            alert("请填写注册日期");
            return false;
       }
        if($('#insurancedate').val()==""||$('#insurancedate').val()==null){
            alert("请填写交强险到期日期");
            return false;
       }
        if($('.models').val()==""||$('.models').val()==null){
            alert("请填写车型");
            return false;
       }
         if($('.vin_no').val()==""||$('.vin_no').val()==null){
            alert("请填写车架号");
            return false;
       }
       if($('.engine_number').val()==""||$('.engine_number').val()==null){
            alert("请填写发动机号");
            return false;
       }
       if($('#p_select').val()==0){
            alert("请选择省份");
            return false;
       }
       if($('#area').val()==0){
            alert("请选择区域");
            return false;
       }
        if($('#address').val()==0){
            alert("请选择地址");
            return false;
       }
       if($('#vehicle').val()==0){
            alert("请选择车辆性质");
            return false;
       }
       if($('#cartype').val()==0){
            alert("请选择车辆类型");
            return false;
       }
       if($('.totalorder').val()==""||$('.totalorder').val()==null){
            alert("请输入订单总额");
            return false;
       }
       
        if($('.offlinepayment').val()==""||$('.offlinepayment').val()==null){
            alert("请输入已付金额");
            return false;
       }
       if(isNaN($('.offlinepayment').val())){
           alert('已付金额请输入数字');
           return false;
        }
       if($('.totalorder').val()==""||$('.totalorder').val()==null){
            alert("请输入订单总额");
            return false;
       }
       if(isNaN($('.totalorder').val())){
           alert('订单总额请输入数字');
           return false;
        }
       var orderRemark=$("#orderRemark").val();
       if(orderRemark==""||orderRemark==undefined){
           alert('备注不能为空');
           return false;
       }
            var remark=$('#remark').val();     
             $.ajax({
		       url:"${basePath}/order/addOrder.shtml",
		       data:$('#addorder').serialize()+"&is_collect_materials="+is_collect_materials+"&injury_accident_last_year="+injury_accident_last_year+"&is_collect_car="+is_collect_car+"&remark="+remark+"&orderRemark="+orderRemark,				 
		       type:"post",			        		        
		       success:function(result){		       	       	       	   
	       	   	        $('.owner').val("");
	                    $('.licenseplate').val("");
	                    $('.identificationno').val("");
	                    $('.phonenumber').val("");
	                    $('#rigisterdate').val("");
	                    $('#insurancedate').val("");
	                    $('.totalorder').val("");
	                    $('.offlinepayment').val("");
	                    $('#remark').val("");
	                    $('.models').val("");
	                    $('.vin_no').val("");
	                    $('.engine_number').val("");
	                    $("#orderRemark").val("");
	                if($('#isjt').hasClass('ico-radio ico-radio-2')){
	                   $('#isjt').removeClass('ico-radio ico-radio-2').addClass('ico-radio ico-radio-1');
                      }
                    if($('#noisjt').hasClass('ico-radio ico-radio-2')){
	                   $('#noisjt').removeClass('ico-radio ico-radio-2').addClass('ico-radio ico-radio-1');
                      }
                     if($('#issm').hasClass('ico-radio ico-radio-2')){
	                   $('#issm').removeClass('ico-radio ico-radio-2').addClass('ico-radio ico-radio-1');
                      }
                     if($('#noissm').hasClass('ico-radio ico-radio-2')){
	                   $('#noissm').removeClass('ico-radio ico-radio-2').addClass('ico-radio ico-radio-1');
                      }
                       if($('#isjs').hasClass('ico-radio ico-radio-2')){
	                   $('#isjs').removeClass('ico-radio ico-radio-2').addClass('ico-radio ico-radio-1');
                     }
                      if($('#nojs').hasClass('ico-radio ico-radio-2')){
	                   $('#nojs').removeClass('ico-radio ico-radio-2').addClass('ico-radio ico-radio-1');
                      }
	                $("#p_select option[value='0']").attr("selected","selected");
	                $("#area option[value='0']").attr("selected","selected");
	                $("#address option[value='0']").attr("selected","selected");     
	                $("#vehicle option[value='0']").attr("selected","selected");
	                $("#cartype option[value='0']").attr("selected","selected"); 
	                      layer.msg("添加成功"); 
	                    //window.location.reload(true);
		       }		       
		        	
	        }); 

	      
	       
      });
      

});
</script>
<script>
$(function(){
    $('#issm').click(function(){
       $('#scl').css({"display":"block"});
    }); 
     $('#noissm').click(function(){
       $('#scl').css({"display":"none"});
    });
     $('#isjs').click(function(){
       $('#s_car').css({"display":"block"});
    }); 
     $('#nojs').click(function(){
       $('#s_car').css({"display":"none"});
    });   
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
<script>
$(function(){
	var Width=$(window).width();
	var Height=$(window).height();
	$('.all,.header').css({'width':Width});
	$('.all').css({'height':Height});
	$('.left').css({'height':Height-80});
	$('.right').css({'width':Width-160,'height':Height-140});
	$('.row').css({'width':'100%'});
	$('.list-group').css({'width':'100%'});
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

<script src="${basePath}/js/laydate/laydate.js"></script>
<script>
lay('#version').html('-v'+ laydate.v);

//执行一个laydate实例
laydate.render({
  elem: '#rigisterdate' //指定元素
});
laydate.render({
  elem: '#insurancedate' //指定元素
});
laydate.render({
  elem: '#collect_materials_time' //指定元素
});
laydate.render({
  elem: '#collect_car_time' //指定元素
});

</script>
</body>
</html>
