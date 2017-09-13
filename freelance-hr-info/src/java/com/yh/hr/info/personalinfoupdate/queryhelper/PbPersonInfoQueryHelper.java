package com.yh.hr.info.personalinfoupdate.queryhelper;

import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import com.yh.hr.component.util.AdminConstants;
import com.yh.hr.res.pb.dto.PbPersonInfoDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.DataAccessFailureException;
import com.yh.platform.core.web.UserContext;

public class PbPersonInfoQueryHelper {
	/**
	 * 根据登录人查询人员Id
	 * @param unitOid
	 * @param powerControl 
	 * @return
	 * @throws DataAccessFailureException
	 */
	public static PbPersonInfoDTO getPbPersonInfoDTO() throws DataAccessFailureException
	{
		StringBuffer sql = new StringBuffer("SELECT jur.REF_OID,jur.USER_ID FROM yhb_user_relation jur WHERE jur.REF_TYPE='"+AdminConstants.REF_TYPE_02+"' AND jur.USER_ID='"+UserContext.getLoginUserID()+"'");
		List<Object[]> list= DaoUtil.findWithSQL(sql.toString());
		if (CollectionUtils.isNotEmpty(list)) 
		{
			for (Object[] objs : list) 
			{
				PbPersonInfoDTO dto = new PbPersonInfoDTO();
				dto.setPersonOid(objs[0] == null ? null : Long.valueOf(objs[0].toString()));
				return dto;
			}
		}
		return null;
	}
}
