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
  

  
  // Step 4: Create menu items and click logic
  var menuItems = {
    "Need help?": "needhelp.html",
    "Careers": "careers.html",
    "Contact Us": "contact.html",
    "Leave Feedback": "feedback.html",
    "Search": "search.html"
  };

 for (let label in menuItems) {
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
    onclick: (function (targetPage) {
      return function () {
        centerPane.set("href", targetPage);
      };
    })(menuItems[label]),
  }, navItemsContainer);

  // ✨ Hover Effect
  navItem.onmouseenter = function () {
    this.style.background = "rgba(255,255,255,0.2)";
  };
  navItem.onmouseleave = function () {
    this.style.background = "transparent";
  };
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
