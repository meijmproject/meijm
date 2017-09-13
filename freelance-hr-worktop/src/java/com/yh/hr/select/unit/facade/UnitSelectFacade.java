package com.yh.hr.select.unit.facade;

import java.util.List;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.select.unit.service.UnitSelectService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.SpringBeanUtil;

import net.sf.json.JSONObject;

public class UnitSelectFacade {

	public List<JSONObject> listUnitInfo(TableTagBean ttb) throws ServiceException {
		String itemCode = ttb.getCondition().get("itemCode").replace("'", "");
		String itemCodeNode = ttb.getCondition().get("itemCodeNode").replace("'", "");
		String beanId = "select" + itemCode + itemCodeNode;
		
		UnitSelectService unitSelectService;
		try {
			unitSelectService = (UnitSelectService) SpringBeanUtil.getBean(beanId);
		} catch (NoSuchBeanDefinitionException e) {
			unitSelectService = (UnitSelectService) SpringBeanUtil.getBean("selectUnitDefault");
		}
		
		return unitSelectService.listUbUnitInfo(ttb);
	}

}
