package fr.cvlaminck.notidroid.cloud.front.admin.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Locale;

/**
 * This controller provides all the data required for the dashboard displayed on the first page
 * of the administration.
 */
@Controller
@RequestMapping("/admin")
public class DashboardController
        implements MessageSourceAware {

    private static final Logger LOGGER = LoggerFactory.getLogger(DashboardController.class);

    private MessageSource messageSource = null;

    @Override
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * This function serve the HTML that will be displayed in the user browser
     *
     * @return The name of the template to use
     */
    @RequestMapping(method = RequestMethod.GET)
    public String get() {
        return "master";
    }


}
