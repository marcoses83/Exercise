package pa.sher.rest;

import pa.sher.model.UserLocation;
import pa.sher.service.DataService;
import pa.sher.service.GeonamesService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Path("geo")
public class GeoService {
    @Inject
    private GeonamesService geonamesService;

    @Inject
    private DataService dataService;

    @GET
    @Path("/{username}/{postalcode}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCity(@PathParam("username") String username, @PathParam("postalcode") String postalcode) {
        try {
            String city = geonamesService.getCity(postalcode);
            UserLocation uLoc = new UserLocation(username, postalcode, city);
            dataService.saveUserLocation(uLoc);
            return Response.status(200).entity(uLoc).build();
        } catch (IOException e) {
            e.printStackTrace();
            return Response.status(500, e.getMessage()).build();
        }
    }
}
