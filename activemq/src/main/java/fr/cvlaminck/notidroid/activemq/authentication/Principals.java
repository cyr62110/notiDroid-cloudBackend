package fr.cvlaminck.notidroid.activemq.authentication;

import org.apache.activemq.jaas.GroupPrincipal;
import org.apache.activemq.jaas.UserPrincipal;

import java.security.Principal;

/**
 * Class defining some standard principals that are used by our plugin.
 */
public class Principals {

    /**
     * Users group principal. This group is composed of all notidroid user connected to the
     * message broker.
     */
    public static Principal USERS_GROUP = new GroupPrincipal("users");

    /**
     * User principal associated with all connections that are established between the cloudBackend
     * and this message broker.
     */
    public static Principal CLOUD_BACKEND_USER = new UserPrincipal("cloudBackend");

}
