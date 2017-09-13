package com.yh.hr.info.dataimport.person.facade;

import java.util.List;

import com.yh.hr.component.im.dto.ColumnDefDTO;
import com.yh.hr.info.dataimport.person.service.ExportPersonService;
import com.yh.hr.res.im.dto.ImCheckPersonUnusualDTO;
import com.yh.hr.res.im.dto.ImImportBatchDTO;
import com.yh.hr.res.im.service.ImCheckPersonUnusualService;
import com.yh.hr.res.im.service.ImImportBatchService;
import com.yh.platform.core.exception.ServiceException;

public class ExportPersonFacade {

	private ExportPersonService exportPersonService;

	private ImImportBatchService  imImportBatchService;
	private ImCheckPersonUnusualService imCheckPersonUnusualService;
	
	public void setExportPersonService(ExportPersonService exportPersonService) {
		this.exportPersonService = exportPersonService;
	}

	public void setImImportBatchService(ImImportBatchService imImportBatchService) {
		this.imImportBatchService = imImportBatchService;
	}


	public void setImCheckPersonUnusualService(
			ImCheckPersonUnusualService imCheckPersonUnusualService) {
		this.imCheckPersonUnusualService = imCheckPersonUnusualService;
	}

	public List<List<ColumnDefDTO>> listDicPerson() throws Exception{
		return exportPersonService.listDicPerson();
	}
	
	/**
	 * 获取当前最新的导入批次
	 * @return
	 * @throws ServiceException
	 */
	public ImImportBatchDTO getCurrentImportBatchDTO() throws Exception {
		return imImportBatchService.getCurrentImportBatchDTO();
	}
	
	/**
	 * 通过导入批次OID和校核人员OID查询该人员检查异常明细
	 * @param importBatchOid
	 * @param checkPersonInfoOid
	 * @return
	 * @throws ServiceException
	 */
	public List<ImCheckPersonUnusualDTO> findImCheckPersonUnusualByBatchOidAndPersonOid(Long importBatchOid, Long checkPersonInfoOid) throws ServiceException {
		return imCheckPersonUnusualService.findImCheckPersonUnusualByBatchOidAndPersonOid(importBatchOid, checkPersonInfoOid);
	}
}
