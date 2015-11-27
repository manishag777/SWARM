var idStoreMap = new Object();
var idMarkerMap = new Object();
var idCircleMap = new Object();
var idParentMap = new Object();
var graphData = [];
var mapMarker = [];
var circleMarker = [];

$(document).ready(function(){
	    $('#panelDiv').height($(window).height() - 150 );
	    $('#mapDiv').height($(window).height() - 150 );
	    $('#addStore').click(addRow);
	    //$("#suggestion-block").style.visibility = 'hidden';
	    fetchStoreList();
	    $('#getSuggestion').click(getSuggestion);
});


var addRow = function(){
	console.info("Hello");
	var storeName = $("#store-type-filter option:selected").text();
	var storeVal = $("#store-type-filter").val();
	if(storeVal == "0") return;
	console.info(storeName + " "+ storeVal);
	updateMap(storeVal);

	var parameter = '\"'+storeVal +'\"' ;
	var parameter2 = '\"'+storeName +'\"' ;
	removeFromStoreDropDown(parameter);
	
	var rowHTML = '<td  style= "width: 10%"> <img class = "imgClass" onClick = "removeRow('+this +')" src = "resources/app/img/cancel.png" style = "height:20px; width:20px; margin:0 auto;"/> </td>'
				 + '<td style= "width: 30%" >'+storeName +'</td>'
				 + '<td style= "width: 60%;" class="drop-downClass" >'
					 + '<select class="drop-downClass form-control" style = "width:80%; margin:0 auto;">'
					 + '<option selected="selected" value="0" >Select range</option>'
					 +  '<option value="15" >Within 15 KM.</option>'
					 + '<option value="30" >Within 30 KM.</option>'
					 + '<option  value="45" >Within 45 KM.</option>'
			         + '</select>'
		         + '</td>'
			         +	'<td style= "display:none;">' 
					 +  '<input name="storeId"  class="form-control pid" value =' + parameter +'></input>'	
				 +	'</td>'
			    	+	'<td style= "display:none;">' 
			    	+  '<input name="storeName"  class="form-control pid" value =' + parameter2 +'></input>'	
			    +	'</td>';
				
	console.info(rowHTML);
	var table = document.getElementById("store-table");
	var row = table.insertRow(-1);
	row.innerHTML = rowHTML;	
	row.className = "rowClass";
	
	$( ".imgClass" ).click(function() {
		console.info('clickedRow');
		var parent = $(this).parent().parent();
		var storeId  = parent.children().eq(3).children().val();
		var storeName  = parent.children().eq(4).children().val();
		parent.remove();
		var select = document.getElementById('store-type-filter');
		select.appendChild(createOption(storeId,storeName));
		idMarkerMap[storeId].setMap(null);
		idMarkerMap[storeId] = null;
		if(isRealValue(idCircleMap[storeId])) {
			idCircleMap[storeId].setMap(null);
			idCircleMap[storeId] = null;
		}
	});
	
	$( ".drop-downClass" ).click(function() {
		var parent = $(this).parent().parent();
		var storeId  = parent.children().eq(3).children().val();
		var address =  idStoreMap[storeId];
		var location = {lat : address.lat, lng : address.lng};
		var radius = $(this).val();
		//console.info(storeVal);
		if(isRealValue(idCircleMap[storeId])) 
			idCircleMap[storeId].setMap(null);
		idCircleMap[storeId] =  drawCircleStore(location,radius,address.index);
		
	});
}

var removeFromStoreDropDown = function(storeVal){
	$('#store-type-filter option[value='+ storeVal +']').remove();		
}

var drawClusterDonutGraph = function(clusterData){
	
	var clusters = clusterData.clusters;
	var unused  = clusterData.unusedIndex;
	var totalArr = [];
	for(var i=0; i<clusters.length; i++){
		totalArr.push(clusters[i].length);
		total += clusters[i].length;
	}
	
	var indexesArr = clusterData.clusterIndexes;
	var storeDtoArr = clusterData.store;
	var plotData  = [];
	for(var i=0; i<indexesArr.length; i++){
		var storeDto = storeDtoArr[i];
		plotData.push({label:storeDto.name, value : totalArr[indexesArr[i]] });
	}
	
	plotData.push({label:"New Store", value : totalArr[unused] });
	
	var donut = new Morris.Donut({
	    element: 'sales-chart',
	    resize: true,
	    colors: ["#3c8dbc", "#f56954", "#00a65a"],
	    data: plotData,
	    hideHover: 'auto'
	  });	
}

var fetchStoreList = function(){
	
	var select = document.getElementById('store-type-filter');
	select.appendChild(createOption(0,"Select Store"));
	$.ajax({
		url : 'fetchStoreList',
		type : 'GET',
		contentType : "application/json",
		success : function(data) {
			sportData = data;
			console.info(data);

			for (var i in data) {
				data[i].index = 1+parseInt(i);
				idStoreMap[data[i].id] =  data[i];
				var opt = document.createElement('option');
				select.appendChild(createOption(data[i].id, data[i].name));				
			}
			
		},
	}).done(function() {
				
	});
}

var createOption = function(value, name){
	var opt = document.createElement('option');
	opt.value = value;
	opt.innerHTML = name;
	return opt;
}

function updateMap(storeVal){
	var address =  idStoreMap[storeVal];
	var location = {lat : address.lat, lng : address.lng};
	var marker = addMarker(location,'resources/app/img/map-marker/store'+address.index+'.png',"Store: " +address.name ,false);
	idMarkerMap[storeVal] = marker;
	//drawCircleStore(location,15,address.index);
	
}

var getSuggestion = function(){


	console.info($("#suggestion-block"));
	console.info($("#suggestion-block").style);
	document.getElementById("suggestion-block").style.display = 'block';

	var count = 0;
	var res = [];
	$('#store-table tr').each(function(){
	    if(count>0){
	    	var obj = new Object();
	    	obj.storeId = $(this).children().eq(3).children().val();
	    	obj.dist = $(this).children().eq(2).children().val();
	    	res.push(obj);
	    	console.info(obj);
	    	if(obj.dist == 0)
	    		alert("Make sure you have selected the range of every store");
	    }
	    count++;	    
	});
	
	for(var i=0; mapMarker.length;  i++){
		mapMarker[i].setMap = null;
	}
	mapMarker = [];
	getClusteringResult(res);
}

var getClusteringResult = function(res){

	$.ajax({
		url : 'getClusteringResult',
		type : 'POST',
		contentType : "application/json",
		data : JSON.stringify(res),
		success : function(data) {
			removeCircle(data);
			console.info(data);
			updateMapWithData(data);
			updateSuggestedLocation(data);
			drawClusterDonutGraph(data);
		},
	}).done(function() {
				
	});
}

var removeCircle = function(data){
	var storeList = data.store;
	for(var i=0; i<storeList.length; i++){
		var storeId = storeList[i].id;
		if(idCircleMap[storeId]!=null){
			idCircleMap[storeId].setMap(null);
			idCircleMap[storeId] = null;
		}
	}
}

var updateSuggestedLocation = function(data){


	var index = data.unusedIndex;
	console.info(index);
	var centroids = data.centroids;
	var longLat = centroids[index];
	console.info(longLat);
	
	var clusters = data.clusters;
	var total = 0;
	for(var i=0; i<clusters.length; i++){
		total += clusters[i].length;
	}
	
	var percent = ((clusters[index].length) / total)*100 ;
	document.getElementById("sharePercent").innerHTML = Number((percent).toFixed(2)) + "%";
	getAddress(longLat.lat,longLat.lng,"saddress");
	getAddress(longLat.lat,longLat.lng,"caddress");
}

var getAddress = function(lat, long, address){



	var geocoder;
	geocoder = new google.maps.Geocoder();
	var latlng = new google.maps.LatLng(lat, long);
	var place, city, state, country; 
	var res;

	geocoder.geocode(
	    {'latLng': latlng}, 
	     function(results, status, id) {
	        if (status == google.maps.GeocoderStatus.OK) {
	                if (results[0]) {
	                    var add= results[0].formatted_address ;
	                    var  value=add.split(",");
	                    count=value.length;
	                    country=value[count-1];
	                    state=value[count-2];
	                    city=value[count-3];
	                    place=value[count-4];
	                    res = place +", "+ city+", "+state+", "+country;
	                    document.getElementById(address).innerHTML  = res;
	                    
	                }
	                else  {
	                	res = "address not found" ;
	                	document.getElementById(address).innerHTML  = res;
	                    //alert("address not found");
	                }
	        }
	         else {
	        	 res = "Geocoder failed due to: " + status ;
	        	 document.getElementById(address).innerHTML  = res;
	           // alert("Geocoder failed due to: " + status);
	        }
	    }
	);
	return res;
}
