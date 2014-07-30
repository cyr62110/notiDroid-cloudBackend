package fr.cvlaminck.notidroid.cloud.data.repositories.extensions.impl;

import fr.cvlaminck.notidroid.cloud.data.annotations.MergeIndex;
import fr.cvlaminck.notidroid.cloud.data.repositories.extensions.MergingGraphRepository;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.neo4j.graphdb.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.data.neo4j.support.mapping.StoredEntityType;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class MergingGraphRepositoryImpl<T>
        implements MergingGraphRepository<T> {

    @Autowired
    private Neo4jTemplate neo4jTemplate;

    @Override
    public T merge(T entity) {
        StoredEntityType storedEntityType = neo4jTemplate.getEntityType(entity.getClass());
        if (!(storedEntityType.getAlias() instanceof String))
            throw new IllegalStateException("Alias must be instance of String to be supported by this repository extension");

        //First, we find all labels used for type representation in the database
        //This implementation only support label-based type representation
        final String sdnPrimaryLabel = "_" + storedEntityType.getAlias().toString();
        final List<String> sdnOtherLabels = new ArrayList<>();
        sdnOtherLabels.add(storedEntityType.getAlias().toString());
        for (StoredEntityType supertypeStoredEntityType : storedEntityType.getSuperTypes())
            sdnOtherLabels.add(supertypeStoredEntityType.getAlias().toString());

        //Then, we look after the field used as index.
        //This field must be annotated with the MergeIndex annotation defined in this project.
        final Field mergeField = findMergeIndexField(entity.getClass());
        final Object value;
        try {
            value = FieldUtils.readField(mergeField, entity, true);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("Cannot read the value of the merge index.", e);
        }

        final Node entityNode = neo4jTemplate.merge(sdnPrimaryLabel, mergeField.getName(), value, Collections.emptyMap(), sdnOtherLabels);

        //Finally, we update the fields of our node with the values inside our entity
        entity = neo4jTemplate.setPersistentState(entity, entityNode);
        neo4jTemplate.save(entity);

        return entity;
    }

    /**
     * Return the field annotated with the FieldIndex annotations or throws an IllegalStateException
     * if none. It also throws an IllegalStateException if the field found is not annotated with the
     * Indexed annotation from Spring data neo4j.
     *
     * @param entityType Entity to inspect
     * @return a field annotated with the FieldIndex annotation.
     */
    private Field findMergeIndexField(Class<?> entityType) {
        for (Field field : FieldUtils.getAllFields(entityType)) {
            final MergeIndex mergeIndex = field.getAnnotation(MergeIndex.class);
            if (mergeIndex != null) {
                if (field.getAnnotation(Indexed.class) == null)
                    throw new IllegalStateException("Field annotated @MergeIndex must also have the @Indexed annotation.");
                return field;
            }
        }
        throw new IllegalStateException("Cannot find any field with the @MergeIndex annotation on the entity. Cannot merge.");
    }

}
