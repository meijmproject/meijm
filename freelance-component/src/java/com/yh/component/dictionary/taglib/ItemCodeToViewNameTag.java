package com.yh.component.dictionary.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.yh.component.dictionary.utils.DicHelper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ItemCodeToViewNameTag extends TagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1189272385727371929L;

	private static final Log log = LogFactory.getLog(ItemCodeToViewNameTag.class);

	private String dicTypeCode;

	private String dicItemCode;

	public String getDicItemCode() {
		return dicItemCode;
	}

	public void setDicItemCode(String dicItemCode) {
		this.dicItemCode = dicItemCode;
	}


	public int doStartTag() throws JspException {
		log.debug("ViewItemNameByTypeOidAndItemCodeTag start...");
		if (dicTypeCode == null) {
			throw new NullPointerException();
		}
		try {

			String dicName = DicHelper.viewName(dicTypeCode, dicItemCode);
			if (dicName == null)
				dicName = "";
			log.debug("dicTypeOid:" + dicTypeCode);
			log.debug("dicItemCode:" + dicItemCode);
			pageContext.getOut().write(dicName);
			return EVAL_PAGE;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}

	public String getDicTypeCode() {
		return dicTypeCode;
	}

	public void setDicTypeCode(String dicTypeCode) {
		this.dicTypeCode = dicTypeCode;
	}

}
