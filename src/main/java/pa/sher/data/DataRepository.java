package pa.sher.data;

import org.json.JSONObject;
import pa.sher.model.UserLocation;

import java.io.*;

public class DataRepository implements IDataRepository {
    private final String DB_PATH_AUTONUMBER = "C:/GIT/Sherpa/database/autonumber";
    private final String DB_PATH_MASTER = "C:/GIT/Sherpa/database/master.json";
    private final String DB_PATH_DETAIL = "C:/GIT/Sherpa/database/detail.json";

    public void SaveUserLocation(UserLocation userLocation) throws IOException {
        int autonumber = 0;

        try {
            FileReader fileReader = new FileReader(DB_PATH_AUTONUMBER);
            autonumber = fileReader.read();
            fileReader.close();
        } catch (FileNotFoundException e) {
            try {
                File file = new File(DB_PATH_AUTONUMBER);
                file.getParentFile().mkdirs();
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write("0");
                fileWriter.close();
            } catch (IOException e1) {
                e1.printStackTrace();
                throw new IOException(e1.getMessage());
            }
        }

        int id = ++autonumber;
        JSONObject masterObject = new JSONObject();
        masterObject.put("id", id);
        masterObject.put("username", userLocation.getUser().getUsername());

        JSONObject detailObject = new JSONObject();
        detailObject.put("id", id);
        detailObject.put("postalcode", userLocation.getLocation().getPostalcode());
        detailObject.put("city", userLocation.getLocation().getCity());

        FileWriter masterFileWriter = new FileWriter(DB_PATH_MASTER);
        masterFileWriter.write(masterObject.toString());
        masterFileWriter.close();

        FileWriter detailFileWriter = new FileWriter(DB_PATH_DETAIL);
        detailFileWriter.write(detailObject.toString());
        detailFileWriter.close();

        FileWriter fileWriter = new FileWriter(DB_PATH_AUTONUMBER);
        fileWriter.write(String.valueOf(autonumber));
        fileWriter.close();
    }
}
