
  require([
    "dojo/ready",
    "dojo/on",
    "dojo/request/xhr",
    "dijit/registry",
    "dojo/parser"
    
], function(ready, on, xhr, registry,parser) {
     
   ready(function () {
  
    var registerBtn = registry.byId("registerBtn");
    parser.parse().then()
    on(registerBtn, "click", function(){
        const name = dijit.byId("usernameInput").get("value").trim();
      const email = dijit.byId("emailInput").get("value").trim();
      const phone = dijit.byId("phoneInput").get("value");
      const dob = dijit.byId("dobInput").get("value").toISOString().split("T")[0];
      const password = dijit.byId("passwordInput").get("value").trim();
        console.log(name);
        console.log(email);
        console.log(phone);
        console.log(dob);
        console.log(password);
        
      // ✅ Validate fields
      if (!name || !email || !phone || !dob || !password) {
        alert("❗ Please fill all fields correctly.");
		
        return false; // Prevent form submission
      }

      // ✅ Prepare JSON object
      const user = {
        name: name,
        email: email,
        phone: phone,
        dob: dob, // format YYYY-MM-DD
        password: password
      };
	  console.log(user)

      // ✅ Send POST request with JSON payload
      xhr.post("register", {
        data: JSON.stringify(user),
        handleAs: "json",
         headers: {
           "Content-Type": "application/json"
         }
      }).then(function (response) {
        alert("✅ Registered Successfully! You can login");
        localStorage.setItem("registeredUser", JSON.stringify(user));
        console.log("Hi")
        window.location.href = "homepage.html";
      }, function (err) {
        console.error("Registration Failed:", err);
        alert("❌ Registration failed. Please try again.");
      });

      return false; // Prevent default form submission
    })
      
   });

  });

