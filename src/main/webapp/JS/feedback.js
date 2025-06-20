 require([
      "dojo/parser",
      "dijit/form/Form",
      "dijit/form/ValidationTextBox",
      "dijit/form/SimpleTextarea",
      "dijit/form/Button",
      "dojo/dom",
      "dojo/on",
      "dijit/registry",
      "dojo/domReady!"
    ], function(parser, Form, ValidationTextBox, SimpleTextarea, Button, dom, on, registry) {
      parser.parse();

      var form = registry.byId("feedbackForm");

      on(form, "submit", function (e) {
        e.preventDefault();
        if (form.validate()) {
          alert("Thank you for your feedback!");
          form.reset();
        } else {
          alert("Please fill in all required fields correctly.");
        }
      });
    });