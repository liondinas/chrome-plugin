/**
 * Created by rawind on 16-7-27.
 */



var extension;

function init(){
    extension = chrome.extension.getBackgroundPage();
    var urlArray = extension.LocalConfig.data;
    for (var i in urlArray) {
        var url = urlArray[i];
        Logger.info("url=" + url);
        var trHTML = '<tr><td>'+url+'</td><td><button type="submit" class="btn btn-danger delBtn" key="'+i+'">删除</button></td></tr>';
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


$(document).ready(function(){
    init();

    $(".delBtn").click(delUrl);

});