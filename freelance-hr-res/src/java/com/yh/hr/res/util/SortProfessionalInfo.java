package com.yh.hr.res.util;

import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.yh.hr.res.dictionary.DicConstants;
import com.yh.hr.res.pt.dto.PtProfessionalInfoDTO;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

@SuppressWarnings("rawtypes")
public class SortProfessionalInfo implements Comparator {
	/**
	 * ְҵ�����ʸ�����ְҵ�����ʸ�ȼ��ߵ������棬�����ȣ��ж�ȡ�����ڣ����ڿ���������
	 * @param o1
	 * @param o2
	 * @return
	 */
	public int compare(Object o1, Object o2) {
		PtProfessionalInfoDTO e1 = null;
		PtProfessionalInfoDTO e2 = null;
		try {
			e1 = BeanHelper.copyProperties(o1, PtProfessionalInfoDTO.class);
			e2 = BeanHelper.copyProperties(o2, PtProfessionalInfoDTO.class);
		} catch(ServiceException se) {
			se.printStackTrace();
		}
		Integer qualificationsLevelCode1 = getLevelMap().get(e1.getQualificationsLevelCode());
		Integer qualificationsLevelCode2 = getLevelMap().get(e2.getQualificationsLevelCode());
		Date procureDate1 = e1.getProcureDate();
		Date procureDate2=e2.getProcureDate();
		//����Ϊ���ж�ȡ���ʸ����ڣ����ȡ��������ǰ
		if(qualificationsLevelCode1==null && qualificationsLevelCode2==null)
		{
			//�������ȫ��Ϊ��
			if(null==procureDate1 && null==procureDate2)
				return 0;
			else if(null==procureDate2)
				return -1;
			else if(null==procureDate1)
				return 1;
			else
				return procureDate2.compareTo(procureDate1);
		}
		else if(qualificationsLevelCode1==null && qualificationsLevelCode2!=null)
		{
			return 1;
		}
		else if(qualificationsLevelCode1!=null && qualificationsLevelCode2==null)
		{
			return -1;
		}
		else if(qualificationsLevelCode1.equals(qualificationsLevelCode2))
		{
			//�������ȫ��Ϊ��
			if(null==procureDate1 && null==procureDate2)
				return 0;
			else if(null==procureDate2)
				return -1;
			else if(null==procureDate1)
				return 1;
			else
				return procureDate2.compareTo(procureDate1);
		}
		else
		{
			return qualificationsLevelCode1.compareTo(qualificationsLevelCode2);
		}
	}
	@SuppressWarnings("serial")
	private Map<String,Integer> getLevelMap()
	{
		return new HashMap<String,Integer>(){
			{
				put(DicConstants.YHRS0049_1, 1);
				put(DicConstants.YHRS0049_2, 2);
				put(DicConstants.YHRS0049_3, 3);
				put(DicConstants.YHRS0049_4, 4);
				put(DicConstants.YHRS0049_5, 5);
			}
		};
	}
}
