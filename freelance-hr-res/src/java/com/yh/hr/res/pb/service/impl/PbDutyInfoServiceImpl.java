package com.yh.hr.res.pb.service.impl;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.hr.res.pb.bo.PbDutyInfo;
import com.yh.hr.res.pb.dto.PbDutyInfoDTO;
import com.yh.hr.res.pb.queryhelper.PbDutyInfoQueryHelper;
import com.yh.hr.res.pb.service.PbDutyInfoService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

import java.util.List;

public class PbDutyInfoServiceImpl implements PbDutyInfoService {
	
	/*
	 * 新增-院内中层职务任职信息
	 */
	public void create(PbDutyInfoDTO pbDutyInfoDTO) throws ServiceException {
		PbDutyInfo pbDutyInfo = new PbDutyInfo();
		BeanHelper.copyProperties(pbDutyInfoDTO, pbDutyInfo);
		pbDutyInfo.save();
		
		//同步主要的专业技术资格信息到人员附属表
        if(DicConstants.YHRS0003_1.equals(pbDutyInfo.getIsMainDutyInfo()))
        {
    		PersonAttachInfoServiceUtil.sysPbDutyInfo(pbDutyInfo.getPersonOid());
        }
	}
	
	/*
	 * 删除-院内中层职务任职信息
	 */
	public void delete(Long dutyOid) throws ServiceException {
		PbDutyInfo bo = DaoUtil.get(PbDutyInfo.class, dutyOid);
		bo.delete();
		
		PersonAttachInfoServiceUtil.sysPbDutyInfo(bo.getPersonOid());
	}
	
	/*
	 * 根据dutyOid查找院内中层职务任职信息
	 */
	public PbDutyInfoDTO get(Long dutyOid) throws ServiceException {
        return BeanHelper.copyProperties(DaoUtil.get(PbDutyInfo.class, dutyOid),PbDutyInfoDTO.class);
	}
	
	/*
	 * 列出多条院内中层职务任职信息
	 */
	public List<PbDutyInfoDTO> find(TableTagBean ttb) throws ServiceException {
		return PbDutyInfoQueryHelper.find(ttb);
	}
	
	/*
	 * 更新和修改院内中层职务任职信息
	 */
	public void update(PbDutyInfoDTO pbDutyInfoDto) throws ServiceException {
		PbDutyInfo pbDutyInfo = new PbDutyInfo();
		BeanHelper.copyProperties(pbDutyInfoDto, pbDutyInfo);
		pbDutyInfo.update();
		
		PersonAttachInfoServiceUtil.sysPbDutyInfo(pbDutyInfo.getPersonOid());
	}
	
	/*
	 * 根据人员id查询该人员所有的院内中层职务任职信息记录
	 */
	public List<PbDutyInfoDTO> listPbDutyInfoByPersonOid(Long personOid) throws ServiceException {
		return PbDutyInfoQueryHelper.listPbDutyInfoByPersonOid(personOid);
	}
	/*
	 * 根据人员id查询该人员所有的院内中层职务任职信息记录(导出用)
	 */
	public List<PbDutyInfoDTO> listExportPbDutyInfoByPersonOid(Long personOid) throws ServiceException {
		return PbDutyInfoQueryHelper.listExportPbDutyInfoByPersonOid(personOid);
	}
	/*
	 * (non-Javadoc)
	 * @see PbDutyInfoService#getPbDutyInfoByPersonOid(java.lang.Long)
	 */
	public PbDutyInfoDTO getPbDutyInfoByPersonOid(Long personOid)
		throws ServiceException {
		return PbDutyInfoQueryHelper.getPbDutyInfoByPersonOid(personOid);
	}
	

}
