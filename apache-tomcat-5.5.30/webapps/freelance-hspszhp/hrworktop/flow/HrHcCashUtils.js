/**
 * 编制工具类
 */
var HrHcCashUtils=(function($, window, document){
	
	return {
	//（公）从市本级机关以外转任审批   根据单位查找编制类型
	gethccode:function(unitOid,hcCode)
	{
		$.post("findHcCash.do?method=findUnitHcCash&unitOid="+unitOid,function(data){
			if (null != data.rows) 
			{
				document.getElementById("DPositionType").innerHTML = "";
				document.getElementById("DPositionType").value = 0;
				var hcInfoList = data.rows;
				var hcCheck = [];
				//$.each(hcInfoList,function(hc) {
				var sum =0;
				for(var i=0;i<data.le;i++){
					if($.inArray(data.rows[i].hcCode,hcCheck)==-1){
						document.getElementById("DPositionType").options[sum] = new Option(data.rows[i].hcName,data.rows[i].hcCode);
						hcCheck.push(data.rows[i].hcCode);
						sum++;
					}
				} 
			     //$("#DPositionType").append('<option value="'+hc.hcCode+'">'+hc.hcName+'</option>');       
			     //});
			}
			if(null != hcCode){
				$("#DPositionType").find("option[value='"+hcCode+"']").attr("selected",true);
			}
		},'json');
	}
	
  }
})(jQuery, window, document);