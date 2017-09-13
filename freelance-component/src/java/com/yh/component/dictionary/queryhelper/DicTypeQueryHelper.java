package com.yh.component.dictionary.queryhelper;

import java.util.List;

import com.yh.component.dictionary.bo.DicType;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.DataAccessFailureException;

public class DicTypeQueryHelper {

	public static DicType getDicTypeByTypeCode(String dicTypeCode) throws DataAccessFailureException {
		List<DicType> list = DaoUtil.findByNamed("from DicType dt where dt.dicTypeCode=:typeCode", "typeCode", dicTypeCode);

		if (list.isEmpty())
			return null;
		else
			return list.get(0);
	}
	
	public static List<DicType> list() throws DataAccessFailureException {
		return DaoUtil.find(" from DicType");
	}

}
