var googleMap;

$(document).ready(function () {
	var js = document.createElement("script");
	js.src = 'https://maps.googleapis.com/maps/api/js?key=AIzaSyD-jncTVkBCtTvR0_tvhOIZ5qYAGZrKcWQ&signed_in=true&callback=initMap';
	initMap();
});

function initMap() {
	var delhi  = {lat : 28.664970, lng : 77.169473};
	googleMap = new google.maps.Map(document.getElementById('map'), {
	    zoom: 12,
	    center: delhi
	  });
	
	  
}


//Adds a marker to the map.
var addMarker =  function(location, icon, pinCode, draggable) {

  // from the array of alphabetical characters.
	//console.info(location);
  var marker = new google.maps.Marker({
    position: location,
   // label: labels[labelIndex++ % labels.length],
    map : googleMap,
    icon : icon, 
    title : pinCode,
    draggable: draggable
  });
  
  // Add dragging event listeners.
  google.maps.event.addListener(marker, 'dragstart', function() {
      console.info("drag started...");
	  //updateMarkerAddress('Dragging...');
  });

  google.maps.event.addListener(marker, 'drag', function() {
      //updateMarkerStatus('Dragging...');
      console.info("Dragging.........");
     // updateMarkerPosition(marker.getPosition());\myMarker.position
      
  });

  google.maps.event.addListener(marker, 'dragend', function() {
    
      $("#clong").val(Number((marker.getPosition().lng()).toFixed(5)));
      $("#clat").val(Number((marker.getPosition().lat()).toFixed(5)));
      getAddress(marker.getPosition().lat(),  marker.getPosition().lng(),"caddress");
  });
  
  return marker;
  
}

function updateMapWithData(data){

	
	var centroidsList = data.centroids;
	var customersClusters = data.clusters;
	var radius = data.radius;
	var index = data.unusedIndex;
	for(var i=0; i<centroidsList.length; i++){
		if(i!=index){
			var marker = addMarker({lat : centroidsList[i].lat, lng : centroidsList[i].lng },'resources/app/img/map-marker/centroid-2.png',"Store",false);
			mapMarker.push(marker);
		}
	}
	
	drawCircle(centroidsList, radius);
	
	for(var i=0; i<customersClusters.length; i++){
		var customersList = customersClusters[i];
		for(var j=0; j<customersList.length; j++){
			var customer = customersList[j];
			addMarker({lat : customer.lat, lng : customer.lng },'resources/app/img/map-marker/cust'+(i%5 + 1)+'.png', customer.pinCode+"",false);
			mapMarker.push(marker);
		}
	}
	
	var storeList = data.store;
	var clusterIndexes = data.clusterIndexes;
	for(var i=0; i<storeList.length; i++){
		var store = storeList[i];
		console.info("store");
		console.info(store);
		var marker =  addMarker({lat : store.lat, lng : store.lng },'resources/app/img/map-marker/store'+((clusterIndexes[i])%5 + 1)+'.png', store.address+"",false);
		mapMarker.push(marker);
	}
	//Suggestion marker
	
	var marker =  addMarker({lat : centroidsList[index].lat, lng : centroidsList[index].lng },'resources/app/img/map-marker/blue-dot.png',"Open Store here",false);
	mapMarker.push(marker);
	var marker2 =  addMarker({lat : centroidsList[index].lat, lng : centroidsList[index].lng },'resources/app/img/map-marker/red-dot.png'," customized store location",true);
	mapMarker.push(marker2);
	$("#clong").val(Number((centroidsList[index].lng).toFixed(5)));
    $("#clat").val(Number((centroidsList[index].lat).toFixed(5)));
	
}

function drawCircle(centroids,radius){

	for (var i in centroids) {
	  // Add the circle for this city to the map.
		//console.info(gmc_cust[i%5]);
	  var cityCircle = new google.maps.Circle({
	    strokeColor: '#000000',
	    strokeOpacity: 0.8,
	    strokeWeight: 2,
	    fillColor:  gmc_cust[i%5],
	    fillOpacity: 0.35,
	    map: googleMap,
	    center: centroids[i],
	    radius: 1000*radius[i]
	  });
	  
	  circleMarker.push(cityCircle);
	  
	}

}

function drawCircleStore(location,radius,index){

	  //console.info(gmc_cust[i%5]);
	  var storeCircle = new google.maps.Circle({
	    strokeColor: '#000000',
	    strokeOpacity: 0.8,
	    strokeWeight: 2,
	    fillColor:  gmc_cust[index%5],
	    fillOpacity: 0.35,
	    map: googleMap,
	    center: location,
	    radius: radius*1000
	  });
	  return storeCircle;
	  
	}















