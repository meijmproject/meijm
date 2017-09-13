insert into YHB_USER_SP (SYSTEM_POSITION_OID, USER_ID, EFFECTIVE_DATE, EXPIRED_DATE, CREATED_BY_CODE, CREATED_BY_NAME, CREATED_DATE, UPDATED_BY_CDOE, UPDATED_BY_NAME, UPDATED_DATE)
values (1, 'admin', now(), null, 'admin', '超级管理员',now(), 'admin', '超级管理员', now());
commit;