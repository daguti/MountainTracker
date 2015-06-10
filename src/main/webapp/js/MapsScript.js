/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function initialize() {
  var mapProp = {
    center:new google.maps.LatLng(51.508742,-0.120850),
    zoom:6,
    disableDefaultUI:true,
    mapTypeId:google.maps.MapTypeId.HYBRID
  };
    var map=new google.maps.Map(document.getElementById("googleMap"), mapProp);
    var myCenter=new google.maps.LatLng(51.508742,-0.120850);
    var marker=new google.maps.Marker({
        position:myCenter,
        animation:google.maps.Animation.BOUNCE
    });
    var stavanger=new google.maps.LatLng(58.983991,5.734863);
    var amsterdam=new google.maps.LatLng(52.395715,4.888916);
    var london=new google.maps.LatLng(51.508742,-0.120850);
    var myTrip = [stavanger,amsterdam,london];
    var flightPath = new google.maps.Polyline({
      path:myTrip,
      strokeColor:"#0000FF",
      strokeOpacity:0.8,
      strokeWeight:2
    });
    marker.setMap(map);
    flightPath.setMap(map);
    // Zoom to 9 when clicking on marker
    google.maps.event.addListener(marker,'click',function() {
        map.setZoom(9);
        map.setCenter(marker.getPosition());
    });
    google.maps.event.addListener(map, 'click', function(event) {
        placeMarker(event.latLng, map);
    });


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
google.maps.event.addDomListener(window, 'load', initialize);


