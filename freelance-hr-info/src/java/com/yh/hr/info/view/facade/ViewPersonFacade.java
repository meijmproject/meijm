package com.yh.hr.info.view.facade;

import java.util.List;

import com.yh.component.dictionary.bo.DicItem;
import com.yh.hr.res.pb.bo.PbPersonInfo;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.DataAccessFailureException;

public class ViewPersonFacade {

	public  List<PbPersonInfo> findAllPersonList() throws DataAccessFailureException {
		return DaoUtil.find(" from PbPersonInfo");
	}
	
	public List<DicItem> findInByPersonType(String personType) throws DataAccessFailureException{
		String sql="SELECT jdt.* FROM  YHA_DIC_ITEM jdi,YHA_DIC_TYPE jdt where jdi.dic_type_oid=jdt.dic_type_oid and  jdt.dic_type_code=?";
		return DaoUtil.findWithSQL(sql, personType);
	}
	
	

}
