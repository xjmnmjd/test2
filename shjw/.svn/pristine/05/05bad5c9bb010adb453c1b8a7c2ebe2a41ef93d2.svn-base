<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>付款信息</title>
<link rel="stylesheet" type="text/css" href="${basePath}/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
<link href="${basePath}/css/page.css" type="text/css" rel="stylesheet"/>

<script type="text/javascript" src="${basePath}/js/common/layer/layer.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery-1.8.3.min.js"></script>
</head>

<body>
       <div class="top">
          <a href="${basePath}/order/order_detail.shtml?orderid=${order.orderid}">订单详情</a><a class="curr">付款信息</a>
        </div>
<div class="down">        
      <div class="information">
               <h3>付款信息</h3>
               <div style="width:600px;margin-left:300px">
<table style="width:100%">
    <thead>
    <tr>
        <th colspan="2">订单总额：￥ ${order.copetotal?default('0.00')}</th>
        <th>已付款：￥ ${order.offlinepayment?default('0.00')+order.cash_fee?default('0.00')?number/100}</th>
        <th style="color:red">未付款</th>
        <th>支出总额</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td rowspan="4">初始订单总额：￥ ${order.totalorder?default('0.00')}</td>
        <td>委托费用：￥ ${order.wt_fee?default('0.00')}</td>
        <td>线上支付：￥${order.cash_fee?default('0.00')?number/100}</td>
        <td style="color:red">￥ ${(order.copetotal-order.offlinepayment-order.cash_fee?default('0.00')?number/100)?default('0.00')}</td>
        <td>：￥${order.pay?default('0.00')}</td>
    </tr>
    <tr>
        <td>年检费用：￥ ${order.nj_fee?default('0.00')}</td>
        <td>线下支付总额：￥ ${order.offlinepayment?default('0.00')}</td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>尾气费用：￥ ${order.wq_fee?default('0.00')}</td>
        <td></td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td>环保检测费用：￥ ${order.hb_nj_fee?default('0.00')}</td>
        <td></td>
        <td></td>
        <td></td>
    </tr>
    </tbody>
</table>
               </div>
       </div>
             <div class="information">           
               <h3>订单总额
               <a style="float:right; margin-right:10px;cursor:pointer" class="open_yf">添加</a>
               </h3>
               <div class="list-group row">
                   <table width="100%" border="0">
                      <tr>
                        <th scope="col">时间</th>
                        <th scope="col">金额</th>
                        <th scope="col">支付种类</th>
                        <th scope="col">说明</th>
                        <th scope="col">客服员</th>
                        <th scope="col" colspan='2'>操作</th>
                      </tr>
                   <#if yf_list?exists && yf_list?size gt 0>
                        <#list yf_list as yf>
                            <tr>                          
                              <td>${yf.pay_time?default('未设置')}</td>
                              <td>${yf.pay_money?default('未设置')}</td>
                              <#if yf.pay_kind==1>                                                           
                              <td>委托服务费</td>
                              <#elseif yf.pay_kind==2>
                              <td>上线检测费</td>
                              <#elseif yf.pay_kind==3>
                              <td>违章费</td>
                              <#elseif yf.pay_kind==4>
                              <td>余款</td>
                              <#elseif yf.pay_kind==5>
                              <td>其他</td>
                              <#else>
                              <td>其他</td>
                              </#if>
                              <td>${yf.remark?default('未设置')}</td>
                              <td>${yf.customer?default('未设置')}</td>
                              <#if user_qx=1>                               
                              <td><a style='cursor:pointer;width:100%;text-align:center' onclick="open_update_yf(${yf.id})">修改</a></td>
                              <td><a style='cursor:pointer;width:100%;text-align:center' onclick="open_delete_yf(${yf.id})">删除</a></td>
                              <#else>
                             <td colspan='2'>禁止操作</td>                           
                              </#if>
                             </tr>
                          </#list>
                        <#else>
				             <tr>
			                    <td class="text-center danger" colspan="7" style="color:#00bc8d">请添加订单总额</td>
				              </tr>
			             </#if> 
                    </table>
              </div>
       </div>
      <div class="information">
             
               <h3>线下支付
               <a style="float:right; margin-right:10px;cursor:pointer" class="open_xx">添加</a>
               </h3>
               <div class="list-group row">
                   <table width="100%" border="0">
                      <tr>
                        <th scope="col">时间</th>
                        <th scope="col">金额</th>
                        <th scope="col">支付种类</th>
                        <th scope="col">说明</th>
                        <th scope="col">客服员</th>
                        <th scope="col" colspan='2'>操作</th>
                      </tr>
                   <#if xx_list?exists && xx_list?size gt 0>
                        <#list xx_list as xx>
                            <tr>                          
                              <td>${xx.pay_time?default('未设置')}</td>
                              <td>${xx.pay_money?default('未设置')}</td>
                              <#if xx.pay_kind==1>                                                           
                              <td>委托服务费</td>
                              <#elseif xx.pay_kind==2>
                              <td>上线检测费</td>
                              <#elseif xx.pay_kind==3>
                              <td>违章费</td>
                              <#elseif xx.pay_kind==4>
                              <td>余款</td>
                              <#elseif xx.pay_kind==5>
                              <td>其他</td>
                              <#else>
                              <td>其他</td>
                              </#if>
                              <td>${xx.remark?default('未设置')}</td>
                              <td>${xx.customer?default('未设置')}</td>
                              <#if user_qx=1>                               
                              <td><a style='cursor:pointer;width:100%;text-align:center' onclick="open_update_xx(${xx.id})">修改</a></td>
                              <td><a style='cursor:pointer;width:100%;text-align:center' onclick="open_delete_xx(${xx.id})">删除</a></td>                              
                              <#else>
                             <td colspan='2'>禁止操作</td>                           
                              </#if>
                             </tr>
                          </#list>
                        <#else>
				             <tr>
			                    <td class="text-center danger" colspan="7" style="color:#00bc8d">请添加线下支付</td>
				              </tr>
			             </#if> 
                    </table>
              </div>
       </div>
       <div class="information">
                   <h3>支出               
                   <a style="float:right; margin-right:10px;;cursor:pointer" class="open_zf">添加</a>
                   </h3>
                   <div class="list-group row">
                       <table width="100%" border="0">
                          <tr>
                            <th scope="col">时间</th>
                            <th scope="col">金额</th>
                            <th scope="col">支付种类</th>
                            <th scope="col">说明</th>
                            <th scope="col">客服员</th>
                            <th scope="col" colspan='2'>操作</th>
                          </tr>
                            <#if zf_list?exists && zf_list?size gt 0>
                        <#list zf_list as zf>
                            <tr>                          
                              <td>${zf.pay_time?default('未设置')}</td>
                              <td>${zf.pay_money?default('未设置')}</td>
                              <#if zf.pay_kind==1>                                                           
                              <td>委托服务费</td>
                              <#elseif zf.pay_kind==2>
                              <td>上线检测费</td>
                              <#elseif zf.pay_kind==3>
                              <td>违章费</td>
                              <#elseif zf.pay_kind==4>
                              <td>余款</td>
                             <#elseif zf.pay_kind==5>
                              <td>其他</td>
                              <#else>
                              <td>其他</td>
                              </#if>
                              <td>${zf.remark?default('未设置')}</td>
                              <td>${zf.customer?default('未设置')}</td>
                             <#if user_qx=1>                               
                              <td><a style='cursor:pointer;width:100%;text-align:center' onclick="open_update_zf(${zf.id})">修改</a></td>                                                      
                              <td><a style='cursor:pointer;width:100%;text-align:center' onclick="open_delete_zf(${zf.id})">删除</a></td>                                                                         
                              <#else>
                             <td colspan='2'>禁止操作</td>                           
                              </#if>
                             </tr>
                          </#list>
                        <#else>
				             <tr>
			                    <td class="text-center danger" colspan="7" style="color:#00bc8d">请添加支出</td>
				              </tr>
			             </#if> 
                        </table>
       </div>
           <#if status=1>
               <input style="display:none" class="fu_orderid" value="${order.orderid}"/>
              <div class="send-jf"><a class="a1" style="float:right;cursor:pointer;background-color:#00bc8d"    
              >缴费确认</a></div>
           </#if> 
       <div class="information">
               <h3>发票信息
                <#if bill?exists>
              <a style="float:right; margin-right:10px;;cursor:pointer" class="upOpen_fp">修改</a>
                <#else>
               <a style="float:right; margin-right:10px;;cursor:pointer" class="open_fp">添加</a>
               </#if>
               </h3>
               <div class="list-group row">
                   <#if bill?exists>
                        <div class="list-lef col-lg-5 col-md-5 col-sm-5">
                        <#if bill.bill_type=="company">
                           <p>发票类型:<span>公司</span></p>
                           <#else>
                             <p>发票类型:<span>${bill.bill_type?default('未设置')}</span></p>
                             </#if>
                        </div>                                
         
                        <div class="list-rig col-lg-5 col-md-5 col-sm-5">
                              <p>纳税人识别号:<span>${bill.taxpayer_identification_number?default('未设置')}</span></p>
                        </div>
                         <div class="list-lef col-lg-5 col-md-5 col-sm-5">
                               <p>寄送地址:<span>${bill.mailing_address?default('未设置')}</span></p>
                         </div>
                         <div class="list-rig col-lg-5 col-md-5 col-sm-5">
                               <p>联系电话:<span>${bill.contact_number?default('未设置')}</span></p>
                         </div>  
                         <div class="list-lef col-lg-5 col-md-5 col-sm-5">
                              <p>发票抬头:<span>${bill.bill_head?default('未设置')}</span></p>
                        </div>             
                   <#else>
                         <h6 style='width:100%;text-align:center;color:#00bc8d'>暂无发票信息</h6>
                   </#if>
                </div>
         </div>
</div>
<!--线下支付弹出层开始-->
<div id="screen"></div>
<div class="popbox1" style="height:90%;overflow-x:hidden;overflow-y:auto;position:absolute;top:5%">
	<div class="mainlist">
        <h2>线下支付<a class="close-btn1" style='cursor:pointer'>&times;</a></h2>
        <form id="pay_form">
        <ul>
            <li style="display:none"><input class="xx_id"/></li>
		    <li style="display:none"><input class="xx_orderid" name="orderid" value="${order.orderid?default('未设置')}"/></li>
		    <li style="display:none"><input class="xx_userid" name="userid" value="${token.id}"/></li>
		    <li style="display:none"><input class="xx_customer" name="customer" value="${token.nickname}"/></li>
			<li><span>支付金额:</span><input class="xx_money" name="pay_money"/></li>
		    <li><span>支付种类:</span><select class="xx_pay_kind" name="pay_kind" style="margin-left:45px;width:180px">
		    <option value="1">委托服务费</option>
		    <option value="2">上线检测费</option>
		    <option value="3">违章费</option>
		    <option value="4">余款</option>
		    <option value="5">其他</option>
		    </select></li>
		   <li style="display:none">修改之前的金额:<input type="text" class="xx_money2"/></li>
		<li><span>填写支付说明:</span> <textarea class="text" id="xx_text" name="remark"></textarea></li>		
		</ul>
        </form>
        <div class="changeCause_xx" style="display:none">
          <p style="font-size:15px; color:#666;float:left;margin-left:20px;margin-top:100px">请填写删除理由:</p>
         <textarea class="text" id="xx_change_text" style="width:400px;margin-right:60px;height:120px"></textarea>
        </div>
        <div class="btn"><div><a class="close-btn1">关闭</a><a class="pay_ok_btn" id="pay_ok_btn" flag="1" style="margin-left:15px">确定</a></div></div>
	</div>
</div>
<!--弹出层结束-->

<!--支出弹出层开始-->
<div id="screen"></div>
<div class="popbox2" style="height:80%;overflow-x:hidden;overflow-y:auto;position:absolute;top:5%">
	<div class="mainlist">
        <h2>支出<a class="close-btn2" style='cursor:pointer'>&times;</a></h2>
        <form id="zf_form">
        <ul>
            <li style="display:none"><input class="zf_id"/></li>    
		    <li style="display:none"><input class="orderid" name="orderid" value="${order.orderid?default('未设置')}"/></li>
		    <li style="display:none"><input class="userid" name="userid" value="${token.id}"/></li>
		    <li style="display:none"><input class="customer" name="customer" value="${token.nickname}"/></li>
			<li><span>支付金额</span><input type="text" class="zf_money" name="pay_money"/></li>
			
			<li style="display:none"><span>修改支付金额</span><input type="text" class="zf_money2"/></li>
			
			<li><span>支付种类</span><select  class="zf_pay_kind" name="pay_kind" style="margin-left:45px;width:180px">
		    <option value="1">委托服务费</option>
		    <option value="2">上线检测费</option>
		    <option value="3">违章费</option>
		    <option value="4">余款</option>
		    <option value="5">其他</option>
		    </select></li>
			<li><span>支出说明</span><textarea class="text" id="zf_text" name="remark"></textarea></li>
		</ul>
        </form>
         <div class="changeCause_zf" style="display:none">
          <p style="font-size:15px; color:#666;float:left;margin-left:20px;margin-top:100px">请填写删除理由:</p>
         <textarea class="text" id="zf_change_text" style="width:400px;margin-right:60px;height:120px"></textarea>
        </div>
        <div class="btn"><div><a class="close-btn2">关闭</a><a class="zf_ok_btn" flag="1" style="margin-left:15px">确定</a></div></div>
	</div>
</div>
<!--弹出层结束-->
<!--订单总额弹出层开始-->
<div id="screen"></div>
<div class="popbox" style="height:90%;overflow-x:hidden;overflow-y:auto;position:absolute;top:5%">
	<div class="mainlist">
        <h2>订单总额<a class="close-btn" style='cursor:pointer'>&times;</a></h2>
        <form id="yf_form">
        <ul>
            <li style="display:none"><input class="yf_id"/></li>    
		    <li style="display:none"><input class="yf_orderid" name="orderid" value="${order.orderid?default('')}"/></li>
		    <li style="display:none"><input class="yf_userid" name="userid" value="${token.id?default('')}"/></li>
		    <li style="display:none"><input class="yf_customer" name="customer" value="${token.nickname?default('')}"/></li>
			<li><span>支付金额</span><input type="text" class="yf_money" name="pay_money"/></li>
			<li style="display:none"><span>支付金额</span><input type="text" class="yf_money2"/></li>
			<li><span>支付种类</span><select  class="yf_pay_kind" name="pay_kind" style="margin-left:15px;width:199px">
		    <option value="1">委托服务费</option>
		    <option value="2">上线检测费</option>
		    <option value="3">违章费</option>
		    <option value="4">余款</option>
		    <option value="5">其他</option>
		    </select></li>
			<li><span>支出说明</span><textarea class="text" id="yf_text" name="remark"></textarea></li>
		</ul>
        </form>
         <div class="changeCause_yf" style="display:none">
          <p style="font-size:15px; color:#666;float:left;margin-left:20px;margin-top:100px">请填写删除理由:</p>
         <textarea class="text" id="yf_change_text" style="width:400px;margin-right:60px;height:120px"></textarea>
        </div>
        <div class="btn"><div><a class="close-btn">关闭</a><a class="yf_ok_btn" flag="1" style="margin-left:15px">确定</a></div></div>
	</div>
</div>
<!--弹出层结束-->

<!--弹出层开始-->
<div id="screen"></div>
<div class="popbox4">
	<div class="mainlist">
        <h2>开票<a class="close-btn4" style='cursor:pointer'>&times;</a></h2>
        <form id="fp_form">
        <ul>
             <li style="display:none"><input id="id"/></li>
             <li style="display:none"><input class="orderno" name="orderno" value="${order.orderno}"/></li>
            <li><span>发票抬头类型:</span><select id="bill_type" onchange="change_bType()" name="bill_type" style="width:193px;margin-left:60px">
               <option value="person">个人</option>
               <option value="company">公司</option>
            </select></li>    
		    <li><span>发票抬头:</span><input id="bill_head" name="bill_head"/></li>
		    <li class="company"><span>纳税人识别号:</span><input  id="taxpayer_identification_number" name="taxpayer_identification_number"/></li>
		    <li class="company"><span>公司地址:</span><input  id="company_address" name="company_address"/></li>
			<li class="company"><span>公司电话:</span><input  type="text" id="company_phone" name="company_phone"/></li>
		    <li class="company"><span>开户行:</span><input  id="opening_bank" name="opening_bank"/></li>
		    <li class="company"><span>银行账号:</span><input  id="bank_no" name="bank_no"/></li>
		    <li><span>寄送地址:</span><input id="mailing_address" name="mailing_address"/></li>
			<li><span>联系电话:</span><input type="text" id="contact_number" name="contact_number"/></li>
		</ul>
        </form>
        <div class="btn"><div><a class="close-btn4">关闭</a><a class="fp_ok_btn" style="margin-left:15px">确定</a></div></div>
	</div>
</div>
<!--弹出层结束-->
<!--删除弹出层开始-->
<div id="screen"></div>
<div class="popbox3">
	<div class="mainlist">
        <h2>是否删除<a class="close-btn3"  style='cursor:pointer'>&times;</a></h2>
        <input type="text" class="delPayid" style="display:none"/>
        <input type="text" class="delStatus" style="display:none"/>
         <p style="font-size:18px; color:#666;float:left;margin-left:20px">请填写删除理由</p>
         <textarea class="text" id="change_text" style="width:500px;margin-right:60px;height:120px"></textarea>     
        <div class="btn"><div><a class="close-btn3" style='cursor:pointer'>关闭</a>
        <a class="del-ok-btn" style='cursor:pointer;margin-left:15px'>确定</a></div></div>
	</div>
</div>
<!--弹出层结束-->
<script type="text/javascript">

$(document).ready(function(){
//发票弹出
   $('.open_fp').click(function(){
        var h = $(document).height();
		$('#screen').css({ 'height': h });	
		$('#screen').show();
		$('.popbox4').center();
	    $(".company").css("display", "none");
		$('.popbox4').fadeIn();
                    $(".id").val("");
			     	$(".bank_no").val("");
					$(".bill_head").val("");
					$(".bill_type").val("");
					$(".company_address").val("");
					$(".company_phone").val("");
					$(".contact_number").val("");
					$(".mailing_address").val("");
					$(".opening_bank").val("");
					$(".taxpayer_identification_number").val("");
								
       return false;
   });
   $('.close-btn4').click(function(){
   		$('.popbox4').fadeOut(function(){ $('#screen').hide(); });
		return false;
   });
   $('.upOpen_fp').click(function(){
         var h = $(document).height();
		  $('#screen').css({ 'height': h });	
		  $('#screen').show();
		  $('.popbox4').center();
		  $('.popbox4').fadeIn();
		   var orderno=$('.orderno').val();
		  var data={orderno:orderno};
		   $.ajax({
		      url:"${basePath}/order/find_bill.shtml",
			  data:data,			 
			  type:"post",			        		        
			     success:function(data){
			        $("#id").val(data.bill.id);
			     	$("#bank_no").val(data.bill.bank_no);
					$("#bill_head").val(data.bill.bill_head);
					$("#bill_type option[value='"+data.bill.bill_type+"']").attr("selected","selected");     
					$("#company_address").val(data.bill.company_address);
					$("#company_phone").val(data.bill.company_phone);
					$("#contact_number").val(data.bill.contact_number);
					$("#mailing_address").val(data.bill.mailing_address);
					$("#opening_bank").val(data.bill.opening_bank);
					$("#taxpayer_identification_number").val(data.bill.taxpayer_identification_number);	
					
				   if (data.bill.bill_type=="company") {
						$(".company").css("display", "block");
					}else {
						$(".company").css("display", "none");
				    }	
				    $("#bill_type").attr("disabled","disabled");	    
			     }
	       });	
        return false;
   });
    //添加修改发票信息
     var edit_type="add";
    $('.fp_ok_btn').click(function(){
        var id=$('.id').val(); 
          if(id==""||id==undefined){ 
               $.ajax({
		         url:"${basePath}/open/addOrUpdateBill.shtml",
			     data:$('#fp_form').serialize()+"&edit_type="+edit_type,			 
			     type:"post",			        		        
			       success:function(result){
			         $('.popbox4').fadeOut(function(){ $('#screen').hide(); });
			         window.location.reload(true);			      
			        }
	          });
	      }else{
	         edit_type = "update";
	        $.ajax({
		         url:"${basePath}/open/addOrUpdateBill.shtml",
			     data:$('#fp_form').serialize()+"&edit_type="+edit_type,			 
			     type:"post",			        		        
			       success:function(result){
			        $('.popbox4').fadeOut(function(){ $('#screen').hide(); });
			        window.location.reload(true);			      
			        }
	          });
	       
	      }	
    });
});

  function change_bType(){
					if ($('#bill_type').val()=="company") {
						$(".company").css("display", "block");
					} else {
						$(".company").css("display", "none");
					}
  }
  
  //修改应付金额弹框
    function open_update_yf(id){
        var h = $(document).height();
		$('#screen').css({ 'height': h });	
		$('#screen').show();
		$('.popbox').center();
		$('.popbox').fadeIn();
		$('.changeCause_yf').css('display','block');		
		var data={id:id};
		    $.ajax({
		      url:"${basePath}/pay/findone_pay.shtml",
			  data:data,			 
			  type:"post",			        		        
			     success:function(result){
			    	     $('.yf_money').val(result.pay.pay_money);
			    	     $('#yf_text').val(result.pay.remark);
			    	     $('.yf_id').val(result.pay.id);
			    	     $(".yf_money2").val(result.pay.pay_money);
			             $(".yf_pay_kind option[value='"+result.pay.pay_kind+"']").attr("selected","selected");
			    	     
			     }
	       });	
		return false;  
    }
//修改线下支付弹框
    function open_update_xx(id){
        var h = $(document).height();
		$('#screen').css({ 'height': h });	
		$('#screen').show();
		$('.popbox1').center();
		$('.popbox1').fadeIn();
		$('.changeCause_xx').css('display','block');
		
		var data={id:id};
		    $.ajax({
		      url:"${basePath}/pay/findone_pay.shtml",
			  data:data,			 
			  type:"post",			        		        
			     success:function(result){
			    	     $('.xx_money').val(result.pay.pay_money);
			    	     $('#xx_text').val(result.pay.remark);
			    	     $('.xx_id').val(result.pay.id);
			    	     $(".xx_money2").val(result.pay.pay_money);
			             $(".xx_pay_kind option[value='"+result.pay.pay_kind+"']").attr("selected","selected");
			    	     
			     }
	       });	
		return false;  
    }
 //修改支出弹框
    function open_update_zf(id){
        var h = $(document).height();
		$('#screen').css({ 'height': h });	
		$('#screen').show();
		$('.popbox2').center();
		$('.popbox2').fadeIn();
		$('.changeCause_zf').css('display','block');
		
		var data={id:id};
		    $.ajax({
		      url:"${basePath}/pay/findone_pay.shtml",
			  data:data,			 
			  type:"post",			        		        
			     success:function(result){
			    	     $('.zf_money').val(result.pay.pay_money);
			    	     $('#zf_text').val(result.pay.remark);
			    	     $('.zf_id').val(id);
			    	     $(".zf_money2").val(result.pay.pay_money);
			             $(".zf_pay_kind option[value='"+result.pay.pay_kind+"']").attr("selected","selected");
			    	     
			     }
	       });	
		return false;  
    }
    //删除订单总额弹框
    function open_delete_yf(id){
        var h = $(document).height();
		$('#screen').css({ 'height': h });	
		$('#screen').show();
		$('.popbox3').center();
		$('.popbox3').fadeIn();
		$('.delPayid').val(id);
		$('.delStatus').val(1);
		return false; 
    }
    //删除线下支付弹框
    function open_delete_xx(id){
        var h = $(document).height();
		$('#screen').css({ 'height': h });	
		$('#screen').show();
		$('.popbox3').center();
		$('.popbox3').fadeIn();
	    $('.delPayid').val(id);
		$('.delStatus').val(2);
		return false; 
    }
    //删除支出弹框
    function open_delete_zf(id){
        var h = $(document).height();
		$('#screen').css({ 'height': h });	
		$('#screen').show();
		$('.popbox3').center();
		$('.popbox3').fadeIn();
		$('.delPayid').val(id);
		$('.delStatus').val(3);
		return false; 
    }
  $(function(){

   //缴费确认
   $('.a1').click(function(){
       var orderid=$('.fu_orderid').val();
       var data={orderid:orderid};
		    $.ajax({
		      url:"${basePath}/pay/order_jfqr_nj.shtml",
			  data:data,			 
			  type:"post",			        		        
			      success:function(result){	
			         alert(result.message);
			         if(result.message=="缴费成功"){		   		   
			             location.href="${basePath}/order/order_nj.shtml";
			          }else{
			      	     location.href="${basePath}/order/sp_order.shtml?status=15";
			            $("#order_sp", window.parent.document).click();
			          }			  
			     }
	       });	
   });
   //添加线下
   $('.open_xx').click(function(){
        var h = $(document).height();
		$('#screen').css({ 'height': h });	
		$('#screen').show();
		$('.popbox1').center();
		$('.popbox1').fadeIn();
		      $('.xx_money').val('');
			  $('#xx_text').val('');
			  $('.xx_id').val('');
			  $(".xx_money2").val('');
			  $(".xx_pay_kind option[value='1']").attr("selected","selected");
		return false;  
    });
    //添加应付总额
    $('.open_yf').click(function(){
        var h = $(document).height();
		$('#screen').css({ 'height': h });	
		$('#screen').show();
		$('.popbox').center();
		$('.popbox').fadeIn();
		      $('.yf_money').val('');
			  $('#yf_text').val('');
			  $('.yf_id').val('');
			  $(".yf_money2").val('');
			  $(".yf_pay_kind option[value='1']").attr("selected","selected");
		return false;  
    });
    //添加支出
    $('.open_zf').click(function(){
        var h = $(document).height();
		$('#screen').css({ 'height': h });	
		$('#screen').show();
		$('.popbox2').center();
		$('.popbox2').fadeIn();
			  $('.zf_money').val('');
			  $('#zf_text').val('');
			  $('.zf_id').val('');
			  $(".zf_money2").val('');
			  $(".zf_pay_kind option[value='1']").attr("selected","selected");
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
	$('.close-btn').click(function(){
		$('.popbox').fadeOut(function(){ $('#screen').hide(); });
		return false;
	});
	 $('.close-btn3').click(function(){
   		$('.popbox3').fadeOut(function(){ $('#screen').hide(); });
		return false;
   });	
	//添加修改应付总额
	$('.yf_ok_btn').click(function(){
        var flag=$('.yf_ok_btn').attr("flag");
        if(flag==2){
        	return;
        }
        $('.yf_ok_btn').attr("flag",2);
        $('.yf_ok_btn').css("background-color","#ddd");
	    var id=$('.yf_id').val();
	    var pay_yf= $(".yf_money2").val();
	    if(id==""||id==undefined){
	        $.ajax({
		       url:"${basePath}/pay/add_yf.shtml",
			   data:$('#yf_form').serialize(),			 
			   type:"post",			        		        
			     success:function(result){
			       $('.yf_money').val('');
			       $('#yf_text').val('');
			       $('.yf_id').val('');
			       $(".yf_money2").val('');
			       $(".yf_pay_kind option[value='1']").attr("selected","selected");
				     $('.popbox').fadeOut(function(){ $('#screen').hide(); });
                     $('.yf_ok_btn').attr("disabled","disabled");					     
				     window.location.reload(true);
			        }, 
                    error: function(){ 
                        $('.yf_ok_btn').attr("flag",1);
                        $('.yf_ok_btn').css("background-color","#00bc8d");                       		     
                        alert("操作失败");
                    } 
	        });
	        }else{
	           var changeCause=$('#yf_change_text').val();
	             if(changeCause==''||changeCause==undefined){
	                 alert("请输入修改理由");
	                 return false;
	             } 
	           $.ajax({
		       url:"${basePath}/pay/update_pay.shtml",
			   data:$('#yf_form').serialize()+"&id="+id+"&pay_yf="+pay_yf+"&changeCause="+changeCause,			 
			   type:"post",			        		        
			     success:function(result){
			          $('.yf_money').val('');
			          $('#yf_text').val('');
			          $('.yf_id').val('');
			          $(".yf_money2").val('');
			          $(".yf_pay_kind option[value='1']").attr("selected","selected");
				      $('.popbox').fadeOut(function(){ $('#screen').hide(); });
                      $('.yf_ok_btn').attr("disabled","disabled");					     				     
				      window.location.reload(true);
			        }, 
                    error: function(){ 
                        $('.yf_ok_btn').attr("flag",1);
                        $('.yf_ok_btn').css("background-color","#00bc8d");                       		     
                        alert("操作失败");
                    } 
	         });
	        }
	});	
	
	//添加修改线下支付
	$('#pay_ok_btn').click(function(){
		var flag=$('#pay_ok_btn').attr("flag");
        if(flag==2){
        	return;
        }
        $('#pay_ok_btn').attr("flag",2);
        $('#pay_ok_btn').css("background-color","#ddd");	   
	    var id=$('.xx_id').val();
	    var pay_xx2= $(".xx_money2").val();
	    if(id==""||id==undefined){
	        $.ajax({
		       url:"${basePath}/pay/add_pay.shtml",
			   data:$('#pay_form').serialize(),			 
			   type:"post",			        		        
			     success:function(result){
		            $('.xx_money').val('');
			        $('#xx_text').val('');
			        $('.xx_id').val('');
			        $(".xx_money2").val('');
			        $(".xx_pay_kind option[value='1']").attr("selected","selected");			     
				        $('.popbox1').fadeOut(function(){ $('#screen').hide(); });
                        $('#pay_ok_btn').attr("disabled","disabled");				     
				        window.location.reload(true);
			        }, 
                    error: function(){ 
                        $('#pay_ok_btn').attr("flag",1);
                        $('#pay_ok_btn').css("background-color","#00bc8d");                       		     
                        alert("操作失败");
                    } 
	        });
	        }else{
	          var changeCause=$('#xx_change_text').val();
	          if(changeCause==''||changeCause==undefined){
	           alert("请输入修改理由");
	           return false;
	          } 
	           $.ajax({
		       url:"${basePath}/pay/update_pay.shtml",
			   data:$('#pay_form').serialize()+"&id="+id+"&pay_xx2="+pay_xx2+"&changeCause="+changeCause,			 
			   type:"post",			        		        
			     success:function(result){
		                   $('.xx_money').val('');
			               $('#xx_text').val('');
			               $('.xx_id').val('');
			               $(".xx_money2").val('');
			               $(".xx_pay_kind option[value='1']").attr("selected","selected");			     
				     $('.popbox1').fadeOut(function(){ $('#screen').hide(); });					     
				     window.location.reload(true);
			        }, 
                    error: function(){ 
                        $('.pay_ok_btn').attr("flag",1);
                        $('.pay_ok_btn').css("background-color","#00bc8d");                       						     
                        alert("操作失败");
                    } 
	         });
	        }	       
	});
    //添加修改支出
	$('.zf_ok_btn').click(function(){
        var flag=$('.zf_ok_btn').attr("flag");
        if(flag==2){
        	return;
        }
        $('.zf_ok_btn').attr("flag",2);
        $('.zf_ok_btn').css("background-color","#ddd");	 	   
	   var id=$('.zf_id').val();
	   var zf_money2=$(".zf_money2").val();
	    if(id==""||id==undefined){
	         $.ajax({
		        url:"${basePath}/pay/add_zf.shtml",
			    data:$('#zf_form').serialize(),			 
			    type:"post",			        		        
			    success:function(result){
              $('.zf_money').val('');
			  $('#zf_text').val('');
			  $('.zf_id').val('');
			  $(".zf_money2").val('');
			  $(".zf_pay_kind option[value='1']").attr("selected","selected");			    
				     $('.popbox2').fadeOut(function(){ $('#screen').hide(); });
				      $('.zf_ok_btn').attr("disabled","disabled");				     
				     window.location.reload(true);
			        }, 
                    error: function(){ 
                        $('.zf_ok_btn').attr("flag",1);
                         $('.zf_ok_btn').css("background-color","#00bc8d");                       						     
                        alert("操作失败");
                    } 
	       });
	    }else{
	   	        var changeCause=$('#zf_change_text').val();
	             if(changeCause==''||changeCause==undefined){
	                 alert("请输入修改理由");
	                 return false;
	             } 
	        $.ajax({
		       url:"${basePath}/pay/update_pay.shtml",
			   data:$('#zf_form').serialize()+"&id="+id+"&zf_money2="+zf_money2+"&changeCause="+changeCause,				 
			   type:"post",			        		        
			   success:function(result){
			      $('.zf_money').val('');
			      $('#zf_text').val('');
			      $('.zf_id').val('');
			      $(".zf_money2").val('');
			      $(".zf_pay_kind option[value='1']").attr("selected","selected");
				     $('.popbox2').fadeOut(function(){ $('#screen').hide(); });
				     $('.zf_ok_btn').attr("disabled","disabled");				     
				      window.location.reload(true);
			        }, 
                    error: function(){ 
                        $('.zf_ok_btn').attr("flag",1);
                         $('.zf_ok_btn').css("background-color","#00bc8d");                       						     
                        alert("操作失败");
                    } 
	       });
	   } 
	});
	//删除确定
	$('.del-ok-btn').click(function(){
		 $('.del-ok-btn').attr("disabled", true);
	     var payid=$('.delPayid').val();
	     var status=$('.delStatus').val();
	     var changeCause=$('#change_text').val();
	     var data={payid:payid,status:status,changeCause:changeCause};
		 $.ajax({
		    url:"${basePath}/pay/deletePay.shtml",
			data:data,			 
		    type:"post",			        		        
		    success:function(result){
				$('.popbox3').fadeOut(function(){ $('#screen').hide(); });
				window.location.reload(true);
			 }
	     });
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
<script>
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


</body>
</html>
