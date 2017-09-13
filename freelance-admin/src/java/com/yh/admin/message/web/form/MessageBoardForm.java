package com.yh.admin.message.web.form;

import java.util.Date;

import org.apache.struts.validator.ValidatorForm;

import com.yh.platform.core.util.DateUtil;

public class MessageBoardForm extends ValidatorForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3058337676050877005L;
	private Long	messageOid;
	/**
	 * 发布者
	 */
	private String	publisher;
	/**
	 * 接收者
	 */
	private String	reader;
	/**
	 * 标题
	 */
	private String	title;
	/**
	 * 内容
	 */
	private String	content;
	/**
	 * 生效日期
	 */
	private Date	effDate;
	private String	effDateStr;
	/**
	 * 失效日期
	 */
	private Date	expDate;
	private String	expDateStr;
	/**
	 * 创建日期
	 */
	private Date	createdDate;
	private String	createdDateStr;
	/**
	 * 创建人
	 */
	private String	createdBy;
	/**
	 * 系统ID
	 */
	private String	systemId;

	public String getEffDateStr()
	{
		return effDateStr;
	}

	public void setEffDateStr(String effDateStr)
	{
		this.effDateStr = effDateStr;
		this.effDate = DateUtil.parseDate(effDateStr);
	}

	public String getExpDateStr()
	{
		return expDateStr;
	}

	public void setExpDateStr(String expDateStr)
	{
		this.expDateStr = expDateStr;
		this.expDate = DateUtil.parseDate(expDateStr);
	}

	public Long getMessageOid()
	{
		return messageOid;
	}

	public void setMessageOid(Long messageOid)
	{
		this.messageOid = messageOid;
	}

	public String getPublisher()
	{
		return publisher;
	}

	public void setPublisher(String publisher)
	{
		this.publisher = publisher;
	}

	public String getReader()
	{
		return reader;
	}

	public void setReader(String reader)
	{
		this.reader = reader;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public Date getEffDate()
	{
		return effDate;
	}

	public void setEffDate(Date effDate)
	{
		this.effDate = effDate;
		this.effDateStr = DateUtil.formatDate(effDate);
	}

	public Date getExpDate()
	{
		return expDate;
		
	}

	public void setExpDate(Date expDate)
	{
		this.expDate = expDate;
		this.expDateStr = DateUtil.formatDate(expDate);
	}

	public Date getCreatedDate()
	{
		return createdDate;
	}

	public void setCreatedDate(Date createdDate)
	{
		this.createdDate = createdDate;
		this.createdDateStr = DateUtil.formatDate(createdDate);
	}

	public String getCreatedBy()
	{
		return createdBy;
	}

	public void setCreatedBy(String createdBy)
	{
		this.createdBy = createdBy;
	}

	public String getCreatedDateStr()
	{
		return createdDateStr;
	}

	public void setCreatedDateStr(String createdDateStr)
	{
		this.createdDateStr = createdDateStr;
		this.createdDate = DateUtil.parseDate(createdDateStr);
	}

	public String getSystemId()
	{
		return systemId;
	}

	public void setSystemId(String systemId)
	{
		this.systemId = systemId;
	}
}
