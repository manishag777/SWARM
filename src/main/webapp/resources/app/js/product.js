var sportData;


$(document).ready(function() {
	var modifyStatus = "add";
	var initPage = function() {
		console.log("Hey Manish in product.js");
		$('#product-save-button').click(updateProduct);
		$('#product-detail-add-button').click(addProductDetail);
		$('#top-add-button').click(addProduct);
		displayProductInGrid();
		fetchSportList();
		fetchBrandList();
		$('#sport-type-filter').change(function() { 
			fetchBrandList();
			ReloadGridProducts();
		});
		
		$('#brand-type-filter').change(function() { 
			ReloadGridProducts();
		});

		
		$('#search-product-input').keyup(function(e){
			//console.info("manish");
			ReloadGridProducts();
//			if(e.keyCode == 13)
//			{
//				var id = $(this).val();
//				ReloadGridProducts();
//				//var x = getCustomerForGivenId(id);	
//			}	
		});
		$('#search-product-btn').click(ReloadGridProducts);
		
		$('.dropdown-menu .seen-unseen').click(function(event){
		    event.stopPropagation();
		});
		
//		$('#seen').click(function(e){
//			$('#seen').css('background-color', 'blue');
//			$('#seen').css('color', 'white');
//			$('#unseen').css('background-color', 'white');
//			$('#unseen').css('color', 'blue');
//		});
		
//		$('#unseen').click(function(e){
//			$('#unseen').css('background-color', 'blue');
//			$('#unseen').css('color', 'white');
//			$('#seen').css('background-color', 'white');
//			$('#seen').css('color', 'blue');
//		});
		
		$('ul.nav-pills li a').click(function (e) {
			  $('ul.nav-pills li.active').removeClass('active')
			  $(this).parent('li').addClass('active')
			})

	}
	initPage();
}
);



var addProductDetail = function(evt) {

	var formData = $('#product-detail-form').serializeObject();
	console.info(formData);
	if(formData.price <=0){
		alert("set cost-price of product before saving");
		return;
	}
	
	if(formData.qty < 0){
		alert("set quantity of product cannot be negative");
		return;
	}

	
	
	var url = 'addProductDetail';
	//formData.productId = $("#wproductId").val();
	console.log("formData = "+formData);
	console.log(formData);
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


	//console.info("editProduct = " +element);
	document.getElementById('wproductId').value = element.productId;
	document.getElementById('wproductName').value = element.productName;
	document.getElementById('wbrandName').value = element.brandName;
	document.getElementById('wsizes').value = element.sizes;
	document.getElementById('wcolors').value = element.colors;
	document.getElementById('wimageUrl').value = element.imageUrl;
	
	var editor = '<label for="body">Product Details</label>'
		+ '<textarea class="textarea form-control" id="textarea-id" name="productInfo" placeholder="Place some text here" style="width: 100%; height: 200px; font-size: 14px; line-height: 18px; border: 1px solid #dddddd; padding: 10px;"></textarea>'
	document.getElementById('textarea-div').innerHTML = editor;
	$("#textarea-id").val(element.productInfo);  
	$("#textarea-id").wysihtml5();
	
	var IdElement = document.getElementById('wproductId');
	IdElement.setAttribute("readonly", "readonly");
	modifyStatus = "edit";
}

var addProduct = function(element) {
	
	document.getElementById('wproductId').value = '';
	document.getElementById('wproductName').value = '';
	document.getElementById('wbrandName').value = '';
	document.getElementById('wsizes').value = '';
	document.getElementById('wcolors').value = '';
	document.getElementById('wimageUrl').value = '';
	var editor = '<label for="body">Product Details</label>'
		+ '<textarea class="textarea form-control" id="textarea-id" name="bodyText"  placeholder="Place some text here" style="width: 100%; height: 200px; font-size: 14px; line-height: 18px; border: 1px solid #dddddd; padding: 10px;"></textarea>'
	document.getElementById('textarea-div').innerHTML = editor;
	$("#textarea-id").wysihtml5();
	var IdElement = document.getElementById('wproductId');
	IdElement.removeAttribute('readonly');
	var value = $("#sport-type-filter").val();
	console.log(value);
	if(value!="0"){
		parameter = "\""+value+"\"";
		$('#sport-filter option[value='+parameter+']').prop('selected', 'selected').change();
	}
	else{
		$('#sport-filter option[value="other"]').prop('selected', 'selected').change();
	}
	
	modifyStatus = "add";
};
var updateProduct = function(){

	var url = 'addProduct';
	var formData = $('#product-form').serializeObject();
	delete formData._wysihtml5_mode;
	if(modifyStatus.localeCompare("add")==0){
		url = 'addProduct';
	}
	else if(modifyStatus.localeCompare("edit")==0){
		url = 'editProduct';
		//formData.productId = $('wproductId').val();
	}

	console.info(formData);
	formData.sportId = $("#sport-filter").val();		
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

	//console.info("retrieveProduct, url = " +element.brandName);	
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
//    var btn = document.createElement("BUTTON");        
//    var t = document.createTextNode("Detail");       
//    btn.appendChild(t);                                
//    btn.setAttribute("data-target","#product-detail-add-modal");
//    btn.setAttribute("data-toggle","modal");
//    btn.addEventListener("click", function(){
//    	distributeProduct(element);
//    });
    
    var btn2 = document.createElement("BUTTON");        
    var t2 = document.createTextNode("Distribute");       
    btn2.appendChild(t2);                                
    btn2.setAttribute("data-target","#product-detail-add-modal");
    btn2.setAttribute("data-toggle","modal");
    btn2.addEventListener("click", function(){
    	distributeProduct(element);
    });
    
    var btn3 = document.createElement("BUTTON");        
    var t3 = document.createTextNode("Edit");       
    btn3.appendChild(t3);                                
    btn3.setAttribute("data-target","#product-add-modal");
    btn3.setAttribute("data-toggle","modal");
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

	var brand = $("#brand-type-filter").val();
	var textSearch = $("#search-product-input").val();
	console.info("brand = " + brand+" textSearch = "+ textSearch);
	
	$.ajax({
		url:'getAllProduct',
		data:{'sport_id': $("#sport-type-filter").val(), 'brand' : brand , 'textSearchInput': textSearch },
		success : function(data){
			$.each(data, function(index, element) {
				retrieveProduct(element);
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
	getPrice();
}

var getPrice = function(){

	var productId = $("#productId").val();
	var color = $("#dropdown_color").val();
	var size = $("#dropdown_size").val();
	var storeId = $("#dropdown_store").val();
	console.info("hey");
	// Ajax query for price
	$.ajax({
		url : 'getPrice',
		data : {pid:productId, color:color, size:size, storeId:storeId},
		type : 'GET',
		contentType : "application/json",
		success : function(data) {
			console.info(data);
			document.getElementById('price').value = data;	
		},
	}).done(function() {
		//console.info(productId + " "+ color+" "+size+" "+storeId);
		//document.getElementById('price').value = 30;	
	});
}

var fetchSportList = function(){

	var select = document.getElementById('sport-type-filter');
	var select2 = document.getElementById('sport-filter');
	$.ajax({
		url : 'fetchSportList',
		type : 'GET',
		contentType : "application/json",
		success : function(data) {
			sportData = data;
			console.info(data);
			for (var i in data) {
				var opt = document.createElement('option');
				opt.value = data[i].sportId;
				opt.innerHTML = data[i].name;
				select.appendChild(opt);
				
				var opt2 = document.createElement('option');
				opt2.value = data[i].sportId;
				opt2.innerHTML = data[i].name;
				select2.appendChild(opt2);
			}
			
		},
	}).done(function() {
				
	});
}


var fetchBrandList = function(){
	$('#brand-type-filter').empty();
	var select = document.getElementById('brand-type-filter');
	select.appendChild(createOption("0","All Brand"));
	console.info("Hey in fetchBrandList");
	$.ajax({
		url:'getBrandList',
		data:{'sport_id': $("#sport-type-filter").val()},
		success : function(data){
			console.info(data);
			for (var i in data) {
					select.appendChild(createOption(data[i], data[i]));
				}
		}
		
	});
}

var createOption = function(value, name){
	var opt = document.createElement('option');
	opt.value = value;
	opt.innerHTML = name;
	return opt;
}

