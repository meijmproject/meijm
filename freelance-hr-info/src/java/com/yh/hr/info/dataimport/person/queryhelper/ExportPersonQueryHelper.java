package com.yh.hr.info.dataimport.person.queryhelper;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.component.im.dto.ColumnDefDTO;
import com.yh.hr.component.im.dto.TableDefDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;

public class ExportPersonQueryHelper {
	/*
	 * 查询出字典未映射的代办数量
	 */
	public static TableDefDTO listNoDicMapped() throws ServiceException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT jict.DATABASE_COLUMN_CODE,jict.DATABASE_COLUMN_CODE_NAME,jict.TEMPLATE_COLL_NAME from yhc_im_collect_template jict ");
		sql.append("WHERE jict.IMPORT_COLL_NAME is NOT NULL AND jict.IMPORT_COLL_NAME <>'' AND jict.EFFECTIVE_FLAG = '1'");
		TableDefDTO tableDefDTO = new TableDefDTO();
		List<ColumnDefDTO> listColumnDefDTO = new ArrayList<ColumnDefDTO>(); 
		List<Object[]> list = DaoUtil.findWithSQL(sql.toString());
		if(CollectionUtils.isNotEmpty(list)){
			for(Object[] obj : list){
				ColumnDefDTO ColumnDefDTO = new ColumnDefDTO();
				String databaseColumnCode = obj[0]==null?null:obj[0].toString();
				String databaseColumnCodeName = obj[1]==null?null:obj[1].toString();
				String templateCollName = obj[2]==null?null:obj[2].toString();
				ColumnDefDTO.setColumnCode(databaseColumnCodeName==null?databaseColumnCode:databaseColumnCodeName);
				ColumnDefDTO.setColumnName(templateCollName);
				listColumnDefDTO.add(ColumnDefDTO);
			}
			ColumnDefDTO ColumnDefDTO1 = new ColumnDefDTO();
			ColumnDefDTO1.setColumnCode("check_person_info_oid");
			ColumnDefDTO1.setColumnName("校核人员oid");
			listColumnDefDTO.add(ColumnDefDTO1);
		}
		tableDefDTO.setColumns(listColumnDefDTO);
		tableDefDTO.setTableCode("yhc_im_check_person_info");
		return tableDefDTO;
	}
}
