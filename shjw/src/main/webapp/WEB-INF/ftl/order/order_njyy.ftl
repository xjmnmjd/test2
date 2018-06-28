<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>订单管理-预约年检</title>
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
<link href="${basePath}/css/page.css" type="text/css" rel="stylesheet"/>
</head>
<style>  
    #oDiv2 div{width: 100px;height: 100px;border:1px solid #c50000;}  
.ts{font-size:4px;color:red}</style>
<body>
        <div class="top">
            <p><img src="${basePath}/img/ico_qiz.png"/>当前位置：订单管理>已付款订单>预约年检<span id="clocks">2017-08-01 11：29 星期二</span></p>
       <a href="${basePath}/order/orderList.shtml">全部</a>       
       <#if user.position="系统管理员"||user.position="主管">
       <a href="${basePath}/order/order_nfp.shtml">
               <#if ox.fp_count gt 0>  
                                     待分配<span class="ts">(${ox.fp_count})</span>
               <#else>待分配</#if></a>
       </#if>
       <a href="${basePath}/order/order_njs.shtml">
               <#if ox.js_counts gt 0>  
                                        待接受<span class="ts">(${ox.js_counts})</span>
                <#else>待接受</#if></a>
       <a href="${basePath}/order/order_wzcx.shtml" >
              <#if ox.wz_counts gt 0>  
                                 违章查询<span class="ts">(${ox.wz_counts})</span>
             <#else>违章查询 </#if></a>
       <a href="${basePath}/order/order_zlsj.shtml" >
              <#if ox.zl_counts gt 0>  
                                 资料收集<span class="ts">(${ox.zl_counts})</span>
             <#else>资料收集 </#if></a>
         <#if user.position="客服员">
       <#else>
       <a href="${basePath}/order/order_wtbl.shtml">
              <#if ox.wt_counts gt 0>  
                                    委托办理<span class="ts">(${ox.wt_counts})</span>
              <#else>委托办理</#if></a> 
       </#if>
              <a href="${basePath}/order/order_yynj.shtml" class="curr">
                  <#if ox.yy_counts gt 0>  
                                   预约年检<span class="ts">(${ox.yy_counts})</span>
              <#else>预约年检</#if></a>
       <a href="${basePath}/order/order_jfqr.shtml" >
               <#if ox.jf_counts gt 0>  
                                   缴费确认<span class="ts">(${ox.jf_counts})</span>
              <#else>缴费确认</#if></a>
          <a href="${basePath}/order/order_nj.shtml">
                   <#if ox.nj_counts gt 0>  
                                   年检<span class="ts">(${ox.nj_counts})</span>
              <#else>年检</#if></a> 
         </div>
        <div class="down">
            <div class="search">
               <div><input  value="${yynj_search?default('')}" placeholder="输入车主姓名或车牌号进行查询" class="search_value"/><a style="cursor:pointer" class="search_yynj">查询</a></div><a class="jq-search" style="cursor:pointer">精确查询<i class="ico-jt ico-jt-1"></i></a>
                        <a class="shuaxin" style="color:#000000;cursor:pointer;float:right">刷新</a>          
 <a class="shuaxin" style="cursor:pointer;float:right;margin-right: 2px">
            <img src="${basePath}/img/shuaxin.png" style=" width:20px;height:20px;"/></a>
            </div>
          <#if status="0">
             <div class="cx-result">
          <#else>
             <div class="cx-result" style="display:block">
          </#if>           
           <form  action="${basePath}/order/order_yynj.shtml?status=1" method="post">
               <div class="choose">                           
                  <input placeholder="车主姓名" name="owner" class="owner" value="${xorder.owner?default('')}"/>
                  <input placeholder="车牌号码" name="licenseplate" class="licenseplate" value="${xorder.licenseplate?default('')}"/>
                  <input placeholder="手机号码" name="phonenumber" class="phonenumber" value="${xorder.phonenumber?default('')}"/>
                   <input class="demo-input" value="${tj_start?default('')}" id="test1" name="tj_start" type="text" placeholder="选择提交开始时间" style="margin-right:0" /><img src="${basePath}/img/line.png"/>
                   <input class="demo-input" value="${tj_end?default('')}" name="tj_end" placeholder="选择提交结束时间" type="text" id="test2"/>                 
                   <input class="demo-input" value="${yy_start?default('')}" name="yy_start" type="text" placeholder="选择预约开始时间" style="margin-right:0" id="test3"/><img src="${basePath}/img/line.png"/>
                   <input class="demo-input" value="${yy_end?default('')}" name="yy_end" placeholder="选择预约结束时间" type="text" id="test4"/>
               </div>  
               <a style="left:94%;top:0px"><input style="cursor:pointer" value="查询" type="submit" class="jq_search_ok"/></a>
            </form>
            </div>
            <table width="100%" border="0">
                  <tr>                 
                    <th scope="col">提交时间</th>
                    <th scope="col">车主</th>
                    <th scope="col">车牌</th>
                    <th scope="col">类型</th>
                    <th scope="col">是否缴费</th>
                    <th scope="col">业务类型</th>
                    <th scope="col">预约时间</th>
                    <th scope="col">预约监测站</th>
                    <th scope="col">客服员</th>
                    <th scope="col">订单状态</th>
                    <th scope="col">审核状态</th>
                    <th scope="col">订单来源</th>
                    <th scope="col" colspan='2'>操作</th>
                  </tr>
          <#if yylist?exists && yylist?size gt 0>
          <#list yylist as order>
                 <tr>
                    <td>${order.submittime?default('')}</td>
                    <td>${order.owner?default('')}</td>
                    <td>${order.licenseplate?default('')}</td>
                    <td>${order.cartype?default('')}</td>
                     <#if order.ispayment=0>
                    <td>未缴费</td>
                    <#else>
                    <td>已经缴费</td>
                    </#if>
                    <td>${order.businesstype?default('')}</td>
                    <td>${order.appointmenttime?default('')}</td>
                    <td>${order.station?default('')}</td>
                    <td>${order.customer?default('')}</td>
                    <td>已支付</td>                                           
                    <td>预约年检中</td>              
                    <td>${order.ordersource?default('')}</td>
                    <td><a style='cursor:pointer;width:100%;text-align:center' href="${basePath}/order/order_detail.shtml?orderid=${order.orderid}">详情</a></td>
                    <td><a style='cursor:pointer;width:100%;text-align:center' onclick="yynj_btn(${order.orderid})">预约</a>
                    <a style='cursor:pointer;width:100%;text-align:center'  onclick='js_print(${order.orderid})'>打印</a>
                    </td>
                  </tr>
            </#list>
             <#else>
				 <tr>
			        <td class="text-center danger" colspan="15">未找到预约年检订单</td>
				</tr>
			    </#if> 
                </table>
                 <input style="display:none" value="${pageIndex}" class="page_i"/>
           <#if s_status==0> 
    <ul class="page"  id="page">
       <li><a href="${basePath}/order/order_yynj.shtml?pageIndex=1">首页</a></li>
       <li>
       <#if pageIndex-1=0>
            <a href="${basePath}/order/order_yynj.shtml?pageIndex=1">上一页</a>
       <#else>
            <a href="${basePath}/order/order_yynj.shtml?pageIndex=${pageIndex-1}">上一页</a>
       </#if> 
       </li>    
      
              <#if total<5 && total gt 0>            
              <#list 1..total as i>    
          <li id="${i}"><a href="${basePath}/order/order_yynj.shtml?pageIndex=${i}">${i}</a></li>
              </#list>
             <#elseif total=0>
            <li id="1"><a href="${basePath}/order/order_yynj.shtml?pageIndex=1">1</a></li>
              <#else>
                     <#if pageIndex<5>
                         <#list 1..4 as i>    
                         <li id="${i}"><a href="${basePath}/order/order_yynj.shtml?pageIndex=${i}">${i}</a></li>
                         </#list>
                     <#else>
                        <#list pageIndex-3..pageIndex as i>    
                         <li id="${i}"><a href="${basePath}/order/order_yynj.shtml?pageIndex=${i}">${i}</a></li>
                        </#list>
                     </#if>           
        
              </#if> 
             <li>  
               <#if pageIndex gte total>
               <a href="${basePath}/order/order_yynj.shtml?pageIndex=${pageIndex}">下一页</a>
              <#else>
             <a href="${basePath}/order/order_yynj.shtml?pageIndex=${pageIndex+1}">下一页</a>
             </#if>
             </li>
         <#if total=0>
          <li><a href="${basePath}/order/order_yynj.shtml?pageIndex=${total+1}">末页</a></li>
          <#else>
          <li><a href="${basePath}/order/order_yynj.shtml?pageIndex=${total}">末页</a></li>          
          </#if>     
          
          </ul>
                        <#else>
           <p style="font-size:16px;color:#FF8040;text-align:center">成功为您找到${yylist?size}款符合的订单</p> 
          </#if>
   </div>
   <input type="text" class="bhao"/>
<!--预约年检弹出层开始-->
<div id="screen"></div>
<div class="popbox">
	<div class="mainlist">
        <h2>预约年检<a class="close-btn" style='cursor:pointer'>&times;</a></h2>
		<ul>		    
		    <li style="display:none"><input class="yy_orderid" /></li>		  
			<li><span>选择时间</span><input type="text" class="demo-input" id="yy_time" placeholder="选择预约时间"/></li>
            <li><span>选择省份</span><select  id="p_select"  onchange="changeProvince()"  style="width:200px;margin-left: 14px"><option value="0">请选择省份</option></select></li>
            <li><span>选择区域</span><select  id="area" onchange="changeArea()" style="width:200px;margin-left: 14px"> <option value="0">请选择区域</option></select></li>
            <li><span>选择地址</span><select  id="address" style="width:200px;margin-left: 14px"><option value="0">请选择地址</option></select></li>
		</ul>
        <div class="btn"><div><a class="close-btn" style='cursor:pointer'>关闭</a>
        <a class="yynj_ok_btn" style='cursor:pointer;margin-left:15px'>确定</a></div></div>
	</div>
</div>
<!--弹出层结束-->

<!--打印弹出层开始-->
<div id="screen"></div>
<div class="popbox1" style="height:90%;overflow-x:hidden;overflow-y:auto;position:absolute;top:5%">
	<div class="mainlist">
	  <h2>打印凭据<a class="close-btn" style='cursor:pointer'>&times;</a></h2>
		<ul>
		    <li style="display:none"><span>姓名</span><input type="text" class="orderid" readonly="readonly"/></li>
		    <li><span>姓名</span><input type="text" class="owner" readonly="readonly"/></li>
            <li><span>车牌</span><input type="text" class="licenseplate" readonly="readonly"/></li> 
            <li><span>联系电话</span><input type="text" class="phonenumber" readonly="readonly"/></li> 		    
		    <li><span>总费用</span><input type="text" class="copetotal" readonly="readonly"/></li>		  
			<li><span>已付费用</span><input type="text" class="payment_amount" readonly="readonly"/></li>
            <li><span>未付用用</span><input type="text" class="nopay" readonly="readonly"/></li>
            <li><span>材料收集</span><input type="text" class="clsj"/></li>
            <li><span>备注</span><textarea class='text' id="d_text"></textarea></li>
		</ul>
        <div class="btn"><div><a class="close-btn1" style='cursor:pointer'>关闭</a>
        <a class="print_ok_btn" style='cursor:pointer;margin-left:15px'>确定</a></div></div>
	</div>
</div>
<!--弹出层结束-->
<script type="text/javascript" src="${basePath}/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${basePath}/js/page.js"></script>
<script type="text/javascript" src="${basePath}/js/common/layer/layer.js"></script>
<script type="text/javascript">
    function removePv(){
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
		                       $('#address').append("<option value='"+addressList[i].station_no+"'>"+addressList[i].station_address+"</option>");
		                   }
		              }else{
		                $('#address').append("<option value='0'>请选择地址</option>");     
		              
		              }
		        }
	       });  
  }
$(document).ready(function(){
   var page_i=$('.page_i').val();
   $('#'+page_i).addClass("curru");

   $('.search_yynj').click(function(){
       var yynj_search=$('.search_value').val();   
       location.href="${basePath}/order/order_yynj.shtml?yynj_search="+encodeURI(encodeURI(yynj_search));           
   });
   
   $('.shuaxin').click(function(){
   location.href="${basePath}/order/order_yynj.shtml";
   });

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
$(document).ready(function(){
    $('.print_ok_btn').click(function(){ 
     var owner=$('.owner').val();
     var licenseplate=$('.licenseplate').val();
     var phonenumber=$('.phonenumber').val();
     var copetotal=$('.copetotal').val();
     var payment_amount=$('.payment_amount').val();
     var nopay=$('.nopay').val();
     var clsj=$('.clsj').val();
     var d_text=$('#d_text').val(); 
     var oPop = window.open('','oPop');
     var bh=$('.bhao').val();
        var str = '<!DOCTYPE html>'  
            str +='<html>'  
            str +='<head>'  
            str +='<meta charset="utf-8">'  
            str +='<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">'  
            str+='<style>';  
            str+='#oDiv2 div{width:800px;height:450px;border:1px solid #666666;}'+
	              'ul{margin-left:30px}'+  
	              'ul li{ margin-top:25px; list-style:none; float:left; margin-left:20px}'+
	              ' span{width:200px;border:1px solid #666666;font-size:16px; height:40pz; padding:3px}'+
	              'input{ margin-left:18px;font-size:16px}'+
	              '.owner{ margin-left:18px}'+
	              '.copetotal{ margin-left:18px}'+
	              '.licenseplate{ margin-left:18px}'+
	             '.clsj{ width:500px;margin-left:18px}'+
	              '.text{width:500px;height:150px;float:right;line-height:30px; border:1px solid #ddd;font-size:16px;padding: 0 5px;margin-left:18px}';            
            str+='</style>';  
            str +='</head>'  
            str +='<body>'  
            str +="<div id='oDiv2'><div><ul><li><span>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名</span><input type='text' value='"+owner+"' class='owner'/></li>"+
    "<li><span>车&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;牌</span><input type='text' value='"+licenseplate+"' class='licenseplate'/></li>"+ 
	"</ul><br><ul><li><span>总&nbsp;&nbsp;费&nbsp;用</span><input value='"+copetotal+"' type='text' class='copetotal' /></li>"+
    "<li><span>联系电话</span><input value='"+phonenumber+"' type='text' class='phonenumber'/></li>"+ 		    
    "</ul><br><ul><li><span>已付费用</span><input value='"+payment_amount+"' type='text' class='payment_amount'/></li>"+
    "<li><span>未付费用</span><input value='"+nopay+"' type='text' class='nopay'/></li>"+
	"</ul><br><ul><li><span>页面编码</span><input type='text' value='"+bh+"'/></li><br>"+ 
    "<li><span>材料收集</span><input value='"+clsj+"' type='text' class='clsj'/></li>"+
    "<li><span>备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注</span><textarea  class='text'>"+d_text+"</textarea></li></ul>"+
    "</div></div>";  
            str +='</body>'  
            str +='</html>'  
  
        oPop.document.write(str);  
        oPop.print();  
        oPop.close();
	  });    
});
//打印弹出
   function js_print(orderid){  
        var h = $(document).height();
		$('#screen').css({ 'height': h });	
		$('#screen').show();
		$('.popbox1').center();
		$('.popbox1').fadeIn(); 
		var data={orderid:orderid};
		  $.ajax({
		    url:"${basePath}/order/order_detail2.shtml",
		    data:data,			 
		    type:"post",
		     async : false,			        		        
		      success:function(result){	
		         var offlinepayment=result.order.offlinepayment;
		         var cash_fee=result.order.cash_fee;
		         var copetotal=result.order.copetotal;
		         if(offlinepayment==null){
		           offlinepayment=0.00;
		         }
		         if(cash_fee==null||cash_fee==undefined){
		           cash_fee=0;
		         }else{
		         cash_fee=cash_fee/100;
		         }
		         if(copetotal==null){
		           copetotal=0.00;
		         }
		         var payment_amount=cash_fee+offlinepayment;	         
		         var nopay=copetotal-offlinepayment-cash_fee;    		   		      
		         $('.owner').val(result.order.owner);
		         $('.licenseplate').val(result.order.licenseplate);
		         $('.phonenumber').val(result.order.phonenumber); 
		         $('.copetotal').val(copetotal); 
		         $('.payment_amount').val(payment_amount); 
		         $('.nopay').val(nopay);
		         $('.orderid').val(result.order.orderid);
		         $('#d_text').val(result.order.orderRemark);	         		         	      	         		         	         		         	      	         
		      }  
	      });
	           	     
        $.ajax({
		     url:"${basePath}/order/find_dy.shtml",
		     data:data,			 
		     type:"post",			        		        
		      success:function(result){
		      $('.bhao').val(result.message);		    
		     }
	     });	
	  return false;						
   }
    //预约年检弹出
   function yynj_btn(orderid){
        var data={orderid:orderid};    
        $('.yy_orderid').val(orderid);
        var h = $(document).height();
		$('#screen').css({ 'height': h });	
		$('#screen').show();
		$('.popbox').center();
		$('.popbox').fadeIn();   
          $.ajax({
		     url:"${basePath}/order/listStation.shtml",			 
		     type:"post",			        		        
		     success:function(result){	  
		        $('#p_select').empty(); 
		         var option="<option value='0'>请选择省份</option>";  	        
		         var listStation=result.listStation;		      
		         if(listStation.length>0){	         		           
		            for(var i=0;i<listStation.length;i++){		    
		                  option+="<option value='"+listStation[i].station_province+"'>"+listStation[i].station_province+"</option>";
		             }                 
		          }
		           $('#p_select').append(option);
		           removePv();
		      }
	     });

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
	
	//预约年检确定
    $('.yynj_ok_btn').click(function(){
       var yy_time=$('#yy_time').val();
       var yy_station=$('#address').val();
       var orderid=$('.yy_orderid').val();
         if(yy_time==""||yy_time==undefined){ 
          alert("预约时间不能为空");   
              return false;
          }     
      var data={orderid:orderid,yy_time:yy_time,yy_station:yy_station};
         $.ajax({
		   url:"${basePath}/order/order_yyok.shtml",
		   data:data,			 
		   type:"post",			        		        
		   success:function(result){
		      layer.msg("预约年检成功");
	       	  $('.popbox').fadeOut(function(){ $('#screen').hide(); });
	           setTimeout(function(){window.location.href="${basePath}/order/order_yynj.shtml?pageIndex="+$('.page_i').val();},1000);
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
  elem: '#test1' //指定元素
});
laydate.render({
  elem: '#test2' //指定元素
});
laydate.render({
  elem: '#test3' //指定元素
});
laydate.render({
  elem: '#test4' //指定元素
});
laydate.render({
  elem: '#yy_time' //指定元素
});
</script>
</body>
</html>
