/*
 * @(#) PagedListInfo.java             
 * 
 * Copyright (c) 2007 IBM Corporation. All Rights Reserved.
 */
package com.yh.platform.core.util;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

public class PageListInfo implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4510101528576922920L;

	private final int PAGE_LIMIT = Integer.parseInt(ConfigUtil.getProperty("page.upper.limit"));

	// current page number
	private int pageNum = 1;

	// page size
	private int pageSize = Integer.parseInt(ConfigUtil.getProperty("page.max.size"));

	// list of value on the page
	@SuppressWarnings("rawtypes")
	private List pageList;

	// total rows
	private int totalRows = 0;

	// query name
	private String query;
	
	private String countSql;
	
	private boolean nameQuery = false;

	// query criteria
	private String criteria;

	// query order by
	private String orderby;
		
	// build html script for paged info
	@SuppressWarnings("unused")
	private String recordInfo;

	@SuppressWarnings("unused")
	private String pageInfo;

	private Locale locale;
	
	private boolean sql = false;

	public boolean isSql()
	{
		return sql;
	}

	public void setSql(boolean sql)
	{
		this.sql = sql;
	}

	/**
	 * @return Returns the pageList.
	 */
	@SuppressWarnings("rawtypes")
	public List getPageList()
	{
		return pageList;
	}

	@SuppressWarnings("rawtypes")
	public List getValueList()
	{
		return pageList;
	}

	/**
	 * @param pageList
	 *            The pageList to set.
	 */
	public void setPageList(@SuppressWarnings("rawtypes") List pageList)
	{
		this.pageList = pageList;
	}

	/**
	 * @return Returns the pageNum.
	 */
	public int getPageNum()
	{
		return pageNum;
	}

	/**
	 * @param pageNum
	 *            The pageNum to set.
	 */
	public void setPageNum(int pageNum)
	{
		this.pageNum = pageNum;
		if(this.pageNum<1)
			this.pageNum = 1;
	}

	/**
	 * @return Returns the pageSize.
	 */
	public int getPageSize()
	{
		return pageSize;
	}

	/**
	 * @param pageSize
	 *            The pageSize to set.
	 */
	public void setPageSize(int pageSize)
	{
		this.pageSize = pageSize;
	}

	/**
	 * @return Returns the criteria.
	 */
	public String getCriteria()
	{				
		return criteria;
	}

	/**
	 * @param criteria
	 *            The criteria to set.
	 */
	public void setCriteria(String criteria)
	{
		this.criteria = criteria;
	}

	/**
	 * @return Returns the query.
	 */
	public String getQuery()
	{
		return query;
	}

	/**
	 * @param query
	 *            The query to set.
	 */
	public void setQuery(String query)
	{
		this.query = query;
	}

	/**
	 * @return Returns the totalRows.
	 */
	public int getTotalRows()
	{
		return totalRows;
	}

	/**
	 * @param totalRows
	 *            The totalRows to set.
	 */
	public void setTotalRows(int totalRows)
	{
		this.totalRows = totalRows;
	}

	public boolean isFirst()
	{
		return this.getQuery() == null;
	}

	/**
	 * 
	 * @return
	 */
	public String getRecordInfo()
	{
		if (locale != null
				&& Locale.ENGLISH.getLanguage().equals(locale.getLanguage()))
			return getRecordInfo4English();
		return getRecordInfo4Chinese();
	}

	private String getRecordInfo4English()
	{
		StringBuffer strResult = new StringBuffer();

		int nCurpage = this.pageNum;
		strResult.append("Records ");
		int start = 0, end = 0;
		if (totalRows > 0)
		{
			start = (pageNum - 1) * pageSize + 1;
			if (pageList.size() < pageSize)
			{
				end = start + pageList.size() - 1;
			} else
			{
				end = start + pageSize - 1;
			}
		}
		strResult.append(start + " to " + end);
		strResult.append(" of " + this.totalRows + " total. ");
		if (totalRows > pageSize)
		{
			int nMin = 1;
			int nMax = totalRows / pageSize;
			if (totalRows > nMax * pageSize)
			{
				nMax++;
			}

			strResult.append("[");
			if (nCurpage > nMin)
			{
				strResult.append("<a href='#' onClick='goPage("
						+ (nCurpage - 1) + ");return false;'>Previous</a>");
			} else
			{
				strResult.append("Previous");
			}

			strResult.append(" | ");

			if (nCurpage < nMax)
			{
				strResult.append("<a href='#' onClick='goPage("
						+ (nCurpage + 1) + ");return false;'>Next</a>");
			} else
			{
				strResult.append("Next");
			}
			strResult.append("]");
		}

		return strResult.toString();
	}

	private String getRecordInfo4Chinese()
	{

		StringBuffer strResult = new StringBuffer();
		int nCurpage = this.pageNum;
		strResult.append(ConfigUtil.getProperty("pageinfo.record") + " ");
		int start = 0, end = 0;
		if (totalRows > 0)
		{
			start = (pageNum - 1) * pageSize + 1;
			if (pageList.size() < pageSize)
			{
				end = start + pageList.size() - 1;
			} else
			{
				end = start + pageSize - 1;
			}
		}
		strResult.append(start + " " + ConfigUtil.getProperty("pageinfo.to")
				+ " " + end);
		strResult.append(" " + ConfigUtil.getProperty("pageinfo.totalrecord1")
				+ " " + this.totalRows + " "
				+ ConfigUtil.getProperty("pageinfo.tiao") + " ");
		if (totalRows > pageSize)
		{
			int nMin = 1;
			int nMax = totalRows / pageSize;
			if (totalRows > nMax * pageSize)
			{
				nMax++;
			}

			strResult.append(ConfigUtil.getProperty("pageinfo.left.bracket"));
			if (nCurpage > nMin)
			{
				strResult.append("<a href='#' onClick='goPage("
						+ (nCurpage - 1) + ");return false;'>"
						+ ConfigUtil.getProperty("pageinfo.prepage") + "</a>");
			} else
			{
				strResult.append(ConfigUtil.getProperty("pageinfo.prepage"));
			}

			strResult.append(" | ");

			if (nCurpage < nMax)
			{
				strResult.append("<a href='#' onClick='goPage("
						+ (nCurpage + 1) + ");return false;'>"
						+ ConfigUtil.getProperty("pageinfo.nextpage") + "</a>");
			} else
			{
				strResult.append(ConfigUtil.getProperty("pageinfo.nextpage"));
			}
			strResult.append(ConfigUtil.getProperty("pageinfo.right.bracket"));
		}

		return strResult.toString();
	}

	/**
	 * 
	 * @return
	 */
	public String getPageInfo()
	{
		if (locale != null
				&& Locale.ENGLISH.getLanguage().equals(locale.getLanguage()))
			return getPageInfo4English();
		return getPageInfo4Chinese();
	}

	private String getPageInfo4English()
	{
		StringBuffer strResult = new StringBuffer();
		if (totalRows > 0)
		{
			int nCurpage = this.pageNum;

			int nMin = 1;
			int nMax = totalRows / pageSize;
			if (totalRows > nMax * pageSize)
			{
				nMax++;
			}

			strResult.append("[");
			if (nCurpage > nMin)
			{
				strResult.append("<a href='#' onClick='goPage("
						+ (nCurpage - 1) + ");return false;'>Previous</a>");
			} else
			{
				strResult.append("Previous");
			}

			strResult.append("] page ");
			if (nMax > PAGE_LIMIT)
			{
				strResult
						.append("<select onchange='goPage(this.value);return false;'>");
				for (int i = nMin; i <= nMax; i++)
				{
					if (nCurpage != i)
					{
						strResult.append("<option value='" + i + "' >" + i
								+ "</option>");
					} else
					{
						strResult.append("<option value='" + i + "' selected >"
								+ i + "</option>");
					}
				}
				strResult.append("</select>");
			} else
			{
				for (int i = nMin; i <= nMax; i++)
				{
					if (nCurpage != i)
					{
						strResult.append("<a href='#' onClick='goPage(" + i
								+ ");return false;'>" + i + "</a>");
					} else
					{
						strResult.append("<FONT color=red>" + i + "</FONT>");
					}
					strResult.append(" ");
				}
			}
			strResult.append(" of " + nMax + " total [");

			if (nCurpage < nMax)
			{
				strResult.append("<a href='#' onClick='goPage("
						+ (nCurpage + 1) + ");return false;'>Next</a>");
			} else
			{
				strResult.append("Next");
			}
			strResult.append("]");
		} else
		{
			strResult.append("<font color='red'><B>" + getNotFound4English()
					+ "</B></font>");
		}
		return strResult.toString();
	}

	private String getPageInfo4Chinese()
	{
		StringBuffer strResult = new StringBuffer();

		if (totalRows > 0)
		{
			int nCurpage = this.pageNum;

			int nMin = 1;
			int nMax = totalRows / pageSize;
			if (totalRows > nMax * pageSize)
			{
				nMax++;
			}

			strResult.append(ConfigUtil.getProperty("pageinfo.left.bracket"));
			if (nCurpage > nMin)
			{
				strResult.append("<a href='#' onClick='goPage("
						+ (nCurpage - 1) + ");return false;'>"
						+ ConfigUtil.getProperty("pageinfo.prepage") + "</a>");
			} else
			{
				strResult.append(ConfigUtil.getProperty("pageinfo.prepage"));
			}

			strResult.append(ConfigUtil.getProperty("pageinfo.no") + " ");
			if (nMax > PAGE_LIMIT)
			{
				strResult
						.append("<select onchange='goPage(this.value);return false;'>");
				for (int i = nMin; i <= nMax; i++)
				{
					if (nCurpage != i)
					{
						strResult.append("<option value='" + i + "' >" + i
								+ "</option>");
					} else
					{
						strResult.append("<option value='" + i + "' selected >"
								+ i + "</option>");
					}
				}
				strResult.append("</select>");
			} else
			{
				for (int i = nMin; i <= nMax; i++)
				{
					if (nCurpage != i)
					{
						strResult.append("<a href='#' onClick='goPage(" + i
								+ ");return false;'>" + i + "</a>");
					} else
					{
						strResult.append("<FONT color=red>" + i + "</FONT>");
					}
					strResult.append(" ");
				}
			}
			strResult.append(" "
					+ ConfigUtil.getProperty("pageinfo.totalpage1") + " "
					+ nMax + " "
					+ ConfigUtil.getProperty("pageinfo.totalpage2"));

			if (nCurpage < nMax)
			{
				strResult.append("<a href='#' onClick='goPage("
						+ (nCurpage + 1) + ");return false;'>"
						+ ConfigUtil.getProperty("pageinfo.nextpage") + "</a>");
			} else
			{
				strResult.append(ConfigUtil.getProperty("pageinfo.nextpage"));
			}
			strResult.append(ConfigUtil.getProperty("pageinfo.right.bracket"));
		} else
		{
			strResult.append("<font color='red'><b>" + getNotFound4Chinese()
					+ "</b></font>");
		}
		return strResult.toString();
	}

	/**
	 * ��ѯ����޼�¼ʱ��ʾδ�ҵ����������?
	 * 
	 * @author zhangyx 2006-09-04
	 */
	private String getNotFound4Chinese()
	{
		return ConfigUtil.getProperty("pageinfo.record.not.found.cn");
	}

	private String getNotFound4English()
	{
		return ConfigUtil.getProperty("pageinfo.record.not.found.en");

	}
	/**
	 * @return Returns the orderby.
	 */
	public String getOrderby()
	{
		return orderby;
	}

	/**
	 * @param orderby
	 *            The orderby to set.
	 */
	public void setOrderby(String orderby)
	{
		this.orderby = orderby;
	}

	/**
	 * @return Returns the locale.
	 */
	public Locale getLocale()
	{
		return locale;
	}

	/**
	 * @param locale
	 *            The locale to set.
	 */
	public void setLocale(Locale locale)
	{
		this.locale = locale;
	}

	/**
	 * @return Returns the count.
	 */
	public String getCountSql()
	{
		return countSql;
	}

	/**
	 * @param count The count to set.
	 */
	public void setCountSql(String count)
	{
		this.countSql = count;
	}

	/**
	 * @return Returns the nameQuery.
	 */
	public boolean isNameQuery()
	{
		return nameQuery;
	}

	/**
	 * @param nameQuery The nameQuery to set.
	 */
	public void setNameQuery(boolean nameQuery)
	{
		this.nameQuery = nameQuery;
	}

}
