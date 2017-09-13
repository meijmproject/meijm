package com.yh.hr.report.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.yh.hr.report.service.OfficeCommonService;
import com.yh.hr.res.pb.dto.PbReviewInfoDTO;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.yh.component.dictionary.utils.DicHelper;
import com.yh.component.taglib.TableTagBean;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.hr.res.pb.dto.PbEducationLevelDegreeDTO;
import com.yh.hr.res.pb.dto.PbResumeInfoDTO;
import com.yh.hr.res.pb.service.PbEducationLevelDegreeService;
import com.yh.hr.res.pb.service.PbResumeInfoService;
import com.yh.hr.res.pb.service.PbReviewInfoService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.DateUtil;

/**
 *  User: liul 
 *  Date: 2017-2-25
 *  从OfficePrintService拆分的公共类
 */
public class OfficeCommonServiceImpl implements OfficeCommonService
{
	@SuppressWarnings("unused")
	private Logger log = Logger.getLogger(OfficeCommonServiceImpl.class);

	@SuppressWarnings("unused")
	private final String pattern = "yyyy.MM.dd";

	private final String pattern_for_resume = "yyyy.MM";


	private PbResumeInfoService pbResumeInfoService;

	private PbEducationLevelDegreeService pbEducationLevelDegreeService;
	private PbReviewInfoService pbReviewInfoService;
	

	public void setPbResumeInfoService(PbResumeInfoService pbResumeInfoService) {
		this.pbResumeInfoService = pbResumeInfoService;
	}

	public void setPbEducationLevelDegreeService(
			PbEducationLevelDegreeService pbEducationLevelDegreeService) {
		this.pbEducationLevelDegreeService = pbEducationLevelDegreeService;
	}

	public void setPbReviewInfoService(PbReviewInfoService pbReviewInfoService) {
		this.pbReviewInfoService = pbReviewInfoService;
	}

	/*
	 * (non-Javadoc)
	 * @see com.yh.hr.hspszhp.report.service.OfficeCommonService#getResumeByPersonOid(java.lang.Long, boolean)
	 */
	public List<String> getResumeByPersonOid(Long personOid, boolean hasEducation) throws ServiceException
	{
		List<String> result = new ArrayList<String>();
		if (hasEducation)
		{
			List<PbEducationLevelDegreeDTO> educationLevelDegreeList = pbEducationLevelDegreeService.listPbEducationLevelDegreeByPersonOid(personOid);
			if (null != educationLevelDegreeList && !educationLevelDegreeList.isEmpty())// 组装学习经历信息
			{
				for (PbEducationLevelDegreeDTO edu : educationLevelDegreeList)
				{
					if (edu.getSchoolEnrollDate() == null)
					{
						continue;
					}
					StringBuffer resume = new StringBuffer();
					resume.append(DateUtil.format(edu.getSchoolEnrollDate(), pattern_for_resume));
					resume.append(" 至 ");
					resume.append(edu.getGraduateDate() == null ? "今" : DateUtil.format(edu.getGraduateDate(), pattern_for_resume));
					resume.append(" ");
					resume.append(edu.getSchoolName() == null ? "" : edu.getSchoolName());
					resume.append("  ");
					resume.append(edu.getMajorCode());
					resume.append("  ");
					resume.append(this.getEducationName(edu.getEducationCode()));
					resume.append("  ");
					resume.append(this.getDegreeName(edu.getDegreeCode()));

					result.add(resume.toString());
				}
			}
			
		}
		TableTagBean ttb = new TableTagBean();
		ttb.getCondition().put("personOid", personOid.toString());
		List<PbResumeInfoDTO> resumeInfoList = pbResumeInfoService.find(ttb);
		if (null != resumeInfoList && !resumeInfoList.isEmpty())// 组装工作经历信息
		{
			for (PbResumeInfoDTO info : resumeInfoList)
			{
				if (info.getStartDate() == null)
				{
					continue;
				}
				StringBuffer resume = new StringBuffer();
				resume.append(DateUtil.format(info.getStartDate(), pattern_for_resume));
				resume.append(" 至 ");
				resume.append(info.getEndDate() == null ? "今" : DateUtil.format(info.getEndDate(), pattern_for_resume));
				resume.append("  ");
				resume.append(info.getUnit() == null ? "" : info.getUnit());
				resume.append("  ");
				resume.append(info.getDuty() == null ? "" : info.getDuty());
				result.add(resume.toString());
			}
		}
		return sortWnList(result);
	}

	/*
	 * (non-Javadoc)
	 * @see com.yh.hr.hspszhp.report.service.OfficeCommonService#getReviewInfoByPersonOid(java.lang.Long, int)
	 */
	public String getReviewInfoByPersonOid(Long personOid, int n) throws ServiceException
	{
		//TableTagBean ttb = new TableTagBean();
		//ttb.getCondition().put("personOid", personOid.toString());
		//ttb.getCondition().put("reviewTypeCode", DicConstants.YHRS0069_1);
		List<Date> years = new ArrayList<Date>();
		Date now = DateUtil.now();
		for (int i = 1; i <= n; i++)
		{
			years.add(DateUtil.addYear(now, -i));
		}

		StringBuffer sb = new StringBuffer();
		if (personOid != null && personOid.intValue() != 0)
		{
			// 查基础库
			for (Date date : years)
			{
				//ttb.getCondition().put("reviewYearStr", DateUtil.formatDate(date));
				List<PbReviewInfoDTO> reviewInfo = pbReviewInfoService.findReviewInfoByPersonOidAndReviewYear(personOid,date);
				if (CollectionUtils.isNotEmpty(reviewInfo))
				{
					String temp = DicHelper.viewName(DicConstants.YHRS0070, reviewInfo.get(0).getReviewResultCode().toString());
					if (StringUtils.isBlank(temp))
					{
						continue;
					}
					if (sb.length() > 0)
					{
						sb.append("；");
					}
					sb.append(DateUtil.format(date, "yyyy年"));
					sb.append(" 年度考核结果 ");
					sb.append(temp);
				}
			}
		}
		return sb.toString();
	}
	/**
	 * 排序
	 * @param list
	 * @return
	 */
	private List<String> sortWnList(List<String> list)
	{
		List<String> sortList = new ArrayList<String>();
		String maxInfo = "";
		String curInfo = "";
		int max = 0;
		int cur = 0;
		int maxindex = 0;
		for (int i = 0; i < list.size(); i++)
		{
			maxindex = i;
			maxInfo = list.get(i);
			if (maxInfo.length() < 7 || !maxInfo.substring(4, 5).equals("."))
			{
				continue;
			}
			max = Integer.parseInt((String) list.get(i).substring(0, 7).replace(".", ""));
			for (int j = i + 1; j < list.size(); j++)
			{
				curInfo = list.get(j);
				if (curInfo.length() < 7 || !curInfo.substring(4, 5).equals("."))
				{
					continue;
				}
				cur = Integer.parseInt((String) list.get(j).substring(0, 7).replace(".", ""));
				if (max <= cur)
				{
					maxindex = j;
					max = cur;
					maxInfo = curInfo;
					continue;
				}
				//2011-03-24修改 开始时间相同时，考虑结束日期是否为 “今”，如是则排在有结束日期前面
				if (max == cur)
				{
					if (curInfo.length() < 10 || !curInfo.substring(10, 11).equals("今"))
					{
						continue;
					}
					maxindex = j;
					max = cur;
					maxInfo = curInfo;
					continue;
				}
			}
			sortList.add(maxInfo);
			if (maxindex != i)
			{
				list.set(maxindex, list.get(i));
			}
		}

		return sortList;
	}
	/**
	 *  取学历名称
	 * @param level
	 * @return
	 * @throws ServiceException
	 */
	private String getEducationName(String level) throws ServiceException
	{
		if (null != level)// 学历
		{
			String str = DicHelper.viewName(DicConstants.YHRS0039, level);
			return str == null ? "" : str;
		}
		return "";
	}
	/**
	 *  取学位名称
	 * @param degree
	 * @return
	 * @throws ServiceException
	 */
	private String getDegreeName(String degree) throws ServiceException
	{
		if (null != degree)// 学位
		{
			String str = DicHelper.viewName(DicConstants.YHRS0040, degree);
			return str == null ? "" : str;
		}
		return "";
	}
}
