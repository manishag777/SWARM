
$(document).ready(function () {
	
	var canvas = '<canvas id="totalrevenuelineChart" style="height:300px; margin:10px;"></canvas>';
	var pieChartCanvas = '<canvas id="pieChart" height="200px"></canvas>' ;
	drawLineChart("#totalrevenuelineChart", revenueLineChartData);
    drawPieChart("#pieChart", profitPieChartData());	
	
	$("#revenueTab").click(function(e){
		$("#revenue-box").addClass("active");
		setTimeout(function(e){
			document.getElementById("revenue-box").innerHTML = "";
			document.getElementById("revenue-box").innerHTML = canvas;
			drawLineChart("#totalrevenuelineChart", revenueLineChartData);			
		},200);

	});
	
	$("#profitTab").click(function(e){
		//console.info("profitTab");
		$("#revenue-box").addClass("active");
		//$("#totalprofitlineChart").css("display", "block");
		//$("#totalrevenuelineChart").css("display", "none");
		document.getElementById("revenue-box").innerHTML = "";
		document.getElementById("revenue-box").innerHTML = canvas;
		setTimeout(function(e){
			drawLineChart("#totalrevenuelineChart", profitLineChartData);	
		},200);
	});
	
	$("#revenue-pie-chartTab").click(function(e){
		$("#pieChartBoxParent").addClass("active");
		document.getElementById("pieChartBox").innerHTML = "";
		document.getElementById("pieChartBox").innerHTML = pieChartCanvas;
		setTimeout(function(e){
			drawPieChart("#pieChart", revenuePieChartData);	
		},200);
	});
	
	$("#profit-pie-chartTab").click(function(e){
		$("#pieChartBoxParent").addClass("active");
		document.getElementById("pieChartBox").innerHTML = "";
		document.getElementById("pieChartBox").innerHTML = pieChartCanvas;
		setTimeout(function(e){
			drawPieChart("#pieChart", revenuePieChartData);	
		},200);
	});
	

	
});

var drawLineChart = function(id, data){

	var lineChartCanvas = $(id).get(0).getContext("2d");
	var lineChart = new Chart(lineChartCanvas);
	var options  = lineChartOptions;
	options.datasetFill = false;
	lineChart.Line(data, options);
	
}

var drawPieChart = function(id, data){
	console.info(id + " "+ data);
//	var pieChartCanvas = $(id).get(0).getContext("2d");
//	var pieChart = new Chart(pieChartCanvas);
//	var options = pieOptions;
//	options.datasetFill = false;
//	pieChart.Pie(data, pieOptions2);
//	
	var pieChartCanvas = $(id).get(0).getContext("2d");
    var pieChart = new Chart(pieChartCanvas);
    pieChart.Pie(data, pieOptions);
	
}

var revenueLineChartData = {

       labels: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul","Aug", "Sep", "Oct", "Nov", "Dec"],
       datasets: [
         {
           label: "2014",
           fillColor: "rgba(210, 214, 222, 1)",
           strokeColor: "rgba(210, 214, 222, 1)",
           pointColor: "rgba(210, 214, 222, 1)",
           pointStrokeColor: "#c1c7d1",
           pointHighlightFill: "#fff",
           pointHighlightStroke: "rgba(220,220,220,1)",
           data: [28.3, 30.3, 27.2, 20.9, 22.8, 19, 15.8, 16.2, 16, 16.8, 17.2, 24 ]
         },
         {
           label: "2015",
           fillColor: "rgba(60,141,188,0.9)",
           strokeColor: "rgba(60,141,188,0.8)",
           pointColor: "#3b8bba",
           pointStrokeColor: "rgba(60,141,188,1)",
           pointHighlightFill: "#fff",
           pointHighlightStroke: "rgba(60,141,188,1)",
           data: [32.4, 28.4, 24.4, 23.4, 24.4, 18, 15.6, 14.4, 15.2, 17.2, 18.9, 4.4]
         }
       ]
};

var profitLineChartData = {


	       labels: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul","Aug", "Sep", "Oct", "Nov", "Dec"],
	       datasets: [
	         {
	           label: "2014",
	           fillColor: "rgba(210, 214, 222, 1)",
	           strokeColor: "rgba(210, 214, 222, 1)",
	           pointColor: "rgba(210, 214, 222, 1)",
	           pointStrokeColor: "#c1c7d1",
	           pointHighlightFill: "#fff",
	           pointHighlightStroke: "rgba(220,220,220,1)",
	           data: [11.32, 12.6, 10.9, 8.6, 9.2, 8.1, 6.33, 5.2 , 7.2, 7.6, 8.1, 10.5]
	         },
	         {
	           label: "2015",
	           fillColor: "rgba(60,141,188,0.9)",
	           strokeColor: "rgba(60,141,188,0.8)",
	           pointColor: "#3b8bba",
	           pointStrokeColor: "rgba(60,141,188,1)",
	           pointHighlightFill: "#fff",
	           pointHighlightStroke: "rgba(60,141,188,1)",
	           data: [12.4, 13.1, 9.2, 8.8, 9.4, 7.9, 6.1, 5.0, 6.8, 7.2, 7.4, 1.2 ]
	         }
	       ]
	};

var revenuePieChartData  = [
		               {
		                 value: 5.4,
		                 color: "#f56954",
		                 highlight: "#f56954",
		                 label: "Athletics"
		               },
		               {
		                 value: 4.3,
		                 color: "#00a65a",
		                 highlight: "#00a65a",
		                 label: "Tennis"
		               },
		               {
		                 value: 13.4,
		                 color: "#f39c12",
		                 highlight: "#f39c12",
		                 label: "Cricket"
		               },
		               {
		                 value: 3.4,
		                 color: "#00c0ef",
		                 highlight: "#00c0ef",
		                 label: "Cycling"
		               },
		               {
		                 value: 4.8,
		                 color: "#3c8dbc",
		                 highlight: "#3c8dbc",
		                 label: "Football"
		               },
		               {
		                 value: 9.2,
		                 color: "#d2d6de",
		                 highlight: "#d2d6de",
		                 label: "Others"
		               }
		             ];



var profitPieChartData = function()
{
		var PieData = [
		               {
		                 value: 1.9,
		                 color: "#f56954",
		                 highlight: "#f56954",
		                 label: "Athletics"
		               },
		               {
		                 value: 1.72,
		                 color: "#00a65a",
		                 highlight: "#00a65a",
		                 label: "Tennis"
		               },
		               {
		                 value: 6.4,
		                 color: "#f39c12",
		                 highlight: "#f39c12",
		                 label: "Cricket"
		               },
		               {
		                 value: 1.19,
		                 color: "#00c0ef",
		                 highlight: "#00c0ef",
		                 label: "Cycling"
		               },
		               {
		                 value: 1.8,
		                 color: "#3c8dbc",
		                 highlight: "#3c8dbc",
		                 label: "Football"
		               },
		               {
		                 value: 2.79,
		                 color: "#d2d6de",
		                 highlight: "#d2d6de",
		                 label: "Others"
		               }
		             ];
		return PieData;
}


var pieOptions2 = {
	      //Boolean - Whether we should show a stroke on each segment
	      segmentShowStroke: true,
	      //String - The colour of each segment stroke
	      segmentStrokeColor: "#fff",
	      //Number - The width of each segment stroke
	      segmentStrokeWidth: 2,
	      //Number - The percentage of the chart that we cut out of the middle
	      percentageInnerCutout: 50, // This is 0 for Pie charts
	      //Number - Amount of animation steps
	      animationSteps: 100,
	      //String - Animation easing effect
	      animationEasing: "easeOutBounce",
	      //Boolean - Whether we animate the rotation of the Doughnut
	      animateRotate: true,
	      //Boolean - Whether we animate scaling the Doughnut from the centre
	      animateScale: false,
	      //Boolean - whether to make the chart responsive to window resizing
	      responsive: true,
	      // Boolean - whether to maintain the starting aspect ratio or not when responsive, if set to false, will take up entire container
	      maintainAspectRatio: true,
	      //String - A legend template
	      legendTemplate: "<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<segments.length; i++){%><li><span style=\"background-color:<%=segments[i].fillColor%>\"></span><%if(segments[i].label){%><%=segments[i].label%><%}%></li><%}%></ul>"
	    };

