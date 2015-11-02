$(document).ready(function() {
	console.log("Hey Manish in product.js");
	var initPage = function() {
		console.log("Hey Manish in product.js");
		$('#product-add-button').click(addProduct);
		//$("#loadMoreProduct").click(retrieveProduct);
		displayProductInGrid();
	}
	initPage();
}
);

var addProduct = function(evt) {
	var formData = $('#employee-form').serializeObject();
	
	// modify role object to become an object if only one is selected
	console.info(formData);
	if (typeof(formData.roles) === 'string') {
		formData.roles = [formData.roles];
	}
	
	var url = 'addProduct';
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
		console.log("Done adding");
		$('#product-add-modal').modal('hide');
		ReloadGridProducts();
		
		
	});
};

var retrieveProduct = function(element) {
	console.info("retrieveProduct, url = " +element.brandName);	
    var d = document.getElementById("productList");
    var x = document.createElement("LI");
    var oImg=document.createElement("img");
    var a = document.createElement("a");
    a.className = "users-list-name" ;
    var span = document.createElement(span);
    span.className = "users-list-date";
    a.innerHTML = element.productName;
    span.innerHTML = "By " + element.brandName; 
   // oImg.setAttribute('src', "http://ecx.images-amazon.com/images/I/31LxLxHO-CL._AA160_FMwebp_QL70_.jpg");
    oImg.setAttribute('src', element.imageUrl);
    x.appendChild(oImg);
    x.appendChild(a);
    x.appendChild(span);
    d.appendChild(x);
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

