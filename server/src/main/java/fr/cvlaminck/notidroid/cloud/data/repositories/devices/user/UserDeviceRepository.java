package fr.cvlaminck.notidroid.cloud.data.repositories.devices.user;

import fr.cvlaminck.notidroid.cloud.data.entities.devices.user.UserDeviceEntity;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface UserDeviceRepository extends GraphRepository<UserDeviceEntity> {
}
