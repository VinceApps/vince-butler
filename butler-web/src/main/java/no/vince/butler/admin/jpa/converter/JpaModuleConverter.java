package no.vince.butler.admin.jpa.converter;

import no.vince.butler.admin.jpa.entity.JpaModule;
import no.vince.butler.admin.jpa.entity.JpaParameter;
import no.vince.butler.common.model.Module;
import no.vince.butler.common.model.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class JpaModuleConverter extends AbstractConverter<JpaModule, Module>
{
    @Autowired
    private JpaParameterConverter parameterConverter;

    @Override
    public Module convertFromDao(JpaModule jpaModule)
    {
        if(jpaModule == null)
        {
            return null;
        }
        
        Module module = new Module();
        module.setName(jpaModule.getName());
        module.setUrl(jpaModule.getUrl());
        module.setParameters(parameterConverter.convertFromDaos(jpaModule.getParameters()));

        return module;
    }

    public void mergeIntoDao(JpaModule jpaModule, Module module)
    {
        jpaModule.setName(module.getName());
        jpaModule.setUrl(module.getUrl());

        for (Parameter parameter : module.getParameters())
        {
            JpaParameter jpaParameter = findOrCreateParameter(parameter.getParameterName(), jpaModule);
            parameterConverter.mergeIntoDao(jpaParameter, parameter);
        }
    }

    private JpaParameter findOrCreateParameter(String parameterName, JpaModule jpaModule)
    {
        if (jpaModule.getParameters() != null)
        {
            for (JpaParameter jpaParameter : jpaModule.getParameters())
            {
                if (jpaParameter.getParameterName().equals(parameterName))
                {
                    return jpaParameter;
                }
            }
        }

        JpaParameter jpaParameter = new JpaParameter();
        jpaParameter.setParameterName(parameterName);
        jpaParameter.setModule(jpaModule);
        jpaModule.getParameters().add(jpaParameter);
        return jpaParameter;
    }
}
