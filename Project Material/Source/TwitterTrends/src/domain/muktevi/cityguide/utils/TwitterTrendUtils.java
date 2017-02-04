/**
 * 
 */
package domain.muktevi.cityguide.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import domain.muktevi.cityguide.constants.TwitterTrendsConstants;
import twitter4j.Trend;
import twitter4j.Trends;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

/**
 * @author Muktevi
 *
 */
@Component
public class TwitterTrendUtils {

	private static Twitter twitter = null;
	
	public List<String> getTrends(int woeid) throws TwitterException{
		ConfigurationBuilder cb = new ConfigurationBuilder();
		 cb.setDebugEnabled(true);
		 cb.setOAuthConsumerKey(TwitterTrendsConstants.CUSTOMER_KEY);
		 cb.setOAuthConsumerSecret(TwitterTrendsConstants.CUSTOMER_SECRET);
		 cb.setOAuthAccessToken(TwitterTrendsConstants.ACCESS_TOKEN);
		 cb.setOAuthAccessTokenSecret(TwitterTrendsConstants.ACCESS_SECRET);
		 
		Configuration conf = cb.build();
		 
		twitter = new TwitterFactory(conf).getInstance();
		List<String> trends= getTrendsByWoeid(woeid);
		return trends;
	}

	private List<String> getTrendsByWoeid(int woeid) throws TwitterException {
		List<String> trendNames = new ArrayList<String>();
		 Trends trends = twitter.getPlaceTrends(2459115);
		 Trend[] trend = trends.getTrends();
		 for (Trend t : trend) {
		 trendNames.add(t.getName());
		 }
		 return trendNames;
	}
}
