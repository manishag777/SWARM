var idValueMap = new Object();
idValueMap[1] = 2;
idValueMap[2] = 1;
idValueMap[3] = 0;
idValueMap[4] = 2;

var  getEventAnalysisDiv = function(id){
	
	console.info("At eventAnalysis");
	var divId = '"eventAnalysisDiv' + id+'" '
	
	var html = '<div class = "col-md-12" id = '+divId+' style = "display:none;">'
	  + getDiscountAnalysisDiv()
	  + getRegistrationAnalysisDiv()
	  + getProfitAnalysisDiv()
	  + getRevenueAnalysisDiv()
	  + getOtherActionDiv(id)
	  + getMailActionDiv(id)
	  + createCommentDiv(id)
    +'</div>' ;
	
	return html;
	
}

var createCommentDiv = function(id){
	
	var mid = id + '"';
	var html = 	'<div class="col-md-7">'
					+'<div class="box box-default">'
				    +'<div class="box-header">'
					    +'<i class="fa  fa-user"></i>'
					    +'<h3 class="box-title">Comments</h3>'
					 +'</div><!-- /.box-header -->'
					  +'<div class="box-body">'
					    +'<ul class="todo-list" id = "comment-list'+mid+'>'
							+addListOfCommentItem()
					    +'</ul>'
					  +'</div>'
					  +'<div class="box-footer clearfix no-border">'
					    +'<button class="btn btn-default pull-right" id = "addCommentTask'+mid+'><i class="fa fa-plus"></i> Add comment</button>'
					  +'</div>'
					+'</div><!-- /.box -->'
				+'</div>';
	
		return html;
}

function addCommentUnit(){
	var item = $("#commentUnit").val();
	console.info(item);
	var html = getLiOfCommentItem(item);
	console.info("eventId"+ eventId);
	$( "#comment-list"+eventId);
	$( "#comment-list"+eventId).append(html);
	addDeleteLIListener();
}



var addListOfCommentItem = function(id){
	
	var task = ["Decrease less number of coupon to increase profit", "Increase purchase amount to increase profit"];
	
	var html="";
	
	for(var i=0; i<task.length; i++){
		html+=getLiOfCommentItem(task[i]);
	}

	return html;	
}

var getLiOfCommentItem = function(item){
	
	var html = '<li>'
      +'<span class="handle">'
        +'<i class="fa fa-comment"></i>'
      +'</span>'
      +'<span class="text">'+item+'</span>'
      +'<div class="tools">'
        +'<i class="fa fa-trash-o deleteLI" ></i>'
      +'</div>'
    +'</li>';
	
	return html;
	
}


function getEventAnalysisHeader(id){
	
  id = "'"+ id +"'";
  var html = '<div class="box-header with-border">'
    +'<h3 class="box-title">After Event Analysis</h3>'    
 +'</div><!-- /.box-header -->'  ;
  
  return html;	
}

function getCommentDiv(id){
	var html =  '<div class="col-md-7" >'
	    +'<div class="box box-default">' 
	     +  '<div class="box-header with-border">'
	     +'<h3 class="box-title">Add comment</h3>'    
	     +'</div><!-- /.box-header -->'
	     +	getMailActionBody(id)
	     + '</div><!-- /.box -->' 
	    + '</div><!-- /.col -->' ;
	
	return html;
}

function getMailActionDiv(id){
	var html =  '<div class="col-md-6" >'
	    +'<div class="box box-default">' 
	     +  '<div class="box-header with-border">'
	     +'<h3 class="box-title">Send mail to resgistered customers who have not visited</h3>'    
	     +'</div><!-- /.box-header -->'
	     +	getMailActionBody(id)
	     + '</div><!-- /.box -->' 
	     
	    + '</div><!-- /.col -->' ;
		
	//console.info(html);
		return html;
}

function getMailActionBody(id){
	
	var html = '<div class="box-body">'
	    			+'<Button class="btn btn-sm btn-info">Send Mail</Button>'
	    		+'</div>';
	return html;
}


function getOtherActionDiv(id){
	var html =  '<div class="col-md-6" >'
	    +'<div class="box box-default">' 
	     +  '<div class="box-header with-border">'
	     +'<h3 class="box-title">Event rating</h3>'    
	     +'</div><!-- /.box-header -->'
	     +	getOtherActionBody(id)
	     + '</div><!-- /.box -->' 
	     
	    + '</div><!-- /.col -->' ;
		
	console.info(html);
		return html;
}

function getOtherActionBody(id){
	
	var html = '<div class="box-body">'
	    		+ getRatingElements(id)
	    		+'</div>';
	return html;
}


function getRatingElements(id){
	var mid = id + '"';
	//var eventId = '"'+id+'"'
	var html = 	'<div class="stars">'
						+'<label style = "margin-right:15px">Rate this event:</label>'
						+'<input type="radio" name="star'+mid+' class="star-1" id="star-1" />'
						+'<label style = "margin-right:15px" class="star-1" for="star-1">1</label>'
						+'<input type="radio" name="star'+mid+' class="star-2" id="star-2" />'
						+'<label style = "margin-right:15px" class="star-2" for="star-2">2</label>'
						+'<input type="radio" name="star'+mid+' class="star-3" id="star-3" checked />'
						+'<label style = "margin-right:15px" class="star-3" for="star-3">3</label>'
						+'<input type="radio" name="star'+mid+' class="star-4" id="star-4" />'
						+'<label style = "margin-right:15px" class="star-4" for="star-4">4</label>'
						+'<input type="radio" name="star'+mid+' class="star-5" id="star-5" />'
						+'<label style = "margin-right:15px" class="star-5" for="star-5">5</label>'
						+'<Button style = "margin-right:25px" onClick = "updateOverAllRating()" class = "btn btn-info">Use System Rating</button>'
				+'</div>';
	console.info(html);
	return html;

}

function getDiscountAnalysisDiv(id){
	
	var html =  '<div class="col-md-3" >'
    +'<div class="box box-success" style="min-height:300px;">' 
     +  '<div class="box-header with-border">'
     +'<h3 class="box-title">Coupons Scheme Analysis</h3>'    
     +'</div><!-- /.box-header -->' 
     +  getDiscountAnalysisBody(id)
     
     + '</div><!-- /.box -->' 
     
    + '</div><!-- /.col -->' ;
	
	return html;
	
}

function getDiscountAnalysisBody(id){
	
	var buttonGrp = '<div class="input-group" style = "margin-top:12px;">'
    +'<div class="input-group-btn">'
     +'<button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown">Rate <span class="fa fa-caret-down"></span></button>' 
     +'<ul class="dropdown-menu">' 
	  	+'<li><a class = "rating" value = "1" rating = "0">Below Expectation</a></li>'
	    +'<li><a class = "rating" value = "1" rating = "1">Met Expectation</a></li>'
	    +'<li><a class = "rating" value = "1" rating = "2">Above Expectation</a></li>'	      
      +'</ul>'
    +'</div><!-- /btn-group -->'
    +'<input type="text" class="form-control" value="Above Expectation">'
  +'</div><!-- /input-group -->' ;
	
	var html = '<div class="box-body" style="min-height:220px;">'
    +'<ul class="nav nav-pills nav-stacked">'
     +'<li><a><b>Coupon Distributed</b> &nbsp;<p  class="pull-right">500</p></a></li>'   
      +'<li><a href="#"><b>Expected usuage</b> &nbsp;<p  class="pull-right">125</p></a></li>'  
        +'<li><a href="#"><b>Coupons redeemed</b> &nbsp; <p class="pull-right">180</p></a></li>'
        +'<li><a href="#"><b>Validity:</b> &nbsp; <p class="pull-right">Expired</p></a></li>'
      +'</ul>'
      +'</hr>'
      + '<p class = "label label-success">Highly Effective</p>'
    +'</div>'
    +'<div class="box-footer">'
    + buttonGrp
    +'</div>';
	
//	 +'</div class="box-footer">'
//	    + buttonGrp
//	 +'</div>';
   
	
     return html;
}

function actionOnClickingRating(){
	$(".rating").click(function(e){
		//console.info("manish");
		var rating = $(this).context.attributes.rating.nodeValue;
		var index = $(this).context.attributes.value.nodeValue;
		//var eventId = $(this).context.attributes.eventId.nodeValue;
		idValueMap[index] = rating;
		var input = $(this).parent().parent().parent().parent().find("input");
		//console.info(input);
		input.val($(this).context.innerHTML);
		updateOverAllRating();
	});
}

function updateOverAllRating(){	
	//3
	var total = 0;
	$.each( idValueMap, function(index,value){
		 console.log("Index = " + index + " value = " + value); 
		 total = total + parseInt(value);
		})
		console.info(total);
	
	var name = "star3";
	var input = "input[type=radio][name="+name+"]";
	console.info($(input));
	
	 if(total==8){
		 //rating = 5
		 $(input)[4].checked = true;
	 }
	 else if(total>5){
		 //rating = 4
		 $(input)[3].checked = true;
	 }
	 
	 else if(total>3){
		 //rating = 3
		 $(input)[2].checked = true;
	 }
	 else if(total>2){
		 //rating = 2
		 $(input)[1].checked = true;
	 }
	 else if(total>=0){
		 //rating = 1
		 $(input)[0].checked = true;
	 }
	
}


function getRegistrationAnalysisDiv(id){
	
	var html =  '<div class="col-md-3">'
		+'<div class="box box-success" style="min-height:300px;">' 
	     +  '<div class="box-header with-border">'
	     +'<h3 class="box-title">Registration Scheme</h3>'    
	     +'</div><!-- /.box-header -->'  
	     +  getRegistrationAnalysisBody(id)
	     + '</div><!-- /.box -->' 
	    + '</div><!-- /.col -->' ;
		
		return html;

	
}

function getRegistrationAnalysisBody(id){
	
	var buttonGrp = '<div class="input-group" style = "margin-top:12px;">'
	    +'<div class="input-group-btn">'
	     +'<button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown">Rate <span class="fa fa-caret-down"></span></button>' 
	     +'<ul class="dropdown-menu">' 
		  	+'<li><a class = "rating" value = "2" rating = "0">Below Expectation</a></li>'
		    +'<li><a class = "rating" value = "2" rating = "1">Met Expectation</a></li>'
		    +'<li><a class = "rating" value = "2" rating = "2">Above Expectation</a></li>'	      
	     +'</ul>'
	    +'</div><!-- /btn-group -->'
	    +'<input type="text" class="form-control" value = "Met Expectation">'
	  +'</div><!-- /input-group -->' ;
	
	var html = '<div class="box-body" style="min-height:220px;">'
	    +'<ul class="nav nav-pills nav-stacked">'
	     +'<li><a><b>Lead generated</b> &nbsp;<p  class="pull-right">150</p></a></li>'   
	      +'<li><a href="#"><b>Expected conversion</b> &nbsp;<p  class="pull-right">60</p></a></li>'  
	        +'<li><a href="#"><b>Lead converted</b> &nbsp; <p class="pull-right">50</p></a></li>'
	        +'<li><a href="#"><b>Validity:</b> &nbsp; <p class="pull-right">5 days remaining</p></a></li>'
	      +'</ul>'
	      +'</hr>'
	      + '<p class = "label label-success">Effective</p>'
	    +'</div>'
	    +'<div class="box-footer">'
	    + buttonGrp
	    +'</div>';
	    return html;
}

function getProfitAnalysisDiv(id){
	
	var html =  '<div class="col-md-3">'
		+'<div class="box box-success" style="min-height:300px;">' 
	     +  '<div class="box-header with-border">'
	     +'<h3 class="box-title">Profit Analysis</h3>'    
	     +'</div><!-- /.box-header -->'  
	     +  getProfitAnalysisBody(id)
	     + '</div><!-- /.box -->' 
	    + '</div><!-- /.col -->' ;
		
		return html;
	
}


function getProfitAnalysisBody(id){
	
	var buttonGrp = '<div class="input-group" style = "margin-top:12px;">'
	    +'<div class="input-group-btn">'
	     +'<button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown">Rate <span class="fa fa-caret-down"></span></button>' 
	     +'<ul class="dropdown-menu">' 
		  	+'<li><a class = "rating" value = "3" rating = "0">Below Expectation</a></li>'
		    +'<li><a class = "rating" value = "3" rating = "1">Met Expectation</a></li>'
		    +'<li><a class = "rating" value = "3" rating = "2">Above Expectation</a></li>'	      
	      +'</ul>'
	    +'</div><!-- /btn-group -->'
	    +'<input type="text" class="form-control" value = "Below Expectation">'
	  +'</div><!-- /input-group -->' ;
	
	
	var html = '<div class="box-body" style="min-height:220px;">'
    +'<ul class="nav nav-pills nav-stacked">'
     +'<li><a><b>Expected Profit</b> &nbsp;<p class="pull-right">Rs. 35,000</p></a></li>'   
      +'<li><a href="#"><b>Profit generated</b> &nbsp;<p class="pull-right">Rs. 28,753</p></a></li>'  
      +'</ul>'
      +'</hr>'
      + '<p class = "label label-danger">Less-effective</p>'
    +'</div>'
    +'<div class="box-footer">'
    + buttonGrp
    +'</div>';

    return html;
}


function getRevenueAnalysisDiv(id){
	
	var html =  '<div class="col-md-3">'
		+'<div class="box box-success" style="min-height:300px;">' 
	     +  '<div class="box-header with-border">'
	     +'<h3 class="box-title">Revenue Analysis</h3>'    
	     +'</div><!-- /.box-header -->' 
	     +  getRevenueAnalysisBody(id)
	     + '</div><!-- /.box -->' 
	    + '</div><!-- /.col -->' ;
		
		return html;	
}

function getRevenueAnalysisBody(id){
	
	var buttonGrp = '<div class="input-group" style = "margin-top:12px;">'
	    +'<div class="input-group-btn">'
	     +'<button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown">Rate <span class="fa fa-caret-down"></span></button>' 
	     +'<ul class="dropdown-menu">' 
		  	+'<li><a class = "rating" value = "4" rating = "0">Below Expectation</a></li>'
		    +'<li><a class = "rating" value = "4" rating = "1">Met Expectation</a></li>'
		    +'<li><a class = "rating" value = "4" rating = "2">Above Expectation</a></li>'	      
	      +'</ul>'
	    +'</div><!-- /btn-group -->'
	    +'<input type="text" class="form-control" value = "Above Expectation">'
	  +'</div><!-- /input-group -->' ;
	
	
	var html = '<div class="box-body" style="min-height:220px;">'
    +'<ul class="nav nav-pills nav-stacked">'
     +'<li><a><b>Expected Revenue</b> &nbsp;<p  class="pull-right"><small>Rs. 286,560</small></p></a></li>'   
      +'<li><a><b>Revenue generated</b> &nbsp;<p class="pull-right"><small>Rs. 348,560</small></p></a></li>'  
      +'</ul>'
      +'</hr>'
      + '<p class = "label label-success">Highly Effective</p>'
    +'</div>'
    +'<div class="box-footer">'
    + buttonGrp
    +'</div>';

    return html;
}





