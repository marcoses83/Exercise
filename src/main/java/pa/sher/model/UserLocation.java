package pa.sher.model;

public class UserLocation {
    private String username;
    private String postalcode;
    private String city;

    public UserLocation(String username, String postalcode, String city) {
        this.username = username;
        this.postalcode = postalcode;
        this.city = city;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
