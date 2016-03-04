package rest.lab6.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import rest.lab6.restservice.utils.WeatherUtils;

@Path("/")
public class WeatherService {
	
	
	
	@Path("{city}")
	@GET
	@Produces("application/json")
	public Response getWeather (@PathParam("city") String city) throws Exception{
		WeatherUtils weatherUtils = new WeatherUtils();
		String result = weatherUtils.callWetherAPI(city);
		return Response.status(200).entity(result).build();
	}
	
}
