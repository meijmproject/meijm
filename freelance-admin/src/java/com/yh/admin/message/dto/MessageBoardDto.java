package com.yh.admin.message.dto;

import java.util.Date;

import com.yh.platform.core.util.DateUtil;

public class MessageBoardDto {

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
	}

	public String getExpDateStr()
	{
		return expDateStr;
	}

	public void setExpDateStr(String expDateStr)
	{
		this.expDateStr = expDateStr;
	}

	/**
	 * Returns the messageOid.
	 * 
	 * @return
	 */
	public Long getMessageOid()
	{
		return messageOid;
	}

	/**
	 * The messageOid to set.
	 * 
	 * @param messageOid
	 */
	public void setMessageOid(Long messageOid)
	{
		this.messageOid = messageOid;
	}

	/**
	 * Returns the publisher.
	 * 
	 * @return
	 */
	public String getPublisher()
	{
		return publisher;
	}

	/**
	 * The publisher to set.
	 * 
	 * @param publisher
	 */
	public void setPublisher(String publisher)
	{
		this.publisher = publisher;
	}

	/**
	 * Returns the reader.
	 * 
	 * @return
	 */
	public String getReader()
	{
		return reader;
	}

	/**
	 * The reader to set.
	 * 
	 * @param reader
	 */
	public void setReader(String reader)
	{
		this.reader = reader;
	}

	/**
	 * Returns the title.
	 * 
	 * @return
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 * The title to set.
	 * 
	 * @param title
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}

	/**
	 * Returns the content.
	 * 
	 * @return
	 */
	public String getContent()
	{
		return content;
	}

	/**
	 * The content to set.
	 * 
	 * @param content
	 */
	public void setContent(String content)
	{
		this.content = content;
	}

	/**
	 * Returns the effDate.
	 * 
	 * @return
	 */
	public Date getEffDate()
	{
		return effDate;
	}

	/**
	 * The effDate to set.
	 * 
	 * @param effDate
	 */
	public void setEffDate(Date effDate)
	{
		this.effDate = effDate;
		this.effDateStr = DateUtil.formatDate(effDate);
	}

	/**
	 * Returns the expDate.
	 * 
	 * @return
	 */
	public Date getExpDate()
	{
		return expDate;
		
	}

	/**
	 * The expDate to set.
	 * 
	 * @param expDate
	 */
	public void setExpDate(Date expDate)
	{
		this.expDate = expDate;
		this.expDateStr = DateUtil.formatDate(expDate);
	}

	/**
	 * Returns the createdDate.
	 * 
	 * @return
	 */
	public Date getCreatedDate()
	{
		return createdDate;
	}

	/**
	 * The createdDate to set.
	 * 
	 * @param createdDate
	 */
	public void setCreatedDate(Date createdDate)
	{
		this.createdDate = createdDate;
		this.createdDateStr = DateUtil.formatDate(createdDate);
	}

	/**
	 * Returns the createdBy.
	 * 
	 * @return
	 */
	public String getCreatedBy()
	{
		return createdBy;
	}

	/**
	 * The createdBy to set.
	 * 
	 * @param createdBy
	 */
	public void setCreatedBy(String createdBy)
	{
		this.createdBy = createdBy;
	}

	/**
	 * Returns the createdDateStr.
	 * 
	 * @return
	 */
	public String getCreatedDateStr()
	{
		return createdDateStr;
	}

	/**
	 * The createdDateStr to set.
	 * 
	 * @param createdDateStr
	 */
	public void setCreatedDateStr(String createdDateStr)
	{
		this.createdDateStr = createdDateStr;
	}

	/**
	 * Returns the systemId.
	 * 
	 * @return
	 */
	public String getSystemId()
	{
		return systemId;
	}

	/**
	 * The systemId to set.
	 * 
	 * @param systemId
	 */
	public void setSystemId(String systemId)
	{
		this.systemId = systemId;
	}
}

