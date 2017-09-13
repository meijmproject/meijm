package com.yh.hr.res.pt.queryhelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yh.hr.res.pt.dto.PtPhotoDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * @desc 业务人员照片信息查询工具类
 * @author wangx
 * @date 2017-06-07
 */
public class PtPersonPhotoQueryHelper {

	public static List<PtPhotoDTO> listPtPersonPhoto(Long bizPersonOid)
			throws ServiceException {

		Map<String, Object> params = new HashMap<String, Object>();

		StringBuilder chql = new StringBuilder();

		chql.append("from PtPhoto p where 1=1 ");

		if (bizPersonOid != null) {
			chql.append(" and p.bizPersonOid = ").append(bizPersonOid);
		}
		//chql.append(" and p.photoType in('jpg','png','tif')");
		List<PtPhotoDTO> list = new ArrayList<PtPhotoDTO>();
		List<PtPhotoDTO> l = BeanHelper.copyProperties(DaoUtil.find(chql.toString(), params),PtPhotoDTO.class);
		if(l!=null) {
			list = l;
		}
		return list;
	}
}
