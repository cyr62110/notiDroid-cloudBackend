package fr.cvlaminck.notidroid.cloud.data.repositories;

import fr.cvlaminck.notidroid.cloud.Application;
import fr.cvlaminck.notidroid.cloud.data.entities.devices.DeviceEntity;
import fr.cvlaminck.notidroid.cloud.data.entities.devices.android.AndroidDeviceEntity;
import fr.cvlaminck.notidroid.cloud.data.entities.users.UserEntity;
import fr.cvlaminck.notidroid.cloud.data.repositories.devices.DeviceRepository;
import fr.cvlaminck.notidroid.cloud.data.repositories.users.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Tests for the Neo4JRepository base implementation.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class})
public class Neo4JRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DeviceRepository deviceRepository;

    @Test
    public void testTypeSafetyPolicy_deviceIsNotAnUser() throws Exception {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("An email");

        DeviceEntity deviceEntity = new AndroidDeviceEntity();
        deviceEntity.setOwnerId("ownerId");

        userRepository.save(userEntity);
        deviceRepository.save(deviceEntity);

        assertNull(userRepository.findOne(Long.valueOf(deviceEntity.getId())));
    }

    @Test
    public void testTypeSafetyPolicy_androidDeviceIsADevice() throws Exception {
        DeviceEntity deviceEntity = new AndroidDeviceEntity();
        deviceEntity.setOwnerId("ownerId");

        deviceRepository.save(deviceEntity);

        assertNotNull(deviceRepository.findOne(Long.valueOf(deviceEntity.getId())));
    }

}
