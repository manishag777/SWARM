var custId = 0;
var total = 0;
var productDetailArray = [];
var orderDto = new Object();
var gmc_cust = ['#ff245b', '#2663ff', '#26e6ff', '#26ff47', '#ff7226'];


var switchActiveTab = function(id) {
	$.each($('#book-store-navbar').children(), function(i, el) {
		if (el.id === id) {
			$(el).addClass('active');
		} else {
			$(el).removeClass('active');
		}
	});
};

var BookStore = {};

jQuery.fn.serializeObject = function() {
	var arrayData, objectData;
	arrayData = this.serializeArray();
	//console.info(arrayData);
	objectData = {};

	$.each(arrayData, function() {
		var value;

		if (this.value != null) {
			value = this.value;
		} else {
			value = '';
		}

		if (objectData[this.name] != null) {
			if (!objectData[this.name].push) {
				objectData[this.name] = [ objectData[this.name] ];
			}

			objectData[this.name].push(value);
		} else {
			//if(this.na)
			objectData[this.name] = value;
		}
	});

	return objectData;
};

function isRealValue(obj){
	 return obj && obj !== "null" && obj!== "undefined";
}

function validateEmail(email) {
    var re = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
    return re.test(email);
}

var createOption = function(value, name){
	var opt = document.createElement('option');
	opt.value = value;
	opt.innerHTML = name;
	return opt;
}

var getTodayDate = function(){
	var q = new Date();
	var mm = q.getMonth()+1;
	var dd = q.getDay();
	var yyyy = q.getFullYear();
	
	
	if(dd<10) {
	    dd='0'+dd
	} 

	if(mm<10) {
	    mm='0'+mm
	} 
	
	today = yyyy+'-'+mm+'-'+dd;
	console.info(today);
	today = "2015-10-31";
	var date = new Date(today);
	return date;
}

var getStandardDate = function(x){
	var a = x.split('-');
	var date = new Date (a[0], a[1] - 1,a[2]);//using a[1]-1 since Date object has month from 0-11
	return date;
}

var getFormattedDate = function(date){
	
	var monthNames = [
	                  "Jan", "Feb", "Mar",
	                  "Apr", "May", "Jun", "Jul",
	                  "Aug", "Sep", "Oct",
	                  "Nov", "Dec"
	                ];

    
    var day = date.getDate();
    var monthIndex = date.getMonth();
    var year = date.getFullYear();
    console.log(day, monthNames[monthIndex], year);
    
    return day+"-"+monthNames[monthIndex]+"-"+year ;
    
}

