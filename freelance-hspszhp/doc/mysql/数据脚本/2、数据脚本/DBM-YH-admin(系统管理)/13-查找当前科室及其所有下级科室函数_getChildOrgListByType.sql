-- ----------------------------
-- Function structure for getChildOrgListByType
-- ----------------------------
DROP FUNCTION IF EXISTS `getChildOrgListByType`;
DELIMITER ;;
CREATE  FUNCTION `getChildOrgListByType`(`orgType` varchar(100)) RETURNS varchar(1000) CHARSET utf8
    COMMENT '通过科室类型获取科室及其下级科室OID'
BEGIN
	DECLARE sTemp text;  
  DECLARE sTempOid text;
	
  SET sTempOid = '';

	select group_concat(ju.org_oid) into sTempOid from yhc_ut_org juo,yhc_ut_relation jur,yhc_ut_org ju where juo.organization_oid = jur.parent_organization_oid
	and ju.organization_oid = jur.child_organization_oid and jur.relation_type = '2' and FIND_IN_SET(juo.org_type,orgType);

	SET sTemp = sTempOid;  

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