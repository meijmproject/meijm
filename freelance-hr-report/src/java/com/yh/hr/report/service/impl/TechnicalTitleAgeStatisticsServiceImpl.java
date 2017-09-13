package com.yh.hr.report.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yh.hr.report.dto.TechnicalTitleAgeStatisticsDTO;
import com.yh.hr.report.service.TechnicalTitleAgeStatisticsService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.report.queryhelper.TechnicalTitleAgeStatisticsQueryHelper;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.platform.core.exception.ServiceException;


/**
 * 卫生技术人员依据职称等级汇总的年龄、性别架构表 - ServiceImpl
 * @author liul
 * @date 2017-3-7
 */
public class TechnicalTitleAgeStatisticsServiceImpl implements TechnicalTitleAgeStatisticsService
{

	/*
	 * (non-Javadoc)
	 * @see com.yh.hr.hspszhp.report.service.TechnicalTitleAgeStatisticsService#getPositionLevelCount(TableTagBean)
	 */
	public Map<String, List<TechnicalTitleAgeStatisticsDTO>> getPositionLevelCount(
			TableTagBean ttb) throws ServiceException {
		Map<String, List<TechnicalTitleAgeStatisticsDTO>> map = new HashMap<String,List<TechnicalTitleAgeStatisticsDTO>>();
		List<TechnicalTitleAgeStatisticsDTO> list = TechnicalTitleAgeStatisticsQueryHelper.getPositionLevel();
		List<TechnicalTitleAgeStatisticsDTO> positionLevelList = new ArrayList<TechnicalTitleAgeStatisticsDTO>();
		TechnicalTitleAgeStatisticsDTO zgDto = new TechnicalTitleAgeStatisticsDTO();
		TechnicalTitleAgeStatisticsDTO fgDto = new TechnicalTitleAgeStatisticsDTO();
		TechnicalTitleAgeStatisticsDTO zjDto = new TechnicalTitleAgeStatisticsDTO();
		TechnicalTitleAgeStatisticsDTO cj1Dto = new TechnicalTitleAgeStatisticsDTO();
		TechnicalTitleAgeStatisticsDTO cj2Dto = new TechnicalTitleAgeStatisticsDTO();
		TechnicalTitleAgeStatisticsDTO qtDto = new TechnicalTitleAgeStatisticsDTO();
		TechnicalTitleAgeStatisticsDTO cjDTO = new TechnicalTitleAgeStatisticsDTO();
		TechnicalTitleAgeStatisticsDTO totalDto = new TechnicalTitleAgeStatisticsDTO();
		if(CollectionUtils.isNotEmpty(list)){
			for(TechnicalTitleAgeStatisticsDTO dto:list){
				if(StringUtils.isNotEmpty(dto.getPositionLevelCode())&&dto.getPositionLevelCode().equals(DicConstants.YHRS0047_411)){
					//正高
					zgDto = TechnicalTitleAgeStatisticsQueryHelper.getPositionLevelCountByPositionLevel(dto,"");
					zgDto.setPositionLevelName("正高");
				}else if(StringUtils.isNotEmpty(dto.getPositionLevelCode())&&dto.getPositionLevelCode().equals(DicConstants.YHRS0047_412)){
					//副高
					fgDto = TechnicalTitleAgeStatisticsQueryHelper.getPositionLevelCountByPositionLevel(dto,"");
					fgDto.setPositionLevelName("副高");
				}else if(StringUtils.isNotEmpty(dto.getPositionLevelCode())&&dto.getPositionLevelCode().equals(DicConstants.YHRS0047_420)){
					//中级
					zjDto = TechnicalTitleAgeStatisticsQueryHelper.getPositionLevelCountByPositionLevel(dto,"");
					zjDto.setPositionLevelName("中级");
				}else if(StringUtils.isNotEmpty(dto.getPositionLevelCode())&&dto.getPositionLevelCode().equals(DicConstants.YHRS0047_434)){
					//初级1
					cj1Dto = TechnicalTitleAgeStatisticsQueryHelper.getPositionLevelCountByPositionLevel(dto,"");
				}else if(StringUtils.isNotEmpty(dto.getPositionLevelCode())&&dto.getPositionLevelCode().equals(DicConstants.YHRS0047_435)){
					//初级2
					cj2Dto = TechnicalTitleAgeStatisticsQueryHelper.getPositionLevelCountByPositionLevel(dto,"");
				}
			}
			//其他
			qtDto = TechnicalTitleAgeStatisticsQueryHelper.getPositionLevelCountByPositionLevel(qtDto,"true");
			qtDto.setPositionLevelName("其他");
			if(StringUtils.isNotEmpty(cj1Dto.getPositionLevelCode())||StringUtils.isNotEmpty(cj2Dto.getPositionLevelCode())){
				//用于计算小计 合计
				List<TechnicalTitleAgeStatisticsDTO> cjList = new ArrayList<TechnicalTitleAgeStatisticsDTO>();
				cjDTO.setPositionLevelName("初级");
				if(StringUtils.isNotEmpty(cj1Dto.getPositionLevelCode())){
					cjList.add(cj1Dto);
				}
				if(StringUtils.isNotEmpty(cj2Dto.getPositionLevelCode())){
					cjList.add(cj2Dto);
				}
				calculate(cjDTO,cjList);
			}
			if(StringUtils.isNotEmpty(cjDTO.getPositionLevelCode())||StringUtils.isNotEmpty(zgDto.getPositionLevelCode())||StringUtils.isNotEmpty(fgDto.getPositionLevelCode())||StringUtils.isNotEmpty(zjDto.getPositionLevelCode())||StringUtils.isNotEmpty(qtDto.getPositionLevelCode())){
				//用于计算小计 合计
				List<TechnicalTitleAgeStatisticsDTO> totalList = new ArrayList<TechnicalTitleAgeStatisticsDTO>();
				totalDto.setPositionLevelName("合计");
				if(StringUtils.isNotEmpty(cjDTO.getPositionLevelName())){
					totalList.add(cjDTO);
				}
				if(StringUtils.isNotEmpty(zgDto.getPositionLevelCode())){
					totalList.add(zgDto);
				}
				if(StringUtils.isNotEmpty(fgDto.getPositionLevelCode())){
					totalList.add(fgDto);
				}
				if(StringUtils.isNotEmpty(zjDto.getPositionLevelCode())){
					totalList.add(zjDto);
				}
				if(StringUtils.isNotEmpty(qtDto.getPositionLevelCode())){
					totalList.add(qtDto);
				}
				calculate(totalDto,totalList);
			}
		}
		positionLevelList.add(zgDto);
		positionLevelList.add(fgDto);
		positionLevelList.add(zjDto);
		positionLevelList.add(cjDTO);
		positionLevelList.add(qtDto);
		positionLevelList.add(totalDto);
		map.put("positionLevelList", positionLevelList);//统计数据
		return map;
	}
	/**
	 * 计算小计 合计
	 * @param dto
	 * @param list
	 * @return
	 */
private static TechnicalTitleAgeStatisticsDTO calculate(TechnicalTitleAgeStatisticsDTO dto,List<TechnicalTitleAgeStatisticsDTO> list){
	int i=0;
	int sum1=0;int sum2=0;int sum3=0;int sum4=0;int sum5=0;int sum6=0;int sum7=0;int sum8=0;int sum9=0;
	int sum10=0;int sum11=0;Double sum12=0D;Double sum13=0D;
	for(TechnicalTitleAgeStatisticsDTO tempDto:list){
		if(StringUtils.isNotEmpty(tempDto.getAvgAge())&&!tempDto.getAvgAge().equals("0")){
			i+=tempDto.getTotal();
			sum13+=Double.valueOf(tempDto.getAvgAge())*tempDto.getTotal();
		}
		sum1+=tempDto.getAge1();
		sum2+=tempDto.getAge2();
		sum3+=tempDto.getAge3();
		sum4+=tempDto.getAge4();
		sum5+=tempDto.getAge5();
		sum6+=tempDto.getAge6();
		sum7+=tempDto.getAge7();
		sum8+=tempDto.getAge8();
		sum9+=tempDto.getSex1();
		sum10+=tempDto.getSex2();
		sum11+=tempDto.getTotal();
		sum12+=tempDto.getBl();
		
	}
	dto.setAge1(sum1);
	dto.setAge2(sum2);
	dto.setAge3(sum3);
	dto.setAge4(sum4);
	dto.setAge5(sum5);
	dto.setAge6(sum6);
	dto.setAge7(sum7);
	dto.setAge8(sum8);
	dto.setSex1(sum9);
	dto.setSex2(sum10);
	dto.setTotal(sum11);
	dto.setBl(sum12);
	if(i==0){
		dto.setAvgAge("0");
	}else{
		BigDecimal b13 = new BigDecimal(sum13/i);  
		dto.setAvgAge(String.valueOf(b13.setScale(1,BigDecimal.ROUND_HALF_UP).doubleValue()));
	}
	return dto;
	
}

}
