package com.yh.hr.res.pb.queryhelper;

import com.yh.hr.res.pb.bo.PbResumeInfo;
import com.yh.hr.res.pb.dto.PbResumeInfoDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @desc 合同变动历史信息查询工具类
 * @author liul
 * @createDate 2017-2-17
 */
public class PbResumeInfoQueryHelper
{

	/**
	 * 用人员id查找该人员的PbResumeInfo信息
	 * @param personOid
	 * @return
	 * @throws com.yh.platform.core.exception.ServiceException
	 */
	public static List<PbResumeInfoDTO> listPbResumeInfoByPersonOid(Long personOid) throws ServiceException {
		List<PbResumeInfo> list = DaoUtil.findByNamed("from PbResumeInfo ri where ri.personOid =:personOid order by ri.startDate desc", "personOid", personOid);
		List<PbResumeInfoDTO> listDTO = new ArrayList<PbResumeInfoDTO>();
		if(CollectionUtils.isEmpty(list)) {
			return null;
		}else{
			for(PbResumeInfo pbResumeInfo:list){
				PbResumeInfoDTO pbResumeInfoDTO = new PbResumeInfoDTO();
				BeanHelper.copyProperties(pbResumeInfo, pbResumeInfoDTO);
				listDTO.add(pbResumeInfoDTO);
			}
			return listDTO;
		}
	}
}
