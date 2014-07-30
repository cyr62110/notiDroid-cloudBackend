package fr.cvlaminck.notidroid.cloud.core.managers.impl.devices;

import fr.cvlaminck.notidroid.cloud.client.api.devices.AndroidUserDeviceResource;
import fr.cvlaminck.notidroid.cloud.client.api.devices.UserDeviceResource;
import fr.cvlaminck.notidroid.cloud.core.managers.api.devices.SpecificDeviceManager;
import fr.cvlaminck.notidroid.cloud.core.utils.security.SecurityUtils;
import fr.cvlaminck.notidroid.cloud.data.entities.devices.hardware.DeviceEntity;
import fr.cvlaminck.notidroid.cloud.data.entities.devices.hardware.android.AndroidDeviceEntity;
import fr.cvlaminck.notidroid.cloud.data.entities.devices.user.UserDeviceEntity;
import fr.cvlaminck.notidroid.cloud.data.entities.devices.user.android.AndroidUserDeviceEntity;
import fr.cvlaminck.notidroid.cloud.data.entities.users.UserEntity;
import fr.cvlaminck.notidroid.cloud.data.repositories.devices.user.UserDeviceRepository;
import fr.cvlaminck.notidroid.cloud.data.repositories.extensions.MergingGraphRepository;
import fr.cvlaminck.remapper.api.ResourceEntityMapper;
import fr.cvlaminck.remapper.api.mappings.ResourceEntityMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AndroidDeviceManagerImpl
        implements SpecificDeviceManager {

    @Autowired
    private MergingGraphRepository<DeviceEntity> mergingGraphRepository;

    @Autowired
    private UserDeviceRepository userDeviceRepository;

    @Autowired
    private ResourceEntityMapper resourceEntityMapper;

    @Override
    public boolean doesSupportResourceType(Class<? extends UserDeviceResource> deviceResourceType) {
        return deviceResourceType == AndroidUserDeviceResource.class;
    }

    @Override
    public boolean doesSupportEntityType(Class deviceEntityType) {
        return false; //TODO
    }

    @Override
    @Transactional
    public UserDeviceResource registerDeviceOwnedByUser(UserEntity owner, UserDeviceResource deviceResource) {
        //First, we map our resource in two entity :
        //The part with info about the hardware
        UserDeviceEntity userDevice = resourceEntityMapper.convertToEntity((AndroidUserDeviceResource) deviceResource, AndroidUserDeviceResource.class, AndroidUserDeviceEntity.class);
        //And the part with info about the specific device that the user owns
        DeviceEntity deviceEntity = resourceEntityMapper.convertToEntity((AndroidUserDeviceResource) deviceResource, AndroidUserDeviceResource.class, AndroidDeviceEntity.class);

        //Then, we merge information about the hardware
        deviceEntity = mergingGraphRepository.merge(deviceEntity);

        //Finally, we build the relationships on the UserDeviceEntity
        userDevice.setHardware(deviceEntity);
        userDevice.setOwner(owner);
        //And we save the entity
        userDevice = userDeviceRepository.save(userDevice);

        //We map the id on the resource and we return
        deviceResource.setId(userDevice.getId());
        return deviceResource;
    }

}
