package com.yh.hr.res.util;

import com.yh.hr.res.dictionary.DicConstants;
import com.yh.hr.res.pt.dto.PtProfTechInfoDTO;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("rawtypes")
public class SortProfTechInfo implements Comparator {
	/**
	 * רҵ�����ʸ�����רҵ�����ʸ�ȼ��ߵ������棬�����ȣ��ж�ȡ�����ڣ����ڿ���������
	 * @param o1
	 * @param o2
	 * @return
	 */
	public int compare(Object o1, Object o2) {
		PtProfTechInfoDTO e1 = null;
		PtProfTechInfoDTO e2 = null;
		try {
			e1 = BeanHelper.copyProperties(o1, PtProfTechInfoDTO.class);
			e2 = BeanHelper.copyProperties(o2, PtProfTechInfoDTO.class);
		} catch(ServiceException se) {
			se.printStackTrace();
		}
		Integer profTechLevel1 = getLevelMap().get(e1.getProfTechLevel());
		Integer profTechLevel2 = getLevelMap().get(e2.getProfTechLevel());
		Date procureDate1 = e1.getProcureDate();
		Date procureDate2=e2.getProcureDate();
		//����Ϊ���ж�ȡ���ʸ����ڣ����ȡ��������ǰ
		if(profTechLevel1==null && profTechLevel2==null)
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
		else if(profTechLevel1==null && profTechLevel2!=null)
		{
			return 1;
		}
		else if(profTechLevel1!=null && profTechLevel2==null)
		{
			return -1;
		}
		else if(profTechLevel1.equals(profTechLevel2))
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
			return profTechLevel1.compareTo(profTechLevel2);
		}
	}
	@SuppressWarnings("serial")
	private Map<String,Integer> getLevelMap()
	{
		return new HashMap<String,Integer>(){
			{
				put(DicConstants.YHRS0047_411, 1);
				put(DicConstants.YHRS0047_412, 2);
				put(DicConstants.YHRS0047_420, 3);
				put(DicConstants.YHRS0047_434, 4);
				put(DicConstants.YHRS0047_435, 5);
			}
		};
	}
}
