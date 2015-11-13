$(document).ready(function() {
	var initPage = function() {
		 $("#save-template").click(function() {
			 	saveEmailTemplate();
		 });
		 
		 fetchAllTemplates();
	}
	
	initPage();

});

var saveEmailTemplate = function(){
	var formData = $('#template-form').serializeObject();
	delete formData._wysihtml5_mode;
	var url = 'addTemplateMail';	
	
	$.ajax({
		url : url,	
		data : JSON.stringify(formData),
		type : 'POST',
		contentType : "application/json",
		success : function(data) {
			console.info(data);
		},
	}).done(function() {
		console.log("Done adding");
		$('#template-modal').modal('hide');
		
	});
}


var fetchAllTemplates = function(){
	
	var url = 'fetchAllTemplates';	
		
		$.ajax({
			url : url,	
			type : 'GET',
			contentType : "application/json",
			success : function(data) {
				createTemplate(data);
			},
		}).done(function() {
			console.log("Done adding");
			$('#template-modal').modal('hide');
			
		});
}

var createTemplate = function(data){
	
	
	for (var i = 0; i < 5; i++) {
		//console.info(data[i].bodyText);
		$( "#template-modal-list" ).append( htmlModal(data[0]));
	//	document.getElementById('mailSubject').innerHTML = data[i].subjectText;
	//	document.getElementById('mailBody').innerHTML = data[i].bodyText;
	}
	
}


var htmlModal = function(data){
	
	var dataParameter = "'"+ data.pid + "'";
	var prm2 = "'"+ data.subjectText + "'";
	var prm3 = "'"+ data.bodyText + "'";
	var prm1 = "'"+ data.tagText + "'";
	
	var modalHtml =  '<div class="templates-modal" style = "margin-top: 40px;" >'
	  + '<div class="modal modal-primary">' 
	  +  '<div class="">' 
	  +  '<div class="modal-content">'  
	  +      '<div class="modal-header">'  
	  +      '<div class = "row">'    
	  +        ' <div class="form-group col-lg-10">'     
	  +        ' <p><b> Tag: </b> #offer </p>'         
	  +         ' </div>'                   
	  +          '<button type="button" class="btn btn-default btn-md" data-toggle="modal" onClick = "openEditModal('+prm1+','+ prm2 +','+prm3+')" >'	
	  +			'<span class="glyphicon glyphicon-plus" aria-hidden="true" ></span>'		
	  +			'<span >Edit Template</span>'		
	  + 		'</button>'	
	  +          '</div>' 
	        
	  +       '</div>' 
	  +      '<div class="modal-body">    '             
	  +       '<div class="row">'   
	  +  	'<div class="col-xs-12">'		
	  +    		'<div class="box">'		
	  +	       '<div class="box-body table-responsive no-padding">'             
	  +	        '<div class="box-body pad">'            	
	  +	          '<div id = "subject">'          		
	  +	         '<p> <b>Subject : </b> <span id = "mailSubject">'+ data.subjectText+ '</span></p>'           			
	  + 	           '<p> <b>Body <br/> </b> <span id = "mailBody">'+ data.bodyText +'</span></p>'         			
	  + 	           '</div>'         		
	  +	           '</div>	'     		
	  +	            ' </div>'     
	  +        		'</div>'
	  +        	'<div>'	
	  +        			
	  +        	'</div>'	
	          		
	  +        '</div>'	
	            
	  +		'</div>'
			
			
	  +		'</div>'
	          
	         
	  +     '</div>' 
	  +   '</div>' 
	  +		'</div>'
	  
	  return modalHtml;
}

var openEditModal = function(prm1, prm2, prm3){
	console.info(prm1+" "+prm2+" "+prm3);
	//var value = 
	$('#template-modal').modal('show');
	$("#subjectId").val(prm2);
	$("#tag").val(prm1);
	var editorp = $('#textarea-id').data("wysihtml5").editor;
	
	editorp.observe("load", function() {
	       console.log("loaded");
	     });
		
}





