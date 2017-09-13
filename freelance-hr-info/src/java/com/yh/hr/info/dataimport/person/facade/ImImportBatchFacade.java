package com.yh.hr.info.dataimport.person.facade;

import com.yh.hr.res.im.dto.ImImportBatchDTO;
import com.yh.hr.res.im.service.ImImportBatchService;
import com.yh.platform.core.exception.ServiceException;

public class ImImportBatchFacade {

	private ImImportBatchService imImportBatchService;

	public void setImImportBatchService(ImImportBatchService imImportBatchService) {
		this.imImportBatchService = imImportBatchService;
	}

	/**
	 * 创建导入批次信息
	 * @param imImportBatchDTO
	 * @throws ServiceException
	 */
	public Long create(ImImportBatchDTO imImportBatchDTO) throws ServiceException{
		Long importBatchOid =imImportBatchService.create(imImportBatchDTO);
		return importBatchOid;
	}
	
	/**
	 * 取得当前最新导入批次信息
	 * @return
	 * @throws ServiceException
	 */
	public ImImportBatchDTO findCurrentImImportBatchDTO() throws ServiceException{
		return imImportBatchService.getCurrentImportBatchDTO();
	}
	
	/**
	 * 更新导入批次信息
	 * @param imImportBatchDTO
	 * @throws ServiceException
	 */
	public void update(ImImportBatchDTO imImportBatchDTO) throws ServiceException{
		imImportBatchService.update(imImportBatchDTO);
	}
}
