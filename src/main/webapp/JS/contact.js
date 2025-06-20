require([
      "dojo/parser",
      "dojo/on",
      "dijit/registry",
      "dijit/form/Form",
      "dijit/form/ValidationTextBox",
      "dijit/form/SimpleTextarea",
      "dijit/form/Button",
      "dojo/domReady!"
    ], function(parser, on, registry) {
      parser.parse();

      var form = registry.byId("contactForm");

      on(form, "submit", function(e) {
        e.preventDefault();
        if (form.validate()) {
          alert("Thank you! Your message has been sent.");
          form.reset();
        } else {
          alert("Please fill all fields correctly.");
        }
      });
    });