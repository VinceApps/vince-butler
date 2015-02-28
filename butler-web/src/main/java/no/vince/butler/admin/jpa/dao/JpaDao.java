package no.vince.butler.admin.jpa.dao;

import no.vince.butler.admin.jpa.entity.BaseEntity;

/**
 * Generic interface for JPA DAOs. Contains methods for get, persist and merge
 */
public interface JpaDao<T extends BaseEntity>
{
    /**
     *
     * @param id The entity id
     * @return The entity with the supplied id. Null if none are found
     */
    T get(long id);

    /**
     *
     * @param t The entity to be persisted
     * @return The unique id of the persisted entity
     */
    long persist(T t);

    /**
     *
     * @param t The merged entity
     * @return The entity to be merged
     */
    T merge(T t);

    /**
     *
     * @param t The entity to be removed
     */
    void remove(T t);
}
