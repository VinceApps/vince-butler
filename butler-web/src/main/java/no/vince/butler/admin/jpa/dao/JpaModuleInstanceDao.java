package no.vince.butler.admin.jpa.dao;

import no.vince.butler.admin.jpa.entity.JpaModuleInstance;
import no.vince.butler.admin.jpa.entity.JpaModuleInstance_;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 *
 */
@Repository
public class JpaModuleInstanceDao extends AbstractJpaDaoImpl<JpaModuleInstance>
{
    @PersistenceContext
    private EntityManager entityManager;

    public List<JpaModuleInstance> findInstances(int firstResult, int maxResults)
    {
        final CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        final CriteriaQuery<JpaModuleInstance> cq = cb.createQuery(JpaModuleInstance.class);
        final Root<JpaModuleInstance> root = cq.from(JpaModuleInstance.class);
        cq.select(root);
        cq.orderBy(cb.asc(root.get(JpaModuleInstance_.id)));

        return findMany(cq, firstResult, maxResults);
    }
}
