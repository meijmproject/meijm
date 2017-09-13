$(function(){
	$(".z_text_id").focus(function(){
		setInterval(sp,600);
	});
	$(".z_text_id").blur(function(){
		clearInterval(sp);
	});
	$(".agree_opinion_bigspan").click(function(){
		$(this).parent().remove();
	});
	function sp(){
		var tex = $('textarea').val()==undefined?"":$('textarea').val();
		$('.agree_opinion_red').text(tex.length);
		$('.agree_opinion_red_p').text(200-parseInt(tex.length));
	}
	
})

