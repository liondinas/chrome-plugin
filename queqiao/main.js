/**
 * Created by rawind on 16-7-23.
 */


var App = chrome.app.getDetails();

var InitComplete = false;

var extDisabled = false;



function init() {

    ProxyPlugin.init();


    LocalConfig.addUrl(".google.com");
    LocalConfig.addUrl(".chrome.com");
    LocalConfig.addUrl(".facebook.com");

    /*Logger.log("pacScriptData= " + LocalConfig.pacScript(), Logger.Types.info);
    var pacScriptData = "var FindProxyForURL = function(url, host){"+
        "if(dnsDomainIs(host, '.facebook.com')){"+
        "return 'SOCKS5 127.0.0.1:1080';"+
        "}"+
        "return 'DIRECT'"+
        "}";*/

    Settings.setValue('pacScriptData', LocalConfig.pacScript());
    //function (proxyMode, proxyString, proxyExceptions, proxyConfigUrl)
    ProxyPlugin.setProxy("auto", "socks5://127.0.0.1:1080", "", ProxyPlugin.memoryPath);

    ProxyPlugin.setProxyCallback = function () {
        InitComplete = true;
        Logger.log("Init ProxyPlugin complete ", Logger.Types.info);
    };

}



function setProxyIcon() {

    var icon = {
        path: "images/off.png",
    }

    chrome.proxy.settings.get(
        {'incognito': false},
        function(config) {
            if (config["value"]["mode"] == "system") {
                //chrome.browserAction.setIcon(icon);
            } else if (config["value"]["mode"] == "direct") {
                //chrome.browserAction.setIcon(icon);
            } else {
                icon["path"] = "images/on.png";
                //chrome.browserAction.setIcon(icon);
            }
        }
    );
}

function addUrl(curl) {
    Logger.info("add url=" + curl);
    if(curl.length>0){
        Logger.info(curl);
        LocalConfig.addUrl(curl);
        closePopup();
        Settings.setValue('pacScriptData', LocalConfig.pacScript());
        //function (proxyMode, proxyString, proxyExceptions, proxyConfigUrl)
        ProxyPlugin.setProxy("auto", "socks5://127.0.0.1:1080", "", ProxyPlugin.memoryPath);
    }
}




$(document).ready(function(){
    init();
});
