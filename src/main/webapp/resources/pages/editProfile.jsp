<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="main-section">
  <div class="col-xs-8 col-xs-offset-2" id="account-pane">
    <div id="account-header"><h2>${currentUser.username} Account Settings</h2></div>
    <div id="account-information" class="row">
      <div class="col-xs-8">
          <div class="col-xs-6">
            <label for="account-type">Account Type:</label>
            <p id="account-type">${currentUser.accountType}</p>
            <!-- <p id="account-type">{currentUser.accountType} </p> -->
          </div>
          <div class="col-xs-6">
              <button class="btn upgradeButton" onclick="viewUpgradePage()">
                <c:choose>
                  <c:when test="${currentUser.accountType == 'Free'}">
                    Upgrade to Premium
                  </c:when>
                  <c:otherwise>
                    View Your Premium Status
                  </c:otherwise>
                </c:choose>
              </button>
          </div>
      </div>
      <div class="col-xs-4">
          <i id="user-icon-img" style="font-size: 100px;" class="fa fa-user"></i>
      </div>
    </div>
  </div>
</div>
