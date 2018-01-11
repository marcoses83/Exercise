package pa.sher.data;

import pa.sher.model.UserLocation;

import java.io.IOException;

public interface IDataRepository {
    void SaveUserLocation(UserLocation userLocation) throws IOException;
}
