package com.yh.hr.res.pb.queryhelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yh.hr.res.pb.bo.PbPersonInfo;
import com.yh.hr.res.pb.dto.PbPersonInfoDTO;
import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.DataAccessFailureException;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.util.StringUtil;

/**
 * @desc 人员基础信息查询工具类
 * @author luqy
 * @createDate 2016-8-16上午09:53:23
 */
public class PbPersonInfoQueryHelper {

	/*
	 * 通过ID获取
	 */
	public static PbPersonInfoDTO getPbPersonInfoDTOById(Long personOid) throws ServiceException {
		return BeanHelper.copyProperties(DaoUtil.get(PbPersonInfo.class, personOid), PbPersonInfoDTO.class);
	}
	
	public static List<PbPersonInfoDTO> listPbPersonInfo(TableTagBean ttb) throws ServiceException {
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		StringBuilder chql = new StringBuilder();
		
		chql.append("from PbPersonInfo p where 1=1 ");
		
		if (StringUtils.isNotEmpty(ttb.getCondition().get("unitOid"))) {
			chql.append(" and p.unitOid = ").append(ttb.getCondition().get("unitOid"));
		}
		
		if (StringUtils.isNotEmpty(ttb.getCondition().get("name"))) {
			chql.append(" and p.name like :name ");
			params.put("name", StringUtil.wrapPercent(ttb.getCondition().get("name")));
		}
		
		if (StringUtils.isNotEmpty(ttb.getCondition().get("idNo"))) {
			chql.append(" and p.idNo like :idNo ");
			params.put("idNo", StringUtil.wrapPercent(ttb.getCondition().get("idNo")));
		}
		
		if (StringUtils.isNotEmpty(ttb.getCondition().get("personStatus"))) {
			chql.append(" and p.personStatus = :personStatus ");
			params.put("personStatus", ttb.getCondition().get("personStatus"));
		}
		
		//权限控制
		if (StringUtils.isNotEmpty(ttb.getCondition().get("authUnits"))) {
			chql.append(" and p.unitOid in(").append(ttb.getCondition().get("authUnits")).append(") ");
		}
		
		if (ttb.getPageSize() != 0) {
			ttb.setTotal(DaoUtil.countByCondition(new StringBuilder().append("select count(*) ").append(chql).toString(), params));
		}
		
		return BeanHelper.copyProperties(
				DaoUtil.listByCondition(new StringBuilder().append(chql).append(" order by p.personOrderView, p.personOid ").toString(), params, ttb.getPage(), ttb.getPageSize()), 
				PbPersonInfoDTO.class);
	}
	

	
	/**
	 * 通过姓名模糊查询基础人员信息列表
	 * @param name
	 * @return
	 * @throws ServiceException
	 */
	public static List<PbPersonInfoDTO> listPbPersonInfoByName(String name) throws ServiceException {
		if(StringUtils.isNotBlank(name)) {
			String hql = "from PbPersonInfo pb where pb.personStatus in('110','120','130') and pb.name like'"+StringUtil.wrapPercent(name) +"'";
			return BeanHelper.copyProperties(DaoUtil.find(hql), PbPersonInfoDTO.class);
		}
		return null;
	}

	/**
	 * 通过人员，证件号码，证件类型查询人员信息
	 * @param idCode
	 * @param idNo
	 * @return
	 * @throws ServiceException
	 */
	public static List<PbPersonInfoDTO> checkUniquePbPerson(String idCode,String idNo,Long personOid) throws ServiceException {
		if(StringUtil.isNotBlank(idCode)&&StringUtil.isNotBlank(idNo))
		{
			String hql = "from PbPersonInfo pb where pb.personStatus in('110','120','130','206','207','208','209','210','300','399') and pb.idCode=:idCode and pb.idNo=:idNo";
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("idCode", idCode);
			params.put("idNo", idNo);
			if (null!=personOid) {
				hql+=(" and pb.personOid !=:personOid");
				params.put("personOid", personOid);
			}
			return BeanHelper.copyProperties(DaoUtil.find(hql, params), PbPersonInfoDTO.class);
		}
		return null;
	}
	
	/**
	 * 通过unitOid查询人员信息（在职、试用期、长期病休）
	 * @param unitOid
	 * @return
	 * @throws ServiceException
	 */
	public static List<PbPersonInfoDTO> listPbPersonInfoByUnitOid(Long unitOid) throws ServiceException {
		String hql = "from PbPersonInfo pb where pb.personStatus in ('110','120','130') and pb.unitOid = " + unitOid;
		return BeanHelper.copyProperties(DaoUtil.find(hql), PbPersonInfoDTO.class);
	}
	
	/**
	 * 通过unitOid以及人员状态和人员类型查询人员数量
	 * @param unitOid
	 * @param personStatus
	 * @param personType
	 * @return
	 * @throws ServiceException
	 */
	public static Long getPersonCountByUnitOid(Long unitOid, List<String> personStatus, String personType) throws ServiceException {
		String hql = "select count(*) from PbPersonInfo pb where pb.personStatus in (" + StringUtil.arrayToSql(personStatus, "'") + ") and pb.unitOid = " + unitOid + " and pb.personType = '" + personType + "'";
		return new Long(DaoUtil.countByCondition(hql, null));
	}
	
	/**
	 * 获取单位下职级实有人员数
	 * @param unitOids
	 * @param positionLevelCode
	 * @param isLeader
	 * @return num
     * @throws ServiceException 
     * @author lenghh
	 */
	public static int countPersonLeader(List<Long> unitOids,String positionLevelCode,String isLeader) throws ServiceException {
		if(CollectionUtils.isEmpty(unitOids)) throw new ServiceException(null, "单位不能为空");
		StringBuilder hql = new StringBuilder().append("select count(*) from PbPersonInfo p,PbPersonAttach pa where p.personOid=pa.personOid and p.personStatus in ('110','120','130')");
		hql.append(" and p.unitOid in(").append(StringUtil.arrayToSql(unitOids)).append(")");
        if(StringUtils.isNotBlank(positionLevelCode)){
        	hql.append(" and pa.administrativeDutyLevel ='").append(positionLevelCode).append("'");
        }
        if(StringUtils.isNotEmpty(isLeader)){
        	hql.append(" and pa.administrativeIsMajorDuty ='").append(isLeader).append("'");
        }
        Object obj = DaoUtil.uniqueResult(hql.toString());
		return obj == null ? 0 : ((Number) obj).intValue();
	}
	
	/**
	 * 获取单位下编制实有人员数
	 * @param unitOids
	 * @param dPositionType
	 * @return num
     * @throws ServiceException 
     * @author lenghh
	 */
	public static int countPersonHc(List<Long> unitOids,String dPositionType) throws ServiceException {
		if(CollectionUtils.isEmpty(unitOids)) throw new ServiceException(null, "单位不能为空");
		StringBuilder hql = new StringBuilder().append("select count(*) from PbPersonInfo p where p.personStatus in ('110','120','130')");
		hql.append(" and p.unitOid in(").append(StringUtil.arrayToSql(unitOids)).append(")");
        if(StringUtils.isNotBlank(dPositionType)){
        	hql.append(" and p.dPositionType ='").append(dPositionType).append("'");
        }
        Object obj = DaoUtil.uniqueResult(hql.toString());
		return obj == null ? 0 : ((Number) obj).intValue();
	}
	
	/**
	 * 根据personOid删除照片信息
	 * @param personOid
	 * @throws ServiceException
	 */
	public static void deleteImageByPersonId(Long personOid) throws ServiceException {
		DaoUtil.executeUpdate("delete from PbImage im where im.personOid = " + personOid);
	}
	
	/**
	 * 以人员的编制类型和经费形式分组得到人员信息
	 * @param unitOid
	 * @return
	 * @throws ServiceException
	 */
	public static List<PbPersonInfoDTO> getGroupByDPositionTypeAndBankpoll(Long unitOid)throws ServiceException {
		
        StringBuilder hql = new StringBuilder();
		
		hql.append("select p.dPositionType,p.bankpoll from PbPersonInfo p where 1=1 ");
		//单位
		hql.append(" and p.unitOid = ").append(unitOid);
		//人员状态--在职
		hql.append(" and p.personStatus = '").append(DicConstants.YHRS0009_110).append("'");
		
		hql.append(" and p.dPositionType is not null and p.bankpoll is not null");
		//分组
		hql.append(" group by p.dPositionType,p.bankpoll");
		//得到信息
		List<Object[]> objects = DaoUtil.find(hql.toString());
		List<PbPersonInfoDTO> pbPersonInfoDTOs = null;
		PbPersonInfoDTO pbPersonInfoDTO = null;
		
		if(CollectionUtils.isNotEmpty(objects)){
			pbPersonInfoDTOs = new ArrayList<PbPersonInfoDTO>();
			for(Object[] object:objects){
				pbPersonInfoDTO = new PbPersonInfoDTO();
				//设置编制类型和经费形式
//			    pbPersonInfoDTO.setdPositionType(object[0].toString());
			    pbPersonInfoDTO.setBankpoll(object[1].toString());
			    //加入列表
			    pbPersonInfoDTOs.add(pbPersonInfoDTO);
			}
			
		}
		
		return pbPersonInfoDTOs;
	}
    /**
     * 检查人员编号(工号)唯一性
     * @param personCode
     * @param personOid
     * @return
     * @throws ServiceException 
     */
	public static List<PbPersonInfoDTO> checkUniquePbPersonCode(
			String personCode, Long personOid) throws ServiceException {
		if(StringUtils.isBlank(personCode))
		{
			throw new ServiceException(null,"缺少查询条件!");
		}
		String hql = "from PbPersonInfo pb where pb.personCode=:personCode";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("personCode", personCode);
		if (null!=personOid) {
			hql+=(" and pb.personOid !=:personOid");
			params.put("personOid", personOid);
		}
		return BeanHelper.copyProperties(DaoUtil.find(hql,params), PbPersonInfoDTO.class);
	}
	
	/**
	 * 根据职务级别和职务属性分组得到人员信息
	 * @param unitOid
	 * @return
	 * @throws ServiceException
	 */
	/*public static List<PbPersonAttachDTO> getPersonByUnitOid(Long unitOid)throws ServiceException {
		
        StringBuilder hql = new StringBuilder();
		
		hql.append("select pa.administrativeDutyLevel,pa.administrativeDutyAttribute from PbPersonInfo p,PbPersonAttach pa where p.personOid=pa.personOid ");
		hql.append(" and pa.administrativeDutyAttribute in (10,11,21,22)");
		//单位
		hql.append(" and p.unitOid = ").append(unitOid);
		//人员状态
		hql.append(" and p.personStatus in (110,120,130,208,300)");
		//分组
		hql.append(" group by pa.administrativeDutyLevel,pa.administrativeDutyAttribute");
		List<Object[]> objects = DaoUtil.find(hql.toString());
		List<PbPersonAttachDTO> pbPersonAttachDTOs = null;
		PbPersonAttachDTO pbPersonAttachDTO = null;
		
		if(CollectionUtils.isNotEmpty(objects)){
			pbPersonAttachDTOs = new ArrayList<PbPersonAttachDTO>();
			for(Object[] object:objects){
				pbPersonAttachDTO = new PbPersonAttachDTO();
				//设置编制类型和经费形式
//				pbPersonAttachDTO.setAdministrativeDutyLevel(object[0].toString());
//				pbPersonAttachDTO.setAdministrativeDutyAttribute(object[1].toString());
			    //加入列表
				pbPersonAttachDTOs.add(pbPersonAttachDTO);
			}
			
		}
		return pbPersonAttachDTOs;
	}*/
	
	/**
	 * 根据人员名称查询人员信息(在职,见习长期休假)
	 * @param name
	 * @return
	 * @throws ServiceException
	 */
	public static List<PbPersonInfoDTO> findPersonInfoByName(String name) throws ServiceException {
		if(StringUtils.isBlank(name))
		{
			throw new ServiceException(null,"缺少查询条件!");
		}
		String hql = "from PbPersonInfo pb where pb.personStatus in('110','120','130') and pb.name like'"+name +"%'";
		return BeanHelper.copyProperties(DaoUtil.find(hql), PbPersonInfoDTO.class);
	}

	/**
	 * 根据科室ID查询科室下面的所有人
	 * @param hireDeptOid
	 * @return
	 * @throws DataAccessFailureException
	 */
	public static List<PbPersonInfoDTO> findPersonByDeptOid(Long hireDeptOid) throws DataAccessFailureException
	{
		List<PbPersonInfo> list= DaoUtil.findByNamed("from PbPersonInfo where hireDeptOid=:hireDeptOid", "hireDeptOid", hireDeptOid);
		if(CollectionUtils.isNotEmpty(list))
		{
			List<PbPersonInfoDTO> dtoList = new ArrayList<PbPersonInfoDTO>();
			for(PbPersonInfo bo : list)
			{
				PbPersonInfoDTO dto = new PbPersonInfoDTO();
				BeanUtils.copyProperties(bo, dto);
				dtoList.add(dto);
			}
			return dtoList;
		}
		return null;
	}

	/**
	 * 根据人员姓名和工号查询人员
	 * @param name
	 * @param personCode
	 * @return
	 */
	public static List<PbPersonInfoDTO> findPersonInfoByNameAndCode(String name, String personCode) throws ServiceException{
		StringBuffer sb = new StringBuffer("from PbPersonInfo where name='"+name+"'");
		if(StringUtils.isNotEmpty(personCode)) {
			sb.append(" and personCode='"+personCode+"'");
		}
		List<PbPersonInfo> list= DaoUtil.find(sb.toString());
		if(CollectionUtils.isNotEmpty(list))
		{
			List<PbPersonInfoDTO> dtoList = new ArrayList<PbPersonInfoDTO>();
			for(PbPersonInfo bo : list)
			{
				PbPersonInfoDTO dto = new PbPersonInfoDTO();
				BeanUtils.copyProperties(bo, dto);
				dtoList.add(dto);
			}
			return dtoList;
		}
		return null;
	}

	public static List<JSONObject> getBusinessCount(Long personOid) throws ServiceException{
		StringBuilder sql = new StringBuilder();
		sql.append(" select jpp.name,jpp.BIZ_PERSON_OID ");
		sql.append(" from yhc_bt_task jbt, yhc_bt_task_item jbti, yhc_pt_person jpp ");
		sql.append(" WHERE jbt.TASK_OID = jbti.TASK_OID AND jpp.TASK_OID = jbt.TASK_OID");
		sql.append(" AND jbti.task_item_oid IN ( SELECT max(bti2.task_item_oid) FROM yhc_bt_task_item bti2 WHERE bti2.task_oid = jbt.task_oid GROUP BY 	bti2.task_item_code)");
		sql.append(" AND jbti.TASK_ITEM_STATUS = 1 AND jpp.person_oid = ").append(personOid);
		List<Object[]> list = DaoUtil.findWithSQL(sql.toString());
		List<JSONObject> listJson = new ArrayList<JSONObject>();
		if(CollectionUtils.isNotEmpty(list)){
			for(Object[] obj : list){
				JSONObject json = new JSONObject();
				json.put("name", obj[0]==null?null: obj[0].toString());
				listJson.add(json);
			}
		}
		return listJson;
	}
	
	/**
	 * 通过姓名和出生日期获取人员基础信息
	 * @param name 姓名
	 * @param birthday 出生日期
	 * @return
	 * @throws ServiceException
	 */
	public static PbPersonInfoDTO getPbPersonInfoDTOByNameAndBirthday(String name, Date birthday) throws ServiceException {
		if(StringUtils.isBlank(name)||birthday==null)
		{
			throw new ServiceException(null,"缺少查询条件!");
		}
		String hql = "from PbPersonInfo pi where pi.name='"+name+"' and date_format(pi.birthday,'%Y-%m-%d')='"+DateUtil.format(birthday, DateUtil.DATE_PATTERN_DEFAULT)+"'";
		List<PbPersonInfo> list = DaoUtil.find(hql);
		if(CollectionUtils.isNotEmpty(list)) {
			return BeanHelper.copyProperties(list.get(0), PbPersonInfoDTO.class);
		}
		return null;
	}
}
