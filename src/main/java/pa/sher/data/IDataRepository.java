package pa.sher.data;

import pa.sher.model.Location;
import pa.sher.model.User;
import pa.sher.model.UserLocation;

import java.io.IOException;

public interface IDataRepository {
    void saveUserLocation(UserLocation userLocation) throws IOException;
}
