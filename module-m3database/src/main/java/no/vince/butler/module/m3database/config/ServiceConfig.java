package no.vince.butler.module.m3database.config;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@ComponentScan(basePackages = {"no.vince.butler.module.m3database.service"})
public class ServiceConfig
{
    @Bean
    public PropertyPlaceholderConfigurer getPropertyPlaceholderConfigurer()
    {
        PropertyPlaceholderConfigurer propertyPlaceholderConfigurer = new PropertyPlaceholderConfigurer();
        propertyPlaceholderConfigurer.setIgnoreUnresolvablePlaceholders(true);
        propertyPlaceholderConfigurer.setIgnoreResourceNotFound(true);
        propertyPlaceholderConfigurer.setLocations(new ClassPathResource("secret.properties"));

        return propertyPlaceholderConfigurer;
    }
}