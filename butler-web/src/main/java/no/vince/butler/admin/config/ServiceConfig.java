package no.vince.butler.admin.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@Configuration
//@ComponentScan(basePackages = {"no.vince.butler.admin.service.mock"})
@ComponentScan(basePackages = {"no.vince.butler.admin.service.jpa"})
public class ServiceConfig
{
}