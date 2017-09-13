insert into YHB_ROLES (ROLE_ID, ROLE_NAME, ROLE_DESC, ROLE_TYPE, CREATED_BY_CODE, CREATED_DATE, UPDATED_BY_CODE, UPDATED_DATE)
values (1, '超级管理员', null, '0', 'admin', now(), 'admin', now());
insert into YHB_ROLES (ROLE_ID, ROLE_NAME, ROLE_DESC, ROLE_TYPE, CREATED_BY_CODE, CREATED_DATE, UPDATED_BY_CODE, UPDATED_DATE)
values (3, '超级数据角色', null, '1', 'admin', now(), 'admin', now());
commit;