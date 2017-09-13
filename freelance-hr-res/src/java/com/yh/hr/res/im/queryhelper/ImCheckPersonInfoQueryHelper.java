package com.yh.hr.res.im.queryhelper;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.res.im.dto.ImCheckPersonInfoDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

public class ImCheckPersonInfoQueryHelper {

	/**
	 * 通过导入批次OID查询该批次的校核人员
	 * @param importBatchOid
	 * @return
	 * @throws ServiceException
	 */
	public static List<ImCheckPersonInfoDTO> findImCheckPersonInfoDTOListByBatchOid(Long importBatchOid) throws ServiceException {
		String hql = "from ImCheckPersonInfo cpi where cpi.importBatchOid=?";
		return BeanHelper.copyProperties(DaoUtil.find(hql, importBatchOid), ImCheckPersonInfoDTO.class);
	}
	
	/**
	 * 通过姓名和出生日期查询当前批次的校核人员
	 * @param importBatchOid
	 * @param name
	 * @param birthday
	 * @return
	 * @throws ServiceException
	 */
	public static ImCheckPersonInfoDTO findImCheckPersonInfoDTOByBatchOidAndNameAndBirthday(Long importBatchOid, String name, String birthday) throws ServiceException {
		String hql = "from ImCheckPersonInfo cpi where cpi.importBatchOid=? and cpi.name=? and date_format(cpi.birthday,'%Y-%m-%d')=?";
		return BeanHelper.copyProperties(DaoUtil.uniqueResult(hql, importBatchOid, name, birthday), ImCheckPersonInfoDTO.class);
	}
	
	/**
	 * 检查传入的字段名在校核人员中是否存在
	 * @param databaseColumnCode
	 * @return
	 * @throws ServiceException
	 */
	public static void checkColumnExist(String databaseColumnCode) throws ServiceException {
		String sql = "SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS WHERE COLUMN_NAME = '"+databaseColumnCode+"' AND TABLE_NAME = 'YHC_IM_CHECK_PERSON_INFO'";
		List<Object[]> list = DaoUtil.findWithSQL(sql);
		if(CollectionUtils.isEmpty(list)) {
			throw new ServiceException(null,"校核人员表"+databaseColumnCode+"字段不存在！"); 
		}
	}
	
	/**
	 * 通过字段名获取该校核人员该字段的值
	 * @param checkPersonInfoOid
	 * @param databaseColumnCode
	 * @return
	 * @throws ServiceException
	 */
	public static Object getColumnValueByColumnCode(Long checkPersonInfoOid, String databaseColumnCode) throws ServiceException {
		String sql = "select cpi." + databaseColumnCode +" from yhc_im_check_person_info cpi where cpi.CHECK_PERSON_INFO_OID = " + checkPersonInfoOid;
		List<Object> list = DaoUtil.findWithSQL(sql);
		if(CollectionUtils.isNotEmpty(list)) {
			return list.get(0);
		}
		return null;
	}
}
