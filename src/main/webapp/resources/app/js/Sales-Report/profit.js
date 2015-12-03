var profitData;

$(document).ready(function () {
	fetchStoreList("store-type-filter-profit");
	fetchSportList("sport-type-filter-profit");
	getProfitByDays();
	$("#r2c1").click(function(){
		console.info("r2c1");
		$("#total-profit-analysis-modal").modal('show');
		setTimeout(function(e){
			//getProfitByDays();
		},200);
	});
	
	$("#r2c2").click(function(){
		console.info("r2c2");
		$("#storewise-profit-analysis-modal").modal('show');
		setTimeout(function(e){
			drawPieChart("#profitsportWiseChart");
		},200);
	});
	
	
	$("#r2c3").click(function(){
		//console.info("r1c1");
		$("#sportwise-profit-analysis-modal").modal('show');
		setTimeout(function(e){
			drawPieChart("#profitstoreWiseChart");
		},200);
	});
	

});


var getProfitByDays = function(){
	var d = new Object();
	d.fromDate = "2015-11-15";
	d.toDate = "2015-12-03";
	
	$.ajax({
		url : 'getProfitByDays',
		data : JSON.stringify(d),
		type : 'POST',
		contentType : "application/json",
		success : function(data) {
			profitData = data;
			console.info(data);
			populateTotalProfitTable(data.profitEntities);
			drawLineGraph(data.profitEntities);
		},
		
		error : function(e) {
			alert ("sorry! Due to some problem couldn't update the gift-card details");
		},
	}).done(function() {
		console.log("Done adding gift-card details");
	});
	
}	

var populateTotalProfitTable = function(profitEntities){
	$('#daybydays-profit-table').DataTable({
    	data: profitEntities,
    	columns: [
					{ data: 'date' },
		          	{ data: 'profit' },
		          	{data : 'percentage'}
		      	],
        filter: false,
        sort: false,
        paging: true
  });
}

var drawLineGraph = function(re){
	var labels = [], data = [];
	
	for(var i=0; i<re.length; i++){
		labels.push(re[i].date);
		data.push(re[i].profit);
	}
	console.info(labels);
	console.info(data);
	var lineChartData = getLineChartData(labels,data);
	var lineChartCanvas = $("#totalprofitlineChart").get(0).getContext("2d");
	var lineChart = new Chart(lineChartCanvas);
	var options  = lineChartOptions;
	options.datasetFill = false;
	lineChart.Line(lineChartData, options);
	
}

var drawLineChart = function(id){

	var lineChartCanvas = $(id).get(0).getContext("2d");
    var lineChart = new Chart(lineChartCanvas);
    var options  = lineChartOptions;
    options.datasetFill = false;
    lineChart.Line(lineChartData, options);
}

var lineChartData = {

       labels: ["January", "February", "March", "April", "May", "June", "July","January", "February", "March", "April", "May", "June", "July"],
       datasets: [
         {
           label: "Electronics",
           fillColor: "rgba(210, 214, 222, 1)",
           strokeColor: "rgba(210, 214, 222, 1)",
           pointColor: "rgba(210, 214, 222, 1)",
           pointStrokeColor: "#c1c7d1",
           pointHighlightFill: "#fff",
           pointHighlightStroke: "rgba(220,220,220,1)",
           data: [65, 59, 80, 81, 56, 55, 40, 65, 59, 80, 81, 56, 55, 40]
         },
         {
           label: "Digital Goods",
           fillColor: "rgba(60,141,188,0.9)",
           strokeColor: "rgba(60,141,188,0.8)",
           pointColor: "#3b8bba",
           pointStrokeColor: "rgba(60,141,188,1)",
           pointHighlightFill: "#fff",
           pointHighlightStroke: "rgba(60,141,188,1)",
           data: [null,null,40, 19, 86, 27, 90,40, 19, 86, 27, 90, 27, 90]
         }
       ]
};

var drawPieChart = function(id){
	var pieChartCanvas = $(id).get(0).getContext("2d");
    var pieChart = new Chart(pieChartCanvas);
    var options  = pieOptions;
    options.datasetFill = false;
    pieChart.Pie(PieData, options);
}

var PieData = [
               {
                 value: 700,
                 color: "#f56954",
                 highlight: "#f56954",
                 label: "Chrome"
               },
               {
                 value: 500,
                 color: "#00a65a",
                 highlight: "#00a65a",
                 label: "IE"
               },
               {
                 value: 400,
                 color: "#f39c12",
                 highlight: "#f39c12",
                 label: "FireFox"
               },
               {
                 value: 600,
                 color: "#00c0ef",
                 highlight: "#00c0ef",
                 label: "Safari"
               },
               {
                 value: 300,
                 color: "#3c8dbc",
                 highlight: "#3c8dbc",
                 label: "Opera"
               },
               {
                 value: 100,
                 color: "#d2d6de",
                 highlight: "#d2d6de",
                 label: "Navigator"
               }
         ];
