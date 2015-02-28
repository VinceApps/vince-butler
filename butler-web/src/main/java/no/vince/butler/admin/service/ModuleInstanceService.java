package no.vince.butler.admin.service;

import no.vince.butler.admin.module.ModuleInstance;

import java.util.List;

/**
 *
 */
public interface ModuleInstanceService
{
    ModuleInstance getInstance(long id);

    List<ModuleInstance> getAllInstances();

    void storeInstance(ModuleInstance moduleInstance);

    void deleteInstance(long id);
}
