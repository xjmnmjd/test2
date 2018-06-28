// JavaScript Document
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
		$('.popbox3').fadeOut(function(){ $('#screen').hide(); });
		return false;
	});
	
	$('.close-btn3').click(function(){
		$('.popbox2').fadeOut(function(){ $('#screen').hide(); });
		return false;
	});
	$('.close-btn4').click(function(){
		$('.popbox4').fadeOut(function(){ $('#screen').hide(); });
		return false;
	});
	$('.close-btn5').click(function(){
		$('.popbox5').fadeOut(function(){ $('#screen').hide(); });
		return false;
	});
	$('#add_station').click(function(){
		var h = $(document).height();
		$('#a_stationid').val("");
		$('.a_station_no').val("");
		$('.a_station_name').val("");
		$('.a_station_adress').val("");
		$('.a_station_phone').val("");
		$('.a_station_area').val("");
		$('.a_station_province').val("");
		$('#screen').css({ 'height': h });	
		$('#screen').show();
		$('.popbox').center();
		$('.popbox').fadeIn();	
		return false;
	});
	
	$('#add_hb').click(function(){
		var h = $(document).height();
		$('#screen').css({ 'height': h });	
		$('#screen').show();
		$('.popbox4').center();
		$('.popbox4').fadeIn();
		$('.a_hbid').val("");
		$('.a_hb_time_a').val("");
		$('.a_hb_time_b').val("");
		$('.a_hb_time_c').val("");				
		$('.a_hb_time_d').val("");   	
		return false;
	});
	$('.popbox-link1').click(function(){
		var h = $(document).height();
		$('#screen').css({ 'height': h });	
		$('#screen').show();
		$('.popbox1').center();
		$('.popbox1').fadeIn();
		return false;
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