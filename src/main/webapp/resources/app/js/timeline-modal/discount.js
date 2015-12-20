
var discountPercentage=0;

function intializeDiscount(id){

 	//setTimeout(sliderEvent(id),1000);
 	sliderEvent(id);
 	$("#maximizeRevenue"+id).click(function(){
 		updateSliderForMaximumRevenue();
 	});

 	$("#maximizeProfit"+id).click(function(){
 		updateSliderForMaximumProfit();
 	});
}


var sliderEvent = function(id){
	console.info("slideEventCalled");
	$("#discSlider"+id).slider();
	$("#discSlider"+id).slider().on('slide', function(){
 		var value = $(this).slider('getValue');
 		console.info("manish"+value);
 		updateValues(value, id);
 	});
}

var updateValues = function(value, id){

	console.info(value);
	discountPercentage = value;
	document.getElementById("dper"+id).innerHTML = value + "%";
	updateRevenue(id);
	updateProfit(id);
}


function updateRevenue(id){
	document.getElementById("revenue"+id).innerHTML = discountPercentage;
}

function updateProfit(id){
	document.getElementById("profit"+id).innerHTML = discountPercentage;
}

function updateSliderForMaximumRevenue(){

}

function updateSliderForMaximumProfit(){
	
}