package fr.cvlaminck.remapper.api.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A field annotated with this annotation will be ignored by the mapping
 * and not copied to the destination object.
 * <p>
 * Otherwise, if the object with this annotated field is the destination type
 * of a mapping, this field will be filled if the source object do not have
 * the IgnoreField annotation on the source field.
 * <p>
 * TODO : to be implemented
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface IgnoreField {
}
