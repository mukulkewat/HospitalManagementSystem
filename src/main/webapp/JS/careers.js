require([
      "dojo/parser",
      "dojo/on",
      "dijit/registry",
      "dojo/domReady!"
    ], function(parser, on, registry) {

      parser.parse().then(function () {
        var form = registry.byId("careerForm");

        if (!form) {
          console.error("Form not found!");
          return;
        }

        on(form, "submit", function (e) {
          e.preventDefault();

          if (form.validate()) {
            alert("Application submitted successfully!");
            form.reset();
          } else {
            alert("Please correct the errors in the form.");
          }
        });
      });
    });