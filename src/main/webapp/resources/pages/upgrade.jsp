<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="main-section">
  <div class="col-xs-8 col-xs-offset-2" id="account-pane">
    <div id="account-header"><h2>${currentUser.username} Account Settings</h2></div>
    <div id="account-information" class="row">
      <div class="col-xs-8">
          <div class="col-xs-6">
            <label for="account-type">Payment Information</label>
            <!-- <p id="account-type">{currentUser.accountType} </p> -->
          </div>
          <div class="col-xs-6">
              <button class="btn upgradeButton" onclick="upgrade()">Upgrade to Premium</button>
          </div>
      </div>
    </div>
    <div class="row">
      
    </div>
  </div>
</div>
