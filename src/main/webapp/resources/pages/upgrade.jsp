<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="main-section">
  <div class="col-xs-8 col-xs-offset-2" id="account-pane">
    <div id="account-information" class="row">
      <div class="row">
          <c:choose>
            <c:when test="${currentUser.accountType == 'Premium'}">
              <form id="upgradeForm" class="">
                <div id="cardWrapper" class="card-wrapper">
                    <!-- Used by cardjs-->
                </div>
                <span id="upgradeError" align="center"></span>
                <h2>Update Your Credit Card Information</h2>
                <div>
                    <input id='cardHold' type="text" name="name" value="${currentUser.paymentInfo.cardHolder}" class="form-control" required autofocus>
                    <input id='cardNum' type="text" name="number" maxlength="19" placeholder="Credit Card Number" value="" class="form-control" required>
                    <input id='ccv' type="text" name="cvc" maxlength="4" placeholder="CVC" value=""class="form-control"  required>
                    <input id='month' type="text" name="expiry" maxlength="7" minlength="7" placeholder="MM/YY" value = "${currentUser.paymentInfo.expirationMonth} / ${currentUser.paymentInfo.expirationYear}"class="form-control"  required>
                    <input id='creditCompany' type="text" name="creditCompany" placeholder="Credit Card Company" value="${currentUser.paymentInfo.creditCompany}"class="form-control" required>
                    <input id='address' type="text" name="address" placeholder="Address" value="${currentUser.paymentInfo.address}" class="form-control"  required>
                    <input id='zipcode' type="text" maxlength="5" minlength="5" name="zipcode" placeholder="Zip Code" value="${currentUser.paymentInfo.zipCode}"class="form-control"  required>
                    <button class="btn btn-primary" type="button" onclick="editPaymentInfo()">Edit Payment Information</button>
                </div>
                </form>
                <div>
                  <h3>You are a Premium Owner</h3>
                  <h5>Card Information</h5>
                  <p>
                      Card on record: ${currentUser.paymentInfo.creditCompany}
                      ending in ${currentUser.paymentInfo.lastFour}
                  </p>
                </div>
                <div>
                  <p>You will be billed $9.99 on the first day next month</p>
                  <button class="btn btn-primary" onclick="cancelPremium()" type="button">Cancel Premium</button>     
                </div>    
            </c:when>
            <c:otherwise>
              <form id="upgradeForm" class="">
                <div id="cardWrapper" class="card-wrapper">
                    <!-- Used by cardjs-->
                </div>
                <span id="upgradeError" align="center"></span>
                <h2>Enter Your Credit Card Information</h2>
                <div>
                    <input id='cardHold' type="text" name="name" placeholder="Name on Card" class="form-control" required autofocus>
                    <input id='cardNum' type="text" name="number" maxlength="19" placeholder="Credit Card Number" class="form-control" required>
                    <input id='ccv' type="text" name="cvc" maxlength="4" placeholder="CVC" class="form-control"  required>
                    <input id='month' type="text" name="expiry" maxlength="7" minlength="7" placeholder="MM/YY" class="form-control"  required>
                    <input id='creditCompany' type="text" name="creditCompany" placeholder="Credit Card Company" class="form-control" required>
                    <input id='address' type="text" name="address" placeholder="Address" class="form-control"  required>
                    <input id='zipcode' type="text" maxlength="5" minlength="5" name="zipcode" placeholder="Zip Code" class="form-control"  required>
                    <button class="btn btn-primary" type="button" onclick="upgradeToPremium()">Upgrade to Premium for $9.99 a month</button>
                </div>
                </form>
            </c:otherwise>
          </c:choose>
      </div>
    </div>
  </div>
</div>
<!--For the premium form-->
<script src="${pageContext.request.contextPath}/resources/js/jquery.card.js"></script>

<script src="${pageContext.request.contextPath}/resources/js/premium.js"></script>