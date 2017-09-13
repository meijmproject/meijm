package com.yh.hr.report.queryhelper;

import java.util.ArrayList;
import java.util.List;

import com.yh.hr.report.dto.QsReportDTO;
import org.apache.commons.lang.StringUtils;

import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.web.UserContext;

public class ReportQueryHelper {

	public static List<QsReportDTO> findByParentOid(Long parentOid, String statType) throws ServiceException {
		StringBuilder hql = new StringBuilder("select * from YHC_QS_REPORT_INFO qr where 1=1");
		if (parentOid!=null&&StringUtils.isNotEmpty(parentOid.toString())) {
			hql.append(" and parent_id ="+parentOid);
			hql.append(" and EXISTS (SELECT 1 FROM yhb_function_res fr,yhb_resources r,yhb_role_function rf,");
			hql.append(" yhb_system_position sp,yhb_user_sp usp WHERE  r.res_id = fr.res_id AND fr.func_id = rf.func_id");
			hql.append(" AND rf.role_id = sp.function_role_id AND sp.system_position_oid = usp.system_position_oid and r.res_value = qr.ACTION");
			hql.append("	AND usp.user_id = '"+UserContext.getLoginUserID()+"' )");
		}else {
			hql.append(" and parent_id = '' or parent_id is null");
		}
		if(statType!=null&&!"".equals(statType)) {
			hql.append(" and qr.stat_type = '"+statType+"'");
		}
		hql.append(" order by order_Seq asc");
		List<Object[]> list = DaoUtil.findWithSQL(hql.toString());
		List<QsReportDTO> qsReportDTOList =  new ArrayList<QsReportDTO>();
		for(Object[] obj :list){
			QsReportDTO qs = new QsReportDTO();
			qs.setId(Long.valueOf(obj[0].toString()));
			qs.setName(obj[1].toString());
			qs.setLocation(obj[4].toString());
			qsReportDTOList.add(qs);
		}
        //List<QsReportDTO> list = BeanHelper.copyProperties(DaoUtil.findWithSQL(hql.toString()),QsReportDTO.class);
        
        return qsReportDTOList;
	}
}
