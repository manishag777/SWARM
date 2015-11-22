

$(document).ready(function () {
	$("#r1c1").click(function(){
		console.info("r1c1");
		$("#report-modal").modal('show');
	});
	
	createRangeCalender('#reportrange1');
	createRangeCalender('#reportrange2');
	createRangeCalender('#reportrange3');
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


	
	
	
	
	