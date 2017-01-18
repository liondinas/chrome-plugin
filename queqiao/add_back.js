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
        var trHTML = '<tr><td>'+i+'</td><td>'+url+'</td><td><button type="submit" class="btn btn-danger delBtn" key="'+i+'">删除</button></td></tr>';
        $("#urlTable").append(trHTML);
    }
}



function addSubmitBtn() {

    var bg = chrome.extension.getBackgroundPage();
    var data = bg.articleData;

    bg.AddNewUrl($("#focusedInput").val());

    extension.refreshProxy();

    window.location.href='./options.html';

}



function addReturnBtn() {   
    window.location.href='./options.html';
}

$(document).ready(function(){

    $("#addSubmitBtn").click(addSubmitBtn);

    $("#addReturnBtn").click(addReturnBtn);

});