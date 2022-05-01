
$(function() {
    var testBox = $('#testBox');
    $('#searchBtn').click(function(){
        testBox.html('');
        var searchYear = $('#searchYear').val();

        var url = 'http://apis.data.go.kr/B552061/AccidentDeath/getRestTrafficAccidentDeath'; /*URL*/
        var queryParams = '?' + encodeURIComponent('serviceKey') + '='+'cfWiR1HunYkkDf7oGcdyeFa0nJA7C4sFUKo9AEZIEaFLXyZirmI%2BeKZf2s7BDV8VaLwGZB%2Bstjl%2B7hx%2BJJ%2Fb0Q%3D%3D'; /*Service Key*/
        queryParams += '&' + encodeURIComponent('searchYear') + '=' + encodeURIComponent(searchYear); /**/
        queryParams += '&' + encodeURIComponent('siDo') + '=' + encodeURIComponent('1100'); /**/
        queryParams += '&' + encodeURIComponent('guGun') + '=' + encodeURIComponent('1117'); /**/
        queryParams += '&' + encodeURIComponent('type') + '=' + encodeURIComponent('json'); /**/
        queryParams += '&' + encodeURIComponent('numOfRows') + '=' + encodeURIComponent('20'); /**/
        queryParams += '&' + encodeURIComponent('pageNo') + '=' + encodeURIComponent('1'); /**/
    
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
        });
    });
});