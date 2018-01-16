package service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pa.sher.helpers.HttpRequestHelper;
import pa.sher.service.GeonamesService;

import javax.ws.rs.core.Response;
import java.io.IOException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GeonamesServiceTest {
    @Mock
    HttpRequestHelper httpRequestHelper;

    @InjectMocks
    private GeonamesService service;

    @Test
    public void testGetCity() throws IOException {
        String geonamesResponse =
                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>" +
                        "<name>myCity</name>";
        Response response = Response.ok().entity(geonamesResponse).build();
        when(httpRequestHelper.get(anyString())).thenReturn(response);
        String result = service.getCity("myPostalcode");

        Assert.assertEquals("myCity", result);
    }

    @Test
    public void testGetCityWhenNonExistingPostalcode() throws IOException {
        String geonamesResponse =
                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
        Response response = Response.ok().entity(geonamesResponse).build();
        when(httpRequestHelper.get(anyString())).thenReturn(response);
        String result = service.getCity("myPostalcode");

        Assert.assertEquals("", result);
    }

    @Test
    public void testGetCityWhenStatusCode404() throws IOException {
        Response response = Response.status(404).entity("").build();
        when(httpRequestHelper.get(anyString())).thenReturn(response);
        String result = service.getCity("myPostalcode");

        Assert.assertEquals("", result);
    }

    @Test
    public void testGetCityWhenErrorOccurs() throws IOException {
        doThrow(new IOException()).when(httpRequestHelper).get(anyString());
        String result = service.getCity("myPostalcode");

        Assert.assertEquals("", result);
    }
}