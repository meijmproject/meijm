package com.yh.hr.worktop.util;

import java.util.List;

import com.yh.hr.worktop.dto.TaskResponseDTO;
import org.apache.commons.lang.StringUtils;

/**
 * 业务事项操作返回信息 - 帮助类
 * 
 * @author Xing.Liu
 * @created 2013-04-26
 */
public class TaskResponseUtil {
	/**
	 * 操作转换成返回提示信息
	 * 
	 * @param applyResponseDTOs
	 *            操作返回信息-集合
	 * @return message 操作返回信息
	 */
	public static String conversionReportDTO(List<TaskResponseDTO> taskResponseDTOs) {
		return conversionReportDTO(taskResponseDTOs, null);
	}

	/**
	 * 操作转换成返回提示信息
	 * 
	 * @param applyResponseDTOs
	 *            操作返回信息-集合
	 * @return message 操作返回信息
	 */
	public static String conversionReportDTO(List<TaskResponseDTO> taskResponseDTOs, String definedmessage) {
		int failureNum = 0;
		// 计算出操作失败的笔数
		for (TaskResponseDTO taskResponseDTO : taskResponseDTOs) {
			if (taskResponseDTO.getIsSuccess().equalsIgnoreCase("Y")) {
				failureNum++;
			}
		}
		StringBuffer messageBuf = new StringBuffer();
		if (failureNum > 10) {
			messageBuf.append("<div style=\"overflow:auto\" style=\"height:200px\" style=\"width:450px\"><table><tr><td style=\"width:60px\">[序号]&nbsp;&nbsp;</td><td>[名称]&nbsp;&nbsp;</td><td>&nbsp;&nbsp;[原因]&nbsp;&nbsp;</td></tr>");
		} else {
			messageBuf.append("<table><tr><td style=\"width:60px\">[序号]&nbsp;&nbsp;</td><td style=\"width:120px\">[名称]&nbsp;&nbsp;</td><td>&nbsp;&nbsp;[原因]&nbsp;&nbsp;</td></tr>");
		}
		int successNum = 0;
		int totalNum = 0;
		int failNum = 1;
		for (TaskResponseDTO taskResponseDTO : taskResponseDTOs) {
			if (taskResponseDTO.getIsSuccess().equalsIgnoreCase("Y"))// 操作成功
			{
				successNum++;
			} 
			// 操作失败
			else if (taskResponseDTO.getIsSuccess().equalsIgnoreCase("N"))
			{
				messageBuf.append("<tr ><td>" + failNum + "&nbsp;</td><td>" + (StringUtils.isEmpty(taskResponseDTO.getApplyName()) ? "" : taskResponseDTO.getApplyName()) + "&nbsp;</td><td>"
						+ (StringUtils.isEmpty(taskResponseDTO.getResultDesc()) ? "" : taskResponseDTO.getResultDesc()) + "&nbsp;</td></tr>");
				failNum++;
			}
			totalNum++;
		}
		messageBuf.append("</table></div>");
		if (StringUtils.isBlank(definedmessage)) {
			messageBuf.append("<table align=\"center\"><tr><td>该操作成功[" + successNum + "]笔。&nbsp;</td></tr></table>");
			if (totalNum == successNum)// 如果所有业务均操作成功，则不需要显示头信息。
			{
				String message = "<table align=\"center\"><tr><td>该操作成功[" + successNum + "]笔。&nbsp;</td></tr></table>";
				return message;
			}
		} else {
			messageBuf.append(definedmessage);
		}

		return messageBuf.toString();
	}

	/**
	 * 获取错误信息反馈
	 * 
	 * @param message
	 *            错误信息
	 * @param applyName
	 *            申请名称
	 * @return applyResponseDTO 操作返回信息
	 * @throws Exception
	 */
	public static TaskResponseDTO getFailResponseDTO(String message, String applyName) {
		TaskResponseDTO taskResponseDTO = new TaskResponseDTO();
		taskResponseDTO.setIsSuccess("N");
		taskResponseDTO.setResultDesc(message);
		taskResponseDTO.setApplyName(applyName);
		return taskResponseDTO;
	}

	/**
	 * 获取成功信息反馈
	 * 
	 * @return taskResponseDTO 操作返回信息
	 * @throws Exception
	 */
	public static TaskResponseDTO getSuccessResponseDTO() {
		TaskResponseDTO taskResponseDTO = new TaskResponseDTO();
		taskResponseDTO.setIsSuccess("Y");
		return taskResponseDTO;
	}
}
