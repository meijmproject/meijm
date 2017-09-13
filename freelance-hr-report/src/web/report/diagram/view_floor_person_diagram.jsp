<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>
<%@ include file="/include/js_css_base_include.jsp"%>

<%@page import="com.yh.hr.res.dictionary.DicConstants"%>

<!--
 * @function    全院人员框架图
 * @page name   view_floor_person_diagram.jsp
 * @author      chenp
 * @created     2017/3/1
 * @version     1.0
-->
<!DOCTYPE html>
<html>
<head lang="en">
<title>全院人员框架图查看页面</title>
<link rel="stylesheet" href="hspszhphtml/css/components/orgTree.css"/>
<script type="text/javascript" src="report/diagram/tree/js/organization.js"></script>
<script type="text/javascript">
    var qyTotalCount ='${qyTotalCount}' ;//全院人员总数
    var wsjs ='${wsjs}';//卫生技术人员数
    var nwsjs ='${nwsjs}';//非卫生技术人员数
    var wsjshl ='${wsjshl}';//护理人员数
    var wsjshlyjks = '${wsjshlyjks}';//医技科室人员数-护理人员
    var wsjshllcks = '${wsjshllcks}';//临床科室人员数-护理人员
    var wsjshllcksmjz = '${wsjshllcksmjz}';//门急诊-临床科室人员数-护理人员
    var wsjshllckszybf = '${wsjshllckszybf}';//住院病房-临床科室人员数-护理人员
    var wsjshllckszybfnk = '${wsjshllckszybfnk}';//内科病区-住院病房-临床科室人员数-护理人员
    var wsjshllckszybfwk = '${wsjshllckszybfwk}';//外科病区-住院病房-临床科室人员数-护理人员
    var yjy = '${yjy}';//医技药人员数
    var yjyyjy = '${yjyyjy}';//其他包括研究人员-医技药人员数
    var yjyys = '${yjyys}';//药师-医技药人员数
    var yjyysyjs = '${yjyysyjs}';//药剂科-药师-医技药人员数
    var yjyjs = '${yjyjs}';//技师-医技药人员数
    var yjyjsyk = '${yjyjsyk}';//医技科室-技师-医技药人员数
    var yjyjslc = '${yjyjslc}';//临床科室-技师-医技药人员数
    var yjyjslczy = '${yjyjslczy}';//住院病房-临床科室-技师--医技药人员数
    var yjyjslcmz = '${yjyjslcmz}';//门急诊-临床科室-技师--医技药人员数
    var yjyyis = '${yjyyis}';//医师 -医技药人员数
    var yjyyisyj = '${yjyyisyj}';//医技科室-医师 -医技药人员数
    var yjyyislc = '${yjyyislc}';//临床科室-医师 -医技药人员数
    var yjyyislczy = '${yjyyislczy}';//住院病房-临床科-医师 -医技药人员数
    var yjyyislczynk = '${yjyyislczynk}';//内科病区-住院病房-临床科-医师 -医技药人员数
    var yjyyislczywk = '${yjyyislczywk}';//外科病区-住院病房-临床科-医师 -医技药人员数
    var yjyyislcmjz = '${yjyyislcmjz}';//门急诊-临床科-医师 -医技药人员数
    var wsjsProportion='${wsjsProportion}';//卫生技术人员数所占比例
    var nwsjsProportion='${nwsjsProportion}';//非卫生技术人员数所占比例
    var wsjshlProportion='${wsjshlProportion}';//护理人员数所占比例
    var yjyProportion='${yjyProportion}';//医技药人员数所占比例
    var wsjshlyjksProportion='${wsjshlyjksProportion}';//医技科室人员数-护理人员所占比例
    var wsjshllcksProportion='${wsjshllcksProportion}';//临床科室人员数-护理人员所占比例
    var yjyyjyProportion='${yjyyjyProportion}';//其他包括研究人员-医技药人员数所占比例
    var yjyysProportion='${yjyysProportion}';//药师-医技药人员数所占比例
    var yjyjsProportion='${yjyjsProportion}';//技师-医技药人员数所占比例
    var yjyyisProportion='${yjyyisProportion}';//医师 -医技药人员数所占比例
</script>
<script type="text/javascript" src="report/diagram/tree/js/nodeData.js"></script>
<script>
$(function(){
      //data为节点数据，orgTree为添加整个树的元素的id名
      $.newCreateOrgTree(data,'orgTree');
  });
</script>
</head>
<body>  
<!-- <button class="daochu">导出数据</button> -->
<div id="orgTree">

</div>
</body>
</html>