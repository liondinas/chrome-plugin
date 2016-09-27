package com.chewen.component.mongo.util;

import java.lang.reflect.Field;

import org.apache.commons.lang.StringUtils;
import org.mongodb.morphia.AdvancedDatastore;
import org.mongodb.morphia.mapping.Mapper;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import com.chewen.tools.commons.mongo.annotation.CwField;

public class DBParamBuilder {
	public static <T> Query<T> buildQuery(AdvancedDatastore dataStrore, Object obj, Class<T> clazz) {
		Query<T> query = dataStrore.createQuery(clazz);
		try {
			for (Field field : clazz.getDeclaredFields()) {
				CwField annotation = field.getAnnotation(CwField.class);
				if (annotation != null) {
					String name = annotation.name();
					if (StringUtils.isBlank(name) || name.equals(Mapper.IGNORED_FIELDNAME)) {
						name = field.getName();
					}
					field.setAccessible(true);
					Object value = getValue(field, obj);
					if (value != null) {
						query.field(name).equal(value);
					}
				}
			}
		} catch (Exception e) {
		}
		return query;
	}
	
	public static <T> UpdateOperations<T> buildOps(AdvancedDatastore dataStrore, Object obj, Class<T> clazz) {
		UpdateOperations<T> ops = dataStrore.createUpdateOperations(clazz);
		try {
			for (Field field : clazz.getDeclaredFields()) {
				CwField annotation = field.getAnnotation(CwField.class);
				if (annotation != null) {
					String name = annotation.name();
					if (StringUtils.isBlank(name) || name.equals(Mapper.IGNORED_FIELDNAME)) {
						name = field.getName();
					}
					field.setAccessible(true);
					Object value = getValue(field, obj);
					if (value != null) {
						ops.set(name, value);
					}
				}
			}
		} catch (Exception e) {
		}
		return ops;
	}
	
	private static Object getValue(Field field, Object obj) throws Exception {
		Class<?> clazz = field.getType();
		if (clazz.isAssignableFrom(short.class)) {
			short value = (short)field.get(obj);
			if (value == 0) {
				return null;
			}
		}
		if (clazz.isAssignableFrom(int.class)) {
			int value = (int)field.get(obj);
			if (value == 0) {
				return null;
			}
		}
		if (clazz.isAssignableFrom(long.class)) {
			long value = (long)field.get(obj);
			if (value == 0l) {
				return null;
			}
		}
		if (clazz.isAssignableFrom(double.class)) {
			long value = (long)field.get(obj);
			if (value == 0l) {
				return null;
			}
		}
		if (clazz.isAssignableFrom(float.class)) {
			long value = (long)field.get(obj);
			if (value == 0l) {
				return null;
			}
		}
		return field.get(obj);
	}
}
