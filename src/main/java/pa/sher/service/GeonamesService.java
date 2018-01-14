package pa.sher.service;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class GeonamesService {
    private static String username = "marcoses83";
    private static String countryIdo2 = "ES";

    public static String getCity(String postalcode, String countryIso2) {
        String city = "";

        try {
            StringBuilder url = new StringBuilder();
            url.append("http://api.geonames.org/postalCodeSearch?");
            url.append("postalcode=").append(postalcode);
            url.append("&country=").append(countryIso2);
            url.append("username=").append(username);

            String xmlResponse = getApiRequest(url.toString());
            DocumentBuilderFactory docBFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBFactory.newDocumentBuilder();
            InputStream stream = new ByteArrayInputStream(xmlResponse.getBytes("UTF-8"));
            Document doc = docBuilder.parse(stream);
            city = doc.getElementsByTagName("name").item(0).getTextContent();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return city;
    }

    private static String getApiRequest(String url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) new URL(url).openConnection();
        try {
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream stream;
            if (200 <= urlConnection.getResponseCode() && urlConnection.getResponseCode() < 300) {
                stream = urlConnection.getInputStream();
            } else if (urlConnection.getResponseCode() == 404) {
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

    /*public static String getCity(String postalcode) {
        return "Bilbao";
    }*/
}
