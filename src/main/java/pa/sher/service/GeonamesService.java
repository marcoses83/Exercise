package pa.sher.service;

import org.w3c.dom.Document;
import pa.sher.helpers.HttpRequestHelper;

import javax.ws.rs.core.Response;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class GeonamesService {
    private static String username = "marcoses83";
    private static String countryIso2 = "ES";

    public static String getCity(String postalcode) {
        String city = "";

        try {
            String url = "http://api.geonames.org/postalCodeSearch?" +
                    "postalcode=" + postalcode +
                    "&country=" + countryIso2 +
                    "&username=" + username;

            Response response = new HttpRequestHelper().get(url);
            DocumentBuilderFactory docBFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBFactory.newDocumentBuilder();
            if (response.getStatus() < 300) {
                InputStream stream = new ByteArrayInputStream(response.getEntity().toString().getBytes("UTF-8"));
                Document doc = docBuilder.parse(stream);
                city = doc.getElementsByTagName("name").item(0).getTextContent();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return city;
    }
}
