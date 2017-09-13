$(function () {

    $(document).on('click','.tws-li a',function (e) {
        e.stopPropagation();
        $(this).addClass('tws-liaselect');
        $(this).parent('li').siblings('li').children('a').removeClass('tws-liaselect');
    });
    // 向右边移动
    $(document).on('click','.tws-btn-right',function (e) {
        e.stopPropagation();
        var moveValue = $('.first-choice').find('.tws-liaselect').text();

        if (moveValue){
            $('.second-choice').find('ul').append('<li class="tws-li"><a href="#">'+moveValue+'</a></li>');
            $('.first-choice').find('.tws-liaselect').parent('li').remove();
        }
    });

    // 向右边全部移动
    $(document).on('click','.tws-btn-allright',function (e) {
        e.stopPropagation();
        var moveDom = $('.first-choice').find('.tws-li');

        if (moveDom){
            $('.second-choice').find('ul').append(moveDom);
            $('.first-choice').find('ul').children('li').remove();
        }
    });

    // 向左边移动
    $(document).on('click','.tws-btn-left',function (e) {
        e.stopPropagation();
        var moveValue = $('.second-choice').find('.tws-liaselect').text();

        if (moveValue){
            $('.first-choice').find('ul').append('<li class="tws-li"><a href="#">'+moveValue+'</a></li>');
            $('.second-choice').find('.tws-liaselect').parent('li').remove();
        }
    });

    // 向左边全部移动
    $(document).on('click','.tws-btn-allleft',function (e) {
        e.stopPropagation();
        var moveDom = $('.second-choice').find('.tws-li');

        if (moveDom){
            $('.first-choice').find('ul').append(moveDom);
            $('.second-choice').find('ul').children('li').remove();
        }
    });
});