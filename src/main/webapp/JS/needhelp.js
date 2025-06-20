require([
      "dojo/parser",
      "dojo/on",
      "dijit/form/Form",
      "dijit/form/ValidationTextBox",
      "dijit/form/Select",
      "dijit/form/SimpleTextarea",
      "dijit/form/Button",
      "dojo/domReady!"
    ], function(parser, on) {
      parser.parse();
      var form = dijit.byId("helpForm");

      on(form, "submit", function(e) {
        e.preventDefault();
        if (form.validate()) {
          alert("Thank you! Your help request has been submitted.");
          form.reset();
        } else {
          alert("Please complete all required fields.");
        }
      });
    });