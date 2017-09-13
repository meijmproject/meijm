package com.yh.component.approvalnode.facade.impl;

import java.util.List;

import com.yh.component.approvalnode.bo.ApprovalNodeDetail;
import com.yh.component.approvalnode.facade.ApprovalNodeFacade;
import com.yh.component.approvalnode.service.ApprovalNodeService;
import com.yh.platform.core.exception.ServiceException;

public class ApprovalNodeFacadeImpl implements ApprovalNodeFacade {

	private ApprovalNodeService approvalNodeService;

	public void setApprovalNodeService(ApprovalNodeService approvalNodeService) {
		this.approvalNodeService = approvalNodeService;
	}

	/**
	 * 通过事项编码和事项环节编码查询该环节之后的所有审批环节
	 * @param itemCode
	 * @param itemNodeCode
	 * @return
	 * @throws ServiceException
	 */
	public List<ApprovalNodeDetail> getApprovalNodeListByItemCodeAndItemNodeCode(
			String itemCode, String itemNodeCode) throws ServiceException {
		return approvalNodeService.getApprovalNodeListByItemCodeAndItemNodeCode(itemCode, itemNodeCode);
	}

	/**
	 * 通过事项编码、事项环节编码和审批环节编码查询该审批环节
	 * @param itemCode
	 * @param itemNodeCode
	 * @param nodeCode
	 * @return
	 * @throws ServiceException
	 */
	public ApprovalNodeDetail getApprovalNodeByItemCodeAndItemNodeCodeAndNodeCode(
			String itemCode, String itemNodeCode, String nodeCode)
			throws ServiceException {
		return approvalNodeService.getApprovalNodeByItemCodeAndItemNodeCodeAndNodeCode(itemCode, itemNodeCode, nodeCode);
	}

}
