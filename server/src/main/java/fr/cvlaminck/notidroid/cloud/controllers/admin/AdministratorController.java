package fr.cvlaminck.notidroid.cloud.controllers.admin;

import fr.cvlaminck.notidroid.cloud.data.entities.Administrator;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/administrators")
public class AdministratorController extends MasterController {

    public AdministratorController() {

    }

    @Secured(value = {"ROLE_ADMIN"})
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public ModelAndView formAddNewAdministrator() {
        return new ModelAndView("administrators.add", "newAdministrator", new Administrator());
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ModelAndView addNewAdministrator(@ModelAttribute Administrator newAdministrator) {
        return new ModelAndView("administrators.add", "newAdministrator", newAdministrator);
    }

}
