package com.yh.admin.message.service.impl;

import java.util.List;

import com.yh.admin.message.bo.MessageBoard;
import com.yh.admin.message.dto.MessageBoardDto;
import com.yh.admin.message.queryhelper.MessageBoardQueryHelper;
import com.yh.admin.message.service.MessageBoardService;
import com.yh.component.taglib.TableTagBean;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.web.UserContext;

public class MessageBoardServiceImpl implements MessageBoardService {

	public void create(MessageBoardDto messageBoardDto) throws ServiceException {
		MessageBoard messageBoard = new MessageBoard();
		BeanHelper.copyProperties(messageBoardDto, messageBoard);
		messageBoard.setCreatedBy(UserContext.getLoginUserName());
		messageBoard.setCreatedDate(DateUtil.now());
		messageBoard.save();
	}

	public List<MessageBoardDto> find(TableTagBean ttb) throws ServiceException {
		return MessageBoardQueryHelper.find(ttb);
	}

	public void update(MessageBoardDto messageBoardDto) throws ServiceException {
		MessageBoard messageBoard = DaoUtil.get(MessageBoard.class, messageBoardDto.getMessageOid());
		BeanHelper.copyProperties(messageBoardDto, messageBoard, BeanHelper.getNullPropertyNames(messageBoardDto));
		messageBoard.update();
	}

	public void delete(Long messageOid) throws ServiceException {
		DaoUtil.get(MessageBoard.class, messageOid).delete();
	}

	/*public PbDeathInfoDTO get(java.lang.Long pbDeathInfoId) throws ServiceException {
        return BeanHelper.copyProperties(DaoUtil.get(PbDeathInfo.class, pbDeathInfoId),PbDeathInfoDTO.class);
	}
    
	public void update(PbDeathInfoDTO pbDeathInfoDto) throws ServiceException {
		PbDeathInfo pbDeathInfo = DaoUtil.get(PbDeathInfo.class, pbDeathInfoDto.getPersonOid());
		BeanHelper.copyProperties(pbDeathInfoDto, pbDeathInfo, BeanHelper.getNullPropertyNames(pbDeathInfoDto));
		pbDeathInfo.setUpdateBy(UserContext.getLoginUserID());
		pbDeathInfo.setUpdateName(UserContext.getLoginUserName());
		pbDeathInfo.setUpdateDate(DateUtil.now());
		pbDeathInfo.update();
	}
    
	public void delete(java.lang.Long pbDeathInfoId) throws ServiceException {
      DaoUtil.get(PbDeathInfo.class, pbDeathInfoId).delete();
	}*/

}
