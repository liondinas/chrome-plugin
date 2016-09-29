/**
 * Created by rawind on 16-7-24.
 */



var QueqiaoUser = {};

QueqiaoUser.status = 0;


QueqiaoUser.userName = '';

QueqiaoUser.userCookie = '';



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
		  type : "POST",
		  url : 'http://proxy.xiaochengzi.vip/plugin/getproxy',
		  data: {
		  	userCookie : QueqiaoUser.userCookie
		  },
		  dataType : "json",
		  async: false,
		  beforeSend : function(xhr) {  
		    /*var cookie = credentials["COOKIE"];//此处设置cookie
		    console.info( "adding cookie: "+ cookie );          
		    xhr.setRequestHeader('Cookie', cookie);*/
		  },
		  success : function(data, textStatus, xmLHttpRequest){
		  },
		  error : function(xhr, ajaxOptions, thrownError) {
		    credentials = null;
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

