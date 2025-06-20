 require([
    "dijit/layout/ContentPane",
    "dijit/form/Button",
    "dojo/dom-construct",
    "dojo/domReady!"
  ], function (ContentPane, Button, domConstruct) {

    // Create the footer ContentPane
    var footerPane = new ContentPane({
      class: "footerPane",
      region: "bottom"
    }, domConstruct.create("div", null, document.body));

    // Add button container inside footer
    var buttonContainer = domConstruct.create("div", {
      className: "buttonContainer"
    }, footerPane.domNode);

    // Save button
    var saveButton = new Button({
      label: "Book Appointment",
      style: "width: 100px;margin-right:100px;",
      onClick: function () {
        alert("Booking Appointment Process form ....");
        window.location.href="BookAppointment.html";
      }
    }).placeAt(buttonContainer);

    // Cancel button
    var cancelButton = new Button({
      label: "FAQ",
      style: "width: 100px;",
      onClick: function () {
        alert("FAQ ....");
      }
    }).placeAt(buttonContainer);

    saveButton.startup();
    cancelButton.startup();

    // Info grid section
    var infoGrid = domConstruct.create("div", {
      className: "infoGrid"
    }, footerPane.domNode);

    // Column 1
    domConstruct.create("div", {
      className: "infoItem",
      innerHTML: "<strong>Center Of Excellence</strong><br><ul id='list'><li>Cardiac Science</li><li>Neuro Sciences</li><li>Orthopaedics</li><li>Nephrology</li><li>Urology</li><li>Gastro Sciences</li><li>Pulmonology & Critical Care</li><li>Obstetrics and Gynaecology</li><li>Paediatrics & Neonatology</li></ul>",
      style: "list-style:none;"

    }, infoGrid);

    // Column 2
    domConstruct.create("div", {
      className: "infoItem",
      innerHTML: "<strong>Address</strong><br>1234 Main Street, City"
    }, infoGrid);

    // Column 3
    domConstruct.create("div", {
      className: "infoItem",
      innerHTML: "<strong>Phone</strong><br>+91-9876543210"
    }, infoGrid);

    // Start ContentPane
    footerPane.startup();
  });