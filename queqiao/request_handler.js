/**
 * Created by rawind on 16-7-23.
 */



//https://developer.chrome.com/extensions/webRequest


var iconOff = {
    path: "images/off.png",
};

var iconOn = {
    path: "images/on.png",
};

chrome.webRequest.onBeforeRequest.addListener (
    function(details) {

        var url = details.url;
        //Logger.debug("requestURL= " + url);
        /*if(url.indexOf("www.baidu.com")!=-1){
         return {redirectUrl:"http://www.chewen.com"}
         }*/

        return {cancel: false};;
    },
    {urls:["<all_urls>"]}//监听所有的url,你也可以通过*来匹配。
    , ["blocking"]
);


chrome.webRequest.onCompleted.addListener (
    function(page) {
        var url = page.url;
        var httpStatus = page.statusCode;

        if(httpStatus>=399){
            Logger.log("httpStatus="+httpStatus + " for url=" + url, Logger.Types.warning);
            //chrome.browserAction.setIcon(iconOff);
        }else{
            //chrome.browserAction.setIcon(iconOn);
        }

    },
    {urls:["<all_urls>"]}//监听所有的url,你也可以通过*来匹配。
);

chrome.webRequest.onErrorOccurred.addListener (
    function(info) {
        var url = info.url;
        var error = info.error;
        //Logger.log("url=" + url + " is error, errorInfo=" + error, Logger.Types.error)

    },
    {urls: ["<all_urls>"]}
);