
var  getEventStatusDiv = function(id){
	
	console.info("At eventAnalysis");
	var divId = '"eventStatusDiv' + id+'" '
	
	var html = '<div class = "col-md-12" id = '+divId+' style = "display:none;">'
      + '<div class="col-md-4">'
      +'<div class="box box-success">' 
       +  getEventStatusHeader(id)
       +  getEventStatusBody(id)
       + '</div><!-- /.box -->' 
      + '</div><!-- /.col -->'
    +'</div>' ;
	
	return html;
	
}

function getEventStatusHeader(id){

  var eventStatusHeaderId = '"eventStatusHeaderId' + id + '"' ;
  var html = '<div class="box-header with-border">'
    +'<h3 class="box-title" id = '+ eventStatusHeaderId +' >Event Not started</h3>'    
 +'</div><!-- /.box-header -->'  ;
  
  return html;	
}

function getEventStatusBody(id){
	var regCustCountId = '"regCustCountId' + id + '"' ;
	var html = '<div class="box-body">'
    +'<ul class="nav nav-pills nav-stacked">'
     +'<li><a><b>No. of customer registered</b> &nbsp;<p class="pull-right" id = '+ regCustCountId +'>0</p></a></li>'   
      +'</ul>'
    +'</div>';

    return html;
}


