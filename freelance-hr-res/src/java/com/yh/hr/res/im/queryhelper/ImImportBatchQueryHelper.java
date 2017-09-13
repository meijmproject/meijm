package com.yh.hr.res.im.queryhelper;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.res.im.bo.ImImportBatch;
import com.yh.hr.res.im.dto.ImImportBatchDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

public class ImImportBatchQueryHelper {

	/**
	 * 查询所有的导入批次
	 * @return
	 * @throws ServiceException
	 */
	public static List<ImImportBatchDTO> findAllImImportBatchDTO() throws ServiceException {
		return BeanHelper.copyProperties(DaoUtil.find("from ImImportBatch"), ImImportBatchDTO.class);
	}
	
	/**
	 * 获取当前最新的导入批次
	 * @return
	 * @throws ServiceException
	 */
	public static ImImportBatchDTO getCurrentImportBatchDTO() throws ServiceException {
		String hql = "from ImImportBatch iib where iib.effectiveFlag = '1' order by iib.startTime desc";
		List<ImImportBatch> list = DaoUtil.find(hql);
		if(CollectionUtils.isNotEmpty(list)) {
			return BeanHelper.copyProperties(list.get(0), ImImportBatchDTO.class);
		}
		return null;
	}
}
