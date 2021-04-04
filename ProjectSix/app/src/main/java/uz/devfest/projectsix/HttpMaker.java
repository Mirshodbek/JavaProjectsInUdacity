package uz.devfest.projectsix;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HttpMaker {
    private static final String TAG = "News";
    private static final String API_KEY = "70cb289e-b0e8-46ca-a590-a7fa1a4111e3";

    public byte[] getUrlBytes(String urlSpec) throws IOException {
        URL url = new URL(urlSpec);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream();
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new IOException(connection.getResponseMessage() +
                        ": with " +
                        urlSpec);
            }
            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, bytesRead);
            }
            out.close();
            return out.toByteArray();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    public String getUrlString(String urlSpec) throws IOException {
        return new String(getUrlBytes(urlSpec));
    }

    public List<News> fetchItems() {
        List<News> items = new ArrayList<>();
        try {
            String url = Uri.parse("https://content.guardianapis.com/search?&show-tags=contributor&api-key=70cb289e-b0e8-46ca-a590-a7fa1a4111e3")
                    .buildUpon()
                    .appendQueryParameter("api_key", API_KEY)
                    .appendQueryParameter("format", "json")
                    .appendQueryParameter("extras", "webUrl")
                    .build().toString();
            String jsonString = getUrlString(url);
            Log.i(TAG, "Received JSON: " + jsonString);
            JSONObject jsonBody = new JSONObject(jsonString);
            parseItems(items, jsonBody);
        } catch (IOException ioe) {
            Log.e(TAG, "Failed to fetch items", ioe);
        } catch (JSONException je) {
            Log.e(TAG, "Failed to parse JSON", je);
        }
        return items;
    }

    private void parseItems(List<News> items, JSONObject jsonBody) throws IOException, JSONException {
        JSONObject responseJsonObject = jsonBody.getJSONObject("response");
        JSONArray resultsJsonArray = responseJsonObject.getJSONArray("results");

        for (int i = 0; i < resultsJsonArray.length(); i++) {
            JSONObject newsJsonObject = resultsJsonArray.getJSONObject(i);

            News item = new News();
            item.setSectionName(newsJsonObject.getString("sectionName"));
            item.setmId(newsJsonObject.getString("id"));
            item.setWebTitle(newsJsonObject.getString("webTitle"));
            if (!newsJsonObject.has("webUrl")) {
                continue;
            }
            item.setWebUrl(newsJsonObject.getString("webUrl"));
            if (newsJsonObject.has("webPublicationDate")) {
                item.setWebPublicationDate(newsJsonObject.getString("webPublicationDate"));
            }
            JSONArray tags = newsJsonObject.getJSONArray("tags");
            if (tags.length() >= 0) {
                JSONObject contributor = tags.getJSONObject(0);
                if (contributor.has("webTitle")) {
                    item.setAuthor(contributor.getString("webTitle"));
                }
            }
            items.add(item);
        }
    }
}
