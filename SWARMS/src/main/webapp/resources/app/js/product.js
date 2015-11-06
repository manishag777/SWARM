$(document).ready(function() {
	console.log("Hey Manish in product.js");
	var modifyStatus = "add";
	var initPage = function() {
		console.log("Hey Manish in product.js");
		$('#product-save-button').click(updateProduct);
		$('#product-detail-add-button').click(addProductDetail);
		$('#top-add-button').click(addProduct);
		displayProductInGrid();
	}
	initPage();
}
);



var addProductDetail = function(evt) {
	var formData = $('#product-detail-form').serializeObject();
	
	// modify role object to become an object if only one is selected
	console.info(formData);
//	if (typeof(formData.roles) === 'string') {
//		formData.roles = [formData.roles];
//	}
	
	var url = 'addProductDetail';
	//console.log("formData = "+formData);
	
	$.ajax({
		url : url,
		data : JSON.stringify(formData),
		type : 'POST',
		contentType : "application/json",
		success : function(data) {
			console.info(data);
		},
	}).done(function() {
		console.log("Done adding product detail");
		$('#product-detail-add-modal').modal('hide');
		ReloadGridProducts();
		
		
	});
};

var editProduct = function(element){
	console.info("editProduct = " +element);
	document.getElementById('wproductId').value = element.productId;
	document.getElementById('wproductName').value = element.productName;
	document.getElementById('wbrandName').value = element.brandName;
	document.getElementById('wproductInfo').value = element.productInfo;
	document.getElementById('wsizes').value = element.sizes;
	document.getElementById('wcolors').value = element.colors;
	document.getElementById('wimageUrl').value = element.imageUrl;
	var IdElement = document.getElementById('wproductId');
	IdElement.setAttribute("readonly", "readonly");
	modifyStatus = "edit";
}

var addProduct = function(element) {
	
	document.getElementById('wproductId').value = '';
	document.getElementById('wproductName').value = '';
	document.getElementById('wbrandName').value = '';
	document.getElementById('wproductInfo').value = '';
	document.getElementById('wsizes').value = '';
	document.getElementById('wcolors').value = '';
	document.getElementById('wimageUrl').value = '';
	var IdElement = document.getElementById('wproductId');
	IdElement.removeAttribute('readonly');
	modifyStatus = "add";
};

var updateProduct = function(){
	
	var url = 'addProduct';
	if(modifyStatus.localeCompare("add")==0)
		url = 'addProduct';
	else if(modifyStatus.localeCompare("edit")==0){
		url = 'editProduct';
	}
	console.info("At edit Product, url = " +url);
	var formData = $('#product-form').serializeObject();
	// modify role object to become an object if only one is selected
	console.info(formData);
	if (typeof(formData.roles) === 'string') {
		formData.roles = [formData.roles];
	}
		
	$.ajax({
		url : url,
		data : JSON.stringify(formData),
		type : 'POST',
		contentType : "application/json",
		success : function(data) {
			console.info(data);
			modifyStatus = "add";
		},
	}).done(function() {
		console.log("Done adding");
		$('#product-add-modal').modal('hide');
		ReloadGridProducts();
		
		
	});
}

var retrieveProduct = function(element) {
	console.info("retrieveProduct, url = " +element.brandName);	
    var parent = document.getElementById("productList");
    var li = document.createElement("LI");
    li.setAttribute("id", element.productId);
    var oImg=document.createElement("img");
    var a = document.createElement("a");
    a.className = "users-list-name" ;
    var span = document.createElement(span);
    span.className = "users-list-date";
    a.innerHTML = element.productName;
    span.innerHTML = "By " + element.brandName; 
   // oImg.setAttribute('src', "http://ecx.images-amazon.com/images/I/31LxLxHO-CL._AA160_FMwebp_QL70_.jpg");
    oImg.setAttribute('src', element.imageUrl);
    
    
    var div = document.createElement("div");
    div.setAttribute("class", "col-lg-12 col-sm-12 table-buttons");
   // div.setAttribute("class","row");
    var btn = document.createElement("BUTTON");        
    var t = document.createTextNode("Detail");       
    btn.appendChild(t);                                
    btn.setAttribute("data-target","#product-detail-add-modal");
    btn.setAttribute("data-toggle","modal");
	console.info("retrieveProduct above button = "+element.imageUrl);	
    btn.addEventListener("click", function(){
    	distributeProduct(element);
    });
    
    var btn2 = document.createElement("BUTTON");        
    var t2 = document.createTextNode("Distribute");       
    btn2.appendChild(t2);                                
    btn2.setAttribute("data-target","#product-detail-add-modal");
    btn2.setAttribute("data-toggle","modal");
	console.info("retrieveProduct above button = "+element.imageUrl);	
    btn2.addEventListener("click", function(){
    	distributeProduct(element);
    });
    
    var btn3 = document.createElement("BUTTON");        
    var t3 = document.createTextNode("Edit");       
    btn3.appendChild(t3);                                
    btn3.setAttribute("data-target","#product-add-modal");
    btn3.setAttribute("data-toggle","modal");
	console.info("retrieveProduct above button = "+element.imageUrl);	
    btn3.addEventListener("click", function(){
    	editProduct(element);
    });
    
    div.appendChild(btn);
    div.appendChild(btn2);
    div.appendChild(btn3);
    li.appendChild(oImg);
    li.appendChild(a);
    li.appendChild(span);
    li.appendChild(div);
    parent.appendChild(li);
};

var displayProductInGrid = function(evt){
	console.info("yes I am called");
	$.ajax({
		url:'getAllProduct',
		success : function(data){
			$.each(data, function(index, element) {
				retrieveProduct(element);
				console.info(element);
	        });
		}
		
	});
};

var ReloadGridProducts = function(evt){	
	//Delete products
	var container = document.getElementById("productList");
	container.innerHTML = '';
	displayProductInGrid();
}

var distributeProduct = function(element){
	console.info("pid ="+ element.productId +" " +element.colors + " "+element.sizes);
	var sizeArr = element.sizes.split("_");
	var colorArr = element.colors.split("_");
	
	console.info(sizeArr);
	
	var dropdownSize  = document.getElementById('dropdown_size');
	dropdownSize.innerHTML = '';
	for(var i = 0; i < sizeArr.length; i++) {
	    var opt = document.createElement('option');
	    opt.innerHTML = sizeArr[i];
	    opt.value = sizeArr[i];
	    dropdownSize.appendChild(opt);
	}
	
	var dropdownColor  = document.getElementById('dropdown_color');
	dropdownColor.innerHTML = '';
	for(var i = 0; i < colorArr.length; i++) {
	    var opt = document.createElement('option');
	    opt.innerHTML = colorArr[i];
	    opt.value = colorArr[i];
	    dropdownColor.appendChild(opt);
	}
	console.info("pid at bottom ="+ element.productId +" " +element.colors + " "+element.sizes);
	document.getElementById('productId').value = element.productId;
	//$( "#productId" ).prop( "disabled", true );	
}



