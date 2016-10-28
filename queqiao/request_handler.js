/**
 * Created by rawind on 16-7-23.
 */



//https://developer.chrome.com/extensions/webRequest




chrome.webRequest.onBeforeSendHeaders.addListener(
  function(details) {
   /* for (var i = 0; i < details.requestHeaders.length; ++i) {
      if (details.requestHeaders[i].name === 'User-Agent') {
        details.requestHeaders.splice(i, 1);
        break;
      }
    }*/
    var headers = details.requestHeaders;  
    headers.push({
      name: 'Proxy-Authorization',
      value: 'Basic dGVzdHVzZXI6dGVzdA=='
    }); 
    return {requestHeaders: headers};
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
        //Logger.debug("requestURL= " + url);
        if(url.indexOf("doubleclick.net")!=-1 ||
            url.indexOf("pos.baidu.com")!=-1 ||
            url.indexOf("a1.alicdn.com")!=-1||
            url.indexOf("l.qq.com")!=-1){
            Logger.log("doubleclick.net find for url="+url);
            if(url.indexOf("proxy.xiaochengzi")<=0){
                return {redirectUrl:"http://proxy.xiaochengzi.vip/ads?click="+url}
            }
            
        }


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
        }else{
            Logger.info("url=" + url + " is error, errorInfo=" + error);
        }

        LocalConfig.setIcon("off");

    },
    {urls: ["<all_urls>"]}
);