var lineInfo;

$(function(){
	
		$('#kyeongbooLine').click(function(){
			alert('확인을 누르시고 잠시만 기다리시면 지도가 나타납니다.');
			lineInfo = 'kyeongbooLine';
			ajaxFunc();		
		});
		
		$('#joongbooLine').click(function(){
			alert('확인을 누르시고 잠시만 기다리시면 지도가 나타납니다.');
			lineInfo = 'joongbooLine';
			ajaxFunc();		
		});
		
		$('#honamLine').click(function(){
			alert('확인을 누르시고 잠시만 기다리시면 지도가 나타납니다.');
			lineInfo = 'honamLine';
			ajaxFunc();		
		});
		
		$('#joongbooLine2').click(function(){
			alert('확인을 누르시고 잠시만 기다리시면 지도가 나타납니다.');
			lineInfo = 'joongbooLine2';
			ajaxFunc();		
		});
		
		$('#daeguToBusanLine').click(function(){
			alert('확인을 누르시고 잠시만 기다리시면 지도가 나타납니다.');
			lineInfo = 'daeguToBusanLine';
			ajaxFunc();		
		});
		
		$('#yellowSeaLine').click(function(){
			alert('확인을 누르시고 잠시만 기다리시면 지도가 나타납니다.');
			lineInfo = 'yellowSeaLine';
			ajaxFunc();		
		});
		
		
	
});

function ajaxFunc(){
	
	$('#mapLocation').html('');
	$('#mapLocation').html('<div id="map"></div>');
					
			$.ajax({
				url: '/traffic/cctvInfo',
				type: 'post',
				data : {lineInfo : lineInfo},
				success: function(result) {
					lineInfo = '';
					var map = L.map('map').setView([result[0].latitude, result[0].longitude], 10);
					
					L.tileLayer('https://mt0.google.com/vt/lyrs=m&hl=kr&x={x}&y={y}&z={z}', {
						attribution: '&copy; <a target="_blank" href="https://maps.google.com/maps?ll='
						+result[0].latitude +',' + result[0].longitude +'&amp;z=15&amp;t=m&amp;hl=ko-KR&amp;gl=US&amp;mapclient=apiv3" title="Google 지도에서 이 지역을 보려면 클릭하세요." ><img alt="" src="https://maps.gstatic.com/mapfiles/api-3/images/google4.png" draggable="false"></a>' //화면 오른쪽 하단 attributors
					}).addTo(map);
					
					$(result).each(function(){
						
						var resultExLine = this.exLine;
						var resultURL = this.cctvURL;
						
						//console.log(this.exLine + ' || ' + this.latitude + ' || ' + this.longitude);
						var marker = L.marker([this.latitude, this.longitude]).addTo(map).on('click', addVideo);
						
						function addVideo (){
							//console.log('test Func1 : '+resultExLine);
							console.log('videoURL : '+resultURL);
							var testVideo = $('#video');
							testVideo.attr('src', resultURL);
							
							/*
							HLS는 player가 불안정함. 이에 API의 cctv 타입을 1(HLS)이 아닌 2(common Viedo)로 변경.
							document.getElementById('video').innerHTML='';
							var videoSrc = resultURL;
							  
							우선 HLS를 지원하는지 체크
							if (video.canPlayType('application/vnd.apple.mpegurl')) {
								console.log('1');
								video.src = videoSrc;
							  
							HLS를 지원하지 않는다면 hls.js를 지원
							} else if (Hls.isSupported()) {
								console.log('2');
								var hls = new Hls();
								hls.loadSource(videoSrc);
								hls.attachMedia(video);
							}*/
						}
						
					});
					
				} //Success 끝
			}); //Ajax 끝

}


