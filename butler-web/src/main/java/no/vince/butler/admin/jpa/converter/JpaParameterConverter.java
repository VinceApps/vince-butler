package no.vince.butler.admin.jpa.converter;

import no.vince.butler.admin.jpa.entity.JpaParameter;
import no.vince.butler.common.model.Parameter;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class JpaParameterConverter extends AbstractConverter<JpaParameter, Parameter>
{
    @Override
    public Parameter convertFromDao(JpaParameter jpaParameter)
    {
        if(jpaParameter == null)
        {
            return null;
        }

        Parameter parameter = new Parameter();
        parameter.setParameterName(jpaParameter.getParameterName());
        parameter.setParameterType(jpaParameter.getParameterType());
        parameter.setMandatory(jpaParameter.isMandatory());

        return parameter;
    }

    public void mergeIntoDao(JpaParameter jpaParameter, Parameter parameter)
    {
        jpaParameter.setMandatory(parameter.isMandatory());
        jpaParameter.setParameterType(parameter.getParameterType());
    }
}
