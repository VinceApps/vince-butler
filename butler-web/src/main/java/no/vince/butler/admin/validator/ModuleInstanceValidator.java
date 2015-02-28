package no.vince.butler.admin.validator;

import no.vince.butler.admin.module.ModuleInstance;
import no.vince.butler.common.model.Parameter;
import no.vince.butler.common.model.ParameterType;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Arrays;

/**
 *
 */
@Component
public class ModuleInstanceValidator implements Validator
{
    @Override
    public boolean supports(Class<?> clazz)
    {
        return ModuleInstance.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors)
    {
        if (target instanceof ModuleInstance)
        {
            ModuleInstance moduleInstance = (ModuleInstance) target;

            if (StringUtils.isBlank(moduleInstance.getName()))
            {
                errors.rejectValue("name", "instance.name_must_be_set");
            }

            if (moduleInstance.getModule() == null)
            {
                errors.rejectValue("module", "instance.module_must_be_selected");
            } else if (moduleInstance.getModule().getParameters() != null)
            {
                for (Parameter parameter : moduleInstance.getModule().getParameters())
                {
                    if (parameter.isMandatory())
                    {
                        boolean notSet = false;
                        if (moduleInstance.getParameterValues() == null)
                        {
                            notSet = true;
                        } else if (moduleInstance.getParameterValues().get(parameter.getParameterName()) == null)
                        {
                            notSet = true;
                        } else if (Arrays.asList(ParameterType.VARCHAR, ParameterType.TEXT).contains(parameter.getParameterType())
                                && moduleInstance.getParameterValues().get(parameter.getParameterName()).equals(""))
                        {
                            notSet = true;
                        }

                        if (notSet)
                        {
                            errors.rejectValue("parameterValues[" + parameter.getParameterName() + "]", "instance.parameter_x1_must_be_set", new Object[]{parameter.getParameterName()}, "");
                        }
                    }
                }
            }
        }
    }
}
