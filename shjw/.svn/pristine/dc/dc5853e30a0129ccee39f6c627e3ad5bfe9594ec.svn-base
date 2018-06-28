<!doctype html>
<html>
    <head>
        <meta charset="utf-8">
        <title>登录</title>
        <link rel="stylesheet" type="text/css" href="${basePath}/css/style.css"/>
        <link rel="stylesheet" href="${basePath}/css/main.css">
        <link rel="stylesheet" href="${basePath}/css/normalize.css">
      <style>
    input:-webkit-autofill {  
    -webkit-box-shadow: 0 0 0px 1000px white inset !important;  
}  
    }
</style>
    </head>
    <body>
    <div class="login_top">

    </div>
<div class="login_main">
<div class="login_h">锦卫年检管理系统</div>
    <div class="login_middle">
        <div class="login_mleft"><img src="${basePath}/img/login_left.png" alt=""></div>
        <div class="login_mright">
            <ul class="login_ul">
                <li>用户登陆</li>
                <li class="login_li">
                    <div><img class="login_usrimg" src="${basePath}/img/user_name1.png" alt=""></div>
                    <div><input id="username" class="login_input" type="text" placeholder="用户名"></div>
                </li>
                <li>
                    <div><img class="login_usrimg" src="${basePath}/img/lock_img.png" alt=""></div>
                    <div><input id="password" class="login_input" type="password" placeholder="密码" ></div>
                </li>
                <li>
                    <div class="y-left">
                        <div><img class="login_usrimg" src="${basePath}/img/yanzheng-img.png" alt=""></div>
                        <div style="width: 135px;"><input class="login_input" type="text" placeholder="验证码"
                        id="valicode" name="vcode" ></div>
                    </div>
                 <div class="y-right"><img src="${basePath}/open/getJPGCode.shtml" class="o_code"  id="xj_yzm"                 
                 />
                
                 </div>
                </li>
                <li class="login_btn">登录</li>
            </ul>
        </div>
    </div>
    <div class="login_bottom">2017 ©  技术支持  浙江联保网络技术有限公司  0571-85819399</div>
</div>

    </body>
    
 <script src="${basePath}/js/main.js"></script>
 <script type="text/javascript" src="${basePath}/js/jquery-1.8.3.min.js"></script>
 <script type="text/javascript" src="${basePath}/js/common/layer/layer.js"></script>
 <script type="text/javascript" src="${basePath}/js/common/MD5.js"></script>
 <script  src="${basePath}/js/common/jquery/jquery1.8.3.min.js"></script>
 
<script>
   $(document).keypress(function(e) { 
    // 回车键事件  
       if(e.which == 13) { 
        
      jQuery(".login_btn").click();  
       }  
   });  
            //获取验证码
              jQuery(document).ready(function() {
				//验证码
				$(".y-right").on("click",'img',function(){
					var i = new Image();
					i.src = '${basePath}/open/getJPGCode.shtml?'+Math.random();
					$(i).replaceAll(this);
				  });
				});
		      //登录操作
			     $('.login_btn').click(function(){			    	
			        var username = $('#username').val();
			        var password = $('#password').val();
			        var vcode=$('#valicode').val();
			        	if($('[name=vcode]').val().length !=4){
			    		return layer.msg('验证码的长度为4位！',function(){}),!1;
			    	}		     
			        if(username == '') {
			          $('.error').fadeOut('fast', function(){
			            $('.error').css('top', '27px').show();
			                        });
			            $('.error').fadeIn('fast', function(){
			                $('#username').focus();
			            });
			            return false;
			        }
			        if(password == '') {
			            $('.error').fadeOut('fast', function(){
			                $('.error').css('top', '96px').show();
			            });
			            $(this).find('.error').fadeIn('fast', function(){
			                $('#password').focus();
			            });
			            return false;
			        }
			        var pswd = MD5(username +"#" + password),
			        	data = {pswd:pswd,email:username,vcode:vcode};
			        var load = layer.load();
			        
			        $.ajax({
			        	url:"${basePath}/u/submitLogin.shtml",
			        	data:data,
			        	type:"post",
			        	dataType:"json",
			        	beforeSend:function(){
			        		//layer.msg('开始登录，请注意后台控制台。');
			        	},
			        	success:function(result){
				        	layer.close(load);
				    		if(result && result.status != 200){
				    			layer.msg(result.message,function(){});
				    			$('#password').val('');		
								$("#xj_yzm").attr("src",'${basePath}/open/getJPGCode.shtml?'+Math.random());		    		
				    			return;
				    		}else{
				    			layer.msg('登录成功！');
				    			setTimeout(function(){
				    				//登录返回
					    			window.location.href= result.back_url || "${basePath}/";
				    			},1000)
				    		}
			        	},
			        	error:function(e){
			        		//console.log(e,e.message);
			        		//layer.msg('请看后台Java控制台，是否报错，确定已经配置数据库和Redis',new Function());
			           }
			        });
			    });
</script>
</html>
