/**
 * 
 */
package com.yh.admin.res.queryhelper;

import java.util.List;

import com.yh.admin.dto.ResourcesDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DataConverUtils;

/**
 * 
 * @author	zhangqp
 * @version	1.0,	16/08/23
 */

public class ResourceQueryHelper {

	/**
	 * 系统所有资源
	 * @param systemCode
	 * @return
	 * @throws ServiceException 
	 */
	public static List<ResourcesDTO> listResources(String systemCode) throws ServiceException {
		
		return BeanHelper.copyProperties(DaoUtil.find("from Resources r where r.systemCode = ? ", systemCode), ResourcesDTO.class) ;
	}

	/**
	 * 用户所有资源
	 * @param systemCode
	 * @return
	 * @throws ServiceException
	 */
	public static List<ResourcesDTO> listUserResources(String userId) throws ServiceException {
		
		String sql = 
				  " select distinct r.RES_ID, r.RES_NAME, r.RES_TYPE, r.RES_VALUE, r.RES_DESC, r.SYSTEM_CODE "
				+ " from yhb_resources r,yhb_function_res fr,yhb_role_function rf,yhb_system_position sp,yhb_user_sp usp "
				+ " where r.res_id = fr.res_id "
				+ " and fr.func_id = rf.func_id "
				+ " and rf.role_id = sp.function_role_id "
				+ " and sp.system_position_oid = usp.system_position_oid "
				+ " and usp.user_id = ? "
				+ " and (usp.effective_date is null or usp.effective_date <= now()) "
				+ " and (usp.expired_date is null or usp.expired_date >= now())";
		
		List<Object[]> list = DaoUtil.findWithSQL(sql, userId);
		
		return BeanHelper.copyProperties(list, new BeanHelper.PropertiesHandler<Object[], ResourcesDTO>() {

			public ResourcesDTO getValue(Object[] src) throws ServiceException {
				ResourcesDTO dto = new ResourcesDTO();
				
				dto.setResId(DataConverUtils.toLong(src[0]));
				dto.setResName(DataConverUtils.toString(src[1]));
				dto.setResType(DataConverUtils.toString(src[2]));
				dto.setResValue(DataConverUtils.toString(src[3]));
				dto.setResDesc(DataConverUtils.toString(src[4]));
				dto.setSystemCode(DataConverUtils.toString(src[5]));
				
				return dto;
			}}) ;
	}

}
