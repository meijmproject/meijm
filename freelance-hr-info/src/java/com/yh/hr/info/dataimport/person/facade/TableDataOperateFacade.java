package com.yh.hr.info.dataimport.person.facade;

import com.yh.hr.component.im.dto.TableDefDTO;
import com.yh.hr.component.im.tablehandle.service.TableDataEmptyService;
import com.yh.hr.component.im.tablehandle.service.TableDataInsertService;
import com.yh.platform.core.exception.ServiceException;

public class TableDataOperateFacade {

	private TableDataInsertService tableDataInsertService;
	private TableDataEmptyService tableDataEmptyService;

	public void setTableDataInsertService(
			TableDataInsertService tableDataInsertService) {
		this.tableDataInsertService = tableDataInsertService;
	}


	public void setTableDataEmptyService(TableDataEmptyService tableDataEmptyService) {
		this.tableDataEmptyService = tableDataEmptyService;
	}

	/**
	 * 清空表数据
	 * @param tableCode
	 * @throws ServiceException
	 */
	public void emptyTable(String tableCode) throws ServiceException {
		tableDataEmptyService.emptyTable(tableCode);
	}


	/**
	 * 插入数据
	 * @param tableDefDTO
	 * @throws ServiceException
	 */
	public void insertData(TableDefDTO tableDefDTO) throws ServiceException {
		tableDataInsertService.insertData(tableDefDTO);
	}
}
