 $(document).ready(function(){
	
		  $('div.list_box,li.hdpj_post,ul.kssq,ul.ywal,ul.rcap,div.uesr_touxiang,.user_intro_left,.user_intro_right').hover(function(){
            $(this).addClass('on');
        },function(){
            $(this).removeClass('on');
        });
 
  
 $('.kscj tbody tr:odd').addClass('odd');
	$('.kscj tbody tr').hover(
	   function() {$(this).addClass('highlight');},
	   function() {$(this).removeClass('highlight');}
	);
 
 
	$('.toTop').click(function () {
		$('html,body').animate({
			scrollTop : '0px'
		}, 300);
		return false; 
	});
 
	$('.toBottom').click(function () {
		$('html,body').animate({
			scrollTop : $(document).height()
		}, 300);
		return false; 
	});
 
});



