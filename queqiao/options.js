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
        
    QueqiaoUser.getCookiesSync("http://proxy.xiaochengzi.vip", "user", function(userCookie) {

        var status = 0;
        if(userCookie){
            // bug fix for has wrong userCookie
            Logger.info('userCookie='+userCookie.value);
            $.ajax({
                  type : "GET",
                  url : 'http://proxy.xiaochengzi.vip/plugin/getProxy',
                  data: {
                    userCookie : userCookie
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
                        showLoginStep(status);
                    }else{
                      Logger.info('getUerName success, returnJson='+JSON.stringify(retJson));  
                      var data = retJson['data'];
                      extension.QueqiaoUser.userName = data.userName;
                      extension.QueqiaoUser.proxyUrl = data.url;
                      extension.QueqiaoUser.status = 1;                    
                      status = 1;
                      extension.QueqiaoUser.setCookie(userCookie);
                      showLoginStep(status);
                    }
                  },
                  error : function(xhr, ajaxOptions, thrownError) {
                    Logger.info('getUserName net error ='+thrownError);
                    showLoginStep(status);
                  }

            });            
            
        }else{
            Logger.info('no userCookie');    
            showLoginStep(status);                                  
        }

    });
}


function showLoginStep(status){

        if(parseInt(status) == 1){
                  var currUser = extension.QueqiaoUser;
            Logger.info("QueqiaoUser.userName="+ currUser.userName);
            $("#userName").html('<i class="icon-user"></i>'+currUser.userName+'<i class="caret"></i>');
        }else{
            Logger.info("QueqiaoUser.userName is null");
            $("#userName").html('<i class="icon-user"></i>请登录<i class="caret"></i>');
        }  
}



function refreshBtnClick() {
    // body...
    Logger.info("refreshBtnClick");
    $.ajax({
          type : "GET",
          url : 'http://proxy.xiaochengzi.vip/plugin/getProxy',
          data: {
            userCookie : extension.QueqiaoUser.userCookie
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
                extension.QueqiaoUser.userName = data.userName;
                extension.QueqiaoUser.proxyUrl = data.url;
                extension.QueqiaoUser.status = 1;
                extension.LocalConfig.proxyUrl = data.url;            
                Logger.info('getUerName from net='+data.userName + ',proxyUrl from net='+data.url); 

                extension.Settings.setValue('pacScriptData', extension.LocalConfig.pacScript());
                //function (proxyMode, proxyString, proxyExceptions, proxyConfigUrl)
                extension.ProxyPlugin.setProxy("auto", extension.LocalConfig.proxyUrl, "", ProxyPlugin.memoryPath);

            }   
            
          },
          error : function(xhr, ajaxOptions, thrownError) {
            Logger.info('getUserName net error ='+thrownError);
          }     
    });
}



function goLoginPage(){
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


$(document).ready(function(){
    init();

    $(".delBtn").click(delUrl);

    $("#refreshBtn").click(refreshBtnClick);

});