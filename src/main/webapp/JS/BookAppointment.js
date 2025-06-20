require([
  "dojo/parser",
  "dojo/dom",
  "dojo/on",
  "dijit/registry",
  "dojo/request/xhr",
  "dojo/domReady!",
], (parser, dom, on, registry, xhr) => {
  parser.parse();

  var form = registry.byId("appointmentForm");
  var submitb = registry.byId("submitBtn");

  on(form, "submit", function (e) {
    e.preventDefault();
    if (form.validate()) {
      let patientName = registry.byId("patientName").get("value").trim();
      let department = registry.byId("department").get("value").trim();
      let appointmentDate = registry.byId("appointmentDate").get("value");
      //convert to string
      let isoDate = appointmentDate.toISOString().split("T")[0];
      let appointmentTime = registry.byId("appointmentTime").get("value");
      //convert to string
      let timeStr = appointmentTime.toTimeString().split(" ")[0];
      let patientDetail = {
        Name: patientName,
        Department: department,
        AppointmentDate: isoDate,
        AppointmentTime: timeStr,
      };

      uploadDetail(patientDetail);

      alert("Appointment submitted successfully!");
    } else {
      alert("Please fill all required fields.");
    }
  });
  function uploadDetail(patient) {
    console.log(patient.Name);
    console.log(patient.Department);
    console.log(patient.AppointmentDate);
    console.log(patient.AppointmentTime);
	  xhr.post("appointment", {
        data: JSON.stringify(patient),
        handleAs: "json",
        headers: {
          "Content-Type": "application/json",
        }
      })
      .then(
        function (response) {
          console.log("Success:", response);
          alert("Appointment submitted in xhrpost!");
        },
        function (errorr) {
          console.error("Error:", errorr);
          alert("Submission failed!");
        }
      );
  }
});
