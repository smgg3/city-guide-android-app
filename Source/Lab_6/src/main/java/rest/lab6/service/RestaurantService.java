package rest.lab6.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import rest.lab6.restservice.utils.FourSquareUtils;

@Path("/")
public class RestaurantService {
	
	
	@Path("{food}")
	@GET
	@Produces("application/json")
	public Response getRestaurants(@PathParam("food") String food) throws Exception{
		FourSquareUtils fourSquareUtils = new FourSquareUtils();
		String result = fourSquareUtils.fourSquareAPI(food);
		return Response.status(200).entity(result).build();
	}

}
