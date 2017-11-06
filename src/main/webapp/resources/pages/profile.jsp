<div id="main-section">
    <div class="col-xs-8 col-xs-offset-2" id="account-pane">
        <div id="account-header"><h2>User Name Account Settings</h2></div>
        <div id="account-information" class="row">
            <div class="col-xs-8">
                <ul>
                    <li>
                        <label for="first-name">First Name:</label>
                        <p id="first-name">User</p>
                    </li>
                    <li>
                        <label for="last-name">Last Name:</label>
                        <p id="last-name">Name</p>
                    </li>
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
                        <p id="account-type">Premium</p>
                    </li>
                </ul>
                <button id="edit-profile-button" class="btn">Edit Profile</button>
            </div>
            <div class="col-xs-4">
                <i id="user-icon-img" style="font-size: 100px;" class="fa fa-user"></i>
            </div>
        </div>
    </div>
</div>
