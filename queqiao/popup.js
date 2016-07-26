
function closePopup() {
	window.close();
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

	//$("#curUrl").text('http://wwww.baidu.com');


	var bg = chrome.extension.getBackgroundPage();
	var data = bg.articleData;
	$("#curUrl").attr("value", data.url);
	document.querySelector('#addURLBTN').addEventListener('click', function(){
		bg.AddNewUrl(data.url);
		closePopup();
	});
});


/*

$(document).ready(function(){
	$("#addURLBTN").click(showAddRule);
});*/


