<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ include file="/include/jsp_headers.jsp"%>
<%@ include file="/include/js_css_base_include.jsp"%>
<!DOCTYPE html>
<HTML>
 <HEAD>
  <TITLE> ZTREE DEMO </TITLE>
  <meta http-equiv="content-type" content="text/html; charset=utf-8">
  <script type="text/javascript" src="hrcomponent/infoJs/info.js"></script>
 </HEAD>
<body class="main-body">
    <div class="head-nav">
        <div class="index-logo">
            <img src="images/common/logo-all.png" height="52" width="239"/>
        </div>
        <ul class="h-nav-ul h-nav-item">
            <li class="h-nav-li"><a class="h-nav-link" href="index.html">首页</a></li>
            <li class="h-nav-li"><a class="h-nav-link" href="businessHandle.html">业务办理</a></li>
            <li class="h-nav-li"><a class="h-nav-link h-nav-linkactive" href="infoManage.html">信息管理</a></li>
            <li class="h-nav-li"><a class="h-nav-link" href="#">查询统计</a></li>
        </ul>

        <ul class="h-nav-ul h-nav-icon">
            <li class="h-nav-li question">
                <a class="h-nav-link" href="#">
                    <img src="images/common/question.png" height="25" width="16"/>
                </a>
                <span class="question-num">4</span>
            </li>
            <li class="h-nav-li">
                <a class="h-nav-link" href="#"><img src="images/common/key.png" height="29" width="14"/></a>
            </li>
            <li class="h-nav-li">
                <a class="h-nav-link" href="#"><img src="images/common/exit.png" height="23" width="21"/></a>
            </li>
        </ul>
    </div>

<div class="main-container">
    <div class="left-content">
        <ul class="siderbar-nav">
            <li><a  class="sdb-first" href="#">岗位设置</a></li>
            <li><a  class="sdb-first" href="#">岗位变动<span class="sdb-infonum">3</span></a>
                <ul class="siderbar-secondNav">
                    <li><a href="#">单位申报</a> </li>
                    <li><a href="#">单位申报</a> </li>
                    <li><a class="icon-down" href="#">单位申报</a>
                        <ul class="siderbar-thirdNav">
                            <li><a href="#">单位申报</a></li>
                            <li><a href="#">单位申报</a></li>
                        </ul>
                    </li>
                    <li><a href="#">单位申报</a> </li>
                    <li><a href="#">单位申报</a> </li>
                </ul>
            </li>
            <li><a class="sdb-first" href="#">公开招聘<span class="sdb-infonum">3</span></a>
                <ul class="siderbar-secondNav">
                    <li><a href="#">单位申报</a> </li>
                    <li><a href="#">单位申报</a> </li>
                    <li><a class="icon-down" href="#">单位申报</a>
                        <ul class="siderbar-thirdNav">
                            <li><a href="#">单位申报</a></li>
                            <li><a href="#">单位申报</a></li>
                        </ul>
                    </li>
                    <li><a href="#">单位申报</a> </li>
                    <li><a href="#">单位申报</a> </li>
                </ul>
            </li>
            <li><a class="sdb-first" href="#">人才引进<span class="sdb-infonum">3</span></a>
                <ul class="siderbar-secondNav">
                    <li><a href="#">单位申报</a> </li>
                    <li><a href="#">单位申报</a> </li>
                    <li><a class="icon-down" href="#">单位申报</a>
                        <ul class="siderbar-thirdNav">
                            <li><a href="#">单位申报</a></li>
                            <li><a href="#">单位申报</a></li>
                        </ul>
                    </li>
                    <li><a href="#">单位申报</a> </li>
                    <li><a href="#">单位申报</a> </li>
                </ul>
            </li>
            <li><a class="sdb-first" href="#">在职人员定岗</a></li>
            <li><a class="sdb-first" href="#">辞退</a></li>
            <li><a class="sdb-first" href="#">调入</a></li>
            <li><a class="sdb-first" href="#">年度考核</a></li>
        </ul>
    </div>

    <div class="right-content">
        <div class="right-wrapper" id="search-component">
            <!-- <div class="search-include">
                <dl class="search-unit">
                    <dt class="search-unit-dt">
                        <select>
                            <option>单位</option>
                            <option>性别</option>
                            <option>姓名</option>
                            <option>出生日期</option>
                        </select>
                        <select>
                            <option>大于</option>
                            <option>等于</option>
                            <option>小于</option>
                        </select>
                    </dt>
                    <dd class="search-unit-dd">
                        <select>
                            <option>事业单位1</option>
                            <option>事业单位2</option>
                            <option>事业单位3</option>
                        </select>
                    </dd>
                </dl>
                <dl class="search-unit">
                    <dt class="search-unit-dt">
                        <select>
                            <option>科室</option>
                            <option>单位</option>
                            <option>性别</option>
                            <option>姓名</option>
                            <option>出生日期</option>
                        </select>
                        <select>
                            <option></option>
                            <option>大于</option>
                            <option>等于</option>
                            <option>小于</option>
                        </select>
                    </dt>
                    <dd class="search-unit-dd">
                        <input type="text">
                        <ul class="s-pulldown-list">
                            <li class="s-pdl-li">麻醉部</li>
                            <li class="s-pdl-li">五官科</li>
                            <li class="s-pdl-li">儿科</li>
                            <li class="s-pdl-li">内科</li>
                            <li class="s-pdl-li">骨科</li>
                            <li class="s-pdl-li">神经科</li>
                            <li class="s-pdl-li">儿科</li>
                            <li class="s-pdl-li">内科</li>
                            <li class="s-pdl-li">骨科</li>
                            <li class="s-pdl-li">神经科</li>
                        </ul>
                    </dd>
                </dl>
                <dl class="search-unit">
                    <dt class="search-unit-dt">
                        <select>
                            <option>创建时间</option>
                            <option>单位</option>
                            <option>姓名</option>
                            <option>出生日期</option>
                        </select>
                        <select>
                            <option></option>
                            <option>大于</option>
                            <option>等于</option>
                            <option>小于</option>
                        </select>
                    </dt>
                    <dd class="search-unit-dd">
                        <input class="search-input-error" type="text" value="2017/02/23">
                    </dd>
                </dl>

                <img class="search-condition-add" src="images/common/add-condition.png"/>

            </div>
            <div class="search-btn-group">
                <button class="search-btn">查询</button>
                <a class="search-advanced-btn">高级查询</a>
            </div> -->
        </div>

        <div class="right-wrapper">
            <div class="mtable-btn">
                <a href="add_modal_dialog.html" class="mt-add check_transaction_popdown">新增业务</a>
                <a href="#" class="mt-modify">修改业务</a>
                <a href="#" class="mt-download">下载方案模板</a>
                <a href="#" class="mt-look">查看/上传附件</a>
                <a href="#" class="mt-declare">申报业务</a>
                <a href="#" class="mt-set">信息项设置</a>

                <div class="mt-info-set">
                    <div class="mt-info">
                        <div class="mt-info-content">
                            <div class="mt-info-title">信息显示</div>
                            <input class="mt-info-search" type="text" placeholder="搜索">
                            <ul class="mt-infoset-ul mt-info-show">
                                <li class="mt-infoset-li">姓名</li>
                                <li class="mt-infoset-li">性别</li>
                                <li class="mt-infoset-li mt-infoset-liselect">
                                    <!--<span class="mt-checkbox"></span>-->
                                    单位
                                    <span class="mt-desc-sort"></span>
                                    <span class="mt-asc-sort"></span>
                                </li>
                                <li class="mt-infoset-li">科室代码</li>
                                <li class="mt-infoset-li">岗位类别</li>
                            </ul>
                            <button class="mt-info-checkall" href="#">全选</button>
                            <button class="mt-info-empty" href="#">清空条件</button>
                        </div>
                    </div>
                    <div class="mt-info">
                        <div class="mt-info-content">
                            <div class="mt-info-title">排序</div>
                            <ul class="mt-infoset-ul mt-info-sort">
                                <li class="mt-infoset-li">性别 <span class="mt-close"></span> <span class="mt-radio">降</span> <span class="mt-radio">升</span></li>
                            </ul>
                        </div>
                    </div>
                    <div class="clear"></div>
                    <div class="mt-infoset-btn">
                        <button class="mt-infoset-sure">确定</button>
                        <button class="mt-infoset-cancel">取消</button>
                    </div>
                </div>
            </div>

            <table class="sr-table">
                <thead class="sr-table-thead">
                <tr>
                    <th width="50" class="md-th"></th>
                    <th width="50" class="md-th"><input type="checkbox"></th>
                    <th class="sorting">岗位设置方案</th>
                    <th class="sorting">当前环节</th>
                    <th class="sorting">上一环节</th>
                    <th class="sorting">单位</th>
                    <th class="sorting">主管单位</th>
                    <th class="sorting">创建时间</th>
                </tr>
                </thead>
                <tbody class="sr-table-tbody">
                <tr>
                    <td class="md-th">1</td>
                    <td class="md-th"><input type="checkbox"></td>
                    <td>asd</td>
                    <td>单位申报</td>
                    <td>核查</td>
                    <td>人力资源科室</td>
                    <td>人力资源科室</td>
                    <td>8/27/2017</td>
                </tr>
                <tr>
                    <td class="md-th">1</td>
                    <td class="md-th"><input type="checkbox"></td>
                    <td>asd</td>
                    <td>单位申报</td>
                    <td>核查</td>
                    <td>人力资源科室</td>
                    <td>人力资源科室</td>
                    <td>8/27/2017</td>
                </tr>
                <tr>
                    <td class="md-th">1</td>
                    <td class="md-th"><input type="checkbox"></td>
                    <td>asd</td>
                    <td>单位申报</td>
                    <td>核查</td>
                    <td>人力资源科室</td>
                    <td>人力资源科室</td>
                    <td>8/27/2017</td>
                </tr>
                <tr>
                    <td class="md-th">1</td>
                    <td class="md-th"><input type="checkbox"></td>
                    <td>asd</td>
                    <td>单位申报</td>
                    <td>核查</td>
                    <td>人力资源科室</td>
                    <td>人力资源科室</td>
                    <td>8/27/2017</td>
                </tr>
                <tr>
                    <td class="md-th">1</td>
                    <td class="md-th"><input type="checkbox"></td>
                    <td>asd</td>
                    <td>单位申报</td>
                    <td>核查</td>
                    <td>人力资源科室</td>
                    <td>人力资源科室</td>
                    <td>8/27/2017</td>
                </tr>
                <tr>
                    <td class="md-th">1</td>
                    <td class="md-th"><input type="checkbox"></td>
                    <td>asd</td>
                    <td>单位申报</td>
                    <td>核查</td>
                    <td>人力资源科室</td>
                    <td>人力资源科室</td>
                    <td>8/27/2017</td>
                </tr>
                <tr>
                    <td class="md-th">1</td>
                    <td class="md-th"><input type="checkbox"></td>
                    <td>asd</td>
                    <td>单位申报</td>
                    <td>核查</td>
                    <td>人力资源科室</td>
                    <td>人力资源科室</td>
                    <td>8/27/2017</td>
                </tr>
                <tr>
                    <td class="md-th">1</td>
                    <td class="md-th"><input type="checkbox"></td>
                    <td>asd</td>
                    <td>单位申报</td>
                    <td>核查</td>
                    <td>人力资源科室</td>
                    <td>人力资源科室</td>
                    <td>8/27/2017</td>
                </tr>
                <tr>
                    <td class="md-th">1</td>
                    <td class="md-th"><input type="checkbox"></td>
                    <td>asd</td>
                    <td>单位申报</td>
                    <td>核查</td>
                    <td>人力资源科室</td>
                    <td>人力资源科室</td>
                    <td>8/27/2017</td>
                </tr>
                <tr>
                    <td class="md-th">1</td>
                    <td class="md-th"><input type="checkbox"></td>
                    <td>asd</td>
                    <td>单位申报</td>
                    <td>核查</td>
                    <td>人力资源科室</td>
                    <td>人力资源科室</td>
                    <td>8/27/2017</td>
                </tr>
                <tr>
                    <td class="md-th">1</td>
                    <td class="md-th"><input type="checkbox"></td>
                    <td>asd</td>
                    <td>单位申报</td>
                    <td>核查</td>
                    <td>人力资源科室</td>
                    <td>人力资源科室</td>
                    <td>8/27/2017</td>
                </tr>
                <tr>
                    <td class="md-th">1</td>
                    <td class="md-th"><input type="checkbox"></td>
                    <td>asd</td>
                    <td>单位申报</td>
                    <td>核查</td>
                    <td>人力资源科室</td>
                    <td>人力资源科室</td>
                    <td>8/27/2017</td>
                </tr>
                <tr>
                    <td class="md-th">1</td>
                    <td class="md-th"><input type="checkbox"></td>
                    <td>asd</td>
                    <td>单位申报</td>
                    <td>核查</td>
                    <td>人力资源科室</td>
                    <td>人力资源科室</td>
                    <td>8/27/2017</td>
                </tr>
                <tr>
                    <td class="md-th">1</td>
                    <td class="md-th"><input type="checkbox"></td>
                    <td>asd</td>
                    <td>单位申报</td>
                    <td>核查</td>
                    <td>人力资源科室</td>
                    <td>人力资源科室</td>
                    <td>8/27/2017</td>
                </tr>
                <tr>
                    <td class="md-th">1</td>
                    <td class="md-th"><input type="checkbox"></td>
                    <td>asd</td>
                    <td>单位申报</td>
                    <td>核查</td>
                    <td>人力资源科室</td>
                    <td>人力资源科室</td>
                    <td>8/27/2017</td>
                </tr>
                </tbody>
            </table>
            <div class="sr-table-page">
                <ul>
                    <li style="margin-left: 15px;"><a href="#">&lt;</a></li>
                    <li>第<input type="text" value="1235" class="input_page">页</li>
                    <li>共2页</li>
                    <li><a href="#">&gt;</a></li>
                    <li class="page-text">共有<span>12</span>条记录.当前显示<span>1- 9</span>条记录</li>
                </ul>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
//此数据从后台配置
var conditionArray = [
  {conditionCode:'unitOid',conditionName:'单位',conditionType:'2',dicType:'YHRS0109'},
  {conditionCode:'sexOid',conditionName:'性别',conditionType:'2',dicType:'YHRS0003'},
  {conditionCode:'name',conditionName:'姓名',conditionType:'1',dicType:void 0},
  {conditionCode:'birthday',conditionName:'出生日期',conditionType:'3',dicType:void 0},
  {conditionCode:'test',conditionName:'测试',conditionType:'3',dicType:void 0}
];
$(document).ready(function(){
	var searchComponent = new Searcher.SearchComponent({
		  url: '',
		  conditionArray: conditionArray,
		  defaultConditionCodeArray: ['unitOid','sexOid','name']
		});
		$('#search-component').append(searchComponent.$el);
})
</script>
</HTML>