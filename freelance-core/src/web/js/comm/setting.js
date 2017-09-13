(function($, window) {
	function addUlSerialNumber(o) {
    var num = 1;
    $(o).children('li').each(function () {
        $(this).children('i').text(num++);
    });
	}
	
	var html ='<div class="mt-info-set"><div class="mt-info"><div class="mt-info-content">';
	html+='<div class="mt-info-title">信息显示<i class="message-alert">鼠标拖动可调换位置</i></div>';
	html+='<ul class="mt-infoset-ul mt-info-show"></ul>';
	html+='<a class="mt-info-checkall" href="#">全选</a>';
	html+='<a class="mt-info-empty" href="#" id="clearInfo">清空条件</a>';
	html+='</div></div>';
	html+='<div class="mt-info"><div class="mt-info-content">';
	html+='<div class="mt-info-title mes-right">排序<i class="message-alert">鼠标拖动可调换位置</i></div>'; 
    html+='<ul class="mt-infoset-ul mt-info-sort"></ul><a class="mt-info-empty" id="clearSort" href="#">清空条件</a></div></div><div class="clear"></div>'; 
	html+='<div class="mt-infoset-btn"><button class="mt-infoset-sure">确定</button><button class="mt-infoset-cancel">关闭</button></div></div>';
	window.SettingCondition = function(options){
	    this.options = options || {};
	    this.sortFields = options.sortFields;
	    this.functionCode = options.functionCode;
	    this.init();
	}
	$.extend(SettingCondition.prototype, {
		init : function() {
			this.initRender();
			this.initEvents();
			var that = this;
		},
		initRender: function() {
			$('#setting').after($(html));
			var columns = worktop.grid.columns;
			if (columns) {
				for ( var i = 2; i < columns.length; i++) {
					$('.mt-info-show').append($('<li class="mt-infoset-li" id=' + columns[i].field+ '><i>'+(i-1)+'</i><a class="mt-infoset-lia">'+ columns[i].header + '</a><span class="mt-set-bottom" style="display:none;"></span><span class="mt-set-top" style="display:none;"></span></li>').data(columns[i]));
				}
			}
			this.selectFields();
			this.sortFields.forEach(function(v,i) {
				if(v.sort=='asc') {
					$('.mt-info-sort').append($('<li id='+v.field+' class=\'mt-infoset-li\'><i>'+(i+1)+'</i><a>'+v.header+'</a><span class=\'mt-close\'></span> '+'<span class=\'mt-radio\'>降</span> '+'<span class=\'mt-radio mt-radioselect\'>升</span>'+ '</li>').data(v));
				}else if(v.sort=='desc') {
					$('.mt-info-sort').append($('<li id='+v.field+' class=\'mt-infoset-li\'><i>'+(i+1)+'</i><a>'+v.header+'</a><span class=\'mt-close\'></span> '+'<span class=\'mt-radio mt-radioselect\'>降</span> '+'<span class=\'mt-radio\'>升</span>'+ '</li>').data(v));
				}
				$('.mt-radio').unbind('click').click(function(e) {
					e.stopPropagation();
					if ($(this).hasClass("mt-radioselect")) {
						$(this).removeClass("mt-radioselect");
					} else {
						if ($(this).siblings('span').hasClass("mt-radioselect")) {
							$(this).addClass("mt-radioselect");
							$(this).siblings('span').removeClass("mt-radioselect");
						} else {
							$(this).addClass("mt-radioselect");
						}
					}
				});
				$('.mt-close').on('click', function(e) {
					e.stopPropagation();
					$(this).parent().remove();
				});
			});
			$('.mt-infoset-ul').outerHeight( $('.x-table').outerHeight(true)-160);
			this.reRenderGridColumns();
		},
		initEvents : function() {
			var that = this;
			$(".mt-infoset-lia").click(function(e) {
				e.stopPropagation();
				$(this).toggleClass('mt-infoset-liaselect');
			});
			$('.mt-infoset-lia').parent().hover(function(e) {
				$(this).find('span').toggle();
			});
			$('.mt-set-top').click(function(e) {
				e.stopPropagation();
				var objUl = $(this).parent('li').parent('ul');
        var onthis = $(this).parent();
        var getUp=onthis.prev();
        if ($(getUp).length == 0)
        {
            alert("已经是顶级元素，不能上移！");
            return;
        }
        $(objUl).prepend(onthis);
        addUlSerialNumber($(".mt-info-show"));
			});
			$('.mt-set-bottom').click(function(e) {
				e.stopPropagation();
        var objUl = $(this).parent('li').parent('ul');
        var onthis = $(this).parent();
        var getDown=onthis.next();

        if ($(getDown).length == 0)
        {
            alert("已经是最后一个元素，不能下移");
            return;
        }
        $(objUl).append(onthis);
        addUlSerialNumber($(".mt-info-show"));
			});
			// 信息项设置信息显示部分点击“全选”时的事件
			$(".mt-info-checkall").click(function(e) {
				e.stopPropagation();
				that.selectAll();
			});
			// 信息项设置信息显示部分点击“清空条件”时的事件
			$("#clearInfo").click(function(e) {
				e.stopPropagation();
				$(".mt-info-show li").each(function(index) {
					$(this).children('a').removeClass("mt-infoset-liaselect");
				});
			});
			$("#clearSort").click(function(e) {
				e.stopPropagation();
				$(".mt-info-sort li").each(function(index) {
					$(this).remove();
				});
			});
			$(".mt-info-show").sortable({
				update: function() {
					addUlSerialNumber($(".mt-info-show")); 
				} 
			});
			$(".mt-info-show").disableSelection();
			$(".mt-info-sort").sortable({ update: function() {    addUlSerialNumber($(".mt-info-sort")); } });
			$(".mt-info-sort").disableSelection();

			$('.mt-info-show .mt-infoset-li').on('mousedown', function() {
        // 给将拖拽的元素添加虚线边框
        var that = this;
        $(that).css('border','1px dashed #d9dfe9');
        var arrText = [];
        // 获取原始排序ul中所有的li值 去掉后面的"升降"字眼
        $('.mt-info-sort .mt-infoset-li').each(function() {
        	var sortText = $(this).children('a').text();
					//sortText = sortText.substring(0, sortText.length - 4);
					arrText.push(sortText.trim());
        });
        var text = $(this).children('a').text().trim();
        var textValue = $(this).data().field;
        var drag_is = true;
        $(this).mousemove(function(){
            if($(this).position().left>parseInt($('.mt-info-show').css('width'))
                &&$(this).position().top>0
                &&$(this).position().top<parseInt($('.mt-info-sort').css('height'))
                &&drag_is 
                && text != ''
                && !contains(arrText,text)){
                var num = arrText.length+1;
                var $li = $('<li id='+textValue+' class=\'mt-infoset-li\'><i>'+ num + '</i><a>'+text+'</a><span class=\'mt-close\'></span> '+'<span class=\'mt-radio\'>降</span> '+'<span class=\'mt-radio mt-radioselect\'>升</span>'+ '</li>').data($(this).data());
                $('.mt-info-sort').append($li);
                drag_is = false;
                text = '';

                //信息设置项排序部分选中升降序的样式改变，只能单选
                $('.mt-radio').unbind('click').click(function (e) {
                    e.stopPropagation();
                    if ($(this).hasClass("mt-radioselect")){
                        $(this).removeClass("mt-radioselect");
                    }else{
                        if ($(this).siblings('span').hasClass("mt-radioselect")){
                            $(this).addClass("mt-radioselect");
                            $(this).siblings('span').removeClass("mt-radioselect");
                        }else {
                            $(this).addClass("mt-radioselect");
                        }
                    }
                });
                //信息设置项排序部分点击删除事件
                $('.mt-close').on('click',function (e) {
                    e.stopPropagation();
                    $(this).parent().remove();
                });
            }
        })
        $(document).on('mouseup',function(){
            $(that).css('border','1px dashed #fff');
            text = '';
        })
			});
			// 判断数组arr中是否包含对象obj
			function contains(arr, obj) {
				var i = arr.length;
				while (i--) {
					if (arr[i] === obj) {
						return true;
					}
				}
				return false;
			}
			// 确定
			$('.mt-infoset-sure').click(function() {
				that.sure();
			});
			$('.mt-infoset-cancel').click(function() {
				that.selectFields();
				$('.mt-info-set').slideUp(0);
			});
		},
		sure : function() {
			this.saveFields();
			this.saveSorts();
		},
		reRenderGrid: function() {
			this.reRenderGridColumns();
			this.reGridSort();
			worktop.grid.draw();
			$('#verPersonId tbody').scrollTop(0);
			$('#verPersonId tbody').scrollLeft(0);
		},
		reRenderGridColumns: function() {
			var fields = [], i = 0;
			$('.mt-info-show li').each(function() {
				if ($(this).children().hasClass('mt-infoset-liaselect')) {
					$(this).data().index = i++;
					fields.push($(this).data());
				}
			})
			$('.mt-info-set').slideUp(0);
			worktop.grid.settings.columns = fields.length > 0 ? fields : [ {
				header : '',
				field : ''
			}];
			worktop.grid.initThead();
		},
		reGridSort: function() {
			var sortFields = [];
			if($('.mt-info-sort li').length>0) {
				$('.mt-info-sort li').each(function() {
					var sortField = {};
					var fieldValue = $(this).attr('id');
					var span = $(this).find('span');
					for ( var j = 0; j < span.length; j++) {
						if ($(span[j]).hasClass('mt-radioselect')) {
							var index = $(span[j]).index();
							if (index == 3) {
								sortField.sort = 'desc';
								sortField.field = fieldValue;
							} else {
								sortField.sort = 'asc';
								sortField.field = fieldValue;
							}
						}
					}
					sortFields.push(sortField);
				})
				var that = this;
				worktop.grid.store.rows.sort(function(a, b) {
					var result, flag;
					sortFields.forEach(function(v, i) {
						if (!flag) {
							var sort = v.sort;
							var field = v.field;
							if (sort == 'desc') {
								result = that.cmpDesc(a.data[field], b.data[field]);
							} else {
								result = that.cmpAsc(a.data[field], b.data[field]);
							}
							if (result) flag = true;
						}
					});
					return result;
				});
			}
		},
		cmpAsc: function(a, b) {
			if (a > b) return +1;
			if (a < b) return -1;
			return 0;
		},
		cmpDesc: function(a, b) {
			if (a > b) return -1;
			if (a < b) return +1;
			return 0;
		},
		saveFields: function() {
			var fields = [], that = this;
			$('.mt-info-show li').each(function(i,v) {
				var fieldOrder = {};
				fieldOrder.resultOid = $(this).data().resultOid;
				fieldOrder.functionCode = $(this).data().functionCode;
				fieldOrder.fieldOrder = i;
				if ($(this).children().hasClass('mt-infoset-liaselect')) {
					fieldOrder.isShow = "1";
				}else {
					fieldOrder.isShow = "0";
				}
				fields.push(fieldOrder);
			});
			$.post('saveOrderFields.do?method=saveOrderFields',{data:JSON.stringify(fields)}, function() {
				that.reRenderGrid();
			});
		},
		saveSorts: function() {
			var fields = [], that = this;
			$('.mt-info-sort li').each(function(i, v) {
				var sortField = {};
				sortField.resultOid =  $(this).data().resultOid;
				sortField.functionCode = $(this).data().functionCode;
				sortField.fieldOrder = i;
				var span = $(this).find('span');
				for ( var j = 0; j < span.length; j++) {
					if ($(span[j]).hasClass('mt-radioselect')) {
						var index = $(span[j]).index();
						if (index == 3) {
							sortField.sort = 'desc';
						} else {
							sortField.sort = 'asc';
						}
					}
				}
				fields.push(sortField);
			});
			$.post('saveSortFields.do?method=saveSortFields',{data:JSON.stringify(fields),functionCode:this.functionCode}, function() {
				worktop.form.goQuery();
			});
		},
		selectAll : function() {
			$(".mt-info-show li").each(function(index) {
					$(this).children('a').addClass("mt-infoset-liaselect");
			});
		},
		//选中并排列当前用户的表格
		selectFields : function() {
			var that = this;
			$('.mt-info-show li').each(function(i,v) {
				if($(this).data().isShow=='1') {
					$(this).children('a').addClass('mt-infoset-liaselect');
				}
			});
		}
	})
})(jQuery, window, document);