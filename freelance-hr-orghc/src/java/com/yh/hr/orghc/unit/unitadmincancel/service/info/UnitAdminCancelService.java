package com.yh.hr.orghc.unit.unitadmincancel.service.info;

import java.util.List;

import com.yh.hr.orghc.unit.transfer.bt.BtGtPostTransferService;
import com.yh.hr.orghc.unit.transfer.bt.BtUtHcTransferService;
import com.yh.hr.orghc.unit.transfer.bt.BtUtLeaderTransferService;
import com.yh.hr.orghc.unit.transfer.bt.BtUtOrgTransferService;
import com.yh.hr.orghc.unit.transfer.bt.BtUtUnitTransferService;
import com.yh.hr.orghc.unit.unitadmincancel.dto.UnitAdminCancelDTO;
import com.yh.hr.orghc.ut.dto.BizUtUnitDTO;
import com.yh.hr.orghc.ut.service.BizUtUnitService;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.hr.res.gt.dto.GtPostPercentDTO;
import com.yh.hr.res.gt.service.GtPostPercentService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * 主管单位撤销 Service
 * @author zhengdr
 *
 * 时间:2016-12-22下午06:10:25
 */
public class UnitAdminCancelService {

	//单位信息
	private BtUtUnitTransferService btUtUnitTransferService;
	//编制信息
	private BtUtHcTransferService btUtHcTransferService;
	//领导职数信息
	private BtUtLeaderTransferService btUtLeaderTransferService;
	//内设机构信息
	private BtUtOrgTransferService btUtOrgTransferService;
	//岗位信息
	private BtGtPostTransferService btGtPostTransferService;
	
	//单位信息管理
	private BizUtUnitService bizUtUnitService;
	private GtPostPercentService gtPostPercentService;
	
	public void setBizUtUnitService(BizUtUnitService bizUtUnitService) {
		this.bizUtUnitService = bizUtUnitService;
	}
	
	public void setGtPostPercentService(GtPostPercentService gtPostPercentService) {
		this.gtPostPercentService = gtPostPercentService;
	}
	
	public void setBtUtUnitTransferService(
			BtUtUnitTransferService btUtUnitTransferService) {
		this.btUtUnitTransferService = btUtUnitTransferService;
	}

	public void setBtUtHcTransferService(BtUtHcTransferService btUtHcTransferService) {
		this.btUtHcTransferService = btUtHcTransferService;
	}

	public void setBtUtLeaderTransferService(
			BtUtLeaderTransferService btUtLeaderTransferService) {
		this.btUtLeaderTransferService = btUtLeaderTransferService;
	}

	public void setBtUtOrgTransferService(
			BtUtOrgTransferService btUtOrgTransferService) {
		this.btUtOrgTransferService = btUtOrgTransferService;
	}
	
	public void setBtGtPostTransferService(
			BtGtPostTransferService btGtPostTransferService) {
		this.btGtPostTransferService = btGtPostTransferService;
	}

	/**
	 * 创建业务保存信息
	 * @param dto
	 * @throws ServiceException
	 */
	public void save(UnitAdminCancelDTO dto)throws ServiceException {
		
		//得到taskOid和unitOid
		Long taskOid = dto.getTaskOid();
		Long unitOid = dto.getUnitOid();
		//单位信息转业务库
		btUtUnitTransferService.transfer(taskOid, unitOid);
		//编制信息转业务库
		btUtHcTransferService.transfer(taskOid, unitOid);
		//领导职数转业务库
		btUtLeaderTransferService.transfer(taskOid, unitOid);
		//内设机构转业务库
		btUtOrgTransferService.transfer(taskOid, unitOid);
		//岗位信息
		btGtPostTransferService.transfer(taskOid, unitOid);
		
		//得到单位utUnitOid
		BizUtUnitDTO bizUtUnitDTO = bizUtUnitService.findBizUtUnitByTaskOid(taskOid);
		//设置
		dto.setUtUnitOid(bizUtUnitDTO.getUtUnitOid());
		
		//清空文号
		bizUtUnitDTO.setFileNo("");
		//清空备注
		bizUtUnitDTO.setRemark("");
		//更新
		bizUtUnitService.updateBizUtUnit(bizUtUnitDTO);
		
	}
	
	/**
	 * 得到主管单位信息
	 * @param utUnitOid
	 * @return
	 * @throws ServiceException
	 */
	public UnitAdminCancelDTO get(Long utUnitOid)throws ServiceException {
		
		UnitAdminCancelDTO unitAdminCreateDTO = new UnitAdminCancelDTO();
		//得到主管单位信息
		BizUtUnitDTO bizUtUnitDTO = bizUtUnitService.findBizUtUnitById(utUnitOid);
		//复制
		BeanHelper.copyProperties(bizUtUnitDTO, unitAdminCreateDTO);
		if(bizUtUnitDTO!=null){
			//事业单位
			if(DicConstants.YHRS0090_104.equals(bizUtUnitDTO.getUnitKind())){
				//得到岗位信息
				List<GtPostPercentDTO> gtPostPercentDTOs = gtPostPercentService.getGtPostPercentDTOList(utUnitOid);
			
			    if(gtPostPercentDTOs!=null&&gtPostPercentDTOs.size()>0){
			    	
			    	for(GtPostPercentDTO gtPostPercentDTO:gtPostPercentDTOs){
			    		if(DicConstants.YHRS0023_A1020010_010.equals(gtPostPercentDTO.getPostLevel())){
			    			//岗位级别--正高级
			    			unitAdminCreateDTO.setZhengGaoPercent(gtPostPercentDTO.getPercent());
			    		}else if(DicConstants.YHRS0023_A1020010_020.equals(gtPostPercentDTO.getPostLevel())){
			    			//岗位级别--副高级
			    			unitAdminCreateDTO.setFuGaoPercent(gtPostPercentDTO.getPercent());
			    		}else if(DicConstants.YHRS0023_A1020020.equals(gtPostPercentDTO.getPostLevel())){
			    			//岗位级别--中级
			    			unitAdminCreateDTO.setZhongPercent(gtPostPercentDTO.getPercent());
			    		}else if(DicConstants.YHRS0023_A1020030.equals(gtPostPercentDTO.getPostLevel())){
			    			//岗位级别--初级
			    			unitAdminCreateDTO.setChuPercent(gtPostPercentDTO.getPercent());
			    		}
			    	}
			    }
			}
		}
		
		return unitAdminCreateDTO;
	}
	
	/**
	 * 更新修改
	 * @param dto
	 * @throws ServiceException
	 */
	public void update(UnitAdminCancelDTO dto)throws ServiceException {
		//得到单位utUnitOid
		BizUtUnitDTO bizUtUnitDTO = bizUtUnitService.findBizUtUnitByTaskOid(dto.getTaskOid());
		//设置文号和备注
		bizUtUnitDTO.setFileNo(dto.getFileNo());
		bizUtUnitDTO.setRemark(dto.getRemark());
	    //修改
		bizUtUnitService.updateBizUtUnit(bizUtUnitDTO);
	}
}
