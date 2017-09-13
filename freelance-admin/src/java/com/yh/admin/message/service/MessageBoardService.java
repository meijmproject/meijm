package com.yh.admin.message.service;

import java.util.List;

import com.yh.admin.message.dto.MessageBoardDto;
import com.yh.component.taglib.TableTagBean;
import com.yh.platform.core.exception.ServiceException;

public interface MessageBoardService {

	void create(MessageBoardDto messageBoardDto) throws ServiceException;

	List<MessageBoardDto> find(TableTagBean ttb) throws ServiceException;

	void update(MessageBoardDto messageBoardDto) throws ServiceException;

	void delete(Long messageOid) throws ServiceException;

}
