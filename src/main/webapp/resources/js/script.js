var audio;
var slider = $("#myRange")[0];
var muteToggle = $("#toggleMute")[0];

$(document).ready(function(){
  
    $("#center-pane").load("/resources/pages/center.jsp");
    w3.includeHTML(playBack);
    $('#myCarousel').carousel({
	    interval: 10000
	})
    $(".next").click(function(){
        $("#myCarousel").carousel("prev");
    });
    $(".prev").click(function(){
        $("#myCarousel").carousel("next");
    });
    
    $('[data-toggle="tooltip"]').tooltip();
    
    $(document).on('mousedown','#progress', scrub);
    //for the search links
    $(document).on('click', '.album-card-search', function(){
        viewAlbum($(this).attr("albumId"));
    });
    
    $(document).on('click', '.artist-card-search', function(){
        viewArtist($(this).attr("artistId"));
    });
    
    $(document).on('click', '.song-row-search', function(){
        viewAlbum($(this).attr("albumId"));
    });
    
    //Unfollow Song
    $(document).on({
      click: function(){
        unfollowSong($(this).attr("songId"),$(this).attr("currentPage"));
      }
    },'.unfollowSong');
    
    $(document).on({
     click: function(){
       followSong($(this).attr("songId"),$(this).attr("currentPage"));
     }  
    },'.followSong');
    
    $(document).on({
      click: function(){
        followArtist($(this).attr("artistId"),$(this).attr("currentPage"));
      }
    },'.followArtist');
    
    $(document).on({
      click: function(){
        unfollowArtist($(this).attr("artistId"),$(this).attr("currentPage"));
      }
    },'.unfollowArtist');
    
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
      click: function(){
        $("#my_file").click();
      }
    }, '#user-img');
    
    $(document).on({
      change: function(){
        var $files = document.getElementById('my_file');
        if ($files.files.length) {
          if($files.files.length==0){
            console.log("no file found");
            return false;
          }
          if ($files.files.length) {
            uploadImage($files, changeProfilePic);
          }
          else{
            var  path = "/resources/img/team0n3.png"
            changeProfilePic(path);
          }
          return false;
        }
      }
    }, '#my_file');
    
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
        if ($files.files.length) {
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
    
    function playBack(){
        audio = $("#audio")[0];
        audio.volume=.5;
        audio.addEventListener("timeupdate",updateProgress,false);
        audio.addEventListener("ended",playNext,false);
        slider.addEventListener("input",changeVolume,false);
        muteToggle.addEventListener("mouseup",toggleMute,false);
        
    } 
    //var activeToggle = $("#browseToggle"); //By default, the center pane shown is the browse overview
    function scrub(event){
        if(!audio.ended){
            var mousex  = event.pageX - (progress.offsetLeft*3);
            var newtime = mousex * (audio.duration/$(progress).width());
            console.log("Current",audio.currentTime);
            console.log("new",newtime);
            audio.currentTime = newtime;
            console.log("after",audio.currentTime);
        }
    }
    
  $("#searchForm").submit(function(){
    var keyword = $("#keyword").val();
    $.ajax({
      url: "search",
      type: "GET",
      data:({
             keyword: keyword
          }),
      success:function(){
          console.log("View success");
          $("#center-pane").load("resources/pages/search_results.jsp",function(){});
      },
      error: function(){
          console.log("View error");
      }
  });
  return false; 
  });
  
  
   
//$("#updatePlaylistForm").submit(function(){

});

function viewQueue(){
  console.log("init");
  $.ajax({
      url: "songPlayer/viewQueue",
      type: "GET",
      success:function(){
          $("#center-pane").load("/resources/pages/queue.jsp",function(){
          });
      },
      error: function(){
          console.log("Error viewing followed songs");
      }
  });
  return false; // Makes sure that the link isn't followed
}

// Update the current slider value (each time you drag the slider handle)
function changeVolume(){
  console.log("inputtign");
  var audioElem = $("#audio")[0];
  var audioValue = slider.value/100;
  if(audioElem.volume===0){ //turning on volume
    $($("#toggleMute")[0]).removeClass("fa-volume-off");
    $($("#toggleMute")[0]).addClass("fa-volume-up");
  }
  audioElem.volume=audioValue;
  if(audioValue===0){ //turning off volume
    $($("#toggleMute")[0]).removeClass("fa-volume-up");
    $($("#toggleMute")[0]).addClass("fa-volume-off");
  }
}

var oldVolume=.5;

function toggleMute(){
  console.log("yes");
  var muteIcon = $("#toggleMute")[0];
  console.log(muteIcon);
  if($(muteIcon).hasClass("fa-volume-up")){ //muting
    $(muteIcon).removeClass("fa-volume-up");
    $(muteIcon).addClass("fa-volume-off");
    oldVolume=audio.volume;
    console.log(audio.volume);
    slider.value=0;
    audio.volume=0.0;
  }
  else{ //unmuting
    $(muteIcon).removeClass("fa-volume-off");
    $(muteIcon).addClass("fa-volume-up");
    slider.value=oldVolume*100;
    audio.volume=oldVolume;
  }
}


function upgradeToPremium(){
    console.log("trying to upgrade");
    var cardHold = $("#cardHold").val();
    var cardNum = $("#cardNum").val();
    var ccv = $("#ccv").val();
    var creditCompany = $("#creditCompany").val();
    var address = $("#address").val();
    var monthYear = ($("#month").val());
    var zipCode = parseInt($('#zipcode').val());
    var dateData = monthYear.split(" "); 
    var month = parseInt(dateData[0]);
    var year = parseInt(dateData[2]);
    var currYear = (new Date()).getFullYear() - 2000;
    if(month > 12 || year < currYear || year > (currYear + 25)){
      console.log(month, year, currYear);
      $("#upgradeError").html('Invalid Experation Date');
    }
    else{
      $.ajax({
         url: "upgradeToPremium",
         type: "POST",
         data:({
             cardNumber: cardNum,
             cardHolder: cardHold,
             ccv: ccv,
             expirationMonth: month,
             expirationYear: year,
             creditCompany: creditCompany,
             address: address,
             zipCode : zipCode
         }),
         success:function(){
             $("#center-pane").load("/resources/pages/profile.jsp",function(){
                 console.log("success upgrading");
             });

         },
         error:function(){
             console.log("failure upgrading");
             $("#upgradeError").html('Invalid Information');
         }
      });
    return false;      
    }
}

function updateProgress() {
    var progress = $(".progress-bar")[0];
    var value = 0;
    if (audio.currentTime > 0)
        value = Math.floor((100 / audio.duration) * audio.currentTime);
    progress.style.width = value + "%";
    var currTime = $("#currentTime")[0];
    var t = audio.currentTime;  
    var less10="";
    var seconds = Math.floor(t%60);
    if (seconds < 10)
        less10 = "0";
    currTime.innerHTML= Math.floor(t/60) + ":" + less10 + seconds;
 }

function togglePlayPause() {
   var icon = $("#playPauseIcon")[0];
   if (audio.paused || audio.ended) { //if the audio is paused or not playing,
      $(icon).removeClass("fa-play");
      $(icon).addClass("fa-pause");
      audio.play();
   }
   else {
      $(icon).removeClass("fa-pause");
      $(icon).addClass("fa-play");
      audio.pause();
   }
}

function viewBrowse(){
    $("#center-pane").load("/resources/pages/center.jsp");
}

function viewEditProfile(){
    $("#center-pane").load("/resources/pages/editProfile.jsp");
}

function viewUpgradePage(){
    $("#center-pane").load("/resources/pages/upgrade.jsp");
}

function viewProfile(){
  $("#center-pane").load("/resources/pages/profile.jsp");
}

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

function viewArtist(id){
  $.ajax({
    url: "artist/viewArtist",
    type: "GET",
    data: ({
      artistID: id
    }),
    success:function(){
      console.log("View success");
      $("#center-pane").load("/resources/pages/artist.jsp",function(){
        console.log("Loaded new artist info into center pane!");
      });
    },
    error: function(){
      console.log("View error");
    }
  });
  return false; // Makes sure that the link isn't followed
}

function viewAlbum(id){
    $.ajax({
        url: "album/viewAlbum",
        type: "GET",
        data: ({
            albumID: id
        }),
        success:function(){
            $("#center-pane").load("/resources/pages/album.jsp",function(){
                
            });
        },
        error: function(){
            console.log("Error viewing album");
        }
    });
    return false; // Makes sure that the link isn't followed
}

function viewFollowedAlbums(){
  $("#center-pane").load("/resources/pages/followedAlbums.jsp");
}

function viewFollowedArtists(){
  $("#center-pane").load("/resources/pages/followedArtists.jsp");
}

function viewFollowedSongs(){
  $("#center-pane").load("/resources/pages/followedSongs.jsp");
}

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

function cancelPremium(){
    $.ajax({
       url: "cancelPremium",
       type: "POST",
       data:({}),
       success:function(){
           $("#center-pane").load("/resources/pages/profile.jsp",function(){
               console.log("success cancelling");
           });
       },
       error:function(){
           console.log("failure cancelling");
       }
    });
    return false;    
}

function playSong(songId,setType,songIndex){
  var repeatTag = $("#repeatTag");
  var shuffleTag = $("#shuffleTag");
  var sliderVal=($('#myRange')[0]).value;
  $.ajax({
    url: "songPlayer/playSong",
    type: "GET",
    data: ({
      songId: songId,
      setType: setType,
      songIndex: songIndex
    }),
    success: function(){
      $("#bottomTool").load("/resources/toolbars/bottom.jsp",function(){
        audio = $("#audio")[0];
        slider = $('#myRange')[0];
        muteToggle = $("#toggleMute")[0];
        var icon = $("#playPauseIcon")[0];
        $(icon).removeClass("fa-play");
        $(icon).addClass("fa-pause");
        $("#repeatTag").replaceWith(repeatTag);
        $("#shuffleTag").replaceWith(shuffleTag);
        audio.addEventListener("timeupdate",updateProgress,false);
        audio.addEventListener("ended",playNext,false);
        slider.addEventListener("input",changeVolume,false);
        muteToggle.addEventListener("mouseup",toggleMute,false);
        slider.value=sliderVal;
        audio.volume=sliderVal/100;
        audio.play();
      });
    },
    failure: function(){
      console.log("Failure playing song");
    }
  });
  return false;
}

function playNext(isOnQueue){
   var repeatTag = $("#repeatTag");
   var shuffleTag = $("#shuffleTag");
   var sliderVal=($('#myRange')[0]).value;
   var queue = $('#queue');
   console.log(queue);
  $.ajax({
    url:"songPlayer/playNext",
    type:"GET",
    data:({
      
    }),
    success:function(){
      $("#bottomTool").load("/resources/toolbars/bottom.jsp",function(){
        audio = $("#audio")[0];
        slider = $('#myRange')[0];
        muteToggle = $("#toggleMute")[0];
        var icon = $("#playPauseIcon")[0];
        $(icon).removeClass("fa-play");
        $(icon).addClass("fa-pause");
        $("#repeatTag").replaceWith(repeatTag);
        $("#shuffleTag").replaceWith(shuffleTag);
        audio.addEventListener("timeupdate",updateProgress,false);
        slider.addEventListener("input",changeVolume,false);
        audio.addEventListener("ended",playNext,false);
        muteToggle.addEventListener("mouseup",toggleMute,false);
        slider.value=sliderVal;
        audio.volume=sliderVal/100;
        audio.play();
        if(isOnQueue){
          $("#center-pane").load("/resources/pages/queue.jsp",function(){
            console.log("Reloaded queue");
          });
        }
      });
    },
    failure:function(){
      console.log("Failure playing next song");
    }
  });
  return false;
}

function playPrev(){
   var repeatTag = $("#repeatTag");
   var shuffleTag = $("#shuffleTag");
   var sliderVal=($('#myRange')[0]).value;
  $.ajax({
    url:"songPlayer/playPrev",
    type:"GET",
    data:({}),
    success:function(){
      $("#bottomTool").load("/resources/toolbars/bottom.jsp",function(){
        audio = $("#audio")[0];
        slider = $('#myRange')[0];
        muteToggle = $("#toggleMute")[0];
        var icon = $("#playPauseIcon")[0];
        $(icon).removeClass("fa-play");
        $(icon).addClass("fa-pause");
        $("#repeatTag").replaceWith(repeatTag);
        $("#shuffleTag").replaceWith(shuffleTag);
        audio.addEventListener("timeupdate",updateProgress,false);
        audio.addEventListener("ended",playNext,false);
        slider.addEventListener("input",changeVolume,false);
        muteToggle.addEventListener("mouseup",toggleMute,false);
        slider.value=sliderVal;
        audio.volume=sliderVal/100;
        audio.play();
      });
    },
    failure:function(){
      console.log("Failure playing prev song");
    }
  });
  return false;
}

function viewUsers(){
    $.ajax({
        url: "viewUsers",
        type: "GET",
        success:function(){
            console.log("View success");
            $("#center-pane").load("resources/pages/users.jsp",function(){
                console.log("Loaded playlists into center pane!");
            });
        },
        error: function(){
            console.log("View error");
        }
    });
    return false; // Makes sure that the link isn't followed
}

function toggleRepeat(){
  var repeatTag = $("#repeatTag")[0];
  var repeatIcon = $("#repeatButton")[0];
  var setting;
  if($(repeatTag).hasClass("repeatSet")){
    if ($(repeatTag).hasClass("repeatSong") ){ //turning off repeat
      repeatIcon.innerHTML="";
      $(repeatTag).removeClass("repeatSet");
      $(repeatTag).removeClass("repeatSong");
      setting="repeatOff";
    }
    else{ //turning on repeat song
      $(repeatTag).addClass("repeatSong");
      repeatIcon.innerHTML=" - 1";
      setting="repeatSong";
    }
  }
  else{ //turning on repeat
    $(repeatTag).addClass("repeatSet");
    setting="repeatSet"
  }
  $.ajax({
    url: "songPlayer/toggleRepeat",
    type: "GET",
    data: ({
      setting: setting
    }),
    error: function(){
      console.log("Couldn't change toggle");
    } 
  });
  return false;
}

function toggleShuffle(){
  var shuffleTag = $("#shuffleTag")[0];
  if($(shuffleTag).hasClass("shuffleOn"))
    $(shuffleTag).removeClass("shuffleOn");
  else
    $(shuffleTag).addClass("shuffleOn");
  $.ajax({
    url: "songPlayer/toggleShuffle",
    type: "GET"
  });
  return false;
}

function followAlbum(albumId,currentPage) {
  $.ajax({
    url: "album/followAlbum",
    type: "POST",
    data: ({
      albumId: albumId
    }),
    success:function(){
      $("#center-pane").load("/resources/pages/"+currentPage,function(){
        console.log("Success following album");
      });
    },
    error: function(){
      console.log("Failure following album");
    }
  });
  return false;
};

function unfollowAlbum(albumId,currentPage) {
  $.ajax({
    url: "album/unfollowAlbum",
    type: "POST",
    data: ({
      albumId: albumId
    }),
    success:function(){
      $("#center-pane").load("/resources/pages/"+currentPage,function(){
                console.log("Success unfollowing album");
            });
    },
    error: function(){
            console.log("Failure unfollowing album");
    }
  });
  return false;
};

function followSong(songId,currentPage) {
  $.ajax({
    url: "song/followSong",
    type: "POST",
    data: ({
      songId: songId
    }),
    success:function(){
      $("#center-pane").load("/resources/pages/"+currentPage,function(){
        console.log("Success following song");
      });
    },
    error: function(){
      console.log("Failure following song");
    }
  });
  return false;
};


function unfollowSong(songId,currentPage) {
  $.ajax({
    url: "song/unfollowSong",
    type: "POST",
    data: ({
      songId: songId
    }),
    success:function(){
      $("#center-pane").load("/resources/pages/"+currentPage,function(){
                console.log("Success unfollowing song");
            });
    },
    error: function(){
            console.log("Failure unfollowing song");
    }
  });
  return false;
};

function followArtist(artistId,currentPage) {
  $.ajax({
    url: "artist/followArtist",
    type: "POST",
    data: ({
      artistId: artistId
    }),
    success:function(){
      $("#center-pane").load("/resources/pages/"+currentPage,function(){});
    },
    error: function(){
      console.log("Failure following artist");
    }
  });
  return false;
};

function unfollowArtist(artistId,currentPage) {
  $.ajax({
    url: "artist/unfollowArtist",
    type: "POST",
    data: ({
      artistId: artistId
    }),
    success:function(){
      $("#center-pane").load("/resources/pages/"+currentPage,function(){});
    },
    error: function(){
      console.log("Failure unfollowing artist");
    }
  });
  return false;
};

$("#updatePlaylistForm").submit(function(){
    var name = $("#pName2").val();
    var desc= $("#pDesc2").val();
    var id=Number($("#playlistID").text());
    console.log(name);
    console.log(desc);
    console.log(id);
    var $files = document.getElementById('iPath2');
    if(!name && !desc && files.files.length!=0){
      $("#updatePlaylistModal").modal('hide');
       return false;
    }
    if(!name){
      name=$("#pName2").attr("placeholder");
    }
    if(!desc){
      name=$("#pDesc2").attr("placeholder");
    }
    if($files.files.length==0){
      console.log("no file found");
    }
    if ($files.files.length) {
      console.log("in");
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

      // Response contains stringified JSON
      // Image URL available at response.data.link
      $.ajax(settings).done(function(response) {
        var res= JSON.parse(response);
        var path= res.data.link;
        console.log(name);
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
      });
    }
    else{
      var  path = $("#iPath2").attr("placeholder");
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
    $("#updatePlaylistModal").modal('hide');
    return false;
  });


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

 function changeProfilePic(path) {
  $.ajax({
    url: "/changeProfPic",
    type: "POST",
    //Sends the necessary form parameters to the servlet
    data:({
     path: path
    }),
    success: function(){
      console.log("Success changing pic");
      $("#user-img").attr("src", path);
      $("#topTool").load("/resources/toolbars/top.jsp");
    },
    error: function(){
      console.log("Failure changing pic");
    }
  });
 }

function editPlaylist(path){
  var name = $("#pName2").val();
  var desc= $("#pDesc2").val();
  var id=Number($("#playlistID").text());
  if(!name && !desc && files.files.length!=0){
    $("#updatePlaylistModal").modal('hide');
     return false;
  }
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
