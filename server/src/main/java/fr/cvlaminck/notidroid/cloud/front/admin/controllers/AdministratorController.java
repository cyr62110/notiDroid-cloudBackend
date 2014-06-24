package fr.cvlaminck.notidroid.cloud.front.admin.controllers;

import fr.cvlaminck.notidroid.cloud.core.exceptions.NotidroidException;
import fr.cvlaminck.notidroid.cloud.core.managers.users.AdministratorManager;
import fr.cvlaminck.notidroid.cloud.data.entities.users.AdministratorEntity;
import fr.cvlaminck.notidroid.cloud.data.entities.users.PermissionEntity;
import fr.cvlaminck.notidroid.cloud.data.entities.users.UserEntity;
import fr.cvlaminck.notidroid.cloud.front.admin.helpers.SecurityHelper;
import fr.cvlaminck.notidroid.cloud.front.admin.resources.users.PermissionResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.*;

@Controller
@RequestMapping("/admin/administrators")
public class AdministratorController
        extends MasterController
        implements MessageSourceAware {
    private Logger LOG = LoggerFactory.getLogger(AdministratorController.class);

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
    public ModelAndView formAddNewAdministrator(Authentication authentication) {
        final AdministratorEntity currentAdministrator = SecurityHelper.getAdministrator(authentication);
        final AdministratorEntity newAdministrator = new AdministratorEntity();

        final Map<String, Object> model = new HashMap<String, Object>();
        model.put("newAdministrator", newAdministrator);
        model.put("giveAllPermissions", Boolean.valueOf(false));

        model.put("permissions", getPermissions(newAdministrator, currentAdministrator));

        return new ModelAndView("administrators.create", model);
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public ModelAndView addNewAdministrator(@ModelAttribute UserEntity newAdministrator) {
        //TODO
        return null;
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
        model.put("giveAllPermissions", Boolean.valueOf(true));

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
        model.put("giveAllPermissions", Boolean.valueOf(true));
        model.put("error", null);

        return new ModelAndView("administrators.create", model);
    }

    /**
     * Return a list of permissions that can be given to the provided administrator.
     */
    private Collection<PermissionResource> getPermissions(AdministratorEntity administrator, AdministratorEntity currentAdministrator) {
        final List<PermissionResource> permissions = new LinkedList<>();
        //We must convert the list of all resources in a list
        //of permission that can be given to the new/modified administrator
        for(PermissionEntity permission : PermissionEntity.values()) {
            permissions.add(new PermissionResource(messageSource, permission, administrator, currentAdministrator));
        }
        return permissions;
    }

}
