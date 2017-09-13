package com.yh.admin.message.queryhelper;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.yh.admin.message.dto.MessageBoardDto;
import com.yh.component.taglib.TableTagBean;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.util.StringMap;

public class MessageBoardQueryHelper {

	public static List<MessageBoardDto> find(TableTagBean ttb) throws ServiceException {
		StringBuilder hql = new StringBuilder();
        HashMap<String, Object> hqlParams = new HashMap<String, Object>();
        buildHQL(ttb.getCondition(), hql, hqlParams);
        hql.append(" order by effDate desc");
        /*String order = ttb.getOrderBy();
        if (order != null) {
            if (ttb.getAsc()) {
                hql.append(" order by " + order + " asc");
            } else {
                hql.append(" order by " + order + " desc");
            }
        }*/
        List<MessageBoardDto> list = BeanHelper.copyProperties(DaoUtil.listByCondition("select mb "+hql.toString(), hqlParams, ttb.getPage(), ttb.getPageSize()),MessageBoardDto.class);
        ttb.setList(list);
        ttb.setTotal(DaoUtil.countByCondition("select count(*)"+hql+"", hqlParams));
        return list;
	}

	private static void buildHQL(StringMap params, StringBuilder hql, HashMap<String, Object> hqlParams) {
		hql.append(" from MessageBoard mb where 1=1 ");
		String show = params.get("show");
		if (StringUtils.isNotEmpty(show)) {//首页展示
			hql.append(" and mb.effDate <= :effDate");
			hqlParams.put("effDate", DateUtil.parseDate(DateUtil.nowString(DateUtil.DATE_PATTERN_DEFAULT)));
			hql.append(" and mb.expDate >= :expDate");
			hqlParams.put("expDate", DateUtil.parseDate(DateUtil.nowString(DateUtil.DATE_PATTERN_DEFAULT)));
		}
		String title = params.get("title");
		if (StringUtils.isNotEmpty(title)) {
			hql.append(" and mb.title like :title");
			hqlParams.put("title", "%"+title+"%");
		}
		String messageOid = params.get("messageOid");
		if (StringUtils.isNotEmpty(messageOid)) {
			hql.append(" and mb.messageOid = :messageOid");
			hqlParams.put("messageOid", Long.valueOf(messageOid));
		}
	}

}
