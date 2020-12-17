package ateam.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

public class Validator {
	/**
	 * Add this annotation to all Attributes that contain objects should get their
	 * inner structure validated.
	 */
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.FIELD)
	public @interface Valid {

	}

	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.FIELD)
	public @interface Required {
		String errorMessage() default "%fieldname% benötigt!";
	}

	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.FIELD)
	public @interface Regex {
		String regex();
		String errorMessage();
	}

	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.FIELD)
	public @interface Min {
		double value();
		String errorMessage() default "%fieldname% benötigt eine größe von mindestens %value%!";
	}

	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.FIELD)
	public @interface Max {
		double value();
		String errorMessage() default "%fieldname% benötigt eine größe von mindestens %value%!";
	}

	public static void validate(Object object, Class<?>... ignoreAnnotations) {
		validate(object, new HashSet<>(), new HashSet<>(Arrays.asList(ignoreAnnotations)));
	}

	private static void validate(Object object, HashSet<Object> seenObjects, HashSet<Class<?>> ignoreAnnotations) {
		validateObjects(object, seenObjects, ignoreAnnotations);
		if(object instanceof Iterable) {
			for(Object o : (Iterable) object) {
				validate(o, seenObjects, ignoreAnnotations);
			}
		} else if(object instanceof Object[]) {
			for(Object o : (Object[]) object) {
				validate(o, seenObjects, ignoreAnnotations);
			}
		}
	}

	private static void validateObjects(Object object, HashSet<Object> seenObjects, HashSet<Class<?>> ignoreAnnotations) {
		if (seenObjects.contains(object)) {
			return;
		} else {
			seenObjects.add(object);
		}

		for(Field field : object.getClass().getDeclaredFields()) {
			try {
				field.setAccessible(true);
				Object fieldValue = field.get(object);
				if(!ignoreAnnotations.contains(Required.class) && field.isAnnotationPresent(Required.class) && fieldValue == null) {
					throw new ValidationException(field.getAnnotation(Required.class).errorMessage().replace("%fieldname%", field.getName()));
				}
				if(fieldValue != null) {
					if(!ignoreAnnotations.contains(Regex.class) && field.isAnnotationPresent(Regex.class)) {
						String string = String.valueOf(fieldValue);
						String regex = field.getAnnotation(Regex.class).regex();
						if(!string.matches(regex)) {
							throw new ValidationException(field.getAnnotation(Regex.class).errorMessage().replace("%fieldname%", field.getName()));
						}
					}
					if(!ignoreAnnotations.contains(Min.class) && field.isAnnotationPresent(Min.class)) {
						double min = field.getAnnotation(Min.class).value();
						double objLength = getObjectLength(fieldValue);
						if(objLength < min && !epsilonEqual(objLength, min)) {
							throw new ValidationException(field.getAnnotation(Min.class).errorMessage()
								.replace("%fieldname%", field.getName()).replace("%value%", String.valueOf(min)));
						}
					}
					if(!ignoreAnnotations.contains(Max.class) && field.isAnnotationPresent(Max.class)) {
						double max = field.getAnnotation(Max.class).value();
						double objLength = getObjectLength(fieldValue);
						if(max < objLength && !epsilonEqual(objLength, max)) {
							throw new ValidationException(field.getAnnotation(Max.class).errorMessage()
								.replace("%fieldname%", field.getName()).replace("%value%", String.valueOf(max)));
						}
					}
					if(!ignoreAnnotations.contains(Valid.class) && field.isAnnotationPresent(Valid.class)) {
						validate(fieldValue, seenObjects, ignoreAnnotations);
					}

				}
				field.setAccessible(false);
			} catch (IllegalAccessException e) {
				throw new ValidationException("Konnte Feld mit dem Namen " + field.getName() + " in einem " + object.getClass().getName() + "-Objekt nicht validieren!");
			}

		}
	}

	private static boolean epsilonEqual(double a, double b) {
		final double EPSILON = 0.0001d;
		return Math.abs(a - b) < EPSILON;
	}

	private static double getObjectLength(Object object) {
		final String isRealNumberRegex = "^\\d+(\\.\\d+)?$";
		final String isCompleteNumberRegex = "^\\d+$";
		String stringValue = String.valueOf(object);
		double numericValue;
		if(object instanceof Object[]) {
			numericValue = ((Object[]) object).length;
		} else if(object instanceof Collection) {
			numericValue = ((Collection) object).size();
		} else if(stringValue.matches(isRealNumberRegex)) {
			if(stringValue.matches(isCompleteNumberRegex)) {
				numericValue = Long.parseLong(stringValue);
			} else {
				numericValue = Double.parseDouble(stringValue);
			}
		} else {
			numericValue = stringValue.length();
		}
		return numericValue;
	}
}
