package no.vince.butler.common.config;

import no.vince.butler.common.model.Module;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.PostConstruct;

/**
 *
 */
public abstract class DefaultModuleWebConfig extends WebMvcConfigurerAdapter
{
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry)
    {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer)
    {
        configurer.enable();
    }

    @PostConstruct
    public void registerModule()
    {
        final Module module = createModule();

        new Thread(() -> {
            try { Thread.sleep(10000); } catch (InterruptedException e) { }
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8080/butler/registerNewModule", module, String.class);
            if (response.getBody().equals("OK"))
            {
                System.out.println("Module " + module.getName() + " was registered!");
            } else
            {
                System.out.println("Module " + module.getName() + " was not registered!");
            }
        }).start();
    }

    protected abstract Module createModule();
}
