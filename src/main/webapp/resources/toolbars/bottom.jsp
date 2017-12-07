<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<div id="bottom-toolbar">
    <div class="col-xs-3" id="songInfo">
        <div id="albumArt" class="col-xs-3">
            <a href='#"' onclick="viewAlbum(${currentSong.albumId.albumId})"><img src="${currentSong.albumId.imagePath}" onerror="this.src='http://placehold.it/50x50'" height="50" width="50"></a>
        </div>
        <div class="col-xs-4">
            <div class="row">
                <a href="#" id="albumSongNamePlayer"onclick="viewAlbum(${currentSong.albumId.albumId})">${currentSong.title}</a>
            </div>
            <div class="row">
                <a href="#" id="artistNamePlayer" onclick="viewArtist(${currentSong.artistId.artistId})">${currentSong.artistId.artistName}</a>
            </div>
        </div>
        <a href="#">
            <i id="addSong" class="fa fa-plus"></i>
        </a>
    </div>
    <div class="col-xs-6" id="musicPlayer">
        <div class="row" id="musicControl">
            <a href="#" id="shuffleTag" onclick="toggleShuffle()">
                <i id="shuffleSong" class="fa fa-random"></i>
            </a>
            <a href="#" onclick="playPrev()">
                <i id="prevButton" class="fa fa-step-backward"></i>
            </a>
            <a href="#" id="playPause" onClick="togglePlayPause()">
                <i id="playPauseIcon" class="fa fa-play"></i>
            </a>
            <a href="#" onclick='playNext()'>
                <i id="nextButton" class="fa fa-step-forward"></i>
            </a>
            <a href="#" id="repeatTag" onclick="toggleRepeat()">
                <i id="repeatButton" class="fa fa-repeat"></i>
            </a>
        </div>
        <audio id="audio" src="${currentSong.audioPath}">Your browser does not support the <code>audio</code> element.</audio>
        
        <div class="row" id="progressRow">
            <div class="col-xs-3" id="currentTime">
                0:00
            </div>
            <div id ="progress" class="progress col-xs-6">
                <div class="progress-bar" role="progressbar" aria-valuenow="0"
                aria-valuemin="0" aria-valuemax="100" style="width:0%">
                  <span class="sr-only"></span>
                </div>

            </div>
            <div class="col-xs-3" id="songDuration">
              <fmt:formatNumber value="${(currentSong.duration/60) - ((currentSong.duration/60)%1)}" maxFractionDigits="0"/>:<fmt:formatNumber value="${currentSong.duration%60}" minIntegerDigits="2"/>
            </div>            
        </div>

    </div>
    
    <div class="col-xs-3" id="playerSettings">
        <a href="#">
            <i id="toggleMute" class="fa fa-volume-up"></i>
        </a>
        <a href="#">
            <i id="lyrics" class="fa fa-book"></i>
        </a>
        <a href="#" id="viewQueue" onclick="viewQueue()">
            <i id="queue" class="fa fa-list-ol"></i>
        </a>
      <div id="slidecontainer">
          <input type="range" min="0" max="100" value="50" class="slider" id="myRange">
      </div>
    </div>
</div>
