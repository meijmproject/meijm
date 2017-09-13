$(function(){
    var trTable=$(".table_edit_bg2 tr");
    for(var i=0;i<trTable.length;i++){
    	 var tdText = $(trTable[i]).find("td");
		 if ($(tdText).text().length>3) {
		 	$(tdText).addClass("z_overflow");
		 };
    }
});