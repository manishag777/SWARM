var expectedCouponCount = new Object();


function intializeCoupon(id){
	expectedCouponCount[id] = ($('#participationCount'+id).val()*0.1).toFixed(0);
 	couponSliderEvent(id);
 	document.getElementById("expCC"+id).innerHTML = expectedCouponCount[id];

 	$('#budget'+id).keyup(function(e){
		    var val = $(this).val();
			console.info(val);
			updateCouponSlider(val, id, 25);
	});

}

var couponSliderEvent = function(id){
	console.info("couponSlideEventCalled");
	$("#couponSlider"+id).slider();
	$("#couponSlider"+id).slider().on('slide', function(){
 		var value = $(this).slider('getValue');
 		setCouponCount(value,id);
 	});
}

var updateCouponSlider = function(val, id){
	
	var couponVal = (val/expectedCouponCount[id]).toFixed(0);
	console.info("couponVal = "+ couponVal);
	couponVal = (((couponVal)/50).toFixed(0)) *(50) ;
	$("#couponSlider"+id).slider('setValue', couponVal)
	var couponCount = (val/couponVal).toFixed(0);
	document.getElementById("cAmt"+id).innerHTML = couponVal;
	document.getElementById("couponCount"+id).innerHTML = couponCount;
	
}

var setCouponCount = function(val,id){
	var couponCount = ($('#budget'+id).val()/val).toFixed(0);
	document.getElementById("cAmt"+id).innerHTML = val;
	document.getElementById("couponCount"+id).innerHTML = couponCount;
}


