package fr.cvlaminck.notidroid.cloud.data.repositories.devices.user;

import fr.cvlaminck.notidroid.cloud.data.entities.devices.user.UserDeviceEntity;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface UserDeviceRepository extends GraphRepository<UserDeviceEntity> {

    /**
     * Check if the user associated with the email address owns the provided device.
     *
     * @param userDeviceId Device id we want to check the owner.
     * @param email        Email address associated to the user account.
     * @return >0 if the device is owned by the user associated to the provided email address. 0 otherwise
     */
    @Query("START n=node({1}) MATCH (n)<-[:owns]-(p:UserEntity{email:{0}}) RETURN COUNT(p)")
    public long checkIfUserWithEmailOwnsDeviceWithId(String email, long userDeviceId);

}
