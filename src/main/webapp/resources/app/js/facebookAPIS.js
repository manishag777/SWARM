$(document).ready(function() {
	var initPage = function() {
		FB.init({
			appId : '1516637011964285',
			xfbml : true,
			version : 'v2.5'
		});
	}
	initPage();
});

// Only works after `FB.init` is called
function myFacebookLogin() {
	FB
			.login(
					function() {
					},
					{
						scope : 'publish_actions,manage_pages,publish_pages,user_location,user_about_me,email'
					});
}

var displayPosts = function() {
	console.log('Displaying posts.... ');
	var id = 1;
	FB.api('/me', function(response) {
		console.info(response);
		id = response.id;
		var url = '/' + id + '/accounts';
		FB.api(url, function(response1) {
			var page_access_token = response1.data[0].access_token;
			FB.api('1528462757447206/posts', function(response) {
				console.info(response);
				for (var i = 0; i < response.data.length; i++) {
					addPostBox(response.data[i], i + 1);
				}
			});
		});

	});

}

var addPostBox = function(element, count) {
	var parent = document.getElementById("postsList");
	var box = document.createElement('div');
	box.innerHTML = getPostBoxInnerHTML(element, count);
	parent.appendChild(box);
};

var getPostBoxInnerHTML = function(element, count) {
	var likeDetailsButtonId = '"likeDetailsButton' + element.id + '"';
	var innerHTML = '<div class="box box-danger"> <div class="box-header"> <h3 class="box-title">Post Id: '
			+ element.id
			+ '&nbsp &nbsp &nbsp Event Id: '
			+ count
			+ '&nbsp &nbsp &nbsp Created Time: '
			+ element.created_time
			+ '</h3>'
			+ '<div class="box-body">'
			+ '<div id="postDetailText">'
			+ '<p>'
			+ element.message
			+ '</p></div>'
			+ '</div>'
			+ '<div class="box-footer">'
			+ '<button type="button" class="btn btn-primary" data-toggle="modal" id='
			+ likeDetailsButtonId
			+ ' data-target="#like-analysis-modal" onclick="likeAnalysis(\''
			+ element.id + '\')">Like Analysis</button>' + '</div>' + '</div>';
	return innerHTML;
}

var likeAnalysis = function(objectId) {
	console.info("getting like analysis");
	console.info(objectId);
	var url = '/' + objectId + '/likes';
	FB.api(url, function(response) {
		if (response && !response.error) {
			console.info(response);
			populateLikeAnalysisModal(response);
		}
	});
}

var populateLikeAnalysisModal = function(element) {
	document.getElementById('likesCountId').value = element.data.length;
	populateLikesUserDetailsTable(element);
}

var populateLikesUserDetailsTable = function(element) {
	console.info("populating table........");
	console.info(element);
	var userDetails = [];
	for (var i = 0; i < element.data.length; i++) {
		var details = getUserDetails(element.data[i].id);
		userDetails.push(details);
	}
	$('#like-users-table').dataTable().fnDestroy();
	$('#like-users-table').DataTable({
		"iDisplayLength" : 3,
		data : userDetails,
		columns : [ {
			data : 'details',

		} ],
		filter : false,
		"bLengthChange" : false,
		destroy : true
	});
}

var getUserDetails = function(userid) {
	console.info('getting user Details...')
	console.info(userid);
	var url = '/' + userid + '?fields=id,name,email';
	FB.api(url, function(response) {
		if (response && !response.error) {
			console.info('after get details');
			console.info(response);
			return response;
		}
	});
}

var post = function() {
	console.info("posting Hello world to facebook");
	FB.api('/me/feed', 'post', {
		message : 'Hello, world!'
	});
}

var postAPI = function() {
	console.log('Welcome!  posting on swarms page ');
	var id = 1;
	FB.api('/me', function(response) {
		id = response.id;
		var url = '/' + id + '/accounts';
		FB.api(url, function(response1) {
			// posting on page
			var page_access_token = response1.data[0].access_token;
			FB.api('1528462757447206/feed', 'post', {
				access_token : page_access_token,
				message : 'Hello Fans!'
			}, function(response) {
				console.info(response);
			});
		});

	});
}
