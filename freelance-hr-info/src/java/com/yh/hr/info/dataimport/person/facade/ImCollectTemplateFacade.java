package com.yh.hr.info.dataimport.person.facade;

import java.util.List;

import com.yh.hr.res.im.dto.ImCollectTemplateDTO;
import com.yh.hr.res.im.service.ImCollectTemplateService;
import com.yh.platform.core.exception.ServiceException;

public class ImCollectTemplateFacade {

	private ImCollectTemplateService imCollectTemplateService;

	public void setImCollectTemplateService(
			ImCollectTemplateService imCollectTemplateService) {
		this.imCollectTemplateService = imCollectTemplateService;
	}


	/**
	 *  通过主键获取采集项映射模板信息
	 * @param templateOid
	 */
	public ImCollectTemplateDTO getTemplateByOid(Long templateOid) throws ServiceException {
		return imCollectTemplateService.get(templateOid);
	}
	
	/**
	 *  获取所有采集项映射模板
	 * @return
	 * @throws ServiceExceptin
	 */
	public List<ImCollectTemplateDTO>  findAllCollTemplates() throws ServiceException {
		return imCollectTemplateService.findAllCollTemplates();
	}

	/**
	 *  获取所有有效的的采集项映射模板
	 * @return
	 * @throws ServiceExceptin
	 */
	public List<ImCollectTemplateDTO>  findEffectiveCollTemplates() throws ServiceException {
		return imCollectTemplateService.findEffectiveCollTemplates();
	}
	
	/**
	 * 更新采集项映射模板信息
	 * @param imCollectTemplateDTO
	 * @throws ServiceException
	 */
	public void update(ImCollectTemplateDTO imCollectTemplateDTO) throws ServiceException{
		imCollectTemplateService.update(imCollectTemplateDTO);
	}
	
	/**
	 * 通过数据库字段代码获取模板信息
	 * @param databaseColumnCode
	 * @return
	 * @throws ServiceException
	 */
	public ImCollectTemplateDTO findCollTemplateByColumnCode(String databaseColumnCode) throws ServiceException {
		return imCollectTemplateService.findCollTemplateByColumnCode(databaseColumnCode);
	}
	
	/**
	 * 获取所有已被映射的采集项映射模板
	 * @return
	 * @throws ServiceExceptin
	 */
	public List<ImCollectTemplateDTO> findCollTemplateForMapped() throws ServiceException{
		return  imCollectTemplateService.findCollTemplateForMapped();
	}
}
