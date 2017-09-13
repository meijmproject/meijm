$(document).ready(function () {
    $('.check_transaction_popdown').popdown();
});

//当点击的时候出现颜色
$(function changecolor(){
    $(".busi_changecolor").click(function(){
        var busichangecolor=$(this).css('background-color','#408ace');
    })
})