package com.yh.hr.info.ver.unit.comm.facade;

import java.util.List;

import com.yh.hr.info.ver.unit.comm.dto.VerPbPhotoDTO;
import com.yh.hr.res.pb.service.PbPhotoService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 *@description 影像信息
 *@author      cheny
 *@created     2017-03-14
 *@version     1.0
 */
public class VerPbPhotoFacade {

    private PbPhotoService pbPhotoService;

    public void setPbPhotoService(PbPhotoService pbPhotoService) {
        this.pbPhotoService = pbPhotoService;
    }

    /**
     * 获取影像信息
     *
     * @param personOid
     * @return
     * @throws ServiceException
     */
    public List<VerPbPhotoDTO> listPersonPhoto(Long personOid) throws ServiceException {
        return BeanHelper.copyProperties(pbPhotoService.listPersonPhoto(personOid),VerPbPhotoDTO.class);
    }
}
