package pa.sher.helpers;

import javax.ws.rs.core.Response;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRequestHelper {
    public Response get(String url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) new URL(url).openConnection();
        try {
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream stream;
            if (200 <= urlConnection.getResponseCode() && urlConnection.getResponseCode() < 300) {
                stream = urlConnection.getInputStream();
            } else if (urlConnection.getResponseCode() == 404) {
                return Response.status(404).entity(null).build();
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
                return Response.ok(builder.toString()).build();
            }
        } catch (IOException e) {
            throw new IOException(e.toString());
        } finally {
            urlConnection.disconnect();
        }
    }
}
