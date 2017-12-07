$(document).ready(function(){
  $(document).on({
      click: function(){
        viewEditProfile();
      }
    }, '.settings-drop');
  $(document).on({
      click: function(){
        $("#my_file").click();
      }
    }, '#user-img');
    
    $(document).on({
      change: function(){
        var $files = document.getElementById('my_file');
        if ($files.files.length) {
          if($files.files.length==0){
            console.log("no file found");
            return false;
          }
          if ($files.files.length) {
            uploadImage($files, changeProfilePic);
          }
          else{
            var  path = "/resources/img/team0n3.png"
            changeProfilePic(path);
          }
          return false;
        }
      }
    }, '#my_file');
    
    $(document).on({
      submit: function(){
        var password=$("#delete-pass").val();
        var confirm=$("#delete-pass-confirm").val();
        console.log(password);
        console.log(confirm);
        if(password!=confirm){
          bootbox.alert("Password and confirm password do not match");
          return false;
        }
        $.ajax({
          url: "deleteUser",
          type: "POST",
          data: ({
            password: password
          }),
          success:function(response){
            if(response){
              window.location.href = '/logout';
            }
            else{
              bootbox.alert("Invalid password!");
            }
          },
      });
      return false;
      }
    }, '#deleteAccountForm');
});

function uploadImage($files, handler) {
    console.log("upload function");
    // Reject big files
    if ($files.files[0].size > 1024 * 1024) {
      console.log("Please select a smaller file");
      return false;
    }

    // Begin file upload
    console.log("Uploading file to Imgur..");

    // Replace ctrlq with your own API key
    var apiUrl = 'https://api.imgur.com/3/image';
    var apiKey = '031ad79e1cfcccf';

    var settings = {
      crossDomain: true,
      processData: false,
      contentType: false,
      type: 'POST',
      url: apiUrl,
      headers: {
        Authorization: 'Client-ID ' + apiKey,
        Accept: 'application/json'
      },
      mimeType: 'multipart/form-data'
    };

    var formData = new FormData();
    formData.append("image", $files.files[0]);
    settings.data = formData;
    $.ajax(settings).done(function(response) {
      var res= JSON.parse(response);
      var path= res.data.link;
      handler(path);
    });
 }

 function changeProfilePic(path) {
  $.ajax({
    url: "/changeProfPic",
    type: "POST",
    //Sends the necessary form parameters to the servlet
    data:({
     path: path
    }),
    success: function(){
      console.log("Success changing pic");
      $("#user-img").attr("src", path);
      $("#topTool").load("/resources/toolbars/top.jsp");
    },
    error: function(){
      console.log("Failure changing pic");
    }
  });
 }

function viewUsers(){
    $.ajax({
        url: "viewUsers",
        type: "GET",
        success:function(){
            console.log("View success");
            $("#center-pane").load("resources/pages/users.jsp",function(){
                console.log("Loaded playlists into center pane!");
            });
        },
        error: function(){
            console.log("View error");
        }
    });
    return false; // Makes sure that the link isn't followed
}

function viewEditProfile(){
    $("#center-pane").load("/resources/pages/editProfile.jsp");
}

function viewProfile(){
  $("#center-pane").load("/resources/pages/profile.jsp");
}
