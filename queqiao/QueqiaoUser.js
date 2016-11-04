/**
 * Created by rawind on 16-7-24.
 */



var QueqiaoUser = {};

/**
* 0-not login, 1-lgoin and normal, 2-login and expired
*/
QueqiaoUser.status = 0;


QueqiaoUser.userName = '';

QueqiaoUser.proxyUrl = '';

QueqiaoUser.userCookie = '';

QueqiaoUser.headerValue = '';



QueqiaoUser.init = function () {
	this.getCookiesSync("http://proxy.xiaochengzi.vip", "user", function(userCookie) {
        if(userCookie){
           	QueqiaoUser.setCookie(userCookie.value);           	
        }
    });
};


QueqiaoUser.setCookie = function(userCookie){
	this.userCookie = userCookie;
	this.status = 1;
	Logger.info('setCookie='+this.userCookie);
	Logger.info('PROXY_URL='+LocalConfig.proxyUrl);

	$.ajax({
		  type : "GET",
		  url : 'http://proxy.xiaochengzi.vip/plugin/getProxy',
		  data: {
		  	userCookie : QueqiaoUser.userCookie
		  },
		  dataType : "json",
		  async: true,
		  beforeSend : function(xhr) {  
		    //var cookie = credentials["COOKIE"];
		    /*var cookie = 'user='+ QueqiaoUser.userCookie;
		    Logger.info( "adding cookie: "+ cookie );          
		    xhr.setRequestHeader('Cookie', cookie);*/
		  },
		  success : function(retJson){
		  	if (retJson['code'] != 0) {
		  		Logger.info('getUerName code error='+JSON.stringify(retJson));	
		  	}else{
		  		var data = retJson['data'];
		  		QueqiaoUser.userName = data.userName;
		  		QueqiaoUser.proxyUrl = data.url;
		  		QueqiaoUser.status = 1;
		  		QueqiaoUser.headerValue = data.headerValue;
		  		LocalConfig.proxyUrl = data.url;		  	
		  		Logger.info('getUerName from net='+data.userName + ',proxyUrl from net='+data.url);	

				Settings.setValue('pacScriptData', LocalConfig.pacScript());
				//function (proxyMode, proxyString, proxyExceptions, proxyConfigUrl)
				ProxyPlugin.setProxy("auto", LocalConfig.proxyUrl, "", ProxyPlugin.memoryPath);

		  	}	
		  	
		  },
		  error : function(xhr, ajaxOptions, thrownError) {
		    Logger.info('getUserName net error ='+thrownError);
		  }  	
	});

};



QueqiaoUser.isLogin = function(){
	return this.status == 1;
};


QueqiaoUser.getCookiesSync = function (domain, name, callback) {
    chrome.cookies.get({"url": domain, "name": name}, function(userCookie) {
        if(callback) {           	
            callback(userCookie);
        }
    });
}

