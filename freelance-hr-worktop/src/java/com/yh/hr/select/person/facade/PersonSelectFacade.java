package com.yh.hr.select.person.facade;

import java.util.List;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.select.person.service.PersonSelectService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.SpringBeanUtil;

import net.sf.json.JSONObject;

/**
 * 人员选择
 * @author	zhangqp
 * @version	1.0,	16/11/09
 */
public class PersonSelectFacade {

	public List<JSONObject> listPbpersonInfo(TableTagBean ttb) throws ServiceException {
		String itemCode = ttb.getCondition().get("itemCode").replace("'", "");
		String itemCodeNode = ttb.getCondition().get("itemCodeNode").replace("'", "");
		String beanId = "select" + itemCode + itemCodeNode;
		
		PersonSelectService personSelectService;
		try {
			personSelectService = (PersonSelectService) SpringBeanUtil.getBean(beanId);
		} catch (NoSuchBeanDefinitionException e) {
			personSelectService = (PersonSelectService) SpringBeanUtil.getBean("selectDefault");
		}
		
		return personSelectService.listPbpersonInfo(ttb);
	}

}
