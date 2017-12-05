<div id="editPlaylistModal" class="modal fade" role="dialog">
        <div class="modal-dialog">
          <!-- Modal content-->
          <div class="modal-content" id="modalBackground">
            <div class="modal-header">
              <span id="closeSpan"><button id="closeButton" type="button" class="btn fa fa-close" data-dismiss="modal"></button></span>
              <h4 class="modal-title">Edit Playlist: ${currentPlaylist.playlistName}</h4>
            </div>
            <form id="updatePlaylistForm" enctype="multipart/form-data">
              <div class="modal-body">
                <div class="row form-group">
                  <input id='pName2' class="form-control" type="text" name="playlistName2" placeholder="${currentPlaylist.playlistName}">
                </div>
                <div class="row">
                  <div class="col-xs-6 form-group">
                    <img height="250" width="250" id="playlist-image2" src="${currentPlaylist.imagePath}" alt="Image" class="row mediaPic">
                    <input id='iPath2' name="imagePath2" size='20' class="row form-control" type="file" accept="image/*" style="display: none;" >
                  </div>
                  <div class="col-xs-6 form-group">
                    <textarea placeholder='${currentPlaylist.description}' id='pDesc2' class="form-control" type="textArea" rows="4" columns="5" form="updatePlaylistForm" maxlength="30" name="description"></textarea>
                  </div>
                </div>
              </div>
              <div class="modal-footer">
                <button class="btn btn-info" type="submit" value="Submit">Update</button>
              </div>
            </form>
          </div>
        </div>
      </div>