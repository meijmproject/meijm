package com.yh.hr.res.pb.service;

import java.util.List;

import com.yh.hr.res.pb.dto.PbPersonInfoDTO;
import net.sf.json.JSONObject;

import com.yh.component.taglib.TableTagBean;
import com.yh.platform.core.exception.ServiceException;

/**
 * @desc 人员基础信息业务逻辑层
 * @author luqy
 * @createDate 2016-8-15下午04:30:56
 */
public interface PbPersonInfoService {
	
	/*final String	USER_ID		= UserContext.getLoginUserID();
	final String	USER_NAME	= UserContext.getLoginUserName();
	final Date		NOW_TIME	= DateUtil.now();*/
	
	public  Long addPbPersonInfo(PbPersonInfoDTO pbPersonInfoDTO) throws ServiceException;
	
	public  void updatePbPersonInfo(PbPersonInfoDTO pbPersonInfoDTO) throws ServiceException;
	
	public  void deletePbPersonInfoById(Long personOid) throws ServiceException;
	
	public  PbPersonInfoDTO getPbPersonInfoDTOById(Long personOid) throws ServiceException;
	
	public  List<PbPersonInfoDTO> listPbPersonInfo(TableTagBean ttb) throws ServiceException;
	
	public  List<PbPersonInfoDTO> listPbPersonInfoByName(String name) throws ServiceException;

	/**
	 * 获取单位下编制实有人员数
	 * @param unitOids
	 * @param dPositionType
	 * @return num
     * @throws ServiceException 
     * @author lenghh
	 */
	public  Integer countPersonHc(List<Long> unitOids,String dPositionType) throws ServiceException;
	
	/**
	 * 获取单位下职级实有人员数
	 * @param unitOids
	 * @param positionLevelCode
	 * @param isLeader
	 * @return num
     * @throws ServiceException 
     * @author lenghh
	 */
	public  Integer countPersonLeader(List<Long> unitOids,String positionLevelCode,String isLeader) throws ServiceException;

	/**
	 * 以人员的编制类型和经费形式分组得到人员信息
	 * @param unitOid
	 * @return
	 * @throws ServiceException
	 */
    public  List<PbPersonInfoDTO> getGroupByDPositionTypeAndBankpoll(Long unitOid)throws ServiceException;
    
    /**
	 * 根据职务级别和职务属性分组得到人员信息
	 * @param unitOid
	 * @return
	 * @throws ServiceException
	 */
   /* public  List<PbPersonAttachDTO> getPersonByUnitOid(Long unitOid)throws ServiceException;*/
    
    /**
	 * 根据人员名称查询人员信息(在职,见习长期休假)
	 * @param name
	 * @return
	 * @throws ServiceException
	 */
	public List<PbPersonInfoDTO> findPersonInfoByName(String name) throws ServiceException;
	
	/**
	 * 根据科室ID查询科室下面的所有人
	 * @param hireDeptOid
	 * @return
	 * @throws ServiceException
	 */
	 public  List<PbPersonInfoDTO> findPersonByDeptOid(Long hireDeptOid)throws ServiceException;

	 /**
	  * 根据姓名和工号查询人员
	  * @param name
	  * @param personCode
	  * @return
	  */
	public List<PbPersonInfoDTO> findPersonInfoByNameAndCode(String name, String personCode) throws ServiceException;

	/**
	 * 无检查新增人员
	 * @param pbPersonInfoDTO
	 * @return
	 */
	public Long createPbPersonInfo(PbPersonInfoDTO pbPersonInfoDTO) throws ServiceException;

	/**
	 * 无检查修改人员
	 * @param pbPersonInfoDTO
	 * @throws ServiceException
	 */
	public void modifyPbPersonInfo(PbPersonInfoDTO pbPersonInfoDTO) throws ServiceException;
	
	/**
	 * 证件唯一性
	 * @param pbPersonInfoDTO
	 * @throws ServiceException
	 */
	public void checkUniquePbPerson(PbPersonInfoDTO pbPersonInfoDTO)throws ServiceException ;
	
	/**
	 * 工号唯一性
	 * @param pbPersonInfoDTO
	 * @throws ServiceException
	 */
	public void checkPersonCode(PbPersonInfoDTO pbPersonInfoDTO)throws ServiceException ;

	public List<JSONObject> getBusinessCount(Long personOid) throws ServiceException;
	
	/**
	 * 校核人员入库（人员导入功能入库）
	 * @param pbPersonInfoDTO
	 * @return
	 * @throws ServiceException
	 */
	public Long transferImportPersonInfo(PbPersonInfoDTO pbPersonInfoDTO) throws ServiceException;

}


