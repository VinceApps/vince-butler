package no.vince.butler.common.model;

import org.apache.commons.lang.builder.EqualsBuilder;

import java.util.List;

/**
 *
 */
public class Module
{
    private String name;
    private String url;
    private List<Parameter> parameters;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public List<Parameter> getParameters()
    {
        return parameters;
    }

    public void setParameters(List<Parameter> parameters)
    {
        this.parameters = parameters;
    }

    @Override
    public boolean equals(Object obj)
    {
        return this == obj ||
                (obj instanceof Module &&
                        new EqualsBuilder()
                                .append(name, ((Module) obj).getName())
                                .isEquals());
    }
}
