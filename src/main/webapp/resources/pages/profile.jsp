<div id="main-section">
  <div class="col-xs-8 col-xs-offset-2" id="account-pane">
    <div id="account-header"><h2>User Name Account Settings</h2></div>
    <div id="account-information" class="row">
      <div class="col-xs-8">
        <ul>
          <li>
            <label for="username">Username:</label>
            <p>${currentUser.username}</p>
          </li>
          <li>
            <label for="email">E-Mail:</label>
            <p>${currentUser.email}</p>
          </li>
          <li>
            <label for="account-type">Account Type:</label>
            <p id="account-type">${currentUser.accountType}</p>
          </li>
        </ul>
        <button id="edit-profile-button" class="btn" onclick="viewEditProfile()">Edit Profile</button>
      </div>
      <div class="col-xs-4">
          <a href="#"><img src="${currentUser.imagePath}" class="img-circle userPic" id="user-img"></a>
      </div>
    </div>
  </div>
</div>
