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
	console.info(arrayData);
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

