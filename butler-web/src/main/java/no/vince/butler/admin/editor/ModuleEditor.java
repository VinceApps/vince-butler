package no.vince.butler.admin.editor;

import no.vince.butler.admin.service.ModuleService;
import org.apache.commons.lang.StringUtils;

import java.beans.PropertyEditorSupport;

/**
 *
 */
public class ModuleEditor extends PropertyEditorSupport
{
    private final ModuleService moduleService;

    public ModuleEditor(ModuleService moduleService)
    {
        this.moduleService = moduleService;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException
    {
        if (StringUtils.isBlank(text))
        {
            setValue(null);
        }
        setValue(moduleService.getModule(text));
    }
}
