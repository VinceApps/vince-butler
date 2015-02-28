package no.vince.butler.admin.jpa.entity;

import no.vince.butler.common.model.ParameterType;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 */
@Entity
@Table(name = "module_parameter", uniqueConstraints = {
        @UniqueConstraint(name = "uc_module_parameter_key", columnNames = {"module_uid", "parameter_name"})
})
public class JpaParameter extends AbstractJpaEntity
{
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "module_uid", nullable = false)
    private JpaModule module;

    @Column(name = "parameter_name", length = 50, nullable = false)
    private String parameterName;

    @Column(name = "parameter_type", nullable = false)
    private ParameterType parameterType;

    @Column(name = "mandatory", nullable = false)
    private boolean mandatory;

    public JpaModule getModule()
    {
        return module;
    }

    public void setModule(JpaModule module)
    {
        this.module = module;
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
