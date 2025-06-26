require([
  "dijit/layout/ContentPane",
  "dijit/form/Button",
  "dojo/dom-construct",
  "dojo/domReady!"
], function (ContentPane, Button, domConstruct) {

  // Create footer container
  var footerPane = new ContentPane({
    class: "footerPane",
    region: "bottom",
    style: "background: linear-gradient(to right, #2c3e50, #3498db); color: white; padding: 30px;"
  }, domConstruct.create("div", null, document.body));

  // === Buttons Row ===
  var buttonContainer = domConstruct.create("div", {
    className: "buttonContainer"
  }, footerPane.domNode);

  var saveButton = new Button({
    label: "Book Appointment",
    style: "width: 160px; background-color: #1abc9c; color: white; font-weight: bold;",
    onClick: function () {
      alert("Redirecting to Book Appointment...");
      window.location.href = "BookAppointment.html";
    }
  }).placeAt(buttonContainer);

  var cancelButton = new Button({
    label: "FAQ",
    style: "width: 100px;  color: white; font-weight: bold;",
    onClick: function () {
      alert("FAQ coming soon...");
    }
  }).placeAt(buttonContainer);

  saveButton.startup();
  cancelButton.startup();

  // === Info Grid Section ===
  var infoGrid = domConstruct.create("div", {
    className: "infoGrid"
  }, footerPane.domNode);

  // Column 1: Departments
  domConstruct.create("div", {
    className: "infoItem",
    innerHTML: `
      <strong>Departments</strong>
      <ul class="footerList">
        <li>Cardiac Science</li>
        <li>Neuro Sciences</li>
        <li>Orthopaedics</li>
        <li>Nephrology</li>
        <li>Gastroenterology</li>
        <li>Paediatrics</li>
        <li>Gynaecology</li>
      </ul>`
  }, infoGrid);

  // Column 2: Contact Info
  domConstruct.create("div", {
    className: "infoItem",
    innerHTML: `
      <strong>Contact</strong><br>
      📍 1234 Main Street, Health City<br>
      📞 +91-9876543210<br>
      ✉️ support@hospital.com`
  }, infoGrid);

  // Column 3: Quick Links
  domConstruct.create("div", {
    className: "infoItem",
    innerHTML: `
      <strong>Quick Links</strong><br>
      <a href="#" class="footerLink">About Us</a><br>
      <a href="#" class="footerLink">Careers</a><br>
      <a href="#" class="footerLink">Privacy Policy</a><br>
      <a href="#" class="footerLink">Terms & Conditions</a>`
  }, infoGrid);

  // Column 4: Social Media
  domConstruct.create("div", {
    className: "infoItem",
    innerHTML: `
      <strong>Follow Us</strong><br>
      <a href="#" class="footerLink">Facebook</a><br>
      <a href="#" class="footerLink">Instagram</a><br>
      <a href="#" class="footerLink">LinkedIn</a><br>
      <a href="#" class="footerLink">Twitter</a>`
  }, infoGrid);

  // === Copyright ===
  domConstruct.create("div", {
    style: "text-align: center; font-size: 13px; margin-top: 25px; color: #ddd;",
    innerHTML: "&copy; 2025 Hospital Management System. All rights reserved."
  }, footerPane.domNode);

  // Final startup
  footerPane.startup();
});
