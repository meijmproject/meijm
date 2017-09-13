<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<title></title>
<!-- 
	调用方式：
	Widget.openContent('widget_report.jsp',{url:'需要显示的页面url'});
 -->
</head>
<body>
	
	<div id="transaction_modal_box" class="transaction_modal_box">
		<div class="modal-dialog-container">
			<div class="md-title">
				xx<a href="#" class="md-close close-popdown" button-click="widget-close"></a>
			</div>
			<iframe class="md-main-content" style="padding:0;margin:0;min-height:700px;" id="report" frameborder="0" src="${param.url}"></iframe>
		</div>
	</div>
	<script>
		$('#report').outerWidth($('#transaction_modal_box').outerWidth());
		$(window).resize(function() {
			$('#report').outerWidth($('#transaction_modal_box').outerWidth());
		});
	</script>
</body>
</html>