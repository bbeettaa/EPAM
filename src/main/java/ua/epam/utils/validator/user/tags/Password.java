package ua.epam.utils.validator.user.tags;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Password {
    final String regEx = "^[\\d\\w-()!@#$%^&*]{5,16}$";
}
