var custId = 0;


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


