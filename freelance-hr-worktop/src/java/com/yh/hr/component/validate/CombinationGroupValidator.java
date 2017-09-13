/**
 * 
 */
package com.yh.hr.component.validate;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.yh.component.validate.BaseValidateService;
import com.yh.platform.core.exception.ServiceException;

/**
 * 组合分组验证
 * @author	zhangqp
 * @version	1.0,	16/10/25
 */

public class CombinationGroupValidator implements BaseValidateService {

	private List<BaseValidateService> validators;
	
	@Override
	public void validate() throws ServiceException {
		if (CollectionUtils.isNotEmpty(validators)) {
			StringBuilder rt = new StringBuilder();
			for (BaseValidateService validator : validators) {
				try {
					validator.validate();
				} catch (ServiceException e) {
					e.printStackTrace();
					rt.append("<br>");
					rt.append(e.getMessage());
				}
			}
			if (rt.length() > 0) {
				throw new ServiceException(null, rt.substring(4));
			}
		}
	}

	public List<BaseValidateService> getValidators() {
		return validators;
	}

	public void setValidators(List<BaseValidateService> validators) {
		this.validators = validators;
	}

}
