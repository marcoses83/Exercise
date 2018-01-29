package service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import pa.sher.data.DataRepository;
import pa.sher.model.UserLocation;
import pa.sher.service.DataService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.doNothing;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.verifyStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DataRepository.class)
public class DataServiceTest {
    @InjectMocks
    private DataService service;

    @Test
    public void testSaveUserLocation() throws Exception {
        mockStatic(DataRepository.class);
        doNothing().when(DataRepository.class, "saveUserLocation", any());
        UserLocation userLocation = new UserLocation("myCity", "myPostalcode", "myCity");
        service.saveUserLocation(userLocation);

        verifyStatic(DataRepository.class, times(1));
        DataRepository.saveUserLocation(userLocation);
    }
}
