package no.vince.butler.admin.controller;

import no.vince.butler.admin.service.ModuleService;
import no.vince.butler.common.model.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 */
@SuppressWarnings("UnusedDeclaration")
@Controller
public class ModuleController
{
    @Autowired
    private ModuleService moduleService;

    @RequestMapping(value = "registerNewModule", method = RequestMethod.POST)
    public
    @ResponseBody
    String registerNewModule(@RequestBody final Module module)
    {
        moduleService.storeModule(module);

        System.out.println("New module registered: " + module.getName());
        return "OK";
    }

    @RequestMapping(value = "listAllModules", method = RequestMethod.GET)
    public ModelAndView listAllModules()
    {
        ModelAndView modelAndView = new ModelAndView("module/listModules");
        modelAndView.addObject("modules", moduleService.getAllModules());

        return modelAndView;
    }


}
