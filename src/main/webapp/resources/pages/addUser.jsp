<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="main-section">
  <div class="col-xs-8 col-xs-offset-2" id="account-pane">
    <div id="user-information" class="row">
      <div class="row"> 
        <form id="addUserForm">
          <input type="text" id="username" name="username" placeholder="Username" class="form-control" required autofocus>
          <input type="text" id="email" name="email" placeholder="Email" class="form-control" required>
          <input type="password" id="password" name="password" placeholder="Password" class="form-control"  required>
          <label for="artistAccount Opt">Artist?</label>
          <input id="artistAccountOpt" name="artist" type="checkbox" value="true">
          <input id="artistAccountOpt" name="artist" type="hidden" value="false" >
          <button class="btn btn-lg btn-primary btn-block" type="submit" >Add User</button>
        </form>
      </div>
    </div>
  </div>
</div>