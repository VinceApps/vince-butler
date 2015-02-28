package no.vince.butler.admin.jpa.entity;

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
@Table(name = "module_instance_param_value", uniqueConstraints = {
        @UniqueConstraint(name = "uc_module_instance_param_value_key", columnNames = {"module_instance_uid", "module_parameter_uid"})
})
public class JpaParameterValue extends AbstractJpaEntity
{
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "module_instance_uid", nullable = false)
    private JpaModuleInstance moduleInstance;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "module_parameter_uid", nullable = false)
    private JpaParameter parameter;

    @Column(name = "parameter_value", nullable = true)
    private String parameterValue;

    public JpaModuleInstance getModuleInstance()
    {
        return moduleInstance;
    }

    public void setModuleInstance(JpaModuleInstance moduleInstance)
    {
        this.moduleInstance = moduleInstance;
    }

    public JpaParameter getParameter()
    {
        return parameter;
    }

    public void setParameter(JpaParameter parameter)
    {
        this.parameter = parameter;
    }

    public String getParameterValue()
    {
        return parameterValue;
    }

    public void setParameterValue(String parameterValue)
    {
        this.parameterValue = parameterValue;
    }
}
