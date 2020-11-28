package ateam.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

public class Validator {

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

	/**
	 *
	 */
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.FIELD)
	public @interface Min {
		long value();
		String errorMessage() default "%fieldname% benötigt eine größe von mindestens %value%!";
	}

	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.FIELD)
	public @interface Max {
		long value();
		String errorMessage() default "%fieldname% benötigt eine größe von mindestens %value%!";
	}

	public static void validate(Object object) {
		for(Field field : object.getClass().getDeclaredFields()) {
			try {
				field.setAccessible(true);
				if(field.isAnnotationPresent(Required.class) && field.get(object) == null) {
					throw new ValidationException(field.getAnnotation(Required.class).errorMessage().replace("%fieldname%", field.getName()));
				}
				if(field.isAnnotationPresent(Regex.class) && field.get(object) != null) {
					String string = String.valueOf(field.get(object));
					String regex = field.getAnnotation(Regex.class).regex();
					if(!string.matches(regex)) {
						throw new ValidationException(field.getAnnotation(Regex.class).errorMessage().replace("%fieldname%", field.getName()));
					}
				}
				if(field.isAnnotationPresent(Min.class) && field.get(object) != null) {
					long min = field.getAnnotation(Min.class).value();
					if(checkIsSmallerThen(field.get(object), min)) {
						throw new ValidationException(field.getAnnotation(Min.class).errorMessage()
							.replace("%fieldname%", field.getName()).replace("%value%", String.valueOf(min)));
					}
				}
				if(field.isAnnotationPresent(Max.class) && field.get(object) != null) {
					long max = field.getAnnotation(Max.class).value();
					if(checkIsSmallerThen(max, field.get(object))) {
						throw new ValidationException(field.getAnnotation(Max.class).errorMessage()
							.replace("%fieldname%", field.getName()).replace("%value%", String.valueOf(max)));
					}
				}
				field.setAccessible(false);
			} catch (IllegalAccessException e) {
				throw new ValidationException("Konnte Feld mit dem Namen " + field.getName() + " in einem " + object.getClass().getName() + "-Objekt nicht validieren!");
			}

		}
	}

	private static boolean checkIsSmallerThen(Object left, Object right) {
		final String isNumericRegex = "^\\d+$";
		String leftString = String.valueOf(left);
		String rightString = String.valueOf(right);
		Long leftValue;
		if(leftString.matches(isNumericRegex)) {
			leftValue = Long.parseLong(leftString);
		} else {
			leftValue = (long) (leftString.length());
		}
		Long rightValue;
		if(rightString.matches(isNumericRegex)) {
			rightValue = Long.parseLong(rightString);
		} else {
			rightValue = (long) (rightString.length());
		}
		return leftValue < rightValue;
	}
}
