
package rest.lab6.restservice.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import rest.lab6.constants.Constants;
public class WeatherUtils {
	
	public String callWetherAPI (String city) throws ClientProtocolException, IOException{
		String url = Constants.WEATHER_URL+"q="+city+"&appid="+Constants.WEATHER_KEY;
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet get = new HttpGet(url);
		CloseableHttpResponse resp = client.execute(get);
		BufferedReader reader = new BufferedReader(new InputStreamReader(resp.getEntity().getContent()));
		StringBuffer response = new StringBuffer();
		String inputLine = "";
        while ((inputLine = reader.readLine()) != null) {
            response.append(inputLine);
        }
        reader.close();
        client.close();
		return response.toString();
	}
}
