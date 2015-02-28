package no.vince.butler.admin.config;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@ComponentScan(basePackages = {
        "no.vince.butler.admin.jpa"
})
public class DaoConfig
{
    @Bean
    public PropertyPlaceholderConfigurer getPropertyPlaceholderConfigurer()
    {
        PropertyPlaceholderConfigurer propertyPlaceholderConfigurer = new PropertyPlaceholderConfigurer();
        propertyPlaceholderConfigurer.setIgnoreUnresolvablePlaceholders(true);
        propertyPlaceholderConfigurer.setIgnoreResourceNotFound(true);
        propertyPlaceholderConfigurer.setLocations(new ClassPathResource("server.properties"));

        return propertyPlaceholderConfigurer;
    }
}
