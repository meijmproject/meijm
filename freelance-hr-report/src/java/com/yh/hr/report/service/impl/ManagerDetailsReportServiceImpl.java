package com.yh.hr.report.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yh.hr.report.dto.ManagerDetailsReportDTO;
import com.yh.hr.report.queryhelper.ManagerDetailsReportServiceQueryHelper;
import com.yh.hr.report.service.ManagerDetailsReportService;
import com.yh.hr.report.utils.Constants;
import org.apache.commons.collections.CollectionUtils;

import com.yh.component.taglib.TableTagBean;
import com.yh.platform.core.exception.ServiceException;

public class ManagerDetailsReportServiceImpl implements ManagerDetailsReportService {

	@Override
	public Map<String, List<ManagerDetailsReportDTO>> getManagerDetails(TableTagBean ttb) throws ServiceException {
		ttb.getCondition().put("orgType", Constants.HSPSZHP_ORG_TYPE_4+","+
										 Constants.HSPSZHP_ORG_TYPE_5+","+
										 Constants.HSPSZHP_ORG_TYPE_6+","+
										 Constants.HSPSZHP_ORG_TYPE_7);//4医疗临床,5医疗技术,6医疗辅助,7行政后勤
		//获取医院领导的人员名单(事业岗位聘任信息中的职务属性,单位领导正职-院长,单位领导副职-副院长)
		//事业岗位聘任信息中还没有添加职务属性字段
		List<ManagerDetailsReportDTO> first = ManagerDetailsReportServiceQueryHelper.getTopmanagerDetails(ttb);
		
		//获取临床科室负责人的人员名单(院内中层职务任职信息,内设机构中的医疗临床和医疗技术)
		ttb.getCondition().put("orgType",Constants.HSPSZHP_ORG_TYPE_4+","+Constants.HSPSZHP_ORG_TYPE_5);//4医疗临床,5医疗技术
		List<ManagerDetailsReportDTO> second = ManagerDetailsReportServiceQueryHelper.getMidLevelManager(ttb);
		
		//获取各职能部门负责人的人员名单(院内中层职务任职信息,内设机构中的行政后勤)
		ttb.getCondition().put("orgType",Constants.HSPSZHP_ORG_TYPE_7);//7行政后勤
		List<ManagerDetailsReportDTO> third = ManagerDetailsReportServiceQueryHelper.getMidLevelManager(ttb);
		
		//获取医技辅助科室负责人的人员名单(院内中层职务任职信息,内设机构中的医疗辅助)
		ttb.getCondition().put("orgType",Constants.HSPSZHP_ORG_TYPE_6);//6医疗辅助
		List<ManagerDetailsReportDTO> fourth = ManagerDetailsReportServiceQueryHelper.getMidLevelManager(ttb);
		
		Map<String, List<ManagerDetailsReportDTO>> map = new HashMap<String, List<ManagerDetailsReportDTO>>();
		if(!CollectionUtils.isEmpty(first)){
			first.get(0).setRowCount(first.size());
			map.put("first", first);
		}
		if(!CollectionUtils.isEmpty(second)){
			second.get(0).setRowCount(second.size());
			map.put("second", second);
		}
		if(!CollectionUtils.isEmpty(third)){
			third.get(0).setRowCount(third.size());
			map.put("third", third);
		}
		if(!CollectionUtils.isEmpty(fourth)){
			fourth.get(0).setRowCount(fourth.size());
			map.put("fourth", fourth);
		}
		return map;
	}

}
