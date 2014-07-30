package fr.cvlaminck.notidroid.cloud.data.repositories.extensions;

/**
 * Extension of repository allowing the developer to perform MERGE operation
 * on the entity handled by the repository.
 */
public interface MergingGraphRepository<T> {

    /**
     * Perform a MERGE on the entity and updates property values from
     * using entity field values.
     *
     * The field used to merge the entity must be annotated @MergeIndex and @Indexed.
     *
     * @param entity Entity to merge
     * @return Merged entity
     */
    public T merge(T entity);

}
