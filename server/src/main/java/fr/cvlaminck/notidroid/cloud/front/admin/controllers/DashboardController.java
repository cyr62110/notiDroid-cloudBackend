package fr.cvlaminck.notidroid.cloud.front.admin.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * This controller provides all the data required for the dashboard displayed on the first page
 * of the administration.
 */
@Controller
@RequestMapping("/admin")
public class DashboardController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DashboardController.class);

    /**
     * This function serve the HTML that will be displayed in the user browser
     *
     * @return The name of the template to use
     */
    @RequestMapping(method = RequestMethod.GET)
    public String get() {
        LOGGER.info("get");
        return "master";
    }

}
