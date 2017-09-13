drop FUNCTION if exists getAllChildMenuCode;

CREATE FUNCTION `getAllChildMenuCode`(`menuCode` varchar(20)) RETURNS varchar(2000) CHARSET utf8
    COMMENT '取得指定菜单编码下的所有子菜单编码'
BEGIN
	DECLARE mTemp varchar(2000);
  DECLARE mChildCode varchar(2000);

	SET mTemp = '$';
	SET mChildCode = menuCode;

	WHILE mChildCode IS NOT NULL DO
		SET mTemp = CONCAT(mTemp,',',mChildCode);
		SELECT GROUP_CONCAT(mm.menu_code) INTO mChildCode FROM JHD_MT_MENU mm 
		WHERE mm.menu_code<>MM.parent_menu_code AND FIND_IN_SET(mm.parent_menu_code,mChildCode);
	END WHILE;

	RETURN mTemp;
END