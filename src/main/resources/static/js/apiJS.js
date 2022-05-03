$(function() {


	var testBox = $('#testBox');
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


		testBox.html('');

		var url = 'http://apis.data.go.kr/B552061/AccidentDeath/getRestTrafficAccidentDeath';
		var queryParams = '?' + encodeURIComponent('serviceKey') + '=' + 'cfWiR1HunYkkDf7oGcdyeFa0nJA7C4sFUKo9AEZIEaFLXyZirmI%2BeKZf2s7BDV8VaLwGZB%2Bstjl%2B7hx%2BJJ%2Fb0Q%3D%3D';
		queryParams += '&' + encodeURIComponent('searchYear') + '=' + encodeURIComponent(searchYear);
		queryParams += '&' + encodeURIComponent('siDo') + '=' + encodeURIComponent(city_value);
		queryParams += '&' + encodeURIComponent('guGun') + '=' + encodeURIComponent(gugun_value);
		queryParams += '&' + encodeURIComponent('type') + '=' + encodeURIComponent('json');
		queryParams += '&' + encodeURIComponent('numOfRows') + '=' + encodeURIComponent('50');
		queryParams += '&' + encodeURIComponent('pageNo') + '=' + encodeURIComponent('1');

		var totalURL = url + queryParams;
		console.log('totalURL : ' + totalURL);

		$.ajax({
			url: totalURL,
			type: "GET",
			success: function(data) {

				/*총 결과 개수
				alert(Object.keys(data.items.item).length);*/
				var testObj = '<div>'
				testObj += '총 ' + Object.keys(data.items.item).length + '건 <br>';
				$(data.items.item).each(function() {
					testObj += dateFormatter(this.occrrnc_dt) + '&emsp; <a href="#">상세보기</a>';
					testObj += '위도 좌표 : ' + this.la_crd + ' ||| 경도 좌표 : ' + this.lo_crd + '<br></div>';
				});
				testBox.append(testObj);
			}
		});
	});
});

function changeCity(city_value) {
	var gugun = $('#gugun');
	var optionTag = '<option value="-1">상세지역 선택</option>';

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

