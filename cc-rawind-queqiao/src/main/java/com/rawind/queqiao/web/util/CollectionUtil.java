package com.rawind.queqiao.web.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.math.NumberUtils;

public class CollectionUtil {
	/**
	 * 将集合连接成字符串
	 * 
	 * @param list
	 * @param seprator
	 * @return
	 */
	public static <T extends Object> String join(Collection<T> list, String seprator) {
		if (list != null && list.size() > 0) {
			StringBuilder ret = new StringBuilder();
			for (T t : list) {
				ret.append(seprator).append(t);
			}
			return ret.substring(1);
		}
		return null;
	}

	public static List<Long> convertString2Long(String[] list) {
		List<Long> ret = new ArrayList<Long>();
		for (String string : list) {
			ret.add(NumberUtils.toLong(string, 0));
		}
		return ret;
	}

	public static <T> boolean isNotEmpty(Collection<T> list) {
		return !isEmpty(list);
	}

	public static <T> boolean isEmpty(Collection<T> list) {
		if (list == null || list.size() < 1) {
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> List<T> converArray2List(T... objArr) {
		List<T> ret = new ArrayList<T>();
		for (int i = 0; i < objArr.length; i++) {
			ret.add(objArr[i]);
		}
		return ret;
	}
}
