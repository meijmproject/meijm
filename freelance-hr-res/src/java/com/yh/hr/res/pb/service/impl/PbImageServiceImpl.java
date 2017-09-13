package com.yh.hr.res.pb.service.impl;

import java.util.Date;

import com.yh.hr.res.pb.dto.PbImageDTO;
import org.apache.struts.upload.FormFile;

import com.yh.hr.res.pb.bo.PbImage;
import com.yh.hr.res.pb.service.PbImageService;
import com.yh.platform.core.constant.Constant;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.web.UserContext;

/**
 *@description 照片信息
 *@author      weizh
 *@created     2016-08-17
 *@version     1.0
 */
public class PbImageServiceImpl implements PbImageService {

    /**
     * 获取照片信息
     *
     * @param pesonOid
     * @return
     * @throws ServiceException
     */
    public PbImageDTO getPbImage(Long pesonOid) throws ServiceException {
        try {
            PbImage pbImage = DaoUtil.get(PbImage.class, pesonOid);
            return BeanHelper.copyProperties(pbImage,PbImageDTO.class);
        } catch (Exception e) {
            throw new ServiceException(null,"获取照片信息失败!");
        }
    }

    /**
     * 更新或插入照片
     * @param image
     * @param personOid
     */
    public void insertOrUpdateImage(FormFile image,Long personOid) throws ServiceException
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

                    PbImage pbImage = DaoUtil.get(PbImage.class,personOid);
                    String fileName = image.getFileName();
                    int i = fileName.lastIndexOf(".");
                    fileName = fileName.substring(i + 1);
                    if (null==pbImage)
                    {
                        pbImage = new PbImage();
                        pbImage.setPhotoType(fileName.toLowerCase());
                        pbImage.setPersonOid(personOid);
                        pbImage.setPhotoPath(bs);
                        pbImage.setCreateBy(UserContext.getLoginUserID());
                        pbImage.setCreateName(UserContext.getLoginUserName());
                        pbImage.setCreateDate(DateUtil.now());
                        pbImage.save();
                        PbImage pbImage1 = DaoUtil.get(PbImage.class,personOid);
                        System.out.println(pbImage1);
                    }
                    else
                    {
                        pbImage.setPhotoType(fileName.toLowerCase());
                        pbImage.setPersonOid(personOid);
                        pbImage.setPhotoPath(bs);
                        pbImage.setUpdateBy(UserContext.getLoginUserID());
                        pbImage.setUpdateName(UserContext.getLoginUserName());
                        pbImage.setUpdateDate(DateUtil.now());
                        pbImage.update();
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

	public void create(PbImageDTO pbImageDTO) throws ServiceException {
		PbImage pbImage = BeanHelper.copyProperties(pbImageDTO, PbImage.class);
		//得到操作人信息
		String userId = UserContext.getLoginUserID();
		String userName = UserContext.getLoginUserName();
		//当前日期
		Date now = new Date();
		//设置新增人信息和修改人信息
		pbImage.setCreateBy(userId);
		pbImage.setCreateName(userName);
		pbImage.setCreateDate(now);
		pbImage.save();
	}
}
