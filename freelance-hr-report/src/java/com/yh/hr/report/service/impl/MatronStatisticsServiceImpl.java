package com.yh.hr.report.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yh.hr.report.dto.MatronStatisticsDTO;
import com.yh.hr.report.queryhelper.MatronStatisticsQueryHelper;
import com.yh.hr.report.service.MatronStatisticsService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.platform.core.exception.ServiceException;


/**
 * 科主任及护士长系列人员汇总 - ServiceImpl
 * @author liul
 * @date 2017-3-7
 */
public class MatronStatisticsServiceImpl implements MatronStatisticsService
{

	/*
	 * (non-Javadoc)
	 * @see com.yh.hr.hspszhp.report.service.MatronStatisticsService#getOrgUnitNameCount(TableTagBean)
	 */
	public Map<String, List<MatronStatisticsDTO>> getOrgUnitNameCount(
			TableTagBean ttb) throws ServiceException {
		Map<String, List<MatronStatisticsDTO>> map = new HashMap<String,List<MatronStatisticsDTO>>();
		List<MatronStatisticsDTO> list = MatronStatisticsQueryHelper.getOrg();
		List<MatronStatisticsDTO> nkbqList = new ArrayList<MatronStatisticsDTO>();
		List<MatronStatisticsDTO> wkbqList = new ArrayList<MatronStatisticsDTO>();
		List<MatronStatisticsDTO> mzksList = new ArrayList<MatronStatisticsDTO>();
		List<MatronStatisticsDTO> yjksList = new ArrayList<MatronStatisticsDTO>();
		List<MatronStatisticsDTO> hjList = new ArrayList<MatronStatisticsDTO>();
		if(CollectionUtils.isNotEmpty(list)){
			for(MatronStatisticsDTO dto:list){
				if(StringUtils.isNotEmpty(dto.getWardType())&&dto.getWardType().equals(DicConstants.YHRS0125_1)){
					//内科病区
					MatronStatisticsDTO nkbqDTO = new MatronStatisticsDTO();
					nkbqDTO=MatronStatisticsQueryHelper.getMatronCountByOrg(dto);
					nkbqList.add(nkbqDTO);
					hjList.add(nkbqDTO);
				}else if(StringUtils.isNotEmpty(dto.getWardType())&&dto.getWardType().equals(DicConstants.YHRS0125_2)){
					//外科病区
					MatronStatisticsDTO wkbqDTO = new MatronStatisticsDTO();
					wkbqDTO=MatronStatisticsQueryHelper.getMatronCountByOrg(dto);
					wkbqList.add(wkbqDTO);
					hjList.add(wkbqDTO);
				}else if(StringUtils.isNotEmpty(dto.getWardType())&&dto.getWardType().equals(DicConstants.YHRS0125_3)){
					//门诊科室
					MatronStatisticsDTO mzksDTO = new MatronStatisticsDTO();
					mzksDTO=MatronStatisticsQueryHelper.getMatronCountByOrg(dto);
					mzksList.add(mzksDTO);
					hjList.add(mzksDTO);
				}else if(StringUtils.isNotEmpty(dto.getWardType())&&dto.getWardType().equals(DicConstants.YHRS0125_4)){
					//医技科室
					MatronStatisticsDTO yjksDTO = new MatronStatisticsDTO();
					yjksDTO=MatronStatisticsQueryHelper.getMatronCountByOrg(dto);
					yjksList.add(yjksDTO);
					hjList.add(yjksDTO);
				}
				
			}
			MatronStatisticsDTO hjDTO = new MatronStatisticsDTO();
			if(CollectionUtils.isNotEmpty(hjList)){
				//用于计算小计 合计
				hjDTO.setDeptOid(1L);
				hjDTO.setDeptName("合计");
				calculate(hjDTO,hjList);
				yjksList.add(hjDTO);
			}
		}
		map.put("nkbqList", nkbqList);//内科病区
		map.put("wkbqList", wkbqList);//外科病区
		map.put("mzksList", mzksList);//门诊科室
		map.put("yjksList", yjksList);//医技科室
		return map;
	}
	/**
	 * 计算小计 合计
	 * @param dto
	 * @param list
	 * @return
	 */
private static MatronStatisticsDTO calculate(MatronStatisticsDTO dto,List<MatronStatisticsDTO> list){
	int i=0;
	@SuppressWarnings("unused")
	int j=0;
	int sum1=0;String sum2="";int sum3=0;String sum4="";int sum5=0;int sum6=0;int sum7=0;int sum8=0;int sum9=0;
	int sum10=0;int sum11=0;int sum12=0;int sum13=0;int sum14=0;int sum15=0;Double sum16=0D;int sum17=0;String sum18="";int sum19=0;
	String sum20="";int sum21=0;int sum22=0;int sum23=0;int sum24=0;int sum25=0;int sum26=0;int sum27=0;int sum28=0;int sum29=0;
	int sum30=0;int sum31=0;Double sum32=0D;
	for(MatronStatisticsDTO tempDto:list){
		if(StringUtils.isNotEmpty(tempDto.getKzrnlCount())&&!tempDto.getKzrnlCount().equals("0")){
			i+=tempDto.getKzrhjCount();
			sum16+=Double.valueOf(tempDto.getKzrnlCount())*tempDto.getKzrhjCount();
		}
		if(StringUtils.isNotEmpty(tempDto.getHsznlCount())&&!tempDto.getHsznlCount().equals("0")){
			j+=tempDto.getHszhjCount();
			sum32+= Double.valueOf(tempDto.getHsznlCount())*tempDto.getHszhjCount();
		}
		sum1+=tempDto.getKzrzwzCount();
		if(StringUtils.isNotEmpty(tempDto.getKzrzwzxmCount())){
			sum2+=tempDto.getKzrzwzxmCount()+",";
		}
		sum3+=tempDto.getKzrzwfCount();
		if(StringUtils.isNotEmpty(tempDto.getKzrzwfxmCount())){
			sum4+=tempDto.getKzrzwfxmCount()+",";
		}
		sum5+=tempDto.getKzrzczgCount();
		sum6+=tempDto.getKzrzcfgCount();
		sum7+=tempDto.getKzrzczjCount();
		sum8+=tempDto.getKzrzccjCount();
		sum9+=tempDto.getKzrxlbsCount();
		sum10+=tempDto.getKzrxlssCount();
		sum11+=tempDto.getKzrxlbkCount();
		sum12+=tempDto.getKzrxldzCount();
		sum13+=tempDto.getKzrhjCount();
		sum14+=tempDto.getKzrSex1Count();
		sum15+=tempDto.getKzrSex2Count();
		
		sum17+=tempDto.getHszzwzCount();
		if(StringUtils.isNotEmpty(tempDto.getHszzwzxmCount())){
			sum18+=tempDto.getHszzwzxmCount()+",";
		}
		sum19+=tempDto.getHszzwfCount();
		if(StringUtils.isNotEmpty(tempDto.getHszzwfxmCount())){
			sum20+=tempDto.getHszzwfxmCount()+",";
		}
		sum21+=tempDto.getHszzczgCount();
		sum22+=tempDto.getHszzcfgCount();
		sum23+=tempDto.getHszzczjCount();
		sum24+=tempDto.getHszzccjCount();
		sum25+=tempDto.getHszxlbsCount();
		sum26+=tempDto.getHszxlssCount();
		sum27+=tempDto.getHszxlbkCount();
		sum28+=tempDto.getHszxldzCount();
		sum29+=tempDto.getHszhjCount();
		sum30+=tempDto.getHszSex1Count();
		sum31+=tempDto.getHszSex2Count();
		
	}
	dto.setKzrzwzCount(sum1);
	if(StringUtils.isNotEmpty(sum2)){
		dto.setKzrzwzxmCount(sum2.substring(0, sum2.length()-1));
	}else{
		dto.setKzrzwzxmCount(sum2);
	}
	
	dto.setKzrzwfCount(sum3);
	if(StringUtils.isNotEmpty(sum4)){
		dto.setKzrzwfxmCount(sum4.substring(0, sum4.length()-1));
	}else{
		dto.setKzrzwfxmCount(sum4);
	}
	dto.setKzrzczgCount(sum5);
	dto.setKzrzcfgCount(sum6);
	dto.setKzrzczjCount(sum7);
	dto.setKzrzccjCount(sum8);
	dto.setKzrxlbsCount(sum9);
	dto.setKzrxlssCount(sum10);
	dto.setKzrxlbkCount(sum11);
	dto.setKzrxldzCount(sum12);
	dto.setKzrhjCount(sum13);
	dto.setKzrSex1Count(sum14);
	dto.setKzrSex2Count(sum15);
	if(i==0){
		dto.setKzrnlCount("0");
	}else{
		BigDecimal b16 = new BigDecimal(sum16/i);  
		dto.setKzrnlCount(String.valueOf(b16.setScale(1,BigDecimal.ROUND_HALF_UP).doubleValue()));
	}
	dto.setHszzwzCount(sum17);
	if(StringUtils.isNotEmpty(sum18)){
		dto.setHszzwzxmCount(sum18.substring(0, sum18.length()-1));
	}else{
		dto.setHszzwzxmCount(sum18);
	}
	dto.setHszzwfCount(sum19);
	if(StringUtils.isNotEmpty(sum20)){
		dto.setHszzwfxmCount(sum20.substring(0, sum20.length()-1));
	}else{
		dto.setHszzwfxmCount(sum20);
	}
	dto.setHszzczgCount(sum21);
	dto.setHszzcfgCount(sum22);
	dto.setHszzczjCount(sum23);
	dto.setHszzccjCount(sum24);
	dto.setHszxlbsCount(sum25);
	dto.setHszxlssCount(sum26);
	dto.setHszxlbkCount(sum27);
	dto.setHszxldzCount(sum28);
	dto.setHszhjCount(sum29);
	dto.setHszSex1Count(sum30);
	dto.setHszSex2Count(sum31);
	if(i==0){
		dto.setHsznlCount("0");
	}else{
		BigDecimal b32 = new BigDecimal(sum32/i);  
		dto.setHsznlCount(String.valueOf(b32.setScale(1,BigDecimal.ROUND_HALF_UP).doubleValue()));
	}
	return dto;
	
}

}
