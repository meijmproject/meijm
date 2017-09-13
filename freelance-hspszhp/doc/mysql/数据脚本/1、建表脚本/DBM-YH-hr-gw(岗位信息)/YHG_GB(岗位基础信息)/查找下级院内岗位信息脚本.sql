DROP FUNCTION IF EXISTS `GET_ALL_CHILD_YNGW`;

CREATE FUNCTION `GET_ALL_CHILD_YNGW`(`position_oid` bigint) RETURNS varchar(1000) CHARSET utf8
    COMMENT '取得当前院内岗位及其所有下级院内岗位OID'
BEGIN
	DECLARE sTemp VARCHAR(1000);  
  DECLARE sTempOid VARCHAR(1000);
	
	SET sTemp = position_oid;  
	SET sTempOid = position_oid;  

	read_loop:LOOP
		select group_concat(gyi2.position_oid) into sTempOid from yhg_gb_yn_info gyi1,yhg_gb_yn_info gyi2 where gyi1.position_oid = gyi2.parent_position_oid
		and FIND_IN_SET(gyi2.parent_position_oid,sTempOid)>0;
		IF sTempOid is null then
			LEAVE read_loop;
		END IF;
		set sTemp = CONCAT(sTemp,',',sTempOid);
  END LOOP;
	RETURN sTemp;
END