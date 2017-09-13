package com.yh.component.approvalnode.service;

import java.util.List;

import com.yh.component.approvalnode.bo.ApprovalNodeDetail;
import com.yh.platform.core.exception.ServiceException;
/**
 * @description 审批环节定义service
 * @author wangx
 * @date 2017-05-16
 * @version 1.0
 */
public interface ApprovalNodeService {

	/**
	 * 通过事项编码和事项环节编码查询该环节之后的所有审批环节
	 * @param itemCode
	 * @param itemNodeCode
	 * @return
	 * @throws ServiceException
	 */
	public List<ApprovalNodeDetail> getApprovalNodeListByItemCodeAndItemNodeCode(String itemCode, String itemNodeCode) throws ServiceException;

	/**
	 * 通过事项编码、事项环节编码和审批环节编码查询该审批环节
	 * @param itemCode
	 * @param itemNodeCode
	 * @param nodeCode
	 * @return
	 * @throws ServiceException
	 */
	public ApprovalNodeDetail getApprovalNodeByItemCodeAndItemNodeCodeAndNodeCode(String itemCode, String itemNodeCode, String nodeCode) throws ServiceException;
}
