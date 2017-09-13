package com.yh.hr.info.ver.unit.comm.facade;

import com.yh.hr.info.ver.unit.comm.dto.VerPbImageDTO;
import com.yh.hr.res.pb.service.PbImageService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 *@description 照片信息
 *@author      weizh
 *@created     2016-08-17
 *@version     1.0
 */
public class VerPbImageFacade {

    private PbImageService pbImageService;

    public void setPbImageService(PbImageService pbImageService) {
        this.pbImageService = pbImageService;
    }

    /**
     * 获取照片信息
     *
     * @param personOid
     * @return
     * @throws ServiceException
     */
    public VerPbImageDTO getPbImage(Long personOid) throws ServiceException {
        return BeanHelper.copyProperties(pbImageService.getPbImage(personOid),VerPbImageDTO.class);
    }
}
