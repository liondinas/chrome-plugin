/**
 * Created by rawind on 16-7-23.
 */



//https://developer.chrome.com/extensions/webRequest




chrome.webRequest.onBeforeSendHeaders.addListener(
  function(details) {
    if(QueqiaoUser.headerValue.length>1){
        Logger.info("from net QueqiaoUser.headerValue=" + QueqiaoUser.headerValue);    
        /*for (var i = 0; i < details.requestHeaders.length; ++i) {
              if (details.requestHeaders[i].name === 'User-Agent') {
                details.requestHeaders.splice(i, 1);
                break;
              }
        }*/
        var headers = details.requestHeaders;  
        headers.push({
          name: 'Proxy-Authorization',
          value: QueqiaoUser.headerValue
        }); 
        return {requestHeaders: headers};
    }else{
        Logger.info("QueqiaoUser.headerValue is null");
    }
   
  },
  {urls: ["<all_urls>"]},
  ["blocking", "requestHeaders"]
);




chrome.webRequest.onBeforeRequest.addListener (
    function(details) {
        var iconOff = {
            path: "images/off.png"
        };

        var iconOn = {
            path: "images/on.png"
        };
        var url = details.url;
        
        var isAd = AdLocalConfig.isAdUrl(url);
        if(isAd){
            Logger.warning("adUrl="+url);
            return {redirectUrl:"http://proxy.xiaochengzi.vip/ads?click="+url}
        }


        //TODO checkthe url is in array, then show diffrent icon
        if(QueqiaoUser.status == 1){
            chrome.browserAction.setIcon(iconOn);
        }else{
            chrome.browserAction.setIcon(iconOff);
        }

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
            //LocalConfig.setIcon("off");
        }else{
            //LocalConfig.setIcon("on");
        }

    },
    {urls:["<all_urls>"]}//监听所有的url,你也可以通过*来匹配。
);

chrome.webRequest.onErrorOccurred.addListener (
    function(info) {

        var url = info.url;
        var error = info.error;
        if(error.indexOf("ERR_CONNECTION_RESET")>=0){
            //Logger.info("CONNECTION_RESET addUrl=" + url);
            if(AdLocalConfig.isStaticUrl(url)){
                LocalConfig.addUrl(url);
            }
            
        }else if(error.indexOf("ERR_CONNECTION_TIMED_OUT")>=0){
            //Logger.info("CONNECTION_TIMED_OUT addUrl=" + url);
            if(AdLocalConfig.isStaticUrl(url)){
                LocalConfig.addUrl(url);
            }
        }else if(error.indexOf("ERR_NAME_RESOLUTION_FAILED")>=0){
            //Logger.info("NAME_RESOLUTION_FAILED addUrl=" + url);
            if(AdLocalConfig.isStaticUrl(url)){
                LocalConfig.addUrl(url);
            }
        }else{
            Logger.info("url=" + url + " is error, errorInfo=" + error);
        }

        //LocalConfig.setIcon("off");

    },
    {urls: ["<all_urls>"]}
);