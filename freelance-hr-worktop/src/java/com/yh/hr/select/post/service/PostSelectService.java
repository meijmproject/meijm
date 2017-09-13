package com.yh.hr.select.post.service;

import java.util.List;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.select.post.dto.PostSelectDTO;
import com.yh.platform.core.exception.ServiceException;

public interface PostSelectService {

	/**
	 * 获取人员列表
	 * @param ttb
	 * @return
	 * @throws ServiceException
	 */
	public List<PostSelectDTO> listPbPostInfo(TableTagBean ttb)throws ServiceException;
	
}
