package no.vince.butler.admin.jpa.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 *
 */
@Entity
@Table(name = "module_instance")
public class JpaModuleInstance extends AbstractJpaEntity
{
    @Column(name = "name", unique = true, nullable = false, length = 50)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "module_uid", nullable = false)
    private JpaModule module;

    @OneToMany(mappedBy = "moduleInstance", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<JpaParameterValue> parameterValues = new LinkedHashSet<>();

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public JpaModule getModule()
    {
        return module;
    }

    public void setModule(JpaModule module)
    {
        this.module = module;
    }

    public Set<JpaParameterValue> getParameterValues()
    {
        return parameterValues;
    }

    public void setParameterValues(Set<JpaParameterValue> parameterValues)
    {
        this.parameterValues = parameterValues;
    }
}
