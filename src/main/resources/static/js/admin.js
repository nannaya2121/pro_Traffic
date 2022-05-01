$(function(){
	
		$('#gugunBtn').click(function(){
			
		    var gugunInsertForm = $('#gugunInsertForm');
		
		    var outer_city_name = $('#outer_city_name').val();
		    var gugun_name = $('#gugun_name').val();
		    var gugun_value = $('#gugun_value').val();
		
		    if(gugun_name == '' || gugun_value == ''){
		        alert('구군의 이름 또는 값을 입력해야만 합니다.');
		        return;
		    };
		
		    if(gugun_value.length != 4){
		        alert('구군의 값이 올바르지 않습니다.');
		        return;
		    };
		
		    gugunInsertForm.submit();
		
		});
	
})

