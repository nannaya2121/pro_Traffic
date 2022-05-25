$(function(){
	
	$('.notice').click(function(){
		alert('페이지를 준비중입니다.');
	})
	
	$(window).resize(function() {        
	    $('body').css('width', $(window).width() );        
	    $('body').css('height', $(window).height() );   
	});

});
