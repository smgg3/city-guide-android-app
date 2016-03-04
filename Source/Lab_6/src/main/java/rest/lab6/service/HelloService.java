package rest.lab6.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

@Path("/")
public class HelloService {
	
	@GET
	@Path("/name")
	@Produces("application/json")
	public Response sayHello() throws JSONException{
		
		JSONObject json = new JSONObject();
		json.put("Name", "Sravani");
		String msg = "Hi"+json;
		return Response.status(200).entity(msg).build();
	}
	

}
