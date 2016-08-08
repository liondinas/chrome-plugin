/**
 * Created by rawind on 16-7-23.
 */



//https://developer.chrome.com/extensions/webRequest




chrome.webRequest.onBeforeRequest.addListener (
    function(details) {
        var iconOff = {
            path: "images/off.png"
        };

        var iconOn = {
            path: "images/on.png"
        };
        var url = details.url;
        //Logger.debug("requestURL= " + url);
        /*if(url.indexOf("www.baidu.com")!=-1){
         return {redirectUrl:"http://www.chewen.com"}
         }*/


        //TODO checkthe url is in array, then show diffrent icon

        chrome.browserAction.setIcon(iconOn);
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
            LocalConfig.setIcon("off");
        }else{
            LocalConfig.setIcon("on");
        }

    },
    {urls:["<all_urls>"]}//监听所有的url,你也可以通过*来匹配。
);

chrome.webRequest.onErrorOccurred.addListener (
    function(info) {

        var url = info.url;
        var error = info.error;
        if(error.indexOf("ERR_CONNECTION_RESET")>=0){
            Logger.info("CONNECTION_RESET addUrl=" + url);
            LocalConfig.addUrl(url);
        }else if(error.indexOf("ERR_CONNECTION_TIMED_OUT")>=0){
            Logger.info("CONNECTION_TIMED_OUT addUrl=" + url);
            LocalConfig.addUrl(url);
        }else if(error.indexOf("ERR_NAME_RESOLUTION_FAILED")>=0){
            Logger.info("NAME_RESOLUTION_FAILED addUrl=" + url);
            LocalConfig.addUrl(url);
        }else if(url.indexOf("popup")>=0){
            Logger.debug("popup error" + error);
        }else{
            Logger.log("url=" + url + " is error, errorInfo=" + error, Logger.Types.error);
        }

        LocalConfig.setIcon("off");

    },
    {urls: ["<all_urls>"]}
);