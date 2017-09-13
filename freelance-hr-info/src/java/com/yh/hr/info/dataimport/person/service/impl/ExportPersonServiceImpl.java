package com.yh.hr.info.dataimport.person.service.impl;

import java.util.List;
import com.yh.hr.component.im.dto.ColumnDefDTO;
import com.yh.hr.component.im.dto.TableDefDTO;
import com.yh.hr.component.im.tablehandle.service.TableDataSelectService;
import com.yh.hr.info.dataimport.person.queryhelper.ExportPersonQueryHelper;
import com.yh.hr.info.dataimport.person.service.ExportPersonService;

public class ExportPersonServiceImpl implements ExportPersonService {
    private TableDataSelectService tableDataSelectService;
	public void setTableDataSelectService(
			TableDataSelectService tableDataSelectService) {
		this.tableDataSelectService = tableDataSelectService;
	}
	public List<List<ColumnDefDTO>> listDicPerson() throws Exception {
		TableDefDTO tableDefDTO = ExportPersonQueryHelper.listNoDicMapped();
		return tableDataSelectService.selectData(tableDefDTO);
	}
	
}
