﻿/*var postInfo = $("div.postDesc");
if(postInfo.length!=1){
	chrome.runtime.sendMessage({type:"cnblog-article-information", error:"获取文章信息失败."});
}
else{
	var msg = {
		type: "cnblog-article-information",
		title : $("#cb_post_title_url").text(),
		postDate : postInfo.find("#post-date").text(),
		author : postInfo.find("a").first().text(),
		url: document.URL
	};
	chrome.runtime.sendMessage(msg, function(response) {
		Logger.info("send cnblog-article-information response:" + response.farewell);});
}*/



var postInfo = $("div.s_form");
if(postInfo.length!=1){
	chrome.runtime.sendMessage({type:"cnblog-article-information", error:"获取文章信息失败."});
}else{
	var msg = {
		type: "BAIDU",
		title : $("#oq").text(),	
		url: document.URL
	};
	chrome.runtime.sendMessage(msg, function(response) {
		Logger.info("send cnblog-article-information response:" + response.farewell);});
}