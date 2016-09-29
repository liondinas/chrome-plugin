/**
 * Created by rawind on 16-7-24.
 */



var QueqiaoUser = {};

QueqiaoUser.status = 0;


QueqiaoUser.userName = '';

QueqiaoUser.userCookie = '';



QueqiaoUser.init = function () {

};


QueqiaoUser.setCookie = function(userCookie){
	this.userCookie = userCookie;
	this.status = 1;
};



QueqiaoUser.isLogin = function(){
	return this.status == 1;
};


QueqiaoUser.getCookies = function (domain, name, callback) {
    chrome.cookies.get({"url": domain, "name": name}, function(userCookie) {
        if(callback) {   
        	if(userCookie){
            	QueqiaoUser.setCookie(userCookie.value);
        	}
            callback(userCookie);
        }
    });
}


QueqiaoUser.checkLogin = function(){
    QueqiaoUser.getCookies("http://proxy.xiaochengzi.vip", "user", function(userCookie) {
        if(userCookie){
           	QueqiaoUser.setCookie(userCookie.value);
        }
    });
}