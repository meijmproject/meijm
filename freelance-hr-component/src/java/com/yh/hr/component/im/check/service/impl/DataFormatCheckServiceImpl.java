package com.yh.hr.component.im.check.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.yh.hr.component.im.check.service.CollItemCheckService;
import com.yh.hr.component.im.dto.CheckColumnDTO;
import com.yh.hr.component.im.dto.CheckResultDTO;
import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.res.dictionary.DicConstants;
import com.yh.hr.res.im.dto.ImCollectTemplateDTO;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.DateUtil;

/**
 * 数据格式检查实现类
 * @author wangx
 * @date 2017-07-11
 * @version 1.0
 */
public class DataFormatCheckServiceImpl implements CollItemCheckService {

	/**
	 * 数据格式检查
	 * @param column
	 * @param collList 已映射的采集项模板
	 * @throws ServiceException
	 */
	public CheckResultDTO check(CheckColumnDTO column, List<ImCollectTemplateDTO> collList) throws ServiceException {
		String importCollName = column.getImportCollName();
		if(importCollName==null) {
			throw new ServiceException(null, "导入采集项名称不能为空！");
		}
		ImCollectTemplateDTO imCollectTemplateDTO=null;
		if(CollectionUtils.isNotEmpty(collList)) {
			for(ImCollectTemplateDTO collDTO:collList) {
				if(DicConstants.YHRS0003_1.equals(collDTO.getEffectiveFlag())&&importCollName.equals(collDTO.getImportCollName())) {
					imCollectTemplateDTO = collDTO;
				}
			}
		}
		String importCollValue = column.getImportCollValue();
		if(importCollValue!=null&&!"".equals(importCollValue)) {
			if(imCollectTemplateDTO!=null) {
				String databaseColumnType = imCollectTemplateDTO.getDatabaseColumnType();
				if(DicConstants.YHRS0139_2.equals(databaseColumnType)) {
					if(!DateUtil.checkDateFormat(importCollValue)) {
						CheckResultDTO result = new CheckResultDTO();
						result.setDatabaseColumnCode(imCollectTemplateDTO.getDatabaseColumnCode());
						result.setImportCollValue(importCollValue);
						result.setCheckType(column.getCheckType());
						result.setCheckStatus(DicConstants.YHRS0003_0);
						result.setCheckMessage("“"+imCollectTemplateDTO.getTemplateCollName()+"”日期格式错误");
						return result;
					}
				}else {
					if("ID_NO".equals(imCollectTemplateDTO.getDatabaseColumnCode())) {
						if(!checkIdNo(importCollValue)&&!is18ByteIdCardComplex(importCollValue)) {
							CheckResultDTO result = new CheckResultDTO();
							result.setDatabaseColumnCode(imCollectTemplateDTO.getDatabaseColumnCode());
							result.setImportCollValue(importCollValue);
							result.setCheckType(column.getCheckType());
							result.setCheckStatus(DicConstants.YHRS0003_0);
							result.setCheckMessage("“"+imCollectTemplateDTO.getTemplateCollName()+"”不合法");
							return result;
						}
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * 验证身份证号是否合法（包含15位和18位）
	 * @param idNo
	 * @return
	 * @throws ServiceException
	 */
	private Boolean checkIdNo(String idNo) throws ServiceException {
		String regex15 = "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$";
//		String regex18 = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X|x)$";
		Pattern pat15 = Pattern.compile(regex15);   
//		Pattern pat18 = Pattern.compile(regex18); 
        Matcher mat15 = pat15.matcher(idNo); 
//        Matcher mat18 = pat18.matcher(idNo); 
		if(mat15.matches()) {
			return true;
		}   
		return false;
	}
	
	/** 
     * 18位身份证校验,比较严格校验 
     * @author lyl 
     * @param idCard 
     * @return 
     */  
    public boolean is18ByteIdCardComplex(String idCard) throws ServiceException { 
        Pattern pattern1 = Pattern.compile("^(\\d{6})(19|20)(\\d{2})(1[0-2]|0[1-9])(0[1-9]|[1-2][0-9]|3[0-1])(\\d{3})(\\d|X|x)?$");   
        Matcher matcher = pattern1.matcher(idCard);  
        int[] prefix = new int[]{7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2};  
        int[] suffix = new int[]{ 1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2 };  
        if(matcher.matches()){  
            Map<String, String> cityMap = initCityMap();  
            if(cityMap.get(idCard.substring(0,2)) == null ){  
                return false;  
            }  
            int idCardWiSum=0; //用来保存前17位各自乖以加权因子后的总和  
            for(int i=0;i<17;i++){  
                idCardWiSum+=Integer.valueOf(idCard.substring(i,i+1))*prefix[i];  
            }  
              
            int idCardMod=idCardWiSum%11;//计算出校验码所在数组的位置  
            String idCardLast=idCard.substring(17);//得到最后一位身份证号码  
              
            //如果等于2，则说明校验码是10，身份证号码最后一位应该是X  
            if(idCardMod==2){  
                if(idCardLast.equalsIgnoreCase("x")){  
                    return true;  
                }else{  
                    return false;  
                }  
            }else{  
                //用计算出的验证码与最后一位身份证号码匹配，如果一致，说明通过，否则是无效的身份证号码  
                if(idCardLast.equals(suffix[idCardMod]+"")){  
                    return true;  
                }else{  
                    return false;  
                }  
           }  
        }  
        return false;  
    }  
      
    private static Map<String, String> initCityMap(){  
        Map<String, String> cityMap = new HashMap<String, String>();  
        cityMap.put("11", "北京");  
        cityMap.put("12", "天津");  
        cityMap.put("13", "河北");  
        cityMap.put("14", "山西");  
        cityMap.put("15", "内蒙古");  
          
        cityMap.put("21", "辽宁");  
        cityMap.put("22", "吉林");  
        cityMap.put("23", "黑龙江");  
          
        cityMap.put("31", "上海");  
        cityMap.put("32", "江苏");  
        cityMap.put("33", "浙江");  
        cityMap.put("34", "安徽");  
        cityMap.put("35", "福建");  
        cityMap.put("36", "江西");  
        cityMap.put("37", "山东");  
          
        cityMap.put("41", "河南");  
        cityMap.put("42", "湖北");  
        cityMap.put("43", "湖南");  
        cityMap.put("44", "广东");  
        cityMap.put("45", "广西");  
        cityMap.put("46", "海南");  
          
        cityMap.put("50", "重庆");  
        cityMap.put("51", "四川");  
        cityMap.put("52", "贵州");  
        cityMap.put("53", "云南");  
        cityMap.put("54", "西藏");  
          
        cityMap.put("61", "陕西");  
        cityMap.put("62", "甘肃");  
        cityMap.put("63", "青海");  
        cityMap.put("64", "宁夏");  
        cityMap.put("65", "新疆");  
          
        return cityMap;  
    }
}
