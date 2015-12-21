
var  getEventAnalysisDiv = function(id){
	
	console.info("At eventAnalysis");
	var divId = '"eventAnalysisDiv' + id+'" '
	
	var html = '<div class = "col-md-12" id = '+divId+' style = "display:none;">'
	  + getDiscountAnalysisDiv()
	  + getRegistrationAnalysisDiv()
	  + getProfitAnalysisDiv()
	  + getRevenueAnalysisDiv()
    +'</div>' ;
	
	return html;
	
}

function getEventAnalysisHeader(id){
	
  id = "'"+ id +"'";
  var html = '<div class="box-header with-border">'
    +'<h3 class="box-title">After Event Analysis</h3>'    
 +'</div><!-- /.box-header -->'  ;
  
  return html;	
}



function getDiscountAnalysisDiv(id){
	
	var html =  '<div class="col-md-3">'
    +'<div class="box box-success">' 
     +  '<div class="box-header with-border">'
     +'<h3 class="box-title">Coupons Scheme Analysis</h3>'    
     +'</div><!-- /.box-header -->' 
     +  getDiscountAnalysisBody(id)
     + '</div><!-- /.box -->' 
    + '</div><!-- /.col -->' ;
	
	return html;
	
}

function getDiscountAnalysisBody(id){
	
	var leadGeneratedId = '"leadGeneratedId' + id + '"' ;
	var leadConvertedId = '"leadConvertedId' + id + '"' ;
	var inputCostId = '"inputCostId' + id + '"' ;
	var revenueId = '"revenueId' + id + '"' ;
	var profitId = '"profitId' + id + '"' ;
	
	var html = '<div class="box-body">'
    +'<ul class="nav nav-pills nav-stacked">'
     +'<li><a><b>Coupon Distributed</b> &nbsp;<p  class="pull-right">500</p></a></li>'   
      +'<li><a href="#"><b>Expected usuage</b> &nbsp;<p  class="pull-right">125</p></a></li>'  
        +'<li><a href="#"><b>Coupons redeemed</b> &nbsp; <p class="pull-right">180</p></a></li>'
      +'</ul>'
      +'</hr>'
      + '<p class = "label label-success">HighlyEffective</p>'
    +'</div>'

    return html;
}

function getRegistrationAnalysisDiv(id){
	
	var html =  '<div class="col-md-3">'
		+'<div class="box box-success">' 
	     +  '<div class="box-header with-border">'
	     +'<h3 class="box-title">Registration Scheme Analysis</h3>'    
	     +'</div><!-- /.box-header -->'  
	     +  getRegistrationAnalysisBody(id)
	     + '</div><!-- /.box -->' 
	    + '</div><!-- /.col -->' ;
		
		return html;

	
}

function getRegistrationAnalysisBody(id){
	
	var leadGeneratedId = '"leadGeneratedId' + id + '"' ;
	var leadConvertedId = '"leadConvertedId' + id + '"' ;
	var inputCostId = '"inputCostId' + id + '"' ;
	var revenueId = '"revenueId' + id + '"' ;
	var profitId = '"profitId' + id + '"' ;
	
	var html = '<div class="box-body">'
	    +'<ul class="nav nav-pills nav-stacked">'
	     +'<li><a><b>Lead generated</b> &nbsp;<p  class="pull-right">150</p></a></li>'   
	      +'<li><a href="#"><b>Expected conversion</b> &nbsp;<p  class="pull-right">60</p></a></li>'  
	        +'<li><a href="#"><b>Lead converted</b> &nbsp; <p class="pull-right">70</p></a></li>'
	      +'</ul>'
	      +'</hr>'
	      + '<p class = "label label-success">HighlyEffective</p>'
	    +'</div>'

	    return html;
}

function getProfitAnalysisDiv(id){
	
	var html =  '<div class="col-md-3">'
		+'<div class="box box-success">' 
	     +  '<div class="box-header with-border">'
	     +'<h3 class="box-title">Profit Analysis</h3>'    
	     +'</div><!-- /.box-header -->'  
	     +  getProfitAnalysisBody(id)
	     + '</div><!-- /.box -->' 
	    + '</div><!-- /.col -->' ;
		
		return html;
	
}


function getProfitAnalysisBody(id){
	
	var leadGeneratedId = '"leadGeneratedId' + id + '"' ;
	var leadConvertedId = '"leadConvertedId' + id + '"' ;
	var inputCostId = '"inputCostId' + id + '"' ;
	var revenueId = '"revenueId' + id + '"' ;
	var profitId = '"profitId' + id + '"' ;
	
	var html = '<div class="box-body">'
    +'<ul class="nav nav-pills nav-stacked">'
     +'<li><a><b>Expected Profit</b> &nbsp;<p class="pull-right">Rs. 35,000</p></a></li>'   
      +'<li><a href="#"><b>Profit generated</b> &nbsp;<p class="pull-right">Rs. 28,753</p></a></li>'  
      +'</ul>'
      +'</hr>'
      + '<p class = "label label-danger">In-effective</p>'
    +'</div>'

    return html;
}


function getRevenueAnalysisDiv(id){
	
	var html =  '<div class="col-md-3">'
		+'<div class="box box-success">' 
	     +  '<div class="box-header with-border">'
	     +'<h3 class="box-title">Revenue Analysis</h3>'    
	     +'</div><!-- /.box-header -->' 
	     +  getRevenueAnalysisBody(id)
	     + '</div><!-- /.box -->' 
	    + '</div><!-- /.col -->' ;
		
		return html;	
}

function getRevenueAnalysisBody(id){
	
	var leadGeneratedId = '"leadGeneratedId' + id + '"' ;
	var leadConvertedId = '"leadConvertedId' + id + '"' ;
	var inputCostId = '"inputCostId' + id + '"' ;
	var revenueId = '"revenueId' + id + '"' ;
	var profitId = '"profitId' + id + '"' ;
	
	var html = '<div class="box-body">'
    +'<ul class="nav nav-pills nav-stacked">'
     +'<li><a><b>Expected Revenue</b> &nbsp;<p id = '+ leadGeneratedId +' class="pull-right"><small>Rs. 286,560</small></p></a></li>'   
      +'<li><a><b>Revenue generated</b> &nbsp;<p id = '+ leadConvertedId +' class="pull-right"><small>Rs. 348,560</small></p></a></li>'  
      +'</ul>'
      +'</hr>'
      + '<p class = "label label-success">Highly-Effective</p>'
    +'</div>'

    return html;
}





