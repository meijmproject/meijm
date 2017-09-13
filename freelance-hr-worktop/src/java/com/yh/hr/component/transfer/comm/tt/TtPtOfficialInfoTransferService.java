/**
 * 
 */
package com.yh.hr.component.transfer.comm.tt;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.yh.hr.component.tansfer.service.InfoTransferService;
import org.apache.commons.lang.ArrayUtils;
import com.yh.hr.res.bt.bo.BtTask;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.hr.res.pt.bo.PtOfficialInfo;
import com.yh.hr.res.pt.bo.PtPerson;
import com.yh.hr.res.pt.dto.PtOfficialInfoDTO;
import com.yh.hr.res.pt.dto.PtPositioningInfoDTO;
import com.yh.hr.res.pt.service.PtOfficialInfoService;
import com.yh.hr.res.pt.service.PtPositioningService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.util.StringUtil;
import com.yh.platform.core.web.UserContext;

/**
 * @desc 业务库创建公务员登记业务信息
 * @author luqy
 * @createDate 2016-11-5下午03:06:41
 */
public class TtPtOfficialInfoTransferService implements InfoTransferService {
	private PtOfficialInfoService ptOfficialInfoService = (PtOfficialInfoService) SpringBeanUtil.getBean("ptOfficialInfoService");
	private PtPositioningService  ptPositioningService = (PtPositioningService) SpringBeanUtil.getBean("ptPositioningService");
	public void transfer(Long refBizPersonOid, Long bizPersonOid) throws ServiceException {
		PtOfficialInfo ptOfficialInfo = null;
		PtOfficialInfoDTO ptOfficialInfoDTO = ptOfficialInfoService.getPtOfficialInfoDTO(refBizPersonOid);
		if(ptOfficialInfoDTO != null){
			ptOfficialInfo = BeanHelper.copyProperties(ptOfficialInfoDTO, PtOfficialInfo.class);
			ptOfficialInfo.setOfficialOid(null);
		}else{
			ptOfficialInfo = new PtOfficialInfo();
			PtPositioningInfoDTO ptPositioningDTO = ptPositioningService.getTopPtPositioningInfoDTO(bizPersonOid,DicConstants.YHRS0003_1);
			if(null != ptPositioningDTO){
				ptOfficialInfo.setEnrolDate(ptPositioningDTO.getDutyDate());
			}
		}
		ptOfficialInfo.setBizPersonOid(bizPersonOid);
		PtPerson ptPerson = DaoUtil.get(PtPerson.class, refBizPersonOid);
		if(StringUtil.isBlank(ptOfficialInfo.getEnterMode())){
			BtTask btTask = DaoUtil.get(BtTask.class, ptPerson.getTaskOid());
			if(btTask != null){
				ptOfficialInfo.setEnterMode((String)this.getValueInMapKey(this.COMMON_MAP, btTask.getItemCode()));
			}
		}
		ptOfficialInfo.setCreatedByCode(UserContext.getLoginUserID());
		ptOfficialInfo.setCreatedByName(UserContext.getLoginUserName());
		ptOfficialInfo.setCreatedDate(DateUtil.now());
		ptOfficialInfo.save();
	}
	
	
	private Object getValueInMapKey(Map<Object, List<Object>> map , Object value){
		Set<Object> keySet = map.keySet();
		for (Object key : keySet){
			List<Object> objLs = map.get(key);
			if (ArrayUtils.contains(objLs.toArray(), value)) { return key; }
		}
		return null;
	}
	
	private Map<Object, List<Object>>	COMMON_MAP	= new HashMap<Object, List<Object>>();
	public void setCOMMON_MAP(Map<Object, List<Object>> COMMON_MAP) {
		this.COMMON_MAP = COMMON_MAP;
	}
}

