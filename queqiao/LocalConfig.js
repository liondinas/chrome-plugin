/**
 * Created by rawind on 16-7-24.
 */



var LocalConfig = {};

LocalConfig.data = new Array();

LocalConfig.size = 0;

LocalConfig.proxyUrl = "SOCKS5 127.0.0.1:1080";


LocalConfig.init = function () {
    if(this.data.length<=0){
        this.addUrl(".google.com");
        this.addUrl(".google.com.hk");
        this.addUrl(".chrome.com");
        this.addUrl(".facebook.com");
        chrome.storage.sync.get(["queqiaoVal"],function(urlArray){
            Logger.info("init LocalConfig from local" + urlArray);
            this.data = urlArray;
        });

    }else{
        Logger.info("init LocalConfig with nothing"+ this.data.length);
    }
};

/**
 * add url for proxy
 * @param url
 */
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
    chrome.storage.sync.set({'queqiaoVal': LocalConfig.data}, function() {
        // Notify that we saved.
        Logger.info("queqiaoVal addUrl")
    });
};


/**
 * remove url for proxy
 * @param index the index of the url in array
 */
LocalConfig.removeUrlByIndex = function remove(index) {
    if(index>=0){
        this.data.splice(index, 1);
        this.size--;
    }

    chrome.storage.sync.set({'queqiaoVal': LocalConfig.data}, function() {
        // Notify that we saved.
        Logger.info("queqiaoVal remove url")
    });

};


/**
 * pacScript data
 * @returns {string}
 */
LocalConfig.pacScript = function getPacScript() {

    if(this.data.size<=0){
        chrome.storage.sync.get(["queqiaoVal"],function(urlArray){
            Logger.info("get from local" + urlArray)
            this.data = urlArray;
        });
    }

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


/**
 * go to the url
 * @param url
 */
LocalConfig.goPage = function goPage(url) {
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


/**
 * get the domain from the url
 * @param url
 * @returns {string} eg. https://www.baidu.com
 */
LocalConfig.getDomain = function getDomainFromUrl(url){
    var host = "null";
    if(typeof url == "undefined" || null == url)
        url = window.location.href;
    var regex = /.*\:\/\/([^\/]*).*/;
    var match = url.match(regex);
    if(typeof match != "undefined" && null != match)
        host = match[1];
    return host;
};


/**
 * set the plugin icon
 * @param status
 */
LocalConfig.setIcon = function setIcon(status) {

        var iconOff = {
            path: "images/off.png",
        };


        var iconOn = {
            path: "images/off.png",
        };

        if(status==='on'){
            chrome.browserAction.setIcon(iconOn);
        }else{
            chrome.browserAction.setIcon(iconOff);
        }
};

