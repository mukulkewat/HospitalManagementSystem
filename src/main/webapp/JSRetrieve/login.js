require([
  "dojo/ready",
  "dojo/on",
  "dojo/request/xhr",
  "dijit/registry",
  "dojo/parser"
], function (ready, on, xhr, registry, parser) {
  ready(function () {
    let loginBtn = registry.byId("loginBtn");

    on(loginBtn, "click", function (e) {
      e.preventDefault();

      const username = registry.byId("usernameInput").get("value");
      const password = registry.byId("passwordInput").get("value");

      if (username && password) {
        xhr.post("login", {
          data: JSON.stringify({
            email_mobileno: username,
            password: password
          }),
          handleAs: "json",
          headers: {
            "Content-Type": "application/json"
          }
        }).then(function (response) {
          if (response.status === "success") {
            window.location.href = response.redirect;
          } else {
            document.getElementById("loginError").innerText = response.message || "Login failed";
            document.getElementById("loginError").style.display = "block";
          }
        }, function (err) {
          console.error("XHR Error:", err);
          alert("Server error. Try again later.");
        });
      }
    });
  });
});
