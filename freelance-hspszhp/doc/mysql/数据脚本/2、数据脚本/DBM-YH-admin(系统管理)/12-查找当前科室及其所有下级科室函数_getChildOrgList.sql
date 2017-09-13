-- ----------------------------
-- Function structure for getChildOrgList
-- ----------------------------
DROP FUNCTION IF EXISTS `getChildOrgList`;
DELIMITER ;;
CREATE  FUNCTION `getChildOrgList`(`orgOid` varchar(12)) RETURNS varchar(1000) CHARSET utf8
    COMMENT '获取当前及其下级所有科室OID'
BEGIN
	DECLARE sTemp text;  
  DECLARE sTempOid text;
	
	SET sTemp = orgOid;  
  SET sTempOid =orgOid;

	REPEAT
		select group_concat(ju.org_oid) into sTempOid from yhc_ut_org juo,yhc_ut_relation jur,yhc_ut_org ju where juo.organization_oid = jur.parent_organization_oid
		and ju.organization_oid = jur.child_organization_oid and jur.relation_type = '2'
		and FIND_IN_SET(juo.org_oid,sTempOid)>0;
		IF sTempOid is not null THEN
			set sTemp = CONCAT(sTemp,',',sTempOid);
		END IF;
  UNTIL sTempOid is NULL END REPEAT;
	RETURN sTemp;
END
;;
DELIMITER ;