package com.yh.hr.component.im.tablehandle.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.yh.hr.component.im.dto.ColumnDefDTO;
import com.yh.hr.component.im.tablehandle.queryHelper.TableDefQueryHelper;
import com.yh.hr.component.im.tablehandle.service.TableDataSelectService;
import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.component.im.dto.TableDefDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DataConverUtils;

/**
 * 组装SQL查询数据实现类
 * @author wangx
 * @date 2017-07-12
 * @version 1.0
 */
public class TableDataSelectServiceImpl implements TableDataSelectService {

	/**
	 * 查询数据
	 * @param tableDefDTO
	 * @return 
	 * @throws ServiceException
	 */
	
	public List<List<ColumnDefDTO>> selectData(TableDefDTO tableDefDTO) throws ServiceException {
		List<List<ColumnDefDTO>> lists = new ArrayList<List<ColumnDefDTO>>();
		if(tableDefDTO!=null) {
			//检查
//			checkData(tableDefDTO);
			
			List<ColumnDefDTO> columns = new ArrayList<ColumnDefDTO>();
			List<ColumnDefDTO> columnList = new ArrayList<ColumnDefDTO>();
			//执行查询sql
			List<Object[]> list = DaoUtil.findWithSQL(buildInsertSql(tableDefDTO));
			if(CollectionUtils.isNotEmpty(list)) {
				for(Object[] objArr : list) {
					columns = tableDefDTO.getColumns();
					columnList = new ArrayList<ColumnDefDTO>();
					for(int i=0;i<objArr.length;i++) {
						ColumnDefDTO dto = new ColumnDefDTO();
						BeanHelper.copyProperties(columns.get(i), dto);
						dto.setColumnValue(objArr[i]==null?null:DataConverUtils.toString(objArr[i]));
						columnList.add(dto);
					}
					lists.add(columnList);
				}
			}
			return lists;
		}
		return null;
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
		String sql = "SELECT "+buildColumns(tableDefDTO.getColumns())+" FROM "+tableCode;
		return sql;
	}
	
	/**
	 * 拼接查询字段
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
}
