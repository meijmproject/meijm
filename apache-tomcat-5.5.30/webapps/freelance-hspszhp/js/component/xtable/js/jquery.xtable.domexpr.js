/**
 *	[Plugins].ux.DomExprParser
 *
 *  @name DomExprParser
 *  @author zhangqp
 *
 *  @example
 *  new [Xtable].ux.DomExprParser().parse(this.dom, this.wrap);
 */
;(function(Plugins,$,window){
	"use strict";
	
	var DomExprParser = function(handlers) {
		this.handlers = handlers || DomExprParser.handlers;
	}

	$.extend(DomExprParser.prototype, {
		
		parse: function(expr, wrap, factory) {
			factory = factory || {};
			var stack = [wrap];//根节点
			for (var i=0; i<expr.length; i++) {
				var cmd = expr.charAt(i);
				var handler = this.handlers[cmd];
				var node =  stack[stack.length - 1];
				var result = handler.call(this.handlers, expr, i, node, factory, this);
				switch(result.action) {
					case 'begin':
						stack.push(result.node);
					break;
					case 'end':
						stack.pop();
					break;
				}
				i = i + result.step;
			}
		}
	});

	DomExprParser.handlers = {
		'<': function(expr,i,node,factory,context) {
			return {action:'begin', node: (factory['<'] || $('<div></div>')).appendTo(node), step: 0};
		},
		'>': function(expr,i,node,factory,context) {
			return {action:'end', step: 0};
		},
		't': function(expr,i,node,factory,context) {
			return {action:'continue', node: (factory['t'] || $('<table></table>')).appendTo(node), step: 0};
		},
		'i': function(expr,i,node,factory,context) {
			return {action:'continue', node: (factory['i'] || $('<div class="xt-info"></div>')).appendTo(node), step: 0};
		},
		'p': function(expr,i,node,factory,context) {
			return {action:'continue', node: (factory['p'] || $('<div class="paginate"></div>')).appendTo(node), step: 0};
		},
		'l': function(expr,i,node,factory,context) {
			return {action:'continue', node: (factory['l'] || $('<div class="length_menu"></div>')).appendTo(node), step: 0};
		},
		'"': function(expr,i,node,factory,context) {
			var end = expr.indexOf('"', i+1);
			var subExpr = expr.substring(i+1, end);

			if (end < 0 || end < i) throw 'dom expr syntax error.';

			this[subExpr.charAt(0)](subExpr, 0, node);

			return {action:'continue', step: end - i};
		},
		'#': function(expr, i, node){
			node.attr('id', expr.substring(1));
		},
		'.': function(expr, i, node){
			node.addClass(expr.substring(1));
		}
	};
	
	Plugins.ux.DomExprParser = DomExprParser;
	
})(Xtable, jQuery, window);
