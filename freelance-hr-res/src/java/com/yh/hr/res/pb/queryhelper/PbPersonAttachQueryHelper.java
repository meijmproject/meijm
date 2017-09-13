package com.yh.hr.res.pb.queryhelper;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.res.pb.bo.PbPersonAttach;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.DataAccessFailureException;

public class PbPersonAttachQueryHelper {

	public static PbPersonAttach findAttachInfoByPersonOid(Long personOid) throws DataAccessFailureException {
		List<PbPersonAttach> list = DaoUtil.findByNamed("from PbPersonAttach where personOid =:personOid", "personOid", personOid);
		if(CollectionUtils.isEmpty(list)) return null;
		return list.get(0);
	}

}
