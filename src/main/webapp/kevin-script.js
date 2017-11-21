$(document).ready(function(){
    $("#newSongToPlaylistForm").submit(function(){
           var playlistId = $("#playlistId").val();
           var songId = $("#songId").val();
           $.ajax({
               url: "doAddSongToPlaylist",
               type: "POST",
               //Sends the necessary form parameters to the servlet
               data:({
                  playlistId: playlistId,
                  songId: songId,
               }),
               
               success: function(){
                   console.log("Success");
               },
               error: function(){
                   console.log("Failure");
               }
           });
           return false;    
    });
});