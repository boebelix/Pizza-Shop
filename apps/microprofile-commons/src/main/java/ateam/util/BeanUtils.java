package ateam.util;

import java.lang.reflect.Field;

public class BeanUtils {

	/**
	 * Assigns all compatible Attributes with the same name of the object source to the object target
	 * @param target The object that should get Attributes of the object source assigned
	 * @param source The source object that provides Data
	 * @param onlyNonNulls if true only Attributes that are non-null will be assigned to the target-object
	 */
	public static void assign(Object target, Object source, boolean onlyNonNulls) {
		Field[] targetFields = target.getClass().getDeclaredFields();
		for(Field targetField : targetFields) {
			targetField.setAccessible(true);
			Field sourceField;
			try {
				sourceField = source.getClass().getDeclaredField(targetField.getName());
			} catch (NoSuchFieldException e) {
				continue;
			} finally {
				targetField.setAccessible(false);
			}

			if(targetField.getType().isAssignableFrom(sourceField.getType())) {
				sourceField.setAccessible(true);
				try {
					if(!(onlyNonNulls && sourceField.get(source) == null)) {
						targetField.set(target, sourceField.get(source));
					}
				} catch (IllegalAccessException e) {
					continue;
				} finally {
					sourceField.setAccessible(false);
				}
				sourceField.setAccessible(false);
			}
			targetField.setAccessible(false);

		}
	}

}
