/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var coordinates = [];
var panorama;

function initialize() {
  var myCenter=coordinates[0];
  var mapProp = {
    center:myCenter,
    zoom:8,
    disableDefaultUI:true,
    mapTypeId:google.maps.MapTypeId.HYBRID
  };
    var map=new google.maps.Map(document.getElementById("googleMap"), mapProp);
    var markerOrig=new google.maps.Marker({
        position:myCenter,
        animation:google.maps.Animation.BOUNCE
    });
    var markerDest=new google.maps.Marker({
        position:coordinates[coordinates.length - 1],
        animation:google.maps.Animation.BOUNCE
    });
    var flightPath = new google.maps.Polyline({
      path:coordinates,
      strokeColor:"#0000FF",
      strokeOpacity:0.8,
      strokeWeight:2
    });
    markerOrig.setMap(map);
    markerDest.setMap(map);
    flightPath.setMap(map);
    panorama = map.getStreetView();
    
    panorama.setPov(/** @type {google.maps.StreetViewPov} */({
      heading: 265,
      pitch: 0
    }));

    // TOGGLE STREET VIEW ON MARKER ORIG OR MARKER DEST
    google.maps.event.addListener(markerOrig,'click',function() {
        panorama.setPosition(markerOrig.getPosition());
        toggleStreetView();
    });
    google.maps.event.addListener(markerDest,'click',function() {
        panorama.setPosition(markerDest.getPosition());
        toggleStreetView();
    });
    /*google.maps.event.addListener(map, 'click', function(event) {
        placeMarker(event.latLng, map);
    });*/
}
function toggleStreetView() {
  var toggle = panorama.getVisible();
  if (toggle == false) {
    panorama.setVisible(true);
  } else {
    panorama.setVisible(false);
  }
}

function placeMarker(location, map) {
  var marker = new google.maps.Marker({
    position: location,
    map: map,
  });
  var infowindow = new google.maps.InfoWindow({
    content: 'Latitude: ' + location.lat() +
    '<br>Longitude: ' + location.lng()
  });
  infowindow.open(map,marker);
}

function createMap(coords) {
    var cutCords = coords.split("|");
    var indivCut;
    var i = 0;
    
    for(i = 0; i < cutCords.length; i++) {
        indivCut = cutCords[i].split(",");
        coordinates.push(new google.maps.LatLng(indivCut[0],indivCut[1]));
    }
    google.maps.event.addDomListener(window, 'load', initialize);
}


