var idStoreMap = new Object();
var idMarkerMap = new Object();
var idCircleMap = new Object();
var idParentMap = new Object();

$(document).ready(function(){
	    $('#panelDiv').height($(window).height() - 150 );
	    $('#mapDiv').height($(window).height() - 150 );
	    $('#addStore').click(addRow);
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

var drawClusterDonutGraph = function(){
	
	var donut = new Morris.Donut({
	    element: 'sales-chart',
	    resize: true,
	    colors: ["#3c8dbc", "#f56954", "#00a65a"],
	    data: [
	      {label: "Download Sales", value: 12},
	      {label: "In-Store Sales", value: 30},
	      {label: "Mail-Order Sales", value: 20}
	    ],
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
	var count = 0;
	var res = [];
	$('#store-table tr').each(function(){
	    if(count>0){
	    	var obj = new Object();
	    	obj.storeId = $(this).children().eq(3).children().val();
	    	obj.dist = $(this).children().eq(2).children().val();
	    	res.push(obj);
	    	console.info(obj);
	    }
	    count++;	    
	});
	
	getClusteringResult(res);
}

var getClusteringResult = function(res){
	
	var select = document.getElementById('store-type-filter');
	select.appendChild(createOption(0,"Select Store"));
	$.ajax({
		url : 'fetchStoreList',
		type : 'POST',
		contentType : "application/json",
		data : JSON.stringify(res);
		success : function(data) {
			console.info(data);
		},
	}).done(function() {
				
	});
	
	
}















