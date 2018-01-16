package pa.sher.test.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.deps.com.google.gson.Gson;
import org.junit.Assert;
import pa.sher.helpers.HttpRequestHelper;
import pa.sher.model.UserLocation;
import pa.sher.test.steps.world.World;

import javax.ws.rs.core.Response;

public class GeoSteps {
    private World world;

    public GeoSteps(World world) {
        this.world = world;
    }

    @Given("^\"([^\"]*)\" user name$")
    public void userName(String username) throws Throwable {
        world.addValue("username", username);
    }

    @And("^\"([^\"]*)\" postal code$")
    public void postalCode(String postalcode) throws Throwable {
        world.addValue("postalcode", postalcode);
    }

    @When("^I request the city$")
    public void iRequestTheCity() throws Throwable {
        String url = "http://localhost:8080/rest/geo/" + world.getValue("username") + "/" + world.getValue("postalcode");
        Response response = new HttpRequestHelper().get(url);
        world.addValue("response", response);
    }

    @Then("^the request is successful$")
    public void theRequestIsSuccessful() throws Throwable {
        Response response = (Response)world.getValue("response");
        Assert.assertEquals(200, response.getStatus());
    }

    @And("^the city \"([^\"]*)\" is returned$")
    public void theCityIsReturned(String city) throws Throwable {
        String username = (String)world.getValue("username");
        String postalcode = (String)world.getValue("postalcode");
        UserLocation expectedUserLocation = new UserLocation(username, postalcode, city);

        String currentResponse = ((Response)world.getValue("response")).getEntity().toString();
        UserLocation currentUserLocation = new Gson().fromJson(currentResponse, UserLocation.class);

        Assert.assertEquals(expectedUserLocation, currentUserLocation);
    }
}
