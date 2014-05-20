package fr.cvlaminck.notidroid.cloud.config;

import org.springframework.context.annotation.Configuration;

/**
 * A mongo database will be used to store notification and images.
 * Images via GridFS that simplify the installation of the project on a server.
 * Notification in a collection since the TTL feature will be really useful for such data.
 */
@Configuration
public class NotifDatabaseConfiguration {
}
