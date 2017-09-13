package com.yh.hr.info.dataimport.person.service;

import java.util.List;

import com.yh.hr.component.im.dto.ColumnDefDTO;



public interface ExportPersonService {

	List<List<ColumnDefDTO>> listDicPerson() throws Exception;
   
}
