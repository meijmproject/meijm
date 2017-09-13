package com.yh.hr.res.pt.service.impl;

import java.util.Date;

import com.yh.hr.res.pt.dto.PtImageDTO;
import org.apache.struts.upload.FormFile;

import com.yh.hr.res.pt.bo.PtImage;
import com.yh.hr.res.pt.service.PtImageService;
import com.yh.platform.core.constant.Constant;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.web.UserContext;

/**
 *@description 照片信息
 *@author      xiongyx
 *@created     2016-10-09
 *@version     1.0
 */
public class PtImageServiceImpl implements PtImageService {

    /**
     * 获取照片信息
     *
     * @param bizPersonOid
     * @return
     * @throws ServiceException
     */
    public PtImageDTO getPtImage(Long bizPersonOid) throws ServiceException {
        try {
            PtImage PtImage = DaoUtil.get(PtImage.class, bizPersonOid);
            return BeanHelper.copyProperties(PtImage,PtImageDTO.class);
        } catch (Exception e) {
            throw new ServiceException(null,"获取照片信息失败!");
        }
    }

    /**
     * 更新或插入照片
     * @param image
     * @param bizPersonOid
     */
    public void insertOrUpdateImage(FormFile image,Long bizPersonOid) throws ServiceException
    {
        try {
            if (image != null)
            {
                byte[] bs = image.getFileData();
                if (bs.length > 0)
                {
                    if (!isValidFile(image.getFileName())) {
                        throw new ServiceException(null, "照片类型不正确!");
                    }

                    int fileSize = image.getFileSize();
                    if (fileSize > Constant.FILE_MAX_SIZE * 1024) {
                        throw new ServiceException(null, "上传照片超过了"+Constant.FILE_MAX_SIZE+"K");
                    }

                    PtImage PtImage = DaoUtil.get(PtImage.class,bizPersonOid);
                    String fileName = image.getFileName();
                    int i = fileName.lastIndexOf(".");
                    fileName = fileName.substring(i + 1);
                    if (null==PtImage)
                    {
                        PtImage = new PtImage();
                        PtImage.setPhotoType(fileName.toLowerCase());
                        PtImage.setBizPersonOid(bizPersonOid);
                        PtImage.setPhotoPath(bs);
                        PtImage.setCreatedByCode(UserContext.getLoginUserID());
                        PtImage.setCreatedByName(UserContext.getLoginUserName());
                        PtImage.setCreatedDate(DateUtil.now());
                        PtImage.save();
                        PtImage PtImage1 = DaoUtil.get(PtImage.class,bizPersonOid);
                        System.out.println(PtImage1);
                    }
                    else
                    {
                        PtImage.setPhotoType(fileName.toLowerCase());
                        PtImage.setBizPersonOid(bizPersonOid);
                        PtImage.setPhotoPath(bs);
                        PtImage.setUpdatedByCode(UserContext.getLoginUserID());
                        PtImage.setUpdatedByName(UserContext.getLoginUserName());
                        PtImage.setUpdatedDate(DateUtil.now());
                        PtImage.update();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(null,e.getMessage());
        }
    }
    /**
     * 验证上传的文件格式
     */
    private boolean isValidFile(String fileName)
    {
        String[] validFiles = { "gif", "jpg", "jpeg", "jpe", "tif", "tiff", "png", "bmp", "dib" };
        boolean ret = false;
        for (int i = 0; i < validFiles.length; i++)
        {
            if (fileName.toLowerCase().endsWith(validFiles[i]))
            {
                ret = true;
                break;
            }
        }
        return ret;
    }

	public void createPtImageInfo(PtImageDTO ptImageDTO)throws ServiceException {
		PtImage ptImage = BeanHelper.copyProperties(ptImageDTO, PtImage.class);
		//得到操作人信息
		String userId = UserContext.getLoginUserID();
		String userName = UserContext.getLoginUserName();
		//当前日期
		Date now = new Date();
		//设置新增人信息和修改人信息
		ptImage.setCreatedByCode(userId);
		ptImage.setCreatedByName(userName);
		ptImage.setCreatedDate(now);
		ptImage.save();
	}
}
