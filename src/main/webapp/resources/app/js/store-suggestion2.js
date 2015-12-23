

$(document).ready(function () {	
	initMap();
	$('#addStore').click(updateMap);
});

function initMap() {
	var delhi  = {lat : 28.664970, lng : 77.169473};
	googleMap = new google.maps.Map(document.getElementById('map'), {
	    zoom: 12,
	    center: delhi
	  }); 
}


