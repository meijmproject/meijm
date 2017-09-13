package com.yh.hr.report.facade.impl;

import java.util.ArrayList;
import java.util.List;

import com.yh.hr.report.dto.QsReportDTO;
import com.yh.hr.report.service.ReportService;
import org.apache.commons.collections.CollectionUtils;

import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.TreeNode;

public class ReportFacadeImpl{
	
	private ReportService reportService;
	
	public void setReportService(ReportService reportService) {
		this.reportService = reportService;
	}

	public List<TreeNode<QsReportDTO>> findReportList(Long parentOid, String statType) throws ServiceException {
		TreeNode<QsReportDTO> root = new TreeNode<QsReportDTO>();
		root.setChildren(getChildren(parentOid,root,statType));

		return root.getChildren();
	}
	
	private List<TreeNode<QsReportDTO>> getChildren(Long parentOid, TreeNode<QsReportDTO> root,String statType) throws ServiceException {
		List<QsReportDTO> children = null;
		children = reportService.find(parentOid,statType);

		List<TreeNode<QsReportDTO>> list = new ArrayList<TreeNode<QsReportDTO>>();

		TreeNode<QsReportDTO> node = null;
		if (CollectionUtils.isNotEmpty(children)) {

			for (QsReportDTO qsReportDTO : children) {
				node = new TreeNode<QsReportDTO>();
				node.setEntry(qsReportDTO);
				node.setId(qsReportDTO.getId());
				node.setChildren(getChildren(qsReportDTO.getId(), node,statType));
				list.add(node);
			}
		}
		return list;
	}

}
