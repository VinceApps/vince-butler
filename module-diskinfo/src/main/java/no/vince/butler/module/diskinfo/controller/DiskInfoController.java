package no.vince.butler.module.diskinfo.controller;

import no.vince.butler.module.diskinfo.model.DiskInfo;
import no.vince.butler.module.diskinfo.service.DiskInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 */
@Controller
public class DiskInfoController
{
    @Autowired
    private DiskInfoService diskInfoService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public
    @ResponseBody
    DiskInfo diskInfo(@RequestParam String rootFolder)
    {
        return diskInfoService.getDiskInfo(rootFolder);
    }
}
