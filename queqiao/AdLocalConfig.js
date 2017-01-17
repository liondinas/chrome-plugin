/**
 * Created by rawind on 16-7-24.
 */



var AdLocalConfig = {};

AdLocalConfig.data = new Array();

AdLocalConfig.size = 0;

AdLocalConfig.proxyUrl = "SOCKS5 127.0.0.1:1080";
//AdLocalConfig.proxyUrl = "PROXY 101.200.121.195:3118";


AdLocalConfig.init = function () {

    var objJSON = JSON.parse(localStorage.getItem("adJSON"));
    if(objJSON!=null){
        $.each(objJSON,function(name,value) {
            AdLocalConfig.addUrl(value);
        });
        Logger.info("init AdLocalConfig with localStorage, length="+ this.data.length);
    }

    if(this.data.length<=0){
        this.addUrl("doubleclick.net");
        this.addUrl("pos.baidu.com");
        this.addUrl("a1.alicdn.com");
        this.addUrl("l.qq.com");        
        Logger.info("init first adDomain"+ this.data.length);
    }

};


AdLocalConfig.isAdUrl = function isAdUrl(url){
    try{
        if(url.indexOf("proxy.xiaochengzi")>=0){
            return false
        }


        if(url.indexOf("chrome-extension://")>=0){
            return false;
        }

       //Logger.info(url + "=" + "http://exmail.qq.com/login".indexOf("l.qq.com"));
        for (var i = 0; i < this.data.length; i++) {
            if(url.indexOf("//"+this.data[i])>-1){    
                return true;
            }           
        }

        return false;
    }catch (e) {

        return false;
    }    
    
}


/**
 * add url for proxy
 * @param url
 */
AdLocalConfig.addUrl = function addValue(url) {
    var start = url.indexOf("//");
    if(start>0){
        url = url.substring(start+2);
        var stop = url.indexOf("/");
        if(stop>0){
            url = url.substring(0, stop);
        }
    }
    

    for (var i = 0; i < this.data.length; i++) {
        if(url === this.data[i]){
            return;
        }
    }
    this.data.push(url);
    this.size++;
    localStorage.setItem("adJSON", this.getDataJSONStr());
};


/**
 * remove url for proxy
 * @param index the index of the url in array
 */
AdLocalConfig.removeUrlByIndex = function remove(index) {
    if(index>=0){
        this.data.splice(index, 1);
        this.size--;
    }

    localStorage.setItem("adJSON", this.getDataJSONStr());

};

/**
 * get the domain from the url
 * @param url
 * @returns {string} eg. https://www.baidu.com
 */
AdLocalConfig.getDomainFromUrl = function getDomainFromUrl(url){
    var host = "null";
    if(typeof url == "undefined" || null == url)
        url = window.location.href;
    var regex = /.*\:\/\/([^\/]*).*/;
    var match = url.match(regex);
    if(typeof match != "undefined" && null != match)
        host = match[1];
    return host;
};



AdLocalConfig.getUrlDomain = function getUrlDomain(url){
　　　　var arrUrl = url.split("//");
    if(arrUrl.length>1){
        var start = arrUrl[1].indexOf("/");
        var relUrl = arrUrl[1].substring(0, start);
        relUrl = relUrl.replace(/www/, "");  
        return relUrl;
    }

　　　　return "";
};


AdLocalConfig.getUrlPathName = function getUrlPathName(url) {
    var pos = url.indexOf("?");
    if(pos >1) {
        return url.substring(0, pos);
    }

    return url;
}



AdLocalConfig.isStaticUrl = function isStaticUrl(url){
    url = this.getUrlPathName(url);
    if(url.endWith('.js') || url.endWith('.css') || url.endWith('.mp4') || url.endWith('.jpg') || url.endWith('.jpeg') || url.endWith('.gif')){
        return true;
    }
    return false;
}


AdLocalConfig.getDataJSONStr = function () {
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
AdLocalConfig.setIcon = function setIcon(status) {

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

