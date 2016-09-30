/**
 * Created by rawind on 16-7-27.
 */



var extension;

function init(){
    
    checkLogin();


    extension = chrome.extension.getBackgroundPage();
    var urlArray = extension.LocalConfig.data;
    for (var i in urlArray) {
        var url = urlArray[i];        
        var trHTML = '<tr><td>'+i+'</td><td>'+url+'</td><td><button type="submit" class="btn btn-danger delBtn" key="'+i+'">删除</button></td></tr>';
        $("#urlTable").append(trHTML);
    }

    var currUser = extension.QueqiaoUser;
    Logger.info("QueqiaoUser.userName="+ currUser.userName);
    $("#userName").html('<i class="icon-user"></i>'+currUser.userName+'<i class="caret"></i>');
}



function delUrl() {
    var target = $(event.target);
    var index = target.attr("key");
    Logger.info("delUrl="+ index);
    extension.LocalConfig.removeUrlByIndex(index);
    window.location.reload();
    extension.refreshProxy();
}






function checkLogin(){
    //usage:
    QueqiaoUser.getCookiesSync("http://proxy.xiaochengzi.vip", "user", function(userCookie) {
        if(userCookie){
            Logger.info('userCookie='+userCookie.value);
        }else{
            Logger.info('no userCookie');
            var loginUrl = 'http://proxy.xiaochengzi.vip/user/login';
            var optionUrl = chrome.extension.getURL('/options.html');
            chrome.tabs.getAllInWindow(undefined, function(tabs) {
                for (var i in tabs) {
                    tab = tabs[i];
                    Logger.log(tab.url);   
                    if(tab.url == optionUrl){
                        chrome.tabs.update(tab.id, { selected: true, url:loginUrl });
                        //return;
                    }    

                    /*if (tab.url == loginUrl) {
                        chrome.tabs.update(tab.id, { selected: true });
                        return;
                    }*/
                }
                /*chrome.tabs.getSelected(null, function(tab) {
                    chrome.tabs.create({url: '/login.html',index: tab.index + 1});
                });*/
            });            
            
        }
    });
}



$(document).ready(function(){
    init();

    $(".delBtn").click(delUrl);

});