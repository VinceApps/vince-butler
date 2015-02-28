package no.vince.butler.admin.jpa.dao;

import no.vince.butler.admin.jpa.entity.JpaModule;
import no.vince.butler.admin.jpa.entity.JpaModule_;
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
public class JpaModuleDao extends AbstractJpaDaoImpl<JpaModule>
{
    @PersistenceContext
    private EntityManager entityManager;

    public List<JpaModule> findModules(int firstResult, int maxResults)
    {
        final CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        final CriteriaQuery<JpaModule> cq = cb.createQuery(JpaModule.class);
        final Root<JpaModule> root = cq.from(JpaModule.class);
        cq.select(root);
        cq.orderBy(cb.asc(root.get(JpaModule_.id)));

        return findMany(cq, firstResult, maxResults);
    }

    public JpaModule findModule(String moduleName)
    {
        final CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        final CriteriaQuery<JpaModule> cq = cb.createQuery(JpaModule.class);
        final Root<JpaModule> root = cq.from(JpaModule.class);
        cq.select(root);
        cq.where(cb.equal(root.get(JpaModule_.name), moduleName));

        return findSingle(cq);
    }
}
