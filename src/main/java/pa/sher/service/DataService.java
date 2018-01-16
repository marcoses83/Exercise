package pa.sher.service;

import pa.sher.data.DataRepository;
import pa.sher.model.UserLocation;

import javax.inject.Inject;
import java.io.IOException;

public class DataService {
    @Inject
    private DataRepository dataRepository;

    public void saveUserLocation(UserLocation userLocation) throws IOException {
        dataRepository.saveUserLocation(userLocation);
    }
}
