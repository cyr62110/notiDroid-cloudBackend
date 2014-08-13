package fr.cvlaminck.notidroid.activemq.utils;

import fr.cvlaminck.notidroid.activemq.authentication.Principals;
import org.apache.activemq.broker.ConnectionContext;
import org.apache.activemq.command.ConnectionInfo;
import org.apache.activemq.jaas.UserPrincipal;

import java.security.Principal;
import java.util.Collections;

/**
 * This class provide some utility methods to extract information from the ConnectionContext.
 */
public abstract class ConnectionContextUtils {

    private ConnectionContextUtils() {
    }

    /**
     * Check if the client has used notidroid user credentials to connect to the message broker.
     *
     * @param connectionContext Connection used by the client.
     */
    public static boolean isUser(ConnectionContext connectionContext) {
        return connectionContext.getSecurityContext().isInOneOf(Collections.singleton(Principals.USERS_GROUP));
    }

    /**
     * Extract the user email address from the principals stored in the SecurityContext associated
     * with this connection.
     *
     * @param connectionContext Connection used by the client.
     * @return User email address.
     */
    public static String userEmailAddress(ConnectionContext connectionContext) {
        for(Principal principal : connectionContext.getSecurityContext().getPrincipals()) {
            if(principal instanceof UserPrincipal) {
                return principal.getName();
            }
        }
        throw new IllegalArgumentException("This connection has not been established using some notidroid user credentials. Cannot extract email from credentials.");
    }

    /**
     * Check if the user can publish message. The user must have the scope 'push-framework'.
     *
     * @param connectionContext Connection used by the client.
     */
    public static boolean canUserPublish(ConnectionContext connectionContext) {
        return false;
    }

}
