package service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pa.sher.data.DataRepository;
import pa.sher.model.UserLocation;
import pa.sher.service.DataService;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DataServiceTest {
    @Mock
    DataRepository dataRepository;

    @InjectMocks
    private DataService service;

    @Test
    public void testSaveUserLocation() throws IOException {
        doNothing().when(dataRepository).saveUserLocation(any());
        ArgumentCaptor<UserLocation> argument = ArgumentCaptor.forClass(UserLocation.class);
        UserLocation userLocation = new UserLocation("myCity", "myPostalcode", "myCity");
        service.saveUserLocation(userLocation);

        verify(dataRepository, times(1)).saveUserLocation(argument.capture());
        Assert.assertEquals(userLocation, argument.getValue());
    }
}
