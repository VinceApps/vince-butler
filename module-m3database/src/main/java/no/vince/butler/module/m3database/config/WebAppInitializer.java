package no.vince.butler.module.m3database.config;

import no.vince.butler.common.config.DefaultModuleWebApplicationInitializer;

/**
 *
 */
@SuppressWarnings("UnusedDeclaration")
public class WebAppInitializer extends DefaultModuleWebApplicationInitializer
{
    @Override
    protected Class<?>[] getConfigClasses()
    {
        return new Class<?>[]{ServiceConfig.class};
    }

    @Override
    protected Class<?> getWebConfigClass()
    {
        return WebConfig.class;
    }
}
