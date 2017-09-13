package com.yh.hr.res.pt.service;

import com.yh.hr.res.pt.dto.PtOfficialInfoDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * @desc 公务员登记 信息
 * @author luqy
 * @createDate 2016-11-5下午09:29:14
 */
public interface PtOfficialInfoService {
    

    /**
     * 获取公务员登记信息
     * @param pesonOid
     * @return
     * @throws ServiceException
     */
    public PtOfficialInfoDTO getPtOfficialInfoDTO(Long bizPersonOid) throws ServiceException;
    
}
