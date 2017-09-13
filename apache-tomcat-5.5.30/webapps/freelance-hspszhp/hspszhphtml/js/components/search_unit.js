
$(function () {
    // 左右切按钮是否显示
    function leftRightSwitch() {
        var searchSelectItemHeight = $('.search-selected-item').outerHeight(true);
        var searchSelectItemTop =$('.search-selected-item').css('top') ;
        searchSelectItemTop = parseInt(searchSelectItemTop.substring(0,searchSelectItemTop.length-2)); /*将获取到的top值转换为数字*/

        if (searchSelectItemHeight > 70 && searchSelectItemTop < 0){
            $('.left-group').css({"visibility":"visible"});
            $('.right-group').css({"visibility":"visible"});
        }else if (searchSelectItemHeight > 35 && searchSelectItemHeight < 71 && (searchSelectItemTop + searchSelectItemHeight) == 35){
            $('.left-group').css({"visibility":"visible"});
            $('.right-group').css({"visibility":"hidden"});
        }else if(searchSelectItemHeight > 35 && searchSelectItemTop == 0){
            $('.right-group').css({"visibility":"visible"});
            $('.left-group').css({"visibility":"hidden"});
        }else {
            $('.left-group').css({"visibility":"hidden"});
            $('.right-group').css({"visibility":"hidden"});
        }
    };

    leftRightSwitch();
    $(window).resize(function () {
        leftRightSwitch();
    });
    // 查询条选择项件向左换一批
    $(document).on('click','.left-group',function () {
        var searchItemTop = $('.search-selected-item').css('top');
        var rValue = parseInt(searchItemTop.substring(0,searchItemTop.length-2)) + 35;/*去掉px*/
        $('.search-selected-item').css({'top':rValue});
        leftRightSwitch();
    });
    // 查询条选择项件向左换一批
    $(document).on('click','.right-group',function () {
        var searchItemTop = $('.search-selected-item').css('top');
        var rValue = parseInt(searchItemTop.substring(0,searchItemTop.length-2)) - 35;
        $('.search-selected-item').css({'top':rValue});
        leftRightSwitch();
    });

    //高级查询点击动作
    $(document).on('click','.search-advanced-btn',function () {
        if ($('.search-advanced').is(':hidden')){
            $('.search-advanced').show();
            // $(this).addClass('sab-click');
            // $('.search-advanced').append($(this));
        }else {
            // $(this).removeClass('sab-click');
            // $('.search-include').append($(this));
            $('.search-advanced').hide();
        }
    });

    //search-unit 查询单元mouseoverr效果
    $(document).on('mouseover','.search-advanced .search-unit',function(){
        $(this).addClass("search-unit-hover");
        $(this).find('.search-unit-close').show();
    });
    // search-unit 查询单元mouseout效果
    $(document).on('mouseout','.search-advanced .search-unit',function(){
        $(this).removeClass("search-unit-hover");
        $(this).find('.search-unit-close').hide();
    });

    //点击查询单元关闭效果
    $(document).on('click','.search-unit-close',function () {
        $(this).parent('dl').remove();
    });
    //点击加号添加一个查询条件
    $(document).on('click','.search-unit-add',function () {
        $(this).parent('dt').parent('dl').before("<dl class='search-unit'> <span class='search-unit-close'></span><dt class='search-unit-dt'> <select> <option>创建时间</option> <option>单位</option> <option>姓名</option> </select> " +
            "<select> <option>等于</option> <option>模糊查询</option> </select> </dt> " +
            "<dd class='search-unit-dd'> <input type='text' value='2017/02/23'> </dd> </dl>");
    });


    // 下拉多选位置
    function pulldownlistPosition() {
        var searchUnitOffset = $(".s-input-focus").offset();
        var searchUnitWidth = $(".s-input-focus").outerWidth();
        $(".s-input-focus").siblings('ul').width(searchUnitWidth);
    }
    pulldownlistPosition();

    $(window).resize(function () {
        pulldownlistPosition();
    });
    //下拉多选显示
    $(document).on('click','.search-unit-dd input',function (e) {
        e.stopPropagation();
        if ($(this).hasClass("s-input-focus")){
            $(this).removeClass("s-input-focus");
        }else {
            $(".search-include").find(".s-input-focus").removeClass("s-input-focus");
            $(this).addClass("s-input-focus");
            pulldownlistPosition();
        }
    });
    //点击后，下拉列表关闭
    $(document).on('click','body',function () {
        $(".s-input-focus").removeClass("s-input-focus");
    });

    //多选下拉选项框操作：选中样式及去掉选中样式
    $(document).on('click','.multiple-choice .s-pdl-li',function (e) {
        if($(this).hasClass('s-pdl-selected')){
            $(this).removeClass('s-pdl-selected');

            //去掉选中同时将input相应字段删除
            var livalue = $(this).text();
            var inputvalue = $(this).parent('ul').siblings('input').val();
            var inputValueEnd = inputvalue.replace(livalue,"");
            inputValueEnd = inputValueEnd.replace(",,",",");
            if (inputValueEnd.substring(0,1) == ","){
                inputValueEnd = inputValueEnd.substring(1,inputValueEnd.length);
            }
            if (inputValueEnd.substring(inputValueEnd.length-1,1) == ","){
                inputValueEnd = inputValueEnd.substring(0,inputValueEnd.length);
            }
            $(this).parent('ul').siblings('input').val(inputValueEnd);
        }else {
            $(this).addClass('s-pdl-selected');
            //将选中的项置入input框内
            var livalue = $(this).text();
            var inputvalue = $(this).parent('ul').siblings('input').val();
            if (inputvalue){
                $(this).parent('ul').siblings('input').val(inputvalue+','+livalue);
            }else{
                $(this).parent('ul').siblings('input').val(livalue);
            }
        }
        e.stopPropagation();
    });

    // 下拉单选选中一个下拉框收起
    $(document).on('click','.single-choice .s-pdl-li',function (e) {
        if($(this).hasClass('s-pdl-selected')){
            $(this).removeClass('s-pdl-selected');
        }else {
            $(this).addClass('s-pdl-selected');
            $(this).siblings('li').removeClass('s-pdl-selected');
            //将选中的项置入input框内
            var livalue = $(this).text();
            $(this).parent('ul').siblings('input').val(livalue);
            $(this).parent('ul').siblings('input').removeClass("s-input-focus");
        }
        e.stopPropagation();
    });

});

