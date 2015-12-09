
$(document).ready(function(){



	hideAll();
	
	$('input[type=radio][name=contactRadio]').change(function() {
        console.info(this.value);
        removeAllClass(".contactManagerButton");
        if(this.value==0)
        	$(".contactManagerButton").addClass("btn-info");
        if(this.value==1){
        	$(".contactManagerButton").addClass("btn-success");
        	$("#timeSetModal").modal("show");
        }
        if(this.value==-1)
        	$(".contactManagerButton").addClass("btn-danger");
    });

});


function contactManager(eventId) {
	//console.info("contactManger");
	hideAll(eventId);
	var contactManagerDiv  = "#contactManagerDiv" + eventId;
	$(contactManagerDiv).css("display", "inline-block");
}

function meeting(eventId) {
		hideAll(eventId);
		var meetingDivId = "#meetingDiv"+eventId ;
		$(meetingDivId).css("display", "inline-block");

}

function assignTask(eventId) {
		hideAll(eventId);
		var assignTaskDivId = "#assignTaskDiv"+eventId ;

		$(assignTaskDivId).css("display", "inline-block");

}

function eventStatus(eventId) {
	
		hideAll(eventId);
		var eventStatusDivId = "#eventStatusDiv"+eventId ;
		$(eventStatusDivId).css("display", "inline-block");

}

function eventOuput(eventId) {
	
	hideAll(eventId);
	var eventOutputDivId = "#eventOutputDiv"+eventId ;
	$(eventOutputDivId).css("display", "inline-block");

}

function hideAll(eventId){
	var contactManagerDiv  = "#contactManagerDiv" + eventId;
	var meetingDivId = "#meetingDiv"+eventId ;
	var assignTaskDivId = "#assignTaskDiv"+eventId ;
	var eventStatusDivId = "#eventStatusDiv"+eventId ;
	var eventOutputDivId = "#eventOutputDiv"+eventId ;
	
	$(contactManagerDiv).css("display", "none");
	$(meetingDivId).css("display", "none");
	$(assignTaskDivId).css("display", "none");
	$(eventStatusDivId).css("display", "none");
	$(eventOutputDivId).css("display", "none");
	
}

function removeAllClass(idName){
	$(idName).removeClass("btn-info");
	$(idName).removeClass("btn-danger");
	$(idName).removeClass("btn-success");
	$(idName).removeClass("btn-default");
}