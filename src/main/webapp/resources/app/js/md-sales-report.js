var selected1 = 0;
var selected2 = 0;

$(document).ready(function () {
	
	createRangeCalender('#earning-reportrange');
	createRangeCalender('#revenue-reportrange');
	$('#revenue-commonCard').css("background-color", '#ccffcc');
	$('#earning-commonCard').css("background-color", '#ccffcc');
	$('#revenue-storeCard').css("background-color", '#ccffcc');
	$('#earning-storeCard').css("background-color", '#ccffcc');
	drawLineChart('earning-line-chart');
	drawdonutChart('earning-donut-chart');
	drawLineChart('revenue-line-chart');
	drawdonutChart('revenue-donut-chart');
	$('input[name=abc][value=1]').click();
	
	$('input[name=abc]').on('click',function(){
		alert('yes');
		console.log($(this).val());
	});
	
	$('#revenue-storeCard').click( function(){
		clearAllColor1();
		this.style.backgroundColor = '#ccffcc';
	
	});
	$('#revenue-sportCard').click( function(){
		clearAllColor1();
		this.style.backgroundColor = '#ccffcc';
	});
	
	$('#earning-storeCard').click( function(){
		clearAllColor2();
		this.style.backgroundColor = '#ccffcc';
	
	});
	$('#earning-sportCard').click( function(){
		clearAllColor2();
		this.style.backgroundColor = '#ccffcc';
	});
	
	
	
});

var radioClicked = function(){
	console.info("hi");
	alert('yes');
}

var clearAllColor1 = function(){
	//$('#storeCard').style.backgroundColor = '#F8F8F8';
	//$('#sportCard').style.backgroundColor = '#F8F8F8';
	$('#revenue-storeCard').css("background-color", "#F8F8F8");
	$('#revenue-sportCard').css("background-color", "#F8F8F8");
	
}

var clearAllColor2 = function(){
	//$('#storeCard').style.backgroundColor = '#F8F8F8';
	//$('#sportCard').style.backgroundColor = '#F8F8F8';
	$('#earning-storeCard').css("background-color", "#F8F8F8");
	$('#earning-sportCard').css("background-color", "#F8F8F8");
	
}



var createRangeCalender =  function(id) {
	
	
	
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

var drawLineChart = function(id){
	var line = new Morris.Line({
		  element: id,
		  resize: true,
		  data: [
		    {y: '2011 Q1', item1: 2666, item2: 2000},
		    {y: '2011 Q2', item1: 2778, item2: 3000},
		    {y: '2011 Q3', item1: 4912, item2: 4912},
		    {y: '2011 Q4', item1: 3767, item2: 3767},
		    {y: '2012 Q1', item1: 6810, item2: 6810},
		    {y: '2012 Q2', item1: 5670, item2: 5670},
		    {y: '2012 Q3', item1: 4820, item2: 4820},
		    {y: '2012 Q4', item1: 15073, item2: 15073},
		    {y: '2013 Q1', item1: 10687, item2: 10687},
		    {y: '2013 Q2', item1: 8432, item2: 8432}
		  ],
		  xkey: 'y',
		  ykeys: ['item1','item2'],
		  labels: ['Item 1', 'item2'],
		  lineColors: ['#3c8dbc', 'ff00ff'],
		  hideHover: 'auto'
		});
}

// LINE CHART

var drawdonutChart = function(id){
	
	var donut = new Morris.Donut({
        element: id,
        resize: true,
        colors: ["#3c8dbc", "#f56954", "#00a65a"],
        data: [
          {label: "Download Sales", value: 12},
          {label: "In-Store Sales", value: 30},
          {label: "Mail-Order Sales", value: 20}
        ],
        hideHover: 'auto'
      });

}


	
	
	
	
	
	