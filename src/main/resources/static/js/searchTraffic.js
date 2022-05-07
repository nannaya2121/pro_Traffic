$(function() {
	
	var resultBox = $('#resultBox');
	
	$('#searchBtn').click(function() {
		
		var searchYear = $('#searchYear').val();
		var city_value = $('#city').val();
		var gugun_value = $('#gugun').val();

		console.log('searchYear 값 : ' + searchYear
			+ '\n city_value 값 : ' + city_value
			+ '\n gugun_value 값 : ' + gugun_value);

		if (searchYear == '-1') {
			alert('조회할 연도를 선택해주세요');
			return;
		}

		if (city_value == '-1') {
			alert('조회지역을 선택해주세요');
			return;
		}

		if (gugun_value == '-1') {
			alert('상세지역을 선택해주세요');
			return;
		}

		resultBox.html('');
		
		
		$.ajax({
			url: '/traffic/moveData',
			type: "POST",
			data:{
				searchYear : searchYear,
				city_value : city_value,
				gugun_value  :gugun_value
			},
			success: function(data) {
				
			$('#detailSidoValue').val(city_value);
			$('#detailGugunValue').val(gugun_value);
			
			console.log('data 값 : ' + data.resultCode);
				var testObj = '<div>'
				testObj += '총 ' + Object.keys(data.items.item).length + '건 <br>';
				
				for(var i = 1; i < Object.keys(data.items.item).length; i++){
					var testStr = JSON.stringify(data.items.item[i]);
					var reverseTest = JSON.parse(testStr.replace(/ /gi, "").replace(/&quot;/g, '"'));
					console.log('p값 확인 : '+ JSON.stringify(reverseTest));
					
					
					testObj += '<span>'+(i)+'</span> &emsp;';
					testObj += dateFormatter(data.items.item[i].occrrnc_dt) 
					+ '&emsp; <a href="javascript:void(0)" onClick=accidentDetail('+JSON.stringify(reverseTest)+')>상세보기</a><br>';
				}

				resultBox.append(testObj);
			}
		});
		
	});
});

function changeCity(city_value) {
	var gugun = $('#gugun');
	var optionTag = '<option value="-1">지역을 선택해주세요</option>';

	console.log('cityListValue 값 확인 : ' + city_value + '\n');
	$.ajax({
		url: '/traffic/gugunSearchList',
		type: "POST",
		data: { city_value: city_value },
		success: function(data) {
			gugun.empty();
			$(data).each(function() {
				optionTag += '<option value="' + this.gugun_value + '">'
					+ this.gugun_name + '</option>';
			});
			gugun.append(optionTag);
		}
	});
}

function dateFormatter(num) {

	if (!num) return "";
	var formatNum = '';

	// 공백제거
	num = num.replace(/\s/gi, "");
	try {
		if (num.length == 10) {
			formatNum = num.replace(/(\d{4})(\d{2})(\d{2})(\d{2})/, '$1년 $2월 $3일 $4시');
		}
	} catch (e) {
		formatNum = num;
		console.log(e);
	}
	return formatNum;
}

function accidentDetail(jsonOne){
	console.log('jsonOne 값 확인 : ' + JSON.stringify(jsonOne));
	var totalVal = $('#totalVal').val(JSON.stringify(jsonOne));
	console.log('totalVal 값 확인 : ' + totalVal);
	$('#trafficDetailForm').submit();
}

