package com.yh.hr.component.gb.facade;

import java.util.List;

import com.yh.hr.component.gb.dto.GbDescriptionDTO;
import com.yh.hr.component.gb.service.impl.GbDescriptionService;
import net.sf.json.JSONObject;
import com.yh.component.taglib.TableTagBean;
import com.yh.platform.core.exception.ServiceException;

public class GbDescriptionFacade {
	
	private GbDescriptionService gbDescriptionService;


	public void setGbDescriptionService(GbDescriptionService gbDescriptionService) {
		this.gbDescriptionService = gbDescriptionService;
	}

	/**
	 * 新增
	 */
	public void createGbDescription(GbDescriptionDTO gbDescriptionDTO) throws ServiceException
	{
		gbDescriptionService.createGbDescription(gbDescriptionDTO);
	}
	
	/**
	 * 查看
	 */
	public GbDescriptionDTO findGbDescriptionById(Long jhgGbDescriptionOid) throws ServiceException
	{
		return gbDescriptionService.findGbDescriptionById(jhgGbDescriptionOid);
	}
	
	/**
	 * 修改
	 */
	public void updateGbDescription(GbDescriptionDTO gbDescriptionDTO) throws ServiceException
	{
		gbDescriptionService.updateGbDescription(gbDescriptionDTO);
	}
	
	/**
	 * 删除
	 */
	public void deleteGbDescription(Long gbDescriptionOid) throws ServiceException
	{
		gbDescriptionService.deleteGbDescription(gbDescriptionOid);
	}

	public List<JSONObject> listGbDescription(TableTagBean ttb)
			throws ServiceException {
		return gbDescriptionService.listGbDescription(ttb);
	}
}
