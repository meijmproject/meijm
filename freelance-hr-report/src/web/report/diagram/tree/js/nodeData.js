
var data = [
    {
        nodeName:'node1',                   //节点编号
        EmpName: "全院人员",            //节点名，即机构名
        parentNode:'',                      //父级节点的节点编号,头结点没有父级几点故父级几点为空,其他节点必填
        Url:'http://www.baidu.com',         //单击跳转的链接
        Unfold:true,                        //该节点是否展示
        department: qyTotalCount+"人",
        EmpPhoto:""
    },{
        nodeName:'node21',
        EmpName: "卫生技术人员",
        parentNode:'node1',
        Url:'http://www.imooc.com/',
        Unfold:true,
        department: wsjs+"人",
        EmpPhoto:wsjsProportion+"%"
    },{
        nodeName:'node22',
        EmpName: "非卫生技术人员",
        parentNode:'node1',
        Url:'http://v3.bootcss.com/',
        Unfold:true,
        department: nwsjs+"人",
        EmpPhoto:nwsjsProportion+"%"
    },{
        nodeName:'node31',
        EmpName: "护理人员",
        parentNode:'node21',
        Url:'http://jquery.cuishifeng.cn/',
        Unfold:true,
        department: wsjshl+"人",
        EmpPhoto:wsjshlProportion+"%"
    },{
        nodeName:'node32',
        EmpName: "医技药人员",
        parentNode:'node21',
        Url:'http://jquery.cuishifeng.cn/',
        Unfold:true,
        department: yjy+"人",
        EmpPhoto:yjyProportion+"%"
    },{
        nodeName:'node41',
        EmpName: "医技科室",
        parentNode:'node31',
        Url:'http://www.baidu.com',
        Unfold:true,
        department: wsjshlyjks+"人",
        EmpPhoto:wsjshlyjksProportion+"%"
    },{
        nodeName:'node42',
        EmpName: "临床科室",
        parentNode:'node31',
        Url:'http://www.baidu.com',
        Unfold:true,
        department: wsjshllcks+"人",
        EmpPhoto:wsjshllcksProportion+"%"
    },{
        nodeName:'node43',
        EmpName: "其他包括研究人员等",
        parentNode:'node32',
        Url:'http://www.baidu.com',
        Unfold:true,
        department: yjyyjy+"人",
        EmpPhoto:yjyyjyProportion+"%"
    },{
        nodeName:'node44',
        EmpName: "药师",
        parentNode:'node32',
        Url:'http://www.baidu.com',
        Unfold:true,
        department: yjyys+"人",
        EmpPhoto:yjyysProportion+"%"
    },{
        nodeName:'node45',
        EmpName: "技师",
        parentNode:'node32',
        Url:'http://www.baidu.com',
        Unfold:true,
        department: yjyjs+"人",
        EmpPhoto:yjyjsProportion+"%"
    },{
        nodeName:'node46',
        EmpName: "医师",
        parentNode:'node32',
        Url:'http://www.baidu.com',
        Unfold:true,
        department: yjyyis+"人",
        EmpPhoto:yjyyisProportion+"%"
    },{
        nodeName:'node51',
        EmpName: "门急诊",
        parentNode:'node42',
        Url:'http://www.baidu.com',
        Unfold:true,
        department: wsjshllcksmjz+"人",
        EmpPhoto:""
    },{
        nodeName:'node52',
        EmpName: "住院病房",
        parentNode:'node42',
        Url:'http://www.baidu.com',
        Unfold:true,
        department: wsjshllckszybf+"人",
        EmpPhoto:""
    },{
        nodeName:'node53',
        EmpName: "药剂科",
        parentNode:'node44',
        Url:'http://www.baidu.com',
        Unfold:true,
        department: yjyysyjs+"人",
        EmpPhoto:""
    },{
        nodeName:'node54',
        EmpName: "医技科室",
        parentNode:'node45',
        Url:'http://www.baidu.com',
        Unfold:true,
        department: yjyjsyk+"人",
        EmpPhoto:""
    },{
        nodeName:'node55',
        EmpName: "临床科室",
        parentNode:'node45',
        Url:'http://www.baidu.com',
        Unfold:true,
        department: yjyjslc+"人",
        EmpPhoto:""
    },{
        nodeName:'node56',
        EmpName: "医技科室",
        parentNode:'node46',
        Url:'http://www.baidu.com',
        Unfold:true,
        department: yjyyisyj+"人",
        EmpPhoto:""
    },{
        nodeName:'node57',
        EmpName: "临床科室",
        parentNode:'node46',
        Url:'http://www.baidu.com',
        Unfold:true,
        department: yjyyislc+"人",
        EmpPhoto:""
    },{
        nodeName:'node61',
        EmpName: "内科病区",
        parentNode:'node52',
        Url:'http://www.baidu.com',
        Unfold:true,
        department: wsjshllckszybfnk+"人",
        EmpPhoto:""
    },{
        nodeName:'node62',
        EmpName: "外科病区",
        parentNode:'node52',
        Url:'http://www.baidu.com',
        Unfold:true,
        department: wsjshllckszybfwk+"人",
        EmpPhoto:""
    },{
        nodeName:'node63',
        EmpName: "住院病房",
        parentNode:'node55',
        Url:'http://www.baidu.com',
        Unfold:true,
        department: yjyjslczy+"人",
        EmpPhoto:""
    },{
        nodeName:'node64',
        EmpName: "门急诊",
        parentNode:'node55',
        Url:'http://www.baidu.com',
        Unfold:true,
        department: yjyjslcmz+"人",
        EmpPhoto:""
    },{
        nodeName:'node65',
        EmpName: "住院病房",
        parentNode:'node57',
        Url:'http://www.baidu.com',
        Unfold:true,
        department: yjyyislczy+"人",
        EmpPhoto:""
    },{
        nodeName:'node66',
        EmpName: "门急诊",
        parentNode:'node57',
        Url:'http://www.baidu.com',
        Unfold:true,
        department: yjyyislcmjz+"人",
        EmpPhoto:""
    },{
        nodeName:'node71',
        EmpName: "内科病区",
        parentNode:'node65',
        Url:'http://www.baidu.com',
        Unfold:true,
        department: yjyyislczynk+"人",
        EmpPhoto:""
    },{
        nodeName:'node72',
        EmpName: "外科病区",
        parentNode:'node65',
        Url:'http://www.baidu.com',
        Unfold:true,
        department: yjyyislczywk+"人",
        EmpPhoto: ""
    }
]