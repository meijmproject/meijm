/**
 * 
 */
package com.yh.hr.component.infotemplate.queryhelper;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.component.infotemplate.dto.ItLibraryGroupDetailDTO;
import com.yh.hr.res.unit.dto.UtUnitDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.DataAccessFailureException;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DataConverUtils;

/**
 * 
 * @author	cheny
 * @version	1.0,	31/08/16
 */

public class PersonInformationQueryHelper {
	public static List<ItLibraryGroupDetailDTO> findInforList(String functionCode) throws ServiceException {
		StringBuffer sql=new StringBuffer("select jilgd.library_group_oid,jilgd.library_name,jilgd.library_oid,jil.info_url,g.library_group_name,jilgd.IS_DEFAULT_SHOW");
		sql.append(" from YHD_IT_LIBRARY jil,YHD_IT_LIBRARY_GROUP_DETAIL jilgd, YHD_IT_LIBRARY_GROUP g  where jilgd.library_oid = jil.library_oid");
		sql.append(" and g.library_group_oid = jilgd.library_group_oid  and jilgd.library_group_oid in");
		sql.append(" (select jilg2.library_group_oid from YHD_IT_FUNCTION jif,YHD_IT_LIBRARY_GROUP jilg1,YHD_IT_LIBRARY_GROUP jilg2");
		sql.append(" where jif.library_group_oid = jilg1.library_group_oid and jilg1.library_group_oid = jilg2.parent_group_oid and jif.function_code = ?)");  //    gg
		sql.append(" order by jilgd.library_group_oid asc	,jilgd.order_seq asc");
		List<Object[]> list=DaoUtil.findWithSQL(sql.toString(), functionCode);
		return BeanHelper.copyProperties(list, new BeanHelper.PropertiesHandler<Object[], ItLibraryGroupDetailDTO>() {
			public ItLibraryGroupDetailDTO getValue(Object[] src) throws ServiceException {
				ItLibraryGroupDetailDTO dto = new ItLibraryGroupDetailDTO();
				dto.setLibraryGroupOid(Long.valueOf((DataConverUtils.toString(src[0]))));
				dto.setLibraryGroupName((DataConverUtils.toString(src[4])));
				dto.setLibraryName((DataConverUtils.toString(src[1])));
				dto.setLibraryOid(Long.valueOf((DataConverUtils.toString(src[2]))));
				dto.setInfoUrl((DataConverUtils.toString(src[3])));
				dto.setIsDefaultShow((DataConverUtils.toString(src[5])));
				return dto;
			}});
	}

	public static UtUnitDTO findUnitType(String unitOid) throws DataAccessFailureException {
		StringBuffer sql=new StringBuffer();
		sql.append("select juu.unit_kind from yhc_ut_unit juu where juu.unit_oid="+unitOid);
		List<String> list=DaoUtil.findWithSQL(sql.toString());
		UtUnitDTO utUnitDTO=new UtUnitDTO();
		if(CollectionUtils.isNotEmpty(list)){
			for(int i=0;i<1;i++){
				utUnitDTO.setUnitKind(list.get(0));
			}
		}
		return utUnitDTO;
	}
	
}
