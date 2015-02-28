package no.vince.butler.admin.module;

import no.vince.butler.common.model.Module;

import java.util.Map;

/**
 *
 */
public class ModuleInstance
{
    private Long id;
    private String name;
    private Module module;
    private Map<String, Object> parameterValues;

    public ModuleInstance()
    {
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Module getModule()
    {
        return module;
    }

    public void setModule(Module module)
    {
        this.module = module;
    }

    public Map<String, Object> getParameterValues()
    {
        return parameterValues;
    }

    public void setParameterValues(Map<String, Object> parameterValues)
    {
        this.parameterValues = parameterValues;
    }
}
