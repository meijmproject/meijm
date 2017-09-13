package com.yh.hr.res.pt.queryhelper;

import java.util.List;



import com.yh.hr.res.pt.bo.PtPerson;
import com.yh.hr.res.pt.dto.PtPersonDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.StringUtil;

/**
 * @desc 人员基础信息查询工具类
 * @author xiongyx
 * @createDate 2016-10-09
 */
public class PtPersonQueryHelper {
	/*
	 * 通过ID获取
	 */
	public static PtPersonDTO getPtPersonDTOById(Long bizPersonOid) throws ServiceException {
		return BeanHelper.copyProperties(DaoUtil.get(PtPerson.class, bizPersonOid), PtPersonDTO.class);
	}
	
	/**
	 * 通过人员，证件号码，证件类型查询人员信息
	 * @param idCode
	 * @param idNo
	 * @return
	 * @throws ServiceException
	 */
	public static List<PtPersonDTO> checkUniquePbPerson(String idCode,String idNo,Long bizPersonOid) throws ServiceException {
		if(StringUtil.isNotBlank(idCode)&&StringUtil.isNotBlank(idNo))
		{
			String hql = "select pt from PtPerson pt where pt.personStatus in('110','120','130','206','207','208','209','210','300','399') "
					+ "and pt.idCode='"+idCode +"' and pt.idNo='"+idNo+"' and not exists(select 1 from BtTask bt where bt.taskOid =pt.taskOid and bt.processResult in ('1','2'))";
			if (null!=bizPersonOid) {
				hql+=(" and pt.bizPersonOid !="+bizPersonOid);
			}
			return BeanHelper.copyProperties(DaoUtil.find(hql), PtPersonDTO.class);
		}
		return null;
	}

	/**
	 * 通过taskOid查找人员业务基础信息
	 * @param bizTaskOid
	 * @return
	 * @throws ServiceException
	 */
	public static PtPerson getByTaskOid(Long bizTaskOid) throws ServiceException {
		return DaoUtil.uniqueResult("from PtPerson pp where pp.taskOid = ? ", bizTaskOid);
	}
	
	/**
	 * 根据bizPersonOid删除照片信息
	 * @param bizPersonOid
	 * @throws ServiceException
	 */
	public static void deleteImageByBizPersonOid(Long bizPersonOid) throws ServiceException {
		DaoUtil.executeUpdate("delete from PtImage im where im.bizPersonOid = " + bizPersonOid);
	}
	
	/**
	 * 通过personOid查找人员业务基础信息
	 * @param bizTaskOid
	 * @return
	 * @throws ServiceException
	 */
	public static PtPerson getPtPersonDTOByPersonId(Long personOid) throws ServiceException {
		return (PtPerson) DaoUtil.find("from PtPerson pp where pp.personOid = ? ", personOid).get(0);
	}
	
	/**
	 * 通过unitOid查找业务人员基础信息（在职、试用期、长期病休）
	 * @param unitOid
	 * @return
	 * @throws ServiceException
	 */
	public static List<PtPersonDTO> listPtPersonByUnitOid(Long unitOid) throws ServiceException {
		String hql = "from PtPerson pt where pt.personStatus in ('110','120','130') and pt.unitOid = " + unitOid;
		return BeanHelper.copyProperties(DaoUtil.find(hql), PtPersonDTO.class);
	}

	/**
	 * @param personCode
	 * @param bizPersonOid
	 * @return
	 * @throws ServiceException
	 */
	public static List<PtPersonDTO> checkUniquePersonCode(
			String personCode, Long bizPersonOid) throws  ServiceException {
		String hql = "select pt from PtPerson pt,BtTask bt where pt.taskOid=bt.taskOid and bt.processResult is null";
		if (null!=bizPersonOid) {
			hql+=(" and pt.bizPersonOid !="+bizPersonOid);
		}
		if(StringUtil.isNotBlank(personCode))
		{
			hql+=(" and pt.personCode ='"+personCode+"'");
		}
		return BeanHelper.copyProperties(DaoUtil.find(hql), PtPersonDTO.class);
	}
}