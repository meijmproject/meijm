var Mask1 = (function($, window, document){
	var html="<div class='loading-bg person-import' style='display:none;'><div class='spinner-background'> <div class='spinner' >";
		html+=" <div class='spinner-container container1'><div class='circle1'></div><div class='circle2'></div><div class='circle3'></div><div class='circle4'></div></div>";
		html+="<div class='spinner-container container2'> <div class='circle1'></div><div class='circle2'></div><div class='circle3'></div><div class='circle4'></div></div>";
		html+="<div class='spinner-container container3'><div class='circle1'></div> <div class='circle2'></div><div class='circle3'></div><div class='circle4'></div></div></div></div></div>";
	var _mask = {};
	function mask() {
		this.init.apply(this, arguments);
	}
	mask.prototype = {
		_div : {
			maskInnerDiv : $(html)
		},
		init : function(_target) {
			var _self = this;
			if (!_target) {
				_target = $("body");
			}
			_self._div.maskInnerDiv.appendTo(_target);
		},
		show : function() {
			var _self = this;
			_self._div.maskInnerDiv.show();
		},
		hide : function() {
			var _self = this;
			_self._div.maskInnerDiv.hide();
		}
	};
	return {
		showMask : function(target) {
			_mask = new mask(target);
			_mask.show();
		},
		hideMask : function() {
			//_mask.hide();
			_mask._div.maskInnerDiv.remove();
		}
	};
})(jQuery, window, document);