package com.yh.hr.orghc.ub.queryhelper;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import com.yh.hr.orghc.ub.bo.UbUnit;
import com.yh.hr.orghc.ub.dto.UbUnitDTO;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * @desc 单位信息查询工具类
 */
public class UbUnitQueryHelper {
	
	/**
	 * 获取单位信息
	 * @param unitOid
	 * @return UbUnit
	 * @throws ServiceException
	 */
	public static UbUnit get(Long unitOid) throws ServiceException {
		return DaoUtil.get(UbUnit.class, unitOid);
	}
	
	/**
	 * 获取单位信息
	 * @param unitOid
	 * @return UbUnitDTO
	 * @throws ServiceException
	 */
	public static UbUnitDTO getUbUnitDTOById(Long unitOid) throws ServiceException {
		return BeanHelper.copyProperties(DaoUtil.get(UbUnit.class, unitOid), UbUnitDTO.class);
	}

	/**
	 * 获取单位所有下设单位信息
	 * @param unitOid
	 * @return List<UbUnit>
	 * @throws ServiceException
	 */
	public static List<UbUnit> listUnitSonsByUnitOid(Long unitOid) throws ServiceException {
        StringBuffer sql = new StringBuffer("select bu.unit_oid,bu.parent_unit_oid,bu.order_of_view,bu.order_of_all from yhe_ub_unit bu start with bu.unit_oid = ").append(unitOid);
        sql.append(" connect by prior bu.unit_oid = bu.parent_unit_oid ");
        sql.append(" order by bu.order_of_all ");
        List<Object[]> list = DaoUtil.findWithSQL(sql.toString());
        return buildUbUnit(list);
	}

	private static List<UbUnit> buildUbUnit(List<Object[]> list) {
		List<UbUnit> ubUnits = new ArrayList<UbUnit>();
		if(CollectionUtils.isNotEmpty(list)){
			for (int i = 0; i < list.size(); i++){
				Object[] task = list.get(i);
				UbUnit ubUnit = new UbUnit();
				ubUnit.setUnitOid(task[0] == null ? null : Long.parseLong(task[0].toString()));
				ubUnit.setParentUnitOid(task[1] == null ? null : Long.parseLong(task[1].toString()));
				ubUnit.setOrderOfView(task[2] == null ? null : task[2].toString());
				ubUnit.setOrderOfAll(task[3] == null ? null : task[3].toString());
				ubUnits.add(ubUnit);
			}
		}
		return ubUnits;
	}
	
	public static List<Long> findUnitOidsByAdminUnitOid(Long adminUnitOid) throws ServiceException{
		StringBuffer sql = new StringBuffer("select bu.unit_oid from yhe_ub_unit bu start with bu.unit_oid = ").append(adminUnitOid);
        sql.append(" connect by prior bu.unit_oid = bu.parent_unit_oid ");
        return DaoUtil.findWithSQL(sql.toString());
	}
	

    /**
     * 生成单位UnitCode
     * @param unitOid
     * @param parentUnitOid
     * @return
     * @throws ServiceException
     */
    public static String createUnitCode(Long unitOid, Long parentUnitOid) throws ServiceException {
        return createUnitCodeByParentUnitOid(parentUnitOid);
    }

    public static String createUnitCodeByParentUnitOid(Long parentUnitOid) throws ServiceException {
        if (parentUnitOid == null || parentUnitOid.intValue() == 0){
            List<String> list = DaoUtil.find("select max(t.unitCode) from UbUnit t where length(t.unitCode)<=5");
            return builderCode(hasCode(list) ? list.get(0) : "0", 5);
        } else {
        	UbUnit parent = DaoUtil.get(UbUnit.class,parentUnitOid);
            if(parent == null) throw new ServiceException("找不到parentUnitOid对应的Unit");
            String parentUnitCode = parent.getUnitCode();
            if (parentUnitCode == null) {
                //一般不可能的了
                parentUnitCode = createUnitCode(parentUnitOid, parent.getParentUnitOid());
                parent.setUnitCode(parentUnitCode);
                parent.update();
            }
            StringBuilder hql = new StringBuilder();
            hql.append("select max(t.unitCode) from UbUnit t where length(t.unitCode)<=")
                    .append(parentUnitCode.length() + 5)
                    .append(" and length(t.unitCode)>")
                    .append(parentUnitCode.length())
                    .append(" and  t.unitCode like '")
                    .append(parentUnitCode)
                    .append("%'");
            List<String> list = DaoUtil.find(hql.toString());
            return builderCode(hasCode(list) ? list.get(0) : parentUnitCode+"00000", parentUnitCode.length()+5);
        }
    }

    private static boolean hasCode(List<String> list) {
        if(CollectionUtils.isEmpty(list)) return false;
        return !GenericValidator.isBlankOrNull(list.get(0));
    }

    private static String builderCode(String beforeCode, long size) {
        long number = Long.valueOf(beforeCode).longValue();
        number++;
        String str = String.valueOf(number);

        long strSize = size - (str.length());
        for (int i = 0; i < strSize; i++) {
            str = "0" + str;
        }
        return str;
    }
    
    /**
     * 检查单位名称是否存在
     * @param unitName
     * @param unitOid
     * @return
     * @throws ServiceException
     */
    public static boolean checkUniqueUnit(String unitName,Long unitOid)throws ServiceException {
    	
    	if(StringUtils.isBlank(unitName))
		{
			throw new ServiceException(null,"缺少查询条件!");
		}
    	//正常状态下
		String hql = "from UbUnit uu where uu.unitName='"+unitName+"'"
		    + " and uu.unitStatus in('"+DicConstants.YHRS0101_2+"')";
		
		if (null!=unitOid) {
			hql+=(" and uu.unitOid !="+unitOid);
		}
		//得到列表
		List<UbUnit> ubUnits = DaoUtil.find(hql);
		if(ubUnits!=null&&ubUnits.size()>0){
			return true;
		}
		
		return false;
    }
}
