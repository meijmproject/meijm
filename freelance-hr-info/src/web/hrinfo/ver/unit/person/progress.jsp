<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>
<%@page import="com.yh.hr.res.dictionary.DicConstants"%>
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<title>人员导入页面</title>
<script type="text/javascript">
var xmlhttp;
function loadXMLDoc()
{				
	var url = "getCookie.do?method=getCookie";
	xmlhttp=null;
	if (window.XMLHttpRequest)
	  {// code for IE7, Firefox, Opera, etc.
	  xmlhttp=new XMLHttpRequest();
	  }
	else if (window.ActiveXObject)
	  {// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }
	if (xmlhttp!=null)
	  {
	  xmlhttp.onreadystatechange=state_Change;
	  xmlhttp.open("GET",url,true);
	  xmlhttp.send(null);
	  }
	else
	  {
	  alert("Your browser does not support XMLHTTP.");
	  }
}

function state_Change()
{
if (xmlhttp.readyState==4)
  {// 4 = "loaded"
  if (xmlhttp.status==200)
    {// 200 = "OK"
    document.getElementById('A1').innerHTML=xmlhttp.status;
    document.getElementById('A2').innerHTML=xmlhttp.statusText;
    document.getElementById('A3').innerHTML=xmlhttp.responseText;
    }
  else
    {
    alert("Problem retrieving XML data:" + xmlhttp.statusText);
    }
  }
}
</script>
</head>
<body>
<h2>Using the HttpRequest Object</h2>

<p><b>Status:</b>
<span id="A1"></span>
</p>

<p><b>Status text:</b>
<span id="A2"></span>
</p>

<p><b>Response:</b>
<br /><span id="A3"></span>
</p>

<button onclick="loadXMLDoc()">Get XML</button>

</body>
</html>