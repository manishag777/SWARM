

var  getEventAnalysisDiv = function(id){
	
	console.info("At eventAnalysis");
	var divId = '"eventAnalysisDiv' + id+'" '
	
	var html = '<div class = "col-md-12" id = '+divId+' style = "display:none;">'
      + '<div class="col-md-4">'
      +'<div class="box box-success">' 
       +  getEventAnalysisHeader(id)
       +  getEventAnalysisBody(id)
       + '</div><!-- /.box -->' 
      + '</div><!-- /.col -->'
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

function getEventAnalysisBody(id){
	
	var leadGeneratedId = '"leadGeneratedId' + id + '"' ;
	var leadConvertedId = '"leadConvertedId' + id + '"' ;
	var inputCostId = '"inputCostId' + id + '"' ;
	var revenueId = '"revenueId' + id + '"' ;
	var profitId = '"profitId' + id + '"' ;
	
	var html = '<div class="box-body">'
    +'<ul class="nav nav-pills nav-stacked">'
     +'<li><a><b>Lead generated</b> &nbsp;<p id = '+ leadGeneratedId +' class="pull-right">830</p></a></li>'   
      +'<li><a href="#"><b>Lead Converted</b> &nbsp;<p id = '+ leadConvertedId +' class="pull-right">140</p></a></li>'  
        +'<li><a href="#"><b>Total Input Cost</b> &nbsp; <p id = '+ inputCostId +' class="pull-right">Rs. 10,300</p></a></li>'
        +'<li><a href="#"><b>Revenue &nbsp;</b> <p id = '+ revenueId +' class="pull-right">Rs. 170,000</p></a></li>'
        +'<li><a href="#"><b>Profit &nbsp;</b><p id = '+ profitId +' class="pull-right">Rs. 35,000</p></a></li>'
      +'</ul>'
    +'</div>'

    return html;
}
