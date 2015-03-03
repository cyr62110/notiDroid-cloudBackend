package fr.cvlaminck.remapper.api.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Define in which type the annotated class must be converted
 * when given to the Object2ObjectMapper#convert(Object) function.
 * <p>
 * TODO: To be implemented
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ConvertInto {
    Class<?> value();
}
