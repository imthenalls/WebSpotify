$(document).ready(function(){
  $(document).on({
      submit: function(){
        var $files = document.getElementById('file');
        if($files.files.length==0){
          console.log("no file found");
        }
        if ($files.files.length) {
          uploadImage($files, createPlaylist);
        }
        else{
          var  path = "/resources/img/team0n3.png"
          createPlaylist(path);
        }
        $("#createPlaylistModal").modal('hide');
        return false;
      }
    },"#newPlaylistForm");
    
    $(document).on({
      click:function(){
        $("#iPath2").click();
      }
    }, '#playlist-image2');
    
    $(document).on({
     change: function(){
      var $files = document.getElementById('iPath2');
      if ($files.files && $files.files[0]) {
        var reader = new FileReader();
        var name = $("#pName2").val();
        var desc= $("#pDesc2").val();
        var id=Number($("#playlistID").text());
        console.log(name);
        console.log(desc);
        console.log(id);
        reader.onload = function (e) {
          $('#playlist-image2').attr('src', e.target.result);
        };
        reader.readAsDataURL($files.files[0]);
      }
     }
   }, '#iPath2');
    
    $(document).on({
      click: function(){
        var $files = document.getElementById('iPath2');
        var name = $("#pName2").val();
        var desc= $("#pDesc2").val();
        if(name.length==0 && desc.length==0 && $files.files.length==0){
          console.log("no args");
        }
        else if ($files.files.length) {
          console.log("in");
          uploadImage($files, editPlaylist);
        }
        else{
          editPlaylist("");
        }
        
        $("#editPlaylistModal").modal('hide');
        return false;
      }
    },'#editPlaylist');
    
    $(document).on({
      click: function(){
        var name = $("#profilePane").attr("user");
        console.log("HIIIIII");
        viewPublicPlaylists(name);
        return false;
      }
    },'#profilePlay');
    
    $(document).on({
      click: function(){
        var id = $("#playlistID").attr("playlistID");
        togglePublic(id);
        return false;
      }
    },'#makePublic');
    
    $(document).on({
      click: function(){
        var id = $("#playlistID").attr("playlistID");
        togglePublic(id);
        return false;
      }
    },'#makePrivate');
    
    $(document).on({
      click: function(){
        var id = $("#playlistID").attr("playlistID");
        toggleCollab(id);
        return false;
      }
    },'#makeCollab');
    $(document).on({
      click: function(){
        var id = $("#playlistID").attr("playlistID");
        toggleCollab(id);
        return false;
      }
    },'#removeCollab');
    
    $(document).on({
      click: function(){
        $.ajax({
        url: "playlist/seeMore",
        type: "GET",

        success:function(){
          $("#center-pane").load("/resources/pages/searchPlaylists.jsp",function(){
                    console.log("Success unfollowing song");
                });
        },
        error: function(){
                console.log("Failure unfollowing song");
        }
      });
      return false;
      }
    },'#morePlaylists');
 });
 
 function viewPlaylist(id){
    $.ajax({
        url: "playlist/viewPlaylist",
        type: "GET",
        data: ({
            playlistID: id
        }),
        success:function(data){
          
            $("#center-pane").load("/resources/pages/playlist.jsp",function(){
               
            });
            //$("#editModalLocation").load("/resources/pages/updatePlaylist.jsp",function(){});
            console.log(data);
            if(data){
              //$("#playlist-image2").attr('src', data);
            }
            else{
             // $("#playlist-image2").attr('src', "/resorces/img/team0n3.png");
              
            }
        },
        error: function(data){
            console.log("Error viewing playlist");
        }
    });
    return false; // Makes sure that the link isn't followed
}
 
 function uploadImage($files, handler) {
    console.log("upload function");
    // Reject big files
    if ($files.files[0].size > 1024 * 1024) {
      console.log("Please select a smaller file");
      return false;
    }

    // Begin file upload
    console.log("Uploading file to Imgur..");

    // Replace ctrlq with your own API key
    var apiUrl = 'https://api.imgur.com/3/image';
    var apiKey = '031ad79e1cfcccf';

    var settings = {
      crossDomain: true,
      processData: false,
      contentType: false,
      type: 'POST',
      url: apiUrl,
      headers: {
        Authorization: 'Client-ID ' + apiKey,
        Accept: 'application/json'
      },
      mimeType: 'multipart/form-data'
    };

    var formData = new FormData();
    formData.append("image", $files.files[0]);
    settings.data = formData;
    $.ajax(settings).done(function(response) {
      var res= JSON.parse(response);
      var path= res.data.link;
      handler(path);
    });
 }
 function createPlaylist(path) {
  var name = $("#pName").val();
  var desc= $("#pDesc").val();
  console.log(name);
  $.ajax({
      url: "playlist/createPlaylist",
      type: "POST",
      //Sends the necessary form parameters to the servlet
      data:({
       name: name,
       description: desc,
       path: path
      }),
      success: function(){
          console.log("Success creating playlist");
          $("#leftTool").load("/resources/toolbars/left.jsp",function(){
          });
      },
      error: function(){
          console.log("Failure creating playlist");
      }
  });
 }
 
 function editPlaylist(path){
  var name = $("#pName2").val();
  var desc= $("#pDesc2").val();
  var id=Number($("#playlistID").text());
  
  if(!name){
    name=$("#pName2").attr("placeholder");
  }
  if(!desc){
    desc=$("#pDesc2").attr("placeholder");
  }
  $.ajax({
    url: "playlist/updatePlaylist",
    type: "POST",
    //Sends the necessary form parameters to the servlet
    data:({
     id: id,
     name: name,
     description: desc,
     path: path
    }),
    success: function(){
        console.log("Success creating playlist");
        $("#leftTool").load("/resources/toolbars/left.jsp",function(){
        });
        $("#center-pane").load("/resources/pages/playlist.jsp",function(){
        });
    },
    error: function(){
        console.log("Failure creating playlist");
    }
  });
}


function followPlaylist(playlist) {
  $.ajax({
    url: "playlist/followPlaylist",
    type: "POST",
    data: ({
      playlist: playlist,
    }),
    success:function(){
      $("#leftTool").load("/resources/toolbars/left.jsp",function(){
        console.log("loaded left tool");
        $("#center-pane").load("/resources/pages/playlist.jsp",function(){
          console.log("and loaded playlist page");
        });
      });
    },
    error: function(){
            console.log("Failure following playlist");
    }
  });
  return false;
};

function unfollowPlaylist(playlist) {
  $.ajax({
    url: "playlist/unfollowPlaylist",
    type: "POST",
    data: ({
      playlist: playlist,
    }),
    success:function(){
      $("#leftTool").load("/resources/toolbars/left.jsp",function(){
        $("#center-pane").load("/resources/pages/playlist.jsp",function(){
          
        });
      });
    },
    error: function(){
      console.log("Failure following playlist");
    }
  });
  return false;
};

function addSongToPlaylist(playlist, song) {
  $.ajax({
    url: "playlist/addSongToPlaylist",
    type: "POST",
    data: ({
      playlist: playlist,
      song: song
    }),
    success:function(){
      console.log("Success adding song");
    },
    error: function(){
      console.log("Failure adding song");
    }
  });
  return false;
}

function deleteSongFromPlaylist(playlistId, songId){
    $.ajax({
      url: "playlist/deleteSongFromPlaylist",
      type: "POST",
      data: ({
        playlistId: playlistId,
        songId: songId
      }),
      success: function(){
        console.log("Success deleting song");
          $("#center-pane").load("/resources/pages/playlist.jsp");
      },
      error: function(){
        console.log("Failure deleting song");
      }
    });
    return false;
};

function deletePlaylist(){
    $.ajax({
        url: "playlist/deletePlaylist",
        type: "POST",
        data: ({}),
        success: function(){
            console.log("Success deleting playlist");
            $("#leftTool").load("/resources/toolbars/left.jsp",function(){
                console.log("Reloaded playlist sidebar after delete");
                $("#center-pane").load("/resources/pages/center.jsp");
            });
        },
        error: function(){
            console.log("Failure deleting playlist");
        }
    })
    return false;
};

function viewPlaylist(id){
    $.ajax({
        url: "playlist/viewPlaylist",
        type: "GET",
        data: ({
            playlistID: id
        }),
        success:function(data){
          
            $("#center-pane").load("/resources/pages/playlist.jsp",function(){
               
            });
            //$("#editModalLocation").load("/resources/pages/updatePlaylist.jsp",function(){});
            if(data){
              //$("#playlist-image2").attr('src', data);
            }
            else{
             // $("#playlist-image2").attr('src', "/resorces/img/team0n3.png");
              
            }
        },
        error: function(data){
            console.log("Error viewing playlist");
        }
    });
    return false; // Makes sure that the link isn't followed
}

function viewPublicPlaylists(name){
    $.ajax({
        url: "playlist/viewPublicPlaylists",
        type: "GET",
        data: ({
            username: name
        }),
        success:function(){
          
            $("#profilePane").load("/resources/pages/profilePlaylists.jsp",function(){
               
            });
        },
        error: function(){
            console.log("Error viewing playlist");
        }
    });
    return false; // Makes sure that the link isn't followed
}
function togglePublic(id){
    $.ajax({
        url: "playlist/togglePublic",
        type: "POST",
        data: ({
            id: id
        }),
        success:function(){
            $("#center-pane").load("/resources/pages/playlist.jsp");
        },
        error: function(){
            console.log("Error viewing playlist");
        }
    });
    return false; // Makes sure that the link isn't followed
}

function toggleCollab(id){
    $.ajax({
        url: "playlist/toggleCollab",
        type: "POST",
        data: ({
            id: id
        }),
        success:function(){
          
            $("#centerPane").load("/resources/pages/playlist.jsp",function(){
               
            });
        },
        error: function(){
            console.log("Error viewing playlist");
        }
    });
    return false; // Makes sure that the link isn't followed
}
