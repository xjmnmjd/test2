<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
<link href="${basePath}/css/page.css" type="text/css" rel="stylesheet"/>
<title>排版管理</title>
</head>
<body>
        <div class="top">
              <p><img src="${basePath}/img/ico_qiz.png"/>当前位置：系统管理>排班管理<span id="clocks">2017-08-01 11：29 星期二</span></p>
              <a class="curr">排班管理</a>
        </div>
<div class="down">
                <div style="overflow:hidden;width:600px;margin:auto">
                   <a style="cursor:pointer;color:#01b386;margin-right:10px; float: left; font-size: 14px; line-height: 34px;" id="last-week">上一周</a> 
                   <div style="width:210px;float:left; background:#eee;border: 1px solid #ddd;height: 34px">       
                   <input  style="line-height:32px;width:100px;text-align:left" class="start_time"/>
              
                   <input  style="line-height:32px;width:100px;text-align:right" class="end_time"/>
                   </div>
                   <a style="cursor:pointer;color:#01b386;margin-left:8px; float: left; font-size: 14px; line-height: 34px;" id="next-week">下一周</a>
               </div>
     
  <div>
   <input value="${kfy_list?size}" class="kfy_size" style="display:none"/>
       <table width="100%" border="0" id="monitor">
         <tr>
          <th scope="col" style="display:none">#</th>
          <th scope="col">客服员</th>
          <th scope="col" id="02">星期一</th>
          <th scope="col" id="03">星期二</th>
          <th scope="col" id="04">星期三</th>
          <th scope="col" id="05">星期四</th>
          <th scope="col" id="06">星期五</th>
          <th scope="col" id="07">星期六</th>
          <th scope="col" id="08">星期日</th>  
        </tr>
             <#if kfy_list?exists && kfy_list?size gt 0>
               <#list kfy_list as kfy>
               <tr>
               <td style="display:none">${kfy.id}</td> 
               <td>${kfy.nickname}</td>
               <td class="z_time1"></td>
               <td class="z_time2"></td>
               <td class="z_time3"></td>
               <td class="z_time4"></td>
               <td class="z_time5"></td>
               <td class="z_time6"></td>
               <td class="z_time7"></td>
                 </tr>                         
               </#list>
             </#if>              
      </table>          
        <div class="check"><a class="fenp popbox-link" id="save" onclick="getTableContent()" style="cursor:pointer;float:right">保存</a>
      </div>
  </div>
</div>
<script type="text/javascript" src="${basePath}/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${basePath}/js/common/layer/layer.js"></script>

<script type="text/javascript"> 
  function getTableContent(){        
           var mytable = document.getElementById("monitor");     
           var data = [];
           var row1 = new Array();
           var colsNum = mytable.rows.item(0).cells.length;
           var rowsNum = mytable.rows.length;      
           for(var i=1;i<rowsNum;i++){
               var userid=mytable.rows[i].cells[0].innerHTML;
               var customer=mytable.rows[i].cells[1].innerHTML;
               for(var j=0;j<colsNum;j++){
                   var typename=$("#"+i+j).prop("type");
                   if(typename=="checkbox"){
                           if($("#"+i+j).attr("checked")){
                           
                              // row1.userid=userid;
                              // row1.pb_time=$("#"+i+j).val();
                              data.push({userid:userid,customer:customer,pb_time:$("#"+i+j).val()});
                       }
                   }
               }
           }
         return data;
  }
     
 
  
  </script>
 <script type="text/javascript">
    window.onload = function(){     
      var cells1 = document.getElementById('monitor').getElementsByClassName('z_time1');
      var cells2 = document.getElementById('monitor').getElementsByClassName('z_time2');     
      var cells3 = document.getElementById('monitor').getElementsByClassName('z_time3');
      var cells4 = document.getElementById('monitor').getElementsByClassName('z_time4');
      var cells5 = document.getElementById('monitor').getElementsByClassName('z_time5');
      var cells6 = document.getElementById('monitor').getElementsByClassName('z_time6');      
      var cells7 = document.getElementById('monitor').getElementsByClassName('z_time7');      
      
     // var clen = cells.length;
      var currentFirstDate;
      var formatDate = function(date){       
        var year = date.getFullYear()+'-';
        var month = (date.getMonth()+1);
        if(month<10){
          month="0"+month+"-";
        }else{
          month=month+"-";
        }
        var day = date.getDate();
          if(day<10){
          day="0"+day;
        }
        var week = '('+['星期天','星期一','星期二','星期三','星期四','星期五','星期六'][date.getDay()]+')'; 
 
        return year+month+day;
      };
      var addDate= function(date,n){
         
        date.setDate(date.getDate()+n);    
        return date;
      };
      var setDate = function(date){
               
        var week = date.getDay()-1;
        date = addDate(date,week*-1);
        currentFirstDate = new Date(date);
         var time1=formatDate(date); 
        for(var i = 0;i<$('.kfy_size').val();i++){         
          cells1[i].innerHTML ="<input type='checkbox' id='"+(i+1)+"2' value='"+time1+"'/>";          
        }
         var time2= formatDate(addDate(date,1));
        for(var i = 0;i<$('.kfy_size').val();i++){         
         cells2[i].innerHTML = "<input type='checkbox' id='"+(i+1)+"3' value='"+time2+"'/>";
        }
        var time3= formatDate(addDate(date,1));
        for(var i = 0;i<$('.kfy_size').val();i++){         
         cells3[i].innerHTML = "<input type='checkbox' id='"+(i+1)+"4' value='"+time3+"'/>";
        }
        var time4= formatDate(addDate(date,1));
        for(var i = 0;i<$('.kfy_size').val();i++){         
         cells4[i].innerHTML = "<input type='checkbox' id='"+(i+1)+"5' value='"+time4+"'/>";
        }
        var time5= formatDate(addDate(date,1));
        for(var i = 0;i<$('.kfy_size').val();i++){         
         cells5[i].innerHTML = "<input type='checkbox' id='"+(i+1)+"6' value='"+time5+"'/>";
        }
        var time6= formatDate(addDate(date,1));
        for(var i = 0;i<$('.kfy_size').val();i++){         
         cells6[i].innerHTML = "<input type='checkbox'  id='"+(i+1)+"7' value='"+time6+"'/>";
        }
        var time7= formatDate(addDate(date,1));
        for(var i = 0;i<$('.kfy_size').val();i++){         
         cells7[i].innerHTML = "<input type='checkbox'  id='"+(i+1)+"8' value='"+time7+"'/>";
        }
         $('.start_time').val(time1);
         $('.end_time').val(time7);         
      };       
      document.getElementById('last-week').onclick = function(){
            setDate(addDate(currentFirstDate,-7));
                var start_time=$('.start_time').val();
                var end_time=$('.end_time').val();
            changetime(start_time,end_time);     
      };       
      document.getElementById('next-week').onclick = function(){         
            setDate(addDate(currentFirstDate,7));
                var start_time=$('.start_time').val();
                var end_time=$('.end_time').val();
            changetime(start_time,end_time);     
      };   
      setDate(new Date());
       changetime($('.start_time').val(),$('.end_time').val()); 
      //保存     
      document.getElementById('save').onclick = function(){
                var datajson=getTableContent(); 
                var start_time=$('.start_time').val();
                var end_time=$('.end_time').val(); 
                var jsonStr=JSON.stringify(datajson);
                var data={jsonStr:jsonStr,start_time:start_time,end_time:end_time};                           
                $.ajax({
		          url:"${basePath}/pb/bj_pb.shtml",
		          data:data,			 
		          type:"post",			        		        
		             success:function(result){
		               layer.msg("保存成功");
		                changetime(start_time,end_time); 
		              }
	             });                                                            
      }
          
  }
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
  <script type="text/javascript"> 
  //获取当前日期
    function show(){
      var date = new Date();
      
        var year = date.getFullYear()+'-';
        var month = (date.getMonth()+1);
        if(month<10){
          month="0"+month+"-";
        }else{
          month=month+"-";
        }
        var day = date.getDate();
          if(day<10){
          day="0"+day;
        }
        return year+month+day;
  }  
    function changetime(start_time,end_time){      
         var data={start_time:start_time,end_time:end_time};
           $.ajax({
		       url:"${basePath}/pb/pb_search.shtml",
		       data:data,			 
		       type:"post",			        		        
		          success:function(result){
		          var pblist=result.pbList;	
		          $("td").css('background','#FFFFFF');
		          $("th").css('background-color','#f5f5f5');
		          $("th").css({color:"#333"});              
		            var mytable = document.getElementById("monitor");           
                    var colsNum = mytable.rows.item(0).cells.length;
                    var rowsNum = mytable.rows.length;      
                       for(var i=1;i<rowsNum;i++){
                          var userid=mytable.rows[i].cells[0].innerHTML;
                          for(var j=0;j<colsNum;j++){

                                for(var k=0;k<pblist.length;k++){                                                                                                                                                                     
                                    if(userid==pblist[k].userid){
                                           var typename=$("#"+i+j).prop("type");
                                           if(typename=="checkbox"){                                           
                                                if($("#"+i+j).val()==pblist[k].pb_time){                                               
                                                    $("#"+i+j).attr("checked",'true');   
                                                 }

                                             } 
                                      }
                                  } 
                                                var today=show(); 
                                                  var date1 = new Date($("#"+i+j).val());
                                                  var date2 = new Date(today);                                      
                                                  if(date1-date2==0){ 
                                                    $("#0"+j).css('background-color','#ffb332');
                                                    $("#0"+j).css({color:"#FFFFF4"}); 
                                                    $("#"+i+j).parent("td").css('background','#FBF0D7');                                           
                                                 }
                                                 if(date1-date2<0){
                                                   $("#"+i+j).attr("disabled","disabled"); 
                                                 }                                                 
                            }
                         }		              	             		                    	      		    	     
		          }
	       });  
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
Date.prototype.format =function(format)
    {
        var o = {
        "M+" : this.getMonth()+1, //month
"d+" : this.getDate(),    //day
"h+" : this.getHours(),   //hour
"m+" : this.getMinutes(), //minute
"s+" : this.getSeconds(), //second
"q+" : Math.floor((this.getMonth()+3)/3),  //quarter
"S" : this.getMilliseconds() //millisecond
        }
        if(/(y+)/.test(format)) format=format.replace(RegExp.$1,
        (this.getFullYear()+"").substr(4- RegExp.$1.length));
        for(var k in o)if(new RegExp("("+ k +")").test(format))
        format = format.replace(RegExp.$1,
        RegExp.$1.length==1? o[k] :
        ("00"+ o[k]).substr((""+ o[k]).length));
        return format;
    }




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
<script type="text/javascript" src="${basePath}/js/common/layer/layer.js"></script>



</body>

</html>
