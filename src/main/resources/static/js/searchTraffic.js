$(function() {
	
	var innerResultBox = $('#innerResultBox');

	// var innerResultBoxNumbering = $('#innerResultBoxNumbering');
	// var innerResultBoxDate = $('#innerResultBoxDate');
	// var innerResultBoxDetail = $('#innerResultBoxDetail');
	
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

		innerResultBox.html('');
		// innerResultBoxNumbering.html('');
		// innerResultBoxDate.html('');
		// innerResultBoxDetail.html('');
		$.ajax({
			url: '/traffic/moveData',
			type: "POST",
			data:{
				searchYear : searchYear,
				city_value : city_value,
				gugun_value  :gugun_value
			},
			success: function(data) {
			
			innerResultBox.css('border','1px solid rgb(0, 255, 115)');
				
			$('#detailSidoValue').val(city_value);
			$('#detailGugunValue').val(gugun_value);
			
			console.log('data 값 : ' + data.resultCode);

				$('#innerResultTotalCount').html('<div>총 ' + Object.keys(data.items.item).length + '건 </div>');
				
				for(var i = 0; i < Object.keys(data.items.item).length; i++){

					var testStr = JSON.stringify(data.items.item[i]);
					var reverseTest = JSON.parse(testStr.replace(/ /gi, "").replace(/&quot;/g, '"'));
					
					var newTag = '<div class="gatherSources"> <span class="innerResultBoxNumbering">' + (i+1) + '</span>';

					// var numberingTag = '<div>' + (i+1) + '</div>';
					// innerResultBoxNumbering.append(numberingTag);

					newTag += '<span class="innerResultBoxDate">' + dateFormatter(data.items.item[i].occrrnc_dt)  + '</span>';

					// var dateTag = '<div>' + dateFormatter(data.items.item[i].occrrnc_dt)  + '</div>';
					// innerResultBoxDate.append(dateTag);

					newTag += '<a class="innerResultBoxDetail" href="javascript:void(0)" onClick=accidentDetail(' + 
					JSON.stringify(reverseTest)  + ')> 상세보기 </a> </div> <br>';

					// var detailTag = '<div> <a href="javascript:void(0)" onClick=accidentDetail(' + 
					// JSON.stringify(reverseTest)  + ')> 상세보기 </a> </div>';
					innerResultBox.append(newTag);
				}

			} /*success구문 끝*/
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

