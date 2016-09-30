/**
 * Created by rawind on 16-7-23.
 * this code just for test, not used int app
 */





var articleData = {};
articleData.type = "ERROR";
articleData.url = "www.chewen.com"
chrome.runtime.onMessage.addListener(function(request, sender, sendResponse){
    if(request.type == "GETURL"){
        articleData = request;
        articleData.url = LocalConfig.getDomainFromUrl(request.url);
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


/*if(url.indexOf("popup.html")===-1){
 chrome.tabs.create({url:"popup.html"});
 }*/
/*chrome.tabs.executeScript(null, {file: 'content.js'}, function() {
 console.log('Success');
 });*/