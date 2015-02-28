package no.vince.butler.module.diskinfo.config;

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
@ComponentScan(basePackages = {"no.vince.butler.module.diskinfo.controller"})
public class WebConfig extends DefaultModuleWebConfig
{
    @Override
    protected Module createModule()
    {
        Module module = new Module();
        module.setName("DiskInfo");
        module.setUrl("http://localhost:8080/diskinfo");
        module.setParameters(new ArrayList<>());
        module.getParameters().add(new Parameter("rootFolder", ParameterType.VARCHAR, true));

        return module;
    }
}
