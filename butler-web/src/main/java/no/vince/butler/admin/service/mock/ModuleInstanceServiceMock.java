package no.vince.butler.admin.service.mock;

import no.vince.butler.admin.module.ModuleInstance;
import no.vince.butler.admin.service.ModuleInstanceService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
@Service
public class ModuleInstanceServiceMock implements ModuleInstanceService
{
    private Map<Long, ModuleInstance> moduleInstances = new LinkedHashMap<>();

    @Override
    public ModuleInstance getInstance(long id)
    {
        return moduleInstances.get(id);
    }

    @Override
    public List<ModuleInstance> getAllInstances()
    {
        return new ArrayList<>(moduleInstances.values());
    }

    @Override
    public void storeInstance(ModuleInstance moduleInstance)
    {
        if (moduleInstance.getId() == null)
        {
            moduleInstance.setId(moduleInstances.size() + 1l);
            this.moduleInstances.put(moduleInstance.getId(), moduleInstance);
        } else
        {
            deleteInstance(moduleInstance.getId());
            this.moduleInstances.put(moduleInstance.getId(), moduleInstance);
        }
    }

    @Override
    public void deleteInstance(long id)
    {
        this.moduleInstances.remove(id);
    }
}
