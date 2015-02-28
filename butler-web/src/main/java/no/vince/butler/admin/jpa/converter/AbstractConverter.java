package no.vince.butler.admin.jpa.converter;

import no.vince.butler.admin.jpa.entity.BaseEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 */
public abstract class AbstractConverter<D extends BaseEntity, E>
{
    public abstract E convertFromDao(D d);

    public List<E> convertFromDaos(Collection<D> jpaParameters)
    {
        if (jpaParameters == null)
        {
            return null;
        }

        final List<E> parameters = new ArrayList<>();
        jpaParameters.forEach(jpaParameter -> parameters.add(convertFromDao(jpaParameter)));
        return parameters;
    }
}
