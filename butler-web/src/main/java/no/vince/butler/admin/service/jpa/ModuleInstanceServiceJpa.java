package no.vince.butler.admin.service.jpa;

import no.vince.butler.admin.jpa.converter.JpaModuleInstanceConverter;
import no.vince.butler.admin.jpa.dao.JpaModuleInstanceDao;
import no.vince.butler.admin.jpa.entity.JpaModuleInstance;
import no.vince.butler.admin.module.ModuleInstance;
import no.vince.butler.admin.service.ModuleInstanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 */
@Service
@Transactional(readOnly = true)
public class ModuleInstanceServiceJpa implements ModuleInstanceService
{
    @Autowired
    private JpaModuleInstanceConverter moduleInstanceConverter;

    @Autowired
    private JpaModuleInstanceDao moduleInstanceDao;

    @Override
    public ModuleInstance getInstance(long id)
    {
        return moduleInstanceConverter.convertFromDao(moduleInstanceDao.get(id));
    }

    @Override
    public List<ModuleInstance> getAllInstances()
    {
        return moduleInstanceConverter.convertFromDaos(moduleInstanceDao.findInstances(0, Integer.MAX_VALUE));
    }

    @Override
    @Transactional(readOnly = false)
    public void storeInstance(ModuleInstance moduleInstance)
    {
        if (moduleInstance.getId() == null) // new
        {
            JpaModuleInstance jpaModuleInstance = new JpaModuleInstance();
            moduleInstanceConverter.mergeIntoDao(jpaModuleInstance, moduleInstance);
            moduleInstanceDao.persist(jpaModuleInstance);
            moduleInstance.setId(jpaModuleInstance.getId());
        } else
        {
            JpaModuleInstance jpaModuleInstance = moduleInstanceDao.get(moduleInstance.getId());
            moduleInstanceConverter.mergeIntoDao(jpaModuleInstance, moduleInstance);
            moduleInstanceDao.merge(jpaModuleInstance);
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteInstance(long id)
    {
        moduleInstanceDao.remove(moduleInstanceDao.get(id));
    }
}
