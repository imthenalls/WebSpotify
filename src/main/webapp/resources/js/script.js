var audio;
var slider = $("#myRange")[0];
var muteToggle = $("#toggleMute")[0];
var adCount=0;

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
  $(document).on({
    click: function(){
      $("#ad").hide();
    }
  }, '.x-button');
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
          audio.currentTime = newtime;
      }
  }

  $("#searchForm").submit(function(){
    var keyword = $("#keyword").val();
    if(keyword.length==0){
      return false;
    }
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
  
  $(document).on({
    click: function(){
      addSongToQueue($(this).attr("songId"));
    }
  },'.addSongToQueue');
  
  $(document).on({
    click:function(){
      addPlaylistToQueue($(this).attr("playlistId"));
    }
  },'.addPlaylistToQueue');
  
  $(document).on({
    click:function(){
      viewHelp();
    }
  },'.viewHelp');
  
  $(document).on({
    click:function(){
      viewHistory();
    }
  },'.viewHistory');
  
});
function viewSongRemovalRequests(){
    console.log("init viewing removal requests ");
  $.ajax({
      url: "artist/viewSongRemovalRequests",
      type: "GET",
      success:function(){
          $("#center-pane").load("/resources/pages/removalRequests.jsp",function(){
          });
      },
      error: function(){
          console.log("Error viewing removal requests ");
      }
  });
  return false; // Makes sure that the link isn't followed
}
function artistRequestSongRemoval(songId,currentPage){
    $.ajax({
        url: "artist/artistRequestSongRemoval",
        type: "POST",
        data: ({
            songId: songId,
        }),
        success:function(){
            $("#center-pane").load("/resources/pages/"+currentPage,function(){});
        },
        error: function(){
            console.log("Error remove song request");
        }
    });    
    }
function viewHelp(){
  console.log("2");
  $("#center-pane").load("/resources/pages/help.jsp",function(){
    console.log("3");
  });
}

function viewPendingRoyaltyPayments(){
  console.log("init pending royalty");
  $.ajax({
      url: "artist/viewPendingRoyaltyPayments",
      type: "GET",
      success:function(){
          $("#center-pane").load("/resources/pages/pendingRoyalty.jsp",function(){
          });
      },
      error: function(){
          console.log("Error viewing pending royalty");
      }
  });
  return false; // Makes sure that the link isn't followed
}

function adminPaySongRoyalties(songId,artistId){
    $.ajax({
        url: "artist/adminPaySongRoyalties",
        type: "POST",
        data: ({
            songId: songId,
            artistId: artistId
        }),
        success:function(){
            $("#center-pane").load("/resources/pages/unpaidSongs.jsp",function(){
                
            });
        },
        error: function(){
            console.log("Error paying songs");
        }
    });
    return false; // Makes sure that the link isn't followed
}

function requestRoyaltyOnSong(songId){
    $.ajax({
        url: "artist/requestRoyaltyOnSong",
        type: "POST",
        data: ({
            songId: songId
        }),
        success:function(){
            $("#center-pane").load("/resources/pages/unpaidSongs.jsp",function(){
                
            });
        },
        error: function(){
            console.log("Error doings songs");
        }
    });
    return false; // Makes sure that the link isn't followed
}

function viewUnpaidSongs(){
  console.log("init unpaid songs");
  $.ajax({
      url: "artist/viewUnpaidSongs",
      type: "GET",
      success:function(){
          $("#center-pane").load("/resources/pages/unpaidSongs.jsp",function(){
          });
      },
      error: function(){
          console.log("Error viewing unpaid songs");
      }
  });
  return false; // Makes sure that the link isn't followed
}

function viewQueue(){
  $.ajax({
      url: "songPlayer/viewQueue",
      type: "GET",
      success:function(){
          $("#center-pane").load("/resources/pages/queue.jsp",function(){
          });
      },
      error: function(){
          console.log("Error viewing queue");
      }
  });
  return false; // Makes sure that the link isn't followed
}

function viewHistory(){
  $.ajax({
      url: "songPlayer/viewHistory",
      type: "GET",
      success:function(){
          $("#center-pane").load("/resources/pages/history.jsp",function(){
          });
      },
      error: function(){
          console.log("Error viewing history");
      }
  });
  return false; // Makes sure that the link isn't followed
}

// Update the current slider value (each time you drag the slider handle)
function changeVolume(){
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
function editPaymentInfo(){
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
         url: "editPaymentInfo",
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
      $("#upgradeError").html('Invalid Expiration Date');
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

function viewUpgradePage(){
    $("#center-pane").load("/resources/pages/upgrade.jsp");
}

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
  var pageType = $("#center-pane").children().eq(1);;
  var elem = $(pageType);
  var onQueuePage = ($(elem).attr("id")=='queue');
  var onHistoryPage = ($(elem).attr("id")=='history');
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
      if(onQueuePage){
        viewQueue();
      }
      if(onHistoryPage){
        viewHistory();
      }
    },
    failure: function(){
      console.log("Failure playing song");
    }
  });
  return false;
}

function playNext(){
   var repeatTag = $("#repeatTag");
   var shuffleTag = $("#shuffleTag");
   var sliderVal=($('#myRange')[0]).value;
   var pageType = $("#center-pane").children().eq(1);;
   var elem = $(pageType);
   var onQueuePage = ($(elem).attr("id")=='queue');
   var onHistoryPage = ($(elem).attr("id")=='history');
   adCount+=1;
   if(adCount>=5){
     $("#ad").show();
     adCount=0;
   }
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
      });
      if(onQueuePage){
        viewQueue();
      }
      if(onHistoryPage){
        viewHistory();
      }
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
  var pageType = $("#center-pane").children().eq(1);;
  var elem = $(pageType);
  var onQueuePage = ($(elem).attr("id")=='queue');
  var onHistoryPage = ($(elem).attr("id")=='history');
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
      if(onQueuePage){
        viewQueue();
      }
      if(onHistoryPage){
        viewHistory();
      }
    },
    failure:function(){
      console.log("Failure playing prev song");
    }
  });
  return false;
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
  var queue = $("#center-pane").children().eq(1);;
  var q = $(queue);
  var onQueuePage = ($(q).attr("id")=='queue');
  var shuffleTag = $("#shuffleTag")[0];
  if($(shuffleTag).hasClass("shuffleOn"))
    $(shuffleTag).removeClass("shuffleOn");
  else
    $(shuffleTag).addClass("shuffleOn");
  $.ajax({
    url: "songPlayer/toggleShuffle",
    type: "GET"
  });
  if(onQueuePage)
    viewQueue();
  return false;
}

function addSongToQueue(songId){
  $.ajax({
    url: "songPlayer/addSongToQueue",
    type: "GET",
    data: ({
      songId: songId
    }),
    error:function(){
      console.log("Error adding song to queue");
    }
  });
  return false;
}

function addPlaylistToQueue(playlistId){
  $.ajax({
    url: "songPlayer/addPlaylistToQueue",
    type: "GET",
    data: ({
      playlistId: playlistId
    }),
    error:function(){
      console.log("Error adding playlist to queue");
    }
  });
  return false;
}

