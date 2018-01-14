package pa.sher.model;

public class Location {
    private int userId;
    private String postalcode;
    private String city;

    public Location(int userId, String postalcode, String city) {
        this.userId = userId;
        this.postalcode = postalcode;
        this.city = city;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
