package com.yh.hr.res.pb.queryhelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.yh.hr.res.pb.dto.PbPhotoDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * @desc 人员基础信息查询工具类
 * @author luqy
 * @createDate 2016-8-16上午09:53:23
 */
public class PbPersonPhotoQueryHelper {

	public static List<PbPhotoDTO> listPbPersonPhoto(Long personOid)
			throws ServiceException {

		Map<String, Object> params = new HashMap<String, Object>();

		StringBuilder chql = new StringBuilder();

		chql.append("from PbPhoto p where 1=1 ");

		if (personOid != null) {
			chql.append(" and p.personOid = ").append(personOid);
		}
		chql.append(" and p.photoType in('jpg','png','tif')");
		return BeanHelper.copyProperties(DaoUtil.find(chql.toString(), params),PbPhotoDTO.class);
	}

}
