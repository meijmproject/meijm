package com.yh.hr.orghc.unit.unitchildcreate.service.info;

import java.util.List;
import com.yh.hr.orghc.ub.queryhelper.UbUnitQueryHelper;
import com.yh.hr.orghc.unit.unitchildcreate.dto.UnitChildCreateDTO;
import com.yh.hr.orghc.ut.dto.BizUtUnitDTO;
import com.yh.hr.orghc.ut.service.BizUtUnitService;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.hr.res.gt.dto.GtPostPercentDTO;
import com.yh.hr.res.gt.service.GtPostPercentService;
import com.yh.hr.res.sao.orghc.dto.SaoUbUnitDTO;
import com.yh.hr.res.sao.orghc.service.SaoUbUnitService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * 下级单位创建
 * @author zhengdr
 *
 * 时间:2016-12-22下午02:50:04
 */
public class UnitChildCreateService {

	//单位信息管理
	private BizUtUnitService bizUtUnitService;
	private GtPostPercentService gtPostPercentService;
    private SaoUbUnitService saoUbUnitService;
	
	public void setSaoUbUnitService(SaoUbUnitService saoUbUnitService) {
		this.saoUbUnitService = saoUbUnitService;
	}
	public void setBizUtUnitService(BizUtUnitService bizUtUnitService) {
		this.bizUtUnitService = bizUtUnitService;
	}
	
	public void setGtPostPercentService(GtPostPercentService gtPostPercentService) {
		this.gtPostPercentService = gtPostPercentService;
	}

	/**
	 * 创建业务保存信息
	 * @param dto
	 * @throws ServiceException
	 */
	public void save(UnitChildCreateDTO dto)throws ServiceException {
		
		dto.setUnitOid(null);//置空
		//判断单位名称是否已存在--基础库
		boolean flag = UbUnitQueryHelper.checkUniqueUnit(dto.getUnitName(),null);
		if(flag){
			throw new ServiceException(null, "该单位名称已经存在");
		}
		
		//复制单位信息
		BizUtUnitDTO bizUtUnitDTO = BeanHelper.copyProperties(dto, BizUtUnitDTO.class);
	    //保存
		Long utUnitOid = bizUtUnitService.createBizUtUnit(bizUtUnitDTO);
	    //设置
		dto.setUtUnitOid(utUnitOid);
		
		//事业单位保存岗位信息
		if(DicConstants.YHRS0090_104.equals(dto.getUnitKind())){
			//岗位信息
			GtPostPercentDTO gtPostPercentDTO = new GtPostPercentDTO();
			gtPostPercentDTO.setUtUnitOid(utUnitOid);
			//岗位类别--默认专业技术类
			gtPostPercentDTO.setPostType(DicConstants.YHRS0022_2);
			//岗位级别--正高级
			gtPostPercentDTO.setPostLevel(DicConstants.YHRS0023_A1020010_010);
			//百分比
			gtPostPercentDTO.setPercent(dto.getZhengGaoPercent());
			//保存
			gtPostPercentService.createGtPostPercent(gtPostPercentDTO);
			
			//岗位级别--副高级
			gtPostPercentDTO.setPostLevel(DicConstants.YHRS0023_A1020010_020);
			//百分比
			gtPostPercentDTO.setPercent(dto.getFuGaoPercent());
			//保存
			gtPostPercentService.createGtPostPercent(gtPostPercentDTO);
			
			//岗位级别--中级
			gtPostPercentDTO.setPostLevel(DicConstants.YHRS0023_A1020020);
			//百分比
			gtPostPercentDTO.setPercent(dto.getZhongPercent());
			//保存
			gtPostPercentService.createGtPostPercent(gtPostPercentDTO);
			
			//岗位级别--初级
			gtPostPercentDTO.setPostLevel(DicConstants.YHRS0023_A1020030);
			//百分比
			gtPostPercentDTO.setPercent(dto.getChuPercent());
			//保存
			gtPostPercentService.createGtPostPercent(gtPostPercentDTO);
			
		}
	}
	
	/**
	 * 得到下级单位信息
	 * @param utUnitOid
	 * @return
	 * @throws ServiceException
	 */
	public UnitChildCreateDTO get(Long utUnitOid)throws ServiceException {
		
		UnitChildCreateDTO unitChildCreateDTO = new UnitChildCreateDTO();
		//得到主管单位信息
		BizUtUnitDTO bizUtUnitDTO = bizUtUnitService.findBizUtUnitById(utUnitOid);
		//复制
		BeanHelper.copyProperties(bizUtUnitDTO, unitChildCreateDTO);
		
		//得到父单位
		if(bizUtUnitDTO.getParentUnitOid()!=null){
			SaoUbUnitDTO saoUbUnitDTO = saoUbUnitService.getSaoUbUnitDTO(bizUtUnitDTO.getParentUnitOid());
		    if(saoUbUnitDTO!=null){
		    	//设置parentUnitName
		    	unitChildCreateDTO.setParentUnitName(saoUbUnitDTO.getUnitName());
		    }
		}
		
		
		if(bizUtUnitDTO!=null){
			//事业单位
			if(DicConstants.YHRS0090_104.equals(bizUtUnitDTO.getUnitKind())){
				//得到岗位信息
				List<GtPostPercentDTO> gtPostPercentDTOs = gtPostPercentService.getGtPostPercentDTOList(utUnitOid);
			
			    if(gtPostPercentDTOs!=null&&gtPostPercentDTOs.size()>0){
			    	
			    	for(GtPostPercentDTO gtPostPercentDTO:gtPostPercentDTOs){
			    		if(DicConstants.YHRS0023_A1020010_010.equals(gtPostPercentDTO.getPostLevel())){
			    			//岗位级别--正高级
			    			unitChildCreateDTO.setZhengGaoPercent(gtPostPercentDTO.getPercent());
			    		}else if(DicConstants.YHRS0023_A1020010_020.equals(gtPostPercentDTO.getPostLevel())){
			    			//岗位级别--副高级
			    			unitChildCreateDTO.setFuGaoPercent(gtPostPercentDTO.getPercent());
			    		}else if(DicConstants.YHRS0023_A1020020.equals(gtPostPercentDTO.getPostLevel())){
			    			//岗位级别--中级
			    			unitChildCreateDTO.setZhongPercent(gtPostPercentDTO.getPercent());
			    		}else if(DicConstants.YHRS0023_A1020030.equals(gtPostPercentDTO.getPostLevel())){
			    			//岗位级别--初级
			    			unitChildCreateDTO.setChuPercent(gtPostPercentDTO.getPercent());
			    		}
			    	}
			    }
			}
		}
		
		return unitChildCreateDTO;
	}
	
	/**
	 * 更新修改
	 * @param dto
	 * @throws ServiceException
	 */
	public void update(UnitChildCreateDTO dto)throws ServiceException {
		BizUtUnitDTO unitdto = bizUtUnitService.findBizUtUnitByTaskOid(dto.getTaskOid());
		//判断单位名称是否已存在--基础库
		boolean flag = true;
		if(null != unitdto.getUnitOid()){
			 flag = UbUnitQueryHelper.checkUniqueUnit(dto.getUnitName(),unitdto.getUnitOid());
		}else{			
			 flag = UbUnitQueryHelper.checkUniqueUnit(dto.getUnitName(),null);
		}
		if(flag){
			throw new ServiceException(null, "该单位名称已经存在");
		}
		
		//复制单位信息
		BizUtUnitDTO bizUtUnitDTO = BeanHelper.copyProperties(dto, BizUtUnitDTO.class);
	    //修改
		bizUtUnitService.updateBizUtUnit(bizUtUnitDTO);
		//事业单位保存岗位信息
		if(DicConstants.YHRS0090_104.equals(dto.getUnitKind())){
			
			Long utUnitOid = dto.getUtUnitOid();
			//得到岗位信息
			List<GtPostPercentDTO> gtPostPercentDTOs = gtPostPercentService.getGtPostPercentDTOList(utUnitOid);
		    if(gtPostPercentDTOs!=null&&gtPostPercentDTOs.size()>0){
		    	//有数据则更新
		    	for(GtPostPercentDTO gtPostPercentDTO:gtPostPercentDTOs){
		    		if(DicConstants.YHRS0023_A1020010_010.equals(gtPostPercentDTO.getPostLevel())){
		    			//岗位级别--正高级
		    			gtPostPercentDTO.setPercent(dto.getZhengGaoPercent());
		    			gtPostPercentService.modifyGtPostPercent(gtPostPercentDTO);
		    			
		    		}else if(DicConstants.YHRS0023_A1020010_020.equals(gtPostPercentDTO.getPostLevel())){
		    			//岗位级别--副高级
		    			gtPostPercentDTO.setPercent(dto.getFuGaoPercent());
		    			gtPostPercentService.modifyGtPostPercent(gtPostPercentDTO);
		    		}else if(DicConstants.YHRS0023_A1020020.equals(gtPostPercentDTO.getPostLevel())){
		    			//岗位级别--中级
		    			gtPostPercentDTO.setPercent(dto.getZhongPercent());
		    			gtPostPercentService.modifyGtPostPercent(gtPostPercentDTO);
		    		}else if(DicConstants.YHRS0023_A1020030.equals(gtPostPercentDTO.getPostLevel())){
		    			//岗位级别--初级
		    			gtPostPercentDTO.setPercent(dto.getChuPercent());
		    			gtPostPercentService.modifyGtPostPercent(gtPostPercentDTO);
		    		}
		    	}
		    }else{
		    	//新增创建
				//岗位信息
				GtPostPercentDTO gtPostPercentDTO = new GtPostPercentDTO();
				gtPostPercentDTO.setUtUnitOid(utUnitOid);
				//岗位类别--默认专业技术类
				gtPostPercentDTO.setPostType(DicConstants.YHRS0022_2);
				//岗位级别--正高级
				gtPostPercentDTO.setPostLevel(DicConstants.YHRS0023_A1020010_010);
				//百分比
				gtPostPercentDTO.setPercent(dto.getZhengGaoPercent());
				//保存
				gtPostPercentService.createGtPostPercent(gtPostPercentDTO);
				
				//岗位级别--副高级
				gtPostPercentDTO.setPostLevel(DicConstants.YHRS0023_A1020010_020);
				//百分比
				gtPostPercentDTO.setPercent(dto.getFuGaoPercent());
				//保存
				gtPostPercentService.createGtPostPercent(gtPostPercentDTO);
				
				//岗位级别--中级
				gtPostPercentDTO.setPostLevel(DicConstants.YHRS0023_A1020020);
				//百分比
				gtPostPercentDTO.setPercent(dto.getZhongPercent());
				//保存
				gtPostPercentService.createGtPostPercent(gtPostPercentDTO);
				
				//岗位级别--初级
				gtPostPercentDTO.setPostLevel(DicConstants.YHRS0023_A1020030);
				//百分比
				gtPostPercentDTO.setPercent(dto.getChuPercent());
				//保存
				gtPostPercentService.createGtPostPercent(gtPostPercentDTO);
				
		    }
		     
		}
		
	}
}
