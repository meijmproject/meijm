package com.yh.hr.info.dataexport.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.yh.hr.info.dataexport.queryhelper.DataExportQueryHelper;
import com.yh.hr.info.dataexport.service.DataExportManagerService;
import com.yh.platform.core.exception.DataAccessFailureException;

public class DataExportManagerServiceImpl implements DataExportManagerService {

	public List<List<Object>> listDataExportPersonInfo(List<String> fieldList, List<String> transFlagList) throws DataAccessFailureException {
		
		List<List<Object>> resultList = new ArrayList<List<Object>>();
		for(int i=0;i<transFlagList.size();i++){
			String transFlag = transFlagList.get(i);  //转码标识
			String field = fieldList.get(i);          //字段名
			
			if("findDept".equals(transFlag)){
				resultList.add(DataExportQueryHelper.findDept(field));  //查询部门
			}else if("findUnit".equals(transFlag)){
				resultList.add(DataExportQueryHelper.findUnit(field));  //查询单位
			}else if(!"none".equals(transFlag)){
				resultList.add(DataExportQueryHelper.findByDic(field, transFlag));  //字典码转码
			}else{
				resultList.add(DataExportQueryHelper.findByPersonInfo(field));  //不做处理，直接查
			}
		}
		return resultList;
	}

}
