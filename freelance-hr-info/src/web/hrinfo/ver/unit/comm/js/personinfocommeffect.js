//人员信息中的公共效果js
$(function(){
	//滚动效果
    $(window).load();
    tableScorll = function () {
        //表头和tbody内容相等
        var ths=$(".sr-table-thead tr th");
        var td=$(".sr-table-tbody tr td");
        for(var i=0;i<td.length;i++){
            $(ths[i]).width($(td[i]).width());
        }
        //表头滚动
        $(".sr-table-tbody").scroll(function (e) {
            $(this).siblings(".sr-table-thead").css({left:-this.scrollLeft});
        });
    }
    tableScorll();
});