package fr.cvlaminck.notidroid.cloud.data.repositories.devices.hardware;

import fr.cvlaminck.notidroid.cloud.data.entities.devices.hardware.DeviceEntity;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

/**
 */
@Repository
public interface DeviceRepository extends GraphRepository<DeviceEntity> {

}
