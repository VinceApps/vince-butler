package no.vince.butler.admin.jpa.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 *
 */
@Entity
@Table(name = "module")
public class JpaModule extends AbstractJpaEntity
{
    @Column(name = "name", unique = true, nullable = false, length = 50)
    private String name;

    @Column(name = "url", nullable = false)
    private String url;

    @OneToMany(mappedBy = "module", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<JpaParameter> parameters = new LinkedHashSet<>();

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

    public Set<JpaParameter> getParameters()
    {
        return parameters;
    }

    public void setParameters(Set<JpaParameter> parameters)
    {
        this.parameters = parameters;
    }
}
