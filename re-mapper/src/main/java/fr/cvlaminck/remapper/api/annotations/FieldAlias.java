package fr.cvlaminck.remapper.api.annotations;

/**
 * Define an alias for a field.
 * <p>
 * During the mapping procedure, this alias will also be used
 * to find the field with which this field is mapped to.
 * The alias can be put either on the source or the destination field.
 * <p>
 * If an alias overlap the name of a field that is not ignored, it will
 * cause the ResourceEntityMapper to throw an AliasOverlappingFieldException.
 * <p>
 * TODO : to be implemented
 */

public @interface FieldAlias {
}
