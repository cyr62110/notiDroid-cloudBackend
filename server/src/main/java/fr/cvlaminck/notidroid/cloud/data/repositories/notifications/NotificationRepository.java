package fr.cvlaminck.notidroid.cloud.data.repositories.notifications;

import fr.cvlaminck.notidroid.cloud.data.entities.notifications.NotificationEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 */
public interface NotificationRepository
    extends MongoRepository<NotificationEntity, String> {



}
