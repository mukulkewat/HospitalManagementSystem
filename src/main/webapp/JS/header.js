require([
  "dijit/layout/BorderContainer",
  "dijit/layout/ContentPane",
  "dojo/dom-construct",
  "dojo/dom",
  "dojo/domReady!",
], function (BorderContainer, ContentPane, domConstruct, dom) {
  // Step 1: Layout Container
  var layout = new BorderContainer(
    {
      design: "headline",
    },
    "mainContainer"
  );

  // Step 2: Create top nav bar wrapper
  var navbarContainer = domConstruct.create("div", {
    id: "navbarContainer",
    style: `
    display: flex;
    align-items: center;
    justify-content: space-between;
    padding: 10px 30px;
    background: linear-gradient(to right, #0f2027, #203a43, #2c5364);
    color: white;
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
    position: sticky;
    top: 0;
    z-index: 1000;
  `,
  });

  // LEFT: Logo
  var logo = domConstruct.create(
    "img",
    {
      src: "Images/logo.png",
      alt: "Hospital Logo",
      style: "height: 50px;",
    },
    navbarContainer
  );

  // CENTER: Menu Container
  var navItemsContainer = domConstruct.create(
    "div",
    {
      style: `
      display: flex;
      justify-content: center;
      flex: 1;
      gap: 30px;
      font-weight: bold;
      font-size: 16px;
      margin-left: 50px;
    `,
    },
    navbarContainer
  );

  // RIGHT: Optional Placeholder
  domConstruct.create(
    "div",
    {
      style: "width: 60px;", // Just spacing balance
    },
    navbarContainer
  );

  // Step 3: Center pane
  var centerPane = new ContentPane({
    region: "center",
    style: "padding: 0; margin: 0;",
    content: `
    <div style="
      text-align: center;
      background: linear-gradient(to right, rgb(27, 55, 63), #0072ff);
      padding: 30px 20px 0;
      border-radius: 10px;
      color: white;
      margin: 0;
    ">
      <h2 style="margin-bottom: 20px;">Welcome to Hospital Management</h2>
      <div id="dynamicText" style="
        font-size: 18px;
        background: rgba(255, 255, 255, 0.1);
        padding: 10px;
        border-radius: 5px;
        margin-bottom: 10px;
      "></div>
      <img src="Images/hospital1.png"
     alt="Hospital Image"
     style="
       display: block;
       width: 100%;
       height: 700px;
       border-radius: 10px;
       box-shadow: 0 0 15px rgba(0,0,0,0.3);
       margin-bottom: -4px;
     ">

    </div>
  `,
  });

  // Step 4: Navbar items
  var menuItems = {
    Home: "homepage.html",
    "Need help?": "needhelp.html",
    Careers: "careers.html",

    "Leave Feedback": "feedback.html",
    Search: "search.html",
    AppointmentDetails: "grids.html",
    Logout: "logout",
  };

  for (let label in menuItems) {
    const navItem = domConstruct.create(
      "span",
      {
        innerHTML: label,
        className: "navItem",
        style: `
      cursor: pointer;
      color: white;
      padding: 8px 16px;
      border-radius: 6px;
      transition: background 0.3s;
    `,
        onclick: (function (targetPage, labelText) {
          return function () {
            if (labelText.toLowerCase() === "logout") {
              require(["dojo/request/xhr"], function (xhr) {
                xhr
                  .post("logout", {
                    handleAs: "json",
                  })
                  .then(
                    function (response) {
                      console.log("Logout success:", response);
                      // Redirect after logout
                      window.location.href = "homepage.html";
                    },
                    function (err) {
                      console.error("Logout failed", err);
                      alert("Logout failed. Please try again.");
                    }
                  );
              });
            }
            //===================================================
            else if (targetPage === "grids.html") {
              require(["dojo/request", "JSRetrieve/grids"], function (
                request,
                gridModule
              ) {
                request.get("grids.html").then(function (html) {
                  centerPane.set("content", html);

                  // Wait a moment to ensure DOM is inserted
                  setTimeout(function () {
                    gridModule.load("gridContainer");
                  }, 0);
                });
              });
            }
            // ==============================================
            else if (targetPage === "search.html" || targetPage == "index.html") {
              window.location.href = targetPage;
            } else {
              centerPane.set("href", targetPage);
            }
          };
        })(menuItems[label], label),
      },
      navItemsContainer
    );

    // ✨ Hover Effect
    navItem.onmouseenter = function () {
      this.style.background = "rgba(255,255,255,0.2)";
    };
    navItem.onmouseleave = function () {
      this.style.background = "transparent";
    };
  }

  // Step 5: Add header and center pane to layout
  var topPane = new ContentPane({
    region: "top",
    content: navbarContainer,
  });

  layout.addChild(topPane);
  layout.addChild(centerPane);
  layout.startup();

  // Step 6: Sidebar buttons
  var sidebarContainer = domConstruct.create(
    "div",
    {
      id: "rightSidebarContainer",
      style: `
      position: fixed;
      top: 40%;
      right: 0;
      transform: translateY(-50%);
      background-color: #8B0000;
      border-radius: 8px 0 0 8px;
      box-shadow: -2px 2px 10px rgba(0,0,0,0.2);
      z-index: 9999;
      display: flex;
      flex-direction: column;
      align-items: center;
      padding: 10px 0;
      gap: 15px;
    `,
    },
    document.body
  );

  function createSidebarButton(icon, label, targetPage, needsLogin) {
    var btn = domConstruct.create(
      "div",
      {
        innerHTML: `${icon}<br><span style='font-size: 13px;'>${label}</span>`,
        style: `
        color: white;
        text-align: center;
        cursor: pointer;
        padding: 10px;
        width: 120px;
        border-bottom: 1px solid rgba(255,255,255,0.2);
        font-family: Arial;
      `,
      },
      sidebarContainer
    );

    btn.addEventListener("click", function () {
      window.location.href = targetPage;
    });
  }

  createSidebarButton("📅", "Book Appointment", "BookAppointment.html", true);
  createSidebarButton("🩺", "Health Checkup", "healthCheckup.html", false);
  createSidebarButton("💻", "Consultation", "consultation.html", false);
});
