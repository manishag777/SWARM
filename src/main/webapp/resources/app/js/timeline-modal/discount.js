
var discountPercentage=40;
var selected = [];
var eventId;

function intializeDiscount(id){

 	//setTimeout(sliderEvent(id),1000);
 	sliderEvent(id);
 	$('#bestDiscountPercentDetail'+id).click(function(e){
 		$("#best-discountPercent-detail").modal("show");
 		discountPercentTable();
 	});
 	$('#setBestDiscountPercent'+id).click(function(e){
 		///discountPercentTable();
 		setBestDiscountPercent(id);
 	});
 	
 	$('#selectProduct'+id).click(function(e){
 		eventId = id;
 		$("#select-product-modal").modal("show");
 		//discountPercentTable();
 		populateSelectProductModal();
 	});
}


var populateSelectProductModal = function() {
	$.ajax({
		url : 'getProductsWithPrice',
		data : {
			'sport_id' : 'athletics'
		},
		success : function(data) {
			console.info(data);
			$('#select-product-table').dataTable().fnDestroy();
			var datatable = $('#select-product-table')
			.DataTable(
					{
						"iDisplayLength" : 5,
						"bLengthChange" : false,
						data : data,
						columns : [
								{
									data : 'pid'
								},
								{
									data : 'productDetails'
								},
								{
									data : 'procurmentPrice'
								},
								{
									data : 'mrp'
								},
								{
									data : 'currentDiscount'
								},
								 ],
						sort : true,
						"order" : [ [ 1, "asc" ] ]
					});
			
			$('#select-product-table tbody').on( 'click', 'tr', function () {
		        $(this).toggleClass('selected');
		    } );
		}
	});
}

var selectedLength=0;

var getSelectedItems = function() {
	var table = $('#select-product-table').DataTable();
	selectedRows = table.rows('.selected').data();
	$('#select-product-modal').modal('hide');
	selectedLength = selectedRows.length;
	console.info(selectedRows.length);
	document.getElementById("productSelectedCount"+eventId).innerHTML = selectedRows.length;
}

var getAllItems = function() {
	var table = $('#select-product-table').DataTable();
	var selectedRows = table.data();
	$('#select-product-modal').modal('hide');
	console.info(selectedRows.length);
	selectedLength = selectedRows.length;
	document.getElementById("productSelectedCount"+eventId).innerHTML = selectedRows.length;
	
}


var ApplyDiscountOnSelected = function(){
	var table = $('#select-product-table').DataTable();
	var selectedRows = table.data();
	console.info("Discounts of "+ selectedLength+" items have been updated");
	swal("Discounts of "+ selectedLength+" items have been updated", " ", "success");
}


var discountPercentTable = function(){
	
    $('#discount-detail').DataTable( {
        data: discountDetailData,
        columns: [
            { title: "Event Name" },
            { title: "Discount(%)" },
            { title: "Associated profit(Rs.)" },
            { title: "Asscoiated customer" },
        ],
        filter: false,
        sort: false,
        paging: false,
        bInfo : false,
        destroy: true	
    } );
}

var setBestDiscountPercent = function(id){
	updateValues(discountPercentage, id);
}


var sliderEvent = function(id){
	console.info("slideEventCalled");
	$("#discSlider"+id).slider();
	$("#discSlider"+id).slider().on('slide', function(){
 		var value = $(this).slider('getValue');
 		//console.info("manish"+value);
 		updateValues(value, id);
 	});
}

var updateValues = function(value, id){

	console.info(value);
	discountPercentage = value;
	document.getElementById("dper"+id).innerHTML = value + "%";
	$("#discSlider"+id).slider('setValue', value)

}




