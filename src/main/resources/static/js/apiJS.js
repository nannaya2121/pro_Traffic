
$(function() {

    var testBox = $('#testBox');
    var resultBox = $('#resultBox');
    $('#searchBtn').click(function(){
		var searchYear = $('#searchYear').val();
		var city_value = $('#city').val();
		
		
		console.log('searchYear 값 : ' + searchYear
					+ '\n city_value 값 : ' + city_value);
	
	
       	/*testBox.html('');
        var searchYear = $('#searchYear').val();

        var url = 'http://apis.data.go.kr/B552061/AccidentDeath/getRestTrafficAccidentDeath';
        var queryParams = '?' + encodeURIComponent('serviceKey') + '='+'cfWiR1HunYkkDf7oGcdyeFa0nJA7C4sFUKo9AEZIEaFLXyZirmI%2BeKZf2s7BDV8VaLwGZB%2Bstjl%2B7hx%2BJJ%2Fb0Q%3D%3D';
        queryParams += '&' + encodeURIComponent('searchYear') + '=' + encodeURIComponent(searchYear);
        queryParams += '&' + encodeURIComponent('siDo') + '=' + encodeURIComponent(city_value);
        queryParams += '&' + encodeURIComponent('guGun') + '=' + encodeURIComponent('1117');
        queryParams += '&' + encodeURIComponent('type') + '=' + encodeURIComponent('json');
        queryParams += '&' + encodeURIComponent('numOfRows') + '=' + encodeURIComponent('20');
        queryParams += '&' + encodeURIComponent('pageNo') + '=' + encodeURIComponent('1');
    
        var totalURL = url + queryParams;
       
        $.ajax({
            url: totalURL,
            type: "GET", 
            success: function(data) {
                alert(Object.keys(data.items.item).length);
                var testObj = '<div>'
                $(data.items.item).each(function(){
                    
                   testObj += '위도 좌표 : '+this.la_crd + ' ||| 경도 좌표 : '+this.lo_crd + '<br></div>';
                });
                testBox.append(testObj);
            }
        });*/
    });
});

function changeCity(){
	
	var cityListValue = $('#cityListValue').val();
	alert(cityListValue);
	
	console.log('e 값 확인 : ' + cityListValue + '\n');
		$.ajax({
		url: '/traffic/gugunSearchList',
		type: "POST", 
		data: {city_value:cityListValue},
		success: function(data) {
			$(data).each(function(){
				console.log(this.gugun_name + '\n');
			});
        }
    });
}

