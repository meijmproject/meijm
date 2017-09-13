package com.yh.platform.core.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.Assert;

import com.yh.platform.core.exception.ServiceException;

/**
 * @description 反射操作工具类
 * <br>
 * 使用示例：
 * 
 * //拷贝属性（使用spring的BeanUtils）<br>
 * DTO dto = BeanHelper.copyProperties(bo, DTO.class)
 * 
 * //拷贝集合<br>
 * List<DTO> dtoList = BeanHelper.copyProperties(boList, DTO.class)
 * 
 * //exportProperties 方法拷贝属性， String 和 Number 类型可以互转<br>
 * DTO dto = BeanHelper.exportProperties(bo, DTO.class)
 * 
 * @author zhangqp
 * @created 14/02/17
 * @version 1.0
 */

public class BeanHelper {
	
//	private static Logger	logger	= Logger.getLogger(BeanRefUtils.class);
	
	/**
	 *@description	拷贝集合时的回调接口
	 *
	 *@author		zhangqp
	 *@created		14/03/31
	 *@version		1.0
	 */
	public interface PropertiesHandler<S,T> {
		public T getValue(S src) throws ServiceException;
	}
	
	/**
	 * 对象为空则抛异常
	 * @param obj
	 * @param message
	 * @throws ServiceException
	 */
	public static void isNull(Object obj, String message) throws ServiceException{
		if(obj == null) throw new ServiceException(null, message);
	}
	
	/**
	 * 判断对象（不包含集合）指定的属性是否为空，如果为空则跑出异常信息
	 * @param obj
	 * @param propertyName
	 * @param message
	 * @throws ServiceException
	 */
	public static void isNull(Object obj, String propertyName, String message) throws ServiceException {
		isNull(get(obj, propertyName), message);
	}
	
	/**
	 * 或者对象指定的值是否为null或空串  该属性值必须是String类型
	 * @param obj
	 * @param propertyName
	 * @param message
	 * @throws ServiceException
	 */
	public static void isEmpty(Object obj, String propertyName, String message) throws ServiceException {
		isEmpty(get(obj, propertyName), message);
	}
	
	/**
	 * 对象为空或空串则抛异常
	 * @param obj
	 * @param message
	 * @throws ServiceException
	 */
	public static void isEmpty(Object obj, String message) throws ServiceException {
		isNull(emptyAs(obj, null), message);
	}
	
	/**
	 * 对象为null 或 空串时返回默认字符
	 * @param str 实际类型是String的object
	 * @param defaultStr
	 * @return
	 */
	public static String emptyAs(Object str, String defaultStr) {
		return StringUtils.defaultIfEmpty((String) str, defaultStr);
	}
	
	/**
	 * 转换list，空集合则返回 null（Spring 拷贝方法）
	 * @param sourceList 	需要转换的List <font color=red>为空返回null</font>
	 * @param rtType		 转换成List的类型
	 * @return
	 * @throws ServiceException
	 */
	public static <T,S> List<T> copyProperties(List<S> sourceList, final Class<T> rtType) throws ServiceException {
		if (CollectionUtils.isNotEmpty(sourceList)) {
			return copyProperties(sourceList,
					new PropertiesHandler<S, T>() {
						public T getValue(S src) throws ServiceException {
							return copyProperties(src, rtType);
						}
					}
			);
		}
		return null;
	}
	
	/**
	 * 转换list，空集合则返回 null（Spring 拷贝方法）
	 * @param sourceList 	需要转换的List （如果是empty， 返回null）
	 * @param rtType		 转换成List的类型
	 * @return
	 * @throws ServiceException
	 */
	public static <T,S> List<T> copyProperties(List<S> sourceList, PropertiesHandler<S,T> handler) throws ServiceException {
		if (CollectionUtils.isNotEmpty(sourceList)) {
			List<T> rtList = new ArrayList<T>();
			for (S src : sourceList) {
				rtList.add(handler.getValue(src));
			}
			return rtList;
		}
		return null;
	}
	
//	/**
//	 * 获取一组beans相同属性的值的集合
//	 * @param sourceList
//	 * @param propertyName
//	 * @param rtType
//	 * @return
//	 * @throws ServiceException
//	 */
//	@SuppressWarnings("unchecked")
//	public static <T> List<T> getValues(List<?> sourceList, String propertyName, Class<T> rtType) throws ServiceException {
//		if (CollectionUtils.isNotEmpty(sourceList)) {
//			List<T> rtList = new ArrayList<T>();
//			for (Object obj : sourceList) {
//				rtList.add((T) get(obj,propertyName));
//			}
//			return rtList;
//		}
//		return null;
//	}
	
//	/**
//	 * 数组转换成 java.util.ArrayList
//	 * @param ary
//	 * @return
//	 */
//	public static <T> List<T> asList(T[] ary) {
//		if (!ArrayUtils.isEmpty(ary)) {
//			return new ArrayList<T>(Arrays.asList(ary));
//		}
//		return null;
//	}
	
	/**
	 * list 为空时，返回一个空(size为0)的 java.util.ArrayList
	 * @param list
	 * @return
	 */
	public static <T> List<T> nullAsEmpty(List<T> list) {
		return list == null ?  new ArrayList<T>(0) : list;
	}
	
	/**
	 * 获取java bean的指定属性值
	 * @param obj
	 * @param propertyName
	 * @return
	 * @throws ServiceException
	 */
	public static Object get(Object obj, String propertyName) throws ServiceException {
		try {
			Assert.notNull(obj, "Object must not be null");
			return BeanUtils.getPropertyDescriptor(obj.getClass(), propertyName).getReadMethod().invoke(obj, new Object[0]);
		} catch (Exception e) {
			throw new ServiceException("Get property value fail [class: "+obj.getClass().getName() + ",propertyName: " +propertyName+"].", e);
		}
	}
	
//	public static <T> T firstOne(List<T> list) {
//		if (CollectionUtils.isNotEmpty(list)) return list.get(0);
//		return null;
//	}
//	
//	public static <T> T lastOne(List<T> list) {
//		if (CollectionUtils.isNotEmpty(list)) return list.get(list.size()-1);
//		return null;
//	}
	
	/**
	 * 拷贝属性， source为null时，不进行拷贝 (直接调用spring的方法)
	 * 
	 * @param source 源
	 * @param target 接受值的目标bean
	 */
	public static void copyProperties(Object source, Object target) {
		if(source != null) {
			org.springframework.beans.BeanUtils.copyProperties(source, target);
		}
	}
	
	/**
	 * 拷贝属性(直接调用spring的方法)
	 * @param source
	 * @param target
	 * @param ignoreProperties 不需要的属性
	 */
	public static void copyProperties(Object source, Object target,String[] ignoreProperties) {
		org.springframework.beans.BeanUtils.copyProperties(source, target, ignoreProperties);
	}
	
	/**
	 * 拷贝属性 同时返回目标实例，如果对象为空，返回null (spring拷贝的方法)
	 * 
	 * @param source
	 * @param target
	 * @return
	 */
	public static <S,T> T copyRtnProperties(S source, T target) {
		if (source == null) return null;
		copyProperties(source, target);
		return target;
	}
	
	/**
	 * 使用空参数构造方法进行实例化 (spring的拷贝方法)
	 * 
	 * @param source
	 * @param clazz
	 * @return
	 * @throws ServiceException
	 */
	public static <S,T> T copyProperties(S source, Class<T> rtType) throws ServiceException {
		try {
			return copyRtnProperties(source, rtType.newInstance());
		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
			throw new ServiceException("BeanRefUtils copyProperties fail.", e);
		}
	}
	
	/**
	 * 拷贝属性，类型不一致时可以 设置忽略 或 自动转换字符和数字类型
	 * @param source
	 * @param target
	 * @param ignoreType
	 * @param convertType
	 */
	public static void exportProperties(Object source, Object target, boolean ignoreType, boolean convertType, String[] ignoreProperties) {
		Assert.notNull(source, "Source must not be null");
		Assert.notNull(target, "Target must not be null");
		PropertyDescriptor targetPds[] = BeanUtils.getPropertyDescriptors(target.getClass());
		List<String> ignoreList = ignoreProperties == null ? null : Arrays.asList(ignoreProperties);
		for (int i = 0; i < targetPds.length; i++) {
			PropertyDescriptor targetPd = targetPds[i];
			if (targetPd.getWriteMethod() == null)
				continue;
			PropertyDescriptor sourcePd = BeanUtils.getPropertyDescriptor(source.getClass(), targetPd.getName());
			if (sourcePd == null || sourcePd.getReadMethod() == null)
				continue;
			try {
				Method readMethod = sourcePd.getReadMethod();
				if(targetPd.getWriteMethod() == null || ignoreProperties != null && ignoreList.contains(targetPd.getName()))
				if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) readMethod.setAccessible(true);
				Object value = readMethod.invoke(source, new Object[0]);
				Class<?> rt = readMethod.getReturnType();
				Method writeMethod = targetPd.getWriteMethod();
				if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers()))
					writeMethod.setAccessible(true);
				Class<?> wt = writeMethod.getParameterTypes()[0];
				boolean same = readMethod.getReturnType().equals(writeMethod.getParameterTypes()[0]);
				if (!same) {
					if (convertType) {
						if (value != null) {
							//string -> number
							if (value instanceof String && Number.class.isAssignableFrom(wt)) {
								if (StringUtils.isEmpty((String) value)) {
									value = null;
								}
								else {
									Method valueOf = wt.getMethod("valueOf", String.class);
									value = valueOf.invoke(null, value);
								}
							}
							// number -> string
							else if(Number.class.isAssignableFrom(rt) && String.class.isAssignableFrom(wt)) {
								value = value.toString();
							}
							else throw new FatalBeanException("Could not convert type: "+readMethod.getReturnType()+" ==> "+writeMethod.getParameterTypes()[0]+", property: " + targetPd.getName());
						}
					}
					else if (ignoreType) continue;
				}
				writeMethod.invoke(target, new Object[] { value });
			} catch (Exception ex) {
				throw new FatalBeanException("Could not copy properties from source to target", ex);
			}
		}
	}

	/**
	 * 拷贝属性，生成指定类型的实例，并尝试转换类型不同的属性（仅支持Number类型和String类型互转），如果对象为空，返回null
	 * 
	 * @param source
	 * @param clazz
	 * @return
	 * @throws ServiceException
	 */
	public static <S,T> T exportProperties(S source, Class<T> rtType) throws ServiceException {
		try {
			return exportProperties(source, rtType, false, true, null);
		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
			throw new ServiceException("BeanRefUtils copyProperties fail.", e);
		}
	}
	
	/**
	 * 拷贝属性，生成指定类型的实例，并尝试转换类型不同的属性（仅支持Number类型和String类型互转）
	 * @param source
	 * @param rtType
	 * @param ignoreProperties 不需要拷贝的属性
	 * @return
	 * @throws ServiceException
	 */
	public static <S,T> T exportProperties(S source, Class<T> rtType, String[] ignoreProperties) throws ServiceException {
		try {
			return exportProperties(source, rtType, false, true, ignoreProperties);
		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
			throw new ServiceException("BeanRefUtils copyProperties fail.", e);
		}
	}
	
	/**
	 * 使用空参数构造方法进行实例化（自动转换类型），如果对象为空，返回null
	 * @param source
	 * @param rtType
	 * @param ignoreType
	 * @param convertType
	 * @param ignoreProperties 不需要拷贝的属性
	 * @return
	 * @throws ServiceException
	 */
	public static <S,T> T exportProperties(S source, Class<T> rtType,boolean ignoreType, boolean convertType, String[] ignoreProperties) throws ServiceException {
		try {
			return exportRtnProperties(source, rtType.newInstance(), ignoreType, convertType, ignoreProperties);
		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
			throw new ServiceException("BeanRefUtils copyProperties fail.", e);
		}
	}
	
	/**
	 * 属性拷贝（自动转换类型），如果对象为空，返回null
	 * @param source
	 * @param rtType
	 * @param ignoreType
	 * @param convertType
	 * @return
	 * @throws ServiceException
	 */
	public static <S,T> T exportProperties(S source, T target) throws ServiceException {
		try {
			return exportRtnProperties(source, target, false, true, null);
		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
			throw new ServiceException("BeanRefUtils copyProperties fail.", e);
		}
	}
	
	/**
	 * 拷贝属性 同时返回目标实例，如果对象为空，返回null
	 * 
	 * @param source
	 * @param target
	 * @return
	 */
	public static <S,T> T exportRtnProperties(S source, T target, boolean ignoreType, boolean auroTrans, String[] ignoreProperties) {
		if (source == null) return null;
		exportProperties(source, target,ignoreType, auroTrans, ignoreProperties);
		return target;
	}
	
	/**
	 * 获取对象所有为null的属性名称
	 * 对于继承自其他类的对象，需要确保所继承的类的属性都有getter，setter方法
	 * 
	 * @param obj
	 * @return
	 */
	public static String[] getNullPropertyNames(Object obj) {
        final BeanWrapper src = new BeanWrapperImpl(obj);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();
        Set<String> emptyNames = new HashSet<String>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
	
}
