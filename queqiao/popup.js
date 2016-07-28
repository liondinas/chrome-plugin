
function closePopup() {
	window.close();
}


function getCurrentTabUrl(callback) {
	var queryInfo = {
		active: true,
		currentWindow: true
	};

	chrome.tabs.query(queryInfo, function(tabs) {
		var tab = tabs[0];
		var url = tab.url;
		callback(url);
	});
}


function renderURL(statusText) {
	$("#curUrl").attr("value", statusText);
	//document.getElementById('status').textContent = statusText;
}


document.addEventListener('DOMContentLoaded', function () {
	/*var data = chrome.extension.getBackgroundPage().articleData;
	if(data.error){
		$("#message").text(data.error);
		$("#content").hide();
	}else{
		$("#message").hide();
		$("#content-title").text(data.title);
		$("#content-author").text(data.author);
		$("#content-date").text(data.postDate);
		$("#content-first-access").text(data.firstAccess);
	}*/

	getCurrentTabUrl(function(url) {
		renderURL(url);
	});

	var bg = chrome.extension.getBackgroundPage();
	var data = bg.articleData;
	//$("#curUrl").attr("value", data.url);

	document.querySelector('#addURLBTN').addEventListener('click', function(){
		bg.AddNewUrl($("#curUrl").attr("value"));
		closePopup();
	});
});


/*

$(document).ready(function(){
	$("#addURLBTN").click(showAddRule);
});*/


