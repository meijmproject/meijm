<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/jsp_headers.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1.0"/>
<title>岗位管理列表</title>
</head>
<body>
<div class="theme-popbod dform">
    <div class="theme_popover_content">
        <div class="theme_popover_content_Main" style="display:block">
            <!--弹出框左侧-->
            <div class="theme_popover_content_Left">
                <div onscroll="myFunction()">
                    <div class="theme_popover_content_Form">
                        <div class="theme_popbod_box">
                            <form class="theme_popbod_form">
                                <label>功能名称</label>
                            </form>
                        </div>
                    </div>
                        <div class="popover_system  popover_left_onscroll">
                            <ul id="treeDemoRight" class="ztree"></ul>
                        </div>
                </div><!--滚动条结束-->
            </div>
            <div style="clear: both"></div><!--清除浮动-->
        </div>
    </div>
</div>
<div style="display: none;">
   <ul id="treeDemoLeft" class="ztree"></ul>
</div>
</body>

<script type="text/javascript">
var roleId=${param.roleId};
var zTreeObjLeft;
var zTreeObjRight;
var settingLeft = {
		view: {
			dblClickExpand: false,
			showLine: false
		},
		data: {
			simpleData: {
				enable: true,
				idKey: "funcId"
			},
			key:{
				name:"funcName"
			}
		},
	   callback: {
	        onClick : zTreeOnClickLeft
	    }
};
var settingRight = {
		view: {
			dblClickExpand: false,
			showLine: false
		},
		data: {
			simpleData: {
				enable: true,
			},
			key:{
				name:"funcName"
			}
		},
	   callback: {
	        onClick : zTreeOnClickRight
	    }
};
function zTreeOnClickLeft(event, treeId, treeNode) {
		   var zTreeLeft = $.fn.zTree.getZTreeObj("treeDemoLeft");
		   zTreeLeft.expandNode(treeNode);
		   
	};
function zTreeOnClickRight(event, treeId, treeNode) {
	  var zTreeRight = $.fn.zTree.getZTreeObj("treeDemoRight");
	  zTreeRight.expandNode(treeNode);
	  
	   
};
function loadTree(){
	var treeNodes;  
	$.ajax({  
	    cache:false,//是否使用缓存  
	    type:'POST',//请求方式：post  
	    dataType:'json',//数据传输格式：json  
	    data : {roleId:roleId},
	    url:"findFunction.do?method=findFunction",//请求的action路径  
	    error:function(){  
	        //请求失败处理函数  
	        alert('亲，请求失败！');  
	    },  
	    success:function(data){  
	        //请求成功后处理函数  
	        treeNodes = data;//把后台封装好的简单Json格式赋给treeNodes  
	        zTreeObjLeft = $.fn.zTree.init($("#treeDemoLeft"), settingLeft, treeNodes);
	        zTreeObjRight = $.fn.zTree.init($("#treeDemoRight"), settingRight, null);
	        moveTreeNode(zTreeObjLeft, zTreeObjRight);
	        zTreeObjRight.expandAll(true);//展开所有节点数据
	    }  
	});  
}

function moveTreeNode(zTreeObjLeft, zTreeObjRight){
	var nodes = zTreeObjLeft.getCheckedNodes();	//获取选中需要移动的数据
	for(var i=0;i<nodes.length;i++){			//把选中的数据从根开始一条一条往右添加
		var node = nodes[i];
		
		var strs={};			//新建一个JSON 格式数据,表示为一个节点,可以是根也可以是叶
		strs.funcId =node.funcId;
		strs.funcName=node.funcName;
		//strs.code= node.code;
		strs.fatherId=node.fatherId;
		strs.children = new Array();	//树节点里面有个 nodes 集合,用来存储父子之间的包涵关系
		
		//调用添加方法
		//strs : json 格式..拼装成树的一个节点
		//zTreeObjRight: 表示需要添加节点的树
		zTreeDataAddNode(strs,zTreeObjRight);
		
		//获取这个被添加的code 如果是右增加  用来把它从左边移除掉
		var Id = strs.fatherId;
		
		//使用递归移除 移除的时候从叶子开始找  和增加的时候刚好相反
		//参数1就是数组最后一个数据  
		//scode  : 上面截取的code 表示父亲节点 
		//zTreeObjLeft : 需要移除的树,在zTreeObjLeft 里面移除此对象
		zTreeDataDelete(nodes[nodes.length-(i+1)],Id,zTreeObjLeft);
		}
		//把选中状态改为未选择
		zTreeObjRight.checkAllNodes(false);	
		zTreeObjLeft.checkAllNodes(false);
		
		//刷新
		zTreeObjRight.refresh();
		zTreeObjLeft.refresh();
}
		
//树数据移动方法
function zTreeDataAddNode(strs,zTreeObjRight){
	var nodes = zTreeObjRight.transformToArray(zTreeObjRight.getNodes());	//获取需要添加数据的树下面所有节点数据
	
	//如果有多个数据需要遍历,找到strs 属于那个父亲节点下面的元素.然后把自己添加进去
	if(nodes.length > 0){
		//这个循环判断是否已经存在,true表示不存在可以添加,false存在不能添加
		var isadd=true;
		for(var j=0;j<nodes.length;j++){
			if(strs.funcId==nodes[j].funcId){
				isadd=false;
				break;
			}
		}
		
		//找到父亲节点
		var Id = strs.fatherId;
		var flag =false;
		var i=0;
		for(i;i<nodes.length;i++){
			if(Id ==nodes[i].funcId){
				flag = true;
				break;
			}
		}
		
		//同时满足两个条件就加进去,就是加到父亲节点下面去
		if(flag && isadd){
				var treeNode1=nodes[i];
				treeNode1.children[treeNode1.children.length <=0 ? 0 : treeNode1.children.length++]=strs;
				
		//如果zTreeObjRight 里面找不到,也找不到父亲节点.就把自己作为一个根add进去
		}else if(isadd){
				zTreeObjRight.addNodes(null,strs);
			}
	}else{
				//树没任何数据时,第一个被加进来的元素
				zTreeObjRight.addNodes(null,strs);
	}
}

//数据移除
function zTreeDataDelete(node,Id,zTreeObjLeft){
	if(node.isParent){	//判断是不是一个根节点,如果是一个根几点从叶子开始遍历寻找
		//如果是个根就检测nodes里面是否有数据
			if(node.children.length > 0){
						//取出来
						var fnodes  = node.children;
						for(var x = 0; x<fnodes.length; x++){
							//不是根节点.并且code 相当就是需要移除的元素
							if(!(fnodes[x].isParent) && fnodes[x].funcId==Id){	
									//调用ztree 的移除方法,参数是一个节点json格式数据
									zTreeObjLeft.removeNode(fnodes[x]);
									
									//如果当前这个节点又是个根节点.开始递归
							}else if(fnodes[x].isParent){	
									zTreeDataDelete(fnodes[x],Id);
							}
						}
				}else{
						//如果是个根,但是下面的元素没有了.就把这个根移除掉
						zTreeObjLeft.removeNode(node);
				}
		}else{
			//不是就直接移除
			zTreeObjLeft.removeNode(node);
		}
}
$(document).ready(function() {
	loadTree();
});
</SCRIPT>
</html>