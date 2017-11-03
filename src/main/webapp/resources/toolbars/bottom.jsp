<div id="bottom-toolbar">
    <div class="col-xs-3" id="songInfo">
        <div id="albumArt" class="col-xs-3">
            <a href='#"'>
                <img src="/resources/img/foo.jpg" alt="Foo" height="50" width="50">
            </a>
        </div>
        <div class="col-xs-4">
            <div class="row">
                <a href="#">
                    <span id="songName">Run</span>    
                </a>
            </div>
            <div class="row">
                <a href="#">
                    <span id="artistName">Foo Fighters</span>    
                </a>
            </div>
        </div>
        <a href="#">
            <i id="addSong" class="fa fa-plus"></i>
        </a>
    </div>
    <div class="col-xs-6" id="musicPlayer">
        <div class="row" id="musicControl">
            <a href="#">
                <i id="shuffleSong" class="fa fa-random"></i>
            </a>
            <a href="#">
                <i id="prevButton" class="fa fa-step-backward"></i>
            </a>
            <a href="#" id="playPause" onClick="togglePlayPause()">
                <i id="playPauseIcon" class="fa fa-play"></i>
            </a>
            <a href="#">
                <i id="nextButton" class="fa fa-step-forward"></i>
            </a>
            <a href="#">
                <i id="repeatButton" class="fa fa-repeat"></i>
            </a>
        </div>
        <audio id="audio" src="/resources/audio/kryptonite.mp3">Your browser does not support the <code>audio</code> element.</audio>
        
        <div class="row" id="progressRow">
            <div class="col-xs-3" id="currentTime">
                0:00
            </div>
            <div class="progress col-xs-6">
                <div class="progress-bar" role="progressbar" aria-valuenow="0"
                aria-valuemin="0" aria-valuemax="100" style="width:0%">
                  <span class="sr-only"></span>
                </div>

            </div>
            <div class="col-xs-3" id="songDuration">
                
            </div>            
        </div>

    </div>
    
    <div class="col-xs-3" id="playerSettings">
        <a href="#">
            <i id="queue" class="fa fa-list-ol"></i>
        </a>
        <a href="#">
            <i id="MuteToggle" class="fa fa-volume-up"></i>
        </a>
        <!-- INSERT VOLUME SLIDER HERE -->
        <a href="#">
            <i id="fullscreen" class="fa fa-expand"></i>
        </a>

    </div>
</div>