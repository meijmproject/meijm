package com.yh.hr.orghc.unit.common.dto;

import java.util.Comparator;

public class BizLeaderDtoComparator implements Comparator<BizUtLeaderInfoDTO> {

	public int compare(BizUtLeaderInfoDTO dto1, BizUtLeaderInfoDTO dto2) {
		int num = 0;
		int dto1Attr = Integer.parseInt(dto1.getDutyAttribute());
		int dto2Attr = Integer.parseInt(dto2.getDutyAttribute());
		int dto1Level = Integer.parseInt(dto1.getDutyLevel());
		int dto2Level = Integer.parseInt(dto2.getDutyLevel());
		if(dto1Attr == 9){
			dto1Attr = 99;
		}
		if(dto2Attr == 9){
			dto2Attr = 99;
		}
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
