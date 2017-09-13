var Mask = (function($, window, document){
	var html="<div class='spinner-background' style='display:none;'><div class='spinner' ><div class='spinner-container container1'>";
		html+="<div class='circle1'></div><div class='circle2'></div><div class='circle3'></div>";
		html+="<div class='circle4'></div></div> <div class='spinner-container container2'>";
		html+="<div class='circle1'></div><div class='circle2'></div><div class='circle3'></div>";
		html+="<div class='circle4'></div></div><div class='spinner-container container3'>";
		html+="<div class='circle1'></div><div class='circle2'></div><div class='circle3'></div>";
		html+="<div class='circle4'></div></div></div></div>";
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