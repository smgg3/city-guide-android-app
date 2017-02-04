/**
 * 
 */
package domain.muktevi.cityguide.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import domain.muktevi.cityguide.beans.TwitterTrendsSO;
import domain.muktevi.cityguide.utils.TwitterTrendUtils;
import twitter4j.TwitterException;

/**
 * @author Muktevi
 *
 */
@Component
@Path("/")
public class TrendService {

	@Autowired
	private TwitterTrendUtils twitterTrendUtils;
	
	
	private Gson gson;
	
	@GET
	@Path("{woeid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTrends (@PathParam ("woeid") Integer woeid) throws TwitterException{
		
		List<String> trendList = twitterTrendUtils.getTrends(woeid);
		TwitterTrendsSO twitterTrendsSO = new TwitterTrendsSO();
		twitterTrendsSO.setTrends(trendList);
		gson = new Gson();
		String trendsJson = gson.toJson(twitterTrendsSO);
		return Response.ok(trendsJson).build();
	}
}
