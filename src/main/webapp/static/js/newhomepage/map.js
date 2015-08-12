 var map;
      "theme: light";
      
      AmCharts.ready(function() {
          map = new AmCharts.AmMap();
          map.pathToImages = "http://www.amcharts.com/lib/3/images/";

          var dataProvider = {
              mapVar: AmCharts.maps.worldLow,
              zoomLevel: 1,
              zoomLongitude: 10,
              zoomLatitude: 52
          };

          map.areasSettings = {
              unlistedAreasColor: "#DDDDDD",
              rollOverOutlineColor: "#FFFFFF",
              rollOverColor: "#CC0000",
              balloonText: "[[title]]"
          };

          map.creditsPosition = "top-right";

          dataProvider.areas = [
              {
              title: "Russia",
              id: "RU",
              color: "#3366CC",
          groupId: "InE"},
          {
              title: "Ukraine",
              id: "UA",
              color: "#3366CC",
              groupId: "InE"},
          {
              title: "Spain",
              id: "ES",
              color: "#3366CC",
              groupId: "InE"},
          {
              title: "Pakistan",
              id: "PK",
              color: "#66CC99",
              groupId: "Im"},
          {
              title: "India",
              id: "IN",
              color: "#66CC99",
              groupId: "before2004"},
          {
              title: "Great Britain",
              id: "GB",
              color: "#66CC99",
              groupId: "Im"},
          {
              title: "United States",
              id: "US",
              color: "#3366CC",
              groupId: "InE"},
              
          {
              title: "Mexico",
              id: "MX",
              color: "#66CC99",
              groupId: "Im"},
          {
              title: "Costarica",
              id: "CR",
              color: "#66CC99",
              groupId: "Im"},
          {
              title: "Hounduras",
              id: "HN",
              color: "#66CC99",
              groupId: "Im"},
          {
              title: "Colombia",
              id: "CO",
              color: "#3366CC",
              groupId: "InE"},
          {
              title: "Guatemala",
              id: "GT",
              color: "#3366CC",
              groupId: "InE"},
          {
              title: "Venezuella",
              id: "VE",
              color: "#66CC99",
              groupId: "Im"},
          {
              title: "Nicaragua",
              id: "NI",
              color: "#3366CC",
              groupId: "InE"},
          {
              title: "Ecuador",
              id: "EC",
              color: "#66CC99",
              groupId: "Im"},

          {
              title: "Panama",
              id: "PA",
              color: "#3366CC",
              groupId: "InE"},
          {
              title: "Peru",
              id: "PE",
              color: "#66CC99",
              groupId: "Im"},
          {
              title: "Brazil",
              id: "BR",
              color: "#66CC99",
              groupId: "Im"},
          {
              title: "El Salvador",
              id: "SV",
              color: "#3366CC",
              groupId: "InE"},
          {
              title: "Chile",
              id: "CL",
              color: "#66CC99",
              groupId: "2004"},
          {
              title: "Paraguay",
              id: "PY",
              color: "#66CC99",
              groupId: "Im"},
          {
              title: "Uruguay",
              id: "UY",
              color: "#66CC99",
              groupId: "Im"},
          {
              title: "Argentina",
              id: "AR",
              color: "#66CC99",
              groupId: "Im"},
          {
              title: "China",
              id: "CN",
              color: "#3366CC",
              groupId: "InE"},
          {
              title: "South Korea",
              id: "KR",
              color: "#3366CC",
              groupId: "InE"},

          {
              title: "Vietnam",
              id: "VN",
              color: "#66CC99",
              customData: "2007",
              groupId: "2007"}
          ];

          map.dataProvider = dataProvider;

          var legend = {
              width: 250,
              backgroundAlpha: 0.5,
              backgroundColor: "#FFFFFF",
              borderColor: "#666666",
              borderAlpha: 1,
              bottom: 15,
              left: 15,
              horizontalGap: 10,
              data: [
                  {
                  title: "Import and Export data",
                  color: "#3366CC"},
              {
                  title: "Import data",
                  color: "#66CC99"}
              ]
          };

          map.addLegend(legend);
          map.write("mapdiv");

      });
