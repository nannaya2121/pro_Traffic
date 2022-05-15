$(function(){
		
	const stationaryLatitude = 37.522610;
	const stationaryLongitude = 127.016256;
	var map = L.map('map').setView([stationaryLatitude, stationaryLongitude], 10);
	
			L.tileLayer('https://mt0.google.com/vt/lyrs=m&hl=kr&x={x}&y={y}&z={z}', {
				attribution: '&copy; <a target="_blank" href="https://maps.google.com/maps?ll='
				+stationaryLatitude +',' + stationaryLongitude +'&amp;z=15&amp;t=m&amp;hl=ko-KR&amp;gl=US&amp;mapclient=apiv3" title="Google 지도에서 이 지역을 보려면 클릭하세요." ><img alt="" src="https://maps.gstatic.com/mapfiles/api-3/images/google4.png" draggable="false"></a>' //화면 오른쪽 하단 attributors
			}).addTo(map);
	
		$('#test').click(function(){
		
		var video = document.getElementById('video');
			$.ajax({
				url: '/traffic/cctvInfo',
				type: 'post',
				success: function(data) {
					
					$(data).each(function(){
						
						var resultExLine = this.exLine;
						var resultURL = this.cctvURL;
						
						function test (){
							console.log('test Func1 : '+resultExLine);
							
							document.getElementById('video').innerHTML='';
							var videoSrc = resultURL;
							  
							//우선 HLS를 지원하는지 체크
							if (video.canPlayType('application/vnd.apple.mpegurl')) {
								console.log('1');
								video.src = videoSrc;
							  
							//HLS를 지원하지 않는다면 hls.js를 지원
							} else if (Hls.isSupported()) {
								console.log('2');
								var hls = new Hls();
								hls.loadSource(videoSrc);
								hls.attachMedia(video);
							}
						}
						
						console.log(this.exLine + ' || ' + this.latitude + ' || ' + this.longitude);
						var marker = L.marker([this.latitude, this.longitude]).addTo(map).on('click', test);
						
					});
					
				}
			});
		
		});
	
});



