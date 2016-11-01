<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>



<table cellspacing="0"  class="table">
  <tr>
    <td><a href="javascript:void(0);" id="pageinfo_first0"  pageNo="1" >首页</a></td>
    <td><a href="javascript:void(0);" id="pageinfo_first"   pageNo="${pageInfo.firstPageNo}">上一页</a></td>
    <td>第
      <input type="text" size="5" style="width:25px;" id="pageinfo_pageno"  value="${pageInfo.pageNo}"   />
      页</td>
    <td><a href="javascript:void(0);" id="pageinfo_next"   pageNo="${pageInfo.nextPageNo}">下一页</a></td>
    <td><a href="javascript:void(0);" id="pageinfo_last"   pageNo="${pageInfo.lastPageNo}">末页</a></td>
    <td>共计<span style="color: #FF0000;font-weight: bold;">${pageInfo.totalRecord}</span>条记录/共计<span  style="color: #FF0000;font-weight: bold;">${pageInfo.lastPageNo}</span>页</td>
    <td> 每页
      <select style="width:50px;"  id="pageinf_limit">
        <option value="10">10</option>
		<option value="20">20</option>
		<option value="50">50</option>
      </select>
      条 </td>
  </tr>
</table>
<script>
//var url = '#unscaped(${url})';
var url = '?a='+Math.random();
jQuery(document).ready(function(){
	$('#pageinf_limit').val('${pageInfo.limit}');
	$('#pageinfo_first0,#pageinfo_first,#pageinfo_next,#pageinfo_last').click(function(){
		var pageNo = $(this).attr('pageNo');
		var limit = $('#pageinf_limit').val();
		var URL = url + '&pageNo='+pageNo +'&limit='+limit;
		//alert(URL);
		window.location.href = URL;
	});
	
	$('#pageinfo_pageno').change(function(){
		var pageNo = $(this).val();
		var limit = $('#pageinf_limit').val();
		var URL = url + '&pageNo='+pageNo +'&limit='+limit;
		//alert(URL);
		window.location.href = URL;
	});
	
	//FIXED for IE
	$('#pageinfo_pageno').bind('keypress',function(e){
		if(e.keyCode == 13){
			var pageNo = $(this).val();
    		var limit = $('#pageinf_limit').val();
    		var URL = url + '&pageNo='+pageNo +'&limit='+limit;
    		//alert(URL);
    		window.location.href = URL;
		}
	});
	
	
	
	$('#pageinf_limit').change(function(){
		var pageNo = $('#pageinfo_pageno').val();
		var limit = $('#pageinf_limit').val();
		var URL = url + '&pageNo='+pageNo +'&limit='+limit;
		//alert(URL);
		window.location.href = URL;
	});
});
</script>
<br/>
			
