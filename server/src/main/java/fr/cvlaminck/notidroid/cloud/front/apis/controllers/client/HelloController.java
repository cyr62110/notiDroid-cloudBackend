package fr.cvlaminck.notidroid.cloud.front.apis.controllers.client;

import fr.cvlaminck.notidroid.cloud.core.utils.security.SecurityUtils;
import fr.cvlaminck.notidroid.cloud.data.entities.users.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hello")
public class HelloController {

    @Autowired
    private SecurityUtils securityUtils;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public String hello(OAuth2Authentication authentication) {
        final UserEntity user = securityUtils.getUserFromAuthentication(authentication);
        return user.getId().toString();
    }

}
