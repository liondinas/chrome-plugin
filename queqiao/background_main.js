/**
 * add url for proxy,called by popup.js
 */
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
		
		ProxyPlugin.setProxy("auto", LocalConfig.proxyUrl, "", ProxyPlugin.memoryPath);
		return ++i;
	}
})();



var AddNewAdUrl = (function(url){
	var i = 0;
	return function(url){
		if(i == 0){
			Logger.info("init i=" + i);
		}
		AdLocalConfig.addUrl(url);
		return ++i;
	}
})();



/**
 * refresh proxy
 */
function refreshProxy(){
	Settings.setValue('pacScriptData', LocalConfig.pacScript());
	//function (proxyMode, proxyString, proxyExceptions, proxyConfigUrl)
	ProxyPlugin.setProxy("auto", LocalConfig.proxyUrl, "", ProxyPlugin.memoryPath);
}

/**
 * get the system info
 */
chrome.runtime.getPlatformInfo(function(platformInfo){
	Logger.log("PlatformOs= " + platformInfo.os + ",PlatformArch="+platformInfo.arch, Logger.Types.info);
});

var InitComplete = false;


function init() {

	ProxyPlugin.init();


	LocalConfig.init();

	AdLocalConfig.init();

    QueqiaoUser.init();

	Settings.setValue('pacScriptData', LocalConfig.pacScript());

	//ProxyPlugin.setProxy("auto", "socks5://127.0.0.1:1080", "", ProxyPlugin.memoryPath);

	ProxyPlugin.setProxyCallback = function () {
		InitComplete = true;
		Logger.log("Init ProxyPlugin complete ", Logger.Types.info);
	};

	LocalConfig.setIcon('off');
}


$(document).ready(function(){
	init();
});




