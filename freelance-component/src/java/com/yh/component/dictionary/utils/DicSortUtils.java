package com.yh.component.dictionary.utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.yh.component.dictionary.bo.DicItem;

/**
 * @description 字典排序工具，需要写自定义字典排序类的，加到这里
 *
 * @author zhangqp
 * @created 16/01/16
 * @version 1.0
 */
public class DicSortUtils {

	private static class DicSorter implements Comparator<DicItem> {
		public static final DicSorter DIC_SORTER = new DicSorter();

		private DicSorter() {
		}

		public int compare(DicItem item1, DicItem item2) {
			Double d1 = item1.getDisplayOrder();
			Double d2 = item2.getDisplayOrder();
			if (d1 == null || d2 == null)
				return 0;
			Double ddd = d1 - d2;
			return ddd > 0 ? 1 : (ddd == 0 ? 0 : -1);
		}
	}
	
	/*private static class CodeBeanSorter implements Comparator<CodeBean> {
		
		public static final CodeBeanSorter CODE_SORTER = new CodeBeanSorter();

		private CodeBeanSorter() {
		}

		public int compare(CodeBean item1, CodeBean item2) {
			Double d1 = item1.getDisplayOrder();
			Double d2 = item2.getDisplayOrder();
			if (d1 == null || d2 == null)
				return 0;
			Double ddd = d1 - d2;
			return ddd > 0 ? 1 : (ddd == 0 ? 0 : -1);
		}
	}*/

	/**
	 * 字典排序
	 * 
	 * @param dicItemList
	 */
	public static void sort(List<DicItem> dicItemList) {
		Collections.sort(dicItemList, DicSortUtils.DicSorter.DIC_SORTER);// 排序
	}

	/**
	 * 字典排序
	 * 
	 * @param dicItemList
	 */
	public static void sort(List<DicItem> dicItemList, Comparator<DicItem> sorter) {
		Collections.sort(dicItemList, sorter);// 排序
	}
}