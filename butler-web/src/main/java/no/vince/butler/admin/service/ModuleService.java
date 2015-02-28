package no.vince.butler.admin.service;

import no.vince.butler.common.model.Module;

import java.util.List;

/**
 *
 */
public interface ModuleService
{
    Module getModule(String name);

    List<Module> getAllModules();

    void storeModule(Module module);

    void deleteModule(String name);
}
