import fr.cvlaminck.notidroid.cloud.Application;
import fr.cvlaminck.notidroid.cloud.data.entities.devices.DeviceEntity;
import fr.cvlaminck.notidroid.cloud.data.entities.devices.android.AndroidDeviceEntity;
import fr.cvlaminck.notidroid.cloud.data.entities.devices.ios.iOSDeviceEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class SomeMongoDbTests {

    @Autowired
    private MongoOperations mongoOperations;

    @Test
    public void test() {
        DeviceEntity device = mongoOperations.findById("534d73a7300411dcc31ca917", iOSDeviceEntity.class);
        assert(device == null);
    }

}
