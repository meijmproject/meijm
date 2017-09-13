package com.yh.hr.component.im.tablehandle.service.impl;

import java.util.List;

import com.yh.hr.component.im.dto.ColumnDefDTO;
import com.yh.hr.component.im.tablehandle.queryHelper.TableDefQueryHelper;
import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.component.im.dto.TableDefDTO;
import com.yh.hr.component.im.tablehandle.service.TableDataInsertService;
import com.yh.hr.component.im.tablehandle.util.SqlDaoUtil;
import com.yh.platform.core.exception.ServiceException;

/**
 * 组装SQL插入数据实现类
 * @author wangx
 * @date 2017-07-12
 * @version 1.0
 */
public class TableDataInsertServiceImpl implements TableDataInsertService {

	/**
	 * 插入数据
	 * @param tableDefDTO
	 * @throws ServiceException
	 */
	public void insertData(TableDefDTO tableDefDTO) throws ServiceException {
		if(tableDefDTO!=null) {
			//检查
//			checkData(tableDefDTO);
			//执行插入sql
			SqlDaoUtil.executeSqlUpdate(buildInsertSql(tableDefDTO));
		}
	}
	
	/**
	 * 检查
	 * @param tableDefDTO
	 * @throws ServiceException
	 */
	private void checkData(TableDefDTO tableDefDTO) throws ServiceException {
		if(tableDefDTO.getTableCode()==null) {
			throw new ServiceException(null,"表名没有定义！");
		}
		if(CollectionUtils.isEmpty(tableDefDTO.getColumns())) {
			throw new ServiceException(null,"没有表的字段定义！");
		}else {
			for(ColumnDefDTO dto:tableDefDTO.getColumns()) {
				if(dto.getColumnCode()==null||"".equals(dto.getColumnCode())) {
					throw new ServiceException(null,"表的字段代码不能为空！");
				}
				if(!TableDefQueryHelper.checkColumnInTable(tableDefDTO.getTableCode(), dto.getColumnCode())) {
					throw new ServiceException(null,"表"+tableDefDTO.getTableCode()+"不存在字段"+dto.getColumnCode()+"！");
				}
			}
		}
	}
	
	/**
	 * 组装插入SQL
	 * @param tableDefDTO
	 * @return
	 * @throws ServiceException
	 */
	private String buildInsertSql(TableDefDTO tableDefDTO) throws ServiceException {
		String tableCode = tableDefDTO.getTableCode();
		String sql = "INSERT INTO "+tableCode+"("+buildColumns(tableDefDTO.getColumns())+") VALUES("+buildValues(tableDefDTO.getColumns())+")";
		return sql;
	}
	
	/**
	 * 拼接字段
	 * @param columns
	 * @return
	 * @throws ServiceException
	 */
	private String buildColumns(List<ColumnDefDTO> columns) throws ServiceException {
		StringBuffer buff = new StringBuffer();
		for(ColumnDefDTO dto:columns) {
			buff.append(dto.getColumnCode()).append(",");
		}
		buff.deleteCharAt(buff.lastIndexOf(","));
		return buff.toString();
	}
	
	/**
	 * 拼接插入值
	 * @param columns
	 * @return
	 * @throws ServiceException
	 */
	private String buildValues(List<ColumnDefDTO> columns) throws ServiceException {
		StringBuffer buff = new StringBuffer();
		for(ColumnDefDTO dto:columns) {
			if(dto.getColumnValue()!=null) {
				buff.append("'").append(dto.getColumnValue()).append("'").append(",");
			}else {
				buff.append("NULL").append(",");
			}
		}
		buff.deleteCharAt(buff.lastIndexOf(","));
		return buff.toString();
	}
//	
//	public static void main(String[] args) throws ServiceException{
//		TableDefDTO tableDefDTO = new  TableDefDTO();
//		tableDefDTO.setTableCode("yhc_im_import_person_info");
//		List<ColumnDefDTO> list = new ArrayList<ColumnDefDTO>();
//		ColumnDefDTO dto = new ColumnDefDTO();
//		dto.setColumnCode("NAME");
//		dto.setColumnValue("测试用户");
//		ColumnDefDTO dto2 = new ColumnDefDTO();
//		dto2.setColumnCode("SEX_CODE");
//		dto2.setColumnValue("男");
//		list.add(dto);
//		list.add(dto2);
//		tableDefDTO.setColumns(list);
//		new TableDataInsertServiceImpl().insertData(tableDefDTO);
//	}
}
