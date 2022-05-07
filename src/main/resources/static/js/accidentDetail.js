$(function() {
	
	getAccidentPoint();
	
	var dayOfWeek = $('#dayOfWeek').val();
	
	if(dayOfWeek === "1"){
		$('#resultDayOfWeek').html('일요일');
	}else if(dayOfWeek === "2"){
		$('#resultDayOfWeek').html('월요일');
	}else if(dayOfWeek === "3"){
		$('#resultDayOfWeek').html('화요일');
	}else if(dayOfWeek === "4"){
		$('#resultDayOfWeek').html('수요일');
	}else if(dayOfWeek === "5"){
		$('#resultDayOfWeek').html('목요일');
	}else if(dayOfWeek === "6"){
		$('#resultDayOfWeek').html('금요일');
	}else if(dayOfWeek === "7"){
		$('#resultDayOfWeek').html('토요일');
	}
	
	var accidentDate = $('#accidentDate').val();
	$('#resultDate').html(dateFormatter(accidentDate));
});

function getAccidentPoint(){
	
	var latitude = $('#latitude').val();
	var longitude = $('#longitude').val();
	var map = L.map('map').setView([latitude, longitude], 15);
	/*	
		leaflet.js에 mapbox 지도 담기
		
		L.tileLayer('https://api.mapbox.com/styles/v1/{id}/tiles/{z}/{x}/{y}?access_token={accessToken}', {
	    attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors, Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
	    maxZoom: 18,
	    id: 'mapbox/streets-v11',
	    tileSize: 512,
	    zoomOffset: -1,
	    accessToken: 'pk.eyJ1IjoieGRyYWdvbiIsImEiOiJjbDIxYzM5N3kxNTByM2pxZHE2eTZvZWd1In0.qNsRIws-_dPBOZ_nh-X4Pg',
		mapbox: '//styles/xdragon/cl2vg06k3000f15pivjl2vf06'
	}).addTo(map);*/
	
	/*
		leaflet.js에 google 지도 담기
	*/
	
	L.tileLayer('https://mt0.google.com/vt/lyrs=m&hl=kr&x={x}&y={y}&z={z}', {
		attribution: '&copy; <a target="_blank" href="https://maps.google.com/maps?ll='
		+latitude +',' + longitude +'&amp;z=10&amp;t=m&amp;hl=ko-KR&amp;gl=US&amp;mapclient=apiv3" title="Google 지도에서 이 지역을 보려면 클릭하세요." ><img alt="" src="https://maps.gstatic.com/mapfiles/api-3/images/google4.png" draggable="false"></a>' //화면 오른쪽 하단 attributors
	}).addTo(map);
	
	
	var marker = L.marker([latitude, longitude]).addTo(map);
	
	var circle = L.circle([latitude, longitude], {
	    color: 'red',
	    fillColor: '#f03',
	    fillOpacity: 0.5,
	    radius: 25
	}).addTo(map);
	
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



	
