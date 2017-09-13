/**
 * 
 */
package com.yh.hr.info.biz.unit.workbench.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.jdbc.datasource.DataSourceUtils;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.info.ver.unit.workbench.dto.VerPersonDTO;
import com.yh.hr.info.ver.unit.workbench.queryhelper.VerPbPersonWorkbenchQueryHelper;
import com.yh.hr.res.unit.dto.UtOrgDTO;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.SpringBeanUtil;

/**
 * 
 * @author	zhangqp
 * @version	1.0,	16/08/16
 */

public class VerPbPersonWorkbenchService {
	
	public List<VerPersonDTO> listVerPerson(TableTagBean ttb) throws ServiceException {
		
		return VerPbPersonWorkbenchQueryHelper.listVerPerson(ttb);
	}

	/**
	 * 校核人员信息
	 * @param personOid
	 * @return
	 * @throws ServiceException
	 */
	public String updateVerPersonResult(Long personOid) throws ServiceException {
		String verResult=null;
		CallableStatement cstmt = null;
		Connection conn = null;
		conn = DataSourceUtils.getConnection(SpringBeanUtil.getJdbcTemplate().getDataSource());
		try {
			cstmt = conn.prepareCall("{call checkpersonmigrationdatas.checkpersoninfo(?,?)}");
			cstmt.setLong(1, personOid);

			cstmt.registerOutParameter(2, Types.VARCHAR);
			cstmt.executeUpdate();
			verResult = String.valueOf((cstmt.getString(2)));
		
			cstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return verResult;
	}
	/**
	 * 分组-科室
	 * @param orgOid
	 * @param organizationOid
	 * @return
	 * @throws ServiceException
	 */
	public List<UtOrgDTO> listLeafOrg(String orgOid, String organizationOid) throws ServiceException{
		return VerPbPersonWorkbenchQueryHelper.listLeafOrg(orgOid,organizationOid);
	}

	public List<VerPersonDTO> listVerPersonByOrgOid(Long orgOid) throws ServiceException{
		return VerPbPersonWorkbenchQueryHelper.listVerPersonByOrgOid(orgOid);
	}
}
