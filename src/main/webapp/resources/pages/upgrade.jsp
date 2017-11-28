<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="main-section">
  <div class="col-xs-8 col-xs-offset-2" id="account-pane">
    <div id="account-information" class="row">
      <div class="row">
          <c:choose>
            <c:when test="${currentUser.accountType == 'Free'}">
              <form id="upgradeForm">
                <h2>Enter Your Credit Card Information</h2>
                <div>
                    <input id='cardHold' type="text" name="cardHolder" placeholder="Name on Card" class="form-control" required autofocus>
                    <input id='cardNum' type="text" name="cardNumber" maxlength="16" placeholder="Credit Card Number" class="form-control" required>
                    <input id='ccv' type="text" name="ccv" maxlength="4" placeholder="CCV" class="form-control"  required>
                    <input id='month' type="text" name="expirationMonth" maxlength="2" minlength="2" placeholder="MM" class="form-control"  required>/
                    <input id='year' type="text" name="expirationYear" minlength="2" maxlength="2" placeholder="YY" class="form-control" required>
                    <input id='creditCompany' type="text" name="creditCompany" placeholder="Credit Card Company" class="form-control" required>
                    <input id='address' type="text" name="address" placeholder="Address" class="form-control"  required>
                    <button type="button" onclick="upgradeToPremium()">Upgrade to Premium for $9.99 a month</button>
                </div>
                </form>
            </c:when>
              <c:otherwise>
                  <div>
                      Hello I have the premiums
                  </div>
              </c:otherwise>
          </c:choose>
      </div>
    </div>
  </div>
</div>
