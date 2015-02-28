package no.vince.butler.admin.controller;

import no.vince.butler.admin.editor.ModuleEditor;
import no.vince.butler.admin.module.ModuleInstance;
import no.vince.butler.admin.service.ModuleInstanceService;
import no.vince.butler.admin.service.ModuleService;
import no.vince.butler.common.model.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 *
 */
@Controller
public class ModuleInstanceController
{
    @Autowired
    @Qualifier("moduleInstanceValidator")
    private Validator validator;

    @Autowired
    private ModuleInstanceService moduleInstanceService;

    @Autowired
    private ModuleService moduleService;

    @InitBinder
    private void initBinder(WebDataBinder binder)
    {
        binder.setValidator(validator);
        binder.registerCustomEditor(Module.class, new ModuleEditor(moduleService));
    }

    @RequestMapping(value = "createNewInstance", method = RequestMethod.GET)
    public ModelAndView createNewInstance()
    {
        ModuleInstance moduleInstance = new ModuleInstance();

        ModelAndView modelAndView = new ModelAndView("instance/moduleInstanceForm");
        modelAndView.addObject("moduleInstance", moduleInstance);
        modelAndView.addObject("editMode", true);
        modelAndView.addObject("modules", moduleService.getAllModules());

        return modelAndView;
    }

    @RequestMapping(value = "editInstance", method = RequestMethod.GET)
    public ModelAndView editInstance(@RequestParam("id") long instanceId)
    {
        ModuleInstance moduleInstance = moduleInstanceService.getInstance(instanceId);
        if (moduleInstance == null)
        {
            return new ModelAndView("redirect:listAllInstances");
        }

        ModelAndView modelAndView = new ModelAndView("instance/moduleInstanceForm");
        modelAndView.addObject("moduleInstance", moduleInstance);
        modelAndView.addObject("editMode", true);
        modelAndView.addObject("modules", moduleService.getAllModules());

        return modelAndView;
    }

    @RequestMapping(value = "viewInstance", method = RequestMethod.GET)
    public ModelAndView viewInstance(@RequestParam("id") long instanceId)
    {
        ModuleInstance moduleInstance = moduleInstanceService.getInstance(instanceId);
        if (moduleInstance == null)
        {
            return new ModelAndView("redirect:listAllInstances");
        }

        ModelAndView modelAndView = new ModelAndView("instance/moduleInstanceForm");
        modelAndView.addObject("moduleInstance", moduleInstance);
        modelAndView.addObject("editMode", false);
        modelAndView.addObject("modules", moduleService.getAllModules());

        return modelAndView;
    }

    @RequestMapping(value = "storeInstance", method = RequestMethod.POST)
    public ModelAndView storeNewInstance(@Validated ModuleInstance moduleInstance, BindingResult result)
    {
        if (result.hasErrors())
        {
            final ModelAndView modelAndView = new ModelAndView("instance/moduleInstanceForm");
            modelAndView.addObject("moduleInstance", moduleInstance);
            modelAndView.addObject("editMode", true);
            modelAndView.addObject("modules", moduleService.getAllModules());
            modelAndView.addObject("errors", result.getAllErrors());

            return modelAndView;
        }

        moduleInstanceService.storeInstance(moduleInstance);

        return new ModelAndView("redirect:viewInstance?id=" + moduleInstance.getId());
    }

    @RequestMapping(value = "listAllInstances", method = RequestMethod.GET)
    public ModelAndView listAllInstances()
    {
        ModelAndView modelAndView = new ModelAndView("instance/moduleInstanceList");
        modelAndView.addObject("instances", moduleInstanceService.getAllInstances());

        return modelAndView;
    }

    @RequestMapping(value = "executeInstance", method = RequestMethod.GET)
    public
    @ResponseBody
    Object executeInstance(@RequestParam("id") long instanceId)
    {
        ModuleInstance moduleInstance = moduleInstanceService.getInstance(instanceId);

        String url = moduleInstance.getModule().getUrl() + getParameterListAsString(moduleInstance);

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, Object.class);
    }

    private String getParameterListAsString(ModuleInstance moduleInstance)
    {
        String parametersAsString = "";
        for (Map.Entry<String, Object> parameterEntry : moduleInstance.getParameterValues().entrySet())
        {
            if (parameterEntry.getValue() != null && !parameterEntry.getValue().equals(""))
            {
                parametersAsString += parametersAsString.length() == 0 ? "?" : "&";
                parametersAsString += parameterEntry.getKey() + "=" + parameterEntry.getValue();
            }
        }

        return parametersAsString;
    }

}
