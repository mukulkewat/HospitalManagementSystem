require([
  "dijit/layout/BorderContainer",
  "dijit/layout/ContentPane",
  "dijit/Dialog",
  "dojo/dom-construct",
  "dojo/request",
  "dojo/dom",
  "dojo/on",
  "dojo/domReady!"
], function (BorderContainer, ContentPane, Dialog, domConstruct, request, dom, on) {

  // 1. Create layout container
  var layout = new BorderContainer({
    design: "headline"
  }, "mainContainer");

  // 2. Navbar container
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
    `
  });

  // 3. Logo (left)
  domConstruct.create("img", {
    src: "Images/logo.png",
    alt: "Hospital Logo",
    style: "height: 50px;"
  }, navbarContainer);

  // 4. Menu items (center)
  var navItemsContainer = domConstruct.create("div", {
    style: `
      display: flex;
      justify-content: center;
      flex: 1;
      gap: 30px;
      font-weight: bold;
      font-size: 16px;
      margin-left: 50px;
    `
  }, navbarContainer);

  // Right spacing
  domConstruct.create("div", {
    style: "width: 60px;"
  }, navbarContainer);

  // 5. Center pane (main content)
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
    `
  });

  // 6. Menu definitions
  var menuItems = {
    "Home": "homepage.html",
    "About Us": "about.html",
    "Login": "login.html",
    "Register": "register.html"
  };

  // 7. Build each menu button
  for (let label in menuItems) {
    const targetPage = menuItems[label];

    const navItem = domConstruct.create("span", {
      innerHTML: label,
      className: "navItem",
      style: `
        cursor: pointer;
        color: white;
        padding: 8px 16px;
        border-radius: 6px;
        transition: background 0.3s;
      `,
      onclick: function () {
        if (targetPage !== "about.html") {
          window.location.href = targetPage;
        } else {
          // Show About Us in Dialog
          var existingDialog = dijit.byId("aboutDialogBox");
          if (existingDialog) {
            existingDialog.show();
            return;
          }

          var aboutDialog = new Dialog({
            id: "aboutDialogBox",
            title: "About Us",
            style: "width: 600px; background-color: white; padding: 20px; border-radius: 10px;"
          });

          request.get("about.html", {
            handleAs: "text"
          }).then(function (data) {
            aboutDialog.set("content", data);
            aboutDialog.show();
          }, function (err) {
            aboutDialog.set("content", "<p>Failed to load About Us content.</p>");
            aboutDialog.show();
          });
        }
      }
    }, navItemsContainer);

    // Hover effect
    navItem.onmouseenter = function () {
      this.style.background = "rgba(255,255,255,0.2)";
    };
    navItem.onmouseleave = function () {
      this.style.background = "transparent";
    };
  }

  // 8. Add navbar and center pane to layout
  var topPane = new ContentPane({
    region: "top",
    content: navbarContainer
  });

  layout.addChild(topPane);
  layout.addChild(centerPane);
  layout.startup();
});
