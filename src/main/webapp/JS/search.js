   require([
      "dojo/dom",
      "dojo/dom-construct",
      "dojo/_base/array",
      "dojo/parser",
      "dijit/form/TextBox",
      "dijit/form/Button"
    ], function (dom, domConstruct, array, parser) {
      parser.parse();

      // Doctor database with disease keywords
      var doctorData = [
        { name: "Dr. Smith - Cardiologist", diseases: ["heart", "cardio", "chest pain"] },
        { name: "Dr. Anjali Mehta - Neurologist", diseases: ["brain", "headache", "stroke"] },
        { name: "Dr. Rakesh Sharma - Pediatrician", diseases: ["child", "baby", "kids", "fever"] },
        { name: "Dr. Fatima Khan - Orthopedic", diseases: ["bone", "fracture", "joint"] },
        { name: "Dr. Suresh Patel - ENT", diseases: ["ear", "nose", "throat", "cold"] }
      ];

      // Function to search doctor by disease
      window.findDoctor = function () {
        var input = dom.byId("diseaseBox").value.toLowerCase().trim();
        var resultDiv = dom.byId("results");
        domConstruct.empty(resultDiv);

        var found = false;

        array.forEach(doctorData, function (doctor) {
          var match = doctor.diseases.some(function (keyword) {
            return keyword.toLowerCase() === input;  // ✅ Exact match only
          });

          if (match) {
            domConstruct.create("div", {
              innerHTML: doctor.name +
                "<br><strong>Specializes in:</strong> " +
                doctor.diseases.join(", "),
              className: "result-item"
            }, resultDiv);
            found = true;
          }
        });

        if (!found) {
          domConstruct.create("div", {
            innerHTML: "No matching doctor found for your problem.",
            className: "result-item"
          }, resultDiv);
        }
      };

    });