
var AddNewUrl = (function(url){
	var i = 0;
	return function(url){
		if(i == 0){
			ProxyPlugin.init();
			Logger.info("init i=" + i);
		}
		Logger.info("addNewUrl=" + url);
		LocalConfig.addUrl(url);
		Settings.setValue('pacScriptData', LocalConfig.pacScript());
		//function (proxyMode, proxyString, proxyExceptions, proxyConfigUrl)
		ProxyPlugin.setProxy("auto", "socks5://127.0.0.1:1080", "", ProxyPlugin.memoryPath);
		return ++i;
	}
})();

function refreshProxy(){
	Settings.setValue('pacScriptData', LocalConfig.pacScript());
	//function (proxyMode, proxyString, proxyExceptions, proxyConfigUrl)
	ProxyPlugin.setProxy("auto", "socks5://127.0.0.1:1080", "", ProxyPlugin.memoryPath);
}

function checkForValidUrl(tabId, changeInfo, tab) {
	var requestUrl = LocalConfig.getDomain(tab.url).toLowerCase();
	articleData.type = "GETURL";
	articleData.url = requestUrl;
	//Logger.debug("curlUrl=" + requestUrl);
};

/**
 * new tab when url update
 */
//chrome.tabs.onUpdated.addListener(checkForValidUrl);



var articleData = {};
articleData.type = "ERROR";
articleData.url = "www.chewen.com"
chrome.runtime.onMessage.addListener(function(request, sender, sendResponse){
	if(request.type == "GETURL"){
		articleData = request;
		articleData.url = LocalConfig.getDomain(request.url);
		//Logger.info("request type right url= " +  articleData.url);
	}else{
		articleData.type = "ERROR";
		articleData.url = "www.chewen.com";
		//Logger.warning("request type is error for " +  request.type);
	}
	/*Logger.info("request.type=" + request.type);
	sendResponse({farewell: "cnblog"});
	/!*Logger.warn(sender.tab ?
	"from a content script:" + sender.tab.url :
		"from the extension");*!/
	if(request.type === "hello"){
		sendResponse({farewell: "goodbye"});
	}

	Logger.info(request.type==="cnblog-article-information");
	if(request.type==="cnblog-article-information"){
		Logger.info("send response for " + request.type)
		sendResponse({farewell: "cnblog"});
		return;
	}


	articleData = request;
	articleData.firstAccess = "获取中...";
	if(!articleData.error){
		$.ajax({
			url: "http://localhost/first_access.php",
			cache: false,
			type: "POST",
			data: JSON.stringify({url:articleData.url}),
			dataType: "json"
		}).done(function(msg) {
			if(msg.error){
				articleData.firstAccess = msg.error;
			} else {
				articleData.firstAccess = msg.firstAccess;
			}
		}).fail(function(jqXHR, textStatus) {
			articleData.firstAccess = textStatus;
		});
	}*/
});


/*chrome.browserAction.onClicked.addListener(function(tab) {
	// 扩展向内容脚本发送消息
	chrome.tabs.sendMessage(tab.id,{
		type: "hello to content script!"
	}, function(response) {
		//console.log(response.farewell);
	});
});*/


/*
chrome.tabs.query({active: true, currentWindow: true}, function(tabs) {
	chrome.tabs.sendMessage(tabs[0].id, testMsg, function(response) {
		Logger.info("send type=hello response:" + response);
	});
});
*/




chrome.runtime.getPlatformInfo(function(platformInfo){
	Logger.log("PlatformOs= " + platformInfo.os + ",PlatformArch="+platformInfo.arch, Logger.Types.info);
});






