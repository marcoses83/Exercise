package pa.sher.model;

public class UserLocation {
    private User user;
    private Location location;

    public UserLocation(String username, String postalcode, String city) {
        this.user = new User(username);
        this.location = new Location(postalcode, city);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
