package no.vince.butler.common.model;

public class Parameter
{
    private String parameterName;
    private ParameterType parameterType;
    private boolean mandatory;

    public Parameter()
    {
    }

    public Parameter(String parameterName, ParameterType parameterType, boolean mandatory)
    {
        this.parameterName = parameterName;
        this.parameterType = parameterType;
        this.mandatory = mandatory;
    }

    public String getParameterName()
    {
        return parameterName;
    }

    public void setParameterName(String parameterName)
    {
        this.parameterName = parameterName;
    }

    public ParameterType getParameterType()
    {
        return parameterType;
    }

    public void setParameterType(ParameterType parameterType)
    {
        this.parameterType = parameterType;
    }

    public boolean isMandatory()
    {
        return mandatory;
    }

    public void setMandatory(boolean mandatory)
    {
        this.mandatory = mandatory;
    }
}
