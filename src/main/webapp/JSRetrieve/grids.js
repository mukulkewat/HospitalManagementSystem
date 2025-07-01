// grids.js
define([
  "dojo/request",
  "dojo/data/ItemFileWriteStore",
  "dojox/grid/EnhancedGrid",
  "dojo/domReady!"
], function(request, ItemFileWriteStore, EnhancedGrid) {

  return {
    load: function(containerId) {
      request.get("getPatients", {
        handleAs: "json"
      }).then(function(data) {
        var store = new ItemFileWriteStore({
          data: {
            identifier: "name",
            items: data
          }
        });

        var layout = [
          { name: "Name", field: "name", width: "150px" },
          { name: "Department", field: "department", width: "200px" },
          { name: "AppointmentDate", field: "appointmentDate", width: "150px" },
          { name: "AppointmentTime", field: "appointmentTime", width: "150px" }
        ];

        var grid = new EnhancedGrid({
          store: store,
          structure: layout,
          autoHeight: true,
          autoWidth: true
        }, containerId);

        grid.startup();
      });
    }
  };
});
