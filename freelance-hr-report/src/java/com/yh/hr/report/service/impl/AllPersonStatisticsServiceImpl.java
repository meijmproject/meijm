package com.yh.hr.report.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.report.dto.AllPersonStatisticsDTO;
import com.yh.hr.report.queryhelper.AllPersonStatisticsQueryHelper;
import com.yh.hr.report.service.AllPersonStatisticsService;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.platform.core.exception.ServiceException;


/**
 * 全院员工汇总统计 - ServiceImpl
 * @author liul
 * @date 2017-3-2
 */
public class AllPersonStatisticsServiceImpl implements AllPersonStatisticsService
{

	/*
	 * (non-Javadoc)
	 * @see com.yh.hr.hspszhp.report.service.AllPersonStatisticsService#getOrgUnitNameCount(TableTagBean)
	 */
	public Map<String, List<AllPersonStatisticsDTO>> getOrgUnitNameCount(
			TableTagBean ttb) throws ServiceException {
		Map<String, List<AllPersonStatisticsDTO>> map = new HashMap<String,List<AllPersonStatisticsDTO>>();
		List<AllPersonStatisticsDTO> list = AllPersonStatisticsQueryHelper.getOrg();
		List<AllPersonStatisticsDTO> nkbqList = new ArrayList<AllPersonStatisticsDTO>();
		List<AllPersonStatisticsDTO> wkbqList = new ArrayList<AllPersonStatisticsDTO>();
		List<AllPersonStatisticsDTO> mzksList = new ArrayList<AllPersonStatisticsDTO>();
		List<AllPersonStatisticsDTO> yjksList = new ArrayList<AllPersonStatisticsDTO>();
		List<AllPersonStatisticsDTO> xzhqList = new ArrayList<AllPersonStatisticsDTO>();
		List<AllPersonStatisticsDTO> qtList = new ArrayList<AllPersonStatisticsDTO>();
		if(CollectionUtils.isNotEmpty(list)){
			for(AllPersonStatisticsDTO dto:list){
				if(StringUtils.isNotEmpty(dto.getWardType())&&dto.getWardType().equals(DicConstants.YHRS0125_1)){
					//内科病区
					AllPersonStatisticsDTO nkbqDTO = new AllPersonStatisticsDTO();
					nkbqDTO=AllPersonStatisticsQueryHelper.getAllPersonCountByOrg(dto);
					nkbqList.add(nkbqDTO);
				}else if(StringUtils.isNotEmpty(dto.getWardType())&&dto.getWardType().equals(DicConstants.YHRS0125_2)){
					//外科病区
					AllPersonStatisticsDTO wkbqDTO = new AllPersonStatisticsDTO();
					wkbqDTO=AllPersonStatisticsQueryHelper.getAllPersonCountByOrg(dto);
					wkbqList.add(wkbqDTO);
				}else if(StringUtils.isNotEmpty(dto.getWardType())&&dto.getWardType().equals(DicConstants.YHRS0125_3)){
					//门诊科室
					AllPersonStatisticsDTO mzksDTO = new AllPersonStatisticsDTO();
					mzksDTO=AllPersonStatisticsQueryHelper.getAllPersonCountByOrg(dto);
					mzksList.add(mzksDTO);
				}else if(StringUtils.isNotEmpty(dto.getWardType())&&dto.getWardType().equals(DicConstants.YHRS0125_4)){
					//医技科室
					AllPersonStatisticsDTO yjksDTO = new AllPersonStatisticsDTO();
					yjksDTO=AllPersonStatisticsQueryHelper.getAllPersonCountByOrg(dto);
					yjksList.add(yjksDTO);
				}else if(StringUtils.isNotEmpty(dto.getWardType())&&dto.getWardType().equals("99")){
					//行政后勤
					AllPersonStatisticsDTO xzhqDTO = new AllPersonStatisticsDTO();
					xzhqDTO=AllPersonStatisticsQueryHelper.getAllPersonCountByOrg(dto);
					xzhqList.add(xzhqDTO);
				}/*else{
					AllPersonStatisticsDTO qtDTO = new AllPersonStatisticsDTO();
					qtDTO=AllPersonStatisticsQueryHelper.getAllPersonCountByOrg(dto);
					qtList.add(qtDTO);
				}*/
				
			}
			AllPersonStatisticsDTO nkbqxjDTO = new AllPersonStatisticsDTO();
			if(CollectionUtils.isNotEmpty(nkbqList)){
				//用于计算小计 合计
				List<AllPersonStatisticsDTO> hjList = new ArrayList<AllPersonStatisticsDTO>();
				nkbqxjDTO.setDeptOid(1L);
				nkbqxjDTO.setDeptName("内科病区小计");
				hjList = nkbqList;
				calculate(nkbqxjDTO,hjList);
				nkbqList.add(nkbqxjDTO);
			}
			AllPersonStatisticsDTO wkbqxjDTO = new AllPersonStatisticsDTO();
			if(CollectionUtils.isNotEmpty(wkbqList)){
				//用于计算小计 合计
				List<AllPersonStatisticsDTO> hjList = new ArrayList<AllPersonStatisticsDTO>();
				wkbqxjDTO.setDeptOid(1L);
				wkbqxjDTO.setDeptName("外科病区小计");
				hjList = wkbqList;
				calculate(wkbqxjDTO,hjList);
				wkbqList.add(wkbqxjDTO);
			}
			AllPersonStatisticsDTO wkbfhjDTO = new AllPersonStatisticsDTO();
			if(StringUtils.isNotEmpty(nkbqxjDTO.getDeptName())||StringUtils.isNotEmpty(wkbqxjDTO.getDeptName())){
				//用于计算小计 合计
				List<AllPersonStatisticsDTO> hjList = new ArrayList<AllPersonStatisticsDTO>();
				wkbfhjDTO.setDeptOid(2L);
				wkbfhjDTO.setDeptName("住院病房合计");
				if(StringUtils.isNotEmpty(nkbqxjDTO.getDeptName())){
					hjList.add(nkbqxjDTO);
				}
				if(StringUtils.isNotEmpty(wkbqxjDTO.getDeptName())){
					hjList.add(wkbqxjDTO);
				}
				calculate(wkbfhjDTO,hjList);
				wkbqList.add(wkbfhjDTO);
			}
			AllPersonStatisticsDTO mjzxjDTO = new AllPersonStatisticsDTO();
			if(CollectionUtils.isNotEmpty(mzksList)){
				//用于计算小计 合计
				List<AllPersonStatisticsDTO> hjList = null;
				mjzxjDTO.setDeptOid(1L);
				mjzxjDTO.setDeptName("门急诊小计");
				hjList = mzksList;
				calculate(mjzxjDTO,hjList);
				mzksList.add(mjzxjDTO);
			}
			AllPersonStatisticsDTO lckshjDTO = new AllPersonStatisticsDTO();
			if(StringUtils.isNotEmpty(wkbfhjDTO.getDeptName())||StringUtils.isNotEmpty(mjzxjDTO.getDeptName())){
				//用于计算小计 合计
				List<AllPersonStatisticsDTO> hjList = new ArrayList<AllPersonStatisticsDTO>();
				lckshjDTO.setDeptOid(2L);
				lckshjDTO.setDeptName("临床科室合计");
				if(StringUtils.isNotEmpty(wkbfhjDTO.getDeptName())){
					hjList.add(wkbfhjDTO);
				}
				if(StringUtils.isNotEmpty(mjzxjDTO.getDeptName())){
					hjList.add(mjzxjDTO);
				}
				calculate(lckshjDTO,hjList);
				mzksList.add(lckshjDTO);
			}
			AllPersonStatisticsDTO yjksxjDTO = new AllPersonStatisticsDTO();
			if(CollectionUtils.isNotEmpty(yjksList)){
				//用于计算小计 合计
				List<AllPersonStatisticsDTO> hjList = null;
				yjksxjDTO.setDeptOid(1L);
				yjksxjDTO.setDeptName("医技科室小计");
				hjList = yjksList;
				calculate(yjksxjDTO,hjList);
				yjksList.add(yjksxjDTO);
			}
			AllPersonStatisticsDTO wsjshjDTO = new AllPersonStatisticsDTO();
			if(StringUtils.isNotEmpty(lckshjDTO.getDeptName())||StringUtils.isNotEmpty(yjksxjDTO.getDeptName())){
				//用于计算小计 合计
				List<AllPersonStatisticsDTO> hjList = new ArrayList<AllPersonStatisticsDTO>();
				wsjshjDTO.setDeptOid(2L);
				wsjshjDTO.setDeptName("卫生技术人员合计");
				if(StringUtils.isNotEmpty(lckshjDTO.getDeptName())){
					hjList.add(lckshjDTO);
				}
				if(StringUtils.isNotEmpty(yjksxjDTO.getDeptName())){
					hjList.add(yjksxjDTO);
				}
				calculate(wsjshjDTO,hjList);
				yjksList.add(wsjshjDTO);
			}
			AllPersonStatisticsDTO xzryhjDTO = new AllPersonStatisticsDTO();
			if(CollectionUtils.isNotEmpty(xzhqList)){
				//用于计算小计 合计
				List<AllPersonStatisticsDTO> hjList = null;
				xzryhjDTO.setDeptOid(1L);
				xzryhjDTO.setDeptName("行政人员合计");
				hjList = xzhqList;
				calculate(xzryhjDTO,hjList);
				xzhqList.add(xzryhjDTO);
			}
			AllPersonStatisticsDTO hjDTO = new AllPersonStatisticsDTO();
			if(StringUtils.isNotEmpty(wsjshjDTO.getDeptName())||StringUtils.isNotEmpty(xzryhjDTO.getDeptName())){
				//用于计算小计 合计
				List<AllPersonStatisticsDTO> hjList = new ArrayList<AllPersonStatisticsDTO>();
				hjDTO.setDeptOid(2L);
				hjDTO.setDeptName("合计");
				if(StringUtils.isNotEmpty(wsjshjDTO.getDeptName())){
					hjList.add(wsjshjDTO);
				}
				if(StringUtils.isNotEmpty(xzryhjDTO.getDeptName())){
					hjList.add(xzryhjDTO);
				}
				calculate(hjDTO,hjList);
				xzhqList.add(hjDTO);
			}
		}
		map.put("nkbqList", nkbqList);//内科病区
		map.put("wkbqList", wkbqList);//外科病区
		map.put("mzksList", mzksList);//门诊科室
		map.put("yjksList", yjksList);//医技科室
		map.put("xzhqList", xzhqList);//行政后勤
		map.put("qtList", qtList);//其他
		return map;
	}
	/**
	 * 计算小计 合计
	 * @param dto
	 * @param list
	 * @return
	 */
private static AllPersonStatisticsDTO calculate(AllPersonStatisticsDTO dto,List<AllPersonStatisticsDTO> list){
	int i=0;
	int j=0;
	int sum1=0;int sum2=0;int sum3=0;int sum4=0;int sum5=0;int sum6=0;int sum7=0;int sum8=0;int sum9=0;
	int sum10=0;int sum11=0;int sum12=0;int sum13=0;int sum14=0;int sum15=0;int sum16=0;int sum17=0;int sum18=0;int sum19=0;
	int sum20=0;int sum21=0;int sum22=0;int sum23=0;int sum24=0;int sum25=0;int sum26=0;int sum27=0;int sum28=0;int sum29=0;
	int sum30=0;Double sum31=0D;int sum32=0;int sum33=0;int sum34=0;int sum35=0;int sum36=0;int sum37=0;int sum38=0;int sum39=0;
	int sum40=0;int sum41=0;int sum42=0;int sum43=0;int sum44=0;int sum45=0;Double sum46=0D;int sum47=0;int sum48=0;int sum49=0;
	for(AllPersonStatisticsDTO tempDto:list){
		if(StringUtils.isNotEmpty(tempDto.getFhnlCount())&&!tempDto.getFhnlCount().equals("0")){
			i+=tempDto.getFhxjCount();
			sum31+= Double.valueOf(tempDto.getFhnlCount())*tempDto.getFhxjCount();
		}
		if(StringUtils.isNotEmpty(tempDto.getHsnlCount())&&!tempDto.getHsnlCount().equals("0")){
			j+=tempDto.getHsxjCount();
			sum46+=Double.valueOf(tempDto.getHsnlCount())*tempDto.getHsxjCount();
		}
		sum1+=tempDto.getYiszgCount();
		sum2+=tempDto.getYisfgCount();
		sum3+=tempDto.getYiszjCount();
		sum4+=tempDto.getYiscjCount();
		sum5+=tempDto.getYishjCount();
		sum6+=tempDto.getYissxCount();
		sum7+=tempDto.getJiszgCount();
		sum8+=tempDto.getJisfgCount();
		sum9+=tempDto.getJiszjCount();
		sum10+=tempDto.getJiscjCount();
		sum11+=tempDto.getJishjCount();
		sum12+=tempDto.getJissxCount();
		sum13+=tempDto.getYaoszgCount();
		sum14+=tempDto.getYaosfgCount();
		sum15+=tempDto.getYaoszjCount();
		sum16+=tempDto.getYaoscjCount();
		sum17+=tempDto.getYaoshjCount();
		sum18+=tempDto.getYaossxCount();
		sum19+=tempDto.getYjyzgCount();
		sum20+=tempDto.getYjyfgCount();
		sum20+=tempDto.getYjyzjCount();
		sum22+=tempDto.getYjycjCount();
		sum23+=tempDto.getYjyhjCount();
		sum24+=tempDto.getQtCount();
		sum25+=tempDto.getFhxjCount();
		sum26+=tempDto.getFhbsxlCount();
		sum27+=tempDto.getFhssxlCount();
		sum28+=tempDto.getFhbkxlCount();
		sum29+=tempDto.getFhdzxlCount();
		sum30+=tempDto.getFhqtxlCount();
		
		sum32+=tempDto.getFhSex1Count();
		sum33+=tempDto.getFhSex2Count();
		sum34+=tempDto.getFhgpCount();
		sum35+=tempDto.getHszgCount();
		sum36+=tempDto.getHsfgCount();
		sum37+=tempDto.getHszjCount();
		sum38+=tempDto.getHscjCount();
		sum39+=tempDto.getHssxCount();
		sum40+=tempDto.getHsxjCount();
		sum41+=tempDto.getHsbsxlCount();
		sum42+=tempDto.getHsssxlCount();
		sum43+=tempDto.getHsbkxlCount();
		sum44+=tempDto.getHsdzxlCount();
		sum45+=tempDto.getHsqtxlCount();
		
		sum47+=tempDto.getHsSex1Count();
		sum48+=tempDto.getHsSex2Count();
		sum49+=tempDto.getTotalCount();
	}
	dto.setYiszgCount(sum1);
	dto.setYisfgCount(sum2);
	dto.setYiszjCount(sum3);
	dto.setYiscjCount(sum4);
	dto.setYishjCount(sum5);
	dto.setYissxCount(sum6);
	dto.setJiszgCount(sum7);
	dto.setJisfgCount(sum8);
	dto.setJiszjCount(sum9);
	dto.setJiscjCount(sum10);
	dto.setJishjCount(sum11);
	dto.setJissxCount(sum12);
	dto.setYaoszgCount(sum13);
	dto.setYaosfgCount(sum14);
	dto.setYaoszjCount(sum15);
	dto.setYaoscjCount(sum16);
	dto.setYaoshjCount(sum17);
	dto.setYaossxCount(sum18);
	dto.setYjyzgCount(sum19);
	dto.setYjyfgCount(sum20);
	dto.setYjyzjCount(sum21);
	dto.setYjycjCount(sum22);
	dto.setYjyhjCount(sum23);
	dto.setQtCount(sum24);
	dto.setFhxjCount(sum25);
	dto.setFhbsxlCount(sum26);
	dto.setFhssxlCount(sum27);
	dto.setFhbkxlCount(sum28);
	dto.setFhdzxlCount(sum29);
	dto.setFhqtxlCount(sum30);
	if(i==0){
		dto.setFhnlCount("0");
	}else{
		BigDecimal b31 = new BigDecimal(sum31/i);  
		dto.setFhnlCount(String.valueOf(b31.setScale(1,BigDecimal.ROUND_HALF_UP).doubleValue()));
	}
	dto.setFhSex1Count(sum32);
	dto.setFhSex2Count(sum33);
	dto.setFhgpCount(sum34);
	dto.setHszgCount(sum35);
	dto.setHsfgCount(sum36);
	dto.setHszjCount(sum37);
	dto.setHscjCount(sum38);
	dto.setHssxCount(sum39);
	dto.setHsxjCount(sum40);
	dto.setHsbsxlCount(sum41);
	dto.setHsssxlCount(sum42);
	dto.setHsbkxlCount(sum43);
	dto.setHsdzxlCount(sum44);
	dto.setHsqtxlCount(sum45);
	if(j==0){
		dto.setHsnlCount("0");
	}else{
		BigDecimal b46 = new BigDecimal(sum46/j);
		dto.setHsnlCount(String.valueOf(b46.setScale(1,BigDecimal.ROUND_HALF_UP).doubleValue()));
	}
	dto.setHsSex1Count(sum47);
	dto.setHsSex2Count(sum48);
	dto.setTotalCount(sum49);
	return dto;
	
}

}
