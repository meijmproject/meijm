package com.yh.hr.select.post.facade;

import java.util.List;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.select.post.dto.PostSelectDTO;
import com.yh.hr.select.post.service.PostSelectService;
import com.yh.platform.core.exception.ServiceException;

/**
 * 人员选择
 * @author	zhangqp
 * @version	1.0,	16/11/09
 */
public class PostSelectFacade {
	private PostSelectService postSelectService;
    
	public void setPostSelectService(PostSelectService postSelectService) {
		this.postSelectService = postSelectService;
	}
	public List<PostSelectDTO> listPbPostInfo(TableTagBean ttb) throws ServiceException {
		return postSelectService.listPbPostInfo(ttb);
	}

}
