$(document).on('click','#lyrics', function(){
    $.ajax({
    type : 'GET',
    url : 'songPlayer/getLyrics',
    data : {
        artistName: $('#artistNamePlayer').html(),
        songName: $('#albumSongNamePlayer').html()
    },
    success : function(data) {
        // Add the summary blurb
        $("#lyricsHere").html(data);
        $('#lyricModal').modal('show');
    },
    error : function(code, message){    
        $("#lyricsHere").html("No Lyrics Avaliable");
        $('#lyricModal').modal('show');
    }
    });
 });

