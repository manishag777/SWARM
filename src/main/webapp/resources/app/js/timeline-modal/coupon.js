var expectedCouponCount = new Object();
var remainingBudgetIdMap = new Object();



function intializeCoupon(id){
	expectedCouponCount[id] = ($('#participationCount'+id).val()*0.06/0.25).toFixed(0);
 	couponSliderEvent(id);
 	document.getElementById("expCC"+id).innerHTML = expectedCouponCount[id];
 	

 	$('#couponCountDetail'+id).click(function(e){
	   	//console.info(val);
 		$("#event-coupon-detail").modal("show");
 		populateCouponTable();
 	});
 	
 	$('#registratioDetail'+id).click(function(e){
	   	//console.info(val);
 		$("#event-registration-detail").modal("show");
 		populateRegistrationTable();
 	});
 	
}

var updateCouponScheme = function(id, remainingBudget){
	
	//var remainingBudget = 20000;
	remainingBudgetIdMap[id] = 4*remainingBudget;
 	document.getElementById('remainingBudgetCoupon'+id).innerHTML = "Rs."+remainingBudget;
 	document.getElementById('couponDistributionAmount'+id).innerHTML = "Rs."+remainingBudget*4;
 	updateCouponSlider(4*remainingBudget, id);
}



var populateCouponTable = function(){
	    $('#coupon-detail').DataTable( {
	        data: couponDetailData,
	        columns: [
	            { title: "Event Name" },
	            { title: "Participant Count" },
	            { title: "Participant visited(%)" },
	            { title: "Coupon Used(%)" },
	        ],
	        filter: false,
	        sort: false,
	        paging: false,
	        bInfo : false,
	        destroy: true	
	    } );
}

var populateRegistrationTable = function(){
    $('#registration-detail').DataTable( {
        data: registrationDetailData,
        columns: [
            { title: "Event Name" },
            { title: "Participant Count" },
            { title: "Participant visited(%)" },
            { title: "Registration Successful(%)" },
        ],
        filter: false,
        sort: false,
        paging: false,
        bInfo : false,
        destroy: true	
    } );
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
	
	expectedCouponCount[id] = ($('#participationCount'+id).val()*0.06/0.25).toFixed(0);
 	document.getElementById("expCC"+id).innerHTML = expectedCouponCount[id];

	var couponVal = (val/expectedCouponCount[id]).toFixed(0);
	console.info("val = "+ val+" couponVal = "+ couponVal);
	couponVal = (((couponVal)/10).toFixed(0)) *(10) ;
	$("#couponSlider"+id).slider('setValue', couponVal)
	var couponCount = (val/couponVal).toFixed(0);
	document.getElementById("cAmt"+id).innerHTML = couponVal;
	document.getElementById("couponCount"+id).innerHTML = couponCount;
	
}


var setCouponCount = function(val,id){
	var couponCount = (remainingBudgetIdMap[id]/val).toFixed(0);
	document.getElementById("cAmt"+id).innerHTML = val;
	document.getElementById("couponCount"+id).innerHTML = couponCount;
}

function shareCouponOnFacebook(){
	swal("Coupon scheme shared on the facebook page","","success");
}

function shareRegistrationOnFacebook(){
	swal("Registration scheme shared on the facebook page","","success");
}


function shareDiscountOnFacebook(){
	
	swal("Discount scheme shared on the facebook page","","success");	
}

function generateAndSaveCoupon(){
	swal("Coupons generated and saved!","","success");
}

function saveRegistrationDetails(){
	swal("Registration details saved!","","success");
}


