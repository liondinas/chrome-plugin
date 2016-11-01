/**
 * Created by rawind on 16-7-27.
 */



var extension;

function init(){
    extension = chrome.extension.getBackgroundPage();
    var urlArray = extension.AdLocalConfig.data;
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
    var url = $("#focusedInput").val();
    bg.AddNewAdUrl(url);

    window.location.href='./addomain.html';

}



function addReturnBtn() {   
    window.location.href='./addomain.html';
}

$(document).ready(function(){

    $("#addSubmitBtn").click(addSubmitBtn);

    $("#addReturnBtn").click(addReturnBtn);

});