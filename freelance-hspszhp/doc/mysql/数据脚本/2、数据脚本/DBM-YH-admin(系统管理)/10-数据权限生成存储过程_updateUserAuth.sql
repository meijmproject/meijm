drop PROCEDURE if exists `updateUserAuth`;

CREATE PROCEDURE `updateUserAuth`(IN `p_userId` varchar(100))
BEGIN
	#定义用户ID
	DECLARE v_userId VARCHAR(100);
	#定义科室OID
	DECLARE v_orgOid VARCHAR(12);
  #定义科室状态
  DECLARE v_orgStatus VARCHAR(20);
	#定义科室名称
	DECLARE v_orgName VARCHAR(200);
	#定义岗位OID
	DECLARE v_systemPositionOid VARCHAR(12);

	#创建结束标志变量  
	DECLARE done int default false;

	#定义游标-通过用户登录ID查询用户具有的所有科室数据权限
	DECLARE cur_auth CURSOR
	FOR select distinct u.user_id,juo.org_oid,juo.org_status,juo.org_name,usp.system_position_oid
	from yhc_ut_org juo,yhb_ROLE_DATA_AUTH  rda,yhb_users u,yhb_user_sp usp,yhb_system_position sp
	where u.user_id = p_userId and usp.user_id = u.user_id and usp.system_position_oid = sp.system_position_oid
	and sp.data_role_id = rda.role_id and sp.data_role_id is not null and rda.AUTH_LEVEL = '0'
	and ((rda.IS_ONLY_OWN = 'N' and FIND_IN_SET(juo.ORG_OID,getChildOrgList(rda.AUTH_CODE)))
		or (rda.IS_ONLY_OWN = 'Y' and juo.ORG_OID=rda.AUTH_CODE))
	and exists (select 1 from v_yhb_user_role ur where ur.user_id = u.user_id and ur.data_role_id = rda.role_id);  

	#指定游标循环结束时的返回值  
	declare continue HANDLER for not found set done = true;

	#删除该用户对应的科室权限记录
	delete from yhb_user_org_auth where user_id=p_userId;

	#打开游标
	OPEN cur_auth;
	#开始遍历游标
	REPEAT
	#获取当前游标指向的值存入变量
	FETCH cur_auth into v_userId,v_orgOid,v_orgStatus,v_orgName,v_systemPositionOid;
	IF NOT done THEN
		#插入用户科室权限关系记录
		insert into yhb_user_org_auth(user_id,org_oid,org_status,org_name,system_position_oid)
		values(v_userId,v_orgOid,v_orgStatus,v_orgName,v_systemPositionOid);
	END IF;
	#游标退出循环条件
	UNTIL done END REPEAT;
	#关闭游标
	CLOSE cur_auth;
	#提交
	COMMIT;
END