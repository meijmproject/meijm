package com.yh.component.approvalnode.queryhelper;

import java.util.List;

import com.yh.component.approvalnode.bo.ApprovalNodeDetail;
import com.yh.platform.core.constant.Constant;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
/**
 * @description 审批环节定义查询类
 * @author wangx
 * @date 2017-05-16
 * @version 1.0
 */
public class ApprovalNodeQueryHelper {

	/**
	 * 通过事项编码和事项环节编码查询该环节之后的所有审批环节
	 * @param itemCode
	 * @param itemNodeCode
	 * @return
	 * @throws ServiceException
	 */
	public static List<ApprovalNodeDetail> getApprovalNodeListByItemCodeAndItemNodeCode(String itemCode, String itemNodeCode) throws ServiceException {
		StringBuffer sb = new StringBuffer();
		sb.append("select ad from ApprovalNodeDefinition an,ApprovalNodeDetail ad where an.approvalNodeDefinitionOid=ad.approvalNodeDefinitionOid")
		.append(" and an.isActive='").append(Constant.YES).append("' and ad.isActive='").append(Constant.YES).append("'")
		.append(" and an.itemCode='").append(itemCode).append("' and an.itemNodeCode='").append(itemNodeCode).append("'");
		return DaoUtil.find(sb.toString());
	}

	/**
	 * 通过事项编码、事项环节编码和审批环节编码查询该审批环节
	 * @param itemCode
	 * @param itemNodeCode
	 * @param nodeCode
	 * @return
	 * @throws ServiceException
	 */
	public static ApprovalNodeDetail getApprovalNodeByItemCodeAndItemNodeCodeAndNodeCode(String itemCode, String itemNodeCode, String nodeCode) throws ServiceException {
		StringBuffer sb = new StringBuffer();
		sb.append("select ad from ApprovalNodeDefinition an,ApprovalNodeDetail ad where an.approvalNodeDefinitionOid=ad.approvalNodeDefinitionOid")
		.append(" and an.isActive='").append(Constant.YES).append("' and ad.isActive='").append(Constant.YES).append("'")
		.append(" and an.itemCode='").append(itemCode).append("' and an.itemNodeCode='").append(itemNodeCode).append("'")
		.append(" and ad.nodeCode='").append(nodeCode).append("'");
		return DaoUtil.uniqueResult(sb.toString());
	}
}
