package no.vince.butler.admin.jpa.dao;


import no.vince.butler.admin.jpa.entity.BaseEntity;

import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Abstract superclass for DAOs which supplies crud methods and simple lookup utils
 */
public abstract class AbstractJpaDaoImpl<T extends BaseEntity> implements JpaDao<T>
{
    @PersistenceContext
    private EntityManager entityManager;

    private final Class<T> type;

    protected AbstractJpaDaoImpl()
    {
        ParameterizedType genericType = (ParameterizedType) this.getClass().getGenericSuperclass();
        //noinspection unchecked
        this.type = (Class<T>) genericType.getActualTypeArguments()[0];
    }

    protected <E> E findSingle(CriteriaQuery<E> cq)
    {
        // uses two as max result to reveal if there are non unique searches performed
        List<E> result = findMany(cq, 0, 2);
        if (result == null || result.isEmpty())
        {
            return null;
        } else if (result.size() == 1)
        {
            return result.get(0);
        }
        throw new NonUniqueResultException("Multiple entities returned from findSingle");
    }

    protected <E> List<E> findMany(CriteriaQuery<E> cq, int startPosition, int maxResult)
    {
        TypedQuery<E> typedQuery = entityManager.createQuery(cq);
        typedQuery.setFirstResult(startPosition);
        typedQuery.setMaxResults(maxResult);

        return typedQuery.getResultList();
    }

    @Override
    public final T get(long id)
    {
        return entityManager.find(type, id);
    }

    @Override
    public final T merge(T entity)
    {
        return entityManager.merge(entity);
    }

    @Override
    public final long persist(T entity)
    {
        entityManager.persist(entity);
        return entity.getId();
    }

    @Override
    public final void remove(T entity)
    {
        entityManager.remove(entity);
    }
}
