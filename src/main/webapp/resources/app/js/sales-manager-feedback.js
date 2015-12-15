$(document).ready(function() {
	var initPage = function() {
		console.info("Displaying feedbacks");
		displayFeedbacks();
	}
	initPage();
});

var displayFeedbacks = function(evt) {
	console.info("inside display feedback");
	$.ajax({
		url : 'getFeedbacks',
		success : function(data) {
			$.each(data, function(index, element) {
				getFeedbackString(element);
			});
		}
	});
};

var addFeedbackBox = function(element, feedbackString) {
	var parent = document.getElementById("feedbackList");
	var box = document.createElement('div');

	box.innerHTML = element.resolved ? getResolvedFeedbackBoxInnerHTML(element,
			feedbackString) : getUnResolvedFeedbackBoxInnerHTML(element,
			feedbackString);
	parent.appendChild(box);
};

var getUnResolvedFeedbackBoxInnerHTML = function(element, feedbackString) {
	var resolveButtonId = '"resolveButton' + element.feedbackId + '"';
	var updatePriceButtonId = '"updatePriceButton' + element.feedbackId + '"';
	var innerHTML = '<div class="box box-danger"> <div class="box-header"> <h3 class="box-title">Feedback Id: '
			+ element.feedbackId
			+ '&nbsp &nbsp &nbsp Customer Id: '
			+ element.customerId
			+ '&nbsp &nbsp &nbsp Product Id: '
			+ element.pid
			+ '</h3>'
			+ '<div class="box-tools pull-right"><span class="label label-danger">UnResolved</span></div>'
			+ '<div class="box-body">'
			+ '<div id="feedbackDetailText">'
			+ '<p>'
			+ feedbackString
			+ '</p></div>'
			+ '</div>'
			+ '<div class="box-footer">'
			+ '<button type="button" class="btn btn-primary" data-toggle="modal" id='
			+ updatePriceButtonId
			+ ' data-target="#price-update-modal" onclick="populateUpdatePriceModal(\''
			+ element.pid
			+ '\')">Update Price</button>'
			+ '&nbsp<button type="button" class="btn btn-success" data-toggle="modal" id='
			+ resolveButtonId
			+ ' onclick="markResolve(\''
			+ element.feedbackId
			+ '\')">Mark Resolved</button>' + '</div>' + '</div>';
	return innerHTML;
}

var getResolvedFeedbackBoxInnerHTML = function(element, feedbackString) {
	var innerHTML = '<div class="box box-success"> <div class="box-header"> <h3 class="box-title">Feedback Id:'
			+ element.feedbackId
			+ '&nbsp &nbsp &nbsp Customer Id: '
			+ element.customerId
			+ '&nbsp &nbsp &nbsp Product Id: '
			+ element.pid
			+ '</h3>'
			+ '<div class="box-tools pull-right"><span class="label label-success">Resolved</span></div>'
			+ '<div class="box-body">'
			+ '<div id="feedbackDetailText">'
			+ '<p>' + feedbackString + '</p></div>' + '</div>' + '</div>';
	return innerHTML;
}

var getFeedbackString = function(element) {
	$.ajax({
		url : 'getComparativePrices',
		data : {
			'product_id' : element.pid
		},
		success : function(data) {
			var prices = data;
			var feedbackString = '';
			var addLine = false;
			if (element.higherThanAmazon) {
				feedbackString += 'Price higher than Amazon - Our Price: '
						+ prices.ourPrices + ' Amazon Price: '
						+ prices.amazonPrices;
				addLine = true;
			}
			if (element.higherThanEbay) {
				if (addLine) {
					feedbackString += '<br\>';
				}
				feedbackString += 'Price higher than Ebay - Our Price: '
						+ prices.ourPrices + ' Ebay Price: '
						+ prices.ebayPrices;
				addLine = true;
			}
			if (element.higherThanOthers) {
				if (addLine) {
					feedbackString += '<br\>'
				}
				feedbackString += 'Price higher than '
						+ element.higherThanOthers + ' - Our Price: '
						+ prices.ourPrices + ' ' + element.higherThanOthers
						+ ' Price: N/A';
			}
			addFeedbackBox(element, feedbackString);
		}
	});
}

var markResolve = function(feedbackId) {
	$.ajax({
		url : 'resolveFeedback',
		data : {
			'feedback_id' : feedbackId
		},
		success : function(data) {
			location.reload();
		}
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

					document.getElementById('UPSuggestionSectionId').innerHTML = '<p><hr></p><p><h4 class="modal-title">Analysis and Suggestions:</h4><h4 class="modal-title">'
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
	$('#review-price-table1').dataTable().fnDestroy();
	$('#review-price-table1').DataTable({
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
		"order": [[ 2, "asc" ]]
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
		suggestionText += '<font color="red"><li type="disc">Amazon and Ebay are selling at lower price than you.<li></font>'
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
		$('#price-update-modal').modal('hide');
		location.reload();
	});
}

