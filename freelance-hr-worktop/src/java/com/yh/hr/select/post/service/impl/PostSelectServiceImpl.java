package com.yh.hr.select.post.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.res.pb.dto.PbPositioningDTO;
import com.yh.hr.res.pb.queryhelper.PbPositioningInfoQueryHelper;
import com.yh.hr.res.pt.dto.PtPersonDTO;
import com.yh.hr.res.pt.dto.PtPostInfoDisDTO;
import com.yh.hr.res.pt.queryhelper.PtPersonQueryHelper;
import com.yh.hr.res.pt.queryhelper.PtPostInfoDisQueryHelper;
import com.yh.hr.select.post.dto.PostSelectDTO;
import com.yh.hr.select.post.queryhelper.PostSelectQueryHelper;
import com.yh.hr.select.post.service.PostSelectService;
import com.yh.platform.core.exception.ServiceException;

public class PostSelectServiceImpl implements PostSelectService{

	public List<PostSelectDTO> listPbPostInfo(TableTagBean ttb) throws ServiceException {
		String bizPersonOid = ttb.getCondition().get("bizPersonOid");
		Long personOid=0L;
		Long postOid=0L;
		if(null!=bizPersonOid){
			PtPersonDTO dto=PtPersonQueryHelper.getPtPersonDTOById(Long.valueOf(bizPersonOid));
			if(null!=dto){personOid=dto.getPersonOid();}			
		}
		
		List<PtPostInfoDisDTO> list=PtPostInfoDisQueryHelper.listPtPostInfoDisByBizPersonOid(Long.valueOf(bizPersonOid));
		if(null!=list){
		if(list.size()==2){throw new ServiceException("已存在两条在任拟免岗位信息");}
		else if(list.size()!=0){postOid=list.get(0).getPostOid();}
		}
		List<PostSelectDTO> listPost=null;
		if(null!=personOid){
			listPost=PostSelectQueryHelper.findCurrentDutyExclusive(personOid, postOid);
			if(CollectionUtils.isNotEmpty(listPost)){
				for (PostSelectDTO pbPostDTO : listPost) {
					PbPositioningDTO pbPositioningDTO=PbPositioningInfoQueryHelper.getPbPositioningInfoDTOById(pbPostDTO.getPositioningOid());
					pbPostDTO.setDutyLevel(pbPositioningDTO.getDutyLevel());
				}
			}
		}
		return listPost;
	}

}
