package pa.sher.service;

import javax.jws.WebService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GeonamesService {
    private static String username = "marcoses83";
    private static int maxResults = 5;

    public static String getCity(String postalcode) {
        String city = "";
        WebService.setUserName(username);
        try {
            PostalCodeSearchCriteria searchCriteria = new PostalCodeSearchCriteria();
            searchCriteria.setCountryCode("ES");
            searchCriteria.setPostalCode(postalcode);
            searchCriteria.setMaxRows(maxResults);
            List<PostalCode> pcodes = WebService.postalCodeSearch(searchCriteria);
            city = pcodes.get(0).getPlaceName();
        } catch (Exception e) {

        }

        return city;
    }

    public static String getApiRequest(String url) throws IOException {

        HttpURLConnection urlConnection = (HttpURLConnection) new URL(url).openConnection();
        try {
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream stream;
            if (HttpStatus.SC_OK <= urlConnection.getResponseCode() && urlConnection.getResponseCode() < HttpStatus.SC_MULTIPLE_CHOICES) {
                stream = urlConnection.getInputStream();
            } else if (urlConnection.getResponseCode() == HttpStatus.SC_NOT_FOUND) {
                return null;
            } else {
                stream = urlConnection.getErrorStream();
                if (stream != null) {
                    StringBuilder errorBuilder = new StringBuilder();
                    errorBuilder.append("Error accessing to ").append(url).append(" : ");
                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
                        reader.lines().forEach(errorBuilder::append);
                    }
                    throw new IOException(errorBuilder.toString());
                } else {
                    throw new IOException("Error accessing to " + url);
                }
            }

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
                StringBuilder builder = new StringBuilder();
                reader.lines().forEach(builder::append);
                return builder.toString();
            }
        } catch (IOException e) {
            throw new IOException(e.toString());
        } finally {
            urlConnection.disconnect();
        }
    }

    public static String getCity(String postalcode) {
        return "Bilbao";
    }
}
