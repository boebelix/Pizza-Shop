package ateam.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashSet;

public class Validator {

	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.FIELD)
	public @interface Required {
		String errorMessage() default "%fieldname% required (not null)!";
	}

	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.FIELD)
	public @interface Regex {
		String regex();
		String errorMessage();
	}

	/**
	 *
	 */
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.FIELD)
	public @interface Min {
		long value();
		String errorMessage() default "%fieldname% should have a length of at least %value%!";
	}

	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.FIELD)
	public @interface Max {
		long value();
		String errorMessage() default "%fieldname% should have a length of max %value%!";
	}

	public static void validate(Object object) {
		validate(object, new HashSet<>());
	}

	private static void validate(Object object, HashSet<Object> seenObjects) {
		for(Field field : object.getClass().getDeclaredFields()) {
			try {
				field.setAccessible(true);
				if(field.get(object) != null && seenObjects.contains(field.get(object))) {
					continue;
				}
				if(field.isAnnotationPresent(Required.class) && field.get(object) == null) {
					throw new ValidationException(field.getAnnotation(Required.class).errorMessage().replace("%fieldname%", field.getName()));
				}
				if(field.get(object) != null) {
					if(field.isAnnotationPresent(Regex.class)) {
						String string = String.valueOf(field.get(object));
						String regex = field.getAnnotation(Regex.class).regex();
						if(!string.matches(regex)) {
							throw new ValidationException(field.getAnnotation(Regex.class).errorMessage().replace("%fieldname%", field.getName()));
						}
					}
					if(field.isAnnotationPresent(Min.class)) {
						long min = field.getAnnotation(Min.class).value();
						if(checkIsSmallerThen(field.get(object), min)) {
							throw new ValidationException(field.getAnnotation(Min.class).errorMessage()
								.replace("%fieldname%", field.getName()).replace("%value%", String.valueOf(min)));
						}
					}
					if(field.isAnnotationPresent(Max.class)) {
						long max = field.getAnnotation(Max.class).value();
						if(checkIsSmallerThen(max, field.get(object))) {
							throw new ValidationException(field.getAnnotation(Max.class).errorMessage()
								.replace("%fieldname%", field.getName()).replace("%value%", String.valueOf(max)));
						}
					}
					seenObjects.add(object);
					validate(field.get(object), seenObjects);
				}
				field.setAccessible(false);
			} catch (IllegalAccessException e) {
				throw new ValidationException("Konnte Feld mit dem Namen " + field.getName() + " in einem " + object.getClass().getName() + "-Objekt nicht validieren!");
			}

		}
	}

	private static boolean checkIsSmallerThen(Object left, Object right) {
		return getObjectLength(left) < getObjectLength(right);
	}

	private static long getObjectLength(Object object) {
		final String isNumericRegex = "^\\d+$";
		String stringValue = String.valueOf(object);
		long numericValue;
		if(object instanceof Collection) {
			numericValue = ((Collection) object).size();
		} else if(stringValue.matches(isNumericRegex)) {
			numericValue = Long.parseLong(stringValue);
		} else {
			numericValue = stringValue.length();
		}
		return numericValue;
	}
}
