package fr.cvlaminck.notidroid.activemq.plugins;

import fr.cvlaminck.notidroid.activemq.filters.NotidroidUserBrokerFilter;
import org.apache.activemq.broker.Broker;
import org.apache.activemq.broker.BrokerPlugin;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.activemq.filter.DestinationMapEntry;
import org.apache.activemq.security.AuthorizationMap;
import org.apache.activemq.security.AuthorizationPlugin;
import org.apache.activemq.security.DefaultAuthorizationMap;
import org.apache.activemq.security.JaasAuthenticationPlugin;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This plugin will install all plugins and filters that are required
 * to make this ActiveMQ Broker instance able to be used with
 * Notidroid.
 * <p>
 * /!\ Do not use this plugin with another one. They may interact with each other.
 */
public class NotidroidBrokerPlugin
        implements BrokerPlugin {

    @Override
    public Broker installPlugin(Broker broker) throws Exception {
        broker = installBrokenFilter(broker);
        //broker = installAuthorizationPlugin(broker);
        broker = installJaasAuthenticationPlugin(broker);
        return broker;
    }

    /**
     * Install the authentication plugin required to let notidroid user
     * connect to this broker and receive message.
     */
    private Broker installJaasAuthenticationPlugin(Broker broker) {
        final JaasAuthenticationPlugin jaasAuthenticationPlugin = new JaasAuthenticationPlugin();
        configureJaasAuthenticationPlugin(jaasAuthenticationPlugin);
        return jaasAuthenticationPlugin.installPlugin(broker);
    }

    /**
     * Install the authorization plugin.
     */
    private Broker installAuthorizationPlugin(Broker broker) {
        final AuthorizationPlugin authorizationPlugin = new AuthorizationPlugin();
        configureAuthorizationPlugin(authorizationPlugin);
        return authorizationPlugin.installPlugin(broker);
    }

    /**
     * Install the broker filter required to let notidroid user
     * interact with the broker.
     */
    private Broker installBrokenFilter(Broker broker) {
        return new NotidroidUserBrokerFilter(broker);
    }

    private void configureJaasAuthenticationPlugin(JaasAuthenticationPlugin authenticationPlugin) {
        authenticationPlugin.setConfiguration("notidroid"); //The configuration for the jaas plugin is called 'notidroid' and its included in the package as resource.
    }

    private void configureAuthorizationPlugin(AuthorizationPlugin authorizationPlugin) {
        final DefaultAuthorizationMap authorizationMap = new DefaultAuthorizationMap();
        final List<DestinationMapEntry> destinationMapEntries = new ArrayList<>();

        //We configure some ACLs
        //User connected through notidroid client can only sub on the virtual 'users.me' topic. Users are in the 'user' group.
        //TODO
        //User having 'push-framework' in their scope can also publish on the virtual 'users.me' topic. Those users also belong to the 'push' group.
        //TODO
        //The cloudBackend has all right on all user topic. In fact, it is the cloudBackend that publishes a message when a notification is received on a device and forwarded to the cloudBackend.
        //TODO

        authorizationMap.setAuthorizationEntries(destinationMapEntries);
        authorizationPlugin.setMap(authorizationMap);
    }

}
