package fr.cvlaminck.notidroid.cloud.front.apis.controllers.client;

import fr.cvlaminck.notidroid.cloud.client.api.push.PushMessage;
import fr.cvlaminck.notidroid.cloud.core.managers.api.push.PushMessageManager;
import fr.cvlaminck.notidroid.cloud.core.utils.security.SecurityUtils;
import fr.cvlaminck.notidroid.cloud.data.entities.users.UserEntity;
import fr.cvlaminck.notidroid.cloud.prvt.api.mq.UserTopicUtils;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/hello")
public class HelloController {

    @Autowired
    private SecurityUtils securityUtils;

    @Autowired
    private PushMessageManager pushMessageManager;

    private static final Base64 base64 = new Base64(true);

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public String hello(OAuth2Authentication authentication) {
        final UserEntity user = securityUtils.getUserFromAuthentication(authentication);

        final PushMessage pushMessage = pushMessageManager.createTextMessage(null, null, "Un texte");
        pushMessageManager.pushMessageTo(user, pushMessage);

        return Long.toString(pushMessage.getAppId());
    }

}
