package fr.cvlaminck.notidroid.cloud.data.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicate this field is used to perform a neo4j MERGE on the entity.
 * The field must also be annotated @Indexed since MERGE can only be executed
 * on index.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface MergeIndex {
}
