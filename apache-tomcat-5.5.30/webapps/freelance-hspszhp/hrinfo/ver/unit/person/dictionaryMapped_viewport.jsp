<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<HTML>
 <HEAD>
  <%@ include file="/include/jsp_headers.jsp"%>
 </HEAD>
<BODY>
<div id='rightPage'>
    <ul class="c-nav-tab clearfix" id="c-nav-tab-ul">
      <li><a id="db" onclick="$('#person-map-table').load('goDicMappedViewport.do?method=goDicMappedViewport&dbflag=0&dicTypeMappingOid=${dicTypeMappingOid}&dicTypeCode=${dicTypeCode}');">待映射</a></li>
      <li><a id="yb" onclick="$('#person-map-table').load('goDicMappedViewport.do?method=goDicMappedViewport&dbflag=1&dicTypeMappingOid=${dicTypeMappingOid}&dicTypeCode=${dicTypeCode}');">已映射</a></li>
    </ul>
    <div class='person-map-table' id="person-map-table"></div>
</div>
</BODY>
<script type="text/javascript">
$('.c-nav-tab a').click(function(e) {
	$(this).parent().siblings().find('a').removeClass('c-navtab-selected');
	$(this).addClass('c-navtab-selected');
});
$('#btn-return').click(function(e){
})
$('#db').click();
</script>
</HTML>