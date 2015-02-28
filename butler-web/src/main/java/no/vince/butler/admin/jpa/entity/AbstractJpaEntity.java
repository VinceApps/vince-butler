package no.vince.butler.admin.jpa.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 *
 */
@MappedSuperclass
public abstract class AbstractJpaEntity implements BaseEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uid", nullable = false)
    private Long id;

    public void setId(Long id)
    {
        this.id = id;
    }

    @Override
    public Long getId()
    {
        return id;
    }
}
