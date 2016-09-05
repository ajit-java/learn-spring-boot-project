<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script>
$(function() {
    $.ajax({
        type: "GET",
            beforeSend: function (request)
            {
                request.setRequestHeader("Access-Control-Allow-Origin", "https://sso.sandbox-immobilienscout24.de");
                request.setRequestHeader("Access-Control-Allow-Credentials", "true");
            },
        url: "authenticate",
        crossDomain: true,
        dataType: "json",
        xhrFields: {
            withCredentials: true
        }
    }).success(function(data, status, jqxhr) {
        console.log(data);
        $("#user").html(jqxhr.responseText);
    }).fail(function(data, status, jqxhr) {
        $("#user").html("None");
    });
  $("#loginBtn").click(function() {
    $.ajax({
        type: "GET",
            beforeSend: function (request)
            {
                request.setRequestHeader("Access-Control-Allow-Origin", "https://sso.sandbox-immobilienscout24.de");
                request.setRequestHeader("Access-Control-Allow-Credentials", "true");
            },
        url: "authenticate_old",
        crossDomain: true,
        dataType: "json",
        xhrFields: {
            withCredentials: true
        }
    }).success(function(data, status, jqxhr) {
        console.log(data);
        $("#user").html(jqxhr.responseText);
    }).fail(function(data, status, jqxhr) {
       location.href = "https://sso.sandbox-immobilienscout24.de/sso/authenticate?appName=expertenforum&sso_return=https://exfo.sandbox-immobilienscout24.de:8444/auth/security/sso_login?original-resource=https://exfo.financescout24.loc:8443/expert-forum/authtest";
    });
  });

  $("#logoutBtn").click(function() {
    $.ajax({
        type: "GET",
            beforeSend: function (request)
            {
                request.setRequestHeader("Access-Control-Allow-Origin", "https://sso.sandbox-immobilienscout24.de");
                request.setRequestHeader("Access-Control-Allow-Credentials", "true");
            },
        url: "logout",
        crossDomain: true,
        dataType: "json",
        xhrFields: {
            withCredentials: true
        }
    }).complete(function(data, status, jqxhr) {
      location.href = "https://sso.sandbox-immobilienscout24.de/sso/logout?sso_return=https://exfo.financescout24.loc:8443/expert-forum/authtest";
    });

  });

});
</script>
</head>
<body>
  <div>Caution! In WebSecurityConfig.java replace .httpBasic().and() by .httpBasic().disable() to make this work!</div>
  <br /><br />
  <button id="loginBtn">Login / Register</button>
  <br /><br />
  <button id="logoutBtn">Logout</button>
  <br /><br />
  <a href="securedTestPage">Enter secured page</a>
  <h2>Logged In User</h2>
  <div id="user">
    ${principal!'None'}
  </div>
</body>
</html>