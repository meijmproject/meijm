(function($, window, document, undefined) {
	$(document).keydown(function(e) {
        if (e.keyCode == 13) {
            $(".btn_search").click();
        }
    });
})(jQuery, window, document); 