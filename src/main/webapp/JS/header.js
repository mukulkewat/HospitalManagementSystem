require([
  "dijit/layout/BorderContainer",
  "dijit/layout/ContentPane",
  "dojo/dom-construct",
  'dojo/dom',
  "dojo/domReady!",
], function (BorderContainer, ContentPane, domConstruct,dom) {
  // Step 1: Create layout
  var layout = new BorderContainer(
    {
      design: "headline",
    },
    "mainContainer"
  );

  // Step 2: Create navbar
  var navbarDiv = domConstruct.create("div", { id: "navbar" });
  

  // Step 3: Create centerPane globally so we can update later
  var centerPane = new ContentPane({
    region: "center",
    content: `
    <div style="
      text-align: center;
    background: linear-gradient(to right, #00c6ff, #0072ff);

      padding: 30px;
      border-radius: 10px;
      color: white;
    ">

      <!-- <h2 style="margin-bottom: 20px;">Welcome to Hospital Management</h2>-->

      <!-- Dynamic text area -->
      <div id="dynamicText" style="
        font-size: 18px;
        background: rgba(255, 255, 255, 0.1);
        padding: 10px;
        border-radius: 5px;
        margin-bottom: 10px;
      "></div>

      <!-- Hospital Image -->
      <img src="https://wallpaperaccess.com/full/624111.jpg" alt="Hospital Image"
           style="max-width: 65%;max-height:100%; border-radius: 10px; box-shadow: 0 0 15px rgba(0,0,0,0.3);">
    </div>
  `,
  });
  

  
  // Step 4: Create menu items and click logic
  var menuItems = {
    "Need help?": "Here is the help information...",
    Careers: "Explore career opportunities at our hospital.",
    "Leave Feedback": "We value your feedback. Please share it!",
    Search: "Use our search feature to find doctors and services.",
  };

  for (var label in menuItems) {
    domConstruct.create(
      "span",
      {
        innerHTML: label,
        className: "navItem",
        onclick: (function (text, labelText) {
          return function () {
             var dynamicArea = document.getElementById("dynamicText");
            if (labelText === "Careers") {
                centerPane.set("href","careers.html");
            } else if (labelText === "Leave Feedback") {
              centerPane.set("href","feedback.html");
            } else if (labelText === "Need help?") {
             centerPane.set("href","needhelp.html");
            } else if (labelText === "Search") {
             centerPane.set("href","search.html");
            } else {
              // ✅ Only update the text inside the dynamicText area
              var dynamicArea = document.getElementById("dynamicText");
              if (dynamicArea) {
                
              } else {
                console.warn("dynamicText not found.");
              }
            }
          };
        })(menuItems[label], label),
      },
      navbarDiv
    );
  }

  // Step 5: Add navbar and centerPane to layout
  var topPane = new ContentPane({
    region: "top",
    content: navbarDiv,
  });

  layout.addChild(topPane);
  layout.addChild(centerPane);
  layout.startup();
});
