insert into YHB_MENU_ITEM (ID, NAME, TITLE, DESCRIPTION, LOCATION, TARGET, ACTION, SYSTEM_CODE, PARENT_ID, PARENT_NAME, ORDER_SEQ, IMAGE)
values (100, '首页', 'menu.homepage', '首页', 'goHomepage.do?method=goHomepage', 'szghrsmain', 'goHomepage', '1000', null, 'Top', 100, null);
insert into YHB_MENU_ITEM (ID, NAME, TITLE, DESCRIPTION, LOCATION, TARGET, ACTION, SYSTEM_CODE, PARENT_ID, PARENT_NAME, ORDER_SEQ, IMAGE)
values (200, '基础信息管理', 'menu.info.hrinfo.base', '基础信息管理', '#', 'szghrsmain', '#', '1000', null, 'Top', 200, null);
insert into YHB_MENU_ITEM (ID, NAME, TITLE, DESCRIPTION, LOCATION, TARGET, ACTION, SYSTEM_CODE, PARENT_ID, PARENT_NAME, ORDER_SEQ, IMAGE)
values (210, '机构管理', 'menu.info.hrinfo.organization', '机构管理', '#', 'szghrsmain', '#', '1000', 200, '基础信息管理', 210, 'img/index/xinxixiaohe.png');
insert into YHB_MENU_ITEM (ID, NAME, TITLE, DESCRIPTION, LOCATION, TARGET, ACTION, SYSTEM_CODE, PARENT_ID, PARENT_NAME, ORDER_SEQ, IMAGE)
values (211, '单位管理', 'menu.info.hrinfo.unit', '单位管理', 'goToUnitManageWorkBench.do?method=goToUnitManageWorkBench', 'szghrsmain', 'goToUnitManageWorkBench', '1000', 210, '机构管理', 211, null);
insert into YHB_MENU_ITEM (ID, NAME, TITLE, DESCRIPTION, LOCATION, TARGET, ACTION, SYSTEM_CODE, PARENT_ID, PARENT_NAME, ORDER_SEQ, IMAGE)
values (212, '科室管理', 'menu.info.hrinfo.org', '科室管理', 'goToOrgManageWorkBench.do?method=goToOrgManageWorkBench', 'szghrsmain', 'goToOrgManageWorkBench', '1000', 210, '机构管理', 212, null);
insert into YHB_MENU_ITEM (ID, NAME, TITLE, DESCRIPTION, LOCATION, TARGET, ACTION, SYSTEM_CODE, PARENT_ID, PARENT_NAME, ORDER_SEQ, IMAGE)
values (213, '科室信息导入', 'menu.info.hrinfo.dataimport.unit', '科室信息导入', 'goUnitInfoImportWorkBench.do?method=goUnitInfoImportWorkBench', 'szghrsmain', 'goUnitInfoImportWorkBench', '1000', 210, '机构管理', 214, null);
insert into YHB_MENU_ITEM (ID, NAME, TITLE, DESCRIPTION, LOCATION, TARGET, ACTION, SYSTEM_CODE, PARENT_ID, PARENT_NAME, ORDER_SEQ, IMAGE)
values (220, '岗位管理', 'menu.info.hrinfo.gw', '岗位管理', '#', 'szghrsmain', '#', '1000', 200, '基础信息管理', 220, 'img/index/xinxixiaohe.png');
insert into YHB_MENU_ITEM (ID, NAME, TITLE, DESCRIPTION, LOCATION, TARGET, ACTION, SYSTEM_CODE, PARENT_ID, PARENT_NAME, ORDER_SEQ, IMAGE)
values (221, '院内岗位管理', 'menu.sys.admin.maintain.yngwset', '院内岗位管理', 'goYngwSetList.do?method=goYngwSetList', 'szghrsmain', 'goYngwSetList', '1000', 220, '岗位管理', 222, null);
insert into YHB_MENU_ITEM (ID, NAME, TITLE, DESCRIPTION, LOCATION, TARGET, ACTION, SYSTEM_CODE, PARENT_ID, PARENT_NAME, ORDER_SEQ, IMAGE)
values (230, '信息校核', 'menu.info.hrinfo.ver', '信息校核', '#', 'szghrsmain', '#', '1000', 200, '基础信息管理', 230, 'img/index/xinxixiaohe.png');
insert into YHB_MENU_ITEM (ID, NAME, TITLE, DESCRIPTION, LOCATION, TARGET, ACTION, SYSTEM_CODE, PARENT_ID, PARENT_NAME, ORDER_SEQ, IMAGE)
values (231, '人员信息校核', 'menu.info.hrinfo.ver.unit.person', '人员信息校核', 'goVerPbPersonWorkbench.do?method=goVerPbPersonWorkbench', 'szghrsmain', 'goVerPbPersonWorkbench', '1000', 230, '信息校核', 231, null);
insert into YHB_MENU_ITEM (ID, NAME, TITLE, DESCRIPTION, LOCATION, TARGET, ACTION, SYSTEM_CODE, PARENT_ID, PARENT_NAME, ORDER_SEQ, IMAGE)
values (240, '信息管理', 'menu.info.hrinfo.manage', '信息管理', '#', 'szghrsmain', '#', '1000', 200, '基础信息管理', 240, 'img/index/xinxixiaohe.png');
insert into YHB_MENU_ITEM (ID, NAME, TITLE, DESCRIPTION, LOCATION, TARGET, ACTION, SYSTEM_CODE, PARENT_ID, PARENT_NAME, ORDER_SEQ, IMAGE)
values (241, '通知通告管理', 'menu.info.hrinfo.manage.notice', '通知通告管理', 'goToNoticeManageWorkbench.do?method=goToNoticeManageWorkbench', 'szghrsmain', 'goToNoticeManageWorkbench', '1000', 240, '信息管理', 241, null);
insert into YHB_MENU_ITEM (ID, NAME, TITLE, DESCRIPTION, LOCATION, TARGET, ACTION, SYSTEM_CODE, PARENT_ID, PARENT_NAME, ORDER_SEQ, IMAGE)
values (242, '数据字典管理', 'menu.info.hrinfo.manage.dic', '数据字典管理', 'goToDictionaryManageWorkbench.do?method=goToDictionaryManageWorkbench', 'szghrsmain', 'goToDictionaryManageWorkbench', '1000', 240, '信息管理', 242, null);
INSERT INTO `YHB_menu_item` (`ID`, `NAME`, `TITLE`, `DESCRIPTION`, `LOCATION`, `TARGET`, `ACTION`, `SYSTEM_CODE`, `PARENT_ID`, `PARENT_NAME`, `ORDER_SEQ`, `IMAGE`)
VALUES ('243', '个人信息维护', 'menu.info.hrinfo.manage.update', '个人信息维护', 'goPersonalInfoUpdate.do?method=goPersonalInfoUpdate', 'szghrsmain', 'goPersonalInfoUpdate', '1000', '240', '信息管理', '243', NULL);

insert into YHB_MENU_ITEM (ID, NAME, TITLE, DESCRIPTION, LOCATION, TARGET, ACTION, SYSTEM_CODE, PARENT_ID, PARENT_NAME, ORDER_SEQ, IMAGE)
values (500, '系统管理', 'menu.sys', '系统管理', '#', 'szghrsmain', '#', '1000', null, 'Top', 500, null);
insert into YHB_MENU_ITEM (ID, NAME, TITLE, DESCRIPTION, LOCATION, TARGET, ACTION, SYSTEM_CODE, PARENT_ID, PARENT_NAME, ORDER_SEQ, IMAGE)
values (510, '系统安全', 'menu.sys.admin.security', '系统安全', '#', 'szghrsmain', '#', '1000', 500, '系统管理', 510, 'img/index/icon_sys_safe.png');
insert into YHB_MENU_ITEM (ID, NAME, TITLE, DESCRIPTION, LOCATION, TARGET, ACTION, SYSTEM_CODE, PARENT_ID, PARENT_NAME, ORDER_SEQ, IMAGE)
values (511, '用户管理', 'menu.sys.admin.security.user', '用户管理', 'goUsersList.do?method=goUsersList', 'szghrsmain', 'goUsersList', '1000', 510, '系统安全', 511, null);
insert into YHB_MENU_ITEM (ID, NAME, TITLE, DESCRIPTION, LOCATION, TARGET, ACTION, SYSTEM_CODE, PARENT_ID, PARENT_NAME, ORDER_SEQ, IMAGE)
values (512, '功能权限角色维护', 'menu.sys.admin.security.funcrole', '功能权限角色维护', 'goFuncRoleList.do?method=goFuncRoleList', 'szghrsmain', 'goFuncRoleList', '1000', 510, '系统安全', 512, null);
insert into YHB_MENU_ITEM (ID, NAME, TITLE, DESCRIPTION, LOCATION, TARGET, ACTION, SYSTEM_CODE, PARENT_ID, PARENT_NAME, ORDER_SEQ, IMAGE)
values (513, '数据权限角色维护', 'menu.sys.admin.security.datarole', '数据权限角色维护', 'goDataRoleList.do?method=goDataRoleList', 'szghrsmain', 'goDataRoleList', '1000', 510, '系统安全', 513, null);
insert into YHB_MENU_ITEM (ID, NAME, TITLE, DESCRIPTION, LOCATION, TARGET, ACTION, SYSTEM_CODE, PARENT_ID, PARENT_NAME, ORDER_SEQ, IMAGE)
values (514, '系统岗位维护', 'menu.sys.admin.security.position', '系统岗位维护', 'goSystemPoistionList.do?method=goSystemPoistionList', 'szghrsmain', 'goSystemPoistionList', '1000', 510, '系统安全', 514, null);
insert into YHB_MENU_ITEM (ID, NAME, TITLE, DESCRIPTION, LOCATION, TARGET, ACTION, SYSTEM_CODE, PARENT_ID, PARENT_NAME, ORDER_SEQ, IMAGE)
values (520, '系统维护', 'menu.sys.admin.maintain', '系统维护', '#', 'szghrsmain', '#', '1000', 500, '系统管理', 520, null);
insert into YHB_MENU_ITEM (ID, NAME, TITLE, DESCRIPTION, LOCATION, TARGET, ACTION, SYSTEM_CODE, PARENT_ID, PARENT_NAME, ORDER_SEQ, IMAGE)
values (521, '操作日志查询', 'menu.sys.admin.maintain.oplog', '操作日志查询', 'goOpLogList.do?method=goOpLogList', 'szghrsmain', 'goOpLogList', '1000', 520, '系统维护', 521, null);
commit;