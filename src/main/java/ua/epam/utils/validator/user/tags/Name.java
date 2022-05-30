package ua.epam.utils.validator.user.tags;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Name {
    final  String regEx = "^[А-ЯA-Z]{1}[а-яa-z]{1,16}$";
}
