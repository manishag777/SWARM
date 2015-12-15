$(document).ready(function() {
	var initPage = function() {
		$.ajax({
			type : 'GET',
			contentType : 'application/json',
			url : 'getAllPriceComparisons',
			dataType : "json",
			success : function(data) {
				populatePriceComparisonTable(data)
			},
			error : function(e) {
				alert("Error getting price comparisons");
			},
		});

	}
	initPage();
});

function format(d) {
	var div = "<h>Information to be displayed later</h>";
	return div;
}

var populatePriceComparisonTable = function(priceComparisons) {
	var datatable = $('#price-comparison-table')
			.DataTable(
					{
						"iDisplayLength" : 20,
						"bLengthChange" : false,
						data : priceComparisons,
						columns : [
								{
									"className" : 'details-control',
									"orderable" : false,
									"data" : null,
									'defaultContent' : ''

								},
								{
									data : 'pid'
								},
								{
									data : 'procurmentPrice'
								},
								{
									data : 'mrp'
								},
								{
									data : 'discount'
								},
								{
									data : 'sellingPrice'
								},
								{
									data : null,
									mRender : function(data, type, full) {
										if (data.amazonPrice < data.sellingPrice)
											return '<h>'
													+ data.amazonPrice
													+ '&nbsp;</h><i class="fa  fa-arrow-down" style = "color:red; float:right"></i>';
										else
											return '<h>'
													+ data.amazonPrice
													+ '&nbsp;</h><i class="fa fa-arrow-up" style = "color:green; float:right"></i>';
									}
								},
								{
									data : null,
									mRender : function(data, type, full) {
										if (data.ebayPrice < data.sellingPrice)
											return '<h>'
													+ data.ebayPrice
													+ '&nbsp;</h><i class="fa  fa-arrow-down" style = "color:red; float:right"></i>';
										else
											return '<h>'
													+ data.ebayPrice
													+ '&nbsp;</h><i class="fa fa-arrow-up" style = "color:green; float:right"></i>';
									}
								},
								{
									data : null,
									mRender : function(data, type, full) {
										if (data.amazonPrice < data.sellingPrice) {
											return 'Yes';
										}
										if (data.ebayPrice < data.sellingPrice) {
											return 'Yes';
										}
										return 'No';
									}
								},
								{
									data : null,
									mRender : function(data, type, full) {
										var updatePriceButtonId = '"updatePriceButton'
												+ data.pid + '"';
										return '<button type="button" class="btn btn-primary" data-toggle="modal" id='
												+ updatePriceButtonId
												+ ' data-target="#price-comparison-update-modal" onclick="populateUpdatePriceModal(\''
												+ data.pid
												+ '\')">Update Price</button>'
									}
								} ],
						sort : true,
						"order": [[ 1, "asc" ]]
					});
	// Array to track the ids of the details displayed rows
	var detailRows = [];

	$('#price-comparison-table tbody').on('click', 'tr td.details-control',
			function() {
				var tr = $(this).closest('tr');
				var row = datatable.row(tr);
				var idx = $.inArray(tr.attr('id'), detailRows);

				if (row.child.isShown()) {
					tr.removeClass('details');
					row.child.hide();

					// Remove from the 'open' array
					detailRows.splice(idx, 1);
				} else {
					tr.addClass('details');
					row.child(format(row.data())).show();

					// Add to the 'open' array
					if (idx === -1) {
						detailRows.push(tr.attr('id'));
					}
				}
			});

	// On each draw, loop over the `detailRows` array and show any child rows
	datatable.on('draw', function() {
		$.each(detailRows, function(i, id) {
			$('#' + id + ' td.details-control').trigger('click');
		});
	});
}

var populateUpdatePriceModal = function(productId) {
	$
			.ajax({
				url : 'getUpdatedPriceEntity',
				data : {
					'product_id' : productId
				},
				success : function(data) {
					document.getElementById('UPproductId').value = data.pid;
					document.getElementById('UPprocurmentPriceId').value = data.procurmentPrice;
					document.getElementById('UPmrpId').value = data.mrp;
					document.getElementById('UPdiscountId').value = data.currentDiscount;
					document.getElementById('UPspId').value = data.ourPrice;
					document.getElementById('UPamazonPriceId').value = data.amazonPrice;
					document.getElementById('UPebayPriceId').value = data.ebayPrice;
					populateReviewPriceBox(data.pid);
					var prevBestDiscountPercentSuggestion = '<font color="blue"><li type="disc">Previous Best Discount percent by Average Profit is <var id="bestDiscount">""</var></li></font>';

					var suggestions = getSuggestions(data.ourPrice,
							data.amazonPrice, data.ebayPrice, data.mrp,
							data.procurmentPrice, 5);

					document.getElementById('UPSuggestionSectionId').innerHTML = '<p><hr></p><p><h4 class="modal-title">Analysis & Suggestions:</h4><h4 class="modal-title">'
							+ suggestions.suggestionText
							+ prevBestDiscountPercentSuggestion + '</h4></p>';

					document.getElementById('UPsuggestedDiscountId').value = suggestions.discount;
					var updatePriceButton = document
							.getElementById('update-discount-button');
					updatePriceButton.addEventListener("click", function() {
						updatePrice();
					});
				}
			});
}

var populateReviewPriceBox = function(productId) {
	$.ajax({
		url : 'getPreviousDiscounts',
		data : {
			'product_id' : productId
		},
		success : function(data) {
			populatePreviousMarkingTable(data)
		},

		error : function(e) {
			alert("Error getting previous discounts");
		},
	});
}

var populatePreviousMarkingTable = function(previousMarkings) {
	console.info(previousMarkings);

	var prevBestProfit = 0;
	var prevBestProfitDiscount;
	for (var i = 0; i < previousMarkings.length; i++) {
		if (previousMarkings[i].averageProfit >= prevBestProfit) {
			prevBestProfit = previousMarkings[i].averageProfit;
			prevBestProfitDiscount = previousMarkings[i].discountPercent;
		}
	}
	$("#bestDiscount").text(prevBestProfitDiscount);
	$('#review-price-table').dataTable().fnDestroy();
	$('#review-price-table').DataTable({
		"iDisplayLength" : 3,
		data : previousMarkings,
		columns : [ {
			data : 'startDate'
		}, {
			data : 'endDate'
		}, {
			data : 'procurementPrice'
		}, {
			data : 'mrp'
		}, {
			data : 'discountPercent'
		}, {
			data : 'averageProfit'
		} ],
		filter : false,
		sort : true,
		"bLengthChange" : false,
		"order": [[ 2, "desc" ]]
	});
}

var getSuggestions = function(ourPrice, amazonPrice, ebayPrice, mrp,
		procurementPrice, minMarginPercent) {
	var minimumMarginPrice = procurementPrice
			+ (minMarginPercent * procurementPrice / 100);
	var returnObject = new Object();
	var lowerPrice = amazonPrice;
	if (ebayPrice < amazonPrice) {
		lowerPrice = ebayPrice;
	}
	var suggestionText = '';
	var discountInt = 0;
	if (minimumMarginPrice > lowerPrice) {
		var discount = (mrp - minimumMarginPrice) * 100 / mrp;
		discountInt = discount | 0 + 1;
		var companies = '';
		var added = false;
		if (minimumMarginPrice > amazonPrice) {
			companies += 'Amazon';
			added = true;
		}
		if (minimumMarginPrice > ebayPrice) {
			if (added) {
				companies += ' and';
			}
			companies += ' Ebay';
		}
		suggestionText += '<font color="red"><li type="disc">'
				+ companies
				+ ': Selling at prices lower than your minimum margin price.</li></font>'
				+ '<font color="blue"><li type="dic">Set discount% = '
				+ discountInt
				+ ' for selling price to be at minimum margin price.</li>';

	} else if (ourPrice < amazonPrice && ourPrice < ebayPrice) {
		var discount = (mrp - lowerPrice) * 100 / mrp;
		discountInt = discount | 0 + 1;
		suggestionText += '<font color="green"><li type="disc">Your product is already at good price.</li></font>'
				+ '<font color="blue"><li type="disc">Can decrease discount upto '
				+ discountInt + "% to compete them.</li></font>";
	} else if (ourPrice < amazonPrice && ourPrice > ebayPrice) {
		var discount = (mrp - lowerPrice) * 100 / mrp;
		console.info("discount=" + discount);
		discountInt = discount | 0 + 1;
		suggestionText += '<font color="red"><li type="disc">Ebay is selling at lower price than you.</font></li>'
				+ '<font color="blue"><li type="disc">Set discount percent more than '
				+ discountInt + "% to compete with Ebay.</li></font>";
	} else if (ourPrice > amazonPrice && ourPrice < ebayPrice) {
		var discount = (mrp - lowerPrice) * 100 / mrp;
		discountInt = discount | 0 + 1;
		suggestionText += '<font color="red"><li type="disc">Amazon is selling at lower price than you.</font></li>'
				+ '<font color="blue"><li type="disc">Set discount more than '
				+ discountInt + "% to compete with Amazon.</li></font>";
	} else if (ourPrice > amazonPrice && ourPrice > ebayPrice) {
		var discount = (mrp - lowerPrice) * 100 / mrp;
		discountInt = discount | 0 + 1;
		suggestionText += '<font color="red"><li type="disc">Amazon and Ebay are selling at lower price than you.</li></font>'
				+ '<font color="blue"><li type="disc">Set discount percent more than '
				+ discountInt + "% to compete with them.</li></font>"
	}
	returnObject.suggestionText = suggestionText;
	returnObject.discount = discountInt;
	return returnObject;
}

var updatePrice = function() {
	console.info("updating price");
	var input = new Object();
	input.productId = $("#UPproductId").val();
	input.newDiscount = $("#UPsuggestedDiscountId").val();
	input.procurmentPrice = $("#UPprocurmentPriceId").val();
	input.mrp = $("#UPmrpId").val();
	console.info(input);
	$.ajax({
		url : 'updateDiscountPrice',
		data : JSON.stringify(input),
		type : 'POST',
		contentType : "application/json",
		success : function(data) {

		},
	}).done(function() {
		$('#price-comparison-update-modal').modal('hide');
		location.reload();
	});
}

var refreshPrices = function() {
	console.info("refreshing all product comparison prices");
	$.ajax(
			{
				url : 'refreshPriceComparisons',
				async : false,
				contentType : "application/json",
				success : function(data) {
					var alertString = "Price of " + data
							+ " products needs to be updated";
					alert(alertString);
				},
			}).done(function() {
		location.reload();
	});
}
