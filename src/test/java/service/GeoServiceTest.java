package service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pa.sher.data.DataRepository;
import pa.sher.model.UserLocation;
import pa.sher.rest.GeoService;
import pa.sher.service.GeonamesService;

import javax.ws.rs.core.Response;
import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GeoServiceTest {
    @Mock
    GeonamesService geonamesService;

    @Mock
    DataRepository dataRepository;

    @InjectMocks
    private GeoService service;

    @Test
    public void testGetCity() throws IOException {
        when(geonamesService.getCity(anyString())).thenReturn("myCity");
        Response result = service.getCity("myUsername", "myPostalcode");
        UserLocation uLoc = (UserLocation)result.getEntity();
        UserLocation expectedULoc = new UserLocation("myUsername", "myPostalcode", "myCity");

        Assert.assertEquals(200, result.getStatus());
        Assert.assertEquals(expectedULoc, uLoc);
    }

    @Test
    public void testGetCityWhenNonExistingPostalcode() throws IOException {
        when(geonamesService.getCity(anyString())).thenReturn("");
        Response result = service.getCity("myUsername", "myPostalcode");
        UserLocation uLoc = (UserLocation)result.getEntity();
        UserLocation expectedULoc = new UserLocation("myUsername", "myPostalcode", "");

        Assert.assertEquals(200, result.getStatus());
        Assert.assertEquals(expectedULoc, uLoc);
    }

    @Test
    public void testGetCityWhenErrorOccurs() throws IOException {
        doThrow(new IOException()).when(dataRepository).saveUserLocation(any());
        Response result = service.getCity("myUsername", "myPostalcode");

        Assert.assertEquals(500, result.getStatus());
    }
}
