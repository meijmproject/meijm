package com.yh.hr.component.im.dto;

import java.util.List;

/**
 * 表定义DTO
 * @author wangx
 * @date 2017-07-12
 * @version 1.0
 */
public class TableDefDTO {

	private String tableCode;	//表代码
	private String tableName;	//表名称
	private List<ColumnDefDTO> columns; //字段
	
	public String getTableCode() {
		return tableCode;
	}
	public void setTableCode(String tableCode) {
		this.tableCode = tableCode;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public List<ColumnDefDTO> getColumns() {
		return columns;
	}
	public void setColumns(List<ColumnDefDTO> columns) {
		this.columns = columns;
	}
}
