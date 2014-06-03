package fr.cvlaminck.notidroid.cloud.front.admin.controllers;

import fr.cvlaminck.notidroid.cloud.core.exceptions.NotidroidException;
import fr.cvlaminck.notidroid.cloud.core.managers.users.AdministratorManager;
import fr.cvlaminck.notidroid.cloud.data.entities.users.AdministratorEntity;
import fr.cvlaminck.notidroid.cloud.data.entities.users.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin/administrators")
public class AdministratorController
        extends MasterController
        implements MessageSourceAware {

    private MessageSource messageSource;

    @Autowired
    private AdministratorManager administratorManager;

    public AdministratorController() {

    }

    @Override
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public ModelAndView formAddNewAdministrator() {
        return null;
        //return new ModelAndView("administrators.add", "newAdministrator", new AdministratorEntity());
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public ModelAndView addNewAdministrator(@ModelAttribute UserEntity newAdministrator) {
        return null;
        //return new ModelAndView("administrators.add", "newAdministrator", newAdministrator);
    }

    /**
     * Page that will create the first administrator will all permissions
     * If there is already an administrator in the system, redirect the administrator to the
     * dashboard.
     */
    @RequestMapping(value = "create-first-admin", method = RequestMethod.POST)
    public ModelAndView createFirstAdministrator(@ModelAttribute AdministratorEntity administratorEntity) {
        //If there is already an administrator in the database, redirect the user to the dashboard
        if (administratorManager.hasAtLeastOneAdministrator()) {
            return new ModelAndView("redirect:/admin");
        }

        final Map<String, Object> model = new HashMap<String, Object>();
        model.put("newAdministrator", new AdministratorEntity());
        model.put("giveAllPermissions", Boolean.valueOf(false));

        try {
            administratorManager.createAdministrator(administratorEntity);
            return new ModelAndView("redirect:/admin");
        } catch (NotidroidException e) {
            model.put("error", e.getMessage(messageSource, LocaleContextHolder.getLocale()));
        }
        return new ModelAndView("administrators.create", model);
    }

    /**
     * Page that will create the first administrator will all permissions
     * If there is already an administrator in the system, redirect the administrator to the
     * dashboard.
     */
    @RequestMapping(value = "create-first-admin", method = RequestMethod.GET)
    public ModelAndView formCreateFirstAdministrator() {
        //If there is already an administrator in the database, redirect the user to the dashboard
        if (administratorManager.hasAtLeastOneAdministrator()) {
            return new ModelAndView("redirect:/admin");
        }

        final Map<String, Object> model = new HashMap<String, Object>();
        model.put("newAdministrator", new AdministratorEntity());
        model.put("giveAllPermissions", Boolean.valueOf(false));
        model.put("error", null);

        return new ModelAndView("administrators.create", model);
    }
}
