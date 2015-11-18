
$(document).ready(function () {
	
	$('#pinCode').keyup(function(e){
		if(e.keyCode == 13)
		{
			var pinCode = document.getElementById("pinCode").value;
			getAndSetAddress(pinCode+"_India");
		}
	})
});


var getAndSetAddress = function(pinCode){
	
	console.info(pinCode);
	$.ajax({
		url : 'http://maps.googleapis.com/maps/api/geocode/json',
		data : {address:pinCode},
		type : 'GET',
		success : function(data) {
			console.info(data);
			parseData(data);
		},
	}).done(function() {

	});
	
}

var parseData = function(googleMapData){
	status = googleMapData.status;
	if(status == "OK")
	{
		pincode = $("#pinCode").val();
		console.info(pincode);

		try {
			var array = googleMapData.results;
			console.info("array");
			var addressComponent = array[0].address_components;
			console.info("geometry");
			console.info(array[0].geometry);
			geometry = array[0].geometry.location;
			console.info(geometry);
			var length = addressComponent.length;
			if(addressComponent[length-1].short_name != "IN"){
				printError();
				return;
			}
			
			try{
				document.getElementById("pincode-span").innerHTML = '';
				var state = addressComponent[length-2].long_name;
				$("#state").val(state);
			}
			catch(err){
				return;
			}
			
		}
		catch(err) {
			console.info("got error");
			printError();
		}
	}
	else{
		printError();
	}
	
}

var printError = function(){
	$("#pinCode").val(0);
	pincode = '';
	document.getElementById("pincode-span").innerHTML = "Incorrect";
	document.getElementById("pincode-span").style.color = "red"; 
}


