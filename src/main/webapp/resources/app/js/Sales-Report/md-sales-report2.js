

$(document).ready(function () {
	
	
	createRangeCalender('#reportrange1');
	createRangeCalender('#reportrange2');
	createRangeCalender('#reportrange4');

		
});


var createRangeCalender =  function(id) {
	
	console.info("yes");
	
    function cb(start, end) {
        $(id+ ' span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
    }
    cb(moment().subtract(29, 'days'), moment());

    $(id).daterangepicker({
        ranges: {
           'Today': [moment(), moment()],
           'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
           'Last 7 Days': [moment().subtract(6, 'days'), moment()],
           'Last 30 Days': [moment().subtract(29, 'days'), moment()],
           'This Month': [moment().startOf('month'), moment().endOf('month')],
           'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
        }
    }, cb);

}


var fetchSportList = function(elementId){
	
	console.info(elementId);
	var select = document.getElementById(elementId);
	$.ajax({
		url : 'fetchSportList',
		type : 'GET',
		contentType : "application/json",
		success : function(data) {
			sportData = data;
			console.info(data);
			for (var i in data) {
				var opt = document.createElement('option');
				opt.value = data[i].sportId;
				opt.innerHTML = data[i].name;
				select.appendChild(opt);				
			}
			
		},
	}).done(function() {
				
	});
}


var fetchStoreList = function(elementId){
	console.info(elementId);
	var select = document.getElementById(elementId);
	$.ajax({
		url : 'fetchStoreList',
		type : 'GET',
		contentType : "application/json",
		success : function(data) {
			sportData = data;
			console.info(data);
			for (var i in data) {
				var opt = document.createElement('option');
				opt.value = data[i].id;
				opt.innerHTML = data[i].name;
				select.appendChild(opt);				
			}
			
		},
	}).done(function() {
				
	});
}	
	
var getLineChartData = function(labels,data)
{
	var lineChartData = {
	       labels: labels,
	       datasets: [
	         {
	           label: "Revenue",
	           fillColor: "rgba(210, 214, 222, 1)",
	           strokeColor: "rgba(210, 214, 222, 1)",
	           pointColor: "rgba(210, 214, 222, 1)",
	           pointStrokeColor: "#c1c7d1",
	           pointHighlightFill: "#fff",
	           pointHighlightStroke: "rgba(220,220,220,1)",
	           data: data
	         }
	       ]
	}
	return lineChartData;
};
	
	