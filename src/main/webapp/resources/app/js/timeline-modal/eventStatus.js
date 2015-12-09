

var  getEventStatusDiv = function(id){
	
	var divId = '"eventStatusDiv' + id+'" '
	
	var html = '<div class = "col-md-12 contactManager" id = '+divId+' style = "display:none;">'
      + '<div class="col-md-8">'
      +'<div class="box box-success">' 
       +  getEventStatusHeader(id)
       + '</div><!-- /.box -->' 
      + '</div><!-- /.col -->'
    +'</div>' ;
	
	return html;
	
}

function getEventStatusHeader(id){
	
  id = "'"+ id +"'";
  var html = '<div class="box-header with-border">'
    +'<h3 class="box-title">Number of customer registered is 100</h3>'    
 +'</div><!-- /.box-header -->'  ;
  
  return html;	
}



