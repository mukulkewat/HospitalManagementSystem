require([
    "dojo/request/xhr",
    "dojo/domReady!"
  ], function(xhr) {

    // This runs as soon as DOM is fully loaded
    xhr.post("logout", {
      handleAs: "json"
    }).then(function(response) {
      console.log("Logged out:", response);
      // ✅ Optional: Redirect to login page
      window.location.href = "login.html";
    }, function(err) {
      console.error("Logout failed:", err);
    });

  });

