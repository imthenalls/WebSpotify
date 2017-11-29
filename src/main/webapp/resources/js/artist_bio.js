var artist = $('#artistHeader').html();
$.ajax({
    type : 'POST',
    url : 'http://ws.audioscrobbler.com/2.0/',
    data : 'method=artist.getinfo&' +
           'artist='+artist+'&' +
           'api_key=57ee3318536b23ee81d6b27e36997cde&' +
           'format=json',
    dataType : 'jsonp',
    success : function(data) {
        // Add the summary blurb
        $('#summary').html(data['artist']['bio']['summary']);  
    },
    error : function(code, message){    
        console.log(code);
    }
});
