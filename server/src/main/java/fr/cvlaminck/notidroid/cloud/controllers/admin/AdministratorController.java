package fr.cvlaminck.notidroid.cloud.controllers.admin;

import fr.cvlaminck.notidroid.cloud.data.entities.users.UserEntity;
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

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public ModelAndView formAddNewAdministrator() {
        return null;
        //return new ModelAndView("administrators.add", "newAdministrator", new AdministratorEntity());
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ModelAndView addNewAdministrator(@ModelAttribute UserEntity newAdministrator) {
        return null;
        //return new ModelAndView("administrators.add", "newAdministrator", newAdministrator);
    }

}
