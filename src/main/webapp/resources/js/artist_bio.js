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
        var summary = data['artist']['bio']['summary'];
        var a = artist.replace(' ', '+');
        //look for 'href="' then get substring to the next '"'
        console.log(summary);
        summary = summary.replace('<a href="https://www.last.fm/music/'+a+'">Read more on Last.fm</a>', 
          '<a onclick="viewArtistContent()">Read more</a>'); 
        summary = summary.replace('Read more on Last.fm', 'Summary not found.'); 
        $('#summary').html(summary);  
    },
    error : function(code, message){    
        console.log(code);
    }
});

function viewArtistContent(){
  $.ajax({
      type : 'POST',
      url : 'http://ws.audioscrobbler.com/2.0/',
      data : 'method=artist.getinfo&' +
             'artist='+artist+'&' +
             'api_key=57ee3318536b23ee81d6b27e36997cde&' +
             'format=json',
      dataType : 'jsonp',
      success : function(data) {
          // Open artistbio modal
          var content = data['artist']['bio']['content']; 
          $('#artistContent').html(content); 
          $('#artistContentModal').modal('show');
      },
      error : function(code, message){    
          console.log(code);
      }
  });
}