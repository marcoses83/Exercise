package pa.sher.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import pa.sher.model.Location;
import pa.sher.model.User;
import pa.sher.model.UserLocation;

import java.io.*;

public class DataRepository {
    private static final String DB_PATH_AUTONUMBER = "C:/Sherpa/database/autonumber";
    private static final String DB_PATH_MASTER = "C:/Sherpa/database/master.json";
    private static final String DB_PATH_DETAIL = "C:/Sherpa/database/detail.json";

    public synchronized static void saveUserLocation(UserLocation userLocation) throws IOException {
        int autonumber = getAutonumber();

        int id = ++autonumber;
        ObjectMapper mapper = new ObjectMapper();
        FileWriter masterWriter = new FileWriter(DB_PATH_MASTER, true);
        mapper.writeValue(masterWriter, new User(id, userLocation.getUsername()));
        FileWriter detailWriter = new FileWriter(DB_PATH_DETAIL, true);
        mapper.writeValue(detailWriter, new Location(id, userLocation.getPostalcode(), userLocation.getCity()));

        setAutonumber(autonumber);
    }

    private static int getAutonumber() throws IOException {
        int autonumber = 0;

        try {
            String fileContent = readFile(DB_PATH_AUTONUMBER);
            autonumber = Integer.parseInt(fileContent);
        } catch (Exception e) {
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
        return autonumber;
    }

    private static void setAutonumber(int autonumber) throws IOException {
        FileWriter fileWriter = new FileWriter(DB_PATH_AUTONUMBER);
        fileWriter.write(String.valueOf(autonumber));
        fileWriter.close();
    }

    private static String readFile(String filename)
            throws Exception
    {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
        String content = bufferedReader.readLine();
        bufferedReader.close();

        return content;
    }
}
