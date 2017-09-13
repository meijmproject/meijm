package com.yh.hr.res.bt.queryhelper;

import com.yh.hr.res.bt.bo.BtTask;
import com.yh.hr.res.bt.dto.BtTaskDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.DataAccessFailureException;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.web.UserContext;


/**
 * 查询待办事项服务类
 * 
 * @author Administrator
 *
 */
public class BtTaskQueryHelper {
	
	/**
	 * 根据流程ID查找task
	 * @param processId
	 * @return
	 * @throws DataAccessFailureException
	 */
	public static BtTask getByProcessId(Long flowInstanceOid) throws DataAccessFailureException {
		return DaoUtil.uniqueResult("from BtTask task where task.flowInstanceOid = ?", flowInstanceOid);
	}
	
	/**
	 * 根据流程ID查找task
	 * @param processId
	 * @return
	 * @throws DataAccessFailureException
	 */
	public static BtTask getByRefTaskId(Long refBizTaskOid) throws DataAccessFailureException {
		return DaoUtil.uniqueResult("from BtTask task where task.refBizTaskOid = ?", refBizTaskOid);
	}

	/**
	 * 通过主键获取
	 * @param taskOid
	 * @return
	 * @throws DataAccessFailureException
	 */
	public static BtTask getBtTaskById(Long taskOid) throws DataAccessFailureException {
		return DaoUtil.uniqueResult("from BtTask task where task.taskOid = ?", taskOid);
	}
	

	public static Long createBtTask(BtTaskDTO btTaskDTO) throws ServiceException {
		BtTask btTask =BeanHelper.copyProperties(btTaskDTO,BtTask.class);
		btTask.setCreatedByCode(UserContext.getLoginUserID());
		btTask.setCreatedByName(UserContext.getLoginUserName());
		btTask.setCreatedDate(DateUtil.now());
		btTask.save();
		return btTask.getTaskOid();
	}
}
