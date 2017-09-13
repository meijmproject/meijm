package com.yh.hr.info.warning.dto;

import java.util.Date;
/**
 * Ԥ������Ϣ��ʾ-DTO
 * @author	Xing.Liu
 * @created 2011-03-08
 */
public class BizWarningResultDTO
{
	// fields
	protected Long			personOid;			// ��ԱOID
	protected String 		name; 				// ����
	protected String 		sexCode; 			// �Ա�
	protected String 		personType; 		// ��ݣ���Ա���
	protected String 		unitName;  			// ������λ
	protected Date 			birthday; 			// ��������
	protected Date probationBegin;				//���ÿ�ʼʱ��
	protected Date startWorkDate;				//��ʼ�μӹ���ʱ��
	
	// constructor
	public BizWarningResultDTO(){}
	

	public BizWarningResultDTO(Long personOid, String name, String sexCode,
			String personType, String unitName, Date birthday,
			Date probationBegin, Date startWorkDate) {
		super();
		this.personOid = personOid;
		this.name = name;
		this.sexCode = sexCode;
		this.personType = personType;
		this.unitName = unitName;
		this.birthday = birthday;
		this.probationBegin = probationBegin;
		this.startWorkDate = startWorkDate;
	}


	// get set
	public Long getPersonOid() 
	{
		return personOid;
	}
	public void setPersonOid(Long personOid) 
	{
		this.personOid = personOid;
	}
	
	public String getName() 
	{
		return name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}

	public String getSexCode() 
	{
		return sexCode;
	}
	public void setSexCode(String sexCode) 
	{
		this.sexCode = sexCode;
	}

	public String getPersonType() 
	{
		return personType;
	}
	public void setPersonType(String personType) 
	{
		this.personType = personType;
	}

	public String getUnitName() 
	{
		return unitName;
	}
	public void setUnitName(String unitName) 
	{
		this.unitName = unitName;
	}

	public Date getBirthday() 
	{
		return birthday;
	}
	public void setBirthday(Date birthday) 
	{
		this.birthday = birthday;
	}	
}
