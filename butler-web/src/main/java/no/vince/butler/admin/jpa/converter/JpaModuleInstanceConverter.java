package no.vince.butler.admin.jpa.converter;

import no.vince.butler.admin.jpa.dao.JpaModuleDao;
import no.vince.butler.admin.jpa.entity.JpaModule;
import no.vince.butler.admin.jpa.entity.JpaModuleInstance;
import no.vince.butler.admin.jpa.entity.JpaParameter;
import no.vince.butler.admin.jpa.entity.JpaParameterValue;
import no.vince.butler.admin.module.ModuleInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 */
@Component
public class JpaModuleInstanceConverter extends AbstractConverter<JpaModuleInstance, ModuleInstance>
{
    @Autowired
    private JpaModuleConverter moduleConverter;

    @Autowired
    private JpaModuleDao moduleDao;

    @Override
    public ModuleInstance convertFromDao(JpaModuleInstance jpaModuleInstance)
    {
        if(jpaModuleInstance == null)
        {
            return null;
        }

        ModuleInstance moduleInstance = new ModuleInstance();
        moduleInstance.setId(jpaModuleInstance.getId());
        moduleInstance.setName(jpaModuleInstance.getName());
        moduleInstance.setModule(moduleConverter.convertFromDao(jpaModuleInstance.getModule()));
        if (jpaModuleInstance.getParameterValues() != null)
        {
            Map<String, Object> parameterValues = new LinkedHashMap<>();
            for (JpaParameterValue jpaParameterValue : jpaModuleInstance.getParameterValues())
            {
                parameterValues.put(jpaParameterValue.getParameter().getParameterName(), jpaParameterValue.getParameterValue());
            }
            moduleInstance.setParameterValues(parameterValues);
        }

        return moduleInstance;
    }

    public void mergeIntoDao(JpaModuleInstance jpaModuleInstance, ModuleInstance moduleInstance)
    {
        jpaModuleInstance.setName(moduleInstance.getName());
        jpaModuleInstance.setModule(moduleDao.findModule(moduleInstance.getModule().getName()));
        if (moduleInstance.getParameterValues() != null)
        {
            for (Map.Entry<String, Object> entry : moduleInstance.getParameterValues().entrySet())
            {
                JpaParameterValue jpaParameterValue = findOrCreateParameterValue(entry.getKey(), jpaModuleInstance);
                String value = entry.getValue() == null ? null : entry.getValue().toString();
                jpaParameterValue.setParameterValue(value);
            }
        }
    }

    private JpaParameterValue findOrCreateParameterValue(String parameterName, JpaModuleInstance jpaModuleInstance)
    {
        if (jpaModuleInstance.getParameterValues() != null)
        {
            for (JpaParameterValue jpaParameterValue : jpaModuleInstance.getParameterValues())
            {
                if (jpaParameterValue.getParameter().getParameterName().equals(parameterName))
                {
                    return jpaParameterValue;
                }
            }
        }

        JpaParameterValue jpaParameterValue = new JpaParameterValue();
        jpaParameterValue.setModuleInstance(jpaModuleInstance);
        jpaParameterValue.setParameter(findParameter(parameterName, jpaModuleInstance.getModule()));

        jpaModuleInstance.getParameterValues().add(jpaParameterValue);

        return jpaParameterValue;
    }

    private JpaParameter findParameter(String parameterName, JpaModule jpaModule)
    {
        for (JpaParameter jpaParameter : jpaModule.getParameters())
        {
            if (jpaParameter.getParameterName().equals(parameterName))
            {
                return jpaParameter;
            }
        }
        throw new IllegalArgumentException("Parameter " + parameterName + " not found.");
    }
}
