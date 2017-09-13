package com.yh.admin.message.facade;

import java.util.List;

import com.yh.admin.message.dto.MessageBoardDto;
import com.yh.component.taglib.TableTagBean;
import com.yh.platform.core.exception.ServiceException;

public interface MessageBoardFacade {

	/**
	 * 创建通知通告
	 * @param messageBoardDto
	 * @throws ServiceException
	 */
	void create(MessageBoardDto messageBoardDto) throws ServiceException;

	/**
	 * 查询通知通告列表
	 * @param ttb
	 * @return
	 * @throws ServiceException 
	 */
	List<MessageBoardDto> find(TableTagBean ttb) throws ServiceException;

	/**
	 * 更新通知通告
	 * @param messageBoardDto
	 * @throws ServiceException
	 */
	void update(MessageBoardDto messageBoardDto) throws ServiceException;

	/**
	 * 删除通知通告
	 * @param messageOids
	 * @throws ServiceException
	 */
	void delete(Long messageOid) throws ServiceException;
	
}
