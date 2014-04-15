package fr.cvlaminck.notidroid.cloud.data.mappers;

import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MongoTypeMapper;

/**
 * This type mapper will check that the type written in the _class field of our document match
 * the type that is asked by the user or at least one ot its super types.
 */
public class EnforcedMongoTypeMapper
    extends DefaultMongoTypeMapper
    implements MongoTypeMapper {

    /**
     * Maximum number of super types that this type mapper will check before returning
     * null as a result.
     */
    private int maximumLevelOfSuper;

    public EnforcedMongoTypeMapper(int maximumLevelOfSuper) {
        this.maximumLevelOfSuper = maximumLevelOfSuper;
        if(maximumLevelOfSuper < 0)
            throw new IllegalArgumentException("maximumLevelOfSuper must not be negative.");
    }




}
