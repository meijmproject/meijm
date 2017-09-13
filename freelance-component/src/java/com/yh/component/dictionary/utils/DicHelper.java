package com.yh.component.dictionary.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yh.component.dictionary.facade.DicTypeFacade;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.util.LabelValueBean;

import com.yh.component.dictionary.bo.DicItem;
import com.yh.component.dictionary.bo.DicType;
import com.yh.component.dictionary.facade.DicItemFacade;
import com.yh.platform.core.constant.Constant;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.ConfigUtil;
import com.yh.platform.core.util.SpringBeanUtil;

/**
 * 字典翻译工具类
 * 
 * @author zhangqp
 * @version 1.0, 16/08/17
 */
@SuppressWarnings("unchecked")
public class DicHelper {
	private static DicTypeFacade dicTypeFacade	= (DicTypeFacade) SpringBeanUtil.getBean("dicTypeFacade");
	private static DicItemFacade						dicItemFacade	= (DicItemFacade) SpringBeanUtil.getBean("dicItemFacade");

	/**
	 * 保存时效起始时间
	 */
	private static HashMap<String, Long>				periods			= new HashMap<String, Long>();

	/**
	 * 缓存代码
	 */
	private static Map<String, Map<String, DicItem>>	parameters		= new HashMap<String, Map<String, DicItem>>();
	private static Map<String, DicType>					dic_type		= new HashMap<String, DicType>(0);

	static {
		try {
			List<DicType> dts = dicTypeFacade.listAllDicType();
			for (DicType dt : dts) {
				dic_type.put(dt.getDicTypeCode(), dt);
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 取得设定的时效
	 */
	private static final Long effperiod = new Long(ConfigUtil.getProperty("parameter.active.period") == null ? "0" : ConfigUtil.getProperty("parameter.active.period"));

	/**
	 * 查找所有下级字典代码，从数据库查找最新代码（已排序、未缓存）
	 * 
	 * @param dicTypeCode
	 * @param filterDelete
	 *            true：过滤掉已删除的字典，false：加载所有的字典
	 * @return
	 * @throws ServiceException
	 */
	public static List<DicItem> listDicItemFromDb(String dicTypeCode, boolean filterDelete) throws ServiceException {
		return dicItemFacade.findDicItemListByTypeId(dicTypeCode, filterDelete);
	}

	private static Map<String, DicItem> dicListToMap(List<DicItem> dicItemList) {
		if (dicItemList == null || dicItemList.size() <= 0)
			return null;
		Map<String, DicItem> dicItemMap = new HashMap<String, DicItem>();
		for (DicItem dicItem : dicItemList) {
			dicItemMap.put(dicItem.getDicItemCode(), dicItem);
		}
		return dicItemMap;
	}

	private static List<DicItem> dicMapToList(Map<String, DicItem> dicItemMap) {
		if (dicItemMap == null || dicItemMap.size() <= 0)
			return null;
		List<DicItem> list = new ArrayList<DicItem>();
		list.addAll(dicItemMap.values());
		return list;
	}

	/**
	 * 从数据库 加载/重新加载 字典到缓存（重新加载缓存）
	 * 
	 * @param typeOid
	 * @throws ServiceException
	 */
	private static void setEffect(String dicTypeCode) throws ServiceException {
		List<DicItem> dicItems = listDicItemFromDb(dicTypeCode, true);
		if (dicItems != null) {
			parameters.put(dicTypeCode, dicListToMap(dicItems));
			periods.put(dicTypeCode, Long.valueOf((System.currentTimeMillis())));
		}
	}

	/**
	 * 判断时效性
	 * 
	 * @param typeOid
	 * @return
	 */
	private static boolean isEffect(String dicTypeCode) {
		if (!periods.containsKey(dicTypeCode))
			return false;
		if (effperiod == 0)
			return true;
		return System.currentTimeMillis() - (periods.get(dicTypeCode)).longValue() < effperiod;
	}

	/**
	 * 加载指定代码到缓存（已排序、未缓存、已过滤）
	 * 
	 * @param dicTypeCode
	 * @throws ServiceException
	 */
	private static void loadDic(String dicTypeCode) throws ServiceException {
		List<DicItem> dicItems = listDicItemFromDb(dicTypeCode, false);
		if (dicItems != null) {
			parameters.put(dicTypeCode, dicListToMap(dicItems));
		}
	}

	/**
	 * 代码来源的定义
	 */
	public static String	DIC_GB		= "1";	// 国标(GB)
	public static String	DIC_RB		= "2";	// 人事部标准(RB)
	public static String	DIC_ZB		= "3";	// 中组部标准(ZB)
	public static String	DIC_ZRB		= "4";	// 人事部中组部标准(ZRB)
	public static String	DIC_JYB		= "5";	// 教育部(JYB)
	public static String	DIC_ADDNEW	= "21";	// 专业代码中98版后增加的代码
	public static String	DIC_ZJ		= "9";	// 自建

	public static String	DIC_LS		= "0";	// 临时

	/**
	 * 过滤删除的代码和临时代码
	 * 
	 * @param items
	 * @return
	 */
	public static List<DicItem> filterDelete(List<DicItem> items) {
		if (items == null || items.size() <= 0)
			return null;
		List<DicItem> dis = new ArrayList<DicItem>();
		for (DicItem di : items) {
			if(Constant.YES.equals(di.getIsActive()))
			{
				dis.add(di);
			}
			/*if (!(Constant.NO.equals(di.getIsActive()) || DIC_LS.equals(di.getCodeResource()))) {
				dis.add(di);
			}*/
		}
		return dis;
	}

	/**
	 * 查找字典所有（未排序、已缓存）
	 * @param dicTypeCode
	 * @param filterDelete 是否包含废除的
	 * @return
	 * @throws ServiceException
	 */
	public static List<DicItem> findDicItemByCode(String dicTypeCode, boolean filterDelete) throws ServiceException {

		List<DicItem> items = null;
		if (!isEffect(dicTypeCode)) {
			setEffect(dicTypeCode);
		}
		if (parameters.containsKey(dicTypeCode)) {
			items = dicMapToList(parameters.get(dicTypeCode));
		}
		return filterDelete ? items :filterDelete(items) ;
	}

	/**
	 * 查找对应类型下所有生效的字典代码（未排序、已缓存、已过滤）
	 * 
	 * @param dicTypeCode
	 * @return
	 * @throws ServiceException
	 */
	public static List<DicItem> findDicItemByCode(String dicTypeCode) throws ServiceException {

		return findDicItemByCode(dicTypeCode, false);
	}

	/**
	 * 取得代码表示的中文释义（已缓存、已过滤）
	 * 
	 * @param dicTypeCode
	 *            代码类型
	 * @param dicItemCode
	 *            要翻译的代码值
	 * @return
	 * @throws ServiceException
	 */
	public static String viewName(String dicTypeCode, String dicItemCode) throws ServiceException {
		if (StringUtils.isEmpty(dicItemCode) || "null".equalsIgnoreCase(dicItemCode)) {
			return null;
		}
		if (!isEffect(dicTypeCode)) {
			setEffect(dicTypeCode);
		}
		if (!parameters.containsKey(dicTypeCode)) {
			loadDic(dicTypeCode);
		}
		if (parameters.containsKey(dicTypeCode)) {
			Map<String, DicItem> mapList = parameters.get(dicTypeCode);
			if (mapList != null && mapList.containsKey(dicItemCode)) {
				return mapList.get(dicItemCode).getDicItemName();
			}
		}
		return null;
	}

	/**
	 * 将字典代码转换成选项Select（label）（已排序、已缓存）
	 * 
	 * @param dicTypeCode
	 * @param ruleCodes
	 *            如果isInclude为true，则表示只包含ruleCodes里面的代码；如果isInclude为false，
	 *            则表示排除ruleCodes里面的代码；如果ruleCodes为null，则表示获取全部
	 * @param isInclude
	 *            true 包含（默认）; false 排除
	 * @return
	 * @throws ServiceException
	 */
	public static List<LabelValueBean> getDicItemLabel(String dicTypeCode, String[] ruleCodes, Boolean isInclude) throws ServiceException {
		List<DicItem> dicItemList = DicHelper.findDicItemByCode(dicTypeCode);
		if (CollectionUtils.isNotEmpty(dicItemList)) {
			DicSortUtils.sort(dicItemList);// 排序
			List<LabelValueBean> list = new ArrayList<LabelValueBean>();
			for (DicItem di : dicItemList) {
				list.add(new LabelValueBean(di.getDicItemName(), di.getDicItemCode()));
			}
			if (ruleCodes == null) {
				return list;
			}

			if (isInclude == null)
			{
				isInclude = true;
			}
			List<LabelValueBean> ruleLable = new ArrayList<LabelValueBean>();
			for (String code : ruleCodes) {
				ruleLable.add(new LabelValueBean(code, code));
			}
			return (List<LabelValueBean>) (isInclude ? intersection(list, ruleLable) : CollectionUtils.subtract(list, ruleLable));
		}
		return null;
	}

	/**
	 * 取集合交集（传入的list应该是没有重复值的，由调用者处理）
	 * 
	 * @param mainColl
	 *            主集合（返回类型、排序依据）
	 * @param ruleColl
	 *            仅参与计算的集合
	 * @return
	 */
	private static <T> List<T> intersection(Collection<T> mainColl, Collection<T> ruleColl) {
		List<T> rtList = new ArrayList<T>();
		if (CollectionUtils.isEmpty(mainColl) || CollectionUtils.isEmpty(ruleColl))
			return rtList;

		int i = 0;
		int lr = ruleColl.size();
		for (T t : mainColl) {
			if (ruleColl.contains(t)) {
				rtList.add(t);
				if (++i == lr)
					break;
			}
		}
		return rtList;
	}

	/**
	 * 从缓存中查找代码对应的DicItem对象（已缓存、已过滤）
	 * 
	 * @param dicTypeCode
	 * @param dicItemCode
	 * @return
	 * @throws ServiceException
	 */
	public static DicItem findCachedDicItem(String dicTypeCode, String dicItemCode) throws ServiceException {
		return findCachedDicItem(dicTypeCode, dicItemCode, findDicItemByCode(dicTypeCode));
	}

	/**
	 * 从缓存中获取DicItem
	 * 
	 * @param dicTypeCode
	 *            代码类别
	 * @param dicItemCode
	 *            要查找的代码
	 * @param list
	 *            已缓存的代码集合
	 * @return
	 */
	public static DicItem findCachedDicItem(String dicTypeCode, String dicItemCode, List<DicItem> list) {
		if (CollectionUtils.isNotEmpty(list)) {
			for (DicItem d : list) {
				if (d.getDicItemCode().equals(dicItemCode)) {
					return d;
				}
			}
		}
		return null;
	}

	/**
	 * 获取子列表（已排序）
	 * 
	 * @param dicTypeCode
	 * @param ruleCodes
	 *            如果isInclude为true，则表示只包含ruleCodes里面的代码；如果isInclude为false，
	 *            则表示排除ruleCodes里面的代码；如果ruleCodes为null，则表示获取全部
	 * @param isInclude
	 *            true 包含（默认）; false 排除
	 * @return
	 * @throws ServiceException
	 */
	public static List<DicItem> findDicItemList(String dicTypeCode, String parentCode, String[] ruleCodes, Boolean isInclude) throws ServiceException {
		List<DicItem> dicItemList = getSubDicItemSortedList(dicTypeCode, parentCode);

		if (ruleCodes == null) {
			return dicItemList;
		}

		if (isInclude == null)
			isInclude = Boolean.valueOf(true);

		List<DicItem> ruleDics = new ArrayList<DicItem>();
		for (String code : ruleCodes) {
			ruleDics.add(findCachedDicItem(dicTypeCode, code, dicItemList));
		}

		return (List<DicItem>) (isInclude ? intersection(dicItemList, ruleDics) : CollectionUtils.subtract(dicItemList, ruleDics));
	}

	/**
	 * 查找字典指定层级的下级代码（从数据库中查找）（已排序、未缓存，未过滤）
	 * 
	 * @param dicTypeCode
	 * @param parentItem
	 * @return
	 * @throws ServiceException
	 */
	public static List<DicItem> listSubItem(String dicTypeCode, String parentItem) throws ServiceException {
		return dicTypeFacade.listSubItem(dicTypeCode, parentItem);
	}
	
	/**
	 * 查找字典指定层级的下级代码（从数据库中查找）（已排序、未缓存，未过滤）
	 * 
	 * @param dicTypeCode
	 * @param parentItem
	 * @return
	 * @throws ServiceException
	 */
	public static List<DicItem> listSubItem(String dicTypeCode) throws ServiceException {
		return dicTypeFacade.listSubItem(dicTypeCode, null);
	}

	/**
	 * 查找字典指定层级的下级代码（从数据库中查找），如果parentCode为空，则查找所有（缓存中查找）（已排序）
	 * 
	 * @param dicTypeCode
	 * @param parentCode
	 * @return
	 * @throws ServiceException
	 */
	private static List<DicItem> getSubDicItemSortedList(String dicTypeCode, String parentCode) throws ServiceException {
		List<DicItem> dicItemList = null;

		if (StringUtils.isNotBlank(parentCode)) {
			dicItemList = listSubItem(dicTypeCode, parentCode);

		} else {
			dicItemList = findDicItemByCode(dicTypeCode);

			if (CollectionUtils.isNotEmpty(dicItemList)) {
				DicSortUtils.sort(dicItemList);
			}
		}
		return dicItemList;
	}
	
	/**
	 * 通过字典名称获取字典码
	 * @param dicTypeCode
	 * @param dicItemName
	 * @return
	 * @throws ServiceException
	 */
	public static String getCode(String dicTypeCode, String dicItemName) throws ServiceException {
		if (StringUtils.isEmpty(dicItemName) || "null".equalsIgnoreCase(dicItemName)) {
			return null;
		}
		if (!isEffect(dicTypeCode)) {
			setEffect(dicTypeCode);
		}
		if (!parameters.containsKey(dicTypeCode)) {
			loadDic(dicTypeCode);
		}
		if (parameters.containsKey(dicTypeCode)) {
			Map<String, DicItem> mapList = parameters.get(dicTypeCode);
			for (String key : mapList.keySet()) {
				if(mapList.get(key).getDicItemName().equals(dicItemName)) {
					return key;
				}
			}
		}
		return null;
	}
	
	/**
	 * 公开缓存方法
	 * @param dicTypeCode
	 * @throws ServiceException
	 */
	public static void reSetEffect(String dicTypeCode) throws ServiceException {
		setEffect(dicTypeCode);
	}
}
