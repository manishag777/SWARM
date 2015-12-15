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

var getFormattedDate2 = function(date){   // month/day/year
	
	

    
    var day = date.getDate();
    var monthIndex = date.getMonth()+1;
    var year = date.getFullYear();
    
    return monthIndex+"/"+day+"/"+year;
    
}

var lineChartOptions = {
        //Boolean - If we should show the scale at all
        showScale: true,
        //Boolean - Whether grid lines are shown across the chart
        scaleShowGridLines: false,
        //String - Colour of the grid lines
        scaleGridLineColor: "rgba(0,0,0,.05)",
        //Number - Width of the grid lines
        scaleGridLineWidth: 1,
        //Boolean - Whether to show horizontal lines (except X axis)
        scaleShowHorizontalLines: true,
        //Boolean - Whether to show vertical lines (except Y axis)
        scaleShowVerticalLines: true,
        //Boolean - Whether the line is curved between points
        bezierCurve: true,
        //Number - Tension of the bezier curve between points
        bezierCurveTension: 0.3,
        //Boolean - Whether to show a dot for each point
        pointDot: false,
        //Number - Radius of each point dot in pixels
        pointDotRadius: 4,
        //Number - Pixel width of point dot stroke
        pointDotStrokeWidth: 1,
        //Number - amount extra to add to the radius to cater for hit detection outside the drawn point
        pointHitDetectionRadius: 20,
        //Boolean - Whether to show a stroke for datasets
        datasetStroke: true,
        //Number - Pixel width of dataset stroke
        datasetStrokeWidth: 2,
        //Boolean - Whether to fill the dataset with a color
        datasetFill: true,
        //String - A legend template
        legendTemplate: "<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<datasets.length; i++){%><li><span style=\"background-color:<%=datasets[i].lineColor%>\"></span><%if(datasets[i].label){%><%=datasets[i].label%><%}%></li><%}%></ul>",
        //Boolean - whether to maintain the starting aspect ratio or not when responsive, if set to false, will take up entire container
        maintainAspectRatio: true,
        //Boolean - whether to make the chart responsive to window resizing
        responsive: true
      };

var pieOptions = {
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

