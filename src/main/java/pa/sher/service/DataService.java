package pa.sher.service;

import pa.sher.data.DataRepository;
import pa.sher.model.UserLocation;

import javax.inject.Inject;
import java.io.IOException;

public class DataService {

    public void saveUserLocation(UserLocation userLocation) throws IOException {
        DataRepository.saveUserLocation(userLocation);
    }
}
