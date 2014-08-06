package fr.cvlaminck.notidroid.activemq;

import fr.cvlaminck.notidroid.activemq.plugins.NotidroidBrokerPlugin;
import org.apache.activemq.broker.BrokerPlugin;
import org.apache.activemq.broker.BrokerService;

/**
 *
 */
public class NotidroidActiveMQ {

    public static void main(String[] args) throws Exception {
        final BrokerService broker = new BrokerService();
        //We remove the advisory support to reduce the overhead.
        //Also the advisory support is not compatible with the destination rewriting introduced by the notidroid plugin.
        broker.setAdvisorySupport(false);

        broker.addConnector("tcp://localhost:8888?trace=true");
        broker.addConnector("mqtt://localhost:8989");

        broker.setPlugins(new BrokerPlugin[] {
                notidroidBrokerPlugin()
        });

        broker.start();
    }

    public static BrokerPlugin notidroidBrokerPlugin() {
        return new NotidroidBrokerPlugin();
    }

}
