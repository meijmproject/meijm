/**
 * Created by admin on 2017/3/30.
 */
//用于上下拖拽的js
$.extend({
    //上下拖动效果
    drag_top_bottom:function(warpId,topId,bottomId,lineId){
        var oBox = findId(warpId),
            oTop = findId(topId),
            oBottom = findId(bottomId),
            oLine = findId(lineId);
        oLine.onmousedown = function(e) {
            var disY = (e || event).clientY;
            oLine.top = oLine.offsetTop;
            oTop.top = oTop.offsetTop;
            oBox.height = oBox.offsetHeight;
            document.onmousemove = function(e) {
                var iT = oLine.top + ((e || event).clientY - disY);
                var minT = oTop.top +60 ;
                var maxT = oTop.top + oBox.height - 60 ;
                if(iT>minT && iT<maxT){
                    oLine.style.top = oTop.style.height = iT - oTop.top  + "px";
                    oBottom.style.height = oBox.clientHeight + oTop.top - iT -10+ "px";
                }
                $(window).resize(function(){
            		worktop.grid.wrap.find('.ct').height(
            			$('#i-tab-left-warning').height()
            			-$('#pcs-search').height()-$('.page_foot').height()-48
            			);//48(分页48)
            	}).resize()
                return false
            };
            document.onmouseup = function() {
                document.onmousemove = null;
                document.onmouseup = null;
            };
            return false
        };
        function findId(id) {
            return document.getElementById(id)
        }
    },


   //左右拖动
    drag_left_right:function(warpId,leftId,rightId,lineId){
        var oBox = findId(warpId),
            oLeft = findId(leftId),
            oRight = findId(rightId),
            oLine = findId(lineId);
        oLine.onmousedown = function(e) {
            var disX = (e || event).clientX;
            oLine.left = oLine.offsetLeft;
            oLeft.left = oLeft.offsetLeft;
            oBox.width = oBox.offsetWidth;
            document.onmousemove = function(e) {
                var iT = oLine.left + ((e || event).clientX - disX);
                var minT = oLeft.left +360 ;
                var maxT = oLeft.left + oBox.width - 700 ;
                if(iT>minT && iT<maxT){
                    oLine.style.left = oLeft.style.width = iT - oLeft.left + "px";
                    oRight.style.width = oBox.clientWidth + oLeft.left - iT - oLine.offsetWidth-5 + "px";
                }
                return false
            };
            document.onmouseup = function() {
                document.onmousemove = null;
                document.onmouseup = null;
            };
            return false
        };
        function findId(id) {
            return document.getElementById(id)
        }
    }
})