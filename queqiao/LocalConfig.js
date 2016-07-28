/**
 * Created by rawind on 16-7-24.
 */



var LocalConfig = {};

LocalConfig.data = new Array();

LocalConfig.size = 0;

LocalConfig.proxyUrl = "SOCKS5 127.0.0.1:1080";

LocalConfig.addUrl = function addValue(url) {
    if(url.indexOf(":")>=0){
        url = this.getDomain(url);
    }
    url.replace("www", "");
    for (var i = 0; i < this.data.length; i++) {
        if(url === this.data[i]){
            return;
        }
    }
    this.data.push(url);
    this.size++;
};

LocalConfig.removeUrlByIndex = function remove(index) {
    if(index>=0){
        this.data.splice(index, 1);
        this.size--;
    }
};

LocalConfig.pacScript = function contact() {

    var pacScriptData = "var FindProxyForURL = function(url, host){";
    var urls = '';
    for (var i = 0; i < this.data.length; i++) {
        urls = urls + "if(dnsDomainIs(host, '"+this.data[i] + "')){"+
        "return '"+ this.proxyUrl + "';}";
    }
    pacScriptData = pacScriptData + urls;

    pacScriptData = pacScriptData + "return 'DIRECT';}";
    return pacScriptData;
};

LocalConfig.goPage = function gotoPage(url) {

    var fulurl = chrome.extension.getURL(url);
    chrome.tabs.getAllInWindow(undefined, function(tabs) {
        for (var i in tabs) {
            tab = tabs[i];
            if (tab.url == fulurl) {
                chrome.tabs.update(tab.id, { selected: true });
                return;
            }
        }
        chrome.tabs.getSelected(null, function(tab) {
            chrome.tabs.create({url: url,index: tab.index + 1});
        });
    });
};



LocalConfig.getDomain = function getDomainFromUrl(url){
    var host = "null";
    if(typeof url == "undefined" || null == url)
        url = window.location.href;
    var regex = /.*\:\/\/([^\/]*).*/;
    var match = url.match(regex);
    if(typeof match != "undefined" && null != match)
        host = match[1];
    return host;
}

