var newCustomerEntities;
var oldCustomerEntities;
var totalCustomerEntities;
var cfromDate;
var ctoDate;

$(document).ready(function () {
	fetchStoreList("store-type-filter-revenue");
	fetchSportList("sport-type-filter-revenue");
	createCustomerRangeCalender('#reportrange3');
	$("#r3c1").click(function(){
		//console.info("r1c1");
		$("#total-customer-analysis-modal").modal('show');
		getCustomerCount();
		setTimeout(function(e){
			drawPieChart("#totalCustomerPieChart",totalCustomerEntities);
			
		},200);
	});
	
	$("#r3c3").click(function(){
		//console.info("r1c1");
		$("#new-customer-analysis-modal").modal('show');
		setTimeout(function(e){
		//	drawPieChart("#newCustomerPieChart");
			drawPieChart("#newCustomerPieChart",newCustomerEntities);
		},200);
	});
	
	
	$("#r3c2").click(function(){
		//console.info("r1c1");
		$("#existing-customer-analysis-modal").modal('show');
		setTimeout(function(e){
			//drawPieChart("#existingCustomerPieChart");
			drawPieChart("#existingCustomerPieChart",oldCustomerEntities);

		},200);
	});
});




var color = ["#f56954", "#00a65a", "#f39c12", "#00c0ef", "#3c8dbc", "#d2d6de"];
var highlight = ["#f56954", "#00a65a", "#f39c12", "#f39c12", "#3c8dbc", "#d2d6de"];

var getPieData = function(data){
	
	var pieData = [];
	for(var i=0; i<data.length; i++){
		var elem = new Object();
		elem.value = data[i].count;
		elem.color = color[i];
		elem.highlight = highlight[i];
		elem.label = data[i].storeId;
		pieData.push(elem);
	}
	return pieData;
	
}


var getCustomerCount = function(){
	
	var d = new Object();
	d.fromDate = cfromDate;
	d.toDate = ctoDate;
	
	$.ajax({
		url : 'getCostumerCount',
		data : JSON.stringify(d),
		type : 'POST',
		contentType : "application/json",
		success : function(data) {
			console.info(data);
			displayCustomerData(data);
		},
		
		error : function(e) {
			alert ("sorry! Due to some problem couldn't update the gift-card details");
		},
	}).done(function() {
		console.log("Done adding gift-card details");
	});
	
}

var displayCustomerData = function(data){
	
	var totalEntity = data.totalCustomerEntities;
	var newEntity = data.newCustomerEntities;
	var oldEntity = data.exisitingCustomerEntities;
	populateCustomerTable("#total-customer-table",totalEntity);
	populateCustomerTable("#new-customer-table",newEntity);
	populateCustomerTable("#exisiting-customer-table",oldEntity);
	var total = updateTotalCount("total_customer",totalEntity);
	var exs = updateTotalCount("exisiting_customer",oldEntity);
	var newCount = total - exs ;
	document.getElementById("new_customer").innerHTML = newCount;
	 newCustomerEntities =  data.newCustomerEntities ;
	 oldCustomerEntities =  data.exisitingCustomerEntities;
	 totalCustomerEntities = data.totalCustomerEntities;
	

}


var populateCustomerTable = function(id,enitites){
	
	$(id).DataTable({
    	data: enitites,
    	columns: [
					{ data: 'storeId' },
		          	{ data: 'count' },
		      	],
        filter: false,
        sort: false,
        paging: true,
        "bDestroy" : true
  });
}

function updateTotalCount(id, entity){
	var total = 0;
	for(var i=0; i<entity.length; i++){
		total += entity[i].count;
	}
	document.getElementById(id).innerHTML = total;
	return total;
}

var drawPieChart = function(id,data){
	console.info("At drawPieChart"+id);
	var pieChartCanvas = $(id).get(0).getContext("2d");
    var pieChart = new Chart(pieChartCanvas);
    var options  = pieOptions;
    options.datasetFill = false;
    pieChart.Pie(getPieData(data), options);
}

var createCustomerRangeCalender =  function(id) {
	
	console.info("yes");
	
    function cb(start, end) {
        $(id+ ' span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
        cfromDate = start.format('YYYY-MM-DD');
        ctoDate = end.format('YYYY-MM-DD');
        getCustomerCount();
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



