package com.yh.component.dictionary.taglib;

import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.ecs.html.Option;
import org.apache.ecs.html.Select;
import org.apache.log4j.Logger;

import com.yh.component.dictionary.bo.DicItem;
import com.yh.component.dictionary.utils.DicHelper;
import com.yh.component.dictionary.utils.DicSortUtils;
import com.yh.platform.core.constant.Constant;

/**
 * 
 */
public class DicItemSelectTag extends TagSupport 
{
	private static final long serialVersionUID = -4022724549073004622L;
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(DicItemSelectTag.class);

	private String m_disabled;
	private String m_multiple;
	private String m_name;
	private String m_onblur;
	private String m_onchange;
	private String m_onclick;
	private String m_ondblclick;
	private String m_onfocus;
	private String m_onkeydown;
	private String m_onkeypress;
	private String m_onkeyup;
	private String m_onmousedown;
	private String m_onmousemove;
	private String m_onmouseout;
	private String m_onmouseover;
	private String m_onmouseup;
	private String m_style;
	private String m_styleClass;
	private String m_tabindex;
	private String m_size;
	private String m_title;
	
	private String m_id;
	private String m_clazz;
	private String m_emptyText;
	private String m_selected;
	private String notInclude;
	private String include;
	private String isInDelete;//是否包含已实现的字典
	
	public String getId()
	{
		return m_id;
	}

	public void setId(String id)
	{
		this.m_id = id;
	}

	public String getClazz()
	{
		return m_clazz;
	}

	public void setClazz(String clazz)
	{
		this.m_clazz = clazz;
	}

	public String getNotInclude() {
		return notInclude;
	}

	public void setNotInclude(String notInclude) {
		this.notInclude = notInclude;
	}
	
	public String getIsInDelete() {
		return isInDelete;
	}

	public void setIsInDelete(String isInDelete) {
		this.isInDelete = isInDelete;
	}

	public String getInclude() {
		return include;
	}

	public void setInclude(String include) {
		this.include = include;
	}

	public String getDisabled() {
		return m_disabled;
	}

	public void setDisabled(String disabled) {
		m_disabled = disabled;
	}

	public String getMultiple() {
		return m_multiple;
	}

	public void setMultiple(String multiple) {
		m_multiple = multiple;
	}

	public String getName() {
		return m_name;
	}

	public void setName(String name) {
		m_name = name;
	}

	public String getOnblur() {
		return m_onblur;
	}

	public void setOnblur(String onblur) {
		m_onblur = onblur;
	}

	public String getOnchange() {
		return m_onchange;
	}

	public void setOnchange(String onchange) {
		m_onchange = onchange;
	}

	public String getOnclick() {
		return m_onclick;
	}

	public void setOnclick(String onclick) {
		m_onclick = onclick;
	}

	public String getOndblclick() {
		return m_ondblclick;
	}

	public void setOndblclick(String ondblclick) {
		m_ondblclick = ondblclick;
	}

	public String getOnfocus() {
		return m_onfocus;
	}

	public void setOnfocus(String onfocus) {
		m_onfocus = onfocus;
	}

	public String getOnkeydown() {
		return m_onkeydown;
	}

	public void setOnkeydown(String onkeydown) {
		m_onkeydown = onkeydown;
	}

	public String getOnkeypress() {
		return m_onkeypress;
	}

	public void setOnkeypress(String onkeypress) {
		m_onkeypress = onkeypress;
	}

	public String getOnkeyup() {
		return m_onkeyup;
	}

	public void setOnkeyup(String onkeyup) {
		m_onkeyup = onkeyup;
	}

	public String getOnmousedown() {
		return m_onmousedown;
	}

	public void setOnmousedown(String onmousedown) {
		m_onmousedown = onmousedown;
	}

	public String getOnmousemove() {
		return m_onmousemove;
	}

	public void setOnmousemove(String onmousemove) {
		m_onmousemove = onmousemove;
	}

	public String getOnmouseout() {
		return m_onmouseout;
	}

	public void setOnmouseout(String onmouseout) {
		m_onmouseout = onmouseout;
	}

	public String getOnmouseover() {
		return m_onmouseover;
	}

	public void setOnmouseover(String onmouseover) {
		m_onmouseover = onmouseover;
	}

	public String getOnmouseup() {
		return m_onmouseup;
	}

	public void setOnmouseup(String onmouseup) {
		m_onmouseup = onmouseup;
	}

	public String getSize() {
		return m_size;
	}

	public void setSize(String size) {
		m_size = size;
	}

	public String getStyle() {
		return m_style;
	}

	public void setStyle(String style) {
		m_style = style;
	}

	public String getStyleClass() {
		return m_styleClass;
	}

	public void setStyleClass(String styleClass) {
		m_styleClass = styleClass;
	}

	public String getTabindex() {
		return m_tabindex;
	}

	public void setTabindex(String tabindex) {
		m_tabindex = tabindex;
	}

	public String getTitle() {
		return m_title;
	}

	public void setTitle(String title) {
		m_title = title;
	}

	public String getEmptyText() {
		return m_emptyText;
	}

	public void setEmptyText(String emptyText) {
		m_emptyText = emptyText;
	}

	public String getSelected() {
		return m_selected;
	}

	public void setSelected(String selected) {
		m_selected = selected;
	}

	/**
	 * custom field
	 */
	private String dicTypeCode;

	public int doStartTag() throws JspException 
	{
		/*log.info("dicItemSelect tag start....");*/
		try 
		{
		
			Select sel_tag = new Select();
			
			if ("true".equals(m_disabled)) {
				sel_tag.setDisabled(true);
			}
			if ("true".equals(m_multiple)) {
				sel_tag.setMultiple(true);
			}
			if(m_id != null){
				sel_tag.setID(m_id);
			}
			if(m_clazz != null){
				sel_tag.setClass(m_clazz);
			}
			if (m_name != null) {
				sel_tag.setName(m_name);
			}
			if (m_onblur != null) {
				sel_tag.setOnBlur(m_onblur);
			}
			if (m_onchange != null) {
				sel_tag.setOnChange(m_onchange);
			}
			if (m_onclick != null) {
				sel_tag.setOnClick(m_onclick);
			}
			if (m_ondblclick != null) {
				sel_tag.setOnDblClick(m_ondblclick);
			}
			if (m_onfocus != null) {
				sel_tag.setOnFocus(m_onfocus);
			}
			if (m_onkeydown != null) {
				sel_tag.setOnKeyDown(m_onkeydown);
			}
			if (m_onkeypress != null) {
				sel_tag.setOnKeyPress(m_onkeypress);
			}
			if (m_onkeyup != null) {
				sel_tag.setOnKeyUp(m_onkeyup);
			}
			if (m_onmousedown != null) {
				sel_tag.setOnMouseDown(m_onmousedown);
			}
			if (m_onmousemove != null) {
				sel_tag.setOnMouseMove(m_onmousemove);
			}
			if (m_onmouseout != null) {
				sel_tag.setOnMouseOut(m_onmouseout);
			}
			if (m_onmouseover != null) {
				sel_tag.setOnMouseOver(m_onmouseover);
			}
			if (m_onmouseup != null) {
				sel_tag.setOnMouseUp(m_onmouseup);
			}
			if (m_style != null) {
				sel_tag.setStyle(m_style);
			}
			if (m_styleClass != null) {
				sel_tag.setClass(m_styleClass);
			}
			if (m_tabindex != null) {
				sel_tag.setTabindex(m_tabindex);
			}
			if (m_size != null) {
				sel_tag.setSize(m_size);
			}
			if (m_title != null) {
				sel_tag.setTitle(m_title);
			}

			if (m_emptyText != null) {
				Option op_tag = new Option();
				op_tag.setValue("");
				op_tag.setTagText(m_emptyText);
				sel_tag.addElement(op_tag);
			}

			// log.info("dicTypeOid:" + dicTypeOid);
			if (dicTypeCode != null) {

				List<DicItem> items = DicHelper.findDicItemByCode(dicTypeCode);
				if(items!=null) {
					DicSortUtils.sort(items);
				}
				Option option = null;
				if (items != null) {
					for (DicItem dicItem : items) {
						if (dicItem == null)
						{
							continue;
						}
						if(dicItem != null && Constant.YES.equals(dicItem.getIsActive()))
						{
							String itemCode = dicItem.getDicItemCode();
							if(StringUtils.isNotEmpty(notInclude)){
								String[] notIncludeAry = StringUtils.split(notInclude,",");
								if(ArrayUtils.contains(notIncludeAry, itemCode)){
									continue;
								}
							}
							else if(StringUtils.isNotEmpty(include)){
								String[] includeAry = StringUtils.split(include,",");
								if(!ArrayUtils.contains(includeAry, itemCode)){
									continue;
								}
							}
							String itemName = dicItem.getDicItemName();
							option = new Option();
							if (itemCode != null && itemCode.length() > 0 && itemName != null) {
								option.setValue(itemCode);
								option.setTagText(itemName);
								// log.info("item code:" + itemCode);
								if (itemCode.equals(m_selected)) {
									option.setSelected(true);
								}
							}
							sel_tag.addElement(option);
						}
					}
				}

			}

			JspWriter out = pageContext.getOut();
			out.println(sel_tag.toString());
		} catch (Exception e) {
			throw new JspException(e);
		}
		/*log.info("dicItemSelect tag END");*/
		return SKIP_BODY;
	}

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	public String getDicTypeCode() {
		return dicTypeCode;
	}

	public void setDicTypeCode(String dicTypeCode) {
		this.dicTypeCode = dicTypeCode;
	}

}
