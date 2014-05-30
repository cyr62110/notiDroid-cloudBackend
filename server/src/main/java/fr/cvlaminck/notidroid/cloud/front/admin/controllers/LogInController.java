package fr.cvlaminck.notidroid.cloud.front.admin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller managing the sign-in page where administrators must authenticate themselves.
 */
@Controller
@RequestMapping("/admin/log-in")
public class LogInController {

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView logIn() {
        return new ModelAndView("login");
    }

}
