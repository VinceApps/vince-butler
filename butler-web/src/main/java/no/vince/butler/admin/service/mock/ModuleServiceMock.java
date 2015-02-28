package no.vince.butler.admin.service.mock;

import no.vince.butler.admin.service.ModuleService;
import no.vince.butler.common.model.Module;
import no.vince.butler.common.model.Parameter;
import no.vince.butler.common.model.ParameterType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
@Service
public class ModuleServiceMock implements ModuleService
{
    private Map<String, Module> modules = new LinkedHashMap<>();

    //    @PostConstruct
    public void createDummyModules()
    {
        Module module1 = new Module();
        module1.setName("M3Database");
        module1.setUrl("/m3database");
        module1.setParameters(new ArrayList<>());
        module1.getParameters().add(createParameter("sqlQuery", ParameterType.TEXT, true));
        module1.getParameters().add(createParameter("queryParam1", ParameterType.VARCHAR, false));
        module1.getParameters().add(createParameter("queryParam2", ParameterType.VARCHAR, false));
        module1.getParameters().add(createParameter("queryParam3", ParameterType.VARCHAR, false));

        storeModule(module1);

        Module module2 = new Module();
        module2.setName("DiskInfo");
        module2.setUrl("/diskinfo");
        module2.setParameters(new ArrayList<>());
        module2.getParameters().add(createParameter("rootFolder", ParameterType.VARCHAR, true));

        storeModule(module2);
    }

    private Parameter createParameter(String parameterName, ParameterType parameterType, boolean mandatory)
    {
        Parameter parameter = new Parameter();

        parameter.setParameterName(parameterName);
        parameter.setParameterType(parameterType);
        parameter.setMandatory(mandatory);

        return parameter;
    }

    @Override
    public Module getModule(String name)
    {
        return this.modules.get(name);
    }

    @Override
    public List<Module> getAllModules()
    {
        return new ArrayList<>(this.modules.values());
    }

    @Override
    public void storeModule(Module module)
    {
        if (this.modules.get(module.getName()) != null)
        {
            deleteModule(module.getName());
        }
        this.modules.put(module.getName(), module);
    }

    @Override
    public void deleteModule(String name)
    {
        this.modules.remove(name);
    }
}
