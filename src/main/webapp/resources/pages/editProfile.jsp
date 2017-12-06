<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="main-section">
  <div class="col-xs-8 col-xs-offset-2" id="account-pane">
    <div id="account-header"><h2>${currentUser.username} Account Settings</h2></div>
    <div id="account-information" class="row">
<div class="col-xs-8">
    <div id="account-information" class="row">
        <ul>
          <li>
            <div class="row">
          <div class="col-xs-8">
            <label for="account-type">Account Type:</label>
            <p id="account-type">${currentUser.accountType}</p>
            <!-- <p id="account-type">{currentUser.accountType} </p> -->
          </div>
          <div class="col-xs-4">
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
            <a data-toggle="modal" href="#deleteAccount">Delete Account
            <i id="deleteButton" class="fa fa-bomb"></i>
          </a>
          </li>
        </ul>
      </div>
    </div>
        <div username="${currentUser.username}" id="deleteAccount" class="modal fade" role="dialog">
        <div class="modal-dialog">
          <!-- Modal content-->
          <div class="modal-content" id="modalBackground">
            <div class="modal-header">
              <span id="closeSpan"><button id="closeButton" type="button" class="btn fa fa-close" data-dismiss="modal"></button></span>
              <h4 class="modal-title">Delete Account: ${currentUser.username}</h4>
            </div>
            <form id="deleteAccountForm">
              <div class="modal-body">
                <div class="row">
                  <div class="col-xs-6 form-group">
                  <input class="form-control" id='delete-pass' type="password" placeholder="enter password" required>
                  </div>
                </div>
                <div class="row">
                  <div class="col-xs-6 form-group">
                    <input id="delete-pass-confirm" class='form-control' type="password" placeholder="confirm password" required>
                  </div>
                </div>
              </div>
              <div class="modal-footer">
                <button class="btn btn-info" type="submit" value="Submit">Submit</button>
              </div>
            </form>
          </div>
        </div>
      </div>  
        
      <div class="container-fluid col-xs-4">
            <div class="userCard">
              <a href="#"><img src="${currentUser.imagePath}" class="img-circle userPic" id="user-img"></a>
              <div class="userOverlay">     
                <a href="#">Change</a>
                <input type="file" id="my_file" style="display: none;" />
              </div>
            </div>
      </div>
    </div>
  </div>
</div>