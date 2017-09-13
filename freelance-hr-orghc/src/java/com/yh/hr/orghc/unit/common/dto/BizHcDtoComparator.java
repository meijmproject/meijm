package com.yh.hr.orghc.unit.common.dto;

import java.util.Comparator;

public class BizHcDtoComparator implements Comparator<BizUtHcInfoDTO> {

	public int compare(BizUtHcInfoDTO o1, BizUtHcInfoDTO o2) {
		int num = 0;
		int dto1Attr = Integer.parseInt(o1.getHcCode());
		int dto2Attr = Integer.parseInt(o2.getHcCode());
		int dto1Level = Integer.parseInt(o1.getBudgetFromCode());
		int dto2Level = Integer.parseInt(o2.getBudgetFromCode());
		if(dto1Attr > dto2Attr){
			num = 1;
		}else if(dto1Attr < dto2Attr){
			num = -1;
		}else{
			if(dto1Level > dto2Level){
				num = 1;
			}else{
				num = -1;
			}
		}
		return num;
	}
}