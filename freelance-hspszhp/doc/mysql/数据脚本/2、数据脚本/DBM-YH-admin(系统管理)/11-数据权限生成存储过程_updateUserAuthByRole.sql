drop PROCEDURE if exists `updateUserAuthByRole`;

CREATE PROCEDURE `updateUserAuthByRole`(IN `p_roleId` bigint)
BEGIN
	#定义用户ID
	DECLARE v_user_id varchar(100);

	#创建结束标志变量
	DECLARE done int default false;

	#定义游标-通过数据角色ID获取对应的用户ID
	DECLARE cur_user CURSOR 
	FOR select distinct u.user_id from yhb_user_sp u,yhb_system_position s
	where u.system_position_oid=s.system_position_oid and s.data_role_id=p_roleId; 

	#指定游标循环结束时的返回值
	declare continue HANDLER for not found set done = true;

	#打开游标
	OPEN cur_user;
	#开始遍历游标
	read_loop:LOOP
	#获取当前游标指向的值存入变量
	FETCH cur_user INTO v_user_id;
	#判断游标的循环是否结束  
	if done then
		#跳出游标循环  
		leave read_loop;
	end if; 
	#调用授权的存储过程
	CALL updateUserAuth(v_user_id);
	#结束游标循环
	END LOOP;
	#关闭游标
	CLOSE cur_user;

END