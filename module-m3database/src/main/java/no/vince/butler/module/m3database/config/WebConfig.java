package no.vince.butler.module.m3database.config;

import no.vince.butler.common.config.DefaultModuleWebConfig;
import no.vince.butler.common.model.Module;
import no.vince.butler.common.model.Parameter;
import no.vince.butler.common.model.ParameterType;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.ArrayList;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = {"no.vince.butler.module.m3database.controller"})
public class WebConfig extends DefaultModuleWebConfig
{
    @Override
    protected Module createModule()
    {
        Module module = new Module();
        module.setName("M3DatabaseQuery");
        module.setUrl("http://localhost:8080/m3database");
        module.setParameters(new ArrayList<>());
        module.getParameters().add(new Parameter("sqlQuery", ParameterType.TEXT, true));
        module.getParameters().add(new Parameter("queryParam1", ParameterType.VARCHAR, false));
        module.getParameters().add(new Parameter("queryParam2", ParameterType.VARCHAR, false));
        module.getParameters().add(new Parameter("queryParam3", ParameterType.VARCHAR, false));

        return module;
    }
}
