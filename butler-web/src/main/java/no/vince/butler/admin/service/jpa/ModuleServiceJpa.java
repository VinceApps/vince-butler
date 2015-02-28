package no.vince.butler.admin.service.jpa;

import no.vince.butler.admin.jpa.converter.JpaModuleConverter;
import no.vince.butler.admin.jpa.dao.JpaModuleDao;
import no.vince.butler.admin.jpa.entity.JpaModule;
import no.vince.butler.admin.service.ModuleService;
import no.vince.butler.common.model.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 */
@Service
@Transactional(readOnly = true)
public class ModuleServiceJpa implements ModuleService
{
    @Autowired
    private JpaModuleConverter moduleConverter;

    @Autowired
    private JpaModuleDao moduleDao;

    @Override
    public Module getModule(String name)
    {
        return moduleConverter.convertFromDao(moduleDao.findModule(name));
    }

    @Override
    public List<Module> getAllModules()
    {
        return moduleConverter.convertFromDaos(moduleDao.findModules(0, Integer.MAX_VALUE));
    }

    @Override
    @Transactional(readOnly = false)
    public void storeModule(Module module)
    {
        JpaModule jpaModule = moduleDao.findModule(module.getName());
        if (jpaModule == null) // new
        {
            jpaModule = new JpaModule();
            moduleConverter.mergeIntoDao(jpaModule, module);
            moduleDao.persist(jpaModule);
        } else
        {
            moduleConverter.mergeIntoDao(jpaModule, module);
            moduleDao.merge(jpaModule);
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteModule(String name)
    {
        moduleDao.remove(moduleDao.findModule(name));
    }
}
