package MiscellaneousClasses;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Joey Francisco
 */
public class DatabaseQuery 
{
    URL url;
    HttpURLConnection connection;
    String APP_ID = "4GCD5XK7GucFbTKnJa0fonFEBlAh3azBS3Gh0NNd";
    String REST_API_KEY = "RYznH1yrJ3DVly2f02aEMkZJNwmPVdDBUQyqRT6H";
    public DatabaseQuery(String url) throws Exception
    {
        this.url = new URL(url);
        connection = (HttpURLConnection) this.url.openConnection();   
    }
    
    public ObservableList<String> RetrieveComboboxData(String method) throws Exception
    {
        ObservableList<String> list = FXCollections.observableArrayList();
        connection.setRequestMethod(method);
        connection.setRequestProperty("X-Parse-Application-Id", APP_ID);
        connection.setRequestProperty("X-Parse-REST-API-Key", REST_API_KEY);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuffer response = new StringBuffer();
        String inputline;
        while((inputline = bufferedReader.readLine()) != null)
        {
            response.append(inputline);
        }
        JSONObject obj = new JSONObject(response.toString());
        JSONArray array = obj.getJSONArray("results");
        for(int position = 0; position < array.length(); position++)
        {
            list.add(array.getJSONObject(position).getString("Title"));
        }
        return list;
    }
    
}
