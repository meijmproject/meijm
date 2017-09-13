package com.yh.admin.message.facade.impl;

import java.util.List;

import com.yh.admin.message.dto.MessageBoardDto;
import com.yh.admin.message.facade.MessageBoardFacade;
import com.yh.admin.message.service.MessageBoardService;
import com.yh.component.taglib.TableTagBean;
import com.yh.platform.core.exception.ServiceException;

public class MessageBoardFacadeImpl implements MessageBoardFacade {
	
	private MessageBoardService messageBoardService;

	public void setMessageBoardService(MessageBoardService messageBoardService) {
		this.messageBoardService = messageBoardService;
	}

	public void create(MessageBoardDto messageBoardDto) throws ServiceException {
		messageBoardService.create(messageBoardDto);
	}

	public List<MessageBoardDto> find(TableTagBean ttb) throws ServiceException {
		return messageBoardService.find(ttb);
	}

	public void update(MessageBoardDto messageBoardDto) throws ServiceException {
		messageBoardService.update(messageBoardDto);
	}

	public void delete(Long messageOid) throws ServiceException {
		messageBoardService.delete(messageOid);
	}
}
