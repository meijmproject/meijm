package com.yh.hr.select.post.queryhelper;

import java.util.List;

import com.yh.hr.select.post.dto.PostSelectDTO;
import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.res.dictionary.DicConstants;
import com.yh.hr.res.pb.bo.PbPostInfo;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;


public class PostSelectQueryHelper {
	
	/*
	 * 通过ID获取,不包含当前
	 */
	public static List<PostSelectDTO> findCurrentDutyExclusive(Long personOid, Long postOid) throws ServiceException {
		if(null==personOid)
		{
			return null;
		}
		String hql =  "from PbPostInfo pb where pb.positionStatus="+ DicConstants.YHRS0026_001+" and pb.personOid= "+personOid;
		if(null!=postOid&&0!=postOid)
		{
			hql+=" and pb.postOid!="+postOid;
		}
		List<PbPostInfo> list = DaoUtil.find(hql);
		if(CollectionUtils.isEmpty(list))
		{
			return null;
		}
		else if(list.size()>2)
		{
			throw new ServiceException(null,"获取到超过2条在任岗位信息!");
		}

		return BeanHelper.copyProperties(list, PostSelectDTO.class);
	}
}
