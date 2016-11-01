/**
 * Created by rawind on 16-7-24.
 */



var LocalConfig = {};

LocalConfig.data = new Array();

LocalConfig.size = 0;

LocalConfig.proxyUrl = "SOCKS5 127.0.0.1:1080";
//LocalConfig.proxyUrl = "PROXY 101.200.121.195:3118";


LocalConfig.init = function () {

    var objJSON = JSON.parse(localStorage.getItem("queqiaoJSON"));
    if(objJSON!=null){
        $.each(objJSON,function(name,value) {
            LocalConfig.addUrl(value);
        });
        Logger.info("init LocalConfig with mem, lenth="+ this.data.length);
    }

    if(this.data.length<=0){
        this.addUrl(".google.com");
        this.addUrl(".google.com.hk");
        this.addUrl(".chrome.com");
        this.addUrl(".facebook.com");
        this.addUrl(".twitter.com");
        this.addUrl(".twimg.com");
        this.addUrl(".youtube.com");
        this.addUrl(".ytimg.com");
        this.addUrl(".ggpht.com");
        this.addUrl(".wsj.com");
        this.addUrl(".wsj.net");
        this.addUrl(".googlevideo.com");
        Logger.info("init first"+ this.data.length);
    }

};

/**
 * add url for proxy
 * @param url
 */
LocalConfig.addUrl = function addValue(url) {
    if(url.indexOf(":")>=0){
        //url = this.getDomain(url);
        url = this.getUrlDomain(url);
    }


    if(url.indexOf('xiaochengzi.vip')>0){
        return ;
    }
   
    url = url.replace(/www/, "");
    for (var i = 0; i < this.data.length; i++) {
        if(url === this.data[i]){
            return;
        }
    }
    this.data.push(url);
    this.size++;
    localStorage.setItem("queqiaoJSON", this.getDataJSONStr());
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

    localStorage.setItem("queqiaoJSON", this.getDataJSONStr());

};


/**
 * pacScript data
 * @returns {string}
 */
LocalConfig.pacScript = function getPacScript() {

    if(this.data.size<=0){
        chrome.storage.sync.get(["queqiaoVal"],function(urlArray){
            Logger.info("get from local" + urlArray);
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
    Logger.info("pacScriptData=" + pacScriptData);
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
LocalConfig.getDomainFromUrl = function getDomainFromUrl(url){
    var host = "null";
    if(typeof url == "undefined" || null == url)
        url = window.location.href;
    var regex = /.*\:\/\/([^\/]*).*/;
    var match = url.match(regex);
    if(typeof match != "undefined" && null != match)
        host = match[1];
    return host;
};



LocalConfig.getUrlDomain = function getUrlDomain(url){
　　　　var arrUrl = url.split("//");
    if(arrUrl.length>1){
        var start = arrUrl[1].indexOf("/");
        var relUrl = arrUrl[1].substring(0, start);
        relUrl = relUrl.replace(/www/, "");  
        return relUrl;
    }

　　　　return "";
};



LocalConfig.getDataJSONStr = function () {
    var dataStr = {};
    for(var i=0; i<this.data.length; i++){
        dataStr[i] = this.data[i];
    }
    return JSON.stringify(dataStr);
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

